package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.bo.History.HistoryType;
import ch.ivy.addon.portalkit.bo.RemoteNote;
import ch.ivy.addon.portalkit.bo.RemoteTask;


public class HistoryService {
  public List<History> createHistories(List<RemoteTask> tasks, List<RemoteNote> notes) {
    List<History> historiesRelatedToTasks = createHistoriesFromTasks(tasks);
    List<History> historiesRelatedToNotes = createToHistoriesFromNotes(notes);
    List<History> soretedHistories =
        sortHistoriesByTimeStampDescending(Arrays.asList(historiesRelatedToTasks, historiesRelatedToNotes));
    return soretedHistories;
  }

  private List<History> sortHistoriesByTimeStampDescending(List<List<History>> listOfHistories) {
    List<History> allHistories = new ArrayList<>();
    for (List<History> histories : listOfHistories) {
      allHistories.addAll(histories);
    }
    allHistories.sort((first, second) -> -first.getTimestamp().compareTo(second.getTimestamp()));
    return allHistories;
  }

  private List<History> createHistoriesFromTasks(List<RemoteTask> tasks) {
    List<History> histories = new ArrayList<>();
    for (RemoteTask task : tasks) {
      histories.add(createHistoryFrom(task));
    }
    return histories;
  }

  private History createHistoryFrom(RemoteTask task) {
    History history = new History();
    history.setContent(task.getName());
    history.setTaskState(task.getState());
    history.setInvolvedUser(task.getActivatorName());
    history.setTimestamp(task.getStartTimestamp());
    history.setType(HistoryType.TASK);
    return history;
  }

  private List<History> createToHistoriesFromNotes(List<RemoteNote> notes) {
    List<History> histories = new ArrayList<>();
    for (RemoteNote note : notes) {
      histories.add(createHistoryFrom(note));
    }
    return histories;
  }

  public History createHistoryFrom(RemoteNote note) {
    History history = new History();
    history.setContent(note.getMessage());
    history.setInvolvedUser(note.getCreatorUserName());
    history.setTimestamp(note.getCreationTimestamp());
    history.setType(HistoryType.NOTE);
    return history;
  }
}
