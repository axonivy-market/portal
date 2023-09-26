package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class ShowTaskNoteHistoryTest extends BaseTest {
    
    private static final String NOTE_CONTENT = "test";
    private NoteHistoryPage taskHistoryPage;
    
    @Override
    @Before
    public void setup() {
      super.setup();
      redirectToRelativeLink(createTestingTasksUrl);
    }
    
    @Test
    public void testShowTaskNoteHistory() {
        NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
        TaskWidgetPage taskWidgetPage = newDashboardPage2.openTaskList();
        String taskId = taskWidgetPage.getTaskIdOfRow(0);
        TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
        taskTemplatePage.openCaseInfo();
        taskTemplatePage.addNewNote(NOTE_CONTENT);
        
        goToTaskNoteHistoryPage(taskId);
        taskHistoryPage = new NoteHistoryPage();
        int numberOfNotes = taskHistoryPage.countNotes();
        
        assertEquals(1, numberOfNotes);
        assertEquals(NOTE_CONTENT, taskHistoryPage.getNoteContentOfRow(0));
    }

}
