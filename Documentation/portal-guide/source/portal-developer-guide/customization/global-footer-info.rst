.. _customization-global-footer-info:

Global Footer Info (Deprecated)
=======================================
.. warning:: Deprecated: This feature is marked for removal in version LTS 12.

This component shows environment info when the ``Portal.GlobalFooterInfo`` variable is not empty.

|global-footer-info|

To change the displayed information, update ``Portal.GlobalFooterInfo`` variable in the :ref:`admin-settings` (HTML code is also supported).

If you use Case Scope, you need to copy the ``PortalStart`` process from the
project ``portal`` to your project. This ensures that the process request is
issued through your project instead of ``portal``.

.. |global-footer-info| image:: ../../screenshots/dashboard/environment-info.png