.. _customization-task-delegation:

Task Delegation
===============

To customize the list of users and roles a task can be delegated to,
create a callable subprocess with:

**Signature**: portalCalculateTaskDelegate

+--------------------+-----------------------------------------------------------+---------------+
| Name               | Type                                                      | Note          |
+====================+===========================================================+===============+
| **Parameter**                                                                                  |
+--------------------+-----------------------------------------------------------+---------------+
| roles              | java.util.List<com.axonivy.portal.components.dto.RoleDTO> |               |
+--------------------+-----------------------------------------------------------+---------------+
| users              | java.util.List<com.axonivy.portal.components.dto.UserDTO> |               |
+--------------------+-----------------------------------------------------------+---------------+
| currentUser        | com.axonivy.portal.components.dto.SecurityMemberDTO       |               |
+--------------------+-----------------------------------------------------------+---------------+
| task               | ch.ivyteam.ivy.workflow.ITask                             |               |
+--------------------+-----------------------------------------------------------+---------------+
| **Result**                                                                                     |
+--------------------+-----------------------------------------------------------+---------------+
| status             | java.lang.String                                          | OK or SKIP    |
+--------------------+-----------------------------------------------------------+---------------+
| roles              | java.util.List<com.axonivy.portal.components.dto.RoleDTO> |               |
+--------------------+-----------------------------------------------------------+---------------+
| users              | java.util.List<com.axonivy.portal.components.dto.UserDTO> |               |
+--------------------+-----------------------------------------------------------+---------------+

|calculate-task-delegate|

The parameters of the callable subprocess data contain the
``currentUser`` and the current ``task`` to be delegated.

Get the users and roles to which the task can be delegated to from
the lists ``users`` and ``roles``. Modify these lists to create
your delegate list.

Portal will call subprocesses with the details above and then combine all
``roles`` and ``users`` from the results into a list of roles and users the task can be delegated to.
To skip the result of one callable subprocess, please set the result variable ``status`` to ``"SKIP"``.

.. tip::
    Portal provides the standard ``users`` list together with their roles as a parameter for the callable.
    Please filter the ``users`` list instead of creating a new user list as this is more performant.

    In case you want to filter specific users by some roles, please use the method ``getRoles()`` of each user.

.. |calculate-task-delegate| image:: images/task-delegation/calculate-task-delegate.png