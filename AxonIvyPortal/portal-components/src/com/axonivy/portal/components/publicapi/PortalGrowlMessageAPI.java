package com.axonivy.portal.components.publicapi;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Public API for setting a custom Portal growl message from within a task.
 *
 * <p>Call one of the {@code setCustomMessage} methods <b>before</b> the task completes
 * (before proceed / cancel). Portal will display the message in the global growl
 * instead of the default task-finished or task-left feedback.</p>
 *
 * <p>Works for both IFrame and dialog tasks.</p>
 *
 * <pre>
 * // Summary only
 * PortalGrowlMessageAPI.setCustomMessage("Order submitted successfully.");
 *
 * // Summary + detail
 * PortalGrowlMessageAPI.setCustomMessage("Order submitted.", "Order #12345 is now in progress.");
 * </pre>
 */
public final class PortalGrowlMessageAPI {

  /** Session attribute key — must stay in sync with {@code SessionAttribute.CUSTOM_GROWL_MESSAGE}. */
  private static final String ATTR_SUMMARY = "CUSTOM_GROWL_MESSAGE";
  /** Session attribute key — must stay in sync with {@code SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL}. */
  private static final String ATTR_DETAIL  = "CUSTOM_GROWL_MESSAGE_DETAIL";

  private PortalGrowlMessageAPI() {}

  /**
   * Sets a custom summary message to show in the Portal growl after the task finishes.
   *
   * @param summary the summary text of the growl message; must not be blank
   */
  public static void setCustomMessage(String summary) {
    setCustomMessage(summary, null);
  }

  /**
   * Sets a custom growl message with a summary and optional detail line.
   *
   * @param summary the summary (title) text of the growl message; must not be blank
   * @param detail  an optional detail line shown below the summary; may be {@code null} or empty
   */
  public static void setCustomMessage(String summary, String detail) {
    Ivy.session().setAttribute(ATTR_SUMMARY, summary);
    if (StringUtils.isNotBlank(detail)) {
      Ivy.session().setAttribute(ATTR_DETAIL, detail);
    }
  }
}
