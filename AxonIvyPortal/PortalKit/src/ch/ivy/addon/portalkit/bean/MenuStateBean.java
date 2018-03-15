package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.util.MenuUtils.PORTAL_MENU_STATE;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.util.MenuUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class MenuStateBean implements Serializable {
  private static final long serialVersionUID = 1L;

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

  public void initMenuState(String state) {
    this.menuState = state;
  }

  public void clearMenuState() {
    MenuUtils.clearMenuState();
  }
}
