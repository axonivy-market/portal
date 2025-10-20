package com.axonivy.portal.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.dto.StatisticDrillDownDto;
import com.axonivy.portal.service.StatisticService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ManagedBean
@ViewScoped
public class StatisticDrillDownBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public void openDrillDown(String drillDownDataJson) throws JsonMappingException, JsonProcessingException {
    if (StringUtils.isBlank(drillDownDataJson)) {
      return;
    }

    ObjectMapper mapper = new ObjectMapper();
    StatisticDrillDownDto drillDownData = mapper.readValue(drillDownDataJson, StatisticDrillDownDto.class);
    StatisticService.getInstance().getStatisticDrillDownData(drillDownData); // TODO z1 consider method name
    PortalNavigatorAPI.navigateToPortalHome();
  }
}
