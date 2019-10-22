.. _axonivyportal.customization.portalhome:

Portal home
===========

.. _axonivyportal.customization.portalhome.beforebeginning:

Before beginning
----------------

This guide assumes that you are already familiar with CSS to integrate
your own widgets.

.. _axonivyportal.customization.portalhome.introduction:

Introduction
------------

The default home page of Portal has three built-in widgets in order:
``Process widget``, ``Task widget``, ``Statistic widget``. If it does
not fit your needs, you can replace it by your own one. We decided that
based on screen size, widget may become hidden, not smaller.

.. _axonivyportal.customization.portalhome.basicusage:

Basic usage
-----------

Following these steps to have your own Portal Home:

1. Create a new home page which uses the
   ``DefaultHomePageTemplate.xhtml`` template. By doing this, your new
   home page will inherit the widgets from the default home page and has
   a place holder for your own widgets.

   Your custom home page should look like below:

   ``<ui:composition template="/layouts/DefaultHomePageTemplate.xhtml" ... >``

   ``<ui:define name="customWidget">``

   ``...``

   ``</ui:define>``

   ``</ui:composition>``

2. *In case of single Portal:* Create a new process start for the new
   home page. Now you will use this process start as the entry point of
   your portal instead of the default one. **To let portal know about
   your new portal home, you have to go to the portal settings and set
   the portal home url to the new one. e.g:
   HOMEPAGE_URL=http://localhost:8081/ivy/pro/designer/CustomizePortalHome/157454FCA39C3844/start.ivp**

   *In case of multi Portal:* refer to `Setup multi
   portals <#axonivyportal.settings.adminsettings>`__ to setup.

..

   **Note**

   Currently, responsive custom home page is not supported.

.. _axonivyportal.customization.portalhome.advancedusage:

Advanced usage
--------------

The ``DefaultHomePageTemplate.xhtml`` template supports some
customizations.

.. _axonivyportal.customization.portalhome.advancedusage.displayhidethedefaultwidgets:

Display/hide the default widgets
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The template has three parameters: ``displayProcessWidget``,
``displayTaskWidget``, ``displayStatisticWidget`` to display or hide the
default widgets. Their default values are true, you can set them to
boolean values (true/false) to display or hide them as you need.

``<ui:composition template="/layouts/DefaultHomePageTemplate.xhtml" ... >``

``<ui:param name="displayTaskWidget" value="false" />``

``</ui:composition>``

   **Tip**

   Task widget now is hidden.

.. _axonivyportal.customization.portalhome.advancedusage.customizethedefaultwidgetssections:

Customize the default widget's sections
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The template has the placeholders to redefine the default widgets'
sections.

``<ui:composition template="/layouts/DefaultHomePageTemplate.xhtml" ... >``

``<ui:define name="statisticWidget">``

``<div class="js-dashboard-main-content-3rd-col dashboard-main-content-3rd-col layout-col">``

``<h:panelGroup layout="block" styleClass="js-statistic-widget" id="statistic-widget-container">``

``<!-- KEEP THE STATISTIC WIDGET -->``

``<ic:ch.ivy.addon.portalkit.component.StatisticWidget id="statistics-widget" compactMode="true" tasks="#{tasks}" ... >``

``<!-- ADD THE WEATHER WIDGET BELOW STATISTIC WIDGET -->``

``<ic:my.namespace.WeatherWidget />``

``</h:panelGroup>``

``</div>``

``</ui:define>``

``</ui:composition>``

.. _axonivyportal.customization.portalhome.advancedusage.addyourownwidgets:

Add your own widgets
~~~~~~~~~~~~~~~~~~~~

The template has a placeholder to add your own widgets. Your own
widgets' default positions are next to statistic widget, you can change
them by setting the default widgets' positions.

``<ui:composition template="/layouts/DefaultHomePageTemplate.xhtml" ... >``

``<ui:define name="customWidget">``

``<ic:my.namespace.ComponentName ... />``

``</ui:define>``

``</ui:composition>``

   **Tip**

   This custom widget will show below the 3 default widget

.. _axonivyportal.customization.portalhome.advancedusage.changethepagestitle:

Change the page's title
~~~~~~~~~~~~~~~~~~~~~~~

The default page title is Cockpit. Apply the following code to change
it:

``<ui:composition template="/layouts/DefaultHomePageTemplate.xhtml" ... >``

``<ui:define name="pageTitle">YOUR PAGE'S TITLE</ui:define>``

``</ui:composition>``
