package ch.ivy.addon.portalkit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.bo.History.HistoryType;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowEvent;
import ch.ivyteam.ivy.workflow.TaskState;

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

  public List<History> createHistoriesFromINotes(List<INote> notes, boolean excludeSystemNotes) {
    if(excludeSystemNotes) {
      return notes.stream()
          .filter(note -> !StringUtils.equals(note.getWritterName(), ISecurityConstants.SYSTEM_USER_NAME))
          .map(this::createHistoryFrom).collect(Collectors.toList());
    }
    return notes.stream().map(this::createHistoryFrom).collect(Collectors.toList());
  }

  public History createFailedReasonFrom(ITask task) {
    History history = createHistoryFrom(task);
    history.setContent(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/taskFailReason", Arrays.asList(task.getFailReason())));
    return history;
  }

  public History createHistoryFrom(ITask task) {
    History history = new History();
    history.setId(task.getId());
    history.setContent(generateHistoryContent(task));
    history.setTaskState(task.getState());
    if (task.getWorkerUserName() != null) {
      history.setInvolvedUsername(task.getWorkerUserName().replaceFirst("#", ""));
      IUser worker = task.getWorkerUser();
      if (worker != null) {
        history.setInvolvedFullname(worker.getFullName());
      }
    }

    // If task is done, set end time as history time
    // Otherwise, set start time as history time
    Date historyTimeStamp = task.getEndTimestamp() == null ? task.getStartTimestamp() : task.getEndTimestamp();
    history.setTimestamp(historyTimeStamp);
    history.setType(HistoryType.TASK);
    return history;
  }
  
  public List<History> createHistoryForTaskWorkflowEvents(ITask task) {
    List<History> histories = new ArrayList<>();
    if (PermissionUtils.checkReadAllWorkflowEventPermission()) {
      for (IWorkflowEvent event : task.getWorkflowEvents()) {
        History history = new History();
        history.setId(event.getId());
        history.setInvolvedFullname(event.getEventKind().name());
        history.setTaskState(event.getTaskState());
        history.setInvolvedUsername(event.getUserName());
        history.setTimestamp(event.getEventTimestamp());
        history.setType(HistoryType.EVENT);
        history.setContent(StringUtils.defaultIfEmpty(String.join(", ",
            event.getAdditionalInfo().stream().filter(Objects::nonNull).collect(Collectors.toList())),
            StringUtils.EMPTY));
        histories.add(history);
      }
    }

    return histories;
  }

  private String generateHistoryContent(ITask task) {
    StringBuilder content = new StringBuilder();
    content.append(task.getName());
    if (task.getState() == TaskState.FAILED || task.getState() == TaskState.JOIN_FAILED) {
      content.append("; ").append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/taskFailReason", Arrays.asList(task.getFailReason())));
    }
    return content.toString();
  }

  public History createHistoryFrom(INote note) {
    History history = new History();
    history.setId(note.getId());
    history.setContent(note.getMessage());
    if (note.getWritter() != null) {
      history.setInvolvedFullname(note.getWritter().getFullName());
    }
    history.setInvolvedUsername(note.getWritterName());
    history.setTimestamp(new Timestamp(note.getCreationTimestamp().getTime()));
    history.setType(HistoryType.NOTE);
    return history;
  }

}
