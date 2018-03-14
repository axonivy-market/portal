package ch.ivy.ws.addon.service;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.SideStepServiceResult;

/**
 * Side step service provides a set of service methods for side step processes
 */
public interface ISideStepService {

  /**
   * Find all side step processes by the criteria
   * @param sideStepSearchCriteria 
   * @param language 
   * @param isUrlBuiltFromSystemProperties 
   * @return SideStepServiceResult
   * @throws WSException 
   */
  public SideStepServiceResult findSideStepsByCriteria(SideStepSearchCriteria sideStepSearchCriteria, String language,
      Boolean isUrlBuiltFromSystemProperties) throws WSException;
}
