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
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

@IvyWebTest
public class DashboardTaskWidgetFilterTest extends BaseTest {
  // WIDGET NAME
  private static final String YOUR_TASK_WIDGET = "Your Tasks";
  
  private NewDashboardPage newDashboardPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
    redirectToRelativeLink(create12CasesWithCategoryUrl);
  }
  
  @Test
  public void testComplexFilterOptionsOnNormalUser() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.maximizeBrowser();

    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Annual Leave Request");
    taskWidget.addFilter("Description", FilterOperator.NOT_EMPTY);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1));
    
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Created Date", FilterOperator.TODAY);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1));
  }
  
  @Test
  public void testSaveFilter() {
    redirectToNewDashBoard();
    login(TestAccount.DEMO_USER);
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.maximizeBrowser();
    
    // Create and save filter
    taskWidget.openFilterWidget();
    removeSavedFilterItemsIfExist(taskWidget);
    
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Created Date", FilterOperator.YESTERDAY);
    taskWidget.saveFilter("TaskOpenYesterday");
    
    ElementsCollection actions = taskWidget.getSavedFilterItems();
    actions.shouldHave(CollectionCondition.size(1));
    actions.filter(Condition.text("TaskOpenYesterday"));
  }
  
  @Test
  public void testSearchSavedFilterItems() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.maximizeBrowser();

    // Create filter items
    taskWidget.openFilterWidget();
    createSavedFilterItems(taskWidget);
    
    // Search function
    taskWidget.openFilterWidget();
    taskWidget.searchWidgetFilter("Leave");
    ElementsCollection actions = taskWidget.getSavedFilterItems();
    actions.shouldHave(CollectionCondition.size(1));

    taskWidget.searchSavedFilters("Name");
    actions.shouldHave(CollectionCondition.size(2));

    // Apply selected filter items
    taskWidget.selectSavedFilter("TaskNameLeaveRequest");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(3));
    taskWidget.countTasks("LeaveRequest").shouldHave(CollectionCondition.size(0));
  }
  
  @Test
  public void testSearchOnManagerFilterDialog() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.maximizeBrowser();

    // Create filter items
    taskWidget.openFilterWidget();
    createSavedFilterItems(taskWidget);
    taskWidget.openFilterWidget();
    taskWidget.openManageFiltersDialog();
    taskWidget.getSavedFilterItemsByFilterNameOnWidgetManagement().shouldHave(CollectionCondition.size(2));
    
    taskWidget.inputValueOnColumnWidgetHeader("Name", "TaskNameLeaveRequest");
    taskWidget.getSavedFilterItemsByFilterNameOnWidgetManagement().shouldHave(CollectionCondition.size(1));
  }
  
  @Test
  public void testFilterNotiNumber() {
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    
    // Add 2 filter items
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Created Date", FilterOperator.TODAY);
    taskWidget.addFilter("Description", FilterOperator.EMPTY);
    taskWidget.applyFilter();
    
    assertTrue(taskWidget.getFilterNotiNumber().equals(2));
  }
  
  @Test
  public void testComplexFilterOnStandardFields() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.maximizeBrowser();
    
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "leave");
    taskWidget.addFilter("Description", FilterOperator.NOT_EMPTY);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(3));
  }
  
  @Test
  public void testFilterDateOnStandardFields() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.maximizeBrowser();
    
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Created Date", FilterOperator.AFTER);
    taskWidget.inputValueOnLatestFilter(FilterValueType.DATE, "01/01/2024");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(14));
    
    taskWidget.openFilterWidget();
    taskWidget.resetFilter();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Expiry", FilterOperator.WITHIN_NEXT);
    taskWidget.inputValueOnLatestFilter(FilterValueType.WITHIN, "2","Year(s)");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(2));
  }
  
  @Test
  public void testFilterDateOnCustomFields() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.resizeBrowser(new Dimension(1980, 1080));
    addCustomFields(taskWidget, List.of("ShipmentDate","AccountNumber"));

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForTaskListDisplay();
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Shipment date", FilterOperator.BETWEEN);
    taskWidget.inputValueOnLatestFilter(FilterValueType.DATE_BETWEEN, "01/01/2024", "12/12/2124");
    taskWidget.clickOnWidgetFilterHeader();
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(2));
  }
  
  @Test
  public void testFilterNumberOnCustomFields() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.resizeBrowser(new Dimension(1980, 1080));
    addCustomFields(taskWidget, List.of("ShipmentDate","AccountNumber"));
    redirectToNewDashBoard();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("AccountNumber", FilterOperator.BETWEEN);
    taskWidget.inputValueOnLatestFilter(FilterValueType.NUMBER_BETWEEN, "1","20");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(2));
  }
  
  @Test
  public void testFilterTaskState() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.maximizeBrowser();
    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openFilter();
    taskEditWidget.resetFilter();
    taskEditWidget.applyFilter();
    taskEditWidget.save();
    redirectToNewDashBoard();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("State", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "OPEN");
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(14));
  }
  
  @Test
  public void testFilterDateOnCustomCaseFields() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.maximizeBrowser();
    addCustomCaseFields(taskWidget, List.of("InvoiceDate","CreatedBillDate"));

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForTaskListDisplay();
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Created Bill date", FilterOperator.BEFORE);
    taskWidget.inputValueOnLatestFilter(FilterValueType.DATE, "01/01/2024");
    taskWidget.applyFilter();
    assertTrue(taskWidget.isEmptyMessageAppear());
    
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Invoice date", FilterOperator.TODAY);
    taskWidget.applyFilter();
    assertTrue(taskWidget.isEmptyMessageAppear());
  }
  
  @Test
  public void testFilterNumberOnCustomCaseFields() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASK_WIDGET);
    ScreenshotUtils.maximizeBrowser();
    addCustomCaseFields(taskWidget, List.of("InvoiceNumber","InvoiceSubTotalAmount"));
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForTaskListDisplay();

    taskWidget.openFilterWidget();
    taskWidget.addFilter("Invoice Subtotal Amount", FilterOperator.EQUAL);
    taskWidget.inputValueOnLatestFilter(FilterValueType.NUMBER, "1000");
    taskWidget.applyFilter();
    assertTrue(taskWidget.isEmptyMessageAppear());
    
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Invoice Subtotal Amount", FilterOperator.NOT_EMPTY);
    taskWidget.applyFilter();
    assertTrue(taskWidget.isEmptyMessageAppear());
  }
  
  private void createSavedFilterItems(TaskWidgetNewDashBoardPage taskWidget) {
    removeSavedFilterItemsIfExist(taskWidget);
    
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Leave Request");
    taskWidget.saveFilter("TaskNameLeaveRequest");
    taskWidget.resetFilter();
    
    taskWidget.openFilterWidget();  
    taskWidget.addFilter("Name", FilterOperator.EMPTY);
    taskWidget.addFilter("Description", FilterOperator.EMPTY);
    taskWidget.saveFilter("TaskNameAndDescriptionEmpty");
    taskWidget.resetFilter();
  }
  
  
  private void removeSavedFilterItemsIfExist(TaskWidgetNewDashBoardPage taskWidget) {
    if(taskWidget.getSavedFilterItems().size() > 0) {
      taskWidget.openManageFiltersDialog();
      taskWidget.removeAllFilterItems();
      taskWidget.closeManageFilterDialog();
    } else {
      taskWidget.resetFilter();
    }
  }
  
  private void addCustomFields(TaskWidgetNewDashBoardPage taskWidget, List<String> fieldNameList) {
    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openColumnManagementDialog();
    taskEditWidget.selectCustomType();
    for(String fieldName : fieldNameList) {
      taskEditWidget.addCustomFields(fieldName);
    }
    taskEditWidget.saveAfterAddingCustomField();
  }
  
  private void addCustomCaseFields(TaskWidgetNewDashBoardPage taskWidget, List<String> fieldNameList) {
    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidget.openEditTaskWidget();
    taskEditWidget.openColumnManagementDialog();
    taskEditWidget.selectCustomCaseType();
    for(String fieldName : fieldNameList) {
      taskEditWidget.addCustomCaseFields(fieldName);
    }
    taskEditWidget.saveAfterAddingCustomField();
  }
  
}
