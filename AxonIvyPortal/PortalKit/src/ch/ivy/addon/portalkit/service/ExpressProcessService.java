package ch.ivy.addon.portalkit.service;


import java.util.List;

import ch.ivy.addon.portalkit.bo.ExpressProcess;

public class ExpressProcessService extends AbstractExpressService<ExpressProcess> {

  public List<ExpressProcess> findAllOrderByName() {
    return repo().search(getType()).orderBy().field("processName").ascending().execute().getAll();
  }

  @Override
  public Class<ExpressProcess> getType() {
    return ExpressProcess.class;
  }
}
