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

  public void saveDefaultSortFieldOfTaskList(String fieldName, String sortDirection) {
    IUser user = getSessionUser();
    if (StringUtils.equals(fieldName, DEFAULT)) {
      user.removeProperty(UserProperty.DEFAULT_SORT_FIELD_OF_TASK_LIST);
      user.removeProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_TASK_LIST);
    }
    
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
    if (StringUtils.equals(fieldName, DEFAULT)) {
      user.removeProperty(UserProperty.DEFAULT_SORT_FIELD_OF_CASE_LIST);
      user.removeProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_CASE_LIST);
    }

    user.setProperty(UserProperty.DEFAULT_SORT_FIELD_OF_CASE_LIST, fieldName);
    user.setProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_CASE_LIST, sortDirection);

    UserUtils.setSessionCaseSortAttribute(null);
  }

  public String getDefaultSortFieldOfCaseList() {
    return getUserProperty(UserProperty.DEFAULT_SORT_FIELD_OF_CASE_LIST);
  }

  public String getDefaultSortDirectionOfCaseList() {
    return getUserProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_CASE_LIST);
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
