.. _components-portal-components-style-customization:

Style Customization
*******************

Introduction
^^^^^^^^^^^^

Portal Components use CSS custom properties (variables) for styling, allowing you to customize colors, fonts, spacing, and other visual aspects without modifying component code.
Override these CSS variables in your own stylesheet to match your corporate design or application theme.

How to customize
^^^^^^^^^^^^^^^^

1. You have to add a new css file to your resources and import it into your template.

   Code Example:

   .. code-block:: html

      <ui:composition template="/layouts/basic-10.xhtml">
         <ui:define name="title">test</ui:define>
         <ui:define name="content">
            <ic:com.axonivy.portal.components.ProcessChain businessCaseId="12345" />
            <h:outputStylesheet name="layouts/styles/process-chain-customize.css" />
         </ui:define>
      </ui:composition>

   .. note::

      You have to place your css file in a ``<h:outputStylesheet />`` below the component to override defined styles.

2. Within this file you can override default css variables of components. For example, the \--process-chain-item-background-color:

   .. code-block:: css

      :root {
         --process-chain-item-background-color: lightblue;
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
