package com.axonivy.portal.util;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;

public class UserExampleUtils {

  private static final String GUIDE_REQUEST_PATH = "Start Processes/UserExampleGuide/userExampleGuide.ivp";

  public static boolean isUserExampleAvailable() {
    String userExampleProcess = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath(GUIDE_REQUEST_PATH);
    return !StringUtils.isEmpty(userExampleProcess);
  }
}
