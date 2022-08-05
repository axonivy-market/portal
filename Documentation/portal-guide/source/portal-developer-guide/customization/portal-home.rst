.. _customization-portal-home:

Legacy Portal Home
==================

.. _customization-portal-home-before-beginning:

Before You Begin
----------------

This guide assumes that you are already familiar with CSS to integrate
your widgets.

.. _customization-portal-home-introduction:

Introduction
------------

The default Portal home page has three built-in widgets in this order:
``Process widget (1)``, ``Task widget (2)``, ``Statistic widget (3)``. If that does
not fit your needs, you can replace it with your customized ones. We decided that
based on screen size, widgets may become hidden, not smaller.

|home-page-template|

.. _customization-portal-home-basic-usage:

Basic Usage
-----------

Follow these steps to create your customzied Portal Home:

#. Create a new home page that uses the template
   ``DefaultHomePageTemplate.xhtml``. This way, your new home page will
   inherit the widgets from the default home page and is a placeholder for
   your widgets.

   Your custom home page should look like this:

   .. code-block:: html

      <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml">
      <ui:define name="customWidget">
      ...
      </ui:define>
      </ui:composition>

#. Create a new processstart named ``DefaultApplicationHomePage.ivp`` for your
   new home page. Now you will use this processstart as the entry point of your
   Portal instead of the default one. To make Portal aware of your new Portal
   home, you have to set the default pages in your project, as detailed in
   :dev-url:`Default Pages
   </doc/nightly/designer-guide/user-interface/default-pages/index.html>`


.. _customization-portal-home-advanced-usage:

Advanced Usage
--------------

The template ``DefaultHomePageTemplate.xhtml`` supports some customizations.

.. _customization-portal-home-advanced-usage-display-hide-the-default-widgets:

Display or Hide the Default Widgets
-----------------------------------

This template has three parameters: ``displayProcessWidget``,
``displayTaskWidget``, ``displayStatisticWidget`` to display or hide the
default widgets. Their default values are true. You can set them to
boolean values (true/false) to display or hide them as needed.

.. code-block:: html

      <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml">
      <ui:param name="displayTaskWidget" value="false" />
      </ui:composition>
..

.. note:: Task widget now is hidden.


.. _customization-portal-home-advanced-usage-customize-the-default-widget-sections:

Customize The Default Widget's Sections
---------------------------------------

The template includes placeholders to redefine the default widgets' sections.

.. code-block:: html

   <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml">
   <ui:define name="statisticWidget">
   <div class="js-dashboard-main-content-3rd-col dashboard-main-content-3rd-col layout-col">
   <h:panelGroup layout="block" styleClass="js-statistic-widget" id="statistic-widget-container">
   <!-- KEEP THE STATISTIC WIDGET -->
   <ic:ch.ivy.addon.portalkit.component.StatisticWidget id="statistics-widget" compactMode="true" tasks="#{tasks}">
   <!-- ADD THE WEATHER WIDGET BELOW STATISTIC WIDGET -->
   <ic:my.namespace.WeatherWidget />
   </h:panelGroup>
   </div>
   </ui:define>
   </ui:composition>

.. _customization-portal-home-advanced-usage-add-your-own-widgets:

Adding Your Own Widgets
-----------------------

The template has a placeholder to add your own widgets. Your own widgets'
default positions are next to statistics widget. You can change them by setting
the default widgets' positions.

.. code-block:: html

   <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml"  >
   <ui:define name="customWidget">
   <ic:my.namespace.ComponentName  />
   </ui:define>
   </ui:composition>
..

.. note:: This custom widget will show below the three default widget

.. _customization-portal-home-advanced-usage-change-the-pages-title:

Changing The Page Title
-----------------------

The default page title is **Dashboard**. Use the following code to change it:

.. code-block:: html

   <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml" >
   <ui:define name="title">YOUR PAGE'S TITLE</ui:define>
   </ui:composition>

.. _customization-portal-home-user-guide:

Customizing User Guide
----------------------

When the user guide is shown, the default order of guide steps is Welcome > Main Menu > Processes > Tasks > Username > Statistics.
If you want to customize the guide, define the sections and put them into the ``GuideOverlayPanel`` component.
Refer to ``CustomizedGuide.xhtml`` in the ``portal-developer-examples`` project.

The supported sections are: ``welcomeGuide``, ``mainMenuGuide``, ``processWidgetGuide``, ``taskWidgetGuide``, ``statisticWidgetGuide``, ``userSettingsGuide``

.. code-block:: html

	<ui:define name="welcomeGuide">
	  <ic:ch.ivy.addon.portalkit.component.WelcomePortalDialog id="welcome-portal-guide-component"
	    title="Welcome to Customized Guide"
	    onNext="PF('left-menu-guide').show()" updatedComponentAfterGuide="#{updatedComponentAfterGuide}"
	    rendered="#{guideBean.isGuideShown}" />
	</ui:define>

	<ui:define name="taskWidgetGuide">
	  <ic:ch.ivy.addon.portalkit.component.GuideOverlayPanel id="task-widget-guide-component"
	    targetComponent="task-widget" targetCssSelector="#task-widget" icon="fa-check-square-o"
	    title="#{ivy.cms.co('/ch.ivy.addon.portalkit.ui.jsf/components/guide/task/title')}"
	    content="#{ivy.cms.co('/ch.ivy.addon.portalkit.ui.jsf/components/guide/task/content')}"
	    updatedComponentAfterGuide="#{updatedComponentAfterGuide}"
	    rendered="#{guideBean.isGuideShown}" />
	</ui:define>
..


.. |home-page-template| image:: ../../screenshots/dashboard/dashboard-3-sections.png