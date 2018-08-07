package ch.ivy.ws.addon.service;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.WebStartableServiceResult;

/**
 * Web startable service provides a set of service methods for available process starts and case map
 * starts
 */
public interface IWebStartableService {

  /**
   * Find all web starts by criteria
   */
  public WebStartableServiceResult findWebStartablesByCriteria(WebStartableSearchCriteria webStartableSearchCriteria,
      String language, Boolean isUrlBuiltFromSystemProperties) throws WSException;
}
