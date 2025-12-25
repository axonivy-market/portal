.. _configure-new-dashboard-navigation-dashboard-widget:

Configure Navigation Dashboard Widget
=====================================

The navigation dashboard widget provides quick access to other dashboards through clickable cards. Users can navigate to target dashboards with a single click, using either icon-based or image-based visual styles.

.. _portal-navigation-dashboard-widget:

Define Navigation Dashboard Widget
----------------------------------

Navigation widgets display as cards with customizable visuals (icons or images) and multilingual text, enabling quick dashboard switching.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a sample JSON definition of a navigation dashboard widget in the Portal dashboard:

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

..

JSON Configuration Reference
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

**Required Properties**

``type`` (string)
   Widget type. Must be ``"navigation-dashboard"`` for navigation dashboard widget

``id`` (string)
   Unique identifier for the widget

``names`` (array)
   Multilingual widget titles. Format: ``[{"locale": "en", "value": "Title"}]``

``buttonNames`` (array)
   Multilingual button labels. Format: ``[{"locale": "en", "value": "Button Text"}]``

``layout`` (object)
   Widget position and size (see Layout Properties below)

``targetDashboardId`` (string)
   ID of the dashboard to navigate to when clicked

**Layout Properties**

``x`` (number)
   Column position in 12-column grid (0-11). CSS left = ``x / 12 * 100%``

``y`` (number)
   Row position. CSS top = ``y / 12 * 100%``

``w`` (number)
   Width in grid columns (1-12). Pixel width = ``60 * w + 20 * (w - 1)``

``h`` (number)
   Height in grid rows (min 2). Pixel height = ``60 * h + 20 * (h - 1)``

.. tip::
   **Recommended navigation widget size:** Width 3-4 columns, Height 3-4 rows for balanced card display.

**Display Properties**

``description`` (string, optional)
   Descriptive text shown on the navigation card

``visualType`` (string, default: ``"ICON"``)
   Visual style: ``"ICON"`` (icon display) or ``"IMAGE"`` (custom image)

``icon`` (string, optional)
   Streamline icon class when ``visualType`` is ``"ICON"`` (e.g., ``"si-task-list-1"``)

**Image Properties**

Used when ``visualType`` is ``"IMAGE"``:

``imageContent`` (string, optional)
   Base64-encoded image data for light mode. Automatically converted to physical file

``imageLocation`` (string, managed by Portal)
   CMS path to stored image in light mode

``imageType`` (string, managed by Portal)
   Image file extension (e.g., ``"jpeg"``, ``"png"``)

``imageContentDarkMode`` (string, optional)
   Base64-encoded image data for dark mode

``imageLocationDarkMode`` (string, managed by Portal)
   CMS path to stored image in dark mode

``imageTypeDarkMode`` (string, managed by Portal)
   Dark mode image file extension

.. note::
   When using ``visualType: "IMAGE"``, provide ``imageContent`` (base64 data). Portal automatically handles storage in Application CMS and manages ``imageLocation`` and ``imageType`` properties.

.. tip::
   For icon-based navigation, browse available Streamline icons in the `HTML Dialog Demo <https://market.axonivy.com/html-dialog-demo>`_.
