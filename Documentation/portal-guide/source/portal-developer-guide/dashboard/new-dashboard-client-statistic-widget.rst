.. _configure-new-dashboard-client-statistic-widget:

Configure Client Statistics Widget
=================================

Define Client Statistics Widget
-------------------------------

The Client Statistics widget of the Portal dashboard displays client statistics and charts.

Below is a sample JSON definition of a client statistics widget in the Portal dashboard

.. code-block:: html

  {
    "id": "1",
    "chartId": "1",
    "aggregates": "priority",
    "chartTarget": "TASK",
    "permissions": [
      "Everybody"
    ],
    "chartType": "pie",
    "names": [
      {
        "locale": "de",
        "value": "Aufgaben nach Priorität"
      },
      {
        "locale": "en",
        "value": "Tasks by Priority"
      },
      {
        "locale": "fr",
        "value": "Tãches par priorité"
      },
      {
        "locale": "es",
        "value": "Tareas por prioridad"
      }
    ],
    "descriptions": [
      {
        "locale": "de",
        "value": "Aufgaben nach Priorität"
      },
      {
        "locale": "en",
        "value": "Tasks by Priority"
      },
      {
        "locale": "fr",
        "value": "Tãches par priorité"
      },
      {
        "locale": "es",
        "value": "Tareas por prioridad"
      }
    ],
    "icon": "si si-pie-line-graph",
    "refreshInterval": 3
  }

..

The basic JSON structure of a client statistics widget

   ``id``: ID of the widget

   ``chartId``: ID of the chart

   ``aggregates``: column configuration in the widget
   
   ``chartTarget``: could be ``TASK`` or ``CASE``
   
   ``permissions``: roles can access the widget
   
   ``chartType``: types of chart, could be ``bar``, ``pie``, ``number`` or ``line``
   
   ``names``: multilingual name of chart on the UI

   ``descriptions``: multilingual description of chart on the UI
   
   ``icon``: icon of chart on the UI

   ``refreshInterval``: refresh rate in seconds