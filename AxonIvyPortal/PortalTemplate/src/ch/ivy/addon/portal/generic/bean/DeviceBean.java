package ch.ivy.addon.portal.generic.bean;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portal.generic.common.DeviceDetector;
import ch.ivyteam.ivy.environment.Ivy;
import org.apache.commons.lang3.StringUtils;

@ManagedBean
@SessionScoped
public class DeviceBean {
	
  String sessionIdentifier;

  public boolean isMobile() {
    sessionIdentifier = Ivy.session().getHttpSessionIdentifier();
    return DeviceDetector.instance().isMobile(sessionIdentifier);
  }

  public boolean isDesktop() {
    return DeviceDetector.instance().isDesktop();
  }

  @PreDestroy
  public void destroy() {
    if (StringUtils.isNotBlank(sessionIdentifier)) {
      DeviceDetector.instance().removeVersionState(sessionIdentifier);
    }
  }
}
