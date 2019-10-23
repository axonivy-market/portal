.. _axonivyportal.architecture:

Architecture
************

.. _axonivyportal.architecture.portalprocessmodulesstructure:

   .. important:: 

      The css styles, java methods, etc., which are not documented, are
      only used internally in Portal. Don't use them because they can be
      changed in next versions.

Currently Portal system contains the following modules:

|process-module-structure|

.. _axonivyportal.architecture.portalkit:

Portal kit
==========

Contains set of UI components. This module contains set of JSF Ivy
Component to provide user the usages to work with Ivy Process Data such
as: task, case, absence... , styles CSS and JavaScript file for
component and theme library. This is the most important module that user
needs to use Portal. This module also contains AdminSettings component
that is used to configure Portal.

.. _axonivyportal.architecture.portalstyle:

Portal style
============

Contains definition of styles that can be overridden/customized later.
As now Portal supports user to customize various colors of the layout
such as: background color, text color, border color, button color,
focus/hover color. In the current version you have to change each color
one by one. There are no common color definitions.

   .. note:: 

      This module is prepared for process developers to override and keep
      customer styles by editing CSS file, CMS's style.

.. _axonivyportal.architecture.portaltemplate:

Portal template
===============

Provides default portal's templates and pages. This module contains
templates page for Portal's user to use as composition, then they will
have supporting features such as : top menu, application menu, user
menu. It also contains some start process links to default page such as
: Portal home, Portal task list, Portal case list... . Portal's user is
advised to depend on this module to use Portal easily.

.. _axonivyportal.architecture.axonivyexpress:

Axon.ivy Express
================

The idea is that user can create his own process and can manage it
easily, it gives user more flexibility when working with Portal.

This project is an extended project from PortalTemplate. It provides:

-  Ability to create his/her own workflow

-  Tools to create and modify the web form for his workflow

.. |process-module-structure| image:: images/process-module-structure.png

