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
   
   The Portal is built as a layer above the Axon Ivy Engine core. Not every core engine permission is automatically used or supported by the Portal.
   If you require a specific engine permission not currently supported by the Portal, please contact Axon Ivy support.
   Many behaviors in Portal require multiple permissions to work correctly. For example, to reset a task, a user typically needs both the permission ``TaskDisplayResetAction`` to display Reset button and the permission ``TaskReset`` to reset it.
   Ensure you review all related permissions when configuring access.

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
       | Description        | View all user tasks (not system) in the system regardless   |
       |                    | of assignment                                               |
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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

.. _TaskWriteDescription:

   :ref:`🔑TaskWriteDescription <TaskWriteDescription>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +--------------------+------------------------------------------------------------+
       | Description        | Modify task description                                    |
       |                    | Cannot change terminated tasks (DONE, DESTROYED, FAILED)   |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can change task description      |
       |                    | in task details.                                           | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskWriteOriginalPriority:

   :ref:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +--------------------+------------------------------------------------------------+
       | Description        | Change task priority level                                 |
       |                    | Cannot change tasks in states: DONE, DESTROYED, FAILED     | 
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can change task priority         |
       |                    | in task details.                                           | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskWriteExpiryTimestamp:

   :ref:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +--------------------+------------------------------------------------------------+
       | Description        | Change task expiry date                                    |
       |                    | Cannot change tasks in states: DONE, DESTROYED, FAILED     |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with ``AXONIVY_PORTAL_ADMIN`` role and has            | 
       |                    | this permission can change task expiry date                | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskWriteActivator:

   :ref:`🔑TaskWriteActivator <TaskWriteActivator>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +--------------------+------------------------------------------------------------+
       | Description        | Delegate tasks to other users/roles                        |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission can delegate task to other       | 
       |                    | user/roles in the same security context                    |  
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskWriteExpiryActivator:

   :ref:`🔑TaskWriteExpiryActivator <TaskWriteExpiryActivator>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +--------------------+------------------------------------------------------------+
       | Description        | Change the responsible when task expires                   |
       |                    | Cannot change tasks in states: DONE, DESTROYED, FAILED     |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with ``AXONIVY_PORTAL_ADMIN`` role and has            | 
       |                    | this permission can change responsible after task expires  | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _TaskWriteDelayTimestamp:

   :ref:`🔑TaskWriteDelayTimestamp <TaskWriteDelayTimestamp>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task Actions**                                           |
       +--------------------+------------------------------------------------------------+   
       | Description        | Modify task delay/start time                               |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with ``AXONIVY_PORTAL_ADMIN`` role and has            | 
       |                    | this permission can change task delay/start time           |
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _TaskWriteActivatorOwnTasks:

   :ref:`🔑TaskWriteActivatorOwnTasks <TaskWriteActivatorOwnTasks>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task UI Display Permissions**                            |
       +--------------------+------------------------------------------------------------+
       | Description        | Delegate personal/group tasks assigned to user             |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | Not sure  ???                                              | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _TaskDisplayAdditionalOptions:

   :ref:`🔑TaskDisplayAdditionalOptions <TaskDisplayAdditionalOptions>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task UI Display Permissions**                            |
       +--------------------+------------------------------------------------------------+
       | Description        | Display additional action                                  |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have additional             |
       |                    | menu items from case map.                                  |
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _TaskDisplayResetAction:

   :ref:`🔑TaskDisplayResetAction <TaskDisplayResetAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task UI Display Permissions**                            |
       +--------------------+------------------------------------------------------------+
       | Description        | Display reset action                                       |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Reset``              |
       |                    | menu item in the task action menu. User also needs         |
       |                    | ``TaskResetOwnWorkingTask`` or ``TaskReset`` permission    |
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+


.. _TaskDisplayReserveAction:

   :ref:`🔑TaskDisplayReserveAction <TaskDisplayReserveAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task UI Display Permissions**                            |
       +--------------------+------------------------------------------------------------+
       | Description        | Display reserve action                                     |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Reserve``            |
       |                    | menu item in the task action menu. User also needs         |
       |                    | ``TaskParkOwnWorkingTask`` permission to enable this menu. | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+
   
.. _TaskDisplayDelegateAction:

   :ref:`🔑TaskDisplayDelegateAction <TaskDisplayDelegateAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task UI Display Permissions**                            |
       +--------------------+------------------------------------------------------------+
       | Description        | Display delegate action                                    |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Delegate``           |
       |                    | menu item in the task action menu. User also needs         |
       |                    | ``TaskWriteActivator`` permission to enable this menu.     |  
       +--------------------+------------------------------------------------------------+
       | Granted by default | Yes                                                        |
       +--------------------+------------------------------------------------------------+

.. _TaskDisplayDestroyAction:

   :ref:`🔑TaskDisplayDestroyAction <TaskDisplayDestroyAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task UI Display Permissions**                            |
       +--------------------+------------------------------------------------------------+
       | Description        | Display destroy action                                     |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Destroy``            |
       |                    | menu item in the task action menu. User also needs         |
       |                    | ``TaskDestroy`` permission to enable this menu.            |  
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

.. _TaskDisplayWorkflowEventAction:

   :ref:`🔑TaskDisplayWorkflowEventAction <TaskDisplayWorkflowEventAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task UI Display Permissions**                            |
       +--------------------+------------------------------------------------------------+
       | Description        | Display workflow events                                    |
       |                    | Allows viewing task execution history and events           |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Workflow events``    |
       |                    | menu item in the task action menu. User also needs         |
       |                    | ``WorkflowEventReadAll`` permission to enable this menu.   | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+

     
.. _TaskDisplayCustomFieldsAction:

   :ref:`🔑TaskDisplayCustomFieldsAction <TaskDisplayCustomFieldsAction>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task UI Display Permissions**                            |
       +--------------------+------------------------------------------------------------+
       | Description        | Display custom fields of case                              | 
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Custom fields``      |
       |                    | menu item in the task action menu                          | 
       +--------------------+------------------------------------------------------------+
       | Granted by default | No                                                         |
       +--------------------+------------------------------------------------------------+


.. _ShareTaskDetailsLink:

   :ref:`🔑ShareTaskDetailsLink <ShareTaskDetailsLink>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Task UI Display Permissions**                            |
       +--------------------+------------------------------------------------------------+
       | Description        | Display share case details link                            |
       +--------------------+------------------------------------------------------------+
       | Type               | **Portal permission**                                      |
       +--------------------+------------------------------------------------------------+
       | Use case           | User with this permission will have ``Share`` button       |
       |                    | next to ``Edit`` button in task details                    | 
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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

.. _CaseWriteDescription:

   :ref:`🔑CaseWriteDescription <CaseWriteDescription>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case Property Modifications**                            |
       +--------------------+------------------------------------------------------------+
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


.. _ShowCaseDetails:

   :ref:`🔑ShowCaseDetails <ShowCaseDetails>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Case UI Display Permissions**                            |
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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

.. _AccessFullProcessList:

   :ref:`🔑AccessFullProcessList <AccessFullProcessList>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Portal Page Access**                                     |
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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

.. _RoleReadAll:

   :ref:`🔑RoleReadAll <RoleReadAll>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Role Management**                                        |
       +--------------------+------------------------------------------------------------+
       | Description        | | Portal does not extend any logic based on this           |
       |                    |   permission, but automatically grants it to admins to     |
       |                    |   allow them to view all roles.                            |
       |                    | | Admins will encounter an error when accessing            |
       |                    |   :ref:`admin-settings` if lacking this permission.        |
       +--------------------+------------------------------------------------------------+
       | Type               | **Core permission**                                        |
       +--------------------+------------------------------------------------------------+
       | Use case           | Grant to admins or users who need to access all available  |
       |                    | roles.                                                     |
       +--------------------+------------------------------------------------------------+
       | Granted by default | ``EVERYBODY``                                              |
       +--------------------+------------------------------------------------------------+

.. _RoleCreate:

   :ref:`🔑RoleCreate <RoleCreate>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Role Management**                                        |
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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

.. _RoleManagement:

   :ref:`🔑RoleManagement <RoleManagement>`
      +--------------------+------------------------------------------------------------+
      | Category           | **Role Management**                                        |
      +--------------------+------------------------------------------------------------+
      | Description        | | This permission controls access to the Role Management   |
      |                    |   tab in the :ref:`admin-settings` page.                   |
      |                    | | To perform operations such as creating new roles or      |
      |                    |   deleting roles, additional core permissions like         |
      |                    |   **RoleCreate** and **RoleDelete** are required.          |
      +--------------------+------------------------------------------------------------+
      | Type               | **Portal permission**                                      |
      +--------------------+------------------------------------------------------------+
      | Use case           | Only grant the privilege to access Role Management tab     |
      |                    | in :ref:`admin-settings` page for certain admins.          |
      +--------------------+------------------------------------------------------------+
      | Granted by default | ``Developer`` and ``Admin`` user.                          |
      +--------------------+------------------------------------------------------------+

.. _RoleMove:

   :ref:`🔑RoleMove <RoleMove>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Role Management**                                        |
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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

.. _UserReadOwnAbsences:

   :ref:`🔑UserReadOwnAbsences <UserReadOwnAbsences>`
       +--------------------+------------------------------------------------------------+
       | Category           | **Manage own absences**                                    |
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
       +--------------------+------------------------------------------------------------+
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
#. :ref:`🔑TaskWriteDescription <TaskWriteDescription>`
#. :ref:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`
#. :ref:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>` 
#. :ref:`🔑TaskWriteDescription <TaskWriteDescription>`
#. :ref:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`
#. :ref:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`
#. :ref:`🔑TaskWriteActivator <TaskWriteActivator>`
#. :ref:`🔑TaskWriteDelayTimestamp <TaskWriteDelayTimestamp>`

**Case Permissions:**

#. :ref:`🔑CaseReadAll <CaseReadAll>`
#. :ref:`🔑CaseDestroy <CaseDestroy>`
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


