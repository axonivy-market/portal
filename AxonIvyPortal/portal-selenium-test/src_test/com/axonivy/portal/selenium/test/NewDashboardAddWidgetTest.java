package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

@IvyWebTest
public class NewDashboardAddWidgetTest extends BaseTest {

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
    newDashboardPage.switchToEditMode();
    newDashboardPage.addWidget();
    CaseEditWidgetNewDashBoardPage newCaseWidget = newDashboardPage.addNewCaseWidget();
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
    newDashboardPage.switchToEditMode();
    newDashboardPage.addWidget();
    TaskEditWidgetNewDashBoardPage newTaskWidget = newDashboardPage.addNewTaskWidget();
    newTaskWidget.changeWidgetTitle("Your New Tasks");
    newTaskWidget.save();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget("Your New Tasks");
    taskWidget.expand().shouldHaveSize(1);
  }
  
}
