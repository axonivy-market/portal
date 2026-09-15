.. _components-portal-components-style-customization:

Style Customization
*******************

Introduction
^^^^^^^^^^^^

Portal Components use CSS custom properties (variables) for styling, allowing you to customize colors, fonts, spacing, and other visual aspects without modifying component code.
Override these CSS variables in your own stylesheet to match your corporate design or application theme.

.. note::

   A stylesheet added this way only affects the page it is loaded on. Portal also renders
   some of these components on its own pages, for example the Process Chain in the task
   header, which sits outside the iframe that shows your task form. Styles from your
   project cannot reach those instances. To restyle them, override the same CSS variables
   in ``custom.css`` instead, see :ref:`customization-portal-logos-and-colors`.

How to customize
^^^^^^^^^^^^^^^^

1. You have to add a new css file to your resources and import it into your template.

   Code Example:

   .. code-block:: html

      <ui:composition template="/layouts/basic-10.xhtml">
         <ui:define name="title">test</ui:define>
         <ui:define name="content">
            <ic:com.axonivy.portal.components.ProcessChain steps="#{['Step 1','Step 2','Step 3']}" actualStepIndex="1" />
            <h:outputStylesheet name="layouts/styles/process-chain-customize.css" />
         </ui:define>
      </ui:composition>

   .. note::

      You have to place your css file in a ``<h:outputStylesheet />`` below the component to override defined styles.

2. Within this file you can override default css variables of components. For example, the \--process-chain-menu-color:

   .. code-block:: css

      :root {
         --process-chain-menu-color: lightblue;
      }

List of css variables that you can override
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Process Chain
-------------

.. csv-table::
  :file: ../documents/css_variables/process_chain.csv
  :header-rows: 1
  :class: longtable
  :widths: 2 1 2

Process Viewer
--------------

.. csv-table::
  :file: ../documents/css_variables/process_viewer.csv
  :header-rows: 1
  :class: longtable
  :widths: 2 1 2
