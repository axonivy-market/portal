.. _public-api-details:

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
==========================================

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

RoleAPI
=======

Manage properties for roles programmatically.

- ``setProperty(IRole role, String key, String value)``: Set a property on a role.
- ``removeProperty(IRole role, String key)``: Remove a property from a role.

Example::

   import com.axonivy.portal.components.publicapi.RoleAPI;
   import ch.ivyteam.ivy.security.IRole;

   IRole role = ...; // get your role
   RoleAPI.setProperty(role, "customKey", "customValue");
   RoleAPI.removeProperty(role, "customKey");

ApplicationMultiLanguageAPI
============================

Retrieve CMS values based on the current user's locale for multilingual menu support.

- ``getCmsValueByUserLocale(String cmsURI)``: Get CMS value for specific URI based on current locale.

Example::

   import com.axonivy.portal.components.publicapi.ApplicationMultiLanguageAPI;

   String localizedValue = ApplicationMultiLanguageAPI.getCmsValueByUserLocale("/Labels/MyLabel");

AiAssistantAPI
==============

Helper methods for integrating AI assistant features with Portal processes.

- ``addIframeIvyProcessLinkToAiResult(String link, Map<String, String> params, AiResultDTO result)``:
  Add an iframe-embedded Ivy process link to AI result.
- ``generateExecutableResult(String link)``: Generate an executable result pattern.
- ``generateErrorAiResult(Throwable error, String errorDescription)``: Create error result from exception.
- ``generateErrorAiResult(String error)``: Create error result from error message.
- ``createSomethingWentWrongError()``: Create a generic error result.

Example::

   import com.axonivy.portal.components.publicapi.AiAssistantAPI;
   import com.axonivy.portal.components.dto.AiResultDTO;
   import java.util.Map;

   AiResultDTO result = new AiResultDTO();
   Map<String, String> params = Map.of("param1", "value1");
   AiAssistantAPI.addIframeIvyProcessLinkToAiResult("Start Processes/MyProcess.ivp", params, result);

   // Generate executable result
   String executable = AiAssistantAPI.generateExecutableResult("/portal/home");

   // Handle errors
   AiResultDTO errorResult = AiAssistantAPI.createSomethingWentWrongError();

PortalDateTimePatternAPI
========================

Get date/time patterns based on Portal's variables and formatting locale.

- ``getDateTimePattern()``: Get date time pattern based on these variables Portal.DateTimeFormat.HideTime and Portal.DateTimeFormat.HideYear.
- ``getDatePattern()``: Get date pattern based on the variable Portal.DateTimeFormat.HideYear.
- ``getDateTimePatternForFiltering(boolean isDateFilter, int dateFormat)``: Get date time pattern for filtering based on the variable Portal.DateTimeFormat.DateFilterWithTime.

Example::

   import com.axonivy.portal.components.publicapi.PortalDateTimePatternAPI;

   // Use for displaying dates/times
   String dateTimePattern = PortalDateTimePatternAPI.getDateTimePattern();
   String datePattern = PortalDateTimePatternAPI.getDatePattern();

   // Use for filtering dates/times
   String filterPattern = PortalDateTimePatternAPI.getDateTimePatternForFiltering(true, DateFormat.SHORT);

Notes
=====

- Ensure the target startables are active and accessible for the current user.
- Portal and your projects should run in the same security context.
- Prefer the ``portal-components`` module's public APIs over internal classes.
