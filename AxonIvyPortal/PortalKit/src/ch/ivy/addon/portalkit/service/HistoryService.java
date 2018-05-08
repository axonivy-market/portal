package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.bo.History.HistoryType;
import ch.ivy.addon.portalkit.bo.RemoteNote;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;

public class HistoryService {
  public List<History> createHistories(List<RemoteTask> tasks, List<RemoteNote> notes) {
    List<History> historiesRelatedToTasks = createHistoriesFromTasks(tasks);
    List<History> historiesRelatedToNotes = createToHistoriesFromNotes(notes);
    List<History> soretedHistories =
        sortHistoriesByTimeStampDescending(Arrays.asList(historiesRelatedToTasks, historiesRelatedToNotes));
    return soretedHistories;
  }

  public List<History> getHistories(List<ITask> tasks, List<INote> notes) {
    List<History> historiesRelatedToTasks = createHistoriesFromITasks(tasks);
    List<History> historiesRelatedToNotes = createToHistoriesFromINotes(notes);
    List<History> soretedHistories =
        sortHistoriesByTimeStampDescending(Arrays.asList(historiesRelatedToTasks, historiesRelatedToNotes));
    return soretedHistories;
  }

  private List<History> sortHistoriesByTimeStampDescending(List<List<History>> listOfHistories) {
    List<History> allHistories = new ArrayList<>();
    for (List<History> histories : listOfHistories) {
      allHistories.addAll(histories);
    }
    allHistories.sort((first, second) -> second.getTimestamp().compareTo(first.getTimestamp()));
    return allHistories;
  }

  private List<History> createHistoriesFromTasks(List<RemoteTask> tasks) {
    return tasks.stream().map(task -> createHistoryFrom(task)).collect(Collectors.toList());
  }

  private List<History> createHistoriesFromITasks(List<ITask> tasks) {
    return tasks.stream().map(task -> createHistoryFrom(task)).collect(Collectors.toList());
  }

  private List<History> createToHistoriesFromNotes(List<RemoteNote> notes) {
    return notes.stream().map(note -> createHistoryFrom(note)).collect(Collectors.toList());
  }

  private List<History> createToHistoriesFromINotes(List<INote> notes) {
    return notes.stream().map(note -> createHistoryFrom(note)).collect(Collectors.toList());
  }

  private History createHistoryFrom(ITask task) {
    History history = new History();
    history.setId(task.getId());
    history.setContent(task.getName());
    history.setTaskState(task.getState());
    if (task.getWorkerUserName() != null) {
      history.setInvolvedUsername(task.getWorkerUserName().replaceFirst("#", ""));
      IUser worker = task.getWorkerUser();
      if (worker != null) {
        history.setInvolvedFullname(worker.getFullName());
      }
    }
    history.setTimestamp(task.getStartTimestamp());
    history.setType(HistoryType.TASK);
    return history;
  }

  private History createHistoryFrom(RemoteTask task) {
    History history = new History();
    history.setId(task.getId());
    history.setContent(task.getName());
    history.setTaskState(task.getState());
    history.setInvolvedUsername(task.getWorkerUserName());
    history.setInvolvedFullname(task.getWorkerFullName());
    history.setTimestamp(task.getStartTimestamp());
    history.setType(HistoryType.TASK);
    return history;
  }

  public History createHistoryFrom(RemoteNote note) {
    History history = new History();
    history.setId(note.getId());
    history.setContent(note.getMessage());
    history.setInvolvedUsername(note.getCreatorUserName());
    history.setInvolvedFullname(note.getCreatorFullName());
    history.setTimestamp(note.getCreationTimestamp());
    history.setType(HistoryType.NOTE);
    return history;
  }

  public History createHistoryFrom(INote note) {
    History history = new History();
    history.setId(note.getId());
    history.setContent(note.getMessage());
    if (note.getWritter() != null) {
      history.setInvolvedFullname(note.getWritter().getFullName());
    }
    history.setInvolvedUsername(note.getWritterName());
    history.setTimestamp(note.getCreationTimestamp());
    history.setType(HistoryType.NOTE);
    return history;
  }

}
