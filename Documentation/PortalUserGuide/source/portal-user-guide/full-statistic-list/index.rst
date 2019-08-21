.. _full-statistic-list:

Full Statistics page
********************

One way to consume statistics is through your personal dashboard (see
:ref:`dashboard`). If you need a deeper insight in what is going on, the
:guilabel:`Full Statistics` page provides you with the perfect toolset. You can
consume statistics and charts here at one glance and have the chance to setup
your own statistics. You can reach the page by using the |image88|
**Statistics** link in the Axon.ivy Portal menu or by using the **Show all
charts** quick link in the statistics section of your dashboard.

.. hint:: 
   The statistics allow you to evaluate case and task data only. Business data
   from your processes is not available in these statistics and must be
   considered separately.                      

|image89|

.. centered:: Navigate to Full Statistics page

On the :guilabel:`Full Statistics` page you see all charts that were previously
created. Each chart provides you with the following features:

1. The heading of the chart

2. An |image90| **Info** button, providing you with the configuration of
   the chart

3. Arrow buttons (|image91|, |image92|, |image93|, |image94|) depending
   on the position of the chart to allow you rearrangement according to
   your personal needs

4. The chart title

5. The chart itself. It might provide you with a drill down function
   down to the task level.

6. The legend, explaining the part of the charts

|image95|

.. centered:: Full statistics page

.. _howto-create-chart:

HowTo: Create chart
-------------------

1. Use the link **Add new chart**

2. You are routed to the :guilabel:`Chart Configuration` page

3. Select one of the available chart types (see Available charts)

4. Select and configure one or more of the available filters (see Chart filter criterias).

5. Click **Add to dashboard**

6. The :guilabel:`Add statistic chart to dashboard` dialog is opened

7. Enter a unique name for the chart

8. Create the chart with the button **OK**

|image96|

.. centered:: Charts configuration page

+-----------------------+-----------------------+-----------------------+
| Name                  | Type                  | Description           |
+=======================+=======================+=======================+
| Tasks by priority     | Pie chart             | Displays all tasks    |
|                       |                       | assigned to a user or |
|                       |                       | a belonged role       |
|                       |                       | categorized by their  |
|                       |                       | priority (Low,        |
|                       |                       | Normal, High,         |
|                       |                       | Exception). The       |
|                       |                       | values are shown in   |
|                       |                       | percentages.          |
+-----------------------+-----------------------+-----------------------+
| Case by state         | Pie chart             | Displays all cases    |
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
|                       |                       | This Year). The valus |
|                       |                       | are the sum of tasks  |
|                       |                       | in the respective     |
|                       |                       | categories.           |
+-----------------------+-----------------------+-----------------------+
| Elapsed time of done  | Bar chart             | Displays the relative |
| cases. (Planned       |                       | elapsed time of all   |
| change for Version    |                       | done cases belonging  |
| 8.0)                  |                       | to a user or a role   |
|                       |                       | categorized by their  |
|                       |                       | Case Category. The    |
|                       |                       | values are shown in   |
|                       |                       | percentages.          |
+-----------------------+-----------------------+-----------------------+
| Processed cases in    | Pie chart             | Displays all cases    |
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
| Done cases in time    | Pie chart             | Displays all cases    |
| period (Planned       |                       | belonging to a user   |
| change for Version    |                       | or a role, which were |
| 8.0)                  |                       | done during the       |
|                       |                       | selected time period. |
|                       |                       | The values are shown  |
|                       |                       | in percentages.       |
+-----------------------+-----------------------+-----------------------+

.. centered:: Available charts

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
| Workflow states                   | Select which status / statuses    |
|                                   | shall be analyzed (Created, In    |
|                                   | Progress, Done).                  |
+-----------------------------------+-----------------------------------+
| Task priorities                   | Select which task categories      |
|                                   | should be included in the chart.  |
+-----------------------------------+-----------------------------------+
| CustomVarFields 1-5               | If some business specific data is |
|                                   | included in these                 |
|                                   | CustomVarFields, you can select   |
|                                   | these fields for further          |
|                                   | filtering.                        |
+-----------------------------------+-----------------------------------+

.. centered:: Chart filter criterias

|image7|

.. centered::  Toggle portal menu

.. |image0| image:: ../media/image2.png
   :width: 0.3937in
   :height: 0.3937in
.. |image1| image:: ../media/image2.png
   :width: 0.3937in
   :height: 0.3937in
.. |image2| image:: ../media/image4.png
   :width: 6.49606in
   :height: 3.26772in
.. |image3| image:: ../media/image2.png
   :width: 0.3937in
   :height: 0.3937in
.. |image4| image:: ../media/image2.png
   :width: 0.3937in
   :height: 0.3937in
.. |image5| image:: ../media/image5.png
   :width: 0.1378in
   :height: 0.15748in
.. |image6| image:: ../media/image7.png
   :width: 6.49606in
   :height: 3.2874in
.. |image7| image:: ../media/image8.png
   :width: 6.49606in
   :height: 3.28346in
.. |image8| image:: ../media/image9.png
   :width: 0.1378in
   :height: 0.15748in
.. |image9| image:: ../media/image11.png
   :width: 6.49606in
   :height: 3.28346in
.. |image10| image:: ../media/image12.png
   :width: 6.49606in
   :height: 3.28346in
.. |image11| image:: ../media/image13.png
   :width: 6.49606in
   :height: 3.28346in
.. |image12| image:: ../media/image14.png
   :width: 0.1378in
   :height: 0.15748in
.. |image13| image:: ../media/image16.png
   :width: 6.49606in
   :height: 3.28346in
.. |image14| image:: ../media/image2.png
   :width: 0.3937in
   :height: 0.3937in
.. |image15| image:: ../media/image2.png
   :width: 0.3937in
   :height: 0.3937in
.. |image16| image:: ../media/image17.png
   :width: 0.10335in
   :height: 0.11811in
.. |image17| image:: ../media/image19.png
   :width: 2.69685in
   :height: 1.54724in
.. |image18| image:: ../media/image20.png
   :width: 2.64961in
   :height: 1.5315in
.. |image19| image:: ../media/image21.png
   :width: 2.69291in
   :height: 1.55512in
.. |image20| image:: ../media/image22.png
   :width: 2.67717in
   :height: 1.54331in
.. |image21| image:: ../media/image23.png
   :width: 2.68504in
   :height: 1.55118in
.. |image22| image:: ../media/image14.png
   :width: 0.1378in
   :height: 0.15748in
.. |image23| image:: ../media/image24.png
   :width: 6.49606in
   :height: 3.28346in
.. |image24| image:: ../media/image25.png
   :width: 6.49606in
   :height: 3.28346in
.. |image25| image:: ../media/image26.png
   :width: 3.40157in
   :height: 1.77953in
.. |image26| image:: ../media/image27.png
   :width: 3.40157in
   :height: 1.77953in
.. |image27| image:: ../media/image28.png
   :width: 6.49606in
   :height: 3.28346in
.. |image28| image:: ../media/image29.png
   :height: 0.15748in
.. |image29| image:: ../media/image31.png
   :height: 0.15748in
.. |image30| image:: ../media/image33.png
   :width: 2.35433in
   :height: 1.97244in
.. |image31| image:: ../media/image34.png
   :width: 0.15748in
   :height: 0.15748in
.. |image32| image:: ../media/image36.png
   :width: 6.49606in
   :height: 0.79134in
.. |Information| image:: ../media/image2.png
   :width: 0.3937in
   :height: 0.3937in
.. |image34| image:: ../media/image37.png
   :width: 6.49606in
   :height: 3.28346in
.. |image35| image:: ../media/image38.png
   :width: 0.17717in
   :height: 0.15748in
.. |image36| image:: ../media/image40.png
   :width: 0.10827in
   :height: 0.15748in
.. |image37| image:: ../media/image42.png
   :width: 6.49606in
   :height: 3.28346in
.. |image38| image:: ../media/image43.png
   :width: 6.49606in
   :height: 3.28346in
.. |image39| image:: ../media/image44.png
   :width: 6.49606in
   :height: 3.28346in
.. |image40| image:: ../media/image45.png
   :width: 6.49606in
   :height: 3.28346in
.. |image41| image:: ../media/image46.png
   :width: 6.49606in
   :height: 3.28346in
.. |image42| image:: ../media/image47.png
   :width: 6.49606in
   :height: 3.28346in
.. |image43| image:: ../media/image48.png
   :width: 6.49606in
   :height: 3.28346in
.. |image44| image:: ../media/image49.png
   :width: 0.15748in
   :height: 0.15748in
.. |image45| image:: ../media/image51.png
   :width: 6.49606in
   :height: 3.28346in
.. |image46| image:: ../media/image52.png
   :width: 6.49606in
   :height: 3.28346in
.. |image47| image:: ../media/image34.png
   :width: 0.15748in
   :height: 0.15748in
.. |Warnung| image:: ../media/image53.png
   :width: 0.3937in
   :height: 0.3937in
.. |image49| image:: ../media/image55.png
   :width: 0.1378in
   :height: 0.15748in
.. |image50| image:: ../media/image57.png
   :width: 6.49606in
   :height: 3.28346in
.. |image51| image:: ../media/image58.png
   :width: 0.15748in
   :height: 0.15748in
.. |image52| image:: ../media/image60.png
   :width: 6.49606in
   :height: 3.28346in
.. |image53| image:: ../media/image61.png
   :height: 0.15748in
.. |image54| image:: ../media/image63.png
   :height: 0.15748in
.. |image55| image:: ../media/image65.png
   :width: 6.49606in
   :height: 3.28346in
.. |image56| image:: ../media/image66.png
   :width: 0.15748in
   :height: 0.15748in
.. |image57| image:: ../media/image68.png
   :width: 6.49606in
   :height: 1.59449in
.. |image58| image:: ../media/image69.png
   :width: 0.15748in
   :height: 0.15748in
.. |image59| image:: ../media/image71.png
   :width: 6.49606in
   :height: 3.28346in
.. |image60| image:: ../media/image40.png
   :width: 0.10827in
   :height: 0.15748in
.. |image61| image:: ../media/image72.png
   :width: 6.49606in
   :height: 3.26772in
.. |image62| image:: ../media/image69.png
   :width: 0.15748in
   :height: 0.15748in
.. |image63| image:: ../media/image73.png
   :width: 6.49606in
   :height: 3.27953in
.. |image64| image:: ../media/image74.png
   :width: 0.1378in
   :height: 0.15748in
.. |image65| image:: ../media/image76.png
   :width: 6.49606in
   :height: 2.3937in
.. |image66| image:: ../media/image77.png
   :width: 6.49606in
   :height: 1.41339in
.. |image67| image:: ../media/image78.png
   :width: 0.15748in
   :height: 0.15748in
.. |image68| image:: ../media/image80.png
   :width: 6.49606in
   :height: 3.28346in
.. |image69| image:: ../media/image58.png
   :width: 0.15748in
   :height: 0.15748in
.. |image70| image:: ../media/image81.png
   :width: 6.49606in
   :height: 1.8622in
.. |image71| image:: ../media/image40.png
   :width: 0.10827in
   :height: 0.15748in
.. |image72| image:: ../media/image82.png
   :height: 0.15748in
.. |image73| image:: ../media/image61.png
   :height: 0.15748in
.. |image74| image:: ../media/image63.png
   :height: 0.15748in
.. |image75| image:: ../media/image84.png
   :width: 6.49606in
   :height: 2.5in
.. |image76| image:: ../media/image66.png
   :width: 0.15748in
   :height: 0.15748in
.. |image77| image:: ../media/image85.png
   :width: 6.49606in
   :height: 2.5in
.. |image78| image:: ../media/image86.png
   :width: 6.49606in
   :height: 1.52756in
.. |image79| image:: ../media/image69.png
   :width: 0.15748in
   :height: 0.15748in
.. |image80| image:: ../media/image87.png
   :width: 6.49606in
   :height: 2.51181in
.. |image81| image:: ../media/image66.png
   :width: 0.15748in
   :height: 0.15748in
.. |image82| image:: ../media/image88.png
   :width: 6.49606in
   :height: 2.51575in
.. |image83| image:: ../media/image69.png
   :width: 0.15748in
   :height: 0.15748in
.. |image84| image:: ../media/image89.png
   :width: 6.49606in
   :height: 2.73228in
.. |image85| image:: ../media/image74.png
   :width: 0.1378in
   :height: 0.15748in
.. |image86| image:: ../media/image90.png
   :width: 6.49606in
   :height: 2.50787in
.. |image87| image:: ../media/image91.png
   :width: 6.49606in
   :height: 1.96063in
.. |image88| image:: ../media/image92.png
   :width: 0.1378in
   :height: 0.15748in
.. |image89| image:: ../media/image94.png
   :width: 6.49606in
   :height: 3.53543in
.. |image90| image:: ../media/image95.png
   :width: 0.15748in
   :height: 0.15748in
.. |image91| image:: ../media/image97.png
   :width: 0.1378in
   :height: 0.15748in
.. |image92| image:: ../media/image99.png
   :width: 0.1378in
   :height: 0.15748in
.. |image93| image:: ../media/image101.png
   :width: 0.1378in
   :height: 0.15748in
.. |image94| image:: ../media/image103.png
   :width: 0.1378in
   :height: 0.15748in
.. |image95| image:: ../media/image105.png
   :width: 6.49606in
   :height: 3.28346in
.. |image96| image:: ../media/image106.png
   :width: 6.49606in
   :height: 3.28346in
.. |image97| image:: ../media/image49.png
   :width: 0.15748in
   :height: 0.15748in
.. |image98| image:: ../media/image51.png
   :width: 6.49606in
   :height: 3.28346in
.. |image99| image:: ../media/image107.png
   :width: 6.49606in
   :height: 1.75197in
.. |image100| image:: ../media/image108.png
   :width: 6.49606in
   :height: 2.56693in
.. |image101| image:: ../media/image109.png
   :width: 6.49606in
   :height: 3.28346in
.. |image102| image:: ../media/image99.png
   :width: 0.1378in
   :height: 0.15748in
.. |image103| image:: ../media/image110.png
   :width: 0.10335in
   :height: 0.11811in
.. |image104| image:: ../media/image110.png
   :width: 0.10335in
   :height: 0.11811in
.. |image105| image:: ../media/image34.png
   :width: 0.15748in
   :height: 0.15748in
.. |image106| image:: ../media/image97.png
   :width: 0.1378in
   :height: 0.15748in
.. |image107| image:: ../media/image112.png
   :width: 6.49606in
   :height: 3.28346in
.. |image108| image:: ../media/image97.png
   :width: 0.1378in
   :height: 0.15748in
.. |image109| image:: ../media/image113.png
   :width: 6.49606in
   :height: 3.28346in
   