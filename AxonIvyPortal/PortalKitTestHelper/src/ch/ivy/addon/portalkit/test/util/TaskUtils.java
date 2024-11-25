package ch.ivy.addon.portalkit.test.util;

import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskUtils {

  public static void destroyAllCase() throws Exception {
    Sudo.call(() -> {
      List<ICase> result = Ivy.wf().getCaseQueryExecutor()
          .getResults(CaseQuery.create().where().state().isNotIn(CaseState.DESTROYED, CaseState.DONE));
      for (ICase iCaze : result) {
        iCaze.destroy();
      }
      return null;
    });
  }

  public static void deleteCompletedCases() throws Exception {
    Sudo.call(() -> {
      List<ICase> result = Ivy.wf().getCaseQueryExecutor()
          .getResults(CaseQuery.create().where().state().isIn(CaseState.DESTROYED, CaseState.DONE));
      for (ICase iCaze : result) {
        Ivy.wf().deleteCompletedCase(iCaze);
      }
      return null;
    });
  }

  public static void deleteDestroyedCases() throws Exception {
    Sudo.call(() -> {
      List<ICase> result = Ivy.wf().getCaseQueryExecutor()
          .getResults(CaseQuery.create().where().state().isEqual(CaseState.DESTROYED));
      for (ICase iCaze : result) {
        Ivy.wf().deleteCompletedCase(iCaze);
      }
      return null;
    });
  }

  public static void destroyTaskByCustomField(String customFieldName) throws Exception {
    Sudo.call(() -> {
      ITask selectedTask =
          TaskQuery.create().where().customField().stringField(customFieldName).isNotNull().executor().firstResult();
      if (selectedTask != null) {
        selectedTask.destroy();
        selectedTask.customFields().stringField(customFieldName).delete();
      }
      return null;
    });
  }
}
