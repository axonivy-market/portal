package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.OperatorPolicyFacade;

@ManagedBean
@ViewScoped
public class CanWorkOnFilterBean implements Serializable{
  private static final long serialVersionUID = 1L;
  
  private final OperatorPolicyFacade operatorPolicyFacade = new OperatorPolicyFacade();
  private List<FilterOperator> cachedOperators;

  @PostConstruct
  public void initOperators() {
    cachedOperators = operatorPolicyFacade.keepGloballyEnabledOperators(
        FilterOperator.STATISTIC_CAN_WORK_ON_OPERATORS.stream().toList());
  }

  public List<FilterOperator> getOperators() {
    return cachedOperators;
  }
  
  public void onChangeOperator(BaseFilter filter) {
    if (filter.getOperator() == FilterOperator.CURRENT_USER) {
      filter.setValues(new ArrayList<>());
    }
  }
}
