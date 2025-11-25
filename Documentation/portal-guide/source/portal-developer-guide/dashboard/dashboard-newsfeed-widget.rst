.. _configure-dashboard-newsfeed-widget:

Configure News Widget
=====================

Display announcements, news items, and important information to users through the News widget.

Overview
--------

The News widget provides a news feed for sharing relevant information, announcements, and updates with Portal users. It supports:

- **Rich Content** - Display text, images, and formatted content
- **User Management** - Requires :bdg-ref-warning:`🔑NewsManagement <NewsManagement>` permission to manage news items
- **Chronological Display** - News items shown in reverse chronological order
- **Fullscreen View** - Expandable widget for easier reading
- **Responsive Layout** - Adapts to different widget sizes

Define News Widget
------------------

**Configuration Example:**

Below is a complete JSON definition of a news widget in the Portal dashboard:

.. code-block:: javascript

   {
      "type": "news",
      "id": "news_18459642ba614d79bddea3f57e800bcf",
      "names": [
         {
            "locale": "en",
            "value": "Company News"
         },
         {
            "locale": "de",
            "value": "Unternehmensnachrichten"
         },
         {
            "locale": "fr",
            "value": "Actualités de l'entreprise"
         }
      ],
      "layout": {
         "x": 8,
         "y": 0,
         "w": 4,
         "h": 6,
         "styleClass": "news-widget-custom",
         "style": "border-radius: 8px;"
      },
      "showFullscreenMode": true
   }

..

JSON Configuration Reference
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

**Required Properties:**

    ``type`` (string)
        Widget type identifier
        
        - Must be set to ``"news"`` for news widget

    ``id`` (string)
        Unique widget identifier
        
        - Must be unique across all dashboards
        - Recommended format: ``news_{uuid}``
        - Example: ``"news_18459642ba614d79bddea3f57e800bcf"``

    ``names`` (array)
        Multilingual widget title displayed in the header
        
        - Each entry contains ``locale`` and ``value``
        - Portal displays name matching user's language
        - Example: ``[{"locale": "en", "value": "Company News"}]``

    ``layout`` (object)
        Widget position and size configuration
        
        - See layout properties below

**Layout Properties:**

    ``x`` (number, required)
        Horizontal position in grid (0-11)
        
        - Dashboard uses 12-column grid system
        - Calculated as: ``left = x / 12 * 100%``

    ``y`` (number, required)
        Vertical position in grid
        
        - Starting row position for the widget
        - Widgets stack vertically based on ``y`` value
        - Example: ``y: 0`` positions widget at top

    ``w`` (number, required)
        Widget width in grid units (1-12)
        
        - Calculated as: ``width = 60 * w + 20 * (w - 1)`` pixels
        - Example: ``w: 4`` creates widget ~260px wide
        - Recommended: 4-6 for news widgets

    ``h`` (number, required)
        Widget height in grid units
        
        - Calculated as: ``height = 60 * h + 20 * (h - 1)`` pixels
        - Example: ``h: 6`` creates widget ~380px tall
        - Recommended: 5-8 for adequate news display

    ``styleClass`` (string, optional)
        Custom CSS classes applied to widget container
        
        - Add multiple classes separated by spaces
        - Example: ``"news-widget-custom highlight-border"``

    ``style`` (string, optional)
        Inline CSS styles applied to widget container
        
        - Use standard CSS syntax
        - Example: ``"border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);"``

**Optional Properties:**

    ``showFullscreenMode`` (boolean)
        Display fullscreen mode icon in widget header
        
        - Default: ``true``
        - Set to ``false`` to hide fullscreen button
        - Fullscreen mode allows easier reading of long news items

.. tip::
   **Layout Best Practices for News Widget:**
   
   - **Width**: Use ``w: 4`` or ``w: 6`` for optimal readability
   - **Height**: Minimum ``h: 5`` to show at least 2-3 news items
   - **Position**: Place in prominent location (top-right or top-left)
   - **Fullscreen**: Keep ``showFullscreenMode: true`` for user convenience

.. note::
   **Managing News Content:**
   
   To add, edit, or delete news items:
   
   #. User must have :bdg-ref-warning:`🔑NewsManagement <NewsManagement>` permission
   #. Access news management through the widget's action menu
   #. News items support rich text formatting, images, and links
   #. Items are displayed in reverse chronological order (newest first)
   
   See the Portal User Guide dashboard documentation for news management details.

.. important::
   **Widget Visibility:**
   
   The News widget itself is controlled by dashboard permissions. However, news content management requires the separate :bdg-ref-warning:`🔑NewsManagement <NewsManagement>` permission.