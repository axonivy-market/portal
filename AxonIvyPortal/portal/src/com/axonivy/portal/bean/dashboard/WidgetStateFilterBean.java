package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.util.CaseUtils;

@ManagedBean
@ViewScoped
public class WidgetStateFilterBean implements Serializable {

  private static final long serialVersionUID = -8191354257458990196L;

  private static List<FilterOperator> stateOperators = FilterOperator.STATE_OPERATORS.stream().toList();

  private List<String> states;
  private String statesString;

  public void init(DashboardFilter filter) {
    states = CaseUtils.getValidStates().stream().map(businessState -> businessState.name()).toList();
    statesString = String.join(", ", filter.getValues());
  }

  public List<FilterOperator> getStateOperators() {
    return stateOperators;
  }

  public List<String> getStates() {
    return states;
  }

  public String getStatesString() {
    return statesString;
  }

  public void setStatesString(String statesString) {
    this.statesString = statesString;
  }

  public void updateStatesString(List<String> states) {
    statesString = String.join(", ", states);
  }
}
