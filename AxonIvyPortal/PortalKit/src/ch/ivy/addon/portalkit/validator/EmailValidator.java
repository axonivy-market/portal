package ch.ivy.addon.portalkit.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {
  public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    String strValue = value == null ? null : value.toString();
    Matcher matcher = pattern.matcher(strValue);
    if (!matcher.matches()) {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/emailSetting/invalidEmailFormat"), null);
      context.addMessage(component.getClientId(), message);
      throw new ValidatorException(message);
    }
  }

}
