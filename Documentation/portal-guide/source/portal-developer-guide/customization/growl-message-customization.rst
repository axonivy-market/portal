.. _customization-growl-message:

Growl Message Customization
===========================

Introduction
------------

When a task finishes (proceed) or is left/cancelled, Portal shows a default feedback message in the global growl, 
i.e. "You have finished the task successfully. Click here for details.". The growl message customization allows you to override this default
feedback with your own summary and detail text, so end users see business-relevant information instead of the generic message.

Implementation
--------------

Call ``PortalGrowlMessageAPI.setCustomMessage`` from your process **before** the task is finished, i.e. before the
**proceed** or **cancel/leave** action completes. Portal stores the message for the current session and displays it
in the global growl right after the task ends, instead of the default message.

**Class**: ``com.axonivy.portal.components.publicapi.PortalGrowlMessageAPI``

``setCustomMessage(String summary)``
   Sets only a summary (title) text.

``setCustomMessage(String summary, String detail)``
   Sets a summary text together with a detail line shown below the summary.

Example, add a script element right before the task end or html dialog end element of your process:

   .. code-block:: java

      import com.axonivy.portal.components.publicapi.PortalGrowlMessageAPI;

      // Summary only
      PortalGrowlMessageAPI.setCustomMessage("Order submitted successfully.");

      // Summary + detail
      PortalGrowlMessageAPI.setCustomMessage("Order submitted.", "Order #12345 is now in progress.");

.. tip::
   For a complete example, see the process ``GlobalGrowl`` in the ``portal-developer-examples`` project.

Example
-------

There is an example implementation in the project ``portal-developer-examples`` under the process name ``GlobalGrowl``.

To see how it works, run the process ``customizedMessage.ivp`` or ``customizedMessageWithSkipTaskList.ivp``, 
then finish or cancel the task. The global growl shows the customized message instead of the
default Portal feedback.

.. hint::

   - The customized message is only used for the **next** task-finishing action of the current session; after it is
     displayed, it is cleared automatically so subsequent tasks show the default message again unless you set it again.
   - If no customized message is set, Portal falls back to its default growl feedback.
   - The summary and detail text are treated as plain text; they are escaped before being rendered.
