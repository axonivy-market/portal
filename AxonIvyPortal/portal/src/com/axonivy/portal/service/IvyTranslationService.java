package com.axonivy.portal.service;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.translation.service.TranslationService;

public class IvyTranslationService {
  private static IvyTranslationService instance;

  public static IvyTranslationService getInstance() {
    if (instance == null) {
      instance = new IvyTranslationService();
    }
    return instance;
  }

  public String translate(String text, Locale source, Locale target) {
    String translatedText = Strings.EMPTY;
    if (StringUtils.isBlank(text) || !TranslationService.isDefaultServiceEnabled()) {
      return translatedText;
    }
    TranslationService translationService = TranslationService.defaultService();
    translatedText = translationService.translate(text).from(source).parameter("preserveFormatting", "1")
        .parameter("tagHandling", "html").to(target);
    return translatedText;
  }

  public boolean isShowTranslation(Locale language) {
    return !language.getLanguage().equals(UserUtils.getUserLanguage()) && TranslationService.isDefaultServiceEnabled();
  }

}
