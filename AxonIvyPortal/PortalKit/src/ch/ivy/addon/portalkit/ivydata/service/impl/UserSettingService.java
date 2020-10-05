package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivyteam.ivy.security.IUser;

public class UserSettingService {

  private static final String DEFAULT_SORT_FIELD_OF_TASK_LIST = "defaultSortFieldOfTaskList";
  private static final String DEFAULT_SORT_DIRECTION_OF_TASK_LIST = "defaultSortDirectionOfTaskList";
  private static final String DEFAULT_SORT_FIELD_OF_CASE_LIST = "defaultSortFieldOfCaseList";
  private static final String DEFAULT_SORT_DIRECTION_OF_CASE_LIST = "defaultSortDirectionOfCaseList";
  private static final String DEFAULT = "DEFAULT";

  private UserSettingService() {}

  public static UserSettingService newInstance() {
    return new UserSettingService();
  }

  public void saveDefaultSortFieldOfTaskList(String username, String fieldName, String sortDirection) {
    String fieldToSave = fieldName.contentEquals(DEFAULT) ? "" : fieldName;
    String directionToSave = fieldName.contentEquals(DEFAULT) ? "" : fieldName;

    IUser user = ServiceUtilities.findUser(username, getServer().getApplicationConfigurationManager().getCurrentApplication());
    user.setProperty(DEFAULT_SORT_FIELD_OF_TASK_LIST, fieldToSave);
    user.setProperty(DEFAULT_SORT_DIRECTION_OF_TASK_LIST, directionToSave);
  }

  public String getDefaultSortFieldOfTaskList(String username) {
    IUser user = ServiceUtilities.findUser(username, getServer().getApplicationConfigurationManager().getCurrentApplication());
    if (user == null) {
      return "";
    }
    return user.getProperty(DEFAULT_SORT_FIELD_OF_TASK_LIST);
  }

  public String getDefaultSortDirectionOfTaskList(String username) {
    IUser user = ServiceUtilities.findUser(username, getServer().getApplicationConfigurationManager().getCurrentApplication());
    if (user == null) {
      return "";
    }
    return user.getProperty(DEFAULT_SORT_DIRECTION_OF_TASK_LIST);
  }

  public void saveDefaultSortFieldOfCaseList(String username, String fieldName, String sortDirection) {
    String fieldToSave = fieldName.contentEquals(DEFAULT) ? "" : fieldName;
    String directionToSave = fieldName.contentEquals(DEFAULT) ? "" : fieldName;

    IUser user = ServiceUtilities.findUser(username, getServer().getApplicationConfigurationManager().getCurrentApplication());
    user.setProperty(DEFAULT_SORT_FIELD_OF_CASE_LIST, fieldToSave);
    user.setProperty(DEFAULT_SORT_DIRECTION_OF_CASE_LIST, directionToSave);
  }

  public String getDefaultSortFieldOfCaseList(String username) {
    IUser user = ServiceUtilities.findUser(username, getServer().getApplicationConfigurationManager().getCurrentApplication());
    if (user == null) {
      return "";
    }
    return user.getProperty(DEFAULT_SORT_FIELD_OF_CASE_LIST);
  }

  public String getDefaultSortDirectionOfCaseList(String username) {
    IUser user = ServiceUtilities.findUser(username, getServer().getApplicationConfigurationManager().getCurrentApplication());
    if (user == null) {
      return "";
    }
    return user.getProperty(DEFAULT_SORT_DIRECTION_OF_CASE_LIST);
  }
}
