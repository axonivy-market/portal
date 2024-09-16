.. _configure-new-dashboard-external-page-widget:

Configure External Page Widget
==============================

.. _define-an-external-page-widget:

Define An External Page Widget
------------------------------

You can use this widget to embed an external webpage into the Portal dashboard.
This feature is extremely useful for a company using other applications besides |ivy|.
Now users can interact with multiple systems from the Portal dashboard.

   |external-page-widget-configuration|

   ``Widget title``: Title of the widget. Leave it blank to hide header of the widget

   ``External URL``: External page link

   ``showFullscreenMode``: visibility of the fullscreen mode icon. The default value is ``true``, set to ``false`` to hide the icon

Define An External Page Widget Using JSON
-----------------------------------------

The JSON structure is the following:

   .. code-block:: html

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

   ``url``: the URL of the external webpage you want to show.

   ``showFullscreenMode``: visibility of the fullscreen mode icon. The default value is ``true``, set to ``false`` to hide the icon

.. warning::
   Some external websites do not allow to be loaded in IFrames. You have to make sure 
   that their security policy allows the embedding if you want to use these pages in your custom widget.

.. |external-page-widget-configuration| image:: ../../screenshots/dashboard/external-page-widget-configuration.png