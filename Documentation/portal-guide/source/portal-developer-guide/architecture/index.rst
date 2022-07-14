.. _architecture:

Architecture
************

.. _architecture-portal-process-modules-structure:

.. important:: 
      The CSS styles, Java methods, etc. which are not explicitly documented are
      only used internally in Portal. Don't use them because they can be
      changed in future versions.

The Portal system consists of the following modules: portal-component, PortalKit, PortalTemplate and AxonIvyExpress. 
While AxonIvyExpress is optional, all other modules are mandatory.

|process-module-structure|

.. _architecture-portalkit:

portal-component
================

**Contains a set of public UI components** 

This module contains 5 JSF Ivy components such as User Selection, Role Selection,
Document Table, Process Chain and Process History. The end user can use these
components without other modules (PortalKit, PortalTemplate and AxonIvyExpress modules).

PortalKit
==========

**Contains a set of UI components** 

This module contains a set of JSF Ivy Component to manipulate and display Ivy
Process Data such as tasks, cases, absences, ...,  CSS styles and JavaScript
files for components and the theme library. This is the most important module
that you need to use the Portal. This module also contains the AdminSettings
component that is used to configure Portal.

.. _architecture-portal-template:

PortalTemplate
===============

**Provides the default templates and pages of the Portal**

This module contains templates for developers. They offer features like top
menu, application menu and user menu. It also contains start process links to
default pages like Portal home, Portal task list, Portal case list etc..
We advise developers to depend on this module to facilitate Portal customization.

.. _architecture-axonivy-express:

AxonIvyExpress
================

**No Code Process Management**

This tool set allows end users to create their own processes and manage them easily.
It gives users more flexibility when working with the Portal.

This project has been extended from PortalTemplate. It allows the end user to

-  Create his own workflow
-  Create and modify a web form for this workflow

.. |process-module-structure| image:: images/process-module-structure.png

Axon Ivy Express also provides an AdHoc process feature. When Express is deployed, users can start an AdHoc process from any task. 
Adhoc allows users to define additional process steps which will be executed before the current task.