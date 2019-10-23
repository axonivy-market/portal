.. _axonivyportal.components.concept:

Widget concept
==============

.. _axonivyportal.components.concept.beforebeginning:

Before beginning
----------------

This guide assumes that you are already familiar with concepts inherent
in JSF programming and in Ivy development.

.. _axonivyportal.components.concept.introduction:

Introduction
------------

This document provides a high-level explanation of how to develop a
Portal widget. The ability to use Portal services and styles can be
particularly useful to developers who wish to do one or more of the
following:

-  Create their own widgets for Portal which have a consistent look and
   feel with the existing widgets.

-  Reuse existing portal services to create their own widgets which can
   manipulate Portal data, such as: cases, tasks, process starts,
   users,...

.. _axonivyportal.components.concept.howitis:

How it is
---------

This section introduces the Html Dialog Component and Portal services,
predefined styles used in building a widget, and goes on to describe the
process of designing and implementing.

Portal widgets should be implemented using the **Html Dialog Component**
technology from Axon.ivy and follow the famous model-view-controller
pattern.

Furthermore, to have a clean architecture and avoid a lot of headaches
going forward, we suggest that you should separate your widget into
layers like below:

-  Entities

   Entities are the business objects of the widget. They encapsulate the
   most general and high-level rules. They are the least likely to
   change when something external changes. (e.g.: by a change to page
   navigation, or security).

-  Use Cases

   Use case are widget specific business rules. This layer encapsulates
   and implements all of the use case of the widget. Changes in this
   layer should not affect the entities. This layer should not be
   affected by changes to externalities such as the Portal services, the
   UI, or any of the common frameworks.

-  Interfaces Adapters

   Interfaces Adapters are set of adapters that convert data from the
   format most convenient for the use cases and entities, to the format
   most convenient for some external agency such as the database or the
   web. Similarly, data is converted, in this layer, from the form most
   convenient for entities and use case, into the form most convenient
   for whatever persistence framework is being used. (The presenters,
   views, and controllers all belong in here. The models are likely just
   data structures that are passed from the controllers to the use case,
   and then back from the use cases to the presenters and views.)

-  Frameworks and Drivers

   This layer is generally composed of frameworks and tools such as the
   database, the web framework, Portal services, etc. This layer is
   where all the details go. The Web is a detail. The Portal services
   are detail. We keep these thing on the outside where they can do
   little harm.

   .. tip:: 
   
      There's no rule that says you must always have just the four layers
      above. However, you should always apply that the source code
      dependencies point from mechanisms to policies:

      Frameworks and Drivers > Interfaces Adapters > User Cases > Entities

      By doing so, you will create a widget that is intrinsically testable,
      independent of frameworks, independent of UI, independent of
      database, and independent of any external agency. When any of the
      external parts of the system become obsolete, like the database, or
      the web framework, you can replace those obsolete elements with a
      minimum of fuss.

.. _axonivyportal.components.concept.maintechnologyandconcept:

Main technology and concept
---------------------------

You should have an understanding of the following technology and concept
as you build your widget:

-  Managed Beans: in Html Dialog Component it is possible to communicate
   with normal Java Objects by using Managed Beans.

-  User Dialog Concept: an Html Dialog Component follows the
   model-view-controller pattern of the User Dialog Concept.

   -  Model is a data class whose data fields can be bound to widget
      properties of the view via the special object data.

   -  Controller is implemented by a series of UI processes that can be
      mapped to events on the view such as mouse clicks. Axon.ivy
      provides the keyword logic to call an event process or a method
      process in the logic.

   -  View of an Html Dialog is defined with the means of an XHTML
      document.

.. _axonivyportal.components.concept.services:

Services
--------

There are separate services for working with each type of data:

-  Application Services: set of services for getting information about
   applications.

-  Absence Services: set of services for manipulating the user's
   absence.

-  Case Services: set of services for working with cases and related
   data, such as: additional properties, notes,...

-  Task Services: set of services for working with tasks.

-  Process Start Services: set of services for querying process starts
   from the Portal system.

-  Security Services: set of services for querying users and roles.

-  User Setting Services: set of services for manipulating the user
   settings and related data, such as: email settings, language
   settings.

-  Portal Configuration Services: set of services for controlling the
   Portal configuration.

.. _axonivyportal.components.concept.builtinwidgets:

Built-in widgets
----------------

Portal comes with some useful widgets:

1. Task widget

   Below is the sample how the task widget being use in the default
   template:

   ``<ui:define name="taskWidget">``
   ``<ic:ch.ivy.addon.portalkit.component.TaskWidget id="task-widget" tasks="#{logic.getTasksOfSessionUser()}" ... />``
   ``</ui:define>``

2. Process widget

   Below is the sample how the process widget being use in the default
   template:

   ``<ui:define name="processWidget">``

   ``<ic:ch.ivy.addon.portalkit.component.ProcessWidget id="process-widget" compactMode="true" ... .>``
   
   ``</ui:define>``

3. Statistic widget

   Below is the sample how the statistic widget being use in the default
   template:

   ``<ui:define name="statisticWidget">``

   ``<ic:ch.ivy.addon.portalkit.component.StatisticWidget id="statistics-widget" compactMode="true" ... >``

   ``...``

   ``</ic:ch.ivy.addon.portalkit.component.StatisticWidget>``

   ``</ui:define>``

Portal setup these widget with the default settings for you, but you can
always re-define them in order to match with your needs. Moreover, if
you want to turn off a built-in widget, you can simply leave its
ui:define container empty like this:

``<ui:define name="taskWidget">``

``<!-- leave it empty -->``

``</ui:define>``

.. _axonivyportal.components.concept.predefinedstyles:

Predefined styles
-----------------

There are separate common styles are predefined to ensure every Portal
widget has a consistent structure and appearance:

``<div class="widget">``

``<div class="widget-header">``

``<ul class="widget-header-menu">``

``<li class="widget-header-menu-item">...</li>``

``<li class="widget-header-menu-item">...</li>``

``<li class="widget-header-menu-item">...</li>``

``...``

``</ul>``

``...``

``</div>``

``<div class="widget-content">``

``<div class="widget-content-list">``

``<div class="widget-content-list-item">...</div>``

``<div class="widget-content-list-item">...</div>``

``<div class="widget-content-list-item">...</div>``

``...``

``</div>``

``</div>``

``<div class="widdget-footer">``

``...``

``</div>``

``</div>``

.. _axonivyportal.components.concept.flow:

Flow
----

The general flow for developing a widget for portal is as follows:

1. Design your widget, deciding which parts to implement in Ivy
   component, and which parts to implement as pure JSF.

2. Create an Html Dialog Component.

   The following code fragment defines an example Html Dialog component:

   ``<cc:interface componentType="IvyComponent">``

   ``<cc:attribute name="caption" />``

   ``</cc:interface>``

   ``<cc:implementation>``

   ``...``

   ``</cc:implementation>``

   A component could be inserted with the ic tag.

   ``<ic:my.namespace.ComponentName ... />``

   For more information, see the Html Dialog Component section in
   Axon.ivy Designer - Help: **Designer Guide > User Interface > User
   Dialogs > Html Dialogs**

3. If you are writing a widget, which manipulates task, case,...
   consider using Portal built-in services.
4. Optionally, your widgets can have their own configuration. There are
   separate methods for manipulating widget configuration:

   -  You can initiate or update your widget configuration by passing an
      JSON object to ``saveSettings()``.

   -  You can load your widget configuration by calling
      ``loadSettings()``.

.. _axonivyportal.components.concept.integration:

Integration
-----------

The general flow for integrating a widget into Portal homepage is as
follows:

1. Create a new home page which uses the
   ``DefaultHomePageTemplate.xhtml`` template. By doing this, your new
   home page will inherit the widget from the previous home page and has
   a place holder for your own widgets. Your custom home page should
   look like below:

   ``<ui:composition template="/layouts/DefaultHomePageTemplate.xhtml" xmlns="http://www.w3.org/1999/xhtml"``

   ``xmlns:f="http://xmlns.jcp.org/jsf/core" xmlns:h="http://xmlns.jcp.org/jsf/html"``

   ``xmlns:ui="http://xmlns.jcp.org/jsf/facelets" xmlns:ic="http://ivyteam.ch/jsf/component">``

   ``<ui:define name="customWidget">``

   ``...``

   ``</ui:define>``

   ``</ui:composition>``

2. Create a new process start for the new home page. Now you will use
   this process start as the entry point of your portal instead of the
   default one. To let portal know about your new portal home, you have
   to go to the portal settings and set the portal home url to the new
   one.

3. In your new home page, place your widget inside the customWidget
   section.

   ``<ui:define name="customWidget">``

   ``<ic:my.namespace.ComponentName ... />``

   ``...``

   ``</ui:define>``

For more details, visit
`#axonivyportal.customization.portalhome <#axonivyportal.customization.portalhome>`__.

.. _axonivyportal.components.concept.exceptionhandling:

Exception handling
------------------

Portal separates exception into 2 types: ajax and non-ajax exception.

Portal handle non-ajax exception for you. You do not need to do anything
for this type of exception.

Portal also handle ajax exception for you as default, but you can
implement your own exception handler by using the Primefaces built-in
exception handler: ``p:ajaxExceptionHandler``.
