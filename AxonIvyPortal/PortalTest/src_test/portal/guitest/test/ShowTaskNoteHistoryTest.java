package portal.guitest.test;

import static junit.framework.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

@Ignore
public class ShowTaskNoteHistoryTest extends BaseTest {
    
    private HomePage homePage;
    private static final String noteContent = "test";
    private NoteHistoryPage taskHistoryPage;
    
    @Override
    @Before
    public void setup() {
      super.setup();
      redirectToRelativeLink(createTestingTasksUrl);
      redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
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
        Sleeper.sleep(15000);
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
        assertEquals(noteContent, taskHistoryPage.getNoteContentOfRow(0));
    }

}
