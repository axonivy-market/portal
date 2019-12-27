
package ch.ivy.addon.portalkit.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExternalLink;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class UserProcessMapper {

  private UserProcessMapper() {}

  public static List<UserProcess> toUserProcesses(List<IWebStartable> webStartables) {
    return webStartables.stream().map(UserProcessMapper::toUserProcess).collect(Collectors.toList());
  }

  public static List<UserProcess> externalLinkstoUserProcesses(List<ExternalLink> externalLinks) {
    return externalLinks.stream().map(UserProcessMapper::toUserProcess).collect(Collectors.toList());
  }

  public static UserProcess toUserProcess(IWebStartable webStartable) {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessName(webStartable.getDisplayName());
    userProcess.setLink(webStartable.getLink().getRelativeEncoded());
    return userProcess;
  }

  public static UserProcess toUserProcess(ExpressProcess expressProcess, String expressStartLink) {
    String startLink = expressStartLink + "?workflowID=" + expressProcess.getId();
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessName(expressProcess.getProcessName());
    userProcess.setLink(startLink);
    userProcess.setWorkflowId(expressProcess.getId());
    return userProcess;
  }

  public static UserProcess toUserProcess(ExternalLink externalLink) {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessName(externalLink.getName());
    userProcess.setLink(externalLink.getLink());
    userProcess.setExternalLink(true);
    userProcess.setWorkflowId(externalLink.getId().toString());
    return userProcess;
  }
}
