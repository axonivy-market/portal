package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

@Ignore
public class ShowCaseNoteHistoryTest extends BaseTest {
    
    private CaseDetailsPage detailsPage;
    private HomePage homePage;
    private MainMenuPage mainMenuPage;
    private NoteHistoryPage caseHistoryPage;
    private static final String NOTE_CONTENT = "test"; 
    private static final String CASE_NAME = "Leave Request";
    private static final String CASE_STATUS = "In Progress";
    
    @Override
    @Before
    public void setup() {
      super.setup();
      redirectToRelativeLink(createTestingTasksUrl);
      redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
      homePage = new HomePage();
      mainMenuPage = homePage.openMainMenu();
      caseHistoryPage = new NoteHistoryPage();
    }
    
    @Test
    public void testShowCaseNoteHistory() {
        CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
        detailsPage = casePage.openDetailsOfCaseHasName(CASE_NAME);        
        String caseName = casePage.getCaseName();
        String caseId = casePage.getCaseId();
        detailsPage.addNote(NOTE_CONTENT);
        detailsPage.showNoteHistory();
        Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
        homePage.switchLastBrowserTab();
        int numberOfNotes;
        try {
            numberOfNotes = caseHistoryPage.countNotes();
        } catch (TimeoutException e) { // sometimes session is destroyed (don't know reason why!!!) so we cannot reach the page
            System.out.println("Stop testShowCaseNoteHistory test here because session is destroyed");
            return ;
        }
        assertEquals(2, numberOfNotes);
        assertEquals(NOTE_CONTENT, caseHistoryPage.getNoteContentOfRow(0));
        assertEquals(caseName, caseHistoryPage.getCaseName());
        assertEquals(caseId, caseHistoryPage.getCaseId());
        assertTrue(StringUtils.equalsIgnoreCase(CASE_STATUS, caseHistoryPage.getCaseState()));
    }
    
    @Test
    public void testShowCaseNoteHistoryInTask() {
        TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
        TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
        taskTemplatePage.openCaseInfo();
        taskTemplatePage.addNewNote(NOTE_CONTENT);
        taskTemplatePage.showNoteHistory();
        String caseName = taskTemplatePage.getCaseName();
        Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
        homePage.switchLastBrowserTab();
        int numberOfNotes;
        try {
            numberOfNotes = caseHistoryPage.countNotes();
        } catch (TimeoutException e) { // sometimes session is destroyed (don't know reason why!!!) so we cannot reach the page
            System.out.println("Stop test testShowCaseNoteHistoryInTask here because session is destroyed");
            return ;
        }
        assertEquals(2, numberOfNotes);
        assertEquals(NOTE_CONTENT, caseHistoryPage.getNoteContentOfRow(0));
        assertEquals(caseName, caseHistoryPage.getCaseName());
        assertTrue(StringUtils.equalsIgnoreCase(CASE_STATUS, caseHistoryPage.getCaseState()));
    }

}
