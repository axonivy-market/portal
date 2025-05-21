package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

public class PinColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -2024416411665418820L;

  @Override
  public void initDefaultValue() {
    this.setVisible(GlobalSettingService.getInstance().isEnablePinCase());
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.PIN.getField();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.sortable = getDefaultSortable();
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-cases__actions text-center";
  }

  @Override
  public int getDefaultColumnWidth() {
    return TINY_WIDTH;
  }

  @Override
  public Boolean getDefaultSortable() {
    return false;
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/PIN";
  }

}
