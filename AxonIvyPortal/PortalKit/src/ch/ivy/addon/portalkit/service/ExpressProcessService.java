package ch.ivy.addon.portalkit.service;


import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.ExpressProcess;

public class ExpressProcessService extends BusinessDataService<ExpressProcess> {

  public List<ExpressProcess> findReadyToExecuteProcessOrderByName() {
    List<ExpressProcess> result = repo().search(getType()).orderBy().textField("processName").ascending().execute().getAll();
    return result.stream().filter(process -> process.isReadyToExecute()).collect(Collectors.toList());
  }

  @Override
  public Class<ExpressProcess> getType() {
    return ExpressProcess.class;
  }
}
