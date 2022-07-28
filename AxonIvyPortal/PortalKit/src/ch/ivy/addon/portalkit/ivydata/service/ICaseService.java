package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;

public interface ICaseService {

  IvyCaseResultDTO findCasesByCriteria(CaseSearchCriteria criteria, int startIndex, int count);

  IvyCaseResultDTO findCasesByCriteria(CaseSearchCriteria criteria);

  IvyCaseResultDTO countCasesByCriteria(CaseSearchCriteria criteria);

  IvyCaseResultDTO findCategoriesByCriteria(CaseCategorySearchCriteria criteria);

  IvyCaseResultDTO analyzeCaseStateStatistic(CaseSearchCriteria criteria);

  IvyCaseResultDTO analyzeElapsedTimeByCaseCategory(CaseSearchCriteria criteria);

  IvyCaseResultDTO findValuesOfCustomString(CaseCustomFieldSearchCriteria criteria);

  IvyCaseResultDTO analyzeCaseCategoryStatistic(CaseSearchCriteria criteria);
  
  IvyCaseResultDTO analyzeCasesByCategoryStatistic(CaseSearchCriteria criteria, List<String> selectedCategories);
  
  IvyCaseResultDTO analyzeCasesByCategoryStatisticDrilldown(CaseSearchCriteria criteria, String selectedCategory);
}
