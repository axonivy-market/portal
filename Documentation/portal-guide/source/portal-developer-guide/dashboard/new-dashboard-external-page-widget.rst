.. _configure-new-dashboard-external-page-widget:

Configure External Page Widget
==============================

The external page widget embeds external webpages into the Portal dashboard using iframes. This enables users to interact with multiple systems (e.g., monitoring tools, analytics dashboards, documentation sites) directly from the Portal without switching applications.

.. warning::
   Some external websites block iframe embedding through security policies (X-Frame-Options, CSP). Verify the target website allows embedding before configuration.

.. _define-an-external-page-widget:

Define An External Page Widget
------------------------------

The external page widget displays an external URL within the dashboard.

Configuration Dialog
^^^^^^^^^^^^^^^^^^^^

   |external-page-widget-configuration|

   ``Widget title``: Widget display name. Leave blank to hide the widget header.

   ``External URL``: Full URL of the external webpage to embed (e.g., ``https://www.example.com``).

   ``showFullscreenMode``: Show/hide fullscreen mode icon. Default is ``true``.

Define An External Page Widget Using JSON
-----------------------------------------

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

The JSON structure for an external page widget:

   .. code-block:: javascript

      {
         "type": "custom",
         "id": "external-page-widget",
         "showFullscreenMode": true,
         "names": [
            {
               "locale": "en",
               "value": "Axon Ivy Website"
            },
            {
               "locale": "de",
               "value": "Axon Ivy Webseite"
            }
         ],
         "layout": {
            "x": 0,
            "y": 0,
            "w": 8,
            "h": 10
         },
         "data": {
            "url": "https://www.axonivy.com/"
         }
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
     - Widget type. Must be ``"custom"`` for external page widget
   * - ``id``
     - string
     - Unique identifier for the widget
   * - ``names``
     - array
     - Multilingual display names. Each entry: ``{"locale": "en", "value": "Name"}``
   * - ``layout``
     - object
     - Widget position and size (see Layout Properties below)
   * - ``data``
     - object
     - Must contain ``url`` property (see Data Properties below)

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
     - Height in grid rows (min 6). Pixel height = ``60 * h + 20 * (h - 1)``

.. tip::
   **Recommended external page widget size:** Width 6-12 columns, Height 8-12 rows for optimal external content display.

**Display Properties**

.. list-table::
   :widths: 20 15 15 50
   :header-rows: 1

   * - Property
     - Type
     - Default
     - Description
   * - ``showFullscreenMode``
     - boolean
     - ``true``
     - Show/hide fullscreen mode icon

**Data Properties**

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``url``
     - string
     - Full URL of the external webpage to embed (must start with ``http://`` or ``https://``)

.. important::
   Ensure the external URL's server allows iframe embedding. Check the website's ``X-Frame-Options`` and ``Content-Security-Policy`` headers.

.. |external-page-widget-configuration| image:: ../../screenshots/dashboard/external-page-widget-configuration.png