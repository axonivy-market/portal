package com.axonivy.portal.util.statisticfilter.operator.string;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class StringCustomFieldInOperatorHandler {
  private static StringCustomFieldInOperatorHandler instance;

  public static StringCustomFieldInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new StringCustomFieldInOperatorHandler();
    }
    return instance;
  }
  
  public String buildFilter(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return StringUtils.EMPTY;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(StatisticConstants.CUSTOM_STRING + filter.getField())
      .append(PortalConstants.COLON);
    for (String value : filter.getValues()) {
      sb.append("\"").append(value).append("\" ");
    }
    return sb.toString();
  }
}
