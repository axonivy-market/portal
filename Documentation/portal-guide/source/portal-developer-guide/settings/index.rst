.. _settings:

Settings
********

This section covers Portal roles, permissions and other settings.

|portal-header|

.. _settings-admin-settings:

Configure Portal Settings
=========================

To manually configure Portal settings, refer to :ref:`update-portal-settings`.

Portal settings are stored as :dev-url:`Variables </doc/nightly/designer-guide/configuration/variables.html>`.

In development, it is a quite tedious task to configure Portal settings after
restarting Designer. Therefore, update your variables in
``PortalKit/config/variables.yaml`` for the settings that you want to configure.
This survives restarting Designer.

.. _settings-language:

Language Settings
=================

-  We use the following language setting precedence:

   |language-precedence|

   If the user email language setting is the application default, the application
   default language is applied.

   If the user selects a language in email settings, this language is applied.

   To configure the languages of Portal applications, refer to
   :ref:`language-settings` in :ref:`my-profile` in the Portal User Guide.

-  For multiple languages, the CMS key ``/AppInfo/SupportedLanguages`` has to
   exist in your application. This CMS entry is located in PortalKit. It
   contains a comma separated list of all languages supported by your
   application.

   The rules for this list are:

   -  This list must not contain spaces
   -  Each entry has to be either a language code (en) or a language-country code (en-US) of a Java Locale (see below)
   -  Entries are separated by comma
   -  The process model version (PMV) containing this CMS entry has to be active.

-  To add a new language to Portal, what you have to do is:

   -  Open CMS item ``/AppInfo/SupportedLanguages`` in PortalKit, then add the new language, for example, Italian

      |add-new-item-to-cms-language|

   -  Add the new language locale to all items. You can input either the language code ``it`` or the language code with country ``it-CH``. 
      If you use language code with country, refer to `Java supported locales <https://www.oracle.com/java/technologies/javase/jdk11-suported-locales.html>`_ .

      |add-new-item-to-cms-language-after|

   -  Export all CMS entries of PortalKit to an Excel file, then add the translations for the new language for all CMS entries

      |export-cms|

   -  Import the Excel file.
   -  Redeploy PortalKit.


Role Configuration
==================

.. table:: 

   +-----------------------------------+-----------------------------------+
   | PortalKit roles                   | Rights                            |
   +===================================+===================================+
   | AXONIVY_PORTAL_ADMIN              | User having to this role can      |
   |                                   | access the Portal Admin page,     |
   |                                   | configure internal role           |
   |                                   | properties, and create public     |
   |                                   | filters. Users who own this role  |
   |                                   | need some permissions.            |
   |                                   |                                   |   
   +-----------------------------------+-----------------------------------+

.. _settings-permission-settings:

Permission Settings
===================

Configure permissions in the :dev-url:`Engine Cockpit
</doc/nightly/engine-guide/tool-reference/engine-cockpit/security.html>`. In the
security area, you will find all these permission in group "PortalPermissions".

Task Permissions
----------------
- Add note

   User needs permission: ``PortalPermission.TASK_CASE_ADD_NOTE``.

- Delegate

   To be able to delegate, the user needs permission
   ``PortalPermission.TASK_DISPLAY_DELEGATE_ACTION``.

   To delegate personal or group tasks, user needs permission
   ``TaskWriteActivatorOwnTasks``. This permission belongs to the
   PortalPermissions group. It is not assigned to role Everybody by default. 
   
   To delegate all tasks in a task list, the user needs permission
   ``IPermission.TASK_WRITE_ACTIVATOR``.

   .. important::
      Only tasks ready for user processing can be delegated.
      Therefore, the task state cannot be one of the following:
      CREATED, DONE, DESTROYED, RESUMED, FAILED.

- Reset

   To see the reset action, the user needs permission
   ``PortalPermission.TASK_DISPLAY_RESET_ACTION``.

   To reset tasks, the user needs permission:
   ``IPermission.TASK_RESET_OWN_WORKING_TASK`` or
   ``PortalPermission.TASK_RESET_READY_FOR_JOIN`` or
   ``IPermission.TASK_RESET``.

   .. important::
      This only works for tasks in one of following states: RESUMED, 
      PARKED, READY_FOR_JOIN, FAILED.

   
- Delete

   To see the Delete Task action, the user needs permission
   ``IPermission.TASK_DESTROY``.

   .. important::
      Delete Task only works if the task state is not already DESTROYED 
      or DONE.

- Reserve

   To see the Reserve action, the user needs permission
   ``PortalPermission.TASK_DISPLAY_RESERVE_ACTION``.

   To reserve a task, the user needs permission
   ``IPermission.TASK_PARK_OWN_WORKING_TASK``.

   .. important::
      Reservation is only possible if the task is in one of the following 
      states: CREATED, RESUMED, SUSPENDED.

- Change description

   User needs permission: 
   ``IPermission.TASK_WRITE_DESCRIPTION``.

   .. important::
      A terminated task cannot be changed. Therefore, the task state cannot be one of the following values:
      DONE, DESTROYED, FAILED.

- Change deadline

   User needs permission
   ``IPermission.TASK_WRITE_EXPIRY_TIMESTAMP``.

   .. important::
      To change an expiry date, the task cannot be in one of the following states:
      DONE, DESTROYED, FAILED.

- Change priority

   User needs permission 
   ``IPermission.TASK_WRITE_ORIGINAL_PRIORITY``.

   .. important::
      To change task priority, the task cannot be in the following states:
      DONE, DESTROYED, FAILED.

- Display additional options

   To see additional actions, the user needs permission
   ``PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS``.

Case Permissions
----------------

- Add note

   User needs permission ``PortalPermission.TASK_CASE_ADD_NOTE``.

- Delete

   User needs permission ``IPermission.CASE_DESTROY``.

   .. important::
      Case state has to be RUNNING.

- Change description

   User needs permission ``IPermission.CASE_WRITE_DESCRIPTION``.

   .. important::
      Case state cannot be DESTROYED.

- See related tasks of case

   To see the related tasks action, the user needs permission
   ``PortalPermission.SHOW_ALL_TASKS_OF_CASE``.

   To see related tasks, user needs permission
   ``IPermission.TASK_READ_OWN_CASE_TASKS`` or ``IPermission.TASK_READ_ALL``.

   .. important::
      Case state cannot be DESTROYED.

- Display show detail link

   User needs permission ``PortalPermission.SHOW_CASE_DETAILS``. By default, this permission
   is not assigned to role Everybody.

Normal users can only see the tasks and cases that they can work on.

Administrators can see all tasks/cases in the application. The require
Permissions ``IPermission.TASK_READ_ALL``, ``IPermission.CASE_READ_ALL``.

Administrators can interact with all workflows in the application.

Administrators can create, update and delete all workflows in the application.

Normal users can update and delete workflows that have been created by them and can interact
with tasks that have been assigned to them.


Other Permissions
-----------------

.. table:: 

 +-----------+---------------------------------+----------------------------------------------------+
 |           | Action                          | Permission required                                |
 +===========+=================================+====================================================+
 | Absence   | Read                            | ``IPermission.USER_READ_OWN_ABSENCES`` or          |
 |           |                                 | ``IPermission.USER_READ_ABSENCES``                 |
 |           +---------------------------------+----------------------------------------------------+
 |           | Create, edit                    | ``IPermission.USER_CREATE_OWN_ABSENCE`` or         |
 |           |                                 | ``IPermission.USER_CREATE_ABSENCE``                |
 |           +---------------------------------+----------------------------------------------------+
 |           | Delete                          | ``IPermission.USER_DELETE_OWN_ABSENCE`` or         |
 |           |                                 | ``IPermission.USER_DELETE_ABSENCE``                |
 |           +---------------------------------+----------------------------------------------------+
 |           | Read absences of all users      | ``IPermission.USER_READ_ABSENCES``                 |
 |           +---------------------------------+----------------------------------------------------+
 |           | Delete absences of all users    | ``IPermission.USER_DELETE_ABSENCE``                |
 |           +---------------------------------+----------------------------------------------------+
 |           | Create new absence for all users| ``IPermission.USER_CREATE_ABSENCE``                |
 +-----------+---------------------------------+----------------------------------------------------+
 | Substitute| Manage substitute               | ``IPermission.USER_CREATE_SUBSTITUTE`` and         |
 |           |                                 | ``IPermission.USER_READ_SUBSTITUTES``              |
 +-----------+---------------------------------+----------------------------------------------------+
 | Document  | Upload, delete                  | ``IPermission.DOCUMENT_WRITE`` or                  |
 |           |                                 | ``IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE``    |
 +-----------+---------------------------------+----------------------------------------------------+
 | Express   | Create Express workflow         | ``PortalPermission.EXPRESS_CREATE_WORKFLOW``       |
 |           |                                 | (assigned to role Everybody by default)            |
 +-----------+---------------------------------+----------------------------------------------------+
 | Statistics| Add dashboard                   | ``PortalPermission.STATISTIC_ADD_DASHBOARD_CHART`` |
 |           |                                 | (assigned to role Everybody by default)            |
 |           +---------------------------------+----------------------------------------------------+
 |           | Analyze, filter tasks           | ``PortalPermission.STATISTIC_ANALYZE_TASK``        |
 |           | and export data to excel        |                                                    |
 |           | for advanced analysis           |                                                    |
 +-----------+---------------------------------+----------------------------------------------------+
 | Portal    | Access to full process          | ``PortalPermission.ACCESS_FULL_PROCESS_LIST``      |
 | permission| list, it's "Processes" on the   |                                                    |
 |           | left menu and link "Show all    |                                                    |
 |           | processes" on Dashboard         |                                                    |
 |           +---------------------------------+----------------------------------------------------+
 |           | Access to full task list, it's  | ``PortalPermission.ACCESS_FULL_TASK_LIST``         |
 |           | "Tasks" on the left menu and    |                                                    | 
 |           | link "Show full task list" on   |                                                    |
 |           | Dashboard                       |                                                    |
 |           +---------------------------------+----------------------------------------------------+
 |           | Access to full case list, it's  | ``PortalPermission.ACCESS_FULL_CASE_LIST``         |
 |           | "Cases" on the left menu        |                                                    |
 |           +---------------------------------+----------------------------------------------------+
 |           | Access to statistics it's       | ``PortalPermission.ACCESS_FULL_STATISTICS_LIST``   |
 |           | "Statistics" on the left menu   |                                                    |
 |           | and link "Show all charts" on   |                                                    |
 |           | Dashboard                       |                                                    |
 |           +---------------------------------+----------------------------------------------------+
 |           | Add note to task/case           | ``PortalPermission.TASK_CASE_ADD_NOTE``            |
 |           +---------------------------------+----------------------------------------------------+
 |           | Display show more note          | ``PortalPermission.TASK_CASE_SHOW_MORE_NOTE``      |
 |           +---------------------------------+----------------------------------------------------+
 |           | Create public external link, all| ``PortalPermission.CREATE_PUBLIC_EXTERNAL_LINK``   |
 |           | other users can see that link in|                                                    |
 |           | full process list and can also  |                                                    |
 |           | add it to User Favorite         |                                                    |
 +-----------+---------------------------------+----------------------------------------------------+

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
``false`` in ``PortalKit/config/variables.yaml`` if you want to disable virus
scanning.

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
   | PortalStartTimeCleanObsoletedDataExpression | 0 0 6 \* \* ?                 | Cron expression defines     |
   |                                             |                               | the time to clean up data   |
   |                                             |                               | of obsolete users. E.g.:    |
   |                                             |                               | expression for at 6AM       |
   |                                             |                               | every day is                |
   |                                             |                               | ``0 0 6 * * ?`` . Refer     |
   |                                             |                               | to                          |
   |                                             |                               | `crontrigger <http://quar   |
   |                                             |                               | tz-scheduler.org/document   |
   |                                             |                               | ation/quartz-2.1.7/tutori   |
   |                                             |                               | als/tutorial-lesson-06.htm  |
   |                                             |                               | l>`__                       |
   |                                             |                               | . Restart Ivy engine        |
   |                                             |                               | after changing this         |
   |                                             |                               | variable.                   |
   +---------------------------------------------+-------------------------------+-----------------------------+
   | PortalDeleteAllFinishedHiddenCases          | false                         | If set to ``true``, the     |
   |                                             |                               | above cron job runs daily   |
   |                                             |                               | and will remove all         |
   |                                             |                               | finished hidden cases on    |
   |                                             |                               | the engine.                 |
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

.. code-block:: html
   
   { "contents": [{
         "language": "en",
         "value": "The announcement content in english"
         }],
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

.. code-block:: html

   [{    "id": "284352a58c7a48a2b64be8a946857c7a",
         "displayName": "{\"de\":\"AxonIvy ger\",\"en\":\"AxonIvy\"}",
         "menuIcon": "fa-group",
         "menuOrdinal": 1,
         "name": "{\"de\":\"AxonIvy ger\",\"en\":\"AxonIvy\"}",
         "link": "https://developer.axonivy.com/download",
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
You can define the standard statistic charts via the following JSON file. They
will be shown as the default charts on the statistic page.

Filename: ``variables.Portal.StatisticCharts.json``

Data model:

.. code-block:: html

   [{ "id": "42e2d9afd9824abc8d3a70b9d9867dba",
      "names": [{
            "locale": "en",
            "value": "Task chart"
         }],
      "type": "TASK_BY_EXPIRY",
      "filter": {
         "timePeriodSelection": "LAST_WEEK",
         "createdDateFrom": null,
         "createdDateTo": null,
         "selectedCaseCategories": ["Alpha_Company"],
         "selectedRoles": ["Everybody"],
         "isAllRolesSelected": false,
         "selectedCaseStates": ["RUNNING"],
         "isAllCaseStatesSelected": false,
         "selectedTaskPriorities": ["HIGH"],
         "isAllTaskPrioritiesSelected": false,
         "customFieldFilters": {
            "CustomVarCharField": ["Request for new computer"],
         }
      },
      "position": 6
   }]

-  ``id``: the identification of chart. An auto-generated UUID.
-  ``names``: the display name of the chart. Supports multi-language.
-  ``type``: type of chart such as ``TASK_BY_PRIORITY``, ``CASES_BY_STATE``, ``CASES_BY_FINISHED_TASK``, ``CASES_BY_FINISHED_TIME``, ``TASK_BY_EXPIRY`` and ``ELAPSED_TIME_BY_CASE_CATEGORY``
-  ``filter``: list filters to apply for each chart

   -  ``timePeriodSelection``: type of period filter such as ``CUSTOM``, ``LAST_WEEK``, ``LAST_MONTH`` and ``LAST_6_MONTH``
   -  ``createdDateFrom``: start time for custom period filter
   -  ``createdDateTo``: end time for custom period filter
   -  ``selectedCaseCategories``: case category filter
   -  ``selectedRoles``: role filter
   -  ``isAllRolesSelected``: indicator to inform if all roles are selected.
   -  ``selectedCaseStates``: case state filter
   -  ``isAllCaseStatesSelected``: indicator to inform if all case states are selected.
   -  ``selectedTaskPriorities``: task priority filter
   -  ``isAllTaskPrioritiesSelected``: indicator to inform if all priorities are selected.
   -  ``customFieldFilters``: list CustomField name filters, define by ``ICase.customFields()``

      -  ``CustomVarCharField``: name of ``ICase.customFields()``

-  ``position``: position index of chart. Used to order the charts in the UI

.. _portal-dashboard-favorite-processes:

Portal Dashboard Favorite Processes
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
You can include the favorite processes of your custom application via the following
JSON file. It will be shown as the processes in the application favorite
section.

Filename: ``variables.Portal.Dashboard.FavoriteProcesses.json``

Data model:

.. code-block:: html

   [{ "id": "234152a58c7a48a2b63be8a946e5731b",
      "processType": "IVY_PROCESS",
      "processName": "Alpha Company Task",
      "names": [
         {
            "locale": "de",
            "value": "Aufgabe der Alpha-Firma"
         },
         {
            "locale": "en",
            "value": "Alpha Company Task"
         },
         {
            "locale": "fr",
            "value": "Tâche de la société Alpha"
         },
         {
            "locale": "es",
            "value": "Tarea de empresa alfa"
         }
      ],
      "icon": "fa-building",
      "processId": "Portal/portal-developer-examples/Start Processes/ProcessHistoryComponent/createAlphaCompany.ivp",
      "index": 1
   }]

- ``id``: the identification of a process. auto-generated UUID.
- ``processType``: type of a process such as ``EXPRESS_PROCESS``, ``EXTERNAL_LINK``, ``IVY_PROCESS``.
- ``processName``: the display name of a process.
-  ``names``: the display name of a process. support multi-language.
- ``icon``: the style class of the process icon.
- ``processId``: the process id of the process start.
- ``index``: the index number to order the processes in the dashboard.

Portal Processes External Links
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
The standard external links of the Portal are defined in the following file:

Filename: ``variables.Portal.Processes.ExternalLinks.json``

Data model:

.. code-block:: html

   [{  "id": "01322912db224658a222804802844a7b",
      "name": "Download latest Axon Ivy",
      "link": "https://developer.axonivy.com/download",
      "creatorId": 2,
      "icon": "fa-paperclip",
      "description": "https://developer.axonivy.com/download"
   }]

- ``id``: the identification of a link, auto-generated by UUID
- ``name``: the display name of a link
- ``link``: the URL of the link
- ``creatorId``: the user id who create the link
- ``icon``: the style class of the link icon
- ``description``: the description of a link


Portal Express Processes
^^^^^^^^^^^^^^^^^^^^^^^^
The standard express processes of the Portal are defined in this file:

Filename: ``variables.Portal.Processes.ExpressProcesses.json``

Data model:

.. code-block:: html

   [{ "id": "f281e1ee7fb54bcda8d7a0c64ba46fc8",
      "processName": "Portal Express process",
      "processDescription": "Process",
      "processType": "AHWF",
      "processPermissions": ["Everybody"],
      "processOwner": "#portaladmin externalId:889",
      "processFolder": "8e9870b2-0179-46eb-bdb8",
      "readyToExecute": true,
      "processCoOwners": ["#demo externalId:9CA"],
      "icon": "fa-codepen",
      "taskDefinitions": [{
         "type": "USER_TASK",
         "responsibles": ["Everybody"],
         "subject": "Express user task",
         "description": "Express user task",
         "taskPosition": 1,
         "untilDays": 2,
         "formElements": [{
            "elementID": "Input area2020-09-07 04:57:05",
            "label": "Input area",
            "required": true,
            "intSetting": 7,
            "elementType": "InputTextArea",
            "optionStrs": [""],
            "elementPosition": "HEADER",
            "indexInPanel": 0
            }]
         }, {
            "type": "EMAIL",
            "responsibles": [],
            "taskPosition": 2,
            "untilDays": 3,
            "email": {
               "recipients": "wawatest@ivy.io",
               "responseTo": "wawa@mail.io",
               "subject": "Verify Express process",
               "content": "<p>Email content</p>",
               "attachments": [],
               "empty": false
            }
         }
      ],
      "ableToEdit": true,
      "useDefaultUI": false
   }]

-  ``id``: the identification of an express process, auto-generated by UUID
-  ``processName``: the display name of an express process
-  ``processDescription``: the description of an express process
-  ``processType``: type of express processes such as ``AMWF`` and ``AHWF``
-  ``processPermissions``: the process permissions who can see this express process
-  ``processOwner``: the user information who create this express process
-  ``processFolder``: the folder id where the express process use to store data
-  ``readyToExecute``: indicator to inform that process can start or not
-  ``processCoOwners``: the user information who can see this express process
-  ``icon``: the style class of express icon
-  ``taskDefinitions``: list tasks of the express process

   -  ``type``: type of the express task such as ``USER_TASK``, ``USER_TASK_WITH_EMAIL``, ``APPROVAL``, and ``EMAIL``
   -  ``responsibles``: responsible for the express task who can work on the task
   -  ``subject``: the name of an express task
   -  ``description``: the description of an express task
   -  ``taskPosition``: the index of a task in the express workflow steps
   -  ``untilDays``: the expiry day of an express task
   -  ``formElements``: list forms on the UI of the express task

      -  ``elementID``: auto-generated
      -  ``label``: the label of the element
      -  ``required``: indicator to inform that form element is required or not
      -  ``intSetting``: auto-generated
      -  ``elementType``: type of element
      -  ``optionStrs``: select options of an element
      -  ``elementPosition``: the position of an element on UI
      -  ``indexInPanel``: auto-generated
      -  ``email``: define an email task

         -  ``recipients``: the recipients of the email
         -  ``responseTo``: response to the email
         -  ``content``: the content of the email
         -  ``attachments``: list attachments
         -  ``empty``: indicator to inform that attachment is empty

- ``ableToEdit``: indicator to inform that express can edit
- ``useDefaultUI``: indicator to inform that express process is using default UI elements


.. |portal-header| image:: ../../screenshots/settings/user-settings.png
.. |global-settings| image:: ../../screenshots/settings/global-settings.png
.. |global-setting-edit| image:: ../../screenshots/settings/edit-global-settings.png
.. |language-precedence| image:: images/settings/language-precedence.png
.. |select-admin-settings| image:: ../../screenshots/settings/select-admin-settings.png
.. |add-new-item-to-cms-language| image:: images/settings/add-new-item-to-cms-language.png 
.. |add-new-item-to-cms-language-after| image:: images/settings/add-new-item-to-cms-language-after.png
.. |export-cms| image:: images/settings/export-cms.png   
