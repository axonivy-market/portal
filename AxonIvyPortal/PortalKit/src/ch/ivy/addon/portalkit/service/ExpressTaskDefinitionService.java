package ch.ivy.addon.portalkit.service;


import java.util.List;

import ch.ivy.addon.portalkit.bo.ExpressTaskDefinition;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;

public class ExpressTaskDefinitionService extends BusinessDataService<ExpressTaskDefinition> {

  public void deleteByProcessId(String processId) {
    List<ExpressTaskDefinition> taskDefinitions = findByProcessId(processId);
    for (ExpressTaskDefinition taskDefinition : taskDefinitions) {
      repo().delete(taskDefinition);
    }
  }

  public List<ExpressTaskDefinition> findByProcessId(String processId) {
    Filter<ExpressTaskDefinition> query = repo().search(getType()).textField("processID").isEqualToIgnoringCase(processId);
    Result<ExpressTaskDefinition> queryResult = query.execute();
    long totalCount = queryResult.totalCount();
    if(totalCount > LIMIT_10) {
      queryResult = query.limit(Math.toIntExact(totalCount)).execute();
    }
    return queryResult.getAll();
  }

  @Override
  public Class<ExpressTaskDefinition> getType() {
    return ExpressTaskDefinition.class;
  }
}
