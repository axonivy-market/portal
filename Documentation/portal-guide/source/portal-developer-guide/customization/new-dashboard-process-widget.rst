.. _customization-new-dashboard-proces-widget:

Customize Process widget
========================

Define Process widget
---------------------

Process widget of Portal dashboard is a flexible Process list with three modes.
Refer to (link to process widget of the dashboard) for more details.

Basic JSON structure of process widget:

   .. code-block:: html

      {
         "type": "process",
         "id": "process-widget",
         "name": "Process Widget",
         "displayMode": "FULL_MODE",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         }
      }
   ..

Besides basic attributes of Dashboard's widget, the process widget has required attribute `displayMode`.
This attribute is used to define the display mode of the widget.

There are three display modes for the process widget: ``FULL_MODE`` (full mode), ``COMPACT_MODE``
(compact mode), and ``COMBINED_MODE`` (combined mode).

Each mode has differences in its JSON definition. Refer to the below sections to understand
how to define the process widget in these modes properly.

Compact mode
------------

Below is a standard JSON definition of Process widget compact mode

   .. code-block:: html

      {
         "type": "process",
         "id": "compact_mode",
         "name": "Process Widget",
         "displayMode": "COMPACT_MODE",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "categories": ["estimation","analysis"]
      }
   ..

``categories``: categories of processes that you want to show.
Process widget will show all processes that belonged to these categories.
If you don't define this attribute, the process widget will show all
available processes by default.

Full mode
---------

Below is a standard JSON definition of Process widget full mode

   .. code-block:: html

      {
         "type": "process",
         "id": "full_mode",
         "name": "Process Widget",
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
         "name": "Process Widget",
         "displayMode": "COMBINED_MODE",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "processPath": "Start Processes/Request/createNewRequest.ivp"
      }
   ..

``processPath``: friendly user request path of the process you want to display in combined mode.
Process widget will show all tasks and cases of this process also.