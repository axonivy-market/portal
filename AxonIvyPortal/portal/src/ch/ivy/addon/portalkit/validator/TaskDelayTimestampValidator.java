package ch.ivy.addon.portalkit.validator;

import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator("taskDelayTimestampValidator")
public class TaskDelayTimestampValidator implements Validator {

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (Objects.isNull(value)) {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskDetails/failedDelayValidation"), null);
      context.addMessage("delay-date-calendar", message);
      throw new ValidatorException(message);
    }
  }

}
