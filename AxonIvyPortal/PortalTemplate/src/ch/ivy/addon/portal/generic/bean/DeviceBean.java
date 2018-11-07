package ch.ivy.addon.portal.generic.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ch.ivy.addon.portal.generic.common.DeviceDetector;

@ManagedBean
@ApplicationScoped
public class DeviceBean {

  public boolean isMobile() {
    return DeviceDetector.newInstance().isMobile();
  }
}
