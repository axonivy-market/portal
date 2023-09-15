.. _customization-user-with-environment-info:

User With Environment Info (Deprecated)
=======================================
.. warning:: Deprecated: This feature is marked for removal in version LTS 12.

This component shows environment info when the ``Portal.ShowEnvironmentInfo`` variable is TRUE.

|user-with-environment-info|

To change the displayed information, override the ``UserWithEnvironmentInfo``
Html Dialog. Refer to :dev-url:`|ivy| HtmlOverride wizard
</doc/|version|/designer-guide/how-to/overrides.html?#override-new-wizard>` to
override ``UserWithEnvironmentInfo`` HTML dialog.

If you use Case Scope, you need to copy the ``PortalStart`` process from the
project ``portal`` to your project. This ensures that the process request is
issued through your project instead of ``portal``.

.. |user-with-environment-info| image:: ../../screenshots/dashboard/environment-info.png