.. _axonivyportal.components.drilldown:

Change Last Drilldown Level Of Task By Expiry Chart
===================================================

.. _axonivyportal.components.expirychart.taskbyexpirychart:

Task by expiry chart
--------------------

|image0|

.. _axonivyportal.components.expirychart.foruserswhowanttochangelastdrilldownlevel:

For users who want to change last drilldown level of Task by expiry chart
-------------------------------------------------------------------------

By default, the last drilldown level of a expiry chart is HOUR. It means
that, to open a related tasks list of this chart, users must navigate
from YEAR -> MONTH -> WEEK -> DAY -> HOUR, clicking on a column of HOUR
chart to open the tasks.

To change this last drilldown level, users can set the value of
``EXPIRY_CHART_LAST_DRILLDOWN_LEVEL`` in ``Global settings``.

For example, to navigate to task list immediately when clicking on a
week column, set ``EXPIRY_CHART_LAST_DRILLDOWN_LEVEL = WEEK``:

|image1|

.. |image0| image:: images/TaskByExpiryChart/task-by-expiry-chart.png
.. |image1| image:: images/TaskByExpiryChart/related-tasks-of-expiry-chart.png

