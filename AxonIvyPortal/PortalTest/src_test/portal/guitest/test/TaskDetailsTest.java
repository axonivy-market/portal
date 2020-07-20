package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.DateTimePattern;
import portal.guitest.common.TestAccount;
import portal.guitest.common.TestRole;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskDetailsTest extends BaseTest {

  private HomePage homePage;
  private TaskWidgetPage taskWidgetPage;
  private TaskDetailsPage taskDetailsPage;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
  }

  
  @Test
  public void testDelegateTaskInTaskDetail() {
    login(TestAccount.HR_ROLE_USER);
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    assertEquals(TestRole.EVERYBODY_ROLE, taskDetailsPage.getTaskResponsible());
    taskDetailsPage.openTaskDelegateDialog();
    taskDetailsPage.selectDelegateResponsible(TestAccount.HR_ROLE_USER.getFullName(), false);
    assertEquals(TestAccount.HR_ROLE_USER.getFullName(), taskDetailsPage.getTaskResponsible());
    
    taskDetailsPage.openTaskDelegateDialog();
    taskDetailsPage.selectDelegateResponsible(TestRole.HR_ROLE, true);
    assertEquals(TestRole.HR_ROLE, taskDetailsPage.getTaskResponsible());
  }
  

  @Test
  public void testChangeTaskDeadline() {
    String tomorrowStringLiteral = prepareTomorrowAsString();

    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    taskDetailsPage.changeExpiryOfTaskAt(tomorrowStringLiteral);
    assertEquals(tomorrowStringLiteral, taskWidgetPage.getExpiryOfTaskAt());
  }


  private String prepareTomorrowAsString() {
    LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
    return tomorrow.format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
  }
  
  @Test
  public void testClearTheDelayTimestampOfTask() {
    openDelayTask();
    assertEquals("DELAYED", taskDetailsPage.getTaskState());
    taskDetailsPage.openActionPanel();
    assertTrue(taskDetailsPage.isClearDelayTimeDisplayed());
    taskDetailsPage.clickOnClearDelayTime();
    assertEquals("SUSPENDED", taskDetailsPage.getTaskState());
    assertEquals("NA", taskDetailsPage.getTaskDelayTime());
  }
  
  @Test
  public void testChangeDelayTimestamp() {
    openDelayTask();
    assertEquals("DELAYED", taskDetailsPage.getTaskState());
    String tomorrow = prepareTomorrowAsString();
    taskDetailsPage.updateDelayTimestamp(tomorrow);
    assertEquals("DELAYED", taskDetailsPage.getTaskState());
    refreshPage();
    String yesterday = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskDetailsPage.updateDelayTimestamp(yesterday);
    assertEquals("SUSPENDED", taskDetailsPage.getTaskState());
  }


  private void openDelayTask() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Delayed"));
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
  }
}
