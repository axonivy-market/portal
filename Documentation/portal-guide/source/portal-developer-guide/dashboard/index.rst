.. _customization-new-dashboard:

Portal Dashboard
================

Create and customize flexible dashboards with drag-and-drop widgets, filters, and multilingual support.

Overview
--------

.. table:: Dashboard Capabilities

   +----------------------------------------+----------------------------------------------------------------+
   | Capability                             | Description                                                    |
   +========================================+================================================================+
   | **Multiple Dashboards**                | Create unlimited dashboards displayed as tabs                  |
   +----------------------------------------+----------------------------------------------------------------+
   | **10 Widget Types**                    | Task, Case, Process, Statistic, Custom, and more               |
   +----------------------------------------+----------------------------------------------------------------+
   | **Drag-and-Drop Layout**               | Users can customize widget positions and sizes                 |
   +----------------------------------------+----------------------------------------------------------------+
   | **Permission-Based Access**            | Control dashboard visibility by roles and users                |
   +----------------------------------------+----------------------------------------------------------------+
   | **Predefined Templates**               | Quick dashboard creation from templates                        |
   +----------------------------------------+----------------------------------------------------------------+
   | **Multilingual Support**               | Define dashboard and widget names in multiple languages        |
   +----------------------------------------+----------------------------------------------------------------+
   | **Widget Filters**                     | Predefined filters for tasks, cases, and processes             |
   +----------------------------------------+----------------------------------------------------------------+
   | **Custom Styling**                     | Configure columns, UI elements, and styles                     |
   +----------------------------------------+----------------------------------------------------------------+

.. _customization-portal-dashboard-introduction:

Introduction
------------

Portal Dashboard is a flexible, customizable page where users can view and manage their work through configurable widgets. Dashboards support:

**Key Features:**

- **Flexible Layout** - Drag-and-drop widgets to customize dashboard appearance
- **Multiple Dashboards** - Create unlimited dashboards displayed as tabs
- **Rich Widget Library** - 10 widget types covering different Portal aspects
- **User Personalization** - Each user can customize their dashboard layout
- **Predefined Configuration** - Administrators can configure default dashboards

**Available Dashboard Widgets:**

.. table:: Widget Types

   +---------------------------+---------------------------------------------------------------+
   | Widget Type               | Description                                                   |
   +===========================+===============================================================+
   | **Task Widget**           | Display and manage tasks with filters and actions             |
   +---------------------------+---------------------------------------------------------------+
   | **Case Widget**           | View and track cases with customizable columns                |
   +---------------------------+---------------------------------------------------------------+
   | **Process Widget**        | Show available processes for starting workflows               |
   +---------------------------+---------------------------------------------------------------+
   | **Statistic Widget**      | Display charts and statistics for analytics                   |
   +---------------------------+---------------------------------------------------------------+
   | **Custom Widget**         | Embed custom content or external applications                 |
   +---------------------------+---------------------------------------------------------------+
   | **Process Viewer Widget** | Visualize process flows and BPMN diagrams                     |
   +---------------------------+---------------------------------------------------------------+
   | **Welcome Widget**        | Show welcome messages and quick links                         |
   +---------------------------+---------------------------------------------------------------+
   | **News Feed Widget**      | Display announcements and news items                          |
   +---------------------------+---------------------------------------------------------------+
   | **External Page Widget**  | Embed external websites or web applications                   |
   +---------------------------+---------------------------------------------------------------+
   | **Notifications Widget**  | Show system notifications and alerts                          |
   +---------------------------+---------------------------------------------------------------+

**Configuration:**

Dashboards are configured using the **Portal.Dashboard** variable. This allows you to predefine:

- Dashboard identifiers and versions
- Multilingual titles and icons
- Access permissions by role or user
- Widget configurations and layouts
- Default filters and columns

.. _customization-dashboards-customization:

Define Your Dashboard
---------------------

Portal supports multiple dashboards displayed as tabs. Each dashboard can have different widgets, permissions, and configurations.

**HowTo: Configure Custom Dashboards**

#. Navigate to Engine Cockpit > Configuration > Variables
#. Find or create the **Portal.Dashboard** variable
#. Define your dashboard configuration using JSON (see example below)
#. Save the configuration
#. Dashboards appear as tabs in Portal

**Configuration Example:**

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
                     "locale": "de",
                     "value": "Ihre Aufgaben"
                  }
               ],
               "layout": {
                  "w": 12,
                  "h": 5,
                  "x": 0,
                  "y": 0
               },
               "columns": [
                  {"field": "start"},
                  {"field": "priority"},
                  {"field": "id", "quickSearch": false},
                  {"field": "name", "quickSearch": true},
                  {"field": "description", "quickSearch": true},
                  {"field": "state"},
                  {"field": "actions"}
               ]
            }
         ],
         "permissions": ["Everybody"],
         "accessibility": false
      }
   ]

..

JSON Configuration Reference
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Each dashboard in the JSON array supports the following properties:

**Dashboard Properties:**

    ``id`` (string, required)
        Unique identifier for the dashboard
        
        - Must be unique across all dashboards
        - Used for internal tracking and configuration

    ``version`` (string, required)
        Portal version for compatibility
        
        - Current version: ``"12.0.0"``
        - Used for migration and compatibility checks

    ``templateId`` (string, optional)
        Reference to predefined dashboard template
        
        - Example: ``"default-portal-dashboard-template"``
        - See :ref:`Dashboard Templates <customization-dashboard-templates>` section

    ``titles`` (array, required)
        Multilingual dashboard tab labels
        
        - Each entry contains ``locale`` and ``value``
        - Portal displays title matching user's language
        - Example languages: en, de, fr, es, it, ja, zh

    ``icon`` (string, optional)
        Dashboard tab icon
        
        - Supports FontAwesome (e.g., ``"fa-play"``, ``"fa-dashboard"``)
        - Supports Streamline (e.g., ``"si si-layout-bullets"``)

    ``widgets`` (array, required)
        Array of widget configurations for the dashboard
        
        - See widget configuration sections below
        - Each widget must have unique ``id``
        - Widgets positioned using ``layout`` property

    ``permissions`` (array, optional)
        Roles or users who can access this dashboard
        
        - Roles: ``["Everybody", "Employee", "Manager"]``
        - Users: Prefix with ``#`` (e.g., ``["#john.doe"]``)
        - Default: If omitted, all users can see the dashboard

    ``accessibility`` (boolean, optional)
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
   **Widget ID Uniqueness:**
   
   Widget ``id`` must be unique across ALL dashboards. Duplicate IDs will cause configuration errors.
   
   - Use descriptive prefixes: ``task_{unique-id}``, ``case_{unique-id}``
   - Consider using UUIDs or timestamps for uniqueness
   - Test configuration after adding new widgets

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

Dashboard templates allow users to quickly create new dashboards from predefined configurations. Instead of configuring everything from scratch, users select a template and customize only what they need.

**Benefits of Templates:**

- **Fast Dashboard Creation** - Users fill in minimal information (name, permissions)
- **Consistent Design** - Ensure dashboards follow organizational standards
- **Reduced Complexity** - Simplify configuration for non-technical users
- **Best Practices** - Embed proven widget layouts and configurations

**Configuration:**

Templates are configured using the **Portal.DashboardTemplates** variable.

.. toctree::
   :maxdepth: 1

   new-dashboard-template

.. note::
   Template configuration is optional. Portal provides default templates out of the box.

Configure Main Menu Entry Dashboard
-----------------------------------

Customize how the Dashboard appears in Portal's main navigation menu by configuring its label and icon.

**HowTo: Customize Dashboard Menu Entry**

#. Navigate to Engine Cockpit > Configuration > Variables
#. Find or create the **Portal.Dashboard.MainMenuEntry** variable
#. Configure menu entry using JSON (see example below)
#. Save the configuration
#. Dashboard menu entry updates immediately

**Configuration Example:**

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

    ``names`` (array, required)
        Multilingual menu entry labels
        
        - Each entry contains ``locale`` (language code) and ``value`` (translated text)
        - Portal displays name matching user's language preference
        - Falls back to first entry if user's locale not found

    ``icon`` (string, required)
        Icon displayed next to menu label
        
        - **Streamline Icons**: ``si si-icon-name`` (e.g., ``"si si-layout-bullets"``)
        - **FontAwesome Icons**: ``fa fa-icon-name`` (e.g., ``"fa fa-dashboard"``)

.. important::
   **Language Display:**
   
   The menu entry name follows the user's language setting configured in their profile. If no matching locale is found, the first entry in the ``names`` array is used as the default.