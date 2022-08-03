.. _configure-new-dashboard-statistic-widget:

Configure Statistics Widget
===========================

Define Statistics Widget
------------------------

The Statistics widget of the Portal dashboard displays statistics and charts.

Below is a sample JSON definition of a statistics widget in the Portal dashboard

.. code-block:: html

   {
      "type": "statistic",
      "id": "statistic_1",
      "name": "Tasks by Priority",
      "layout": {
         "styleClass": "new-widget",
         "w": 5,
         "h": 5,
         "x": 0,
         "y": 0
      },
      "chart": {
         "id": "0",
         "names": [{
               "locale": "de",
               "value": "Aufgaben nach Priorität"
            }, {
               "locale": "en",
               "value": "Tasks by Priority"
            }, {
               "locale": "fr",
               "value": "Tãches par priorité"
            }, {
               "locale": "es",
               "value": "Tareas por prioridad"
            }
         ],
         "type": "TASK_BY_PRIORITY",
         "filter": {
            "timePeriodSelection": "CUSTOM"
         }
      }
   }
..

The basic JSON structure of a statistics widget

   ``type``: type of widget. Use ``statistic`` for a statistics widget

   ``id``: ID of the widget

   ``name``: Name of the widget on UI

   ``layout``: layout definition of statistics widget

      ``x``: HTML DOM Style ``left`` will be calculated by formula ``x / 12 * 100%``

      ``y``: HTML DOM Style ``top`` will be calculated by formula ``y / 12 * 100%``

      ``w``: HTML DOM Style ``width`` will be calculated by formula ``60 * w + 20 * (w - 1)``

      ``h``: HTML DOM Style ``height`` will be calculated by formula ``60 * h + 20 * (h - 1)``

      ``styleClass`` (optional): add CSS Classes to HTML DOM of the widget

      ``style`` (optional): add inline style to HTML DOM of the widget

   ``chart``: the chart data model of the widget

      ``id``: the identification of the chart. It is an auto-generated UUID.

      ``names``: the display name of the chart. This allows for multi-language
      using the CMS and the user's Locale.

      ``type``: type of chart such as ``TASK_BY_PRIORITY``, ``CASES_BY_STATE``,
      ``CASES_BY_FINISHED_TASK``, ``CASES_BY_FINISHED_TIME``, ``TASK_BY_EXPIRY``,
      ``ELAPSED_TIME_BY_CASE_CATEGORY``, and ``CASES_BY_CATEGORY``

      ``filter``: list of filters to apply for each chart. Please refer to :ref:`Portal.StatisticCharts <portal-statistic-charts>` for more information.
