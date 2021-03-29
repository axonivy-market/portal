package ch.ivy.addon.portalkit.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExternalLink;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class UserProcessMapper {

  private UserProcessMapper() {}

  public static List<UserProcess> toUserProcesses(List<IWebStartable> processes) {
    return processes.stream().map(UserProcessMapper::toUserProcess).collect(Collectors.toList());
  }

  public static List<UserProcess> externalLinksToUserProcesses(List<ExternalLink> externalLinks) {
    return externalLinks.stream().map(UserProcessMapper::toUserProcess).collect(Collectors.toList());
  }

  public static UserProcess toUserProcess(IWebStartable process) {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessId(process.getId());
    userProcess.setProcessType(ProcessType.IVY_PROCESS);
    userProcess.setProcessName(stripHtmlTags(process.getDisplayName()));
    userProcess.setLink(process.getLink().getRelativeEncoded());
    userProcess.setDescription(process.getDescription());
    return userProcess;
  }

  public static UserProcess toUserProcess(ExpressProcess expressProcess, String expressStartLink) {
    String startLink = expressStartLink + "?workflowID=" + expressProcess.getId();
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessId(expressProcess.getId());
    userProcess.setProcessType(ProcessType.EXPRESS_PROCESS);
    userProcess.setProcessName(stripHtmlTags(expressProcess.getProcessName()));
    userProcess.setLink(startLink);
    userProcess.setDescription(expressProcess.getProcessDescription());
    return userProcess;
  }

  public static UserProcess toUserProcess(ExternalLink externalLink) {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessId(externalLink.getId().toString());
    userProcess.setProcessType(ProcessType.EXTERNAL_LINK);
    userProcess.setProcessName(stripHtmlTags(externalLink.getName()));
    userProcess.setUserId(externalLink.getCreatorId());
    userProcess.setLink(externalLink.getLink());
    userProcess.setDescription(externalLink.getLink());
    return userProcess;
  }

  private static String stripHtmlTags(String text) {
    return text.replaceAll("\\<.*?>", "");
  }
}
