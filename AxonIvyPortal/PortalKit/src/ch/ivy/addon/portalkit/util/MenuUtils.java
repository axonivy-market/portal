package ch.ivy.addon.portalkit.util;

import javax.faces.context.FacesContext;

import ch.ivy.addon.portalkit.bean.MenuStateBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivyteam.ivy.environment.Ivy;

public class MenuUtils {

  public static final String PORTAL_MENU_STATE = "portalMenuState";

  private MenuUtils() {}

  public static String getMenuState() {
    MenuStateBean menuStateBean = getMenuStateBean();
    if (menuStateBean == null) {
      return "";
    }
    return menuStateBean.getMenuState();
  }

  public static void clearMenuState() {
    Ivy.session().removeAttribute(PORTAL_MENU_STATE);

    MenuStateBean menuStateBean = getMenuStateBean();
    if (menuStateBean != null) {
      menuStateBean.setMenuState("");
    }
  }

  private static MenuStateBean getMenuStateBean() {
    if (FacesContext.getCurrentInstance() == null) {
      return null;
    }
    return (MenuStateBean) ManagedBeans.find("menuStateBean").get();
  }
}
