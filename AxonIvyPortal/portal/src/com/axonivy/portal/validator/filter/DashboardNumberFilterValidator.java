package com.axonivy.portal.validator.filter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "dashboardNumberFilterValidator")
public class DashboardNumberFilterValidator implements Validator {

  @Override
  public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
    // TODO Auto-generated method stub
    
  }

}
