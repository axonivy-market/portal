package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.TaskTemplatePage;
import portal.page.TaskWidgetPage;

public class TaskTemplateTest extends BaseTest {

  @Before
  public void setup() {
    super.setup();
    String prepareTaskForTestUrl = "internalSupport/14B2FC03D2E87141/CreateTestTasks.ivp";
    navigateToUrl(prepareTaskForTestUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
  }

  @Test
  public void testCaseDocumentsDisplayed() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.openDocumentTab();
    assertTrue("Upload component does not exist", taskTemplatePage.containsFileUploadComponent());
  }

  @Test
  public void testCaseNotesDisplayed() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.openNoteTab();
    assertTrue("Add note button does not exist", taskTemplatePage.containsAddNoteButton());
  }

  @Test
  public void testAddingANote() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.openNoteTab();
    taskTemplatePage.addNewNote("A sample note");
    assertEquals(1, taskTemplatePage.getNumberOfNotes());
  }

}
