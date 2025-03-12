package com.axonivy.portal.util.statisticfilter.operator;

import org.apache.commons.collections4.CollectionUtils;

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
      return "";
    }

    StringBuilder sb = new StringBuilder();
    String field = changeFilterField(filter.getField());
    sb.append(field).append(PortalConstants.COLON);
    for (String category : filter.getValues()) {
      sb.append(category).append(" ");
    }

    return sb.toString();
  }
  
  private String changeFilterField(String field) {
    return switch(field) {
      case "state" -> "businessState";
      default -> field; 
    };
  }

}
