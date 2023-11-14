.. _list-permissions:

Permission List
===============

Portal has a flexible security system that allows you to configure who can access applications, and what they can do/see in Portal.


Portal Task Permissions
-----------------------

- _`TaskReadAll`
    - Permission to see all tasks

- _`TaskReadOwnCaseTasks`
    - Permission to see related tasks

    - Granted to the 'Everybody' role by default

- _`TaskParkOwnWorkingTask`
    - Permission to reserve my own tasks

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskResetOwnWorkingTask`
    - Permission to reset my own tasks 

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskWriteName`
    - Permission to change the task name

- _`TaskWriteDescription`
    - Permission to change the task description

- _`TaskWriteOriginalPriority`
    - Permission to change the task priority

- _`TaskWriteActivator`
    - Permission to delegate tasks

    - **Default assignment**: :guilabel:`Everybody`

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

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskDisplayResetAction`
    - Permission to see the Reset action

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskDisplayReserveAction`
    - Permission to see the Reserve action

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskDisplayDelegateAction`
    - Permission to see the Delegate action

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskDisplayWorkflowEventAction`
    - Permission to see the Workflow Event action

- _`TaskDisplayDestroyAction`
    - Permission to see the Destroy action

- _`TaskResetReadyForJoin`
    - Permission to reset tasks that are in state Ready for Join

- _`ShareTaskDetailsLink`
    - Permission to see the Share button in task details

    - **Default assignment**: :guilabel:`Everybody`

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

    - **Default assignment**: :guilabel:`Everybody`

- _`ShowCaseDetails`
    - Permission to see the Business details action

- _`ShareCaseDetailsLink`
    - Permission to see the Share button in case details

    - **Default assignment**: :guilabel:`Everybody`

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

    - **Default assignment**: :guilabel:`Everybody`

- _`DocumentRead`
    - Permission to see all documents

- _`DocumentWrite`
    - Permission to update, delete documents

- _`DocumentOfInvolvedCaseWrite`
    - Permission to update, delete documents

    - **Default assignment**: :guilabel:`Everybody`

- _`DashboardWriteOwn`
    - Permission to update private dashboards

    - **Default assignment**: :guilabel:`Everybody`

- _`DashboardWritePublic`
    - Permission to update public dashboards

- _`AccessFullProcessList`
    - Permission to access the full process list. See :ref:`full-process-list`.

    - **Default assignment**: :guilabel:`Everybody`

- _`AccessFullTaskList`
    - Permission to access the full task list. See :ref:`full-task-list`.

    - **Default assignment**: :guilabel:`Everybody`

- _`AccessFullCaseList`
    - Permission to access the full case list. See :ref:`full-case-list`.

    - **Default assignment**: :guilabel:`Everybody`

- _`AccessFullStatisticsList`
    - Permission to access the statistics section. See :ref:`full-statistic-list`.

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskCaseAddNote`
    - Permission to add notes to a task/case

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskCaseShowMoreNote`
    - Permission to see Show more note

    - **Default assignment**: :guilabel:`Everybody`

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

    - **Default assignment**: :guilabel:`Everybody`

- _`DashboardImportPublic`
    - Permission to import public dashboards

    - **Default assignment**: :guilabel:`Everybody`

- _`ShareDashboardLink`
    - Permission to share dashboard links

    - **Default assignment**: :guilabel:`Everybody`


Portal Absence And Substitute Permissions
-----------------------------------------

- _`UserCreateAbsence`
    - Permission to create, edit absences of all users

- _`UserCreateOwnAbsence`
    - Permission to create, edit my own absences

    - **Default assignment**: :guilabel:`Everybody`

- _`UserDeleteAbsence`
    - Permission to remove the absence entries for all users

- _`UserDeleteOwnAbsence`
    - Permission to remove my own absence entries

    - **Default assignment**: :guilabel:`Everybody`

- _`UserReadAbsences`
    - Permission to read absences of all users

- _`UserReadOwnAbsences`
    - Permission to read my own absences

    - **Default assignment**: :guilabel:`Everybody`

- _`UserCreateSubstitute`
    - Permission to create substitutes of all users

- _`UserCreateOwnSubstitute`
    - Permission to create my own substitutes

    - **Default assignment**: :guilabel:`Everybody`

- _`UserReadSubstitutes`
    - Permission to read substitutes of all users


Portal Statistic Permissions
----------------------------

- _`StatisticAddDashboardChart`
    - Permission to add dashboard charts

    - **Default assignment**: :guilabel:`Everybody`

- _`StatisticAnalyzeTask`
    - Permission to analyze, filter tasks and export data to excel for advanced analysis


Portal Express Permissions
--------------------------

- _`ExpressCreateWorkflow`
    - Permission to create Express workflow

    - **Default assignment**: :guilabel:`Everybody`
