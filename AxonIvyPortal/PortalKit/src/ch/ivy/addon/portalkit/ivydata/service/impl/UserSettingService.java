package ch.ivy.addon.portalkit.ivydata.service.impl;

import org.apache.commons.codec.binary.StringUtils;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class UserSettingService {

  private static final String DEFAULT = "DEFAULT";

  private UserSettingService() {}

  public static UserSettingService newInstance() {
    return new UserSettingService();
  }

  public void saveDefaultSortFieldOfTaskList(String fieldName, String sortDirection) {
    IUser user = Ivy.session().getSessionUser();
    if (StringUtils.equals(fieldName, DEFAULT)) {
      user.removeProperty(UserProperty.DEFAULT_SORT_FIELD_OF_TASK_LIST);
      user.removeProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_TASK_LIST);
    }
    
    user.setProperty(UserProperty.DEFAULT_SORT_FIELD_OF_TASK_LIST, fieldName);
    user.setProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_TASK_LIST, sortDirection);
  }

  public String getDefaultSortFieldOfTaskList(String username) {
    return Ivy.session().getSessionUser().getProperty(UserProperty.DEFAULT_SORT_FIELD_OF_TASK_LIST);
  }

  public String getDefaultSortDirectionOfTaskList(String username) {
    return Ivy.session().getSessionUser().getProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_TASK_LIST);
  }

  public void saveDefaultSortFieldOfCaseList(String fieldName, String sortDirection) {
    IUser user = Ivy.session().getSessionUser();
    if (StringUtils.equals(fieldName, DEFAULT)) {
      user.removeProperty(UserProperty.DEFAULT_SORT_FIELD_OF_CASE_LIST);
      user.removeProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_CASE_LIST);
    }

    user.setProperty(UserProperty.DEFAULT_SORT_FIELD_OF_CASE_LIST, fieldName);
    user.setProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_CASE_LIST, sortDirection);
  }

  public String getDefaultSortFieldOfCaseList(String username) {
    return Ivy.session().getSessionUser().getProperty(UserProperty.DEFAULT_SORT_FIELD_OF_CASE_LIST);
  }

  public String getDefaultSortDirectionOfCaseList(String username) {
    return Ivy.session().getSessionUser().getProperty(UserProperty.DEFAULT_SORT_DIRECTION_OF_CASE_LIST);
  }
}
