package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.appear;
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

  private static final String MAIN_MENU_PROCESS = ".*main-menu_process_1";
  private static final String DASHBOARD_PARENT_DASHBOARD_PATTERN = ".*DASHBOARD-parent-dashboard";
  private static final String DEFAULT_CASE_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN =
      ".*default-case-list-dashboard-main-dashboard";
  private static final String DASHBOARD_0_PARENT_DASHBOARD_PATTERN = ".*dashboard_0-parent-dashboard";
  private static final String DEFAULT_TASK_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN =
      ".*default-task-list-dashboard-main-dashboard";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
  }

  @Test
  public void testHighlightDefaultMenus() {
    assertMenuHighlighted(DASHBOARD_0_PARENT_DASHBOARD_PATTERN);
    MainMenuPage menuPage = new MainMenuPage();
    menuPage.selectProcessesMenu();
    assertMenuHighlighted(MAIN_MENU_PROCESS);
    menuPage.selectTaskMenuItem();
    assertMenuHighlighted(DEFAULT_TASK_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    menuPage.selectCaseMenu();
    assertMenuHighlighted(DEFAULT_CASE_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    menuPage.selectDashboardMenu();
    assertMenuHighlighted(DASHBOARD_0_PARENT_DASHBOARD_PATTERN);
    NavigationHelper.navigateToProcessList();
    assertMenuHighlighted(MAIN_MENU_PROCESS);
    NavigationHelper.navigateToTaskList();
    assertMenuHighlighted(DEFAULT_TASK_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    NavigationHelper.navigateToCaseList();
    assertMenuHighlighted(DEFAULT_CASE_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    createJSonFile("complex-dashboard.json", PortalVariable.DASHBOARD.key);
    clickLogo();
    assertMenuHighlighted(DASHBOARD_PARENT_DASHBOARD_PATTERN);
    $("li[id$='1-sub-dashboard'].active-menuitem").shouldBe(Condition.appear);
  }

  @Test
  public void testHighlightTaskListWithoutSubDashboards() {
    executeHighlightMenuScenario();
    assertMenuHighlighted(DASHBOARD_0_PARENT_DASHBOARD_PATTERN);
  }

  @Test
  public void testHighlightTaskListWithSubDashboards() {
    createJSonFile("complex-dashboard.json", PortalVariable.DASHBOARD.key);
    executeHighlightMenuScenario();
    assertMenuHighlighted(DASHBOARD_PARENT_DASHBOARD_PATTERN);
  }

  @Test
  public void testHighlightCaseList() {
    new NewDashboardPage();
    MainMenuPage menuPage = new MainMenuPage();
    CaseWidgetNewDashBoardPage caseListPage = menuPage.selectCaseMenu();
    assertMenuHighlighted(DEFAULT_CASE_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    CaseDetailsPage caseDetailsPage = caseListPage.openDetailsFirstCase();
    assertMenuHighlighted(DEFAULT_CASE_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    caseDetailsPage.clickBackButton();
    caseListPage = new CaseWidgetNewDashBoardPage();
    assertMenuHighlighted(DEFAULT_CASE_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
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
    MainMenuPage menuPage = new MainMenuPage();
    menuPage.selectTaskMenuItem();
    assertMenuHighlighted(DEFAULT_TASK_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    TopMenuTaskWidgetPage taskWidgetPage = new TopMenuTaskWidgetPage();
    TaskIFrameTemplatePage taskTemplatePage = taskWidgetPage.startTaskIFrameByIndex(0);
    getActiveMenuItems().shouldBe(size(0));
    taskTemplatePage.clickOnCancelButton();
    ElementsCollection menuItems = getActiveMenuItems();
    menuItems.shouldBe(size(1));
    redirectToRelativeLink(createAlphaCompanyUrl);
    new CaseWidgetNewDashBoardPage();
    assertMenuHighlighted(DEFAULT_CASE_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    menuPage.selectTaskMenuItem();
    $("td.dashboard-tasks__state").shouldBe(Condition.text("Open"));
    taskWidgetPage.startTask(0);
    $("td.dashboard-tasks__state").shouldBe(Condition.text("Done"));
    taskWidgetPage.getTheFirstTaskWidgetByColumn("State").shouldHave(Condition.text("Done"));
    assertMenuHighlighted(DEFAULT_TASK_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    clickLogo();
    assertMenuHighlighted(DEFAULT_CASE_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
  }

  private void executeHighlightMenuScenario() {
    NavigationHelper.navigateToTaskList();
    assertMenuHighlighted(DEFAULT_TASK_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    TopMenuTaskWidgetPage taskWidgetPage = new TopMenuTaskWidgetPage();
    TaskIFrameTemplatePage taskTemplatePage = taskWidgetPage.startTaskIFrameByIndex(0);
    getActiveMenuItems().shouldBe(size(0));
    taskTemplatePage.clickOnCancelButton();
    ElementsCollection menuItems = getActiveMenuItems();
    menuItems.shouldBe(size(1));
    taskWidgetPage = new TopMenuTaskWidgetPage();
    TaskDetailsPage taskDetailsPage = taskWidgetPage.openDashboardTaskDetails("Categoried Leave Request");
    assertMenuHighlighted(DEFAULT_TASK_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    taskDetailsPage.gotoBusinessCase();
    CaseDetailsPage caseDetailsPage = new CaseDetailsPage();
    assertMenuHighlighted(DEFAULT_TASK_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    caseDetailsPage.clickBackButton();
    taskDetailsPage = new TaskDetailsPage();
    assertMenuHighlighted(DEFAULT_TASK_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    taskDetailsPage.clickBackButton();
    taskWidgetPage = new TopMenuTaskWidgetPage();
    assertMenuHighlighted(DEFAULT_TASK_LIST_DASHBOARD_MAIN_DASHBOARD_PATTERN);
    redirectToRelativeLink(createAlphaCompanyUrl);
    NavigationHelper.navigateToTaskList();
    taskDetailsPage = taskWidgetPage.openDashboardTaskDetails("Alpha Company");
    taskDetailsPage.clickStartTaskWithoutDialog();
    getActiveMenuItems().shouldBe(size(0));
    clickLogo();
  }

  private void assertMenuHighlighted(String highlightedMenuItemPattern) {
    ElementsCollection menuItems = getActiveMenuItems();
    menuItems.shouldBe(size(1));
    menuItems.first().shouldHave(Condition.attributeMatching("id", highlightedMenuItemPattern));
  }

  private ElementsCollection getActiveMenuItems() {
    return $$("ul[id$=':main-menu'] > li.active-menuitem").filter(appear);
  }

  private void clickLogo() {
    $(".sidebar-logo").shouldBe(appear).click();
  }
}
