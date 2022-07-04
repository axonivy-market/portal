package ch.ivy.addon.portalkit.casefilter.impl;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseCreatorFilter extends CaseFilter {
  @JsonIgnore
  private UserDTO selectedCreator;
  private String selectedCreatorMemberName;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/CREATOR");
  }

  @Override
  public String value() {
    if (StringUtils.isEmpty(selectedCreatorMemberName)) {
      setSelectedCreator(null);
      return ALL;
    } else {
      findCreator(selectedCreatorMemberName);
    }
    return String.format(DOUBLE_QUOTES, formatName(selectedCreator));
  }

  @Override
  public CaseQuery buildQuery() {
    if (getSelectedCreator() == null) {
      return null;
    }

    return CaseUtils.createBusinessCaseQuery().where().creatorUserName().isEqual(selectedCreator.getName());
  }

  @Override
  public void resetValues() {
    selectedCreator = null;
    selectedCreatorMemberName = null;
  }

  public String formatName(UserDTO creator) {
    return SecurityMemberDisplayNameUtils.generateFullDisplayNameForUserDTO(creator);
  }

  public UserDTO getSelectedCreator() {
    return selectedCreator;
  }

  public void setSelectedCreator(UserDTO selectedCreator) {
    this.selectedCreator = selectedCreator;
    this.selectedCreatorMemberName = Optional.ofNullable(selectedCreator).map(UserDTO::getName).orElse(StringUtils.EMPTY);
  }

  public String getSelectedCreatorMemberName() {
    return selectedCreatorMemberName;
  }

  /**
   * Check selectedCreatorMemberName which is saved on BusinessData
   * Then find correspond UserDTO of selectedCreatorMemberName
   * @param memberName is selectedCreatorMemberName
   */
  private void findCreator(String memberName) {
    if (selectedCreator == null || !StringUtils.equals(memberName, selectedCreator.getName())) {
      setSelectedCreator(ServiceUtilities.findUserDTO(memberName.replaceFirst("#", "")));
    }
  }

  public void setSelectedCreatorMemberName(String selectedCreatorMemberName) {
    this.selectedCreatorMemberName = selectedCreatorMemberName;
  }
  
}
