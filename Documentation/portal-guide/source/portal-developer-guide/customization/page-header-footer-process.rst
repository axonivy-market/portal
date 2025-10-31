.. _customization-page-header-and-footer:

Page Header and Footer
======================

Introduction
------------

The Portal allows you to customize:

   - The page header and footer that is shown on each Portal page.

You can easily customize the page header and footer using HTML code. For example:

   .. code-block:: java

      in.pageHeader = "<div style='height: 20px; text-align: center; background-color: var(--primary-color); color: white'><span>custom page header</span></div>";
      in.pageFooter = "<div style='height: 20px; text-align: center; background-color: var(--primary-color); color: white'><span>custom page footer</span></div>";

.. tip::
   For a complete example, see the process ``CustomPageHeaderAndFooter`` in the ``portal-developer-examples`` project.

Page header and footer customization function
---------------------------------------------

Create a callable subprocess with

**Signature**: portalGetPageHeaderAndFooter

+-----------------------+-------------------+
| Name                  | Type              |
+=======================+===================+
|**Result**                                 |
+-----------------------+-------------------+
| pageHeader            | java.lang.String  |
+-----------------------+-------------------+
| pageFooter            | java.lang.String  |
+-----------------------+-------------------+