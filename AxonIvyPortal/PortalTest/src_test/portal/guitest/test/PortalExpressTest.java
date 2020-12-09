package portal.guitest.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskWidgetPage;

public class PortalExpressTest extends BaseTest {
  protected HomePage homePage;
  protected ProcessWidgetPage processWidgetPage;
  protected ExpressProcessPage expressProcessPage;
  protected TaskWidgetPage taskWidgetPage;

  @Before
  public void setup() {
    super.setup();
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    homePage = new HomePage();
    taskWidgetPage = new TaskWidgetPage();
    processWidgetPage = new ProcessWidgetPage();
    processWidgetPage.loadAllProcessesPage();
    processWidgetPage.goToNewExpressWorkFlowPage();
  }

  @Test
  public void createAdHocWorkFlowProcess() {
    expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, "Ad-hoc Workflow process", "Ad-hoc Workflow process description");
    createTasksAndMoveNextStep();
    assertTrue(expressProcessPage.isCreateElementFormDisplayed());
    createElementsAndMoveToPanel();
    assertTrue(expressProcessPage.hasElementsInPanel());
    expressProcessPage.executeWorkflowDirectly();
    expressProcessPage.waitForElementDisplayed(By.cssSelector("fieldset[id$='form:applicantFieldSet']"), true);
    assertTrue(expressProcessPage.isStartWorkFlowButtonDisplayed());
    expressProcessPage.startWorkflow();
    homePage.waitForElementPresent(By.cssSelector("div[id$='task-widget:task-view']"), true);
    taskWidgetPage.startTask(0);
    assertTrue(expressProcessPage.hasElementsInPanel());
    expressProcessPage.acknowledgedTask();
    expressProcessPage.closeWorkflow();
    homePage.waitForElementPresent(By.cssSelector("div[id$='task-widget:task-view']"), true);
  }

  private void createTasksAndMoveNextStep() {
    expressProcessPage.createDefaultTask(0, "First task", "First task description");
    expressProcessPage.waitForChooseAssigneeDialogHidden();
    expressProcessPage.addNewOrRemoveTask(true);
    expressProcessPage.createDefaultTask(1, "Second task", "Second task description");
    expressProcessPage.waitForChooseAssigneeDialogHidden();
    expressProcessPage.addNewOrRemoveTask(false);
    expressProcessPage.moveNextStep();
    expressProcessPage.waitForCreateElementFormDisplayed();
  }

  private void createElementsAndMoveToPanel() {
    expressProcessPage.createDefaultInputField("First Input", "First Input Label");
    expressProcessPage.createDefaultInputField("Second Input", "Second Input Label");
    expressProcessPage.moveAllElementToDragAndDrogPanel();
  }

  @Test
  public void createAdministratedWorkFlowProcess() {
    expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(false, "Administrated Workflow process",
        "Administrated Workflow process description");
    createTasksAndMoveNextStep();
    assertTrue(expressProcessPage.isCreateElementFormDisplayed());
    createElementsAndMoveToPanel();
    assertTrue(expressProcessPage.hasElementsInPanel());
    expressProcessPage.saveWorkflow();
    homePage.waitForElementPresent(By.cssSelector("div[id$='task-widget:task-view']"), true);
    processWidgetPage.loadAllProcessesPage();
    processWidgetPage.goToCreatedExpressWorkFlowPage();
    expressProcessPage.waitForElementDisplayed(By.cssSelector("fieldset[id$='form:applicantFieldSet']"), true);
    assertTrue(expressProcessPage.isStartWorkFlowButtonDisplayed());
    expressProcessPage.startWorkflow();
    homePage.waitForElementPresent(By.cssSelector("div[id$='task-widget:task-view']"), true);
    taskWidgetPage.startTask(0);
    assertTrue(expressProcessPage.hasElementsInPanel());
    expressProcessPage.acknowledgedTask();
    expressProcessPage.closeWorkflow();
    homePage.waitForElementPresent(By.cssSelector("div[id$='task-widget:task-view']"), true);
    processWidgetPage.loadAllProcessesPage();
    expressProcessPage.deleteCreatedExpressWorkFlowProcess();
    assertFalse(expressProcessPage.hasExpressProcessList());
  }
}
