package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.SelfServicePage;
import portal.guitest.page.SelfServiceTaskPage;
import portal.guitest.page.TaskWidgetPage;

@Ignore
public class SelfServiceTest extends BaseTest {

  /*
   * ivy 7.0 use SelfServicePMName = SelfServiceBpm, ivy trunk use SelfServicePMName = SelfServiceBPM. We
   * differentiate them by using system property passed by maven.
   */
  private static final String SELF_SERVICE_BPM = System.getProperty("SelfServicePMName") != null ? System
      .getProperty("SelfServicePMName") : "selfServiceBpm";
  private String selfServiceProcessUrl = SELF_SERVICE_BPM + "/14232C3D829C4D71/start.ivp";

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    redirectToRelativeLink(selfServiceProcessUrl);
  }

  @Test
  public void chooseSelfServicePatternTest() {
    SelfServicePage selfServicePage = new SelfServicePage();
    int todoIndex = 0;
    int approvalIndex = 1;
    int qaIndex = 2;
    int adHocIndex = 3;

    selfServicePage.choosePattern(approvalIndex);
    assertEquals("APPROVAL", selfServicePage.getPatternOfTaskDef());

    selfServicePage.choosePattern(qaIndex);
    assertEquals("QA", selfServicePage.getPatternOfTaskDef());

    selfServicePage.choosePattern(todoIndex);
    assertEquals("TODO", selfServicePage.getPatternOfTaskDef());

    selfServicePage.choosePattern(adHocIndex);
    assertTrue(selfServicePage.isAdHocPatternSelected());

    selfServicePage.goToHomePage();
  }

  @Test
  public void addUserValidationTest() {
    SelfServicePage selfServicePage = new SelfServicePage();
    selfServicePage.openUserListSelectionOfFirstTodoTaskDef();
    selfServicePage.inputUser(TestAccount.DEMO_USER.getUsername());
    selfServicePage.waitForPageLoaded();
    selfServicePage.waitForElementDisplayed(By.id("error-message"), true);
    assertEquals("Assignee is already selected or invalid", selfServicePage.getUserListErrorMessage());
  }
  
  @Test
  public void selfServiceTodoProcessTest() {
    String todoSubject = "Test Todo process";
    String todoTask = "TODO " + todoSubject;
    String insertedTask = "TASK " + todoSubject;
    SelfServicePage selfServicePage = new SelfServicePage();
    selfServicePage.inputSubject(todoSubject);

    selfServicePage.addTask();
    selfServicePage.startWorkflow();

    HomePage homePage = new HomePage();
    assertTrue(homePage.isDisplayed());

    TaskWidgetPage taskWidget = homePage.openTaskList();
    assertEquals(todoTask, taskWidget.getNameOfTaskAt(0));
    assertEquals(TestAccount.DEMO_USER.getFullName(), taskWidget.getResposibleOfTaskAt(0));

    taskWidget.startTask(0);
    SelfServiceTaskPage taskDefPage = new SelfServiceTaskPage();
    taskDefPage.appendTask("appended task", TestAccount.DEMO_USER.getFullName());
    taskDefPage.insertTask("inserted task", TestAccount.DEMO_USER.getFullName());

    assertTrue(homePage.isDisplayed());
    taskWidget = homePage.openTaskList();
    assertEquals(insertedTask, taskWidget.getNameOfTaskAt(0));
    assertEquals(TestAccount.DEMO_USER.getFullName(), taskWidget.getResposibleOfTaskAt(0));

    taskWidget.startTask(0);
    taskDefPage.inputTaskComment("approved");
    taskDefPage.clickSendButton();
    
    assertTrue(homePage.isDisplayed());
    taskWidget = homePage.openTaskList();
    assertEquals(todoTask, taskWidget.getNameOfTaskAt(0));
    assertEquals(TestAccount.DEMO_USER.getFullName(), taskWidget.getResposibleOfTaskAt(0));
    
    taskWidget.startTask(0);
    taskDefPage.clickDoneButton();

    assertTrue(homePage.isDisplayed());
    taskWidget = homePage.openTaskList();    
    assertEquals(insertedTask, taskWidget.getNameOfTaskAt(0));
    assertEquals(TestAccount.DEMO_USER.getFullName(), taskWidget.getResposibleOfTaskAt(0));
    
    taskWidget.startTask(0);
    taskDefPage.inputTaskComment("approved");
    taskDefPage.clickSendButton();
    
    assertTrue(homePage.isDisplayed());
    taskWidget = homePage.openTaskList();    
    assertEquals(todoTask, taskWidget.getNameOfTaskAt(0));
    assertEquals(TestAccount.DEMO_USER.getFullName(), taskWidget.getResposibleOfTaskAt(0));
    
    taskWidget.startTask(0);
    taskDefPage.clickDoneButton();
    
    assertTrue(taskWidget.hasNoTask());
  }
}