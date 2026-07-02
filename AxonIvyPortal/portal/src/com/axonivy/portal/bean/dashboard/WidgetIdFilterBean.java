package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

@ManagedBean
@ViewScoped
public class WidgetIdFilterBean implements Serializable {

  private static final long serialVersionUID = 4671032185665650209L;

  public void onChangeOperator(DashboardFilter filter) {
  }
}
