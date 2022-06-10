package ch.ivy.addon.portalkit.test.util;

import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.IQueryResult;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.CaseProperty;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IPropertyFilter;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskProperty;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskUtils {

  public static List<ICase> destroyAllCase() {
    try {
      return Sudo.call(() -> {
        try {
          IPropertyFilter<CaseProperty> noFilter = null;
          IQueryResult<ICase> qr = Ivy.wf().findCases(noFilter, null, 0, -1, true);
          List<ICase> names = new ArrayList<ICase>();
          for (ICase ivyCase : qr.getResultList()) {
            try {
              if (ivyCase.getState().intValue() != TaskState.DESTROYED.intValue()
                  && ivyCase.getState().intValue() != TaskState.DONE.intValue()) {
                ivyCase.destroy();
              }
            } catch (Exception e1) {
              Ivy.log().error(e1);
            }
          }
          return names;
        } catch (Exception e2) {
          Ivy.log().error(e2);
          return null;
        }
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return null;
    }
  }

  public static boolean resumeFirstTask() {
    try {
      Sudo.call(() -> {
        try {
          IPropertyFilter<TaskProperty> filter = null;
          IQueryResult<ITask> result = Ivy.wf().findTasks(filter, null, 0, -1, true);
          List<ITask> tasks = result.getResultList();
          for (ITask task : tasks) {
            if (TaskState.SUSPENDED == task.getState()) {
              Ivy.session().resumeTask(task.getId());
              break;
            }
          }
          return true;
        } catch (Exception e) {
          return false;
        }
      });
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  public static void deleteCompletedCases() throws Exception {
    Sudo.call(() -> {
      IPropertyFilter<CaseProperty> noFilter = null;
      IQueryResult<ICase> qr = Ivy.wf().findCases(noFilter, null, 0, -1, true);
      for (ICase ivyCase : qr.getResultList()) {
        if (ivyCase.getState() == CaseState.DESTROYED || ivyCase.getState() == CaseState.DONE
            || ivyCase.getState() == CaseState.ZOMBIE) {
          Ivy.wf().deleteCompletedCase(ivyCase);
        }
      }
      return null;
    });
  }

  public static void destroyTaskByCustomField(String customFieldName) throws Exception {
    Sudo.call(() -> {
      TaskQuery taskQuery = TaskQuery.create().where().customField().stringField(customFieldName).isNotNull();
      ITask selectedTask =  Ivy.wf().getTaskQueryExecutor().getFirstResult(taskQuery);
      if (selectedTask != null) {
        ch.ivy.addon.portalkit.util.TaskUtils.destroyTaskById(selectedTask.getId());
        selectedTask.customFields().stringField(customFieldName).delete();
      }
      return null;
    });
  }
}
