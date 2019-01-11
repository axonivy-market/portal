package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomVarCharSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;

public interface ICaseService {

  IvyCaseResultDTO findCasesByCriteria(CaseSearchCriteria criteria, int startIndex, int count) throws Exception;
  
  IvyCaseResultDTO countCasesByCriteria(CaseSearchCriteria criteria) throws Exception;
  
  IvyCaseResultDTO findCategoriesByCriteria(CaseCategorySearchCriteria criteria) throws Exception;
  
  IvyCaseResultDTO analyzeCaseStateStatistic(CaseSearchCriteria criteria) throws Exception;
  
  IvyCaseResultDTO analyzeElapsedTimeByCaseCategory(CaseSearchCriteria criteria) throws Exception;
  
  IvyCaseResultDTO findValuesOfCustomVarChar(CaseCustomVarCharSearchCriteria criteria);
}
