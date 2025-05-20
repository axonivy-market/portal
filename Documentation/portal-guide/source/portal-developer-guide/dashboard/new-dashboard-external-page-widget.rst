.. _configure-new-dashboard-external-page-widget:

Configure External Page Widget
==============================

.. _define-an-external-page-widget:

Define An External Page Widget
------------------------------

You can use this widget to embed an external webpage into the Portal dashboard.
This feature is especially useful for a company using other applications besides |ivy|.
It allows user to interact with multiple systems directly from the Portal dashboard.

   |external-page-widget-configuration|

   ``Widget title``: Title of the widget. Leave it blank to hide header of the widget.

   ``External URL``: External page link.

   ``showFullscreenMode``: visibility of the fullscreen mode icon. The default
   value is ``true``, set to ``false`` to hide the icon.

Define An External Page Widget Using JSON
-----------------------------------------

The JSON structure is the following:

   .. code-block:: javascript

      {
         "type": "custom",
         "id": "custom-widget",
         "showFullscreenMode": true,
         "names": [
            {
               "locale": "en",
               "value": "Custom Widget"
            }
         ],
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "data": {
            "url" : "https://www.axonivy.com/"
         }
      }
   ..

Attribute explanation:

   ``type``: type of the widget. Use ``custom`` for an external page widget.

   ``id``: ID of the widget.

   ``names``: multi-language names of the widget on the UI

   ``layout``: layout definition of the statistic widget

      - ``x``: HTML DOM Style ``left`` is calculated as formula ``x / 12 * 100%``

      - ``y``: HTML DOM Style ``top`` is calculated as formula ``y / 12 * 100%``

      - ``w``: HTML DOM Style ``width`` is calculated as formula ``60 * w + 20 * (w - 1)``

      - ``h``: HTML DOM Style ``height`` is calculated as formula ``60 * h + 20 * (h - 1)``

   ``url``: the URL of the external webpage you want to show.

   ``showFullscreenMode``: visibility of the fullscreen mode icon. The default value is ``true``, set to ``false`` to hide the icon

.. warning::
   Some external websites do not allow to be loaded in IFrames. You have to make sure 
   that their security policy allows the embedding if you want to use these pages in your custom widget.

.. |external-page-widget-configuration| image:: ../../screenshots/dashboard/external-page-widget-configuration.png