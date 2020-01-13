package ch.ivy.addon.portalkit.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;

public class ExpressProcessService extends BusinessDataService<ExpressProcess> {
  
  private static final String PROCESS_TYPE = "processType";

  public List<ExpressProcess> findReadyToExecuteProcessOrderByName() {
    Result<ExpressProcess> queryResult = repo().search(getType()).orderBy().textField("processName").ascending().limit(LIMIT_20).execute();
    long totalCount = queryResult.totalCount();
    if(totalCount > LIMIT_20) {
      queryResult = repo().search(getType()).orderBy().textField("processName").ascending().limit(Math.toIntExact(totalCount)).execute();
    }
    return queryResult.getAll().stream().filter(ExpressProcess::isReadyToExecute).collect(Collectors.toList());
  }
  
  public ExpressProcess findExpressProcessByName(String processName) {
    Filter<ExpressProcess> filter = repo().search(getType()).textField("processName").isEqualToIgnoringCase(processName);
    return filter.limit(1).execute().getFirst();
  }
  
  @Override
  public Class<ExpressProcess> getType() {
    return ExpressProcess.class;
  }

  public long totalCounts() {
    return repo().search(getType()).execute().totalCount();
  }

  public List<ExpressProcess> findReadyToExecuteProcessOrderByName(int first, int pageSize, String processType) {
    try {
      Filter<ExpressProcess> publicFilterQuery =
          repo().search(getType()).textField(PROCESS_TYPE).isEqualToIgnoringCase(processType);
      Result<ExpressProcess> queryResult =
          publicFilterQuery.orderBy().textField("processName").ascending().limit(first, pageSize).execute();
      return queryResult.getAll().stream().filter(ExpressProcess::isReadyToExecute).collect(Collectors.toList());
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return new ArrayList<>();
  }

}