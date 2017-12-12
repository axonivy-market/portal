package ch.ivy.addon.portalkit.bean;

import javax.faces.bean.ManagedBean;

import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;

@ManagedBean(name = "caseTaskNoteHistoryBean")
public class CaseTaskNoteHistoryBean {
    
    public String getCaseNoteHistoryLink(RemoteCase remoteCase) {
        ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
        IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath("Start Processes/CaseNoteHistory/showCaseNoteHistory.ivp");
        String redirectLink = RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), process).toString()
                + "?remoteCaseId=" + remoteCase.getId() + "&serverId=" + remoteCase.getServer().getId();
        redirectLink = redirectLink.replace("/ivy", ""); //remove duplicate part of path
        return redirectLink;
    }
    
    public String getTaskNoteHistoryLink(RemoteTask remoteTask) {
        ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
        IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath("Start Processes/TaskNoteHistory/showTaskNoteHistory.ivp");
        String redirectLink = RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), process).toString()
                + "?remoteTaskId=" + remoteTask.getId() + "&serverId=" + remoteTask.getApplicationRegister().getServerId();
        redirectLink = redirectLink.replace("/ivy", ""); //remove duplicate part of path
        return redirectLink;
    }
    
    public String getCaseNoteHistoryLink(Long caseId) {
        ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
        IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath("Start Processes/CaseNoteHistory/showCaseNoteHistoryInTask.ivp");
        String redirectLink = RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), process).toString() + "?caseId=" + caseId;
        redirectLink = redirectLink.replace("/ivy", ""); //remove duplicate part of path
        return redirectLink;
    }

}
