package ch.ivy.addon.portal.generic.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portal.generic.common.DeviceDetector;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class DeviceBean {

  public boolean isMobile() {
    return DeviceDetector.instance().isMobile(Ivy.session().getIdentifier());
  }
}
