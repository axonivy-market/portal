package com.axonivy.portal.util.statisticfilter.operator.text;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class TextInOperatorHandler {
  private static TextInOperatorHandler instance;

  public static TextInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new TextInOperatorHandler();
    }
    return instance;
  }
  
  public String buildFilter(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return StringUtils.EMPTY;
    }

    StringBuilder sb = new StringBuilder();
    String field = changeFilterField(filter.getField());
    sb.append(field).append(PortalConstants.COLON);
    for (String value : filter.getValues()) {
      sb.append(value).append(" ");
    }
    return sb.toString();
  }
  
  public String buildFilterWithoutValue(DashboardFilter filter) {
    return changeFilterField(filter.getField());
  }
  
  private String changeFilterField(String field) {
    return switch(field) {
      case StatisticConstants.STATE -> StatisticConstants.BUSSINESS_STATE;
      case StatisticConstants.ACTIVATOR -> StatisticConstants.RESPONSIBLE_NAME;
      default -> field; 
    };
  }
  
  public String buildCustomFieldFilter(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return StringUtils.EMPTY;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(StatisticConstants.CUSTOM_STRING + filter.getField())
      .append(PortalConstants.COLON);
    for (String value : filter.getValues()) {
      sb.append(value).append(" ");
    }
    return sb.toString();
  }

}

