.. _configure-dashboard-newsfeed-widget:

Configure News Widget
=====================

Define News widget
------------------

The news widget shares relevant information as a News Feed into |ivy|.

Below is a sample JSON definition of a news widget in the Portal dashboard

.. code-block:: javascript

   {
      "type": "news",
      "id": "news_id",
      "names": [
         {
            "locale": "en",
            "value": "News feed"
         }
      ],
      "layout": {
         "w": 4,
         "h": 6,
         "x": 0,
         "y": 0
      },
      "showFullscreenMode": true
   }
..

The basic JSON structure of a news widget

   ``type``: type of the widget. Use ``news`` for the news widget

   ``id``: ID of the widget

   ``names``: multilingual name of the widget on the UI.

   ``layout``: layout definition of the news widget

      ``x``: HTML DOM Style ``left`` is calculated as formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` is calculated as formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` is calculated as formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` is calculated as formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of the widget

      ``style`` (optional): add inline style to HTML DOM of the widget

   ``showFullscreenMode``: visibility of the fullscreen mode icon. The default value is ``true``, set to ``false`` to hide the icon