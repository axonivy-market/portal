package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.bo.TaskColumnsConfiguration;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskColumnsConfigurationService extends BusinessDataService<TaskColumnsConfiguration> {
  
  private static final String APP_ID = "applicationId";
  private static TaskColumnsConfigurationService instance;

  private TaskColumnsConfigurationService() {}

  public static TaskColumnsConfigurationService getInstance() {
    if (instance == null) {
      instance = new TaskColumnsConfigurationService();
    }
    return instance;
  }

  @Override
  public Class<TaskColumnsConfiguration> getType() {
    return TaskColumnsConfiguration.class;
  }

  public TaskColumnsConfiguration getConfiguration(Long applicationId, Long userId, Long processModelId) {
    Filter<TaskColumnsConfiguration> query =
        repo().search(getType()).numberField(APP_ID).isEqualTo(applicationId).and().numberField("userId")
            .isEqualTo(userId).and().numberField("processModelId").isEqualTo(processModelId);
    return query.limit(1).execute().getFirst();
  }

  /**
   * Get total count of Task configuration by application id
   * @param applicationId
   * @return totalCount
   */
  public long getTotalTaskConfigCountByAppId(Long applicationId) {
    try {
      Filter<TaskColumnsConfiguration> query =
          repo().search(getType()).numberField(APP_ID).isEqualTo(applicationId);
      return query.execute().totalCount();
    } catch (Exception e) {
      Ivy.log().error(e);
      return 0;
    }
  }

  /**
   * Get list of Task configuration by application id 
   * @param applicationId
   * @param firstIndex is first entity
   * @param offset is size of return list
   * @return list of task configuration
   */
  public List<TaskColumnsConfiguration> getTaskConfigurationWithOffset(Long applicationId, int firstIndex, int offset) {
    try {
      Filter<TaskColumnsConfiguration> query =
          repo().search(getType()).numberField(APP_ID).isEqualTo(applicationId);
      Result<TaskColumnsConfiguration> queryResult = query.limit(firstIndex, offset).execute();
      return queryResult.getAll();
    } catch (Exception e) {
      Ivy.log().error(e);
      return new ArrayList<>();
    }
  }
}
