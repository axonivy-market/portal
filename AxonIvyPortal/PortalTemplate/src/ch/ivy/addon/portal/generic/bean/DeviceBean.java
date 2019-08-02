package ch.ivy.addon.portal.generic.bean;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portal.generic.common.DeviceDetector;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class DeviceBean {

  public boolean isMobile() {
    return DeviceDetector.instance().isMobile(Ivy.session().getHttpSessionIdentifier());
  }
  
  public boolean isDesktop() {
    return DeviceDetector.instance().isDesktop();
  }
  
  @PreDestroy
  public void destroy() {
	Ivy.log().info("ivy session Identifier: " +Ivy.session().getHttpSessionIdentifier());
    DeviceDetector.instance().removeVersionState(Ivy.session().getHttpSessionIdentifier());
  }
}
