package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

@ManagedBean
@ViewScoped
public class WidgetStateFilterBean implements Serializable {

  private static final long serialVersionUID = 3218284115015773931L;

  private static List<FilterOperator> stateOperators = FilterOperator.STATE_OPERATORS.stream().toList();

  public List<FilterOperator> getStateOperators() {
    return stateOperators;
  }

  public void onChangeOperator(DashboardFilter filter) {
    
  }

  public boolean isShowStateListPanel(DashboardFilter filter) {
    for (FilterOperator operator : stateOperators) {
      if (operator == FilterOperator.EMPTY || operator == FilterOperator.NOT_EMPTY) {
        continue;
      }
      if (operator == filter.getOperator()) {
        return true;
      }
    }

   return false;
  }
}
