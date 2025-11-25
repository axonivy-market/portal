.. _configure-new-dashboard-navigation-dashboard-widget:

Configure Navigation Dashboard Widget
=====================================

.. _portal-navigation-dashboard-widget:

Define Navigation Dashboard Widget
----------------------------------

The Navigation Dashboard widget of the Portal dashboard allows users to navigate to a target dashboard quickly with a single click.

Below is a sample JSON definition of a navigation dashboard widget in the Portal dashboard.

.. code-block:: javascript

    {
    "type": "navigation-dashboard",
    "id": "navigation_dashboard_e261b17a19e844939b130bdad87bb073",
    "names": [
        {
        "locale": "en",
        "value": "Navigate to Dashboard"
        },
        {
        "locale": "fr",
        "value": "Naviguer vers le tableau de bord"
        },
        {
        "locale": "de",
        "value": "Zum Dashboard navigieren"
        },
        {
        "locale": "es",
        "value": "Navegar al dashboard"
        }
    ],
    "buttonNames": [
        {
        "locale": "en",
        "value": "Navegue por"
        },
        {
        "locale": "fr",
        "value": "Naviguer"
        },
        {
        "locale": "de",
        "value": "navigieren."
        },
        {
        "locale": "es",
        "value": "Navegue por"
        }
    ],
    "layout": {
        "w": 3,
        "h": 3,
        "x": 0,
        "y": 0
    },
    "targetDashboardId": "default-task-list-dashboard",
    "description": "Dive into the task list",
    "visualType" : "IMAGE",
    "icon": "fa-play",
    "imageContentDarkMode": "<your-image-data-as-base64>",
    "imageLocationDarkMode": "/com/axonivy/portal/NavigationWidget/dd91ec84-c5aa-4202-aeea-4500fbd414ef",
    "imageTypeDarkMode": "png",
    "imageContent": "<your-image-data-as-base64>",
    "imageLocation": "/com/axonivy/portal/NavigationWidget/dd91ec84-c5aa-4202-aeea-4500fbd404ef",
    "imageType": "jpeg",
    }
..

The basic JSON structure of a statistic widget

   ``id``: ID of the widget

   ``type``: type of the widget. Use ``navigation-dashboard`` for a navigation dashboard widget
   
   ``names``: multilingual name of the widget on the UI

   ``buttonNames``: multilingual button name on the widget

   ``layout``: layout definition of the client statistic widget

      - ``x``: HTML DOM Style ``left`` is calculated as formula ``x / 12 * 100%``

      - ``y``: HTML DOM Style ``top`` is calculated as formula ``y / 12 * 100%``

      - ``w``: HTML DOM Style ``width`` is calculated as formula ``60 * w + 20 * (w - 1)``

      - ``h``: HTML DOM Style ``height`` is calculated as formula ``60 * h + 20 * (h - 1)``
   
   ``targetDashboardId``: the target dashboard id

   ``description``: the widget description

   ``visualType``: the visual type of the widget. Possible values are ``ICON`` and ``IMAGE``

   ``icon``: the icon shown on the widget

   ``imageContent``: the base64 data format of the process image. If this value is valid then it will be converted to a physical file. If you want to configure external link images when deploying, you just need to define ``imageContent``
   
   ``imageLocation``: the location of the process image. This image is stored in Application CMS. Basically, this should be handled by the engine
   
   ``imageType``: the extension of the process image. Basically, this should be handled by the engine

   ``imageContentDarkMode``: the same as ``imageContent`` but for dark mode

   ``imageLocationDarkMode``: the same as ``imageLocation`` but for dark mode

   ``imageTypeDarkMode``: the same as ``imageType`` but for dark mode
