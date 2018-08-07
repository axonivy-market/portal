package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.util.MenuUtils.PORTAL_MENU_STATE;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.util.MenuUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class MenuStateBean {
  private String menuState;

  public String getMenuState() {
    if (StringUtils.isEmpty(menuState)) {
      return (String) Ivy.session().getAttribute(PORTAL_MENU_STATE);
    }
    return menuState;
  }

  public void setMenuState(String menuState) {
    Ivy.session().setAttribute(PORTAL_MENU_STATE, menuState);
    this.menuState = menuState;
  }

  public void initMenuState(String menuState) {
    this.menuState = menuState;
  }

  public void clearMenuState() {
    MenuUtils.clearMenuState();
  }
}
