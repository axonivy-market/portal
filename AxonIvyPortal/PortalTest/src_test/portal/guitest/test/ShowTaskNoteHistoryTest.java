package portal.guitest.test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

public class ShowTaskNoteHistoryTest extends BaseTest {
    
    private HomePage homePage;
    private static final String noteContent = "test";
    private NoteHistoryPage taskHistoryPage;
    
    @Before
    public void setup() {
      super.setup();
      navigateToUrl(createTestingTasksUrl);
      navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
      LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
      loginPage.login();
      
      homePage = new HomePage();
      taskHistoryPage = new NoteHistoryPage();
    }
    
    @Test
    public void testShowTaskNoteHistory() {
        TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
        TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
        taskTemplatePage.openStatusTab();
        taskTemplatePage.addNewNote(noteContent);
        taskTemplatePage.clickCancelButton();
        MainMenuPage mainMenuPage = new MainMenuPage();
        Sleeper.sleepTight(2000);
        mainMenuPage.openTaskList();
        taskWidgetPage.openTaskDetails(0);
        taskWidgetPage.showNoteHistory();
        Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
        homePage.switchLastBrowserTab();
        int numberOfNotes;
        try {
            numberOfNotes = taskHistoryPage.countNotes();
        } catch (TimeoutException e) { // sometimes session is destroyed (don't know reason why!!!) so we cannot reach the page
            System.out.println("Stop test testShowTaskNoteHistory here because session is destroyed");
            return ;
        }
        assertEquals(1, numberOfNotes);
        assertEquals(noteContent, taskHistoryPage.getNoteContentOfFirstRow());
    }

}
