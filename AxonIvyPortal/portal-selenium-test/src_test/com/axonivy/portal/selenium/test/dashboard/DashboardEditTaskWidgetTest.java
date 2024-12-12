package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.Condition.text;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class DashboardEditTaskWidgetTest extends BaseTest {
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String MATERNITY_LEAVE_REQUEST = "Maternity Leave Request";
  private static final String SICK_LEAVE_REQUEST = "Sick Leave Request";

  private NewDashboardDetailsEditPage newDashboardDetailsEditPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingTasksUrl);
  }
  
  @Test
  public void testFilterName() {
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    newDashboardDetailsEditPage = modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    
    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage taskWidget = newDashboardDetailsEditPage.addNewTaskWidget();
    taskWidget.waitPreviewTableLoaded();
    taskWidget.openFilter();
    taskWidget.addFilter("Name",FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "leave request");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(4));
    taskWidget.waitPreviewTableLoaded();

    taskWidget.openFilter();
    taskWidget.resetFilter();
    taskWidget.addFilter("Name",FilterOperator.NOT_EMPTY);
    taskWidget.applyFilter();
    taskWidget.waitPreviewTableLoaded();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(4));

    taskWidget.openFilter();
    taskWidget.resetFilter();
    taskWidget.addFilter("Name",FilterOperator.END_WITH);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "request");
    
    taskWidget.addFilter("Name",FilterOperator.START_WITH);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "sick");
    taskWidget.applyFilter();
    taskWidget.waitPreviewTableLoaded();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1), DEFAULT_TIMEOUT);
  }
  
  @Test
  public void testFilterDate() {
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    newDashboardDetailsEditPage = modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    
    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage taskWidget = newDashboardDetailsEditPage.addNewTaskWidget();
    taskWidget.waitPreviewTableLoaded();
    taskWidget.openFilter();
    taskWidget.addFilter("Created Date",FilterOperator.TODAY);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(4));

    taskWidget.openFilter();
    taskWidget.removeFilter(0);
    taskWidget.addFilter("Created Date", FilterOperator.WITHIN_NEXT);
    taskWidget.inputValueOnLatestFilter(FilterValueType.WITHIN, "2","Year(s)");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(0));
  }
  
  @Test
  public void testFilterCategory() {
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    newDashboardDetailsEditPage = modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    
    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage taskWidget = newDashboardDetailsEditPage.addNewTaskWidget();
    taskWidget.waitPreviewTableLoaded();
    taskWidget.openFilter();
    taskWidget.addFilter("Category", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.CATEGORY_TYPE, "Leave Request");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(2));

    taskWidget.openFilter();
    taskWidget.removeFilter(0);
    taskWidget.addFilter("Category", FilterOperator.NO_CATEGORY);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1));

    taskWidget.openFilter();
    taskWidget.removeFilter(0);
    taskWidget.addFilter("Category", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT,"Annual Leave");  
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(0));
  }
  
  @Test
  public void testFilterMixedFields() {
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    newDashboardDetailsEditPage = modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    
    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage taskWidget = newDashboardDetailsEditPage.addNewTaskWidget();
    taskWidget.waitPreviewTableLoaded();
    taskWidget.addCustomColumns("CustomerName", "AccountNumber", "ShipmentDate");
    taskWidget.openFilter();
    taskWidget.addFilter("Name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Leave");
    
    taskWidget.addFilter("Description", FilterOperator.NOT_EMPTY);
    
    taskWidget.addFilter("Created Date", FilterOperator.WITHIN_NEXT);
    taskWidget.inputValueOnLatestFilter(FilterValueType.WITHIN, "2", "Year(s)");
    
    taskWidget.addFilter("Customer name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Anh Nguyen");
    
    taskWidget.addFilter("AccountNumber", FilterOperator.NOT_EMPTY);
    
    taskWidget.addFilter("Shipment date", FilterOperator.TODAY);
    
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(0));
  }
  
  @Test
  public void testFilterResponsible() {
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    newDashboardDetailsEditPage = modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    
    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage taskWidget = newDashboardDetailsEditPage.addNewTaskWidget();
    taskWidget.waitPreviewTableLoaded();
    taskWidget.openFilter();
    taskWidget.addFilter("Responsible", FilterOperator.CURRENT_USER);
    taskWidget.addFilter("Responsible", FilterOperator.IN);
    taskWidget.inputValueOnLatestFilter(FilterValueType.RESPONSIBLE_TYPE, "Everybody");
    taskWidget.addFilter("Responsible", FilterOperator.NOT_IN);
    taskWidget.inputValueOnLatestFilter(FilterValueType.RESPONSIBLE_TYPE, "costObject1");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(0));
  }
  
  @Test
  public void testDefaultSortOnTaskWidget() {
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    newDashboardDetailsEditPage = modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage(YOUR_TASKS_WIDGET);

    // sort by task name
    taskWidget.clickOnTaskNameColumn();
    taskWidget.getFirstTaskOfTaskWidget();
    taskWidget.getFirstTaskOfTaskWidget().shouldHave(text(MATERNITY_LEAVE_REQUEST), DEFAULT_TIMEOUT);

    configurationPage  = newDashboardDetailsEditPage.backToConfigurationPage();
    configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    // sort by task priority
    taskWidget.clickOnTaskPriorityColumn();
    taskWidget.getFirstTaskOfTaskWidget();
    taskWidget.getFirstTaskOfTaskWidget().shouldHave(text(SICK_LEAVE_REQUEST), DEFAULT_TIMEOUT);
  }
}
