package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardStandardProcessColumn;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NameColumnModel extends ProcessColumnModel implements Serializable {

  private static final long serialVersionUID = 8951771185949809098L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardProcessColumn.NAME.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-process__name");
    this.quickSearch = defaultIfEmpty(this.quickSearch, true);
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/processwidget/processName";
  }
  
  @Override
  public boolean canQuickSearch() {
    return true;
  }
}
