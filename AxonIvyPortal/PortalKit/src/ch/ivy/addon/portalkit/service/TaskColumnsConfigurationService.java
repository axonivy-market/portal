package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.bo.TaskColumnsConfigurationData;
import ch.ivyteam.ivy.business.data.store.search.Filter;

public class TaskColumnsConfigurationService extends BusinessDataService<TaskColumnsConfigurationData> {

  @Override
  public Class<TaskColumnsConfigurationData> getType() {
    return TaskColumnsConfigurationData.class;
  }

  public TaskColumnsConfigurationData getConfiguration(Long serverId, Long applicationId, Long userId){
    Filter<TaskColumnsConfigurationData> query;
    if(serverId != null){
      query =
          repo().search(getType())
          .numberField("serverId").isEqualTo(serverId)
          .and().numberField("applicationId").isEqualTo(applicationId)
          .and().numberField("userId").isEqualTo(userId);
    } else {
      query =
          repo().search(getType())
          .numberField("applicationId").isEqualTo(applicationId)
          .and().numberField("userId").isEqualTo(userId);
    }
    return query.execute().getFirst();
  }
}
