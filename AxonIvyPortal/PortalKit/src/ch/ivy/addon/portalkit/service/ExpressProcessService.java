package ch.ivy.addon.portalkit.service;


import java.util.List;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivyteam.ivy.business.data.store.search.Result;

public class ExpressProcessService extends BusinessDataService<ExpressProcess> {

  public List<ExpressProcess> findAllOrderByName() {
    Result<ExpressProcess> queryResult =
        repo().search(getType()).orderBy().textField("processName").ascending().limit(LIMIT_20).execute();
    long totalCount = queryResult.totalCount();
    if (totalCount > LIMIT_20) {
      queryResult =
          repo().search(getType()).orderBy().textField("processName").ascending().limit(Math.toIntExact(totalCount))
              .execute();
    }
    return queryResult.getAll();
  }

  @Override
  public Class<ExpressProcess> getType() {
    return ExpressProcess.class;
  }
}
