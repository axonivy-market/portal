.. _configure-new-dashboard-statistic-widget:

Configure Statistic Widget
===========================

The statistic widget displays charts and statistics on the Portal dashboard, providing visual insights into workflow data. Charts are predefined and referenced by their chart ID.

.. _portal-statistic-widget:

Define Statistic Widget
-------------------------

The statistic widget displays standard charts configured in the Portal. Refer to :ref:`Portal.StatisticCharts <portal-statistic-charts>` for available chart definitions.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a sample JSON definition of a statistic widget in the Portal dashboard:

.. code-block:: javascript

   {
      "type": "client-statistic",
      "id": "client-statistic-widget",
      "layout": {
         "x": 0,
         "y": 0,
         "w": 4,
         "h": 6
      },
      "chartId": "10",
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
     - Widget type. Must be ``"client-statistic"`` for statistic widget
   * - ``id``
     - string
     - Unique identifier for the widget
   * - ``layout``
     - object
     - Widget position and size (see Layout Properties below)
   * - ``chartId``
     - string
     - ID of the standard client statistic chart (see :ref:`Portal.StatisticCharts <portal-statistic-charts>`)

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

.. tip::
   **Recommended statistic widget size:** Width 3-6 columns, Height 4-8 rows for optimal chart display with labels.

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

.. note::
   Chart definitions including chart types, data sources, and configurations are managed separately in Portal.StatisticCharts. The widget references these charts by their ``chartId``.