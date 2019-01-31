package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.Map;
import java.util.Set;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;

public class IvyAbsenceResultDTO extends AbstractResultDTO {

  private Map<String, Set<IvyAbsence>> ivyAbsencesByUser;

  public Map<String, Set<IvyAbsence>> getIvyAbsencesByUser() {
    return ivyAbsencesByUser;
  }

  public void setIvyAbsencesByUser(Map<String, Set<IvyAbsence>> ivyAbsencesByUser) {
    this.ivyAbsencesByUser = ivyAbsencesByUser;
  }

}
