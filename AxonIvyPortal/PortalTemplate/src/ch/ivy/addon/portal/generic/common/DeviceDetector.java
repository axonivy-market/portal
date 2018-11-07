package ch.ivy.addon.portal.generic.common;

import java.awt.Toolkit;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;

public class DeviceDetector {

  private static final int TABLET_WIDTH = 992;

  public static DeviceDetector newInstance() {
    return new DeviceDetector();
  }
  
  public boolean isMobile() {
    if (Ivy.request() instanceof IHttpRequest) {
        IHttpRequest httpRequest = (IHttpRequest) Ivy.request();
        boolean isMobileMode = StringUtils.containsIgnoreCase(httpRequest.getHttpServletRequest().getHeader("User-Agent"), "mobile");
        boolean isLessThanTabletResolution = Toolkit.getDefaultToolkit().getScreenSize().getWidth() < TABLET_WIDTH;
        return isMobileMode && isLessThanTabletResolution;
    }
    return false;
  }
}
