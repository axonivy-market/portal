package com.axonivy.portal.service;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import com.axonivy.portal.dto.TranslationResult;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.translation.service.TranslationService;

public class IvyTranslationService {
  private static final String TRANSLATION_FAILED_CMS =
      "/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/SomeThingWentWrong";

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

  public TranslationResult translate(DisplayName target, List<DisplayName> values) {
    String currentLanguage = UserUtils.getUserLanguage();
    if (target.getLocale().getLanguage().equals(currentLanguage)) {
      return TranslationResult.EMPTY;
    }

    return CollectionUtils.emptyIfNull(values).stream().filter(value -> value.getLocale() != null)
        .filter(value -> currentLanguage.equals(value.getLocale().getLanguage())).findFirst()
        .map(source -> translateValue(source, target)).orElse(TranslationResult.EMPTY);
  }

  private TranslationResult translateValue(DisplayName source, DisplayName target) {
    try {
      return new TranslationResult(translate(source.getValue(), source.getLocale(), target.getLocale()), Strings.EMPTY);
    } catch (Exception e) {
      Ivy.log().error("Ivy Translation Service error: ", e.getMessage());
      return new TranslationResult(Strings.EMPTY, Ivy.cms().co(TRANSLATION_FAILED_CMS));
    }
  }

  public boolean isShowTranslation(Locale language) {
    boolean enableTranslationService = GlobalSettingService.getInstance()
        .findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_TRANSLATION_SERVICE);
    return !language.getLanguage().equals(UserUtils.getUserLanguage()) && TranslationService.isDefaultServiceEnabled()
        && enableTranslationService;
  }

}
