.. raw:: html

   <style>
      .wy-table-responsive table td, .wy-table-responsive table th {
         white-space: inherit;
      }
   </style>

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
      +--------------------+------------------------------------------------------------+
      | Category           | **Role Management Permissions**                            |
      +====================+============================================================+
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
     - Change task priority level
     - Cannot change tasks in states: DONE, DESTROYED, FAILED

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
       | Use case           | User with this permission will have Destroy memu item in   |
       |                    | the case action memu                                       |
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
       | Use case           | User with this permission and TaskWriteActivator can       | 
       |                    | delegate tasks within the case which he is case owner      |
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

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

.. _AccessFullProcessList:

   :ref:`🔑AccessFullProcessList <AccessFullProcessList>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Portal Page Access**                                     |
       +====================+============================================================+
       | Description        | This permission controls the visibility of the             |
       |                    | **Processes** page in the left menu and the **Processes**  |
       |                    | tab in the global search.                                  |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Hide the **Processes** entry from the left menu and the    |
       |                    | **Processes** tab in the global search for certain users or|
       |                    | roles.                                                     |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``EVERYBODY``                                              |
       +--------------------+------------------------------------------------------------+

.. _AccessFullTaskList:

   :ref:`🔑AccessFullTaskList <AccessFullTaskList>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Portal Page Access**                                     |
       +====================+============================================================+
       | Description        | This permission controls the visibility of the             |
       |                    | **Tasks** page in the left menu and the **Tasks**          |
       |                    | tab in the global search.                                  |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Hide the **Tasks** entry from the left menu and the        |
       |                    | **Tasks** tab in the global search for certain             |
       |                    | users or roles.                                            |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``EVERYBODY``                                              |
       +--------------------+------------------------------------------------------------+

.. _AccessFullCaseList:

   :ref:`🔑AccessFullCaseList <AccessFullCaseList>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Portal Page Access**                                     |
       +====================+============================================================+
       | Description        | This permission controls the visibility of the             |
       |                    | **Cases** page in the left menu and the **Cases** tab      |
       |                    | in the global search.                                      |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Hide the **Cases** entry from the left menu and the        |
       |                    | **Cases** tab in the global search for certain             |
       |                    | users or roles.                                            |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``EVERYBODY``                                              |
       +--------------------+------------------------------------------------------------+


.. _DashboardWriteOwn:

   :ref:`🔑DashboardWriteOwn <DashboardWriteOwn>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Dashboard Permissions**                                  |
       +====================+============================================================+
       | Description        | | This permission controls the ability to create and       |
       |                    |   modify private (personal) dashboards in the              |
       |                    |   :ref:`dashboard-configuration` page.                     |
       |                    | | If user is not granted this permission, the Private      |
       |                    |   Dashboard tab will be hidden and they cannot manage their|
       |                    |   own dashboards.                                          |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to users who need to create and customize their own  |
       |                    | personal dashboards.                                       |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``EVERYBODY``                                              |
       +--------------------+------------------------------------------------------------+

.. _DashboardWritePublic:

   :ref:`🔑DashboardWritePublic <DashboardWritePublic>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Dashboard Permissions**                                  |
       +====================+============================================================+
       | Description        | | This permission controls the ability to create and       |
       |                    |   modify public (shared) dashboards in the                 |
       |                    |   :ref:`dashboard-configuration` page.                     |
       |                    | | If user is not granted this permission, the Public       |
       |                    |   Dashboard tab will be hidden and they cannot manage      |
       |                    |   shared dashboards.                                       |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to administrators or dashboard managers who need to  |
       |                    | create and manage dashboards shared across the             |
       |                    | application.                                               |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+

.. note::
   If a user is not granted either **DashboardWriteOwn** or **DashboardWritePublic** permission, 
   the **Dashboard configuration** menu option will be hidden from the user profile dropdown menu.

.. _DashboardExportOwn:

   :ref:`🔑DashboardExportOwn <DashboardExportOwn>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Dashboard**                                              |
       +====================+============================================================+
       | Description        | | This permission controls the ability to export private   |
       |                    |   (personal) dashboards to JSON files for backup or        |
       |                    |   sharing purposes.                                        |
       |                    | | If user is not granted this permission, the Export       |
       |                    |   option will be hidden in the private dashboard actions   |
       |                    |   menu.                                                    |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to users who need to backup or share their personal  |
       |                    | dashboard configurations.                                  |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+

.. _DashboardExportPublic:

   :ref:`🔑DashboardExportPublic <DashboardExportPublic>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Dashboard**                                              |
       +====================+============================================================+
       | Description        | | This permission controls the ability to export public    |
       |                    |   (shared) dashboards to JSON files for backup or          |
       |                    |   distribution purposes.                                   |
       |                    | | If user is not granted this permission, the Export       |
       |                    |   option will be hidden in the public dashboard actions    |
       |                    |   menu.                                                    |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to administrators who need to backup or distribute   |
       |                    | public dashboard templates across environments.            |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+

.. _DashboardImportOwn:

   :ref:`🔑DashboardImportOwn <DashboardImportOwn>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Dashboard**                                              |
       +====================+============================================================+
       | Description        | | This permission controls the ability to import private   |
       |                    |   (personal) dashboards from JSON files.                   |
       |                    | | If user is not granted this permission, the Import       |
       |                    |   option will be hidden when creating private              |
       |                    |   dashboards.                                              |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to users who need to create a private dashboard using|
       |                    | JSON templates.                                            |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+

.. _DashboardImportPublic:

   :ref:`🔑DashboardImportPublic <DashboardImportPublic>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Dashboard**                                              |
       +====================+============================================================+
       | Description        | | This permission controls the ability to import public    |
       |                    |   (shared) dashboards from JSON files,                     |
       |                    | | If user is not granted this permission, the Import       |
       |                    |   option will be hidden when creating public               |
       |                    |   dashboards.                                              |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to users who need to create a public dashboard using |
       |                    | JSON templates.                                            |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+

.. _ShareDashboardLink:

   :ref:`🔑ShareDashboardLink <ShareDashboardLink>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Dashboard**                                              |
       +====================+============================================================+
       | Description        | | This permission controls the ability to share direct     |
       |                    |   links to a public dashboards with other users.           |
       |                    | | If user is not granted this permission, the Share        |
       |                    |   option will be hidden in the dashboard action menu.      |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to users who need to share dashboard links with      |
       |                    | colleagues for collaboration or reference purposes.        |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``EVERYBODY``                                              |
       +--------------------+------------------------------------------------------------+



.. _DocumentRead:

   :ref:`🔑DocumentRead <DocumentRead>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Document**                                               |
       +====================+============================================================+
       | Description        | | In Portal, this permission controls the ability to view  |
       |                    |   all documents across all cases and tasks without         |
       |                    |   pagination in a new page.                                |
       |                    | | If user is not granted this permission, the              |
       |                    |   :guilabel:`Show more` option will be hidden in the       |
       |                    |   Documents widget.                                        |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to users who need full visibility into all           |
       |                    | documents for viewing or document management purposes.     |
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _DocumentWrite:
.. _DocumentOfInvolvedCaseWrite:

   :ref:`🔑DocumentWrite <DocumentWrite>` and :ref:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Document**                                               |
       +====================+============================================================+
       | Description        | | These permissions allow users to **upload and delete**   |
       |                    |   documents in the Documents widget.                       |
       |                    | | Portal checks both permissions. If user has either one,  |
       |                    |   they will see the upload and delete document actions     |
       |                    | | **Note:** Upload and delete actions may be hidden for    |
       |                    |   cases in DONE state if the global setting                |
       |                    |   ``HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE`` is enabled.       |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permissions**                                       |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant one of these permissions to users who need to upload |
       |                    | and delete documents in the Documents widget.              |
       +--------------------+------------------------------------------------------------+
       | Granted by default | | **DocumentWrite**: No                                    |
       |                    | | **DocumentOfInvolvedCaseWrite**: ``EVERYBODY``           |
       +--------------------+------------------------------------------------------------+


.. _RoleManagement:

   :ref:`🔑RoleManagement <RoleManagement>`
      +--------------------+------------------------------------------------------------+
      | Category           | **Role Management**                                        |
      +====================+============================================================+
      | Description        | | This permission controls access to the Role Management   |
      |                    |   tab in the :ref:`admin-settings` page.                   |
      |                    | | To perform operations such as creating new roles or      |
      |                    |   deleting roles, additional core permissions like         |
      |                    |   :ref:`RoleCreate`, :ref:`RoleDelete` are required.       |
      +--------------------+------------------------------------------------------------+
      | Type               | **Portal permission**                                      |
      +--------------------+------------------------------------------------------------+
      | Use case           | Only grant the privilege to access Role Management tab     |
      |                    | in :ref:`admin-settings` page for certain admins.          |
      +--------------------+------------------------------------------------------------+
      | Granted by default | ``Developer`` and ``Admin`` user.                          |
      +--------------------+------------------------------------------------------------+

.. _RoleCreate:

   :ref:`🔑RoleCreate <RoleCreate>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Role Management**                                        |
       +====================+============================================================+
       | Description        | | In Portal, this permission controls the ability to create|
       |                    |   new dynamic roles inside the Role Management tab in      |
       |                    |   :ref:`admin-settings` page.                              |
       |                    | | The button :guilabel:`Create new role` in the Role       |
       |                    |   Management tab will be hidden if admin is not granted    |
       |                    |   this permission.                                         |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant permission to certain admins to allow them to create |
       |                    | new dynamic roles.                                         |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+

.. _RoleDelete:

   :ref:`🔑RoleDelete <RoleDelete>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Role Management**                                        |
       +====================+============================================================+
       | Description        | | In Portal, if an admin is not granted this permission,   |
       |                    |   the delete icon is disabled to prevent deletion of       |
       |                    |   existing dynamic roles in the Role Management tab on the |
       |                    |   :ref:`admin-settings` page.                              |
       |                    | | If the admin has this permission but the role is not     |
       |                    |   dynamic, the delete icon is still disabled.              |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant permission to certain admins to allow them to delete |
       |                    | dynamic roles.                                             |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+

.. _RoleMove:

   :ref:`🔑RoleMove <RoleMove>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Role Management**                                        |
       +====================+============================================================+
       | Description        | | In Portal, this permission controls the ability to select|
       |                    |   parent role when creating a dynamic role in Role         |
       |                    |   Management tab in :ref:`admin-settings` page.            |
       |                    | | If the admin is not granted this permission, the parent  |
       |                    |   role selection in the Role Creation dialog will be       |
       |                    |   disabled and set to ``Everybody`` by default.            |
       |                    |   See :ref:`manage-roles`.                                 |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant permission to certain admins to allow them to select |
       |                    | parent role when creating a dynamic role in Role           |
       |                    | Management tab in :ref:`admin-settings` page.              |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+


.. _TaskCaseAddNote:

   :ref:`🔑TaskCaseAddNote <TaskCaseAddNote>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Notes and Comments**                                     |
       +====================+============================================================+
       | Description        | | This permission controls the ability to add notes in a   |
       |                    |   task or case.                                            |
       |                    | | If user is not granted this permission, they won't see   |
       |                    |   the :guilabel:`Add note` option in the History widget    |
       |                    |    (Case details page) and Notes widget (Task details page)|
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to all users who need to collaborate and communicate |
       |                    | on tasks and cases by adding notes.                        |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``EVERYBODY``                                              |
       +--------------------+------------------------------------------------------------+

.. _TaskCaseShowMoreNote:

   :ref:`🔑TaskCaseShowMoreNote <TaskCaseShowMoreNote>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Notes and Comments**                                     |
       +====================+============================================================+
       | Description        | | This permission controls the visibility of the           |
       |                    |   :guilabel:`Show more` option in the top right corner of  |
       |                    |   the History widget (:ref:`full-case-list`) and Notes     |
       |                    |   widget (:ref:`full-task-list`).                          |
       |                    | | When user clicks :guilabel:`Show more`, the full list of |
       |                    |   notes is shown without pagination in a new page.         |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to users who need to view the complete list of notes |
       |                    | without pagination in History and Notes widgets.           |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``EVERYBODY``                                              |
       +--------------------+------------------------------------------------------------+

.. _NoteReadAllCaseTaskDetails:

   :ref:`🔑NoteReadAllCaseTaskDetails <NoteReadAllCaseTaskDetails>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Notes and Comments**                                     |
       +====================+============================================================+
       | Description        | | This permission allows non-admin users to view system    |
       |                    |   notes  in the History widget and Notes widget.           | 
       |                    | | Without this permission, regular users only see user     |
       |                    |   notes, while system notes remain visible only to admins. |
       |                    | | **Note:** Visibility also depends on global settings     |
       |                    |   ``HIDE_SYSTEM_NOTES_FROM_HISTORY`` and                   |
       |                    |   ``HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR``.        |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to users who need to see system activity logs in     |
       |                    | case and task histories.                                   |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+

   .. note::
      **Pre-LTS Versions:** This permission does not exist in Portal versions before 12.0. Use global variables ``Portal.Histories.HideSystemNotes`` and ``Portal.Histories.HideSystemNotesForAdministrator`` instead.


.. _NewsManagement:

   :ref:`🔑NewsManagement <NewsManagement>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Dashboard**                                              |
       +====================+============================================================+
       | Description        | | This permission controls the ability to create, edit, and|
       |                    |   delete news items in the News widget on dashboards.      |
       |                    | | If user is not granted this permission, they can only    |
       |                    |   view news items, but cannot create, edit, or delete them.|
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to users who need to manage and publish news content |
       |                    | on dashboard news widgets.                                 |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+

.. _PasswordValidation:

   :ref:`🔑PasswordValidation <PasswordValidation>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Admin Settings & Configuration**                         |
       +====================+============================================================+
       | Description        | This permission controls access to the Password            |
       |                    | Validation tab which supports to configure password        |
       |                    | complexity requirements in the :ref:`admin-settings` page. |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Only grant the privilege to access Password Validation tab |
       |                    | in :ref:`admin-settings` page for certain admins.          |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``Developer`` and ``Admin`` user.                          |
       +--------------------+------------------------------------------------------------+

.. _NotificationChannelsSetting:

   :ref:`🔑NotificationChannelsSetting <NotificationChannelsSetting>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Admin Settings & Configuration**                         |
       +====================+============================================================+
       | Description        | This permission allows overwriting default notification    |
       |                    | settings (opt-out notifications) in :ref:`my-profile` page.|
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Prevent certain users or roles from modifying notification |
       |                    | channel preferences in :ref:`my-profile` page.             |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``EVERYBODY``                                              |
       +--------------------+------------------------------------------------------------+

.. _CreatePublicExternalLink:

   :ref:`🔑CreatePublicExternalLink <CreatePublicExternalLink>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Process & External Links**                               |
       +====================+============================================================+
       | Description        | | This permission controls the visibility of the           |
       |                    |   **Visibility** section in the Add External Link dialog   |
       |                    |   on the :ref:`full-process-list` page.                    |
       |                    | | If user is not granted this permission, the Visibility   |
       |                    |   section is hidden and they can only create private       |
       |                    |   external links (visible only to themselves).             |
       |                    | | With this permission, users can choose to create public  |
       |                    |   links with role-based permissions that appear in the     |
       |                    |   process list for selected roles.                         |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to users who need to create external links that are  |
       |                    | shared with other users or roles, not just private links.  |
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

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

