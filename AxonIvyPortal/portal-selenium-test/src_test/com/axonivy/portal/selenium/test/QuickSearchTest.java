package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.util.ConfigurationJsonUtils;

@IvyWebTest(headless = false)
public class QuickSearchTest extends BaseTest {

  private static final String YOUR_PROCESSES = "Your Processes";
	private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String YOUR_CASES_WIDGET = "Your Cases";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testVisibilityOfQuickSearchOnTaskWidget() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    assertTrue(taskWidget.isQuickSearchInputShow());
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.save();
    assertFalse(taskWidget.isQuickSearchInputShow());
  }

  @Test
  public void testTaskQuickSearchStandardFields() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openColumnManagementDialog();
    
    assertTrue(taskEditWidget.isQuickSearchClicked("name"));
    assertTrue(taskEditWidget.isQuickSearchClicked("description"));

    taskEditWidget.addFirstStandardField();
    taskEditWidget.clickOnQuickSearchByField("id");
    taskEditWidget.clickOnQuickSearchByField("activator");
    taskEditWidget.clickOnQuickSearchByField("category");
    taskEditWidget.clickOnQuickSearchByField("application");

    taskEditWidget.saveColumn();
    taskEditWidget.save();

    taskWidget.waitPageLoaded();
    redirectToNewDashBoard();
    taskWidget.setInputForQuickSearch("TestCase1");
    taskWidget.countAllTasks().shouldHave(size(3), DEFAULT_TIMEOUT);
    taskWidget.clickOnButtonExpandTaskWidget();
    taskWidget.countAllTasks().shouldHave(size(3), DEFAULT_TIMEOUT);
    taskWidget.clickOnButtonCollapseTaskWidget();
    taskWidget.countAllTasks().shouldHave(size(3), DEFAULT_TIMEOUT);
  }

  @Test
  public void testTaskQuickSearchKeywordSessionCache() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.setInputForQuickSearch("Task number 10");
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);
    
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);
  }

  @Test
  public void testTaskQuickSearchCustomCaseFields() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openColumnManagementDialog();

    List<String> customCaseFields = List.of("CustomerName", "CustomerType", "SupportData");

    customCaseFields.stream()
        .forEach(item -> taskEditWidget.addCustomFieldByCustomTypeAndFieldName("Custom case field", item));
    customCaseFields.stream().forEach(item -> taskEditWidget.clickOnQuickSearchByField(item));

    taskEditWidget.saveColumn();
    taskEditWidget.save();

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.setInputForQuickSearch("leave request");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1), DEFAULT_TIMEOUT);
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("supportdata");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(0), DEFAULT_TIMEOUT);
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("support data");
    assertTrue(taskWidget.isEmptyMessageAppear());
  }

  @Test
  public void testTaskQuickSearchCustomFields() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openColumnManagementDialog();

    List<String> customFields = List.of("CustomerName", "CustomerType", "CustomerAddress", "CustomerEmail");

    customFields.stream().forEach(item -> taskEditWidget.addCustomFieldByCustomTypeAndFieldName("Custom field", item));
    customFields.stream().forEach(item -> taskEditWidget.clickOnQuickSearchByField(item));

    taskEditWidget.saveColumn();
    taskEditWidget.save();
    ScreenshotUtils.maximizeBrowser();

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.setInputForQuickSearch("interior");
    taskWidget.countAllTasks().shouldHave(size(2), DEFAULT_TIMEOUT);
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("test@email.com");
    assertTrue(taskWidget.isEmptyMessageAppear());
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("ho chi minh city");
    assertTrue(taskWidget.isEmptyMessageAppear());
    resizeBrowserTo2kResolution();
    taskWidget.clickOnButtonExpandTaskWidget();
    assertTrue(taskWidget.isEmptyMessageAppear());
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("tung le");
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);
    redirectToNewDashBoard();
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);
  }
  
  @Test
  public void testTaskQuickSearchCustomFieldsWithCms() throws IOException {
    ConfigurationJsonUtils.updateJSONSetting("dashboard-task-widget-has-quicksearch.json", Variable.DASHBOARD);
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestDataForCustomFieldsWithCMS);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    setLanguageOfCurrentUserToGerman();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForTaskWidgetLoaded();

    taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.setInputForQuickSearch("Spanien");
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);
    resetLanguageOfCurrentUser();
  }

//   Case
  @Test
  public void testVisibilityOfQuickSearchOnCaseWidget() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();

    caseEditWidget.clickOnQuickSearchCheckBox();
    caseEditWidget.save();
    assertFalse(caseWidget.isQuickSearchInputShow("0"));
  }

  @Test
  public void testCaseQuickSearchStandardFields() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.clickOnQuickSearchCheckBox();
    caseEditWidget.openColumnManagementDialog();
    assertTrue(caseEditWidget.isQuickSearchClicked("name"));
    assertTrue(caseEditWidget.isQuickSearchClicked("description"));

    caseEditWidget.addFirstStandardField();
    caseEditWidget.clickOnQuickSearchByField("id");
    caseEditWidget.clickOnQuickSearchByField("creator");
    caseEditWidget.clickOnQuickSearchByField("category");
    caseEditWidget.clickOnQuickSearchByField("application");

    caseEditWidget.saveColumn();
    caseEditWidget.save();

    caseWidget.waitPageLoaded();
    redirectToNewDashBoard();

    caseWidget.setInputForQuickSearch("engine");
    assertTrue(caseWidget.isEmptyMessageAppear());
    caseWidget.clearQuickSearchInput();
    caseWidget.setInputForQuickSearch("TestCase");
    caseWidget.countAllCases().shouldHave(size(12), DEFAULT_TIMEOUT);
    caseWidget.clickOnButtonExpandCaseWidget();
    caseWidget.countAllCases().shouldHave(size(12), DEFAULT_TIMEOUT);
  }

  @Test
  public void testCaseQuickSearchKeywordSessionCache() throws IOException {
    ConfigurationJsonUtils.updateJSONSetting("dashboard-case-widget-has-quicksearch.json", Variable.DASHBOARD);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.setInputForQuickSearch("TestCase");

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    assertEquals("TestCase", caseWidget.getQuickSearchInput());
  }

  @Test
  public void testCaseQuickSearchCustomFields() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.clickOnQuickSearchCheckBox();
    caseEditWidget.openColumnManagementDialog();

    List<String> customFields = List.of("CustomerType", "SupportData");

    customFields.stream().forEach(item -> caseEditWidget.addCustomColumnByName(item));
    customFields.stream().forEach(item -> caseEditWidget.clickOnQuickSearchByField(item));

    caseEditWidget.saveColumn();
    caseEditWidget.save();

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    caseWidget = new CaseWidgetNewDashBoardPage();
    caseWidget.setInputForQuickSearch("Leave Request Type");
    caseWidget.countAllCases().shouldHave(size(1), DEFAULT_TIMEOUT);
    caseWidget.clearQuickSearchInput();

    caseWidget.setInputForQuickSearch("test@email.com");
    assertTrue(caseWidget.isEmptyMessageAppear());
    caseWidget.clearQuickSearchInput();

    caseWidget.setInputForQuickSearch("SupportData");
    caseWidget.countAllCases().shouldHave(size(1), DEFAULT_TIMEOUT);
    caseWidget.clearQuickSearchInput();
  }
  
  @Test
  public void testCaseQuickSearchCustomFieldsWithCms() throws IOException {
    ConfigurationJsonUtils.updateJSONSetting("dashboard-case-widget-has-quicksearch.json", Variable.DASHBOARD);
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestDataForCustomFieldsWithCMS);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    setLanguageOfCurrentUserToGerman();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    caseWidget = new CaseWidgetNewDashBoardPage();
    caseWidget.setInputForQuickSearch("Milchtee");
    caseWidget.countAllCases().shouldHave(size(1), DEFAULT_TIMEOUT);
    resetLanguageOfCurrentUser();
  }
  
  @Test
  public void testCopyAndPasteKeywordOnQuickSearch() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    taskWidget = new TaskWidgetNewDashBoardPage();
    refreshPage();

    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("Task number 10");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1), DEFAULT_TIMEOUT);
    
    taskWidget.copyAndPasteOnQuickSearchInput();
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1), DEFAULT_TIMEOUT);
    
    taskWidget.shiftAndArrowKeyOnQuickSearchInput();
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1), DEFAULT_TIMEOUT);
    
  }
  
  @Test
  public void testVisibilityOfQuickSearchInProcessWidget() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget(YOUR_PROCESSES);
    ProcessEditWidgetNewDashBoardPage processEditWidget = newDashboardPage.editProcessWidgetConfiguration();
    processEditWidget.enableQuickSearchCheckbox();
    processEditWidget.save();
    assertTrue(processWidget.isQuickSearchInputShow());
  }
  

  @Test
  public void testQuickSearchInProcessWidget() {
    login(TestAccount.ADMIN_USER);
    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget("Your Processes");
    assertTrue(processWidget.isQuickSearchInputShow());
    ScreenshotUtils.maximizeBrowser();
    processWidget.setQuickSearchKeyword("appraisal");
    assertEquals(1, processWidget.getNumberOfProcessListInWidget());
    processWidget.clearQuickSearchInput();
    processWidget.setQuickSearchKeyword("pizza");
    assertEquals(1, processWidget.getNumberOfProcessListInWidget());
  }
  
  @Test
  public void testSessionCacheInProcessWidget() {
    login(TestAccount.ADMIN_USER);
    ScreenshotUtils.maximizeBrowser();
    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget("Your Processes");
    processWidget.setQuickSearchKeyword("login");
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    assertEquals(3, processWidget.getNumberOfProcessListInWidget());
  }
  
  @Test
  public void testCopyAndPasteOnQuickSearchInputInProcessWidget() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget("Your Processes");
    ScreenshotUtils.maximizeBrowser();
    processWidget.setQuickSearchKeyword("login");
    assertEquals(3, processWidget.getNumberOfProcessListInWidget());
    
    processWidget.copyAndPasteOnQuickSearchInput();
    assertEquals(3, processWidget.getNumberOfProcessListInWidget());
    
    processWidget.shiftAndArrowKeyOnQuickSearchInput();
    assertEquals(3, processWidget.getNumberOfProcessListInWidget());
  }
}
