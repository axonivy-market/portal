package com.axonivy.portal.selenium.test.task;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.TaskState;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.TestRole;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class TaskDetailsTest extends BaseTest {

  // TASK NAME
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String TAKE_ORDER = "Take Order";

  // NOTE CONTENT
  private static final String NOTE_TASK_DETAIL_TECHNICAL_CASE =
      "Note is added on Task Details and the task has Technical Case";
  private static final String NOTE_TASK_DETAIL_BUSINESS_CASE =
      "Note is added on Task Details and the task has only Business Case";

  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";
  private static final String COMMENT_CONTENT = "Test comment";

  private HomePage homePage;
  private TaskWidgetPage taskWidgetPage;
  private TaskDetailsPage taskDetailsPage;


  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }

  @Test
  public void testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithoutTechnicalCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.openTask(ORDER_PIZZA);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.addNote(NOTE_TASK_DETAIL_BUSINESS_CASE);
    taskDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_BUSINESS_CASE).shouldHave(size(1));
    taskDetailsPage.gotoBusinessCase();
    CaseDetailsPage caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_BUSINESS_CASE).shouldHave(size(1));
  }

  @Test
  public void testVisibilityOfNotesWhenAddNoteOnTaskDetailsWithTechnicalCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.openTask(TAKE_ORDER);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.addNote(NOTE_TASK_DETAIL_TECHNICAL_CASE);
    taskDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_TECHNICAL_CASE).shouldHave(size(1));
    CaseDetailsPage caseDetailsPage = taskDetailsPage.gotoTechnicalCase();
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_TECHNICAL_CASE).shouldHave(size(1));
    caseDetailsPage.gotoBusinessCase();
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.getNotesWithContent(NOTE_TASK_DETAIL_TECHNICAL_CASE).shouldHave(size(1));
  }

  @Test
  public void testShareTaskDetails() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(grantShareLinkTaskDetailsPermission);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.openTask(TAKE_ORDER);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getShareButton().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    taskDetailsPage.getShareDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    redirectToRelativeLink(denyShareLinkTaskDetailsPermission);
    redirectToNewDashBoard();
    mainMenuPage.openTaskList();
    taskWidgetPage.openTask(TAKE_ORDER);
    taskDetailsPage.getShareButton().shouldBe(Condition.disappear);
  }
  
  @Test
  public void testCannotChangeTaskEscaltionActivator() {
    updateLegacyUIConfiguration();
    createTestingTasks();
    login(TestAccount.DEMO_USER);
    homePage = new HomePage();
    taskDetailsPage = openDetailsPageOfFirstTask();
    assertFalse(taskDetailsPage.canChangeEscalationActivator());
  }
  
  private TaskDetailsPage openDetailsPageOfFirstTask() {
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    return taskWidgetPage.openTaskDetails(0);
  }

  
  @Test
  public void testChangeDelayTimestamp() {
    updateLegacyUIConfiguration();
    createTestingTasks();
    homePage = new HomePage();

    setFormattingLanguage();
    openDelayTask();
    assertEquals(TaskState.DELAYED.getValue(),taskDetailsPage.getTaskState());
    LocalDateTime now = LocalDateTime.now();
    String tomorrow = prepareTomorrowAsString(now);
    String tomorrowWithLocale = prepareTomorrowAsLocaleDateString(now);
    assertEquals(tomorrowWithLocale, taskDetailsPage.updateDelayTimestamp(tomorrow, tomorrowWithLocale).replace(",", ""));
    assertEquals(TaskState.DELAYED.getValue(),taskDetailsPage.getTaskState());
    refreshPage();
    taskDetailsPage = new TaskDetailsPage();
    String yesterday = LocalDateTime.now().minusDays(1)
        .format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
    String yesterdayWithLocale = LocalDateTime.now().minusDays(1)
        .format(DateTimeFormatter.ofPattern(DateTimePattern.LOCALE_DATE_TIME_PATTERN, Locale.UK));
    assertEquals(yesterdayWithLocale, taskDetailsPage.updateDelayTimestamp(yesterday, yesterdayWithLocale).replace(",", ""));
    assertEquals(TaskState.SUSPENDED.getValue(),taskDetailsPage.getTaskState());
  }

  private void setFormattingLanguage() {
    HomePage homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    userProfilePage.inputFormattingLanguage("English (United Kingdom)");
    homePage = userProfilePage.saveAndGoToHomePage();
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
  
  private String prepareTomorrowAsString(LocalDateTime now) {
    LocalDateTime tomorrow = now.plusDays(1);
    return tomorrow.format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));
  }

  private String prepareTomorrowAsLocaleDateString(LocalDateTime now) {
    LocalDateTime tommorrow = now.plusDays(1);
    return tommorrow.format(DateTimeFormatter.ofPattern(DateTimePattern.LOCALE_DATE_TIME_PATTERN, Locale.UK));
  }

  @Test
  public void testChangeTaskDeadline() {
    updateLegacyUIConfiguration();
    createTestingTasks();
    homePage = new HomePage();
    
    setFormattingLanguage();
    LocalDateTime now = LocalDateTime.now();
    String tomorrowStringLiteral = prepareTomorrowAsString(now);
    taskDetailsPage = openDetailsPageOfFirstTask();
    taskDetailsPage.changeExpiryOfTaskAt(tomorrowStringLiteral);
    assertEquals(prepareTomorrowAsLocaleDateString(now), taskDetailsPage.getExpiryOfTaskAt().replace(",", ""));
  }

  @Test
  public void testChangeTaskDeadlineWithAfterEscalationIsNA() {
    updateLegacyUIConfiguration();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingCaseMapUrl);
    homePage = new HomePage();

    setFormattingLanguage();
    LocalDateTime now = LocalDateTime.now();
    String tomorrowStringLiteral = prepareTomorrowAsString(now);
    taskDetailsPage = openDetailsPageOfFirstTask();
    taskDetailsPage.changeExpiryOfTaskAt(tomorrowStringLiteral);
    assertTrue(StringUtils.equalsIgnoreCase(prepareTomorrowAsLocaleDateString(now), taskDetailsPage.getExpiryOfTaskAt().replace(",", "")));
    String firstTaskNoteComment = taskDetailsPage.getFirstTaskNoteComment();
    assertTrue(StringUtils.contains(firstTaskNoteComment, "Portal Admin User (admin) has set deadline to task"));
  }

  @Test
  public void testChangeTaskEscaltionActivator() {
    updateLegacyUIConfiguration();
    createTestingTasks();
    homePage = new HomePage();
    
    login(TestAccount.ADMIN_USER);
    taskDetailsPage = openDetailsPageOfFirstTask();
    taskDetailsPage.changeEscaltionActivatorTo("HR", false);
    assertTrue(StringUtils.equalsIgnoreCase("Human resources department", taskDetailsPage.getAfterEscalation()));
    assertTrue(StringUtils.contains(taskDetailsPage.getFirstTaskNoteComment(), "changed from Everybody to Human resources department"));
  }
  
  @Test
  public void testClearTheDelayTimestampOfTask() {
    updateLegacyUIConfiguration();
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    homePage = new HomePage();

    openDelayTask();
    assertEquals(TaskState.DELAYED.getValue(), taskDetailsPage.getTaskState());
    taskDetailsPage.openActionPanel();

    assertTrue(taskDetailsPage.isClearDelayTimeDisplayed());
    taskDetailsPage.clickOnClearDelayTime();

    assertEquals(TaskState.SUSPENDED.getValue(), taskDetailsPage.getTaskState());

    assertEquals("N/A", taskDetailsPage.getTaskDelayTime());
  }

  
  @Test
  public void testDelegateTaskInTaskDetail() {
    updateLegacyUIConfiguration();
    createTestingTasks();
    login(TestAccount.ADMIN_USER);
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
  
  @Test
  public void testDragDropWidgets() {
    updateLegacyUIConfiguration();
    createTestingTasks();

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
  
  private void openFirstTaskInTaskList() {
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
  }

  
  @Test
  public void testRemoveTaskDeadline() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    homePage = new HomePage();

    taskDetailsPage = openDetailsPageOfFirstTask();
    taskDetailsPage.openActionPanel();
    assertTrue(taskDetailsPage.isClearDeadlineDisplayed());
    taskDetailsPage.clickOnClearDeadlineTime();
    assertEquals("N/A", taskDetailsPage.getExpiryOfTaskAt());
    assertTrue(StringUtils.contains(taskDetailsPage.getFirstTaskNoteComment(), "Portal Admin User (admin) has removed expiry time of task"));
  }
  
  @Test
  public void testShowDurationOfDoneTask() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
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
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);

    login(TestAccount.ADMIN_USER);
    openFirstTaskInTaskList();
    assertTrue(StringUtils.equalsIgnoreCase(taskDetailsPage.getTaskDelayTime(), "N/A"));
    
    login(TestAccount.GUEST_USER);
    openFirstTaskInTaskList();
    assertFalse(taskDetailsPage.isDelayTimeDisplayed());
  }
  
  @Test
  public void testShowTaskWorkflowEvent() {
    updateLegacyUIConfiguration();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTechnicalStateUrl);
    homePage = new HomePage();

    taskDetailsPage = openDetailsPageOfFirstTask();
    String eventData = taskDetailsPage.openWorkflowEventDialog();
    assertTrue(eventData.contains("admin"));
  }
}
