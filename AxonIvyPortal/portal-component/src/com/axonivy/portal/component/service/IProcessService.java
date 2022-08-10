package com.axonivy.portal.component.service;

import com.axonivy.portal.component.dto.IvyProcessResultDTO;

@FunctionalInterface
public interface IProcessService {

  /**
   * Finds the processes that current login user can start
   * @return IvyProcessResultDTO
   */
  IvyProcessResultDTO findProcesses();
  
}
