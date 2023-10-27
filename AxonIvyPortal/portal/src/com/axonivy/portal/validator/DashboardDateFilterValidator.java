package com.axonivy.portal.validator;

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

@FacesValidator(value = "dashboardDateFilterValidator")
public class DashboardDateFilterValidator implements Validator {

  private static final String MESSAGE_PREFIX_PATTERN = "%s(%d)";
  private static final String MESSAGES_COMPONENT_ID = "widget-configuration-form:new-widget-configuration-component:filter-messages";

  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
  
    DashboardFilter filter = (DashboardFilter) component.getAttributes().get("filter");
    Integer filterIndex = Optional.ofNullable((Integer)component.getAttributes().get("filterIndex")).orElse(0);

    switch (filter.getOperator()) {
      case BETWEEN -> validateBetweenOperator((Date)value, filter, filterIndex, context, component);
      case NOT_BETWEEN -> validateBetweenOperator((Date)value, filter, filterIndex, context, component);
      default -> validateDefaultOperator((Date)value, filter, filterIndex, context, component);
    };
  }

  private void validateBetweenOperator(Date value, DashboardFilter filter, int filterIndex, FacesContext context, UIComponent component) {
    if (value == null) {
      context.addMessage(
          MESSAGES_COMPONENT_ID,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, getRequiredMessage(filter.getField(), filterIndex), null));
      invalidate(context, component);
      return;
    }

    if (component.getId().contentEquals("to-date")) {
      FacesMessage error = validateFromToValues(filter.getFromDate(), value, filter.getField(), filterIndex);
      if (error != null) {
        context.addMessage(MESSAGES_COMPONENT_ID, error);
        invalidate(context, component);
        return;
      }
    }
  }

  private void validateDefaultOperator(Date value, DashboardFilter filter, int filterIndex, FacesContext context, UIComponent component) {
    if (value == null) {
      context.addMessage(
          MESSAGES_COMPONENT_ID,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, getRequiredMessage(filter.getField(), filterIndex), null));
      invalidate(context, component);
    }
  }

  private void invalidate(FacesContext context, UIComponent component) {
    context.validationFailed();
    UIInput input = (UIInput) component;
    input.setValid(false);
  }

  private FacesMessage validateFromToValues(Date from, Date to, String field, int index) {
    if(from != null && from.compareTo(to) > 0) {
      return new FacesMessage(FacesMessage.SEVERITY_ERROR, getFromToViolationError(field, index), null);
    }
    return null;
  }

  private String getMessagePrefix(String field, int index) {
    return String.format(MESSAGE_PREFIX_PATTERN, DashboardStandardCaseColumn.findBy(Optional.ofNullable(field).orElse("")).getLabel(), index + 1);
  }

  public String getWrongFormatMessage(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index), Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/wrongDateFormat"));
  }

  public String getRequiredMessage(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index), Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/requiredFieldMessage"));
  }

  public String getFromToViolationError(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index), Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/dateFromBiggerThanTo"));
  }
}
