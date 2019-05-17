package ch.ivy.addon.portalkit.bo;

import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcess extends Process{
  
  private IWebStartable process;
  
  public IvyProcess(IWebStartable process) {
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
  public IWebStartable getProcess() {
    return process;
  }

  @Override
  public ProcessType getType() {
    return ProcessType.IVY_PROCESS;
  }
  
  @Override
  public String getTypeName() {
    return ProcessType.IVY_PROCESS.getType();
  }
}
