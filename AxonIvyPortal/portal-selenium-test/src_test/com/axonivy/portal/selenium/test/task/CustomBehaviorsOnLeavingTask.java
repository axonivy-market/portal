package com.axonivy.portal.selenium.test.task;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskTemplateIFramePage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.axonivy.portal.selenium.page.WorkingTaskDialogPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class CustomBehaviorsOnLeavingTask extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testExtendLeaveLogicOnLeavingWorkingTask() {
    login(TestAccount.DEMO_USER);
    createStockInvestment();
    openDashboard();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.clickOnTaskName("Create stock investment");
    TaskTemplateIFramePage templatePage = new TaskTemplateIFramePage();
    templatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.leaveTask();
    NavigationHelper.navigateToTaskList();
    TaskDetailsPage taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    taskDetailsPage.getNotesWithContent("Demo has worked on the task recently").shouldHave(CollectionCondition.size(1));
  }
  
  @Test
  public void testExtendReserveLogicOnLeavingWorkingTask() {
    login(TestAccount.DEMO_USER);
    createStockInvestment();
    openDashboard();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.clickOnTaskName("Create stock investment");
    TaskTemplateIFramePage templatePage = new TaskTemplateIFramePage();
    templatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.reserveTask();
    login(TestAccount.ADMIN_USER);
    NavigationHelper.navigateToTaskList();
    taskWidget.clickCustomFieldsButtonOnActions(0);
    assertTrue(taskWidget.isCustomFieldsDialogDisplayed());
    List<String> taskCustomFieldNames = taskWidget.getCustomFieldNamesOnTaskCustomFieldsDialog();
    List<String> taskCusotmFieldValues = taskWidget.getCustomFieldValuesOnTaskCustomFieldsDialog();
    assertFalse(taskCustomFieldNames.isEmpty());
    assertFalse(taskCusotmFieldValues.isEmpty());
    assertEquals(taskCustomFieldNames.getFirst(), "reserveTaskWhenWorking");
    assertEquals(taskCusotmFieldValues.getFirst(), "1");
  }

  private void openDashboard() {
    redirectToNewDashBoard();
    NewDashboardPage dashboardPage = new NewDashboardPage();
    dashboardPage.waitForCaseWidgetLoaded();
  }
}
