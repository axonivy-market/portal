package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.CapabilityType;

import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.SelenideConfig;

public class NavigationHelper {
  private static final String DEFAULT_BROWSER = "firefox";
  private static final String TASK_LIST_PAGE_URL = "portal/1549F58C18A6C562/DefaultTaskListPage.ivp";
  private static final String CASE_LIST_PAGE_URL = "portal/1549F58C18A6C562/CaseListPage.ivp";
  private static final String PROCESS_LIST_PAGE_URL = "portal/1549F58C18A6C562/DefaultProcessStartListPage.ivp";
  private static Config config;
  
  static {
    initConfig();
  }
  
  public static void initConfig() {
    config = new SelenideConfig().browser(DEFAULT_BROWSER);
    config.browserCapabilities().setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY); 
  }

  public static void navigateToRelativeLink(String relativeProcessStartUrl) {
    try {
      open(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartUrl), config);
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

  public static NewDashboardPage navigateToTaskList() {
    navigateToRelativeLink(TASK_LIST_PAGE_URL);
    return new NewDashboardPage();
  }

  public static CaseWidgetNewDashBoardPage navigateToCaseList() {
    navigateToRelativeLink(CASE_LIST_PAGE_URL);
    return new CaseWidgetNewDashBoardPage();
  }

  public static ProcessWidgetPage navigateToProcessList() {
    navigateToRelativeLink(PROCESS_LIST_PAGE_URL);
    return new ProcessWidgetPage();
  }

}
