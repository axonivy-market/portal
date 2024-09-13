package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.DateTimePattern;
import portal.guitest.common.TestAccount;
import portal.guitest.common.TestRole;
import portal.guitest.common.Variable;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.UserProfilePage;

public class TaskDetailsTest extends BaseTest {

  private static final String COMMENT_CONTENT = "Test comment";
  private HomePage homePage;
  private TaskWidgetPage taskWidgetPage;
  private TaskDetailsPage taskDetailsPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
  }

  @Test
  public void testDelegateTaskInTaskDetail() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    taskDetailsPage = openDetailsPageOfFirstTask();
    assertTrue(StringUtils.equalsIgnoreCase(TestRole.EVERYBODY_ROLE, taskDetailsPage.getTaskResponsible()));
    taskDetailsPage.openTaskDelegateDialog();
    taskDetailsPage.selectDelegateResponsible(TestAccount.HR_ROLE_USER.getFullName(), false);
    assertTrue(StringUtils.equalsIgnoreCase(TestAccount.HR_ROLE_USER.getFullName(), taskDetailsPage.getTaskResponsible()));

    taskDetailsPage.openTaskDelegateDialog();
    taskDetailsPage.addCommentOnTaskDelegationDialog(COMMENT_CONTENT);
    taskDetailsPage.selectDelegateResponsible(TestRole.HR_ROLE, true);
    assertTrue(StringUtils.equalsIgnoreCase(TestRole.HR_ROLE, taskDetailsPage.getTaskResponsible()));
    assertTrue(StringUtils.contains(taskDetailsPage.getFirstTaskNoteComment(), COMMENT_CONTENT));
  }

  private TaskDetailsPage openDetailsPageOfFirstTask() {
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    return taskWidgetPage.openTaskDetails(0);
  }
  
  private void setFormattingLanguage() {
    HomePage homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    userProfilePage.inputFormattingLanguage("English (United Kingdom)");
    homePage = userProfilePage.save();
  }

  @Test
  public void testChangeTaskDeadline() {
    setFormattingLanguage();
    LocalDateTime now = LocalDateTime.now();
    String tomorrowStringLiteral = prepareTomorrowAsString(now);
    taskDetailsPage = openDetailsPageOfFirstTask();
    taskDetailsPage.changeExpiryOfTaskAt(tomorrowStringLiteral);
    assertEquals(prepareTomorrowAsLocaleDateString(now), taskDetailsPage.getExpiryOfTaskAt());
  }

  @Test
  @Ignore("Due to feature of 10, will fix later - id: IVYPORTAL-17553")
  public void testChangeTaskDeadlineWithAfterEscalationIsNA() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingCaseMapUrl);
    setFormattingLanguage();
    LocalDateTime now = LocalDateTime.now();
    String tomorrowStringLiteral = prepareTomorrowAsString(now);
    taskDetailsPage = openDetailsPageOfFirstTask();
    taskDetailsPage.changeExpiryOfTaskAt(tomorrowStringLiteral);
    assertTrue(StringUtils.equalsIgnoreCase(prepareTomorrowAsLocaleDateString(now), taskDetailsPage.getExpiryOfTaskAt()));
    String firstTaskNoteComment = taskDetailsPage.getFirstTaskNoteComment();
    assertTrue(StringUtils.contains(firstTaskNoteComment, "Portal Admin User (admin) has set deadline to task"));
    assertTrue(StringUtils.contains(firstTaskNoteComment, "assign Everybody as the task escalation activator"));
  }

  @Test
  public void testRemoveTaskDeadline() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    taskDetailsPage = openDetailsPageOfFirstTask();
    taskDetailsPage.openActionPanel();
    assertTrue(taskDetailsPage.isClearDeadlineDisplayed());
    taskDetailsPage.clickOnClearDeadlineTime();
    assertTrue(StringUtils.equalsIgnoreCase("N/A", taskDetailsPage.getExpiryOfTaskAt()));
    assertTrue(StringUtils.contains(taskDetailsPage.getFirstTaskNoteComment(), "Portal Admin User (admin) has removed expiry time of task"));
  }

  @Test
  public void testChangeTaskEscaltionActivator() {
    login(TestAccount.ADMIN_USER);
    taskDetailsPage = openDetailsPageOfFirstTask();
    taskDetailsPage.changeEscaltionActivatorTo("HR", false);
    assertTrue(StringUtils.equalsIgnoreCase("Human resources department", taskDetailsPage.getAfterEscalation()));
    assertTrue(StringUtils.contains(taskDetailsPage.getFirstTaskNoteComment(), "changed from Everybody to Human resources department"));
  }

  @Test
  public void testCannotChangeTaskEscaltionActivator() {
    login(TestAccount.DEMO_USER);
    taskDetailsPage = openDetailsPageOfFirstTask();
    assertFalse(taskDetailsPage.canChangeEscalationActivator());
  }

  private String prepareTomorrowAsString(LocalDateTime now) {
    LocalDateTime tomorrow = now.plusDays(1);
    return tomorrow.format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
  }
  
  private String prepareTomorrowAsLocaleDateString(LocalDateTime now) {
    LocalDateTime tommorrow = now.plusDays(1);
    return tommorrow.format(DateTimeFormatter.ofPattern(DateTimePattern.LOCALE_DATE_TIME_PATTERN, Locale.UK));
  }

  @Test
  public void testClearTheDelayTimestampOfTask() {
    openDelayTask();
    assertTrue(StringUtils.equalsIgnoreCase("DELAYED", taskDetailsPage.getTaskState()));
    taskDetailsPage.openActionPanel();
    assertTrue(taskDetailsPage.isClearDelayTimeDisplayed());
    taskDetailsPage.clickOnClearDelayTime();
    assertTrue(StringUtils.equalsIgnoreCase("SUSPENDED", taskDetailsPage.getTaskState()));
    assertTrue(StringUtils.equalsIgnoreCase("N/A", taskDetailsPage.getTaskDelayTime()));
  }

  @Test
  public void testChangeDelayTimestamp() {
    setFormattingLanguage();
    openDelayTask();
    assertTrue(StringUtils.equalsIgnoreCase("DELAYED", taskDetailsPage.getTaskState()));
    LocalDateTime now = LocalDateTime.now();
    String tomorrow = prepareTomorrowAsString(now);
    String tomorrowWithLocale = prepareTomorrowAsLocaleDateString(now);
    assertEquals(tomorrowWithLocale, taskDetailsPage.updateDelayTimestamp(tomorrow,tomorrowWithLocale));
    assertTrue(StringUtils.equalsIgnoreCase("DELAYED", taskDetailsPage.getTaskState()));
    refreshPage();
    taskDetailsPage = new TaskDetailsPage();
    String yesterday = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    String yesterdayWithLocale = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(DateTimePattern.LOCALE_DATE_TIME_PATTERN, Locale.UK));
    assertEquals(yesterdayWithLocale, taskDetailsPage.updateDelayTimestamp(yesterday,yesterdayWithLocale));
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
    openFirstTaskInCompletedTasks();
    assertFalse(StringUtils.equalsIgnoreCase("", taskDetailsPage.getDurationTimeText()));
  }

  private void openFirstTaskInCompletedTasks() {
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskWidgetPage.openAdvancedFilter("Completed on (from/to)", "completed");
    filterByDateType("completed");
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
  }

  private void filterByDateType(String dateType) {
    DateFormat dateFormat = new SimpleDateFormat(DateTimePattern.DATE_TIME_PATTERN);
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

  @Test
  public void testShowNotAvailableValues() {
    login(TestAccount.ADMIN_USER);
    openFirstTaskInTaskList();
    assertTrue(StringUtils.equalsIgnoreCase(taskDetailsPage.getTaskDelayTime(), "N/A"));
    
    login(TestAccount.GUEST_USER);
    openFirstTaskInTaskList();
    assertFalse(taskDetailsPage.isDelayTimeDisplayed());
  }

  private void openFirstTaskInTaskList() {
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
  }

  @Test
  public void testDragDropWidgets() {
    openFirstTaskInTaskList();
    taskDetailsPage.clickOnSwitchToEditModeButton();
    taskDetailsPage.waitForSwitchToViewModeButtonDisplayed();
    taskDetailsPage.drapAndDropWidgets("note", "document");
    taskDetailsPage.drapAndDropWidgets("document", "note");
    taskDetailsPage.drapAndDropWidgets("note", "document");
    taskDetailsPage.drapAndDropWidgets("document", "note");
    taskDetailsPage.clickOnSwitchToViewModeButton();
    taskDetailsPage.clickOnSwitchToEditModeButton();
    taskDetailsPage.waitForResetButtonDisplayed();
  }
}
