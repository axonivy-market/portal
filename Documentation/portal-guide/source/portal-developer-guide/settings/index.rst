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

Portal Settings Configuration
=============================

Portal Settings Configuration
=============================

Portal settings control global behavior and can be configured through multiple methods:

**Configuration Methods:**

- **Admin Settings UI**: See :ref:`update-portal-settings` for user-friendly configuration
- **Variables File**: Update ``portal/config/variables.yaml`` for development (survives Designer restarts)
- **Engine Cockpit**: Configure via :dev-url:`Variables </doc/12.0/designer-guide/configuration/variables.html>`

.. tip::
   In development, configure settings in ``portal/config/variables.yaml`` to avoid re-configuring after each Designer restart.

.. _settings-language:

Language Settings
=================

HowTo: Add a New Language to Portal
------------------------------------

#. **Export CMS Entries**

   Export all CMS entries of the Portal to an Excel file

#. **Add Language Column**

   - Open the exported Excel file
   - Add a new column at the end with the language locale code (e.g., ``it`` for Italian)
   - Refer to `Java supported locales (JDK 21) <https://www.oracle.com/java/technologies/javase/jdk21-suported-locales.html>`_ for supported locale codes
   
   |export-cms|
   
   For JDK 11:
   
   Refer to `Java supported locales (JDK 11) <https://www.oracle.com/java/technologies/javase/jdk11-suported-locales.html>`_ for supported locale codes

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

   - Import the Excel file back into the Portal
   - Redeploy the Portal

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

Configure permissions in the :dev-url:`Engine Cockpit </doc/12.0/engine-guide/reference/engine-cockpit/security.html>`. All Portal permissions are grouped under "PortalPermissions".

.. important::
   **Portal Permission Support:**
   
   The Portal is built as a layer above the Axon Ivy Engine core. Not every core engine permission is automatically honored or supported by the Portal. Supporting every core permission would require significant effort and increase maintenance overhead.
   If you require a specific engine permission that is not currently supported by the Portal, please contact Axon Ivy support to discuss your requirements.

.. note::
   **General Permission Rules:**
   
   - Normal users can only see tasks and cases they can work on
   - Administrators with :bdg-ref-warning:`🔑TaskReadAll <TaskReadAll>` and :bdg-ref-warning:`🔑CaseReadAll <CaseReadAll>` can see all tasks/cases
   - Administrators can interact with, create, update, and delete all workflows
   - Normal users can only update/delete workflows they created and work on assigned tasks

Task Permissions
----------------

**Add Note**

User needs permission: :bdg-ref-warning:`🔑TaskCaseAddNote <TaskCaseAddNote>`

**Delegate**

Required permissions:

- To see delegate action: :bdg-ref-warning:`🔑TaskDisplayDelegateAction <TaskDisplayDelegateAction>` and :bdg-ref-warning:`🔑TaskWriteActivator <TaskWriteActivator>`
- To delegate personal/group tasks: :bdg-ref-warning:`🔑TaskWriteActivatorOwnTasks <TaskWriteActivatorOwnTasks>` (not assigned to Everybody by default)
- Case owner delegation: :bdg-ref-warning:`🔑CaseOwnerTaskDelegate <CaseOwnerTaskDelegate>` allows delegating all related tasks within their case

.. important::
   Only tasks ready for user processing can be delegated. Task state cannot be: CREATED, DONE, DESTROYED, RESUMED, FAILED

**Reset**

Required permissions:

- To see reset action: :bdg-ref-warning:`🔑TaskDisplayResetAction <TaskDisplayResetAction>`
- To reset tasks: :bdg-ref-warning:`🔑TaskResetOwnWorkingTask <TaskResetOwnWorkingTask>` or :bdg-ref-warning:`🔑TaskResetReadyForJoin <TaskResetReadyForJoin>` or :bdg-ref-warning:`🔑TaskReset <TaskReset>`

.. important::
   Only works for tasks in states: RESUMED, PARKED, READY_FOR_JOIN, FAILED

**Delete**

User needs permission: :bdg-ref-warning:`🔑TaskDestroy <TaskDestroy>`

.. important::
   Only works if task state is not DESTROYED or DONE

**Reserve**

Required permissions:

- To see reserve action: :bdg-ref-warning:`🔑TaskDisplayReserveAction <TaskDisplayReserveAction>`
- To reserve a task: :bdg-ref-warning:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>`

.. important::
   Only possible if task is in states: CREATED, RESUMED, SUSPENDED

**Change Description**

User needs permission: :bdg-ref-warning:`🔑TaskWriteDescription <TaskWriteDescription>`

.. important::
   Terminated tasks cannot be changed. Task state cannot be: DONE, DESTROYED, FAILED

**Change Deadline**

User needs permission: :bdg-ref-warning:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`

.. important::
   Task cannot be in states: DONE, DESTROYED, FAILED

**Change Priority**

User needs permission: :bdg-ref-warning:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`

.. important::
   Task cannot be in states: DONE, DESTROYED, FAILED

**Display Additional Options**

User needs permission: :bdg-ref-warning:`🔑TaskDisplayAdditionalOptions <TaskDisplayAdditionalOptions>`

**Display Workflow Events**

User needs permission: :bdg-ref-warning:`🔑TaskDisplayWorkflowEventAction <TaskDisplayWorkflowEventAction>`

**Display Custom Fields**

User needs permission: :bdg-ref-warning:`🔑TaskDisplayCustomFieldsAction <TaskDisplayCustomFieldsAction>`

**Share Task Details Link**

User needs permission: :bdg-ref-warning:`🔑ShareTaskDetailsLink <ShareTaskDetailsLink>`

**Change Expiry Activator**

User needs permission: :bdg-ref-warning:`🔑TaskWriteExpiryActivator <TaskWriteExpiryActivator>`

.. important::
   Task cannot be in states: DONE, DESTROYED, FAILED

**Read System Tasks**

User needs permission: :bdg-ref-warning:`🔑SystemTaskReadAll <SystemTaskReadAll>`

Case Permissions
----------------

**Add Note**

User needs permission: :bdg-ref-warning:`🔑TaskCaseAddNote <TaskCaseAddNote>`

**Delete**

User needs permission: :bdg-ref-warning:`🔑CaseDestroy <CaseDestroy>`

.. important::
   Case state must be RUNNING

**Change Description**

User needs permission: :bdg-ref-warning:`🔑CaseWriteDescription <CaseWriteDescription>`

.. important::
   Case state cannot be DESTROYED

**See Related Tasks of Case**

Required permissions:

- To see the action: :bdg-ref-warning:`🔑ShowAllTasksOfCase <ShowAllTasksOfCase>`
- To view related tasks: :bdg-ref-warning:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>` or :bdg-ref-warning:`🔑TaskReadAll <TaskReadAll>`

.. important::
   Case state cannot be DESTROYED

**Display Show Detail Link**

User needs permission: :bdg-ref-warning:`🔑ShowCaseDetails <ShowCaseDetails>` (not assigned to Everybody by default)

**Share Case Details Link**

User needs permission: :bdg-ref-warning:`🔑ShareCaseDetailsLink <ShareCaseDetailsLink>`

**Display Custom Fields**

User needs permission: :bdg-ref-warning:`🔑CaseDisplayCustomFieldsAction <CaseDisplayCustomFieldsAction>`

.. _settings-permission-settings-others:

Other Permissions
-----------------

.. table::

 +-----------+---------------------------------+---------------------------------------------------------------------------------------+
 |           | Action                          | Permission required                                                                   |
 +===========+=================================+=======================================================================================+
 | Absence   | Read                            | :bdg-ref-warning:`🔑UserReadOwnAbsences <UserReadOwnAbsences>`  or                    |
 |           |                                 | :bdg-ref-warning:`🔑UserReadAbsences <UserReadAbsences>`                              |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Create, edit                    | :bdg-ref-warning:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>` or                   |
 |           |                                 | :bdg-ref-warning:`🔑UserCreateAbsence <UserCreateAbsence>`                            |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Delete                          | :bdg-ref-warning:`🔑UserDeleteOwnAbsence <UserDeleteOwnAbsence>` or                   |
 |           |                                 | :bdg-ref-warning:`🔑UserDeleteAbsence <UserDeleteAbsence>`                            |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Read absences of all users      | :bdg-ref-warning:`🔑UserReadAbsences <UserReadAbsences>`                              |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Delete absences of all users    | :bdg-ref-warning:`🔑UserDeleteAbsence <UserDeleteAbsence>`                            |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Create new absence for all users| :bdg-ref-warning:`🔑UserCreateAbsence <UserCreateAbsence>`                            |
 +-----------+---------------------------------+---------------------------------------------------------------------------------------+
 | Substitute| Manage substitute               | :bdg-ref-warning:`🔑UserCreateSubstitute <UserCreateSubstitute>` and                  |
 |           |                                 | :bdg-ref-warning:`🔑UserReadSubstitutes <UserReadSubstitutes>`                        |
 +-----------+---------------------------------+---------------------------------------------------------------------------------------+
 | Document  | Upload, delete                  | :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>`                                    |
 |           |                                 | :bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>`        |
 +-----------+---------------------------------+---------------------------------------------------------------------------------------+
 | Portal    | Access to full process          | :bdg-ref-warning:`🔑AccessFullProcessList <AccessFullProcessList>`                    |
 | permission| list, it's "Processes" on the   |                                                                                       |
 |           | left menu and link "Show all    |                                                                                       |
 |           | processes" on Dashboard         |                                                                                       |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Access to full task list, it's  | :bdg-ref-warning:`🔑AccessFullTaskList <AccessFullTaskList>`                          |
 |           | "Tasks" on the left menu and    |                                                                                       |
 |           | link "Show full task list" on   |                                                                                       |
 |           | Dashboard                       |                                                                                       |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Access to full case list, it's  | :bdg-ref-warning:`🔑AccessFullCaseList <AccessFullCaseList>`                          |
 |           | "Cases" on the left menu        |                                                                                       |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Add note to task/case           | :bdg-ref-warning:`🔑TaskCaseAddNote <TaskCaseAddNote>`                                |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Display show more note          | :bdg-ref-warning:`🔑TaskCaseShowMoreNote <TaskCaseShowMoreNote>`                      |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Create public external link, all| :bdg-ref-warning:`🔑CreatePublicExternalLink <CreatePublicExternalLink>`              |
 |           | other users can see that link in|                                                                                       |
 |           | the full process list           |                                                                                       |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Dashboard sharing               | :bdg-ref-warning:`🔑ShareDashboardLink <ShareDashboardLink>`                          |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Modify notification channels    | :bdg-ref-warning:`🔑NotificationChannelsSetting <NotificationChannelsSetting>`        |
 |           | preferences in :ref:`my-profile`|                                                                                       |
 |           | page                            |                                                                                       |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Role management (Admin Settings)| :bdg-ref-warning:`🔑RoleManagement <RoleManagement>`                                  |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | News management (Dashboard)     | :bdg-ref-warning:`🔑NewsManagement <NewsManagement>`                                  |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Password validation settings    | :bdg-ref-warning:`🔑PasswordValidation <PasswordValidation>`                          |
 |           +---------------------------------+---------------------------------------------------------------------------------------+
 |           | Read all notes on tasks/cases   | :bdg-ref-warning:`🔑NoteReadAllCaseTaskDetails <NoteReadAllCaseTaskDetails>`          |
 +-----------+---------------------------------+---------------------------------------------------------------------------------------+

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

Portal has built-in statistical chart definitions that can be used by the :ref:`Statistic widget <portal-statistic-widget>` in the Portal dashboard. 

Filename: ``variables.Portal.ClientStatistic.json``

.. warning::
   In the next version of Portal, this variable will be removed as we implement a more powerful approach for statistics. 
   Any modifications to this variable will be lost. Please consider this before making any changes.

Data model:

.. code-block:: javascript

   [
      {
         "id": "1",
         ...
      }
   ]

- ``id``: ID of the statistic chart

- ``barChartConfig``: required fields for configuring the ``Bar`` chart, please add them if chart type is ``bar`` 

   - ``xTitles``: the multilingual display title for the x-axis
   - ``yTitles``: the multilingual display title for the y-axis
   - ``yValue``: add this field with **time** value when you want to show the amount of time on the y-axis

- ``lineChartConfig``: required fields for configuring the ``Line`` chart, please add them if chart type is ``line``

   - ``xTitles``: the multilingual display title for the x-axis
   - ``yTitles``: the multilingual display title for the y-axis

- ``numberChartConfig``: additional fields for configuring the ``Number`` chart, you can add if chart type is ``number``

   - ``suffixSymbol``: icon next to the number. Portal supports both Streamline and FontAwesome icons
   
.. _portal-process-external-link:

Portal Processes External Links
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
The standard external links of the Portal are defined in the following file:

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
.. |translate-json| image:: images/translate-json.png
.. |translate-java| image:: images/translate-java.png
