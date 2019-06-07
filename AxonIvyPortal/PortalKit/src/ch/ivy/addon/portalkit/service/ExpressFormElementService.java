package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.bo.ExpressFormElement;

public class ExpressFormElementService extends BusinessDataService<ExpressFormElement> {

  public void deleteByProcessId(String processId) {
    List<ExpressFormElement> formElements = findByProcessId(processId);
    for (ExpressFormElement formElement : formElements) {
      repo().delete(formElement);
    }
  }

  public List<ExpressFormElement> findByProcessId(String processId) {
    return repo().search(ExpressFormElement.class).textField("processID").isEqualToIgnoringCase(processId).limit(100).execute().getAll();
  }

  @Override
  public Class<ExpressFormElement> getType() {
    return ExpressFormElement.class;
  }

}
