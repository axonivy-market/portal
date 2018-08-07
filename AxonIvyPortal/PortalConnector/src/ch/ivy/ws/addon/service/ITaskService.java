package ch.ivy.ws.addon.service;

import java.util.Date;
import java.util.List;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.types.IvySecurityMember;
import ch.ivy.ws.addon.types.IvyTask;
import ch.ivy.ws.addon.types.NumberOfExpiryTasks;
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
   */
  public IvyTask parkTask(String username, Integer idenfier, Boolean isUrlBuiltFromSystemProperties) throws WSException;

  /**
   * Delegate a task to a given security member
   */
  public TaskServiceResult delegateTask(Integer idenfier, IvySecurityMember securityMember,
      Boolean isUrlBuiltFromSystemProperties) throws WSException;

  /**
   * Create note for task
   * 
   * @param note
   * @return
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
   */
  TaskServiceResult resetTask(String currentUserName, Integer taskId, Boolean isUrlBuiltFromSystemProperties)
      throws WSException;

  public TaskServiceResult findTasksByCriteria(TaskSearchCriteria taskSearchCriteria, Integer startIndex,
      Integer count, Boolean isUrlBuiltFromSystemProperties) throws WSException;

  /**
   * Check if the given user can resume the given task
   */
  public TaskServiceResult canUserResumeTask(Integer taskId, String userName) throws WSException;


  /**
   * Finds task by id
   * 
   * @param taskId
   * @param errors
   * @return
   */
  public ITask findTask(final Integer taskId, List<WSException> errors);

  public void save(IvyTask task) throws WSException;

  public TaskServiceResult countTasksByCriteria(TaskSearchCriteria taskSearchCriteria) throws WSException;

  public TaskServiceResult findCategories(String jsonQuery, final String username, List<String> apps, String language) throws WSException;

  public TaskServiceResult analyzePriorityStatistic(String jsonQuery, final String username, List<String> apps) throws WSException;

  public NumberOfExpiryTasks countExpiryTasksByDate(String jsonQuery, final String username, List<String> apps, Date date)
      throws WSException;

  TaskServiceResult findPersonalTaskCategories(String jsonQuery, String username, List<String> apps, String language) throws WSException;

  TaskServiceResult findGroupTaskCategories(String jsonQuery, String username, List<String> apps, String language) throws WSException;


}
