package com.axonivy.portal.util.filter.operator.caze.name;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class NameInOperatorHandler {
  private static NameInOperatorHandler instance;
  
  public static NameInOperatorHandler getInstance() {
    if(instance == null) {
      instance = new NameInOperatorHandler();
    }
    
    return instance;
  }
  
  public String buildFilter(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return StringUtils.EMPTY;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(filter.getField())
      .append(PortalConstants.COLON);
    for (String value : filter.getValues()) {
      sb.append("\"").append(value).append("\" ");
    }

    return sb.toString();
  }

}
