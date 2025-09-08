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

  public static boolean isSafe(String svgContent) {
    try {
      Document doc = Jsoup.parse(svgContent, "", org.jsoup.parser.Parser.xmlParser());

      if (!doc.select("script, foreignObject").isEmpty()) {
        return false;
      }
      for (Element el : doc.getAllElements()) {
        for (Attribute attr : el.attributes()) {
          String key = attr.getKey().toLowerCase(Locale.ROOT);
          String val = attr.getValue();
          String valLower = val.toLowerCase(Locale.ROOT).trim();

          if (key.startsWith("on")) {
            return false;
          }

          if (HREF_ATTRS.contains(key) || key.endsWith(":href")) { // covers namespaced forms
            if (DANGEROUS_SCHEME.matcher(valLower).find()) {
              return false;
            }
          }

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

      for (Element style : doc.select("style")) {
        String css = style.data();
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

      return true;
    } catch (Exception e) {
      Ivy.log().error("SVG security check failed", e);
      return false;
    }
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
