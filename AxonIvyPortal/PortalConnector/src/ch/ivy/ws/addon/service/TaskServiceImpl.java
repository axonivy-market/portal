package ch.ivy.ws.addon.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;

import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.AvailableAppsResult;
import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.transformer.IvyNoteTransformer;
import ch.ivy.ws.addon.transformer.IvyTaskTransformer;
import ch.ivy.ws.addon.types.ExpiryStatistic;
import ch.ivy.ws.addon.types.IvySecurityMember;
import ch.ivy.ws.addon.types.IvyTask;
import ch.ivy.ws.addon.types.PriorityStatistic;
import ch.ivy.ws.addon.util.SessionUtil;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskServiceImpl extends AbstractService implements ITaskService {

  @Override
  public NoteServiceResult createNote(final String username, final Integer taskId, final String message)
      throws WSException {
    try {
      return securityManager().executeAsSystem(new Callable<NoteServiceResult>() {
        @Override
        public NoteServiceResult call() throws Exception {

          NoteServiceResult result = new NoteServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          if (username == null || username.trim().isEmpty()) {
            // No username given
            errors.add(new WSException(WSErrorType.WARNING, 10044, null, null, null));
          } else {

            IvyNoteTransformer noteTransformer = new IvyNoteTransformer();
            TaskQuery query = TaskQuery.create().where().taskId().isEqual(taskId);

            ITask t = null;
            IWorkflowSession session = null;
            try {
              t = executeTaskQuery(query, 0, -1).get(0);
              session = findUserWorkflowSession(username, t.getApplication());
            } catch (Exception e) {
              // Wrong combination of taskId and username
              List<Object> userText = new ArrayList<Object>();
              userText.add(taskId);
              userText.add(username);
              errors.add(new WSException(WSErrorType.WARNING, 10031, e, userText, null));
            }

            if (session != null) {
              INote note = t.createNote(session, message);
              result.setNewNote(noteTransformer.transform(note));
            }
          }

          result.setErrors(errors);

          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10012, e);
    }
  }

  @Override
  public TaskServiceResult delegateTask(final Integer taskId, final IvySecurityMember securityMember, String serverUrl)
      throws WSException {
    try {
      return securityManager().executeAsSystem(new Callable<TaskServiceResult>() {
        @Override
        public TaskServiceResult call() throws Exception {
          TaskServiceResult result = new TaskServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          if (taskId == null) {
            // TaskId not given
            errors.add(new WSException(WSErrorType.WARNING, 10027, null, null));
          } else if (securityMember == null) {
            // Wrong securityMember
            List<Object> userText = new ArrayList<Object>();
            userText.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10028, userText, null));
          } else {

            TaskQuery query = TaskQuery.create().where().taskId().isEqual(taskId);
            ITask t = null;
            ISecurityMember member = null;
            try {
              t = executeTaskQuery(query, 0, -1).get(0);
              member = t.getApplication().getSecurityContext().findSecurityMember(securityMember.getMemberName());
            } catch (Exception e) {
              // Wrong securityMember
              List<Object> userText = new ArrayList<Object>();
              userText.add(taskId);
              errors.add(new WSException(WSErrorType.WARNING, 10028, e, userText, null));
            }
            t.setActivator(member);
            IvyTaskTransformer transformer = new IvyTaskTransformer(serverUrl);
            result.setTask(transformer.transform(t));
          }

          result.setErrors(errors);
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10014, e);
    }
  }

  @Override
  public NoteServiceResult findNotes(final Integer taskId) throws WSException {
    try {
      return securityManager().executeAsSystem(new Callable<NoteServiceResult>() {
        @Override
        public NoteServiceResult call() throws Exception {
          NoteServiceResult result = new NoteServiceResult();
          IvyNoteTransformer noteTransformer = new IvyNoteTransformer();
          List<WSException> errors = ch.ivyteam.ivy.scripting.objects.List.create(WSException.class);
          ITask task = findTask(taskId, errors);

          if (task != null && task.getNotes() != null) {
            result.setNotes(noteTransformer.transform(task.getNotes()));
          }

          result.setErrors(errors);
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10013, e);
    }
  }

  @Override
  public IvyTask parkTask(final String username, final Integer taskId, String serverUrl) throws WSException {
    try {
      return securityManager().executeAsSystem(new Callable<IvyTask>() {
        @Override
        public IvyTask call() throws Exception {
          IvyTask result = null;
          List<WSException> errors = new ArrayList<WSException>();

          if (taskId != null) {
            TaskQuery query = TaskQuery.create().where().taskId().isEqual(taskId);
            ITask t = Ivy.wf().getGlobalContext().getTaskQueryExecutor().getResults(query).get(0);
            if (t != null) {
              try {
                IWorkflowSession workflowSession = findUserWorkflowSession(username, t.getApplication());
                if (workflowSession != null) {
                  workflowSession.parkTask(t);
                  IvyTaskTransformer transformer = new IvyTaskTransformer(serverUrl);
                  result = transformer.transform(t);
                }
              } catch (Exception e) {
                // Invalid username
                List<Object> userText = new ArrayList<Object>();
                userText.add(username);
                errors.add(new WSException(WSErrorType.WARNING, 10029, e, userText, null));
              }
            } else {
              // Invalid taskId
              List<Object> userText = new ArrayList<Object>();
              userText.add(taskId);
              errors.add(new WSException(WSErrorType.WARNING, 10027, userText, null));
            }
          } else {
            // No taskId given
            List<Object> userText = new ArrayList<Object>();
            userText.add("");
            errors.add(new WSException(WSErrorType.WARNING, 10027, userText, null));
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10017, e);
    }
  }

  @Override
  public TaskServiceResult resetTask(final Integer taskId, String serverUrl) throws WSException {
    try {
      return securityManager().executeAsSystem(new Callable<TaskServiceResult>() {
        @Override
        public TaskServiceResult call() throws Exception {
          TaskServiceResult result = new TaskServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          ITask task = findTask(taskId, errors);

          if (task != null) {
            task.reset();
            IvyTaskTransformer transformer = new IvyTaskTransformer(serverUrl);
            result.setTask(transformer.transform(task));
          }

          result.setErrors(errors);
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10014, e);
    }
  }

  @Override
  public TaskServiceResult findTasksByCriteria(TaskSearchCriteria taskSearchCriteria, final Long serverId,
      String serverUrl, Integer startIndex, Integer count) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(
          () -> {
            if (taskSearchCriteria.isEmpty()) {
              return result(noErrors());
            }

            TaskQuery taskQuery = createTaskQuery(taskSearchCriteria);
            List<ITask> tasks = executeTaskQuery(taskQuery, startIndex, count);
            List<IvyTask> ivyTasks = new ArrayList<>();
            List<IvyTask> allIvyTasks = new ArrayList<>();
            IvyTaskTransformer transformer = new IvyTaskTransformer(serverUrl);
            tasks.forEach(task -> {
              IvyTask ivyTask = transformer.transform(task);
              ivyTask.setServerId(serverId);

              if (taskSearchCriteria.hasInvolvedUsername()) {
                boolean canUserResumeTask = canUserResumeTask(taskSearchCriteria.getInvolvedUsername(), task);
                ivyTask.setCanReset(hasPermissionToResetTask(task, taskSearchCriteria.getInvolvedUsername(),
                    canUserResumeTask));
                ivyTask.setCanDelegate(hasPermissionToDelegateTask(task, taskSearchCriteria.getInvolvedUsername()));
                ivyTask.setCanResume(canUserResumeTask);
                
                if (taskSearchCriteria.hasTaskId()) {
                  ivyTasks.add(ivyTask);
                } else {
                  if (canUserResumeTask) {
                    ivyTasks.add(ivyTask);
                  }
                }

                if (taskSearchCriteria.isIgnoreInvolvedUser()) {
                  allIvyTasks.add(ivyTask);
                }
              } else {
                ivyTasks.add(ivyTask);
              }

            });

            return result(ivyTasks, allIvyTasks, errors);
          });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @Override
  public TaskServiceResult countTasksByCriteria(TaskSearchCriteria taskSearchCriteria) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(() -> {
        if (taskSearchCriteria.isEmpty()) {
          return result(0, errors);
        }

        TaskQuery taskQuery = createTaskQuery(taskSearchCriteria);
        long taskCount = countTasks(taskQuery);
        return result(taskCount, errors);

      });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @Override
  public TaskServiceResult findCategories(final String username, List<String> apps) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(
          () -> {
            TaskQuery taskQuery = TaskQuery.create();
            if (username != null && !StringUtils.isEmpty(username)) {
              AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
              taskQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()));
            }
            taskQuery.where().and(queryForStates(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE)));
            taskQuery.where().and().customVarCharField5().isNotNull().groupBy().customVarCharField5();
            Recordset recordSet = Ivy.wf().getGlobalContext().getTaskQueryExecutor().getRecordset(taskQuery);
            List<String> categories = new ArrayList<>();
            String customVarCharField5Column = recordSet.getKeys().get(0);
            recordSet.getRecords().forEach(
                record -> categories.add(record.getField(customVarCharField5Column).toString()));
            return result(categories, errors);
          });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @Override
  public TaskServiceResult analyzePriorityStatistic(final String username, List<String> apps) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(() -> {
        TaskQuery lowPriorityQuery = TaskQuery.create();
        TaskQuery normalPriorityQuery = TaskQuery.create();
        TaskQuery highPriorityQuery = TaskQuery.create();
        TaskQuery exceptionPriorityQuery = TaskQuery.create();
        if (username != null && !StringUtils.isEmpty(username)) {
          AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
          lowPriorityQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()));
          normalPriorityQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()));
          highPriorityQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()));
          exceptionPriorityQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()));
        }
        List<TaskState> states = Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED);
        lowPriorityQuery.where().and(queryForStates(states)).and(queryForPriority(WorkflowPriority.LOW));
        normalPriorityQuery.where().and(queryForStates(states)).and(queryForPriority(WorkflowPriority.NORMAL));
        highPriorityQuery.where().and(queryForStates(states)).and(queryForPriority(WorkflowPriority.HIGH));
        exceptionPriorityQuery.where().and(queryForStates(states)).and(queryForPriority(WorkflowPriority.EXCEPTION));

        PriorityStatistic priorityStatistic = new PriorityStatistic();
        priorityStatistic.setLow(countTasks(lowPriorityQuery));
        priorityStatistic.setNormal(countTasks(normalPriorityQuery));
        priorityStatistic.setHigh(countTasks(highPriorityQuery));
        priorityStatistic.setException(countTasks(exceptionPriorityQuery));
        return result(priorityStatistic, errors);
      });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @Override
  public TaskServiceResult analyzeExpiryStatistic(final String username, List<String> apps) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(() -> {
        TaskQuery todayQuery = TaskQuery.create();
        TaskQuery tomorrowQuery = TaskQuery.create();
        TaskQuery in2DaysQuery = TaskQuery.create();
        TaskQuery in3DaysQuery = TaskQuery.create();
        if (username != null && !StringUtils.isEmpty(username)) {
          AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
          todayQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()));
          tomorrowQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()));
          in2DaysQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()));
          in3DaysQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()));
        }
        LocalDate now = LocalDate.now();

        Date today = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date tomorrow = Date.from(now.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date in2Days = Date.from(now.plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date in3Days = Date.from(now.plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date in4Days = Date.from(now.plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<TaskState> states = Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED);
        todayQuery.where().and(queryForStates(states)).and(queryForExpiry(today, tomorrow));
        tomorrowQuery.where().and(queryForStates(states)).and(queryForExpiry(tomorrow, tomorrow));
        in2DaysQuery.where().and(queryForStates(states)).and(queryForExpiry(in2Days, in3Days));
        in3DaysQuery.where().and(queryForStates(states)).and(queryForExpiry(in3Days, in4Days));

        ExpiryStatistic expiryStatistic = new ExpiryStatistic();
        expiryStatistic.setToday(countTasks(todayQuery));
        expiryStatistic.setTomorrow(countTasks(tomorrowQuery));
        expiryStatistic.setIn2Days(countTasks(in2DaysQuery));
        expiryStatistic.setIn3Days(countTasks(in3DaysQuery));
        return result(expiryStatistic, errors);
      });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  private ISecurityManager securityManager() {
    return ServerFactory.getServer().getSecurityManager();
  }

  private TaskQuery createTaskQuery(TaskSearchCriteria taskSearchCriteria) throws Exception {
    boolean includedTaskId = false;
    TaskQuery finalQuery = TaskQuery.create();

    if (taskSearchCriteria.hasCaseId()) {
      finalQuery.where().and().caseId().isEqual(taskSearchCriteria.getCaseId());
      includedTaskId = true;
    }

    if (taskSearchCriteria.hasKeyword()) {
      finalQuery.where().and(queryForKeyword(taskSearchCriteria.getKeyword()));
      includedTaskId = true;
    }

    if (taskSearchCriteria.hasTaskId() && !includedTaskId) {
      finalQuery.where().and(queryForTaskId(taskSearchCriteria.getTaskId()));
    }

    if (taskSearchCriteria.hasInvolvedUsername() && !taskSearchCriteria.isIgnoreInvolvedUser()) {
      List<String> involvedApplications = taskSearchCriteria.getInvolvedApplications();
      String involvedUsername = taskSearchCriteria.getInvolvedUsername();

      AvailableAppsResult availableAppsResult =
          findAvailableApplicationsAndUsers(involvedApplications, involvedUsername);
      finalQuery.where().and(queryForInvolvedUsers(availableAppsResult.getUsers()));
    }

    if (taskSearchCriteria.hasExcludedStates()) {
      finalQuery.where().and(queryForExcludedStates(taskSearchCriteria.getExcludedStates()));
    }

    if (taskSearchCriteria.hasIncludedStates()) {
      finalQuery.where().and(queryForStates(taskSearchCriteria.getIncludedStates()));
    }

    if (taskSearchCriteria.hasCategory()) {
      finalQuery.where().and(queryForCategory(taskSearchCriteria.getCategory()));
    }

    if (taskSearchCriteria.isSortByPriority()) {
      if (taskSearchCriteria.isSortDescending()) {
        finalQuery.orderBy().priority().descending();
      } else {
        finalQuery.orderBy().priority();
      }
    } else if (taskSearchCriteria.isSortByExpiryDate()) {
      if (taskSearchCriteria.isSortDescending()) {
        finalQuery.orderBy().expiryTimestamp().descending();
      } else {
        finalQuery.orderBy().expiryTimestamp();
      }
    } else if (taskSearchCriteria.isSortByName()) {
      if (taskSearchCriteria.isSortDescending()) {
        finalQuery.orderBy().name().descending();
      } else {
        finalQuery.orderBy().name();
      }
    }

    return finalQuery;
  }

  @Override
  public TaskServiceResult canUserResumeTask(Integer taskId, String userName) throws WSException {
    try {
      return securityManager().executeAsSystem(new Callable<TaskServiceResult>() {
        @Override
        public TaskServiceResult call() throws Exception {
          TaskServiceResult result = new TaskServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          ITask task = findTask(taskId, errors);

          if (task != null) {
            boolean canUserResumeTask = canUserResumeTask(userName, task);
            result.setCanUserResumeTask(canUserResumeTask);
          }

          IUser workerUser = task.getWorkerUser();
          if (workerUser != null) {
            String fullName = workerUser.getFullName();
            String workerName =
                fullName == null || fullName.isEmpty() ? workerUser.getName() : workerUser.getFullName() + "("
                    + workerUser.getName() + ")";
            result.setWorkerUserName(workerName);
          }
          result.setErrors(errors);
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10014, e);
    }
  }

  @Override
  public ITask findTask(final Integer taskId, List<WSException> errors) {
    if (taskId == null) {
      List<Object> userText = new ArrayList<Object>();
      userText.add(taskId);
      errors.add(new WSException(WSErrorType.WARNING, 10028, userText, null));
      return null;
    }

    try {
      TaskQuery query = TaskQuery.create().where().taskId().isEqual(taskId);
      ITask task = executeTaskQuery(query, 0, -1).get(0);
      return task;
    } catch (Exception e) {
      // Wrong TaskId
      List<Object> userText = new ArrayList<Object>();
      userText.add(taskId);
      errors.add(new WSException(WSErrorType.WARNING, 10027, e, userText, null));
      return null;
    }
  }

  private boolean hasPermissionToResetTask(ITask task, String username, boolean canUserResumeTask) {
    TaskState taskState = task.getState();
    if (!(TaskState.SUSPENDED.equals(taskState) || TaskState.RESUMED.equals(taskState) || TaskState.PARKED
        .equals(taskState))) {
      return false;
    }

    return (SessionUtil
        .doesUserHavePermission(task.getApplication(), username, IPermission.TASK_RESET_OWN_WORKING_TASK) && canUserResumeTask)
        || SessionUtil.doesUserHavePermission(task.getApplication(), username, IPermission.TASK_RESET);
  }

  private boolean hasPermissionToDelegateTask(ITask task, String username) {
    TaskState taskState = task.getState();
    if (!(TaskState.SUSPENDED.equals(taskState) || TaskState.RESUMED.equals(taskState) || TaskState.PARKED
        .equals(taskState))) {
      return false;
    }

    return SessionUtil.doesUserHavePermission(task.getApplication(), username, IPermission.TASK_WRITE_ACTIVATOR);
  }

  private boolean canUserResumeTask(String userName, ITask task) {
    if (TaskState.SUSPENDED.equals(task.getState()) || TaskState.RESUMED.equals(task.getState())
        || TaskState.PARKED.equals(task.getState())) {
      IUser user = task.getApplication().getSecurityContext().findUser(userName);
      if (TaskState.RESUMED.equals(task.getState())) {
        return user.equals(task.getWorkerUser());
      }
      return task.getActivatorUserCandidates().contains(user);
    }
    return false;
  }

  private TaskServiceResult result(List<WSException> errors) {
    TaskServiceResult result = new TaskServiceResult();
    result.setErrors(errors);
    return result;
  }

  private TaskServiceResult result(List<IvyTask> ivyTasks, List<IvyTask> allIvyTasks, List<WSException> errors) {
    TaskServiceResult result = new TaskServiceResult();
    result.setTasks(ivyTasks);
    result.setAllTasks(allIvyTasks);
    result.setErrors(errors);
    return result;
  }

  private TaskServiceResult result(long taskCount, List<WSException> errors) {
    TaskServiceResult result = new TaskServiceResult();
    result.setTaskCount(taskCount);;
    result.setErrors(errors);
    return result;
  }

  private TaskServiceResult result(List<String> categories, List<WSException> errors) {
    TaskServiceResult result = new TaskServiceResult();
    result.setCategories(categories);
    result.setErrors(errors);
    return result;
  }

  private TaskServiceResult result(PriorityStatistic priorityStatistic, List<WSException> errors) {
    TaskServiceResult result = new TaskServiceResult();
    result.setPriorityStatistic(priorityStatistic);
    result.setErrors(errors);
    return result;
  }

  private TaskServiceResult result(ExpiryStatistic expiryStatistic, List<WSException> errors) {
    TaskServiceResult result = new TaskServiceResult();
    result.setExpiryStatistic(expiryStatistic);
    result.setErrors(errors);
    return result;
  }

  private List<WSException> noErrors() {
    return Collections.emptyList();
  }

  private TaskQuery queryForInvolvedUsers(List<IUser> users) {
    TaskQuery taskQuery = TaskQuery.create();
    users.forEach(user -> taskQuery.where().or().isInvolved(user));
    return taskQuery;
  }
  
  private TaskQuery queryForCanWorkOnUsers(List<IUser> users) {
    TaskQuery taskQuery = TaskQuery.create();
    users.forEach(user -> taskQuery.where().or().canWorkOn(user));
    return taskQuery;
  }

  private TaskQuery queryForKeyword(String keyword) {
    String containingKeyword = String.format("%%%s%%", keyword);
    TaskQuery filterByKeywordQuery =
        TaskQuery.create().where().or().name().isLike(containingKeyword).or().description().isLike(containingKeyword)
            .or().customVarCharField1().isLike(containingKeyword).or().customVarCharField2().isLike(containingKeyword)
            .or().customVarCharField3().isLike(containingKeyword).or().customVarCharField4().isLike(containingKeyword)
            .or().customVarCharField5().isLike(containingKeyword);

    return filterByKeywordQuery;
  }

  private TaskQuery queryForCategory(String keyword) {
    String startingWithCategory = String.format("%s%%", keyword);
    TaskQuery filterByKeywordQuery = TaskQuery.create().where().customVarCharField5().isLike(startingWithCategory);

    return filterByKeywordQuery;
  }

  private TaskQuery queryForStates(List<TaskState> states) {
    TaskQuery stateFieldQuery = TaskQuery.create();

    if (states == null || states.isEmpty()) {
      stateFieldQuery.where().state().isNotEqual(TaskState.DONE).and().state().isNotEqual(TaskState.ZOMBIE).and()
          .state().isNotEqual(TaskState.DESTROYED);
    } else {
      IFilterQuery filterQuery = stateFieldQuery.where();
      for (TaskState state : states) {
        filterQuery.or().state().isEqual(state);
      }
    }
    return stateFieldQuery;
  }

  private TaskQuery queryForExcludedStates(List<TaskState> excludedStates) {
    TaskQuery stateFieldQuery = TaskQuery.create();

    if (excludedStates == null || excludedStates.isEmpty()) {
      return stateFieldQuery;
    }

    excludedStates.forEach(excludedState -> stateFieldQuery.where().and().state().isNotEqual(excludedState));

    return stateFieldQuery;
  }

  private TaskQuery queryForTaskId(Long taskId) {
    TaskQuery query = TaskQuery.create();
    query.where().taskId().isEqual(taskId);
    return query;
  }

  private TaskQuery queryForPriority(WorkflowPriority priority) {
    TaskQuery priorityQuery = TaskQuery.create().where().priority().isEqual(priority);
    return priorityQuery;
  }

  private TaskQuery queryForExpiry(Date date, Date dateAfter1Day) {
    TaskQuery priorityQuery =
        TaskQuery.create().where().expiryTimestamp().isGreaterOrEqualThan(date).and().expiryTimestamp()
            .isLowerThan(dateAfter1Day);
    return priorityQuery;
  }

  /**
   * Execute query
   * 
   * @param query given {@link TaskQuery}
   * @param startIndex starts from 0
   * @param count used -1 to find all from startIndex
   * @return
   */
  private List<ITask> executeTaskQuery(TaskQuery query, Integer startIndex, Integer count) {
    List<ITask> tasks = Ivy.wf().getGlobalContext().getTaskQueryExecutor().getResults(query, startIndex, count);
    return tasks;
  }

  private long countTasks(TaskQuery query) {
    return Ivy.wf().getGlobalContext().getTaskQueryExecutor().getCount(query);
  }
}
