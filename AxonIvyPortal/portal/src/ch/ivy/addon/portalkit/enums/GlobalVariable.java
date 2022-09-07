package ch.ivy.addon.portalkit.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.ThemeMode;

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
  CHAT_MAX_CONNECTION("Portal.Chat.MaxConnection", GlobalVariableType.SELECTION, "3", "chatMaxConnection", new Object[] { 1, 2, 3, 4, 5}),
  ENABLE_CASE_OWNER("Portal.Cases.EnableOwner", GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableCaseOwner"),
  DISABLE_CASE_COUNT("Portal.Cases.DisableCount", GlobalVariableType.SELECTION, Option.FALSE.toString(), "disableCaseCount"),
  REFRESH_TASK_LIST_INTERVAL("Portal.Tasks.RefreshInterval", GlobalVariableType.NUMBER, String.valueOf(TaskWidgetBean.DEFAULT_TASK_LIST_REFRESH_INTERVAL), "refreshTaskListIntervalNote"),
  DISABLE_TASK_COUNT("Portal.Tasks.DisableCount", GlobalVariableType.SELECTION, Option.FALSE.toString(), "disableTaskCount"),
  SHOW_TASK_DURATION_TIME("Portal.TaskDetails.ShowDurationTime", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showTaskDurationTime"),
  HIDE_TASK_DOCUMENT("Portal.TaskDetails.HideDocument", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideTaskDocument"),
  HIDE_CASE_DOCUMENT("Portal.CaseDetails.HideDocument", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideCaseDocument"),
  SHOW_CASE_DURATION_TIME("Portal.CaseDetails.ShowDurationTime", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showCaseDurationTime"),
  HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE("Portal.CaseDetails.HideUploadDocumentForDoneCase", GlobalVariableType.SELECTION, Option.FALSE.toString(), "hideUploadDocumentForDoneCaseNote"),
  DISPLAY_MESSAGE_AFTER_FINISH_TASK("Portal.DisplayMessageAfterFinishTask", GlobalVariableType.SELECTION, Option.TRUE.toString(), "displayMessageAfterFinishOrLeaveTask"),
  EXPRESS_END_PAGE("Portal.ExpressEndPage", GlobalVariableType.SELECTION, Option.TRUE.toString(), "expressEndPageNote"),
  CLIENT_SIDE_TIMEOUT("Portal.ClientSideTimeout", GlobalVariableType.NUMBER, "0", "clientSideTimeoutNote"),
  EMBED_IN_FRAME("Portal.EmbedInFrame", GlobalVariableType.SELECTION, Option.TRUE.toString(), "embedInFrame"),
  SHOW_GLOBAL_SEARCH("Portal.ShowGlobalSearch", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showGlobalSearch"),
  SHOW_BUTTON_ICON("Portal.ShowButtonIcon", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showButtonIcon"),
  DISPLAY_USERS_OF_ROLE("Portal.DisplayUsersOfRole", GlobalVariableType.SELECTION, Option.FALSE.toString(), "displayAllUsersOfTaskActivator"),
  SHOW_PROCESS_INFORMATION("Portal.Processes.ShowInformation", GlobalVariableType.SELECTION, Option.TRUE.toString(), "showProcessInformation"),
  LOGGED_IN_USER_FORMAT("Portal.LoggedInUserFormat", GlobalVariableType.SELECTION, Option.DISPLAY_NAME.name(), "loggedInUserFormat", getLoggedInUserFormatOptions()),
  SHOW_LEGACY_UI("Portal.ShowLegacyUI", GlobalVariableType.SELECTION, Option.FALSE.toString(), "showLegacyUI"),
  DEFAULT_SORT_FIELD_OF_CASE_LIST("Portal.Cases.SortField", GlobalVariableType.EXTERNAL_SELECTION, CaseSortField.ID.name(), "defaultSortFieldOfCaseList", getCaseListSortFields()),
  DEFAULT_SORT_DIRECTION_OF_CASE_LIST("Portal.Cases.SortDirection", GlobalVariableType.EXTERNAL_SELECTION, SortDirection.DESC.name(), "defaultSortDirectionOfCaseList", getSortDirections()),
  DEFAULT_PROCESS_MODE("Portal.Processes.Mode", GlobalVariableType.EXTERNAL_SELECTION, ProcessMode.IMAGE.name(), "defaultProcessMode", getProcessMode()),
  DEFAULT_PROCESS_IMAGE("Portal.Processes.DefaultImage", GlobalVariableType.EXTERNAL_SELECTION, DefaultImage.DEFAULT.name(), "defaultProcessImage", getDefaultProcessImage()),
  DEFAULT_HOMEPAGE("Portal.Homepage", GlobalVariableType.EXTERNAL_SELECTION, StringUtils.capitalize(HomepageType.DASHBOARD.name().toLowerCase()), "defaultHomepage"),
  DEFAULT_SORT_DIRECTION_OF_TASK_LIST("Portal.Tasks.SortDirection", GlobalVariableType.EXTERNAL_SELECTION, SortDirection.DESC.name(), "defaultSortDirectionOfTaskList", getSortDirections()),
  DEFAULT_SORT_FIELD_OF_TASK_LIST("Portal.Tasks.SortField", GlobalVariableType.EXTERNAL_SELECTION, TaskSortField.ID.name(), "defaultSortFieldOfTaskList", getTaskListSortFields()),
  DEFAULT_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST("Portal.Tasks.BehaviourWhenClickingOnLineInTaskList", GlobalVariableType.EXTERNAL_SELECTION,
      BehaviourWhenClickingOnLineInTaskList.RUN_TASK.name(), "behaviourWhenClickingOnLineInTaskList", getBehavioursWhenClickingOnLineInTaskList()),
  ENABLE_PROCESS_VIEWER("Portal.ProcessViewer", GlobalVariableType.SELECTION, Option.TRUE.toString(), "enableProcessViewer"),
  HIDE_RELATED_CASE_INFO_FROM_HISTORY("Portal.Histories.HideRelatedCaseInfo", GlobalVariableType.SELECTION, Option.TRUE.toString(), "hideRelatedCaseInfoFromHistory"),
  SHOW_ERROR_LOG_TO_CONSOLE("Portal.ShowErrorLogToConsole", GlobalVariableType.SELECTION, Option.FALSE.toString(), "showErrorLogToConsole"),
  SHOW_AVATAR("Portal.ShowAvatar",GlobalVariableType.SELECTION,Option.TRUE.toString(),"showAvatar"),
  STATISTIC_CHART_SCALING_INTERVAL("Portal.StatisticChartScalingInterval", GlobalVariableType.NUMBER, "0", "statisticChartScalingInterval"),
  ROLE_DIRECT_CHILDREN_LIMIT("Portal.RoleDirectChildrenLimit", GlobalVariableType.SELECTION, "50", "RoleDirectChildrenLimit", new Object[] { 10, 50, 100}),
  ROLE_PARENT_LIMIT("Portal.RoleParentLimit", GlobalVariableType.SELECTION, "10", "RoleParentLimit", new Object[] { 5, 10, 20}),
  SHOW_LOGIN_FOOTER("Portal.LoginPage.ShowFooter", GlobalVariableType.SELECTION, Option.TRUE.toString(), "ShowLoginPageFooter"),
  ENABLE_SWITCH_THEME_BUTTON("Portal.Theme.EnableSwitchThemeModeButton", GlobalVariableType.SELECTION, Option.TRUE.toString(), "EnableSwitchThemeModeButton"),
  DEFAULT_THEME_MODE("Portal.Theme.Mode", GlobalVariableType.EXTERNAL_SELECTION, ThemeMode.LIGHT.toString(), "DefaultThemeMode", getThemeModes());

  private String key;
  private GlobalVariableType type;
  private String defaultValue;
  private String noteCMS;
  private Option[] options;
  private Object[] objectOptions;
  private Map<String, Object> externalOptions;
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
      Map<String, Object> externalOptions) {
    this.key = key;
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    this.externalOptions = externalOptions;
  }

  private GlobalVariable(String key, GlobalVariableType type, String defaultValue, String noteCMS, Object[] objectOptions) {
    this.key = key;
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    this.objectOptions = objectOptions;
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

  public Object[] getObjectOptions() {
    return objectOptions;
  }

  public Map<String, Object> getExternalOptions() {
    return externalOptions;
  }

  public void setExternalOptions(Map<String, Object> externalOptions) {
    this.externalOptions = externalOptions;
  }

  private static Option[] getLoggedInUserFormatOptions() {
    return new Option[] {Option.USERNAME, Option.DISPLAY_NAME, Option.DISPLAY_NAME_USERNAME, Option.USERNAME_DISPLAY_NAME};
  }

  private static Map<String, Object> getTaskListSortFields() {
    Map<String, Object> result = new HashMap<>();
    for (TaskSortField sortField : TaskSortField.values()) {
      // Task sort field not available
      if (StringUtils.isNotBlank(sortField.getLabel()) && sortField != TaskSortField.CATEGORY) {
        result.put(sortField.name(), sortField);
      }
    }
    return result;
  }

  private static Map<String, Object> getCaseListSortFields() {
    Map<String, Object> result = new HashMap<>();
    for (CaseSortField sortField : CaseSortField.values()) {
      // Case sort field not available
      if (StringUtils.isNotBlank(sortField.getLabel()) && sortField != CaseSortField.CATEGORY) {
        result.put(sortField.name(), sortField);
      }
    }
    return result;
  }

  private static Map<String, Object> getSortDirections() {
    Map<String, Object> result = new HashMap<>();
    for (SortDirection direction : SortDirection.values()) {
      result.put(direction.name(), direction);
    }
    return result;
  }

  private static Map<String, Object> getProcessMode() {
    Map<String, Object> result = new HashMap<>();
    for (ProcessMode mode : ProcessMode.values()) {
      result.put(mode.name(), mode);
    }
    return result;
  }

  private static Map<String, Object> getDefaultProcessImage() {
    Map<String, Object> result = new HashMap<>();
    for (DefaultImage defaultImage : DefaultImage.values()) {
      result.put(defaultImage.name(), defaultImage);
    }
    return result;
  }

  private static Map<String, Object> getBehavioursWhenClickingOnLineInTaskList() {
    Map<String, Object> result = new HashMap<>();
    for (BehaviourWhenClickingOnLineInTaskList behaviour : BehaviourWhenClickingOnLineInTaskList.values()) {
      result.put(behaviour.name(), behaviour);
    }
    return result;
  }

  private static Map<String, Object> getThemeModes() {
    Map<String, Object> result = new HashMap<>();
    for (ThemeMode themeMode : ThemeMode.values()) {
      result.put(themeMode.name(), themeMode);
    }
    return result;
  }

  public static GlobalVariable valueOfKey(String key) {
    return keyToVariable.get(key);
  }
}
