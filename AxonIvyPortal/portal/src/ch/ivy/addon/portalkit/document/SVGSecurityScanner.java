package ch.ivy.addon.portalkit.document;

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

  // URI schemes that can execute code
  private static final Pattern DANGEROUS_SCHEME =
      Pattern.compile("^\\s*(javascript|vbscript|data)\\s*:", Pattern.CASE_INSENSITIVE);
  // CSS url(...) extractor
  private static final Pattern CSS_URL =
      Pattern.compile("url\\s*\\(\\s*(['\"]?)(.*?)\\1\\s*\\)", Pattern.CASE_INSENSITIVE);
  // CSS expression(...)
  private static final Pattern CSS_EXPRESSION = Pattern.compile("expression\\s*\\(", Pattern.CASE_INSENSITIVE);

  private static final Set<String> HREF_ATTRS = Set.of("href", "xlink:href");

  /** Convenience method for string content. */
  public static boolean isSafe(String svgContent) {
    try {
      // Parse as XML; jsoup resolves common entity escapes for attributes
      Document doc = Jsoup.parse(svgContent, "", org.jsoup.parser.Parser.xmlParser());

      // 1) Block elements that can execute/contain active script
      if (!doc.select("script, foreignObject").isEmpty()) {
        return false;
      }

      // 2) Walk attributes and styles
      for (Element el : doc.getAllElements()) {
        // 2a) Event handlers: any attribute starting with "on"
        for (Attribute attr : el.attributes()) {
          String key = attr.getKey().toLowerCase(Locale.ROOT);
          String val = attr.getValue();
          String valLower = val.toLowerCase(Locale.ROOT).trim();

          if (key.startsWith("on")) {
            return false;
          }

          // 2b) Dangerous schemes in href/xlink:href
          if (HREF_ATTRS.contains(key) || key.endsWith(":href")) { // covers namespaced forms
            if (DANGEROUS_SCHEME.matcher(valLower).find()) {
              return false;
            }
          }

          // 2c) Inline style attribute: check url(...) and expression(...)
          if ("style".equals(key)) {
            if (CSS_EXPRESSION.matcher(valLower).find()) {
              return false;
            }
            Matcher m = CSS_URL.matcher(valLower);
            while (m.find()) {
              String url = m.group(2).trim();
              String urlLower = url.toLowerCase(Locale.ROOT);
              if (DANGEROUS_SCHEME.matcher(urlLower).find()) {
                return false;
              }
            }
          }
        }
      }

      // 3) <style> blocks: scan CSS text for url(...) and expression(...)
      for (Element style : doc.select("style")) {
        String css = style.data(); // inside <style> ... CSS ... </style>
        String cssLower = css.toLowerCase(Locale.ROOT);
        if (CSS_EXPRESSION.matcher(cssLower).find()) {
          return false;
        }
        Matcher m = CSS_URL.matcher(cssLower);
        while (m.find()) {
          String url = m.group(2).trim();
          String urlLower = url.toLowerCase(Locale.ROOT);
          if (DANGEROUS_SCHEME.matcher(urlLower).find()) {
            return false;
          }
        }
      }

      return true; // no script-like constructs found
    } catch (Exception e) {
      Ivy.log().error("SVG security check failed", e);
      return false; // fail closed
    }
  }

  /** Implementation for DocumentDetector. */
  @Override
  public boolean isSafe(InputStream inputStream) {
    try {
      String svg = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
          .collect(Collectors.joining("\n"));
      return isSafe(svg);
    } catch (Exception e) {
      Ivy.log().error("SVG security check failed", e);
      return false; // fail closed
    }
  }
}
