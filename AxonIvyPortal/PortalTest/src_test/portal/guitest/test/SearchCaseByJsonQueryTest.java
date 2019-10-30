package portal.guitest.test;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;

public class SearchCaseByJsonQueryTest extends BaseTest {
    
    @SuppressWarnings("unused")
    @Before
    @Override
    public void setup() {
      super.setup();
      navigateToUrl(createTestingTasksUrl);
      navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
      
      LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
      loginPage.login();
      new HomePage();
    }
    
    @Test
    public void testSearchCaseByJsonQuery() {
        redirectToRelativeLink("internalSupport/15FA421D253C6746/searchByCaseJsonQuery.ivp");
        CaseWidgetPage casePage = new CaseWidgetPage();
        assertEquals(1, casePage.getNumberOfCases());
    }

}
