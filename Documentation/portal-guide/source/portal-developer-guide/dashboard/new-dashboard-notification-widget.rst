.. _configure-new-dashboard-notification-widget:

Configure Notifications Widget
==============================

The notifications widget displays user notifications based on notification settings. It can show all notifications or filter to display only unread items, providing quick access to important alerts and messages directly from the dashboard.

Define Notifications Widget
---------------------------

The notifications widget displays notifications with configurable filtering options. Refer to :ref:`Notifications widget <add-new-notification-widget>` for widget behavior details.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a sample JSON definition of a notification widget in the Portal dashboard:

.. code-block:: javascript

   {
      "type": "notification",
      "id": "notification-widget",
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
         "x": 0,
         "y": 0,
         "w": 4,
         "h": 6,
         "style": "text-color: blue;",
         "styleClass": "your-widget-class"
      },
      "onlyUnread": true,
      "showFullscreenMode": true
   }
..

JSON Configuration Reference
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

**Required Properties**

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``type``
     - string
     - Widget type. Must be ``"notification"`` for notifications widget
   * - ``id``
     - string
     - Unique identifier for the widget
   * - ``names``
     - array
     - Multilingual display names. Each entry: ``{"locale": "en", "value": "Name"}``
   * - ``layout``
     - object
     - Widget position and size (see Layout Properties below)

**Layout Properties**

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``x``
     - number
     - Column position in 12-column grid (0-11). CSS left = ``x / 12 * 100%``
   * - ``y``
     - number
     - Row position. CSS top = ``y / 12 * 100%``
   * - ``w``
     - number
     - Width in grid columns (1-12). Pixel width = ``60 * w + 20 * (w - 1)``
   * - ``h``
     - number
     - Height in grid rows (min 4). Pixel height = ``60 * h + 20 * (h - 1)``
   * - ``styleClass``
     - string
     - *(Optional)* CSS classes for custom styling
   * - ``style``
     - string
     - *(Optional)* Inline CSS styles

.. tip::
   **Recommended notifications widget size:** Width 3-5 columns, Height 5-8 rows for optimal notification list display.

**Filtering & Display Properties**

.. list-table::
   :widths: 20 15 15 50
   :header-rows: 1

   * - Property
     - Type
     - Default
     - Description
   * - ``onlyUnread``
     - boolean
     - ``false``
     - Filter notifications. ``true`` = show only unread, ``false`` = show all
   * - ``showFullscreenMode``
     - boolean
     - ``true``
     - Show/hide fullscreen mode icon

.. note::
   Notifications displayed depend on the user's notification settings. Users can manage notification preferences in their user profile.
