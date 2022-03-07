package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.bo.History.HistoryType;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.NoteHistoryExporter;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.util.Pair;

@ManagedBean(name = "caseTaskNoteHistoryBean")
public class CaseTaskNoteHistoryBean implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public boolean isShowAddNote() {
    return PermissionUtils.hasPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }
  
  public boolean isShowMoreNote() {
    return PermissionUtils.hasPortalPermission(PortalPermission.TASK_CASE_SHOW_MORE_NOTE);
  }

  public String getTaskNoteHistoryLink(ITask task) {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    IProcessStart process =
        collector.findProcessStartByUserFriendlyRequestPath("Start Processes/TaskNoteHistory/showTaskNoteHistory.ivp");
    String redirectLink = RequestUriFactory
        .createProcessStartUri(process, new Pair<String, String>("selectedTaskId", String.valueOf(task.getId())))
        .toASCIIString();
    return redirectLink;
  }

  public String getCaseNoteHistoryLink(ICase iCase) {
    return getCaseNoteHistoryLink(iCase.getId());
  }
  
  public String getCaseNoteHistoryLink(Long caseId) {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    IProcessStart process =
        collector.findProcessStartByUserFriendlyRequestPath("Start Processes/CaseNoteHistory/showCaseNoteHistory.ivp");
    String redirectLink = RequestUriFactory
        .createProcessStartUri(process, new Pair<String, String>("caseId", String.valueOf(caseId))).toASCIIString();
    return redirectLink;
  }

  public String getCaseNoteContent(History history) {
    String content = history.getContent();
    if (history.getType() == HistoryType.TASK) {
      switch (history.getTaskState()) {
        case DONE:
          content = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsDone") + ": " + content;
          break;
        case DESTROYED:
          content = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsDestroyed") + ": " + content;
          break;
        case ZOMBIE:
          content = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateIsZombie") + ": " + content;
          break;
        case CREATED:
          content = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateIsCreated") + ": " + content;
          break;
        case FAILED:
        case JOIN_FAILED:
          content = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsFailed") + ": " + content;
          break;
        case WAITING_FOR_INTERMEDIATE_EVENT:
          content = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsWaiting") + ": " + content;
          break;
        default:
          break;
      }
    }
    return content; 
  }
    
    public StreamedContent getExportedFileOfTaskNoteHistory(List<INote> taskNoteHistory, String fileName) {
      NoteHistoryExporter exporter = new NoteHistoryExporter();
      return exporter.getStreamedContentOfTaskNoteHistory(taskNoteHistory, fileName + ".xlsx");
    }

    public StreamedContent getStreamedContentOfCaseNoteHistory(List<History> caseNoteHistory, ICase iCase, String fileName) {
      NoteHistoryExporter exporter = new NoteHistoryExporter();
      return exporter.getStreamedContentOfCaseNoteHistory(caseNoteHistory, iCase, fileName + ".xlsx");
    }
}
