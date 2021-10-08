package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;

@IvyWebTest
public class NewDashboardAddWidgetTest extends BaseTest {

  private NewDashBoardPage newDashBoardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashBoardPage = new NewDashBoardPage();
  }
  
  @Test
  public void testAddNewCaseList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    newDashBoardPage.switchToEditMode();
    newDashBoardPage.addWidget();
    CaseEditWidgetNewDashBoardPage newCaseWidget = newDashBoardPage.addNewCaseWidget();
    newCaseWidget.changeWidgetTitle("Your New Cases");
    newCaseWidget.save();
    CaseWidgetNewDashBoardPage caseWidget = newDashBoardPage.selectCaseWidget("Your New Cases");
    caseWidget.expand().shouldHaveSize(1);
  }
  
  @Test
  public void testAddNewTaskList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    newDashBoardPage.switchToEditMode();
    newDashBoardPage.addWidget();
    TaskEditWidgetNewDashBoardPage newTaskWidget = newDashBoardPage.addNewTaskWidget();
    newTaskWidget.changeWidgetTitle("Your New Tasks");
    newTaskWidget.save();
    TaskWidgetNewDashBoardPage taskWidget = newDashBoardPage.selectTaskWidget("Your New Tasks");
    taskWidget.expand().shouldHaveSize(1);
  }
  
}
