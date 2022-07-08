package com.axonivy.portal.component.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.primefaces.PrimeFaces;

/**
 * Portal API for navigation in iFrame
 *
 */
public final class PortalNavigatorInFrameAPI {
  private PortalNavigatorInFrameAPI() {}

  /**
   * Navigate to target url
   * @param url target url
   */ 
  public static void navigateToUrl(String url) {
    String statement = "parent.redirectToUrlCommand([{name: 'url', value: '" + URLDecoder.decode(url, StandardCharsets.UTF_8) + "'}])";
    PrimeFaces.current().executeScript(statement);
  }
  
  /**
   * Navigate to portal home
   */
  public static void navigateToPortalHome() {
    String statement = "parent.redirectToHomePageCommand()";
    PrimeFaces.current().executeScript(statement);
  }
}