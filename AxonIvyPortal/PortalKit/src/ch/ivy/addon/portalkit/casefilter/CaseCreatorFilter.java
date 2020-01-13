package ch.ivy.addon.portalkit.casefilter;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.util.CaseUtils;
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
    if (getSelectedCreator() == null) {
      return ALL;
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
    if (StringUtils.isBlank(creator.getDisplayName())) {
      return creator.getName();
    }
    return creator.getDisplayName() + " (" + creator.getName() + ")";
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

  public void setSelectedCreatorMemberName(String selectedCreatorMemberName) {
    this.selectedCreatorMemberName = selectedCreatorMemberName;
  }
  
}
