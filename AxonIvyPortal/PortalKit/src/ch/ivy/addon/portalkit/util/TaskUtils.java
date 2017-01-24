package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;

import ch.ivy.add.portalkit.admin.Task;
import ch.ivy.addon.portalkit.bo.NodeObject;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.vo.TaskVO;
import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.IQueryResult;
import ch.ivyteam.ivy.persistence.OrderDirection;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IPropertyFilter;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.PropertyOrder;
import ch.ivyteam.ivy.workflow.TaskProperty;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.logicalexpression.RelationalOperator;

/**
 * Provide the utilities related to Task.
 * 
 * @author bolt
 *
 */
public final class TaskUtils {

  private TaskUtils() {

  }

  private static final boolean FINISHED_MODE = true;
  private static final boolean RUNNING_MODE = false;
  private static final int DEFAULT_PAGESIZE = -1;
  private static final int DEFAULT_INDEX = 0;

  /**
   * Get the running tasks of the session user
   * 
   * @return List<ITask> : List running task of the session user
   */
  public static List<ITask> findWorkTaskOfSessionUser() {

    return findTaskOfSessionUser(DEFAULT_INDEX, DEFAULT_PAGESIZE, null, SortOrder.ASCENDING, RUNNING_MODE, null);
  }

  /**
   * Get the finished tasks of the session user
   * 
   * @return List<ITask> : List finished task of the session user
   */
  public static List<ITask> findWorkedTaskOfSessionUser() {
    return findTaskOfSessionUser(DEFAULT_INDEX, DEFAULT_PAGESIZE, null, SortOrder.ASCENDING, FINISHED_MODE, null);
  }

  /**
   * Get the task of session user by some Filter properties
   * 
   * @param startIndex : the index of the first task of all tasks found returned
   * @param pageSize : how many tasks found are returned. -1 for all.
   * @param sortField : Field to sort the task list
   * @param sortOrder : Order to sort, ascending or descending
   * @param isHistory : true => finished task , else : running task
   * @param taskFilter : filter criteria to specify which tasks should be read
   * @return List<ITask> : the list task of session user the task of session user by some Filter properties
   */
  public static List<ITask> findTaskOfSessionUser(int startIndex, int pageSize, String sortField, SortOrder sortOrder,
      boolean isHistory, IPropertyFilter<TaskProperty> taskFilter) {
    Ivy ivy = Ivy.getInstance();
    IQueryResult<ITask> queryResult;

    List<PropertyOrder<TaskProperty>> taskPropertyOrder =
        PropertyOrder.create(getTaskProperty(sortField), getTaskDirection(sortOrder));
    if (isHistory) {
      queryResult = ivy.session.findWorkedOnTasks(taskFilter, taskPropertyOrder, startIndex, pageSize, true);
    } else {

      queryResult =
          ivy.session.findWorkTasks(taskFilter, taskPropertyOrder, startIndex, pageSize, true,
              EnumSet.of(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED));
    }

    List<ITask> tasks = queryResult.getResultList();
    return tasks;
  }

  /**
   * Get the sort field and set set sort ID by default if input is null
   * 
   * @param sortField : Field to sort the task list
   * @return TaskProperty : The properties of a task
   */
  public static TaskProperty getTaskProperty(String sortField) {
    TaskProperty taskProperty = TaskProperty.ID;
    if (sortField != null) {
      taskProperty = TaskProperty.valueOf(sortField);
    }
    return taskProperty;
  }

  /**
   * Get the Sort order and set ASCENDING by default if input is null
   * 
   * @param sortOrder : Field to sort the task list
   * @return OrderDirection : Defines the order direction
   */
  public static OrderDirection getTaskDirection(SortOrder sortOrder) {
    OrderDirection direction;
    switch (sortOrder) {
      case DESCENDING:
        direction = OrderDirection.DESCENDING;
        break;
      default:
        direction = OrderDirection.ASCENDING;
    }
    return direction;
  }

  /**
   * Reset the Task
   * 
   * @param task : Task need to be reset
   * @throws Exception Exception
   * @throws EnvironmentNotAvailableException EnvironmentNotAvailableException
   * @throws PersistencyException PersistencyException
   */
  public static void resetTask(final ITask task) throws PersistencyException, EnvironmentNotAvailableException,
      Exception {
    // must be in RESUMED, CREATED, PARKED, READY_FOR_JOIN, FAILED
    if (task.getState().equals(TaskState.RESUMED) || (task.getState().equals(TaskState.CREATED))
        || task.getState().equals(TaskState.PARKED) || task.getState().equals(TaskState.READY_FOR_JOIN)
        || task.getState().equals(TaskState.FAILED)) {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Boolean>() {
        public Boolean call() throws Exception {
          task.reset();
          return true;
        }
      });
    }
  }

  /**
   * Park The Task. If it is SUSPENDED state, then change to RESUMED first, then park it.
   * 
   * @param task : Task need to be park
   * @throws Exception exception
   */
  public static void parkTask(final ITask task) throws Exception {
    ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        IWorkflowSession iWorkflowSession = Ivy.session();
        // Resume a task if it's suspended.
        if (task.getState().equals(TaskState.SUSPENDED)) {
          iWorkflowSession.resumeTask(task.getId());
        }

        // If the task is resumed or created, then park task.
        if (task.getState().equals(TaskState.RESUMED) || (task.getState().equals(TaskState.CREATED))) {
          iWorkflowSession.parkTask(task);
        }

        return null;
      }
    });

  }

  /**
   * Remove delay time of task
   * 
   * @param task : Task
   * @return Boolean
   */
  public static Boolean removeTaskDelay(final ITask task) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Boolean>() {
        public Boolean call() {
          try {
            task.setDelayTimestamp(null);
            return true;
          } catch (Exception e) {
            Ivy.log().error(e);
            return false;
          }
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return false;
    }
  }

  /**
   * Generate a tree data for Universal Task List UI
   * 
   * @param tasks : list task need to build
   * @param apps : list application
   * @return TreeNode : tree data for Universal Task List UI
   */
  public static TreeNode generateAppTree(List<ITask> tasks) {
    TreeNode root = new DefaultTreeNode();
    // appMap: applicationRegister as key, appNode as value
    Map<Application, TreeNode> appMap = new HashMap<Application, TreeNode>();

    for (ITask task : tasks) {
      Application appRegister = ((RemoteTask) task).getApplicationRegister();
      TreeNode appNode = appMap.get(appRegister);
      // build application nodes
      if (appNode == null) {
        appNode = new DefaultTreeNode(new NodeObject(appRegister), root);
        appNode.setExpanded(true);
        appMap.put(appRegister, appNode);

      }
      // build task nodes
      @SuppressWarnings("unused")
      TreeNode taskNode = new DefaultTreeNode(new NodeObject(task), appNode);

    }
    return root;
  }

  /**
   * Get the task of session user by id
   * 
   * @param taskId task id to find
   * @return ITask
   */
  public static ITask findWorkOnTaskById(long taskId) {
    Ivy ivy = Ivy.getInstance();
    IPropertyFilter<TaskProperty> taskFilter =
        ivy.session.getWorkflowContext().createTaskPropertyFilter(TaskProperty.ID, RelationalOperator.EQUAL, taskId);
    IQueryResult<ITask> queryResult =
        ivy.session.findWorkTasks(taskFilter, null, 0, 1, true,
            EnumSet.of(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED));
    List<ITask> tasks = queryResult.getResultList();
    if (tasks != null && tasks.size() > 0) {
      return tasks.get(0);
    }
    return null;
  }

  /**
   * Gets task by ID that session user has permission to see
   * 
   * @param taskId task id to find
   * @return task if it exists and user has insufficient rights to see, null if otherwise
   */
  public static ITask findTaskUserHasPermissionToSee(final long taskId) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<ITask>() {
        public ITask call() throws Exception {
          try {
            TaskQuery taskQuery1 = TaskQuery.create().where().taskId().isEqual(taskId);
            TaskQuery taskQuery2 = TaskQuery.create().where().currentUserIsInvolved();
            IUser user = Ivy.session().getSessionUser();
            if (user == null) {
              return null;
            }
            for (IRole role : user.getRoles()) {
              taskQuery2 = taskQuery2.where().or().roleIsInvolved(role);
            }
            return Ivy.wf().getTaskQueryExecutor().getFirstResult(taskQuery1.where().and(taskQuery2));
          } catch (Exception e) {
            Ivy.log().error(e);
            return null;
          }
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return null;
    }
  }

  /**
   * Filter current tasks by ICase
   * 
   * @param iCase : ICase
   * @return the list of TaskVO : (List of current task)
   */
  public static List<TaskVO> filterCurrentTasksByCase(ICase iCase) {
    List<TaskVO> currentTasks = new ArrayList<TaskVO>();
    if (iCase != null && iCase.getTasks() != null && iCase.getTasks().size() > 0) {
      EnumSet<TaskState> notDoneStates =
          EnumSet.of(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DELAYED,
              TaskState.WAITING_FOR_INTERMEDIATE_EVENT);
      for (ITask iTask : iCase.getTasks()) {
        if (notDoneStates.contains(iTask.getState())) {
          currentTasks.add(ConverterUtils.convertITaskToTaskVO(iTask));
        }
      }
    }
    return currentTasks;
  }

  /**
   * Filter list of task which have done by ICase
   * 
   * @param iCase case to filter
   * @return the list of TaskVO have done.
   */
  public static List<TaskVO> filterFinishedTasksByCase(ICase iCase) {
    List<TaskVO> currentTasks = new ArrayList<TaskVO>();
    if (iCase != null && iCase.getTasks() != null && iCase.getTasks().size() > 0) {
      for (ITask iTask : iCase.getTasks()) {
        if (TaskState.DONE.equals(iTask.getState())) {
          currentTasks.add(ConverterUtils.convertITaskToTaskVO(iTask));
        }
      }
    }
    return currentTasks;
  }

  /**
   * Get finished tasks by {@link ICase}
   * 
   * @param iCase object as {@link ICase}
   * 
   * @return list of {@link ITask}
   */
  public static List<ITask> getFinishedTasksByCase(ICase iCase) {
    List<ITask> currentTasks = new ArrayList<ITask>();
    if (iCase != null && iCase.getTasks() != null && iCase.getTasks().size() > 0) {
      for (ITask iTask : iCase.getTasks()) {
        if (iTask != null && TaskState.DONE.equals(iTask.getState())) {
          currentTasks.add(iTask);
        }
      }
    }
    return currentTasks;
  }

  /**
   * Convert from taskVO to ITask object
   *
   * @param iCase : iCase value to get all the task
   * @param taskId : id value of task
   * @return ITask value
   */
  public static ITask convertToITask(ICase iCase, long taskId) {
    for (ITask iTask : iCase.getTasks()) {
      if (iTask.getId() == taskId) {
        return iTask;
      }
    }
    return null;
  }

  /**
   * Get email address of creator user in ITask object
   * 
   * @param iTask ITask object to get the email
   * @return String get the email of creator of case, execute under system permission
   */
  public static String getEmailAddress(final ITask iTask) {
    if (iTask.getActivator().getSecurityContext() != null
        && iTask.getActivator().getSecurityContext().getUsers() != null
        && iTask.getActivator().getSecurityContext().getUsers().size() > 0) {
      try {
        return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<String>() {
          public String call() {
            try {
              String st = iTask.getActivator().getMemberName();
              List<IUser> l = iTask.getActivator().getSecurityContext().getUsers();
              for (IUser user : l) {
                if (st.equals(user.getMemberName())) {
                  return user.getEMailAddress();
                }
              }
              return st;
            } catch (Exception e) {
              Ivy.log().error(e);
              return null;
            }
          }
        });
      } catch (Exception e) {
        Ivy.log().error(e);
        return null;
      }
    } else {
      return null;
    }
  }

  /**
   * Get phone of creator user in ITask object
   * 
   * @param iTask ITask object to get the email
   * @return String get phone number of creator of case, execute under system permission
   */
  public static String getPhone(final ITask iTask) {
    if (iTask.getActivator().getSecurityContext() != null
        && iTask.getActivator().getSecurityContext().getUsers() != null
        && iTask.getActivator().getSecurityContext().getUsers().size() > 0) {
      try {
        return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<String>() {
          public String call() {
            try {
              String st = iTask.getActivator().getMemberName();
              List<IUser> l = iTask.getActivator().getSecurityContext().getUsers();
              for (IUser user : l) {
                if (st.equals(user.getMemberName())) {
                  return user.getProperty(UserUtils.PHONE);
                }
              }
              return st;
            } catch (Exception e) {
              Ivy.log().error(e);
              return null;
            }
          }
        });
      } catch (Exception e) {
        Ivy.log().error(e);
        return null;
      }
    } else {
      return null;
    }
  }

  /**
   * Get mobile of creator user in ITask object
   * 
   * @param iTask ITask object to get the email
   * @return String get mobile number of creator of case, execute under system permission
   */
  public static String getMobile(final ITask iTask) {
    if (iTask.getActivator().getSecurityContext() != null
        && iTask.getActivator().getSecurityContext().getUsers() != null
        && iTask.getActivator().getSecurityContext().getUsers().size() > 0) {
      try {
        return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<String>() {
          public String call() {
            try {
              String st = iTask.getActivator().getMemberName();
              List<IUser> l = iTask.getActivator().getSecurityContext().getUsers();
              for (IUser user : l) {
                if (st.equals(user.getMemberName())) {
                  return user.getProperty(UserUtils.MOBILE);
                }
              }
              return st;
            } catch (Exception e) {
              Ivy.log().error(e);
              return null;
            }
          }
        });
      } catch (Exception e) {
        Ivy.log().error(e);
        return null;
      }
    } else {
      return null;
    }
  }

  /**
   * Get phone of creator user in iUser object
   * 
   * @param iUser IUser object to get the email
   * @return String get phone number, execute under system permission
   */
  public static String getPhone(final IUser iUser) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<String>() {
        public String call() {
          try {
            return iUser.getProperty(UserUtils.PHONE);
          } catch (Exception e) {
            Ivy.log().error(e);
            return null;
          }
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return null;
    }
  }

  /**
   * Get mobile of creator user in IUser object
   * 
   * @param iUser IUser object to get the email
   * @return String get phone number, execute under system permission
   */
  public static String getMobile(final IUser iUser) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<String>() {
        public String call() {
          try {
            return iUser.getProperty(UserUtils.MOBILE);
          } catch (Exception e) {
            Ivy.log().error(e);
            return null;
          }
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return null;
    }
  }

  /**
   * Get email of creator user in IUser object
   * 
   * @param iUser IUser object to get the email
   * @return String get email, execute under system permission
   */
  public static String getEmailAddress(final IUser iUser) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<String>() {
        public String call() {
          try {
            return iUser.getEMailAddress();
          } catch (Exception e) {
            Ivy.log().error(e);
            return null;
          }
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return null;
    }
  }

  /**
   * Check if current user has read all tasks permission
   * 
   * @return True : has read all tasks permission, False : do not have read all tasks permission
   */
  public static boolean checkReadAllTasksPermission() {
    boolean hasReadAllTasksPermission =
        Ivy.session()
            .getSecurityContext()
            .hasPermission(Ivy.request().getApplication().getSecurityDescriptor(),
                ch.ivyteam.ivy.security.IPermission.TASK_READ_ALL);
    return hasReadAllTasksPermission;
  }

  /**
   * Check if current user has read all tasks permission
   * 
   * @return True : has read all tasks permission, False : do not have read all tasks permission
   */
  public static boolean checkReadAllCasesPermission() {
    boolean hasReadAllCasesPermission =
        Ivy.session()
            .getSecurityContext()
            .hasPermission(Ivy.request().getApplication().getSecurityDescriptor(),
                ch.ivyteam.ivy.security.IPermission.CASE_READ_ALL);
    return hasReadAllCasesPermission;
  }

  /**
   * Find waiting tasks by task code.
   * 
   * @param kindCode to find waiting tasks
   * @return list of task.
   */
  public static List<ITask> findWaitingTaskByKindCode(final String kindCode) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<List<ITask>>() {
        public List<ITask> call() throws Exception {
          try {
            TaskQuery taskQuery =
                TaskQuery.create().where().state().isEqual(TaskState.WAITING_FOR_INTERMEDIATE_EVENT).and().kindCode()
                    .isEqual(kindCode);

            List<ITask> taskList = Ivy.wf().getTaskQueryExecutor().getResults(taskQuery);
            return taskList;
          } catch (Exception e) {
            Ivy.log().error(e);
            return null;
          }

        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return null;
    }

  }

  /**
   * Find task by task Id.
   * 
   * @param taskId task id to find
   * @return ITask task found or null if not found
   */
  public static ITask findTaskById(final long taskId) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<ITask>() {
        public ITask call() throws Exception {
          ITask t = null;
          try {
            TaskQuery query = TaskQuery.create().where().taskId().isEqual(taskId);
            t = Ivy.wf().getGlobalContext().getTaskQueryExecutor().getResults(query).get(0);
          } catch (Exception e) {
            Ivy.log().error(e);
          }
          return t;
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return null;
    }
  }

  /**
   * Find the task by Task query
   * 
   * @param taskQuery
   * @return RecordSet Task list filter by Task query
   */
  public static Recordset findtasks(final TaskQuery taskQuery) {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Recordset>() {
        public Recordset call() throws Exception {
          return Ivy.wf().getTaskQueryExecutor().getRecordset(taskQuery);
        }
      });

    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return null;
  }

  /**
   * Delegate a task
   * 
   * @param iTask task need to delegate
   */
  public static void delegateTask(final ITask iTask, final ISecurityMember iSecurityMember) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Object>() {
        public Object call() throws Exception {
          iTask.setActivator(iSecurityMember);
          return null;
        }
      });

    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  /**
   * Convert iTask to Task
   * 
   * @param iTask
   * @return Task
   */
  private static Task convertITaskToTask(ITask iTask) {
    Task task = new Task();
    task.setId(iTask.getId());
    task.setCaseId(iTask.getCase().getId());
    task.setDescription(iTask.getDescription());
    task.setCaseDescription(iTask.getCase().getDescription());
    task.setCreatedOn(iTask.getStartTimestamp());
    task.setState(iTask.getState().toString());
    task.setActivator(iTask.getActivator());
    task.setIvyTask(iTask);
    return task;
  }

  /**
   * Get all task in the system. Only for administrator who has read all tasks and administrator workflow permission
   * 
   * @return List<Task> : All running tasks
   */
  public static List<Task> findAllRunningTasks() {
    List<Task> outTasks = new ArrayList<Task>();
    List<ITask> iTasks = findAllTasks(DEFAULT_INDEX, DEFAULT_PAGESIZE, null, SortOrder.ASCENDING, RUNNING_MODE, null);

    for (ITask iTask : iTasks) {
      outTasks.add(convertITaskToTask(iTask));
    }
    return outTasks;
  }

  /**
   * Get the All tasks by Filter properties
   * 
   * @param startIndex : the index of the first task of all tasks found returned
   * @param pageSize : how many tasks found are returned. -1 for all.
   * @param sortField : Field to sort the task list
   * @param sortOrder : Order to sort, ascending or descending
   * @param isHistory : true => finished task , else : running task
   * @param taskFilter : filter criteria to specify which tasks should be read
   * @return List<ITask> : the list task of session user the task of session user by some Filter properties
   */
  public static List<ITask> findAllTasks(final int startIndex, final int pageSize, final String sortField,
      final SortOrder sortOrder, final boolean isHistory, final IPropertyFilter<TaskProperty> taskFilter) {
    List<ITask> outTasks = Collections.emptyList();
    Ivy ivy = Ivy.getInstance();
    IQueryResult<ITask> queryResult;

    List<PropertyOrder<TaskProperty>> taskPropertyOrder =
        PropertyOrder.create(ch.ivy.addon.portalkit.util.TaskUtils.getTaskProperty(sortField),
            ch.ivy.addon.portalkit.util.TaskUtils.getTaskDirection(sortOrder));
    if (isHistory) {
      queryResult = ivy.session.findWorkedOnTasks(taskFilter, taskPropertyOrder, startIndex, pageSize, true);
    } else {
      if (checkReadAllTasksPermission()) {
        IPropertyFilter<TaskProperty> filter =
            Ivy.wf().createTaskPropertyFilter(TaskProperty.STATE, RelationalOperator.EQUAL,
                TaskState.SUSPENDED.intValue());
        filter = filter.or(TaskProperty.STATE, RelationalOperator.EQUAL, TaskState.RESUMED.intValue());
        filter = filter.or(TaskProperty.STATE, RelationalOperator.EQUAL, TaskState.PARKED.intValue());
        filter = filter.or(TaskProperty.STATE, RelationalOperator.EQUAL, TaskState.DELAYED.intValue());
        queryResult = Ivy.wf().findTasks(filter, taskPropertyOrder, startIndex, pageSize, true);
        outTasks = queryResult.getResultList();
      }
    }

    return outTasks;

  }
}
