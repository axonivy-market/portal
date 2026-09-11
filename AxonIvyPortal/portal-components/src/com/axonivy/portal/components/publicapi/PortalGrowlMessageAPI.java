package com.axonivy.portal.components.publicapi;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.SessionAttribute;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Public API to override the default Portal growl message shown after a task ends.
 *
 * <p>{@code setCustomMessage} does not add a growl message to the current page, it only stores the
 * message in the Ivy session of the current user. Portal reads it from the session right after the
 * task ended and displays it in the global growl instead of the default task-finished or task-left
 * feedback, then removes it from the session so that it is used exactly once.</p>
 *
 * <p>Call it <b>right before the task ends</b>, i.e. as the last step in front of the task end or
 * html dialog end element.</p>
 *
 * <pre>
 * PortalGrowlMessageAPI.setCustomMessage("Order submitted.");
 * PortalGrowlMessageAPI.setCustomMessage("Order submitted.", "Order #12345 is now in progress.");
 * </pre>
 */
public final class PortalGrowlMessageAPI {

  private PortalGrowlMessageAPI() {}

  /**
   * Stores the summary text of the growl message shown after the task ends in the session of the
   * current user. Call it right before the task ends.
   *
   * @param summary the summary (title) text
   */
  public static void setCustomMessage(String summary) {
    setCustomMessage(summary, null);
  }

  /**
   * Stores the summary and detail text of the growl message shown after the task ends in the
   * session of the current user. Call it right before the task ends.
   *
   * @param summary the summary (title) text
   * @param detail an optional detail line shown below the summary; blank values are ignored
   */
  public static void setCustomMessage(String summary, String detail) {
    Ivy.session().setAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE.name(), summary);
    if (StringUtils.isNotBlank(detail)) {
      Ivy.session().setAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name(), detail);
    } else {
      Ivy.session().removeAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name());
    }
  }
}
