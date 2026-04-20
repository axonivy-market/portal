.. _customization-navigateback:

Navigate Back
=============

.. _customization-navigateback.introduction:

When a task finishes, the Portal navigates back to the previous page. For
example, if a task is started from the homepage, it redirects to the homepage.
If a task is started from a task list, it redirects to that task list
after the task is finished.

If your project has a navigation button that does not finish a task, e.g Cancel,
you have to implement the following:

If using an IFrame:

-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorInFrameAPI``.
-  A specific URL: call ``navigateToUrl(String url)`` from class ``PortalNavigatorInFrameAPI``.

.. warning::
   Never pass unsanitized, user-controlled input directly as the ``url`` parameter.
   ``navigateToUrl()`` internally URL-decodes the value and escapes it for JavaScript
   context via ``SanitizeAPI.escapeForJavascript()``. However, you should still validate
   the URL at the application boundary (e.g., check that the scheme is ``http`` or
   ``https``) to prevent open-redirect or protocol-injection attacks.

Without IFrame:

-  Home page: call ``navigateToPortalHome()`` from class ``PortalNavigatorAPI``.
-  Previous page: call ``navigateToPortalEndPage()`` from class ``PortalNavigatorAPI``.

   |navigate-back|


.. |navigate-back| image:: images/navigate-back/navigate-back.png

