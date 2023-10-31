package com.axonivy.portal.validator.filter;

import java.util.Date;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator(value = "dashboardTextFilterValidator")
public class DashboardTextFilterValidator implements Validator {

  private static final String MESSAGE_PREFIX_PATTERN = "%s(%d)";

  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
  
    DashboardFilter filter = (DashboardFilter) component.getAttributes().get("filter");
    Integer filterIndex = Optional.ofNullable((Integer)component.getAttributes().get("filterIndex")).orElse(0);
    String messageComponentId = Optional.ofNullable((String)component.getAttributes().get("messageId")).orElse(null);
    validateDefaultOperator((Date)value, filter, filterIndex, component, messageComponentId);
  }

  private void validateDefaultOperator(Date value, DashboardFilter filter, int filterIndex, UIComponent component, String messageComponentId) {
    if (value == null) {
      FacesContext.getCurrentInstance().addMessage(
          messageComponentId,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, getRequiredMessage(filter.getField(), filterIndex), null));
      invalidate(component);
    }
  }

  private void invalidate(UIComponent component) {
    FacesContext.getCurrentInstance().validationFailed();
    UIInput input = (UIInput) component;
    input.setValid(false);
  }

  private String getMessagePrefix(String field, int index) {
    return String.format(MESSAGE_PREFIX_PATTERN, DashboardStandardCaseColumn.findBy(Optional.ofNullable(field).orElse("")).getLabel(), index + 1);
  }

  public String getRequiredMessage(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index), Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/requiredFieldMessage"));
  }
}
