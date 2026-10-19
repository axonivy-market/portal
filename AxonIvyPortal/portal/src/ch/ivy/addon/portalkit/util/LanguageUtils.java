package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivyteam.ivy.language.LanguageManager;
import ch.ivyteam.ivy.security.ISecurityContext;

public final class LanguageUtils {

  private LanguageUtils() {}

  public static String getLocalizedName(List<DisplayName> names, String name) {
    if (names == null) {
      return name;
    }
    return getLocalizedName(names);
  }

  /**
   * Resolve the display value for the current user. Blank values are treated as missing.
   * Fallback order: user language, application default content language, English, first non-blank value.
   */
  public static String getLocalizedName(List<DisplayName> names) {
    if (CollectionUtils.isEmpty(names)) {
      return "";
    }
    String userLanguage = LanguageService.getInstance().getUserLanguage();
    String systemLanguage = LanguageManager.instance().configurator(ISecurityContext.current()).content().toLanguageTag();
    return findNonBlankValueByLanguage(names, userLanguage)
        .or(() -> findNonBlankValueByLanguage(names, systemLanguage))
        .or(() -> findNonBlankValueByLanguage(names, Locale.ENGLISH.getLanguage()))
        .or(() -> names.stream().filter(Objects::nonNull).map(DisplayName::getValue).filter(StringUtils::isNotBlank).findFirst())
        .orElse("");
  }

  private static Optional<String> findNonBlankValueByLanguage(List<DisplayName> names, String language) {
    if (StringUtils.isBlank(language)) {
      return Optional.empty();
    }
    Optional<String> exactMatch = names.stream()
        .filter(name -> name != null && name.getLocale() != null && StringUtils.isNotBlank(name.getValue()))
        .filter(name -> Strings.CI.equals(name.getLocale().toLanguageTag(), language))
        .map(DisplayName::getValue).findFirst();
    if (exactMatch.isPresent()) {
      return exactMatch;
    }
    String languageCode = Locale.forLanguageTag(language).getLanguage();
    return names.stream()
        .filter(name -> name != null && name.getLocale() != null && StringUtils.isNotBlank(name.getValue()))
        .filter(name -> Strings.CI.equals(name.getLocale().getLanguage(), languageCode))
        .map(DisplayName::getValue).findFirst();
  }

  public static Optional<DisplayName> findNameInUserLanguage(List<DisplayName> names) {
    return findNameByLanguage(names, LanguageService.getInstance().getUserLanguage());
  }

  public static Optional<DisplayName> findNameByLanguage(List<DisplayName> names, String language) {
    return CollectionUtils.emptyIfNull(names).stream()
        .filter(name -> Strings.CI.equals(name.getLocale().toLanguageTag(), language)).findFirst();
  }

  public static NameResult collectMultilingualNames(List<DisplayName> names, String name) {
    names = ObjectUtils.getIfNull(names, new ArrayList<>());
    Optional<DisplayName> nameInUserLanguage = LanguageUtils.findNameInUserLanguage(names);
    if (nameInUserLanguage.isPresent()) {
      nameInUserLanguage.get().setValue(name);
    } else {
      DisplayName newName = new DisplayName(LanguageService.getInstance().getUserLocale(), name);
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
