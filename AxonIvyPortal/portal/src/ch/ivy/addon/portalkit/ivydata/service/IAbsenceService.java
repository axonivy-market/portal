package ch.ivy.addon.portalkit.ivydata.service;

import java.util.Set;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;

public interface IAbsenceService {

  /**
   * Finds the absences of the given user. If the user is empty, finds the absences of all users.
   * @param username
   * @return IvyAbsenceResultDTO
   */
  IvyAbsenceResultDTO findAbsences(String username);
  
  /**
   * Creates the new absence of the given user
   * @param ivyAbsence
   */
  void createAbsence(IvyAbsence ivyAbsence);
  
  /**
   * Updates the existing absences (Deletes all of the absences then creates the new ones of the given user)
   * @param username
   * @param ivyAbsences
   */
  void updateAbsences(String username, Set<IvyAbsence> ivyAbsences);
  
  /**
   * Deletes the given absence
   * @param ivyAbsence
   */
  void deleteAbsence(IvyAbsence ivyAbsence);
}
