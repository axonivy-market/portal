.. _list-permissions:

Permission List
===============

Comprehensive reference of all Portal permissions. Use this list to assign least-privilege access and understand which features each permission controls.
Proper permission configuration ensures Portal security aligns with your organization's access control policies.

**Permission Architecture:**

Portal's permission system extends Axon Ivy Engine's core security model with Portal-specific permissions. All permissions are configured in the Engine Cockpit under the PortalPermissions section and can be assigned to:

- **Roles**: Grant permissions to all users with specific roles (e.g., "Manager", "Employee")
- **Individual Users**: Grant permissions to specific user accounts (prefix with ``#``)
- **Combinations**: Mix role-based and user-specific permissions for granular control

**How to Use This Reference:**

#. Find the permission category matching your use case
#. Locate the specific permission you need to configure
#. Note the permission name (used in Engine Cockpit configuration)
#. Assign permission to appropriate roles or users
#. Test with users from different roles to verify behavior

**Configuration Location:**

All permissions are configured in the :doc-url:`Engine Cockpit </engine-guide/reference/engine-cockpit/security.html>` under Security > PortalPermissions. 
For detailed configuration instructions and examples, see :ref:`Permission Settings <settings-permission-settings>`.

**Best Practices:**

- **Start with Roles**: Assign permissions to roles rather than individual users for easier maintenance
- **Principle of Least Privilege**: Grant only the permissions users need for their work
- **Test Thoroughly**: Verify permission configurations with users from different roles
- **Document Decisions**: Keep track of why specific permissions were granted or denied

**Related Sections:**

- :ref:`Settings <settings-permission-settings>` - Permission configuration examples and detailed explanations
- :ref:`Customization <customization>` - Build custom permission-based features

Overview
--------

Portal has a flexible security system that allows you to configure who can access applications and what they can do/see in Portal.

.. table:: Permission Categories

   +------------------------------------------+-------------------------------------------------------+
   | Category                                 | Description                                           |
   +==========================================+=======================================================+
   | :ref:`permission-task-permissions`       | Control task visibility, actions, and modifications   |
   +------------------------------------------+-------------------------------------------------------+
   | :ref:`permission-case-permissions`       | Control case visibility, actions, and business details|
   +------------------------------------------+-------------------------------------------------------+
   | :ref:`permission-general-permissions`    | Dashboard, document, process list, and role access    |
   +------------------------------------------+-------------------------------------------------------+
   | :ref:`permission-absence-substitute`     | Absence and substitute management permissions         |
   +------------------------------------------+-------------------------------------------------------+

.. important::
   **Portal Permission Support:**
   
   The Portal is built as a layer above the Axon Ivy Engine core. Not every core engine permission is automatically honored or supported by the Portal.
   If you require a specific engine permission not currently supported by the Portal, please contact Axon Ivy support.

.. note::
   **Permission Types in this Documentation:**
   
   - **Portal Permissions** - Custom permissions defined by Portal (e.g., DashboardWriteOwn, ShareTaskDetailsLink, NewsManagement)
   - **Engine Permissions** - Core Axon Ivy permissions that Portal respects (see list at end of this page)
   
   Permissions marked with "Granted to role Everybody by default" are automatically assigned when Portal is installed.

.. _permission-task-permissions:




Portal Task Permissions
-----------------------

Permissions controlling task visibility, actions, and property modifications.

**Task Visibility**

.. _TaskReadAll:

   :ref:`🔑TaskReadAll <TaskReadAll>`
     - View all tasks in the system regardless of assignment
     - Typically granted to administrators

.. _SystemTaskReadAll:

   :ref:`🔑SystemTaskReadAll <SystemTaskReadAll>`
     - View system tasks (background/automated tasks)
     - Required for debugging and system monitoring

.. _TaskReadOwnCaseTasks:

   :ref:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>`
     - View tasks related to cases where user is involved
     - Granted to role Everybody by default

**Task Actions**

.. _TaskParkOwnWorkingTask:

   :ref:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>`
     - Reserve (park) own working tasks
     - Allows users to temporarily set aside tasks they're working on
     - Granted to role Everybody by default

.. _TaskResetOwnWorkingTask:

   :ref:`🔑TaskResetOwnWorkingTask <TaskResetOwnWorkingTask>`
     - Reset own working tasks to their initial state
     - Only works for tasks in states: RESUMED, PARKED, READY_FOR_JOIN, FAILED
     - Granted to role Everybody by default

.. _TaskReset:

   :ref:`🔑TaskReset <TaskReset>`
     - Reset any task in the system (administrative permission)
     - Typically restricted to administrators

.. _TaskResetReadyForJoin:

   :ref:`🔑TaskResetReadyForJoin <TaskResetReadyForJoin>`
     - Reset tasks in READY_FOR_JOIN state
     - Useful for workflow error recovery

.. _TaskDestroy:

   :ref:`🔑TaskDestroy <TaskDestroy>`
     - Delete tasks permanently
     - Only works if task state is not DESTROYED or DONE
     - High-privilege permission for administrators

**Task Property Modifications**

.. _TaskWriteName:

   :ref:`🔑TaskWriteName <TaskWriteName>`
     - Modify task name/title

.. _TaskWriteDescription:

   :ref:`🔑TaskWriteDescription <TaskWriteDescription>`
     - Modify task description
     - Cannot change terminated tasks (DONE, DESTROYED, FAILED)

.. _TaskWriteOriginalPriority:

   :ref:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`
     - Change task priority level
     - Cannot change tasks in states: DONE, DESTROYED, FAILED

.. _TaskWriteExpiryTimestamp:

   :ref:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`
     - Change task deadline/expiry date
     - Cannot change tasks in states: DONE, DESTROYED, FAILED

.. _TaskWriteActivator:

   :ref:`🔑TaskWriteActivator <TaskWriteActivator>`
     - Delegate tasks to other users/roles
     - Granted to role Everybody by default

.. _TaskWriteExpiryActivator:

   :ref:`🔑TaskWriteExpiryActivator <TaskWriteExpiryActivator>`
     - Change the user responsible when task expires
     - Cannot change tasks in states: DONE, DESTROYED, FAILED

.. _TaskWriteDelayTimestamp:

   :ref:`🔑TaskWriteDelayTimestamp <TaskWriteDelayTimestamp>`
     - Modify task delay/start time

**Task UI Display Permissions**

.. _TaskWriteActivatorOwnTasks:

   :ref:`🔑TaskWriteActivatorOwnTasks <TaskWriteActivatorOwnTasks>`
     - Delegate personal/group tasks assigned to user
     - Not assigned to Everybody by default (more restrictive than :ref:`🔑TaskWriteActivator <TaskWriteActivator>`)

.. _TaskDisplayAdditionalOptions:

   :ref:`🔑TaskDisplayAdditionalOptions <TaskDisplayAdditionalOptions>`
     - Display additional action menu in task lists
     - Granted to role Everybody by default

.. _TaskDisplayResetAction:

   :ref:`🔑TaskDisplayResetAction <TaskDisplayResetAction>`
     - Show Reset action button in task interface
     - Requires corresponding :ref:`🔑TaskReset <TaskReset>` permission to execute
     - Granted to role Everybody by default

.. _TaskDisplayReserveAction:

   :ref:`🔑TaskDisplayReserveAction <TaskDisplayReserveAction>`
     - Show Reserve (Park) action button in task interface
     - Requires :ref:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>` to execute
     - Granted to role Everybody by default

.. _TaskDisplayDelegateAction:

   :ref:`🔑TaskDisplayDelegateAction <TaskDisplayDelegateAction>`
     - Show Delegate action button in task interface
     - Requires :ref:`🔑TaskWriteActivator <TaskWriteActivator>` to execute delegation
     - Granted to role Everybody by default

.. _TaskDisplayDestroyAction:

   :ref:`🔑TaskDisplayDestroyAction <TaskDisplayDestroyAction>`
     - Show Delete/Destroy action button in task interface
     - Requires :ref:`🔑TaskDestroy <TaskDestroy>` permission to execute

.. _TaskDisplayWorkflowEventAction:

   :ref:`🔑TaskDisplayWorkflowEventAction <TaskDisplayWorkflowEventAction>`
     - Show Workflow Events button in task details
     - Allows viewing task execution history and events

.. _TaskDisplayCustomFieldsAction:

   :ref:`🔑TaskDisplayCustomFieldsAction <TaskDisplayCustomFieldsAction>`
     - Show Custom Fields button in task interface
     - Displays additional business data fields

.. _ShareTaskDetailsLink:

   :ref:`🔑ShareTaskDetailsLink <ShareTaskDetailsLink>`
     - Show Share button in task details page
     - Allows sharing direct links to specific tasks
     - Granted to role Everybody by default

.. _permission-case-permissions:

Portal Case Permissions
-----------------------

Permissions controlling case visibility, actions, and business details.

**Case Visibility**

.. _CaseReadAll:

   :ref:`🔑CaseReadAll <CaseReadAll>`
     - View all cases in the system regardless of involvement
     - Typically granted to administrators
     - Combined with :ref:`🔑TaskReadAll <TaskReadAll>` for full system visibility

**Case Actions**

.. _CaseDestroy:

   :ref:`🔑CaseDestroy <CaseDestroy>`
     - Delete cases permanently
     - Only works when case state is RUNNING
     - High-privilege permission for administrators

.. _CaseOwnerTaskDelegate:

   :ref:`🔑CaseOwnerTaskDelegate <CaseOwnerTaskDelegate>`
     - Delegate all related tasks within cases where user is the case owner
     - Allows case owners to manage task assignments for their cases

**Case Property Modifications**

.. _CaseWriteName:

   :ref:`🔑CaseWriteName <CaseWriteName>`
     - Modify case name/title
     - Cannot change cases in DESTROYED state

.. _CaseWriteDescription:

   :ref:`🔑CaseWriteDescription <CaseWriteDescription>`
     - Modify case description
     - Cannot change cases in DESTROYED state

**Case UI Display Permissions**

.. _ShowAllTasksOfCase:

   :ref:`🔑ShowAllTasksOfCase <ShowAllTasksOfCase>`
     - Display "Show all tasks" action in case details
     - Requires :ref:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>` or :ref:`🔑TaskReadAll <TaskReadAll>` to view tasks
     - Granted to role Everybody by default

.. _ShowCaseDetails:

   :ref:`🔑ShowCaseDetails <ShowCaseDetails>`
     - Display Business Details tab in case interface
     - Shows additional case information and custom widgets
     - Granted to role Everybody by default

.. _CaseDisplayCustomFieldsAction:

   :ref:`🔑CaseDisplayCustomFieldsAction <CaseDisplayCustomFieldsAction>`
     - Display Custom Fields button in case interface
     - Shows additional business data fields

.. _ShareCaseDetailsLink:

   :ref:`🔑ShareCaseDetailsLink <ShareCaseDetailsLink>`
     - Show Share button in case details page
     - Allows sharing direct links to specific cases
     - Granted to role Everybody by default

.. _permission-general-permissions:

Portal General Permissions
--------------------------

General permissions for dashboards, documents, lists, roles, and Portal features.

**Portal Page Access**

.. _AccessFullProcessList:

   :ref:`🔑AccessFullProcessList <AccessFullProcessList>`
     - Access full process list page showing all available processes
     - Shows "Processes" in left menu and "Show all processes" on Dashboard
     - See :ref:`full-process-list` for details
     - Granted to role Everybody by default

**Dashboard Permissions**

.. _DashboardWriteOwn:

   :ref:`🔑DashboardWriteOwn <DashboardWriteOwn>`
     - Create and modify private (personal) dashboards
     - Granted to role Everybody by default

.. _DashboardWritePublic:

   :ref:`🔑DashboardWritePublic <DashboardWritePublic>`
     - Create and modify public (shared) dashboards
     - Typically restricted to administrators or dashboard managers

.. _DashboardExportOwn:

   :ref:`🔑DashboardExportOwn <DashboardExportOwn>`
     - Export private dashboards to JSON files
     - Allows backup and sharing of personal dashboard configurations

.. _DashboardExportPublic:

   :ref:`🔑DashboardExportPublic <DashboardExportPublic>`
     - Export public dashboards to JSON files
     - Typically restricted to administrators

.. _DashboardImportOwn:

   :ref:`🔑DashboardImportOwn <DashboardImportOwn>`
     - Import private dashboards from JSON files
     - Allows restoring or applying dashboard templates

.. _DashboardImportPublic:

   :ref:`🔑DashboardImportPublic <DashboardImportPublic>`
     - Import public dashboards from JSON files
     - Typically restricted to administrators

.. _ShareDashboardLink:

   :ref:`🔑ShareDashboardLink <ShareDashboardLink>`
     - Share dashboard links with other users
     - Granted to role Everybody by default

**Document Permissions**

.. _DocumentRead:

   :ref:`🔑DocumentRead <DocumentRead>`
     - View all documents across all cases/tasks
     - Administrative permission for full document visibility

.. _DocumentWrite:

   :ref:`🔑DocumentWrite <DocumentWrite>`
     - Upload and delete any documents
     - Administrative permission for document management

.. _DocumentOfInvolvedCaseWrite:

   :ref:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>`
     - Upload and delete documents in cases where user is involved
     - Standard permission for case participants
     - Granted to role Everybody by default

**Role Management Permissions**

.. _RoleReadAll:

   :ref:`🔑RoleReadAll <RoleReadAll>`
     - View all roles in the system
     - Required for role selection in various features
     - Granted to role Everybody by default

.. _RoleManagement:

   :ref:`🔑RoleManagement <RoleManagement>`
     - Access Role Management tab in Admin Settings
     - Required to view dynamic role configuration interface

.. _RoleCreate:

   :ref:`🔑RoleCreate <RoleCreate>`
     - Create new dynamic roles
     - Typically restricted to administrators

.. _RoleDelete:

   :ref:`🔑RoleDelete <RoleDelete>`
     - Delete existing dynamic roles
     - Typically restricted to administrators

.. _RoleMove:

   :ref:`🔑RoleMove <RoleMove>`
     - Change role hierarchy (select parent role)
     - Affects role inheritance structure

**Notes and Comments**

.. _TaskCaseAddNote:

   :ref:`🔑TaskCaseAddNote <TaskCaseAddNote>`
     - Add notes/comments to tasks and cases
     - Enables collaboration and communication
     - Granted to role Everybody by default

.. _TaskCaseShowMoreNote:

   :ref:`🔑TaskCaseShowMoreNote <TaskCaseShowMoreNote>`
     - View "Show more" option to expand long notes
     - Granted to role Everybody by default

.. _NoteReadAllCaseTaskDetails:

   :ref:`🔑NoteReadAllCaseTaskDetails <NoteReadAllCaseTaskDetails>`
     - View system notes in case and task details
     - Allows non-admin users to see audit and system-generated notes
     - **New in LTS 12.0+**: Replaces legacy global variables ``Portal.Histories.HideSystemNotes`` and ``Portal.Histories.HideSystemNotesForAdministrator``
     
   .. note::
      **Pre-LTS Versions:** This permission does not exist in Portal versions before 12.0. Use global variables ``Portal.Histories.HideSystemNotes`` and ``Portal.Histories.HideSystemNotesForAdministrator`` instead.

**Admin Settings & Configuration**

- :ref:`🔑RoleManagement <RoleManagement>`
    - Access Role Management tab in Admin Settings
    - See dynamic role configuration and management

.. _NewsManagement:

   :ref:`🔑NewsManagement <NewsManagement>`
     - Manage News widget content on dashboards
     - Create, edit, and delete news items

.. _PasswordValidation:

   :ref:`🔑PasswordValidation <PasswordValidation>`
     - Access Password Validation settings in Admin Settings
     - Configure password complexity requirements

.. _NotificationChannelsSetting:

   :ref:`🔑NotificationChannelsSetting <NotificationChannelsSetting>`
     - Customize notification channel preferences in :ref:`my-profile`
     - Control email, browser, and other notification methods
     - Granted to role Everybody by default

**Process & External Links**

.. _CreatePublicExternalLink:

   :ref:`🔑CreatePublicExternalLink <CreatePublicExternalLink>`
     - Create public external links visible to all users
     - Links appear in full process list for all users
     - Useful for sharing processes with external systems

.. _permission-absence-substitute:

Portal Absence And Substitute Permissions
-----------------------------------------

Permissions for managing user absences and task substitution.

**Absence Management - Own Absences**

.. _UserReadOwnAbsences:

   :ref:`🔑UserReadOwnAbsences <UserReadOwnAbsences>`
     - View own absence records
     - Granted to role Everybody by default

.. _UserCreateOwnAbsence:

   :ref:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>`
     - Create and edit own absence periods
     - Allows users to mark when they are unavailable
     - Granted to role Everybody by default

.. _UserDeleteOwnAbsence:

   :ref:`🔑UserDeleteOwnAbsence <UserDeleteOwnAbsence>`
     - Delete own absence records
     - Granted to role Everybody by default

**Absence Management - All Users**

.. _UserReadAbsences:

   :ref:`🔑UserReadAbsences <UserReadAbsences>`
     - View absence records of all users
     - Administrative permission for HR or management

.. _UserCreateAbsence:

   :ref:`🔑UserCreateAbsence <UserCreateAbsence>`
     - Create and edit absences for any user
     - Typically restricted to administrators or HR personnel

.. _UserDeleteAbsence:

   :ref:`🔑UserDeleteAbsence <UserDeleteAbsence>`
     - Delete absence records for any user
     - Administrative permission for absence management

**Substitute Management**

.. _UserCreateOwnSubstitute:

   :ref:`🔑UserCreateOwnSubstitute <UserCreateOwnSubstitute>`
     - Create own substitute assignments
     - Delegate tasks to others during absence
     - Granted to role Everybody by default

.. _UserCreateSubstitute:

   :ref:`🔑UserCreateSubstitute <UserCreateSubstitute>`
     - Create substitute assignments for any user
     - Administrative permission for managing substitutions

.. _UserReadSubstitutes:

   :ref:`🔑UserReadSubstitutes <UserReadSubstitutes>`
     - View substitute assignments for all users
     - Required for seeing who is substituting whom

.. _engine-permissions-respected:

Engine Permissions Respected by Portal
---------------------------------------

Portal honors the following Axon Ivy Engine core permissions. These are documented here for completeness as they directly affect Portal functionality:

**Task Permissions:**
:ref:`🔑TaskReadAll <TaskReadAll>`, :ref:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>`, :ref:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>`, :ref:`🔑TaskResetOwnWorkingTask <TaskResetOwnWorkingTask>`, :ref:`🔑TaskReset <TaskReset>`, :ref:`🔑TaskDestroy <TaskDestroy>`, :ref:`🔑TaskWriteName <TaskWriteName>`, :ref:`🔑TaskWriteDescription <TaskWriteDescription>`, :ref:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`, :ref:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`, :ref:`🔑TaskWriteActivator <TaskWriteActivator>`, :ref:`🔑TaskWriteDelayTimestamp <TaskWriteDelayTimestamp>`

**Case Permissions:**
:ref:`🔑CaseReadAll <CaseReadAll>`, :ref:`🔑CaseDestroy <CaseDestroy>`, :ref:`🔑CaseWriteName <CaseWriteName>`, :ref:`🔑CaseWriteDescription <CaseWriteDescription>`

**Role Permissions:**
:ref:`🔑RoleReadAll <RoleReadAll>`, :ref:`🔑RoleCreate <RoleCreate>`, :ref:`🔑RoleDelete <RoleDelete>`, :ref:`🔑RoleMove <RoleMove>`

**Document Permissions:**
:ref:`🔑DocumentRead <DocumentRead>`, :ref:`🔑DocumentWrite <DocumentWrite>`, :ref:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>`

**Absence & Substitute Permissions:**
:ref:`🔑UserReadOwnAbsences <UserReadOwnAbsences>`, :ref:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>`, :ref:`🔑UserDeleteOwnAbsence <UserDeleteOwnAbsence>`, :ref:`🔑UserReadAbsences <UserReadAbsences>`, :ref:`🔑UserCreateAbsence <UserCreateAbsence>`, :ref:`🔑UserDeleteAbsence <UserDeleteAbsence>`, :ref:`🔑UserCreateOwnSubstitute <UserCreateOwnSubstitute>`, :ref:`🔑UserCreateSubstitute <UserCreateSubstitute>`, :ref:`🔑UserReadSubstitutes <UserReadSubstitutes>`

.. tip::
   For comprehensive details on each permission including usage context and restrictions, see the detailed sections above.

