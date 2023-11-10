.. _page-header-footer:

Page Header And Footer
======================

Introduction
------------

You have the option to customize the header and footer that will be applied to every page within the Portal.


Customize
---------
Create the callable subprocess in your project with

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

.. tip::

   Refer to process ``CustomPageHeaderAndFooter`` in project ``portal-developer-examples``
   for an example of page header and footer customization.

