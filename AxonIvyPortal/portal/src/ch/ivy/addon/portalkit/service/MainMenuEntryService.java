package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.configuration.MainMenuEntry;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.DisplayNameAdaptor;
import ch.ivyteam.ivy.environment.Ivy;

public class MainMenuEntryService {
  private static final String MAIN_MENU_ENTRY = PortalVariable.DASHBOARD_MAIN_MENU_ENTRY.key;

  public MainMenuEntryService() {}

  public Class<MainMenuEntry> getType() {
    return MainMenuEntry.class;
  }

  public String getConfigKey() {
    return MAIN_MENU_ENTRY;
  }

  private MainMenuEntry getMainMenuEntry() {
    Optional<MainMenuEntry> mainMenuEntry = Optional.ofNullable(getPublicConfig());
    return mainMenuEntry.orElse(null);
  }

  public String getNameInCurrentLocale() {
    Locale currentLocale = Ivy.session().getContentLocale();
    MainMenuEntry mainMenuEntry = getMainMenuEntry();

    if (mainMenuEntry != null) {
      String displayName = getNameByLocale(mainMenuEntry.getNames(), currentLocale);
      DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(displayName, currentLocale);
      return displayNameAdaptor.getDisplayNameAsString();
    }
    return "";
  }

  public String getMenuIcon() {
    MainMenuEntry mainMenuEntry = getMainMenuEntry();
    if (mainMenuEntry != null) {
      return mainMenuEntry.getIcon();
    }
    return "";
  }

  private String getNameByLocale(List<DisplayName> displayNameList, Locale currentLocale) {

    if (CollectionUtils.isEmpty(displayNameList) || isNotValidDisplayName(displayNameList)
        || notContainsLocale(displayNameList, currentLocale)) {
      return "";
    }

    return displayNameList.stream().filter(item -> item.getLocale().equals(currentLocale)).map(DisplayName::getValue)
        .findFirst().orElse(null);
  }

  private boolean isNotValidDisplayName(List<DisplayName> displayNameList) {
    return displayNameList.stream().anyMatch(item -> item.getValue() == null || item.getLocale() == null);
  }

  private boolean notContainsLocale(List<DisplayName> displayNameList, Locale currentLocale) {
    return !displayNameList.stream().map(DisplayName::getLocale).collect(Collectors.toList()).contains(currentLocale);
  }

  private MainMenuEntry getPublicConfig() {
    String value = Ivy.var().get(getConfigKey());
    MainMenuEntry mainMenuEntryList = BusinessEntityConverter.jsonValueToEntity(value, MainMenuEntry.class);
    return mainMenuEntryList;
  }
}
