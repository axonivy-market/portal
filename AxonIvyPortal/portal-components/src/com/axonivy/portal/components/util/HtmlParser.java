package com.axonivy.portal.components.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class HtmlParser {
  public static String sanitize(String text) {
    return text == null ? null : Jsoup.clean(text, Safelist.relaxed().addAttributes(":all", "style", "class"));
  }
}
