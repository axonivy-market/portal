.. _configure-new-dashboard-notification-widget:

Configure Notifications Widget
==============================

Define Notifications Widget
---------------------------

The Notifications widget of the Portal dashboard displays all notifications based on your notification settings. Refer
to :ref:`Notifications widget <add-new-notification-widget>` for details.

Below is a sample JSON definition of a notification widget in the Portal dashboard

.. code-block:: javascript

   {
      "type": "notification",
      "id": "notification_848ba48391014fcb801a14fb841a879a",
      "names": [
         {
            "locale": "en",
            "value": "My Notifications"
         },
         {
            "locale": "fr",
            "value": "Mes notifications"
         },
         {
            "locale": "de",
            "value": "Meine Benachrichtigungen"
         },
         {
            "locale": "es",
            "value": "Mis notificaciones"
         }
      ],
      "layout": {
         "w": 4,
         "h": 6,
         "x": 0,
         "y": 12,
         "style": "text-color: blue;",
         "styleClass": "your-widget-class"
      },
      "onlyUnread": true,
      "showFullscreenMode": true
   }
..

The basic JSON structure of a Notifications widget

   ``type``: type of the widget. Use ``notification`` for a notification widget

   ``id``: ID of the widget

   ``names``: multilingual name of the widget on the UI

   ``layout``: layout definition of the widget

      ``x``: HTML DOM Style ``left`` is calculated as formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` is calculated as formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` is calculated as formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` is calculated as formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of the widget

      ``style`` (optional): add inline style to HTML DOM of the widget

   ``onlyUnread``: filter only unread notifications. The default value is "false"

   ``showFullscreenMode``: visibility of the fullscreen mode icon. The default value is ``true``, set to ``false`` to hide the icon
