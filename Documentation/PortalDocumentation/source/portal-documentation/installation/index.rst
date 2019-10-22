.. _axonivyportal.installation:

Installation
************

Release installation
====================

Installation
------------

The installation section describes all the steps, that are necessary to
install and setup the Process Application.

If you install the application for the first time than it's important to
start with the
`#axonivyportal.installation.basicinstallation <#axonivyportal.installation.basicinstallation>`__
. It describes all the initial steps, that must be done for the first
installation.

Release Installation
--------------------

If the application is already installed and initial prepared, than refer
to the Release Installation Steps, that are provided, here you will only
find those steps, that are necessary to install this release.

.. _axonivyportal.installation.basicinstallation:

Basic installation
==================

Project modules
---------------

The application consists of 5 process modules. For detailed information
of each module, refer to
`#axonivyportal.architecture <#axonivyportal.architecture>`__ .

-  PortalStyle

-  PortalKit

-  PortalTemplate

-  AxonIvyExpress

The project deployment of Ivy project are described in `project
deployment <http://developer.axonivy.com/doc/latest/EngineGuideHtml/administration.html#administration-deployment>`__
.

Server configuration
--------------------

-  The minimum required engine version is ``6.0.0.49863``

Specify applications used in Portal
-----------------------------------

.. _axonivyportal.installation.basicinstallation.specifyservers.generalConcept:

General concept
~~~~~~~~~~~~~~~

Portal has 2 different configurations:

-  Single mode
   : The working application must include portalKit, portalTemplate and
   portalStyle modules.
-  Multi applications mode
   : Multiple Portal applications on one engine. Each Portal application
   must include portalKit, portalTemplate and portalStyle modules.

..

   .. important::

      -  In multi applications mode, if you need overall dashboard, create
         the standard Portal application with the Portal modules.

Manually configure applications
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Refer
`#axonivyportal.settings.adminsettings.setupmultiportals <#axonivyportal.settings.adminsettings.setupmultiportals>`__
.

   .. important::

      The engine, installed in the demo mode, automatically deploys the
      Portal application with default users. You need to deploy it in
      production mode.

Default user credentials in demo mode
-------------------------------------

Portal provides 3 default users:

.. table:: Default user credentials

   +-----------------------+-----------------------+-----------------------+
   | Username              | Password              | Description           |
   +=======================+=======================+=======================+
   | admin                 | admin                 | This user has all     |
   |                       |                       | Portal permissions,   |
   |                       |                       | can access to Portal  |
   |                       |                       | Admin Settings.       |
   +-----------------------+-----------------------+-----------------------+
   | demo                  | demo                  | This user has         |
   |                       |                       | permission to manage  |
   |                       |                       | user absences.        |
   +-----------------------+-----------------------+-----------------------+
   | guest                 | guest                 | Default normal user   |
   |                       |                       | of portal.            |
   +-----------------------+-----------------------+-----------------------+

Role configuration
------------------

.. table:: Role configuration

   +-----------------------------------+-----------------------------------+
   | PortalKit roles                   | Rights                            |
   +===================================+===================================+
   | AXONIVY_PORTAL_ADMIN              | User belong to this role can      |
   |                                   | handle AdminUI page, configure    |
   |                                   | the internal role properties,     |
   |                                   | create public filters. Users who  |
   |                                   | own this role need some           |
   |                                   | permissions. See                  |
   |                                   | `#axonivyportal.installation.basi |
   |                                   | cinstallation.permissionsettings  |
   |                                   | <#axonivyportal.installation.basi |
   |                                   | cinstallation.permissionsettings> |
   |                                   | `__                               |
   |                                   | .                                 |
   +-----------------------------------+-----------------------------------+

.. _axonivyportal.installation.basicinstallation.permissionsettings:

Permission settings
-------------------

Absences
~~~~~~~~

-  READ

   This function will be disabled if session user does not have
   ``IPermission.USER_READ_OWN_ABSENCES`` and
   ``IPermission.USER_READ_ABSENCES``.

-  CREATE/MODIFY

   This function will be disabled if session user does not have
   ``IPermission.USER_CREATE_OWN_ABSENCE`` and
   ``IPermission.USER_CREATE_ABSENCE``.

-  DELETE

   This function will be disabled if session user does not have
   ``IPermission.USER_DELETE_OWN_ABSENCE`` and
   ``IPermission.USER_DELETE_ABSENCE``.

-  MANAGE EVERY USER ABSENCES

   User can read, add, delete absences of all users. This function will
   be disabled if session user does not have all of the following
   permissions: ``IPermission.USER_CREATE_ABSENCE`` ,
   ``IPermission.USER_DELETE_ABSENCE`` ,
   ``IPermission.USER_READ_ABSENCES``.

Substitute
~~~~~~~~~~

-  CREATE OWN SUBSTITUTE

   This function will be disabled if session user does not have
   ``IPermission.USER_CREATE_OWN_SUBSTITUTE`` and
   ``IPermission.USER_CREATE_SUBSTITUTE``.

-  MANAGE EVERY USER SUBSTITUTES

   This function will be disabled if session user does not have
   ``IPermission.USER_CREATE_SUBSTITUTE`` or
   ``IPermission.USER_READ_SUBSTITUTES``.

Personal task permission
~~~~~~~~~~~~~~~~~~~~~~~~

-  DELEGATE

   User can delegate his personal or group tasks if he has permission
   ``TaskWriteActivatorOwnTasks`` (This permission belongs to Portal
   permission group and it is not assigned to role Everybody by
   default). User can delegate all the tasks he see in his task list if
   he has permission ``IPermission.TASK_WRITE_ACTIVATOR`` .

      .. important::

         Task state cannot be one of the following:
         DONE, DESTROYED, RESUMED, FAILED.

   This function will be hidden if session user does not have permission
   ``PortalPermission.TASK_DISPLAY_DELEGATE_ACTION`` .

-  ADD NOTE

   No permission requires.

      .. important::

         Task state cannot be one of the following:
         DONE, DESTROYED, RESUMED, FAILED .

-  RESET

   This function will be enabled if session user has permission
   ``IPermission.TASK_RESET_OWN_WORKING_TASK`` or
   ``IPermission.TASK_RESET`` .

      .. important::

         Task state has to be one of following:
         RESUMED
         ,
         PARKED
         .

   This function will be hidden if session user does not have permission
   ``PortalPermission.TASK_DISPLAY_RESET_ACTION`` .

-  RESERVE

   This function will be enabled if session user has permission
   ``IPermission.TASK_PARK_OWN_WORKING_TASK`` .

      .. important::

         Task state has to be
         RESUMED
         .

   This function will be hidden if session user does not have permission
   ``PortalPermission.TASK_DISPLAY_RESERVE_ACTION`` .

-  CHANGE TASK NAME

   This function will be enabled if session user has
   ``IPermission.TASK_WRITE_NAME`` .

      .. important::

         Task state cannot be one of following values:
         DONE
         ,
         DESTROYED
         ,
         FAILED
         .

-  CHANGE TASK DESCRIPTION

   This function will be enabled if session user has
   ``IPermission.TASK_WRITE_DESCRIPTION`` .

      .. important::

         Task state cannot be one of following values:
         DONE
         ,
         DESTROYED
         ,
         FAILED
         .

-  CHANGE DEADLINE

   This function will be enabled if session user has
   ``IPermission.TASK_WRITE_EXPIRY_TIMESTAMP`` .

      .. important::

         Task state cannot be one of following values:
         DONE
         ,
         DESTROYED
         ,
         FAILED
         .

-  CHANGE PRIORITY

   This function will be disabled if session user does not have
   ``IPermission.TASK_WRITE_ORIGINAL_PRIORITY`` .

      .. important::

         Task state cannot be one of following:
         DONE
         ,
         DESTROYED
         ,
         FAILED
         .

-  DISPLAY ADDITIONAL OPTIONS

   This function will be hidden if session user does not have permission
   ``PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS`` .

Personal case permission
~~~~~~~~~~~~~~~~~~~~~~~~

-  ADD NOTE

   Add note function will be enabled if case state is ``RUNNING`` .

-  DELETE CASE

   Delete case function will be enabled if session user has
   ``IPermission.CASE_DESTROY`` .

      .. important::

         Case state has to be
         RUNNING
         .

-  CHANGE CASE NAME

   Delete case function will be enabled if session user has
   ``IPermission.CASE_WRITE_NAME`` .

      .. important::

         Case state cannot to be:
         DESTROYED
         .

-  CHANGE CASE DESCRIPTION

   Delete case function will be enabled if session user has
   ``IPermission.CASE_WRITE_DESCRIPTION`` .

      .. important::

         Case state cannot to be:
         DESTROYED
         .

-  SEE RELATED TASKS OF CASE

   Session user can see all related tasks of case if he has
   ``IPermission.TASK_READ_OWN_CASE_TASKS`` or
   ``IPermission.TASK_READ_ALL`` .

      .. important::

         Case state cannot to be:
         DESTROYED
         .

   Link to show all tasks of case will be hidden if session user does
   not have permission ``PortalPermission.SHOW_ALL_TASKS_OF_CASE`` .

-  DISPLAY SHOW DETAILS LINK

   This link will be hidden if session user does not have permission
   ``PortalPermission.SHOW_CASE_DETAILS`` . This permission is not
   assigned to role Everybody by default.

Upload/delete document permission
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Upload/delete document function will be enabled if session user has
``IPermission.DOCUMENT_WRITE`` or
``IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE``.

Express Workflow permission
~~~~~~~~~~~~~~~~~~~~~~~~~~~

-  CREATE EXPRESS WORKFLOW

   Create Express Workflow function will be enabled if session user has
   ``PortalPermission.EXPRESS_CREATE_WORKFLOW`` (This permission belongs
   to Portal permission group, assigned to role Everybody by default).

Statistics permission
~~~~~~~~~~~~~~~~~~~~~

-  ADD DASHBOARD CHART

   Add new charts function will be enabled if session user has
   ``PortalPermission.STATISTIC_ADD_DASHBOARD_CHART`` (This permission
   belongs to Portal permission group, assigned to role Everybody by
   default).

-  ANALYZE TASK

   Filter tasks and export data to excel for advanced analysis. This
   function will be enabled if session user has
   ``PortalPermission.STATISTIC_ANALYZE_TASK`` (This permission belongs
   to Portal permission group and it is not assigned to role Everybody
   by default).

Portal general permission
~~~~~~~~~~~~~~~~~~~~~~~~~

-  ACCESS TO FULL PROCESS LIST

   User cannot see "Processes" on the left menu and link "Show all
   processes" (on Dashboard) if he does not have permission
   ``PortalPermission.ACCESS_FULL_PROCESS_LIST`` .

-  ACCESS TO FULL TASK LIST

   User cannot see "Tasks" on the left menu and link "Show full task
   list" (on Dashboard) if he does not have permission
   ``PortalPermission.ACCESS_FULL_TASK_LIST`` .

-  ACCESS TO FULL CASE LIST

   User cannot see "Cases" on the left menu if he does not have
   permission ``PortalPermission.ACCESS_FULL_CASE_LIST`` .

-  ACCESS TO FULL STATISTIC LIST

   User cannot see "Statistics" on the left menu and link "Show all
   charts" (on Dashboard) if he does not have permission
   ``PortalPermission.ACCESS_FULL_STATISTICS_LIST`` .

-  DISPLAY ADD NOTE BUTTON

   This button will be hidden if session user does not have permission
   ``PortalPermission.TASK_CASE_ADD_NOTE`` .

-  DISPLAY SHOW MORE NOTE BUTTON

   This button will be hidden if session user does not have permission
   ``PortalPermission.TASK_CASE_SHOW_MORE_NOTE`` .

Administrator permission can see all tasks/cases in the application
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Normal users can only see their tasks/cases they can work on.

Administrator can see all tasks/cases in the application.

Permissions needed: ``IPermission.TASK_READ_ALL`` ,
``IPermission.CASE_READ_ALL`` .

Administrator permission can interact with all workflows in the application
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Normal users can updates and deletes workflows which created by him and
can interact with workflow's task which assigned to him.

Administrator can creates, updates and deletes all workflows in the
application.

Global variables
----------------

.. table:: Global variables

   +---------------------------+-------------+---------------------------+
   | Variable                  | Default     | Description               |
   |                           | value       |                           |
   +===========================+=============+===========================+
   | PortalStartTimeCleanObsol | 0 0 6 \* \* | Cron expression define    |
   | etedDataExpression        | ?           | the time to clean up data |
   |                           |             | of obsoleted users. E.g.: |
   |                           |             | expression for at 6AM     |
   |                           |             | every day is              |
   |                           |             | ``0 0 6 * * ?`` . Refer   |
   |                           |             | to                        |
   |                           |             | `crontrigger <http://quar |
   |                           |             | tz-scheduler.org/document |
   |                           |             | ation/quartz-2.1.x/tutori |
   |                           |             | als/crontrigger>`__       |
   |                           |             | . Restart Ivy engine      |
   |                           |             | after changing this       |
   |                           |             | variable.                 |
   +---------------------------+-------------+---------------------------+
   | PortalDeleteAllFinishedHi | false       | If set to ``true``, the   |
   | ddenCases                 |             | cron job runs daily (at   |
   |                           |             | 6.AM as default) will     |
   |                           |             | clean all finished hidden |
   |                           |             | cases in engine.          |
   |                           |             | Otherwise, just hidden    |
   |                           |             | cases which were          |
   |                           |             | generated by Portal will  |
   |                           |             | be deleted.               |
   +---------------------------+-------------+---------------------------+
   | PortalGroupId             | ch.ivyteam. | Maven group id of Portal. |
   |                           | ivy.project |                           |
   |                           | .portal     |                           |
   +---------------------------+-------------+---------------------------+
   | PortalHiddenTaskCaseExclu | true        | By default, Portal will   |
   | ded                       |             | query tasks and cases     |
   |                           |             | which don't have hide     |
   |                           |             | information. Set it to    |
   |                           |             | ``false``, portal will    |
   |                           |             | ignore this additional    |
   |                           |             | property.                 |
   +---------------------------+-------------+---------------------------+

Look and feel
-------------

Portal doesn't use `Modena <http://www.primefaces.org/eos/modena/>`__
theme from version 6.3.

-  Yes/Ok buttons on the left, No/Cancel buttons on the right

.. _axonivyportal.installation.migrationnotes:

Migration notes
===============

This document informs you in detail about incompatibilities that were
introduced between Portal versions and tells you what needs to be done
to make your existing Portal working with current Axon.ivy engine.

How to migrate
--------------
   
   .. important:: 
   
      If you call any Portal API which is not mentioned in document. It
      could be changed or removed. Re-implement it in your own project.

      In order to migrate Portal, you need to migrate Axon.ivy, refer
      `Axon.ivy migration
      notes <https://developer.axonivy.com/doc/latest/MigrationNotes.html>`__.
      Changes in Axon.ivy could lead to problems if customer project is not
      migrated properly.

In designer
~~~~~~~~~~~

1. Replace all Portal projects
2. Update PortalTemplate dependency of customer project in pom.xml.
3. If PortalStyle is customized, copy logo, customization.scss,
   font-faces.scss, customized stuff from old to new PortalStyle, run
   maven to compile CSS.
4. Follow migration notes.
5. If customization needs copying code from Portal, merge changes
   between 2 version of Portal for copied code.

..

   .. important::

      -  Scenario migrating one customer project without customization:
         Follow guidelines to step 2.
      -  Scenario migrating one customer project with supported
         customization: Follow the guidelines.
      -  Scenario migrating one customer project with (un)supported
         customization: Follow guidelines for supported customization. If
         unsupported customization needs copying code from Portal, merge
         changes between 2 versions of Portal for copied code. Take care
         your own unsupported customization.

In engine
~~~~~~~~~

1. Convert database schema if needed.
2. If your ivy version is before 7.3.0 : deactivate standard Portal
   application if it's not needed.
3. Redeploy Portal projects (exclude PortalConnector) and customer
   project.
4. Follow migration notes to migrate data, if any.

.. _axonivyportal.installation.migrationnotes.8.0.0:

Migrate to 8.0.0
----------------

How to convert `LESS <http://lesscss.org>`__ to `SASS <https://sass-lang.com/>`__ languages
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

From Portal version 8.0.0, we use the Serenity theme as the default
style for project. So, if your project is using ``LESS`` languages for
customizing style, we should do one more step as convert them to a new
format as ``SASS``. Otherwise, please ignore this step

Please follow below step to do automation step convert your less file by
plugin ``less2sass.``

The **less2sass** converter is pretty good and maintained
https://www.npmjs.com/package/less2sass.

-  Install
   NoteJS
   on your system (can get by this page
   ). Once installed, restart your system as well.
-  Open Cmd command line and run command:
   npm install -g less2sass
-  Once installed you can simply run:
   ``less2sass 'path_to_less_file_or_directory'``

   E.g for path_to_less_file_or_directory:

   ``C:\Projects\Portal\axonivyportal\AxonIvyPortal\PortalStyle\webContent\resources\less\customization.less``

-  After run above command, you also see a new
   \*.scss
   file is created into same folder.
   ``C:\Projects\Portal\axonivyportal\AxonIvyPortal\PortalStyle\webContent\resources\less\customization.scss``

   Copy ``*.scss`` files to new folder as
   ``..\webContent\resources\sass\ivy``

-  Run
   mvn libsass:compile
   to compile your
   scss
   to
   css
   file.

.. _axonivyportal.installation.migrationnotes.8.0.0.taskbody:

How to migrate TaskBody to `TaskItemDetails <#axonivyportal.customization.taskitemdetails>`__ component
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

On Portal version 8.0.0, we removed ``taskBody`` in TaskWidget. Instead
of that, we will use TaskItemDetails component to show task information
with more details and responsiveness.

If you have customized ``taskBody`` of TaskWidget, we need to migrate
the code of ``taskBody`` to new component as ``TaskItemDetails``

Please follow the below steps to migrate

-  You can take a look at ``PortalTaskDetails.xhtml`` to see how to use
   and customize ``TaskItemDetails``.

   There are 2 sections we need to take a look:

   -  On the ``taskItemDetailCustomPanelTop`` section.

      This section will be shown on the top ``TaskItemDetails``
      component. You can change the width of this panel as your
      requirement, we recommend to use ``ui-g-*`` class of ``Primeface``
      to define size of the width for the box can display flexibility.

   -  On the ``taskItemDetailCustomPanelBottom`` section.

      This section will be shown on the bottom of the
      ``TaskItemDetails`` component. You can change the width of this
      panel as your requirement, we recommend to use ``ui-g-*`` class of
      ``Primeface`` to define size of the width for the box can display
      flexibility.

   -  After deciding where we will push the custom code to
      ``TaskItemDetails``.

      Move your customized code for Custom box section from old
      ``taskBody`` to under that sections.

      Finally, your customization will be shown in the
      ``TaskItemDetails``.

   -  For example:

      Old taskBody

      TaskItemDetail content

-  In case we need to hide Notes, Documents, we can refer to `Show/hide
   component on Task Item
   Details <#axonivyportal.customization.taskitemdetails.howtooverideui.showhiddenui>`__

-  Additional, if we want to customize more ``TaskItemDetails``
   components, please refer to `TaskItemDetails
   component <#axonivyportal.customization.taskitemdetails.howtooverideui>`__.

.. _axonivyportal.installation.migrationnotes.8.0.0.casebody:

How to migrate CaseBody to `CaseItemDetails <#axonivyportal.customization.caseitemdetails>`__ component
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

On Portal version 8.0.0, we removed ``caseBody`` in CaseWidget. Instead
of that, we are using CaseItemDetails component for showing case
information with more details and responsiveness.

If you have customized ``caseBody`` of CaseWidget, we need to migrate
the code of ``caseBody`` to new component as ``CaseItemDetails``

Please follow below check list to migrate

-  You can take a look at ``PortalCaseDetails.xhtml`` to see how to use
   and customize ``CaseItemDetails``.

   There are 3 sections we need to take a look:

   -  On the ``caseItemDetailCustomTop`` section.

      This section will be shown on the top of the ``CaseItemDetails``
      component. You can define the width of this panel as your
      requirement, we recommend to use ``ui-g-*`` class of ``Primeface``
      to define size of the width for the box can display flexibility.

   -  On the ``caseItemDetailCustomMiddle`` section.

      This section will be shown on the middle of the
      ``CaseItemDetails`` component. You can define the width of this
      panel as your requirement, we recommend to use ``ui-g-*`` class of
      ``Primeface`` to define size of the width for the box can display
      flexibility.

   -  On the ``caseItemDetailCustomBottom`` section.

      This section will be shown on the bottom of the
      ``CaseItemDetails`` component. You can define the width of this
      panel as your requirement, we recommend to use ``ui-g-*`` class of
      ``Primeface`` to define size of the width for the box can display
      flexibility.

   -  After decided where we will push the custom code to
      ``CaseItemDetails``.

      Move your customized code for Custom box section from old
      ``caseBody`` to under that sections.

      Finally, your customization will be shown in ``CaseItemDetails``.

   -  For example:

      Old caseBody

      CaseItemDetail content

-  In case we need to hide Notes, Documents, Related running component,
   we can refer to `Show/hide component on Case Item
   Details <#axonivyportal.customization.caseitemdetails.howtooverideui.showhiddenui>`__

-  Additional, if we want to customize more ``CaseItemDetails``
   component, please help refer to `CaseItemDetails
   component <#axonivyportal.customization.caseitemdetails.howtooverideui>`__

Migrate to 7.4.0
----------------

From 7.4.0, CaseTemplate is deprecated and we don't support it anymore.
If you are using CaseTemplate, please do consider to migrate to
TaskTemplate manually.

Migrate to 7.3.0
----------------

From 7.3.0, Portal supports some permissions to show/hide left menu
item, if you override ``LoadSubMenuItems`` process and want to use these
permissions, refer to
`#axonivyportal.customization.menu.customization <#axonivyportal.customization.menu.customization>`__
for more detail.

There is a small change when initializing statistic chart, so if you
override ``DefaultChart.mod``, have a look at its note to see what is
changed.

Portal connector is removed, so there are many things related to it must
be adjusted. Check this list below

-  All
   Remote\*
   classes are removed, replaced by the Ivy classes: ICase, ITask,
   IUser, IApplication, etc..
-  Use
   BuildTaskQuery
   and
   BuildCaseQuery
   callable process instead of
   BuildTaskJsonQuery
   and
   BuildCaseJsonQuery
   .
-  If you override TaskLazyDataModel, remove
   extendSortTasksInNotDisplayedTaskMap
   method. Use
   criteria
   field instead of
   queryCriteria
   or
   searchCriteria
   , use
   adminQuery
   field instead of
   ignoreInvolvedUser
   .
-  If you override CaseLazyDataModel: remove
   extendSortCasesInNotDisplayedTaskMap
   method. Use
   criteria
   field instead of
   queryCriteria
   or
   searchCriteria
   , use
   adminQuery
   field instead of
   ignoreInvolvedUser
   .
-  If you override ChangePassword.mod: change process call from
   MultiPortal/PasswordService:changePasswordService(String,String)
   to
   Ivy Data Processes/PasswordService:changePassword(String,String)
   .

Migrate hidden task and case to 7.3.0
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Portal 7.0.10 has option to store hidden information in custom field of
task and case instead of additional property for better performance.
Other versions of Portal store these info in additional property.

If you use hide task/case feature, you need to follow these steps:

1. Deploy this project
   MigrateHiddenTaskCase.iar
   to all your portal applications.
2. In each application, run start process
   MigrateHiddenTaskCase
   to migrate.
3. It's optional to clean up redundant data. After migration finishes
   successfully, run start process
   RemoveHideAdditionalProperty
   in each application to clean up HIDE additional property. It will
   delete HIDE additional property of all tasks and cases in current
   application, so be careful with it.

Migrate 7.1.0 to 7.2.0
----------------------

Portal needs `Apache POI <https://poi.apache.org>`__ for exporting to
Excel features.

If you override task widget's data query described at
`#axonivyportal.customization.taskwidget.howtooverridedataquery <#axonivyportal.customization.taskwidget.howtooverridedataquery>`__,
follow these steps to migrate

-  Add new start method with signature
   buildTaskJsonQuery(Boolean)
   in your overridden file of BuildTaskJsonQuery.mod (refer to original
   file BuildTaskJsonQuery.mod).
-  If you customized
   TaskLazyDataModel
   , change
   withStartSignature("buildTaskJsonQuery()
   to
   withStartSignature("buildTaskJsonQuery(Boolean)").withParam("isQueryForHomePage",
   compactMode)
   in your customized
   TaskLazyDataModel
   class.

There are some changes (DefaultApplicationHomePage, DefaultLoginPage,
GlobalSearch) in PortalStart process of Portal Template. If you have
customized this process in your project, copy the new PortalStart from
Portal Template to your project and re-apply your customization.

   .. important::

      In case you already have PortalStart process in your project, delete
      all elements in that process and copy everything from PortalStart
      process of Portal Template (to prevent start link id change).
      ``Do not delete`` PortalStart proccess in your project and copy new
      again.

      Check map param result of callable process after copy to make sure
      it's the same as original process.

EXPIRY_CHART_LAST_DRILLDOWN_LEVEL global variable is removed. User now
can use a context menu to drilldown Task By Expiry chart.

Migrate 7.0.3 to 7.0.5 (or 7.1.0)
---------------------------------

There are some changes in PortalStart process of Portal Template. If you
have customized this process in your project, copy the new PortalStart
from Portal Template to your project and re-apply your customization.

We introduce new method
``findStartableLinkByUserFriendlyRequestPath(String requestPath)`` in
``ProcessStartCollector`` class. If your project has customized
`#axonivyportal.customization.defaultuserprocess <#axonivyportal.customization.defaultuserprocess>`__,
use this method to generate link to your process. If user doesn't have
permission to start the process, this method will return empty string.

   .. important::

      In case you already have PortalStart process in your project, delete
      all elements in that process and copy everything from PortalStart
      process of Portal Template (to prevent start link id change).
      ``Do not delete`` PortalStart proccess in your project and copy new
      again.

      Check map param result of callable process after copy to make sure
      it's the same as original process.

Migrate 7.0.2 to 7.0.3
----------------------

If you have additional columns in your customized task widget, refer
`Task
widget <#axonivyportal.customization.taskwidget.howtooverideui.taskheader>`__
to adapt your customization in ``taskHeader`` section.

Migrate 7.0.1 to 7.0.2
----------------------

In PortalStyle\pom.xml, update project-build-plugin version to
``7.1.0``\ and run maven to compile CSS.

If changing password is customized, change method call
``PasswordService.mod#changePassword(String,String)`` to
``PasswordService.mod#changePasswordService(String, String)`` in this
customization.

Custom fields in Portal task list can now be sorted properly. The method
``extendSort()`` of ``TaskLazyDataModel`` is changed to have a
``taskQuery`` parameter. If you override this method, change your code
to use the new parameter instead of using the ``criteria`` taskQuery.

Portal does not have separate full task list in the homepage anymore.
It's mean that you don't have to customize the task list in
``/layouts/DefaultHomePageTemplate.xhtml``. You can remove your task
list customization code in ``PortalHome.xhtml``.

If you have added new language to Portal by adding cms entry
``/AppInfo/SupportedLanguages`` in your project. Move this entry to
Portal Style.

Migrate 7.0.0 to 7.0.1
----------------------

**Ajax error handling**: By default, Portal handles all exceptions from
ajax requests. Old configuration, customization of ajax error handling
should be removed.

Migrate 6.x to 7.0.0
--------------------

If you copy the ``PortalStart`` process or the ``PortalHome`` HTMLDialog
for customizations, adapt the changes:

-  The whole process is refactored to be clearer. So it is recommended
   that you copy it again.

-  New process is introduced: restorePortalTaskList.ivp

-  PortalStart: some new ivy scripts are added to handle the navigation
   back to the same page before starting a task.

-  PortalHome: The ``taskView`` parameter is added to the start method.

SQL conversion
~~~~~~~~~~~~~~

From Portal ``7.0`` , we use standard axon.ivy Task Category field to
store task category.

To migrate task categories, deploy
`MigrateTaskCategorySample.iar <documents/MigrateTaskCategorySample.iar>`__
to your application and run ``Migrate Task Category`` process to:

1. Migrate data from column ``customVarCharField5`` to ``category`` for
   all tasks in the application.

2. Delete leftover data in ``customVarCharField5`` of all tasks in the
   application.

3. Create CMS entries for task categories in the application.

If you have queries which referring to task category, plese replace
``customVarCharField5()`` part with ``category()`` part.

Migrate 6.4 or 6.5 to 6.6
-------------------------

-  Task header is supported to be customized. The
   useOverride
   param, which is used to override the task item's body, is changed to
   useOverrideBody
-  If you customize
   TaskLazyDataModel
   , remove that customized class and customize as
   .

Migrate 6.4 to 6.5
------------------

-  If compilation error "The type org.apache.axis2.databinding.ADBBean
   cannot be resolved" occurs, refer
   Project compilation classpath
   to fix.
-  The relative link in default user processes starts with ivy context
   path instead of "pro". If there are customized default user proceses,
   append context path at the beginning. E.g. in Portal
   6.4
   , it is /pro/.../PortalStart.ivp. In Portal
   6.5
   , change it to /ivy/pro/.../PortalStart.ivp. You may use :
   ivy.html.startref(...)
   or
   RequestUriFactory.createProcessStartUri(...)
   to generate links.

Migrate 6.x (x < 4) to 6.4 (Jakobshorn)
---------------------------------------

Portal appearance
~~~~~~~~~~~~~~~~~

Portal ``6.4`` are redesigned. Therefore many components look different
from the previous version like menu, task list, case list ... . Portal
``BasicTemplate`` does not use ``p:layout`` and ``p:layoutUnit``
anymore. You may need to adapt your pages to this change.

For now the menu customization is not supported.

From ``6.4`` , Portal applies `LESS <http://lesscss.org/>`__ to support
customizing Portal styles. You can customize colors, fonts and Portal's
component styles. For more information about customizing Portal's style
with LESS, refer to
`#axonivyportal.customization.portallogosandcolors <#axonivyportal.customization.portallogosandcolors>`__
.

Steps to migrate

1. Copy PortalStyle/webContent/resources of Portal ``6.4`` to
   PortalStyle/webContent/resources of the current Portal.

2. Modify PortalStyle/webContent/resources/less/theme.less, update value
   of @body-background-color for the background color and @menu-color
   for the menu, button color.

3. Put custom styles to
   PortalStyle/webContent/resources/less/customization.less.

4. Add properties and plugins which are defined in PortalStyle/pom.xml
   of Portal ``6.4`` to PortalStyle/pom.xml of the current Portal.

5. Run the maven command ``mvn lesscss:compile`` in PortalStyle to build
   CSS file.

6. PortalStyle/webContent/resources/css/theme.css is obsolete, remove
   it.

Migrate 5.0 (Rothorn) to 6.0 (Säntis)
-------------------------------------

Database conversion
~~~~~~~~~~~~~~~~~~~

If you are using Portal ``5.0`` , you have to manual configure all
settings (create servers, applications, variables) again since Portal
now doesn't use external database. All settings on from Portal ``6.0``
are stored in Ivy system database. If you are using Portal ``6.0`` , you
don't need to convert database.

Portal appearance
~~~~~~~~~~~~~~~~~

Portal now doesn't use `Modena <http://primefaces.org/eos/modena>`__
theme, it's a big difference to previous ``6.0`` . Therefore many things
in Portal ``5.0`` and ``6.0`` will not look the same in new Portal. Many
things have been redesigned like menu, task list, case list ...

.. _axonivyportal.installation.releasenotes:

Release notes
=============

This part lists all relevant changes since the last official product
releases of Axon.ivy.

Changes in 8.0
--------------

-  Upgraded to Serenity's theme, refer to `Migration
   Notes <#axonivyportal.installation.migrationnotes.8.0.0>`__ for more
   details

-  Remove ``caseBody`` inside CaseWidget, refer to `Migration
   Notes <#axonivyportal.installation.migrationnotes.8.0.0.casebody>`__
   for more details

-  Introduce new actions button on `CaseWidget's
   header <#axonivyportal.customization.casewidget.howtooverideui.caseheader>`__.

-  Introduce new page as the Case item details. The default page is
   portal case details, refer to
   `#axonivyportal.customization.caseitemdetails <#axonivyportal.customization.caseitemdetails>`__
   for the customization.

-  Remove ``taskBody`` inside TaskWidget, refer to `Migration
   Notes <#axonivyportal.installation.migrationnotes.8.0.0.taskbody>`__
   for more details

-  Introduce two new actions button on `TaskWidget's
   header <#axonivyportal.customization.taskwidget.howtooverideui.taskheader>`__.

-  Introduce new page as Task item details. The default page is portal
   task details, refer to
   `#axonivyportal.customization.taskitemdetails <#axonivyportal.customization.taskitemdetails>`__
   for the customization.

-  Task list customization now support responsiveness. refer to `this
   part <#axonivyportal.customization.taskwidget.responsivelayout>`__
   for more detail.

-  Case list customization now support responsiveness. refer to `this
   part <#axonivyportal.customization.casewidget.responsivelayout>`__
   for more detail.

Changes in 7.4
--------------

-  New Portal Chat is introduced, now Portal supports Group chat and
   Private chat, refer to
   `#axonivyportal.components.portalchat <#axonivyportal.components.portalchat>`__
   for more detail

-  Portal group id is officially configurable, refer to
   `#axonivyportal.customization.changegroupid <#axonivyportal.customization.changegroupid>`__
   for more detail

-  CaseTemplate is removed, from now on we only use TaskTemplate. Please
   refer to
   `#axonivyportal.installation.migrationnotes <#axonivyportal.installation.migrationnotes>`__
   to see how to migrate CaseTemplate to TaskTemplate

Changes in 7.3
--------------

-  Remove PortalConnector, query data via Ivy API directly to increase
   performance, refer to Migration Notes

-  Provide the mobile pages. The default page is task list, refer to
   `#axonivyportal.customization.mobiledefaultpage <#axonivyportal.customization.mobiledefaultpage>`__
   for the customization.

-  Provide more permissions to show/hide menu, button and link in
   Portal, refer to
   `#axonivyportal.installation.basicinstallation.permissionsettings <#axonivyportal.installation.basicinstallation.permissionsettings>`__
   for more detail.

-  Hide Statistic widget can be configured in Admin setting.

-  Hide technical task / case can be configured using additional
   property or custom field (more performance).

Changes in 7.2
--------------

-  Introduce variables to customize task priority and state colors and
   header bar colors

-  Introduce new page: Global search result, and supports the
   customization

-  Override DefaultApplicationHomePage.ivp, DefaultLoginPage.ivp,
   DefaultEndPage.ivp processes, refer to `Replacement
   Project <https://developer.axonivy.com/doc/latest/EngineGuideHtml/administration.html#ServerAdministration-htmlworkflowui>`__,
   check migration notes if you have the customized PortalStart.ivp
   process.

-  Check permission when upload/delete document. User needs permission
   ``IPermission.DOCUMENT_WRITE`` or
   ``IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE`` to upload/delete
   document.

-  Support disable upload/delete document when a case is done. This
   function can be configured by ``HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE``
   setting.

-  Support configure upload file extension whitelist. Only file
   extensions appear in this list are allowed to upload to Portal. This
   function can be configured by ``UPLOAD_DOCUMENT_WHITELIST_EXTENSION``
   setting.

-  Support script checking function for upload file. You can
   enable/disable this function by configuring
   ``ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT`` setting.

Changes in 7.1
--------------

-  Support client side timeout: informs user when session is about to
   expire and auto logout when expired.

-  Hide technical cases (the HIDE additional property is set), so that
   they and their related task are not displayed in any Portal case
   lists.

-  More search criteria for user in Case list are added and allowed to
   customize.

-  User can add new language. Refer to
   `#axonivyportal.settings.languagesettings <#axonivyportal.settings.languagesettings>`__
   for detail.

-  Axon ivy express has custom end page. It can be turned off or
   customized.

-  User can create default start process with permission check. If the
   user doesn't have permission to start the process, it won't appear in
   favorite processes. Refer to
   `#axonivyportal.customization.defaultuserprocess <#axonivyportal.customization.defaultuserprocess>`__
   for detail.

Changes in 7.0 (Jakobshorn)
---------------------------

-  More search criteria for user in Task list are added and allowed to
   customize.

-  Task delegate customization is supported

-  The same task list is displayed before and after a task. Set default
   end page to another project to remove this feature.

-  Task category of Portal is now stored in new Task category field of
   ivy.

   Refer to
   `#axonivyportal.installation.migrationnotes <#axonivyportal.installation.migrationnotes>`__
   to learn how to migrate data from ``customVarCharField5`` to new
   ``category`` field.

-  Hide technical tasks (the HIDE additional property is set), so that
   they are not displayed in any Portal task lists.

-  Change password is supported to be customized. Refer to
   `#axonivyportal.customization.changepasswordprocess <#axonivyportal.customization.changepasswordprocess>`__
   to know how to customize this feature.

Changes in 6.6 (Jakobshorn)
---------------------------

-  Task widget's customization is extended with task header and task
   data query.

-  Hide technical roles (the HIDE property is set), so that they are not
   displayed anywhere (e.g. delegate, absence mgmt). The default hidden
   role is AXONIVY_PORTAL_ADMIN

Changes in 6.0 (Säntis)
-----------------------

-  Portal has 2 level menu with animation.

-  All components such as button, text field ...have been re-styled, not
   applied Modena's styles.

-  Support responsiveness with 3 screen widths: 1920, 1366 and 1024.
   Refer to
   `#axonivyportal.components.layouttemplates.reponsiveness <#axonivyportal.components.layouttemplates.reponsiveness>`__
   for more details.

-  Some customizations are not supported in this release: main menu,
   case header.
