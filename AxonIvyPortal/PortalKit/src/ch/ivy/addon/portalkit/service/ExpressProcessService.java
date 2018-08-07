package ch.ivy.addon.portalkit.service;


import java.util.Collections;
import java.util.List;

import ch.ivy.addon.portalkit.bo.ExpressProcess;

public class ExpressProcessService extends AbstractExpressService<ExpressProcess> {

  public List<ExpressProcess> findAllOrderByName() {
    if (repo().search(getType()).execute().totalCount() > 0) {
      return repo().search(getType()).orderBy().textField("processName").ascending().execute().getAll();
    }
    return Collections.emptyList();
  }

  @Override
  public Class<ExpressProcess> getType() {
    return ExpressProcess.class;
  }
}
