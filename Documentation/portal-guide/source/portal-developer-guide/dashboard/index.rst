.. _customization-new-dashboard:

Portal dashboard
================

.. _customization-portal-dashboard-introduction:

Introduction
------------

Portal Dashboard is a flexible page, user can add, remove and edit widgets filters.
Users can custom layout just by drag & drop in multiple dashboards.

More, there is an easy way for users to predefine a lot of details for each dashboard,
each widget such as predefined filters, columns, UI, and styles.

Available widgets of Portal dashboard

- Task widget

- Case widget

- Process widget

- Statistic widget

- Custom widget

You can predefine Portal dashboards, and details of each widget
by configuring Global Variable **Portal.Dashboard**.

.. _customization-dashboards-customization:

Define your own dashboards
--------------------------

Portal support multiple dashboards, they will be displayed as tabs.

You can predefine dashboards id, title, permissions to access, and widgets
for each dashboard.

Below is a JSON example for the configuration of dashboards.

.. code-block:: html

   [
      {
         "id": "default-dashboard",
         "templateId": "template-id"
         "title": "cms:/dashboard/DefaultDashboard",
         "widgets": [
            {
               "type": "task",
               "id": "task_10",
               "layout": {
                  "x": 0, "y": 0, "w": 10, "h": 9,
                  "styleClass": "your-widget-class"
               }
            },
            {
               "type": "case",
               "id": "case_10",
               "layout": {
                  "x": 0, "y": 10, "w": 10, "h": 9
               }
            }
         ]
      },
      {
         "id": "general-dashboard",
         "title": "General Dashboard",
         "permissions": ["Employee", "CIO", "#daniel"],
         "widgets": [
            {
               "type": "task",
               "id": "task_20",
               "layout": {
                  "x": 0, "y": 0, "w": 10, "h": 9
               }
            }
         ]
      }
   ]

..

Structure of JSON for each dashboard:

   ``id``: ID to identifying dashboard.

   ``templateId``: the key to identify the predefined template this dashboard is referring to.

   ``title``: title of dashboard. You can input a string as dashboard
   title, or can use CMS by using prefix ``cms:`` before your CMS URI
   to define dashboard title in multilingual.

   ``permissions``: roles can access the dashboard.

   .. tip:: 
      If you don't define ``permissions`` for a dashboard, every user can see it.

   ``widgets``: definition of widgets dashboard. There are four types of
   widget ``task``, ``case``, ``process``, and ``custom``. Refer
   to the next sections to learn more about the configurations of each widget

   .. warning::
      Widget ``id`` must be unique between dashboards.

Configure dashboard widgets
---------------------------

Below are details of JSON configuration for each widget of the Portal dashboard.
They will help you understand how to configure widget efficiency.

.. toctree::
   :maxdepth: 1

   new-dashboard-task-widget
   new-dashboard-case-widget
   new-dashboard-process-widget
   new-dashboard-statistic-widget
   new-dashboard-custom-widget

.. tip:: 
   To quickly understand how the JSON of custom dashboard looks like.

   - Refer to ``variables.Portal.Dashboard.json`` file in ``portal-developer-examples/resources/files`` project.
   - Copy to the corresponding application folder located in the designer.

      - e.g: AxonIvyDesigner9.3.0/configuration/applications/designer

   - Start the process ``Start Processes/CreateTestData/CreateTestDataForCustomizedDashboard.ivp`` in ``portal-developer-examples`` project.
   - Open Portal new dashboard to check new custom layout.

   About how to configure Variables, refer to :dev-url:`Axon Ivy Variables </doc/nightly/designer-guide/configuration/variables.html>`

Configure dashboard templates
-----------------------------

Portal provides predefined dashboard templates to help users reduce time and complication when create dashboard.
Users can quickly create a dashboard just by input some information such as dashboard name, choose one of these
predefined templates and modify details to fit their needs.

These templates are configurable by modify the global variable ``DashboardTemplates.json``.

.. toctree::
   :maxdepth: 1

   new-dashboard-template