package com.axonivy.portal.components.document;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * SVG scanner that focuses on blocking <em>script-like</em> behavior only.
 *
 * <p>It ALLOWS normal SVG structure (e.g. {@code <defs>}, {@code <style>}, classes) but rejects:</p>
 * <ul>
 *   <li>{@code <script>} and {@code <foreignObject>} elements</li>
 *   <li>SMIL animation elements ({@code <animate>}, {@code <set>}, {@code <animateTransform>},
 *       {@code <animateMotion>}, {@code <animateColor>}) — can install on* handlers or rewrite
 *       href to javascript: at render time</li>
 *   <li>Internal DTD {@code <!ENTITY>} declarations — jsoup does not expand them but browsers do</li>
 *   <li>Any on* event handler attributes (e.g. onload, onmouseover)</li>
 *   <li>javascript:, vbscript:, or data: URIs in href/xlink:href and CSS url(...)</li>
 *   <li>CSS expression(...)</li>
 * </ul>
 * <p>Elements are matched by local name so namespace-prefixed forms (e.g. {@code svg:script}) are
 * caught. URL schemes are canonicalized to detect control-character obfuscation.</p>
 */
public class SVGSecurityScanner implements DocumentDetector {

  private static final Pattern DANGEROUS_SCHEME =
      Pattern.compile("^\\s*(javascript|vbscript|data)\\s*:", Pattern.CASE_INSENSITIVE);
  private static final Pattern ENTITY_DECL = Pattern.compile("<!ENTITY", Pattern.CASE_INSENSITIVE);
  private static final Pattern ENCODED_CONTROL_CHAR =
      Pattern.compile("%(0[0-9a-f]|1[0-9a-f]|7f)", Pattern.CASE_INSENSITIVE);
  private static final Pattern CSS_URL =
      Pattern.compile("url\\s*\\(\\s*(['\"]?)(.*?)\\1\\s*\\)", Pattern.CASE_INSENSITIVE);
  private static final Pattern CSS_EXPRESSION = Pattern.compile("expression\\s*\\(", Pattern.CASE_INSENSITIVE);

  private static final Set<String> HREF_ATTRS = Set.of("href", "xlink:href");

  private static final Set<String> FORBIDDEN_ELEMENTS = Set.of(
      "script", "foreignobject",
      "animate", "animatemotion", "animatetransform", "animatecolor", "set");

  public static boolean isSafe(String svgContent) {
    try {
      if (ENTITY_DECL.matcher(svgContent).find()) {
        return false;
      }

      Document doc = Jsoup.parse(svgContent, "", org.jsoup.parser.Parser.xmlParser());

      for (Element el : doc.getAllElements()) {
        if (FORBIDDEN_ELEMENTS.contains(localName(el))) {
          return false;
        }
        for (Attribute attr : el.attributes()) {
          String key = attr.getKey().toLowerCase(Locale.ROOT);
          String val = attr.getValue();

          if (key.startsWith("on")) {
            return false;
          }

          if (HREF_ATTRS.contains(key) || key.endsWith(":href")) {
            if (hasDangerousScheme(val)) {
              return false;
            }
          }

          if ("style".equals(key)) {
            String valLower = val.toLowerCase(Locale.ROOT).trim();
            if (CSS_EXPRESSION.matcher(valLower).find()) {
              return false;
            }
            Matcher m = CSS_URL.matcher(valLower);
            while (m.find()) {
              if (hasDangerousScheme(m.group(2))) {
                return false;
              }
            }
          }
        }
      }

      for (Element style : doc.select("style")) {
        String cssData = style.data();
        String cssLower = (cssData.isEmpty() ? style.wholeOwnText() : cssData).toLowerCase(Locale.ROOT);
        if (CSS_EXPRESSION.matcher(cssLower).find()) {
          return false;
        }
        Matcher m = CSS_URL.matcher(cssLower);
        while (m.find()) {
          if (hasDangerousScheme(m.group(2))) {
            return false;
          }
        }
      }

      return true;
    } catch (Exception e) {
      Ivy.log().error("SVG security check failed", e);
      return false;
    }
  }

  /**
   * Returns true if the value resolves to a dangerous URL scheme (javascript:, vbscript:, data:).
   * Canonicalizes first so control-character obfuscation (e.g. {@code java&#9;script:},
   * {@code java%09script:}) is detected the same way a browser would.
   */
  private static boolean hasDangerousScheme(String value) {
    if (value == null) {
      return false;
    }
    return DANGEROUS_SCHEME.matcher(canonicalizeScheme(value)).find();
  }

  private static String canonicalizeScheme(String value) {
    String result = ENCODED_CONTROL_CHAR.matcher(value).replaceAll("");
    StringBuilder sb = new StringBuilder(result.length());
    for (int i = 0; i < result.length(); i++) {
      char c = result.charAt(i);
      if (c > 0x20 && c != 0x7f) {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  private static String localName(Element el) {
    String tag = el.tagName().toLowerCase(Locale.ROOT);
    int colon = tag.lastIndexOf(':');
    return colon >= 0 ? tag.substring(colon + 1) : tag;
  }

  @Override
  public boolean isSafe(InputStream inputStream) {
    try {
      String svg = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
          .collect(Collectors.joining("\n"));
      return isSafe(svg);
    } catch (Exception e) {
      Ivy.log().error("SVG security check failed", e);
      return false;
    }
  }
}
