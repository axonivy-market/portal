package ch.ivy.addon.portalkit.ivydata.dto;

import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcessStartDTO {

  private IWebStartable startableProcessStart;

  public IvyProcessStartDTO() {}

  public IvyProcessStartDTO(IWebStartable startableProcessStart) {
    super();
    this.startableProcessStart = startableProcessStart;
  }

  public IWebStartable getStartableProcessStart() {
    return startableProcessStart;
  }

  public void setStartableProcessStart(IWebStartable startableProcessStart) {
    this.startableProcessStart = startableProcessStart;
  }
}
