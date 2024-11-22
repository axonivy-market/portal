package com.axonivy.portal.selenium.common;

public enum Variable {

  SHOW_QR_CODE("Portal.UserMenu.ShowQRCode"), HIDE_LOGOUT_BUTTON("Portal.UserMenu.HideLogoutMenu"),
  HIDE_CHANGE_PASSWORD_BUTTON("Portal.UserMenu.HideChangePasswordMenu"),
  ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT("Portal.Document.EnableScriptChecking"),
  ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT("Portal.Document.EnableVirusScanner"),
  UPLOAD_DOCUMENT_WHITELIST_EXTENSION("Portal.Document.WhitelistExtension"),
  HIDE_TIME("Portal.DateTimeFormat.HideTime"), HIDE_YEAR("Portal.DateTimeFormat.HideYear"),
  HIDE_SYSTEM_TASKS_FROM_HISTORY("Portal.Histories.HideSystemTasks"),
  HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR("Portal.Histories.HideSystemTasksForAdministrator"),
  HIDE_SYSTEM_NOTES_FROM_HISTORY("Portal.Histories.HideSystemNotes"),
  HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR("Portal.Histories.HideSystemNotesForAdministrator"),
  ENABLE_GROUP_CHAT("Portal.Chat.EnableGroup"),
  ENABLE_PRIVATE_CHAT("Portal.Chat.EnablePrivate"), CHAT_RESPONSE_TIMEOUT("Portal.Chat.ResponseTimeout"),
  CHAT_MAX_CONNECTION("Portal.Chat.MaxConnection"), ENABLE_CASE_OWNER("Portal.Cases.EnableOwner"),
  SHOW_TASK_DURATION_TIME("Portal.TaskDetails.ShowDurationTime"), HIDE_TASK_DOCUMENT("Portal.TaskDetails.HideDocument"),
  HIDE_CASE_DOCUMENT("Portal.CaseDetails.HideDocument"), SHOW_CASE_DURATION_TIME("Portal.CaseDetails.ShowDurationTime"),
  HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE("Portal.CaseDetails.HideUploadDocumentForDoneCase"),
  DEFAULT_PROCESS_MODE("Portal.Processes.Mode"), DEFAULT_PROCESS_IMAGE("Portal.Processes.DefaultImage"),
  DISPLAY_MESSAGE_AFTER_FINISH_TASK("Portal.DisplayMessageAfterFinishTask"),
  EMBED_IN_FRAME("Portal.EmbedInFrame"),
  LOGGED_IN_USER_FORMAT("Portal.LoggedInUserFormat"), SHOW_GLOBAL_SEARCH("Portal.ShowGlobalSearch"),
  SHOW_BUTTON_ICON("Portal.ShowButtonIcon"), DEFAULT_HOMEPAGE("Portal.Homepage"),
  HIDE_RELATED_CASE_INFO_FROM_HISTORY("Portal.Histories.HideRelatedCaseInfo"),
  DISPLAY_USERS_OF_TASK_ACTIVATOR("Portal.DisplayUsersOfRole"), ANNOUNCEMENT("Portal.Announcement"),
  THIRD_PARTY_APP("Portal.ThirdPartyApplications"), EXTERNAL_LINK("Portal.Processes.ExternalLinks"),
  TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST("Portal.Tasks.BehaviourWhenClickingOnLineInTaskList"),
  TASK_DETAIL("Portal.TaskDetails"), CASE_COLUMN("Portal.Cases.CaseColumn"), CASE_FILTER("Portal.Cases.CaseFilters"),
  CASE_DETAIL("Portal.CaseDetails"), SHOW_AVATAR("Portal.ShowAvatar"),
  GLOBAL_SEARCH_BY_TASK_FIELDS("Portal.SearchScope.ByTaskFields"),
  GLOBAL_SEARCH_BY_CASE_FIELDS("Portal.SearchScope.ByCaseFields"), DEEPL_AUTH_KEY("Portal.DeepL.AuthKey"),
  ENABLE_DEEPL_TRANSLATION("Portal.DeepL.Enable"), DASHBOARD("Portal.Dashboard"),
  GLOBAL_FOOTER_INFO("Portal.GlobalFooterInfo"), USER_MENU("Portal.UserMenu"), DEFAULT_THEME_MODE("Portal.Theme.Mode"),
  GLOBAL_SEARCH_SCOPE_BY_CATEGORIES("Portal.GlobalSearchScopeCategories"),
  SHOW_QUICK_GLOBAL_SEARCH("Portal.ShowQuickGlobalSearch"),
  ENABLE_SWITCH_THEME_BUTTON("Portal.Theme.EnableSwitchThemeModeButton"), DASHBOARD_MAIN_MENU_ENTRY("Portal.Dashboard.MainMenuEntry"), APPLICATION_NAME("Portal.ApplicationName");

  private String key;

  private Variable(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

}
