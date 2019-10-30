package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.ProcessHistoryPage;

public class ProcessHistoryTest extends BaseTest {
  private static final String CREATE_ALPHA_COMPANY_CASE_LINK = "portalExamples/1624C1C79661758C/createAlphaCompany.ivp";
  private static final String CREATE_BETA_COMPANY_CASE_LINK = "portalExamples/1624C1C79661758C/createBetaCompany.ivp";
  private static final String DISPLAY_PROCESS_HISTORY_ALPHA_COMPANY_PAGE =
      "portalExamples/1624C1C79661758C/viewProcessHistoryOfAlphaCompany.ivp";
  private static final String DISPLAY_PROCESS_HISTORY_BETA_COMPANY_PAGE =
      "portalExamples/1624C1C79661758C/viewProcessHistoryOfBetaCompany.ivp";

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
    redirectToRelativeLink(CREATE_ALPHA_COMPANY_CASE_LINK);
    redirectToRelativeLink(CREATE_ALPHA_COMPANY_CASE_LINK);
    redirectToRelativeLink(CREATE_ALPHA_COMPANY_CASE_LINK);

    redirectToRelativeLink(DISPLAY_PROCESS_HISTORY_ALPHA_COMPANY_PAGE);

    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitForPageLoaded();

    assertEquals(3, processHistoryPage.countCases());
    
  }
  
  @Test
  public void testDisplayProcessHistoryDialog() {
    redirectToRelativeLink(CREATE_BETA_COMPANY_CASE_LINK);
    redirectToRelativeLink(CREATE_BETA_COMPANY_CASE_LINK);
    
    redirectToRelativeLink(DISPLAY_PROCESS_HISTORY_BETA_COMPANY_PAGE);
    
    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitForPageLoaded();
    
    assertEquals(2, processHistoryPage.openDialogAndCountCases());

  }
  
  @Test
  public void testDisplayEmptyMessage() {
    redirectToRelativeLink(DISPLAY_PROCESS_HISTORY_ALPHA_COMPANY_PAGE);

    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitForPageLoaded();
    
    assertTrue(processHistoryPage.isEmptyMessageDisplay());
  }
}
