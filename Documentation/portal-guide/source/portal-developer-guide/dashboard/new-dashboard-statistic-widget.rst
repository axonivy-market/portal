.. _configure-new-dashboard-statistic-widget:

Configure Statistic Widget
===========================

The statistic widget displays charts and statistics on the Portal dashboard, providing visual insights into workflow data through bar charts, line charts, pie charts, and numeric displays.

.. _portal-statistic-widget:

Define Statistic Widget
-----------------------

A statistic widget places a chart on a dashboard. The chart itself is defined once in the statistics JSON configuration. See :ref:`Portal Statistic Charts <portal-statistic-charts>` for the file format and all chart fields.

The dashboard JSON only references the chart by its ``id``. Do not copy the chart definition into the dashboard JSON.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a sample statistic widget in the dashboard JSON. ``chartId`` must match the ``id`` of a chart defined in the statistics JSON:

.. code-block:: javascript

  {
    "type": "client-statistic",
    "id": "statistic-widget",
    "names": [
      {
        "locale": "en",
        "value": "Open tasks"
      }
    ],
    "layout": {
      "x": 0,
      "y": 0,
      "w": 4,
      "h": 4
    },
    "chartId": "3e188e86a6534324b864167320ef3831",
    "showFullscreenMode": true
  }

JSON Configuration Reference
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``type``
     - string
     - Widget type. Must be ``"client-statistic"``
   * - ``id``
     - string
     - Unique identifier of the widget on the dashboard
   * - ``names``
     - array
     - Multilingual display names. Each entry: ``{"locale": "en", "value": "Name"}``
   * - ``layout``
     - object
     - Widget position and size (``x``, ``y``, ``w``, ``h``)
   * - ``chartId``
     - string
     - ``id`` of the chart definition in the statistics JSON configuration
   * - ``showFullscreenMode``
     - boolean
     - Show or hide the fullscreen icon. Default ``true``

.. tip::
   **Recommended statistic widget size:** Width 3-6 columns, Height 4-8 rows for optimal chart display with labels.

