package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.bo.AdhocHistory;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;

public class AdhocHistoryService extends BusinessDataService<AdhocHistory> {

  private static final String ORIGINAL_TASK_ID = "originalTaskId";

  @Override
  public Class<AdhocHistory> getType() {
    return AdhocHistory.class;
  }
  
  public List<AdhocHistory> getHistoriesByTaskID(long taskID) {
    Filter<AdhocHistory> query = repo().search(getType()).numberField(ORIGINAL_TASK_ID).isEqualTo(taskID);
    Result<AdhocHistory> queryResult = query.execute();
    long totalCount = queryResult.totalCount();
    if(totalCount > LIMIT_10) {
      queryResult = query.limit(Math.toIntExact(totalCount)).execute();
    }
    return queryResult.getAll();
  }
  
  public boolean hasAdhocHistory(long taskID) {
    Filter<AdhocHistory> query = repo().search(getType()).numberField(ORIGINAL_TASK_ID).isEqualTo(taskID);
    return query.limit(1).execute().count() > 0;
  }

}
