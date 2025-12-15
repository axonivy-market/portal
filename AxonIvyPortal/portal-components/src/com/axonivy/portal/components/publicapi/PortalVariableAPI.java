package com.axonivy.portal.components.publicapi;

import com.axonivy.portal.components.enums.GlobalVariable;
import com.axonivy.portal.components.service.GlobalSettingService;

public final class PortalVariableAPI {
  
  public static Boolean isYearHidden() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_YEAR, Boolean.FALSE);
  }
  
  public static Boolean isTimeHidden() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_TIME, Boolean.FALSE);
  }
  
  public static Boolean isDateFilterWithTime() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.DATE_FILTER_WITH_TIME, Boolean.FALSE);
  }

}
