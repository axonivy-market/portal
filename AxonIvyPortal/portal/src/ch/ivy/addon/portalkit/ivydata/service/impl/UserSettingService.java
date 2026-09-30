package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.ProcessMode;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class UserSettingService {

  private static final String PROCESS_MODE_CMS_PATH = "/ch.ivy.addon.portalkit.ui.jsf/Enums/ProcessMode/";
  public static final String DEFAULT = "DEFAULT";
  private static UserSettingService instance;

  private UserSettingService() {}

  public static UserSettingService newInstance() {
    return new UserSettingService();
  }

  public static UserSettingService getInstance() {
    if (instance == null) {
      instance = newInstance();
    }
    return instance;
  }

  public void saveProcessModeSetting(String processMode) {
    IUser user = getSessionUser();
    if (isDefaultProcessModeOption(processMode)) {
      user.removeProperty(UserProperty.DEFAULT_PROCESS_MODE);
    } else {
      user.setProperty(UserProperty.DEFAULT_PROCESS_MODE, processMode);
    }
  }

  public boolean isDefaultProcessModeOption(String processMode) {
    return DEFAULT.equals(processMode);
  }

  public String getDateFormat() {
    return getUserProperty(UserProperty.DATE_FORMAT);
  }
  
  /**
   * Returns the user's default process mode as a {@link ProcessMode} name, or empty if the user
   * has none (the global setting is then used).
   * Older versions stored the translated label (e.g. "Raster"). When such a value is found,
   * it is converted to the enum name and the user property is updated.
   */
  public String getDefaultProcessMode() {
    String userProcessMode = getUserProperty(UserProperty.DEFAULT_PROCESS_MODE);
    if (StringUtils.isBlank(userProcessMode)) {
      return StringUtils.EMPTY;
    }

    if (EnumUtils.isValidEnum(ProcessMode.class, userProcessMode)) {
      return userProcessMode;
    }

    String processMode = findProcessModeByLocalizedLabel(userProcessMode);
    if (StringUtils.isNotBlank(processMode)) {
      updateUserProperty(UserProperty.DEFAULT_PROCESS_MODE, processMode);
    }
    return processMode;
  }

  public String getDefaultProcessImage() {
    return getUserProperty(UserProperty.DEFAULT_PROCESS_IMAGE);
  }

  private String getUserProperty(String property) {
    if (Ivy.session().isSessionUserUnknown()) {
      return StringUtils.EMPTY;
    }
    return getSessionUser().getProperty(property);
  }

  private IUser getSessionUser() {
    return Ivy.session().getSessionUser();
  }
  
  public void updateUserProperty(String property, String value) {
    IUser user = getSessionUser();
    if (user != null) {
      user.setProperty(property, value);
    }
  }
  
  public boolean isKeyboardShortcutsEnabled() {
    String isKeyboardShortcutsEnabled = getUserProperty(UserProperty.ENABLE_KEYBOARD_SHORTCUTS);
    if (StringUtils.isBlank(isKeyboardShortcutsEnabled)) {
      updateUserProperty(UserProperty.ENABLE_KEYBOARD_SHORTCUTS, Boolean.FALSE.toString());
      return Boolean.FALSE;
    }
    return Boolean.parseBoolean(isKeyboardShortcutsEnabled);
  }

  private String findProcessModeByLocalizedLabel(String label) {
    List<Locale> locales = LanguageService.getInstance().getContentLocales();
    return Stream.of(ProcessMode.values())
        .filter(mode -> locales.stream()
            .anyMatch(locale -> label.equals(Ivy.cms().coLocale(PROCESS_MODE_CMS_PATH + mode.name(), locale))))
        .map(ProcessMode::name)
        .findFirst()
        .orElse(StringUtils.EMPTY);
  }
}
