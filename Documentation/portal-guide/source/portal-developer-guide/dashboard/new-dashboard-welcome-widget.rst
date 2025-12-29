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

``type`` (string)
   Widget type. Must be ``"welcome"`` for welcome widget

``id`` (string)
   Unique identifier for the widget

``layout`` (object)
   Widget position and size (see Layout Properties below)

``welcomeTexts`` (array)
   Multilingual welcome messages. Format: ``[{"locale": "en", "value": "Welcome"}]``

**Layout Properties**

``x`` (number)
   Column position in 12-column grid (0-11). CSS left = ``x / 12 * 100%``

``y`` (number)
   Row position. CSS top = ``y / 12 * 100%``

``w`` (number)
   Width in grid columns (1-12). Pixel width = ``60 * w + 20 * (w - 1)``

``h`` (number)
   Height in grid rows (min 2). Pixel height = ``60 * h + 20 * (h - 1)``

``styleClass`` (string, optional)
   CSS classes for custom styling

``style`` (string, optional)
   Inline CSS styles

.. tip::
   **Recommended welcome widget size:** Width 12 columns (full width), Height 3-5 rows for prominent display.

**Text Configuration Properties**

``welcomeTextPosition`` (string, default: ``BOTTOM_LEFT``)
   Text position: ``BOTTOM_LEFT``, ``BOTTOM_RIGHT``, ``TOP_LEFT``, ``TOP_RIGHT``, ``CENTER``

``welcomeTextSize`` (string, default: ``NORMAL_TEXT``)
   Font size: ``NORMAL_TEXT`` (1.5rem), ``HEADING_3`` (2.5rem), ``HEADING_2`` (3.5rem), ``HEADING_1`` (5rem)

``welcomeTextColor`` (string, optional)
   Text color in light mode (CSS color value, e.g., ``#FFFFFF``)

``welcomeTextColorDarkMode`` (string, optional)
   Text color in dark mode (CSS color value)

``greeting`` (boolean, default: ``false``)
   ``true`` = show time-based greeting + custom text, ``false`` = show custom text only

``welcomeTextStyleClass`` (string, optional)
   CSS class for text styling

**Image Configuration Properties**

``welcomeImageFit`` (string, default: ``COVER``)
   Image resize mode: ``NONE``, ``FILL``, ``COVER``, ``CONTAIN``

``imageStyleClass`` (string, optional)
   CSS class for image container

``imageInlineStyle`` (string, optional)
   Inline CSS for image container

``imageLocation`` (string, managed by Portal)
   Light mode image filename

``imageType`` (string, managed by Portal)
   Light mode image type

``imageLocationDarkMode`` (string, managed by Portal)
   Dark mode image filename

``imageTypeDarkMode`` (string, managed by Portal)
   Dark mode image type

**Image Fit Options**

``NONE``
   Image shown at original size, centered. No resizing

``FILL``
   Image stretched to fill container. May distort aspect ratio

``COVER``
   Image resized to fill container, maintaining aspect ratio. May crop edges

``CONTAIN``
   Image resized to fit within container, maintaining aspect ratio. May show empty space

**Greeting Feature**

When ``greeting`` is set to ``true``, Portal adds a time-based greeting before your custom welcome text:

- **Morning** (6 AM - 12 PM): "Good morning"
- **Afternoon** (12 PM - 6 PM): "Good afternoon"
- **Evening** (6 PM - 12 AM): "Good evening"

Result format: ``"Good morning, Welcome to Your Dashboard"``

.. note::
   Background images can be uploaded directly through the Portal widget configuration UI. The image properties (``imageLocation``, ``imageType``, etc.) are managed automatically by Portal.

.. warning::
   Do not manually modify these internally-managed properties: ``imageLocation``, ``imageType``, ``imageContent``, ``imageLocationDarkMode``, ``imageTypeDarkMode``, ``imageContentDarkMode``. Changes may cause widget errors.
