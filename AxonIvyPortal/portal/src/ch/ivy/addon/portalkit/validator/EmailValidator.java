package ch.ivy.addon.portalkit.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator<Object> {
  public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  private static final Pattern EMAIL_VALIDATION_PATTERN = Pattern.compile(EMAIL_PATTERN);

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    String strValue = value == null ? null : value.toString();
    Matcher matcher = EMAIL_VALIDATION_PATTERN.matcher(strValue);
    if (!matcher.matches()) {
      FacesMessage message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/emailSetting/invalidEmailFormat"), null);
      throw new ValidatorException(message);
    }
  }

}
