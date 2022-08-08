package com.axonivy.portal.component.dto;

import java.util.List;

import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcessResultDTO {

  private List<IWebStartable> processes;

  public List<IWebStartable> getProcesses() {
    return processes;
  }

  public void setProcesses(List<IWebStartable> processes) {
    this.processes = processes;
  }

}
