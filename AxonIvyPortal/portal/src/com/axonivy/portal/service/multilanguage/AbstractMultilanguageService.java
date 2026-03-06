package com.axonivy.portal.service.multilanguage;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.UserUtils;

public abstract class AbstractMultilanguageService {

  protected abstract String getValue();

  protected abstract void setValue(String value);

  protected abstract List<DisplayName> getValues();

  private List<String> getSupportedLanguages() {
    return LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
  }

  private Map<String, DisplayName> getMapLanguages() {
    List<DisplayName> languages = getValues();
    return languages.stream().collect(Collectors.toMap(o -> o.getLocale().toLanguageTag(), o -> o));
  }

  public void initMultipleLanguagesForName(String name) {
    Map<String, DisplayName> mapLanguage = getMapLanguages();
    List<String> supportedLanguages = getSupportedLanguages();
    for (String language : supportedLanguages) {
      DisplayName localeLanguage = mapLanguage.get(language);
      if (localeLanguage == null) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        displayName.setValue(name);
        getValues().add(displayName);
      } else if (StringUtils.isBlank(localeLanguage.getValue())) {
        localeLanguage.setValue(name);
      }
    }
  }


  public List<DisplayName> getNames() {
    if (getValues().isEmpty()) {
      List<String> supportedLanguages = getSupportedLanguages();
      for (String language : supportedLanguages) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        getValues().add(displayName);
      }
    }
    return getValues();
  }

  private void updateForCurrentLanguage(List<DisplayName> names) {
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional =
        names.stream().filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    optional.ifPresent(displayName -> setValue(displayName.getValue()));

  }

  public void updateNameForCurrentLanguage() {
    updateForCurrentLanguage(getValues());
  }

  public void updateNameByLocale() {
    String currentName = LanguageUtils.getLocalizedName(getValues(), getValue());
    initAndSetValue(currentName, getValues());
  }

  private void initAndSetValue(String value, List<DisplayName> values) {
    DisplayNameConvertor.initMultipleLanguages(value, values);
    DisplayNameConvertor.setValue(value, values);
  }
}
