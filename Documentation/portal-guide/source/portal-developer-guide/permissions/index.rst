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

All permissions are configured in the :dev-url:`Engine Cockpit </doc/12.0/engine-guide/reference/engine-cockpit/security.html>` under Security > PortalPermissions. 
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

TaskReadAll
   :bdg-ref-warning:`🔑TaskReadAll <TaskReadAll>`
     - View all tasks in the system regardless of assignment
     - Typically granted to administrators

.. _SystemTaskReadAll:

SystemTaskReadAll
   :bdg-ref-warning:`🔑SystemTaskReadAll <SystemTaskReadAll>`
     - View system tasks (background/automated tasks)
     - Required for debugging and system monitoring

.. _TaskReadOwnCaseTasks:

TaskReadOwnCaseTasks
   :bdg-ref-warning:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>`
     - View tasks related to cases where user is involved
     - Granted to role Everybody by default

**Task Actions**

.. _TaskParkOwnWorkingTask:

TaskParkOwnWorkingTask
   :bdg-ref-warning:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>`
     - Reserve (park) own working tasks
     - Allows users to temporarily set aside tasks they're working on
     - Granted to role Everybody by default

.. _TaskResetOwnWorkingTask:

TaskResetOwnWorkingTask
   :bdg-ref-warning:`🔑TaskResetOwnWorkingTask <TaskResetOwnWorkingTask>`
     - Reset own working tasks to their initial state
     - Only works for tasks in states: RESUMED, PARKED, READY_FOR_JOIN, FAILED
     - Granted to role Everybody by default

.. _TaskReset:

TaskReset
   :bdg-ref-warning:`🔑TaskReset <TaskReset>`
     - Reset any task in the system (administrative permission)
     - Typically restricted to administrators

.. _TaskResetReadyForJoin:

TaskResetReadyForJoin
   :bdg-ref-warning:`🔑TaskResetReadyForJoin <TaskResetReadyForJoin>`
     - Reset tasks in READY_FOR_JOIN state
     - Useful for workflow error recovery

.. _TaskDestroy:

TaskDestroy
   :bdg-ref-warning:`🔑TaskDestroy <TaskDestroy>`
     - Delete tasks permanently
     - Only works if task state is not DESTROYED or DONE
     - High-privilege permission for administrators

**Task Property Modifications**

.. _TaskWriteName:

TaskWriteName
   :bdg-ref-warning:`🔑TaskWriteName <TaskWriteName>`
     - Modify task name/title

.. _TaskWriteDescription:

TaskWriteDescription
   :bdg-ref-warning:`🔑TaskWriteDescription <TaskWriteDescription>`
     - Modify task description
     - Cannot change terminated tasks (DONE, DESTROYED, FAILED)

.. _TaskWriteOriginalPriority:

TaskWriteOriginalPriority
   :bdg-ref-warning:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`
     - Change task priority level
     - Cannot change tasks in states: DONE, DESTROYED, FAILED

.. _TaskWriteExpiryTimestamp:

TaskWriteExpiryTimestamp
   :bdg-ref-warning:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`
     - Change task deadline/expiry date
     - Cannot change tasks in states: DONE, DESTROYED, FAILED

.. _TaskWriteActivator:

TaskWriteActivator
   :bdg-ref-warning:`🔑TaskWriteActivator <TaskWriteActivator>`
     - Delegate tasks to other users/roles
     - Granted to role Everybody by default

.. _TaskWriteExpiryActivator:

TaskWriteExpiryActivator
   :bdg-ref-warning:`🔑TaskWriteExpiryActivator <TaskWriteExpiryActivator>`
     - Change the user responsible when task expires
     - Cannot change tasks in states: DONE, DESTROYED, FAILED

.. _TaskWriteDelayTimestamp:

TaskWriteDelayTimestamp
   :bdg-ref-warning:`🔑TaskWriteDelayTimestamp <TaskWriteDelayTimestamp>`
     - Modify task delay/start time

**Task UI Display Permissions**

.. _TaskWriteActivatorOwnTasks:

TaskWriteActivatorOwnTasks
   :bdg-ref-warning:`🔑TaskWriteActivatorOwnTasks <TaskWriteActivatorOwnTasks>`
     - Delegate personal/group tasks assigned to user
     - Not assigned to Everybody by default (more restrictive than :bdg-ref-warning:`🔑TaskWriteActivator <TaskWriteActivator>`)

.. _TaskDisplayAdditionalOptions:

TaskDisplayAdditionalOptions
   :bdg-ref-warning:`🔑TaskDisplayAdditionalOptions <TaskDisplayAdditionalOptions>`
     - Display additional action menu in task lists
     - Granted to role Everybody by default

.. _TaskDisplayResetAction:

TaskDisplayResetAction
   :bdg-ref-warning:`🔑TaskDisplayResetAction <TaskDisplayResetAction>`
     - Show Reset action button in task interface
     - Requires corresponding :bdg-ref-warning:`🔑TaskReset <TaskReset>` permission to execute
     - Granted to role Everybody by default

.. _TaskDisplayReserveAction:

TaskDisplayReserveAction
   :bdg-ref-warning:`🔑TaskDisplayReserveAction <TaskDisplayReserveAction>`
     - Show Reserve (Park) action button in task interface
     - Requires :bdg-ref-warning:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>` to execute
     - Granted to role Everybody by default

.. _TaskDisplayDelegateAction:

TaskDisplayDelegateAction
   :bdg-ref-warning:`🔑TaskDisplayDelegateAction <TaskDisplayDelegateAction>`
     - Show Delegate action button in task interface
     - Requires :bdg-ref-warning:`🔑TaskWriteActivator <TaskWriteActivator>` to execute delegation
     - Granted to role Everybody by default

.. _TaskDisplayDestroyAction:

TaskDisplayDestroyAction
   :bdg-ref-warning:`🔑TaskDisplayDestroyAction <TaskDisplayDestroyAction>`
     - Show Delete/Destroy action button in task interface
     - Requires :bdg-ref-warning:`🔑TaskDestroy <TaskDestroy>` permission to execute

.. _TaskDisplayWorkflowEventAction:

TaskDisplayWorkflowEventAction
   :bdg-ref-warning:`🔑TaskDisplayWorkflowEventAction <TaskDisplayWorkflowEventAction>`
     - Show Workflow Events button in task details
     - Allows viewing task execution history and events

.. _TaskDisplayCustomFieldsAction:

TaskDisplayCustomFieldsAction
   :bdg-ref-warning:`🔑TaskDisplayCustomFieldsAction <TaskDisplayCustomFieldsAction>`
     - Show Custom Fields button in task interface
     - Displays additional business data fields

.. _ShareTaskDetailsLink:

ShareTaskDetailsLink
   :bdg-ref-warning:`🔑ShareTaskDetailsLink <ShareTaskDetailsLink>`
     - Show Share button in task details page
     - Allows sharing direct links to specific tasks
     - Granted to role Everybody by default

.. _permission-case-permissions:

Portal Case Permissions
-----------------------

Permissions controlling case visibility, actions, and business details.

**Case Visibility**

.. _CaseReadAll:

CaseReadAll
   :bdg-ref-warning:`🔑CaseReadAll <CaseReadAll>`
     - View all cases in the system regardless of involvement
     - Typically granted to administrators
     - Combined with :bdg-ref-warning:`🔑TaskReadAll <TaskReadAll>` for full system visibility

**Case Actions**

.. _CaseDestroy:

CaseDestroy
   :bdg-ref-warning:`🔑CaseDestroy <CaseDestroy>`
     - Delete cases permanently
     - Only works when case state is RUNNING
     - High-privilege permission for administrators

.. _CaseOwnerTaskDelegate:

CaseOwnerTaskDelegate
   :bdg-ref-warning:`🔑CaseOwnerTaskDelegate <CaseOwnerTaskDelegate>`
     - Delegate all related tasks within cases where user is the case owner
     - Allows case owners to manage task assignments for their cases

**Case Property Modifications**

.. _CaseWriteName:

CaseWriteName
   :bdg-ref-warning:`🔑CaseWriteName <CaseWriteName>`
     - Modify case name/title
     - Cannot change cases in DESTROYED state

.. _CaseWriteDescription:

CaseWriteDescription
   :bdg-ref-warning:`🔑CaseWriteDescription <CaseWriteDescription>`
     - Modify case description
     - Cannot change cases in DESTROYED state

**Case UI Display Permissions**

.. _ShowAllTasksOfCase:

ShowAllTasksOfCase
   :bdg-ref-warning:`🔑ShowAllTasksOfCase <ShowAllTasksOfCase>`
     - Display "Show all tasks" action in case details
     - Requires :bdg-ref-warning:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>` or :bdg-ref-warning:`🔑TaskReadAll <TaskReadAll>` to view tasks
     - Granted to role Everybody by default

.. _ShowCaseDetails:

ShowCaseDetails
   :bdg-ref-warning:`🔑ShowCaseDetails <ShowCaseDetails>`
     - Display Business Details tab in case interface
     - Shows additional case information and custom widgets
     - Granted to role Everybody by default

.. _CaseDisplayCustomFieldsAction:

CaseDisplayCustomFieldsAction
   :bdg-ref-warning:`🔑CaseDisplayCustomFieldsAction <CaseDisplayCustomFieldsAction>`
     - Display Custom Fields button in case interface
     - Shows additional business data fields

.. _ShareCaseDetailsLink:

ShareCaseDetailsLink
   :bdg-ref-warning:`🔑ShareCaseDetailsLink <ShareCaseDetailsLink>`
     - Show Share button in case details page
     - Allows sharing direct links to specific cases
     - Granted to role Everybody by default

.. _permission-general-permissions:

Portal General Permissions
--------------------------

General permissions for dashboards, documents, lists, roles, and Portal features.

**Portal Page Access**

.. _AccessFullProcessList:

AccessFullProcessList
   :bdg-ref-warning:`🔑AccessFullProcessList <AccessFullProcessList>`
     - Access full process list page showing all available processes
     - Shows "Processes" in left menu and "Show all processes" on Dashboard
     - See :ref:`full-process-list` for details
     - Granted to role Everybody by default

.. _AccessFullTaskList:

AccessFullTaskList
   :bdg-ref-warning:`🔑AccessFullTaskList <AccessFullTaskList>`
     - Access full task list page showing all accessible tasks
     - Shows "Tasks" in left menu and "Show full task list" on Dashboard
     - See :ref:`full-task-list` for details
     - Granted to role Everybody by default

.. _AccessFullCaseList:

AccessFullCaseList
   :bdg-ref-warning:`🔑AccessFullCaseList <AccessFullCaseList>`
     - Access full case list page showing all accessible cases
     - Shows "Cases" in left menu
     - See :ref:`full-case-list` for details
     - Granted to role Everybody by default

**Dashboard Permissions**

.. _DashboardWriteOwn:

DashboardWriteOwn
   :bdg-ref-warning:`🔑DashboardWriteOwn <DashboardWriteOwn>`
     - Create and modify private (personal) dashboards
     - Granted to role Everybody by default

.. _DashboardWritePublic:

DashboardWritePublic
   :bdg-ref-warning:`🔑DashboardWritePublic <DashboardWritePublic>`
     - Create and modify public (shared) dashboards
     - Typically restricted to administrators or dashboard managers

.. _DashboardExportOwn:

DashboardExportOwn
   :bdg-ref-warning:`🔑DashboardExportOwn <DashboardExportOwn>`
     - Export private dashboards to JSON files
     - Allows backup and sharing of personal dashboard configurations

.. _DashboardExportPublic:

DashboardExportPublic
   :bdg-ref-warning:`🔑DashboardExportPublic <DashboardExportPublic>`
     - Export public dashboards to JSON files
     - Typically restricted to administrators

.. _DashboardImportOwn:

DashboardImportOwn
   :bdg-ref-warning:`🔑DashboardImportOwn <DashboardImportOwn>`
     - Import private dashboards from JSON files
     - Allows restoring or applying dashboard templates

.. _DashboardImportPublic:

DashboardImportPublic
   :bdg-ref-warning:`🔑DashboardImportPublic <DashboardImportPublic>`
     - Import public dashboards from JSON files
     - Typically restricted to administrators

.. _ShareDashboardLink:

ShareDashboardLink
   :bdg-ref-warning:`🔑ShareDashboardLink <ShareDashboardLink>`
     - Share dashboard links with other users
     - Granted to role Everybody by default

**Document Permissions**

.. _DocumentRead:

DocumentRead
   :bdg-ref-warning:`🔑DocumentRead <DocumentRead>`
     - View all documents across all cases/tasks
     - Administrative permission for full document visibility

.. _DocumentWrite:

DocumentWrite
   :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>`
     - Upload and delete any documents
     - Administrative permission for document management

.. _DocumentOfInvolvedCaseWrite:

DocumentOfInvolvedCaseWrite
   :bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>`
     - Upload and delete documents in cases where user is involved
     - Standard permission for case participants
     - Granted to role Everybody by default

**Role Management Permissions**

.. _RoleReadAll:

RoleReadAll
   :bdg-ref-warning:`🔑RoleReadAll <RoleReadAll>`
     - View all roles in the system
     - Required for role selection in various features
     - Granted to role Everybody by default

.. _RoleManagement:

RoleManagement
   :bdg-ref-warning:`🔑RoleManagement <RoleManagement>`
     - Access Role Management tab in Admin Settings
     - Required to view dynamic role configuration interface

.. _RoleCreate:

RoleCreate
   :bdg-ref-warning:`🔑RoleCreate <RoleCreate>`
     - Create new dynamic roles
     - Typically restricted to administrators

.. _RoleDelete:

RoleDelete
   :bdg-ref-warning:`🔑RoleDelete <RoleDelete>`
     - Delete existing dynamic roles
     - Typically restricted to administrators

.. _RoleMove:

RoleMove
   :bdg-ref-warning:`🔑RoleMove <RoleMove>`
     - Change role hierarchy (select parent role)
     - Affects role inheritance structure

**Notes and Comments**

.. _TaskCaseAddNote:

TaskCaseAddNote
   :bdg-ref-warning:`🔑TaskCaseAddNote <TaskCaseAddNote>`
     - Add notes/comments to tasks and cases
     - Enables collaboration and communication
     - Granted to role Everybody by default

.. _TaskCaseShowMoreNote:

TaskCaseShowMoreNote
   :bdg-ref-warning:`🔑TaskCaseShowMoreNote <TaskCaseShowMoreNote>`
     - View "Show more" option to expand long notes
     - Granted to role Everybody by default

.. _NoteReadAllCaseTaskDetails:

NoteReadAllCaseTaskDetails
   :bdg-ref-warning:`🔑NoteReadAllCaseTaskDetails <NoteReadAllCaseTaskDetails>`
     - View system notes in case and task details
     - Allows non-admin users to see audit and system-generated notes
     - **New in LTS 12.0+**: Replaces legacy global variables ``Portal.Histories.HideSystemNotes`` and ``Portal.Histories.HideSystemNotesForAdministrator``
     
   .. note::
      **Pre-LTS Versions:** This permission does not exist in Portal versions before 12.0. Use global variables ``Portal.Histories.HideSystemNotes`` and ``Portal.Histories.HideSystemNotesForAdministrator`` instead.

**Admin Settings & Configuration**

- :bdg-ref-warning:`🔑RoleManagement <RoleManagement>`
    - Access Role Management tab in Admin Settings
    - See dynamic role configuration and management

.. _NewsManagement:

NewsManagement
   :bdg-ref-warning:`🔑NewsManagement <NewsManagement>`
     - Manage News widget content on dashboards
     - Create, edit, and delete news items

.. _PasswordValidation:

PasswordValidation
   :bdg-ref-warning:`🔑PasswordValidation <PasswordValidation>`
     - Access Password Validation settings in Admin Settings
     - Configure password complexity requirements

.. _NotificationChannelsSetting:

NotificationChannelsSetting
   :bdg-ref-warning:`🔑NotificationChannelsSetting <NotificationChannelsSetting>`
     - Customize notification channel preferences in :ref:`my-profile`
     - Control email, browser, and other notification methods
     - Granted to role Everybody by default

**Process & External Links**

.. _CreatePublicExternalLink:

CreatePublicExternalLink
   :bdg-ref-warning:`🔑CreatePublicExternalLink <CreatePublicExternalLink>`
     - Create public external links visible to all users
     - Links appear in full process list for all users
     - Useful for sharing processes with external systems

.. _permission-absence-substitute:

Portal Absence And Substitute Permissions
-----------------------------------------

Permissions for managing user absences and task substitution.

**Absence Management - Own Absences**

.. _UserReadOwnAbsences:

UserReadOwnAbsences
   :bdg-ref-warning:`🔑UserReadOwnAbsences <UserReadOwnAbsences>`
     - View own absence records
     - Granted to role Everybody by default

.. _UserCreateOwnAbsence:

UserCreateOwnAbsence
   :bdg-ref-warning:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>`
     - Create and edit own absence periods
     - Allows users to mark when they are unavailable
     - Granted to role Everybody by default

.. _UserDeleteOwnAbsence:

UserDeleteOwnAbsence
   :bdg-ref-warning:`🔑UserDeleteOwnAbsence <UserDeleteOwnAbsence>`
     - Delete own absence records
     - Granted to role Everybody by default

**Absence Management - All Users**

.. _UserReadAbsences:

UserReadAbsences
   :bdg-ref-warning:`🔑UserReadAbsences <UserReadAbsences>`
     - View absence records of all users
     - Administrative permission for HR or management

.. _UserCreateAbsence:

UserCreateAbsence
   :bdg-ref-warning:`🔑UserCreateAbsence <UserCreateAbsence>`
     - Create and edit absences for any user
     - Typically restricted to administrators or HR personnel

.. _UserDeleteAbsence:

UserDeleteAbsence
   :bdg-ref-warning:`🔑UserDeleteAbsence <UserDeleteAbsence>`
     - Delete absence records for any user
     - Administrative permission for absence management

**Substitute Management**

.. _UserCreateOwnSubstitute:

UserCreateOwnSubstitute
   :bdg-ref-warning:`🔑UserCreateOwnSubstitute <UserCreateOwnSubstitute>`
     - Create own substitute assignments
     - Delegate tasks to others during absence
     - Granted to role Everybody by default

.. _UserCreateSubstitute:

UserCreateSubstitute
   :bdg-ref-warning:`🔑UserCreateSubstitute <UserCreateSubstitute>`
     - Create substitute assignments for any user
     - Administrative permission for managing substitutions

.. _UserReadSubstitutes:

UserReadSubstitutes
   :bdg-ref-warning:`🔑UserReadSubstitutes <UserReadSubstitutes>`
     - View substitute assignments for all users
     - Required for seeing who is substituting whom

.. _engine-permissions-respected:

Engine Permissions Respected by Portal
---------------------------------------

Portal honors the following Axon Ivy Engine core permissions. These are documented here for completeness as they directly affect Portal functionality:

**Task Permissions:**
:bdg-ref-warning:`🔑TaskReadAll <TaskReadAll>`, :bdg-ref-warning:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>`, :bdg-ref-warning:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>`, :bdg-ref-warning:`🔑TaskResetOwnWorkingTask <TaskResetOwnWorkingTask>`, :bdg-ref-warning:`🔑TaskReset <TaskReset>`, :bdg-ref-warning:`🔑TaskDestroy <TaskDestroy>`, :bdg-ref-warning:`🔑TaskWriteName <TaskWriteName>`, :bdg-ref-warning:`🔑TaskWriteDescription <TaskWriteDescription>`, :bdg-ref-warning:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`, :bdg-ref-warning:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`, :bdg-ref-warning:`🔑TaskWriteActivator <TaskWriteActivator>`, :bdg-ref-warning:`🔑TaskWriteDelayTimestamp <TaskWriteDelayTimestamp>`

**Case Permissions:**
:bdg-ref-warning:`🔑CaseReadAll <CaseReadAll>`, :bdg-ref-warning:`🔑CaseDestroy <CaseDestroy>`, :bdg-ref-warning:`🔑CaseWriteName <CaseWriteName>`, :bdg-ref-warning:`🔑CaseWriteDescription <CaseWriteDescription>`

**Role Permissions:**
:bdg-ref-warning:`🔑RoleReadAll <RoleReadAll>`, :bdg-ref-warning:`🔑RoleCreate <RoleCreate>`, :bdg-ref-warning:`🔑RoleDelete <RoleDelete>`, :bdg-ref-warning:`🔑RoleMove <RoleMove>`

**Document Permissions:**
:bdg-ref-warning:`🔑DocumentRead <DocumentRead>`, :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>`, :bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>`

**Absence & Substitute Permissions:**
:bdg-ref-warning:`🔑UserReadOwnAbsences <UserReadOwnAbsences>`, :bdg-ref-warning:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>`, :bdg-ref-warning:`🔑UserDeleteOwnAbsence <UserDeleteOwnAbsence>`, :bdg-ref-warning:`🔑UserReadAbsences <UserReadAbsences>`, :bdg-ref-warning:`🔑UserCreateAbsence <UserCreateAbsence>`, :bdg-ref-warning:`🔑UserDeleteAbsence <UserDeleteAbsence>`, :bdg-ref-warning:`🔑UserCreateOwnSubstitute <UserCreateOwnSubstitute>`, :bdg-ref-warning:`🔑UserCreateSubstitute <UserCreateSubstitute>`, :bdg-ref-warning:`🔑UserReadSubstitutes <UserReadSubstitutes>`

.. tip::
   For comprehensive details on each permission including usage context and restrictions, see the detailed sections above.

