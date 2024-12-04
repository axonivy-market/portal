package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskIFrameTemplatePage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class MenuHighlightTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
  }

  @Test
  public void testHighlightTaskList() {
    executeHighlightMenuScenario();
    assertMenuHighlighted(".*dashboard_0-parent-dashboard");

  }

  private void executeHighlightMenuScenario() {
    NavigationHelper.navigateToTaskList();
    $("li[id$='default-task-list-dashboard-main-dashboard']").shouldHave(Condition.cssClass("active-menuitem"));
    assertTaskListMenuHighlighted();
    TopMenuTaskWidgetPage taskWidgetPage = new TopMenuTaskWidgetPage();
    TaskIFrameTemplatePage taskTemplatePage = taskWidgetPage.startTaskIFrameByIndex(0);
    getMenuItems().shouldBe(size(0));
    taskTemplatePage.clickOnCancelButton();
    ElementsCollection menuItems = getMenuItems();
    menuItems.shouldBe(size(1));
    taskWidgetPage = new TopMenuTaskWidgetPage();
    TaskDetailsPage taskDetailsPage = taskWidgetPage.openDashboardTaskDetails("Categoried Leave Request");
    assertTaskListMenuHighlighted();
    taskDetailsPage.gotoBusinessCase();
    CaseDetailsPage caseDetailsPage = new CaseDetailsPage();
    assertTaskListMenuHighlighted();
    caseDetailsPage.clickBackButton();
    taskDetailsPage = new TaskDetailsPage();
    assertTaskListMenuHighlighted();
    $("[id$=':task-detail-title-form:back-to-previous-page']").scrollIntoView(false);
    taskDetailsPage.clickBackButton();
    taskWidgetPage = new TopMenuTaskWidgetPage();
    assertTaskListMenuHighlighted();
    $(".sidebar-logo").shouldBe(Condition.appear).click();
  }

  private void assertTaskListMenuHighlighted() {
    assertMenuHighlighted(".*default-task-list-dashboard-main-dashboard");
  }

  private void assertMenuHighlighted(String highlightedMenuItemPattern) {
    ElementsCollection menuItems = getMenuItems();
    menuItems.shouldBe(size(1));
    menuItems.first().shouldHave(Condition.attributeMatching("id", highlightedMenuItemPattern));
  }
  private ElementsCollection getMenuItems() {
    return $$("ul[id$=':main-menu'] > li.active-menuitem").filter(Condition.appear);
  }

  @Test
  public void testHighlightDefaultMenu() {
    showNewDashboard();
    assertMenuHighlighted(".*dashboard_0-parent-dashboard");
    MainMenuPage menuPage = new MainMenuPage();
    menuPage.selectProcessesMenu();
    assertMenuHighlighted(".*main-menu_process_1");
    menuPage.selectTaskMenu();
    assertTaskListMenuHighlighted();
    menuPage.selectCaseMenu();
    assertMenuHighlighted(".*default-case-list-dashboard-main-dashboard");
    menuPage.selectDashboardMenu();
    assertMenuHighlighted(".*dashboard_0-parent-dashboard");
  }

  @Test
  public void testHighlightMenu() {
    createJSonFile("complex-dashboard.json", PortalVariable.DASHBOARD.key);
    executeHighlightMenuScenario();
    assertMenuHighlighted(".*DASHBOARD-parent-dashboard");
  }

  @Test
  public void tesChangingtHomepageInUserProfile() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();
    newDashboardPage.waitForGrowlMessageDisappear();

    UserProfilePage profilePage = newDashboardPage.openMyProfilePage();
    profilePage.changeNewDashboardPageToCase();
    profilePage.saveWithoutWaitingNavigation();
    new CaseWidgetNewDashBoardPage();
  }
}
