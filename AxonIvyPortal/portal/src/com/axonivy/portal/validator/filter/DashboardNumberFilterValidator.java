package com.axonivy.portal.validator.filter;

import java.math.BigDecimal;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;

import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator(value = "dashboardNumberFilterValidator")
public class DashboardNumberFilterValidator implements Validator {

  private static final String MESSAGE_PREFIX_PATTERN = "%s(%d)";

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    DashboardFilter filter = (DashboardFilter) component.getAttributes().get("filter");
    Integer filterIndex = Optional.ofNullable((Integer) component.getAttributes().get("filterIndex")).orElse(0);
    String messageComponentId = Optional.ofNullable((String) component.getAttributes().get("messageId")).orElse(null);

    if (!validateDefaultOperator(value, filter, filterIndex, component, messageComponentId)) {
      return;
    }

    String valueString = String.valueOf(value);
    BigDecimal valueNumber = NumberUtils.toScaledBigDecimal(valueString);

    switch (filter.getOperator()) {
    case BETWEEN -> validateBetweenOperator(valueNumber, filter, filterIndex, component, messageComponentId);
    case NOT_BETWEEN -> validateBetweenOperator(valueNumber, filter, filterIndex, component, messageComponentId);
    default -> {
    }
    }
    
  }

  private void validateBetweenOperator(BigDecimal value, DashboardFilter filter, int filterIndex, UIComponent component,
      String messageComponentId) {
    if (component.getId().contentEquals("to-number")) {
      BigDecimal fromNumber = BigDecimal.valueOf(NumberUtils.toDouble(filter.getFrom()));
      FacesMessage error = validateFromToValues(fromNumber, value, filter.getField(), filterIndex);
      if (error != null) {
        FacesContext.getCurrentInstance().addMessage(messageComponentId, error);
        invalidate(component);
        return;
      }
    }
  }

  private boolean validateDefaultOperator(Object value, DashboardFilter filter, int filterIndex, UIComponent component,
      String messageComponentId) {
    if (value == null) {
      FacesContext.getCurrentInstance().addMessage(messageComponentId,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, getRequiredMessage(filter.getField(), filterIndex), null));
      invalidate(component);
      return false;
    }
    return true;
  }

  private void invalidate(UIComponent component) {
    FacesContext.getCurrentInstance().validationFailed();
    UIInput input = (UIInput) component;
    input.setValid(false);
  }

  private FacesMessage validateFromToValues(BigDecimal from, BigDecimal to, String field, int index) {
    if (from != null && from.compareTo(to) > 0) {
      return new FacesMessage(FacesMessage.SEVERITY_ERROR, getFromToViolationError(field, index), null);
    }
    return null;
  }

  private String getMessagePrefix(String field, int index) {
    return Optional.ofNullable(FilterFieldFactory.findBy(Optional.ofNullable(field).orElse("")))
        .map(c -> String.format(MESSAGE_PREFIX_PATTERN, c.getLabel(), index + 1)).orElse("");
  }

  public String getWrongFormatMessage(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/wrongDateFormat"));
  }

  public String getRequiredMessage(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/requiredFieldMessage"));
  }

  public String getFromToViolationError(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/numberFromBiggerThanTo"));
  }
}
