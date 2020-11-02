.. _settings:

Settings
********

This section covers Portal role, permissions and some settings in User menu

|portal-header|

.. _settings-admin-settings:

Admin settings
==============

User needs to have role ``AXONIVY_PORTAL_ADMIN`` to see this menu.

How to open Admin Settings
--------------------------

Open ``Admin Settings`` by selecting the item in ``UserMenu`` on the
topbar, if your page using layout of ``PortalTemplate`` .

|select-admin-settings|

.. important:: 
   If your application does not use templates of PortalTemplate project, you
   have to create a page and use AdminSettings component inside.

.. _settings-admin-settings-global-settings:

Global settings
---------------

Global settings for Portal can be set in Settings tab. All available
settings with their default value and description are listed in this
place.

|global-settings|

You can edit value for a specific setting

|global-setting-edit|

You can also get back the default value for each setting by clicking
reset button on each row, or reset all values of all settings by
clicking ``Restore All To Defaults`` button.

Setup Global settings by configuration file
"""""""""""""""""""""""""""""""""""""""""""
.. important:: 
   This feature only supports modification of Portal settings in Designer.

Process developer can set up global settings of Portal in file ``portal_settings_config.properties`` which put in ``PortalKit``.

   .. code-block:: Python

      # This file is for developer configure values for Portal settings.
      # As developer, you only need to remove comment mark of corresponding settings and modify their values.
      # Portal will apply setting values once you start designer.

      # System tasks visibility
      #HIDE_SYSTEM_TASKS_FROM_HISTORY = true
      #HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR = false

      #System notes visibility
      #HIDE_SYSTEM_NOTES_FROM_HISTORY = true
      #HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR = false

      # Portal chat settings
      #ENABLE_GROUP_CHAT = false
      #ENABLE_PRIVATE_CHAT = false
      #CHAT_RESPONSE_TIMEOUT =
      #CHAT_MAX_CONNECTION = 3

      # Documents security settings
      #ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT = false
      #UPLOAD_DOCUMENT_WHITELIST_EXTENSION = doc, docx, xls, xlsx, xlsm, csv, pdf, ppt, pptx, txt, zip, jpg, jpeg, bmp, png

      # Time settings
      #HIDE_TIME = false
      #CLIENT_SIDE_TIMEOUT =
      #REFRESH_TASK_LIST_INTERVAL = 10000

      # Task/case counting functions
      #DISABLE_CASE_COUNT = false
      #DISABLE_TASK_COUNT = false

      # IFrame setting
      #EMBED_IN_FRAME = true
      
      # Logged in user format, available values: USERNAME, DISPLAY_NAME, DISPLAY_NAME_USERNAME, USERNAME_DISPLAYNAME
      #LOGGED_IN_USER_FORMAT = DISPLAY_NAME

      # Task/case detail document component setting
      #HIDE_TASK_DOCUMENT = false
      #HIDE_CASE_DOCUMENT = false
      
      # Available standard values: dashboard, process, task, case, statistics.
      # If you want your customized SubMenuIitem to be the default homepage, fill its name which is set in the overridden LoadSubMenuItems process.
      #DEFAULT_HOMEPAGE =

      # Others
      #SHOW_ENVIRONMENT_INFO = false
      #ENABLE_CASE_OWNER = false
      #EXPRESS_END_PAGE = true
      #HIDE_CHANGE_PASSWORD_BUTTON = false
      #HIDE_LOGOUT_BUTTON = false
      #HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE = false
      #HIDE_STATISTIC_WIDGET = false
      #ENABLE_USER_FAVORITES = true
      #DISPLAY_MESSAGE_AFTER_FINISH_TASK = true
      #SHOW_USER_GUIDE = true
      #SHOW_GLOBAL_SEARCH = true
      #SHOW_BUTTON_ICON = true

This file already contains list of settings with their default values.
These settings will be applied once you start Portal.

If you change configuration file while coding, please stop engine in designer and start engine again to apply changes.

.. _settings-language:

Language settings
=================

-  Below is applied language setting precedence:

   |language-precedence|

If user email language setting is application default, language would be applied by application email language setting.

If user selects a language in email setting, language would be appplied by this selection.

To configure languages of Portal applications, select ``My profile`` . 

Select one in dropdown list of ``Selected language``. After the change is saved, new language will be set for whole application.

   |language-settings|

-  For multiple languages, the CMS key ``/AppInfo/SupportedLanguages``
   must exist in your application. This CMS entry is
   in Portal Style. It contains list of all languages supported by
   your application, separated by comma.

   -  Must not contain spaces
   -  Same as display name of Locale
   -  Separated by comma
   -  Process model version, which has this CMS, must active

-  To add new language to Portal, what you have to do is

   -  Add new language locale to cms entry of Portal Style ``/AppInfo/SupportedLanguages`` 
   -  Export all CMS entries of Portal Style to excel file
   -  Add translation of new language for all CMS entries
   -  Import file excel back, then redeploy Portal Style
   -  This is sample how to add new Spanish to portal

      |add-new-language|

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

Permissions can be configured in `Cockpit
<https://developer.axonivy.com/doc/9.1/engine-guide/tool-reference/engine-cockpit/security.html>`_.
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
   ``PortalPermission.SHOW_ALL_TASKS_OF_CASE`` .

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

Permissions needed: ``IPermission.TASK_READ_ALL`` ,
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
 |           | Read, add, delete               | ``IPermission.USER_CREATE_ABSENCE`` and            |
 |           | absences of all users           | ``IPermission.USER_DELETE_ABSENCE`` and            |
 |           |                                 | ``IPermission.USER_READ_ABSENCES``                 |
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


Global variables
================

.. table:: 

   +---------------------------------------------+-------------+-----------------------------+
   | Variable                                    | Default     | Description                 |
   |                                             | value       |                             |
   +=============================================+=============+=============================+
   | PortalStartTimeCleanObsoletedDataExpression | 0 0 6 \* \* | Cron expression define      |
   |                                             | ?           | the time to clean up data   |
   |                                             |             | of obsoleted users. E.g.:   |
   |                                             |             | expression for at 6AM       |
   |                                             |             | every day is                |
   |                                             |             | ``0 0 6 * * ?`` . Refer     |
   |                                             |             | to                          |
   |                                             |             | `crontrigger <http://quar   |
   |                                             |             | tz-scheduler.org/document   |
   |                                             |             | ation/quartz-2.1.7/tutori   |
   |                                             |             | als/tutorial-lesson-06.htm  |
   |                                             |             | l>`__                       |
   |                                             |             | . Restart Ivy engine        |
   |                                             |             | after changing this         |
   |                                             |             | variable.                   |
   +---------------------------------------------+-------------+-----------------------------+
   | PortalDeleteAllFinishedHiddenCases          | false       | If set to ``true``, the     |
   |                                             |             | cron job runs daily (at     |
   |                                             |             | 6.AM as default) will       |
   |                                             |             | clean all finished hidden   |
   |                                             |             | cases in engine.            |
   |                                             |             | Otherwise, just hidden      |
   |                                             |             | cases which were            |
   |                                             |             | generated by Portal will    |
   |                                             |             | be deleted.                 |
   +---------------------------------------------+-------------+-----------------------------+
   | PortalGroupId                               | ch.ivyteam. | Maven group id of Portal.   |
   |                                             | ivy.project |                             |
   |                                             | .portal     |                             |
   +---------------------------------------------+-------------+-----------------------------+
   | PortalHiddenTaskCaseExcluded                | true        | By default, Portal will     |
   |                                             |             | query tasks and cases       |
   |                                             |             | which don't have hide       |
   |                                             |             | information. Set it to      |
   |                                             |             | ``false``, portal will      |
   |                                             |             | ignore this additional      |
   |                                             |             | property.                   |
   +---------------------------------------------+-------------+-----------------------------+
   |PortalLoginPageDisplay                       |true         |By default, Portal will      |
   |                                             |             |redirect to Login Page if    |
   |                                             |             |login is required and user   |
   |                                             |             |is unknown. Set it to false  |
   |                                             |             |to redirect to login error   |
   |                                             |             |page and hide Logout in      |
   |                                             |             |User menu when you are using |
   |                                             |             |external authentication and  |
   |                                             |             |the user is not created in   |
   |                                             |             |your application user list.  |
   +---------------------------------------------+-------------+-----------------------------+


.. |portal-header| image:: ../../screenshots/settings/user-settings.png
.. |global-settings| image:: ../../screenshots/settings/global-settings.png
.. |global-setting-edit| image:: ../../screenshots/settings/edit-global-settings.png
.. |language-precedence| image:: images/settings/language-precedence.png
.. |language-settings| image:: ../../screenshots/my-profile/my-profile.png
.. |add-new-language| image:: images/settings/add-new-language.png
.. |select-admin-settings| image:: ../../screenshots/settings/select-admin-settings.png

