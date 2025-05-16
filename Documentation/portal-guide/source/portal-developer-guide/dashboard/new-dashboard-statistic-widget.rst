.. _configure-new-dashboard-statistic-widget:

Configure Statistic Widget
===========================

.. _portal-statistic-widget:

Define Statistic Widget
-----------------------

The Statistic widget of the Portal dashboard displays statistic and charts.

Below is a sample JSON definition of a statistic widget in the Portal dashboard

.. code-block:: javascript

  {
    "id": "3e188e86a6534324b864167320ef3831",
    "version": "13.1.0",
    "statisticAggregation": {
      "field": "businessState",
      "type": "standard"
    },
    "filters": [
      {
        "field": "state",
        "values": [
          "DONE",
          "IN_PROGRESS",
          "OPEN"
        ],
        "operator": "in",
        "type": "standard"
      }
    ],
    "permissions": [
      "Everybody"
    ],
    "chartTarget": "task",
    "chartType": "bar",
    "icon": "si-add-circle",
    "refreshInterval": 300,
    "names": [
      {
        "locale": "en",
        "value": "New statistic"
      },
      {
        "locale": "fr",
        "value": "New statistic"
      },
      {
        "locale": "de",
        "value": "New statistic"
      },
      {
        "locale": "ja",
        "value": "New statistic"
      },
      {
        "locale": "es",
        "value": "New statistic"
      }
    ],
    "descriptions": [
      {
        "locale": "en",
        "value": "Demo new statistic"
      },
      {
        "locale": "fr",
        "value": "Demo new statistic"
      },
      {
        "locale": "de",
        "value": "Demo new statistic"
      },
      {
        "locale": "ja",
        "value": "Demo new statistic"
      },
      {
        "locale": "es",
        "value": "Demo new statistic"
      }
    ],
    "barChartConfig": {
      "xTitles": [
        {
          "locale": "en",
          "value": "State"
        },
        {
          "locale": "fr",
          "value": "State"
        },
        {
          "locale": "de",
          "value": "State"
        },
        {
          "locale": "ja",
          "value": "State"
        },
        {
          "locale": "es",
          "value": "State"
        }
      ],
      "yTitles": [
        {
          "locale": "en",
          "value": "Number of tasks"
        },
        {
          "locale": "fr",
          "value": "Number of tasks"
        },
        {
          "locale": "de",
          "value": "Number of tasks"
        },
        {
          "locale": "ja",
          "value": "Number of tasks"
        },
        {
          "locale": "es",
          "value": "Number of tasks"
        }
      ],
      "backgroundColors": [
        "#6299f7",
        "#8dc261",
        "#98bffa",
        "#bee3cb",
        "#c8befa",
        "#f5bf9f",
        "#f8da96",
        "#f9908c"
      ]
    }
  }
..

The basic JSON structure of a statistic widget

   ``id``: ID of the widget.

   ``version``: current version of the widget.

   ``statisticAggregation``: statistic's aggregation.

      - ``field``: aggregation field.

      - ``type``: type of the field, could be `standard` or `custom`.

   ``filters``: filters for the statistic chart.

      - ``field``: field to filter.

      - ``values``: value of the filter to query.

      - ``operator``: operator for the filter.

      - ``type``: type of the field.

   ``permissions``: permissions for statistic chart.

   ``chartTarget``: chart's target, could be `case` or `task`.

   ``chartType``: chart's type, could be `bar`, `line`, `pie` or `number`.

   ``icon``: icon for the statistic chart.

   ``refreshInterval``: number of seconds for the chart auto-refreshed, minimum value is 60.

   ``names``: name for the statistic chart, multi-language supported.

   ``bar/line/pie/numberChartConfig``: configuration for each chart type.
