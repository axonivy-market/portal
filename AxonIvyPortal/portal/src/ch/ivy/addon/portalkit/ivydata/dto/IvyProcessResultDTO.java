package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcessResultDTO extends AbstractResultDTO {

  private List<IWebStartable> processes;

  public List<IWebStartable> getProcesses() {
    return processes;
  }

  public void setProcesses(List<IWebStartable> processes) {
    this.processes = processes;
  }

}
