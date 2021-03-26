package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

public class StateColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;
  
  @JsonIgnore
  private
  List<CaseState> userFilterStateOptions;
  
  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, cms("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/STATE"));
    this.field = DashboardStandardCaseColumn.STATE.getField();
    this.style = defaultIfEmpty(this.style, "width: 120px");
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__state u-text-align-center");
    this.fieldStyleClass = defaultIfEmpty(this.fieldStyleClass, "dashboard-tasks__state-text");
    this.format = DashboardColumnFormat.CUSTOM;
    initUserFilterStateOptions();
  }
  
  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return null;
    }
    return getUserFriendlyCaseState(caze.getState());
  }
  
  private String getUserFriendlyCaseState(CaseState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    return cms("/ch.ivy.addon.portalkit.ui.jsf/caseState/" + state.toString());
  }
  
  @JsonIgnore
  public List<CaseState> getStates() {
    return this.filterList.stream().map(String::toUpperCase).map(CaseState::valueOf).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setStates(List<CaseState> states) {
    this.filterList = states.stream().map(CaseState::toString).collect(Collectors.toList());
  }
  
  @JsonIgnore
  private void initUserFilterStateOptions() {
    setUserFilterStateOptions(getStates());
    if (CollectionUtils.isEmpty(getUserFilterStateOptions())) {
      setUserFilterStateOptions(Arrays.asList(CaseState.values()).stream().sorted((s1, s2) -> StringUtils.compare(s1.toString(), s2.toString())).collect(Collectors.toList()));
    }
  }
  
  @JsonIgnore
  public List<CaseState> getUserFilterStates() {
    return this.userFilterList.stream().map(String::toUpperCase).map(CaseState::valueOf).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setUserFilterStates(List<CaseState> states) {
    this.userFilterList = states.stream().map(CaseState::toString).collect(Collectors.toList());
  }

  public List<CaseState> getUserFilterStateOptions() {
    return userFilterStateOptions;
  }

  public void setUserFilterStateOptions(List<CaseState> userFilterStateOptions) {
    this.userFilterStateOptions = userFilterStateOptions;
  }
}
