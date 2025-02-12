package com.axonivy.portal.selenium.test.dashboard;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class DashboardCloneWidgetTest extends BaseTest {

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
  public void cloneWidgetToNewDashboard() {
    DashboardConfigurationPage configurationPage = newDashboardPage
        .openDashboardConfigurationPage();
    configurationPage.openEditPublicDashboardsPage();

    configurationPage.openCreatePublicDashboardMenu();
    var newDashboardDialog = configurationPage.openCreateDashboardDialog();
    configurationPage.changeDashboardTitle(newDashboardDialog,
        "A test dashboard");

    configurationPage.createPublicDashboardFromScratch(newDashboardDialog,
        Arrays.asList("Everybody"));

    NewDashboardDetailsEditPage detailsEditPage = new NewDashboardDetailsEditPage();
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();

    configurationPage = newDashboardPage
        .openDashboardConfigurationPage();
    configurationPage.openEditPublicDashboardsPage();
    configurationPage
        .clickButtonOnDashboardConfigurationActionMenu("Configuration");

    detailsEditPage = new NewDashboardDetailsEditPage();
    detailsEditPage.waitForTaskWidgetLoaded();
    detailsEditPage.openCloneWidgetDialog("Your Tasks", 0);
    detailsEditPage.cloneWidget("A test dashboard");
    detailsEditPage.waitForTaskWidgetLoaded();

    MainMenuPage menu = detailsEditPage.openMainMenu();
    WaitHelper.waitForNavigation(() -> {
      menu.selectDashboardByName("A test dashboard");
    });

    newDashboardPage = new NewDashboardPage();
    assertTrue(newDashboardPage.hasWidgetWithName("Your Tasks"));
  }

  @Test
  public void cloneCaseWidgetFromAnotherDashboard() {
    DashboardConfigurationPage configurationPage = newDashboardPage
        .openDashboardConfigurationPage();
    configurationPage.openEditPublicDashboardsPage();

    configurationPage.openCreatePublicDashboardMenu();
    var newDashboardDialog = configurationPage.openCreateDashboardDialog();
    configurationPage.changeDashboardTitle(newDashboardDialog, "My dashboard");

    configurationPage.createPublicDashboardFromScratch(newDashboardDialog,
        Arrays.asList("Everybody"));

    NewDashboardDetailsEditPage detailsEditPage = new NewDashboardDetailsEditPage();
    detailsEditPage.addWidget();
    detailsEditPage.cloneWidgetFromDashboard("Dashboard",
        "Your Cases", "Cloned Case Widget", false);

    assertTrue(detailsEditPage.hasWidgetWithName("Cloned Case Widget"));
  }

  @Test
  public void cloneStatisticFromAnotherDashboard() {
    DashboardConfigurationPage configurationPage = newDashboardPage
        .openDashboardConfigurationPage();
    configurationPage.openEditPublicDashboardsPage();

    configurationPage.openCreatePublicDashboardMenu();
    var newDashboardDialog = configurationPage.openCreateDashboardDialog();
    configurationPage.changeDashboardTitle(newDashboardDialog, "My dashboard");

    configurationPage.createPublicDashboardFromScratch(newDashboardDialog,
        Arrays.asList("Everybody"));

    NewDashboardDetailsEditPage detailsEditPage = new NewDashboardDetailsEditPage();
    detailsEditPage.addWidget();
    detailsEditPage.cloneWidgetFromDashboard("Dashboard", "Tasks Due Today",
        "Tasks Due Today", true);

    assertTrue(detailsEditPage.hasWidgetWithName("Tasks Due Today"));
  }
}
