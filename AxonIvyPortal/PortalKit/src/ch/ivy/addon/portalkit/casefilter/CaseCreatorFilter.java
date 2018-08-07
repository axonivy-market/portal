package ch.ivy.addon.portalkit.casefilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.bo.RemoteSecurityMember;
import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivy.addon.portalkit.comparator.RemoteUserComparator;
import ch.ivy.addon.portalkit.mapper.RemoteSecurityMemberMapper;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseCreatorFilter extends CaseFilter {
  private List<RemoteSecurityMember> securityMembers = new ArrayList<>();
  private RemoteSecurityMember selectedCreator;
  private final static String SECURITY_SERVICE_CALLABLE = "MultiPortal/SecurityService";

  @SuppressWarnings("unchecked")
  public CaseCreatorFilter() {
    try {
      List<RemoteUser> users =
          ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<RemoteUser>>() {
            public List<RemoteUser> call() throws Exception {
              return SubProcessCall.withPath(SECURITY_SERVICE_CALLABLE).withStartName("findAllUsers").call()
                  .get("users", List.class);
            }
          });

      List<RemoteUser> distinctUsers =
          users.stream().collect(
              Collectors.collectingAndThen(
                  Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RemoteUser::getUsername))),
                  ArrayList::new));

      Collections.sort(distinctUsers, new RemoteUserComparator());

      securityMembers.addAll(RemoteSecurityMemberMapper.mapFromRemoteUsers(distinctUsers));
    } catch (Exception e) {
      Ivy.log().error("Can't get list of users for creator filter", e);
    }
  }

  @Override
  public String label() {
    return Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/Creator");
  }

  @Override
  public String value() {
    if (selectedCreator == null) {
      return ALL;
    }
    return String.format(DOUBLE_QUOTES, formatName(selectedCreator));
  }

  @Override
  public CaseQuery buildQuery() {
    if (selectedCreator == null) {
      return null;
    }

    String userName = selectedCreator.getMemberName();
    return CaseQuery.create().where().creatorUserName().isEqual(userName);
  }

  @Override
  public void resetValues() {
    selectedCreator = null;
  }

  public String formatName(RemoteSecurityMember responsible) {
    if (StringUtils.isBlank(responsible.getDisplayName())) {
      return responsible.getMemberName();
    }
    return responsible.getDisplayName() + " (" + responsible.getMemberName() + ")";
  }

  public List<RemoteSecurityMember> getSecurityMembers() {
    return securityMembers;
  }

  public void setResponsibles(List<RemoteSecurityMember> securityMembers) {
    this.securityMembers = securityMembers;
  }

  public RemoteSecurityMember getSelectedCreator() {
    return selectedCreator;
  }

  public void setSelectedCreator(RemoteSecurityMember selectedCreator) {
    this.selectedCreator = selectedCreator;
  }
}
