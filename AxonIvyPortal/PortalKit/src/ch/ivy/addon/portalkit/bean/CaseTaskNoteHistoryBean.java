package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.bo.History.HistoryType;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.NoteHistoryExporter;
import ch.ivy.ws.addon.IvyNote;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

@ManagedBean(name = "caseTaskNoteHistoryBean")
public class CaseTaskNoteHistoryBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public String getTaskNoteHistoryLink(ITask task) {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    IProcessStart process =
        collector.findProcessStartByUserFriendlyRequestPath("Start Processes/TaskNoteHistory/showTaskNoteHistory.ivp");
    String redirectLink =
        RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), process)
            .toString() + "?selectedTaskId=" + task.getId();
    redirectLink = removeDuplicatedPartOfUrl(redirectLink);
    return redirectLink;
  }

  public String getCaseNoteHistoryLink(ICase iCase) {
    return getCaseNoteHistoryLink(iCase.getId());
  }
  
  public String getCaseNoteHistoryLink(Long caseId) {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    IProcessStart process =
        collector.findProcessStartByUserFriendlyRequestPath("Start Processes/CaseNoteHistory/showCaseNoteHistory.ivp");
    String redirectLink =
        RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), process)
            .toString() + "?caseId=" + caseId;
    redirectLink = removeDuplicatedPartOfUrl(redirectLink);
    return redirectLink;
  }

  private String removeDuplicatedPartOfUrl(String redirectLink) {
    String applicationContextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
    return redirectLink.replace(applicationContextPath, ""); // remove duplicate part of path
  }

  public String getCaseNoteContent(History history) {
    String content = history.getContent();
    if (history.getType() == HistoryType.TASK) {
      if (history.getTaskState() == TaskState.DONE) {
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsDone") + ": " + content;
      } else if (history.getTaskState() == TaskState.DESTROYED) {
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsDestroyed") + ": " + content;
      } else if (history.getTaskState() == TaskState.ZOMBIE) {
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateIsZombie") + ": " + content;
      } else if (history.getTaskState() == TaskState.CREATED) {
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateIsCreated") + ": " + content;
      }
    }
    return content;
  }
    
//    public StreamedContent getExportedFileOfTaskNoteHistory(List<IvyNote> taskNoteHistory, String fileName) {
//      NoteHistoryExporter exporter = new NoteHistoryExporter();
//      return exporter.getStreamedContentOfTaskNoteHistory(taskNoteHistory, fileName + ".xls");
//    }
//
//    public StreamedContent getStreamedContentOfCaseNoteHistory(List<History> caseNoteHistory, RemoteCase remoteCase, String fileName) {
//      NoteHistoryExporter exporter = new NoteHistoryExporter();
//      return exporter.getStreamedContentOfCaseNoteHistory(caseNoteHistory, remoteCase, fileName + ".xls");
//    }
}
