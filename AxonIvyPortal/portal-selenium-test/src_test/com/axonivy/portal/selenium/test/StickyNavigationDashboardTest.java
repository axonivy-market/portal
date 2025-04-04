package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.ReorderDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class StickyNavigationDashboardTest extends BaseTest {

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(grantPortalPermission);
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testStickyDashboardAfterStartTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"), DEFAULT_TIMEOUT);
    newDashboardPage.selectDashboard(1);
    newDashboardPage.waitForTaskListDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"), DEFAULT_TIMEOUT);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget("Your Tasks");
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.clickOnTaskActionLink(0);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"), DEFAULT_TIMEOUT);
  }

  @Test
  public void testStickyDashboardAfterReorderDashboard() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.reorderPublicDashboard();
    ReorderDashboardPage reorderDashboardPage = new ReorderDashboardPage();
    reorderDashboardPage.reorderPublicDashboard("New public dashboard 1", "New public dashboard");
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"), DEFAULT_TIMEOUT);
  }

  @Test
  public void testStickyDashboardAfterDeleteDashboard() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"), DEFAULT_TIMEOUT);
    newDashboardPage.selectDashboard(2);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 2"), DEFAULT_TIMEOUT);
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.clickDeleteDashboardByName("New public dashboard 2");
    newDashboardPage = configurationPage.backToHomePage();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"), DEFAULT_TIMEOUT);
  }

  @Test
  public void testStickyDashboardAfterHideDashboard() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"), DEFAULT_TIMEOUT);
    newDashboardPage.selectDashboard(1);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"), DEFAULT_TIMEOUT);
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.reorderPublicDashboard();
    newDashboardPage = configurationPage.backToHomePage();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"), DEFAULT_TIMEOUT);
  }

  @Test
  public void testStickyDashboardAfterStartProcess() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"), DEFAULT_TIMEOUT);
    newDashboardPage.selectDashboard(1);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"), DEFAULT_TIMEOUT);
    ProcessWidgetNewDashBoardPage processWidget = new ProcessWidgetNewDashBoardPage();
    processWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    WaitHelper.waitForNavigation(() -> processWidget.startProcessByName("Categoried Leave Request"));
    newDashboardPage.waitPageLoaded();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"), DEFAULT_TIMEOUT);
  }

  @Test
  public void testStickyDashboardAfterAddPrivateDashboard() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"), DEFAULT_TIMEOUT);
    newDashboardPage.selectDashboard(1);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"), DEFAULT_TIMEOUT);
    String name = "New private dashboard 3";
    String icon = "fa-coffee";
    String description = "New private dashboard 3 description";
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.createPrivateDashboardFromScratch(name, icon, description);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    configurationPage = newDashboardDetailsEditPage.backToConfigurationPage();
    newDashboardPage = configurationPage.backToHomePage();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New private dashboard 3"), DEFAULT_TIMEOUT);
  }

  @Test
  public void testStickyDashboardAfterAddPublicDashboard() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"), DEFAULT_TIMEOUT);
    newDashboardPage.selectDashboard(1);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"), DEFAULT_TIMEOUT);
    String name = "New public dashboard 3";
    String icon = "fa-coffee";
    String description = "New public dashboard 3 description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Everybody");
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromScratch(name, icon, description, permissions, DashboardDisplayType.SUB_MENU);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    configurationPage = newDashboardDetailsEditPage.backToConfigurationPage();
    newDashboardPage = configurationPage.backToHomePage();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 3"), DEFAULT_TIMEOUT);
  }

}
