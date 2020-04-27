package ch.ivy.addon.portalkit.bo;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivyteam.ivy.workflow.IProcessStart;

public class IvyProcess implements Process {
  
  private IProcessStart process;
  
  public IvyProcess(IProcessStart process) {
    this.process = process;
  }

  @Override
  public String getStartLink() {
    return process.getLink().getRelativeEncoded();
  }

  @Override
  public String getDescription() {
    return process.getDescription();
  }

  @Override
  public String getName() {
    return process.getName();
  }

  @Override
  public IProcessStart getProcess() {
    return process;
  }

  @Override
  public ProcessType getType() {
    return ProcessType.IVY_PROCESS;
  }
  
  @Override
  public String getTypeName() {
    return ProcessType.IVY_PROCESS.name();
  }

  @Override
  public String getId() {
    return StringUtils.EMPTY;
  }
}
