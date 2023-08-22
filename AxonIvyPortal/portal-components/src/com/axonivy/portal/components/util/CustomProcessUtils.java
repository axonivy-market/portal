package com.axonivy.portal.components.util;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CustomProcessUtils {

  private static final String STATUS = "status";
  public static final String SKIP_STATUS = "skip";

  public static boolean isSkipCustomProcess(Map<String, Object> respose) {
    if (Objects.isNull(respose)) {
      return true;
    }
    String status = (String)respose.get(STATUS);
    return Optional.ofNullable(status).orElse("").contentEquals(SKIP_STATUS);
  }
}