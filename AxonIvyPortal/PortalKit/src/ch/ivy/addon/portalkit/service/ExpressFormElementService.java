package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.bo.ExpressFormElement;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;

public class ExpressFormElementService extends BusinessDataService<ExpressFormElement> {

  public void deleteByProcessId(String processId) {
    List<ExpressFormElement> formElements = findByProcessId(processId);
    for (ExpressFormElement formElement : formElements) {
      repo().delete(formElement);
    }
  }

  public List<ExpressFormElement> findByProcessId(String processId) {
    Filter<ExpressFormElement> query = repo().search(getType()).textField("processID").isEqualToIgnoringCase(processId);
    Result<ExpressFormElement> queryResult = query.limit(LIMIT_100).execute();
    long totalCount = queryResult.totalCount();
    if(totalCount > LIMIT_100) {
      queryResult = query.limit(Math.toIntExact(LIMIT_100)).execute();
    }
    return queryResult.getAll();
  }

  @Override
  public Class<ExpressFormElement> getType() {
    return ExpressFormElement.class;
  }

}
