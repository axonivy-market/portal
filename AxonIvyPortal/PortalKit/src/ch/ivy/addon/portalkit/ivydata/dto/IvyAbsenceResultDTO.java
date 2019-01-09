package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;

public class IvyAbsenceResultDTO {

  private Map<String, Set<IvyAbsence>> ivyAbsencesByUser;
  private List<PortalIvyDataException> errors;

  public Map<String, Set<IvyAbsence>> getIvyAbsencesByUser() {
    return ivyAbsencesByUser;
  }

  public void setIvyAbsencesByUser(Map<String, Set<IvyAbsence>> ivyAbsencesByUser) {
    this.ivyAbsencesByUser = ivyAbsencesByUser;
  }

  public List<PortalIvyDataException> getErrors() {
    return errors;
  }

  public void setErrors(List<PortalIvyDataException> errors) {
    this.errors = errors;
  }

}
