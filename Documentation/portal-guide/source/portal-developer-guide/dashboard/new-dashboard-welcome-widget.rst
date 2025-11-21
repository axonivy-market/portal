.. _configure-new-dashboard-welcome-widget:

Configure Welcome Widget
=========================

The welcome widget displays a customizable greeting message with a background image, supporting personalized text, positioning, styling, and dark mode. It's ideal for creating branded dashboard landing pages that greet users with time-based messages or custom welcome text.

Define Welcome Widget
----------------------

Welcome widgets can be configured with multilingual text, custom images, and flexible layout options.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a sample JSON definition of a welcome widget in the Portal dashboard:

.. code-block:: javascript

   {
      "type": "welcome",
      "id": "welcome_1",
      "layout": {
         "x": 0,
         "y": 0,
         "w": 12,
         "h": 3
      },
      "welcomeTexts": [
         {
            "locale": "en",
            "value": "Welcome to Your Dashboard"
         },
         {
            "locale": "de",
            "value": "Willkommen zu Ihrem Dashboard"
         },
         {
            "locale": "fr",
            "value": "Bienvenue sur votre tableau de bord"
         }
      ],
      "imageLocation": "welcome_1_en.jpg",
      "imageType": "jpg",
      "imageLocationDarkMode": "welcome_1_en_darkmode.png",
      "imageTypeDarkMode": "png",
      "welcomeTextPosition": "BOTTOM_LEFT",
      "welcomeTextSize": "HEADING_2",
      "welcomeTextColor": "#FFFFFF",
      "welcomeTextColorDarkMode": "#FFFFFF",
      "greeting": true,
      "welcomeTextStyleClass": "custom-text",
      "imageStyleClass": "custom-image",
      "welcomeImageFit": "COVER",
      "imageInlineStyle": "background-color: #f5f5f5;"
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
     - Widget type. Must be ``"welcome"`` for welcome widget
   * - ``id``
     - string
     - Unique identifier for the widget
   * - ``layout``
     - object
     - Widget position and size (see Layout Properties below)
   * - ``welcomeTexts``
     - array
     - Multilingual welcome messages. Format: ``[{"locale": "en", "value": "Text"}]``

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
     - Height in grid rows (min 2). Pixel height = ``60 * h + 20 * (h - 1)``
   * - ``styleClass``
     - string
     - *(Optional)* CSS classes for custom styling
   * - ``style``
     - string
     - *(Optional)* Inline CSS styles

.. tip::
   **Recommended welcome widget size:** Width 12 columns (full width), Height 3-5 rows for prominent display.

**Text Configuration Properties**

.. list-table::
   :widths: 25 15 15 45
   :header-rows: 1

   * - Property
     - Type
     - Default
     - Description
   * - ``welcomeTextPosition``
     - string
     - ``BOTTOM_LEFT``
     - Text position: ``BOTTOM_LEFT``, ``BOTTOM_RIGHT``, ``TOP_LEFT``, ``TOP_RIGHT``, ``CENTER``
   * - ``welcomeTextSize``
     - string
     - ``NORMAL_TEXT``
     - Font size: ``NORMAL_TEXT`` (1.5rem), ``HEADING_3`` (2.5rem), ``HEADING_2`` (3.5rem), ``HEADING_1`` (5rem)
   * - ``welcomeTextColor``
     - string
     - *(theme default)*
     - Text color in light mode (CSS color value, e.g., ``#FFFFFF``)
   * - ``welcomeTextColorDarkMode``
     - string
     - *(theme default)*
     - Text color in dark mode (CSS color value)
   * - ``greeting``
     - boolean
     - ``false``
     - ``true`` = show time-based greeting + custom text, ``false`` = show custom text only
   * - ``welcomeTextStyleClass``
     - string
     - *(none)*
     - CSS class for text styling

**Image Configuration Properties**

.. list-table::
   :widths: 25 15 15 45
   :header-rows: 1

   * - Property
     - Type
     - Default
     - Description
   * - ``welcomeImageFit``
     - string
     - ``COVER``
     - Image resize mode: ``NONE``, ``FILL``, ``COVER``, ``CONTAIN``
   * - ``imageStyleClass``
     - string
     - *(none)*
     - CSS class for image container
   * - ``imageInlineStyle``
     - string
     - *(none)*
     - Inline CSS for image container
   * - ``imageLocation``
     - string
     - *(managed)*
     - Light mode image filename (managed by Portal)
   * - ``imageType``
     - string
     - *(managed)*
     - Light mode image type (managed by Portal)
   * - ``imageLocationDarkMode``
     - string
     - *(managed)*
     - Dark mode image filename (managed by Portal)
   * - ``imageTypeDarkMode``
     - string
     - *(managed)*
     - Dark mode image type (managed by Portal)

**Image Fit Options**

.. list-table::
   :widths: 25 75
   :header-rows: 1

   * - Value
     - Behavior
   * - ``NONE``
     - Image shown at original size, centered. No resizing
   * - ``FILL``
     - Image stretched to fill container. May distort aspect ratio
   * - ``COVER``
     - Image resized to fill container, maintaining aspect ratio. May crop edges
   * - ``CONTAIN``
     - Image resized to fit within container, maintaining aspect ratio. May show empty space

**Greeting Feature**

When ``greeting`` is set to ``true``, Portal adds a time-based greeting before your custom welcome text:

- **Morning** (6 AM - 12 PM): "Good morning"
- **Afternoon** (12 PM - 6 PM): "Good afternoon"
- **Evening** (6 PM - 12 AM): "Good evening"
- **Night** (12 AM - 6 AM): "Good night"

Result format: ``"Good morning, Welcome to Your Dashboard"``

.. note::
   Background images can be uploaded directly through the Portal widget configuration UI. The image properties (``imageLocation``, ``imageType``, etc.) are managed automatically by Portal.

.. warning::
   Do not manually modify these internally-managed properties: ``imageLocation``, ``imageType``, ``imageContent``, ``imageLocationDarkMode``, ``imageTypeDarkMode``, ``imageContentDarkMode``. Changes may cause widget errors.

