.. _public-api:

Public API
**********

The Portal exposes a focused Java Public API in the
``com.axonivy.portal.components.publicapi`` package (module: ``portal-components``).
Use these helpers to integrate with Portal pages, navigate, and control
visibility or behavior from your own processes and beans.

.. important::
   Only the APIs documented here are supported and stable. Classes and
   methods not explicitly documented are internal implementation details and
   may change without notice.

ProcessStartAPI
===============

Find startable process links by a friendly request path (IWebStartable ID).

- ``findStartableLinkByUserFriendlyRequestPath(String)``: returns a relative link
  if the current user may start it, otherwise an empty string.
- ``findRelativeUrlByProcessStartFriendlyRequestPath(String)``: returns the
  relative link regardless of start permissions, or an empty string if not found.

Example::

   import com.axonivy.portal.components.publicapi.ProcessStartAPI;

   String friendly = "Start Processes/PortalStart/DefaultApplicationHomePage.ivp";
   String startable = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath(friendly);
   if (!startable.isEmpty()) {
     // redirect to startable
   }

PortalNavigatorAPI (no iFrame)
==============================

Navigate between Portal pages and build deep links when your UI is not embedded
in the Portal iFrame.

- ``navigateToPortalHome()`` and ``navigateToPortalEndPage()``
- ``buildUrlToPortalCaseDetailsPageById/ByUUID(...)``
- ``buildUrlToPortalTaskDetailsPageById/ByUUID(...)``
- ``getPasswordResetUrl(String token, String username)``

Example::

   import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;

   String caseUrl = PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageById(12345L);
   String taskUrl = PortalNavigatorAPI.buildUrlToPortalTaskDetailsPageByUUID("TASK-UUID");
   PortalNavigatorAPI.navigateToPortalHome();

PortalNavigatorInFrameAPI (inside iFrame)
========================================

Control navigation from a page running inside Portal's iFrame.

- ``navigateToUrl(String)``
- ``navigateToPortalHome()``
- ``navigateToPortalEndPage()``

Example (bean code)::

   import com.axonivy.portal.components.publicapi.PortalNavigatorInFrameAPI;

   PortalNavigatorInFrameAPI.navigateToPortalEndPage();

TaskAPI and CaseAPI
===================

Hide or show specific tasks/cases in the Portal lists by setting/clearing the
``HIDE`` additional property.

Example::

   import com.axonivy.portal.components.publicapi.TaskAPI;
   import com.axonivy.portal.components.publicapi.CaseAPI;
   import ch.ivyteam.ivy.workflow.ITask;
   import ch.ivyteam.ivy.workflow.ICase;

   // Hide
   TaskAPI.setHidePropertyToHideInPortal(someTask);
   CaseAPI.setHidePropertyToHideInPortal(someCase);

   // Unhide
   TaskAPI.removeHidePropertyToDisplayInPortal(someTask);
   CaseAPI.removeHidePropertyToDisplayInPortal(someCase);

BusinessDetailsAPI
==================

Define a business details page for a case. Supports either an
``IWebStartable`` ID or an external URL, with optional "embed in frame".

Examples::

   import com.axonivy.portal.components.publicapi.BusinessDetailsAPI;
   import com.axonivy.portal.components.dto.BusinessDetailsDTO;

   // by IWebStartable ID (full or unique trailing part)
   BusinessDetailsAPI.create(
     BusinessDetailsDTO.builder()
       .path("designer/portal-components-examples/Start Processes/BusinessDetails/showInvestmentRequestCustomFields.ivp")
       .isEmbedInFrame(true)
       .build()
   );

   // by external link
   BusinessDetailsAPI.create("https://example.com/details/123");

SanitizeAPI
===========

Escape strings for safe embedding in JavaScript to prevent injections/XSS.

Example::

   import com.axonivy.portal.components.publicapi.SanitizeAPI;

   String safe = SanitizeAPI.escapeForJavascript(userInput);

Notes
=====

- Ensure the target startables are active and accessible for the current user.
- Portal and your projects should run in the same security context.
- Prefer the ``portal-components`` module's public APIs over internal classes.

