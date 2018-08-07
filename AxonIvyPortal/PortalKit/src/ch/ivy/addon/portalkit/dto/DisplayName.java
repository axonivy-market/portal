package ch.ivy.addon.portalkit.dto;

import java.util.Locale;

public class DisplayName {

  private Locale locale;
  private String value;

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
