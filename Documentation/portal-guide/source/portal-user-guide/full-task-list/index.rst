.. _full-task-list:

Full Task List
**************

The **Full Task List** page is a dashboard featuring a single, full-width task widget. 
You can reach the page by using the |task-list-icon| :guilabel:`Tasks` link in the Axon Ivy Portal menu.

.. important::
   - Full Task list dashboard now works normally like other dashboards. You can manage it in the :bdg-ref-warning:`Dashboard Configuration <dashboard-configuration>` page,
     including removing it from your dashboard list and left menu.
   
   - A new template :guilabel:`Full Task list dashboard` will support you to create a new dashboard having a similar layout, see :ref:`public-available-dashboard-template`.

   - **Access Requirement**: Only users with the :bdg-ref-warning:`🔑AccessFullTaskList <AccessFullTaskList>` 
     permission can see the |task-list-icon| :guilabel:`Tasks` menu item.

.. tip:: 
   Depending on your application roles and Portal configuration, you might see not only your tasks but all open tasks.

|navigate-to-full-task-list-page|

Overview
========

On the top of the **Full Task List** page you see next to the heading
:guilabel:`Tasks` in brackets the overall number of tasks shown to you. Below you find the filter feature.
The usage will be explained in the HowTo's further down this chapter.
Finally, you see the list of tasks.

For each task the following key information is shown in the list:

#. Priority

#. Pin

#. Task ID

#. Name

#. Description

#. Responsible user or role

#. State

#. Creation Date

#. Expiry Date

#. Category

#. Application (This column is not displayed by default)

|task-key-information|

Available Actions
-----------------

At the end of each row you find the `Actions` menu with the following options:

.. table::
   :widths: 30 70

   +---------------------------+---------------------------------------------------------------+
   | Action                    | Description                                                   |
   +===========================+===============================================================+
   | **Details**               | View full task information                                    |
   +---------------------------+---------------------------------------------------------------+
   | **Reset Task**            | Revert the task to its initial state                          |
   +---------------------------+---------------------------------------------------------------+
   | **Delegate Task**         | Assign the task to another user or role                       |
   +---------------------------+---------------------------------------------------------------+
   | **Reserve**               | Reserve the task for yourself                                 |
   +---------------------------+---------------------------------------------------------------+
   | **Destroy**               | Destroy the task                                              |
   +---------------------------+---------------------------------------------------------------+
   | **Trigger Escalation**    | Manually trigger task escalation                              |
   +---------------------------+---------------------------------------------------------------+
   | **Custom Fields**         | View task custom fields                                       |
   +---------------------------+---------------------------------------------------------------+
   | **Workflow Events**       | View workflow events                                          |
   +---------------------------+---------------------------------------------------------------+
   | **Process Viewer**        | Visual representation of the process flow                     |
   +---------------------------+---------------------------------------------------------------+

Task Details
============

Access the full set of task data by clicking on `Actions` then `Details`.

The task details page is organized into four main sections:

.. table::
   :widths: 30 70

   +---------------------------+---------------------------------------------------------------+
   | Section                   | Content                                                       |
   +===========================+===============================================================+
   | **Status Banner**         | Information that needs attention or action                    |
   +---------------------------+---------------------------------------------------------------+
   | **Data and Description**  | Task metadata and related case information                    |
   +---------------------------+---------------------------------------------------------------+
   | **Histories**             | Notes and task history entries                                |
   +---------------------------+---------------------------------------------------------------+
   | **Documents**             | Attached files with add, delete, rename, preview options      |
   +---------------------------+---------------------------------------------------------------+

#. Task status banner to display information that needs attention or that you should take action on.

   |task-status-banner|

#. Data and description, you find various metadata concerning the task and
   the respective case it is related to.

   |detailed-task-information-data-description|

#. Histories, you may add additional notes which are described below

   |detailed-task-information-note|

#. Documents, these documents once attached to the case are
   available on all tasks. You may add, delete, rename or preview documents
   described in the HowTo’s below.

   |detailed-task-information-documents|

|detailed-task-information|

How-to
======

HowTo: Configure the Full Task List
-----------------------------------

The full task list page functions as a dashboard accessible from the top-level menu. 
To customize the task list, navigate to :ref:`Dashboard configuration <dashboard-configuration>`.

To restore the dashboard to its default settings, including the menu icon and task list layout, delete the dashboard. Portal will automatically 
recreate the dashboard with the default settings.

HowTo: Move and Resize Widgets in Task Details
----------------------------------------------

#. Open the task details of a task

#. Click on the :guilabel:`Edit` button to drag, drop and resize widgets.

   |how-to-switch-to-edit-mode|

#. Click on the :guilabel:`Save` button to save current state of page and switch to readonly mode.

   |how-to-switch-to-view-mode|

#. Click :guilabel:`Reset` button to reset to default configuration.

   |how-to-reset-to-default|


HowTo: Attach a Document to a Case
-----------------------------------

#. Open the task details of a task

#. Click the link |add-icon| ``Add document`` 

#. The :guilabel:`Add document` dialog is opened

#. Upload a file by clicking :guilabel:`Upload here` or by dragging the
   file into the dialog

#. Click the button :guilabel:`Close` to close the dialog

|how-to-upload-document|

HowTo: Rename a Document of a Case
----------------------------------

#. Open the case details

#. Click on the |edit-icon| button next to the
   attachment you want to rename

#. A dialog is opened to rename the selected document

#. Update filename as you expect in the input field

#. Click the button :guilabel:`Save` to save your change

#. Filename will be updated and a note about the renaming will be added

|how-to-rename-document|


HowTo: Remove an Attachment from the Case
------------------------------------------

#. Open the task details of a task

#. Click on the |delete-icon| button next to the
   attachment you want to remove

#. A confirmation dialog is opened

#. Confirm the deletion with the button :guilabel:`Delete` 

|how-to-delete-document|


HowTo: Preview a Document of a Task
------------------------------------

#. Open the task details of a task

#. Click on the |preview-icon| button next to the
   attachment you want to preview

#. A preview dialog is opened

#. Click the button :guilabel:`Close` to close the dialog

|how-to-preview-document|

.. note::
   **Supported Document Types:**
   
   - **Default**: Images (jpg, jpeg, bmp, png), plain text (txt, log), and PDF documents
   - **With DocFactory**: Word (doc, docx), Excel (xls, xlsx), and email (eml) files
   
   To enable this feature, set :ref:`Portal settings <update-portal-settings>` :guilabel:`Portal.Document.EnablePreview` to true. 
   For Office documents, deploy `DocFactory <https://market.axonivy.com/doc-factory#tab-description>`_ in the same security context.


HowTo: Add a Note to a Task
---------------------------

#. Open the task details of a task

#. Click on the link |add-icon| ``Add note`` below the notes section

#. The :guilabel:`Add note` dialog is opened

#. Enter your note

#. Confirm your note by clicking the button :guilabel:`Save`

|how-to-add-note|

.. note::
   **Note Visibility:**
   
   - Task notes are displayed in the ``Notes`` table of the task details and in the ``History`` table of its business case
   - If the task belongs to a technical case, notes are also displayed in the ``History`` table of that technical case

HowTo: Export a Task History
----------------------------

#. Open the task details of a task

#. Click on the link |show-more-icon| ``Show more`` below the notes section

   |how-to-show-note-details|

#. A new page with the task history is opened

#. You may export the history by clicking on the button :guilabel:`Export to Excel` 

   |how-to-export-note-details|


.. _how-to-show-workflow-events:

HowTo: Show Workflow Events
---------------------------

#. Open the task details of a task

#. Click on the link ``Actions`` on the right top corner of the **Data and Description** section

#. Click the link ``Workflow Events`` on the overlay panel to see Workflow Events dialog

**Workflow Events dialog**

|workflow-events-table|

HowTo: Show Custom Fields
---------------------------

#. Open the task details of a task

#. Click on the link ``Actions`` on the right top corner of the **Data and Description** section

#. Click the link ``Custom Fields`` on the overlay panel to see Custom Fields dialog

**Custom Fields dialog**

|custom-fields-dialog|

HowTo: Share Task Details
-------------------------

#. Open the task details

#. Click on the share button |share-icon|

|how-to-share-task-details|

Permission Reference
====================

Different task operations require specific permissions:

.. table::
   :widths: 40 60

   +--------------------------------------+--------------------------------------------------------+
   | Action                               | Required Permission                                    |
   +======================================+========================================================+
   | **Reset task**                       | :bdg-ref-warning:`🔑TaskDisplayResetAction             |
   |                                      | <TaskDisplayResetAction>`                              |
   +--------------------------------------+--------------------------------------------------------+
   | **Reserve task**                     | :bdg-ref-warning:`🔑TaskDisplayReserveAction           |
   |                                      | <TaskDisplayReserveAction>`                            |
   +--------------------------------------+--------------------------------------------------------+
   | **Delegate task**                    | :bdg-ref-warning:`🔑TaskDisplayDelegateAction          |
   |                                      | <TaskDisplayDelegateAction>`                           |
   +--------------------------------------+--------------------------------------------------------+
   | **Destroy task**                     | :bdg-ref-warning:`🔑TaskDisplayDestroyAction           |
   |                                      | <TaskDisplayDestroyAction>`                            |
   +--------------------------------------+--------------------------------------------------------+
   | **View additional options**          | :bdg-ref-warning:`🔑TaskDisplayAdditionalOptions       |
   |                                      | <TaskDisplayAdditionalOptions>`                        |
   +--------------------------------------+--------------------------------------------------------+
   | **View workflow events**             | :bdg-ref-warning:`🔑TaskDisplayWorkflowEventAction     |
   |                                      | <TaskDisplayWorkflowEventAction>` and                  |
   |                                      | :bdg-warning:`🔑WorkflowEventReadAll`                  |
   +--------------------------------------+--------------------------------------------------------+
   | **View custom fields**               | :bdg-ref-warning:`🔑TaskDisplayCustomFieldsAction      |
   |                                      | <TaskDisplayCustomFieldsAction>`                       |
   +--------------------------------------+--------------------------------------------------------+
   | **Add/delete task documents**        | :bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite        |
   |                                      | <DocumentOfInvolvedCaseWrite>` or                      |
   |                                      | :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>`     |
   +--------------------------------------+--------------------------------------------------------+
   | **Add note to task**                 | :bdg-ref-warning:`🔑TaskCaseAddNote                    |
   |                                      | <TaskCaseAddNote>`                                     |
   +--------------------------------------+--------------------------------------------------------+
   | **Share task details link**          | :bdg-ref-warning:`🔑ShareTaskDetailsLink               |
   |                                      | <ShareTaskDetailsLink>`                                |
   +--------------------------------------+--------------------------------------------------------+

.. include:: ../includes/_common-icon.rst


.. |navigate-to-full-task-list-page| image:: ../../screenshots/dashboard/expanded-left-menu.png
   :alt: Menu to navigate to the Full task list
.. |task-key-information| image:: ../../screenshots/task/task-key-information.png
   :alt: Full task list: key informations
.. |detailed-task-information-data-description| image:: ../../screenshots/task-detail/detailed-task-information-data-description.png
   :alt: Task Details: General section
.. |detailed-task-information-note| image:: ../../screenshots/task-detail/detailed-task-information-note.png
   :alt: Task Details: Notes section
.. |detailed-task-information-documents| image:: ../../screenshots/task-detail/detailed-task-information-documents.png
   :alt: Task Details: Documents section
.. |detailed-task-information| image:: ../../screenshots/task-detail/detailed-task-information.png
   :alt: The Task Details page
.. |how-to-upload-document| image:: ../../screenshots/task-detail/how-to-upload-document.png
   :alt: Task Details:Add document dialog
.. |how-to-delete-document| image:: ../../screenshots/task-detail/how-to-delete-document.png
   :alt: Task Details: Delete document confirm dialog
.. |how-to-add-note| image:: ../../screenshots/task-detail/how-to-add-note.png
   :alt: Task Details: Add note dialog
.. |how-to-show-note-details| image:: ../../screenshots/task-detail/how-to-show-note-details.png
   :alt: Task Details: Link to show more notes details
.. |how-to-export-note-details| image:: ../../screenshots/task-detail/how-to-export-note-details.png
   :alt: Link to export task notes
.. |workflow-events-table| image:: ../../screenshots/task-detail/workflow-events-table.png
   :alt: Workflow events of task dialog
.. |custom-fields-dialog| image:: ../../screenshots/task-detail/custom-fields-dialog.png
   :alt: Custom fields of task dialog
.. |how-to-switch-to-view-mode| image:: ../../screenshots/task-detail/how-to-switch-to-view-mode.png
   :alt: Task Details: save button
.. |how-to-switch-to-edit-mode| image:: ../../screenshots/task-detail/how-to-switch-to-edit-mode.png
   :alt: Task Details: button to switch to the edit mode
.. |how-to-reset-to-default| image:: ../../screenshots/task-detail/how-to-reset-to-default.png
   :alt: Task Details: button to reset to the default configurations
.. |how-to-share-task-details| image:: ../../screenshots/task-detail/share-page-button.png
   :alt: Task Details: button to get the shareable link of the task details
.. |task-status-banner| image:: ../../screenshots/task-detail/task-status-banner.png
   :alt: Task Details: the Status banner
.. |how-to-preview-document| image:: ../../screenshots/case-detail/how-to-preview-document.png
   :alt: Task Details: preview document dialog
.. |how-to-rename-document| image:: ../../screenshots/case-detail/how-to-rename-document.png
   :alt: Task Details: rename document dialog
