.. _page-header-footer:

Page Header And Footer
======================

The components ``PageHeader`` and ``PageFooter`` show the header and footer applied to every Portal page.

|page-header-footer|

You can add a customized header and footer to every page by overriding the
``PageHeader`` and ``PageFooter`` Html Dialogs.
Refer to :dev-url:`Axon Ivy HtmlOverride wizard
</doc/9.4.0-m229/designer-guide/how-to/overrides.html?#override-new-wizard>` to
override HTML dialogs.

If you use Case Scope, you need to copy the ``PortalStart`` process from
``portal`` to your project. This ensures that the process request is
issued through your project instead of the (standard) portal.

.. |page-header-footer| image:: ../../screenshots/dashboard/page-header-footer.png