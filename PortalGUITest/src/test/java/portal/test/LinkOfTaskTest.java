package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.TaskWidgetPage;

public class LinkOfTaskTest extends BaseTest {
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
  }

  @Test
  public void testOpenLinkOfTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    long taskId = taskWidgetPage.getIdOfTaskHasIndex(0);
    redirectToRelativeLink("portalTemplate/14BEE67A1CA77C16/PortalSingleTask.ivp?remoteTaskId=" + taskId);
    assertTrue(taskWidgetPage.isTaskShowDetails(0));
  }
}
