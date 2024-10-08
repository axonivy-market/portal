package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivyteam.ivy.environment.Ivy;
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

  public static String getLocalizedName(List<DisplayName> names) {
    if (CollectionUtils.isEmpty(names)) {
      return "";
    }
    String nameInUserLanguage = findNameInUserLanguage(names).map(DisplayName::getValue).orElse(null);
    if (nameInUserLanguage != null) {
      return nameInUserLanguage;
    }
    String systemLanguage = LanguageManager.instance().configurator(ISecurityContext.current()).content().toString();
    String nameInSystemLanguage = findNameByLanguage(names, systemLanguage).map(DisplayName::getValue).orElse(null);
    if (nameInSystemLanguage != null) {
      return nameInSystemLanguage;
    }
    return CollectionUtils.emptyIfNull(names).stream().map(DisplayName::getValue).filter(Objects::nonNull).findFirst()
        .orElse("");
  }

  public static Optional<DisplayName> findNameInUserLanguage(List<DisplayName> names) {
    return findNameByLanguage(names, getUserLanguage());
  }

  public static Optional<DisplayName> findNameByLanguage(List<DisplayName> names, String language) {
    return CollectionUtils.emptyIfNull(names).stream()
        .filter(name -> StringUtils.equalsIgnoreCase(name.getLocale().toLanguageTag(), language)).findFirst();
  }

  public static String getUserLanguage() {
    return getUserLocale().toLanguageTag();
  }

  public static Locale getUserLocale() {
    return Ivy.session().getContentLocale();
  }

  public static NameResult collectMultilingualNames(List<DisplayName> names, String name) {
    names = ObjectUtils.defaultIfNull(names, new ArrayList<>());
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
