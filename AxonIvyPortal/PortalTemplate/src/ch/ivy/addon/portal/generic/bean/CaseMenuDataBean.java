package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData;

@ManagedBean
@ViewScoped
public class CaseMenuDataBean implements Serializable {

  private static final long serialVersionUID = 8661959768116528710L;
  private PortalCaseMenuData portalCaseMenuData;

  public PortalCaseMenuData getPortalCaseMenuData() {
    return portalCaseMenuData;
  }

  public void setPortalCaseMenuData(PortalCaseMenuData portalCaseMenuData) {
    this.portalCaseMenuData = portalCaseMenuData;
  }
}
