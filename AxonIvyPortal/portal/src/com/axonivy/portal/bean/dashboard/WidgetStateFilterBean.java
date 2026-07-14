package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.GlobalOperatorPolicyService;

import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class WidgetStateFilterBean implements Serializable {

  private static final long serialVersionUID = -8191354257458990196L;

  private List<FilterOperator> resolvedStateOperators;

  private List<String> states;
  private String statesString;

  @PostConstruct
  public void initOperators() {
    resolvedStateOperators = GlobalOperatorPolicyService.getInstance().keepGloballyEnabledOperators(FilterOperator.STATE_OPERATORS.stream().toList());
  }

  public void init(DashboardFilter filter, DashboardWidget widget) {
    if (DashboardWidgetType.TASK == widget.getType()) {
      states = TaskUtils.getValidStates().stream().map(businessState -> businessState.name()).toList();
    } else {
      states = CaseUtils.getValidStates().stream().map(businessState -> businessState.name()).toList();
    }
    statesString = String.join(", ", filter.getValues());
  }

  public String getUserFriendlyState(String state, DashboardWidget widget) {
    String cmsUri = DashboardWidgetType.TASK == widget.getType()
        ? "/ch.ivy.addon.portalkit.ui.jsf/taskBusinessState/"
        : "/ch.ivy.addon.portalkit.ui.jsf/businessCaseState/";
    String displayState = Ivy.cms().co(cmsUri + state.toString());
    return StringUtils.isBlank(displayState) ? state : displayState;
  }

  public List<FilterOperator> getStateOperators() {
    return resolvedStateOperators;
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
