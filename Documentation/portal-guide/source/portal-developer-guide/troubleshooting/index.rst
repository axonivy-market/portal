.. _troubleshooting:

===============
Troubleshooting
===============

Solutions to common problems related to Axon Ivy Portal. This guide addresses deployment issues, configuration problems, and runtime errors to help you quickly resolve issues and maintain a stable Portal installation.

**Quick Navigation:**

- :ref:`troubleshooting-reverse-proxy` - Reverse proxy configuration
- :ref:`troubleshooting-process-start` - Process start link issues
- :ref:`troubleshooting-visibility` - Tasks or cases not appearing
- :ref:`troubleshooting-iframe` - iFrame navigation problems
- :ref:`troubleshooting-sanitization` - Input sanitization for JavaScript

**Additional Help Resources:**

- **Community**

  The `Axon Ivy Community <https://community.axonivy.com/>`__ contains questions and answers about Portal, Designer, and Engine. Search existing topics or ask new questions.

- **Stack Overflow**

  Problems related to common technologies like Java, JSF, and PrimeFaces are answered on `Stack Overflow <https://stackoverflow.com/>`__. Use tags like ``axon-ivy``, ``jsf``, ``primefaces``.

- **Support**

  Contact support@axonivy.com for direct assistance (support may be subject to charging, depending on your licence agreement).

.. _troubleshooting-reverse-proxy:

Portal Installation with Reverse Proxy
======================================

If you install the Portal behind a reverse proxy (Nginx, IIS, Apache httpd), ensure the Axon Ivy Engine and proxy are aligned. Misconfiguration typically shows up as broken links, wrong redirects, missing resources, or login loops.

**Configuration Checklist:**

- **Forward headers**: Make sure the proxy forwards ``Host``, ``X-Forwarded-Proto``, ``X-Forwarded-For`` and respects the original scheme/host. Configure the Engine to derive absolute links from these headers.
- **Base URL**: If your Portal is published under a context path (e.g. ``/portal``), forward that path and ensure the Engine knows the external base URL.
- **Cookies**: If Portal and apps are on different subdomains, validate cookie ``SameSite`` and ``Secure`` attributes and use HTTPS.
- **Web resources**: Proxy static resources (JS/CSS) and PrimeFaces endpoints without stripping required headers. Allow WebSocket/long polling if used.
- **iFrame embedding**: If you embed pages in iFrames, configure CSP/headers (``frame-ancestors``/``X-Frame-Options``) to allow your domains.

Common Symptoms and Fixes
-------------------------

.. table::
   :widths: 40 60

   +---------------------------------------------+-----------------------------------------------------------+
   | Symptom                                     | Solution                                                  |
   +=============================================+===========================================================+
   | Absolute links point to the proxy's         | Forward ``X-Forwarded-Proto`` and ``Host``; configure     |
   | upstream host                               | Engine to trust these headers when generating absolute    |
   |                                             | URLs                                                      |
   +---------------------------------------------+-----------------------------------------------------------+
   | 404 on asset URLs under a context path      | Ensure the proxy preserves the context path and does not  |
   |                                             | rewrite away the leading path segment used by Portal      |
   +---------------------------------------------+-----------------------------------------------------------+
   | Login loop after authentication             | Check cookie domain/path and ``Secure``/``SameSite``      |
   |                                             | attributes; align proxy scheme (HTTPS) and forwarded      |
   |                                             | headers                                                   |
   +---------------------------------------------+-----------------------------------------------------------+
   | CSS/JavaScript files not loading            | Verify proxy passes through static resource requests      |
   |                                             | without modification; check MIME types                    |
   +---------------------------------------------+-----------------------------------------------------------+
   | WebSocket connection failures               | Configure proxy to allow WebSocket upgrade requests;      |
   |                                             | set proper timeout values                                 |
   +---------------------------------------------+-----------------------------------------------------------+

.. tip::
   Test your reverse proxy configuration with browser developer tools (Network tab) to identify which resources fail to load and inspect response headers.

.. _troubleshooting-process-start:

Process Start link not found
============================

When using ``ProcessStartAPI.find...ByUserFriendlyRequestPath(...)``, the process start link may not be found due to incorrect path or permissions.

**Common Causes and Solutions:**

**Incorrect Path Format**

Use the correct friendly request path (``IWebStartable`` ID). The full ID is safest:

.. code-block:: java

   // Recommended: Use full path
   ProcessStartAPI.findByUserFriendlyRequestPath(
       "designer/myproject/Start Processes/MyFolder/something.ivp"
   );
   
   // Alternative: Use trailing part only if unique
   ProcessStartAPI.findByUserFriendlyRequestPath("something.ivp");

.. important::
   If you pass only the trailing part, ensure it is unique in the security context. Multiple processes with the same name will cause ambiguity.

**Permission Issues**

The target startable must be:

- Active and deployed
- Accessible to the current user with proper permissions
- In the same security context

**Debugging Steps:**

#. Verify the process is deployed and active in Engine Cockpit
#. Check user permissions for the process start
#. Confirm the friendly request path matches the deployed process
#. Test with full path before using shortened version

.. _troubleshooting-visibility:

Tasks or cases not visible
==========================

If a task or case does not show up in Portal lists, check whether the additional property ``HIDE`` is set. Portal excludes hidden tasks and cases from default queries.

**Solution: Unhide Tasks and Cases**

Use the Public API to remove the hide property:

.. code-block:: java

   import com.axonivy.portal.components.publicapi.TaskAPI;
   import com.axonivy.portal.components.publicapi.CaseAPI;

   // Unhide a task
   TaskAPI.removeHidePropertyToDisplayInPortal(task);
   
   // Unhide a case
   CaseAPI.removeHidePropertyToDisplayInPortal(caze);

**When to Use Hide Property:**

The ``HIDE`` property is useful for:

- Background/system tasks that users shouldn't interact with
- Internal process steps not meant for Portal display
- Temporary tasks during process execution
- Technical cases used for coordination

**Checking Hide Status:**

To check if a task or case is hidden:

.. code-block:: java

   import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
   
   // Check task hide status
   String hideValue = task.customFields().stringField("HIDE").getOrDefault("");
   boolean isHidden = "true".equalsIgnoreCase(hideValue);
   
   // Check case hide status
   String caseHideValue = caze.customFields().stringField("HIDE").getOrDefault("");
   boolean isCaseHidden = "true".equalsIgnoreCase(caseHideValue);

.. note::
   Hidden tasks and cases are still accessible via API calls. The hide property only affects Portal UI visibility.

.. _troubleshooting-iframe:

Navigation inside iFrame not working
====================================

If calling ``PortalNavigatorInFrameAPI`` has no effect, the page may not be properly embedded or configured for iFrame communication.

**Common Causes and Solutions:**

**Not Running Inside Portal iFrame**

Ensure the page actually runs inside Portal's iFrame context:

.. code-block:: java

   import com.axonivy.portal.components.publicapi.PortalNavigatorInFrameAPI;
   
   // Check if running in Portal iframe
   boolean isInFrame = PortalNavigatorInFrameAPI.isInFrame();
   
   if (isInFrame) {
       // Navigation will work
       PortalNavigatorInFrameAPI.navigateToPortalHome();
   } else {
       // Use alternative navigation
       Ivy.html().applicationHomeRef().redirect();
   }

**CSP and X-Frame-Options Issues**

Allow embedding by setting proper headers:

.. code-block:: xml

   <!-- In web.xml or security configuration -->
   <context-param>
       <param-name>primefaces.CSP</param-name>
       <param-value>frame-ancestors 'self' https://yourportal.com</param-value>
   </context-param>

**Reverse Proxy Stripping Headers**

Confirm the reverse proxy does not strip or cache dynamic JS responses:

- Check proxy configuration for JavaScript MIME types
- Verify ``Content-Type: application/javascript`` is preserved
- Disable caching for dynamic Portal responses
- Test without proxy to isolate the issue

**Debugging Steps:**

#. Open browser developer console
#. Check for ``postMessage`` errors or warnings
#. Verify iframe parent/child relationship in DOM
#. Test navigation API in browser console: ``parent.postMessage(...)``
#. Review CSP violations in console

.. _troubleshooting-sanitization:

Sanitizing user input for JavaScript
====================================

If rendering user-controlled content into inline JavaScript, always sanitize input to prevent XSS (Cross-Site Scripting) attacks.

**Use SanitizeAPI for JavaScript Context**

Use ``SanitizeAPI.escapeForJavascript(...)`` to escape special characters:

.. code-block:: java

   import com.axonivy.portal.components.publicapi.SanitizeAPI;
   
   // User input that needs to be rendered in JavaScript
   String userInput = "User's input with 'quotes' and \"special\" chars";
   
   // Sanitize before rendering in JS context
   String sanitized = SanitizeAPI.escapeForJavascript(userInput);
   
   // Safe to use in JavaScript
   String javascript = "var userName = '" + sanitized + "';";

**Common Scenarios Requiring Sanitization:**

.. table::
   :widths: 50 50

   +----------------------------------------------+--------------------------------------------+
   | Scenario                                     | Example                                    |
   +==============================================+============================================+
   | JavaScript variable assignment               | ``var name = '#{sanitized}';``             |
   +----------------------------------------------+--------------------------------------------+
   | Event handler attributes                     | ``onclick="handleClick('#{sanitized}')"``  |
   +----------------------------------------------+--------------------------------------------+
   | Dynamic script generation                    | ``<script>alert('#{sanitized}');</script>``|
   +----------------------------------------------+--------------------------------------------+
   | JSON data embedded in HTML                   | ``var data = {name: '#{sanitized}'};``     |
   +----------------------------------------------+--------------------------------------------+

**Best Practices:**

- **Always sanitize user input** before embedding in JavaScript contexts
- **Use parameterized APIs** when possible instead of string concatenation
- **Validate input** on the server side before sanitization
- **Use CSP headers** as an additional security layer
- **Review security guidelines** regularly for new attack vectors

**Example: Safe Dynamic JavaScript:**

.. code-block:: xml

   <!-- XHTML page with safe JavaScript -->
   <h:outputScript>
       var config = {
           userName: '#{sanitizeBean.escapeJs(userData.name)}',
           message: '#{sanitizeBean.escapeJs(userData.message)}',
           timestamp: #{userData.timestamp}  // Numbers don't need escaping
       };
       
       console.log('User: ' + config.userName);
   </h:outputScript>

.. warning::
   Never trust user input. Even internal users may inadvertently or maliciously inject harmful content. Always sanitize.
