package com.axonivy.portal.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PortalSanitizeUtils {

  private static class SVGSecurityConfig {
    static final Set<String> DANGEROUS_ATTRS = new HashSet<>(Arrays.asList("onload", "onerror", "onclick",
        "onmouseover", "onmousedown", "onmouseup", "onmousemove", "onbegin", "onrepeat", "onend"));
    static final Set<String> DANGEROUS_ELEMENTS =
        new HashSet<>(Arrays.asList("script", "foreignObject", "animate", "animateTransform", "set"));
    static final Pattern DANGEROUS_URI_PATTERN = Pattern.compile("^\\s*(javascript|data):", Pattern.CASE_INSENSITIVE);
  }

  public static String sanitizeSvg(String svgContent) {
    Document doc = Jsoup.parse(svgContent, "", org.jsoup.parser.Parser.xmlParser());

    // Remove all script elements
    removeElements(doc, SVGSecurityConfig.DANGEROUS_ELEMENTS);

    // Sanitize attributes
    sanitizeAttributes(doc);

    return doc.outerHtml();
  }


  private static void removeElements(Document doc, Set<String> elementsToRemove) {
    for (String tag : elementsToRemove) {
      Elements elements = doc.getElementsByTag(tag);
      for (Element element : elements) {
        element.remove();
      }
    }
  }

  private static void sanitizeAttributes(Document doc) {
    for (Element element : doc.getAllElements()) {
      for (String attr : SVGSecurityConfig.DANGEROUS_ATTRS) {
        element.removeAttr(attr);
      }
      element.attributes().asList().stream()
          .filter(attr -> SVGSecurityConfig.DANGEROUS_URI_PATTERN.matcher(attr.getValue()).find())
          .collect(Collectors.toList()).forEach(attr -> element.removeAttr(attr.getKey()));
    }
  }
}

