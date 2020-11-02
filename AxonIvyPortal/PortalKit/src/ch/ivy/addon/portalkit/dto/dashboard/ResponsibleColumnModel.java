package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ITask;

public class ResponsibleColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  public ResponsibleColumnModel() {
    this.header = cms("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ACTIVATOR");
    this.width = "150";
    this.property = DashboardStandardTaskColumn.RESPONSIBLE.getProperty();
    this.type = DashboardColumnType.RESPONSIBLE;
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
