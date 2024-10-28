package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;

public class StateColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = 654113365187067735L;
  
  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.STATE.getField();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.format = getDefaultFormat();
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/STATE";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }

  @Override
  protected int getDefaultColumnWidth() {
    return NORMAL_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-cases__state u-text-align-center";
  }

  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return null;
    }
    return getUserFriendlyCaseState(caze.getBusinessState());
  }
  
  private String getUserFriendlyCaseState(CaseBusinessState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    return cms("/ch.ivy.addon.portalkit.ui.jsf/businessCaseState/" + state.toString());
  }

  @JsonIgnore
  public List<CaseBusinessState> getStates() {
    List<CaseBusinessState> states = filterList.stream().map(String::toUpperCase)
        .map(CaseBusinessState::valueOf)
        .collect(Collectors.toList());
    return CaseUtils.filterStateByPermission(states);
  }

  @JsonIgnore
  public void setStates(List<CaseBusinessState> states) {
    this.filterList = states.stream().map(CaseBusinessState::toString).collect(Collectors.toList());
  }

  @JsonIgnore
  public List<CaseBusinessState> getUserFilterStates() {
    return this.userFilterList.stream().map(String::toUpperCase).map(CaseBusinessState::valueOf).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setUserFilterStates(List<CaseBusinessState> states) {
    this.userFilterList = states.stream().map(CaseBusinessState::toString).collect(Collectors.toList());
  }

  @JsonIgnore
  public List<CaseBusinessState> getUserFilterStateOptions() {
    List<CaseBusinessState> states = getStates();
    if (CollectionUtils.isEmpty(states)) {
      states = CaseUtils.getValidStates();
    } else {
      states = CaseUtils.filterStateByPermission(states);
    }
    return states;
  }

}
