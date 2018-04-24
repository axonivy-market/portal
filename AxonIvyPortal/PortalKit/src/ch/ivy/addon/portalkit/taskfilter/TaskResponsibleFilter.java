package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.bo.RemoteRole;
import ch.ivy.addon.portalkit.bo.RemoteSecurityMember;
import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivy.addon.portalkit.comparator.RemoteRoleComparator;
import ch.ivy.addon.portalkit.comparator.RemoteUserComparator;
import ch.ivy.addon.portalkit.mapper.RemoteSecurityMemberMapper;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskResponsibleFilter extends TaskFilter {

  @JsonIgnore
  private List<RemoteSecurityMember> responsibles;
  private RemoteSecurityMember selectedResponsible;
  @JsonIgnore
  private final static String SECURITY_SERVICE_CALLABLE = "MultiPortal/SecurityService";

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ACTIVATOR");
  }

  @Override
  public String value() {
    if (selectedResponsible == null) {
      return ALL;
    }
    return String.format(DOUBLE_QUOTES, formatName(selectedResponsible));
  }

  @Override
  public TaskQuery buildQuery() {
    if (selectedResponsible == null) {
      return null;
    }

    String memberName = selectedResponsible.getMemberName();
    if (selectedResponsible.isUser() && !memberName.startsWith("#")) {
      memberName = "#" + memberName;
    }
    return TaskQuery.create().where().activatorName().isEqual(memberName);
  }

  @Override
  public void resetValues() {
    selectedResponsible = null;
  }

  public String formatName(RemoteSecurityMember responsible) {
    if (StringUtils.isBlank(responsible.getDisplayName())) {
      return responsible.getMemberName();
    }
    return responsible.getDisplayName() + " (" + responsible.getMemberName() + ")";
  }

  public List<RemoteSecurityMember> getResponsibles() {
    if (responsibles == null) {
      initResponsibles();
    }
    return responsibles;
  }

  @SuppressWarnings("unchecked")
  private void initResponsibles() {
    responsibles = new ArrayList<>();
    try {
      List<RemoteUser> users =
          ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<RemoteUser>>() {
            @Override
            public List<RemoteUser> call() throws Exception {
              if (Ivy.request().getApplication().getName().equals(IApplication.PORTAL_APPLICATION_NAME)) {
                return SubProcessCall.withPath(SECURITY_SERVICE_CALLABLE).withStartName("findAllUsers").call()
                  .get("users", List.class);
              }
              return SubProcessCall.withPath(SECURITY_SERVICE_CALLABLE).withStartName("findAllUsersByApplication")
                  .call(Ivy.request().getApplication().getName())
                  .get("users", List.class);
            }
          });
      List<RemoteRole> roles =
          ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<RemoteRole>>() {
            @Override
            public List<RemoteRole> call() throws Exception {
              if (Ivy.request().getApplication().getName().equals(IApplication.PORTAL_APPLICATION_NAME)) {
                return SubProcessCall.withPath(SECURITY_SERVICE_CALLABLE).withStartName("findAllRoles").call()
                    .get("roles", List.class);
              }
              return SubProcessCall.withPath(SECURITY_SERVICE_CALLABLE).withStartName("findAllRolesByApplication")
                  .call(Ivy.request().getApplication().getName())
                  .get("roles", List.class);
            }
          });

      List<RemoteUser> distinctUsers =
          users.stream().collect(
              Collectors.collectingAndThen(
                  Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RemoteUser::getUsername))),
                  ArrayList::new));
      List<RemoteRole> distinctRoles =
          roles.stream().collect(
              Collectors.collectingAndThen(
                  Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RemoteRole::getMemberName))),
                  ArrayList::new));

      Collections.sort(distinctUsers, new RemoteUserComparator());
      Collections.sort(distinctRoles, new RemoteRoleComparator());

      responsibles.addAll(RemoteSecurityMemberMapper.mapFromRemoteUsers(distinctUsers));
      responsibles.addAll(RemoteSecurityMemberMapper.mapFromRemoteRoles(distinctRoles));
    } catch (Exception e) {
      Ivy.log().error("Can't get list of users or roles in responsible filter", e);
    }
  }

  public void setResponsibles(List<RemoteSecurityMember> responsibles) {
    this.responsibles = responsibles;
  }

  public RemoteSecurityMember getSelectedResponsible() {
    return selectedResponsible;
  }

  public void setSelectedResponsible(RemoteSecurityMember selectedResponsible) {
    this.selectedResponsible = selectedResponsible;
  }
}
