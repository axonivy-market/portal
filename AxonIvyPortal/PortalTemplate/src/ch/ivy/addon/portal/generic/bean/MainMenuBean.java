package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class MainMenuBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final String PORTAL_SEARCH_DELAY_IN_MILLISECONDS = "PortalSearchDelayInMilliseconds";

  public Integer getSearchDelayTime() {
    String delaySearchTime = Ivy.var().get(PORTAL_SEARCH_DELAY_IN_MILLISECONDS);
    try {
      return Integer.valueOf(delaySearchTime);
    } catch (NumberFormatException e) {
      String message = String.format("Value of global variable %s  is not number", PORTAL_SEARCH_DELAY_IN_MILLISECONDS);
      Ivy.log().error(message, e);
    }
    return 0;
  }

}
