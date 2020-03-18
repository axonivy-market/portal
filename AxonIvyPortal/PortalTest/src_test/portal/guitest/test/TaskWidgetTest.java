package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.DateTimePattern;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TaskState;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskWidgetPage;
public class TaskWidgetTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
  }
  
  @Test
  public void testShowHideTaskDetailOnExpandedMode() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();

    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(0);
    assertTrue(taskWidgetPage.isTaskShowDetails(0));

    taskWidgetPage.closeTaskDetails(0);
    assertFalse(taskWidgetPage.isTaskShowDetails(0));
  }

  @Test
  public void testTasksInPortalHomePageUpdatedAfterExpandToFullMode() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks());

    taskWidgetPage.createTestingTasksInNewWindow();
    taskWidgetPage.expand();
    assertEquals(6, taskWidgetPage.countTasks());
  }

  @Test
  public void testOpenRelatedCaseOfTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(0);

    String relatedCaseName = taskWidgetPage.getRelatedCase();
    CasePage casePage = taskWidgetPage.openRelatedCaseOfTask();

    String caseName = casePage.getCaseName(0);
    assertEquals(relatedCaseName, caseName);
  }

  @Test
  public void testReserveTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(TaskState.RESERVED, taskWidgetPage.getTaskState(0));
    taskWidgetPage.resetTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(TaskState.OPEN, taskWidgetPage.getTaskState(0));
  }

  @Test
  public void testChangeTaskDeadline() {
    int firstTask = 0;
    LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
    String tomorrowStringLiteral = tomorrow.format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    taskWidgetPage.changeExpiryOfTaskAt(firstTask, tomorrowStringLiteral);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(tomorrowStringLiteral, taskWidgetPage.getExpiryOfTaskAt(firstTask));
  }
  
  /*
   * Disable since refresh task list is increase to 10000
  @Test
  public void testRefreshTaskList(){
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.openTaskList();
    int taskBeforeRefresh = taskWidgetPage.countTasks();
    JavascriptExecutor js = (JavascriptExecutor) getBrowser().getDriver();
    String url = UrlHelpers.generateAbsoluteProcessStartLink("internalSupport/14B2FC03D2E87141/CategoriedLeaveRequest.ivp");
    js.executeScript("window.open('');");

    
    String main = "";
    for (String string : getBrowser().getDriver().getWindowHandles()) {
      WebDriver window = getBrowser().getDriver().switchTo().window(string);
      if (window.getCurrentUrl().contains("blank")){
        window.get(url);
      } else {
        main = string;
      }
    }
    
    Sleeper.sleep(60000);
    getBrowser().getDriver().switchTo().window(main);
    int taskAfterRefresh = taskWidgetPage.countTasks();
    assertNotEquals(taskBeforeRefresh, taskAfterRefresh);
  }*/
  
  @Test
  public void testStickyTaskListOnCancel(){
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.openTaskList();
    taskWidgetPage.startAndCancelTask();
    Sleeper.sleep(3000);
    Assert.assertTrue(taskWidgetPage.isTaskListShown());
  }
  
  @Test
  public void testShowTaskCount() {
	TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
	Assert.assertEquals("In Dashboard, Task Count != 3", 3, taskWidgetPage.getTaskCount());
	taskWidgetPage.openTaskList();
	Assert.assertEquals("In Task list, Task Count != 3", 3, taskWidgetPage.getTaskCount());
  }
}
