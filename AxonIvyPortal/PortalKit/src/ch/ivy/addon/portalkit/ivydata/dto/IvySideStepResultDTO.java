package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvySideStep;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;

public class IvySideStepResultDTO {

  private List<IvySideStep> sideSteps;
  private List<PortalIvyDataException> errors;

  public List<IvySideStep> getSideSteps() {
    return sideSteps;
  }

  public void setSideSteps(List<IvySideStep> sideSteps) {
    this.sideSteps = sideSteps;
  }

  public List<PortalIvyDataException> getErrors() {
    return errors;
  }

  public void setErrors(List<PortalIvyDataException> errors) {
    this.errors = errors;
  }

}
