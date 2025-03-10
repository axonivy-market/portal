package com.axonivy.portal.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PortalSanitizeUtils {

  public static String sanitizeSvg(String svgContent) {
    Document doc = Jsoup.parse(svgContent, "", org.jsoup.parser.Parser.xmlParser());

    // Remove all <script> elements
    Elements scripts = doc.getElementsByTag("script");
    for (Element script : scripts) {
      script.remove();
    }

    return doc.outerHtml();
    }
}
