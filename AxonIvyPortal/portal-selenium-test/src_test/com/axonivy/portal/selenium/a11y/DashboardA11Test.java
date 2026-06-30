package com.axonivy.portal.selenium.a11y;

import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.A11yHelpers;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.ChangePasswordPage;
import com.axonivy.portal.selenium.page.LoginPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProjectVersionPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardA11Test extends BaseTest{

  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void loginPage() {
    redirectToNewDashBoard();
    launchBrowserAndLogoutInDesigner();
    new LoginPage().waitPageLoaded();
    A11yHelpers.makeA11yReport();
  }

  @Test
  public void changePassword() {
    redirectToNewDashBoard();
    var newDashboardPage = new NewDashboardPage();
    newDashboardPage.openChangePasswordPage();
    var changePasswordDialog = $("div[id$='change-password-dialog']").shouldBe(Condition.visible);
    A11yHelpers.makeElementA11yReport(changePasswordDialog, "change-password-dialog");
  }

  @Test
  public void dashboard() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    var homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    A11yHelpers.makeA11yReport();

    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    // Check accessibility of TASK ACTION LINK IN TASK WIDGET
    taskWidget.clickOnTaskActionLink(0);
    var actionPanel = taskWidget.getSelectedTaskAction(0);
    A11yHelpers.makeElementA11yReport(actionPanel, "task-widget-actions");
    actionPanel.$(".ui-overlaypanel-close").click();

    ChangePasswordPage changePasswordPage = homePage.openChangePasswordPage();
    var changePasswordDialog = changePasswordPage.getChangePasswordDialog().shouldBe(Condition.visible);
    A11yHelpers.makeElementA11yReport(changePasswordDialog, "change-password-dialog");
    changePasswordDialog.$("a[aria-label='Close']").shouldBe(Condition.visible).click();
    changePasswordDialog.shouldBe(Condition.disappear);

    ProjectVersionPage projectVersionPage = homePage.openProjectVersionPage();
    var projectVersionDialog = projectVersionPage.getProjectVersionDialog().shouldBe(Condition.visible);
    A11yHelpers.makeElementA11yReport(projectVersionDialog, "project-version-dialog");
  }

  @Test
  public void taskWidget() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    NewDashboardPage dashboardPage = new NewDashboardPage();
    dashboardPage.waitForCaseWidgetLoaded();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();

    // Check accessibility of TASK DELEGATE DIALOG
    taskWidget.clickOnTaskActionLink(0);
    taskWidget.clickTaskAction(0, "Delegate");
    taskWidget.waitForTaskDelegateDialogContent();
    var delegateDialog = taskWidget.getDelegateDialog();
    A11yHelpers.makeElementA11yReport(delegateDialog, "task-delegate-dialog");
    $("a[id$=':cancel-task-delegate-command']").shouldBe(Condition.visible).click();
    delegateDialog.shouldNotBe(Condition.visible);

    // Check accessibility of TASK FILTER WIDGET
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Created", FilterOperator.YESTERDAY);
    taskWidget.saveFilter("TaskOpenYesterday");
    A11yHelpers.makeElementA11yReport(taskWidget.getFilterOverlayPanel(0), "task-filter-widget");

    // Check accessibility of MANAGE FILTERS DIALOG
    taskWidget.openManageFiltersDialog();
    var manageFiltersDialog = $("div[id='manage-filter-dialog']").shouldBe(Condition.visible);
    taskWidget.getSavedFilterItemsByFilterNameOnWidgetManagement().shouldHave(CollectionCondition.size(1));
    A11yHelpers.makeElementA11yReport(manageFiltersDialog, "manage-filters-dialog");
    taskWidget.removeAllFilterItems();
    manageFiltersDialog.$("a[aria-label='Close']").shouldBe(Condition.visible).click();

    // Check accessibility of TASK WIDGET INFORMATION DIALOG
    taskWidget.clickOnButtonWidgetInformation();
    var infoFooter = taskWidget.getWidgetInfomation(0).shouldBe(Condition.visible);
    A11yHelpers.makeElementA11yReport(infoFooter, "widget-information");
    taskWidget.closeWidgetInformationDialog();
  }
}
