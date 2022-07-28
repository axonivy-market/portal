package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class DashboardAddWidgetTest extends BaseTest {

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }
  
  @Test
  public void testAddNewCaseList() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage newCaseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    newCaseWidget.changeWidgetTitle("Your New Cases");
    newCaseWidget.save();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget("Your New Cases");
    caseWidget.expand().shouldHaveSize(1);
  }
  
  @Test
  public void testAddNewTaskList() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage newTaskWidget = newDashboardDetailsEditPage.addNewTaskWidget();
    newTaskWidget.changeWidgetTitle("Your New Tasks");
    newTaskWidget.save();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget("Your New Tasks");
    taskWidget.expand().shouldHaveSize(1);
  }

  @Test
  public void testAddNewStatisticChart() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    var newStatisticWidget = newDashboardDetailsEditPage.addNewStatisticWidget();
    newStatisticWidget.selectFirstChart();
    newStatisticWidget.save();
    var chartWidget = newDashboardPage.selectStatisticWidget();
    chartWidget.countStatisticCharts().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
  }

  private NewDashboardDetailsEditPage gotoEditPublicDashboardPage() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    return modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
  }
}
