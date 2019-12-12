package ch.ivy.addon.portalkit.taskfilter;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskResponsibleFilter extends TaskFilter {

  @JsonIgnore
  private List<ISecurityMember> responsibles;
  @JsonIgnore
  private ISecurityMember selectedResponsible;
  private String selectedResponsibleMemberName;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ACTIVATOR");
  }

  @Override
  public String value() {
    if (getSelectedResponsible() == null) {
      return ALL;
    }
    return String.format(DOUBLE_QUOTES, formatName(selectedResponsible));
  }

  @Override
  public TaskQuery buildQuery() {
    if (getSelectedResponsible() == null) {
      return null;
    }

    String memberName = selectedResponsible.getMemberName();
    return TaskQuery.create().where().activatorName().isEqual(memberName);
  }

  @Override
  public void resetValues() {
    selectedResponsible = null;
    selectedResponsibleMemberName = null;
  }

  @Override
  public boolean defaultFilter() {
    return true;
  }

  public String formatName(ISecurityMember responsible) {
    if (StringUtils.isBlank(responsible.getDisplayName())) {
      return responsible.getName();
    }
    return responsible.getDisplayName() + " (" + responsible.getName() + ")";
  }

  public List<ISecurityMember> getResponsibles() {
    return responsibles;
  }

  public void setResponsibles(List<ISecurityMember> responsibles) {
    this.responsibles = responsibles;
  }

  public ISecurityMember getSelectedResponsible() {
    return selectedResponsible;
  }

  public void setSelectedResponsible(ISecurityMember selectedResponsible) {
    this.selectedResponsible = selectedResponsible;
    this.selectedResponsibleMemberName = Optional.ofNullable(selectedResponsible).map(ISecurityMember::getMemberName).orElse(StringUtils.EMPTY);
  }

  public String getSelectedResponsibleMemberName() {
    return selectedResponsibleMemberName;
  }

  public void setSelectedResponsibleMemberName(String selectedResponsibleMemberName) {
    this.selectedResponsibleMemberName = selectedResponsibleMemberName;
  }
  
}
