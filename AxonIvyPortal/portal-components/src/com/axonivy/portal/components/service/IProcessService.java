package com.axonivy.portal.components.service;

import com.axonivy.portal.components.dto.IvyProcessResultDTO;

@FunctionalInterface
public interface IProcessService {

  /**
   * Finds the processes that current login user can start
   * @return IvyProcessResultDTO
   */
  IvyProcessResultDTO findProcesses();
  
}
