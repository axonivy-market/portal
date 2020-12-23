package ch.ivy.addon.portalkit.validator;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("duplicateFieldValidator")
public class DuplicateFieldValidator implements Validator {

  @SuppressWarnings("unchecked")
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    List<String> fields = (List<String>) component.getAttributes().getOrDefault("existingFields", new ArrayList<>());
    if (fields.contains(value)) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The " + value + " field has already been existing", null);
      FacesContext.getCurrentInstance().addMessage(component.getClientId(), msg);
      throw new ValidatorException(msg);
    }
  }

}
