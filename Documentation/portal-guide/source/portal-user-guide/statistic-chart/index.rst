.. _statistic-chart:

Statistic Chart
***************

Introduction
------------

Portal offers an all-new statistic charts widget, designed to empower users with deeper insights into their tasks, cases, overall productivity and more customization capabilities.

How to use
----------

To utilize the Statistic feature on the Dashboard Configuration, follow these steps:

- Navigate to Dashboard Configuration of the dashboard where you want to add the widget and select :guilabel:`Add Widget` for predefined Standard Charts or :guilabel:`Create custom statistic widget` for configuring new statistic chart.

- From the dropdown menu labeled **Statistic Widgets**, choose from a variety of insightful charts.

|statistic-widget-list-page|

Available Standard Charts
-------------------------

- Tasks By Priority

    |tasks-by-prior-pie-chart|

    - This pie chart displays all tasks by priority.
    - Chart type: :bdg-info:`Pie`

- Top Priority: 3 Days

    |top-prior-chart|

    - This chart displays all tasks that the user can work on, grouped by expiry within the next 3 days.
    - Chart type: :bdg-info:`Bar`

- Tasks By Priority
 
    |tasks-by-prior-bar-chart|

    - This bar chart displays all tasks that the user can work on grouped by priority.
    - Chart type: :bdg-info:`Bar`

- Case Category Avg. Runtime

    |avg-runtime-chart|

    - This chart shows the average processing time of cases by category. 
    - Chart type: :bdg-info:`Bar`

- New Cases Per Day

    |new-cases-chart|

    - This chart shows the number of cases started per day for the last 5 days.
    - Chart type: :bdg-info:`Line`

- Completed Cases Per Day

    |completed-cases-chart|

    - This chart shows the number of cases finished per day for the last 5 days.
    - Chart type: :bdg-info:`Line`

- Running Cases

    |running-cases-chart|

    - This chart shows all running cases in which the user is involved.
    - Chart type: :bdg-info:`Number`

- Tasks that expire by the end of the week

    |tasks-expire-end-week-chart|

    - This chart shows all tasks that expire by the end of the week.
    - Chart type: :bdg-info:`Number`

- Tasks By Priority

    |tasks-by-prior-number-chart|

    - This chart shows all tasks the user can work on by priority.
    - Chart type: :bdg-info:`Number`

- Open Tasks

    |open-tasks-chart|

    - This chart shows all tasks the user can work on.
    - Chart type: :bdg-info:`Number`

- Tasks Due Today

    |due-today-chart|

    - This chart shows all tasks that are due today.
    - Chart type: :bdg-info:`Number`
    
How to configure new statistic chart
------------------------------------

- Navigate to Dashboard Configuration of the dashboard and select :guilabel:`Create custom statistic widget`.

    |create-new-custom-statistic-widget|

- From the configuration for statistic chart, fill in the form to create new statistic chart.

    |custom-statistic-widget-configuration-page|

Available values for configuring statistic chart 
------------------------------------------------

.. list-table::

 * - **Value**
   - **Usage Guideline**
 * - Name
   - | Name of the statistic chart.
 * - Description
   - | Description for the statistic chart.
 * - Chart type
   - | Type of the chart (Bar, Line, Pie, Number).
 * - For
   - | Chart target (Task, Case).
 * - Icon
   - | Icon for custom statistic chart.
     | Click on the plus icon at the end of the line to see full list of icons.
 * - Group by
   - | Aggregation to group all the results, the drop down values depend on the chart target.
     | Currently we don't support aggregations have type number.
 * - Filters
   - | Works like complex filter of Portal.
     | Please refer to :ref:`complex-filter` for more details.
 * - Permissions
   - | Permission for the current statistic chart.
 * - Auto-refresh enabled
   - | The statistic can be auto-refreshed after a certain prediod.
     | Click on this button to turn on/off the feature.
 * - Auto-refresh interval (seconds)
   - | The time for statistic chart to be auto-refreshed in second.
     | Minimum number is 60.
 * - X axis title
   - | X title for the statistic chart.
 * - Y axis title
   - | Y title for the statistic chart.
 * - Color 1-8
   - | Colors for data in the chart.
 * - Hide label
   - | Toggle to show label of number chart.
 * - Condition-based coloring enabled
   - | This feature allows user to define colors for each chart based on specific conditions.
     | Click on this button to turn on/off the feature.

Condition-based coloring feature
--------------------------------

When the feature is enabled, the user can define colors for each chart based on specific conditions. The user can use this feature on ``Bar chart``, ``Line chart`` and ``Pie chart``.

    |condition-based-coloring-enabled|

Scope
~~~~~

Choose the scope that the condition will affect.

There are two options:

- All values: apply color conditions to all data in the chart.

- Specific value: apply color conditions to selected value.

.. note::
  If there is no data available, the user cannot add a new condition with ``specific`` scope.

Default color
~~~~~~~~~~~~~

This color is applied to charts when no data is available.

Condition
~~~~~~~~~

Each condition requires four values:

1. **Target value:** Either ``All values`` or a specific value based on your ``Group by`` selection.

2. **Operator:** Greater than, Less than, Equal to, Greater than or equal to, or Less than or equal to.

3. **Comparison value:** The value to compare against.

4. **Color:** The color applied when data meets the condition.

.. important::

  - Define clear conditions.

  - Keep it simple.

  - When multiple conditions are satisfied, the first condition defined wins.


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
.. |open-tasks-chart| image:: ../../screenshots/statistic/open-tasks-chart.png
   :alt: Number of opening tasks chart
.. |tasks-expire-end-week-chart| image:: ../../screenshots/statistic/tasks-expire-end-week-chart.png
   :alt: Number of tasks expire this week chart
.. |top-prior-chart| image:: ../../screenshots/statistic/top-prior-chart.png
   :alt: Top priority tasks chart
.. |running-cases-chart| image:: ../../screenshots/statistic/running-cases-chart.png
   :alt: Running cases chart
.. |create-new-custom-statistic-widget| image:: ../../screenshots/statistic/create-new-custom-statistic-widget.png
   :alt: Create new custom statistic widget
.. |custom-statistic-widget-configuration-page| image:: ../../screenshots/statistic/custom-statistic-widget-configuration-page.png
   :alt: Custom statistic widet configuration page
.. |condition-based-coloring-enabled| image:: ../../screenshots/statistic/condition-based-coloring-enable.png
   :alt: Condition-based coloring feature