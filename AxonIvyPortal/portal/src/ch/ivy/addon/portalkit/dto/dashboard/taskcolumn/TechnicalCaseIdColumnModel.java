package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

public class TechnicalCaseIdColumnModel extends TaskColumnModel {

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.TECHNICAL_CASE_ID.getField();
    this.styleToDisplay = initDefaultStyle();
    this.format = getDefaultFormat();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.quickSearch = defaultIfEmpty(this.quickSearch, false);
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/technicalCaseId";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__technical-case-id text-center widget-column";
  }

  @Override
  public Boolean getDefaultSortable() {
    return true;
  }

  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    // Resolve the case once: task.getCase() was previously dereferenced twice per render.
    ICase caze = task.getCase();
    if (caze == null) {
      return null;
    }
    return caze.getId();
  }

  @Override
  public boolean canQuickSearch() {
    return true;
  }
}
