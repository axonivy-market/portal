package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardCaseWidgetTest extends BaseTest {

  // WIDGET
  private static final String YOUR_CASES_WIDGET = "Your Cases";
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  // CASES
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String LEAVE_REQUEST_DEFAULT_CASE = "Leave Request for Default Additional Case Details";
  private static final String INVESTMENT_REQUEST_CUSTOMIZATION_CASE = "Create Investment";

  // TASKS
  private static final String REPORT_HIDE_CASE = "Report and hide case";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testHideCase() {
    redirectToRelativeLink(hideCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(REPORT_HIDE_CASE, FilterOperator.IS);
    taskWidget.applyFilter();
    taskWidget.startFirstTask();
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    newDashboardPage.getCaseWidgetEmptyMessageWhenNotFilter().shouldBe(Condition.appear);
  }

  @Test
  public void testDestroyCaseWithPermission() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.changeWidgetTitle("New Your Cases");
    caseEditWidget.openFilter();
    caseEditWidget.resetFilter();
    caseEditWidget.applyFilter();
    caseEditWidget.save();
    redirectToNewDashBoard();
    
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    CaseDetailsPage detailsCase = caseWidget.openDetailsFirstCase();
    detailsCase.openActionPanel();
    detailsCase.destroy();
    refreshPage();
    redirectToNewDashBoard();
    caseWidget.stateOfFirstCase().shouldHave(text("Destroyed"));
  }

  @Test
  public void testDestroyCaseWithoutPermission() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    CaseDetailsPage detailsCase = caseWidget.openDetailsFirstCase();
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
    CaseDetailsPage detailsCase = caseWidget.openDetailsCase(LEAVE_REQUEST_CASE_NAME);
    detailsCase.countRelatedTasks().shouldHave(size(4));
  }

  @Test
  public void testOpenRelatedCasesOfCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    CaseDetailsPage detailsCase = caseWidget.openDetailsCase(ORDER_PIZZA);
    assertEquals(1, detailsCase.countRelatedCases());
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
    CaseDetailsPage detailsCase = caseWidget.openDetailsFirstCase();
    detailsCase.openActionPanel();
    detailsCase.openAdditionalCaseDetailsPage();
    newDashboardPage.switchLastBrowserTab();
    detailsCase.countAdditionalFieldsPage().shouldHave(size(13));
    detailsCase.firstAdditionalFieldsPage().shouldBe(text("Customer name"));
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
    CaseDetailsPage detailsCase = caseWidget.openDetailsFirstCase();
    detailsCase.openActionPanel();
    detailsCase.openAdditionalCaseDetailsPage();
    newDashboardPage.switchLastBrowserTab();
    detailsCase.switchToIframe();
    detailsCase.countAdditionalFieldsPage().shouldHave(size(4));
    detailsCase.firstAdditionalFieldsPage().shouldBe(text("Apartment A"));
  }

  @Test
  public void testStickyFilterCaseList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    // Filter Case Name
    caseWidget.openFilterWidget();
    caseWidget.filterCaseName("TestCase");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(4));
    caseWidget.openFilterWidget();
    caseWidget.resetFilter();

    // Filter State Open
    caseWidget.stateOfFirstCase().shouldHave(text("Open"));
  }

  @Test
  public void testEditFilterCaseList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.changeWidgetTitle("New Your Cases");
    caseEditWidget.openFilter();
    caseEditWidget.resetFilter();
    caseEditWidget.filterCaseName("TestCase");
    caseEditWidget.applyFilter();
    caseEditWidget.countCases().shouldHave(size(12));
    caseEditWidget.save();
    // After Edit
    CaseWidgetNewDashBoardPage caseWidgetEdited = newDashboardPage.selectCaseWidget("New Your Cases");
    caseWidgetEdited.expand().shouldHave(sizeGreaterThanOrEqual(1), DEFAULT_TIMEOUT);
    caseWidgetEdited.countCases("TestCase").shouldHave(sizeGreaterThanOrEqual(1));
  }

  @Test
  public void testAddNewCaseList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    var configurationPage = LinkNavigator.navigateToPortalDashboardConfiguration();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage newCaseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    newCaseWidget.waitPreviewTableLoaded();
    newCaseWidget.changeWidgetTitle("New Your Cases");
    newCaseWidget.save();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget("New Your Cases");
    caseWidget.expand().shouldHave(size(1));
  }

  @Test
  public void testExportExcel() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    String caseWidgetName = "Export Excel Case";

    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage newCaseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    newCaseWidget.waitPreviewTableLoaded();
    newCaseWidget.openColumnManagementDialog();
    newCaseWidget.selectCustomType();
    newCaseWidget.addFirstCustomField();
    newCaseWidget.saveColumn();
    newCaseWidget.waitPreviewTableLoaded();
    newCaseWidget.changeWidgetTitle(caseWidgetName);
    newCaseWidget.save();
    redirectToNewDashBoard();

    NewDashboardPage dashboardPage = new NewDashboardPage();
    CaseWidgetNewDashBoardPage caseWidget = dashboardPage.selectCaseWidget(caseWidgetName);
    caseWidget.clickExportExcel();
    dashboardPage.isDownloadCompleted();
  }

  @Test
  public void testCustomActionButton() {
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();

    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));
    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.openFilter();
    caseEditWidget.resetFilter();
    caseEditWidget.applyFilter();
    caseEditWidget.openColumnManagementDialog();

    caseEditWidget.removeAddedField("id");

    caseEditWidget.selectCustomType();
    String customColumn = caseEditWidget.addCustomColumnByName("DestroyCaseAction");
    caseEditWidget.getCustomField(customColumn).shouldNotBe(Condition.exist);
    caseEditWidget.saveColumn();
    caseEditWidget.save();

    redirectToNewDashBoard();
    redirectToRelativeLink(createCustomActionCaseExampleUrl);

    caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    caseWidget.clickOnCustomActionButton();
    caseWidget.stateOfFirstCase().shouldHave(text("Destroyed"));

  }
  
  @Test
  public void testHideWidgetInfoIcon() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    NewDashboardPage dashboardPage = new NewDashboardPage();
    CaseWidgetNewDashBoardPage caseWidget = dashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.clickOnWidgetInfoIconCheckbox();
    caseEditWidget.save();
    newDashboardDetailsEditPage.backToConfigurationPage();
    redirectToNewDashBoard();
    assertFalse(caseWidget.isWidgetInfomationIconAppear());
  }

  @Test
  public void testHideExpandMode() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    NewDashboardPage dashboardPage = new NewDashboardPage();
    CaseWidgetNewDashBoardPage caseWidget = dashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.clickOnExpandModeCheckbox();
    caseEditWidget.save();
    newDashboardDetailsEditPage.backToConfigurationPage();
    redirectToNewDashBoard();
    assertFalse(caseWidget.isExpandButtonAppear());
  }
  
  @Test
  public void testResizeColumnWidth() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    int oldSize = modificationPage.getPriorityColumnSize();
    modificationPage.resizeColumn();
    int newSize = modificationPage.getPriorityColumnSize();
    assertTrue(newSize > oldSize);
    redirectToNewDashBoard();
  }
  
  @Test
  public void testCaseOwners() {
    redirectToRelativeLink(multipleOwnersUrl);
    login(TestAccount.ADMIN_USER);
    updatePortalSetting(Variable.ENABLE_CASE_OWNER.getKey(), "true");
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    CaseDetailsPage detailsCase = caseWidget.openDetailsCase("Case with multiple owners");
    detailsCase.clickShowCaseOwners();
    assertTrue(detailsCase.countCaseOwners() > 0);
    updatePortalSetting(Variable.ENABLE_CASE_OWNER.getKey(), "false");
  }
}
