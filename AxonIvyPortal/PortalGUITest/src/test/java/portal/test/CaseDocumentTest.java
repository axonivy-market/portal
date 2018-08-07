package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.CaseDocumentPage;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.TaskWidgetPage;

public class CaseDocumentTest extends BaseTest {

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
  public void testCaseDocumentDisplayed() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTask(0);
    CaseDocumentPage caseDocumentPage = taskWidgetPage.openCaseDocumentPage();
    assertTrue("Upload component does not exist", caseDocumentPage.containsFileUploadComponent());
  }

}
