package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;
import java.util.Map;

import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;

public class IvySubstituteResultDTO extends AbstractResultDTO {

  private Map<IvyApplication, List<IvySubstitute>> ivySubstitutesByApp;

  public Map<IvyApplication, List<IvySubstitute>> getIvySubstitutesByApp() {
    return ivySubstitutesByApp;
  }

  public void setIvySubstitutesByApp(Map<IvyApplication, List<IvySubstitute>> ivySubstitutesByApp) {
    this.ivySubstitutesByApp = ivySubstitutesByApp;
  }

}
