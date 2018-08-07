package portal.guitest.test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class ShowCaseNoteHistoryTest extends BaseTest {
    
    private CaseDetailsPage detailsPage;
    private HomePage homePage;
    private MainMenuPage mainMenuPage;
    private NoteHistoryPage caseHistoryPage;
    private static final String noteContent = "test"; 
    
    @Before
    public void setup() {
      super.setup();
      navigateToUrl(createTestingTasksUrl);
      navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
      LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
      loginPage.login();
      
      homePage = new HomePage();
      mainMenuPage = homePage.openMainMenu();
      caseHistoryPage = new NoteHistoryPage();
    }
    
    @Test
    public void testShowCaseNoteHistory() {
        CasePage casePage = mainMenuPage.selectCaseMenu();
        detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
        detailsPage.addNote(noteContent);
        detailsPage.showNoteHistory();
        Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
        homePage.switchLastBrowserTab();
        assertEquals(2, caseHistoryPage.countNotes());
        assertEquals(noteContent, caseHistoryPage.getNoteContentOfFirstRow());
    }
    
    @Test
    public void testShowCaseNoteHistoryInTask() {
        TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
        TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
        taskTemplatePage.openStatusTab();
        taskTemplatePage.addNewNote(noteContent);
        taskTemplatePage.showNoteHistory();
        Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
        homePage.switchLastBrowserTab();
        assertEquals(2, caseHistoryPage.countNotes());
        assertEquals(noteContent, caseHistoryPage.getNoteContentOfFirstRow());
    }

}
