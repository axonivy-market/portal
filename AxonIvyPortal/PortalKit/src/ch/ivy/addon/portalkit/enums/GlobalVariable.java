package ch.ivy.addon.portalkit.enums;

import ch.ivy.addon.portalkit.bean.TaskWidgetBean;
import ch.ivy.addon.portalkit.document.DocumentExtensionConstants;
import ch.ivy.addon.portalkit.statistics.StatisticChartConstants;

public enum GlobalVariable {

  HIDE_LOGOUT_BUTTON(Boolean.FALSE.toString()),
  SHOW_ENVIRONMENT_INFO(Boolean.FALSE.toString()),
  SHOW_TASK_BUTTON_LABELS(Boolean.FALSE.toString()),
  HIDE_CHANGE_PASSWORD_BUTTON(Boolean.FALSE.toString()), //NOSONAR
  HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE(Boolean.FALSE.toString()),
  ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT(Boolean.FALSE.toString()),
  REFRESH_TASK_LIST_INTERVAL(String.valueOf(TaskWidgetBean.DEFAULT_TASK_LIST_REFRESH_INTERVAL)),
  EXPIRY_CHART_LAST_DRILLDOWN_LEVEL(StatisticChartConstants.DRILLDOWN_LEVEL_HOUR),
  UPLOAD_DOCUMENT_WHITELIST_EXTENSION(String.join(", ", DocumentExtensionConstants.DEFAULT_WHITELIST_EXTENSION)),
  HOMEPAGE_URL,
  CLIENT_SIDE_TIMEOUT,
  EXPRESS_END_PAGE;

  private String defaultValue;

  private GlobalVariable() {
    
  }

  private GlobalVariable(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

}
