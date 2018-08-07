package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.bo.RemoteNote;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.ws.addon.IvyNote;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Ivy.class)
public class HistoryServiceTest {

  @Test
  public void testCreateHistoriesFromRemoteNotesAndRemoteTasks() {
    HistoryService historyService = new HistoryService();
    List<History> histories = historyService.createHistories(createRemoteTasks(), createRemoteNotes());
    Assert.assertEquals(2, histories.size());
    Assert.assertEquals(History.HistoryType.NOTE, histories.get(0).getType());
    Assert.assertEquals(History.HistoryType.TASK, histories.get(1).getType());
  }

  @Test
  public void testCreateHistoriesFromINotesAndITasks() {
    HistoryService historyService = new HistoryService();
    List<History> histories = historyService.getHistories(createITasks(), createINotes());
    Assert.assertEquals(2, histories.size());
    Assert.assertEquals(History.HistoryType.NOTE, histories.get(0).getType());
    Assert.assertEquals(History.HistoryType.TASK, histories.get(1).getType());
  }

  private List<RemoteTask> createRemoteTasks() {
    RemoteTask task = new RemoteTask();
    task.setId(Long.valueOf(1));
    task.setName("Sample task");
    task.setState(TaskState.SUSPENDED);
    task.setStartTimestamp(new Date());
    return Arrays.asList(task);
  }

  private List<RemoteNote> createRemoteNotes() {
    IvyNote ivyNote = new IvyNote();
    ivyNote.setId(Long.valueOf(2));
    ivyNote.setCreatorUserName("demo");
    ivyNote.setMessage("Sample note");
    Calendar oneHourFromNow = oneHourFromNow();
    ivyNote.setCreationTimestamp(oneHourFromNow);
    return Arrays.asList(new RemoteNote(ivyNote));
  }

  private List<ITask> createITasks() {
    ITask mockTask = Mockito.mock(ITask.class);
    Mockito.when(mockTask.getId()).thenReturn(Long.valueOf(4));
    Mockito.when(mockTask.getName()).thenReturn("Sample task");
    Mockito.when(mockTask.getState()).thenReturn(TaskState.SUSPENDED);

    Mockito.when(mockTask.getActivatorName()).thenReturn("demo");
    Mockito.when(mockTask.getStartTimestamp()).thenReturn(new Date());
    return Arrays.asList(mockTask);
  }

  private List<INote> createINotes() {
    INote mockNote = Mockito.mock(INote.class);
    Mockito.when(mockNote.getId()).thenReturn(Long.valueOf(3));
    Mockito.when(mockNote.getMessage()).thenReturn("Sample message");
    Mockito.when(mockNote.getCreationTimestamp()).thenReturn(oneHourFromNow().getTime());

    IUser mockUser = Mockito.mock(IUser.class);
    Mockito.when(mockUser.getDisplayName()).thenReturn("demo");
    Mockito.when(mockNote.getWritter()).thenReturn(mockUser);
    return Arrays.asList(mockNote);
  }

  private Calendar oneHourFromNow() {
    Calendar oneHourFromNow = Calendar.getInstance();
    oneHourFromNow.add(Calendar.HOUR, 1);
    return oneHourFromNow;
  }

}
