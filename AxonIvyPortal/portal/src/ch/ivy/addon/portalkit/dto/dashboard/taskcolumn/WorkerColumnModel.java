package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ITask;

public class WorkerColumnModel extends TaskColumnModel {

  private static final long serialVersionUID = -1L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.WORKER.getField();
    this.styleToDisplay = initDefaultStyle();
    this.format = getDefaultFormat();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.quickSearch = defaultIfEmpty(this.quickSearch, false);
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/workingUser";
  }
  
  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }
  
  @Override
  protected int getDefaultColumnWidth() {
    return EXTRA_WIDTH;
  }
  
  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__worker widget-column";
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null || task.getWorkerUser() == null) {
      return StringUtils.EMPTY;
    }
    ISecurityMember member = task.getWorkerUser();
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(member, member.getMemberName());
  }

  @Override
  public boolean canQuickSearch() {
    return true;
  }
}
