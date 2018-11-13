package ch.ivy.addon.portal.generic.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;

public class DeviceDetector {

  private static DeviceDetector instance = new DeviceDetector();
  private Map<Integer, Boolean> versionStateBySessionId = new HashMap<>();

  public static DeviceDetector instance() {
    return instance;
  }

  public void updateVersionState(int sessionId, Boolean isSwitchedToMobile) {
    versionStateBySessionId.put(sessionId, isSwitchedToMobile);
  }

  public boolean isMobile(int sessionId) {
    if (versionStateBySessionId.get(sessionId) != null) {
      return versionStateBySessionId.get(sessionId).booleanValue();
    }

    if (Ivy.request() instanceof IHttpRequest) {
      IHttpRequest httpRequest = (IHttpRequest) Ivy.request();
      return StringUtils.containsIgnoreCase(httpRequest.getHttpServletRequest().getHeader("User-Agent"), "mobile");
    }
    return false;
  }

}
