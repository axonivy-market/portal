.. _customization-portal-home:

Legacy Portal home
==================

.. _customization-portal-home-before-beginning:

Before beginning
----------------

This guide assumes that you are already familiar with CSS to integrate
your own widgets.

.. _customization-portal-home-introduction:

Introduction
------------

The default home page of Portal has three built-in widgets in order:
``Process widget (1)``, ``Task widget (2)``, ``Statistic widget (3)``. If it does
not fit your needs, you can replace it by your own one. We decided that
based on screen size, widget may become hidden, not smaller.

|home-page-template|

.. _customization-portal-home-basic-usage:

Basic usage
-----------

Following these steps to have your own Portal Home:

#. Create a new home page which uses the
   ``DefaultHomePageTemplate.xhtml`` template. By doing this, your new
   home page will inherit the widgets from the default home page and has
   a place holder for your own widgets.

   Your custom home page should look like below:

   .. code-block:: html
   
      <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml">
      <ui:define name="customWidget">
      ...
      </ui:define>
      </ui:composition>

#. Create a new process start named ``DefaultApplicationHomePage.ivp`` for the new
   home page. Now you will use this process start as the entry point of
   your Portal instead of the default one. To let Portal know about
   your new Portal home, you have to set default pages to your project, follow this chapter to customize standard processes:
   :dev-url:`Standard Processes </doc/9.3.5/designer-guide/user-interface/standard-processes/index.html>`


.. _customization-portal-home-advanced-usage:

Advanced usage
--------------

The ``DefaultHomePageTemplate.xhtml`` template supports some
customizations.

.. _customization-portal-home-advanced-usage-display-hide-the-default-widgets:

Display/hide the default widgets
--------------------------------

The template has three parameters: ``displayProcessWidget``,
``displayTaskWidget``, ``displayStatisticWidget`` to display or hide the
default widgets. Their default values are true, you can set them to
boolean values (true/false) to display or hide them as you need.

.. code-block:: html

      <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml">
      <ui:param name="displayTaskWidget" value="false" />
      </ui:composition>
..

.. note:: Task widget now is hidden.


.. _customization-portal-home-advanced-usage-customize-the-default-widget-sections:

Customize the default widget's sections
---------------------------------------

The template has the placeholders to redefine the default widgets'
sections.

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

Add your own widgets
--------------------

The template has a placeholder to add your own widgets. Your own
widgets' default positions are next to statistic widget, you can change
them by setting the default widgets' positions.

.. code-block:: html

   <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml"  >
   <ui:define name="customWidget">
   <ic:my.namespace.ComponentName  />
   </ui:define>
   </ui:composition>
..

.. note:: This custom widget will show below the 3 default widget

.. _customization-portal-home-advanced-usage-change-the-pages-title:

Change the page's title
-----------------------

The default page title is Dashboard. Apply the following code to change it:

.. code-block:: html

   <ui:composition template="/layouts/DefaultHomePageTemplate.xhtml" >
   <ui:define name="title">YOUR PAGE'S TITLE</ui:define>
   </ui:composition>

.. _customization-portal-home-user-guide:

Customize user guide
--------------------

When user guide is shown, default order of guide steps is Welcome > Main Menu > Processes > Tasks > Username > Statistics.
If you want to customize the guide, define the sections and using the ``GuideOverlayPanel`` components, 
refer to ``CustomizedGuide.xhtml`` in the ``portal-developer-examples`` project. 

The supported sections: ``welcomeGuide``, ``mainMenuGuide``, ``processWidgetGuide``, ``taskWidgetGuide``, ``statisticWidgetGuide``, ``userSettingsGuide``

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