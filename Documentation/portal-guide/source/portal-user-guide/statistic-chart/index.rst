.. _statistic-chart:

Statistic Chart
***************

Overview
========

Portal offers statistic chart widgets designed to provide deeper insights into tasks, cases, and overall productivity with visual analytics.

.. table::
   :widths: 30 70

   +---------------------------+---------------------------------------------------------------+
   | Chart Category            | Available Metrics                                             |
   +===========================+===============================================================+
   | **Task Statistics**       | By priority, expiry dates, due today, open tasks              |
   +---------------------------+---------------------------------------------------------------+
   | **Case Statistics**       | Running cases, completed per day, new per day                 |
   +---------------------------+---------------------------------------------------------------+
   | **Performance Metrics**   | Average runtime by category, top priority analysis            |
   +---------------------------+---------------------------------------------------------------+

HowTo: Add Statistic Widgets to Dashboard
------------------------------------------

To add statistic charts to your dashboard:

#. Navigate to :ref:`Dashboard Configuration <dashboard-configuration>` for the desired dashboard

#. Click :guilabel:`Add Widget`

#. From the **Statistic Widgets** dropdown menu, choose from the available charts

|statistic-widget-list-page|

Available Statistic Charts
==========================

Task-Related Charts
-------------------

**Tasks By Priority (Pie Chart)**

|tasks-by-prior-pie-chart|

- Displays all tasks by priority
- Chart type: ``Pie``

**Tasks By Priority (Bar Chart)**

|tasks-by-prior-bar-chart|

- Displays all tasks the user can work on, grouped by priority
- Chart type: ``Bar``

**Tasks By Priority (Number)**

|tasks-by-prior-number-chart|

- Shows all tasks the user can work on by priority
- Chart type: ``Number``

**Top Priority: 3 Days**

|top-prior-chart|

- Displays all tasks the user can work on that expire within the next 3 days
- Chart type: ``Bar``

**Tasks that Expire by the End of the Week**

|tasks-expire-end-week-chart|

- Shows all tasks that expire by the end of the week
- Chart type: ``Number``

**Tasks Due Today**

|due-today-chart|

- Shows all tasks that are due today
- Chart type: ``Number``

**Open Tasks**

|open-tasks-chart|

- Shows all tasks the user can work on
- Chart type: ``Number``

Case-Related Charts
-------------------

**Running Cases**

|running-cases-chart|

- Shows all running cases in which the user is involved
- Chart type: ``Number``

**New Cases Per Day**

|new-cases-chart|

- Shows the number of cases started per day for the last 5 days
- Chart type: ``Line``

**Completed Cases Per Day**

|completed-cases-chart|

- Shows the number of cases finished per day for the last 5 days
- Chart type: ``Line``

**Case Category Avg. Runtime**

|avg-runtime-chart|

- Shows the average processing time of cases by category
- Chart type: ``Bar``

.. |statistic-widget-list-page| image:: ../../screenshots/statistic/statistic-widget-list.png
   :alt: Statistic widget list
.. |dashboard-statistic-widget-demo| image:: ../../screenshots/statistic/dashboard-statistic-widget-demo.png
   :alt: Statistic widget demo
.. |tasks-by-prior-pie-chart| image:: ../../screenshots/statistic/tasks-by-prior-pie-chart.png
   :alt: Task by priority pie chart
.. |tasks-by-prior-bar-chart| image:: ../../screenshots/statistic/tasks-by-prior-bar-chart.png
   :alt: Task by priority bar chart
.. |tasks-by-prior-number-chart| image:: ../../screenshots/statistic/tasks-by-prior-number-chart.png
   :alt: Task by priority number chart
.. |avg-runtime-chart| image:: ../../screenshots/statistic/avg-runtime-chart.png
   :alt: Average runtime chart
.. |completed-cases-chart| image:: ../../screenshots/statistic/completed-cases-chart.png
   :alt: Completed cases chart
.. |due-today-chart| image:: ../../screenshots/statistic/due-today-chart.png
   :alt: Number of tasks expire today 
.. |new-cases-chart| image:: ../../screenshots/statistic/new-cases-chart.png
   :alt: Number of new cases chart
.. |open-tasks-chart| image:: ../../screenshots/statistic/running-cases-chart.png
   :alt: Number of running cases chart
.. |tasks-expire-end-week-chart| image:: ../../screenshots/statistic/tasks-expire-end-week-chart.png
   :alt: Number of tasks expire this week chart
.. |top-prior-chart| image:: ../../screenshots/statistic/top-prior-chart.png
   :alt: Top priority tasks chart
.. |running-cases-chart| image:: ../../screenshots/statistic/running-cases-chart.png
   :alt: Running cases chart