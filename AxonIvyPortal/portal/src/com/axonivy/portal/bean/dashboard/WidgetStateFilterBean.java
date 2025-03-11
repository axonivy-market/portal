package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class WidgetStateFilterBean implements Serializable {

  private static final long serialVersionUID = -8191354257458990196L;

  private static List<FilterOperator> stateOperators = FilterOperator.STATE_OPERATORS.stream().toList();
  private static List<FilterOperator> statisticOperators = FilterOperator.STATISTIC_TEXT_OPERATORS.stream().toList();

  private List<String> states;
  private String statesString;

  public void init(BaseFilter filter, String widgetType) {
    if ("task".equals(StringUtils.lowerCase(widgetType))) {
      states = TaskUtils.getValidStates().stream().map(businessState -> businessState.name()).toList();
    } else {
      states = CaseUtils.getValidStates().stream().map(businessState -> businessState.name()).toList();
    }
    statesString = String.join(", ", filter.getValues());
  }

  public String getUserFriendlyState(String state, String widgetType) {
    String cmsUri = "task".equals(StringUtils.lowerCase(widgetType))
        ? "/ch.ivy.addon.portalkit.ui.jsf/taskBusinessState/"
        : "/ch.ivy.addon.portalkit.ui.jsf/businessCaseState/";
    String displayState = Ivy.cms().co(cmsUri + state.toString());
    return StringUtils.isBlank(displayState) ? state : displayState;
  }

  public List<FilterOperator> getStateOperators() {
    return stateOperators;
  }

  public List<FilterOperator> getStatisticOperators() {
    return statisticOperators;
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
