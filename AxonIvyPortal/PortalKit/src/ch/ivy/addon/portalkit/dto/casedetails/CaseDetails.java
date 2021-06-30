package ch.ivy.addon.portalkit.dto.casedetails;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.ivy.addon.portalkit.dto.AbstractWidgetFilter;
import ch.ivy.addon.portalkit.dto.AbstractConfigurableContent;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CaseDetails extends AbstractConfigurableContent implements Serializable {

  private static final long serialVersionUID = -8424800742022240884L;

  public CaseDetails() {}

  @Override
  @JsonSerialize(as = CaseDetailsFilters.class)
  public CaseDetailsFilters getFilters() {
    return (CaseDetailsFilters) this.filters;
  }

  @Override
  @JsonDeserialize(as = CaseDetailsFilters.class)
  public void setFilters(AbstractWidgetFilter filters) {
    this.filters = filters;
  }

}