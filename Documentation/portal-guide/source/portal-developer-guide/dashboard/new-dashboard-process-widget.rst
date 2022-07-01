.. _configure-new-dashboard-proces-widget:

Configure Process Widget
========================

Define Process Widget
---------------------

The Process widget of the Portal dashboard is a flexible Process list with three modes.
Refer to (link to process widget of the dashboard) for details.

The basic structure of the JSON of a process widget is as follows:

   .. code-block:: html

      {
         "type": "compact-process",
         "id": "process-widget",
         "name": "Process Widget",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         }
      }
   ..

The basic structure of the JSON of a Process widget

``type``: type of process widget. There are four types for related display
modes: ``compact-process`` (compact mode), ``combined-process`` (combined mode),
``full-process`` (full mode), and ``image-process`` (image mode).

   ``id``: ID of the widget

   ``name``: Name of the widget on UI

   ``layout``: layout definition of widget

      ``x``: HTML DOM Style ``left`` will be calculated by formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` will be calculated by formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` will be calculated by formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` will be calculated by formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of widget

      ``style`` (optional): add inline style to HTML DOM of widget

Each mode has differences in its JSON definition. Refer to the below sections to
understand how to define the process widget in these modes properly.

Compact Mode
------------

Below is a standard JSON definition of aProcess widget in compact mode

   .. code-block:: html

      {
         "type": "compact-process",
         "id": "compact_mode",
         "name": "Process Widget",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "processPaths": ["Start Processes/Request/createNewRequest.ivp", "Start Processes/Request/collectDataRequest.ivp"],
         "categories": ["estimation","analysis"]
      }
   ..

``processPaths``: userfriendly request path of the processes that you want to
show.

``categories``: categories of processes that you want to show. Process widget
will show all processes that belonged to these categories.

If you define both ``processPaths`` and ``categories``, the process widget will
show processes by ``processPaths``.

If you don't define these attributes, the process widget will show all available
processes by default.

Combined Mode
-------------

Below is a standard JSON definition of a Process widget in combined mode

   .. code-block:: html

      {
         "type": "combined-process",
         "id": "combined_mode",
         "name": "Process Widget",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "processPath": "Start Processes/Request/createNewRequest.ivp",
         "rowsPerPage": 5
      }
   ..

``processPath``: user friendly request path of the process you want to display.
The Process widget will show all tasks and cases of this process, too.

``rowsPerPage``: the number of tasks/cases that are displayed on one page. 
If you don't define this attribute, the default value is 5 rows per page.

Full mode
---------

Below is a standard JSON definition of a Process widget in full mode

   .. code-block:: html

      {
         "type": "full-process",
         "id": "full_mode",
         "name": "Process Widget",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "processPath": "Start Processes/Request/createNewRequest.ivp"
      }
   ..

Image Mode
----------

Below is a standard JSON definition of the Process widget in image mode

   .. code-block:: html

      {
         "type": "image-process",
         "id": "image_mode",
         "name": "Process Widget",
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "processPath": "Start Processes/Request/createNewRequest.ivp"
      }
   ..