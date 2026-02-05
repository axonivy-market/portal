package com.axonivy.portal.components.publicapi;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.primefaces.PrimeFaces;

import com.axonivy.portal.components.enums.SessionAttribute;
import com.axonivy.portal.components.generic.navigation.BaseNavigator;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Portal API for navigation in iFrame
 *
 */
public final class PortalNavigatorInFrameAPI extends BaseNavigator {
	private PortalNavigatorInFrameAPI() {
	}

	/**
	 * Navigate to target url
	 * 
	 * @param url target url
	 */
	public static void navigateToUrl(String url) {
		String statement = "parent.redirectToUrlCommand([{name: 'url', value: '"
				+ URLDecoder.decode(url, StandardCharsets.UTF_8) + "'}])";
		PrimeFaces.current().executeScript(statement);
	}

    /**
     * Reset the current task and navigate to target url.
     * This method stores the current task's UUID in the session for reset purposes
     * and then redirects to the specified URL.
     * 
     * @param url target url to navigate to after resetting the task
     */
    public static void resetTaskAndNavigateToUrl(String url) {
      Ivy.session().setAttribute(SessionAttribute.RESET_TASK_UUID.name(), Ivy.wfTask().uuid());
      String statement = "parent.resetTaskAndRedirectToUrlCommand([{name: 'url', value: '" + URLDecoder.decode(url, StandardCharsets.UTF_8) + "'}])";
      PrimeFaces.current().executeScript(statement);
    }

  /**
   * Navigate to portal home
   */
  public static void navigateToPortalHome() {
    navigateToUrl(Ivy.html().applicationHomeLink().getAbsoluteEncoded());
  }

	/**
	 * Navigate to PortalEndPage without finishing a task, e.g. clicking on Cancel
	 * button then back to previous page: task list or task details or global search
	 */
	public static void navigateToPortalEndPage() {
		String statement = "parent.redirectToEndPageCommand([{name: 'taskId', value: " + Ivy.wfTask().getId() + "}]);";
		PrimeFaces.current().executeScript(statement);
	}

}