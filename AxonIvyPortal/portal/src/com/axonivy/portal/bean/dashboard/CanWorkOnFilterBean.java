package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

@ManagedBean
@ViewScoped
public class CanWorkOnFilterBean implements Serializable{
  private static final long serialVersionUID = 1L;
  
  private static List<FilterOperator> operators = FilterOperator.STATISTIC_CAN_WORK_ON_OPERATORS.stream().toList();

  public List<FilterOperator> getOperators() {
    return operators;
  }
  
  public void onChangeOperator(BaseFilter filter) {
    if (filter.getOperator() == FilterOperator.CURRENT_USER) {
      filter.setValues(new ArrayList<>());
    }
  }
}
