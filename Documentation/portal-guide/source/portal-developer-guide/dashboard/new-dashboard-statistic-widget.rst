.. _configure-new-dashboard-statistic-widget:

Configure Statistic Widget
===========================

The statistic widget displays charts and statistics on the Portal dashboard, providing visual insights into workflow data through bar charts, line charts, pie charts, and numeric displays.

.. _portal-statistic-widget:

Define Statistic Widget
-------------------------

The statistic widget displays customizable charts with configurable data aggregation, filtering, and visualization options.

Configuration Example
^^^^^^^^^^^^^^^^^^^^^

Below is a sample JSON definition of a statistic widget in the Portal dashboard:

.. code-block:: javascript

  {
    "id": "3e188e86a6534324b864167320ef3831",
    "version": "13.1.0",
    "statisticAggregation": {
      "field": "state",
      "type": "standard",
      "kpiField": "InvoiceTotalAmount",
      "aggregationMethod": "avg"
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

JSON Configuration Reference
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

**Required Properties**

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``id``
     - string
     - Unique identifier for the statistic widget
   * - ``version``
     - string
     - Current version of the widget configuration
   * - ``chartTarget``
     - string
     - Data source. Options: ``"task"`` or ``"case"``
   * - ``chartType``
     - string
     - Chart visualization type. Options: ``"bar"``, ``"line"``, ``"pie"``, ``"number"``
   * - ``names``
     - array
     - Multilingual display names. Each entry: ``{"locale": "en", "value": "Name"}``

**Data Aggregation**

.. list-table::
   :widths: 20 15 15 50
   :header-rows: 1

   * - Property
     - Type
     - Required
     - Description
   * - ``statisticAggregation``
     - object
     - Yes
     - Defines how data is grouped and calculated
   * - ``statisticAggregation.field``
     - string
     - Yes
     - Field to group by (e.g., "state", "priority")
   * - ``statisticAggregation.type``
     - string
     - Yes
     - Field type: ``"standard"`` or ``"custom"``
   * - ``statisticAggregation.kpiField``
     - string
     - No
     - Numeric custom field for calculations (e.g., "InvoiceTotalAmount")
   * - ``statisticAggregation.aggregationMethod``
     - string
     - No
     - Calculation method: ``"sum"``, ``"avg"``, ``"max"``, ``"min"``

.. note::
   When ``kpiField`` and ``aggregationMethod`` are not provided, the chart displays count statistics (number of tasks or cases). When specified, the chart applies the aggregation method to numeric values in ``kpiField``.

**Filtering & Permissions**

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``filters``
     - array
     - Data filters. Each filter: ``{"field": "state", "values": ["OPEN"], "operator": "in", "type": "standard"}``
   * - ``permissions``
     - array
     - Role names that can view this chart (e.g., ``["Everybody"]``)

**Display Options**

.. list-table::
   :widths: 20 15 15 50
   :header-rows: 1

   * - Property
     - Type
     - Default
     - Description
   * - ``descriptions``
     - array
     - *(empty)*
     - Multilingual descriptions. Each entry: ``{"locale": "en", "value": "Description"}``
   * - ``icon``
     - string
     - *(none)*
     - Icon class (e.g., ``"si-add-circle"``)
   * - ``refreshInterval``
     - number
     - ``300``
     - Auto-refresh interval in seconds (minimum 60)

.. tip::
   **Recommended statistic widget size:** Width 3-6 columns, Height 4-8 rows for optimal chart display with labels.

Chart-Specific Configuration
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Each chart type supports additional configuration for axis labels and colors:

**Bar Chart Configuration**

For ``chartType: "bar"``, add ``barChartConfig``:

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``xTitles``
     - array
     - Multilingual x-axis labels. Each entry: ``{"locale": "en", "value": "Label"}``
   * - ``yTitles``
     - array
     - Multilingual y-axis labels
   * - ``backgroundColors``
     - array
     - Hex color codes for bars (e.g., ``["#6299f7", "#8dc261"]``)

**Line Chart Configuration**

For ``chartType: "line"``, add ``lineChartConfig``:

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``xTitles``
     - array
     - Multilingual x-axis labels
   * - ``yTitles``
     - array
     - Multilingual y-axis labels
   * - ``backgroundColors``
     - array
     - Hex color codes for line segments

**Pie Chart Configuration**

For ``chartType: "pie"``, add ``pieChartConfig``:

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``backgroundColors``
     - array
     - Hex color codes for pie slices

**Number Chart Configuration**

For ``chartType: "number"``, add ``numberChartConfig``:

.. list-table::
   :widths: 20 15 65
   :header-rows: 1

   * - Property
     - Type
     - Description
   * - ``hideLabel``
     - boolean
     - Toggle to show/hide the number chart label
