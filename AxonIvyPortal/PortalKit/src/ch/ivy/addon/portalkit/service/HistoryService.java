package ch.ivy.addon.portalkit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.bo.History.HistoryType;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;

public class HistoryService {

  public List<History> getHistories(List<ITask> tasks, List<INote> notes, boolean excludeSystemTasks, boolean excludeSystemNotes) {
    List<History> historiesRelatedToTasks = createHistoriesFromITasks(tasks, excludeSystemTasks);
    List<History> historiesRelatedToNotes = createHistoriesFromINotes(notes, excludeSystemNotes);
    return sortHistoriesByTimeStampDescending(Arrays.asList(historiesRelatedToTasks, historiesRelatedToNotes));
  }

  private List<History> sortHistoriesByTimeStampDescending(List<List<History>> listOfHistories) {
    List<History> allHistories = new ArrayList<>();
    for (List<History> histories : listOfHistories) {
      allHistories.addAll(histories);
    }
    allHistories.sort((first, second) -> second.getTimestamp().compareTo(first.getTimestamp()));
    return allHistories;
  }

  private List<History> createHistoriesFromITasks(List<ITask> tasks, boolean excludeSystemTasks) {
    if(excludeSystemTasks) {
      return tasks.stream()
          .filter(task -> !StringUtils.equals(task.getWorkerUserName(), ISecurityConstants.SYSTEM_USER_NAME))
          .filter(task -> task.customFields().stringField(AdditionalProperty.ADHOC_EXPRESS_TASK.toString()).getOrNull() == null)
          .map(this::createHistoryFrom).collect(Collectors.toList());
    }
    return tasks.stream()
        .filter(task -> task.customFields().stringField(AdditionalProperty.ADHOC_EXPRESS_TASK.toString()).getOrNull() == null)
        .map(this::createHistoryFrom).collect(Collectors.toList());
  }

  private List<History> createHistoriesFromINotes(List<INote> notes, boolean excludeSystemNotes) {
    if(excludeSystemNotes) {
      return notes.stream()
          .filter(note -> !StringUtils.equals(note.getWritterName(), ISecurityConstants.SYSTEM_USER_NAME))
          .map(this::createHistoryFrom).collect(Collectors.toList());
    }
    return notes.stream().map(this::createHistoryFrom).collect(Collectors.toList());
  }

  private History createHistoryFrom(ITask task) {
    History history = new History();
    history.setId(task.getId());
    history.setContent(task.getName());
    history.setTaskState(task.getState());
    history.setInvolvedUsername(task.getWorkerUserName());
    history.setInvolvedUser(task.getWorkerUser());

    // If task is done, set end time as history time
    // Otherwise, set start time as history time
    Date historyTimeStamp = task.getEndTimestamp() == null ? task.getStartTimestamp() : task.getEndTimestamp();
    history.setTimestamp(historyTimeStamp);
    history.setType(HistoryType.TASK);
    return history;
  }

  public History createHistoryFrom(INote note) {
    History history = new History();
    history.setId(note.getId());
    history.setContent(note.getMessage());
    history.setInvolvedUser(note.getWritter());
    history.setInvolvedUsername(note.getWritterName());
    history.setTimestamp(new Timestamp(note.getCreationTimestamp().getTime()));
    history.setType(HistoryType.NOTE);
    return history;
  }

}
