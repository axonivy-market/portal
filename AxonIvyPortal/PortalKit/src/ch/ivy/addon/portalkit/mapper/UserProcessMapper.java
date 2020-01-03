
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

  public static List<UserProcess> externalLinksToUserProcesses(List<ExternalLink> externalLinks) {
    return externalLinks.stream().map(UserProcessMapper::toUserProcess).collect(Collectors.toList());
  }

  public static UserProcess toUserProcess(IWebStartable webStartable) {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessName(stripHtmlTags(webStartable.getDisplayName()));
    userProcess.setLink(webStartable.getLink().getRelativeEncoded());
    userProcess.setDescription(webStartable.getDescription());
    return userProcess;
  }

  public static UserProcess toUserProcess(ExpressProcess expressProcess, String expressStartLink) {
    String startLink = expressStartLink + "?workflowID=" + expressProcess.getId();
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessName(stripHtmlTags(expressProcess.getProcessName()));
    userProcess.setLink(startLink);
    userProcess.setWorkflowId(expressProcess.getId());
    userProcess.setDescription(expressProcess.getProcessDescription());
    return userProcess;
  }

  public static UserProcess toUserProcess(ExternalLink externalLink) {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessName(stripHtmlTags(externalLink.getName()));
    userProcess.setLink(externalLink.getLink());
    userProcess.setExternalLink(true);
    userProcess.setWorkflowId(externalLink.getId().toString());
    userProcess.setDescription(externalLink.getLink());
    return userProcess;
  }

  private static String stripHtmlTags(String text) {
    return text.replaceAll("\\<.*?>", "");
  }
}
