.. _customization-task-item-details:

Task item details
=================

TaskItemDetails is a built-in component of Portal which contains the
role, user, task, case and time information which users can interact with.
In order to show needed task's information,
Portal supports overriding concept for TaskItemDetails.

Each TaskItemDetails contains

-  **Data and Description** ``1``

-  **Documents** ``2``

-  **Histories** ``3``

-  **Custom panels**

|task-standard|

.. important:: All visible widgets will be configured in
            :ref:`Task Details JSON Configuration File<task-details-json-configuration-file>`.


.. _task-details-json-configuration-file:

Task Details JSON Configuration File:
---------------------------------------------

-  This JSON Configuration File contains settings of all visible widgets on page.
-  Default JSON Configuration File:

   .. code-block:: html

      {
         "widgets": 
         [
            {
               "type": "information",
               "id": "information",
               "axisX": 0,
               "axisY": 0,
               "width": 6,
               "height": 12
            },
            {
               "type": "document",
               "id": "document",
               "axisX": 6,
               "axisY": 0,
               "width": 6,
               "height": 6
            },
            {
               "type": "history",
               "id": "history",
               "axisX": 6,
               "axisY": 6,
               "width": 6,
               "height": 6
            }
         ]
      }

   ..

-  Structure of each widget in JSON Configuration File:

   ``type``: There are 4 types: ``information``, ``document``, ``history``, ``custom``

   ``id``: It's used for detecting custom widgets.

   ``axisX``: HTML DOM Style ``top`` will be calculated by fomula ``axisX / 12 * 100%``

   ``axisY``: HTML DOM Style ``left`` will be calculated by fomula ``axisY / 12 * 100%``

   ``width``: HTML DOM Style ``width`` will be calculated by formula ``60 * width + 20 * (width - 1)``

   ``height``: HTML DOM Style ``height`` will be calculated by formula ``60 * height + 20 * (height - 1)``

.. important:: Please do not change **type** and **id** of widgets.
            You can change **axisX**, **axisY**, **width** and **height** for updating size and position of widgets.

.. _customization-task-item-details-how-to-overide-ui:

How to customize Task item details UI
----------------------------------

Refer to ``portal-developer-examples`` project for examples.

1. Introduce an Axon.ivy project which has ``PortalTemplate`` as a
   required library.

2. To customize task item details, you must customize Portal Home first.
   Refer to :ref:`Customize Portal
   home <customization-portal-home>` to set new home
   page.

3. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. Point PortalHome element to your custom home page in
   previous step. This process is new home page and administrator should
   register this link by Portal's Admin Settings.

4. Use `Axon.ivy HtmlOverride wizard <https://developer.axonivy.com/doc/9.1/designer-guide/how-to/overrides.html?#override-new-wizard>`_ to override ``PortalTaskDetails`` HTML dialog.

5. After previous steps, you can override Task item details UI elements
   to show or hide elements.

   To show or hide elements, please refer to :ref:`Show or hide
   elements <customization-task-item-details-how-to-overide-ui-show-hidden-ui>`.

   And to add a new elements, please refer to  :ref:`Add new Custom
   panel <customization-task-item-details-how-to-overide-ui-custom-body>`
   code.

.. _customization-task-item-details-how-to-overide-ui-show-hidden-ui:

Show or hide elements
--------------------------------

Refer to the ``ui`` tag list in ``PortalTaskDetails.xhtml`` of
PortalTemplate. If you want to show or hide any elements on
TaskItemDetails, you must override value of ``ui:param``

List valid ui parameters:

-  ``ui:param name="showItemDetailsHeader" value="true"``

   To show or hide Task Header, use ``showItemDetailsHeader``. Default value is true.


.. _customization-task-item-details-how-to-overide-ui-custom-body:

Add new Custom panel
--------------------

Refer to the ``taskItemDetailCustomPanel*`` section in
``PortalTaskDetails.xhtml`` of PortalTemplate.


-  You need to define the ``ui:define`` with the valid name such as
   ``taskItemDetailCustomPanel1``,
   ``taskItemDetailCustomPanel2``,
   ``taskItemDetailCustomPanel3``,
   ``taskItemDetailCustomPanel4``,
   ``taskItemDetailCustomPanel5`` and
   ``taskItemDetailCustomPanel6``.

   The ``taskItemDetailCustomPanel1``, ``taskItemDetailCustomPanel2``,
   The ``taskItemDetailCustomPanel3``, ``taskItemDetailCustomPanel4``,
   The ``taskItemDetailCustomPanel5`` and ``taskItemDetailCustomPanel6``
   will be shown base on values in :ref:`Task Details JSON Configuration File<task-details-json-configuration-file>`.


-  Add your custom code into that tag

-  Finally, your custom panel will be displayed on
   :ref:`TaskItemDetails <customization-task-item-details>`
   page

-  Example code for overriding custom panel box of task details:

   .. code-block:: html

            
      <!-- In this HTML dialog, we override task list header, task header, task filter, and task body -->

      <ui:composition template="/layouts/PortalTaskDetailsTemplate.xhtml">
      <ui:param name="task" value="#{data.task}" />
      <ui:param name="dataModel" value="#{data.dataModel}" />
      <ui:param name="portalPage" value="#{data.portalPage}" />
      <ui:param name="isFromTaskList" value="#{data.isFromTaskList}" />
      <ui:param name="isTaskStartedInDetails" value="#{data.isTaskStartedInDetails}" />
      <ui:define name="title">#{ivy.cms.co('/Labels/TaskItemDetail')}</ui:define>

      <!-- To show/hidden any sections of Task detail, you can turn true/false for below parameters -->
      <!-- To show the Header component inside Task details body. By default it's true -->
      <ui:param name="showItemDetailsHeader" value="true" />
      
      <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
      !!!!!!!!!!! TO ADD YOUR CUSTOMIZATION CODE ON THE TASK DETAILS PAGE, WE PROVIDE 2 SECTIONS AS BELOW HELP YOU CAN DO IT !!!!!!!!!!!!
      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
      
      <!-- Add a content as Custom panel for Task Detail-->
      <ui:define name="taskItemDetailCustomPanel1">
      <h:panelGroup styleClass="ui-g-12 ui-sm-12 custom-task-panel-1">
         <div class="card card-w-title ">
            <div class="task-detail-section-title u-truncate-text">
            <h:outputText value="This is custom panel section 1" />
            </div>
            <div class="Separator" />

            <div class="custom-task-details-panel">
            <h1>This is custom content 1</h1>
            <p>Custom height to auto</p>
            <p>Custom font size to 1.6rem</p>
            </div>
         </div>
      </h:panelGroup>
      </ui:define>

      <!-- Add a content as Custom panel for Task Detail-->
      <ui:define name="taskItemDetailCustomPanel2">
      <h:panelGroup styleClass="ui-g-12 ui-sm-12 custom-task-panel-2">
         <div class="card card-w-title ">
            <div class="task-detail-section-title u-truncate-text-2">
            <h:outputText value="This is custom panel section 2" />
            </div>
            <div class="Separator" />

            <div class="custom-task-details-panel">
            <h1>This is custom content 2</h1>
            <p>Custom height to auto</p>
            <p>Custom font size to 1.6rem</p>
            </div>
         </div>
      </h:panelGroup>
      </ui:define>

      <!-- Add a content as Custom panel for Task Detail-->
      <ui:define name="taskItemDetailCustomPanel3">
      <h:panelGroup styleClass="ui-g-12 ui-sm-12 custom-task-panel-3">
         <div class="card card-w-title ">
            <div class="task-detail-section-title u-truncate-text">
            <h:outputText value="This is custom panel section 3" />
            </div>
            <div class="Separator" />

            <div class="custom-task-details-panel">
            <h1>This is custom content 3</h1>
            <p>Custom height to auto</p>
            <p>Custom font size to 1.6rem</p>
            </div>
         </div>
      </h:panelGroup>
      </ui:define>


      <!-- Add a content as Custom panel for Task Detail-->
      <ui:define name="taskItemDetailCustomPanel4">
      <h:panelGroup styleClass="ui-g-12 ui-sm-12 custom-task-panel-4">
         <div class="card card-w-title ">
            <div class="task-detail-section-title u-truncate-text">
            <h:outputText value="This is custom panel section 4" />
            </div>
            <div class="Separator" />

            <div class="custom-task-details-panel">
            <h1>This is custom content 4</h1>
            <p>Custom height to auto</p>
            <p>Custom font size to 1.6rem</p>
            </div>
         </div>
      </h:panelGroup>
      </ui:define>

      <ui:define name="css">
         <h:outputStylesheet library="css" name="examples.css" />
      </ui:define>
      </ui:composition>
   ..

-  You have to overwrite :ref:`task-details.json<task-details-json-configuration-file>` for adding custom panels.

   You can add up to 6 custom widgets. In :ref:`Task Details JSON Configuration File<task-details-json-configuration-file>`:

   Id must be one in
   ``custom-widget-1``, ``custom-widget-2``,
   ``custom-widget-3``, ``custom-widget-4``,
   ``custom-widget-5`` and ``custom-widget-6``.

   Each id ``custom-widget-*`` corresponds to each defined ui ``taskItemDetailCustomPanel*``

   Type must be ``custom``.

-  Example Task Details JSON Configuration File:

   .. code-block:: html

      {
         "widgets": 
         [
            {
               "type": "information",
               "id": "information",
               "axisX": 0,
               "axisY": 0,
               "width": 6,
               "height": 12
            },
            {
               "type": "document",
               "id": "document",
               "axisX": 6,
               "axisY": 0,
               "width": 6,
               "height": 6
            },
            {
               "type": "history",
               "id": "history",
               "axisX": 6,
               "axisY": 6,
               "width": 6,
               "height": 6
            },
            {
               "type": "custom",
               "id": "custom-widget-1",
               "axisX": 0,
               "axisY": 12,
               "width": 6,
               "height": 5
            },
            {
               "type": "custom",
               "id": "custom-widget-2",
               "axisX": 6,
               "axisY": 12,
               "width": 6,
               "height": 5
            },
            {
               "type": "custom",
               "id": "custom-widget-3",
               "axisX": 0,
               "axisY": 17,
               "width": 6,
               "height": 5
            },
            {
               "type": "custom",
               "id": "custom-widget-4",
               "axisX": 6,
               "axisY": 17,
               "width": 6,
               "height": 5
            }
         ]
      }

   ..

-  After applied above example xhtml code and JSON Configuration File to your custom page, the custom panels
   will display as below

   |task-customized-new-style|


.. |task-standard| image:: ../../screenshots/task-detail/customization/task-standard.png
.. |task-customized-top| image:: ../../screenshots/task-detail/customization/task-customized-top.png
.. |task-customized-bottom| image:: ../../screenshots/task-detail/customization/task-customized-bottom.png
.. |task-customized-new-style| image:: ../../screenshots/task-detail/customization/customized-tasks-new-style.png


