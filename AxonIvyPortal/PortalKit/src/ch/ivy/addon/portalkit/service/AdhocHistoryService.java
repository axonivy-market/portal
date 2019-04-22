package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.bo.AdhocHistory;
import ch.ivyteam.ivy.business.data.store.search.Filter;

public class AdhocHistoryService extends BusinessDataService<AdhocHistory> {

  @Override
  public Class<AdhocHistory> getType() {
    return AdhocHistory.class;
  }
  
  public List<AdhocHistory> getHistoriesByTaskID(long taskID) {
    Filter<AdhocHistory> query = repo().search(getType()).numberField("originalTaskId").isEqualTo(taskID);
    return query.limit(1000).execute().getAll();
  }
  
  public boolean hasAdhocHistory(long taskID) {
    Filter<AdhocHistory> query = repo().search(getType()).numberField("originalTaskId").isEqualTo(taskID);
    return query.limit(1).execute().count() > 0;
  }

}
