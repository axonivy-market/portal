package ch.ivy.addon.portalkit.ivydata.dto;

import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcessStartDTO extends AbstractResultDTO {
  
  public IvyProcessStartDTO() {}

  private IWebStartable startableProcessStart;

  public IWebStartable getStartableProcessStart() {
    return startableProcessStart;
  }

  public void setStartableProcessStart(IWebStartable startableProcessStart) {
    this.startableProcessStart = startableProcessStart;
  }
}
