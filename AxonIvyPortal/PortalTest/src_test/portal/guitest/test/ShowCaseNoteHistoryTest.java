package portal.guitest.test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseHistoryPage;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;

public class ShowCaseNoteHistoryTest extends BaseTest {
    
    private CaseDetailsPage detailsPage;
    private HomePage homePage;
    private CaseHistoryPage caseHistoryPage;
    
    @Before
    public void setup() {
      super.setup();
      navigateToUrl(createTestingTasksUrl);
      navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
      LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
      loginPage.login();
      
      homePage = new HomePage();
      MainMenuPage mainMenuPage = homePage.openMainMenu();
      CasePage casePage = mainMenuPage.selectCaseMenu();
      detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    }
    
    @Test
    public void testShowCaseNoteHistory() {
        detailsPage.addNote("test");
        detailsPage.showNoteHistory();
        Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
        homePage.switchLastBrowserTab();
        caseHistoryPage = new CaseHistoryPage();
        assertEquals(2, caseHistoryPage.countNotes());
    }

}
