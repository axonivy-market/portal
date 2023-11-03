.. _list-permissions:

Permission List
================

Portal has a flexible security system which allows you to configure who can access applications, and what they can do/see within them.


Portal Task Permissions
-----------------------
These apply to Task as a whole

- _`TaskReadAll`
    - Permission to see all tasks in the application

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskReadOwnCaseTasks`
    - Permission to see related tasks

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskParkOwnWorkingTask`
    - Permission to reserve a task

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskResetOwnWorkingTask`
    - Permission to reset tasks

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskWriteName`
    - Permission to change task name

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskWriteDescription`
    - Permission to change task description

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskWriteOriginalPriority`
    - Permission to change task priority

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskWriteActivator`
    - Permission to delegate all tasks in a task list

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskWriteExpiryActivator`
    - Permission to change responsible after deadline

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskWriteDelayTimestamp`
    - Permission to change delay time

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskReset`
    - Permission to reset tasks

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskDestroy`
    - Permission to see the Destroy Task action

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskWriteExpiryTimestamp`
    - Control whether to change deadline

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskWriteActivatorOwnTasks`
    - Permission to delegate personal or group tasks

    - **Default assignment**: :guilabel:`Administrator`

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
    - Permission to see the Reserve action

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskDisplayWorkflowEventAction`
    - Permission to see the Workflow Event action

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskDisplayDestroyAction`
    - Permission to see the Destroy action

    - **Default assignment**: :guilabel:`Administrator`

- _`TaskResetReadyForJoin`
    - Permission to reset tasks

    - **Default assignment**: :guilabel:`Administrator`

- _`ShareTaskDetailsLink`
    - Permission to see the Share button in task details

    - **Default assignment**: :guilabel:`Everybody`

Portal Case Permissions
-----------------------
These apply to Case as a whole

- _`CaseReadAll`
    - Permission to see all cases in the application

    - **Default assignment**: :guilabel:`Administrator`

- _`CaseDestroy`
    Permission to delete case

    - **Default assignment**: :guilabel:`Administrator`

- _`CaseWriteName`
    - Permission to change case name

    - **Default assignment**: :guilabel:`Administrator`

- _`CaseWriteDescription`
    - Permission to change case description

    - **Default assignment**: :guilabel:`Administrator`

- _`ShowAllTasksOfCase`
    - Permission to see the related tasks action 

    - **Default assignment**: :guilabel:`Administrator`

- _`ShowCaseDetails`
    - Permission to see business detail action

    - **Default assignment**: :guilabel:`Administrator`

- _`ShareCaseDetailsLink`
    - Permission to see the Share button in case details

    - **Default assignment**: :guilabel:`Administrator`


Portal General Permissions
--------------------------
These apply as a whole

- _`RoleCreate`
    - Permission to create a new dynamic role 

    - **Default assignment**: :guilabel:`Administrator`

- _`RoleDelete`
    - Permission to delete a dynamic role

    - **Default assignment**: :guilabel:`Administrator`

- _`RoleMove`
    - Permission to ....

    - **Default assignment**: :guilabel:`Administrator`

- _`RoleReadAll`
    - Permission to 

    - **Default assignment**: :guilabel:`Administrator`

- _`DocumentRead`
    - Permission to 

    - **Default assignment**: :guilabel:`Administrator`

- _`DocumentWrite`
    - Permission to update, delete document

    - **Default assignment**: :guilabel:`Administrator`

- _`DocumentOfInvolvedCaseWrite`
    - Permission to update, delete document

    - **Default assignment**: :guilabel:`Administrator`

- _`DashboardWriteOwn`
    - Permission to alter private dashboard 

    - **Default assignment**: :guilabel:`Everybody`

- _`DashboardWritePublic`
    - Permission to alter public dashboard

    - **Default assignment**: :guilabel:`Administrator`

- _`AccessFullProcessList`
    - Permission to access full process list. See :ref:`full-process-list`.

    - **Default assignment**: :guilabel:`Everybody`

- _`AccessFullTaskList`
    - Permission to access full task list. See :ref:`full-task-list`.

    - **Default assignment**: :guilabel:`Everybody`

- _`AccessFullCaseList`
    - Permission to access full case list. See :ref:`full-case-list`.

    - **Default assignment**: :guilabel:`Everybody`

- _`AccessFullStatisticsList`
    - Permission to statistics section. See :ref:`full-statistic-list`.

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskCaseAddNote`
    - Permission to add note to task/case

    - **Default assignment**: :guilabel:`Everybody`

- _`TaskCaseShowMoreNote`
    - Permission to see show more note

    - **Default assignment**: :guilabel:`Everybody`

- _`CreatePublicExternalLink`
    - Permission to create public external link, all other users can see that link in full process list.

    - **Default assignment**: :guilabel:`Administrator`

- _`RoleManagement`
    - Permission to access :guilabel:`Role Management` tab

    - **Default assignment**: :guilabel:`Administrator`

- _`NewsManagement`
    - Permission to manage the content of the news feed

    - **Default assignment**: :guilabel:`Administrator`

- _`PasswordValidation`
    - Permission to access the :guilabel:`Password Validation` tab

    - **Default assignment**: :guilabel:`Administrator`

- _`DashboardExportOwn`
    - Permission to export private dashboard

    - **Default assignment**: :guilabel:`Administrator`

- _`DashboardExportPublic`
    - Permission to export public dashboard

    - **Default assignment**: :guilabel:`Administrator`

- _`DashboardImportOwn`
    - Permission to import private dashboard

    - **Default assignment**: :guilabel:`Administrator`

- _`DashboardImportPublic`
    - Permission to import public dashboard

    - **Default assignment**: :guilabel:`Administrator`

- _`ShareDashboardLink`
    - Permission to share dashboard

    - **Default assignment**: :guilabel:`Everybody`


Portal Absence And Substitute Permissions
-----------------------
These apply to Absence and Substitute

- _`UserCreateAbsence`
    - Permission to craete, edit absence of all users

    - **Default assignment**: :guilabel:`Administrator`

- _`UserCreateOwnAbsence`
    - Permission to craete, edit personal absence

    - **Default assignment**: :guilabel:`Everybody`

- _`UserDeleteAbsence`
    - Permission to remove the absence entries for all users

    - **Default assignment**: :guilabel:`Administrator`

- _`UserDeleteOwnAbsence`
    - Permission to remove the personal absence entries

    - **Default assignment**: :guilabel:`Everybody`

- _`UserReadAbsences`
    - Permission to read absences of all users

    - **Default assignment**: :guilabel:`Administrator`

- _`UserReadOwnAbsences`
    - Permission to read absences

    - **Default assignment**: :guilabel:`Everybody`

- _`UserCreateSubstitute`
    - Permission to 

    - **Default assignment**: :guilabel:`Administrator`

- _`UserCreateOwnSubstitute`
    - Permission to 

    - **Default assignment**: :guilabel:`Everybody`

- _`UserReadSubstitutes`
    - Permission to 

    - **Default assignment**: :guilabel:`Administrator`


Portal Statistic Permissions
-----------------------
These apply to Absence and Substitute

- _`StatisticAddDashboardChart`
    - Permission to 

    - **Default assignment**: :guilabel:`Administrator`

- _`StatisticAnalyzeTask`
    - Permission to 

    - **Default assignment**: :guilabel:`Administrator`


Portal Express Permissions
-----------------------
These apply to Absence and Substitute

- _`ExpressCreateWorkflow`
    - Permission to 

    - **Default assignment**: :guilabel:`Administrator`
