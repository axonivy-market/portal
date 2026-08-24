.. _customization-growl-message:

Growl Message Customization
===========================

Introduction
------------

When a task is finished (**proceed**) or left (**cancel**), Portal shows a default feedback message in the global
growl, e.g. "You have finished the task successfully. Click here for details.". The growl message customization lets
you replace this default feedback with your own summary and detail text, so end users see business-relevant
information instead of the generic message.

Implementation
--------------

**Class**: ``com.axonivy.portal.components.publicapi.PortalGrowlMessageAPI``

``setCustomMessage(String summary)``
   Sets only a summary (title) text.

``setCustomMessage(String summary, String detail)``
   Sets a summary text together with a detail line shown below the summary.

Add a script element that calls one of these methods **right before the task ends**, i.e. as the last step in front
of the task end or html dialog end element of your process:

   .. code-block:: java

      import com.axonivy.portal.components.publicapi.PortalGrowlMessageAPI;

      // Summary only
      PortalGrowlMessageAPI.setCustomMessage("Order submitted successfully.");

      // Summary + detail
      PortalGrowlMessageAPI.setCustomMessage("Order submitted.", "Order #12345 is now in progress.");

Portal stores the message in the session of the current user, reads it right after the task ended and displays it in
the global growl instead of the default message.

Example
-------

There is an example implementation in the project ``portal-developer-examples`` under the process name ``GlobalGrowl``.

To see how it works, run the process ``customizedMessage.ivp`` or ``customizedMessageWithSkipTaskList.ivp``, then
finish or cancel the task. The global growl shows the customized message instead of the default Portal feedback.

.. hint::

   - The customized message is only used for the **next** task-ending action of the current session; after it is
     displayed, it is cleared automatically so subsequent tasks show the default message again unless you set it again.
   - If no customized message is set, Portal falls back to its default growl feedback.
   - The customized message follows the global variable ``Portal.DisplayMessageAfterFinishTask``. When growl feedback
     is turned off, no message is shown at all, not even a customized one. The stored message is still cleared, so it
     is not displayed later.
   - The message is stored per session, not per task or per browser tab. If a user works on several tasks of the same
     session in parallel, the message is consumed by whichever task ends first - one more reason to set it directly
     before the task ends.
