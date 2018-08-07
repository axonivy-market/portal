package ch.ivy.addon.portalkit.service;


import java.util.List;

import ch.ivy.addon.portalkit.bo.ExpressProcess;

public class ExpressProcessService extends BusinessDataService<ExpressProcess> {

  public List<ExpressProcess> findAllOrderByName() {
    return repo().search(getType()).orderBy().textField("processName").ascending().execute().getAll();
  }

  @Override
  public Class<ExpressProcess> getType() {
    return ExpressProcess.class;
  }
}
