.. _customization-task-item-details:

Task item details
=================

TaskItemDetails is a built-in component of Portal which contains the
role, user, task, case and time information which users can interact with.
To show needed task's information,
Portal supports you to override concepts of TaskItemDetails component.

Each TaskItemDetails contains

-  **Data and Description** ``1``

-  **Documents** ``2``

-  **Histories** ``3``

-  **Custom panels (widgets)**

|task-standard|

.. important:: All visible widgets will be configured in :ref:`Variable Portal.TaskDetails<task-details-configuration-variable>`.


.. _task-details-configuration-variable:

How to configure widgets in task details
----------------------------------------

-  Settings of all visible widgets on task details page are saved in **variable Portal.TaskDetails**.
-  Cockpit Administrator can configure widgets via variable **Portal.TaskDetails** on Cockpit settings page.
   |edit-variable-portal-task-case-details|

-  Default configuration includes 3 widgets.

   .. code-block:: html

      [
         {
            "id": "default-task-detail",
            "filters": {
               "categories" : ["support"],
               "states" : ["DONE", "OPEN"]
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
                     "processPath": "Start Processes/TaskDetailsCustomWidgetExample/invoiceDetails.ivp",
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

   ``filters``: conditions to determine which tasks are able to use the layout. There are 2 types of filter **:** ``categories`` (task categories) and ``states`` (task business states).

-  Structure of each widget inside task details layout in variable **Portal.TaskDetails**:

   ``type``: There are 4 types: ``information``, ``document``, ``history``, ``custom``

   ``x``: HTML DOM Style ``left`` will be calculated by formula ``x / 12 * 100%``

   ``y``: HTML DOM Style ``top`` will be calculated by formula ``y / 12 * 100%``

   ``w``: HTML DOM Style ``width`` will be calculated by formula ``60 * w + 20 * (w - 1)``

   ``h``: HTML DOM Style ``height`` will be calculated by formula ``60 * h + 20 * (h - 1)``

   ``styleClass`` (optional): add CSS Classes to HTML DOM

   ``style`` (optional): add inline style to HTML DOM

   ``data`` (for custom widget): data for custom widget using iframe

      ``type``: type of custom widget which is not using IFrame. There are two type ``taskItemDetailCustomPanelTop`` and ``taskItemDetailCustomPanelBottom``

      ``url``: URL for external website

      ``processPath``: the user-friendly request path of the Ivy process which will be displayed in the custom widget

      ``params``: parameters for the Ivy process above, each parameter can be defined as follows:

         - Key name that will be parameter name for the Ivy process above. Note: don't use ``taskId``.

         - Key value for task has to start with ``task.``. Supported are two values: ``task.id``, ``task.category``.

         - Key value for task custom fields have to start with ``task.customFields.``, follow by the custom field name.

         - Other key values will be treated as hard coded values.

.. important::
   -  **Do not change** ``type`` of widgets.
      You can change ``x``, ``y``, ``w`` and ``h`` to update size and position of widgets.
   -  ``x``, ``y``, ``w`` and ``h`` must be **integers**.
   -  ``x + w`` must **not be larger** than **12**.
   -  For data of custom widget, if you input ``processPath``, don't input ``url``. You can only use one of them.
   -  We support all task business states for filter type ``states``. Please refer to :dev-url:`Task Business States </doc/|version|/public-api/ch/ivyteam/ivy/workflow/TaskBusinessState.html>` to check for available task business states.


.. _customization-task-item-details-how-to-overide-ui-show-hidden-ui:

Show or hide elements
---------------------

Refer to list of ``ui:param`` tag in ``PortalTaskDetails.xhtml`` of
``portal``. If you want to show or hide elements on
TaskItemDetails, you must override ``ui:param``

List of valid ``ui:param``:

-  ``ui:param name="showItemDetailsHeader" value="true"``

   To show or hide Task Header, use ``showItemDetailsHeader``. Default value is true.


.. |task-standard| image:: ../../screenshots/task-detail/customization/task-standard.png
.. |edit-variable-portal-task-case-details| image:: images/customization/edit-variable-portal-task-case-details.png