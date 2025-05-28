package com.axonivy.portal.validator.filter;

import java.util.ArrayList;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;

import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator(value = "dashboardDefaultListFilterValidator")
public class DashboardDefaultListFilterValidator implements Validator {

  private static final String MESSAGE_PREFIX_PATTERN = "%s(%d)";

  @Override
  @SuppressWarnings("unchecked")
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

    BaseFilter filter = (BaseFilter) component.getAttributes().get("filter");
    Integer filterIndex = Optional.ofNullable((Integer) component.getAttributes().get("filterIndex")).orElse(0);
    String messageComponentId = Optional.ofNullable((String) component.getAttributes().get("messageId")).orElse(null);
    validateDefaultOperator((ArrayList<String>) value, filter, filterIndex, component, messageComponentId);
  }

  private void validateDefaultOperator(ArrayList<String> value, BaseFilter filter, int filterIndex,
      UIComponent component, String messageComponentId) {
    if (CollectionUtils.isEmpty(value)) {
      FacesContext.getCurrentInstance().addMessage(messageComponentId,
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
    String label = Optional.ofNullable(FilterFieldFactory.findBy(field)).map(FilterField::getLabel).orElse("");
    return String.format(MESSAGE_PREFIX_PATTERN, label, index + 1);
  }

  public String getRequiredMessage(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/requiredFieldMessage"));
  }
}
