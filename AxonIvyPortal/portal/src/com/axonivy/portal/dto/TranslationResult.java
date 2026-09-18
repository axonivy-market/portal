package com.axonivy.portal.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import ch.ivy.addon.portalkit.dto.DisplayName;

public class TranslationResult implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final TranslationResult EMPTY = new TranslationResult(Strings.EMPTY, Strings.EMPTY);

  private final String translatedText;
  private final String warningText;

  public TranslationResult(String translatedText, String warningText) {
    this.translatedText = StringUtils.defaultString(translatedText);
    this.warningText = StringUtils.defaultString(warningText);
  }

  public String getTranslatedText() {
    return translatedText;
  }

  public String getWarningText() {
    return warningText;
  }

  public TranslationResult applyTo(DisplayName target) {
    if (StringUtils.isBlank(translatedText)) {
      return this;
    }
    target.setValue(translatedText);
    return new TranslationResult(Strings.EMPTY, warningText);
  }

}
