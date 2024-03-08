package com.axonivy.portal.components.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.primefaces.util.HtmlSanitizer;

public class HtmlUtils {

  public static String parseTextFromHtml(String text) {
    String sanitizedText = sanitize(text);
    Document doc = Jsoup.parse(sanitizedText);
    return doc.body().text();
  }

  public static String sanitize(String text) {
    // Not use Jsoup.clean(text, Safelist.relaxed().addAttributes(":all", "style", "class")) because the result is href
    // of tag a does not allow relative path. If allowing all protocols of tag a then it could lead to XSS
    return text == null ? null : HtmlSanitizer.sanitizeHtml(text, true, true, true, true, true);
  }
}
