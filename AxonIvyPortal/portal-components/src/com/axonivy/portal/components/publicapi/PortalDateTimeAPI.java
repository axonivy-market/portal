package com.axonivy.portal.components.publicapi;

import com.axonivy.portal.components.service.DateTimeGlobalSettingService;

/**
 * Portal API for date time pattern
 *
 */
public final class PortalDateTimeAPI {
  
  private PortalDateTimeAPI() {}
  
  /**
   * Get the date pattern based on the variable Portal.DateTimeFormat.HideYear. 
   * If it is true, the year part will be removed.
   * 
   * @return date pattern string
   */
  public static String getDatePattern() {
    return DateTimeGlobalSettingService.getInstance().getDatePatternForDatePicker();
  }
  
  /**
   * Get the date time pattern based on the following variables:
   * - Portal.DateTimeFormat.HideYear: If true, the year part will be excluded.
   * - Portal.DateTimeFormat.HideTime: If true, the time part will be excluded.
   * 
   * @return date time pattern string
   */
  public static String getDateTimePattern() {
    return DateTimeGlobalSettingService.getInstance().getDateTimePatternForDatePicker(false);
  }

}
