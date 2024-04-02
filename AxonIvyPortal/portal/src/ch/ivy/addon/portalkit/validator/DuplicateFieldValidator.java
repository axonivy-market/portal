package ch.ivy.addon.portalkit.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import ch.ivy.addon.portal.generic.bean.ColumnManagementBean;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import com.axonivy.portal.components.util.HtmlParser;
import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator("duplicateFieldValidator")
public class DuplicateFieldValidator implements Validator {

  @SuppressWarnings("unchecked")
  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    List<ColumnManagementBean.FetchingField> fields = (List<ColumnManagementBean.FetchingField>) component
        .getAttributes().getOrDefault("existingFields", new ArrayList<>());
    DashboardColumnType selectedFieldType = (DashboardColumnType) component.getAttributes()
        .getOrDefault("selectedFieldType", DashboardColumnType.STANDARD);
    Optional<ColumnManagementBean.FetchingField> field = fields.stream()
        .filter(f -> f.getType() == selectedFieldType && f.getField().equals(value)).findFirst();
    if (field.isPresent()) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
          HtmlParser.sanitize(
              Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/existingField", Arrays.asList(value))),
          null);
      FacesContext.getCurrentInstance().addMessage(component.getClientId(), msg);
      throw new ValidatorException(msg);
    }
  }

}
