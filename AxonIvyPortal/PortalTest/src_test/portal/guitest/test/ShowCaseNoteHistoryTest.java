package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

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
      homePage = new HomePage();
      mainMenuPage = homePage.openMainMenu();
    }
    
    @Test
    public void testShowCaseNoteHistory() {
        CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
        detailsPage = casePage.openDetailsOfCaseHasName(CASE_NAME);        
        String caseName = detailsPage.getCaseName();
        String caseId = detailsPage.getCaseId();
        detailsPage.addNote(NOTE_CONTENT);
        goToCaseNoteHistoryPage(caseId);
        
        caseHistoryPage = new NoteHistoryPage();
        int numberOfNotes = caseHistoryPage.countNotes();
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
        String caseName = taskTemplatePage.getCaseName();
        String caseId = taskTemplatePage.getCaseId();
        goToCaseNoteHistoryPage(caseId);
        
        caseHistoryPage = new NoteHistoryPage();
        int numberOfNotes = caseHistoryPage.countNotes();
        assertEquals(2, numberOfNotes);
        assertEquals(NOTE_CONTENT, caseHistoryPage.getNoteContentOfRow(0));
        assertEquals(caseName, caseHistoryPage.getCaseName());
        assertTrue(StringUtils.equalsIgnoreCase(CASE_STATUS, caseHistoryPage.getCaseState()));
    }

}
