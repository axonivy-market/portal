.. _customization-user-with-environment-info:

User with environment info
==========================

This component shows environment info when the ``SHOW_ENVIRONMENT_INFO`` variable value is TRUE.

|user-with-environment-info|

You could change the displayed information by overriding the ``UserWithEnvironmentInfo`` Html Dialog, refer to 
`<https://developer.axonivy.com/doc/9.1/designer-guide/how-to/overrides.html#overrides-tool-reference>`_.

If you use Case Scope, you need to copy the ``PortalStart`` process from ``PortalTemplate`` to your project and
it is ensured that the process request is issued through your project instead of PortalTemplate

.. |user-with-environment-info| image:: ../../screenshots/dashboard/environment-info.png