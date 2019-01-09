package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.ProcessSearchCriteria;

public interface IProcessService {

  /**
   * Finds the processes which the given user can start
   * @param criteria
   * @return IvyProcessResultDTO
   * @throws Exception
   */
  IvyProcessResultDTO findProcesses(ProcessSearchCriteria criteria) throws Exception;
  
}
