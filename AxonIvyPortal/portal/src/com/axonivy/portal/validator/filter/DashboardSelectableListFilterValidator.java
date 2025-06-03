package com.axonivy.portal.validator.filter;

import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectMany;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator(value = "dashboardSelectableListFilterValidator")
public class DashboardSelectableListFilterValidator implements Validator {

  private static final String MESSAGE_PREFIX_PATTERN = "%s(%d)";
  private static final String PRIORITY = "priority";

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    BaseFilter filter = (BaseFilter) component.getAttributes().get("filter");
    Integer filterIndex = Optional.ofNullable((Integer)component.getAttributes().get("filterIndex")).orElse(0);
    String messageComponentId = Optional.ofNullable((String)component.getAttributes().get("messageId")).orElse(null);
    String componentToValidate =  Optional.ofNullable((String)component.getAttributes().get("componentToValidate")).orElse(null);

    validateDefaultOperator((String)value, filter, filterIndex, component, messageComponentId, componentToValidate);
  }

  private void validateDefaultOperator(String value, BaseFilter filter, int filterIndex, UIComponent component, String messageComponentId, String componentToValidate) {
    if (StringUtils.isBlank(value)) {
      FacesContext.getCurrentInstance().addMessage(
          messageComponentId,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, getRequiredMessage(filter.getField(), filterIndex), null));
      invalidate(component, componentToValidate);
    }
  }

  private void invalidate(UIComponent component, String componentToValidate) {
    UIComponent applicationSelection = component.getParent().findComponent(componentToValidate);
    FacesContext.getCurrentInstance().validationFailed();
    UISelectMany input = (UISelectMany) applicationSelection;
    input.setValid(false);
  }

  private String getMessagePrefix(String field, int index) {
    if (PRIORITY.equals(field)) {
      return Optional.ofNullable(DashboardStandardTaskColumn.findBy(Optional.ofNullable(field).orElse("")))
          .map(c -> String.format(MESSAGE_PREFIX_PATTERN, c.getLabel(), index + 1)).orElse("");
    }
    return Optional.ofNullable(DashboardStandardCaseColumn.findBy(Optional.ofNullable(field).orElse("")))
        .map(c -> String.format(MESSAGE_PREFIX_PATTERN, c.getLabel(), index + 1)).orElse("");
  }

  public String getRequiredMessage(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index), Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/requiredFieldMessage"));
  }
}
