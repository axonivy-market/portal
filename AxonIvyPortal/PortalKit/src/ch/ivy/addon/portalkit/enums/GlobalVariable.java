package ch.ivy.addon.portalkit.enums;

import java.util.HashMap;
import java.util.Map;

import ch.ivy.addon.portalkit.document.DocumentExtensionConstants;
import ch.ivy.addon.portalkit.bean.TaskWidgetBean;
import ch.ivyteam.ivy.environment.Ivy;

@SuppressWarnings("deprecation")
public enum GlobalVariable {
  
  HIDE_LOGOUT_BUTTON(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideLogoutButtonNote"),
  SHOW_ENVIRONMENT_INFO(GlobalVariableType.SELECTION, Option.FALSE.toString(), "showEnvironmentInfoNote"),
  HIDE_CHANGE_PASSWORD_BUTTON(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideChangePasswordButtonNote"), 
  HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideUploadDocumentForDoneCaseNote"),
  ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT(GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableScriptCheckingForUploadedDocumentNote"),
  ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT(GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableVirusScannerForUploadedDocumentNote"),
  HIDE_TIME(GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideTimeNote"),
  DATE_FILTER_WITH_TIME(GlobalVariableType.SELECTION, Option.FALSE.toString(), "dateFilterWithTime"),
  EXPRESS_END_PAGE(GlobalVariableType.SELECTION, Option.TRUE.toString(), "expressEndPageNote"),
  REFRESH_TASK_LIST_INTERVAL(GlobalVariableType.NUMBER, String.valueOf(TaskWidgetBean.DEFAULT_TASK_LIST_REFRESH_INTERVAL), "refreshTaskListIntervalNote"),
  UPLOAD_DOCUMENT_WHITELIST_EXTENSION(GlobalVariableType.TEXT, String.join(", ", DocumentExtensionConstants.DEFAULT_WHITELIST_EXTENSION), "uploadDocumentWhiteListExtensionNote"),
  HOMEPAGE_URL(GlobalVariableType.TEXT, "homePageUrlNote"),
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
  SHOW_GLOBAL_SEARCH(GlobalVariableType.SELECTION, Option.TRUE.toString(), "showGlobalSearch"),
  DISPLAY_USERS_OF_ROLE(GlobalVariableType.SELECTION, Option.FALSE.toString(), "displayAllUsersOfTaskActivator"),
  BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST(GlobalVariableType.EXTERNAL_SELECTION, BehaviourWhenClickingOnLineInTaskList.ACCESS_TASK_DETAILS.name(),
      "behaviourWhenClickingOnLineInTaskList", getBehavioursWhenClickingOnLineInTaskList()),
  SHOW_ERROR_LOG_TO_CONSOLE(GlobalVariableType.SELECTION, Option.FALSE.toString(), "showErrorLogToConsole");
  
  private GlobalVariableType type;
  private String defaultValue;
  private String noteCMS;
  private Option[] options;
  private Map<String, Object> externalOptions;

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

  private GlobalVariable(GlobalVariableType type, String defaultValue, String noteCMS, Map<String, Object> externalOptions) {
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    this.externalOptions = externalOptions;
  }

  private static Map<String, Object> getBehavioursWhenClickingOnLineInTaskList() {
    Map<String, Object> result = new HashMap<>();
    for (BehaviourWhenClickingOnLineInTaskList behaviour : BehaviourWhenClickingOnLineInTaskList.values()) {
      result.put(behaviour.name(), behaviour);
    }

    return result;
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
  
  private static Option[] getLoggedInUserFormatOptions() {
    return new Option[] {Option.USERNAME, Option.DISPLAY_NAME, Option.DISPLAY_NAME_USERNAME, Option.USERNAME_DISPLAY_NAME};
  }

  public Map<String, Object> getExternalOptions() {
    return externalOptions;
  }

  public void setExternalOptions(Map<String, Object> externalOptions) {
    this.externalOptions = externalOptions;
  }
}
