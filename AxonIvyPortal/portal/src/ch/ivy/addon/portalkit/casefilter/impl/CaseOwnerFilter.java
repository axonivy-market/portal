package ch.ivy.addon.portalkit.casefilter.impl;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseOwnerFilter extends CaseFilter {
  @JsonIgnore
  private SecurityMemberDTO selectedOwner;
  private String selectedOwnerMemberName;
  
  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/OWNER");
  }

  @Override
  public String value() {
    if (StringUtils.isEmpty(selectedOwnerMemberName)) {
      setSelectedOwner(null);
      return getAllLabel();
    } else {
      findSelectedOwner();
    }
    return String.format(DOUBLE_QUOTES, formatName(selectedOwner));
  }

  @Override
  public CaseQuery buildQuery() {
    if (getSelectedOwner() == null) {
      return null;
    }

    String memberName = selectedOwner.getMemberName();
    return CaseQuery.create().where().ownerName().isEqual(memberName);
  }

  @Override
  public void resetValues() {
    selectedOwner = null;
    selectedOwnerMemberName = null;
  }

  public String formatName(SecurityMemberDTO owner) {
    if (owner == null) {
      return getAllLabel();
    }
    
    String ownerName = String.format("%s (%s)", owner.getDisplayName(), owner.getName());
    return owner.isEnabled() ? ownerName : String.format("%s %s", Ivy.cms().co("/Labels/disabledUserPrefix"), ownerName);
  }

  public SecurityMemberDTO getSelectedOwner() {
    return selectedOwner;
  }

  public void setSelectedOwner(SecurityMemberDTO selectedOwner) {
    this.selectedOwner = selectedOwner;
    this.selectedOwnerMemberName = Optional.ofNullable(selectedOwner).map(SecurityMemberDTO::getMemberName).orElse(StringUtils.EMPTY);
  }

  /**
   * Checks selectedOwnerMemberName which is saved on BusinessData
   * then find correspond SecurityMemberDTO of selectedOwnerMemberName
   */
  public void findSelectedOwner() {
    if (selectedOwner == null || !StringUtils.equals(selectedOwnerMemberName, selectedOwner.getMemberName())) {
      setSelectedOwner(ServiceUtilities.findSecurityMemberByName(selectedOwnerMemberName));
    }
  }

  public String getSelectedOwnerMemberName() {
    return selectedOwnerMemberName;
  }

  public void setSelectedOwnerMemberName(String selectedOwnerMemberName) {
    this.selectedOwnerMemberName = selectedOwnerMemberName;
  }
  
}
