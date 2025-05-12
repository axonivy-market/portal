package com.axonivy.portal.selenium.test.dashboard;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NavigationDashboardWidgetConfigurationPage;
import com.axonivy.portal.selenium.page.NavigationDashboardWidgetPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

import ch.ivy.addon.portalkit.enums.DashboardDisplayType;

@IvyWebTest
public class DashboardNavigationWidgetTest extends BaseTest{
  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    createTestingTasks();
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }
  
  @Test
  public void testNavigateToAnotherDashboardUsingWidget() {
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    String name = "New public dashboard";
    String icon = "fa-coffee";
    String description = "New public dashboard description";
    List<String> permissions = Arrays.asList("Everybody");
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromScratch(name, icon, description, permissions, DashboardDisplayType.SUB_MENU);
    NewDashboardDetailsEditPage detailsEditPage = new NewDashboardDetailsEditPage();

    detailsEditPage.addWidget();
    addNewNavigationDashboardWidget(detailsEditPage);
    configurationPage.backToHomePage();
    NavigationDashboardWidgetPage navigationWidget = new NavigationDashboardWidgetPage();
    ScreenshotUtils.maximizeBrowser();
    navigationWidget.clickOnNavigateButton();
    newDashboardPage.waitForTaskWidgetLoaded();
    assertTrue(newDashboardPage.isTaskListDisplayed());
    assertTrue(newDashboardPage.isBackButtonAppear());
    newDashboardPage.clickOnBackButton();
    }
  
  @Test
  public void testNavigateToHiddenDashboardUsingWidget() {
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.clickEditDashboardByName("Tasks");
    modificationPage.selectDashboardDisplayType(DashboardDisplayType.HIDDEN);
    modificationPage.saveEditDashboard();
    
    String name = "New public dashboard";
    String icon = "fa-coffee";
    String description = "New public dashboard description";
    List<String> permissions = Arrays.asList("Everybody");
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromScratch(name, icon, description, permissions, DashboardDisplayType.SUB_MENU);
    NewDashboardDetailsEditPage detailsEditPage = new NewDashboardDetailsEditPage();

    detailsEditPage.addWidget();
    addNewNavigationDashboardWidget(detailsEditPage);
    configurationPage.backToHomePage();
    NavigationDashboardWidgetPage navigationWidget = new NavigationDashboardWidgetPage();
    ScreenshotUtils.maximizeBrowser();
    navigationWidget.clickOnNavigateButton();
    newDashboardPage.waitForTaskWidgetLoaded();
    assertTrue(newDashboardPage.isTaskListDisplayed());
    }

  private void addNewNavigationDashboardWidget(NewDashboardDetailsEditPage newDashboardDetailsEditPage) {
    NavigationDashboardWidgetConfigurationPage navigationDashboardWidget =
        newDashboardDetailsEditPage.addNavigationDashboardWidgetConfigurationPage();
    navigationDashboardWidget.setWidgetTitle("Navigate to Tasks");
    navigationDashboardWidget.setWidgetDescription("Navigate to Tasks");
    navigationDashboardWidget.selectTargetDashboard("Tasks");
    navigationDashboardWidget.save();
  }
}
