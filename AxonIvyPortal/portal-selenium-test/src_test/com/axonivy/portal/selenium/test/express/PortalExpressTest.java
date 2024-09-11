package com.axonivy.portal.selenium.test.express;

import static com.codeborne.selenide.Selenide.$;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.bean.ExpressResponsible;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DefaultExpresTaskPage;
import com.axonivy.portal.selenium.page.ExpressApprovalPage;
import com.axonivy.portal.selenium.page.ExpressEndPage;
import com.axonivy.portal.selenium.page.ExpressFormDefinitionPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
import com.axonivy.portal.selenium.page.ExpressReviewPage;
import com.axonivy.portal.selenium.page.ExpressTaskPage;
import com.axonivy.portal.selenium.page.GlobalSearchResultPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.SearchResultPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.TemplatePage.GlobalSearch;
import com.axonivy.portal.selenium.page.UserTaskWithMailFormPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;



@IvyWebTest
public class PortalExpressTest extends BaseTest {
  protected static final int USER_TASK_INDEX = 0;
  protected static final int USER_TASK_WITH_EMAIL_INDEX = 1;
  protected static final int INFORMATION_EMAIL_INDEX = 2;
  protected static final int APPROVAL_INDEX = 3;

  protected static final int INPUT_TEXT_TYPE_INDEX = 0;
  protected static final int INPUT_NUMBER_TYPE_INDEX = 1;
  protected static final int INPUT_DATE_TYPE_INDEX = 2;
  final String testProcessName = "Test approval";

  protected NewDashboardPage newDashboardPage;
  protected ProcessWidgetPage processWidget;
  protected TaskWidgetPage taskWidgetPage;

  ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
  ExpressResponsible responsible2 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
  ExpressResponsible groupHr = setExpressResponsible("Human resources department", true);


  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(grantPortalPermission);
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testOpenProcessWidgetWithoutCreateExpressWorkflowPermission() throws Exception {
    String denyAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/denyPortalPermission.ivp";
    redirectToRelativeLink(denyAllPermissionsForAdminUserURL);
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    processWidget = mainMenuPage.selectProcessesMenu();
    assertEquals(false, processWidget.hasCreateNewExpressWorkflowLink());
    // run process to grant permission back to normal
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }

  @Test
  public void testCreateDocumentElement() {
    goToCreateExpressProcess();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test 1", "Test description");

    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1));

    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createUploadComponent("Upload");
    formDefinition.countElementPrepareToDrag(1);
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.executeWorkflow();
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    assertTrue(expressTaskPage.isDocumentTableVisible());
    assertTrue(expressTaskPage.isDocumentUploadButtonVisible());
    expressTaskPage.finish();
  }

  @Test
  public void createFullElementsOfForm() {
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test 1", "Test description");

    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1));

    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));
    formDefinition.createTextInputField("Input Text", INPUT_TEXT_TYPE_INDEX, false);
    formDefinition.createTextInputField("Input number", INPUT_NUMBER_TYPE_INDEX, true);
    formDefinition.createTextInputField("Input date", INPUT_DATE_TYPE_INDEX, true);
    formDefinition.createRadioButtonField("Radio", 3);
    formDefinition.createUploadComponent("Upload");
    formDefinition.createCheckboxField("Checkbox", 3);
    formDefinition.createTextAreaField("Text area", true);
    formDefinition.createCheckboxFieldWithDataProvider("Checkbox with data provider");
    formDefinition.countElementPrepareToDrag(8);
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.countElementPrepareToDrag(0);

    assertEquals(14, formDefinition.countNumberOfElementsInPreviewDialog());
    $(By.xpath("//label[text()='Data Provider Item 1']")).shouldBe(Condition.exist, DEFAULT_TIMEOUT);
    $(By.xpath("//label[text()='Data Provider Item 2']")).shouldBe(Condition.exist, DEFAULT_TIMEOUT);
    $(By.xpath("//label[text()='Data Provider Item 3']")).shouldBe(Condition.exist, DEFAULT_TIMEOUT);
  }

  @Test
  public void createFullTaskType() {
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test full task type", "Test description");

    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    ExpressResponsible responsible2 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description",
        Arrays.asList(responsible1, responsible2));

    expressProcessPage.addNewTask(0);
    expressProcessPage.createTask(1, USER_TASK_WITH_EMAIL_INDEX, "Task 2", "Task 2 description",
        Arrays.asList(responsible2));

    expressProcessPage.addNewTask(1);
    expressProcessPage.createTask(2, INFORMATION_EMAIL_INDEX, null, null, null);

    expressProcessPage.addNewTask(2);
    expressProcessPage.createTask(3, APPROVAL_INDEX, "Task 4", "Task 4 description", Arrays.asList(responsible1));

    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    assertEquals(4, formDefinition.countNumberOfSteps());
  }

  @Test
  public void testStartFirstTaskIfCreatorIsAssigned() {
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test 1 Process for creator",
        "Test description Process for creator");
    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task for creator", "Task 1 description for creator",
        Arrays.asList(responsible2));

    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createTextInputField("Input Text", INPUT_TEXT_TYPE_INDEX, false);
    formDefinition.countElementPrepareToDrag(1);
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.executeWorkflow();
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.finish();
    NewDashboardPage home = new NewDashboardPage();
    assertTrue(home.isDisplayed());
  }

  @Test
  public void createUserDefaultProcess() {
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, false, "Test create default process", "Test description");

    ExpressResponsible demoResponsible = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
    expressProcessPage.createDefaultTask(0, "Default Task", Arrays.asList(demoResponsible));
    expressProcessPage.addNewTask(0);
    expressProcessPage.createDefaultTask(1, "Next Default Task", Arrays.asList(demoResponsible));
    expressProcessPage.executeDirectly();

    DefaultExpresTaskPage defaultExpresTaskPage = new DefaultExpresTaskPage();
    defaultExpresTaskPage.enterTextToDefaultTask("Test input");
    defaultExpresTaskPage.finishDefaultTask();

    taskWidgetPage = new NewDashboardPage().openTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Next Default Task");
    taskWidgetPage.startTask(0);
    defaultExpresTaskPage = new DefaultExpresTaskPage();
    defaultExpresTaskPage.enterTextToDefaultTask("Comment");
    defaultExpresTaskPage.finishDefaultTask();

    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksInExpandedModeBy("Test create default process: Final Review");
    taskWidgetPage.startTask(0);

    ExpressReviewPage reviewPage = new ExpressReviewPage();
    reviewPage.finish();
  }

  @Test
  public void testMultiApprovalWhenMultiTask() {
    createAdministratedWorkflow(testProcessName, Arrays.asList(responsible1, groupHr), true);
    startExpressProcess(testProcessName);
    executeExpressProcessWhenMultiApproval();
  }

  @Test
  public void testAbleToStartAdministratedWorkflow() {
    createAdministratedWorkflow(testProcessName, Arrays.asList(responsible1, groupHr), false);
    // responsible1, groupHr is able to start process
    login(TestAccount.ADMIN_USER);
    startExpressProcess(testProcessName);
    login(TestAccount.HR_ROLE_USER);
    startExpressProcess(testProcessName);
    // creator is able to start process
    login(TestAccount.DEMO_USER);
    startExpressProcess(testProcessName);
  }

  @Test
  public void testUserAdminCanViewEditDeleteProcess() {
    createAdministratedWorkflow(testProcessName, Arrays.asList(responsible1, groupHr), false);
    login(TestAccount.ADMIN_USER);
    newDashboardPage = new NewDashboardPage();
    GlobalSearchResultPage searchResultPage = newDashboardPage.inputGlobalSearchKeyword(testProcessName);
    searchResultPage.waitForFirstTabFinishedLoading();
    searchResultPage.clickOnActionButton(testProcessName);

    assertTrue(searchResultPage.isInfoWorkflowIcon());
    assertTrue(searchResultPage.isEditExpressWorkflow(testProcessName));
    assertTrue(searchResultPage.isDeleteExpressWorkflown(testProcessName));
    assertTrue(searchResultPage.isExpressProcessLogo());
  }

  @Test
  public void testUserCreatorCanViewEditDeleteProcess() {
    createAdministratedWorkflow(testProcessName, Arrays.asList(responsible1, groupHr), false);
    newDashboardPage = new NewDashboardPage();
    GlobalSearchResultPage searchResultPage = newDashboardPage.inputGlobalSearchKeyword(testProcessName);
    searchResultPage.waitForFirstTabFinishedLoading();
    searchResultPage.clickOnActionButton(testProcessName);

    assertTrue(searchResultPage.isInfoWorkflowIcon());
    assertTrue(searchResultPage.isEditExpressWorkflow(testProcessName));
    assertTrue(searchResultPage.isDeleteExpressWorkflown(testProcessName));
    assertTrue(searchResultPage.isExpressProcessLogo());
  }

  @Test
  public void testProcessOwnerCanViewAndEditProcess() {
    createAdministratedWorkflow(testProcessName, Arrays.asList(responsible1, groupHr), false);
    login(TestAccount.HR_ROLE_USER);
    newDashboardPage = new NewDashboardPage();
    GlobalSearchResultPage searchResultPage = newDashboardPage.inputGlobalSearchKeyword(testProcessName);
    searchResultPage.waitForFirstTabFinishedLoading();

    assertTrue(searchResultPage.isInfoWorkflowIcon());
    assertTrue(searchResultPage.isExpressProcessLogo());

    searchResultPage.clickOnActionButton(testProcessName);

    assertTrue(searchResultPage.isEditExpressWorkflow(testProcessName));
    assertTrue(searchResultPage.isDeleteExpressWorkflown(testProcessName));
  }

  @Test
  public void testAbleToStartCanViewProcess() {
    login(TestAccount.ADMIN_USER);
    createAdministratedWorkflow(testProcessName, Arrays.asList(groupHr), false);
    login(TestAccount.DEMO_USER);
    newDashboardPage = new NewDashboardPage();
    GlobalSearchResultPage searchResultPage = newDashboardPage.inputGlobalSearchKeyword(testProcessName);
    searchResultPage.waitForFirstTabFinishedLoading();

    assertTrue(searchResultPage.isInfoWorkflowIcon());
    assertTrue(searchResultPage.isExpressProcessLogo());
  }

  @Test
  public void testRejectedApprovalWhenMultiTask() {
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(false, true, testProcessName, "Test description");
    expressProcessPage.fillProcessOwners(Arrays.asList(responsible1, groupHr));
    ExpressFormDefinitionPage formDefinition = configureExpressProcessWhenMultiApproval(expressProcessPage);
    formDefinition.finishWorkflow();
    startExpressProcess(testProcessName);
    rejectWhenMultiApproval();
  }

  @Test
  public void testComplexProcess() {
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, testProcessName, "Test description");
    ExpressFormDefinitionPage formDefinition = configureComplexProcess(expressProcessPage);
    formDefinition.executeWorkflowAndWaitForUserTaskWithEmailDisplay();
    executeComplexProcess();
  }

  protected void goToCreateExpressProcess() {
    processWidget = NavigationHelper.navigateToProcessList();
    processWidget.openExpressPage();
  }

  protected void goToExpressCreationPage() {
    redirectToRelativeLink(expressStartLink);
  }

  private void createAdministratedWorkflow(String expressProcessName, List<ExpressResponsible> processOwners,
      Boolean isMultiApproved) {
    ExpressFormDefinitionPage formDefinition;
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(false, true, expressProcessName, "Test description");
    expressProcessPage.fillProcessOwners(processOwners);
    if (isMultiApproved) {
      formDefinition = configureExpressProcessWhenMultiApproval(expressProcessPage);
    } else {
      formDefinition = configureExpressProcessWhenOneApproval(expressProcessPage);
    }
    formDefinition.finishWorkflow();
  }

  protected ExpressFormDefinitionPage configureExpressProcessWhenMultiApproval(ExpressProcessPage expressProcessPage) {
    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    ExpressResponsible responsible2 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description",
        Arrays.asList(responsible1, responsible2));

    expressProcessPage.addNewTask(0);
    expressProcessPage.createTask(1, APPROVAL_INDEX, "Task 2", "Task 2 description", Arrays.asList(responsible2));

    expressProcessPage.addNewTask(1);
    expressProcessPage.createTask(2, APPROVAL_INDEX, "Task 3", "Task 3 description",
        Arrays.asList(responsible1, responsible2));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createTextInputField("Input Text 1", INPUT_TEXT_TYPE_INDEX, false);
    formDefinition.createTextInputField("Input Number 2", INPUT_NUMBER_TYPE_INDEX, false);
    formDefinition.countElementPrepareToDrag(2);
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.countElementPrepareToDrag(0);
    return formDefinition;
  }

  protected ExpressFormDefinitionPage configureExpressProcessWhenOneApproval(ExpressProcessPage expressProcessPage) {
    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    ExpressResponsible responsible2 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description",
        Arrays.asList(responsible1, responsible2));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createTextInputField("Input Text 1", INPUT_TEXT_TYPE_INDEX, false);
    formDefinition.createTextInputField("Input Number 2", INPUT_NUMBER_TYPE_INDEX, false);
    formDefinition.countElementPrepareToDrag(2);
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.countElementPrepareToDrag(0);
    return formDefinition;
  }

  protected void startExpressProcess(String processName) {
    newDashboardPage = new NewDashboardPage();
    GlobalSearch globalSearch = newDashboardPage.getGlobalSearch();
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(processName);
    searchResultPage.waitForFirstTabFinishedLoading();
    searchResultPage.startProcess(processName);
    new TaskTemplatePage().isDisplayed();
  }

  protected void executeExpressProcessWhenMultiApproval() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.finish();
    executeApproval("Approved at first level", 0);
    executeApproval("Approved at second level", 0);
    login(TestAccount.ADMIN_USER);
    executeApproval("Approved at second level", 1, "Task 3", 2);
    login(TestAccount.DEMO_USER);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    String approvalResult = executeReview();
    assertEquals(
        "Portal Demo User,Approved at first level,Yes," + TestAccount.DEMO_USER.getFullName()
            + ",Approved at second level,Yes," + TestAccount.ADMIN_USER.getFullName() + ",Approved at second level,Yes",
        approvalResult);
    new ExpressEndPage().finish();
  }

  protected void executeApproval(String comment, int taskIndex, String taskNameFilter, int taskCountAfterFiltering) {
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    if (taskWidgetPage.countTasks().size() <= taskIndex) {
      taskWidgetPage.filterTasksInExpandedModeBy("Task", taskCountAfterFiltering);
    }
    if (StringUtils.isNotEmpty(taskNameFilter)) {
      taskWidgetPage.filterTasksInExpandedModeBy(taskNameFilter, taskCountAfterFiltering);
    }
    taskWidgetPage.openStateFilter();
    taskWidgetPage.startTask(taskIndex);
    ExpressApprovalPage approvalPage1 = new ExpressApprovalPage();
    approvalPage1.comment(comment);
    approvalPage1.approve();
  }

  protected void executeApproval(String comment, int taskIndex) {
    executeApproval(comment, taskIndex, null, 1);
  }

  protected String executeReview() {
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.startTask(0);
    ExpressReviewPage reviewPage = new ExpressReviewPage();
    String approvalResult = reviewPage.getApprovalResult();
    reviewPage.finish();
    return approvalResult;
  }

  protected void rejectWhenMultiApproval() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.finish();
    new NewDashboardPage().waitPageLoaded();
    NavigationHelper.navigateToTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.countTasks().shouldBe(CollectionCondition.size(1));
    rejectApproval("Rejected at first level");
    String approvalResult = executeReview();
    assertEquals("Portal Demo User,Rejected at first level,No", approvalResult);
    new ExpressEndPage().finish();
  }

  protected void rejectApproval(String comment) {
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.startTask(0);
    ExpressApprovalPage approvalPage1 = new ExpressApprovalPage();
    approvalPage1.comment(comment);
    approvalPage1.reject();
  }


  protected ExpressFormDefinitionPage configureComplexProcess(ExpressProcessPage expressProcessPage) {
    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    ExpressResponsible responsible2 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_WITH_EMAIL_INDEX, "Task 1", "Task 1 description",
        Arrays.asList(responsible2));

    expressProcessPage.addNewTask(0);
    expressProcessPage.createTask(1, APPROVAL_INDEX, "Task 2", "Task 2 description", Arrays.asList(responsible2));

    expressProcessPage.addNewTask(1);
    expressProcessPage.createTask(2, INFORMATION_EMAIL_INDEX, "Task 3", "Task 3 description",
        Arrays.asList(responsible1, responsible2));
    expressProcessPage.addNewTask(2);
    expressProcessPage.createTask(3, USER_TASK_INDEX, "Task 4", "Task 4 description", Arrays.asList(responsible2));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createTextInputField("Input Text", INPUT_TEXT_TYPE_INDEX, false);
    formDefinition.countElementPrepareToDrag(1);
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.countElementPrepareToDrag(0);
    formDefinition.nextStep();
    formDefinition.inputMailRecipient("wawa@axongroupio.ch");
    formDefinition.inputMailSubject("Information for task 2");
    formDefinition.inputMailContent("Task is finished");
    formDefinition.nextStep();
    formDefinition = new ExpressFormDefinitionPage();
    resizeBrowserTo2kResolution();
    formDefinition.createTextInputField("Input Text 1", INPUT_TEXT_TYPE_INDEX, false);
    formDefinition.createTextInputField("Input Number 2", INPUT_NUMBER_TYPE_INDEX, false);
    formDefinition.countElementPrepareToDrag(2);
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.countElementPrepareToDrag(0);
    return formDefinition;
  }

  protected void executeComplexProcess() {
    UserTaskWithMailFormPage userTaskWithMailFormPage = new UserTaskWithMailFormPage();
    userTaskWithMailFormPage.waitPageLoaded();
    userTaskWithMailFormPage.selectEmailTab();
    userTaskWithMailFormPage.inputData("wawa@axongroupio.ch", "Task information", "Task is created");
    userTaskWithMailFormPage.finish();
    new NewDashboardPage();
    executeApproval("Approved at first level", 0);
    executeUserTask();
    String approvalResult = executeReview("Test approval: Final Review");
    assertEquals("Portal Demo User,Approved at first level,Yes", approvalResult);
    new ExpressEndPage().finish();
  }

  protected void executeUserTask() {
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    refreshPage();
    taskWidgetPage.countTasks().shouldBe(CollectionCondition.size(1));
    taskWidgetPage.startTask(0);
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.finish();
    taskWidgetPage = new TaskWidgetPage();
  }


  protected String executeReview(String taskName) {
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy(taskName);
    taskWidgetPage.startTask(0);
    ExpressReviewPage reviewPage = new ExpressReviewPage();
    String approvalResult = reviewPage.getApprovalResult();
    reviewPage.finish();
    return approvalResult;
  }

}
