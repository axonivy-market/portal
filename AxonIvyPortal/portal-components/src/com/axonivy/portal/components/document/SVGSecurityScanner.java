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
 * SVG scanner that focuses on blocking *script-like* behavior only.
 *
 * It ALLOWS normal SVG structure (e.g. <defs>, <style>, classes) but rejects: - <script> elements - any on* event
 * handler attributes (e.g. onload, onmouseover, ...) - javascript:, vbscript:, or data: URIs in href/xlink:href and CSS
 * url(...) - CSS expression( ... ) - <foreignObject> (can embed HTML/JS)
 */
public class SVGSecurityScanner implements DocumentDetector {

  private static final Pattern DANGEROUS_SCHEME =
      Pattern.compile("^\\s*(javascript|vbscript|data)\\s*:", Pattern.CASE_INSENSITIVE);
  private static final Pattern CSS_URL =
      Pattern.compile("url\\s*\\(\\s*(['\"]?)(.*?)\\1\\s*\\)", Pattern.CASE_INSENSITIVE);
  private static final Pattern CSS_EXPRESSION = Pattern.compile("expression\\s*\\(", Pattern.CASE_INSENSITIVE);

  private static final Set<String> HREF_ATTRS = Set.of("href", "xlink:href");

  // SMIL animation elements can dynamically (re)assign attributes at runtime - e.g. set the
  // parent's xlink:href to a javascript: URL, or define an on* handler - while hiding the target
  // attribute behind attributeName=. They are rejected when they animate a dangerous attribute.
  private static final Set<String> SMIL_ANIMATION_ELEMENTS =
      Set.of("set", "animate", "animatetransform", "animatemotion");

  // Percent-encoded ASCII control/whitespace bytes (e.g. %09 TAB, %0A LF, %7F DEL) that browsers
  // strip from URL schemes; removed during canonicalization so encoded scheme splits are detected.
  private static final Pattern ENCODED_CONTROL_CHAR =
      Pattern.compile("%(0[0-9a-f]|1[0-9a-f]|7f)", Pattern.CASE_INSENSITIVE);

  public static boolean isSafe(String svgContent) {
    try {
      Document doc = Jsoup.parse(svgContent, "", org.jsoup.parser.Parser.xmlParser());

      if (!doc.select("script, foreignObject").isEmpty()) {
        return false;
      }
      for (Element el : doc.getAllElements()) {
        if (SMIL_ANIMATION_ELEMENTS.contains(el.tagName().toLowerCase(Locale.ROOT))) {
          String animatedAttr = el.attributes().getIgnoreCase("attributeName").toLowerCase(Locale.ROOT).trim();
          if (isDangerousAttributeName(animatedAttr)) {
            return false;
          }
        }

        for (Attribute attr : el.attributes()) {
          String key = attr.getKey().toLowerCase(Locale.ROOT);
          String val = attr.getValue();

          if (key.startsWith("on")) {
            return false;
          }

          if (HREF_ATTRS.contains(key) || key.endsWith(":href")) { // covers namespaced forms
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
        String cssLower = style.data().toLowerCase(Locale.ROOT);
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

  private static boolean isDangerousAttributeName(String attributeName) {
    return attributeName.startsWith("on") || HREF_ATTRS.contains(attributeName)
        || attributeName.endsWith(":href");
  }

  /**
   * Returns true if the value resolves to a dangerous URL scheme (javascript:, vbscript:, data:).
   * The value is canonicalized first so that schemes obfuscated with control characters or their
   * percent-encoded forms - e.g. "java&#9;script:" (TAB), "java&#10;script:" (LF), "java%09script:"
   * - are detected the same way a browser would after stripping those characters.
   */
  private static boolean hasDangerousScheme(String value) {
    if (value == null) {
      return false;
    }
    return DANGEROUS_SCHEME.matcher(canonicalizeScheme(value)).find();
  }

  private static String canonicalizeScheme(String value) {
    String lower = value.toLowerCase(Locale.ROOT);
    // Drop percent-encoded ASCII control/whitespace bytes (browsers ignore them inside schemes).
    lower = ENCODED_CONTROL_CHAR.matcher(lower).replaceAll("");
    // Drop raw ASCII whitespace and control characters - including TAB/LF/CR that jsoup already
    // decoded from numeric character references such as &#9; / &#10; - so a scheme split across
    // those characters collapses back to its canonical form before the dangerous-scheme check.
    StringBuilder canonical = new StringBuilder(lower.length());
    for (int i = 0; i < lower.length(); i++) {
      char c = lower.charAt(i);
      if (c > 0x20 && c != 0x7f) {
        canonical.append(c);
      }
    }
    return canonical.toString();
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
