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

  public void saveDefaultTaskBehaviourWhenClickingOnLineInTaskList(String taskBehaviour) {
    IUser user = getSessionUser();
    user.setProperty(UserProperty.DEFAULT_TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST, taskBehaviour);
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

  private IUser getSessionUser() {
    return Ivy.session().getSessionUser();
  }
}
