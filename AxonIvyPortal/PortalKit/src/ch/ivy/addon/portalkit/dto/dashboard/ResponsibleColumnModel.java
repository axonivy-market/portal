package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ITask;

public class ResponsibleColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, cms("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ACTIVATOR"));
    this.field = DashboardStandardTaskColumn.RESPONSIBLE.getField();
    this.style = defaultIfEmpty(this.style, "width: 150px");
    this.format = DashboardColumnFormat.CUSTOM;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null || task.getActivator() == null) {
      return StringUtils.EMPTY;
    }
    ISecurityMember member = task.getActivator();
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(member, member.getMemberName());
  }
}
