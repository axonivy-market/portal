.. _settings:

Settings
********

This section covers Portal role, permissions and some settings.

|portal-header|

.. _settings-admin-settings:

Configure Portal settings
=========================

To manually configure Portal settings, refer to :ref:`update-portal-settings`.

Portal settings are stored as :dev-url:`Variables </doc/nightly/designer-guide/configuration/variables.html>`.

In development, it is a quite tedious task to configure Portal settings after restarting Designer. You could update value of Variables in
``PortalKit/config/variables.yaml`` for the settings that you want to configure, it could survive after restarting Designer.

.. _settings-language:

Language settings
=================

-  Below is applied language setting precedence:

   |language-precedence|

If user email language setting is application default, language would be applied by application email language setting.

If user selects a language in email setting, language would be appplied by this selection.

To configure languages of Portal applications, refer to :ref:`language-settings`.

-  For multiple languages, the CMS key ``/AppInfo/SupportedLanguages``
   must exist in your application. This CMS entry is
   in Portal Style. It contains list of all languages supported by
   your application, separated by comma.

   -  Must not contain spaces
   -  Same as display name of Locale
   -  Separated by comma
   -  Process model version, which has this CMS, must active

-  To add new language to Portal, what you have to do is

   -  Open CMS item ``/AppInfo/SupportedLanguages`` in Portal Style, then add new language for example Italian 
      |add-new-item-to-cms-language|
   -  Add new language locale to all items, you can input either language code ``it`` or language code with country ``it-CH``. If you use language code with country, refer to `Java supported locales <https://www.oracle.com/java/technologies/javase/jdk11-suported-locales.html>`_ .
      |add-new-item-to-cms-language-after|
   -  Export all CMS entries of Portal Style to excel file then add translation of new language for all CMS entries
      |export-cms|
   -  Import file excel back.
   -  Redeploy Portal Style.


Role configuration
==================

.. table:: 

   +-----------------------------------+-----------------------------------+
   | PortalKit roles                   | Rights                            |
   +===================================+===================================+
   | AXONIVY_PORTAL_ADMIN              | User belong to this role can      |
   |                                   | handle AdminUI page, configure    |
   |                                   | the internal role properties,     |
   |                                   | create public filters. Users who  |
   |                                   | own this role need some           |
   |                                   | permissions.                      |
   |                                   |                                   |   
   +-----------------------------------+-----------------------------------+

.. _settings-permission-settings:

Permission settings
===================

Permissions can be configured in :dev-url:`Cockpit
</doc/nightly/engine-guide/tool-reference/engine-cockpit/security.html>`.
In the security area, you will find all these permission in the Group
"PortalPermissions"

Task permission
---------------
- Add note

   User needs permission: ``PortalPermission.TASK_CASE_ADD_NOTE``.

- Delegate

   To show delegate action, user needs permission:
   ``PortalPermission.TASK_DISPLAY_DELEGATE_ACTION``.

   To delegate personal or group tasks, user needs permission:
   ``TaskWriteActivatorOwnTasks`` (This permission belongs to Portal
   permission group and it is not assigned to role Everybody by
   default). 
   
   To delegate all tasks in task list, user needs permission:
   ``IPermission.TASK_WRITE_ACTIVATOR``.

   .. important::
      Task state cannot be one of the following values:
      CREATED, DONE, DESTROYED, RESUMED, FAILED.

- Reset

   To show reset action, user needs permission:
   ``PortalPermission.TASK_DISPLAY_RESET_ACTION``.

   To reset task, user needs permission:
   ``IPermission.TASK_RESET_OWN_WORKING_TASK`` or
   ``PortalPermission.TASK_RESET_READY_FOR_JOIN`` or
   ``IPermission.TASK_RESET``.

   .. important::
      Task state has to be one of following values: RESUMED, PARKED, READY_FOR_JOIN, FAILED.

   
- Delete

   User needs permission:
   ``IPermission.TASK_DESTROY``.

   .. important::
      Task state isn't DESTROYED or DONE.

- Reserve

   To show reserve action, user needs permission: 
   ``PortalPermission.TASK_DISPLAY_RESERVE_ACTION``.

   To reserve task, user needs permission: 
   ``IPermission.TASK_PARK_OWN_WORKING_TASK``.

   .. important::
      Task state has to be one of following values: CREATED, RESUMED, SUSPENDED.

- Change description

   User needs permission: 
   ``IPermission.TASK_WRITE_DESCRIPTION``.

   .. important::
      Task state cannot be one of following values:
      DONE, DESTROYED, FAILED.

- Change deadline

   User needs permission: 
   ``IPermission.TASK_WRITE_EXPIRY_TIMESTAMP``.

   .. important::
      Task state cannot be one of following values:
      DONE, DESTROYED, FAILED.

- Change priority

   User needs permission: 
   ``IPermission.TASK_WRITE_ORIGINAL_PRIORITY``.

   .. important::
      Task state cannot be one of following values:
      DONE, DESTROYED, FAILED.

- Display additional options

   To show additional action, user needs permission: 
   ``PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS``.

Case permission
---------------

- Add note

   User needs permission: ``PortalPermission.TASK_CASE_ADD_NOTE``.

- Delete

   User needs permission: 
   ``IPermission.CASE_DESTROY``.

   .. important::
      Case state must be RUNNING.

- Change description

   User needs permission: 
   ``IPermission.CASE_WRITE_DESCRIPTION``.

   .. important::
      Case state cannot be DESTROYED.

- See related tasks of case

   To show related tasks action, user needs permission: 
   ``PortalPermission.SHOW_ALL_TASKS_OF_CASE``.

   To see related tasks, user needs permission: 
   ``IPermission.TASK_READ_OWN_CASE_TASKS`` or
   ``IPermission.TASK_READ_ALL``.

   .. important::
      Case state cannot be DESTROYED.

- Display show detail link

   User needs permission: 
   ``PortalPermission.SHOW_CASE_DETAILS``.
   This permission is not assigned to role Everybody by default.

Administrator permission can see all tasks/cases in the application

Normal users can only see their tasks/cases they can work on.

Administrator can see all tasks/cases in the application.

Permissions needed: ``IPermission.TASK_READ_ALL``,
``IPermission.CASE_READ_ALL`` .

Administrator permission can interact with all workflows in the application

Normal user can update and delete workflow which created by him and
can interact with workflow's task which assigned to him.

Administrator can create, update and deletes all workflows in the
application.

Other permissions
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
 |           | Access to statistic, it's       | ``PortalPermission.ACCESS_FULL_STATISTICS_LIST``   |
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

Virus Scanning Setting 
======================

PrimeFaces is delivered with one implementation of that interface that uses
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

By default after configured context-param in the web XML file, the Virus
Scanning is enabled. You could update the value of the variable
``EnableVirusScanner`` to ``false`` in ``PortalKit/config/variables.yaml`` that
you want to disable.


Reference: `How to check if uploaded files contain a virus
<https://community.axonivy.com/d/144-how-to-check-if-a-uploaded-files-contain-a-virus/>`_.

Global variables
================

Variables
---------
These variables are storing as key, value. Can edit in the cockpit only.

.. table:: 

   +---------------------------------------------+-------------------------------+-----------------------------+
   | Variable                                    | Default                       | Description                 |
   |                                             | value                         |                             |
   +=============================================+===============================+=============================+
   | PortalStartTimeCleanObsoletedDataExpression | 0 0 6 \* \* ?                 | Cron expression define      |
   |                                             |                               | the time to clean up data   |
   |                                             |                               | of obsoleted users. E.g.:   |
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
   |                                             |                               | cron job runs daily (at     |
   |                                             |                               | 6.AM as default) will       |
   |                                             |                               | clean all finished hidden   |
   |                                             |                               | cases in engine.            |
   |                                             |                               | Otherwise, just hidden      |
   |                                             |                               | cases which were            |
   |                                             |                               | generated by Portal will    |
   |                                             |                               | be deleted.                 |
   +---------------------------------------------+-------------------------------+-----------------------------+
   | PortalHiddenTaskCaseExcluded                | true                          | By default, Portal will     |
   |                                             |                               | query tasks and cases       |
   |                                             |                               | which don't have hide       |
   |                                             |                               | information. Set it to      |
   |                                             |                               | ``false``, Portal will      |
   |                                             |                               | ignore this additional      |
   |                                             |                               | property.                   |
   +---------------------------------------------+-------------------------------+-----------------------------+
   | PortalLoginPageDisplay                      | true                          |By default, Portal will      |
   |                                             |                               |redirect to Login Page if    |
   |                                             |                               |login is required and user   |
   |                                             |                               |is unknown. Set it to false  |
   |                                             |                               |to redirect to login error   |
   |                                             |                               |page and hide Logout in      |
   |                                             |                               |User menu when you are using |
   |                                             |                               |external authentication and  |
   |                                             |                               |the user is not created in   |
   |                                             |                               |your application user list.  |
   +---------------------------------------------+-------------------------------+-----------------------------+


Configuration
-------------

These variables are storing in JSON format, can edit on the cockpit, or using the UI on the Portal Admin setting.


Portal.Announcement
^^^^^^^^^^^^^^^^^^^
The standard announcement for Portal included general information (e.g. Downtime, Changes, etc.). This message can be seen by all portal users.

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


Portal.ThirdPartyApplications
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
You can define your custom menu item via this JSON file. It will be included on the left menu.

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

- ``id``: the identification number of a third-party application, auto-generated by UUID
- ``displayName``: the display name of the app that shows in the left menu, support multi-language
- ``menuIcon``: the style class of app icon that shows in the left menu
- ``menuOrdinal``: index of app that uses to sort menu items in the left menu
- ``name``: the name of third-party app
- ``link``: the URL of third-party app

.. _portal-statistic-charts:

Portal.StatisticCharts
^^^^^^^^^^^^^^^^^^^^^^
You can define the standard statistic charts via this JSON file. It will be shown as the default charts on the statistic page.

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

-  ``id``: the identification of chart, auto-generated by UUID
-  ``names``: the display name of the chart, support multi-language by defined locale and value
-  ``type``: type of chart such as ``TASK_BY_PRIORITY``, ``CASES_BY_STATE``, ``CASES_BY_FINISHED_TASK``, ``CASES_BY_FINISHED_TIME``, ``TASK_BY_EXPIRY`` and ``ELAPSED_TIME_BY_CASE_CATEGORY``
-  ``filter``: list filters apply for each chart

   -  ``timePeriodSelection``: type of period filter such as ``CUSTOM``, ``LAST_WEEK``, ``LAST_MONTH`` and ``LAST_6_MONTH``
   -  ``createdDateFrom``: start time for custom period filter
   -  ``createdDateTo``: end time for custom period filter
   -  ``selectedCaseCategories``: case category filter
   -  ``selectedRoles``: role filter
   -  ``isAllRolesSelected``: indicator to inform that selected all roles or not
   -  ``selectedCaseStates``: case state filter
   -  ``isAllCaseStatesSelected``: indicator to inform that selected all states or not
   -  ``selectedTaskPriorities``: task priority filter
   -  ``isAllTaskPrioritiesSelected``: indicator to inform that selected all priorities or not
   -  ``customFieldFilters``: list CustomField name filters, define by ``ICase.customFields()``

      -  ``CustomVarCharField``: name of ``ICase.customFields()``

-  ``position``: position index of chart that uses to sort chart in the UI

.. _portal-dashboard-favorite-processes:

Portal.Dashboard.FavoriteProcesses
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
You can include your custom application favorites processes via this JSON file. It will be shown as the processes in the application favorite section.

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

- ``id``: the identification of a process, auto-generated by UUID
- ``processType``: type of a process such as ``EXPRESS_PROCESS``, ``EXTERNAL_LINK``, ``IVY_PROCESS``
- ``processName``: the display name of a process
-  ``names``: the display name of a process, support multi-language by defined locale and value
- ``icon``: the style class of the process icon
- ``processId``: the process id of the process start in ivy
- ``index``: the index number to sort the processes in the dashboard

Portal.Processes.ExternalLinks
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
The standard external links of Portal.

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


Portal.Processes.ExpressProcesses
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
The standard express processes of Portal.

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
