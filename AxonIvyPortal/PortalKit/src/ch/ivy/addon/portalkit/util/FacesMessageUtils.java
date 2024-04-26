package ch.ivy.addon.portalkit.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public class FacesMessageUtils {

  public static FacesMessage message(String summary) {
    return new FacesMessage(summary);
  }

  public static FacesMessage message(String summary, String detail) {
    return new FacesMessage(summary, detail);
  }

  public static FacesMessage message(Severity severity, String summary, String detail) {
    return new FacesMessage(severity, summary, detail);
  }

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
