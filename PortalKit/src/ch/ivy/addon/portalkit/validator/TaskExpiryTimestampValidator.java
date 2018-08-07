package ch.ivy.addon.portalkit.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("taskExpiryTimestampValidator")
public class TaskExpiryTimestampValidator implements Validator {

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (Objects.isNull(value)) {
      return;
    }
    LocalDate expiryTimestamp = ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    if (expiryTimestamp.isBefore(LocalDate.now())) {
      throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
          "Task expiry timestamp should not be in the past.", null));
    }
  }

}
