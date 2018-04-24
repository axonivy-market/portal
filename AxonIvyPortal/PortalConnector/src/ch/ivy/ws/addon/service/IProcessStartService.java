package ch.ivy.ws.addon.service;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.ProcessStartServiceResult;

/**
 * Process start service provides a set of service methods for available process starts
 */
public interface IProcessStartService {

  /**
   * Find all process starts by criteria
   * @param processSearchCriteria 
   * @param language 
   * @param isUrlBuiltFromSystemProperties 
   * @return ProcessStartServiceResult
   * @throws WSException 
   */
  public ProcessStartServiceResult findProcessStartsByCriteria(ProcessSearchCriteria processSearchCriteria,
      String language, Boolean isUrlBuiltFromSystemProperties) throws WSException;
}
