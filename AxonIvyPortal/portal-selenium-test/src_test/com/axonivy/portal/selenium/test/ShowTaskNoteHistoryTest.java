package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class ShowTaskNoteHistoryTest extends BaseTest {

  private static final String NOTE_CONTENT = "test";
  private NoteHistoryPage taskHistoryPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void testShowTaskNoteHistory() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    TaskWidgetPage taskWidgetPage = newDashboardPage.openTaskList();
    TaskDetailsPage taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    String taskId = taskDetailsPage.getTaskId();
    NavigationHelper.navigateToTaskList();
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
