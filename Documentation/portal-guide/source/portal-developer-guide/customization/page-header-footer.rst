.. _page-header-footer:

Page header and footer
======================

The ``PageHeader`` component shows header and the ``PageFooter`` component shows footer in every page.

|page-header-footer|

You could add header and footer to every page by overriding the ``PageHeader`` and ``PageFooter`` Html Dialogs.
Refer to :dev-url:`Axon Ivy HtmlOverride wizard </doc/9.3.5/designer-guide/how-to/overrides.html?#override-new-wizard>` to override HTML dialogs.

If you use Case Scope, you need to copy the ``PortalStart`` process from ``PortalTemplate`` to your project and
it is ensured that the process request is issued through your project instead of PortalTemplate


.. |page-header-footer| image:: ../../screenshots/dashboard/page-header-footer.png