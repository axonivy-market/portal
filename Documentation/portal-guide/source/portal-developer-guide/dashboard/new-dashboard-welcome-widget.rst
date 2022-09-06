.. _configure-new-dashboard-welcome-widget:

Configure Welcome Widget
========================

Define Welcome Widget
---------------------

The Welcome widget of the Portal dashboard displays a welcome text and an image as its background.

Below is a sample JSON definition of a welcome widget in the Portal dashboard

.. code-block:: html

   {
      "type": "welcome", 
      "id": "welcome_1", 
      "layout": {
         "x": 0, "y": 0, "w": 12, "h": 3
      },
      "welcomeTexts" : [{
            "locale" : "en",
            "value" : "Welcome"
         }, {
            "locale" : "fr",
            "value" : "Bienvenue"
         }, {
            "locale" : "de",
            "value" : "Willkommen"
         }, {
            "locale" : "es",
            "value" : "Bienvenido"
         }
      ],
      "welcomeTextPosition" : "BOTTOM_LEFT",
      "welcomeTextSize" : "NORMAL_TEXT",
      "welcomeTextColor" : "000000",
      "greeting" : false,
      "welcomeTextStyleClass" : "custom-text",
      "imageStyleClass" : "custom-image"
   }

The basic JSON structure of a welcome widget

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

         ``TOP_RIGHT``: Top right corner of ther widget

         ``CENTER``: Center of the widget

   ``welcomeTextSize``: size for the welcome text. There are four sizes:

      ``NORMAL_TEXT``: Normal text font size (1.5rem)

      ``HEADING_3``: Normal header font size (2.5rem)

      ``HEADING_2``: Bigger header font size (3.5rem)

      ``HEADING_1``: Big header font size (5rem)

   ``welcomeTextColor``: color for the welcome text. You can input any value that accepted by the CSS attribute ``font-color``.

   ``greeting``: Defines if a greeting text is added to the welcome text:

      ``false``: The widget displays just the text contained in ``welcomeTexts`` for the language of the user.

      ``true``: The widget displays the standard Portal greeting text, followed by text contained in ``welcomeTexts``, both for the language of the user. The two texts are separated by comma.

   ``welcomeTextStyleClass``: style class for the welcome text.

   ``imageStyleClass``: style class for the image.

This widget also support user to upload an image as background.
This feature only available when user configure widget directly on Portal.
