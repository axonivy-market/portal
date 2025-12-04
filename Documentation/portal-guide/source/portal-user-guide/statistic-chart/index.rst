.. _statistic-chart:

Statistic Chart
***************

Portal's statistic charts widget empowers users with deeper insights into tasks, cases, and overall productivity through customizable visualizations.

Overview
========

The statistic chart feature provides:

- **Standard Charts**: Pre-configured charts for common metrics
- **Custom Charts**: Create personalized charts with flexible KPI configurations
- **Multiple Chart Types**: Visualize data as Bar, Line, Pie, or Number charts
- **Auto-Refresh**: Keep data current with configurable refresh intervals
- **Dashboard Integration**: Add charts to any dashboard for at-a-glance insights

Adding Statistic Charts
=======================

To add statistic charts to your dashboard:

#. Navigate to Dashboard Configuration
#. Choose one of the following options:
   
   - Select :guilabel:`Add Widget` to add pre-configured Standard Charts
   - Select :guilabel:`Create custom statistic widget` to configure a new custom chart

#. From the **Statistic Widgets** dropdown, choose your desired chart

|statistic-widget-list-page|

Available Standard Charts
=========================

Portal provides pre-configured charts for common metrics:

.. table::
   :widths: 25 15 60

   +--------------------------------------+---------------+---------------------------------------------------------------+
   | Chart Name                           | Chart Type    | Description                                                   |
   +======================================+===============+===============================================================+
   | **Tasks By Priority**                | Pie / Bar /   | Displays tasks grouped by priority                            |
   |                                      | Number        |                                                               |
   +--------------------------------------+---------------+---------------------------------------------------------------+
   | **Top Priority: 3 Days**             | Bar           | Tasks expiring within the next 3 days                         |
   +--------------------------------------+---------------+---------------------------------------------------------------+
   | **Case Category Avg. Runtime**       | Bar           | Average processing time of cases by category                  |
   +--------------------------------------+---------------+---------------------------------------------------------------+
   | **New Cases Per Day**                | Line          | Number of cases started per day for the last 5 days           |
   +--------------------------------------+---------------+---------------------------------------------------------------+
   | **Completed Cases Per Day**          | Line          | Number of cases finished per day for the last 5 days          |
   +--------------------------------------+---------------+---------------------------------------------------------------+
   | **Running Cases**                    | Number        | All running cases in which the user is involved               |
   +--------------------------------------+---------------+---------------------------------------------------------------+
   | **Tasks Expire by Week End**         | Number        | Tasks that expire by the end of the week                      |
   +--------------------------------------+---------------+---------------------------------------------------------------+
   | **Open Tasks**                       | Number        | All tasks the user can work on                                |
   +--------------------------------------+---------------+---------------------------------------------------------------+
   | **Tasks Due Today**                  | Number        | All tasks that are due today                                  |
   +--------------------------------------+---------------+---------------------------------------------------------------+

Chart Examples
--------------

**Tasks By Priority (Pie Chart)**

|tasks-by-prior-pie-chart|

Displays all tasks by priority in a pie chart visualization.

Chart type: :bdg-info:`Pie`

**Top Priority: 3 Days**

|top-prior-chart|

Displays all tasks that the user can work on, grouped by expiry within the next 3 days.

Chart type: :bdg-info:`Bar`

**Tasks By Priority (Bar Chart)**

|tasks-by-prior-bar-chart|

Displays all tasks that the user can work on grouped by priority in a bar chart.

Chart type: :bdg-info:`Bar`

**Case Category Avg. Runtime**

|avg-runtime-chart|

Shows the average processing time of cases by category.

Chart type: :bdg-info:`Bar`

**New Cases Per Day**

|new-cases-chart|

Shows the number of cases started per day for the last 5 days.

Chart type: :bdg-info:`Line`

**Completed Cases Per Day**

|completed-cases-chart|

Shows the number of cases finished per day for the last 5 days.

Chart type: :bdg-info:`Line`

**Running Cases**

|running-cases-chart|

Shows all running cases in which the user is involved.

Chart type: :bdg-info:`Number`

**Tasks that Expire by the End of the Week**

|tasks-expire-end-week-chart|

Shows all tasks that expire by the end of the week.

Chart type: :bdg-info:`Number`

**Tasks By Priority (Number)**

|tasks-by-prior-number-chart|

Shows all tasks the user can work on by priority.

Chart type: :bdg-info:`Number`

**Open Tasks**

|open-tasks-chart|

Shows all tasks the user can work on.

Chart type: :bdg-info:`Number`

**Tasks Due Today**

|due-today-chart|

Shows all tasks that are due today.

Chart type: :bdg-info:`Number`

Creating Custom Statistic Charts
================================

HowTo: Create Custom Statistic Chart
------------------------------------

#. Navigate to Dashboard Configuration
#. Select :guilabel:`Create custom statistic widget`

   |create-new-custom-statistic-widget|

#. Fill in the configuration form to create your custom chart

   |custom-statistic-widget-configuration-page|

Configuration Options
---------------------

Configuration Options
---------------------

.. table::
   :widths: 30 70

   +------------------------------------+-------------------------------------------------------------------------+
   | Configuration Field                | Description                                                             |
   +====================================+=========================================================================+
   | **Name**                           | Name of the statistic chart                                             |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Description**                    | Description for the statistic chart                                     |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Icon**                           | Icon for custom statistic chart. Click the plus icon to see full list  |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Chart type**                     | Type of chart: Bar, Line, Pie, or Number                                |
   +------------------------------------+-------------------------------------------------------------------------+
   | **For**                            | Chart target: Task or Case                                              |
   +------------------------------------+-------------------------------------------------------------------------+
   | **KPI**                            | Numeric custom fields or ``Counting``. The calculation using selected   |
   |                                    | aggregation method will be performed on selected KPI. If ``Counting``   |
   |                                    | is selected, aggregation method will be disabled                        |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Aggregation method**             | Aggregation methods: Sum, Average, Max, or Min                          |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Group by**                       | Aggregation to group results (depends on chart target). Number type     |
   |                                    | aggregations are not currently supported                                |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Filters**                        | Complex filters (see :ref:`complex-filter`)                             |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Permissions**                    | Permission settings for the statistic chart                             |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Auto-refresh enabled**           | Enable/disable auto-refresh feature                                     |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Auto-refresh interval**          | Time for auto-refresh in seconds (minimum: 60)                          |
   | **(seconds)**                      |                                                                         |
   +------------------------------------+-------------------------------------------------------------------------+
   | **X axis title**                   | Title for the X axis                                                    |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Y axis title**                   | Title for the Y axis                                                    |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Color 1-8**                      | Colors for data in the chart                                            |
   +------------------------------------+-------------------------------------------------------------------------+
   | **Hide label**                     | Toggle to show/hide labels on number charts                             |
   +------------------------------------+-------------------------------------------------------------------------+


Example: KPI Procurement Overview
=================================

The dashboards shown below demonstrate Custom Statistics Widgets capabilities, available starting from Portal version 13.2. These widgets enable fully customizable visualization of KPIs and other information.

This example showcases procurement process dashboards that provide insights into various procurement activities.

Accessing the Sample Dashboard
------------------------------

#. Run the ``portal-user-examples`` project in your Portal installation
#. Navigate to the left menu and select :guilabel:`Sample: KPI Procurement Overview`
#. Generate chart data by:
   
   - Clicking the :guilabel:`Start Process` button in the Process widget on the sample dashboard, or
   - Finding and running the ``Procurement`` process manually
   - Run the process multiple times to generate additional data

#. View the sample dashboards as shown below:

   |statistic-sample-dashboard|

Reviewing Widget Configuration
------------------------------

To view the configuration of these example statistic widgets:

#. In the dashboard configuration page, click :guilabel:`Add widget`
#. Look for widgets labeled as :bdg-secondary:`Example`
#. Select :guilabel:`Edit` to view and review their configurations

   |statistic-config|

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
.. |statistic-sample-dashboard| image:: ../../screenshots/sample-dashboard/statistic-sample-dashboard.png
   :alt: Statistic sample dashboard
.. |statistic-config| image:: ../../screenshots/sample-dashboard/statistic-config.png
   :alt: Statistic configuration example
