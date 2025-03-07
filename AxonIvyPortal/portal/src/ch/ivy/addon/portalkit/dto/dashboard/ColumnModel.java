package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix.CMS;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.util.PortalCustomFieldUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"format", "filterType", "filterListOptions", "dateFilterFrom", "dateFilterTo", "userFilter",
    "userFilterList", "userFilterFrom", "userFilterTo", "userDateFilterFrom", "userDateFilterTo",
    "userFilterListOptions"})
public class ColumnModel extends AbstractColumn implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public String getHeaderText() {
    if (getHeader() == null || StringUtils.equals(getHeader(), CMS + getDefaultHeaderCMS())) {
      return Ivy.cms().co(getDefaultHeaderCMS());
    }
    return getHeader();
  }

  @JsonIgnore
  @SuppressWarnings("unused")
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {}
  
  @JsonIgnore
  public boolean canQuickSearch() {
    return false;
  }
  
  public String displayStringFieldContent(ICustomFields customFields) {
    if (Boolean.TRUE.equals(this.hasCmsValues)) {
      return PortalCustomFieldUtils.getDisplayValueByField(customFields, field);
    }
    return customFields.stringField(field).getOrNull();
  }
}
