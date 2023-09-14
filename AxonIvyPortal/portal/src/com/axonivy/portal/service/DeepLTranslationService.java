package com.axonivy.portal.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import com.axonivy.portal.components.service.IvyAdapterService;
import com.deepl.api.v2.client.InlineResponse200;
import com.deepl.api.v2.client.SourceLanguage;
import com.deepl.api.v2.client.TargetLanguage;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.UserUtils;

public class DeepLTranslationService {
  private static DeepLTranslationService instance;

  public static DeepLTranslationService getInstance() {
    if (instance == null) {
      instance = new DeepLTranslationService();
    }
    return instance;
  }

  public String translate(String text, Locale source, Locale target) {
    String translatedText = Strings.EMPTY;
    if (StringUtils.isNotBlank(text)) {
      Map<String, Object> params = new HashMap<>();
      params.put("text", text);
      params.put("targetLanguage", getTargetLanguageFromValue(target.getLanguage().toUpperCase()));
      params.put("sourceLanguage", getSourceLanguageFromValue(source.getLanguage().toUpperCase()));
      params.put("preserveFormatting", "1");
      params.put("tagHandling", "html");
      Map<String, Object> response = null;
      response = IvyAdapterService.startSubProcessInProjectAndAllRequired(
          "translateText(String,com.deepl.api.v2.client.TargetLanguage,com.deepl.api.v2.client.SourceLanguage,String,String)", params);
      if (response != null) {
        InlineResponse200 inlineResponse = (InlineResponse200) response.get("translation");
        translatedText = inlineResponse.getTranslations().get(0).getText();
      }
    }
    return translatedText;
  }

  private TargetLanguage getTargetLanguageFromValue(String language) {
    if (Locale.ENGLISH.getLanguage().equalsIgnoreCase(language)) {
      return TargetLanguage.EN_US;
    }
    return TargetLanguage.fromValue(language);
  }

  private SourceLanguage getSourceLanguageFromValue(String language) {
    if (Locale.ENGLISH.getLanguage().equalsIgnoreCase(language)) {
      return SourceLanguage.EN;
    }
    return SourceLanguage.fromValue(language);
  }

  public boolean isShowTranslation(Locale language) {
    String deepLAuthKey = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.DEEPL_AUTH_KEY);
    boolean enableDeepL = GlobalSettingService.getInstance()
        .findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_DEEPL_TRANSLATION);
    boolean isShow = !language.getLanguage().equals(UserUtils.getUserLanguage()) && enableDeepL
        && StringUtils.isNotBlank(deepLAuthKey);
    return isShow;
  }

}
