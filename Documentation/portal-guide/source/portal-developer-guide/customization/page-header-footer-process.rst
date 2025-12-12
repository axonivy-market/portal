.. _customization-page-header-and-footer:

Page Header and Footer
======================

Introduction
------------

The Page Header and Footer customization allows you to add custom HTML content to the top and bottom of every Portal page,
enabling you to display corporate branding, system-wide notifications, legal disclaimers, or navigation elements
that maintain consistent presence across all Portal views while supporting dynamic content through custom CSS and variables.

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

**Result:**

``pageHeader`` (java.lang.String)
   HTML content to display at the top of every Portal page.

``pageFooter`` (java.lang.String)
   HTML content to display at the bottom of every Portal page.