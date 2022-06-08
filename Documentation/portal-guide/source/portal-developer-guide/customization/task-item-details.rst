.. _customization-task-item-details:

Task item details
=================

TaskItemDetails is a built-in component of Portal which contains the
role, user, task, case and time information which users can interact with.
In order to show needed task's information,
Portal supports you to override concepts of TaskItemDetails component.

Each TaskItemDetails contains

-  **Data and Description** ``1``

-  **Documents** ``2``

-  **Histories** ``3``

-  **Custom panels (widgets)**

|task-standard|

.. important:: All visible widgets will be configured in :ref:`Global Variable Portal.TaskDetails<task-details-configuration-variable>`.


.. _task-details-configuration-variable:

How to configure widgets in task details
----------------------------------------

-  Settings of all visible widgets on task details page are saved in **Global Variable Portal.TaskDetails**.
-  Cockpit Administrator can configure widgets via global variable **Portal.TaskDetails** on Cockpit settings page.
   |edit-variable-portal-task-case-details|

-  Default configuration includes 3 widgets.

   .. code-block:: html

      [
         {
            "id": "default-task-detail",
            "filters": {
               "categories" : ["support"],
               "states" : ["DONE", "SUSPENDED"]
            },
            "widgets": 
            [
               {
                  "id": "information",
                  "type": "information",
                  "layout": {
                     "x": 0, "y": 0, "w": 6, "h": 6
                  }
               },
               {
                  "id": "history",
                  "type": "history",
                  "layout": {
                     "x": 6, "y": 6, "w": 6, "h": 6
                  }
               },
               {
                  "id": "document",
                  "type": "document",
                  "layout": {
                     "x": 6, "y": 0, "w": 6, "h": 6
                  }
               },
               {
                  "id": "custom",
                  "type": "custom",
                  "layout": {
                     "x": 0, "y": 6, "w": 6, "h": 6
                  },
                  "data" : {
                     "processStart": "Start Processes/TaskDetailsCustomWidgetExample/invoiceDetails.ivp",
                     "params": {
                        "startedTaskId": "task.id",
                        "startedTaskCategory": "task.category",
                        "invoiceId": "000001573",
                        "invoiceDescription": "task.customFields.invoiceDescription"
                     }
                  }
               }
            ]
         }
      ]

   ..

-  Structure of each task details layout in variable **Portal.TaskDetails**:

   ``id``: ID which used to identify layout.

   ``widgets``: definition of widgets in layout.

   ``filters``: conditions to determine which tasks able to use the layout. There are 2 types of filter ``categories`` (task categories) and ``states`` (task states).

-  Structure of each widget inside task details layout in variable **Portal.TaskDetails**:

   ``type``: There are 4 types: ``information``, ``document``, ``history``, ``custom``

   ``x``: HTML DOM Style ``left`` will be calculated by formula ``x / 12 * 100%``

   ``y``: HTML DOM Style ``top`` will be calculated by formula ``y / 12 * 100%``

   ``w``: HTML DOM Style ``width`` will be calculated by formula ``60 * w + 20 * (w - 1)``

   ``h``: HTML DOM Style ``height`` will be calculated by formula ``60 * h + 20 * (h - 1)``

   ``styleClass`` (optional): add CSS Classes to HTML DOM

   ``style`` (optional): add inline style to HTML DOM

   ``data`` (for custom widget): data for custom widget using iframe

      ``type``: type of custom widget which is not using Iframe. There are two type ``taskItemDetailCustomPanelTop`` and ``taskItemDetailCustomPanelBottom``

      ``url``: URL for external website

      ``processStart``: relative link to the ivy process which will be displayed in custom widget

      ``params``: paramters for ivy process above, each parameter can be defined as follows:

         - Key name that will be parameter name for ivy process above. Note: don't use ``taskId``.

         - Key value for task: must start with ``task.``. Support 2 values: ``task.id``, ``task.category``.

         - Key value for task custom fields: must start with ``task.customFields.``, follow by custom field name.

         - Other key value will be treated as hard code value.

.. important::
   -  **Do not change** ``type`` of widgets.
      You can change ``x``, ``y``, ``w`` and ``h`` to update size and position of widgets.
   -  ``x``, ``y``, ``w`` and ``h`` must be **integers**.
   -  ``x + w`` must **not be larger** than **12**.
   -  For data of custom widget, if you input ``processStart``, don't input ``url``. You can only use one of them.
   -  We support all task states for filter type ``states``. Please refer to :dev-url:`Task States </doc/nightly/public-api/ch/ivyteam/ivy/workflow/TaskState.html>` to check for avaiable task states.


.. _customization-task-item-details-how-to-overide-ui:

How to customize Task item details UI
-------------------------------------

Refer to ``portal-developer-examples`` project for examples.

#. Introduce an Axon Ivy project which has ``PortalTemplate`` as a
   required library.

#. To customize task item details, you must customize Portal Home first.
   Refer to :ref:`Customize Portal
   home <customization-portal-home>` to set new home
   page.

#. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. Point PortalHome element to your custom home page in
   previous step. This process is new home page and administrator should
   register this link by Portal's Admin Settings.

#. Use :dev-url:`Axon Ivy HtmlOverride wizard </doc/nightly/designer-guide/how-to/overrides.html?#override-new-wizard>` to override ``PortalTaskDetails`` HTML dialog.

#. After previous steps, you can override Task item details UI elements
   to show custom panels, show or hide elements.

   To **show or hide elements**, refer to :ref:`Show or hide
   elements <customization-task-item-details-how-to-overide-ui-show-hidden-ui>`.

   To **show custom panels (widgets)**, refer to :ref:`Show Custom
   Panels (Widgets) <customization-task-item-details-how-to-overide-ui-custom-body>`.


.. _customization-task-item-details-how-to-overide-ui-show-hidden-ui:

Show or hide elements
---------------------

Refer to list of ``ui:param`` tag in ``PortalTaskDetails.xhtml`` of
PortalTemplate. If you want to show or hide elements on
TaskItemDetails, you must override ``ui:param``

List of valid ``ui:param``:

-  ``ui:param name="showItemDetailsHeader" value="true"``

   To show or hide Task Header, use ``showItemDetailsHeader``. Default value is true.

.. _customization-task-item-details-how-to-overide-ui-custom-body:

Show Custom Panels (Widgets)
----------------------------


.. tip:: 
   To quickly understand how the JSON of custom task details looks like.

   - Refer to ``variables.Portal.TaskDetails.json`` file in ``portal-developer-examples/resources/files`` project.
   - Copy to the corresponding application folder located in the designer.

      - e.g: AxonIvyDesigner/configuration/applications/designer

   - Create some destroyed task or start the process ``Start Processes/TaskDetailsCustomWidgetExample/SalesManagement.ivp`` in ``portal-developer-examples`` project.
   - Go to the example homepage by the process ``Start Processes/ExamplePortalStart/DefaultApplicationHomePage.ivp``
   - And then go to task details to check the new custom layout.

   About how to configure Variables, refer to :dev-url:`Axon Ivy Variables </doc/nightly/designer-guide/configuration/variables.html>`


There are **two steps** for adding new custom panels.

#. **Cockpit admin** must configure global variable :ref:`Portal.TaskDetails<task-details-configuration-variable>`
   on Cockpit Page to add custom widgets.

   .. _task-details-custom-configuration-variable-example:
   
   -  Example Portal.TaskDetails with layout configuration includes 4 custom widgets:
   
   .. code-block:: html

      [
         {
            "id": "default-task-detail",
            "widgets": 
               [
                  {
                     "type": "information",
                     "layout": {
                        "x": 0, "y": 4, "w": 6, "h": 12
                     }
                  },
                  {
                     "type": "document",
                     "layout": {
                        "x": 6, "y": 4, "w": 6, "h": 6
                     }
                  },
                  {
                     "type": "history",
                     "layout": {
                        "x": 6, "y": 10, "w": 6, "h": 6
                     }
                  },
                  {
                     "type": "custom",
                     "layout": {
                        "x": 0, "y": 0, "w": 12, "h": 4
                     },
                     "data" : {
                        "type": "taskItemDetailCustomPanelTop"
                     }
                  },
                  {
                     "type": "custom",
                     "layout": {
                        "x": 0, "y": 16, "w": 6, "h": 4
                     },
                     "data" : {
                        "type": "taskItemDetailCustomPanelBottom"
                     }
                  }
               ]
            }
        ]

   ..

#. To customize task details which do not use Iframe, refer to the ``taskItemDetailCustomPanel*`` section in ``PortalTaskDetails.xhtml`` of PortalTemplate.

   -  We need to define the ``ui:define`` tag with the valid name such as
      ``taskItemDetailCustomPanelTop`` and ``taskItemDetailCustomPanelBottom``.
   
      The ``taskItemDetailCustomPanel*`` will be displayed
      base on value of global variable
      :ref:`Portal.TaskDetails<task-details-configuration-variable>`.
   
   -  Add your custom code into ``<ui:define name="taskItemDetailCustomPanel*"></ui:define>`` tags.
   
   -  Example code for overriding custom panel box of task details:
   
      .. code-block:: html
   
               
         <!-- In this HTML dialog, we override task list header, task header, task filter, and task body -->
   
         <ui:composition template="/layouts/PortalTaskDetailsTemplate.xhtml">
            <ui:param name="task" value="#{data.task}" />
            <ui:param name="dataModel" value="#{data.dataModel}" />
            <ui:param name="portalPage" value="#{data.portalPage}" />
            <ui:param name="isFromTaskList" value="#{data.isFromTaskList}" />
            <ui:param name="isTaskStartedInDetails" value="#{data.isTaskStartedInDetails}" />
            <!-- To show/hidden any sections of Task detail, you can turn true/false for below parameters -->
            <!-- To show the Header component inside Task details body. By default it's true -->
            <ui:param name="showItemDetailsHeader" value="true" />
            <!-- To show the Notes component inside Task details body. By default it's true -->
            <ui:param name="showItemDetailsNotes" value="true" />
            <!-- To show the Documents component inside Task details body. By default, it's true -->
            <ui:param name="showItemDetailDocuments" value="true" />
            <ui:define name="title">#{ivy.cms.co('/Labels/TaskItemDetail')}</ui:define>
   
            <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
               !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
               !!!!!BELOW IS SAMPLE CODE FOR CUSTOMIZATION, WRAPPED IN <ui:remove> TAG. TO ACTIVATE THE CUSTOMIZATION, REMOVE <ui:remove> TAG!!!!!
               !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
               !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            <!-- Add a content as Custom panel for Task Detail on top -->
            <ui:define name="taskItemDetailCustomPanelTop">
               <h:panelGroup styleClass="ui-g-12 ui-sm-12 custom-task-panel">
               <div class="task-detail-section-title u-truncate-text">
                  <h:outputText value="This is custom panel on top section" />
               </div>
               <div class="Separator" />
   
               <div class="custom-task-details-panel-top">
                  <h1>This is custom content on top</h1>
                  <p>Custom height to auto</p>
                  <p>Custom font size to 1.2rem</p>
               </div>
               </h:panelGroup>
            </ui:define>
   
            <!-- Add a content as Custom panel for Task Detail on top-left-->
            <ui:define name="taskItemDetailCustomPanelBottom">
               <h:panelGroup styleClass="ui-g-12 ui-sm-12 custom-task-panel">
               <div class="task-detail-section-title u-truncate-text">
                  <h:outputText value="This is custom panel on bottom section" />
               </div>
               <div class="Separator" />
   
               <div class="custom-task-details-panel custom-task-details-panel-bottom">
                  <h1>This is custom content on bottom</h1>
                  <p>Custom height to auto</p>
                  <p>Custom font size to 1.2rem</p>
               </div>
               </h:panelGroup>
            </ui:define>
   
            <ui:define name="css">
               <h:outputStylesheet library="css" name="examples.css" />
            </ui:define>
         </ui:composition>
      ..
   
   -  After applied above **example xhtml code** and **example variable Portal.TaskDetails** to your custom page, custom panels
      will be displayed as the below image.
      ``taskItemDetailCustomPanelTop (1)``
   
      ``taskItemDetailCustomPanelBottom (2)``
   
      |task-customized-top|
      |task-customized-bottom|

#. To customize task details use Iframe, please make sure

   -  Must input parameter ``url`` if you want to use external URL.

   -  Must input parameter ``ivy`` if you want to use ivy start process.

   -  If you use ivy start process, you can predefine parameter for ``params``.

      Customized task details using external URL

      .. code-block:: html

         [
            {
               "id": "task-detail",
               "widgets": [
                  {
                     "type": "information",
                     "layout": {
                     "x": 0, "y": 0, "w": 4, "h": 12
                     }
                  },
                  {
                     "type": "custom",
                     "layout": {
                     "x": 6, "y": 0, "w": 8, "h": 6
                     },
                     "data" : {
                     "url": "https://www.axonivy.com/"
                     }
                  }
               ]
            }
         ]

      Result

      |task-customized-iframe-url|

      Customized task details using ivy process start, please refer to ``TaskDetailsCustomWidgetExample`` process in ``portal-developer-examples`` for more details

      .. code-block:: html

            [
               {
                  "id": "task-detail",
                  "widgets": [
                     {
                        "type": "information",
                        "layout": {
                        "x": 0, "y": 0, "w": 6, "h": 12
                        }
                     },
                     {
                        "type": "history",
                        "layout": {
                        "x": 6, "y": 6, "w": 6, "h": 6
                        }
                     },
                     {
                        "type": "custom",
                        "layout": {
                        "x": 0, "y": 6, "w": 6, "h": 6
                        },
                        "data" : {
                           "processStart": "Start Processes/TaskDetailsCustomWidgetExample/invoiceDetails.ivp",
                           "params": {
                              "startedTaskId": "task.id",
                              "startedTaskCategory": "task.category",
                              "invoiceId": "000001573",
                              "invoiceDescription": "task.customFields.invoiceDescription"
                           }
                        }
                     }
                  ]
               }
            ]

      Provide task custom field

      |task-customized-iframe-process-custom-field|

      Map parameters to process data

      |task-customized-iframe-process-input-mapping|

      Result

      |task-customized-iframe-process|


.. |task-standard| image:: ../../screenshots/task-detail/customization/task-standard.png
.. |task-customized-top| image:: ../../screenshots/task-detail/customization/task-customized-top.png
.. |task-customized-bottom| image:: ../../screenshots/task-detail/customization/task-customized-bottom.png
.. |edit-variable-portal-task-case-details| image:: images/customization/edit-variable-portal-task-case-details.png
.. |task-customized-iframe-url| image:: ../../screenshots/task-detail/customization/task-customized-iframe-url.png
.. |task-customized-iframe-process| image:: ../../screenshots/task-detail/customization/task-customized-iframe-process.png
.. |task-customized-iframe-process-custom-field| image:: images/customization/task-customized-iframe-process-custom-field.png
.. |task-customized-iframe-process-input-mapping| image:: images/customization/task-customized-iframe-process-input-mapping.png
