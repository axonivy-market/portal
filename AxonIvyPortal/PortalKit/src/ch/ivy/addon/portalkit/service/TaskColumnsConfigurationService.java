package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.bo.TaskColumnsConfiguration;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskColumnsConfigurationService extends BusinessDataService<TaskColumnsConfiguration> {

  @Override
  public Class<TaskColumnsConfiguration> getType() {
    return TaskColumnsConfiguration.class;
  }

  public TaskColumnsConfiguration getConfiguration(Long applicationId, Long userId, Long processModelId) {
    Filter<TaskColumnsConfiguration> query =
        repo().search(getType()).numberField("applicationId").isEqualTo(applicationId).and().numberField("userId")
            .isEqualTo(userId).and().numberField("processModelId").isEqualTo(processModelId);
    return query.limit(1).execute().getFirst();
  }

  public long getTotalCount(Long applicationId) {
    try {
      Filter<TaskColumnsConfiguration> query =
          repo().search(getType()).numberField("applicationId").isEqualTo(applicationId);
      return query.execute().totalCount();
    } catch (Exception e) {
      Ivy.log().error(e);
      return 0;
    }
  }

  public List<TaskColumnsConfiguration> getAllConfiguration(Long applicationId, int limitFrom, int limitSize) {
    try {
      Filter<TaskColumnsConfiguration> query =
          repo().search(getType()).numberField("applicationId").isEqualTo(applicationId);
      Result<TaskColumnsConfiguration> queryResult = query.limit(limitFrom, limitSize).execute();
      return queryResult.getAll();
    } catch (Exception e) {
      Ivy.log().error(e);
      return new ArrayList<>();
    }
  }
}
