package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.bo.TaskColumnsConfigurationData;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;

public class TaskColumnsConfigurationService extends BusinessDataService<TaskColumnsConfigurationData> {

  @Override
  public Class<TaskColumnsConfigurationData> getType() {
    return TaskColumnsConfigurationData.class;
  }

  public TaskColumnsConfigurationData getConfiguration(Long serverId, Long applicationId, Long userId, Long taskColumnsConfigDataId){
    Filter<TaskColumnsConfigurationData> query;
    if(serverId != null){
      query =
          repo().search(getType())
          .numberField("serverId").isEqualTo(serverId)
          .and().numberField("applicationId").isEqualTo(applicationId)
          .and().numberField("userId").isEqualTo(userId)
          .and().numberField("taskColumnsConfigDataId").isEqualTo(taskColumnsConfigDataId);
    } else {
      query =
          repo().search(getType())
          .numberField("applicationId").isEqualTo(applicationId)
          .and().numberField("userId").isEqualTo(userId)
          .and().numberField("taskColumnsConfigDataId").isEqualTo(taskColumnsConfigDataId);
    }
    return query.limit(1).execute().getFirst();
  }

  public List<TaskColumnsConfigurationData> getAllConfiguration(Long serverId, Long applicationId) {
    Filter<TaskColumnsConfigurationData> query;
    if(serverId != null){
      query =
          repo().search(getType())
          .numberField("serverId").isEqualTo(serverId)
          .and().numberField("applicationId").isEqualTo(applicationId);
    } else {
      query =
          repo().search(getType())
          .numberField("applicationId").isEqualTo(applicationId);
    }
    Result<TaskColumnsConfigurationData> queryResult = query.limit(LIMIT_100).execute();
    long totalCount = queryResult.totalCount();
    if(totalCount > LIMIT_100) {
      queryResult = query.limit(Math.toIntExact(totalCount)).execute();
    }
    return queryResult.getAll();
  }
}
