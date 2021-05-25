package ch.ivy.addon.portalkit.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.ivy.addon.portalkit.bean.TaskWidgetBean;
import ch.ivy.addon.portalkit.document.DocumentExtensionConstants;
import ch.ivyteam.ivy.environment.Ivy;

public enum GlobalVariable {
  
  HIDE_LOGOUT_BUTTON(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideLogoutButtonNote"),
  SHOW_ENVIRONMENT_INFO(GlobalVariableType.SELECTION, Option.FALSE.toString(), "showEnvironmentInfoNote"),
  HIDE_CHANGE_PASSWORD_BUTTON(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideChangePasswordButtonNote"), 
  HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideUploadDocumentForDoneCaseNote"),
  ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT(GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableScriptCheckingForUploadedDocumentNote"),
  HIDE_TIME(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideTimeNote"),
  SHOW_TASK_DURATION_TIME(GlobalVariableType.SELECTION, Option.TRUE.toString(), "showTaskDurationTime"),
  SHOW_CASE_DURATION_TIME(GlobalVariableType.SELECTION, Option.TRUE.toString(), "showCaseDurationTime"),
  EXPRESS_END_PAGE(GlobalVariableType.SELECTION, Option.TRUE.toString(), "expressEndPageNote"),
  REFRESH_TASK_LIST_INTERVAL(GlobalVariableType.NUMBER, String.valueOf(TaskWidgetBean.DEFAULT_TASK_LIST_REFRESH_INTERVAL), "refreshTaskListIntervalNote"),
  UPLOAD_DOCUMENT_WHITELIST_EXTENSION(GlobalVariableType.TEXT, String.join(", ", DocumentExtensionConstants.DEFAULT_WHITELIST_EXTENSION), "uploadDocumentWhiteListExtensionNote"),
  CLIENT_SIDE_TIMEOUT(GlobalVariableType.NUMBER, "clientSideTimeoutNote"),
  HIDE_SYSTEM_TASKS_FROM_HISTORY(GlobalVariableType.SELECTION, Option.TRUE.toString(), "hideSystemTasksFromHistory"),
  HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideSystemTasksFromHistoryAdministrator"),
  HIDE_SYSTEM_NOTES_FROM_HISTORY(GlobalVariableType.SELECTION, Option.TRUE.toString(), "hideSystemNotesFromHistory"),
  HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideSystemNotesFromHistoryAdministrator"),
  ENABLE_USER_FAVORITES(GlobalVariableType.SELECTION, Option.TRUE.toString(), "enableUserFavorites"),
  HIDE_STATISTIC_WIDGET(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideStatisticWidget"),
  DISPLAY_MESSAGE_AFTER_FINISH_TASK(GlobalVariableType.SELECTION, Option.TRUE.toString(), "displayMessageAfterFinishOrLeaveTask"),
  ENABLE_GROUP_CHAT(GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableGroupChat"),
  ENABLE_PRIVATE_CHAT(GlobalVariableType.SELECTION, Option.FALSE.toString(), "enablePrivateChat"),
  CHAT_RESPONSE_TIMEOUT(GlobalVariableType.NUMBER, "chatResponseTimeout"),
  CHAT_MAX_CONNECTION(GlobalVariableType.NUMBER, "3", "chatMaxConnection"),
  ENABLE_CASE_OWNER(GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableCaseOwner"),
  DISABLE_CASE_COUNT(GlobalVariableType.SELECTION, Option.FALSE.toString(), "disableCaseCount"),
  DISABLE_TASK_COUNT(GlobalVariableType.SELECTION, Option.FALSE.toString(), "disableTaskCount"),
  EMBED_IN_FRAME(GlobalVariableType.SELECTION, Option.TRUE.toString(), "embedInFrame"),
  LOGGED_IN_USER_FORMAT(GlobalVariableType.SELECTION, Option.DISPLAY_NAME.toString(), "loggedInUserFormat", getLoggedInUserFormatOptions()),
  HIDE_TASK_DOCUMENT(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideTaskDocument"),
  HIDE_CASE_DOCUMENT(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideCaseDocument"),
  SHOW_USER_GUIDE(GlobalVariableType.SELECTION, Option.TRUE.toString(), "showUserGuide"),
  SHOW_GLOBAL_SEARCH(GlobalVariableType.SELECTION, Option.TRUE.toString(), "showGlobalSearch"),
  SHOW_BUTTON_ICON(GlobalVariableType.SELECTION, Option.TRUE.toString(), "showButtonIcon"),
  DEFAULT_SORT_FIELD_OF_TASK_LIST(GlobalVariableType.EXTERNAL_SELECTION, TaskSortField.ID.name(), "defaultSortFieldOfTaskList", getTaskListSortFields()),
  DEFAULT_SORT_DIRECTION_OF_TASK_LIST(GlobalVariableType.EXTERNAL_SELECTION, SortDirection.DESC.name(), "defaultSortDirectionOfTaskList", getSortDirections()),
  DEFAULT_SORT_FIELD_OF_CASE_LIST(GlobalVariableType.EXTERNAL_SELECTION, CaseSortField.ID.name(), "defaultSortFieldOfCaseList", getCaseListSortFields()),
  DEFAULT_SORT_DIRECTION_OF_CASE_LIST(GlobalVariableType.EXTERNAL_SELECTION, SortDirection.DESC.name(), "defaultSortDirectionOfCaseList", getSortDirections()),
  DEFAULT_HOMEPAGE(GlobalVariableType.EXTERNAL_SELECTION, StringUtils.capitalize(HomepageType.DASHBOARD.name().toLowerCase()), "defaultHomepage"),
  DISPLAY_ALL_USERS_OF_TASK_ACTIVATOR(GlobalVariableType.SELECTION, Option.FALSE.toString(), "displayAllUsersOfTaskActivator"),
  HIDE_YEAR(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideYear"),
  DEFAULT_PROCESS_MODE(GlobalVariableType.EXTERNAL_SELECTION, ProcessMode.GRID.name(), "defaultProcessMode", getProcessMode()),
  SHOW_PROCESS_INFORMATION("Portal.Processes.ShowInformation", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showProcessInformation");
  
  private GlobalVariableType type;
  private String defaultValue;
  private String noteCMS;
  private Option[] options;
  private Map<String, String> externalOptions;

  public enum Option {
    FALSE,
    TRUE,
    USERNAME,
    DISPLAY_NAME,
    DISPLAY_NAME_USERNAME,
    USERNAME_DISPLAY_NAME;
    
    public String translate() {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/GlobalVariable/Option/" + name());
    }
  }
  
  private GlobalVariable() {
  }

  private GlobalVariable(GlobalVariableType type, String noteCMS) {
    this.type = type;
    this.noteCMS = noteCMS;
  }

  private GlobalVariable(GlobalVariableType type, String defaultValue, String noteCMS) {
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    if (type == GlobalVariableType.SELECTION) {
      options = new Option[] {Option.FALSE, Option.TRUE};
    }
  }
  
  private GlobalVariable(GlobalVariableType type, String defaultValue, String noteCMS, Option[] options) {
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    this.options = options;
  }
  
  private GlobalVariable(GlobalVariableType type, String defaultValue, String noteCMS, Map<String, String> externalOptions) {
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    this.externalOptions = externalOptions;
  }
  
  public GlobalVariableType getType() {
    return type;
  }
  
  public String getDefaultValue() {
    return defaultValue;
  }

  public String getNote() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/globalVariableNote/" + noteCMS);
  }
  
  public Object[] getOptions() {
    return options;
  }
  
  public Map<String, String> getExternalOptions() {
    return externalOptions;
  }

  public void setExternalOptions(Map<String, String> externalOptions) {
    this.externalOptions = externalOptions;
  }
  
  private static Option[] getLoggedInUserFormatOptions() {
    return new Option[] {Option.USERNAME, Option.DISPLAY_NAME, Option.DISPLAY_NAME_USERNAME, Option.USERNAME_DISPLAY_NAME};
  }

  private static Map<String, String> getTaskListSortFields() {
    Map<String, String> result = new HashMap<>();
    for (TaskSortField sortField : TaskSortField.values()) {
      // Task sort field not available
      if (StringUtils.isNotBlank(sortField.getLabel()) && sortField != TaskSortField.CATEGORY) {
        result.put(sortField.name(), sortField.getLabel());
      }
    }
    return result;
  }

  private static Map<String, String> getCaseListSortFields() {
    Map<String, String> result = new HashMap<>();
    for (CaseSortField sortField : CaseSortField.values()) {
      // Case sort field not available
      if (StringUtils.isNotBlank(sortField.getLabel()) && sortField != CaseSortField.CATEGORY) {
        result.put(sortField.name(), sortField.getLabel());
      }
    }
    return result;
  }

  private static Map<String, String> getSortDirections() {
    Map<String, String> result = new HashMap<>();
    for (SortDirection direction : SortDirection.values()) {
      result.put(direction.name(), direction.getLabel());
    }
    return result;
  }
  

  private static Map<String, String> getProcessMode() {
    Map<String, String> result = new HashMap<>();
    for (ProcessMode mode : ProcessMode.values()) {
      result.put(mode.name(), mode.getLabel());
    }
    return result;
  }
}
