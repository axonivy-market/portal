package ch.ivy.ws.addon.service;

import static ch.ivyteam.ivy.workflow.TaskState.PARKED;
import static ch.ivyteam.ivy.workflow.TaskState.RESUMED;
import static ch.ivyteam.ivy.workflow.TaskState.SUSPENDED;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import ch.ivy.ws.addon.CategoryData;
import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.AvailableAppsResult;
import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.transformer.IvyNoteTransformer;
import ch.ivy.ws.addon.transformer.IvyTaskTransformer;
import ch.ivy.ws.addon.types.ElapsedTimeStatistic;
import ch.ivy.ws.addon.types.ExpiryStatistic;
import ch.ivy.ws.addon.types.IvyApplication;
import ch.ivy.ws.addon.types.IvySecurityMember;
import ch.ivy.ws.addon.types.IvyTask;
import ch.ivy.ws.addon.types.PriorityStatistic;
import ch.ivy.ws.addon.util.SessionUtil;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Record;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
import ch.ivyteam.ivy.workflow.query.ITaskQueryExecutor;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

import com.google.gson.Gson;

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
            IApplication application = null;
            try {
              t = executeTaskQuery(query, 0, -1).get(0);
              application = t.getApplication();
              session = findUserWorkflowSession(username, application);

              if (session != null) {
                INote note = t.createNote(session, message);
                result.setNewNote(noteTransformer.transform(note));
              }
            } catch (Exception e) {
              // Wrong combination of taskId and username
              List<Object> userText = new ArrayList<Object>();
              userText.add(taskId);
              userText.add(username);
              errors.add(new WSException(WSErrorType.WARNING, 10031, e, userText, null));
            } finally {
              if (session != null && application != null) {
                ISecurityContext securityContext = application.getSecurityContext();
                securityContext.destroySession(session.getIdentifier());
              }
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
  public TaskServiceResult delegateTask(final Integer taskId, final IvySecurityMember securityMember,
      Boolean isUrlBuiltFromSystemProperties) throws WSException {
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
            if (t != null) {
              t.setActivator(member);
              t.setCustomTimestampField5(new Date());
            }
            IvyTask ivyTask = new IvyTaskTransformer(isUrlBuiltFromSystemProperties).transform(t);
            result.setTask(ivyTask);
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
  public IvyTask parkTask(final String username, final Integer taskId, Boolean isUrlBuiltFromSystemProperties)
      throws WSException {
    try {
      return securityManager().executeAsSystem(new Callable<IvyTask>() {
        @Override
        public IvyTask call() throws Exception {
          IvyTask result = null;
          List<WSException> errors = new ArrayList<WSException>();

          if (taskId != null) {
            TaskQuery query = TaskQuery.create().where().taskId().isEqual(taskId);
            ITask t = taskQueryExecutor().getResults(query).get(0);
            IWorkflowSession workflowSession = null;
            IApplication application = null;
            if (t != null) {
              try {
                application = t.getApplication();
                workflowSession = findUserWorkflowSession(username, application);
                if (workflowSession != null) {
                  if (t.getState().equals(TaskState.SUSPENDED)) {
                    t = workflowSession.resumeTask(t.getId());
                  }
                  workflowSession.parkTask(t);
                  result = new IvyTaskTransformer(isUrlBuiltFromSystemProperties).transform(t);
                }
              } catch (Exception e) {
                // Invalid username
                List<Object> userText = new ArrayList<Object>();
                userText.add(username);
                errors.add(new WSException(WSErrorType.WARNING, 10029, e, userText, null));
              } finally {
                if (workflowSession != null && application != null) {
                  ISecurityContext securityContext = application.getSecurityContext();
                  securityContext.destroySession(workflowSession.getIdentifier());
                }
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
  public TaskServiceResult resetTask(String currentUserName, final Integer taskId,
      Boolean isUrlBuiltFromSystemProperties) throws WSException {
    try {
      return securityManager().executeAsSystem(new Callable<TaskServiceResult>() {
        @Override
        public TaskServiceResult call() throws Exception {
          TaskServiceResult result = new TaskServiceResult();
          List<WSException> errors = new ArrayList<WSException>();

          ITask task = findTask(taskId, errors);

          if (task != null) {
            task.setCustomTimestampField5(new Date());
            task.reset();
            IvyTaskTransformer transformer = new IvyTaskTransformer(isUrlBuiltFromSystemProperties);
            IvyTask ivyTask = transformer.transform(task);

            checkUserPemissions(currentUserName, task, ivyTask);

            result.setTask(ivyTask);
          }

          result.setErrors(errors);
          return result;
        }

      });
    } catch (Exception e) {
      throw new WSException(10014, e);
    }
  }

  public void checkUserPemissions(String userName, ITask task, IvyTask ivyTask) {
    boolean canUserResumeTask = canUserResumeTask(userName, task);
    ivyTask.setCanReset(hasPermissionToResetTask(task, userName, canUserResumeTask));
    ivyTask.setCanDelegate(hasPermissionToDelegateTask(task, userName));
    ivyTask.setCanPark(hasPermissionToParkTask(task, userName));
    ivyTask.setCanResume(canUserResumeTask);
    ivyTask.setCanChangePriority(hasPermissionToChangeOriginalPriority(userName, task.getApplication()));
    ivyTask.setCanChangeDescription(hasPermissionToChangeDescription(userName, task));
    ivyTask.setCanChangeName(hasPermissionToChangeName(userName, task));
    ivyTask.setCanChangeExpiry(hasPermissionToChangeExpiry(userName, task));
  }

  @Override
  public TaskServiceResult findTasksByCriteria(TaskSearchCriteria taskSearchCriteria, Integer startIndex,
      Integer count, Boolean isUrlBuiltFromSystemProperties) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(() -> {
        if (taskSearchCriteria.isEmpty() && StringUtils.isBlank(taskSearchCriteria.getJsonQuery())) {
          return result(noErrors());
        }
        TaskQuery taskQuery = createTaskQuery(taskSearchCriteria);
        queryExcludeHiddenTasks(taskQuery);
        List<ITask> tasks = executeTaskQuery(taskQuery, startIndex, count);
        List<IvyTask> ivyTasks = new ArrayList<>();
        List<IvyTask> allIvyTasks = new ArrayList<>();
        IvyTaskTransformer transformer = new IvyTaskTransformer(isUrlBuiltFromSystemProperties);
        tasks.forEach(task -> {
          IvyTask ivyTask = transformer.transform(task);

          if (taskSearchCriteria.hasInvolvedUsername()) {
            String involvedUsername = taskSearchCriteria.getInvolvedUsername();
            boolean canUserResumeTask = canUserResumeTask(involvedUsername, task);
            checkUserPemissions(involvedUsername, task, ivyTask);
            try {
              ivyTask.setHasMoreActions(hasMoreActions(task, involvedUsername));
            } catch (Exception e) {
              Ivy.log().error("Error when checking whether task has more actions", e);
            }
            if (taskSearchCriteria.isQueryByTaskId()) {
              ivyTasks.add(ivyTask);
            } else if (canUserResumeTask || taskSearchCriteria.isTaskStartedByAnotherDisplayed()) {
              ivyTasks.add(ivyTask);
            } else if (isTaskDoneByInvolveUser(involvedUsername, task)){
              ivyTasks.add(ivyTask);
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

  private boolean hasMoreActions(ITask task, String userName) throws Exception {
    boolean isAdmin = SessionUtil.doesUserHavePermission(task.getApplication(), userName, IPermission.TASK_READ_ALL);
    boolean isOpenTask = Stream.of(SUSPENDED, RESUMED, PARKED).anyMatch(state -> state.equals(task.getState()));
    boolean canUserResumeTask = canUserResumeTask(userName, task);
    boolean isAdhocIncluded = (isAdmin && isOpenTask) || canUserResumeTask;
    SideStepServiceImpl sideStepService = new SideStepServiceImpl();
    return sideStepService.hasSideSteps(task.getCase(), isAdhocIncluded);
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
        queryExcludeHiddenTasks(taskQuery);

        long taskCount = countTasks(taskQuery);
        return result(taskCount, errors);

      });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @SuppressWarnings("static-access")
  @Override
  public TaskServiceResult findCategories(String jsonQuery, final String username, List<String> apps, String language)
      throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(
          () -> {
            TaskQuery taskQuery = Ivy.wf().getGlobalContext().getTaskQueryExecutor().createTaskQuery();
            if (StringUtils.isNotBlank(jsonQuery)) {
              taskQuery.fromJson(jsonQuery);
            }
            queryExcludeHiddenTasks(taskQuery);

            if (username != null && !StringUtils.isEmpty(username)) {
              AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
              taskQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()))
                  .and(queryForInvolvedApplications(availableAppsResult.getAvailableApps()));
            } else {
              taskQuery.where().and(queryForInvolvedApplications(apps));
            }
            taskQuery.where()
                .and(
                    queryForStates(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED,
                        TaskState.DONE)));
            taskQuery.where().and().category().isNotNull();

            CategoryTree categoryTree = CategoryTree.createFor(taskQuery);
            List<CategoryData> categories = new ArrayList<>();
            categoryTree.getAllChildren().forEach(category -> {
              CategoryData categoryData = new CategoryData();
              categoryData.setPath(category.getCategory().getPath(Locale.forLanguageTag(language)));
              categoryData.setRawPath(category.getRawPath());
              categories.add(categoryData);
            });
            return result(categories, errors);
          });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @SuppressWarnings("static-access")
  @Override
  public TaskServiceResult findPersonalTaskCategories(String jsonQuery, final String username, List<String> apps,
      String language) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(
          () -> {
            TaskQuery taskQuery = Ivy.wf().getGlobalContext().getTaskQueryExecutor().createTaskQuery();
            if (StringUtils.isNotBlank(jsonQuery)) {
              taskQuery = taskQuery.fromJson(jsonQuery);
            }
            queryExcludeHiddenTasks(taskQuery);

            AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
            taskQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()))
                .and(queryForInvolvedApplications(availableAppsResult.getAvailableApps()));
            TaskQuery reservedTaskQuery =
                TaskQuery.create().where().activatorRoleId().isNotNull().and().state().isEqual(TaskState.PARKED);
            taskQuery.where().and().activatorUserId().isNotNull().or(reservedTaskQuery);
            taskQuery.where()
                .and(
                    queryForStates(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED,
                        TaskState.DONE)));
            taskQuery.where().and().category().isNotNull();

            CategoryTree categoryTree = CategoryTree.createFor(taskQuery);
            List<CategoryData> categories = new ArrayList<>();
            categoryTree.getAllChildren().forEach(category -> {
              CategoryData categoryData = new CategoryData();
              categoryData.setPath(category.getCategory().getPath(Locale.forLanguageTag(language)));
              categoryData.setRawPath(category.getRawPath());
              categories.add(categoryData);
            });

            return result(categories, errors);
          });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @SuppressWarnings("static-access")
  @Override
  public TaskServiceResult findGroupTaskCategories(String jsonQuery, final String username, List<String> apps,
      String language) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(
          () -> {
            TaskQuery taskQuery = Ivy.wf().getGlobalContext().getTaskQueryExecutor().createTaskQuery();
            if (StringUtils.isNotBlank(jsonQuery)) {
              taskQuery = taskQuery.fromJson(jsonQuery);
            }
            queryExcludeHiddenTasks(taskQuery);

            AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
            taskQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()))
                .and(queryForInvolvedApplications(availableAppsResult.getAvailableApps()));
            taskQuery.where().and().activatorRoleId().isNotNull();
            taskQuery.where()
                .and(
                    queryForStates(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED,
                        TaskState.DONE)));
            taskQuery.where().and().category().isNotNull();

            CategoryTree categoryTree = CategoryTree.createFor(taskQuery);
            List<CategoryData> categories = new ArrayList<>();
            categoryTree.getAllChildren().forEach(category -> {
              CategoryData categoryData = new CategoryData();
              categoryData.setPath(category.getCategory().getPath(Locale.forLanguageTag(language)));
              categoryData.setRawPath(category.getRawPath());
              categories.add(categoryData);
            });

            return result(categories, errors);
          });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @SuppressWarnings("static-access")
  @Override
  public TaskServiceResult findUnassignedTaskCategories(String jsonQuery, List<String> apps, String language)
      throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(() -> {
        TaskQuery taskQuery = Ivy.wf().getGlobalContext().getTaskQueryExecutor().createTaskQuery();
        if (StringUtils.isNotBlank(jsonQuery)) {
          taskQuery = taskQuery.fromJson(jsonQuery);
        }
        queryExcludeHiddenTasks(taskQuery);

        taskQuery.where().and(queryForInvolvedApplications(apps));
        taskQuery.where().and(queryForStates(Arrays.asList(TaskState.UNASSIGNED)));
        taskQuery.where().and().category().isNotNull();

        CategoryTree categoryTree = CategoryTree.createFor(taskQuery);
        List<CategoryData> categories = new ArrayList<>();
        categoryTree.getAllChildren().forEach(category -> {
          CategoryData categoryData = new CategoryData();
          categoryData.setPath(category.getCategory().getPath(Locale.forLanguageTag(language)));
          categoryData.setRawPath(category.getRawPath());
          categories.add(categoryData);
        });

        return result(categories, errors);
      });
    } catch (Exception e) {
      throw new WSException(10016, e);
    }
  }

  @Override
  public TaskServiceResult analyzePriorityStatistic(String jsonQuery, final String username, List<String> apps)
      throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(
          () -> {
            TaskQuery priorityQuery =
                StringUtils.isNotBlank(jsonQuery) ? TaskQuery.fromJson(jsonQuery) : TaskQuery.create();

            if (username != null && !StringUtils.isEmpty(username)) {
              AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
              priorityQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()))
                  .and(queryForInvolvedApplications(availableAppsResult.getAvailableApps()));
            } else {
              priorityQuery.where().and(queryForInvolvedApplications(apps));
            }

            queryExcludeHiddenTasks(priorityQuery);

            priorityQuery.aggregate().countRows()
              .groupBy().priority()
              .orderBy().priority();

            Recordset recordSet = taskQueryExecutor().getRecordset(priorityQuery);
            PriorityStatistic priorityStatistic = new PriorityStatistic();
            if (recordSet != null) {
              recordSet.getRecords().forEach(record -> {
                int priority = Integer.parseInt(record.getField("PRIORITY").toString());
                long numberOfTasks = Long.parseLong(record.getField("COUNT").toString());
                if (priority == WorkflowPriority.EXCEPTION.intValue()) {
                  priorityStatistic.setException(numberOfTasks);
                } else if (priority == WorkflowPriority.HIGH.intValue()) {
                  priorityStatistic.setHigh(numberOfTasks);
                } else if (priority == WorkflowPriority.NORMAL.intValue()) {
                  priorityStatistic.setNormal(numberOfTasks);
                } else {
                  priorityStatistic.setLow(numberOfTasks);
                }
              });
            }

            return result(priorityStatistic, errors);
          });
    } catch (Exception e) {
      throw new WSException(10049, e);
    }
  }

  @Override
  public TaskServiceResult analyzeExpiryStatistic(String jsonQuery, final String username, List<String> apps) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(
          () -> {
            TaskQuery expiryQuery =
                StringUtils.isNotBlank(jsonQuery) ? TaskQuery.fromJson(jsonQuery) : TaskQuery.create();

            if (username != null && !StringUtils.isEmpty(username)) {
              AvailableAppsResult availableAppsResult = findAvailableApplicationsAndUsers(apps, username);
              expiryQuery.where().and(queryForCanWorkOnUsers(availableAppsResult.getUsers()))
                  .and(queryForInvolvedApplications(availableAppsResult.getAvailableApps()));
            } else {
              expiryQuery.where().and(queryForInvolvedApplications(apps));
            }

            queryExcludeHiddenTasks(expiryQuery);

            expiryQuery.aggregate().countRows()
              .groupBy().expiryTimestamp()
              .orderBy().expiryTimestamp();

            Recordset recordSet = taskQueryExecutor().getRecordset(expiryQuery);
            HashMap<String, String> recordMap = new HashMap<String, String>();
            if (recordSet != null) {
              for (Record record : recordSet.getRecords()) {
                if (record.getField("EXPIRYTIMESTAMP") != null) {
                  recordMap.put(record.getField("EXPIRYTIMESTAMP").toString(), record.getField("COUNT").toString());
                }
              }
            }

            ExpiryStatistic expiryStatistic = new ExpiryStatistic();
            Gson gsonConverter = new Gson();
            String json = "";
            if (recordMap.size() != 0) {
              json= gsonConverter.toJson(recordMap);
            }
            expiryStatistic.setResult(json);

            return result(expiryStatistic, errors);
          });
    } catch (Exception e) {
      throw new WSException(10050, e);
    }
  }

  @Override
  public TaskServiceResult analyzeElapsedTimeOfTasks(String jsonQuery, List<String> apps) throws WSException {
    List<WSException> errors = Collections.emptyList();
    try {
      return securityManager().executeAsSystem(
          () -> {
            TaskQuery elapsedTimeQuery =
                StringUtils.isNotBlank(jsonQuery) ? TaskQuery.fromJson(jsonQuery) : TaskQuery.create();

            elapsedTimeQuery.where().and(queryForInvolvedApplications(apps));
            queryExcludeHiddenTasks(elapsedTimeQuery);

            elapsedTimeQuery.where().and().businessRuntime().isNotNull();
            elapsedTimeQuery.aggregate().avgBusinessRuntime()
            .groupBy().category();

            Recordset recordSet = taskQueryExecutor().getRecordset(elapsedTimeQuery);
            HashMap<String, Long> recordMap = new HashMap<String, Long>();
            if (recordSet != null) {
              recordSet.getRecords().forEach(record -> {
                String categoryName = record.getField("CATEGORY").toString();
                BigDecimal averageElapsedTime
                  = Optional.ofNullable((BigDecimal)record.getField("AVGBUSINESSRUNTIME")).orElse(new BigDecimal(0));
                long averageElapsedTimeValue = averageElapsedTime.longValue();
                recordMap.put(categoryName, averageElapsedTimeValue);
              });
            }

            ElapsedTimeStatistic elapsedTimeStatistic = new ElapsedTimeStatistic();
            Gson gsonConverter = new Gson();
            String json = "";
            if (recordMap.size() != 0) {
              json = gsonConverter.toJson(recordMap);
            }
            elapsedTimeStatistic.setResult(json);

            return result(elapsedTimeStatistic, errors);
          });
    } catch (Exception e) {
      throw new WSException(10054, e);
    }
  }

  private TaskQuery createTaskQuery(TaskSearchCriteria taskSearchCriteria) throws Exception {
    TaskQuery finalQuery = TaskQuery.fromJson(taskSearchCriteria.getJsonQuery());

    if (taskSearchCriteria.hasInvolvedUsername() && !taskSearchCriteria.isIgnoreInvolvedUser()) {
      List<String> involvedApplications = taskSearchCriteria.getInvolvedApplications();
      String involvedUsername = taskSearchCriteria.getInvolvedUsername();

      AvailableAppsResult availableAppsResult =
          findAvailableApplicationsAndUsers(involvedApplications, involvedUsername);
      finalQuery.where().and(queryForInvolvedUsers(availableAppsResult.getUsers(), taskSearchCriteria.isTaskStartedByAnotherDisplayed()))
          .and(queryForInvolvedApplications(availableAppsResult.getAvailableApps()));
    } else if (taskSearchCriteria.hasInvolvedApplications()) {
      finalQuery.where().and(queryForInvolvedApplications(taskSearchCriteria.getInvolvedApplications()));
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
            IUser workerUser = task.getWorkerUser();
            if (workerUser != null) {
              String fullName = workerUser.getFullName();
              String workerName =
                  fullName == null || fullName.isEmpty() ? workerUser.getName() : workerUser.getFullName() + " ("
                      + workerUser.getName() + ")";
                  result.setWorkerUserName(workerName);
            }
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

  @Override
  public void save(IvyTask task) throws WSException {
    try {
      securityManager().executeAsSystem(() -> {
        TaskQuery query = TaskQuery.create().where().taskId().isEqual(task.getId());
        ITask existingTask = taskQueryExecutor().getFirstResult(query);

        // As of now, expiryTimestamp, description and originalPriority are the
        // only fields
        // supported for updating.
          existingTask.setDescription(task.getDescription());
          existingTask.setExpiryTimestamp(task.getExpireTimestamp());
          existingTask.setOriginalPriority(WorkflowPriority.valueOf(task.getPriority()));
          existingTask.setName(task.getName());
          return null;
        });
    } catch (Exception e) {
      throw new WSException(10045, e);
    }
  }

  @Override
  public void setAdditionalProperty(Long taskId, String name, String value) throws WSException {
    try {
      securityManager().executeAsSystem(() -> {
        if (taskId != null) {
          TaskQuery query = TaskQuery.create().where().taskId().isEqual(taskId);
          ITask existingTask = taskQueryExecutor().getFirstResult(query);
          existingTask.setAdditionalProperty(name, value);
        }
        return null;
      });
    } catch (Exception e) {
      throw new WSException(10045, e);
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

  private boolean hasPermissionToParkTask(ITask task, String username) {
    TaskState taskState = task.getState();

    if (!canUserResumeTask(username, task)) {
      return false;
    }
    if (!(TaskState.SUSPENDED.equals(taskState) || TaskState.RESUMED.equals(taskState))) {
      return false;
    }
    return SessionUtil.doesUserHavePermission(task.getApplication(), username, IPermission.TASK_PARK_OWN_WORKING_TASK);
  }

  private boolean canUserResumeTask(String userName, ITask task) {
    IUser user = findUser(userName, task);

    if (TaskState.SUSPENDED.equals(task.getState())) {
      return task.getActivatorUserCandidates().contains(user);
    }

    if (TaskState.RESUMED.equals(task.getState()) || TaskState.PARKED.equals(task.getState())) {
      return user.equals(task.getWorkerUser());
    }
    return false;
  }
  
  private boolean isTaskDoneByInvolveUser(String userName, ITask task) {
    IUser user = findUser(userName, task);
    return TaskState.DONE.equals(task.getState()) && user.equals(task.getWorkerUser());
  }

  private boolean hasPermissionToChangeExpiry(String username, ITask task) {
    return SessionUtil.doesUserHavePermission(task.getApplication(), username, IPermission.TASK_WRITE_EXPIRY_TIMESTAMP);
  }

  private boolean hasPermissionToChangeOriginalPriority(String userName, IApplication application) {
    return SessionUtil.doesUserHavePermission(application, userName, IPermission.TASK_WRITE_ORIGINAL_PRIORITY);
  }

  private boolean hasPermissionToChangeDescription(String username, ITask task) {
    return SessionUtil.doesUserHavePermission(task.getApplication(), username, IPermission.TASK_WRITE_DESCRIPTION);
  }

  private boolean hasPermissionToChangeName(String username, ITask task) {
    return SessionUtil.doesUserHavePermission(task.getApplication(), username, IPermission.TASK_WRITE_NAME);
  }

  private IUser findUser(String userName, ITask task) {
    return task.getApplication().getSecurityContext().findUser(userName);
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
    result.setTaskCount(taskCount);
    result.setErrors(errors);
    return result;
  }

  private TaskServiceResult result(List<CategoryData> categories, List<WSException> errors) {
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

  private TaskServiceResult result(ElapsedTimeStatistic elapsedTimeStatistic, List<WSException> errors) {
    TaskServiceResult result = new TaskServiceResult();
    result.setElapsedTimeStatistic(elapsedTimeStatistic);
    result.setErrors(errors);
    return result;
  }

  private List<WSException> noErrors() {
    return Collections.emptyList();
  }

  private TaskQuery queryForInvolvedUsers(List<IUser> users, boolean isTaskStartedByAnotherDisplayed) {
    TaskQuery taskQuery = TaskQuery.create();
    users.forEach(user -> {
      taskQuery.where().or().isInvolved(user);
      if (isTaskStartedByAnotherDisplayed) {
        user.getAllRoles().forEach(role -> {
          taskQuery.where().or().activatorName().isEqual(role.getName()); 
        });
      }
    });
    return taskQuery;
  }

  private TaskQuery queryForInvolvedApplications(List<String> apps) {
    List<IvyApplication> applications = WsServiceFactory.getApplicationService().getApplicationsBy(apps);
    TaskQuery taskQuery = TaskQuery.create();
    applications.forEach(app -> taskQuery.where().or().applicationId().isEqual(app.getId()));
    return taskQuery;
  }

  private TaskQuery queryForCanWorkOnUsers(List<IUser> users) {
    TaskQuery taskQuery = TaskQuery.create();
    users.forEach(user -> taskQuery.where().or().canWorkOn(user));
    return taskQuery;
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

  private void queryExcludeHiddenTasks(TaskQuery query) {
    List<ITask> hiddenTasks =
        executeTaskQuery(TaskQuery.create().where().additionalProperty("HIDE").isNotNull(), 0, -1);

    hiddenTasks.forEach(hiddenTask -> {
      query.where().and().taskId().isNotEqual(hiddenTask.getId());
    });
  }

  /**
   * Execute query
   * 
   * @param query given {@link TaskQuery}
   * @param startIndex starts from 0
   * @param count used -1 to find all from startIndex
   * @return List<ITask>
   */
  private List<ITask> executeTaskQuery(TaskQuery query, Integer startIndex, Integer count) {
    List<ITask> tasks = taskQueryExecutor().getResults(query, startIndex, count);
    return tasks;
  }

  private long countTasks(TaskQuery query) {
    return taskQueryExecutor().getCount(query);
  }

  private ITaskQueryExecutor taskQueryExecutor() {
    return Ivy.wf().getGlobalContext().getTaskQueryExecutor();
  }

  private ISecurityManager securityManager() {
    return ServerFactory.getServer().getSecurityManager();
  }


}
