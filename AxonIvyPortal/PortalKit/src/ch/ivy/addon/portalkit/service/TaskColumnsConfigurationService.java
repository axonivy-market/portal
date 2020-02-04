package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.bo.TaskColumnsConfigurationData;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskColumnsConfigurationService extends BusinessDataService<TaskColumnsConfigurationData> {
  
  private static final String SERVER_ID = "serverId";
  private static final String APP_ID = "applicationId";
  private static final String USER_ID = "userId";
  private static final String TASK_COLUMN_CONFIG_ID = "taskColumnsConfigDataId";

  @Override
  public Class<TaskColumnsConfigurationData> getType() {
    return TaskColumnsConfigurationData.class;
  }

  public TaskColumnsConfigurationData getConfiguration(Long serverId, Long applicationId, Long userId, Long taskColumnsConfigDataId){
    Filter<TaskColumnsConfigurationData> query;
    if(serverId != null){
      query =
          repo().search(getType())
          .numberField(SERVER_ID).isEqualTo(serverId)
          .and().numberField(APP_ID).isEqualTo(applicationId)
          .and().numberField(USER_ID).isEqualTo(userId)
          .and().numberField(TASK_COLUMN_CONFIG_ID).isEqualTo(taskColumnsConfigDataId);
    } else {
      query =
          repo().search(getType())
          .numberField(APP_ID).isEqualTo(applicationId)
          .and().numberField(USER_ID).isEqualTo(userId)
          .and().numberField(TASK_COLUMN_CONFIG_ID).isEqualTo(taskColumnsConfigDataId);
    }
    return query.limit(1).execute().getFirst();
  }

  /**
   * Get total count of Task configuration by server id
   * @param serverId
   * @param applicationId
   * @return totalCount
   */
  public long getTotalTaskConfigCountByAppId(Long serverId, Long applicationId) {
    try {
      Filter<TaskColumnsConfigurationData> query;
      if(serverId != null){
        query =
            repo().search(getType())
            .numberField(SERVER_ID).isEqualTo(serverId)
            .and().numberField(APP_ID).isEqualTo(applicationId);
      } else {
        query =
            repo().search(getType())
            .numberField(APP_ID).isEqualTo(applicationId);
      }
      return query.execute().totalCount();
    } catch (Exception e) {
      Ivy.log().error(e);
      return 0;
    }
  }

  /**
   * Get list of Task configuration by application id 
   * @param serverId
   * @param applicationId
   * @param firstIndex is first entity
   * @param offset is size of return list
   * @return list of task configuration
   */
  public List<TaskColumnsConfigurationData> getTaskConfigurationWithOffset(Long serverId, Long applicationId, int firstIndex, int offset) {
    try {
      Filter<TaskColumnsConfigurationData> query;
      if(serverId != null){
        query =
            repo().search(getType())
            .numberField(SERVER_ID).isEqualTo(serverId)
            .and().numberField(APP_ID).isEqualTo(applicationId);
      } else {
        query =
            repo().search(getType())
            .numberField(APP_ID).isEqualTo(applicationId);
      }
      Result<TaskColumnsConfigurationData> queryResult = query.limit(firstIndex, offset).execute();
      return queryResult.getAll();
    } catch (Exception e) {
      Ivy.log().error(e);
      return new ArrayList<>();
    }
  }
}
