package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.service.GlobalOperatorPolicyService;

@Named
@ViewScoped
public class CanWorkOnFilterBean implements Serializable{
  private static final long serialVersionUID = 1L;
  
  private final GlobalOperatorPolicyService globalOperatorPolicyService = new GlobalOperatorPolicyService();
  private List<FilterOperator> operators;

  @PostConstruct
  public void initOperators() {
    operators = globalOperatorPolicyService.keepGloballyEnabledOperators(
        FilterOperator.STATISTIC_CAN_WORK_ON_OPERATORS.stream().toList());
  }

  public List<FilterOperator> getOperators() {
    return operators;
  }
  
  public void onChangeOperator(BaseFilter filter) {
    if (filter.getOperator() == FilterOperator.CURRENT_USER) {
      filter.setValues(new ArrayList<>());
    }
  }
}
