package portal.guitest.common;

import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskWidgetPage;
import vn.wawa.guitest.base.client.Browser;

public class NavigationHelper {
  private static final String TASK_LIST_PAGE_URL = "portal/1549F58C18A6C562/DefaultTaskListPage.ivp";
  private static final String CASE_LIST_PAGE_URL = "portal/1549F58C18A6C562/CaseListPage.ivp";
  private static final String PROCESS_LIST_PAGE_URL = "portal/1549F58C18A6C562/DefaultProcessStartListPage.ivp";
  public static void navigateToRelativeLink(String relativeProcessStartUrl) {
    try {
      Browser.getBrowser().goHome(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartUrl));
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  public static TaskWidgetPage navigateToTaskList() {
    navigateToRelativeLink(TASK_LIST_PAGE_URL);
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
}
