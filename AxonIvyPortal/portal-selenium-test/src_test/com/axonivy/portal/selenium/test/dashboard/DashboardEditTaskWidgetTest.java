package com.axonivy.portal.selenium.test.dashboard;

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
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest(headless = false)
public class DashboardEditTaskWidgetTest extends BaseTest {
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

    taskWidget.removeFilter(0);
    taskWidget.addFilter("Name",FilterOperator.NOT_EMPTY);
    taskWidget.applyFilter();
    taskWidget.waitPreviewTableLoaded();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(4));
    
    taskWidget.removeFilter(0);
    taskWidget.addFilter("Name",FilterOperator.END_WITH);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "request");
    
    taskWidget.addFilter("Name",FilterOperator.START_WITH);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "sick");
    taskWidget.applyFilter();
    taskWidget.waitPreviewTableLoaded();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1));
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
    
    taskWidget.removeFilter(0);
    taskWidget.addFilter("Created Date", FilterOperator.WITHIN_NEXT);
    taskWidget.inputValueOnLatestFilter(FilterValueType.WITHIN, "2","Year(s)");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(4));
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
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(4));
    
    taskWidget.removeFilter(0);
    taskWidget.addFilter("Category", FilterOperator.NO_CATEGORY);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1));
    
    taskWidget.removeFilter(0);
    taskWidget.addFilter("Category", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT,"Annual Leave");  
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(0));
  }
  
}
