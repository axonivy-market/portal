package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Strings;

import ch.ivy.addon.portalkit.dto.DisplayName;
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
    String nameInEnglish = findNameByLanguageCode(names, DEFAULT_LANGUAGE).map(DisplayName::getValue).orElse("");
    return nameInEnglish;
  }

  public static Optional<DisplayName> findNameInUserLanguage(List<DisplayName> names) {
    return findNameByFullLocale(names, getUserLocale());
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
      DisplayName newName = new DisplayName(LanguageUtils.getUserLocale(), name);
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
}
