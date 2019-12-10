package ch.ivy.addon.portalkit.casefilter;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseCreatorFilter extends CaseFilter {
  @JsonIgnore
  private List<IUser> creators;
  @JsonIgnore
  private IUser selectedCreator;
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

    return CaseQuery.create().where().creatorUserName().isEqual(selectedCreator.getName());
  }

  @Override
  public void resetValues() {
    selectedCreator = null;
    selectedCreatorMemberName = null;
  }

  public String formatName(IUser creator) {
    if (StringUtils.isBlank(creator.getFullName())) {
      return creator.getName();
    }
    return creator.getFullName() + " (" + creator.getName() + ")";
  }

  public List<IUser> getCreators() {
    return creators;
  }

  public void setCreators(List<IUser> creators) {
    this.creators = creators;
  }

  public IUser getSelectedCreator() {
    return selectedCreator;
  }

  public void setSelectedCreator(IUser selectedCreator) {
    this.selectedCreator = selectedCreator;
    this.selectedCreatorMemberName = Optional.ofNullable(selectedCreator).map(IUser::getMemberName).orElse(StringUtils.EMPTY);
  }

  public String getSelectedCreatorMemberName() {
    return selectedCreatorMemberName;
  }

  public void setSelectedCreatorMemberName(String selectedCreatorMemberName) {
    this.selectedCreatorMemberName = selectedCreatorMemberName;
  }
  
}
