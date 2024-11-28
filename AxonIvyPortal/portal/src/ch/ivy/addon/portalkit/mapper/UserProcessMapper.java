package ch.ivy.addon.portalkit.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.configuration.UserProcess;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.ProcessType;
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
    String icon = process.customFields().value("cssIcon");
    if (StringUtils.isEmpty(icon)) {
      icon = Process.DEFAULT_PROCESS_ICON;
    }
    userProcess.setIcon(icon);
    return userProcess;
  }
  
  public static UserProcess toUserProcess(DashboardProcess process) {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessId(process.getId());
    userProcess.setProcessType(process.getType());
    userProcess.setLink(process.getStartLink());
    userProcess.setDescription(process.getDescription());
    userProcess.setIcon(process.getIcon());
    return userProcess;
  }

  public static UserProcess toUserProcess(ExternalLink externalLink) {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessId(externalLink.getId().toString());
    userProcess.setProcessType(ProcessType.EXTERNAL_LINK);
    userProcess.setProcessName(stripHtmlTags(externalLink.getName()));
    userProcess.setLink(externalLink.getLink());
    userProcess.setDescription(externalLink.getDescription());
    userProcess.setIcon(externalLink.getIcon());
    return userProcess;
  }

  private static String stripHtmlTags(String text) {
    return text.replaceAll("\\<.*?>", "");
  }
}
