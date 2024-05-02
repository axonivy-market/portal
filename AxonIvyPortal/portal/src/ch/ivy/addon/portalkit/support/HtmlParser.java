package ch.ivy.addon.portalkit.support;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.HtmlUtils;

import ch.ivyteam.ivy.environment.Ivy;

public class HtmlParser {
  public static String sanitizeHTML(String text) {
    String sanitizedText = HtmlUtils.sanitize(text);
    if (StringUtils.isBlank(HtmlUtils.parseTextFromHtml(text))) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/noDescription");
    }
    return sanitizedText;
  }

  public static String extractTextFromHtml(String text) {
    String extractedText = HtmlUtils.parseTextFromHtml(text);
    if (StringUtils.isBlank(extractedText)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/noDescription");
    }
    return extractedText;
  }
}
