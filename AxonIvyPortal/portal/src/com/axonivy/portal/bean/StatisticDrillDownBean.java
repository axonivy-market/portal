package com.axonivy.portal.bean;

import java.io.Serializable;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import com.axonivy.portal.service.StatisticService;

@Named
@ViewScoped
public class StatisticDrillDownBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public void drillDownToDashboard(String drillDownDataJson) {
    StatisticService.getInstance().createDrillDownDashboard(drillDownDataJson);
  }
}
