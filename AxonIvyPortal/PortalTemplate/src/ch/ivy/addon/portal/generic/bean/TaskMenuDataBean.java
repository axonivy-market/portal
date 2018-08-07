package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData;

@ManagedBean
@ViewScoped
public class TaskMenuDataBean implements Serializable {

  private static final long serialVersionUID = 8661959768116528710L;
  private PortalTaskMenuData portalTaskMenuData;

  public PortalTaskMenuData getPortalTaskMenuData() {
    return portalTaskMenuData;
  }

  public void setPortalTaskMenuData(PortalTaskMenuData portalTaskMenuData) {
    this.portalTaskMenuData = portalTaskMenuData;
  }

}
