package ch.ivy.addon.portalkit.support;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import ch.ivyteam.ivy.environment.Ivy;

public class HtmlParser {

  private static String NO_DESC = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/noDescription");

  public static String sanitizeHTML(String text) {
    String sanitizedText = sanitize(text);
    if (StringUtils.isBlank(parseTextFromHtml(text))) {
      return NO_DESC;
    }
    return sanitizedText;
  }

  public static String extractTextFromHtml(String text) {
    String extractedText = parseTextFromHtml(text);
    if (StringUtils.isBlank(extractedText)) {
      return NO_DESC;
    }
    return extractedText;
  }

  private static String parseTextFromHtml(String text) {
    String sanitizedText = sanitize(text);
    Document doc = Jsoup.parse(sanitizedText);
    return doc.body().text();
  }

  private static String sanitize(String text) {
    return Jsoup.clean(text, Whitelist.relaxed().addAttributes(":all", "style"));
  }
}
