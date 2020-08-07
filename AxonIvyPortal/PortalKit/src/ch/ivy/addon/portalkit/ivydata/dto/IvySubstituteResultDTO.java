package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;

public class IvySubstituteResultDTO extends AbstractResultDTO {

  private List<IvySubstitute> ivySubstitutes;
//  private Map<IvyApplication, List<IvySubstitute>> ivySubstitutesByApp;
//
//  public Map<IvyApplication, List<IvySubstitute>> getIvySubstitutesByApp() {
//    return ivySubstitutesByApp;
//  }
//
//  public void setIvySubstitutesByApp(Map<IvyApplication, List<IvySubstitute>> ivySubstitutesByApp) {
//    this.ivySubstitutesByApp = ivySubstitutesByApp;
//  }

  public List<IvySubstitute> getIvySubstitutes() {
    return ivySubstitutes;
  }

  public void setIvySubstitutes(List<IvySubstitute> ivySubstitutes) {
    this.ivySubstitutes = ivySubstitutes;
  }

}
