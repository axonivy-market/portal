.. _customization-user-with-environment-info:

User with environment info
==========================

This component shows environment info when the ``Portal.ShowEnvironmentInfo`` variable value is TRUE.

|user-with-environment-info|

You could change the displayed information by overriding the ``UserWithEnvironmentInfo`` Html Dialog.
Refer to `Axon Ivy HtmlOverride wizard <https://developer.axonivy.com/doc/nightly/designer-guide/how-to/overrides.html?#override-new-wizard>`_ to override ``UserWithEnvironmentInfo`` HTML dialog.

If you use Case Scope, you need to copy the ``PortalStart`` process from ``PortalTemplate`` to your project and
it is ensured that the process request is issued through your project instead of PortalTemplate

.. |user-with-environment-info| image:: ../../screenshots/dashboard/environment-info.png