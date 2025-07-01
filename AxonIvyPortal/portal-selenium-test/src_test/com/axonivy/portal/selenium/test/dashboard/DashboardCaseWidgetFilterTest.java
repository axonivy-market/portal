package com.axonivy.portal.selenium.test.dashboard;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

@IvyWebTest
public class DashboardCaseWidgetFilterTest extends BaseTest {

  private NewDashboardPage newDashboardPage;

  // WIDGET NAME
  private static final String YOUR_CASES_WIDGET = "Your Cases";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
    redirectToRelativeLink(create12CasesWithCategoryUrl);
  }

  @Test
  public void testEditFilterOperator() {
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    // Filter Case Name IS_NOT
    caseWidget.openFilterWidget();
    caseWidget.addFilter("State", null);
    caseWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "OPEN");

    caseWidget.addFilter("Name", FilterOperator.IS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "TestCase");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));

    // CONTAINS
    caseWidget.openFilterWidget();
    caseWidget.changeOperator("Name", FilterOperator.CONTAINS, "text");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));

    // START_WITH
    caseWidget.openFilterWidget();
    caseWidget.changeOperator("Name", FilterOperator.START_WITH, "text");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));

    // END_WITH
    caseWidget.openFilterWidget();
    caseWidget.changeOperator("Name", FilterOperator.END_WITH, "text");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));
  }

  @Test
  public void testComplexFilterOptionsForNormalUser() {
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    // Add several options
    caseWidget.openFilterWidget();
    caseWidget.addFilter("Created", FilterOperator.TODAY);
    caseWidget.addFilter("Name", FilterOperator.CONTAINS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "TestCase");
    caseWidget.addFilter("Description", FilterOperator.EMPTY);
    caseWidget.applyFilter();

    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));
  }

  @Test
  public void testSaveFilter() {
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    caseWidget.openFilterWidget();
    removeSavedFilterItemsIfExist(caseWidget);

    // Create and save filter
    caseWidget.openFilterWidget();
    caseWidget.addFilter("Created", FilterOperator.YESTERDAY);
    caseWidget.addFilter("State", null);
    caseWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "OPEN");
    caseWidget.saveFilter("CasesOpenYesterday");

    ElementsCollection actions = caseWidget.getSavedFilterItems();
    actions.shouldHave(CollectionCondition.size(1));
    actions.filter(Condition.text("CasesOpenYesterday"));
  }

  @Test
  public void testSearchSavedFilterItems() {
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    // Create filter items
    caseWidget.openFilterWidget();
    createSavedFilterItems(caseWidget);

    // Search function
    caseWidget.openFilterWidget();
    caseWidget.searchFilter("Leave");
    ElementsCollection actions = caseWidget.getSavedFilterItems();
    actions.shouldHave(CollectionCondition.size(1));

    caseWidget.searchFilter("Name");
    actions.shouldHave(CollectionCondition.size(2));

    caseWidget.searchFilter("Today");
    actions.shouldHave(CollectionCondition.size(1));

    // Apply selected filter items
    caseWidget.selectSavedFilter("CasesOpenToday");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));
    caseWidget.countCases("Leave Request").shouldHave(CollectionCondition.size(0));
  }

  @Test
  public void testSearchOnManageFilterDialog() {
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    // Create filter items
    caseWidget.openFilterWidget();
    createSavedFilterItems(caseWidget);
    caseWidget.openFilterWidget();
    caseWidget.openManageFiltersDialog();
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));

    // Search on Name, Widget name and Type
    caseWidget.inputValueOnColumnWidgetHeader("Name", "CasesNameLeaveRequest");
    caseWidget.getSavedFilterItemsByFilterNameOnWidgetManagement().filter(Condition.text("CasesNameLeaveRequest"))
        .shouldHave(CollectionCondition.size(1));

    caseWidget.inputValueOnColumnWidgetHeader("Widget name", "cases");
    caseWidget.getSavedFilterItemsByFilterNameOnWidgetManagement().shouldHave(CollectionCondition.size(3));

    caseWidget.removeAllFilterItems();
  }

  @Test
  public void testFilterNotiNumber() {
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    // Add 2 filter items
    caseWidget.openFilterWidget();
    caseWidget.addFilter("Created", FilterOperator.TODAY);
    caseWidget.addFilter("Description", FilterOperator.EMPTY);
    caseWidget.applyFilter();

    assertTrue(caseWidget.getFilterNotiNumber().equals(2));
  }

  @Test
  public void testFilterCaseState() {
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    caseWidget.destroyCase(0);
    caseWidget.waitTableLoaded();
    caseWidget.destroyCase(1);
    caseWidget.waitTableLoaded();
    caseWidget.openFilterWidget();
    caseWidget.addFilter("Name", null);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "TestCase");
    caseWidget.applyFilter();

    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(10));
  }

  @Test
  public void testFilterDateWithStandardFields() {
    redirectToRelativeLink(testCaseListPermission);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    caseWidget.openFilterWidget();
    caseWidget.addFilter("Created", FilterOperator.TODAY);
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));
    caseWidget.countCases("Test Case List Permission").shouldHave(CollectionCondition.size(1));

    caseWidget.openFilterWidget();
    caseWidget.changeOperator("Created", FilterOperator.AFTER, "date");
    caseWidget.inputValueOnLatestFilter(FilterValueType.DATE, "01/01/2024");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));
    caseWidget.countCases("Test Case List Permission").shouldHave(CollectionCondition.size(1));

    caseWidget.openFilterWidget();
    caseWidget.changeOperator("Created", FilterOperator.CURRENT, "date");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));
    caseWidget.countCases("Test Case List Permission").shouldHave(CollectionCondition.size(1));

  }

  @Test
  public void testFilterAddComplexCustomFields() {
    redirectToRelativeLink(testCaseListPermission);
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    addCustomFields(caseWidget, List.of("AccountNumber", "CustomerName", "ShipmentDate", "SupportData"));

    caseWidget.openFilterWidget();

    caseWidget.addFilter("Account Number", FilterOperator.NOT_EMPTY);
    caseWidget.addFilter("Customer name", FilterOperator.NOT_EMPTY);
    caseWidget.addFilter("Shipment date", FilterOperator.BEFORE);
    caseWidget.inputValueOnLatestFilter(FilterValueType.DATE, "01/01/2024");

    caseWidget.applyFilter();

    caseWidget.countCases("Test Case List Permission").shouldHave(CollectionCondition.size(1));
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(0));
  }

  @Test
  public void testSavedFilterItemsWithCustomFields() {
    redirectToRelativeLink(testCaseListPermission);
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    addCustomFields(caseWidget, List.of("AccountNumber", "CustomerName"));

    caseWidget.openFilterWidget();
    caseWidget.addFilter("Account Number", FilterOperator.NOT_EMPTY);
    caseWidget.saveFilter("CasesAccountNumberNotEmpty");
    caseWidget.resetFilter();

    caseWidget.openFilterWidget();
    caseWidget.addFilter("Customer name", FilterOperator.CONTAINS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "CustomerName");
    caseWidget.saveFilter("CasesContainCustomerName");
    caseWidget.resetFilter();

    caseWidget.openFilterWidget();
    caseWidget.selectSavedFilter("CasesContainCustomerName");
    caseWidget.selectSavedFilter("CasesAccountNumberNotEmpty");
    caseWidget.applyFilter();

    caseWidget.countCases("Test Case List Permission").shouldHave(CollectionCondition.size(1));
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(0));

    caseWidget.openFilterWidget();
    removeSavedFilterItemsIfExist(caseWidget);
  }

  @Test
  public void testFilterNumberOnCustomFields() {
    redirectToRelativeLink(testCaseListPermission);
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    addCustomFields(caseWidget, List.of("AccountNumber", "InvoiceSubtotalAmount"));

    caseWidget.openFilterWidget();
    caseWidget.addFilter("Account Number", FilterOperator.NOT_EMPTY);
    caseWidget.addFilter("Invoice Subtotal Amount", FilterOperator.BETWEEN);
    caseWidget.inputValueOnLatestFilter(FilterValueType.NUMBER_BETWEEN, 100, 500);
    caseWidget.applyFilter();
    caseWidget.countCases("Test Case List Permission").shouldHave(CollectionCondition.size(1));
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(0));
  }

  @Test
  public void testFilterDateOnCustomFields() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(testCaseListPermission);
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    addCustomFields(caseWidget, List.of("CreatedBillDate", "ShipmentDate"));

    caseWidget.openFilterWidget();
    caseWidget.addFilter("Created Bill date", FilterOperator.IS_NOT);
    caseWidget.inputValueOnLatestFilter(FilterValueType.DATE, "01/01/2024");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(0));
    caseWidget.countCases("Test Case List Permission").shouldHave(CollectionCondition.size(1));

    caseWidget.openFilterWidget();
    caseWidget.changeOperator("Created Bill date", FilterOperator.BETWEEN, "date");
    caseWidget.inputValueOnLatestFilter(FilterValueType.DATE_BETWEEN, "01/01/2018", "01/01/2024");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(0));
    caseWidget.countCases("Test Case List Permission").shouldHave(CollectionCondition.size(1));

    caseWidget.openFilterWidget();
    caseWidget.changeOperator("Created Bill date", FilterOperator.WITHIN_LAST, "date");
    caseWidget.inputValueOnLatestFilter(FilterValueType.WITHIN, "10", "Year(s)");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(0));
    caseWidget.countCases("Test Case List Permission").shouldHave(CollectionCondition.size(1));
  }

  private void createSavedFilterItems(CaseWidgetNewDashBoardPage caseWidget) {
    removeSavedFilterItemsIfExist(caseWidget);

    caseWidget.openFilterWidget();
    caseWidget.addFilter("Created", FilterOperator.TODAY);
    caseWidget.addFilter("State", null);
    caseWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "OPEN");
    caseWidget.saveFilter("CasesOpenToday");
    caseWidget.resetFilter();

    caseWidget.openFilterWidget();
    caseWidget.addFilter("Name", FilterOperator.CONTAINS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Leave Request");
    caseWidget.saveFilter("CasesNameLeaveRequest");
    caseWidget.resetFilter();

    caseWidget.openFilterWidget();
    caseWidget.addFilter("Name", FilterOperator.EMPTY);
    caseWidget.addFilter("Description", FilterOperator.EMPTY);
    caseWidget.saveFilter("CasesNameAndDescriptionEmpty");
    caseWidget.resetFilter();
  }

  private void removeSavedFilterItemsIfExist(CaseWidgetNewDashBoardPage caseWidget) {
    if (caseWidget.getSavedFilterItems().size() > 0) {
      caseWidget.openManageFiltersDialog();
      caseWidget.removeAllFilterItems();
      caseWidget.closeManageFilterDialog();
    } else
      caseWidget.resetFilter();
  }

  private void addCustomFields(CaseWidgetNewDashBoardPage caseWidget, List<String> fieldNameList) {
    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.openColumnManagementDialog();
    caseEditWidget.selectCustomType();
    for (String fieldName : fieldNameList) {
      caseEditWidget.addCustomField(fieldName);
    }
    caseEditWidget.saveAfterAddingCustomField();
    redirectToNewDashBoard();
  }
}
