package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO;

@FunctionalInterface
public interface IProcessService {

  /**
   * Finds the processes that current login user can start
   * @return IvyProcessResultDTO
   */
  IvyProcessResultDTO findProcesses();
  
}
