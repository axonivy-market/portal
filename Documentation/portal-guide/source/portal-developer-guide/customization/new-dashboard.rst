.. _customization-new-dashboard:

Portal dashboard
================

.. _customization-portal-dashboard-introduction:

Introduction
------------

Portal dashboard is a flexible page, user can add, remove and edit widgets filters.
Users can custom layout just by drag & drop in multiple dashboards.

More, there is an easy way for users to predefine a lot of details for each dashboard,
each widget such as predefined filters, columns, UI, and styles.

Available widgets of Portal dashboard

- Task widget

- Case widget

- Process widget

You can predefine Portal dashboards, and details of each widget
by configuring Global Variable **Portal.Dashboard**.

.. _customization-dashboards-customization:

Define your own dashboards
--------------------------

You can predefine dashboards id, title, permissions to access, and widgets
inside each of them.

Below is a JSON example for the configuration of dashboards.

.. code-block:: json

   [
      {
         "id": "portal-dashboard",
         "title": "cms:/dashboard/ITDepartmentDashboard",
         "permissions": ["IT_Engineer", "CIO", "#daniel"],
         "widgets": [
            {
               "type": "task",
               "id": "task_1",
               "layout": {
                  "x": 0, "y": 0, "w": 10, "h": 9,
                  "styleClass": "your-widget-class"
               }
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
               "id": "task_1",
               "layout": {
                  "x": 0, "y": 0, "w": 10, "h": 9
               }
            }
         ]
      },
   ]

..

Structure of JSON for each dashboard:

   ``id``: ID for identifying dashboard

   ``title``: title of dashboard. You can input a string as dashboard
   title, or can use CMS by using prefix ``cms:`` before your CMS URI
   to define dashboard title in multilingual

   ``permissions``: roles and users can access the dashboard. To define
   users, use the hashtag as a prefix of username, for example, ``#your_username``

   ``widgets``: definition of widgets dashboard. There are four types of
   widget ``task``, ``case``, ``process``, and ``custom``. Refer
   to the next sections to learn more about the configurations of each widget

Configure dashboard widgets
---------------------------

Below are details of JSON configuration for each widget of the Portal dashboard.
They will help you understand how to configure widget efficiency.

.. toctree::
   :maxdepth: 1

   new-dashboard-task-widget
   new-dashboard-case-widget
   new-dashboard-process-widget

.. tip:: 
   To quickly understand how the JSON of custom dashboard looks like.

   - Refer to ``variables.Portal.Dashboard.json`` file in ``portal-developer-examples/resources/files`` project.
   - Copy to the corresponding application folder located in the designer.

      - e.g: AxonIvyDesigner9.3.0/configuration/applications/designer

   - Start the process ``Start Processes/CreateTestData/CreateTestDataForCustomizedDashboard.ivp`` in ``portal-developer-examples`` project.
   - Open Portal new dashboard to check new custom layout.

   About how to configure Global Variable, refer to `Axon Ivy Global Variables <https://developer.axonivy.com/releases/ivy/9.1/documents/designer-guide/configuration/global-variables.html>`_
