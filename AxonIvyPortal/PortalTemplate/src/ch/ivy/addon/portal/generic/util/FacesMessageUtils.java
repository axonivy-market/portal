package ch.ivy.addon.portal.generic.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public class FacesMessageUtils {

  public static FacesMessage sanitizedMessage(String summary) {
    return new FacesMessage(HtmlUtils.sanitize(summary));
  }

  public static FacesMessage sanitizedMessage(String summary, String detail) {
    return new FacesMessage(HtmlUtils.sanitize(summary), HtmlUtils.sanitize(detail));
  }

  public static FacesMessage sanitizedMessage(Severity severity, String summary, String detail) {
    return new FacesMessage(severity, HtmlUtils.sanitize(summary), HtmlUtils.sanitize(detail));
  }
}

