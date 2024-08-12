package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class QuickSearchTest extends BaseTest {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String YOUR_CASES_WIDGET = "Your Cases";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createJSonFile("default-dashboard-quicksearch.json", PortalVariable.DASHBOARD.key);
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
    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("Task number 10");
    taskWidget.countAllTasks().shouldHave(sizeGreaterThanOrEqual(1), DEFAULT_TIMEOUT);
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.clickOnQuickSearchCheckBox();
    taskEditWidget.save();
    assertFalse(taskWidget.isQuickSearchInputShow("0"));
   }

  @Test
  public void testTaskQuickSearchStandardFields() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
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

    taskWidget.setInputForQuickSearch("engine");
    assertTrue(taskWidget.isEmptyMessageAppear());
    redirectToNewDashBoard();
    taskWidget.clearQuickSearchInput();
    taskWidget.setInputForQuickSearch("Task number 10");
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);
    taskWidget.clickOnButtonExpandTaskWidget();
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);
    taskWidget.clickOnButtonCollapseTaskWidget();
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);
  }

  @Test
  public void testTaskQuickSearchKeywordSessionCache() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.save();

    taskWidget.waitPageLoaded();
    refreshPage();
    taskWidget.setInputForQuickSearch("Task number 10");
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.setInputForQuickSearch("Task number 10");
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
    refreshPage();
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
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openColumnManagementDialog();

    List<String> customFields = List.of("CustomerName", "CustomerType", "CustomerAddress", "CustomerEmail");

    customFields.stream().forEach(item -> taskEditWidget.addCustomFieldByCustomTypeAndFieldName("Custom field", item));
    customFields.stream().forEach(item -> taskEditWidget.clickOnQuickSearchByField(item));

    taskEditWidget.saveColumn();
    taskEditWidget.save();

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    taskWidget = new TaskWidgetNewDashBoardPage();
    refreshPage();
    taskWidget.setInputForQuickSearch("interior");
    taskWidget.countAllTasks().shouldHave(size(2), DEFAULT_TIMEOUT);
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("test@email.com");
    assertTrue(taskWidget.isEmptyMessageAppear());
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("ho chi minh city");
    assertTrue(taskWidget.isEmptyMessageAppear());
    taskWidget.clickOnButtonExpandTaskWidget();
    assertTrue(taskWidget.isEmptyMessageAppear());
    taskWidget.clearQuickSearchInput();

    taskWidget.setInputForQuickSearch("tung le");
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);
    redirectToNewDashBoard();
    taskWidget.countAllTasks().shouldHave(size(1), DEFAULT_TIMEOUT);
  }

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
    caseWidget.isQuickSearchInputShow("0");

    refreshPage();
    caseWidget.setInputForQuickSearch("Create 12 cases with");
    caseWidget.countAllCases().shouldHave(sizeGreaterThanOrEqual(1), DEFAULT_TIMEOUT);
    caseWidget.clearQuickSearchInput();

    caseWidget.openEditWidget();
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
    refreshPage();

    caseWidget.setInputForQuickSearch("engine");
    assertTrue(caseWidget.isEmptyMessageAppear());
    redirectToNewDashBoard();
    caseWidget.clearQuickSearchInput();
    caseWidget.setInputForQuickSearch("TestCase1");
    caseWidget.countAllCases().shouldHave(size(3), DEFAULT_TIMEOUT);
    caseWidget.clickOnButtonExpandCaseWidget();
    caseWidget.countAllCases().shouldHave(size(3), DEFAULT_TIMEOUT);
    caseWidget.clickOnButtonCollapseCaseWidget();
    caseWidget.countAllCases().shouldHave(size(3), DEFAULT_TIMEOUT);
  }

  @Test
  public void testCaseQuickSearchKeywordSessionCache() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.clickOnQuickSearchCheckBox();
    caseEditWidget.save();

    caseWidget.waitPageLoaded();

    caseWidget.setInputForQuickSearch("Create 12 cases");
    caseWidget.countAllCases().shouldHave(size(1), DEFAULT_TIMEOUT);

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    caseWidget = new CaseWidgetNewDashBoardPage();
    assertEquals("Create 12 cases", caseWidget.getQuickSearchInput());
    caseWidget.countAllCases().shouldHave(size(1), DEFAULT_TIMEOUT);
  }

  @Test
  public void testCaseQuickSearchCustomFields() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
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
  public void testQuickSearchInProcessWidget() {
    login(TestAccount.ADMIN_USER);
    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget("Your Processes");
    assertTrue(processWidget.isQuickSearchInputShow());
    processWidget.setQuickSearchKeyword("appraisal");
    assertEquals(1, processWidget.getNumberOfProcessListInWidget());
    processWidget.clearQuickSearchInput();
    processWidget.setQuickSearchKeyword("pizza");
    assertEquals(1, processWidget.getNumberOfProcessListInWidget());
  }

  @Test
  public void testSessionCacheInProcessWidget() {
    login(TestAccount.ADMIN_USER);
    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget("Your Processes");
    assertTrue(processWidget.isQuickSearchInputShow());
    processWidget.setQuickSearchKeyword("login");
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    assertEquals(4, processWidget.getNumberOfProcessListInWidget());
  }

  @Test
  public void testCopyAndPasteOnQuickSearchInputInProcessWidget() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    ProcessWidgetNewDashBoardPage processWidget = newDashboardPage.selectProcessWidget("Your Processes");
    processWidget.setQuickSearchKeyword("login");
    assertEquals(4, processWidget.getNumberOfProcessListInWidget());
    processWidget.copyAndPasteOnQuickSearchInput();
    processWidget.shiftAndArrowKeyOnQuickSearchInput();
    assertEquals(4, processWidget.getNumberOfProcessListInWidget());
  }
}
