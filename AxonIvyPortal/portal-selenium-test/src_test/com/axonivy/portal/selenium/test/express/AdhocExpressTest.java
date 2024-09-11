package com.axonivy.portal.selenium.test.express;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.bean.ExpressResponsible;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DefaultExpresTaskPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.WorkingTaskDialogPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class AdhocExpressTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void testAddAdhocForTask() {
    String taskNamePrefix = "Maternity";
    String defaultTaskName1 = "Task 1";
    String defaultTaskName2 = "Task 2";
    String defaultTaskComment1 = "good";
    String defaultTaskComment2 = "it's okay";

    // check if task Maternity exists
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy(taskNamePrefix, 1);
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(1));
    String taskId = taskWidgetPage.getTaskId();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);

    // create adhoc from Maternity task
    assertEquals(true, taskTemplatePage.isShowAdhocHistoryBtnNotExist());
    taskTemplatePage.clickAdhocCreationButton();
    assertEquals("You may lose your work in progress and start the Ad-hoc process. Do you want to continue?",
        taskTemplatePage.getAdhocCreationMessage());
    taskTemplatePage.clickAdhocOkButton();

    // create tasks in adhoc page
    ExpressProcessPage expressPage = new ExpressProcessPage();
    String processName = expressPage.getProcessName();
    assertTrue(processName.startsWith(String.format("AdHoc Process for Task %s -", taskId)));
    ExpressResponsible responsible = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    List<ExpressResponsible> responsibles = Arrays.asList(responsible);
    expressPage.createDefaultTask(0, defaultTaskName1, responsibles);
    expressPage.addNewTask(0);
    expressPage.createDefaultTask(1, defaultTaskName2, responsibles);
    expressPage.executeDirectly();

    // first task of adhoc
    DefaultExpresTaskPage defaultExpressTaskPage = new DefaultExpresTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment1);
    defaultExpressTaskPage.finishDefaultTask();

    // approval task of adhoc
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy(defaultTaskName2, 1);
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(1));
    taskWidgetPage.startTask(0);
    defaultExpressTaskPage = new DefaultExpresTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment2);
    defaultExpressTaskPage.finishDefaultTask();

    // check if task Maternity task
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy(taskNamePrefix, 1);
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(1));
    taskWidgetPage.startTask(0);

    // check adhoc history
    assertEquals(true, taskTemplatePage.isAdhocHistoryDialogExistWhenOpenTaskFirstTime());
    assertEquals(defaultTaskName2, taskTemplatePage.getTaskNameOfAdhocHistoryRow(0));
    assertEquals(defaultTaskComment2, taskTemplatePage.getCommentOfAdhocHistoryRow(0));
    assertEquals(defaultTaskName1, taskTemplatePage.getTaskNameOfAdhocHistoryRow(1));
    assertEquals(defaultTaskComment1, taskTemplatePage.getCommentOfAdhocHistoryRow(1));

    // open again by clicking adhoc dialog icon
    taskTemplatePage.closeAdhocHistoryDialog();
    taskTemplatePage.clickShowAdhocHistoryBtn();
    taskTemplatePage.getAdhocHistoryDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    taskTemplatePage.closeAdhocHistoryDialog();

    // open Maternity task again and make sure adhoc history dialog doesn't appear
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.leaveTask();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskTemplatePage = taskWidgetPage.startTask(0);

    taskTemplatePage.getAdhocHistoryDialog().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);

    // click adhoc creation button and check warning message
    taskTemplatePage.clickAdhocCreationButton();
    assertEquals("There is already an adhoc process for this task, do you want to create another one?",
        taskTemplatePage.getAdhocCreationMessage());
  }

}
