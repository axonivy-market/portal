package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public final class ExecutingExpressProcessUtils {
  public static final String TASK_GROUP_ID_KEY = "expressGroupId";

  private ExecutingExpressProcessUtils() {}

  @SuppressWarnings("unchecked")
  public static <T> List<T> getAttributesOfTasks(String groupId, String attribute) {
    return IvyExecutor.executeAsSystem(() -> {
      TaskQuery query =
          TaskQuery.create().where().caseId().isEqual(Ivy.wfCase().getId()).and().customField().textField(TASK_GROUP_ID_KEY)
              .isEqual(groupId).orderBy().endTimestamp();
      List<ITask> tasks = Ivy.wf().getTaskQueryExecutor().getResults(query);
      List<T> result = new ArrayList<>();
      for (ITask task : tasks) {
        try {
          result.add((T) task.getEndProcessData().get(attribute));
        } catch (PersistencyException | NoSuchFieldException e) {
          throw new PortalException(e);
        }
      }
      return result;
    });
  }
  
  @SuppressWarnings("unchecked")
  public static <T> List<T> getExpressTaskEndProcessData(Long caseId, String parentCategoryName) {
    return IvyExecutor.executeAsSystem(() -> {
      TaskQuery query = buildExpressTaskQuery(caseId, parentCategoryName);
      query.orderBy().endTimestamp();
      List<ITask> tasks = Ivy.wf().getTaskQueryExecutor().getResults(query);
      List<T> result = new ArrayList<>();
      for (ITask task : tasks) {
        try {
          if (task.getEndProcessData() != null) {
            result.add((T) task.getEndProcessData());
          }
        } catch (PersistencyException e) {
          throw new PortalException(e);
        }
      }
      return result;
    });
  }
  
  private static TaskQuery buildExpressTaskQuery(Long caseId, String parentCategoryName) {
    String startingWithCategory = String.format("%s%%", parentCategoryName);
    TaskQuery query = TaskQuery.create().where().businessCaseId().isEqual(caseId)
        .and().category().isLike(startingWithCategory);
    return query;
  }

  public static ICase getExpressCase(long caseId) {
    return IvyExecutor.executeAsSystem(() -> {
      CaseQuery query = CaseQuery.businessCases().where().caseId().isEqual(caseId)
          .and().customField().stringField(CustomFields.IS_EXPRESS_PROCESS).isEqual("true");
      List<ICase> result = Ivy.wf().getCaseQueryExecutor().getResults(query);
      return CollectionUtils.isEmpty(result) ? null : result.get(0);
    });
  }
}
