package ch.ivy.addon.portalkit.validator;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator("taskExpiryTimestampValidator")
public class TaskExpiryTimestampValidator implements Validator {

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (Objects.isNull(value)) {
      return;
    }
    LocalDateTime expiryTimestamp = ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    if (expiryTimestamp.isBefore(LocalDateTime.now())) {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskDetails/failedExpiryValidation"), null);
      context.addMessage("expiry-calendar", message);
      throw new ValidatorException(message);
    }
  }

}
