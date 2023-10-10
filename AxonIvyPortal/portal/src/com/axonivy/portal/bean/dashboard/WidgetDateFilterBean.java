package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;

@ManagedBean
@ViewScoped
public class WidgetDateFilterBean implements Serializable {

  private static final long serialVersionUID = 4356531402161360729L;

  private static List<FilterOperator> operators = FilterOperator.DATE_OPERATORS.stream().toList();
  private static FilterPeriodType[] filterPeriodTypes = FilterPeriodType.values();
  private Date beforeDate;
  private Date fromDate;
  private Date toDate;

  public List<FilterOperator> getOperators() {
    return operators;
  }

  public FilterPeriodType[] getFilterPeriodTypes() {
    return filterPeriodTypes;
  }

  public void onChangeOperator(DashboardFilter filter) {
    filter.setFrom(null);
    filter.setTo(null);
    filter.setPeriodType(FilterPeriodType.YEAR);
    filter.setPeriods(1L);
  }

  public Date getBeforeDate() {
    return beforeDate;
  }

  public void setBeforeDate(Date beforeDate) {
    this.beforeDate = beforeDate;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }
}
