.. _customization-user-with-environment-info:

User with environment info
==========================

This component shows environment info when the ``Portal.ShowEnvironmentInfo`` variable value is TRUE.

|user-with-environment-info|

You could change the displayed information by overriding the ``UserWithEnvironmentInfo`` Html Dialog.
Refer to :dev-url:`Axon Ivy HtmlOverride wizard </doc/9.3.5/designer-guide/how-to/overrides.html?#override-new-wizard>` to override ``UserWithEnvironmentInfo`` HTML dialog.

If you use Case Scope, you need to copy the ``PortalStart`` process from ``PortalTemplate`` to your project and
it is ensured that the process request is issued through your project instead of PortalTemplate

.. |user-with-environment-info| image:: ../../screenshots/dashboard/environment-info.png