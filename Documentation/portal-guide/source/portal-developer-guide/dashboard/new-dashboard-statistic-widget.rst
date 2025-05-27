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
      "field": "state",
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
        "value": "Nouvelle statistique"
      },
      {
        "locale": "de",
        "value": "Neue Statistik"
      },
      {
        "locale": "ja",
        "value": "新しい統計"
      },
      {
        "locale": "es",
        "value": "Nueva estadística"
      }
    ],
    "descriptions": [
      {
        "locale": "en",
        "value": "Demo new statistic"
      },
      {
        "locale": "fr",
        "value": "Démonstration d'une nouvelle statistique"
      },
      {
        "locale": "de",
        "value": "Neue Demo-Statistik"
      },
      {
        "locale": "ja",
        "value": "新しい統計のデモ"
      },
      {
        "locale": "es",
        "value": "Demo nueva estadística"
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
          "value": "État"
        },
        {
          "locale": "de",
          "value": "Status"
        },
        {
          "locale": "ja",
          "value": "状態"
        },
        {
          "locale": "es",
          "value": "Estatus"
        }
      ],
      "yTitles": [
        {
          "locale": "en",
          "value": "Number of tasks"
        },
        {
          "locale": "fr",
          "value": "Nombre de tâches"
        },
        {
          "locale": "de",
          "value": "Anzahl der Aufgaben"
        },
        {
          "locale": "ja",
          "value": "タスクの数"
        },
        {
          "locale": "es",
          "value": "Número de tareas"
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

   ``descriptions``: description for the statistic chart, multi-language supported.

For some specific charts such as ``Bar``, ``Pie``, ``Line`` or ``Number``, there are additional fields:

- ``barChartConfig``: additional fields for configuring the ``Bar`` chart, you can add if chart type is ``bar`` 

   - ``xTitles``: the multilingual display title for the x-axis

   - ``yTitles``: the multilingual display title for the y-axis
   
   - ``backgroundColors`` : the colors to display the chart

- ``lineChartConfig``: additional fields for configuring the ``Line`` chart, you can add if chart type is ``line``

   - ``xTitles``: the multilingual display title for the x-axis

   - ``yTitles``: the multilingual display title for the y-axis

   - ``backgroundColors`` : the colors to display the chart
 
- ``pieChartConfig``: additional fields for configuring the ``Pie`` chart, you can add if chart type is ``pie``

   - ``backgroundColors`` : the colors to display the chart

- ``numberChartConfig``: additional fields for configuring the ``Number`` chart, you can add if chart type is ``number``

   - ``hideLabel``: toggle to show label of the number chart
