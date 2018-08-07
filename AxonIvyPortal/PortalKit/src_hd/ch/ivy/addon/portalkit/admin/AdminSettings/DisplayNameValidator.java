package ch.ivy.addon.portalkit.admin.AdminSettings;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator(value = "displayNameValidator")
public class DisplayNameValidator implements Validator {

  @Override
  public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
    if (doValidation(facesContext)) {
      if (value == null || StringUtils.isBlank(value.toString())) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co(
            "/ch.ivy.addon.portalkit.ui.jsf/adminSettings/appDisplayNameRequiredMsg"), "");
        throw new ValidatorException(message);
      }
    }
  }

  private boolean doValidation(FacesContext facesContext) {
    String parameter = facesContext.getExternalContext().getRequestParameterMap().get("skipDisplayNameInputValidation");
    if ("true".equals(parameter)) {
      return false;
    }
    return true;
  }
}
