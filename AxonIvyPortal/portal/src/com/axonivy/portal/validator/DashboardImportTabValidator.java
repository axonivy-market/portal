package com.axonivy.portal.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

@FacesValidator(value = "dashboardImportTabValidator")
public class DashboardImportTabValidator implements Validator {

  @Override
  public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
    if (doValidation(facesContext)) {
      if (value == null || StringUtils.isBlank(value.toString())) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
      }
    }
  }

  private boolean doValidation(FacesContext facesContext) {
    String parameter = facesContext.getExternalContext().getRequestParameterMap().get("skipValidator");
    if ("true".equals(parameter)) {
      return false;
    }
    return true;
  }
}
