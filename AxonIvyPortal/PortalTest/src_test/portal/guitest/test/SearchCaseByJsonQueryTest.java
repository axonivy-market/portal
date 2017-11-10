package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CasePage;
import portal.guitest.page.LoginPage;

public class SearchCaseByJsonQueryTest extends BaseTest {
    
    @Before
    @Override
    public void setup() {
      super.setup();
      navigateToUrl(createTestingTasksUrl);
    }
    
    @Test
    public void testSearchCaseByJsonQuery() {
        LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
        loginPage.login();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            
        }
        
        /*navigateToUrl("internalSupport/15FA421D253C6746/searchByCaseJsonQuery.ivp");
        CasePage casePage = new CasePage();
        assertEquals(1, casePage.getNumberOfCases());*/
    }

}
