package com.axonivy.portal.components.publicapi;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.SessionAttribute;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Public API to override the default Portal growl message shown after a task ends.
 *
 * <p>Call {@code setCustomMessage} <b>before</b> the task completes, i.e. before proceed or
 * cancel. Portal then displays the given message in the global growl instead of the default
 * task-finished or task-left feedback. It applies to both IFrame and dialog tasks, and is
 * cleared once displayed.</p>
 *
 * <pre>
 * PortalGrowlMessageAPI.setCustomMessage("Order submitted.");
 * PortalGrowlMessageAPI.setCustomMessage("Order submitted.", "Order #12345 is now in progress.");
 * </pre>
 */
public final class PortalGrowlMessageAPI {

  private PortalGrowlMessageAPI() {}

  /**
   * Sets the summary text of the growl message shown after the task ends.
   *
   * @param summary the summary (title) text
   */
  public static void setCustomMessage(String summary) {
    setCustomMessage(summary, null);
  }

  /**
   * Sets the summary and detail text of the growl message shown after the task ends.
   *
   * @param summary the summary (title) text
   * @param detail an optional detail line shown below the summary; blank values are ignored
   */
  public static void setCustomMessage(String summary, String detail) {
    Ivy.session().setAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE.name(), summary);
    if (StringUtils.isNotBlank(detail)) {
      Ivy.session().setAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name(), detail);
    }
  }
}
