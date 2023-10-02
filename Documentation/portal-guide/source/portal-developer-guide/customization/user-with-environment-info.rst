.. _customization-user-with-environment-info:

User With Environment Info (Deprecated)
=======================================
.. warning:: Deprecated: This feature is marked for removal in version LTS 12.

This component shows environment info when the ``Portal.ShowEnvironmentInfo`` variable is TRUE.

|user-with-environment-info|

To change the displayed information, update ``Portal.GlobalEnvFooterInfo`` variable and ``Portal.GlobalHostFooterInfo`` variable in the :ref:`admin-settings` (HTML code is also supported).

If you use Case Scope, you need to copy the ``PortalStart`` process from the
project ``portal`` to your project. This ensures that the process request is
issued through your project instead of ``portal``.

.. |user-with-environment-info| image:: ../../screenshots/dashboard/environment-info.png