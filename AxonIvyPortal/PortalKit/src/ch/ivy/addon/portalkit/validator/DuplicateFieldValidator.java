package ch.ivy.addon.portalkit.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator("duplicateFieldValidator")
public class DuplicateFieldValidator implements Validator {

  @SuppressWarnings("unchecked")
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    List<String> fields = (List<String>) component.getAttributes().getOrDefault("existingFields", new ArrayList<>());
    if (fields.contains(value)) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/existingField", Arrays.asList(value)), null);
      FacesContext.getCurrentInstance().addMessage(component.getClientId(), msg);
      throw new ValidatorException(msg);
    }
  }

}
