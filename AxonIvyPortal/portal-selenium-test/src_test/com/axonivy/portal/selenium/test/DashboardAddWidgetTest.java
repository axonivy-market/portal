package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

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
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = configPage.navigateToEditDashboardDetailsByName("Dashboard");

    newDashboardDetailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage newCaseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    newCaseWidget.changeWidgetTitle("Your New Cases");
    newCaseWidget.save();
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget("Your New Cases");
    caseWidget.expand().shouldHaveSize(1);
  }
  
  @Test
  public void testAddNewTaskList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = configPage.navigateToEditDashboardDetailsByName("Dashboard");

    newDashboardDetailsEditPage.addWidget();
    TaskEditWidgetNewDashBoardPage newTaskWidget = newDashboardDetailsEditPage.addNewTaskWidget();
    newTaskWidget.changeWidgetTitle("Your New Tasks");
    newTaskWidget.save();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget("Your New Tasks");
    taskWidget.expand().shouldHaveSize(1);
  }
  
}
