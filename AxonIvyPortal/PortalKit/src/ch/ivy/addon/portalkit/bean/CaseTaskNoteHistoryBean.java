package ch.ivy.addon.portalkit.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.bo.History.HistoryType;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.TaskState;

@ManagedBean(name = "caseTaskNoteHistoryBean")
public class CaseTaskNoteHistoryBean {
    
    public String getCaseNoteHistoryLink(RemoteCase remoteCase) {
        ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
        IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath("Start Processes/CaseNoteHistory/showCaseNoteHistory.ivp");
        String redirectLink = RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), process).toString()
                + "?remoteCaseId=" + remoteCase.getId() + "&serverId=" + remoteCase.getServer().getId();
        redirectLink = removeDuplicatedPartOfUrl(redirectLink);
        return redirectLink;
    }
    
    public String getTaskNoteHistoryLink(RemoteTask remoteTask) {
        ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
        IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath("Start Processes/TaskNoteHistory/showTaskNoteHistory.ivp");
        String redirectLink = RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), process).toString()
                + "?remoteTaskId=" + remoteTask.getId() + "&serverId=" + remoteTask.getApplicationRegister().getServerId();
        redirectLink = removeDuplicatedPartOfUrl(redirectLink);
        return redirectLink;
    }
    
    public String getCaseNoteHistoryLink(Long caseId) {
        ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
        IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath("Start Processes/CaseNoteHistory/showCaseNoteHistoryInTask.ivp");
        String redirectLink = RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), process).toString() + "?caseId=" + caseId;
        redirectLink = removeDuplicatedPartOfUrl(redirectLink);
        return redirectLink;
    }
    
    private String removeDuplicatedPartOfUrl(String redirectLink) {
      String applicationContextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
      return redirectLink.replace(applicationContextPath, ""); //remove duplicate part of path
    }
    
    public String getCaseNoteContent(History history) {
        String content = history.getContent();
        if (history.getType() == HistoryType.TASK) {
            if (history.getTaskState() == TaskState.DONE) {
                return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsDone") + ": " + content;
            } else if (history.getTaskState() == TaskState.DESTROYED) {
                return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskIsDestroyed") + ": " + content;
            }else if (history.getTaskState() == TaskState.ZOMBIE) {
                return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateIsZombie") + ": " + content;
            }else if (history.getTaskState() == TaskState.CREATED) {
                return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateIsCreated") + ": " + content;
            }
        }
        return content;
    }

}
