package ch.ivy.addon.portalkit.publicapi;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.primefaces.PrimeFaces;

import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Portal API for navigation in iFrame
 *
 */
public final class PortalNavigatorInFrameAPI {
  private PortalNavigatorInFrameAPI() {}
  
  /**
   * Navigates to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page: task list or task details or global search
   */
  public void navigateToPortalEndPage() {
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_FINISHED.toString(), false);
    String statement = "parent.redirectToEndPageCommand([{name: 'taskId', value: " + Ivy.wfTask().getId() + "}]);";
    PrimeFaces.current().executeScript(statement);
  }

  public void navigateToUrl(String url) {
    String statement = "parent.redirectToUrlCommand([{name: 'url', value: '" + URLDecoder.decode(url, StandardCharsets.UTF_8) + "'}])";
    PrimeFaces.current().executeScript(statement);
  }
}