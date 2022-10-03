.. _components-portal-components-style-customization:

Style Customization
*******************

How to customize
^^^^^^^^^^^^^^^^

1. You have to add a new css file to your resources and import it into your template.

   Code Example:

   .. code-block:: html

      <ui:composition template="/layouts/basic-10.xhtml">
         <ui:define name="title">test</ui:define>
         <ui:define name="content">
            <ic:com.axonivy.portal.components.ProcessHistory businessEntityId="alpha" chunkSize="12" scrollHeight="600" />
            <h:outputStylesheet name="layouts/styles/process-history-customize.css" />
         </ui:define>
      </ui:composition>

   .. note::
      You have to place your css file in a ``<h:outputStylesheet />`` below the component to override defined styles.

2. Within this file you can override default css variables of components. For example, the \--process-history-description-text-color:

   .. code-block:: css

      :root {
         --process-history-description-text-color: red;
      }

List of css variables that you can override
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Process Chain
^^^^^^^^^^^^^

.. csv-table::
  :file: ../documents/css_variables/process_chain.csv
  :header-rows: 1
  :class: longtable
  :widths: 2 1 2


Process History
^^^^^^^^^^^^^^^

.. csv-table::
  :file: ../documents/css_variables/process_history.csv
  :header-rows: 1
  :class: longtable
  :widths: 2 1 2


Process Viewer
^^^^^^^^^^^^^^

.. csv-table::
  :file: ../documents/css_variables/process_viewer.csv
  :header-rows: 1
  :class: longtable
  :widths: 2 1 2
