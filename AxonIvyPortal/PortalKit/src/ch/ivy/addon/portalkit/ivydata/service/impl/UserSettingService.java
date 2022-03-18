package ch.ivy.addon.portalkit.ivydata.service.impl;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class UserSettingService {

  public static final String DEFAULT = "DEFAULT";

  private UserSettingService() {}

  public static UserSettingService newInstance() {
    return new UserSettingService();
  }

  public void saveDateFormat(String selectedDateFormat) {
    String dateFormat = StringUtils.equals(selectedDateFormat, DEFAULT) ? getDefaultDateFormat() : selectedDateFormat;
    getSessionUser().setProperty(UserProperty.DATE_FORMAT, dateFormat);
  }

  public void saveDefaultSortFieldOfTaskList(String fieldName, String sortDirection) {
    IUser user = getSessionUser();
    user.setProperty(UserProperty.DEFAULT_SORT_FIELD_OF_TASK_LIST, fieldName);
    user.setProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_TASK_LIST, sortDirection);

    UserUtils.setSessionTaskSortAttribute(null);
  }

  public String getDefaultSortFieldOfTaskList() {
    return getUserProperty(UserProperty.DEFAULT_SORT_FIELD_OF_TASK_LIST);
  }

  public String getDefaultSortDirectionOfTaskList() {
    return getUserProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_TASK_LIST);
  }

  public void saveDefaultSortFieldOfCaseList(String fieldName, String sortDirection) {
    IUser user = getSessionUser();
    user.setProperty(UserProperty.DEFAULT_SORT_FIELD_OF_CASE_LIST, fieldName);
    user.setProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_CASE_LIST, sortDirection);

    UserUtils.setSessionCaseSortAttribute(null);
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

  public String getDefaultSortFieldOfCaseList() {
    return getUserProperty(UserProperty.DEFAULT_SORT_FIELD_OF_CASE_LIST);
  }

  public String getDefaultSortDirectionOfCaseList() {
    return getUserProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_CASE_LIST);
  }

  public String getDateFormat() {
    return getUserProperty(UserProperty.DATE_FORMAT);
  }

  public String getDefaultDateFormat() {
    return getUserProperty(UserProperty.DEFAULT_DATE_FORMAT);
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
