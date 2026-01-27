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

.. _TaskReadAll:

   :ref:`🔑TaskReadAll <TaskReadAll>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Visibility                                             |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to view **all user tasks** in the system,     |
       |                        | independent of task assignment, responsibility, or          |
       |                        | involvement. System tasks are not included.                 |
       |                        | With this you see all tasks in the security context.        |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Required for administrators, supervisors, or support users  |
       |                        | who need a global overview of all user tasks for            |
       |                        | monitoring, troubleshooting, audits, or operational support.| 
       |                        | Without this permission, users only see tasks they are      |
       |                        | directly involved in.                                       |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | No                                                          |
       +------------------------+-------------------------------------------------------------+


.. _SystemTaskReadAll:

   :ref:`🔑SystemTaskReadAll <SystemTaskReadAll>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Visibility                                             |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to view system tasks, such as background jobs | 
       |                        | and automated tasks executed by the platform. These tasks   |
       |                        | are not created or processed by end users.                  |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Intended for administrators and technical users who need    |                        
       |                        | insight into **background processing**, system behavior, or |                        
       |                        | task execution for **debugging, monitoring, or operational  |
       |                        | troubleshooting**. Not relevant for business users.         |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | No                                                          |
       +------------------------+-------------------------------------------------------------+


.. _TaskReadOwnCaseTasks:

   :ref:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Visibility                                             |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to view **tasks that belong to cases they are |                        
       |                        | involved in**, even if the tasks are assigned to other      |
       |                        | users or roles.                                             |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | See my own task and case in task list, case list            |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+


.. _TaskParkOwnWorkingTask:

   :ref:`🔑TaskParkOwnWorkingTask <TaskParkOwnWorkingTask>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Actions                                                |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **reserve (park) a task they are currently |
       |                        | working on**, temporarily setting it aside without          |
       |                        | completing or releasing it.                                 |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Useful when a user needs to **interrupt their work**, wait  |
       |                        | for additional information, or switch priorities, while     |
       |                        | ensuring the task remains reserved and is not taken by      |
       |                        | another user. Enables the Reserve action in the task menu.  |                                            
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _TaskResetOwnWorkingTask:

   :ref:`🔑TaskResetOwnWorkingTask <TaskResetOwnWorkingTask>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Actions                                                |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **reset their own working task to its      |
       |                        | initial state**. The reset action is available when the     |
       |                        | task is in one of the following states: **RESUMED, PARKED,  |
       |                        | READY_FOR_JOIN, FAILED**. Resetting a task clears the       |
       |                        | current working progress and returns it to its original     |
       |                        | start state.                                                |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Used when a user needs to **start a task over from the      |
       |                        | beginning**, for example after an error, a failed execution,| 
       |                        | or an invalid intermediate state. Enables the Reset         |
       |                        | action in the task action menu for applicable task states.  |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+
     
.. _TaskReset:

   :ref:`🔑TaskReset <TaskReset>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Actions                                                |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **reset any task within the current        |
       |                        | security context**, regardless of task ownership or         |
       |                        | assignment.                                                 |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Intended for **high-privileged users**, such as             |
       |                        | administrators or support staff, who need to reset tasks    |
       |                        | to resolve errors, unblock processes, or correct invalid    |
       |                        | task states.                                                |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _TaskResetReadyForJoin:


   :ref:`🔑TaskResetReadyForJoin <TaskResetReadyForJoin>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Actions                                                |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **reset tasks that are currently in the    |
       |                        | READY_FOR_JOIN state**.                                     |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Useful for **workflow error recovery**, for example when    |
       |                        | tasks are blocked or cannot be joined correctly and need    |
       |                        | to be reset to restore normal processing.                   | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _TaskDestroy:

   :ref:`🔑TaskDestroy <TaskDestroy>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Actions                                                |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **permanently delete tasks**. The action   |
       |                        | is only available if the task state is **not DESTROYED or   |
       |                        | DONE**.                                                     |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | High-privilege permission for **administrators** who need   |
       |                        | to permanently remove tasks, for example during cleanup,    |
       |                        | error handling, or system maintenance.                      | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _TaskWriteDescription:

   :ref:`🔑TaskWriteDescription <TaskWriteDescription>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Property Modifications                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **modify the task description**. The       | 
       |                        | description cannot be changed if the task is in a           | 
       |                        | terminated state (**DONE, DESTROYED, FAILED**).             |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Enables users with this permission to **update or correct   |
       |                        | the task description** in the task details, for example to  |
       |                        | clarify requirements or add missing information.            | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _TaskWriteOriginalPriority:

   :ref:`🔑TaskWriteOriginalPriority <TaskWriteOriginalPriority>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Property Modifications                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **change the task priority level**. The    |
       |                        | priority cannot be modified if the task is in one of        |
       |                        | the following states: **DONE, DESTROYED, FAILED**.          | 
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Enables users with this permission to **adjust the          |
       |                        | priority of a task** in the task details, for example to    |
       |                        | reflect changing urgency or business importance.            | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _TaskWriteExpiryTimestamp:

   :ref:`🔑TaskWriteExpiryTimestamp <TaskWriteExpiryTimestamp>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Property Modifications                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **change the task expiry date**. The expiry| 
       |                        | date cannot be modified if the task is in one of the        |
       |                        | following states: **DONE, DESTROYED, FAILED**.              |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Allows users with the **AXONIVY_PORTAL_ADMIN** role and     |
       |                        | this permission to **update the task expiry date**, for     |
       |                        | example to extend or shorten deadlines.                     | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _TaskWriteActivator:

   :ref:`🔑TaskWriteActivator <TaskWriteActivator>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Property Modifications                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **delegate tasks to other users or roles**.|
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Enables users with this permission to **delegate a task to  |
       |                        | other users or roles within the same security context**,    |
       |                        | for example to change responsibility or hand over work.     |  
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _TaskWriteExpiryActivator:

   :ref:`🔑TaskWriteExpiryActivator <TaskWriteExpiryActivator>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Property Modifications                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **change the responsible user or role when |
       |                        | a task expires**. The responsible party cannot be changed   |
       |                        | if the task is in one of the following states:              |
       |                        | **DONE, DESTROYED, FAILED**.                                |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Enables users with the ``AXONIVY_PORTAL_ADMIN`` role and    |
       |                        | this permission to **define or update who becomes           |
       |                        | responsible after a task expires**.                         | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _TaskWriteDelayTimestamp:

   :ref:`🔑TaskWriteDelayTimestamp <TaskWriteDelayTimestamp>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task Property Modifications                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **modify the task delay or start time**.   |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Enables users with the ``AXONIVY_PORTAL_ADMIN`` role and    |
       |                        | this permission to **change when a task starts or is        |
       |                        | delayed**, for example to reschedule execution.             |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _TaskWriteActivatorOwnTasks:

   :ref:`🔑TaskWriteActivatorOwnTasks <TaskWriteActivatorOwnTasks>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Allows a user to **delegate personal or group tasks that    |    
       |                        | are assigned to themselves**.                               |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Enables users to **delegate tasks they currently own**      |
       |                        | to another user or role via the Portal UI, for example when |
       |                        | handing over work or changing responsibility within a team. |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Allows a user to **delegate personal or group tasks that    |    
       |                        | are assigned to themselves**.                               |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | No                                                          |
       +------------------------+-------------------------------------------------------------+


.. _TaskDisplayAdditionalOptions:

   :ref:`🔑TaskDisplayAdditionalOptions <TaskDisplayAdditionalOptions>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display additional task actions**    |
       |                        | in the user interface.                                      |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see **additional menu       |
       |                        | items** on tasks that are defined in the **case map**,      |
       |                        | enabling extended actions directly from the task UI.        |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _TaskDisplayResetAction:

   :ref:`🔑TaskDisplayResetAction <TaskDisplayResetAction>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display the reset action** for tasks | 
       |                        | in the user interface.                                      |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see the **Reset** menu item |
       |                        | in the task action menu. To actually reset a task, the user |
       |                        | must also have either the **TaskResetOwnWorkingTask** or    |
       |                        | **TaskReset** permission.                                   |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+


.. _TaskDisplayReserveAction:

   :ref:`🔑TaskDisplayReserveAction <TaskDisplayReserveAction>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display the reserve action**         |
       |                        | for tasks in the user interface.                            |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see the **Reserve** menu    |
       |                        | item in the task action menu. To actually reserve a task,   |
       |                        | the user must also have the **TaskParkOwnWorkingTask**      |
       |                        | permission.                                                 |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+
   
.. _TaskDisplayDelegateAction:

   :ref:`🔑TaskDisplayDelegateAction <TaskDisplayDelegateAction>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display the delegate action**        |
       |                        | for tasks in the user interface.                            |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see the **Delegate** menu   |
       |                        | item in the task action menu. To actually delegate a task,  |
       |                        | the user must also have the **TaskWriteActivator**          |
       |                        | permission.                                                 |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _TaskDisplayDestroyAction:

   :ref:`🔑TaskDisplayDestroyAction <TaskDisplayDestroyAction>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display the destroy action**         |
       |                        | for tasks in the user interface.                            |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see the **Destroy** menu    |
       |                        | item in the task action menu. To actually destroy a task,   |
       |                        | the user must also have the **TaskDestroy** permission.     |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _TaskDisplayWorkflowEventAction:

   :ref:`🔑TaskDisplayWorkflowEventAction <TaskDisplayWorkflowEventAction>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display workflow events** for a      |
       |                        | task, including the **task execution history and related    |
       |                        | workflow events**.                                          |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see the **Workflow Events** | 
       |                        | menu item in the task action menu. To actually view the     |
       |                        | workflow event data, the user must also have the            |
       |                        | **WorkflowEventReadAll** permission.                        |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

     
.. _TaskDisplayCustomFieldsAction:

   :ref:`🔑TaskDisplayCustomFieldsAction <TaskDisplayCustomFieldsAction>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display custom fields of a case**    |
       |                        | in the task user interface.                                 | 
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see the **Custom Fields**   |
       |                        | menu item in the task action menu, allowing them to view    |
       |                        | custom case fields related to the task.                     |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _ShareTaskDetailsLink:

   :ref:`🔑ShareTaskDetailsLink <ShareTaskDetailsLink>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Task UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display the share task details       |
       |                        | link** in the task user interface.                          |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see a **Share** button next |
       |                        | to the **Edit** button in the task details, allowing them   |
       |                        | to share a link to the task details.                        |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _permission-case-permissions:

Portal Case Permissions
-----------------------

.. _CaseReadAll:

   :ref:`🔑CaseReadAll <CaseReadAll>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Case Action                                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **view all cases within the security       |
       |                        | context**, regardless of whether the user is involved       |
       |                        | in the case.                                                |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant it to see all cases                                   | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _CaseDestroy:

   :ref:`🔑CaseDestroy <CaseDestroy>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Case Action                                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows to **permanently delete a case that is currently     |
       |                        | running**. Once deleted, the case and its data cannot be    |
       |                        | recovered.                                                  |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Intended for administrators who need to **remove            |
       |                        | incorrectly started, test, or blocked cases, for example    |
       |                        | during troubleshooting, cleanup, or system maintenance**.   |
       |                        | The Destroy action will be available in the case action     |
       |                        | menu.                                                       | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _CaseOwnerTaskDelegate:

   :ref:`🔑CaseOwnerTaskDelegate <CaseOwnerTaskDelegate>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Case Action                                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a **case owner to delegate all tasks related to      |
       |                        | their case** to other users or roles.                       |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users who are **case owners** and have this permission,     |
       |                        | together with **TaskWriteActivator**, can **delegate tasks  |
       |                        | within their own cases**, for example to hand over work,    |
       |                        | involve other team members, or reassign responsibilities.   |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _CaseWriteDescription:

   :ref:`🔑CaseWriteDescription <CaseWriteDescription>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Case Action                                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **edit the case description**. The         |
       |                        | description cannot be changed if the case is in the         |
       |                        | **DESTROYED** state.                                        |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Enables users with this permission to **update or correct   | 
       |                        | the case description** in the case details, for example to  |
       |                        | add context, clarify information, or fix mistakes.          | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _ShowCaseDetails:

   :ref:`🔑ShowCaseDetails <ShowCaseDetails>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Case UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display business details of a case   |
       |                        | in a separate tab**, providing additional case-related      |
       |                        | information.                                                |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see the **Business details**| 
       |                        | menu item in the case action menu, allowing them to open    |
       |                        | a new tab with extended case information.                   | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _CaseDisplayCustomFieldsAction:

   :ref:`🔑CaseDisplayCustomFieldsAction <CaseDisplayCustomFieldsAction>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Case UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display custom fields of a case**    | 
       |                        | in the user interface.                                      |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see the **Custom fields**   |
       |                        | menu item in the case action menu, allowing them to view    |
       |                        | additional, case-specific information.                      | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _ShareCaseDetailsLink:

   :ref:`🔑ShareCaseDetailsLink <ShareCaseDetailsLink>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Case UI Display Permissions                                 |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows the Portal to **display a link for sharing case      |
       |                        | details** in the user interface.                            |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Users with this permission will see a **Share** button next |
       |                        | to the **Edit** button in the case details, allowing them   |
       |                        | to share a link to the case details with others.            | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+


.. _permission-general-permissions:

Portal General Permissions
--------------------------

.. _AccessFullProcessList:

   :ref:`🔑AccessFullProcessList <AccessFullProcessList>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Portal Page Access                                          |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Controls the visibility of the **Processes** page in the    |
       |                        | left navigation menu and the **Processes** tab in the       |
       |                        | global search.                                              |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Used to **show or hide access to the Processes list** for   |
       |                        | specific users or roles, for example to simplify the user   |
       |                        | interface or restrict access to process overviews.          |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |     
       +------------------------+-------------------------------------------------------------+

.. _AccessFullTaskList:

   :ref:`🔑AccessFullTaskList <AccessFullTaskList>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Portal Page Access                                          |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Controls the visibility of the **Tasks** page in the left   |
       |                        | navigation menu and the **Tasks** tab in the global search. |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Used to **show or hide access to the Tasks list** for       |
       |                        | specific users or roles, for example to simplify the user   |
       |                        | interface or restrict task visibility.                      |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _AccessFullCaseList:

   :ref:`🔑AccessFullCaseList <AccessFullCaseList>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Portal Page Access                                          |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Controls the visibility of the **Cases** page in the left   |
       |                        | navigation menu and the **Cases** tab in the global search. |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Used to **show or hide access to the Cases list** for       |
       |                        | specific users or roles, for example to simplify the user   |
       |                        | interface or restrict case visibility.                      |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+


.. _DashboardWriteOwn:

   :ref:`🔑DashboardWriteOwn <DashboardWriteOwn>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Dashboard Permissions                                       |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **create and modify private (personal)     |
       |                        | dashboards** in the Dashboard Configuration page. If this   |
       |                        | permission is not granted, the **Private Dashboard** tab is |
       |                        | hidden and the user cannot manage their own dashboards.     |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant this permission to users who need to **create,        |
       |                        | customize, and maintain their own private and personal      |
       |                        | dashboards**.                                               |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _DashboardWritePublic:

   :ref:`🔑DashboardWritePublic <DashboardWritePublic>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Dashboard Permissions                                       |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **create and modify public (shared)        |
       |                        | dashboards** in the **Dashboard Configuration** page. If    |
       |                        | this permission is not granted, the **Public Dashboard**    |
       |                        | tab is hidden and the user cannot manage shared dashboards. |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to **administrators or dashboard managers** who       |
       |                        | need to **create, maintain, and manage dashboards**         |
       |                        | shared across the application.                              |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. note::
   If a user is not granted either **DashboardWriteOwn** or **DashboardWritePublic** permission, 
   the **Dashboard configuration** menu option will be hidden from the user profile dropdown menu.

.. _DashboardExportOwn:

   :ref:`🔑DashboardExportOwn <DashboardExportOwn>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Dashboard Permissions                                       |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **export private (personal) dashboards as  |
       |                        | JSON files** for backup or sharing purposes. If this        |
       |                        | permission is not granted, the Export option is hidden      |
       |                        | in the private dashboard actions menu.                      |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to users who need to **back up their personal         |
       |                        | dashboards or share dashboard configurations** with others  |
       |                        | or across environments.                                     |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _DashboardExportPublic:

   :ref:`🔑DashboardExportPublic <DashboardExportPublic>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Dashboard Permissions                                       |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **export public (shared) dashboards        |
       |                        | as JSON files** for backup or distribution purposes.        |
       |                        | If this permission is not granted, the Export option is     |
       |                        | hidden in the public dashboard actions menu.                |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to administrators who need to **back up or distribute |
       |                        | public dashboard templates**, for example across different  |
       |                        | environments.                                               |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _DashboardImportOwn:

   :ref:`🔑DashboardImportOwn <DashboardImportOwn>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Dashboard Permissions                                       |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **import private (personal) dashboards     |
       |                        | from JSON files**. If this permission is not granted, the   |
       |                        | Import option is hidden when creating private dashboards.   |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to users who need to **create personal dashboards     |
       |                        | from JSON templates**, for example when restoring backups   |
       |                        | or reusing existing configurations.                         |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _DashboardImportPublic:

   :ref:`🔑DashboardImportPublic <DashboardImportPublic>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Dashboard Permissions                                       |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **import public (shared) dashboards        |
       |                        | from JSON files**. If this permission is not granted, the   |
       |                        | Import option is hidden when creating public dashboards.    |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to users who need to **create shared dashboards       |
       |                        | from JSON templates**, for example when setting up          |
       |                        | dashboards across environments or reusing standard templates|
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _ShareDashboardLink:

   :ref:`🔑ShareDashboardLink <ShareDashboardLink>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Dashboard Permissions                                       |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **share direct links to public dashboards**| 
       |                        | with other users. If this permission is not granted, the    |
       |                        | Share option is hidden in the dashboard action menu.        |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to users who need to **share dashboard links with     |
       |                        | colleagues**, for example for collaboration, reporting,     |
       |                        | or reference purposes.                                      |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+



.. _DocumentRead:

   :ref:`🔑DocumentRead <DocumentRead>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Document                                                    |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **view all documents across all cases and  |
       |                        | tasks in the Portal** on a dedicated page without           |
       |                        | pagination. If this permission is not granted,              |
       |                        | the **Show more** option is hidden in the Documents widget. |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to users who need **full visibility into all          |
       |                        | documents**, for example for review, auditing, or document  |
       |                        | management purposes.                                        |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | No                                                          |
       +------------------------+-------------------------------------------------------------+

.. _DocumentWrite:
.. _DocumentOfInvolvedCaseWrite:

   :ref:`🔑DocumentWrite <DocumentWrite>` and :ref:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Document                                                    |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | These permissions allow users to **upload and delete        |
       |                        | documents** in the Documents widget. The Portal checks both |
       |                        | permissions: if a user has either one, the upload and       |
       |                        | delete actions are shown.                                   |
       |                        | Note: Upload and delete actions may be hidden for cases     |
       |                        | in the **DONE** state if the global setting                 |
       |                        | ``HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE`` is enabled.          |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant **one of these permissions** to users who need to     |
       |                        | **upload or delete** documents within the Documents widget, |
       |                        | depending on whether document access should be limited to   |
       |                        | involved cases or allowed more broadly.                     |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | ``DocumentWrite``: No                                       |
       |                        | ``DocumentOfInvolvedCaseWrite``: role ``Everybody``         |
       +------------------------+-------------------------------------------------------------+

.. _RoleReadAll:

   :ref:`🔑RoleReadAll <RoleReadAll>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Role Management                                             |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **view all available roles**. The Portal   |
       |                        | itself does not apply additional logic based on this        |
       |                        | permission, but it is **automatically granted to            |
       |                        | administrators** to allow role visibility. If this          |
       |                        | permission is missing, administrators will encounter an     |
       |                        | error when accessing **Admin Settings**.                    |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to **administrators or users** who need to **view     |
       |                        | and access all roles**, for example when configuring users, | 
       |                        | permissions, or role assignments.                           |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _RoleCreate:

   :ref:`🔑RoleCreate <RoleCreate>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Role Management                                             |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **create new dynamic roles** in the **Role |
       |                        | Management** tab within the **Admin Settings** page. If     |
       |                        | this permission is not granted, the **Create new role**     |
       |                        | button is hidden.                                           |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to **selected administrators** who are responsible    |
       |                        | for **creating and maintaining** dynamic roles within the   |
       |                        | system.                                                     |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _RoleDelete:

   :ref:`🔑RoleDelete <RoleDelete>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Role Management                                             |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **delete dynamic roles** in the **Role     |
       |                        | Management** tab within the **Admin Settings** page. If     |
       |                        | this permission is not granted, the delete icon is disabled.| 
       |                        | Even with this permission, **non-dynamic roles cannot be    |
       |                        | deleted** and the delete icon remains disabled.             |
       +------------------------+-------------------------------------------------------------+       
       | **Use case**           | Grant to **selected administrators** who are responsible    |
       |                        | for **removing dynamic roles** that are no longer needed.   |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _RoleManagement:

   :ref:`🔑RoleManagement <RoleManagement>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Role Management                                             |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **access the Role Management tab** in      |
       |                        | the **Admin Settings** page. This permission only grants    |
       |                        | access to the page itself. To perform actions such as       |
       |                        | **creating or deleting roles**, additional core permissions |
       |                        | like **RoleCreate** and **RoleDelete** are required.        |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to **selected administrators** who need **access to   |
       |                        | role management**, while controlling specific actions       |
       |                        | through additional permissions.                             |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _RoleMove:

   :ref:`🔑RoleMove <RoleMove>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Role Management                                             |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **select a parent role when creating a     |
       |                        | dynamic role** in the **Role Management** tab within the    |
       |                        | **Admin Settings** page. If this permission is not granted, |
       |                        | the parent role selection in the role creation dialog is    |
       |                        | disabled and automatically set to **Everybody**.            |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to **selected administrators** who need to **define   |
       |                        | role hierarchies** by choosing a specific parent role when  |
       |                        | creating dynamic roles.                                     |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _TaskCaseAddNote:

   :ref:`🔑TaskCaseAddNote <TaskCaseAddNote>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Notes and Comments                                          |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **add notes to a task or a case**. If      |
       |                        | this permission is not granted, the **Add note** option is  |
       |                        | hidden in the **History widget** (case details page) and    |
       |                        | the **Notes widget** (task details page).                   |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to users who need to **collaborate and communicate    |
       |                        | on tasks and cases** by adding notes, comments, or          |
       |                        | additional information.                                     |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _TaskCaseShowMoreNote:

   :ref:`🔑TaskCaseShowMoreNote <TaskCaseShowMoreNote>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Notes and Comments                                          |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Controls the visibility of the **Show more** option in the  |
       |                        | **History widget** (Full Case List) and the **Notes widget**| 
       |                        | (Full Task List). When a user clicks **Show more**, the     |
       |                        | complete list of notes is displayed on a separate page      |
       |                        | **without pagination**.                                     |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to users who need to **view the full list of notes**  | 
       |                        | for tasks or cases, for example for review, auditing, or    |
       |                        | detailed collaboration.                                     |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _NoteReadAllCaseTaskDetails:

   :ref:`🔑NoteReadAllCaseTaskDetails <NoteReadAllCaseTaskDetails>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Notes and Comments                                          |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows **non-admin users** to view system notes in the      |
       |                        | **History widget** and **Notes widget** for cases and tasks.| 
       |                        | Without this permission, regular users only see their own   |
       |                        | notes, while system notes remain visible only to admins.    |
       |                        | **Note**: Visibility also depends on the global settings    |
       |                        | ``HIDE_SYSTEM_NOTES_FROM_HISTORY`` and                      |
       |                        | ``HIDE_SYSTEM_NOTES_FROM_HISTORY_ADMINISTRATOR``.           |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to users who need to **see system-generated activity  |
       |                        | logs and system notes** in case and task histories, for     |
       |                        | example for troubleshooting or detailed process tracking.   |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

   .. note::
      **Pre-LTS Versions:** This permission does not exist in Portal versions before 12.0. Use global variables ``Portal.Histories.HideSystemNotes`` and ``Portal.Histories.HideSystemNotesForAdministrator`` instead.


.. _NewsManagement:

   :ref:`🔑NewsManagement <NewsManagement>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Dashboard Permissions                                       |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **create, edit, and delete news items** in |
       |                        | the **News widget** on dashboards. If this permission is    |
       |                        | not granted, users can **only view news items**, but cannot |
       |                        | create, edit, or delete them.                               |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to users who are responsible for **managing and       |
       |                        | publishing news content** on dashboard news widgets, for    |
       |                        | example administrators or content managers.                 |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _PasswordValidation:

   :ref:`🔑PasswordValidation <PasswordValidation>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Admin Settings & Configuration                              |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **access the Password Validation** tab in  |
       |                        | the **Admin Settings** page, where password complexity and  |
       |                        | validation rules can be configured.                         |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to **selected administrators** who are responsible    |
       |                        | for **defining and maintaining password security policies** |
       |                        | within the system.                                          |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _NotificationChannelsSetting:

   :ref:`🔑NotificationChannelsSetting <NotificationChannelsSetting>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Admin Settings & Configuration                              |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **override the default notification        |
       |                        | settings** (opt-out notifications) on the **My Profile**    |
       |                        | page.                                                       |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Use this permission to **allow or restrict users or roles   |
       |                        | from modifying their notification channel preferences** on  |
       |                        | the **My Profile** page.                                    |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _CreatePublicExternalLink:

   :ref:`🔑CreatePublicExternalLink <CreatePublicExternalLink>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Portal permission                                           |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Process & External Links                                    |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Controls the visibility of the **Visibility** section in    |
       |                        | the **Add External Link** dialog on the **Full Process      |
       |                        | List** page. If this permission is not granted, the         |
       |                        | Visibility section is hidden and users can only create      |
       |                        | **private external links** (visible only to themselves).    |
       |                        | With this permission, users can create **public external    |
       |                        | links** with role-based visibility that appear in the       |
       |                        | process list for selected roles.                            |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to users who need to **create external links shared   |
       |                        | with other users or roles**, not just private links, for    |
       |                        | example for collaboration or guided access to processes.    |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | No                                                          |
       +------------------------+-------------------------------------------------------------+

.. _permission-absence-substitute:

Portal Absence And Substitute Permissions
-----------------------------------------


.. _UserReadOwnAbsences:

   :ref:`🔑UserReadOwnAbsences <UserReadOwnAbsences>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Manage own absences                                         |
       +------------------------+-------------------------------------------------------------+
       | **Description**        |  Allows a user to **view their own absence records**.       |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Enables users to **see their personal absence information**,| 
       |                        | for example to review planned or recorded absences.         | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _UserCreateOwnAbsence:

   :ref:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Manage own absences                                         |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **create and edit their own absences**.    |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Enables users to **create and maintain their own absence    |
       |                        | entries**, for example for vacation or other planned        |
       |                        | absences.                                                   | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+

.. _UserDeleteOwnAbsence:

   :ref:`🔑UserDeleteOwnAbsence <UserDeleteOwnAbsence>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Manage own absences                                         |
       +------------------------+-------------------------------------------------------------+
       | **Description**        | Allows a user to **delete their own absence records**.      |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Enables users to **remove their own absence entries**, for  |
       |                        | example if an absence was entered incorrectly or is no      |
       |                        | longer relevant.                                            | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+


.. _UserReadAbsences:

   :ref:`🔑UserReadAbsences <UserReadAbsences>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Manage absences for all users                               |
       +------------------------+-------------------------------------------------------------+      
       | **Description**        | Allows a user to **view absence records of all users**      |
       |                        | within the current security context.                        |       
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to administrators or managers who need **visibility   |
       |                        | into absences of all users**, for example for planning,     |
       |                        | coordination, or administrative oversight.                  | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _UserCreateAbsence:

   :ref:`🔑UserCreateAbsence <UserCreateAbsence>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Manage absences for all users                               |
       +------------------------+-------------------------------------------------------------+     
       | **Description**        | Allows a user to **create and edit absence records** for    |
       |                        | any user within the current security context.               |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to administrators or managers who need to **create    |
       |                        | or update absences on behalf of other users**, for example  |
       |                        | for corrections or administrative handling.                 | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _UserDeleteAbsence:

   :ref:`🔑UserDeleteAbsence <UserDeleteAbsence>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Manage absences for all users                               |
       +------------------------+-------------------------------------------------------------+    
       | **Description**        | Allows a user to **delete absence records for any user**    |
       |                        | within the current security context.                        |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to administrators or managers who need to **remove    |
       |                        | absence entries for other users**, for example to correct   |
       |                        | mistakes or clean up outdated records.                      | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+


.. _UserCreateOwnSubstitute:

   :ref:`🔑UserCreateOwnSubstitute <UserCreateOwnSubstitute>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Manage substitutes                                          |
       +------------------------+-------------------------------------------------------------+   
       | **Description**        | Allows a user to **create their own substitute**.           |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Enables users to **define a substitute for themselves**,    |
       |                        | for example to ensure tasks and responsibilities are        |
       |                        | handled during their absence                                |
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - Role ``Everybody``                                    |
       +------------------------+-------------------------------------------------------------+
   

.. _UserCreateSubstitute:

   :ref:`🔑UserCreateSubstitute <UserCreateSubstitute>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Manage substitutes                                          |
       +------------------------+-------------------------------------------------------------+   
       | **Description**        | Allows a user to **create substitute assignments for any    |
       |                        | user** within the current security context.                 |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to administrators or managers who need to **assign    |
       |                        | substitutes on behalf of other users**, for example for     |
       |                        | planned absences or organizational coverage.                | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

.. _UserReadSubstitutes:

   :ref:`🔑UserReadSubstitutes <UserReadSubstitutes>`
       +------------------------+-------------------------------------------------------------+
       | Field                  | Description                                                 |
       +========================+=============================================================+
       | **Type**               | Core permission                                             |
       +------------------------+-------------------------------------------------------------+
       | **Category**           | Manage substitutes                                          |
       +------------------------+-------------------------------------------------------------+   
       | **Description**        | Allows a user to **view substitute assignments of any       |
       |                        | user** within the current security context.                 |
       +------------------------+-------------------------------------------------------------+
       | **Use case**           | Grant to administrators or managers who need **visibility   |
       |                        | into substitute assignments**, for example to review        |
       |                        | coverage, validate substitutions, or support organizational | 
       |                        | planning.                                                   | 
       +------------------------+-------------------------------------------------------------+
       | **Granted by default** | Yes - User ``Admin``                                        |
       +------------------------+-------------------------------------------------------------+

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


