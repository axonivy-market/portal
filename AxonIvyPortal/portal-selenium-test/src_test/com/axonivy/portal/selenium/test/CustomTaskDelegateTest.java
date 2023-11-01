package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class CustomTaskDelegateTest extends BaseTest {

  private static final String SICK_LEAVE_REQUEST = "Sick Leave Request";
  private static final String MATERNITY_LEAVE_REQUEST = "Maternity Leave Request";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestingTasks();
  }

  @Test
  public void testCustomTaskDelegateOnlyToGroup() {
    login(TestAccount.HR_ROLE_USER);
    redirectToNewDashBoard();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(SICK_LEAVE_REQUEST);
    taskWidgetPage.waitTillOnlyOneTaskAppear();
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.openTaskDelegationDialog();

    // User type is disabled
    assertTrue(taskWidgetPage.isDelegateTypeDisabled(0));
    // Role type is enabled
    assertFalse(taskWidgetPage.isDelegateTypeDisabled(1));
    assertTrue(taskWidgetPage.isDelegateListSelectionAvailable());
  }

  @Test
  public void testCustomTaskDelegateOnlyToUser() {
    login(TestAccount.HR_ROLE_USER);
    redirectToNewDashBoard();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(MATERNITY_LEAVE_REQUEST);
    taskWidgetPage.waitTillOnlyOneTaskAppear();
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.openTaskDelegationDialog();

    // Role type is disabled
    assertTrue(taskWidgetPage.isDelegateTypeDisabled(1));
    // User type is enabled
    assertFalse(taskWidgetPage.isDelegateTypeDisabled(0));
    assertTrue(taskWidgetPage.isDelegateListSelectionAvailable());
  }

  @Test
  public void testCustomTaskDelegateNoDelegateOption() {
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(MATERNITY_LEAVE_REQUEST);
    taskWidgetPage.waitTillOnlyOneTaskAppear();
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.openTaskDelegationDialog();
    assertFalse(taskWidgetPage.isDelegateTypeAvailable());
    assertEquals("This task cannot be delegated to any other user or group.", taskWidgetPage.getCannotDelegateText());
  }
}