package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcessResultDTO {

  private List<IWebStartable> processes;
  private List<PortalIvyDataException> errors;

  public List<IWebStartable> getProcesses() {
    return processes;
  }

  public void setProcesses(List<IWebStartable> processes) {
    this.processes = processes;
  }

  public List<PortalIvyDataException> getErrors() {
    return errors;
  }

  public void setErrors(List<PortalIvyDataException> errors) {
    this.errors = errors;
  }

}
