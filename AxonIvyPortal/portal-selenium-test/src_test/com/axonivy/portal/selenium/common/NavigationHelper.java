package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.Selenide.open;

import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.StatisticWidgetPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

public class NavigationHelper {
  private static final String TASK_LIST_PAGE_URL = "portal/1549F58C18A6C562/DefaultTaskListPage.ivp";
  private static final String CASE_LIST_PAGE_URL = "portal/1549F58C18A6C562/CaseListPage.ivp";
  private static final String PROCESS_LIST_PAGE_URL = "portal/1549F58C18A6C562/DefaultProcessStartListPage.ivp";
  private static final String STATISTIC_PAGE_URL = "portal/1549F58C18A6C562/StatisticPage.ivp";
  private static final String DEVELOPER_EXAMPLES_TASK_LIST_PAGE_URL  = "portal-developer-examples/164211E97C598DAA/DefaultTaskListPage.ivp";
  

  public static void navigateToRelativeLink(String relativeProcessStartUrl) {
    try {
      open(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartUrl));
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  public static TaskWidgetPage navigateToTaskList() {
    navigateToRelativeLink(TASK_LIST_PAGE_URL);
    return new TaskWidgetPage();
  }

  public static TaskWidgetPage navigateToDeveloperExamplesTaskList() {
    navigateToRelativeLink(DEVELOPER_EXAMPLES_TASK_LIST_PAGE_URL);
    return new TaskWidgetPage();
  }

  public static CaseWidgetPage navigateToCaseList() {
    navigateToRelativeLink(CASE_LIST_PAGE_URL);
    return new CaseWidgetPage();
  }

  public static ProcessWidgetPage navigateToProcessList() {
    navigateToRelativeLink(PROCESS_LIST_PAGE_URL);
    return new ProcessWidgetPage();
  }

  public static StatisticWidgetPage navigateToStatisticPage() {
    navigateToRelativeLink(STATISTIC_PAGE_URL);
    return new StatisticWidgetPage();
  }
}
