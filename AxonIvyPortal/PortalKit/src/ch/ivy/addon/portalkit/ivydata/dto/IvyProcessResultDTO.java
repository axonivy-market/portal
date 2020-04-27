package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivyteam.ivy.workflow.IProcessStart;

public class IvyProcessResultDTO extends AbstractResultDTO {

  private List<IProcessStart> processes;

  public List<IProcessStart> getProcesses() {
    return processes;
  }

  public void setProcesses(List<IProcessStart> processes) {
    this.processes = processes;
  }

}
