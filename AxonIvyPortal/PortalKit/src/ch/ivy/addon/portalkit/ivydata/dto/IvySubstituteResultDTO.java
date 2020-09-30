package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;

public class IvySubstituteResultDTO extends AbstractResultDTO {

  private List<IvySubstitute> ivySubstitutes;

  public List<IvySubstitute> getIvySubstitutes() {
    return ivySubstitutes;
  }

  public void setIvySubstitutes(List<IvySubstitute> ivySubstitutes) {
    this.ivySubstitutes = ivySubstitutes;
  }

}
