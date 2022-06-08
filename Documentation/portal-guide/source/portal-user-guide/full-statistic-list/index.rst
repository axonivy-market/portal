.. _full-statistic-list:

Full Statistics Page
********************

One way to consume statistics is through your personal dashboard (see
:ref:`dashboard`). If you need a deeper insight in what is going on, the
**Full Statistics** page provides you with the perfect toolset. You can
consume statistics and charts here at one glance and have the chance to set up
your own statistics. You can reach the page by using the 
:guilabel:`Statistics` link in the |axon-ivy| Portal menu.

.. hint::
   The statistics allow you to evaluate case and task data only. Business data
   from your processes is not available in these statistics and must be
   considered separately.

|navigate-to-full-statistics-page|

On the **Full Statistics** page you see all charts that were previously
created. Each chart provides you with the following features:

|full-statistics-page-chart-detail|

#. The heading of the chart

#. An |info-icon| **Info** icon, providing you with the configuration of
   the chart

   |full-statistics-page-chart-info-detail|

#. Arrow buttons (|arrow-right-icon|, |arrow-left-icon|) depending
   on the position of the chart to allow you rearrangement according to
   your personal needs

#. The chart itself. It might provide you with a drill down function
   down to the task level.

#. The legend, explaining the part of the charts

.. _howto-create-chart:

HowTo: Create chart
-------------------

#. Click on the link ``Add new chart``. 

#. You are routed to the Chart Configuration page.

#. Select one of the available chart types (see Available charts).

#. Select and configure one or more of the available filters (see Chart filter criteria).

#. Click :guilabel:`Add to dashboard`

#. The :guilabel:`Add statistic chart to dashboard` dialog is opened

#. Enter unique names in supported languages for the chart

#. Create the chart with the button :guilabel:`Ok`

|charts-configuration-page|

**Available charts**

|full-statistics-page|

.. centered:: Chart types

+-----------------------+-----------------------+-----------------------+
| Name                  | Type                  | Description           |
+=======================+=======================+=======================+
| Tasks by priority     | Donut chart           | Displays all tasks    |
|                       |                       | assigned to a user or |
|                       |                       | a belonged role       |
|                       |                       | categorized by their  |
|                       |                       | priority (Low,        |
|                       |                       | Normal, High,         |
|                       |                       | Exception). The       |
|                       |                       | values are shown in   |
|                       |                       | percentages.          |
+-----------------------+-----------------------+-----------------------+
| Case by state         | Donut chart           | Displays all cases    |
|                       |                       | belonging to a user   |
|                       |                       | or a role categorized |
|                       |                       | by their state (In    |
|                       |                       | progress, Done). The  |
|                       |                       | values are shown in   |
|                       |                       | percentages.          |
+-----------------------+-----------------------+-----------------------+
| Tasks by expiry       | Bar chart             | Displays all tasks    |
|                       |                       | assigned to a user or |
|                       |                       | a belonged role       |
|                       |                       | categorized by expiry |
|                       |                       | time (Today, This     |
|                       |                       | Week, This Month,     |
|                       |                       | This Year). The values|
|                       |                       | are the sum of tasks  |
|                       |                       | in the respective     |
|                       |                       | categories.           |
+-----------------------+-----------------------+-----------------------+
| Elapsed time of done  | Bar chart             | Displays the relative |
| cases.                |                       | elapsed time of all   |
|                       |                       | done cases belonging  |
|                       |                       | to a user or a role   |
|                       |                       | categorized by their  |
|                       |                       | Case Category. The    |
|                       |                       | values are shown in   |
|                       |                       | percentages.          |
+-----------------------+-----------------------+-----------------------+
| Processed cases in    | Donut chart           | Displays all cases    |
| time period           |                       | belonging to a user   |
|                       |                       | or a role, where at   |
|                       |                       | least one task was    |
|                       |                       | done during the       |
|                       |                       | selected time period. |
|                       |                       | In addition, the      |
|                       |                       | chart shows if the    |
|                       |                       | related case is still |
|                       |                       | in progress or        |
|                       |                       | already done. The     |
|                       |                       | values are shown in   |
|                       |                       | percentages.          |
+-----------------------+-----------------------+-----------------------+
| Done cases in time    | Donut chart           | Displays all cases    |
| period                |                       | belonging to a user   |
|                       |                       | or a role, which were |
|                       |                       | done during the       |
|                       |                       | selected time period. |
|                       |                       | The values are shown  |
|                       |                       | in percentages.       |
+-----------------------+-----------------------+-----------------------+


.. centered:: Chart filter criteria

+-----------------------------------+-----------------------------------+
| Filter                            | Description                       |
+===================================+===================================+
| Time period                       | Select a predefined time filter   |
|                                   | (last month, last week, etc.) or  |
|                                   | set a custom filter selecting a   |
|                                   | fixed time period with a date     |
|                                   | picker.                           |
+-----------------------------------+-----------------------------------+
| Case categories                   | Select which case categories      |
|                                   | should be included in the chart.  |
|                                   | If you select “Select all”, also  |
|                                   | new categories created in the     |
|                                   | future will be included in the    |
|                                   | chart.                            |
+-----------------------------------+-----------------------------------+
| Roles                             | Select which roles involved in a  |
|                                   | case/task should be included in   |
|                                   | the chart. If you select “Select  |
|                                   | all”, also new roles created in   |
|                                   | the future will be included in    |
|                                   | the chart.                        |
+-----------------------------------+-----------------------------------+
| Workflow states                   | Select which status/statuses      |
|                                   | shall be analyzed (Created, In    |
|                                   | Progress, Done).                  |
+-----------------------------------+-----------------------------------+
| Task priorities                   | Select which task categories      |
|                                   | should be included in the chart.  |
+-----------------------------------+-----------------------------------+
| Custom fields                     | If some business specific data is |
|                                   | included in these                 |
|                                   | custom fields, you can select     |
|                                   | these fields for further          |
|                                   | filtering.                        |
+-----------------------------------+-----------------------------------+

Task analysis
-------------

Portal provides lots of statistic charts, but maybe your business needs further analysis. Therefore, Portal provides Task Analysis that
helps you query tasks with filters, then you could export data to excel for further analysis.

.. important::
   You need to have Portal permission STATISTIC_ANALYZE_TASK to use this feature.

HowTo: Access task analysis
^^^^^^^^^^^^^^^^^^^^^^^^^^^

On the Full Statistics page, click on :guilabel:`Task Analysis`.

|navigate-to-task-analysis|

Task Analysis page is displayed as below.

|task-analysis|

.. _task-analysis-filter-tasks:

HowTo: Filter tasks
^^^^^^^^^^^^^^^^^^^

#. Click on :guilabel:`More options` of :guilabel:`Task filters` or :guilabel:`More options` of :guilabel:`Case filters` to select one of the available filters.

#. Click on the newly added filter and configure it.

#. Add more filters by clicking on :guilabel:`More options` again.

#. To reset to no filters, click on :guilabel:`Reset`.

#. Drop filters by clicking on the |delete-circle-icon| icon next to
   the filter.

#. When finished, click on :guilabel:`Apply filters`.

|filtered-tasks|

HowTo: Create new filter
^^^^^^^^^^^^^^^^^^^^^^^^

#. Filter tasks as described in :ref:`task-analysis-filter-tasks`.

#. Click on :guilabel:`Save filter`.

#. The dialog :guilabel:`Save filter` is opened.

   |saved-filter|

#. Under :guilabel:`Filter name`, provide a recognizable name for the filter.

#. Under :guilabel:`Filter visibility`, you can select if the filter is only
   visible to you or shall be available to all users.

#. Save the filter by clicking on :guilabel:`Ok`.

.. hint::

      #. You may also start the creation of a new filter by selecting an existing
         filter first, reconfiguring it and saving it.

      #. Depending on your permissions you might only be allowed to save filters for
         yourself.

.. _task-analysis-display-tasks:

HowTo: Configure displayed tasks
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. To choose columns to display, click on :guilabel:`Manage columns`. Then you could choose displayed columns by checking the related checkboxes.

#. To sort data, click on the column header. To change sort direction, click on that column header one more time.
   Note that some columns do not support sorting.

HowTo: Export to excel
^^^^^^^^^^^^^^^^^^^^^^

#. All the data that you filtered with selected columns and sort order will be exported.
   Refer to :ref:`task-analysis-filter-tasks` and :ref:`task-analysis-display-tasks` for configuration.

#. In Task Analysis page, click on :guilabel:`Export to Excel`.

#. You could use exported excel file to do further analysis by excel or import it to third-party applications to analyze more.


.. include:: ../includes/_common-icon.rst

.. |navigate-to-full-statistics-page| image:: ../../screenshots/statistic/navigate-to-full-statistics-page.png
.. |full-statistics-page-chart-detail| image:: ../../screenshots/statistic/chart-detail-with-annotation.png
.. |full-statistics-page-chart-info-detail| image:: ../../screenshots/statistic/chart-info-dialog.png
.. |charts-configuration-page| image:: ../../screenshots/statistic/chart-creation-dialog.png
.. |full-statistics-page| image:: ../../screenshots/statistic/available-charts.png
.. |navigate-to-task-analysis| image:: ../../screenshots/statistic/task-analysis/navigate-to-task-analysis.png
.. |task-analysis| image:: ../../screenshots/statistic/task-analysis/task-analysis.png
.. |filtered-tasks| image:: ../../screenshots/statistic/task-analysis/filtered-tasks.png
.. |saved-filter| image:: ../../screenshots/statistic/task-analysis/saved-filter.png
