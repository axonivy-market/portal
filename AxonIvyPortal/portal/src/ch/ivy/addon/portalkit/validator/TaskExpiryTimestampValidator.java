package ch.ivy.addon.portalkit.validator;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator("taskExpiryTimestampValidator")
public class TaskExpiryTimestampValidator implements Validator<Object> {

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (Objects.isNull(value)) {
      return;
    }
    LocalDateTime expiryTimestamp = ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    if (expiryTimestamp.isBefore(LocalDateTime.now())) {
      FacesMessage message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskDetails/failedExpiryValidation"), null);
      context.addMessage("expiry-calendar", message);
      throw new ValidatorException(message);
    }
  }

}
