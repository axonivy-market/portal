.. _customization-new-dashboard:

Portal dashboard
================

.. _customization-portal-dashboard-introduction:

Introduction
------------

Portal dashboard is a flexible page which user can add, remove
and edit widgets filters and UI by using friendly drag and drop
approach in multiple dashboards.
More, Portal dashboard also give customer an easy way to predefine
a lot of details for each dashboard, each widget such as
predefined filters, columns, UI and styles.

Available widgets of Portal dashboard

- Task widget

- Case widget

- Process widget

You can predefine Portal dashboards, and details of each widgets
by configure global variable **Portal.Dashboard**.

.. _customization-dashboards-customization:

Define your own dashboards
--------------------------

You can predefine dashboards id, title, permissions to access, and widgets
inside each of them.

Below is JSON example for configuration of dashboards.

.. code-block:: html

   [
      {
         "id": "portal-dashboard",
         "title": "cms:/dashboard/ITDepartmentDashboard",
         "permissions": ["IT_Engineer", "CIO", "#daniel"],
         "widgets": [
            {
               "type": "task",
               "id": "task_1",
               "axisX": 0,
               "axisY": 0,
               "width": 10,
               "height": 9
            }
         ]
      },
      {
         "id": "2",
         "title": "General Dashboard",
         "permissions": ["Employee"],
         "widgets": [
            {
               "type": "task",
               "id": "task_2",
               "axisX": 0,
               "axisY": 0,
               "width": 10,
               "height": 9
            }
         ]
      },
   ]

..

Structure of JSON for each dashboard:

   ``id``: ID for identify dashboard

   ``title``: title of dashboard. You can input a string as dashboard
   title, or can use CMS by using prefix ``cms:`` before your CMS URI
   to define dashboard title in multi languages

   ``permissions``: roles and users can access the dashboard. To define
   users, please use hashtag as a prefix of username, for example ``#your_username``

   ``widgets``: definition of widgets dashboard. There are four types of
   widget ``task``, ``case``, ``process``, and ``custom``. Please refer
   to next sections to learn more about configurations of each widget

Configure dashboard widgets
---------------------------

Below are details of JSON configuration for each widgets of dashboard.
They will help you understand how to configure widgets efficiency.

.. toctree::
   :maxdepth: 1

   new-dashboard-task-widget
   new-dashboard-case-widget
   new-dashboard-process-widget