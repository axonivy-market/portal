package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;
import java.util.Map;

import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;

public class IvySubstituteResultDTO {

  private Map<IvyApplication, List<IvySubstitute>> ivySubstitutesByApp;
  private List<PortalIvyDataException> errors;

  public Map<IvyApplication, List<IvySubstitute>> getIvySubstitutesByApp() {
    return ivySubstitutesByApp;
  }

  public void setIvySubstitutesByApp(Map<IvyApplication, List<IvySubstitute>> ivySubstitutesByApp) {
    this.ivySubstitutesByApp = ivySubstitutesByApp;
  }

  public List<PortalIvyDataException> getErrors() {
    return errors;
  }

  public void setErrors(List<PortalIvyDataException> errors) {
    this.errors = errors;
  }

}
