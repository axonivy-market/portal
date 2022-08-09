package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;

public interface ITaskService {

  IvyTaskResultDTO findTasksByCriteria(TaskSearchCriteria criteria, int startIndex, int count);
  
  IvyTaskResultDTO countTasksByCriteria(TaskSearchCriteria criteria);
  
  IvyTaskResultDTO findCategoriesByCriteria(TaskCategorySearchCriteria criteria);
  
  IvyTaskResultDTO analyzePriorityStatistic(TaskSearchCriteria criteria);
  
  IvyTaskResultDTO analyzeExpiryStatistic(TaskSearchCriteria criteria);

  IvyTaskResultDTO analyzeTaskStateStatistic(TaskSearchCriteria criteria);
  
  IvyTaskResultDTO analyzeTaskCategoryStatistic(TaskSearchCriteria criteria);
}
