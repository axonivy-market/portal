package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;

public interface ITaskService {

  IvyTaskResultDTO findTasksByCriteria(TaskSearchCriteria criteria, int startIndex, int count) throws Exception;
  
  IvyTaskResultDTO countTasksByCriteria(TaskSearchCriteria criteria) throws Exception;
  
  IvyTaskResultDTO findCategoriesByCriteria(TaskCategorySearchCriteria criteria) throws Exception;
  
  IvyTaskResultDTO analyzePriorityStatistic(TaskSearchCriteria criteria) throws Exception;
  
  IvyTaskResultDTO analyzeExpiryStatistic(TaskSearchCriteria criteria) throws Exception;
  
  IvyTaskResultDTO analyzeElapsedTimeOfTasks(TaskSearchCriteria criteria) throws Exception;
}
