package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.ProcessSearchCriteria;

@FunctionalInterface
public interface IProcessService {

  /**
   * Finds the processes which the given user can start
   * @param criteria
   * @return IvyProcessResultDTO
   */
  IvyProcessResultDTO findProcesses(ProcessSearchCriteria criteria);
  
}
