package ch.ivy.addon.portalkit.test.util;

import org.primefaces.util.HtmlSanitizer;

public class HtmlUtils {
  public static String sanitize(String text) {
    // Not use Jsoup.clean(text, Safelist.relaxed().addAttributes(":all", "style",
    // "class")) because the result is href
    // of tag a does not allow relative path. If allowing all protocols of tag a
    // then it could lead to XSS
    return text == null ? null : HtmlSanitizer.sanitizeHtml(text, true, true, true, true, true);
  }
}
