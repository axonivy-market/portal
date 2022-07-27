.. _configure-new-dashboard-process-viewer-widget:

Configure Process Viewer Widget
===============================

Define Process Viewer Widget
----------------------------

The Process Viewer Widget of the Portal dashboard provides a visual representation of the process flow.

Below is a sample JSON definition of a process viewer widget in the Portal dashboard

.. code-block:: html

   {
      "type": "process-viewer", 
      "id": "process_viewer_1", 
      "name": "Your Process Viewer", 
      "layout": {
         "x": 0, "y": 0, "w": 12, "h": 6
      }, 
      "processStart": "Start Processes/Request/createNewRequest.ivp"
   }

The basic JSON structure of a process viewer widget

   ``type``: type of widget. Use ``process-viewer`` for a process viewer widget

   ``id``: ID of the widget

   ``name``: Name of the widget on UI

   ``layout``: layout definition of process viewer widget

      ``x``: HTML DOM Style ``left`` will be calculated by formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` will be calculated by formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` will be calculated by formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` will be calculated by formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of the widget

      ``style`` (optional): add inline style to HTML DOM of the widget

   ``processPath``: user friendly request path of the process you want to display
