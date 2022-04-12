package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseDetailsWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class DashboardCaseWidgetTest extends BaseTest {

  // WIDGET
  private static final String YOUR_CASES_WIDGET = "Your Cases";
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  // CASES
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String HIDE_CASE = "Repair Computer";
  private static final String LEAVE_REQUEST_DEFAULT_CASE= "Leave Request for Default Additional Case Details";
  private static final String INVESTMENT_REQUEST_CUSTOMIZATION_CASE = "Investment Request";
  private static final String CREATE_12_CASES_WITH_CATEGORY_CASE = "Create 12 Cases with category";

  // TASKS
  private static final String REPORT_HIDE_CASE = "Report and hide case";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test()
  public void testHideCase() {
    redirectToRelativeLink(hideCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(REPORT_HIDE_CASE);
    taskWidget.applyFilter();
    taskWidget.startFirstTask();
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    caseWidget.countCases(HIDE_CASE).shouldHaveSize(0);
  }

  @Test
  public void testDestroyCaseWithPermission() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    CaseDetailsWidgetNewDashBoardPage detailsCase = caseWidget.openDetailsFirstCase();
    detailsCase.openActionPanel();
    detailsCase.destroy();
    refreshPage();
    redirectToNewDashBoard();
    caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.stateOfFirstCase().shouldHave(text("Destroyed"));
  }

  @Test
  public void testDestroyCaseWithoutPermission() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    CaseDetailsWidgetNewDashBoardPage detailsCase = caseWidget.openDetailsFirstCase();
    detailsCase.openActionPanel();
    detailsCase.destroyLink().shouldNotHave(visible);
  }

  @Test
  public void testOpenRelatedTasksOfCase() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    CaseDetailsWidgetNewDashBoardPage detailsCase = caseWidget.openDetailsCase(LEAVE_REQUEST_CASE_NAME);
    detailsCase.countRelatedTasks().shouldHaveSize(4);
  }

  @Test
  public void testOpenRelatedCasesOfCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    CaseDetailsWidgetNewDashBoardPage detailsCase = caseWidget.openDetailsCase(ORDER_PIZZA);
    detailsCase.countRelatedCases().shouldHaveSize(1);
  }
  
  @Test
  public void testOpenAdditionalCaseDetailsPage() {
    redirectToRelativeLink(createTestingCaseUrlForDefaultAdditionalCaseDetails);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    caseWidget.openFilterWidget();
    caseWidget.filterCaseName(LEAVE_REQUEST_DEFAULT_CASE);
    caseWidget.applyFilter();
    CaseDetailsWidgetNewDashBoardPage detailsCase = caseWidget.openDetailsFirstCase();
    detailsCase.openActionPanel();
    detailsCase.openAdditionalCaseDetailsPage();
    newDashboardPage.switchLastBrowserTab();
    detailsCase.countAdditionalFieldsPage().shouldHaveSize(15);
    detailsCase.firstAdditionalFieldsPage().shouldBe(text("CustomVarCharField 1"));
  }
  
  @Test
  public void testOpenCustomizationAdditionalCaseDetailsPage() {
    redirectToRelativeLink(createTestingCaseUrlForCustomizationAdditionalCaseDetails);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    caseWidget.openFilterWidget();
    caseWidget.filterCaseName(INVESTMENT_REQUEST_CUSTOMIZATION_CASE);
    caseWidget.applyFilter();
    CaseDetailsWidgetNewDashBoardPage detailsCase = caseWidget.openDetailsFirstCase();
    detailsCase.openActionPanel();
    detailsCase.openAdditionalCaseDetailsPage();
    newDashboardPage.switchLastBrowserTab();
    detailsCase.countAdditionalFieldsPage().shouldHaveSize(7);
    detailsCase.firstAdditionalFieldsPage().shouldBe(text("Apartment A"));
  }
  
  @Test
  public void testStickyFilterCaseList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));   
    //Filter Case Name
    caseWidget.openFilterWidget();
    caseWidget.filterCaseName("TestCase");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(4));
    //Filter State
    caseWidget.openFilterWidget();
    caseWidget.clearFilterCaseName();
    caseWidget.filterCaseName(CREATE_12_CASES_WITH_CATEGORY_CASE);
    caseWidget.filterCaseState();
    caseWidget.selectStateAsDone();
    caseWidget.applyFilter();
    caseWidget.stateOfFirstCase().shouldHave(text("Done"));
  }
  
  @Test
  public void testEditFilterCaseList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    configPage.navigateToEditDashboardDetailsByName("Dashboard");

    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.changeWidgetTitle("New Your Cases");
    caseEditWidget.filterCaseName("TestCase");
    caseEditWidget.filterCaseState();
    caseEditWidget.selectStateAsInProgress();
    caseEditWidget.preview();
    caseEditWidget.countCases().shouldHaveSize(5);
    caseEditWidget.nextPageTable();
    caseEditWidget.nextPageTable();
    caseEditWidget.countCases().shouldHaveSize(2);
    caseEditWidget.save();
    //After Edit
    CaseWidgetNewDashBoardPage caseWidgetEdited = newDashboardPage.selectCaseWidget("New Your Cases");
    caseWidgetEdited.expand().shouldHave(sizeGreaterThanOrEqual(1));
    caseWidgetEdited.countCases("TestCase").shouldHaveSize(5);
  }
  
  @Test
  public void testAddNewCaseList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();

    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = configPage.navigateToEditDashboardDetailsByName("Dashboard");

    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage newCaseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    newCaseWidget.changeWidgetTitle("New Your Cases");
    newCaseWidget.save();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget("New Your Cases");
    caseWidget.expand().shouldHaveSize(1);
  }
  
}
