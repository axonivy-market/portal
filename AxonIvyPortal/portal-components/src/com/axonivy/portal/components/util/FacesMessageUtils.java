package com.axonivy.portal.components.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.FacesMessage.Severity;

public final class FacesMessageUtils {
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
