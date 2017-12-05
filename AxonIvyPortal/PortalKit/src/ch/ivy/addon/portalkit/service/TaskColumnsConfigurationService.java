package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.bo.TaskColumnsConfigurationData;
import ch.ivyteam.ivy.business.data.store.search.Filter;

public class TaskColumnsConfigurationService extends BusinessDataService<TaskColumnsConfigurationData> {

  @Override
  public Class<TaskColumnsConfigurationData> getType() {
    return TaskColumnsConfigurationData.class;
  }

  public TaskColumnsConfigurationData getConfiguration(Long userId){
    Filter<TaskColumnsConfigurationData> query =
        repo().search(getType()).numberField("userId").isEqualTo(userId);
    return query.execute().getFirst();
  }
}
