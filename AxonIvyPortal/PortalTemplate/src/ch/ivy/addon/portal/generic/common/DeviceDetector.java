package ch.ivy.addon.portal.generic.common;

import java.util.HashMap;
import java.util.Map;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;

public class DeviceDetector {

  private static DeviceDetector instance = new DeviceDetector();
  private Map<String, Boolean> versionStateBySessionId = new HashMap<>();

  public static DeviceDetector instance() {
    return instance;
  }

  public void updateVersionState(String sessionId, Boolean isSwitchedToMobile) {
    versionStateBySessionId.put(sessionId, isSwitchedToMobile);
  }
  
  public void removeVersionState(String sessionId) {
    versionStateBySessionId.remove(sessionId);
  }

  public boolean isMobile(String sessionId) {
    if (versionStateBySessionId.get(sessionId) != null) {
      return versionStateBySessionId.get(sessionId).booleanValue();
    }

    if (Ivy.request() instanceof IHttpRequest) {
      IHttpRequest httpRequest = (IHttpRequest) Ivy.request();
      String userAgent = httpRequest.getHttpServletRequest().getHeader("User-Agent");
      String httpAccept = httpRequest.getHttpServletRequest().getHeader("Accept");
      UAgentInfo agentInfo = new UAgentInfo(userAgent, httpAccept);
      return agentInfo.detectTierIphone();
    }
    return false;
  }

}
