package ch.ivy.addon.portalkit.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class UserProcessMapper {
  
  private UserProcessMapper() {
  }

  public static List<UserProcess> toUserProcesses(List<IWebStartable> webStartables) {
    return webStartables.stream().map(UserProcessMapper::toUserProcess).collect(Collectors.toList());
  }
  
  public static UserProcess toUserProcess(IWebStartable webStartable) {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessName(webStartable.getDisplayName());
    userProcess.setDescription(webStartable.getDescription());
    userProcess.setLink(webStartable.getLink().getRelativeEncoded());
    return userProcess;
  }
}
