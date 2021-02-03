package ch.ivy.addon.portalkit.publicapi;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.primefaces.PrimeFaces;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Portal API for navigation in iFrame
 *
 */
public final class PortalNavigatorInFrameAPI {
  private PortalNavigatorInFrameAPI() {}
  
  /**
   * Navigate to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page: task list or task details or global search
   */
  public static void navigateToPortalEndPage() {
    String statement = "parent.redirectToEndPageCommand([{name: 'taskId', value: " + Ivy.wfTask().getId() + "}]);";
    PrimeFaces.current().executeScript(statement);
  }

  /**
   * Navigate to target url
   * @param url target url
   */
  public static void navigateToUrl(String url) {
    String statement = "parent.redirectToUrlCommand([{name: 'url', value: '" + URLDecoder.decode(url, StandardCharsets.UTF_8) + "'}])";
    PrimeFaces.current().executeScript(statement);
  }

}
