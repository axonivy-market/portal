.. _components-portal-components-process-viewer:

Process Viewer
**************

Introduction
^^^^^^^^^^^^

The Process Viewer component provides a visual representation of the process flow from a given Case ID or Process relative link.

|portal-process-viewer-component|

Attributes of this component:

.. csv-table::
  :file: ../documents/process_viewer_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

How to Use
^^^^^^^^^^

You can integrate the Process Viewer component in any page by including following code.

#. You have to set one of parameters below:

   - ``caseId``: show the process or Case Map of the case has ID equals to ``caseId``.

   .. code-block:: html

      <ic:com.axonivy.portal.components.ProcessViewer caseId="000001" containerStyleClass="process-viewer-container" />

   - ``processLink``: show the process or Case Map which by its defined relative link.

   .. code-block:: html

      <ic:com.axonivy.portal.components.ProcessViewer processLink="/designer/pro/portal-components-examples/182E92730FF57035/start.ivp" containerStyleClass="process-viewer-container" />

   .. warning::
      Sub Case (Technical Case) or Express Workflow Process is not supported.

#. You can customize the container style by using parameter ``containerStyleClass``

Refer to process ``ProcessViewerExample`` in project ``portal-components-examples`` for more details.


If you want to customize its style,
please refer to :ref:`components-portal-components-style-customization`.

.. |portal-process-viewer-component| image:: ../../screenshots/components/portal-process-viewer-component.png