package com.axonivy.portal.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.service.StatisticService;

@ManagedBean
@ViewScoped
public class StatisticDrillDownBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public void drillDownToDashboard(String drillDownDataJson) {
    StatisticService.getInstance().createDrillDownDashboard(drillDownDataJson);
  }
}
