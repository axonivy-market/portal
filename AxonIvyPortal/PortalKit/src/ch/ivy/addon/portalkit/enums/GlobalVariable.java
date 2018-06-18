
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
  HIDE_CHANGE_PASSWORD_BUTTON(Boolean.FALSE.toString(), "hideChangePasswordButtonNote"), //NOSONAR
  HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE(Boolean.FALSE.toString(), "hideUploadDocumentForDoneCaseNote"),
  ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT(Boolean.FALSE.toString(), "enableScriptCheckingForUploadedDocumentNote"),
  HIDE_TIME(Boolean.FALSE.toString(), "hideTimeNote"),
  EXPRESS_END_PAGE(Boolean.TRUE.toString(), "expressEndPageNote"),
  REFRESH_TASK_LIST_INTERVAL(String.valueOf(TaskWidgetBean.DEFAULT_TASK_LIST_REFRESH_INTERVAL), "refreshTaskListIntervalNote"),
  UPLOAD_DOCUMENT_WHITELIST_EXTENSION(String.join(", ", DocumentExtensionConstants.DEFAULT_WHITELIST_EXTENSION), "uploadDocumentWhiteListExtensionNote"),
  HOMEPAGE_URL("homePageUrlNote"),
  CLIENT_SIDE_TIMEOUT("clientSideTimeoutNote");

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
            HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE, ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT, HIDE_TIME, EXPRESS_END_PAGE);
  }

  public static List<GlobalVariable> getNumberType() {
    return Arrays.asList(REFRESH_TASK_LIST_INTERVAL, CLIENT_SIDE_TIMEOUT);
  }
}
