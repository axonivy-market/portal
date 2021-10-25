package ch.ivy.addon.portalkit.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.ivy.addon.portalkit.bean.TaskWidgetBean;
import ch.ivy.addon.portalkit.document.DocumentExtensionConstants;
import ch.ivyteam.ivy.environment.Ivy;

public enum GlobalVariable {
  
  HIDE_LOGOUT_BUTTON("Portal.UserMenu.HideLogoutMenu", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideLogoutButtonNote"),
  HIDE_CHANGE_PASSWORD_BUTTON("Portal.UserMenu.HideChangePasswordMenu", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideChangePasswordButtonNote"),
  SHOW_ENVIRONMENT_INFO("Portal.ShowEnvironmentInfo", GlobalVariableType.SELECTION, Option.FALSE.toString(), "showEnvironmentInfoNote"),
  ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT("Portal.Document.EnableScriptChecking", GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableScriptCheckingForUploadedDocumentNote"),
  ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT("Portal.Document.EnableVirusScanner", GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableVirusScannerForUploadedDocumentNote"),
  UPLOAD_DOCUMENT_WHITELIST_EXTENSION("Portal.Document.WhitelistExtension", GlobalVariableType.TEXT, String.join(", ", DocumentExtensionConstants.DEFAULT_WHITELIST_EXTENSION), "uploadDocumentWhiteListExtensionNote"),
  HIDE_TIME("Portal.DateTimeFormat.HideTime", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideTimeNote"),
  HIDE_YEAR("Portal.DateTimeFormat.HideYear", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideYear"),
  DATE_FILTER_WITH_TIME("Portal.DateTimeFormat.DateFilterWithTime", GlobalVariableType.SELECTION, Option.FALSE.toString(), "dateFilterWithTime"),
  HIDE_SYSTEM_TASKS_FROM_HISTORY("Portal.Histories.HideSystemTasks", GlobalVariableType.SELECTION, Option.TRUE.toString(), "hideSystemTasksFromHistory"),
  HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR("Portal.Histories.HideSystemTasksForAdministrator", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideSystemTasksFromHistoryAdministrator"),
  HIDE_SYSTEM_NOTES_FROM_HISTORY("Portal.Histories.HideSystemNotes", GlobalVariableType.SELECTION, Option.TRUE.toString(), "hideSystemNotesFromHistory"),
  HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR("Portal.Histories.HideSystemNotesForAdministrator", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideSystemNotesFromHistoryAdministrator"),
  ENABLE_USER_FAVORITES("Portal.Dashboard.ShowUserFavorites", GlobalVariableType.SELECTION, Option.TRUE.toString(), "enableUserFavorites"),
  HIDE_STATISTIC_WIDGET("Portal.Dashboard.HideStatisticWidget", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideStatisticWidget"),
  SHOW_USER_GUIDE("Portal.Dashboard.ShowUserGuide", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showUserGuide"),
  ENABLE_GROUP_CHAT("Portal.Chat.EnableGroup", GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableGroupChat"),
  ENABLE_PRIVATE_CHAT("Portal.Chat.EnablePrivate", GlobalVariableType.SELECTION, Option.FALSE.toString(), "enablePrivateChat"),
  CHAT_RESPONSE_TIMEOUT("Portal.Chat.ResponseTimeout", GlobalVariableType.NUMBER, "0", "chatResponseTimeout"),
  CHAT_MAX_CONNECTION("Portal.Chat.MaxConnection", GlobalVariableType.NUMBER, "3", "chatMaxConnection"),
  ENABLE_CASE_OWNER("Portal.Cases.EnableOwner", GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableCaseOwner"),
  DISABLE_CASE_COUNT("Portal.Cases.DisableCount", GlobalVariableType.SELECTION, Option.FALSE.toString(), "disableCaseCount"),
  DEFAULT_SORT_FIELD_OF_CASE_LIST("Portal.Cases.SortField", GlobalVariableType.EXTERNAL_SELECTION, CaseSortField.ID.name(), "defaultSortFieldOfCaseList", getCaseListSortFields()),
  DEFAULT_SORT_DIRECTION_OF_CASE_LIST("Portal.Cases.SortDirection", GlobalVariableType.EXTERNAL_SELECTION, SortDirection.DESC.name(), "defaultSortDirectionOfCaseList", getSortDirections()),
  REFRESH_TASK_LIST_INTERVAL("Portal.Tasks.RefreshInterval", GlobalVariableType.NUMBER, String.valueOf(TaskWidgetBean.DEFAULT_TASK_LIST_REFRESH_INTERVAL), "refreshTaskListIntervalNote"),
  DISABLE_TASK_COUNT("Portal.Tasks.DisableCount", GlobalVariableType.SELECTION, Option.FALSE.toString(), "disableTaskCount"),
  DEFAULT_SORT_FIELD_OF_TASK_LIST("Portal.Tasks.SortField", GlobalVariableType.EXTERNAL_SELECTION, TaskSortField.ID.name(), "defaultSortFieldOfTaskList", getTaskListSortFields()),
  DEFAULT_SORT_DIRECTION_OF_TASK_LIST("Portal.Tasks.SortDirection", GlobalVariableType.EXTERNAL_SELECTION, SortDirection.DESC.name(), "defaultSortDirectionOfTaskList", getSortDirections()),
  SHOW_TASK_DURATION_TIME("Portal.TaskDetails.ShowDurationTime", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showTaskDurationTime"),
  HIDE_TASK_DOCUMENT("Portal.TaskDetails.HideDocument", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideTaskDocument"),
  HIDE_CASE_DOCUMENT("Portal.CaseDetails.HideDocument", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideCaseDocument"),
  SHOW_CASE_DURATION_TIME("Portal.CaseDetails.ShowDurationTime", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showCaseDurationTime"),
  HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE("Portal.CaseDetails.HideUploadDocumentForDoneCase", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideUploadDocumentForDoneCaseNote"),
  DEFAULT_PROCESS_MODE("Portal.Processes.Mode", GlobalVariableType.EXTERNAL_SELECTION, ProcessMode.IMAGE.name(), "defaultProcessMode", getProcessMode()),
  DEFAULT_PROCESS_IMAGE("Portal.Processes.DefaultImage", GlobalVariableType.EXTERNAL_SELECTION, DefaultImage.DEFAULT.name(), "defaultProcessImage", getDefaultProcessImage()),
  DISPLAY_MESSAGE_AFTER_FINISH_TASK("Portal.DisplayMessageAfterFinishTask", GlobalVariableType.SELECTION, Option.TRUE.toString(), "displayMessageAfterFinishOrLeaveTask"),
  EXPRESS_END_PAGE("Portal.ExpressEndPage", GlobalVariableType.SELECTION, Option.TRUE.toString(), "expressEndPageNote"),
  CLIENT_SIDE_TIMEOUT("Portal.ClientSideTimeout", GlobalVariableType.NUMBER, "0", "clientSideTimeoutNote"),
  EMBED_IN_FRAME("Portal.EmbedInFrame", GlobalVariableType.SELECTION, Option.TRUE.toString(), "embedInFrame"),
  LOGGED_IN_USER_FORMAT("Portal.LoggedInUserFormat", GlobalVariableType.SELECTION, Option.DISPLAY_NAME.toString(), "loggedInUserFormat", getLoggedInUserFormatOptions()),
  SHOW_GLOBAL_SEARCH("Portal.ShowGlobalSearch", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showGlobalSearch"),
  SHOW_BUTTON_ICON("Portal.ShowButtonIcon", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showButtonIcon"),
  DEFAULT_HOMEPAGE("Portal.Homepage", GlobalVariableType.EXTERNAL_SELECTION, StringUtils.capitalize(HomepageType.DASHBOARD.name().toLowerCase()), "defaultHomepage"),
  DISPLAY_USERS_OF_ROLE("Portal.DisplayUsersOfRole", GlobalVariableType.SELECTION, Option.FALSE.toString(), "displayAllUsersOfTaskActivator"),
  SHOW_PROCESS_INFORMATION("Portal.Processes.ShowInformation", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showProcessInformation"),
  SHOW_LEGACY_UI("Portal.ShowLegacyUI", GlobalVariableType.SELECTION, Option.FALSE.toString(), "showLegacyUI");

  private String key;
  private GlobalVariableType type;
  private String defaultValue;
  private String noteCMS;
  private Option[] options;
  private Map<String, String> externalOptions;
  private static Map<String, GlobalVariable> keyToVariable =
      Stream.of(GlobalVariable.values()).collect(Collectors.toMap(GlobalVariable::getKey, v -> v));

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
  
  private GlobalVariable() {}

  private GlobalVariable(String key, GlobalVariableType type, String noteCMS) {
    this.key = key;
    this.type = type;
    this.noteCMS = noteCMS;
  }

  private GlobalVariable(String key, GlobalVariableType type, String defaultValue, String noteCMS) {
    this.key = key;
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    if (type == GlobalVariableType.SELECTION) {
      options = new Option[] {Option.FALSE, Option.TRUE};
    }
  }
  
  private GlobalVariable(String key, GlobalVariableType type, String defaultValue, String noteCMS, Option[] options) {
    this.key = key;
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    this.options = options;
  }
  
  private GlobalVariable(String key, GlobalVariableType type, String defaultValue, String noteCMS,
      Map<String, String> externalOptions) {
    this.key = key;
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    this.externalOptions = externalOptions;
  }
  
  public String getKey() {
    return key;
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

  private static Map<String, String> getDefaultProcessImage() {
    Map<String, String> result = new HashMap<>();
    for (DefaultImage defaultImage : DefaultImage.values()) {
      result.put(defaultImage.name(), defaultImage.name());
    }
    return result;
  }

  public static GlobalVariable valueOfKey(String key) {
    return keyToVariable.get(key);
   }
}
