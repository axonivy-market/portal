package ch.ivy.ws.addon.service;

import java.util.List;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.types.IvySecurityMember;
import ch.ivy.ws.addon.types.IvyTask;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Task service provides a set of service methods for Ivy task instances
 * 
 * @author mde
 *
 */
public interface ITaskService {

  /**
   * Park task for user
   * @param username 
   * @param idenfier 
   * @param isUrlBuiltFromSystemProperties 
   * @return IvyTask
   * @throws WSException 
   */
  public IvyTask parkTask(String username, Integer idenfier, Boolean isUrlBuiltFromSystemProperties) throws WSException;

  /**
   * Delegate a task to a given security member
   * @param idenfier 
   * @param securityMember 
   * @param isUrlBuiltFromSystemProperties 
   * @return TaskServiceResult
   * @throws WSException 
   */
  public TaskServiceResult delegateTask(Integer idenfier, IvySecurityMember securityMember,
      Boolean isUrlBuiltFromSystemProperties) throws WSException;

  /**
   * Create note for task
   * @param username 
   * @param taskId 
   * @param message 
   * @return NoteServiceResult
   * @throws WSException
   */
  public NoteServiceResult createNote(String username, Integer taskId, String message) throws WSException;

  /**
   * Find all notes for a task
   * 
   * @param taskId
   * @return list of case notes
   * @throws WSException
   */
  public NoteServiceResult findNotes(Integer taskId) throws WSException;

  /**
   * Resets a task
   * @param currentUserName 
   * @param taskId 
   * @param isUrlBuiltFromSystemProperties 
   * @return TaskServiceResult
   * @throws WSException 
   */
  TaskServiceResult resetTask(String currentUserName, Integer taskId, Boolean isUrlBuiltFromSystemProperties)
      throws WSException;

  public TaskServiceResult findTasksByCriteria(TaskSearchCriteria taskSearchCriteria, Integer startIndex,
      Integer count, Boolean isUrlBuiltFromSystemProperties) throws WSException;

  /**
   * Check if the given user can resume the given task
   * @param taskId 
   * @param userName 
   * @return TaskServiceResult
   * @throws WSException 
   */
  public TaskServiceResult canUserResumeTask(Integer taskId, String userName) throws WSException;


  /**
   * Finds task by id
   * 
   * @param taskId
   * @param errors
   * @return ITask
   */
  public ITask findTask(final Integer taskId, List<WSException> errors);

  public void save(IvyTask task) throws WSException;

  public TaskServiceResult countTasksByCriteria(TaskSearchCriteria taskSearchCriteria) throws WSException;

  public TaskServiceResult findCategories(String jsonQuery, final String username, List<String> apps, String language) throws WSException;

  TaskServiceResult findPersonalTaskCategories(String jsonQuery, String username, List<String> apps, String language) throws WSException;

  TaskServiceResult findGroupTaskCategories(String jsonQuery, String username, List<String> apps, String language) throws WSException;
  
  TaskServiceResult findUnassignedTaskCategories(String jsonQuery, List<String> apps, String language) throws WSException;

  void setAdditionalProperty(Long taskId, String name, String value) throws WSException;

  /**
   * Get statistic data by task priority
   * 
   * @param jsonQuery
   * @param username
   * @param apps
   * @return statistic data
   * @throws WSException
   */
  public TaskServiceResult analyzePriorityStatistic(String jsonQuery, final String username, List<String> apps) throws WSException;

  /**
   * Get statistic data by task expiry date
   * 
   * @param jsonQuery
   * @param username
   * @param apps
   * @return statistic data
   * @throws WSException
   */
  public TaskServiceResult analyzeExpiryStatistic(String jsonQuery, final String username, List<String> apps) throws WSException;

  /**
   * Get statistic data about elapsed time of done tasks
   * 
   * @param jsonQuery
   * @param apps
   * @return statistic data
   * @throws WSException
   */
  public TaskServiceResult analyzeElapsedTimeOfTasks(String jsonQuery, List<String> apps) throws WSException;
}
