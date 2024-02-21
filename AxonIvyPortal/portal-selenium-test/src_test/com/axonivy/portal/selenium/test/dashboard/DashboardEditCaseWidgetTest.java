package com.axonivy.portal.selenium.test.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.CollectionCondition;

public class DashboardEditCaseWidgetTest extends BaseTest{
  private NewDashboardPage newDashboardPage;
  private static final String NAME_STR = "Name";
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
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
    caseWidget.countCases().shouldBe(CollectionCondition.size(1));
    
    caseWidget.removeFilter(0);
    caseWidget.removeFilter(0);
    
    caseWidget.addFilter(NAME_STR, FilterOperator.CONTAINS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "List", "Pi");
    caseWidget.applyFilter();
    caseWidget.countCases().shouldBe(CollectionCondition.size(2));
    
    caseWidget.addFilter(NAME_STR, FilterOperator.EMPTY);
    caseWidget.applyFilter();
    caseWidget.countCases().shouldBe(CollectionCondition.size(0));
    caseWidget.removeFilter(0);
    caseWidget.removeFilter(0);

    caseWidget.addFilter(NAME_STR, FilterOperator.START_WITH);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Or", "Te");
    caseWidget.applyFilter();
    caseWidget.countCases().shouldBe(CollectionCondition.size(2));
    caseWidget.addFilter(NAME_STR, FilterOperator.NOT_START_WITH);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Or", "Te");
    caseWidget.applyFilter();
    caseWidget.countCases().shouldBe(CollectionCondition.size(0));
    caseWidget.addFilter(NAME_STR, FilterOperator.END_WITH);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Or", "Te");
    caseWidget.closeFilter();
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
    caseWidget.countCases().shouldBe(CollectionCondition.size(1));
    caseWidget.removeFilter(0);

    caseWidget.addFilter("Creator", FilterOperator.IN);
    caseWidget.inputValueOnLatestFilter(FilterValueType.CREATOR_TYPE, "admin");
    caseWidget.applyFilter();
    caseWidget.countCases().shouldBe(CollectionCondition.size(1));

    caseWidget.addFilter("Creator", FilterOperator.NOT_IN);
    caseWidget.inputValueOnLatestFilter(FilterValueType.CREATOR_TYPE, "admin");
    caseWidget.applyFilter();
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
    caseWidget.countCases().shouldBe(CollectionCondition.size(1));
    
    caseWidget.removeFilter(0);
    caseWidget.addFilter("Category", FilterOperator.NO_CATEGORY);
    caseWidget.applyFilter();
    caseWidget.countCases().shouldBe(CollectionCondition.size(0));

    caseWidget.removeFilter(0);
    caseWidget.addFilter("Category", FilterOperator.NOT_CONTAINS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Leave");
    caseWidget.applyFilter();
    caseWidget.countCases().shouldBe(CollectionCondition.size(0));
  }
  
  @Test
  public void filterDateTest() {
    redirectToRelativeLink(createDataCreatedDate);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage caseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    caseWidget.waitPreviewTableLoaded();
    
    caseWidget.openFilter();
    caseWidget.addFilter("Created Date", FilterOperator.TODAY);
    caseWidget.applyFilter();
    caseWidget.countCases().shouldBe(CollectionCondition.size(2));
    
    caseWidget.removeFilter(0);
    caseWidget.addFilter("Created Date", FilterOperator.CURRENT);
    caseWidget.inputValueOnLatestFilter(FilterValueType.DATE_CURRENT, "Month");
    caseWidget.applyFilter();
    caseWidget.countCases().shouldBe(CollectionCondition.size(6));
    
  }
  
  @Test
  public void filterMixFieldTest() {
    redirectToRelativeLink(createDataCreatedDate);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToRelativeLink(testCaseListPermission);
    redirectToRelativeLink(testCaseListPermission);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage caseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    caseWidget.waitPreviewTableLoaded();
    caseWidget.addCustomColumns("AccountNumber", "ShipmentDate", "CustomerName",  "CustomerType", "InvoiceNumber");
    
    caseWidget.openFilter();
    caseWidget.addFilter("Description", FilterOperator.CONTAINS);
    caseWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Leave", "Test");
    caseWidget.applyFilter();
//    caseWidget.countCases().shouldBe(CollectionCondition.size(2));

    
//    caseWidget.countCases().shouldBe(CollectionCondition.size(6));

//    caseWidget.addFilter()
  }
  
  private NewDashboardDetailsEditPage gotoEditPublicDashboardPage() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    return modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
  }
  
  
}
