package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.ReorderDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class StickyNavigationDashboardTest extends BaseTest {
  
  private NewDashboardPage newDashboardPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
    login(TestAccount.ADMIN_USER);
  }
  
  @Test
  public void testStickyDashboardAfterStartTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
    newDashboardPage.selectDashboard(1);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"));
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget("Your Tasks");
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.clickOnTaskActionLink(0);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"));
  }
  
  @Test
  public void testStickyDashboardAfterReorderDashboard() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.reorderPublicDashboard();
    ReorderDashboardPage reorderDashboardPage = new ReorderDashboardPage();
    reorderDashboardPage.reorderDashboard("New public dashboard 1", "New public dashboard");
    newDashboardPage = reorderDashboardPage.saveAndBackToHomePage();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
  }
  
  @Test
  public void testStickyDashboardAfterDeleteDashboard() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
    newDashboardPage.selectDashboard(2);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 2"));
    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    configPage.clickDeleteDashboardByName("New public dashboard 2");
    newDashboardPage = configPage.navigateToNewDashboardPage();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
  }
  
  @Test
  public void testStickyDashboardAfterHideDashboard() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
    newDashboardPage.selectDashboard(1);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"));
    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.reorderPrivateDashboard();
    ReorderDashboardPage reorderDashboardPage = new ReorderDashboardPage();
    reorderDashboardPage.toggleVisibility("New public dashboard 1");
    newDashboardPage = reorderDashboardPage.saveAndBackToHomePage();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
  }
  
  @Test
  public void testStickyDashboardAfterStartProcess() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
    newDashboardPage.selectDashboard(1);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"));
    ProcessWidgetNewDashBoardPage processWidget = new ProcessWidgetNewDashBoardPage();
    processWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    processWidget.startProcessByName("Categoried Leave Request");
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"));
  }
  
  @Test
  public void testStickyDashboardAfterAddPrivateDashboard() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
    newDashboardPage.selectDashboard(1);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"));
    String name = "New private dashboard 3";
    String description = "New private dashboard 3 description";
    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.openCreatePrivateDashboardMenu();
    newDashboardPage.createPrivateDashboardFromScratch(name, description);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.backToConfigurationPage().navigateToNewDashboardPage();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"));
  }
  
  @Test
  public void testStickyDashboardAfterAddPublicDashboard() {
    createJSonFile("multi-dashboards.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard"));
    newDashboardPage.selectDashboard(1);
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"));
    String name = "New public dashboard 3";
    String description = "New public dashboard 3 description";
    List<String> permissions = new ArrayList<>();
    permissions.add("Everybody");
    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.openCreatePublicDashboardMenu();
    newDashboardPage.createPublicDashboardFromScratch(name, description, permissions);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.waitPageDisplay();
    newDashboardDetailsEditPage.backToConfigurationPage().navigateToNewDashboardPage();
    newDashboardPage.getDashboardActive().shouldBe(Condition.text("New public dashboard 1"));
  }

}
