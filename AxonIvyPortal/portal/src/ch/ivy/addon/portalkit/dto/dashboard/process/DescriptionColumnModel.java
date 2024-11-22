package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DescriptionColumnModel extends ProcessColumnModel implements Serializable {

  private static final long serialVersionUID = -327372992675183938L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardProcessColumn.DESCRIPTION.getField();
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/description";
  }
}
