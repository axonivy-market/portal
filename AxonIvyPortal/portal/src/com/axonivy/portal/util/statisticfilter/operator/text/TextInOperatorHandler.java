package com.axonivy.portal.util.statisticfilter.operator.text;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.statistic.StatisticFilter;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class TextInOperatorHandler {
  private static TextInOperatorHandler instance;

  public static TextInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new TextInOperatorHandler();
    }
    return instance;
  }
  
  public String buildFilter(StatisticFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return Strings.EMPTY;
    }

    StringBuilder sb = new StringBuilder();
    String field = changeFilterField(filter.getField());
    sb.append(field).append(PortalConstants.COLON);
    for (String value : filter.getValues()) {
      sb.append(value).append(" ");
    }
    return sb.toString();
  }
  
  public String buildFilterWithoutValue(StatisticFilter filter) {
    return changeFilterField(filter.getField());
  }
  
  private String changeFilterField(String field) {
    return switch(field) {
      case StatisticConstants.STATE -> StatisticConstants.BUSSINESS_STATE;
      case StatisticConstants.ACTIVATOR -> StatisticConstants.RESPONSIBLE_NAME;
      default -> field; 
    };
  }

}

