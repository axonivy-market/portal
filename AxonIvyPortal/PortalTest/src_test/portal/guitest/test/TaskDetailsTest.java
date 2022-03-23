package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.DateTimePattern;
import portal.guitest.common.TestAccount;
import portal.guitest.common.TestRole;
import portal.guitest.page.HomePage;
import portal.guitest.page.LanguagePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskDetailsTest extends BaseTest {

  private static final String COMMENT_CONTENT = "Test comment";
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
    assertEquals(TestAccount.HR_ROLE_USER.getFullName(), taskDetailsPage.getTaskResponsible());
    
    taskDetailsPage.openTaskDelegateDialog();
    taskDetailsPage.addCommentOnTaskDelegationDialog(COMMENT_CONTENT);
    taskDetailsPage.selectDelegateResponsible(TestRole.HR_ROLE, true);
    assertEquals(TestRole.HR_ROLE, taskDetailsPage.getTaskResponsible());
    assertTrue(StringUtils.contains(taskDetailsPage.getFirstTaskNoteComment(), COMMENT_CONTENT));
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
    assertEquals(tomorrowStringLiteral, taskWidgetPage.getExpiryOfTaskAt());
  }


  private String prepareTomorrowAsString() {
    LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
    return tomorrow.format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
  }
  
  @Test
  public void testClearTheDelayTimestampOfTask() {
    openDelayTask();
    assertEquals("Delayed", taskDetailsPage.getTaskState());
    taskDetailsPage.openActionPanel();
    assertTrue(taskDetailsPage.isClearDelayTimeDisplayed());
    taskDetailsPage.clickOnClearDelayTime();
    assertEquals("Suspended", taskDetailsPage.getTaskState());
    assertEquals("NA", taskDetailsPage.getTaskDelayTime());
  }
  
  @Test
  public void testChangeDelayTimestamp() {
    openDelayTask();
    assertEquals("Delayed", taskDetailsPage.getTaskState());
    String tomorrow = prepareTomorrowAsString();
    taskDetailsPage.updateDelayTimestamp(tomorrow);
    assertEquals("Delayed", taskDetailsPage.getTaskState());
    refreshPage();
    String yesterday = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    taskDetailsPage.updateDelayTimestamp(yesterday);
    assertEquals("Suspended", taskDetailsPage.getTaskState());
  }

  @Test
  public void testShowTaskWorkflowEvent() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTechnicalStateUrl);
    homePage = new HomePage();
    
    taskDetailsPage = openDetailsPageOfFirstTask();
    taskDetailsPage.openActionPanel();
    taskDetailsPage.clickOnShowWorkFlowEvents();
    assertTrue(org.apache.commons.lang3.StringUtils.contains(taskDetailsPage.getFirstEventDataRow(), "admin"));
  }

  private void openDelayTask() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    homePage = new HomePage();
    LanguagePage languagePage = homePage.openLanguagePage();
    languagePage.selectLanguage(1);
    languagePage.save();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Delayed"));
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
  }
}
