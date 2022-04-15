package ch.ivy.addon.portalkit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.bo.History.HistoryType;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowEvent;

public class HistoryService {

  private static final String CASE_NAME_FORMAT = "#%d %s";

  public List<History> getHistories(List<ITask> tasks, List<INote> notes, boolean excludeSystemTasks, boolean excludeSystemNotes) {
    List<History> historiesRelatedToTasks = createHistoriesFromITasks(tasks, excludeSystemTasks);
    List<History> historiesRelatedToNotes = createHistoriesFromINotes(notes, excludeSystemNotes);
    return sortHistoriesByTimeStampDescending(Arrays.asList(historiesRelatedToTasks, historiesRelatedToNotes));
  }

  public List<History> getCaseHistories(Long selectedCaseId, List<ITask> tasks, List<ICase> cases,
      boolean excludeSystemTasks, boolean excludeSystemNotes) {
    var historiesRelatedToTasks = createHistoriesFromITasks(tasks, excludeSystemTasks, selectedCaseId);
    var historiesRelatedToNotes = new ArrayList<History>();
    for (var subCase : cases) {
      historiesRelatedToNotes.addAll(createCaseHistories(excludeSystemNotes, subCase, selectedCaseId));
    }
    return sortHistoriesByTimeStampDescending(Arrays.asList(historiesRelatedToTasks, historiesRelatedToNotes));
  }

  private List<History> createCaseHistories(boolean excludeSystemNotes, ICase caseHistory, Long selectedCaseId) {
    var histories = new ArrayList<History>();
    for (var note : caseHistory.getNotes()) {
      if(excludeSystemNotes && !isNotSystemNote(note)) {
        continue;
      }
      var history = createHistoryFrom(note);
      buildDisplayCaseNameForNote(selectedCaseId, caseHistory, history);
      histories.add(history);
    }
    return histories;
  }

  private void buildDisplayCaseNameForNote(Long selectedCaseId, ICase caseHistory, History history) {
    history.setCaseId(caseHistory.getId());
    history.setDisabledCaseName(selectedCaseId == caseHistory.getId());
    var caseName = caseHistory.getName();
    if (StringUtils.isBlank(caseName)) {
      caseName = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/noCaseName");
    }
    history.setDisplayCaseName(String.format(CASE_NAME_FORMAT, caseHistory.getId(), caseName));
  }

  private List<History> createHistoriesFromITasks(List<ITask> tasks, boolean excludeSystemTasks, long selectedCaseId) {
    List<ITask> refineTasks = new ArrayList<>();
    refineTasks = tasks.stream().filter(isNotExpressTask()).collect(Collectors.toList());
    if (excludeSystemTasks) {
      refineTasks = refineTasks.stream().filter(isNotSystemTaskNote()).collect(Collectors.toList());
    }
    var histories = new ArrayList<History>();
    CollectionUtils.emptyIfNull(refineTasks).forEach(task -> {
      var history = createHistoryFrom(task);
      buildDisplayCaseNameForNote(selectedCaseId, task.getCase(), history);
      histories.add(history);
    });
    return histories;
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
    if (excludeSystemTasks) {
      return tasks.stream().filter(isNotSystemTaskNote()).filter(isNotExpressTask())
          .map(this::createHistoryFrom).collect(Collectors.toList());
    }
    return tasks.stream().filter(isNotExpressTask())
        .map(this::createHistoryFrom).collect(Collectors.toList());
  }

  public List<History> createHistoriesFromINotes(List<INote> notes, boolean excludeSystemNotes) {
    if(excludeSystemNotes) {
      return notes.stream().filter(note -> isNotSystemNote(note))
          .map(this::createHistoryFrom).collect(Collectors.toList());
    }
    return notes.stream().map(this::createHistoryFrom).collect(Collectors.toList());
  }

  private Predicate<? super ITask> isNotSystemTaskNote() {
    return task -> !StringUtils.equals(task.getWorkerUserName(), ISecurityConstants.SYSTEM_USER_NAME);
  }

  private Predicate<? super ITask> isNotExpressTask() {
    return task -> task.customFields().stringField(AdditionalProperty.ADHOC_EXPRESS_TASK.toString()).getOrNull() == null;
  }

  private boolean isNotSystemNote(INote note) {
    return !StringUtils.equals(note.getWritterName(), ISecurityConstants.SYSTEM_USER_NAME);
  }

  public History createHistoryFrom(ITask task) {
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
  
  public History createFailedReasonFrom(ITask task) {
    History history = createHistoryFrom(task);
    history.setContent(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/taskFailReason", Arrays.asList(task.getFailReason())));
    return history;
  }
  
  public List<History> createHistoryForTaskWorkflowEvents(ITask task) {
    List<History> histories = new ArrayList<>();
    List<IWorkflowEvent> taskEvents = getTaskWorkflowEvents(task);

    for (IWorkflowEvent event : taskEvents) {
      History history = new History();
      history.setId(event.getId());
      history.setDisplayName(event.getEventKind().toString());
      history.setContent(StringUtils.defaultIfEmpty(
          String.join(", ", event.getAdditionalInfo().stream().filter(Objects::nonNull).collect(Collectors.toList())),
          StringUtils.EMPTY));

      history.setTaskState(event.getTaskState());
      history.setInvolvedUsername(SecurityMemberDisplayNameUtils.generateFullDisplayNameForUser(event.getUser(), event.getUserName()));

      history.setTimestamp(event.getEventTimestamp());
      history.setType(HistoryType.EVENT);
      histories.add(history);
    }

    return sortHistoriesByTimeStampDescending(Arrays.asList(histories));
  }

  private List<IWorkflowEvent> getTaskWorkflowEvents(ITask task) {
    return IvyExecutor.executeAsSystem(() -> {
      return PermissionUtils.checkReadAllWorkflowEventPermission() ? task.getWorkflowEvents() : new ArrayList<>();
    });
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
