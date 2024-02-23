package ch.ivy.addon.portalkit.support;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import ch.ivyteam.ivy.environment.Ivy;

public class HtmlParser {
  public static String sanitizeHTML(String text) {
    String sanitizedText = com.axonivy.portal.components.util.HtmlUtils.sanitize(text);
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
    String sanitizedText = com.axonivy.portal.components.util.HtmlUtils.sanitize(text);
    Document doc = Jsoup.parse(sanitizedText);
    return doc.body().text();
  }
}
