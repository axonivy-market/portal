.. _customization-new-dashboard:

Portal Dashboard
================

Configure custom dashboards using the **Portal.Dashboard** variable. Dashboards support multiple widget types, permission-based access, and multilingual configuration.

.. _customization-dashboards-customization:

Configuration
-------------

Below is a comprehensive JSON example configuring a dashboard with a task widget:

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
                 "y": 0
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
                    "field": "state"
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
         "accessibility": false
      }
   ]

..

JSON Configuration Reference
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Each dashboard in the JSON array supports the following properties:

**Dashboard Properties:**

    ``id`` (string)
        Unique identifier for the dashboard
        
        - Must be unique across all dashboards
        - Used for internal tracking and configuration

    ``version`` (string)
        Portal version for compatibility
        
        - Current version: ``"12.0.0"``
        - Used for migration and compatibility checks

    ``templateId`` (string)
        Reference to predefined dashboard template
        
        - Example: ``"default-portal-dashboard-template"``
        - See :ref:`Dashboard Templates <customization-dashboard-templates>` section

    ``titles`` (array)
        Multilingual dashboard tab labels
        
        - Each entry contains ``locale`` and ``value``
        - Portal displays title matching user's language
        - Example languages: en, de, fr, es, it, ja, zh

    ``icon`` (string)
        Dashboard tab icon
        
        - Supports FontAwesome (e.g., ``"fa-play"``, ``"fa-dashboard"``)
        - Supports Streamline (e.g., ``"si si-layout-bullets"``)

    ``widgets`` (array)
        Array of widget configurations for the dashboard
        
        - See widget configuration sections below
        - Each widget must have unique ``id``
        - Widgets positioned using ``layout`` property

    ``permissions`` (array)
        Roles or users who can access this dashboard
        
        - Roles: ``["Everybody", "Employee", "Manager"]``
        - Users: Prefix with ``#`` (e.g., ``["#john.doe"]``)
        - Default: If omitted, all users can see the dashboard

    ``accessibility`` (boolean)
        Enable accessibility features for the dashboard
        
        - Default: ``false``
        - Set to ``true`` for enhanced screen reader support

.. tip::
   **Dashboard Permission Best Practices:**
   
   - Use ``"Everybody"`` for public dashboards all users should see
   - Create role-specific dashboards (e.g., ``["Manager"]`` for management dashboard)
   - Combine roles for shared access (e.g., ``["Employee", "Contractor"]``)
   - Omit permissions to make dashboard visible to all users

.. warning::
   Widget ``id`` must be unique across ALL dashboards. Use descriptive prefixes like ``task_{unique-id}`` or UUIDs.

Configure Dashboard Widgets
---------------------------

Each widget type has specific configuration options for customization. Click on a widget type below to learn about its configuration:

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

.. tip::
   **Quick Start with Examples:**
   
   To understand the JSON structure of custom dashboards:
   
   #. Open ``portal-developer-examples`` project
   #. Navigate to ``resources/files/variables.Portal.Dashboard.json``
   #. Copy this file to Designer configuration:
      
      - Location: ``AxonIvyDesigner12.0.0/configuration/applications/designer``
   
   #. Run the test data creation process:
      
      - ``Start Processes/CreateTestData/CreateTestDataForCustomizedDashboard.ivp``
   
   #. Open Portal to see the customized dashboard with example widgets
   
   For variable configuration details, see :dev-url:`Axon Ivy Variables </doc/12.0/designer-guide/configuration/variables.html>`

.. _customization-dashboard-templates:

Configure Dashboard Templates
-----------------------------
- **Fast Dashboard Creation** - Users fill in minimal information (name, permissions)
- **Consistent Design** - Ensure dashboards follow organizational standards
- **Reduced Complexity** - Simplify configuration for non-technical users
- **Best Practices** - Embed proven widget layouts and configurations

Configure dashboard templates using the **Portal.DashboardTemplates** variable to provide predefined configurations for users.

.. toctree::
   :maxdepth: 1

   new-dashboard-template

.. note::
   Template configuration is optional. Portal provides default templates out of the box.

Configure Dashboard Main Menu Item
----------------------------------

Customize the Dashboard menu item using the **Portal.Dashboard.MainMenuEntry** variable.

.. code-block:: javascript

   {
      "names": [
         {
            "locale": "en",
            "value": "Dashboards"
         },
         {
            "locale": "fr",
            "value": "Tableaux de bord"
         },
         {
            "locale": "de",
            "value": "Dashboards"
         },
         {
            "locale": "es",
            "value": "Tableros"
         }
      ],
      "icon": "si si-layout-bullets"
   }

..

**Configuration Properties:**

    ``names`` (array)
        Multilingual menu entry labels
        
        - Each entry contains ``locale`` (language code) and ``value`` (translated text)
        - Portal displays name matching user's language preference
        - Falls back to first entry if user's locale not found

    ``icon`` (string)
        Icon displayed next to menu label
        
        - **Streamline Icons**: ``si si-icon-name`` (e.g., ``"si si-layout-bullets"``)
        - **FontAwesome Icons**: ``fa fa-icon-name`` (e.g., ``"fa fa-dashboard"``)

.. important::
   The menu entry name follows the user's language setting. If no matching locale is found, the first entry in the ``names`` array is used as default.
