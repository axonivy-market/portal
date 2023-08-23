package com.axonivy.portal.components.util;

import java.util.Map;
import java.util.Objects;

import com.axonivy.portal.components.enums.CustomProcessStatus;

public class CustomProcessUtils {

  private static final String STATUS = "status";

  public static boolean isSkipCustomProcess(Map<String, Object> respose) {
    if (Objects.isNull(respose)) {
      return true;
    }
    return CustomProcessStatus.toEnum((String)respose.get(STATUS)) == CustomProcessStatus.SKIP;
  }
}