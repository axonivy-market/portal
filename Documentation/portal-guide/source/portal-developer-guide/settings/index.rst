.. _settings:

.. raw:: html

    <style>
      .wy-nav-content {
         max-width: 1350px;
      }
    </style>

Settings
********

This guide covers Portal configuration including roles, permissions, variables, and integrations. Settings control global Portal behavior across dashboards, widgets, and administrative features.
Proper configuration ensures Portal aligns with your organization's security policies, business processes, and user experience requirements.

**What Settings Control:**

- User interface language and translations
- Security roles and permissions for Portal features
- Global variables affecting Portal behavior
- Document upload restrictions and virus scanning
- External integrations (announcements, third-party apps, external links)
- Statistical chart configurations for dashboards

**Related Sections:**

- :ref:`Permissions <list-permissions>` - Detailed permission reference
- :ref:`Dashboard Configuration <customization-new-dashboard>` - Configure dashboards
- :ref:`Customization <customization>` - Extend Portal functionality
- :ref:`Deployment <deployment>` - Production deployment settings

|portal-header|

.. _settings-admin-settings:

Portal Settings Configuration
=============================

Portal settings control global behavior and can be configured through multiple methods:

**Configuration Methods:**

- **Admin Settings UI**: See :ref:`update-portal-settings` for user-friendly configuration
- **Variables File**: Update ``portal/config/variables.yaml`` for development (survives Designer restarts)
- **Engine Cockpit**: Configure via :doc-url:`Variables </designer-guide/configuration/variables.html>`

.. tip::
   In development, configure settings in ``portal/config/variables.yaml`` to avoid re-configuring after each Designer restart.

.. _settings-language:

Language Settings
=================

HowTo: Add a New Language to Portal
------------------------------------

#. **Export CMS Entries**

   Export all CMS entries from Portal to an Excel file

#. **Add Language Column**

   - Open the exported Excel file
   - Add a new column at the end with the language locale code (e.g., ``it`` for Italian)
   - Refer to `Java supported locales <https://www.oracle.com/java/technologies/javase/jdk21-suported-locales.html>`_ for supported locale codes
   
   |export-cms|

#. **Translate CMS Entries**

   - For each row in the Excel file, add the translated text in the new language column
   - Base your translations on the existing English (``en``) or German (``de``) values
   - Ensure all cells in the new language column are filled with appropriate translations

#. **Update JSON Configuration Files**

   For each JSON file in ``portal/config/variables/``:
   
   - Open the file and locate the ``locale`` arrays
   - Add a new locale/value pair for your language following the existing pattern
   - Example: ``{"locale": "it", "value": "Translated text"}``
   
   |translate-json|

#. **Update Java Source Files**

   In ``DefaultDashboardUtils.java``:
   
   - Add new locale/value entries for your language into default dashboard constants.
   
   |translate-java|

#. **Import and Deploy**

   - Import the Excel file back into Portal
   - Redeploy Portal

Role Configuration
==================

Portal uses specific roles to control administrative access:

.. table::
   :widths: 30 70

   +---------------------------+----------------------------------------------------------------+
   | Role                      | Description                                                    |
   +===========================+================================================================+
   | **AXONIVY_PORTAL_ADMIN**  | Can access Portal Admin page, configure internal role          |
   |                           | properties, and create public filters.                         |
   +---------------------------+----------------------------------------------------------------+


.. _settings-permission-settings:

Permission Settings
===================

Configure permissions in the :doc-url:`Engine Cockpit </engine-guide/reference/engine-cockpit/security.html>`. All Portal permissions are grouped under "PortalPermissions".

.. important::
   **Portal Permission Support:**
   
   Portal is built as a layer above the Axon Ivy Engine core. Not every core engine permission is automatically used or supported by Portal. Supporting every engine permission would require significant effort and increase maintenance overhead.
   If you require a specific engine permission that is not currently supported by Portal, please contact Axon Ivy support to discuss your requirements.

.. note::
   **General Permission Rules:**
   
   - Normal users can only see tasks and cases they can work on
   - Administrators with :ref:`🔑TaskReadAll <TaskReadAll>` and :ref:`🔑CaseReadAll <CaseReadAll>` can see all tasks/cases
   - Administrators can interact with, create, update, and delete all workflows
   - Normal users can only update/delete workflows they created and work on assigned tasks

Task Permissions
----------------

**Add Note**

User needs permission: :ref:`🔑TaskCaseAddNote <TaskCaseAddNote>`

**Delegate**

Required permissions:

- To see delegate action: :ref:`🔑TaskDisplayDelegateAction <TaskDisplayDelegateAction>` and :ref:`🔑TaskWriteActivator <TaskWriteActivator>`
- To delegate personal/group tasks: :ref:`🔑TaskWriteActivatorOwnTasks <TaskWriteActivatorOwnTasks>` (not assigned to Everybody by default)
- Case owner delegation: :ref:`🔑CaseOwnerTaskDelegate <CaseOwnerTaskDelegate>` allows delegating all related tasks within their case

.. important::
   Only tasks ready for user processing can be delegated. Task state cannot be: CREATED, DONE, DESTROYED, RESUMED, FAILED

**Reset**

Required permissions:

- To see reset action: :ref:`🔑TaskDisplayResetAction <TaskDisplayResetAction>`
- To reset tasks: :ref:`🔑TaskResetOwnWorkingTask <TaskResetOwnWorkingTask>` or :ref:`🔑TaskResetReadyForJoin <TaskResetReadyForJoin>` or :ref:`🔑TaskReset <TaskReset>`

.. important::
   Only works for tasks in states: RESUMED, PARKED, READY_FOR_JOIN, FAILED

**Delete**

User needs permission: :ref:`🔑TaskDestroy <TaskDestroy>`

.. important::
   Only works if task state is not DESTROYED or DONE

**Reserve**

Required permissions:

- To see reserve action: :ref:`🔑TaskDisplayReserveAction <TaskDisplayReserveAction>`
- To reserve a task: :ref:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>`

.. important::
   Only possible if task is in states: CREATED, RESUMED, SUSPENDED

**Change Description**

User needs permission: :ref:`🔑TaskWriteDescription <TaskWriteDescription>`

.. important::
   Terminated tasks cannot be changed. Task state cannot be: DONE, DESTROYED, FAILED

**Change Deadline**

User needs permission: :ref:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`

.. important::
   Task cannot be in states: DONE, DESTROYED, FAILED

**Change Priority**

User needs permission: :ref:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`

.. important::
   Task cannot be in states: DONE, DESTROYED, FAILED

**Display Additional Options**

User needs permission: :ref:`🔑TaskDisplayAdditionalOptions <TaskDisplayAdditionalOptions>`

**Display Workflow Events**

User needs permission: :ref:`🔑TaskDisplayWorkflowEventAction <TaskDisplayWorkflowEventAction>`

**Display Custom Fields**

User needs permission: :ref:`🔑TaskDisplayCustomFieldsAction <TaskDisplayCustomFieldsAction>`

**Share Task Details Link**

User needs permission: :ref:`🔑ShareTaskDetailsLink <ShareTaskDetailsLink>`

**Change Expiry Activator**

User needs permission: :ref:`🔑TaskWriteExpiryActivator <TaskWriteExpiryActivator>`

.. important::
   Task cannot be in states: DONE, DESTROYED, FAILED

**Read System Tasks**

User needs permission: :ref:`🔑SystemTaskReadAll <SystemTaskReadAll>`

Case Permissions
----------------

**Add Note**

User needs permission: :ref:`🔑TaskCaseAddNote <TaskCaseAddNote>`

**Delete**

User needs permission: :ref:`🔑CaseDestroy <CaseDestroy>`

.. important::
   Case state must be RUNNING

**Change Description**

User needs permission: :ref:`🔑CaseWriteDescription <CaseWriteDescription>`

.. important::
   Case state cannot be DESTROYED

**See Related Tasks of Case**

User needs permission: :ref:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>` or :ref:`🔑TaskReadAll <TaskReadAll>`

.. important::
   Case state cannot be DESTROYED

**Display Show Detail Link**

User needs permission: :ref:`🔑ShowCaseDetails <ShowCaseDetails>` (not assigned to Everybody by default)

**Share Case Details Link**

User needs permission: :ref:`🔑ShareCaseDetailsLink <ShareCaseDetailsLink>`

**Display Custom Fields**

User needs permission: :ref:`🔑CaseDisplayCustomFieldsAction <CaseDisplayCustomFieldsAction>`

.. _settings-permission-settings-others:

Other Permissions
-----------------

.. table::

 +-----------+---------------------------------+---------------------------------------------------------------------------+
 |           | Action                          | Permission required                                                       |
 +===========+=================================+===========================================================================+
 | Absence   | Read                            | :ref:`🔑UserReadOwnAbsences <UserReadOwnAbsences>`  or                    |
 |           |                                 | :ref:`🔑UserReadAbsences <UserReadAbsences>`                              |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Create, edit                    | :ref:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>` or                   |
 |           |                                 | :ref:`🔑UserCreateAbsence <UserCreateAbsence>`                            |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Delete                          | :ref:`🔑UserDeleteOwnAbsence <UserDeleteOwnAbsence>` or                   |
 |           |                                 | :ref:`🔑UserDeleteAbsence <UserDeleteAbsence>`                            |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Read absences of all users      | :ref:`🔑UserReadAbsences <UserReadAbsences>`                              |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Delete absences of all users    | :ref:`🔑UserDeleteAbsence <UserDeleteAbsence>`                            |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Create new absence for all users| :ref:`🔑UserCreateAbsence <UserCreateAbsence>`                            |
 +-----------+---------------------------------+---------------------------------------------------------------------------+
 | Substitute| Manage substitute               | :ref:`🔑UserCreateSubstitute <UserCreateSubstitute>` and                  |
 |           |                                 | :ref:`🔑UserReadSubstitutes <UserReadSubstitutes>`                        |
 +-----------+---------------------------------+---------------------------------------------------------------------------+
 | Document  | Upload, delete                  | :ref:`🔑DocumentWrite <DocumentWrite>`                                    |
 |           |                                 | :ref:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>`        |
 +-----------+---------------------------------+---------------------------------------------------------------------------+
 | Portal    | Access to full process          | :ref:`🔑AccessFullProcessList <AccessFullProcessList>`                    |
 | permission| list, it's "Processes" on the   |                                                                           |
 |           | left menu and link "Show all    |                                                                           |
 |           | processes" on Dashboard         |                                                                           |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Add note to task/case           | :ref:`🔑TaskCaseAddNote <TaskCaseAddNote>`                                |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Display show more note          | :ref:`🔑TaskCaseShowMoreNote <TaskCaseShowMoreNote>`                      |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Create public external link, all| :ref:`🔑CreatePublicExternalLink <CreatePublicExternalLink>`              |
 |           | other users can see that link in|                                                                           |
 |           | the full process list           |                                                                           |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Dashboard sharing               | :ref:`🔑ShareDashboardLink <ShareDashboardLink>`                          |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Modify notification channels    | :ref:`🔑NotificationChannelsSetting <NotificationChannelsSetting>`        |
 |           | preferences in :ref:`my-profile`|                                                                           |
 |           | page                            |                                                                           |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Role management (Admin Settings)| :ref:`🔑RoleManagement <RoleManagement>`                                  |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | News management (Dashboard)     | :ref:`🔑NewsManagement <NewsManagement>`                                  |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Password validation settings    | :ref:`🔑PasswordValidation <PasswordValidation>`                          |
 |           +---------------------------------+---------------------------------------------------------------------------+
 |           | Read all notes on tasks/cases   | :ref:`🔑NoteReadAllCaseTaskDetails <NoteReadAllCaseTaskDetails>`          |
 +-----------+---------------------------------+---------------------------------------------------------------------------+
.. _settings-virus-scanning-setting:

Virus Scanning Settings
=======================

PrimeFaces is delivered with one implementation of the interface that uses
`VirusTotal <https://www.virustotal.com/>`_. To enable `VirusTotal
<https://www.virustotal.com/>`_ you need to create a community account at the
`VirusTotal website <https://www.virustotal.com/>`_. You receive an API key once
you have an account. To configure the API key add the following snippet to the
configuration/web.xml file:

   .. code-block:: xml

      <context-param>
      <param-name>primefaces.virusscan.VIRUSTOTAL_KEY</param-name>
      <param-value>PUT YOUR API KEY HERE</param-value>
      </context-param>

   ..

By default, after you configured the context-param in the web XML file, the
Virus Scanning is enabled. You can change the variable ``EnableVirusScanner`` to
``false`` in ``portal/config/variables.yaml`` if you want to disable virus scanning.

Reference: `How to check if uploaded files contain a virus
<https://community.axonivy.com/d/144-how-to-check-if-a-uploaded-files-contain-a-virus/>`_.

.. warning::
   Files that are checked for viruses are uploaded to VirusTotal. If you may
   not store the data of your application on servers outside the internal
   network or a given country, you might want to refrain from using this solution.

Variables
=========

These variables are stored as key-value pairs. They have to be edited in the Engine Cockpit.

.. table::

   +---------------------------------------------+-------------------------------+-----------------------------+
   | Variable                                    | Default                       | Description                 |
   |                                             | value                         |                             |
   +=============================================+===============================+=============================+
   | PortalStartTimeCleanObsoletedDataExpression | 0 6 \* \* \*                  | Cron expression defines     |
   |                                             |                               | the time to clean up data   |
   |                                             |                               | of obsolete users. E.g.:    |
   |                                             |                               | expression for at 6AM       |
   |                                             |                               | every day is                |
   |                                             |                               | ``0 6 * * *``. Refer to     |
   |                                             |                               | `CRON Expression <https://  |
   |                                             |                               | developer.axonivy.com/doc/  |
   |                                             |                               | 11.2/engine-guide/configur  |
   |                                             |                               | ation/advanced-configurati  |
   |                                             |                               | on.html#cron-expression>`_. |
   |                                             |                               | Restart Ivy engine after    |
   |                                             |                               | changing this variable.     |
   +---------------------------------------------+-------------------------------+-----------------------------+
   | PortalDeleteAllFinishedHiddenCases          | false                         | If set to ``true``, the     |
   |                                             |                               | above cron job runs daily   |
   |                                             |                               | and removes all finished    |
   |                                             |                               | hidden cases on the engine. |
   |                                             |                               |                             |
   |                                             |                               | Otherwise, just cases which |
   |                                             |                               | were generated by this      |
   |                                             |                               | Portal will be deleted.     |
   +---------------------------------------------+-------------------------------+-----------------------------+
   | PortalHiddenTaskCaseExcluded                | true                          | By default, Portal will     |
   |                                             |                               | query tasks and cases       |
   |                                             |                               | which are not hidden. If    |
   |                                             |                               | set to ``false``, Portal    |
   |                                             |                               | will ignore this property.  |
   +---------------------------------------------+-------------------------------+-----------------------------+
   | PortalLoginPageDisplay                      | true                          |By default, Portal will      |
   |                                             |                               |redirect to Login Page if    |
   |                                             |                               |login is required and the    |
   |                                             |                               |user is unknown.             |
   |                                             |                               |Set to ``false`` to          |
   |                                             |                               |redirect to the login error  |
   |                                             |                               |page and hide Logout in the  |
   |                                             |                               |User menu (when you use      |
   |                                             |                               |external authentication and  |
   |                                             |                               |the user is not present in   |
   |                                             |                               |your application user list.) |
   +---------------------------------------------+-------------------------------+-----------------------------+


Configuration
-------------

These variables are stored in JSON format. You can edit them in the cockpit, or
use the UI on the Portal Admin settings.


Portal Announcement
^^^^^^^^^^^^^^^^^^^
The standard announcement for Portal is intended to be used for general
information (e.g. Downtime, Changes, etc.). This message can be seen by all
Portal users.

Filename: ``variables.Portal.Announcement.json``

Data model:

.. code-block:: javascript

   {
       "contents": [
           {
               "language": "en",
               "value": "The announcement content in English"
           }
       ],
       "enabled": false
   }

-  ``contents``: list of supported languages and content for each language.

   -  ``language``: the language code such as ``en``, ``de``, ``es``, and ``fr``
   -  ``value``: the announcement content of that language

-  ``enabled``: the status of the announcement, true shows the announcement


Third Party Applications Linked Into Portal
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
You can define your custom menu item in the following JSON file. It will be included in
the left menu.

Filename: ``variables.Portal.ThirdPartyApplications.json``

Data model:

.. code-block:: javascript

   [
      {
         "id": "284352a58c7a48a2b64be8a946857c7a",
         "displayName": "{\"de\":\"AxonIvy ger\",\"en\":\"AxonIvy\"}",
         "menuIcon": "fa-group",
         "menuOrdinal": 1,
         "name": "{\"de\":\"AxonIvy ger\",\"en\":\"AxonIvy\"}",
         "link": "https://developer.axonivy.com/download"
      }
   ]

- ``id``: the identification number of a third-party application. It is an
  auto-generated UUID.
- ``displayName``: the display name of the app that is shown in the left menu.
  Supports multi-language.
- ``menuIcon``: the style class of the app icon that shows in the left menu.
- ``menuOrdinal``: index of the app. Used to sort menu items in the left menu.
- ``name``: the name of third-party app.
- ``link``: the URL of third-party app.

.. _portal-statistic-charts:

Portal Statistic Charts
^^^^^^^^^^^^^^^^^^^^^^^
You can define filter logic, appearance, and other settings for all statistic
charts that can be used by the :ref:`Statistic widget <portal-statistic-widget>` of Portal dashboard.

Filename: ``variables.Portal.CustomStatistic.json``

Data model:

.. code-block:: javascript

  {
    "id": "3e188e86a6534324b864167320ef3831",
    "version": "13.1.0",
    "statisticAggregation": {
      "field": "state",
      "type": "standard",
      "kpiField": "InvoiceTotalAmount",
      "aggregationMethod": "avg"
    },
    "filters": [
      {
        "field": "state",
        "values": [
          "DONE",
          "IN_PROGRESS",
          "OPEN"
        ],
        "operator": "in",
        "type": "standard"
      }
    ],
    "permissions": [
      "Everybody"
    ],
    "chartTarget": "task",
    "chartType": "bar",
    "icon": "si-add-circle",
    "refreshInterval": 300,
    "names": [
      {
        "locale": "en",
        "value": "New statistic"
      },
      {
        "locale": "fr",
        "value": "Nouvelle statistique"
      },
      {
        "locale": "de",
        "value": "Neue Statistik"
      },
      {
        "locale": "ja",
        "value": "新しい統計"
      },
      {
        "locale": "es",
        "value": "Nueva estadística"
      }
    ],
    "descriptions": [
      {
        "locale": "en",
        "value": "Demo new statistic"
      },
      {
        "locale": "fr",
        "value": "Démonstration d'une nouvelle statistique"
      },
      {
        "locale": "de",
        "value": "Neue Demo-Statistik"
      },
      {
        "locale": "ja",
        "value": "新しい統計のデモ"
      },
      {
        "locale": "es",
        "value": "Demo nueva estadística"
      }
    ],
    "barChartConfig": {
      "xTitles": [
        {
          "locale": "en",
          "value": "State"
        },
        {
          "locale": "fr",
          "value": "État"
        },
        {
          "locale": "de",
          "value": "Status"
        },
        {
          "locale": "ja",
          "value": "状態"
        },
        {
          "locale": "es",
          "value": "Estatus"
        }
      ],
      "yTitles": [
        {
          "locale": "en",
          "value": "Number of tasks"
        },
        {
          "locale": "fr",
          "value": "Nombre de tâches"
        },
        {
          "locale": "de",
          "value": "Anzahl der Aufgaben"
        },
        {
          "locale": "ja",
          "value": "タスクの数"
        },
        {
          "locale": "es",
          "value": "Número de tareas"
        }
      ],
      "backgroundColors": [
        "#6299f7",
        "#8dc261",
        "#98bffa",
        "#bee3cb",
        "#c8befa",
        "#f5bf9f",
        "#f8da96",
        "#f9908c"
      ]
    }
  }
..

- ``id``: ID of the widget.

- ``version``: current version of the widget.

- ``statisticAggregation``: statistic's aggregation.

   - ``field``: aggregation (group by) field.

   - ``type``: type of the field, could be `standard` or `custom`.

   - ``kpiField`` (optional): the numeric custom field on which to perform calculations.

   - ``aggregationMethod`` (optional): the calculation method to apply, could be `sum`, `avg`, `max` or `min`.

   When ``kpiField`` and ``aggregationMethod`` are not provided, returns count statistics (counting number of tasks or cases).
   In contrast, the system applies the specified aggregation method to the numeric values in ``kpiField``.

- ``filters``: filters for the statistic chart.

   - ``field``: field to filter.

   - ``values``: value of the filter to query.

   - ``operator``: operator for the filter.

   - ``type``: type of the field.

- ``permissions``: permissions for statistic chart.

- ``chartTarget``: chart's target, could be `case` or `task`.

- ``icon``: icon for the statistic chart.

- ``refreshInterval``: number of seconds for the chart auto-refreshed, minimum value is 60.

- ``names``: name for the statistic chart, multi-language supported.

- ``descriptions``: description for the statistic chart, multi-language supported.

- ``chartType``: chart's type, could be `bar`, `line`, `pie` or `number`.
   
   ``pie``: Pie chart

   |pie-chart|

   ``bar``: Bar chart

   |bar-chart|

   ``line``: Line chart

   |line-chart|

   ``number``: Labelled number chart
   
   |number-chart|

For some specific charts such as ``Bar``, ``Pie``, ``Line`` or ``Number``, there are additional fields:

- ``barChartConfig``: additional fields for configuring the ``Bar`` chart, you can add if chart type is ``bar`` 

   - ``xTitles``: the multilingual display title for the x-axis

   - ``yTitles``: the multilingual display title for the y-axis
   
   - ``backgroundColors`` : the colors to display the chart

- ``lineChartConfig``: additional fields for configuring the ``Line`` chart, you can add if chart type is ``line``

   - ``xTitles``: the multilingual display title for the x-axis

   - ``yTitles``: the multilingual display title for the y-axis

   - ``backgroundColors`` : the colors to display the chart
 
- ``pieChartConfig``: additional fields for configuring the ``Pie`` chart, you can add if chart type is ``pie``

   - ``backgroundColors`` : the colors to display the chart

- ``numberChartConfig``: additional fields for configuring the ``Number`` chart, you can add if chart type is ``number``

   - ``hideLabel``: toggle to show label of the number chart
   
.. _portal-process-external-link:

Portal Processes External Links
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
The standard external links of Portal are defined in the following file:

Filename: ``variables.Portal.Processes.ExternalLinks.json``

Data model:

.. code-block:: javascript

   [
      {
         "id": "01322912db224658a222804802844a7b",
         "version": "10.0.9",
         "name": "Download latest Axon Ivy",
         "link": "https://developer.axonivy.com/download",
         "creatorId": 2,
         "icon": "fa-paperclip",
         "description": "https://developer.axonivy.com/download",
         "imageContent": "<your-image-data-as-base64>",
         "imageLocation": "/com/axonivy/portal/ExternalLink/dd91ec84-c5aa-4202-aeea-4500fbd394ef",
         "imageType": "jpeg",
         "permissions": [
            "Everybody"
         ]
      }
   ]

- ``id``: the identification of a link, auto-generated by UUID
- ``version``: the version of the json
- ``name``: the display name of a link
- ``link``: the URL of the link
- ``creatorId``: the user id who create the link. This user can see and start the external link in any case
- ``icon``: the style class of the link icon
- ``description``: the description of a link
- ``imageContent``: the base64 data format of the process image. If this value is valid then it will be converted to a physical file. If you want to configure external link images when deploying, you just need to define ``imageContent``
- ``imageLocation``: the location of the process image. This image is stored in Application CMS. Basically, this should be handled by the engine
- ``imageType``: the extension of the process image. Basically, this should be handled by the engine
- ``permissions``: users have these roles can see and start the external link. If you don't define it, default role is Everybody


.. |portal-header| image:: ../../screenshots/settings/user-settings.png
.. |global-settings| image:: ../../screenshots/settings/global-settings.png
.. |global-setting-edit| image:: ../../screenshots/settings/edit-global-settings.png
.. |select-admin-settings| image:: ../../screenshots/settings/select-admin-settings.png
.. |export-cms| image:: images/export-cms.png

.. _Task Aggregation and Filter: https://developer.axonivy.com/api-browser?configUrl=https%3A%2F%2Fdeveloper.axonivy.com%2Fdoc%2F11.3%2Fopenapi%2Fconfig.json&urls.primaryName=default#/workflow/stats_1 
.. _Case Aggregation and Filter: https://developer.axonivy.com/api-browser?configUrl=https%3A%2F%2Fdeveloper.axonivy.com%2Fdoc%2F11.3%2Fopenapi%2Fconfig.json&urls.primaryName=default#/workflow/stats
   
.. |pie-chart| image:: ../../screenshots/statistic/tasks-by-prior-pie-chart.png
.. |bar-chart| image:: ../../screenshots/statistic/tasks-by-prior-bar-chart.png
.. |number-chart| image:: ../../screenshots/statistic/tasks-by-prior-number-chart.png
.. |line-chart| image:: ../../screenshots/statistic/completed-cases-chart.png
.. |translate-json| image:: images/translate-json.png
.. |translate-java| image:: images/translate-java.png