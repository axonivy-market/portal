package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage.AddNewExternalLinkDialog;

@IvyWebTest
public class ProcessWidgetTest extends BaseTest {

  private static final String CASE_MAP_LEAVES = "Case Map: Leave Request";
  private static final String AGOOGLE_LINK = "AGoogle";

  private NewDashboardPage newDashboardPage;
  ProcessWidgetPage processWidget;

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testCaseMapIsDisplayedInExpandedMode() {
    processWidget = NavigationHelper.navigateToProcessList();
    processWidget.waitPageLoaded();
    assertNotNull(processWidget.getProcess(CASE_MAP_LEAVES));
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testDeleteProcess() {
    String processName = "AGoogle";
    String processLink = "google.com";
    processWidget = NavigationHelper.navigateToProcessList();
    createPrivateExternalTestProcess(processName, processLink);
    processWidget = NavigationHelper.navigateToProcessList();
    processWidget.selectViewMode(ProcessWidgetPage.GRID_MODE);
    processWidget.waitForGridProcessListDisplayed();
    processWidget.clickMoreButtonOfFirstGridProcess();
    processWidget.deleteGridProcess(0);
    resetLanguageOfCurrentUser();
  }

  @Test
  // May not run on IE version 11.0.20 or later due to Selenium.
  public void testOpenExternalProcessInNewTab() {
    String processName = "AGoogle";
    String processLink = "google.com";
    processWidget = NavigationHelper.navigateToProcessList();
    createPrivateExternalTestProcess(processName, processLink);
    assertEquals(1, newDashboardPage.countBrowserTab());
    processWidget = NavigationHelper.navigateToProcessList();
    processWidget.waitPageLoaded();
    assertNotNull(processWidget.getProcess(AGOOGLE_LINK));
    processWidget.startProcess(AGOOGLE_LINK);
    webDriverWait().until((webDriver) -> newDashboardPage.countBrowserTab() > 1);
    newDashboardPage.switchLastBrowserTab();
    webDriverWait().until((webDriver) -> newDashboardPage.getPageTitle().length() > 1);
    assertEquals("Google", newDashboardPage.getPageTitle());
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testBreadCrumb() {
    processWidget = NavigationHelper.navigateToProcessList();
    assertEquals("Processes", processWidget.getTextOfCurrentBreadcrumb());

    processWidget.goToHomeFromBreadcrumb();
    newDashboardPage = new NewDashboardPage();
    assertEquals(true, newDashboardPage.isDisplayed());
    resetLanguageOfCurrentUser();
  }

  private void createPrivateExternalTestProcess(String processName, String processLink) {
    AddNewExternalLinkDialog addNewExternalLinkDialog = processWidget.openNewExternalLinkDialog();
    addNewExternalLinkDialog.inputDataForPrivateExternalLink(processName, processLink);
    addNewExternalLinkDialog.submitForm();
  }

}
