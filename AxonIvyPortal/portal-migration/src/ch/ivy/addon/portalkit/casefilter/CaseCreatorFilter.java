package ch.ivy.addon.portalkit.casefilter;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import portalmigration.dto.UserDTO;

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
    }
    return String.format(DOUBLE_QUOTES, formatName(selectedCreator));
  }

  @Override
  public CaseQuery buildQuery() {return null;}

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
