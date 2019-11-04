
package ch.ivy.addon.portalkit.enums;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.bean.TaskWidgetBean;
import ch.ivy.addon.portalkit.document.DocumentExtensionConstants;
import ch.ivyteam.ivy.environment.Ivy;

public enum GlobalVariable {

  HIDE_LOGOUT_BUTTON(Boolean.FALSE.toString(), "hideLogoutButtonNote"),
  SHOW_ENVIRONMENT_INFO(Boolean.FALSE.toString(), "showEnvironmentInfoNote"),
  SHOW_TASK_BUTTON_LABELS(Boolean.FALSE.toString(), "showTaskButtonLabelsNote"),
  HIDE_CHANGE_PASSWORD_BUTTON(Boolean.FALSE.toString(), "hideChangePasswordButtonNote"), 
  HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE(Boolean.FALSE.toString(), "hideUploadDocumentForDoneCaseNote"),
  ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT(Boolean.FALSE.toString(), "enableScriptCheckingForUploadedDocumentNote"),
  HIDE_TIME(Boolean.FALSE.toString(), "hideTimeNote"),
  EXPRESS_END_PAGE(Boolean.TRUE.toString(), "expressEndPageNote"),
  REFRESH_TASK_LIST_INTERVAL(String.valueOf(TaskWidgetBean.DEFAULT_TASK_LIST_REFRESH_INTERVAL), "refreshTaskListIntervalNote"),
  UPLOAD_DOCUMENT_WHITELIST_EXTENSION(String.join(", ", DocumentExtensionConstants.DEFAULT_WHITELIST_EXTENSION), "uploadDocumentWhiteListExtensionNote"),
  HOMEPAGE_URL("homePageUrlNote"),
  CLIENT_SIDE_TIMEOUT("clientSideTimeoutNote"),
  HIDE_SYSTEM_TASKS_FROM_HISTORY(Boolean.TRUE.toString(), "hideSystemTasksFromHistory"),
  HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR(Boolean.FALSE.toString(), "hideSystemTasksFromHistoryAdministrator"),
  ENABLE_USER_FAVORITES(Boolean.TRUE.toString(), "enableUserFavorites"),
  HIDE_STATISTIC_WIDGET(Boolean.FALSE.toString(), "hideStatisticWidget"),
  DISPLAY_MESSAGE_AFTER_FINISH_TASK(Boolean.TRUE.toString(), "displayMessageAfterFinishTask"),
  ENABLE_GROUP_CHAT(Boolean.FALSE.toString(), "enableGroupChat"),
  ENABLE_PRIVATE_CHAT(Boolean.FALSE.toString(), "enablePrivateChat"),
  CHAT_RESPONSE_TIMEOUT("chatResponseTimeout"),
  START_IN_IFRAME(Boolean.FALSE.toString(), "startInIFrame");

  private String defaultValue;
  private String noteCMS;

  private GlobalVariable() {
  }

  private GlobalVariable(String noteCMS) {
    this.noteCMS = noteCMS;
  }

  private GlobalVariable(String defaultValue, String noteCMS) {
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public String getNote() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/globalVariableNote/" + noteCMS);
  }

  public static List<GlobalVariable> getBooleanType() {
    return Arrays.asList(HIDE_LOGOUT_BUTTON, SHOW_ENVIRONMENT_INFO, SHOW_TASK_BUTTON_LABELS, HIDE_CHANGE_PASSWORD_BUTTON,
            HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE, ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT, HIDE_TIME, EXPRESS_END_PAGE, HIDE_SYSTEM_TASKS_FROM_HISTORY, 
            HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR, ENABLE_USER_FAVORITES, HIDE_STATISTIC_WIDGET, DISPLAY_MESSAGE_AFTER_FINISH_TASK, ENABLE_GROUP_CHAT, ENABLE_PRIVATE_CHAT,
            START_IN_IFRAME);
  }

  public static List<GlobalVariable> getNumberType() {
    return Arrays.asList(REFRESH_TASK_LIST_INTERVAL, CLIENT_SIDE_TIMEOUT);
  }
}
