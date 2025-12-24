package com.axonivy.portal.components.publicapi;

import com.axonivy.portal.components.service.DateTimeGlobalSettingService;

/**
 * Portal API for date time pattern settings
 *
 */
public final class PortalDateTimeAPI {
  
  private PortalDateTimeAPI() {}
  
  /**
   * Get the date pattern based on the variable Portal.DateTimeFormat.HideYear. 
   * If it is true, the year part will be removed from the date pattern.
   * 
   * @return date pattern string for date picker
   */
  public static String getShortDatePattern() {
    return DateTimeGlobalSettingService.getInstance().getDatePatternForDatePicker();
  }
  
  /**
   * Get the date time pattern based on the following variables:
   * - Portal.DateTimeFormat.HideYear: If true, the year part will be removed from the pattern.
   * - Portal.DateTimeFormat.HideTime: If true and isDateFilter is false, the time part will be excluded.
   * - Portal.DateTimeFormat.DateFilterWithTime: If true and isDateFilter is true, the time part will be included.
   * 
   * @param isDateFilter - true if this is for date filter
   * @return date time pattern string for date picker
   */
  public static String getShortDateTimePattern(boolean isDateFilter) {
    return DateTimeGlobalSettingService.getInstance().getDateTimePatternForDatePicker(isDateFilter);
  }
}
