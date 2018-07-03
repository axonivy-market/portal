package ch.ivy.addon.portalkit.casefilter;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.bo.RemoteSecurityMember;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CaseCreatorFilter extends CaseFilter {
  @JsonIgnore
  private List<RemoteSecurityMember> securityMembers;
  private RemoteSecurityMember selectedCreator;
  @JsonIgnore
  private static final String SECURITY_SERVICE_CALLABLE = "MultiPortal/SecurityService";

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/creator");
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
    if (securityMembers == null) {
      initSecurityMembers();
    }
    return securityMembers;
  }

  private void initSecurityMembers() {
    securityMembers = UserUtils.findAllUserByApplication("Can't get list of users for creator filter");
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
