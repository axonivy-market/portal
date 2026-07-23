package ch.ivy.addon.portalkit.validator;

import java.util.Objects;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator("taskDelayTimestampValidator")
public class TaskDelayTimestampValidator implements Validator<Object> {

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (Objects.isNull(value)) {
      FacesMessage message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskDetails/failedDelayValidation"), null);
      context.addMessage("delay-date-calendar", message);
      throw new ValidatorException(message);
    }
  }

}
