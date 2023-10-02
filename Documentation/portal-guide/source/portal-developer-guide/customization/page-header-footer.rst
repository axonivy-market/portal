.. _page-header-footer:

Page Header And Footer (Deprecated)
===================================
.. warning:: Deprecated: This feature is marked for removal in version LTS 12.

Introduction
------------

The components ``PageHeader`` and ``PageFooter`` show the header and footer applied to every Portal page.

|page-header-footer|

Customize
---------
You can add a customized header and footer to every page by overriding the callable subprocess in your project with

**Signature**: portalGetPageHeaderAndFooter

+-----------------------+-----------------------+-----------------------------+
| Name                  | Type                  | Note                        |
+=======================+=======================+=============================+
|**Result**                                     |                             |
+-----------------------+-----------------------+-----------------------------+
| pageHeader            | java.lang.String      | HTML code is also supported |
+-----------------------+-----------------------+-----------------------------+
| pageFooter            | java.lang.String      | HTML code is also supported |
+-----------------------+-----------------------+-----------------------------+

If you use Case Scope, you need to copy the ``PortalStart`` process from
``portal`` to your project. This ensures that the process request is
issued through your project instead of the (standard) portal.

.. tip::

   Refer to process ``CustomPageHeaderAndFooter`` in project ``portal-developer-examples``
   for an example of page header and footer customization.

.. |page-header-footer| image:: ../../screenshots/dashboard/page-header-footer.png