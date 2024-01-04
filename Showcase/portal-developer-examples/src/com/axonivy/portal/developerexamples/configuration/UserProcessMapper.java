package com.axonivy.portal.developerexamples.configuration;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.ProcessType;
import com.axonivy.portal.developerexamples.dto.UserProcess;

import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class UserProcessMapper {
  public static final String DEFAULT_PROCESS_ICON = "si si-hierarchy-6 si-rotate-270";
  private UserProcessMapper() {}

  public static List<UserProcess> toUserProcesses(List<IWebStartable> processes) {
    return processes.stream().map(UserProcessMapper::toUserProcess).collect(Collectors.toList());
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
      icon = DEFAULT_PROCESS_ICON;
    }
    userProcess.setIcon(icon);
    return userProcess;
  }

  private static String stripHtmlTags(String text) {
    return text.replaceAll("\\<.*?>", "");
  }
}
