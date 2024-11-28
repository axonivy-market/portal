package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

@ManagedBean
@ViewScoped
public class WidgetIdFilterBean implements Serializable {

  private static final long serialVersionUID = 4671032185665650209L;

  private static List<FilterOperator> operators = FilterOperator.ID_OPERATORS.stream().toList();

  public List<FilterOperator> getOperators() {
    return operators;
  }

  public void onChangeOperator(@SuppressWarnings("unused") DashboardFilter filter) {
  }
}
