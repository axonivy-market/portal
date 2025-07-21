package ch.ivy.addon.portalkit.ivydata.service.impl;

import org.apache.commons.lang3.StringUtils;


import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class UserSettingService {

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
    return StringUtils.startsWithIgnoreCase(processMode,
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/MyProfile/defaultOption").replace("({0})", ""));
  }

  public String getDateFormat() {
    return getUserProperty(UserProperty.DATE_FORMAT);
  }

  public String getDefaultProcessMode() {
    return getUserProperty(UserProperty.DEFAULT_PROCESS_MODE);
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
  
  public void updateUserProperty(String property, String value) {
    IUser user = getSessionUser();
    if (user != null) {
      user.setProperty(property, value);
    }
  }

  private IUser getSessionUser() {
    return Ivy.session().getSessionUser();
  }
  
  public boolean isKeyboardShortcutsEnabled() {
    String isKeyboardShortcutsEnabled = getUserProperty(UserProperty.ENABLE_KEYBOARD_SHORTCUTS);
    if (StringUtils.isBlank(isKeyboardShortcutsEnabled)) {
      updateUserProperty(UserProperty.ENABLE_KEYBOARD_SHORTCUTS, Boolean.TRUE.toString());
      return Boolean.TRUE;
    }
    return Boolean.parseBoolean(isKeyboardShortcutsEnabled);
  }
}
