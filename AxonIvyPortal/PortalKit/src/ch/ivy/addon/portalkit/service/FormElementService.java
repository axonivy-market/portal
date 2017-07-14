package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.bo.FormElement;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.BusinessDataRepository;
import ch.ivyteam.ivy.environment.Ivy;

public class FormElementService {

  private static FormElementService service = new FormElementService();

  public static FormElementService getInstance() {
    return service;
  }

  public BusinessDataInfo<FormElement> save(FormElement formElement) {
    return repo().save(formElement);
  }

  public FormElement findById(String id) {
    return repo().find(id, FormElement.class);
  }

  public void deleteByProcessId(String processId) {
    List<FormElement> formElements = findByProcessId(processId);
    for (FormElement formElement : formElements) {
      repo().delete(formElement);
    }
  }

  public List<FormElement> findByProcessId(String processId) {
    return repo().search(FormElement.class).textField("processID").isEqualToIgnoringCase(processId).execute().getAll();
  }

  private BusinessDataRepository repo() {
    return Ivy.repo();
  }
}
