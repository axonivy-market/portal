package ch.ivy.addon.portal.generic.common;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;

public class DeviceDetector {

  public static DeviceDetector newInstance() {
    return new DeviceDetector();
  }
  
  public boolean isMobile() {
    if (Ivy.request() instanceof IHttpRequest) {
        IHttpRequest httpRequest = (IHttpRequest) Ivy.request();
        return StringUtils.containsIgnoreCase(httpRequest.getHttpServletRequest().getHeader("User-Agent"), "mobile");
    }
    return false;
  }
}
