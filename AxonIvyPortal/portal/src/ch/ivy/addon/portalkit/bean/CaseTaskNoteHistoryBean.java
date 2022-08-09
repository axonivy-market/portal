package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.bo.History.HistoryType;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.exporter.internal.NoteHistoryExporter;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean(name = "caseTaskNoteHistoryBean")
public class CaseTaskNoteHistoryBean implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final String SHOW_TASK_NOTE_HISTORY_FRIENDLY_REQUEST_PATH = "Start Processes/PortaStart/showTaskNoteHistory.ivp";
  private static final String SHOW_CASE_NOTE_HISTORY_FRIENDLY_REQUEST_PATH = "Start Processes/PortaStart/showCaseNoteHistory.ivp";
  
  public boolean isShowAddNote() {
    return PermissionUtils.hasPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }
  
  public boolean isShowMoreNote() {
    return PermissionUtils.hasPortalPermission(PortalPermission.TASK_CASE_SHOW_MORE_NOTE);
  }

  public String getTaskNoteHistoryLink(ITask task) {
    Map<String, String> params = new HashMap<>();
    params.put("selectedTaskId", String.valueOf(task.getId()));
    return PortalNavigator.buildUrlByKeyword("showTaskNoteHistory.ivp", SHOW_TASK_NOTE_HISTORY_FRIENDLY_REQUEST_PATH, params);
  }

  public String getCaseNoteHistoryLink(ICase iCase) {
    return getCaseNoteHistoryLink(iCase.getId());
  }
  
  public String getCaseNoteHistoryLink(Long caseId) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    return PortalNavigator.buildUrlByKeyword("showCaseNoteHistory.ivp", SHOW_CASE_NOTE_HISTORY_FRIENDLY_REQUEST_PATH, params);
  }

  public String getCaseNoteContent(History history) {
    String content = history.getContent();
    if (history.getType() == HistoryType.TASK) {
      switch (history.getTaskState()) {
        case DONE:
          return createContentWithTaskState("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsDone", content);
        case DESTROYED:
          return createContentWithTaskState("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsDestroyed", content);
        case ZOMBIE:
          return createContentWithTaskState("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateIsZombie", content);
        case CREATED:
          return createContentWithTaskState("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateIsCreated", content);
        case FAILED:
        case JOIN_FAILED:
          return createContentWithTaskState("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsFailed", content);
        case WAITING_FOR_INTERMEDIATE_EVENT:
          return createContentWithTaskState("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsWaiting", content);
        default:
          break;
      }
    }
    return content; 
  }
  
  private String createContentWithTaskState(String taskStateCmsUrl, String content) {
    return String.format("%s: %s", Ivy.cms().co(taskStateCmsUrl), content);
  }
    
  public StreamedContent getExportedFileOfTaskNoteHistory(List<INote> taskNoteHistory, String fileName) {
    NoteHistoryExporter exporter = new NoteHistoryExporter();
    return exporter.getStreamedContentOfTaskNoteHistory(taskNoteHistory, fileName + ".xlsx");
  }

  public StreamedContent getStreamedContentOfCaseNoteHistory(List<History> caseNoteHistory, ICase iCase, String fileName) {
    NoteHistoryExporter exporter = new NoteHistoryExporter();
    return exporter.getStreamedContentOfCaseNoteHistory(caseNoteHistory, iCase, fileName + ".xlsx");
  }

  public SortMeta getCaseHistorySortByTimestamp() {
    return SortFieldUtil.buildSortMeta("timestamp", true);
  }

  public SortMeta getTaskHistorySortByCreationTimestamp() {
    return SortFieldUtil.buildSortMeta("creationTimestamp.time", true);
  }
}
