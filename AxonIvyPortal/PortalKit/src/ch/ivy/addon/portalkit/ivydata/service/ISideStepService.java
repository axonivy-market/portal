package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.dto.IvySideStepResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria;

public interface ISideStepService {

  IvySideStepResultDTO findSideStepsByCriteria(SideStepSearchCriteria criteria) throws Exception;
}
