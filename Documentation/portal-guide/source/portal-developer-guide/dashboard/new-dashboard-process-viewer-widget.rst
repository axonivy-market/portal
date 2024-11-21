.. _configure-new-dashboard-process-viewer-widget:

Configure Process Viewer Widget
===============================

Define Process Viewer Widget
----------------------------

The Process Viewer Widget of the Portal dashboard provides a visual representation of the process flow.

Below is a sample JSON definition of a process viewer widget in the Portal dashboard

.. code-block:: javascript

   {
      "type": "process-viewer",
      "id": "process_viewer_1",
      "names": [
         {
            "locale": "en",
            "value": "Your Process Viewer"
         }
      ],
      "layout": {
         "x": 0,
         "y": 0,
         "w": 12,
         "h": 6
      },
      "processPath": "designer/portal-developer-examples/Start Processes/CreateTestData/createNewPayment.ivp",
      "showFullscreenMode": true
   }

The basic JSON structure of a process viewer widget

   ``type``: type the of widget. Use ``process-viewer`` for a process viewer widget

   ``id``: ID of the widget

   ``names``: multilingual name of the widget on UI.

   ``layout``: layout definition of the process viewer widget

      ``x``: HTML DOM Style ``left`` is calculated as formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` is calculated as formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` is calculated as formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` is calculated as formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of the widget

      ``style`` (optional): add inline style to HTML DOM of the widget

   ``processPath``: the :dev-url:`|ivy| IWebStartable </doc/|version|/public-api/ch/ivyteam/ivy/workflow/start/IWebStartable.html>` identifier of the process you want to display

   ``showFullscreenMode``: visibility of the fullscreen mode icon. The default value is ``true``, set to ``false`` to hide the icon
