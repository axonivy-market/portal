package com.axonivy.portal.selenium.test.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

@IvyWebTest
public class DashboardCasefWidgetFilterTest extends BaseTest {

  private NewDashboardPage newDashboardPage;

  // WIDGET NAME
  private static final String YOUR_CASES_WIDGET = "Your Cases";
  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";

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
    caseWidget.addFilter("Name", FilterOperator.IS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "TestCase");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));

    // CONTAINS
    caseWidget.openFilterWidget();
    caseWidget.changeOperator(FilterOperator.CONTAINS, "text");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));

    // START_WITH
    caseWidget.openFilterWidget();
    caseWidget.changeOperator(FilterOperator.START_WITH, "text");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));

    // END_WITH
    caseWidget.openFilterWidget();
    caseWidget.changeOperator(FilterOperator.END_WITH, "text");
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
    caseWidget.addFilter("Created Date", FilterOperator.TODAY);
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
    caseWidget.addFilter("Created Date", FilterOperator.YESTERDAY);
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
        .shouldHave(CollectionCondition.size(0));

    caseWidget.inputValueOnColumnWidgetHeader("Widget name", "cases");
    caseWidget.getSavedFilterItemsByFilterNameOnWidgetManagement().shouldHave(CollectionCondition.size(3));

    caseWidget.inputValueOnColumnWidgetHeader("Type", "case");
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
    caseWidget.addFilter("Created Date", FilterOperator.TODAY);
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
    caseWidget.destroyCase(1);

    caseWidget.openFilterWidget();
    caseWidget.addFilter("State", null);
    caseWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "DESTROYED");
    caseWidget.addFilter("Name", null);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "TestCase");
    caseWidget.applyFilter();

    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(2));
  }

  @Test
  public void testFilterDateOnStandardFields() {
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);

    caseWidget.openFilterWidget();
    caseWidget.addFilter("Created Date", FilterOperator.TODAY);
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));

    caseWidget.openFilterWidget();
    caseWidget.changeOperator(FilterOperator.AFTER, "date");
    caseWidget.inputValueOnLatestFilter(FilterValueType.DATE, "01/01/2024");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));

    caseWidget.openFilterWidget();
    caseWidget.changeOperator(FilterOperator.CURRENT, "date");
    caseWidget.applyFilter();
    caseWidget.countCases("TestCase").shouldHave(CollectionCondition.size(12));
  }

  private void createSavedFilterItems(CaseWidgetNewDashBoardPage caseWidget) {
    removeSavedFilterItemsIfExist(caseWidget);

    caseWidget.openFilterWidget();
    caseWidget.addFilter("Created Date", FilterOperator.TODAY);
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
}
