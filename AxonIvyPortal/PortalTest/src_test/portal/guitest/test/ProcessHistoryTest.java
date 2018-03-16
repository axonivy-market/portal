package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.ProcessHistoryPage;

public class ProcessHistoryTest extends BaseTest {
  private static final String CREATE_CASE_LINK = "internalSupport/16193718E2B3D6C4/InspectResource.ivp";
  private static final String DISPLAY_PROCESS_HISTORY_PAGE =
      "internalSupport/16193718E2B3D6C4/viewProcessHistoryOfResource.ivp";

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
  }

  @Test
  public void testDisplayProcessHistory() {
    redirectToRelativeLink(CREATE_CASE_LINK);
    redirectToRelativeLink(CREATE_CASE_LINK);
    redirectToRelativeLink(CREATE_CASE_LINK);

    redirectToRelativeLink(DISPLAY_PROCESS_HISTORY_PAGE);

    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitForPageLoaded();

    assertEquals(3, processHistoryPage.countCases());
  }

  @Test
  public void testDisplayEmptyMessage() {
    redirectToRelativeLink(DISPLAY_PROCESS_HISTORY_PAGE);

    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitForPageLoaded();
    
    assertTrue(processHistoryPage.isEmptyMessageDisplay());
  }
}
