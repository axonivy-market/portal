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
- **Night** (12 AM - 6 AM): "Good night"

Result format: ``"Good morning, Welcome to Your Dashboard"``

.. note::
   Background images can be uploaded directly through the Portal widget configuration UI. The image properties (``imageLocation``, ``imageType``, etc.) are managed automatically by Portal.

.. warning::
   Do not manually modify these internally-managed properties: ``imageLocation``, ``imageType``, ``imageContent``, ``imageLocationDarkMode``, ``imageTypeDarkMode``, ``imageContentDarkMode``. Changes may cause widget errors.

   ``type``: type of the widget. Use ``welcome`` for a welcome widget

   ``id``: ID of the widget

   ``layout``: layout definition of the welcome widget

      ``x``: HTML DOM Style ``left`` is calculated as ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` is calculated as ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` is calculated as ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` is calculated as ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of the widget

      ``style`` (optional): add inline style to HTML DOM of the widget

   ``welcomeTexts``: the welcome text on the widget. This allows multi-language by user’s locale.

   ``welcomeTextPosition``: position for the welcome text relative to the widget. There are five positions:

         ``BOTTOM_LEFT``: Bottom left corner of the widget

         ``BOTTOM_RIGHT``: Bottom right corner of the widget

         ``TOP_LEFT``: Top left corner of the widget

         ``TOP_RIGHT``: Top right corner of the widget

         ``CENTER``: Center of the widget

   ``welcomeTextSize``: size for the welcome text. There are four sizes:

      ``NORMAL_TEXT``: Normal text font size (1.5rem)

      ``HEADING_3``: Normal header font size (2.5rem)

      ``HEADING_2``: Bigger header font size (3.5rem)

      ``HEADING_1``: Big header font size (5rem)

   ``welcomeTextColor``: color for the welcome text. You can input any value that accepted by the CSS attribute ``font-color``.
   
   ``welcomeTextColorDarkMode``: color for the welcome text in dark mode. You can input any value that accepted by the CSS attribute ``font-color``.

   ``greeting``: Defines if a greeting text is added to the welcome text:

      ``false``: The widget displays just the text contained in ``welcomeTexts`` for the language of the user.

      ``true``: The widget displays the standard Portal greeting text, followed by text contained in ``welcomeTexts``, both for the language of the user. The two texts are separated by comma.

   ``welcomeTextStyleClass``: style class for the welcome text.

   ``imageStyleClass``: style class for the image.

   ``imageInlineStyle``: inline style for the image.

   ``welcomeImageFit``: the property is used to specify how an image should be resized to fit its container. There are four options:

        ``NONE``: The image is not resized. If it is bigger than the container, its center will be shown partially, if it is smaller than the container, it is shown centered in the container.

        ``FILL``: The image is resized to fill the given dimension. The image will be distorted if the aspect ratios of image and container  differ.

        ``COVER``: The image will keep its aspect ratio and be resized and cropped such that it fills the container completely.

        ``CONTAIN``: The image will keep its aspect ratio and will be resized such that it is shown completely within the container, showing empty side- or top/bottom bars.

You may upload a background image by configuring the widget directly in Portal.

.. warning::

   Portal is using these attributes internally: ``imageLocation``, ``imageType``, ``imageContent``, ``imageLocationDarkMode``, ``imageTypeDarkMode``, ``imageContentDarkMode``.

   Please don't change them manually because it could cause errors for the widget.