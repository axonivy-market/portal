package com.axonivy.portal.smart.statistic.provider.dto;

import java.io.Serializable;

/** One field title in one language, as a language tag and the text. */
public class FieldTitleProposal implements Serializable {

  private static final long serialVersionUID = 1L;

  private String language;
  private String value;

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
