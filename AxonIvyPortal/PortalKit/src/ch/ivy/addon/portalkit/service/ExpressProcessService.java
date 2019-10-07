package ch.ivy.addon.portalkit.service;


import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivyteam.ivy.business.data.store.search.Filter;

public class ExpressProcessService extends BusinessDataService<ExpressProcess> {

  public List<ExpressProcess> findReadyToExecuteProcessOrderByName() {
    List<ExpressProcess> result = repo().search(getType()).orderBy().textField("processName").ascending().limit(100).execute().getAll();
    return result.stream().filter(ExpressProcess::isReadyToExecute).collect(Collectors.toList());
  }
  
  public List<ExpressProcess> findExpressProcessByName(String processName) {
    Filter<ExpressProcess> filter = repo().search(getType()).textField("processName").isEqualToIgnoringCase(processName);
    return filter.limit(100).execute().getAll();
  }
  
  @Override
  public Class<ExpressProcess> getType() {
    return ExpressProcess.class;
  }
}