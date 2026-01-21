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


.. _TaskReadAll:

   :ref:`🔑TaskReadAll <TaskReadAll>`
       +--------------------+-------------------------------------------------------------+
       | Category           | **Task Visibility**                                         |
       +--------------------+-------------------------------------------------------------+
       | Description        | View all user tasks (not system) in the system regardless of|
       |                    | assignment                                                  |
       +--------------------+-------------------------------------------------------------+
       | Type               | **Core permission**                                         |
       +--------------------+-------------------------------------------------------------+
       | Use case           | Grant it to see all tasks in the security context           |
       +--------------------+-------------------------------------------------------------+
       | Granted by default | No                                                          |
       +--------------------+-------------------------------------------------------------+

.. _SystemTaskReadAll:

   :ref:`🔑SystemTaskReadAll <SystemTaskReadAll>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Visibility**                                        |
       +--------------------+------------------------------------------------------------+
       | Description        | View system tasks (background/automated tasks)             |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant it to see system tasks for debugging or monitoring   |
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskReadOwnCaseTasks:

   :ref:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Visibility**                                        |
       +--------------------+------------------------------------------------------------+
       | Description        | View tasks related to cases where user is involved         |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | See my own task and case in task list, case list           |
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+


.. _TaskParkOwnWorkingTask:

   :ref:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +--------------------+------------------------------------------------------------+
       | Description        | Reserve (park) an own working task                         |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant this permission to enable Reserve menu item in task  |
       |                    | action                                                     | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _TaskResetOwnWorkingTask:

   :ref:`🔑TaskResetOwnWorkingTask <TaskResetOwnWorkingTask>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Reset own working tasks to their initial state             |
       |                    | in states: RESUMED, PARKED, READY_FOR_JOIN, FAILED         |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant this permission to enable Reset menu item in task    |
       |                    | action                                                     | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+
     
.. _TaskReset:

   :ref:`🔑TaskReset <TaskReset>`
      :ref:`🔑TaskResetOwnWorkingTask <TaskResetOwnWorkingTask>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Reset any tasks in the security context                    |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Only high privilege user should have this permission       | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _TaskResetReadyForJoin:


   :ref:`🔑TaskResetReadyForJoin <TaskResetReadyForJoin>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Reset tasks in READY_FOR_JOIN state                        |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Useful for workflow error recovery                         | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskDestroy:

   :ref:`🔑TaskDestroy <TaskDestroy>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Delete tasks permanently                                   |
       |                    | Only works if task state is not DESTROYED or DONE          |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | High-privilege permission for administrators               | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

**Task Property Modifications**

.. _TaskWriteName:

   :ref:`🔑TaskWriteName <TaskWriteName>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Modify task name                                           | 
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | High-privilege permission for administrators               | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskWriteDescription:

   :ref:`🔑TaskWriteDescription <TaskWriteDescription>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Modify task description                                    |
       |                    | Cannot change terminated tasks (DONE, DESTROYED, FAILED)   |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskWriteOriginalPriority:

   :ref:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Change task priority level                                 |
       |                    | Cannot change tasks in states: DONE, DESTROYED, FAILED     | 
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskWriteExpiryTimestamp:

   :ref:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Change task deadline/expiry date                           |
       |                    | Cannot change tasks in states: DONE, DESTROYED, FAILED     |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskWriteActivator:

   :ref:`🔑TaskWriteActivator <TaskWriteActivator>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Delegate tasks to other users/roles                        |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+


.. _TaskWriteExpiryActivator:

   :ref:`🔑TaskWriteExpiryActivator <TaskWriteExpiryActivator>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Change the user responsible when task expires              |
       |                    | Cannot change tasks in states: DONE, DESTROYED, FAILED     |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _TaskWriteDelayTimestamp:

   :ref:`🔑TaskWriteDelayTimestamp <TaskWriteDelayTimestamp>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Modify task delay/start time                               |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _TaskWriteActivatorOwnTasks:

   :ref:`🔑TaskWriteActivatorOwnTasks <TaskWriteActivatorOwnTasks>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Delegate personal/group tasks assigned to user             |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskDisplayAdditionalOptions:

   :ref:`🔑TaskDisplayAdditionalOptions <TaskDisplayAdditionalOptions>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Display additional action menu in task lists               |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _TaskDisplayResetAction:

   :ref:`🔑TaskDisplayResetAction <TaskDisplayResetAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Show Reset action button in task interface                 |
       |                    | Requires corresponding TaskReset permission to execute     |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+


.. _TaskDisplayReserveAction:

   :ref:`🔑TaskDisplayReserveAction <TaskDisplayReserveAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Show Reserve (Park) action button in task interface        |
       |                    | Requires TaskParkOwnWorkingTask to execute                 |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+
   
.. _TaskDisplayDelegateAction:

   :ref:`🔑TaskDisplayDelegateAction <TaskDisplayDelegateAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Show Delegate action button in task interface              |
       |                    | Requires TaskWriteActivator to execute delegation          |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _TaskDisplayDestroyAction:

   :ref:`🔑TaskDisplayDestroyAction <TaskDisplayDestroyAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Show Delete/Destroy action button in task interface        |
       |                    | Requires TaskDestroy permission to execute                 |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _TaskDisplayWorkflowEventAction:

   :ref:`🔑TaskDisplayWorkflowEventAction <TaskDisplayWorkflowEventAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Show Workflow Events button in task details                |
       |                    | Allows viewing task execution history and events           |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

     
.. _TaskDisplayCustomFieldsAction:

   :ref:`🔑TaskDisplayCustomFieldsAction <TaskDisplayCustomFieldsAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Show Custom Fields button in task interface                |
       |                    | Displays additional business data fields                   |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            |
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+


.. _ShareTaskDetailsLink:

   :ref:`🔑ShareTaskDetailsLink <ShareTaskDetailsLink>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +====================+============================================================+
       | Description        | Show Share button in task details page                     |
       |                    | Allows sharing direct links to specific tasks              |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           |                                                            | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _permission-case-permissions:

Portal Case Permissions
-----------------------

Permissions controlling case visibility, actions, and business details.

.. _CaseReadAll:

   :ref:`🔑CaseReadAll <CaseReadAll>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case Visibility**                                        |
       +====================+============================================================+
       | Description        | View all cases in the security context regardless          |
       |                    | of involvement                                             |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant it to see all cases                                  | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _CaseDestroy:

   :ref:`🔑CaseDestroy <CaseDestroy>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case Action**                                            |
       +====================+============================================================+
       | Description        | Delete cases with state RUNNING                            |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Destroy`` menu       |
       |                    | item in the case action memu                               |
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _CaseOwnerTaskDelegate:

   :ref:`🔑CaseOwnerTaskDelegate <CaseOwnerTaskDelegate>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case Action**                                            |
       +====================+============================================================+
       | Description        | Delegate all related tasks within cases where user is      |
       |                    | the case owner                                             | 
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission and ``TaskWriteActivator`` can   | 
       |                    | delegate tasks within the case which he is case owner      |
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _CaseWriteName:

   :ref:`🔑CaseWriteName <CaseWriteName>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case Property Modifications**                            |
       +====================+============================================================+
       | Description        | Modify case name, cannot modify case in DESTROYED state    |                                        
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Not clear                                                  | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _CaseWriteDescription:

   :ref:`🔑CaseWriteDescription <CaseWriteDescription>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case Property Modifications**                            |
       +====================+============================================================+
       | Description        | Modify case description, cannot modify case in DESTROYED   |
       |                    | state                                                      |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can edit case description in     |
       |                    | case details                                               | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _ShowAllTasksOfCase:

   :ref:`🔑ShowAllTasksOfCase <ShowAllTasksOfCase>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case UI Display Permissions**                            |
       +====================+============================================================+
       | Description        | Display "Show all tasks" action in case details            |
       |                    | Requires TaskReadOwnCaseTasks and TaskReadAll to view tasks|
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Not clear / Can remove ?                                   | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _ShowCaseDetails:

   :ref:`🔑ShowCaseDetails <ShowCaseDetails>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case UI Display Permissions**                            |
       +====================+============================================================+
       | Description        | Display business details in new tab to show                |
       |                    | additional case information                                |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Business details``   |
       |                    | menu item in the case action menu                          | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _CaseDisplayCustomFieldsAction:

   :ref:`🔑CaseDisplayCustomFieldsAction <CaseDisplayCustomFieldsAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case UI Display Permissions**                            |
       +====================+============================================================+
       | Description        | Display custom fields of case                              |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Custom fields``      |
       |                    | menu item in the case action menu                          | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _ShareCaseDetailsLink:

   :ref:`🔑ShareCaseDetailsLink <ShareCaseDetailsLink>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case UI Display Permissions**                            |
       +====================+============================================================+
       | Description        | Display share case details link                            |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Share`` button       |
       |                    | next to ``Edit`` button in case details                    | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+


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

.. _UserReadOwnAbsences:

   :ref:`🔑UserReadOwnAbsences <UserReadOwnAbsences>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Manage own absences**                                    |
       +====================+============================================================+
       | Description        |  View own absence records                                  |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission sees his absence                 | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _UserCreateOwnAbsence:

   :ref:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Manage own absences**                                    |
       +====================+============================================================+
       | Description        | Create and edit own absences                               |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can create and edit his          |
       |                    | own absences                                               | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _UserDeleteOwnAbsence:

   :ref:`🔑UserDeleteOwnAbsence <UserDeleteOwnAbsence>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Manage own absences**                                    |
       +====================+============================================================+
       | Description        | Delete own absences                                        |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can delete his own absences      | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+


.. _UserReadAbsences:

   :ref:`🔑UserReadAbsences <UserReadAbsences>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Manage absences for all users**                          |
       +====================+============================================================+
       | Description        | View absences of all users in the security context         |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can view absences of all users   |
       |                    | in the security context                                    | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _UserCreateAbsence:

   :ref:`🔑UserCreateAbsence <UserCreateAbsence>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Manage absences for all users**                          |
       +====================+============================================================+
       | Description        | Create and edit absences for any users in                  | 
       |                    | the security context                                       |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can create and edit absences     |
       |                    | for any users in the security context                      | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _UserDeleteAbsence:

   :ref:`🔑UserDeleteAbsence <UserDeleteAbsence>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Manage absences for all users**                          |
       +====================+============================================================+
       | Description        | Delete absences for any users in the security context      |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can delete absences              |
       |                    | for any users in the security context                      | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _UserCreateOwnSubstitute:

   :ref:`🔑UserCreateOwnSubstitute <UserCreateOwnSubstitute>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Manage substitutes**                                     |
       +====================+============================================================+
       | Description        | Create own substitute                                      |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can create own substitute        |
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+
   

.. _UserCreateSubstitute:

   :ref:`🔑UserCreateSubstitute <UserCreateSubstitute>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Manage substitutes**                                     |
       +====================+============================================================+
       | Description        | Create substitute assignments for any users in the         |
       |                    | security context                                           |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can create substitute            |
       |                    | for any users in the security context                      | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _UserReadSubstitutes:

   :ref:`🔑UserReadSubstitutes <UserReadSubstitutes>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Manage substitutes**                                     |
       +====================+============================================================+
       | Description        | View substitutes of any users in the security context      |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can view substitutes of          |
       |                    | any users in the security context                          | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _engine-permissions-respected:

Engine Permissions used by Portal
----------------------------------

Portal uses the following Axon Ivy Engine core permissions. These are documented here for completeness as they directly affect Portal functionality:

**Task Permissions:**

#. :ref:`🔑TaskReadAll <TaskReadAll>` 
#. :ref:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>`
#. :ref:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>` 
#. :ref:`🔑TaskResetOwnWorkingTask <TaskResetOwnWorkingTask>`
#. :ref:`🔑TaskReset <TaskReset>` 
#. :ref:`🔑TaskDestroy <TaskDestroy>` 
#. :ref:`🔑TaskWriteName <TaskWriteName>` 
#. :ref:`🔑TaskWriteDescription <TaskWriteDescription>`
#. :ref:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`
#. :ref:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>` 
#. :ref:`🔑TaskWriteName <TaskWriteName>`
#. :ref:`🔑TaskWriteDescription <TaskWriteDescription>`
#. :ref:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`
#. :ref:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`
#. :ref:`🔑TaskWriteActivator <TaskWriteActivator>`
#. :ref:`🔑TaskWriteDelayTimestamp <TaskWriteDelayTimestamp>`

**Case Permissions:**

#. :ref:`🔑CaseReadAll <CaseReadAll>`
#. :ref:`🔑CaseDestroy <CaseDestroy>`
#. :ref:`🔑CaseWriteName <CaseWriteName>`
#. :ref:`🔑CaseWriteDescription <CaseWriteDescription>`

**Role Permissions:**

#. :ref:`🔑RoleReadAll <RoleReadAll>`
#. :ref:`🔑RoleCreate <RoleCreate>`
#. :ref:`🔑RoleDelete <RoleDelete>`
#. :ref:`🔑RoleMove <RoleMove>`

**Document Permissions:**

#. :ref:`🔑DocumentRead <DocumentRead>`
#. :ref:`🔑DocumentWrite <DocumentWrite>`
#. :ref:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>`

**Absence & Substitute Permissions:**

#. :ref:`🔑UserReadOwnAbsences <UserReadOwnAbsences>`
#. :ref:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>`
#. :ref:`🔑UserDeleteOwnAbsence <UserDeleteOwnAbsence>`
#. :ref:`🔑UserReadAbsences <UserReadAbsences>`
#. :ref:`🔑UserCreateAbsence <UserCreateAbsence>`
#. :ref:`🔑UserDeleteAbsence <UserDeleteAbsence>`
#. :ref:`🔑UserCreateOwnSubstitute <UserCreateOwnSubstitute>`
#. :ref:`🔑UserCreateSubstitute <UserCreateSubstitute>`
#. :ref:`🔑UserReadSubstitutes <UserReadSubstitutes>`

.. tip::
   For comprehensive details on each permission including usage context and restrictions, see the detailed sections above.

