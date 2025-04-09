.. _customization-new-dashboard:

Portal Dashboard
================

.. _customization-portal-dashboard-introduction:

Introduction
------------

Portal Dashboard is a flexible page where you can add, remove and edit widgets and filters.
Users can customize the layout simply by dragging and dropping between multiple dashboards.

There is an easy way for users to predefine many details for each dashboard and widget, such as predefined filters, columns, UI elements, and styles.

Available widgets of Portal dashboard:

- Task widget
- Case widget
- Process widget
- Statistic widget
- Custom widget
- Process viewer widget
- Welcome widget
- News feed widget
- External page widget
- Notifications widget

You can predefine Portal dashboards, and details of each widget
by configuring variable **Portal.Dashboard**.

.. _customization-dashboards-customization:

Define Your Dashboard
---------------------

Portal allows for multiple dashboards. They will be displayed as tabs.

You can predefine dashboard id, title, permissions to access, and widgets
for each dashboard separately.

Below is a JSON example for configuring a dashboard with one task widget

.. code-block:: javascript

   [
      {
         "id": "1",
         "version": "12.0.0",
         "templateId": "default-portal-dashboard-template",
         "titles": [
            {
            "locale": "en",
            "value": "Dashboard"
            },
            {
            "locale": "fr",
            "value": "Tableau de bord"
            },
            {
            "locale": "de",
            "value": "Dashboard"
            },
            {
            "locale": "es",
            "value": "Tablero de mandos"
            }
         ],
         "icon": "fa-play",
         "widgets": [
            {
            "type": "task",
            "id": "task_18459642ba614d79bddea3f57e800bcf",
            "names": [
               {
                  "locale": "en",
                  "value": "Your Tasks"
               },
               {
                  "locale": "fr",
                  "value": "Your Tasks"
               },
               {
                  "locale": "de",
                  "value": "Your Tasks"
               },
               {
                  "locale": "es",
                  "value": "Your Tasks"
               }
            ],
            "layout": {
               "w": 12,
               "h": 5,
               "x": 0,
               "y": 5
            },
            "columns": [
               {
                  "field": "start"
               },
               {
                  "field": "priority"
               },
               {
                  "field": "id",
                  "quickSearch": false
               },
               {
                  "field": "name",
                  "quickSearch": true
               },
               {
                  "field": "description",
                  "quickSearch": true
               },
               {
                  "field": "activator",
                  "quickSearch": false
               },
               {
                  "field": "state"
               },
               {
                  "field": "startTimestamp"
               },
               {
                  "field": "expiryTimestamp"
               },
               {
                  "field": "category",
                  "quickSearch": false
               },
               {
                  "field": "application",
                  "styleClass": "dashboard-tasks__priority u-text-align-center widget-column",
                  "quickSearch": false
               },
               {
                  "field": "actions"
               }
            ]
            }
         ],
         "permissions": [
            "Everybody"
         ],
         "accessibility": false,
         "dashboardDisplayType": "sub_menu"
      }
   ]

..

Structure of the JSON for each dashboard:

   ``id``: ID to identifying dashboard.

   ``version``: version of dashboard

   ``templateId``: the key to identify the predefined template this dashboard is referring to.

   ``titles``: multilingual title of dashboard.

   ``icon``: dashboard icon.

   ``permissions``: roles can access the dashboard.

   ``dashboardDisplayType``: the display type of your dashboard in the left menu of Portal

   .. tip:: 
      If you don't define ``permissions`` for a dashboard, every user can see it.

   ``widgets``: definition of widgets dashboard. There are four types of
   widget: ``task``, ``case``, ``process``, and ``custom``. Refer
   to the next sections to learn more about the configurations of each widget.

   .. warning::
      Widget ``id`` must be unique between dashboards.

Configure Dashboard Widgets
---------------------------

Below are the details of the JSON configuration for each widget on the Portal
dashboard. They will help you understand how to configure the widgets
efficiently.

.. toctree::
   :maxdepth: 1

   new-dashboard-task-widget
   new-dashboard-case-widget
   new-dashboard-process-widget
   new-dashboard-statistic-widget
   new-dashboard-custom-widget
   new-dashboard-process-viewer-widget
   new-dashboard-welcome-widget
   dashboard-newsfeed-widget
   new-dashboard-external-page-widget
   new-dashboard-notification-widget
   new-dashboard-navigation-dashboard-widget

.. tip:: 
   To get an understanding of the JSON structure of the custom dashboard,

   - Refer to ``variables.Portal.Dashboard.json`` file in ``portal-developer-examples/resources/files`` project.
   - Copy to the corresponding application folder located in the designer.

      - e.g: AxonIvyDesigner12.0.0/configuration/applications/designer

   - Start the process ``Start Processes/CreateTestData/CreateTestDataForCustomizedDashboard.ivp`` in the ``portal-developer-examples`` project.
   - Open the new Portal dashboard to check the new custom layout.

   To configure variables, refer to :dev-url:`|ivy| Variables </doc/|version|/designer-guide/configuration/variables.html>`

Configure Dashboard Templates
-----------------------------

Portal provides predefined dashboard templates to help users reduce time and complication when creating a dashboard.
Users can quickly create a dashboard just by entering a few items such as dashboard name, choosing one of the
predefined templates, and modifying details to fit their needs.

These templates are configurable using variable ``DashboardTemplates.json``.

.. toctree::
   :maxdepth: 1

   new-dashboard-template

Configure Main Menu Entry Dashboard
-----------------------------------
Portal supports customizing the main menu entry of dashboards.

To customize the menu, you can edit the Portal variable
``Portal.Dashboard.MainMenuEntry.json``. Below is an example JSON of a custom menu.

.. code-block:: javascript

      {
         "names": [
               {
                  "locale": "en",
                  "value": "Dashboard EN"
               },
               {
                  "locale": "fr",
                  "value": "Dashboard FR"
               },
               {
                  "locale": "de",
                  "value": "Dashboard DE"
               },
               {
                  "locale": "es",
                  "value": "Dashboard ES"
               }
            ],
        "icon": "si si-layout-bullets"
      }

..
   
Structure of the JSON:

   ``names``: Multilingual name of the menu entry.

   .. important:: 
         The displayed name will follow the language setting of the account.

   ``icon``: Icon of the menu entry. Portal supports both Streamline and FontAwesome icons.