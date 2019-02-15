package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;
import java.util.Set;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;

public interface IAbsenceService {

  /**
   * Finds the absences of the given user. If the user is empty, finds the absences of all users.
   * @param username
   * @param apps
   * @return IvyAbsenceResultDTO
   */
  IvyAbsenceResultDTO findAbsences(String username, List<String> apps);
  
  /**
   * Creates the new absence of the given user
   * @param ivyAbsence
   * @param apps
   * @return IvyAbsenceResultDTO
   */
  IvyAbsenceResultDTO createAbsence(IvyAbsence ivyAbsence, List<String> apps);
  
  /**
   * Updates the existing absences (Deletes all of the absences then creates the new ones of the given user)
   * @param username
   * @param ivyAbsences
   * @param apps
   * @return IvyAbsenceResultDTO
   */
  IvyAbsenceResultDTO updateAbsences(String username, Set<IvyAbsence> ivyAbsences, List<String> apps);
  
  /**
   * Deletes the given absence
   * @param ivyAbsence
   * @param apps
   * @return IvyAbsenceResultDTO
   */
  IvyAbsenceResultDTO deleteAbsence(IvyAbsence ivyAbsence, List<String> apps);
}
