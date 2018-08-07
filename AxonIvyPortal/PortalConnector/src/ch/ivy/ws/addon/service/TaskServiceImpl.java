package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.math.NumberUtils;

import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.AvailableAppsResult;
import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.transformer.IvyNoteTransformer;
import ch.ivy.ws.addon.transformer.IvyTaskTransformer;
import ch.ivy.ws.addon.types.IvySecurityMember;
import ch.ivy.ws.addon.types.IvyTask;
import ch.ivy.ws.addon.util.SessionUtil;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskServiceImpl extends AbstractService implements ITaskService {

  @Override
  public NoteServiceResult createNote(final String username, final Integer taskId, final String message)
      throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<NoteServiceResult>() {
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
              t = executeTaskQuery(query).get(0);
              session = findUserWorkflowSession(username, t.getApplication());
            } catch (Exception e) {
              // Wrong combination of taskId and username
              List<Object> userText = new ArrayList<Object>();
              userText.add(taskId);
              userText.add(username);
              errors.add(new WSException(WSErrorType.WARNING, 10031, userText, null));
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
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<TaskServiceResult>() {
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
              t = executeTaskQuery(query).get(0);
              member = t.getApplication().getSecurityContext().findSecurityMember(securityMember.getMemberName());
            } catch (Exception e) {
              // Wrong securityMember
              List<Object> userText = new ArrayList<Object>();
              userText.add(taskId);
              errors.add(new WSException(WSErrorType.WARNING, 10028, userText, null));
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
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<NoteServiceResult>() {
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
  public TaskServiceResult findTask(final Integer taskId, String serverUrl) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<TaskServiceResult>() {
        @Override
        public TaskServiceResult call() throws Exception {
          TaskServiceResult result = new TaskServiceResult();
          List<WSException> errors = new ArrayList<WSException>();
          ITask task = findTask(taskId, errors);

          if (task != null) {
            IvyTaskTransformer transformer = new IvyTaskTransformer(serverUrl);
            result.setTask(transformer.transform(task));
          }

          result.setErrors(errors);
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10015, e);
    }
  }

  @Override
  public TaskServiceResult findTasksByFilter(final String username, final String filter, final List<String> apps,
      final Long serverId, String serverUrl, final TaskState... states) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<TaskServiceResult>() {
        @Override
        public TaskServiceResult call() throws Exception {
          TaskServiceResult result = new TaskServiceResult();
          List<WSException> errors = new ArrayList<WSException>();
          List<String> availableApps = new ArrayList<String>();

          // Username given
          if (username != null && !username.trim().equals("")) {
            // Check if there is any unavailable application
            AvailableAppsResult aaResult = findAvailableApplicationsAndUsers(apps, username);
            errors.addAll(aaResult.getErrors());
            availableApps.addAll(aaResult.getAvailableApps());

            List<IUser> users = aaResult.getUsers();

            if (users.size() > 0) {
              IvyTaskTransformer transformer = new IvyTaskTransformer(serverUrl);

              TaskQuery taskQuery = TaskQuery.create();

              taskQuery.where().and(queryForUsers(users));

              if (filter != null && filter.length() > 0) {
                TaskQuery filterQuery = queryForFilter(filter);
                taskQuery.where().and(filterQuery);
              }

              TaskQuery stateFieldQuery = TaskQuery.create();

              if (states == null || states.length == 0) {
                stateFieldQuery.where().state().isNotEqual(TaskState.DONE);
              } else {
                stateFieldQuery = queryForStates(Arrays.asList(states));
              }
              taskQuery.where().and(stateFieldQuery);

              List<ITask> tasks = executeTaskQuery(taskQuery);

              List<IvyTask> ivyTasks = new ArrayList<>();
              for (ITask task : tasks) {
                IvyTask ivyTask = transformer.transform(task);
                ivyTask.setServerId(serverId);
                ivyTask.setCanReset(SessionUtil.doesUserHavePermission(task.getApplication(), username,
                    IPermission.TASK_RESET_OWN_WORKING_TASK));
                ivyTask.setCanDelegate(SessionUtil.doesUserHavePermission(task.getApplication(), username,
                    IPermission.TASK_WRITE_ACTIVATOR));
                ivyTask.setCanResume(true);
                ivyTasks.add(ivyTask);
              }
              result.setTasks(ivyTasks);
            }
          } else {
            // Username not given
            List<Object> userText = new ArrayList<Object>();
            userText.add(username);
            errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
          }

          result.setErrors(errors);
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @Override
  public IvyTask parkTask(final String username, final Integer taskId, String serverUrl) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<IvyTask>() {
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
                errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
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
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<TaskServiceResult>() {
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
  public TaskServiceResult findTasksByCriteria(TaskSearchCriteria taskSearchCriteria, String serverUrl)
      throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return ServerFactory
          .getServer()
          .getSecurityManager()
          .executeAsSystem(
              () -> {

                if (taskSearchCriteria.isEmpty()) {
                  return result(noErrors());
                }

                TaskQuery finalQuery = TaskQuery.create();
                String involvedUsername = taskSearchCriteria.getInvolvedUsername();
                if (taskSearchCriteria.hasInvolvedUsername()) {
                  List<String> involvedApplications = taskSearchCriteria.getInvolvedApplications();

                  AvailableAppsResult availableAppsResult =
                      findAvailableApplicationsAndUsers(involvedApplications, involvedUsername);
                  finalQuery.where().and(queryForUsers(availableAppsResult.getUsers()));
                }

                if (taskSearchCriteria.hasKeyword()) {
                  finalQuery.where().and(queryForKeyword(taskSearchCriteria.getKeyword()));
                }

                if (taskSearchCriteria.hasExcludedStates()) {
                  finalQuery.where().and(queryForExcludedStates(taskSearchCriteria.getExcludedStates()));
                }

                if (taskSearchCriteria.hasIncludedStates()) {
                  finalQuery.where().and(queryForStates(taskSearchCriteria.getIncludedStates()));
                }

                IvyTaskTransformer transformer = new IvyTaskTransformer(serverUrl);
                List<ITask> tasks = executeTaskQuery(finalQuery);
                List<IvyTask> ivyTasks = new ArrayList<>();
                for (ITask task : tasks) {
                  IvyTask ivyTask = transformer.transform(task);
                  boolean canUserResumeTask = canUserResumeTask(taskSearchCriteria.getInvolvedUsername(), task);
                  ivyTask.setCanReset(hasPermissionToResetTask(task, taskSearchCriteria.getInvolvedUsername(), canUserResumeTask));
                  ivyTask.setCanDelegate(hasPermissionToDelegateTask(task, taskSearchCriteria.getInvolvedUsername()));
                  ivyTask.setCanResume(canUserResumeTask);
                  ivyTasks.add(ivyTask);
                }

                return result(ivyTasks, errors);
              });
    } catch (Exception e) {
      throw new WSException(10002, e);
    }
  }

  @Override
  public TaskServiceResult canUserResumeTask(Integer taskId, String userName) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<TaskServiceResult>() {
        @Override
        public TaskServiceResult call() throws Exception {
          TaskServiceResult result = new TaskServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          ITask task = findTask(taskId, errors);

          if (task != null) {
            boolean canUserResumeTask = canUserResumeTask(userName, task);
            result.setCanUserResumeTask(canUserResumeTask);
          }

          result.setWorkerUserName(task.getWorkerUserName());
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
      ITask task = executeTaskQuery(query).get(0);
      return task;
    } catch (Exception e) {
      // Wrong TaskId
      List<Object> userText = new ArrayList<Object>();
      userText.add(taskId);
      errors.add(new WSException(WSErrorType.WARNING, 10027, userText, null));
      return null;
    }
  }

  @Override
  public TaskServiceResult findAllRunningTasks(final String username, final List<String> apps, final Long serverId,
      String serverUrl) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<TaskServiceResult>() {
        @Override
        public TaskServiceResult call() throws Exception {
          TaskServiceResult result = new TaskServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          TaskQuery taskQuery =
              queryForStates(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED)).orderBy()
                  .priority();
          List<ITask> tasks = executeTaskQuery(taskQuery);

          List<IvyTask> allIvyTasks = new ArrayList<>();
          List<IvyTask> personalIvyTasks = new ArrayList<>();

          IvyTaskTransformer transformer = new IvyTaskTransformer(serverUrl);
          for (ITask task : tasks) {
            IvyTask ivyTask = transformer.transform(task);
            ivyTask.setServerId(serverId);
            boolean canUserResumeTask = canUserResumeTask(username, task);
            ivyTask.setCanReset(hasPermissionToResetTask(task, username, canUserResumeTask));
            ivyTask.setCanDelegate(hasPermissionToDelegateTask(task, username));
            ivyTask.setCanResume(canUserResumeTask);
            if (canUserResumeTask) {
              personalIvyTasks.add(ivyTask);
            }
            allIvyTasks.add(ivyTask);
          }
          result.setTasks(personalIvyTasks);
          result.setAllTasks(allIvyTasks);

          result.setErrors(errors);
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @Override
  public TaskServiceResult findTasksOfCase(final Long caseId, String username, String serverUrl) throws WSException {
    try {
      TaskQuery query = TaskQuery.create().where().caseId().isEqual(caseId);
      TaskServiceResult result = findTasksByQuery(query, username, serverUrl);
      return result;
    } catch (Exception e) {
      throw new WSException(10044, e, Arrays.asList(caseId), null);
    }
  }

  private TaskServiceResult findTasksByQuery(TaskQuery query, String username, String serverUrl) throws Exception {
    return ServerFactory
        .getServer()
        .getSecurityManager()
        .executeAsSystem(
            () -> {
              List<ITask> tasks = Ivy.wf().getGlobalContext().getTaskQueryExecutor().getResults(query);

              IvyTaskTransformer transformer = new IvyTaskTransformer(serverUrl);
              List<IvyTask> ivyTasks = new ArrayList<>();
              for (ITask task : tasks) {
                IvyTask ivyTask = transformer.transform(task);
                boolean canUserResumeTask = canUserResumeTask(username, task);
                ivyTask.setCanReset(hasPermissionToResetTask(task, username, canUserResumeTask));
                ivyTask.setCanDelegate(hasPermissionToDelegateTask(task, username));
                ivyTask.setCanResume(canUserResumeTask);
                ivyTasks.add(ivyTask);
              }

              TaskServiceResult result = new TaskServiceResult();
              result.setTasks(ivyTasks);
              return result;
            });
  }

  private boolean hasPermissionToResetTask(ITask task, String username, boolean canUserResumeTask) {
    TaskState taskState = task.getState();
    if (!(TaskState.SUSPENDED.equals(taskState) || TaskState.RESUMED.equals(taskState) || TaskState.PARKED.equals(taskState))) {
      return false;
    }
    
    return (SessionUtil.doesUserHavePermission(task.getApplication(), username, IPermission.TASK_RESET_OWN_WORKING_TASK) && canUserResumeTask)
        || SessionUtil.doesUserHavePermission(task.getApplication(), username, IPermission.TASK_RESET);
  }

  private boolean hasPermissionToDelegateTask(ITask task, String username) {
    TaskState taskState = task.getState();
    if (!(TaskState.SUSPENDED.equals(taskState) || TaskState.RESUMED.equals(taskState) || TaskState.PARKED.equals(taskState))) {
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

  private static TaskServiceResult result(List<WSException> errors) {
    TaskServiceResult result = new TaskServiceResult();
    result.setErrors(errors);
    return result;
  }

  private static TaskServiceResult result(List<IvyTask> ivyTasks, List<WSException> errors) {
    TaskServiceResult result = new TaskServiceResult();
    result.setTasks(ivyTasks);
    result.setErrors(errors);
    return result;
  }

  private static List<WSException> noErrors() {
    return Collections.emptyList();
  }

  private static TaskQuery queryForUsers(List<IUser> users) {
    TaskQuery taskQuery = TaskQuery.create();
    users.forEach(user -> taskQuery.where().or().isInvolved(user));
    return taskQuery;
  }

  private static TaskQuery queryForFilter(String filter) {
    TaskQuery filterStringFieldQuery = filterStringFields(filter);
    Number filterAsNumber = NumberUtils.createNumber(filter);

    return filterAsNumber == null ? filterStringFieldQuery : filterStringFieldQuery.where().or(
        filterNumericFields(filterAsNumber));
  }

  private static TaskQuery queryForKeyword(String keyword) {

    String containingKeyword = String.format("%%%s%%", keyword);

    TaskQuery filterByKeywordQuery =
        TaskQuery.create().where().or().name().isLike(containingKeyword).or().description().isLike(containingKeyword)
            .or().customVarCharField1().isLike(containingKeyword).or().customVarCharField2().isLike(containingKeyword)
            .or().customVarCharField3().isLike(containingKeyword).or().customVarCharField4().isLike(containingKeyword)
            .or().customVarCharField5().isLike(containingKeyword);

    return filterByKeywordQuery;
  }

  private static TaskQuery filterStringFields(String filter) {
    return TaskQuery.create().where().or().activatorName().isLike(filter).or().businessMainContactName().isLike(filter)
        .or().businessObjectCode().isLike(filter).or().businessObjectName().isLike(filter).or().customVarCharField1()
        .isLike(filter).or().customVarCharField2().isLike(filter).or().customVarCharField3().isLike(filter).or()
        .customVarCharField4().isLike(filter).or().customVarCharField5().isLike(filter).or().name().isLike(filter).or()
        .processCategoryCode().isLike(filter).or().processCategoryName().isLike(filter).or().processCode()
        .isLike(filter).or().processName().isLike(filter).or().subTypeCode().isLike(filter).or().subTypeName()
        .isLike(filter).or().description().isLike(filter);
  }

  private static TaskQuery filterNumericFields(Number filter) {
    int intValue = filter.intValue();
    double doubleValue = filter.doubleValue();
    return TaskQuery.create().where().or().businessMainContactId().isEqual(intValue).or().caseId().isEqual(intValue)
        .or().customDecimalField1().isEqual(doubleValue).or().customDecimalField2().isEqual(doubleValue).or()
        .customDecimalField3().isEqual(doubleValue).or().customDecimalField4().isEqual(doubleValue).or()
        .customDecimalField5().isEqual(doubleValue);
  }

  private static TaskQuery queryForStates(List<TaskState> states) {
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

  private static TaskQuery queryForExcludedStates(List<TaskState> excludedStates) {
    TaskQuery stateFieldQuery = TaskQuery.create();

    if (excludedStates == null || excludedStates.isEmpty()) {
      return stateFieldQuery;
    }

    excludedStates.forEach(excludedState -> stateFieldQuery.where().and().state().isNotEqual(excludedState));

    return stateFieldQuery;
  }

  private List<ITask> executeTaskQuery(TaskQuery finalQuery) {
    List<ITask> tasks = Ivy.wf().getGlobalContext().getTaskQueryExecutor().getResults(finalQuery);
    return tasks;
  }
}
