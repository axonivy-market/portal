package com.axonivy.portal.components.util;

import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

public class HtmlUtils {

  private static String HTML_PROTOCOL = "http:";
  private static final Pattern ICON_PATTERN = Pattern.compile("^(fa|si)\\s+(fa|si)-[a-zA-Z0-9\\-]+$");

  public static String parseTextFromHtml(String text) {
    String sanitizedText = sanitize(text);
    Document doc = Jsoup.parse(sanitizedText);
    return doc.body().text();
  }

  public static String sanitize(String text) {
    // Not use primefaces util HtmlSanitizer.sanitizeHtml(text, true, true, true,
    // true, true); because string special character will be converted to html
    // special character
    // Use Jsoup.clean(text, Safelist.relaxed().addAttributes(":all", "style",
    // "class")) and customize it to allow relative path for href of tag a
    return text == null ? null : Jsoup.clean(text, HTML_PROTOCOL, Safelist.relaxed().addAttributes(":all", "style", "class").preserveRelativeLinks(true));
  }

  public static String escapeForIcon(String input) {
    if (input == null) {
      return "";
    }

    String sanitizedIcon = input.trim();

    if (!ICON_PATTERN.matcher(sanitizedIcon).matches()) {
      return ""; // Default fallback to a harmless icon
    }
    return sanitizedIcon;
  }
}
