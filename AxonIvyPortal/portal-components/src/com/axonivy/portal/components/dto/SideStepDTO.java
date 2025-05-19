package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.List;

public class SideStepDTO implements Serializable {
  private static final long serialVersionUID = -1404169898838544342L;

  private List<SideStepProcessDTO> processes;
  private Boolean parallel;

  public List<SideStepProcessDTO> getProcesses() {
    return processes;
  }

  public void setProcesses(List<SideStepProcessDTO> processes) {
    this.processes = processes;
  }

  public Boolean getParallel() {
    return parallel;
  }

  public void setParallel(Boolean parallel) {
    this.parallel = parallel;
  }

}
