package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.process.call.SubProcessCallResult;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
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
    if (responsibles == null) {
      initResponsibles();
    }
    return responsibles;
  }

  @SuppressWarnings("unchecked")
  private void initResponsibles() {
    responsibles = new ArrayList<>();
    try {
      SubProcessCallResult result = ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        if (Ivy.request().getApplication().getName().equals(PortalConstants.PORTAL_APPLICATION_NAME)) {
          return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE).withStartName("findSecurityMembersOverAllApplications")
              .call(Ivy.session().getSessionUserName());
        }
        return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE).withStartName("findSecurityMembers")
            .call(Ivy.request().getApplication());
      }); 
      List<IUser> users = result.get("users", List.class);
      List<IRole> roles = result.get("roles", List.class);
      responsibles.addAll(users);
      responsibles.addAll(roles);
    } catch (Exception e) {
      Ivy.log().error("Can't get list of users or roles in responsible filter", e);
    }
  }

  public void setResponsibles(List<ISecurityMember> responsibles) {
    this.responsibles = responsibles;
  }

  public ISecurityMember getSelectedResponsible() {
    if (selectedResponsible == null && CollectionUtils.isNotEmpty(getResponsibles())) {
      selectedResponsible = responsibles.stream()
          .filter(responsible -> StringUtils.equals(responsible.getMemberName(), selectedResponsibleMemberName))
          .findFirst()
          .orElse(null);
    }
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
