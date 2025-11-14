package com.axonivy.portal.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.service.StatisticService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ManagedBean
@ViewScoped
public class StatisticDrillDownBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public void drillDownToDashboard(String drillDownDataJson) throws JsonMappingException, JsonProcessingException {
    StatisticService.getInstance().createDrillDownDashboard(drillDownDataJson);
    PortalNavigatorAPI.navigateToPortalHome();
  }
}
