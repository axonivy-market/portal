package ch.ivy.addon.portalkit.support;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import ch.ivyteam.ivy.environment.Ivy;

public class HtmlParser {
  public static String sanitizeHTML(String text) {
    String sanitizedText = sanitize(text);
    if (StringUtils.isBlank(parseTextFromHtml(text))) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/noDescription");
    }
    return sanitizedText;
  }

  public static String extractTextFromHtml(String text) {
    String extractedText = parseTextFromHtml(text);
    if (StringUtils.isBlank(extractedText)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/noDescription");
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
