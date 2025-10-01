package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Strings;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivyteam.ivy.environment.Ivy;

public final class LanguageUtils {

  private static final String DEFAULT_LANGUAGE = "en";

  private LanguageUtils() {}

  public static String getLocalizedName(List<DisplayName> names, String name) {
    if (names == null) {
      return name;
    }
    return getLocalizedName(names);
  }

  public static String getLocalizedName(List<DisplayName> names) {
    if (CollectionUtils.isEmpty(names)) {
      return "";
    }
    
    Locale userLocale = getUserLocale();
    
    // 1. Try full locale match (including country variants)
    String nameInFullLocale = findNameByFullLocale(names, userLocale).map(DisplayName::getValue).orElse(null);
    if (nameInFullLocale != null) {
      return nameInFullLocale;
    }
    
    // 2. Try language code match (ignoring country)
    String nameInLanguage = findNameByLanguageCode(names, userLocale.getLanguage()).map(DisplayName::getValue).orElse(null);
    if (nameInLanguage != null) {
      return nameInLanguage;
    }
    
    // 3. Try default language (English)
    return findNameByLanguageCode(names, DEFAULT_LANGUAGE).map(DisplayName::getValue).orElse("");
  }

  public static Optional<DisplayName> findNameInUserLanguage(List<DisplayName> names) {
    return findNameByLanguage(names, getUserLocale().getLanguage());
  }

  public static Optional<DisplayName> findNameByLanguage(List<DisplayName> names, String language) {
    return CollectionUtils.emptyIfNull(names).stream()
        .filter(name -> Strings.CI.equals(name.getLocale().toLanguageTag(), language)).findFirst();
  }

  public static Optional<DisplayName> findNameByFullLocale(List<DisplayName> names, Locale locale) {
    return CollectionUtils.emptyIfNull(names).stream()
        .filter(name -> locale.equals(name.getLocale())).findFirst();
  }

  public static Optional<DisplayName> findNameByLanguageCode(List<DisplayName> names, String languageCode) {
    return CollectionUtils.emptyIfNull(names).stream()
        .filter(name -> Strings.CI.equals(name.getLocale().getLanguage(), languageCode)).findFirst();
  }

  public static String getUserLanguage() {
    return getUserLocale().toLanguageTag();
  }

  public static Locale getUserLocale() {
    return Ivy.session().getContentLocale();
  }

  public static NameResult collectMultilingualNames(List<DisplayName> names, String name) {
    names = ObjectUtils.getIfNull(names, new ArrayList<>());
    Optional<DisplayName> nameInUserLanguage = LanguageUtils.findNameInUserLanguage(names);
    if (nameInUserLanguage.isPresent()) {
      nameInUserLanguage.get().setValue(name);
    } else {
      Locale userLocale = convertLocaleWithCountryToGenericLocale(getUserLocale());
      DisplayName newName = new DisplayName(userLocale, name);
      names.add(newName);
    }
    return new NameResult(names, name);
  }

  public static record NameResult(List<DisplayName> names, String name) {
    public NameResult(List<DisplayName> names, String name) {
      this.names = names;
      this.name = name;
    }
  }
  
  private static Locale convertLocaleWithCountryToGenericLocale(Locale locale) {
    if (locale.getCountry().isEmpty()) {
      return locale;
    }
    return LocaleUtils.toLocale(locale.getLanguage());
  }
  
  public static List<String> getSupportedLanguages() {
    return LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
  }
  
  public static Locale getSupportedLocale(Locale userLocale) {
    String userLanguage = userLocale.getLanguage();
    List<String> supportedLanguages = LanguageUtils.getSupportedLanguages();
    
    List<Locale> supportedLocales = supportedLanguages.stream()
        .map(LocaleUtils::toLocale)
        .collect(Collectors.toList());

    // 1. Try exact locale match
    if (supportedLocales.contains(userLocale)) {
        return userLocale;
    }
    
    // 2. Try language-only match
    if (supportedLanguages.contains(userLanguage)) {
        return LocaleUtils.toLocale(userLanguage);
    }
    
    // 3. Fall back to English
    return LocaleUtils.toLocale(DEFAULT_LANGUAGE);
  }
  
  public static boolean isSupportedLocale(Locale locale) {
    return getSupportedLanguages().contains(locale.getLanguage());
  }
}
