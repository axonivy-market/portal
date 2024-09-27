.. _list-permissions:

Permission List
===============

Portal has a flexible security system that allows you to configure who can access applications, and what they can do/see in Portal.


Portal Task Permissions
-----------------------

- _`TaskReadAll`
    - Permission to see all tasks

- _`SystemTaskReadAll`
    - Permission to see system tasks

- _`TaskReadOwnCaseTasks`
    - Permission to see related tasks

    - Granted to role Everybody by default

- _`TaskParkOwnWorkingTask`
    - Permission to reserve my own tasks

    - Granted to role Everybody by default

- _`TaskResetOwnWorkingTask`
    - Permission to reset my own tasks 

    - Granted to role Everybody by default

- _`TaskWriteName`
    - Permission to change the task name

- _`TaskWriteDescription`
    - Permission to change the task description

- _`TaskWriteOriginalPriority`
    - Permission to change the task priority

- _`TaskWriteActivator`
    - Permission to delegate tasks

    - Granted to role Everybody by default

- _`TaskWriteExpiryActivator`
    - Permission to change the expiry responsible

- _`TaskWriteDelayTimestamp`
    - Permission to change the delay time

- _`TaskReset`
    - Permission to reset all tasks

- _`TaskDestroy`
    - Permission to see the Destroy Task action

- _`TaskWriteExpiryTimestamp`   
    - Control whether to change the deadline

- _`TaskWriteActivatorOwnTasks`
    - Permission to delegate my own tasks

- _`TaskDisplayAdditionalOptions`
    - Permission to see additional actions

    - Granted to role Everybody by default

- _`TaskDisplayResetAction`
    - Permission to see the Reset action

    - Granted to role Everybody by default

- _`TaskDisplayReserveAction`
    - Permission to see the Reserve action

    - Granted to role Everybody by default

- _`TaskDisplayDelegateAction`
    - Permission to see the Delegate action

    - Granted to role Everybody by default

- _`TaskDisplayWorkflowEventAction`
    - Permission to see the Workflow Event action

- _`TaskDisplayDestroyAction`
    - Permission to see the Destroy action

- _`TaskResetReadyForJoin`
    - Permission to reset tasks that are in state Ready for Join

- _`ShareTaskDetailsLink`
    - Permission to see the Share button in task details

    - Granted to role Everybody by default

Portal Case Permissions
-----------------------

- _`CaseReadAll`
    - Permission to see all cases

- _`CaseDestroy`
    Permission to delete cases

- _`CaseWriteName`
    - Permission to change the case name

- _`CaseWriteDescription`
    - Permission to change the case description

- _`ShowAllTasksOfCase`
    - Permission to see the related tasks's action 

    - Granted to role Everybody by default

- _`ShowCaseDetails`
    - Permission to see the Business details action

    - Granted to role Everybody by default

- _`ShareCaseDetailsLink`
    - Permission to see the Share button in case details

    - Granted to role Everybody by default

Portal General Permissions
--------------------------

- _`RoleCreate`
    - Permission to create a new dynamic role 

- _`RoleDelete`
    - Permission to delete a dynamic role

- _`RoleMove`
    - Permission to move role (select the parent role)

- _`RoleReadAll`
    - Permission to see all roles

    - Granted to role Everybody by default

- _`DocumentRead`
    - Permission to see all documents

- _`DocumentWrite`
    - Permission to update, delete documents

- _`DocumentOfInvolvedCaseWrite`
    - Permission to update, delete documents

    - Granted to role Everybody by default

- _`DashboardWriteOwn`
    - Permission to update private dashboards

    - Granted to role Everybody by default

- _`DashboardWritePublic`
    - Permission to update public dashboards

- _`AccessFullProcessList`
    - Permission to access the full process list. See :ref:`full-process-list`.

    - Granted to role Everybody by default

- _`AccessFullTaskList`
    - Permission to access the full task list. See :ref:`full-task-list`.

    - Granted to role Everybody by default

- _`AccessFullCaseList`
    - Permission to access the full case list. See :ref:`full-case-list`.

    - Granted to role Everybody by default

- _`TaskCaseAddNote`
    - Permission to add notes to a task/case

    - Granted to role Everybody by default

- _`TaskCaseShowMoreNote`
    - Permission to see Show more note

    - Granted to role Everybody by default

- _`CreatePublicExternalLink`
    - Permission to create public external links, all other users can see the links in the full process list.

- _`RoleManagement`
    - Permission to access the Role Management tab

- _`NewsManagement`
    - Permission to manage the content of the News feed

- _`PasswordValidation`
    - Permission to access the Password Validation section in the Admin Settings page

- _`DashboardExportOwn`
    - Permission to export private dashboards

- _`DashboardExportPublic`
    - Permission to export public dashboards

- _`DashboardImportOwn`
    - Permission to import private dashboards

- _`DashboardImportPublic`
    - Permission to import public dashboards

- _`ShareDashboardLink`
    - Permission to share dashboard links

    - Granted to role Everybody by default

.. _portal-absence-and-sub-permission:

Portal Absence And Substitute Permissions
-----------------------------------------

- _`UserCreateAbsence`
    - Permission to create, edit absences of all users

- _`UserCreateOwnAbsence`
    - Permission to create, edit my own absences

    - Granted to role Everybody by default

- _`UserDeleteAbsence`
    - Permission to remove the absence entries for all users

- _`UserDeleteOwnAbsence`
    - Permission to remove my own absence entries

    - Granted to role Everybody by default

- _`UserReadAbsences`
    - Permission to read absences of all users

- _`UserReadOwnAbsences`
    - Permission to read my own absences

    - Granted to role Everybody by default

- _`UserCreateSubstitute`
    - Permission to create substitutes of all users

- _`UserCreateOwnSubstitute`
    - Permission to create my own substitutes

    - Granted to role Everybody by default

- _`UserReadSubstitutes`
    - Permission to read substitutes of all users

