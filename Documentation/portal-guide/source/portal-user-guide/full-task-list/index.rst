.. _full-task-list:

Task Widget
***********

The **Task Widget** shows a list of tasks. Depending on your application roles and Portal
configuration, you might see not only your own tasks but all open tasks. This chapter describes
how to work with the widget and with the task details behind it.

To add a Task Widget to a dashboard and configure it, follow :ref:`Task list widget
<new-dashboard-task-list-widget>` in the Dashboard chapter. To configure it in the dashboard JSON
instead, see :ref:`Configure Task Widget <configure-new-dashboard-task-widget>`.

.. hint::
   Portal ships a default dashboard named :guilabel:`Tasks` that contains a single task widget.
   You can reach it with the |task-list-icon| :guilabel:`Tasks` link in the Axon Ivy Portal menu.

   |navigate-to-full-task-list-page|

   - The template :guilabel:`Full Task List dashboard` will support you in creating a new dashboard with a similar layout, see :ref:`public-available-dashboard-template`.

   - The dashboard is visible to all users by default. To restrict access to specific user roles, configure the dashboard permissions in the :ref:`Dashboard Configuration <dashboard-configuration>` page.

Overview
========

Below are the standard fields of a task. You can configure them in the column management dialog,
see :ref:`Column Management <task-widget-column-management>`.

#. Start

#. Pin

#. Priority

#. Task Id

#. Task name

#. Description

#. Responsible

#. State

#. Created

#. Completed

#. Expiry

#. Category

#. Application (This column is not added by default)

#. Working user (This column is not added by default)

#. Business Case ID (This column is not added by default)

#. Technical Case ID (This column is not added by default)

|task-key-information|

Widget Actions
--------------

On the right side of the widget header you find the operations that apply to the whole widget.
Click the actions menu button to open them.

|widget-actions-menu|

- **Quick search**: Search the tasks of the widget by keyword

- **Bulk selection**: Select several tasks and delegate them in one step

- **Show pinned tasks**: Show your pinned tasks only, :guilabel:`Show all tasks` switches back

- **Filters**: Open the complex filter panel of the widget, see :ref:`Complex Filter <complex-filter>`

- **Expand**: Show the widget in fullscreen, :guilabel:`Collapse` returns it to its normal size

- **Widget information**: Show additional widget details and statistics, and export the list to Excel

.. note::
   Which of these operations are available depends on the widget configuration, see
   :ref:`Configuration panel <task-list-widget-configuration-panel>` of the Task list widget.

**Quick search**

When quick search is enabled, click the :guilabel:`Quick search` button to reveal the search input
and start searching.

|task-quick-search-textbox|

  .. important::

    #. The scope of the quick search feature is defined in the configuration panel of the widget.
    #. The result of the quick search function may be affected if you apply a complex filter on the widget.

**Bulk selection**

:guilabel:`Bulk selection` lets you select multiple tasks and delegate them at once.

|bulk-delegate-task|

**Show pinned tasks**

:guilabel:`Show pinned tasks` filters the widget so it only displays tasks you have pinned; the
item then switches to :guilabel:`Show all tasks`, which returns you to the full task list.

**Filters**

:guilabel:`Filters` opens the widget's complex filter panel, where you can narrow down the
displayed tasks by criteria such as status, category, or date. See :ref:`complex-filter` for more
details.

**Expand**

:guilabel:`Expand` enlarges the widget to fill the entire screen for a better view of your data;
:guilabel:`Collapse` returns it to its normal size.

**Widget information**

:guilabel:`Widget information` opens the information panel, showing additional widget details and
statistics. From there, you can also export all data of the task widget using the
``Export to Excel`` link at the bottom left corner of the panel.

|widget-info|

If the number of exported tasks exceeds the maximum row number of the Excel file, Portal will
separate data into multiple Excel files and put them into a single zip file.

.. _task-row-actions:

Task Actions
------------

At the end of each row you find the |actions-menu-icon| :guilabel:`Actions` menu with the
following options:

.. table::
   :widths: 30 70

   +---------------------------+---------------------------------------------------------------+
   | Action                    | Description                                                   |
   +===========================+===============================================================+
   | **Details**               | View full task information                                    |
   +---------------------------+---------------------------------------------------------------+
   | **Pin**                   | Pin the task to find it quickly later, :guilabel:`Unpin`      |
   |                           | removes it again                                              |
   +---------------------------+---------------------------------------------------------------+
   | **Reset**                 | Revert the task to its initial state                          |
   +---------------------------+---------------------------------------------------------------+
   | **Delegate**              | Assign the task to another user or role                       |
   +---------------------------+---------------------------------------------------------------+
   | **Reserve**               | Reserve the task for yourself                                 |
   +---------------------------+---------------------------------------------------------------+
   | **Trigger Escalation**    | Manually trigger task escalation                              |
   +---------------------------+---------------------------------------------------------------+
   | **Workflow Events**       | View workflow events                                          |
   +---------------------------+---------------------------------------------------------------+
   | **Custom Fields**         | View task custom fields                                       |
   +---------------------------+---------------------------------------------------------------+
   | **Process Viewer**        | Visual representation of the process flow                     |
   +---------------------------+---------------------------------------------------------------+
   | **Destroy**               | Destroy the task                                              |
   +---------------------------+---------------------------------------------------------------+

At the end of the menu you find the side steps of the process.

Task Details
============

Clicking on a row opens the task details by default. If the :ref:`Portal setting
<update-portal-settings>` :guilabel:`Portal.Tasks.BehaviourWhenClickingOnLineInTaskList` is set
to start the task instead, use :guilabel:`Actions` then :guilabel:`Details`.

|detailed-task-information|

The task details page consists of four widgets:

#. **Summary**: The name of the task, its ID and creation date, the :guilabel:`Start` button and
   the |actions-menu-icon| :guilabel:`Actions` menu of the task. Below you find :guilabel:`Priority`,
   :guilabel:`State`, :guilabel:`Due` and :guilabel:`Workflow Events`.

#. **Task Details**: The description of the task, followed by
   :guilabel:`Role and User Information`, :guilabel:`Time Information` and
   :guilabel:`Task and Case Information`.

   |detailed-task-information-data-description|

#. **Documents**: The documents attached to the case. They are available on all tasks of the case.
   You may add, delete, rename or preview documents as described in the HowTo's below.

   |detailed-task-information-documents|

#. **Notes**: The notes of the task. Activate :guilabel:`System notes` to show the notes written
   by the system as well, and click :guilabel:`Show more` to open the full history.

   |detailed-task-information-note|

.. note::
   Whenever a task needs your attention, a status banner is shown above the widgets.

   |task-status-banner|

How-to
======

HowTo: Configure the Task Widget
--------------------------------

The Task Widget page functions as a dashboard accessible from the top-level menu. 
To customize the task list, navigate to :ref:`Dashboard configuration <dashboard-configuration>`.

To restore the dashboard to its default settings, including the menu icon and task list layout, delete the dashboard. Portal will automatically 
recreate the dashboard with the default settings.

HowTo: Move and Resize Widgets in Task Details
----------------------------------------------

#. Open the task details of a task

#. Click on the :guilabel:`Edit layout` button to drag, drop and resize widgets.

   |how-to-switch-to-edit-mode|

#. Click on the :guilabel:`Save` button to save current state of page and switch to readonly mode.

   |how-to-switch-to-view-mode|

#. Click :guilabel:`Reset` button to reset to default configuration.

   |how-to-reset-to-default|


HowTo: Attach a Document to a Case
-----------------------------------

#. Open the task details of a task

#. Click the |add-icon| button in the header of the **Documents** widget

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

#. Click the |add-icon| button in the header of the **Notes** widget

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

#. Click on |show-more-icon| :guilabel:`Show more` in the header of the **Notes** widget

   |how-to-show-note-details|

#. A new page with the task history is opened

#. You may export the history by clicking on the button :guilabel:`Export to Excel` 

   |how-to-export-note-details|


.. _how-to-show-workflow-events:

HowTo: Show Workflow Events
---------------------------

#. Open the task details of a task

#. Click on the |actions-menu-icon| :guilabel:`Actions` menu in the **Summary** widget

#. Click :guilabel:`Workflow Events` to open the Workflow Events dialog

**Workflow Events dialog**

|workflow-events-table|

HowTo: Show Custom Fields
---------------------------

#. Open the task details of a task

#. Click on the |actions-menu-icon| :guilabel:`Actions` menu in the **Summary** widget

#. Click :guilabel:`Custom Fields` to open the Custom Fields dialog

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
   | **Reset task**                       | :ref:`🔑TaskDisplayResetAction                         |
   |                                      | <TaskDisplayResetAction>`                              |
   +--------------------------------------+--------------------------------------------------------+
   | **Reserve task**                     | :ref:`🔑TaskDisplayReserveAction                       |
   |                                      | <TaskDisplayReserveAction>`                            |
   +--------------------------------------+--------------------------------------------------------+
   | **Delegate task**                    | :ref:`🔑TaskDisplayDelegateAction                      |
   |                                      | <TaskDisplayDelegateAction>`                           |
   +--------------------------------------+--------------------------------------------------------+
   | **Destroy task**                     | :ref:`🔑TaskDisplayDestroyAction                       |
   |                                      | <TaskDisplayDestroyAction>`                            |
   +--------------------------------------+--------------------------------------------------------+
   | **View additional options**          | :ref:`🔑TaskDisplayAdditionalOptions                   |
   |                                      | <TaskDisplayAdditionalOptions>`                        |
   +--------------------------------------+--------------------------------------------------------+
   | **View workflow events**             | :ref:`🔑TaskDisplayWorkflowEventAction                 |
   |                                      | <TaskDisplayWorkflowEventAction>` and                  |
   |                                      | 🔑WorkflowEventReadAll                                 |
   +--------------------------------------+--------------------------------------------------------+
   | **View custom fields**               | :ref:`🔑TaskDisplayCustomFieldsAction                  |
   |                                      | <TaskDisplayCustomFieldsAction>`                       |
   +--------------------------------------+--------------------------------------------------------+
   | **Add/delete task documents**        | :ref:`🔑DocumentOfInvolvedCaseWrite                    |
   |                                      | <DocumentOfInvolvedCaseWrite>` or                      |
   |                                      | :ref:`🔑DocumentWrite <DocumentWrite>`                 |
   +--------------------------------------+--------------------------------------------------------+
   | **Add note to task**                 | :ref:`🔑TaskCaseAddNote                                |
   |                                      | <TaskCaseAddNote>`                                     |
   +--------------------------------------+--------------------------------------------------------+
   | **Share task details link**          | :ref:`🔑ShareTaskDetailsLink                           |
   |                                      | <ShareTaskDetailsLink>`                                |
   +--------------------------------------+--------------------------------------------------------+

.. include:: ../includes/_common-icon.rst


.. |navigate-to-full-task-list-page| image:: ../../screenshots/dashboard/expanded-left-menu.png
   :alt: Menu to navigate to the Task Widget page
.. |task-key-information| image:: ../../screenshots/task/task-key-information.png
   :alt: Task Widget: key information
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
.. |task-quick-search-textbox| image:: ../../screenshots/new-dashboard/task-quick-search-textbox.png
   :alt: Dashboard task widget's quick search
.. |bulk-delegate-task| image:: ../../screenshots/new-dashboard/task-widget-bulk-delegate-selection.png
   :alt: Dashboard task widget bulk delegate feature
.. |widget-actions-menu| image:: ../../screenshots/new-dashboard/task-widget-actions-menu-panel.png
   :alt: Dashboard widget's actions menu panel
.. |widget-info| image:: ../../screenshots/new-dashboard/widget-info.png
   :alt: Dashboard widget's info panel
