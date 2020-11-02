package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
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
    taskDetailsPage = openDetailsPageOfFirstTask();
    assertTrue(StringUtils.equalsIgnoreCase(TestRole.EVERYBODY_ROLE, taskDetailsPage.getTaskResponsible()));
    taskDetailsPage.openTaskDelegateDialog();
    taskDetailsPage.selectDelegateResponsible(TestAccount.HR_ROLE_USER.getFullName(), false);
    assertTrue(StringUtils.equalsIgnoreCase(TestAccount.HR_ROLE_USER.getFullName(), taskDetailsPage.getTaskResponsible()));
    
    taskDetailsPage.openTaskDelegateDialog();
    taskDetailsPage.selectDelegateResponsible(TestRole.HR_ROLE, true);
    assertTrue(StringUtils.equalsIgnoreCase(TestRole.HR_ROLE, taskDetailsPage.getTaskResponsible()));
  }

  private TaskDetailsPage openDetailsPageOfFirstTask() {
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    return taskWidgetPage.openTaskDetails(0);
  }

  @Test
  public void testChangeTaskDeadline() {
    String tomorrowStringLiteral = prepareTomorrowAsString();

    taskDetailsPage = openDetailsPageOfFirstTask();
    taskDetailsPage.changeExpiryOfTaskAt(tomorrowStringLiteral);
    assertTrue(StringUtils.equalsIgnoreCase(tomorrowStringLiteral, taskWidgetPage.getExpiryOfTaskAt()));
  }

  private String prepareTomorrowAsString() {
    LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
    return tomorrow.format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
  }
  
  @Test
  public void testClearTheDelayTimestampOfTask() {
    openDelayTask();
    assertTrue(StringUtils.equalsIgnoreCase("DELAYED", taskDetailsPage.getTaskState()));
    taskDetailsPage.openActionPanel();
    assertTrue(taskDetailsPage.isClearDelayTimeDisplayed());
    taskDetailsPage.clickOnClearDelayTime();
    assertTrue(StringUtils.equalsIgnoreCase("SUSPENDED", taskDetailsPage.getTaskState()));
    assertTrue(StringUtils.equalsIgnoreCase("NA", taskDetailsPage.getTaskDelayTime()));
  }
  
  @Test
  public void testChangeDelayTimestamp() {
    openDelayTask();
    assertTrue(StringUtils.equalsIgnoreCase("DELAYED", taskDetailsPage.getTaskState()));
    String tomorrow = prepareTomorrowAsString();
    taskDetailsPage.updateDelayTimestamp(tomorrow);
    assertTrue(StringUtils.equalsIgnoreCase("DELAYED", taskDetailsPage.getTaskState()));
    refreshPage();
    taskDetailsPage = new TaskDetailsPage();
    String yesterday = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskDetailsPage.updateDelayTimestamp(yesterday);
    assertTrue(StringUtils.equalsIgnoreCase("SUSPENDED", taskDetailsPage.getTaskState()));
  }

  @Test
  public void testShowTaskWorkflowEvent() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTechnicalStateUrl);
    homePage = new HomePage();
    
    taskDetailsPage = openDetailsPageOfFirstTask();
    String eventData = taskDetailsPage.openWorkflowEventDialog();
    assertTrue(eventData.contains("admin"));
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
  
  @Test
  public void testShowDurationOfDoneTask() {
    login(TestAccount.ADMIN_USER);
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskWidgetPage.openAdvancedFilter("Completed on (from/to)", "completed");
    filterByDateType("completed");
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    assertFalse(StringUtils.equalsIgnoreCase("", taskDetailsPage.getDurationTimeText()));
  }
  
  private void filterByDateType(String dateType) {
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
    Calendar calendar = Calendar.getInstance();
    Date today = new Date();
    calendar.setTime(today);
    
    calendar.add(Calendar.DAY_OF_YEAR, -1);
    Date yesterday = calendar.getTime();
    String yesterdayText = dateFormat.format(yesterday);
    
    calendar.add(Calendar.DAY_OF_YEAR, 2);
    Date tomorrow = calendar.getTime();
    String tomorrowText = dateFormat.format(tomorrow);  

    taskWidgetPage.filterByDate(dateType, yesterdayText, tomorrowText);
  }
}
