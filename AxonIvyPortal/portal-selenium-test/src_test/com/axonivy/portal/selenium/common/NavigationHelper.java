package com.axonivy.portal.selenium.common;

import static com.axonivy.portal.selenium.common.LinkNavigator.redirectToRelativeLink;

import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

public class NavigationHelper {
  private static final String TASK_LIST_PAGE_URL = "portal/1549F58C18A6C562/DefaultTaskListPage.ivp";
  private static final String CASE_LIST_PAGE_URL = "portal/1549F58C18A6C562/CaseListPage.ivp";
  private static final String PROCESS_LIST_PAGE_URL = "portal/1549F58C18A6C562/DefaultProcessStartListPage.ivp";

  public static TaskWidgetPage navigateToTaskList() {
    redirectToRelativeLink(TASK_LIST_PAGE_URL);
    return new TaskWidgetPage();
  }

  public static CaseWidgetPage navigateToCaseList() {
    redirectToRelativeLink(CASE_LIST_PAGE_URL);
    return new CaseWidgetPage();
  }

  public static ProcessWidgetPage navigateToProcessList() {
    redirectToRelativeLink(PROCESS_LIST_PAGE_URL);
    return new ProcessWidgetPage();
  }
}
