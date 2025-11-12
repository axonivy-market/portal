.. _troubleshooting:

===============
Troubleshooting
===============

Here, we present solutions to some of the most common problems related
to the Axon Ivy Portal.

If you can't find your solution here there are some other sources which
could help:

-  Community

   The `Community <http://community.axonivy.com/>`__ contains a
   considerable amount of questions and answers related to Axon Ivy
   Designer and Engine.

-  Stack Overflow

   Problems related to common technologies like Java, JSF, Primefaces
   are answered on the web, e.g. on `Stack
   Overflow <http://www.stackoverflow.com/>`__.

-  Support

   You can get support via support@axonivy.com (support may be subject
   to charging, depending on your licence agreement).

.. _troubleshooting-ie-security-problem:

Portal Installation with Reverse Proxy
======================================

If you install the Portal behind a reverse proxy (Nginx, IIS, Apache httpd),
ensure the Axon Ivy Engine and proxy are aligned. Misconfiguration typically
shows up as broken links, wrong redirects, missing resources, or login loops.

Checklist:

- Forward headers: Make sure the proxy forwards ``Host``, ``X-Forwarded-Proto``,
  ``X-Forwarded-For`` and respects the original scheme/host. Configure the
  Engine to derive absolute links from these headers.
- Base URL: If your Portal is published under a context path (e.g. ``/portal``),
  forward that path and ensure the Engine knows the external base URL.
- Cookies: If Portal and apps are on different subdomains, validate cookie
  ``SameSite`` and ``Secure`` attributes and use HTTPS.
- Web resources: Proxy static resources (JS/CSS) and PrimeFaces endpoints
  without stripping required headers. Allow WebSocket/long polling if used.
- iFrame embedding: If you embed pages in iFrames, configure CSP/headers
  (``frame-ancestors``/``X-Frame-Options``) to allow your domains.

Common Symptoms and Fixes
-------------------------

- Symptom: Absolute links point to the proxy’s upstream host.

  Fix: Forward ``X-Forwarded-Proto`` and ``Host``; configure Engine to trust
  these headers when generating absolute URLs.

- Symptom: 404 on asset URLs under a context path.

  Fix: Ensure the proxy preserves the context path and does not rewrite away
  the leading path segment used by Portal.

- Symptom: Login loop after authentication.

  Fix: Check cookie domain/path and ``Secure``/``SameSite`` attributes; align
  proxy scheme (HTTPS) and forwarded headers.


Process Start link not found
============================

When using ``ProcessStartAPI.find...ByUserFriendlyRequestPath(...)``:

- Use the correct friendly request path (``IWebStartable`` ID). The full ID is
  safest (e.g. ``designer/<project>/Start Processes/.../something.ivp``). If
  you pass only the trailing part, ensure it is unique in the security context.
- The target startable must be active and the current user must have permission
  to start it.


Tasks or cases not visible
==========================

If a task/case does not show up in Portal lists, check whether the additional
property ``HIDE`` is set.

Use the Public API to unhide:

::

   import com.axonivy.portal.components.publicapi.TaskAPI;
   import com.axonivy.portal.components.publicapi.CaseAPI;

   TaskAPI.removeHidePropertyToDisplayInPortal(task);
   CaseAPI.removeHidePropertyToDisplayInPortal(caze);


Navigation inside iFrame not working
====================================

If calling ``PortalNavigatorInFrameAPI`` has no effect:

- Ensure the page actually runs inside Portal’s iFrame.
- Allow embedding by setting proper CSP (``frame-ancestors``) and
  ``X-Frame-Options`` on your upstream and proxy.
- Confirm the reverse proxy does not strip or cache dynamic JS responses.


Sanitizing user input for JavaScript
====================================

If rendering user-controlled content into inline JavaScript, use
``SanitizeAPI.escapeForJavascript(...)`` to escape special characters and
prevent injections.
