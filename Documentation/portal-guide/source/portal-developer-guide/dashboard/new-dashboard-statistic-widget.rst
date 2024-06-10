.. _configure-new-dashboard-statistic-widget:

Configure Statistics Widget
===========================

Define Statistics Widget
------------------------

The Statistics widget of the Portal dashboard displays statistics and charts.

Below is a sample JSON definition of a statistics widget in the Portal dashboard

.. code-block:: html

   {
      "id": "1",
      "aggregates": "priority",
      "filter": "businessState:OPEN IN_PROGRESS,canWorkOn",
      "chartTarget": "TASK",
      "chartType": "pie",
      "names": [
         {
            "locale": "de",
            "value": "Aufgaben nach Prioritäten"
         },
         {
            "locale": "en",
            "value": "Tasks by Priority"
         },
         {
            "locale": "fr",
            "value": "Tâches par Priorité"
         },
         {
            "locale": "es",
            "value": "Tareas por Prioridad"
         }
         ],
      "descriptions": [
         {
            "locale": "de",
            "value": "Dieses Kreisdiagramm zeigt alle Aufgaben nach Priorität an."
         },
         {
            "locale": "en",
            "value": "This pie chart displays all tasks by priority."
         },
         {
            "locale": "fr",
            "value": "Ce diagramme à secteurs affiche toutes les tâches par priorité."
         },
         {
            "locale": "es",
            "value": "Este gráfico circular muestra todas las tareas por prioridad."
         }
      ],
      "icon": "si si-analytics-pie-2",
      "refreshInterval": 300
   }
..

The basic JSON structure of a statistics widget

   ``id``: ID of the widget

   ``aggregates``: the aggregation query to make bucket (grouping) or metric aggregations. Please refer to :ref:`Portal.StatisticCharts <portal-statistic-charts>` for more information.

   ``filter``: list of filters to apply for each chart. Please refer to :ref:`Portal.StatisticCharts <portal-statistic-charts>` for more information.

   ``chartTarget``: the chart target of the widget, ``TASK`` or ``CASE``.

   ``chartType``: type of the chart such as ``Pie``, ``Bar``, ``Line`` and ``KPI``. For ``KPI`` chart, you have to set the type as ``Number`` instead of ``KPI``.

   ``names``: the multilingual display name of the chart.

   ``descriptions``: the multilingual description of the chart.

   ``icon``: the icon of each widget.
   
   ``refreshInterval``: statistic widget refresh interval in seconds 

For a specific chart such as ``Bar``, ``Line`` or ``KPI`` chart, it requires some additional fields. Please refer to :ref:`Portal.StatisticCharts <portal-statistic-charts>` for more information.
