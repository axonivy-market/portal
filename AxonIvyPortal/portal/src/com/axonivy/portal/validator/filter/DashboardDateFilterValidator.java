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

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator(value = "dashboardDateFilterValidator")
public class DashboardDateFilterValidator implements Validator {

  private static final String MESSAGE_PREFIX_PATTERN = "%s(%d)";
  private static final String EXPIRY_TIMESTAMP = "expiryTimestamp";

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
  
    BaseFilter filter = (BaseFilter) component.getAttributes().get("filter");
    Integer filterIndex = Optional.ofNullable((Integer)component.getAttributes().get("filterIndex")).orElse(0);
    String messageComponentId = Optional.ofNullable((String)component.getAttributes().get("messageId")).orElse(null);

    switch (filter.getOperator()) {
      case BETWEEN -> validateBetweenOperator((Date)value, filter, filterIndex, component, messageComponentId);
      case NOT_BETWEEN -> validateBetweenOperator((Date)value, filter, filterIndex, component, messageComponentId);
      default -> validateDefaultOperator((Date)value, filter, filterIndex, component, messageComponentId);
    }
  }

  private void validateBetweenOperator(Date value, BaseFilter filter, int filterIndex, UIComponent component, String messageComponentId) {
    if (value == null) {
      FacesContext.getCurrentInstance().addMessage(
          messageComponentId,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, getRequiredMessage(filter.getField(), filterIndex), null));
      invalidate(component);
      return;
    }

    if (component.getId().contentEquals("to-date")) {
      FacesMessage error = validateFromToValues(filter.getFromDate(), value, filter.getField(), filterIndex);
      if (error != null) {
        FacesContext.getCurrentInstance().addMessage(messageComponentId, error);
        invalidate(component);
        return;
      }
    }
  }

  private void validateDefaultOperator(Date value, BaseFilter filter, int filterIndex, UIComponent component, String messageComponentId) {
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

  private FacesMessage validateFromToValues(Date from, Date to, String field, int index) {
    if(from != null && from.compareTo(to) > 0) {
      return new FacesMessage(FacesMessage.SEVERITY_ERROR, getFromToViolationError(field, index), null);
    }
    return null;
  }

  private String getMessagePrefix(String field, int index) {
    if(EXPIRY_TIMESTAMP.equals(field)) {
      return String.format(MESSAGE_PREFIX_PATTERN, DashboardStandardTaskColumn.findBy(Optional.ofNullable(field).orElse("")).getLabel(), index + 1);
    }
    return String.format(MESSAGE_PREFIX_PATTERN,
        FilterFieldFactory.findBy(Optional.ofNullable(field).orElse("")).getLabel(), index + 1);
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
