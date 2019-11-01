package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.DefaultExpresTaskPage;
import portal.guitest.page.ExpressApprovalPage;
import portal.guitest.page.ExpressEndPage;
import portal.guitest.page.ExpressFormDefinitionPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.ExpressReviewPage;
import portal.guitest.page.ExpressTaskPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.TemplatePage.GlobalSearch;
import portal.guitest.page.UserTaskWithMailFormPage;

public class PortalExpressTest extends BaseTest {
	protected static final int USER_TASK_INDEX = 0;
	protected static final int USER_TASK_WITH_EMAIL_INDEX = 1;
	protected static final int INFORMATION_EMAIL_INDEX = 2;
	protected static final int APPROVAL_INDEX = 3;

	protected static final int INPUT_TEXT_TYPE_INDEX = 0;
	protected static final int INPUT_NUMBER_TYPE_INDEX = 1;
	protected static final int INPUT_DATE_TYPE_INDEX = 2;

	protected HomePage homePage;
	protected ProcessWidgetPage processWidget;
	protected TaskWidgetPage taskWidgetPage;

	ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
	ExpressResponsible responsible2 = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
	ExpressResponsible groupHr = new ExpressResponsible("Human resources department", true);

	@Override
	@Before
	public void setup() {
		super.setup();
		redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
		redirectToRelativeLink("portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp");
		homePage = new HomePage();
	}

	@Test
	public void testOpenProcessWidgetWithoutCreateExpressWorkflowPermission() throws Exception {
		String denyAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/denyPortalPermission.ivp";
		redirectToRelativeLink(denyAllPermissionsForAdminUserURL);
		MainMenuPage mainMenuPage = homePage.openMainMenu();
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

		ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

		expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1));

		ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
		formDefinition.createUploadComponent("Upload");
		formDefinition.moveAllElementToDragAndDrogPanel();
		formDefinition.executeWorkflow();
		Sleeper.sleep(2000);
		ExpressTaskPage expressTaskPage = new ExpressTaskPage();
		Assert.assertTrue(expressTaskPage.isDocumentTableVisible());
		Assert.assertTrue(expressTaskPage.isDocumentUploadButtonVisible());
		expressTaskPage.finish();
	}

	@Test
	public void createFullElementsOfForm() {
		goToCreateExpressProcess();
		ExpressProcessPage expressProcessPage = new ExpressProcessPage();
		expressProcessPage.fillProcessProperties(true, true, "Test 1", "Test description");

		ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);

		expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1));

		ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
		formDefinition.createTextInputField("Input Text", INPUT_TEXT_TYPE_INDEX, false);
		formDefinition.createTextInputField("Input number", INPUT_NUMBER_TYPE_INDEX, true);
		formDefinition.createTextInputField("Input date", INPUT_DATE_TYPE_INDEX, true);
		formDefinition.createRadioButtonField("Radio", 3);
		formDefinition.createUploadComponent("Upload");
		formDefinition.createCheckboxField("Checkbox", 3);
		formDefinition.createTextAreaField("Text area", true);
		formDefinition.createCheckboxFieldWithDataProvider("Checkbox with data provider");
		formDefinition.moveAllElementToDragAndDrogPanel();
		// if we have radio button or checkbox, remember multiply with number of options
		// Example: we have 1 checkbox with 2 options, 1 radio with 3 options. Total we
		// have 5 inputs
		// plus 1 hidden input
		Assert.assertEquals(15, formDefinition.countNumberOfElementsInPreviewDialog());
		Assert.assertNotNull(formDefinition.findElementByXpath("//label[text()='Data Provider Item 1']"));
		Assert.assertNotNull(formDefinition.findElementByXpath("//label[text()='Data Provider Item 2']"));
		Assert.assertNotNull(formDefinition.findElementByXpath("//label[text()='Data Provider Item 3']"));
	}

	@Test
	public void createFullTaskType() {
		goToCreateExpressProcess();
		ExpressProcessPage expressProcessPage = new ExpressProcessPage();
		expressProcessPage.fillProcessProperties(true, true, "Test full task type", "Test description");

		ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
		ExpressResponsible responsible2 = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

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
		Assert.assertEquals(4, formDefinition.countNumberOfSteps());
	}

	@Test
	public void testStartFirstTaskIfCreatorIsAssigned() {
		goToCreateExpressProcess();
		ExpressProcessPage expressProcessPage = new ExpressProcessPage();
		expressProcessPage.fillProcessProperties(true, true, "Test 1 Process for creator",
				"Test description Process for creator");
		expressProcessPage.createTask(0, USER_TASK_INDEX, "Task for creator", "Task 1 description for creator",
				Arrays.asList(responsible2));

		ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
		formDefinition.createTextInputField("Input Text", INPUT_TEXT_TYPE_INDEX, false);
		formDefinition.moveAllElementToDragAndDrogPanel();
		formDefinition.executeWorkflow();
		Sleeper.sleep(2000);
		ExpressTaskPage expressTaskPage = new ExpressTaskPage();
		expressTaskPage.finish();
		HomePage home = new HomePage();
		home.waitForPageLoaded();
	}

	@Test
	public void createUserDefaultProcess() {
		goToCreateExpressProcess();
		ExpressProcessPage expressProcessPage = new ExpressProcessPage();
		expressProcessPage.waitForPageLoaded();
		expressProcessPage.fillProcessProperties(true, false, "Test create default process", "Test description");

		ExpressResponsible demoResponsible = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
		expressProcessPage.createDefaultTask(0, "Default Task", Arrays.asList(demoResponsible));
		expressProcessPage.addNewTask(0);
		expressProcessPage.createDefaultTask(1, "Next Default Task", Arrays.asList(demoResponsible));
		expressProcessPage.executeDirectly();

		DefaultExpresTaskPage defaultExpresTaskPage = new DefaultExpresTaskPage();
		defaultExpresTaskPage.enterTextToDefaultTask("Test input");
		defaultExpresTaskPage.finishDefaultTask();

		taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.filterTasksBy("Next Default Task");
		taskWidgetPage.startTask(0);
		defaultExpresTaskPage = new DefaultExpresTaskPage();
		defaultExpresTaskPage.enterTextToDefaultTask("Comment");
		defaultExpresTaskPage.finishDefaultTask();

		taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.filterTasksBy("Test create default process: Final Review");
		taskWidgetPage.startTask(0);
		
		ExpressReviewPage reviewPage = new ExpressReviewPage();
    reviewPage.finish();
	}

	@Test
	public void testMultiApprovalWhenMultiTask() {
		createAdministratedWorkflow("Test approval", Arrays.asList(responsible1, groupHr), true);
		startExpressProcess("Test approval");
		executeExpressProcessWhenMultiApproval();
	}

	@Test
	public void testAbleToStartAdministratedWorkflow() {
		createAdministratedWorkflow("Test approval", Arrays.asList(responsible1, groupHr), false);
		// responsible1, groupHr is able to start process
		login(TestAccount.ADMIN_USER);
		startExpressProcess("Test approval");
		login(TestAccount.HR_ROLE_USER);
		startExpressProcess("Test approval");
		// creator is able to start process
		login(TestAccount.DEMO_USER);
		startExpressProcess("Test approval");
	}

	@Test
	public void testUserAdminCanViewEditDeleteProcess() {
		createAdministratedWorkflow("Test approval", Arrays.asList(responsible1, groupHr), false);
		login(TestAccount.ADMIN_USER);
		GlobalSearch globalSearch = homePage.getGlobalSearch();
		SearchResultPage searchResultPage = globalSearch.inputSearchKeyword("Test approval");

		assertTrue(searchResultPage.isInfoWorkflowIcon());
		assertTrue(searchResultPage.isEditExpressWorkflow());
		assertTrue(searchResultPage.isDeleteExpressWorkflown());
		assertTrue(searchResultPage.isExpressProcessLogo());
	}

	@Test
	public void testUserCreatorCanViewEditDeleteProcess() {
		createAdministratedWorkflow("Test approval", Arrays.asList(responsible1, groupHr), false);
		GlobalSearch globalSearch = homePage.getGlobalSearch();
		SearchResultPage searchResultPage = globalSearch.inputSearchKeyword("Test approval");

		assertTrue(searchResultPage.isInfoWorkflowIcon());
		assertTrue(searchResultPage.isEditExpressWorkflow());
		assertTrue(searchResultPage.isDeleteExpressWorkflown());
		assertTrue(searchResultPage.isExpressProcessLogo());
	}

	@Test
	public void testProcessOwnerCanViewAndEditProcess() {
		createAdministratedWorkflow("Test approval", Arrays.asList(responsible1, groupHr), false);
		login(TestAccount.HR_ROLE_USER);
		GlobalSearch globalSearch = homePage.getGlobalSearch();
		SearchResultPage searchResultPage = globalSearch.inputSearchKeyword("Test approval");

		assertTrue(searchResultPage.isInfoWorkflowIcon());
		assertTrue(searchResultPage.isExpressProcessLogo());
		assertTrue(searchResultPage.isEditExpressWorkflow());
		assertTrue(searchResultPage.isDeleteExpressWorkflown());
	}
	
	@Test
  public void testAbleToStartCanViewProcess() {
    login(TestAccount.ADMIN_USER);
    createAdministratedWorkflow("Test approval", Arrays.asList(groupHr), false);
    login(TestAccount.DEMO_USER);
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword("Test approval");
    
    assertTrue(searchResultPage.isInfoWorkflowIcon());
    assertTrue(searchResultPage.isExpressProcessLogo());
  }
	
	@Test
	public void testRejectedApprovalWhenMultiTask() {
		goToCreateExpressProcess();
		ExpressProcessPage expressProcessPage = new ExpressProcessPage();
		expressProcessPage.fillProcessProperties(false, true, "Test approval", "Test description");
		expressProcessPage.fillProcessOwners(Arrays.asList(responsible1, groupHr));
		ExpressFormDefinitionPage formDefinition = configureExpressProcessWhenMultiApproval(expressProcessPage);
		formDefinition.finishWorkflow();
		startExpressProcess("Test approval");
		rejectWhenMultiApproval();
	}

	@Test
	public void testComplexProcess() {
		goToCreateExpressProcess();
		ExpressProcessPage expressProcessPage = new ExpressProcessPage();
		expressProcessPage.fillProcessProperties(true, true, "Test approval", "Test description");
		ExpressFormDefinitionPage formDefinition = configureComplexProcess(expressProcessPage);
		formDefinition.executeWorkflow();
		executeComplexProcess();
	}

	private void createAdministratedWorkflow(String expressProcessName, List<ExpressResponsible> processOwners,
			Boolean isMultiApproved) {
		ExpressFormDefinitionPage formDefinition;
		goToCreateExpressProcess();
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

	protected void rejectWhenMultiApproval() {
		ExpressTaskPage expressTaskPage = new ExpressTaskPage();
		expressTaskPage.finish();
		assertEquals(1, new TaskWidgetPage().countTasks());
		rejectApproval("Rejected at first level");
		String approvalResult = executeReview();
		Assert.assertEquals("Portal Demo User,Rejected at first level,No",
				approvalResult);
		new ExpressEndPage().finish();
	}

	protected ExpressFormDefinitionPage configureExpressProcessWhenOneApproval(ExpressProcessPage expressProcessPage) {
		ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
		ExpressResponsible responsible2 = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

		expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description",
				Arrays.asList(responsible1, responsible2));
		ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
		formDefinition.createTextInputField("Input Text 1", INPUT_TEXT_TYPE_INDEX, false);
		formDefinition.createTextInputField("Input Number 2", INPUT_NUMBER_TYPE_INDEX, false);
		formDefinition.moveAllElementToDragAndDrogPanel();
		return formDefinition;
	}

	protected ExpressFormDefinitionPage configureExpressProcessWhenMultiApproval(
			ExpressProcessPage expressProcessPage) {
		ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
		ExpressResponsible responsible2 = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

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
		formDefinition.moveAllElementToDragAndDrogPanel();
		return formDefinition;
	}

	protected ExpressFormDefinitionPage configureComplexProcess(ExpressProcessPage expressProcessPage) {
		ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
		ExpressResponsible responsible2 = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

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
		formDefinition.moveAllElementToDragAndDrogPanel();
		formDefinition.nextStep();
		formDefinition.inputMailRecipient("wawa@axonivy.io");
		formDefinition.inputMailSubject("Information for task 2");
		formDefinition.inputMailContent("Task is finished");
		formDefinition.nextStep();
		formDefinition = new ExpressFormDefinitionPage();
		formDefinition.createTextInputField("Input Text 1", INPUT_TEXT_TYPE_INDEX, false);
		formDefinition.createTextInputField("Input Number 2", INPUT_NUMBER_TYPE_INDEX, false);
		formDefinition.moveAllElementToDragAndDrogPanel();
		return formDefinition;
	}

	protected void executeExpressProcessWhenMultiApproval() {
		ExpressTaskPage expressTaskPage = new ExpressTaskPage();
		expressTaskPage.finish();
		executeApproval("Approved at first level");
		executeApproval("Approved at second level");
		assertEquals(0, new TaskWidgetPage().countTasks());
		login(TestAccount.ADMIN_USER);
		executeApproval("Approved at second level");
		login(TestAccount.DEMO_USER);

		String approvalResult = executeReview();
		Assert.assertEquals("Portal Demo User,Approved at first level,Yes," + TestAccount.DEMO_USER.getFullName()
				+ ",Approved at second level,Yes," + TestAccount.ADMIN_USER.getFullName()
				+ ",Approved at second level,Yes", approvalResult);
		new ExpressEndPage().finish();
	}

	protected void executeComplexProcess() {
		UserTaskWithMailFormPage userTaskWithMailFormPage = new UserTaskWithMailFormPage();
		userTaskWithMailFormPage.waitForPageLoaded();
		userTaskWithMailFormPage.selectEmailTab();
		userTaskWithMailFormPage.inputData("wawa@axonivy.io", "Task information", "Task is created");
		userTaskWithMailFormPage.finish();
		executeApproval("Approved at first level");
		executeUserTask("Task 4");
		String approvalResult = executeReview("Test approval: Final Review");
		Assert.assertEquals("Portal Demo User,Approved at first level,Yes", approvalResult);
		new ExpressEndPage().finish();
	}

	protected String executeReview() {
		taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.startTask(0);
		ExpressReviewPage reviewPage = new ExpressReviewPage();
		String approvalResult = reviewPage.getApprovalResult();
		reviewPage.finish();
		return approvalResult;
	}

	protected String executeReview(String taskName) {
		taskWidgetPage = new TaskWidgetPage();
		Sleeper.sleep(2000);
		taskWidgetPage.filterTasksBy(taskName);
		taskWidgetPage.startTask(0);
		ExpressReviewPage reviewPage = new ExpressReviewPage();
		String approvalResult = reviewPage.getApprovalResult();
		reviewPage.finish();
		return approvalResult;
	}

	protected void executeUserTask() {
		taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.startTask(0);
		ExpressTaskPage expressTaskPage = new ExpressTaskPage();
		expressTaskPage.finish();
		HomePage home = new HomePage();
		home.waitForPageLoaded();
	}

	protected void executeUserTask(String taskName) {
		taskWidgetPage = new TaskWidgetPage();
		Sleeper.sleep(2000);
		taskWidgetPage.filterTasksBy(taskName);
		taskWidgetPage.startTask(0);
		ExpressTaskPage expressTaskPage = new ExpressTaskPage();
		expressTaskPage.finish();
	}

	protected void startExpressProcess(String processName) {
		homePage = new HomePage();
		GlobalSearch globalSearch = homePage.getGlobalSearch();
		SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(processName);
		searchResultPage.startProcess(processName);
	}

	protected void executeApproval(String comment) {
		taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.startTask(0);
		ExpressApprovalPage approvalPage1 = new ExpressApprovalPage();
		approvalPage1.comment(comment);
		approvalPage1.approve();
		HomePage home = new HomePage();
		home.waitForPageLoaded();
	}

	protected void rejectApproval(String comment) {
		taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.startTask(0);
		ExpressApprovalPage approvalPage1 = new ExpressApprovalPage();
		approvalPage1.comment(comment);
		approvalPage1.reject();
	}

	protected void goToCreateExpressProcess() {
		processWidget = homePage.getProcessWidget();
		processWidget.expand();
		processWidget.openExpressPage();
	}
}
