package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvySideStep;

public class IvySideStepResultDTO {

  private List<IvySideStep> sideSteps;

  public List<IvySideStep> getSideSteps() {
    return sideSteps;
  }

  public void setSideSteps(List<IvySideStep> sideSteps) {
    this.sideSteps = sideSteps;
  }

}
