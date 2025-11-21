.. _configure-new-dashboard-template:

Configure Dashboard Template
=============================

Dashboard templates provide pre-configured dashboard layouts that users can select when creating new dashboards. Templates define the structure, widgets, and initial configuration for common dashboard use cases.

Define Dashboard Template
--------------------------

Templates are defined in JSON format and displayed in the dashboard creation wizard's "Select your template" dialog.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a sample JSON definition of a Portal dashboard template:

.. code-block:: javascript

   [
      {
         "id": "template-id",
         "titles": [
            {
               "locale": "en",
               "value": "Task Management Template"
            },
            {
               "locale": "de",
               "value": "Aufgabenverwaltung Vorlage"
            }
         ],
         "descriptions": [
            {
               "locale": "en",
               "value": "Create dashboard with task list and case overview"
            },
            {
               "locale": "de",
               "value": "Dashboard mit Aufgabenliste und Fallübersicht"
            }
         ],
         "icon": "si-cog-double-2",
         "dashboard": {
            "id": "dashboard_id",
            "templateId": "template-id",
            "titles": [
               {
                  "locale": "en",
                  "value": "My Tasks Dashboard"
               }
            ],
            "permissions": ["Everybody"],
            "widgets": [
               {
                  "type": "task",
                  "id": "task-widget",
                  "names": [
                     {
                        "locale": "en",
                        "value": "Tasks"
                     }
                  ],
                  "layout": {
                     "x": 0,
                     "y": 0,
                     "w": 12,
                     "h": 8
                  },
                  "columns": [
                     {"field": "start"},
                     {"field": "priority"},
                     {"field": "name"}
                  ]
               }
            ]
         }
      }
   ]

..

JSON Configuration Reference
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

**Template Properties**

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``id``
     - string
     - **Required.** Unique identifier for the template. Must be unique across all templates
   * - ``titles``
     - array
     - **Required.** Multilingual titles displayed in template selection. Format: ``[{"locale": "en", "value": "Title"}]``
   * - ``descriptions``
     - array
     - **Required.** Multilingual descriptions shown in template selection. Format: ``[{"locale": "en", "value": "Description"}]``
   * - ``icon``
     - string
     - **Required.** Streamline icon class for template display (e.g., ``"si-cog-double-2"``)
   * - ``dashboard``
     - object
     - **Required.** Complete dashboard configuration (see Dashboard Configuration below)

**Dashboard Configuration**

The ``dashboard`` object contains the full dashboard definition that will be created when the template is selected:

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``id``
     - string
     - Dashboard identifier
   * - ``templateId``
     - string
     - Reference to the template ID
   * - ``titles``
     - array
     - Multilingual dashboard titles: ``[{"locale": "en", "value": "Title"}]``
   * - ``permissions``
     - array
     - List of roles/users with access (e.g., ``["Everybody"]``, ``["#admin"]``)
   * - ``widgets``
     - array
     - Array of widget configurations (task, case, process, custom, etc.)
   * - Additional properties
     - various
     - See :ref:`Dashboard Configuration <customization-new-dashboard>` for full details

.. tip::
   **Icon Selection:** Portal supports Streamline icons. Find available icons in the `HTML Dialog Demo <https://market.axonivy.com/html-dialog-demo>`_.

.. note::
   For detailed widget configuration options, refer to the individual widget documentation pages (task widget, case widget, process widget, etc.).