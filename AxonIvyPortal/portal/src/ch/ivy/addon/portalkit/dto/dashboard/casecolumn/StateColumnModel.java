package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

public class StateColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = 654113365187067735L;
  
  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.STATE.getField();
    this.style = defaultIfEmpty(this.style, getDefaultStyle());
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.format = getDefaultFormat();
  }

  @Override
  public String getHeaderText() {
    return translateHeader(defaultIfEmpty(this.header, DashboardConfigurationPrefix.CMS + getDefaultHeaderCMS()));
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
  public String getDefaultStyle() {
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
  public List<CaseState> getUserFilterStates() {
    return this.userFilterList.stream().map(String::toUpperCase).map(CaseState::valueOf).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setUserFilterStates(List<CaseState> states) {
    this.userFilterList = states.stream().map(CaseState::toString).collect(Collectors.toList());
  }

  @JsonIgnore
  public List<CaseState> getUserFilterStateOptions() {
    List<CaseState> states = getStates();
    if (CollectionUtils.isEmpty(states)) {
      states = CaseUtils.getValidStates();
    } else {
      states = CaseUtils.filterStateByPermission(states);
    }
    return states;
  }

}
