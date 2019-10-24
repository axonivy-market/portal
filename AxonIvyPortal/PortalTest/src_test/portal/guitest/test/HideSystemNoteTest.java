package portal.guitest.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class HideSystemNoteTest extends BaseTest {

  private static final String CREATE_TESTING_TASK_URL = "internalSupport/14B2FC03D2E87141/processWithSystemNote.ivp";
  private static final String SYSTEM_USER_NAME = "System user";

  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testAdminCanSeeSystemNoteInCaseDetail() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    List<String> caseNoteAuthors = getCaseNoteAuthors();
    Assert.assertTrue(caseNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  @Test
  public void testAdminCanSeeSystemNoteInTaskDetail() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    List<String> taskNoteAuthors = getTaskNoteAuthors();
    Assert.assertTrue(taskNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  @Test
  public void testUserCanNotSeeSystemNoteInCaseDetail() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    List<String> caseNoteAuthors = getCaseNoteAuthors();
    Assert.assertFalse(caseNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  @Test
  public void testUserCanNotSeeSystemNoteInTaskDetail() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    List<String> taskNoteAuthors = getTaskNoteAuthors();
    Assert.assertFalse(taskNoteAuthors.contains(SYSTEM_USER_NAME));
  }

  private List<String> getTaskNoteAuthors() {
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    TaskWidgetPage taskWidget = mainMenuPage.selectTaskMenu();
    TaskDetailsPage taskDetailsPage = taskWidget.openTaskDetails(0);
    List<String> taskNoteAuthors = taskDetailsPage.getTaskNoteAuthors();
    return taskNoteAuthors;
  }

  private List<String> getCaseNoteAuthors() {
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

    CaseDetailsPage caseDetailsPage = casePage.openDetailsOfCaseHasName("Create note");
    List<String> caseNoteAuthors = caseDetailsPage.getCaseNoteAuthors();
    return caseNoteAuthors;
  }

  @Override
  public void createTestingTasks() {
    redirectToRelativeLink(CREATE_TESTING_TASK_URL);
  }
}
