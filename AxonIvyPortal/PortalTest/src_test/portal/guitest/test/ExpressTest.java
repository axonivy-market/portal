package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.TestAccount;
import portal.guitest.page.ExpressFormDefinitionPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;

public class ExpressTest extends PortalExpressTest{
  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    homePage = new HomePage();
  }

  @Test
  public void testAdhocMultiApprovalWhenMultiTask() {
    goToCreateExpressProcess();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test approval", "Test description");
    
    ExpressFormDefinitionPage formDefinition = configureExpressProcessWhenMultiApproval(expressProcessPage);
    formDefinition.executeWorkflow();
    executeExpressProcessWhenMultiApproval();
  }
}
