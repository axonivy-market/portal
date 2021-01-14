package ch.ivy.addon.portal.generic.navigation;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.primefaces.PrimeFaces;

import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * This class introduces the methods which navigate to specific pages and are only used inside Portal Frame
 */
public final class PortalNavigatorInFrame {
  
  /**
   * Navigates to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page: task list or task details or global search
   */
  public void navigateToPortalEndPage() {
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_FINISHED.toString(), false);
    String statement = "parent.redirectToEndPageCommand([{name: 'taskId', value: " + Ivy.wfTask().getId() + "}]);";
    PrimeFaces.current().executeScript(statement);
  }
  
  public void navigateToPortalHome() {
    String statement = "parent.redirectToHomePageCommand()";
    PrimeFaces.current().executeScript(statement);
  }
  

  public void navigateToUrl(String url) {
    String statement = "parent.redirectToUrlCommand([{name: 'url', value: '" + URLDecoder.decode(url, StandardCharsets.UTF_8) + "'}])";
    PrimeFaces.current().executeScript(statement);
  }
}
