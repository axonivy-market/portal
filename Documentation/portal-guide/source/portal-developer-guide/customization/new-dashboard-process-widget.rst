.. _customization-new-dashboard-proces-widget:

Customize Process widget
========================

Define Process widget
---------------------

Process widget of Portal dashboard is an flexible Process list with three modes.
Please refer to (link to process widget of dashboard) for more details.

Basic JSON structure of Process widget:

   .. code-block:: html

      {
         "type": "process",
         "id": "process-widget",
         "displayMode": "FULL_MODE",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         }
      }
   ..

Beside basic attributes of Dashboard's widget, Process widget has required attribute `displayMode`.
This attribute is used to define display mode of widget.

There are three display mode for Process widget: ``FULL_MODE`` (full mode), ``COMPACT_MODE``
(compact mode), and ``COMBINED_MODE`` (combined mode).

Each mode have differences in their JSON definition. Please read below sections to understand
how to deine Process widget in these mode properly.

Compact mode
------------

Below is a standard JSON definition of Process widget compact mode

   .. code-block:: html

      {
         "type": "process",
         "id": "compact_mode",
         "displayMode": "COMPACT_MODE",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "categories": ["estimation","analysis"]
      }
   ..

``categories``: categories of processes which you want to show.
Process widget will show all processes belong to these categories.
If you dont define this attribute, Process widget will show all
avaiable processes by default.

Full mode
---------

Below is a standard JSON definition of Process widget full mode

   .. code-block:: html

      {
         "type": "process",
         "id": "full_mode",
         "displayMode": "FULL_MODE",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "processPath": "Start Processes/Request/createNewRequest.ivp"
      }
   ..

``processPath``: friendly user request path of the process you want to display in full mode.

Combined mode
-------------

Below is a standard JSON definition of Process widget combined mode

   .. code-block:: html

      {
         "type": "process",
         "id": "combined_mode",
         "displayMode": "COMBINED_MODE",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "processPath": "Start Processes/Request/createNewRequest.ivp"
      }
   ..

``processPath``: friendly user request path of the process you want to display in combined mode.
Process widget will show all tasks and cases of this process also.