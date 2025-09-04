package com.axonivy.portal.selenium.test.caze;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class CaseOwnerTest extends BaseTest {

  private static final String USER_IS_OWNER_URL = "internalSupport/16A68510A341BE6E/userIsOwner.ivp";
  private static final String ROLE_IS_OWNER_URL = "internalSupport/16A68510A341BE6E/roleIsOwner.ivp";
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  private NewDashboardPage newDashboardPage;
  private MainMenuPage mainMenuPage;
  private CaseWidgetPage casePage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.ENABLE_CASE_OWNER.getKey(), "true");
  }

  @AfterEach
  public void destroy() {
    updatePortalSetting(Variable.ENABLE_CASE_OWNER.getKey(), "false");
  }

  @Test
  public void testUserIsOwner() {
    redirectToRelativeLink(USER_IS_OWNER_URL);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);

    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed("demo user is owner"));
  }

  @Test
  public void testRoleIsOwner() {
    login(TestAccount.CASE_OWNER_USER);
    redirectToRelativeLink(ROLE_IS_OWNER_URL);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);

    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed("Test role is owner"));
  }

  @Test
  public void testCaseOwnerFilterOnTaskWidget() {
    createJSonFile("dashboard-has-one-task-widget.json", PortalVariable.DASHBOARD.key);
    redirectToRelativeLink(ROLE_IS_OWNER_URL);
    login(TestAccount.ADMIN_USER);
    newDashboardPage = new NewDashboardPage();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    resizeBrowserTo2kResolution();
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();

    taskEditWidget.preview();
    assertTrue(taskEditWidget.countAllTasks().isEmpty());
    login(TestAccount.CASE_OWNER_USER);

    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(2));
  }
}
