package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.Condition.text;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class DashboardEditCaseWidgetTest extends BaseTest {
  private static final String NAME_STR = "Name";
  private static final String YOUR_CASES_WIDGET = "Your Cases";

  private static final String ALPHA_COMPANY = "Alpha Company";
  private static final String ORDER_PIZZA = " Order Pizza";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void toggleFilterDialogTest() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage caseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.openFilter();
    caseWidget.closeFilter();
  }

  @Test
  public void filterNameTest() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToRelativeLink("internalSupport/14B2FC03D2E87141/TestCaseListPermissions.ivp");

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage caseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.openFilter();
    caseWidget.addFilter(NAME_STR, FilterOperator.NOT_EMPTY);
    caseWidget.addFilter(NAME_STR, FilterOperator.IS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Leave Request");
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(1), DEFAULT_TIMEOUT);

    caseWidget.openFilter();
    caseWidget.resetFilter();

    caseWidget.addFilter(NAME_STR, FilterOperator.CONTAINS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "List", "Pi");
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(2), DEFAULT_TIMEOUT);

    caseWidget.openFilter();
    caseWidget.addFilter(NAME_STR, FilterOperator.EMPTY);
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(0));

    caseWidget.openFilter();
    caseWidget.removeFilter(0);
    caseWidget.removeFilter(0);
    caseWidget.addFilter(NAME_STR, FilterOperator.START_WITH);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Or", "Te");
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(2), DEFAULT_TIMEOUT);

    caseWidget.openFilter();
    caseWidget.addFilter(NAME_STR, FilterOperator.NOT_START_WITH);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Or", "Te");
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(0), DEFAULT_TIMEOUT);

    caseWidget.openFilter();
    caseWidget.addFilter(NAME_STR, FilterOperator.END_WITH);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Or", "Te");
    caseWidget.closeFilter();
    caseWidget.countCases().shouldBe(CollectionCondition.size(0),
        DEFAULT_TIMEOUT);
  }

  @Test
  public void filterCreatorTest() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage caseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(2));
    caseWidget.openFilter();
    caseWidget.addFilter("Creator", FilterOperator.CURRENT_USER);
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(1));

    caseWidget.openFilter();
    caseWidget.removeFilter(0);
    caseWidget.addFilter("Creator", FilterOperator.IN);
    caseWidget.inputValueOnLatestFilter(FilterValueType.CREATOR_TYPE, "admin");
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(1));

    caseWidget.openFilter();
    caseWidget.addFilter("Creator", FilterOperator.NOT_IN);
    caseWidget.inputValueOnLatestFilter(FilterValueType.CREATOR_TYPE, "admin");
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(0));
  }

  @Test
  public void filterCategoryTest() {
    login(TestAccount.ADMIN_USER);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage caseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.openFilter();
    caseWidget.addFilter("Category", null);
    caseWidget.inputValueOnLatestFilter(FilterValueType.CATEGORY_TYPE, "Leave Request");
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(1));

    caseWidget.openFilter();
    caseWidget.removeFilter(0);
    caseWidget.addFilter("Category", FilterOperator.NO_CATEGORY);
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(0));

    caseWidget.openFilter();
    caseWidget.removeFilter(0);
    caseWidget.addFilter("Category", FilterOperator.NOT_CONTAINS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Leave");
    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(0));
  }


  @Test
  public void filterMixFieldTest() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createDataCreatedDate);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToRelativeLink(testCaseListPermission);
    redirectToRelativeLink(testCaseListPermission);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    ScreenshotUtils.resizeBrowser(new Dimension(1900, 1400));
    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage caseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.addCustomColumns("AccountNumber", "ShipmentDate", "CustomerName", "CustomerType", "InvoiceNumber");

    caseWidget.openFilter();
    caseWidget.resetFilter();
    caseWidget.addFilter("Name", FilterOperator.CONTAINS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Permission");

    caseWidget.addFilter("State", null);
    caseWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "OPEN");

    caseWidget.addFilter("Creator", FilterOperator.IN);
    caseWidget.inputValueOnLatestFilter(FilterValueType.CREATOR_TYPE, "admin");

    caseWidget.addFilter("Created Date", FilterOperator.TODAY);

    caseWidget.addFilter("Account Number", FilterOperator.BETWEEN);
    caseWidget.inputValueOnLatestFilter(FilterValueType.NUMBER_BETWEEN, 5, 700);

    caseWidget.addFilter("Shipment Date", FilterOperator.AFTER);
    caseWidget.inputValueOnLatestFilter(FilterValueType.DATE, "11/11/2017");

    caseWidget.applyFilter();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.countCases().shouldBe(CollectionCondition.size(2));
  }

  private NewDashboardDetailsEditPage gotoEditPublicDashboardPage() {
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    return modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
  }

  @Test
  public void testDefaultSortOnCaseWidget() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToRelativeLink(createAlphaCompanyUrl);

    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    
    CaseWidgetNewDashBoardPage caseWidget = new CaseWidgetNewDashBoardPage(YOUR_CASES_WIDGET);
    CaseEditWidgetNewDashBoardPage caseEditWidgetPage = caseWidget.openEditWidget();

    caseEditWidgetPage.clickOnCaseNameColumn();
    caseEditWidgetPage.getFirstCaseOfCaseWidget().shouldHave(text(ALPHA_COMPANY), DEFAULT_TIMEOUT);

    caseEditWidgetPage.clickOnCaseNameColumn();
    caseEditWidgetPage.getFirstCaseOfCaseWidget().shouldHave(text(ORDER_PIZZA), DEFAULT_TIMEOUT);
    
  }
}
