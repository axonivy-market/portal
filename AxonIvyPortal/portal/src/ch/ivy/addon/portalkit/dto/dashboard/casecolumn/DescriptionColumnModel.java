package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.ICase;

public class DescriptionColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = 1111740263997339274L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.DESCRIPTION.getField();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.sortable = defaultIfEmpty(this.sortable, getDefaultSortable());
    this.quickSearch = defaultIfEmpty(this.quickSearch, true);
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/description";
  }

  @Override
  public Boolean getDefaultSortable() {
    return false;
  }

  @Override
  protected int getDefaultColumnWidth() {
    return 200;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-cases__description";
  }

  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return null;
    }
    return caze.descriptions().current();
  }

  @Override
  public boolean canQuickSearch() {
    return true;
  }
}
