.. _full-task-list:

Full Task List
**************

The **Full Task List** page is a dashboard featuring a single, full-width task widget. 
You can reach the page by using the |task-list-icon| :guilabel:`Tasks` link in the |ivy| Portal menu.

.. note::

   Grant permission :bdg-ref-warning:`🔑AccessFullTaskList <AccessFullTaskList>` to allow a user to access the full task list ( |task-list-icon| :guilabel:`Tasks`) in the |ivy| Portal menu.


.. hint:: 
   Depending on the roles you hold
   in the application and the     
   configuration of the |ivy|  
   Portal, you might see not only 
   the tasks belonging to you but 
   all open tasks.     

|navigate-to-full-task-list-page|

On the top of the **Full Task List** page you see next to the heading
:guilabel:`Tasks` in brackets the overall number of tasks shown to you. Below you find the filter feature.
The usage will be explained in the HowTo’s further down this chapter.
Finally, you see the list of tasks.

For each task the following key information is shown in the list:

#. Priority

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

Furthermore, at the end of each row you find the `Actions` menu of key features in
handling tasks:

#. Details

#. Reset Task

#. Delegate Task

#. Reserve

#. Destroy

#. Trigger Escalation

#. Workflow Events

#. Process Viewer

.. note::

   Grant permissions :bdg-ref-warning:`🔑TaskDisplayResetAction <TaskDisplayResetAction>`, 
   :bdg-ref-warning:`🔑TaskDisplayReserveAction <TaskDisplayReserveAction>`, :bdg-ref-warning:`🔑TaskDisplayDelegateAction <TaskDisplayDelegateAction>`, 
   :bdg-ref-warning:`🔑TaskDisplayDestroyAction <TaskDisplayDestroyAction>`, :bdg-ref-warning:`🔑TaskDisplayAdditionalOptions <TaskDisplayAdditionalOptions>`,
   :bdg-ref-warning:`🔑TaskDisplayWorkflowEventAction <TaskDisplayWorkflowEventAction>` to allow a user to see the corresponding buttons and menu items.

Finally, you have the possibility to access the full set of the task
data by clicking on `Actions` then clicking on the `Details` menu item.

The task details are separated into 4 different sections:

#. Task status banner to display information that needs attention or that you should take action on.

   |task-status-banner|

#. Data and description, you find various metadata concerning the task and
   the respective case it is related to.

   |detailed-task-information-data-description|

#. Histories, you may add additional notes which are described below

   |detailed-task-information-note|

#. Documents, these documents once attached to the case are
   available on all tasks. You may add, delete or preview documents 
   described in the HowTo’s below.

   |detailed-task-information-documents|

|detailed-task-information|

HowTo: Configure the full task list
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


HowTo: Attach a document to a case
------------------------------------

#. Open the task details of a task
#. Click the link |add-icon| ``Add document`` 

#. The :guilabel:`Add document` dialog is opened

#. Upload a file by clicking :guilabel:`Upload here` or by dragging the
   file into the dialog

#. Click the button :guilabel:`Close` to close the dialog

|how-to-upload-document|

.. note::

   Grant either permission :bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>` or :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>` to allow a user to upload documents.

HowTo: Remove an attachment from the case
-----------------------------------------

#. Open the task details of a task

#. Click on the |delete-icon| button next to the
   attachment you want to remove

#. A confirmation dialog is opened

#. Confirm the deletion with the button :guilabel:`Delete` 

|how-to-delete-document|

.. note::

   Grant either permission :bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>` or :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>` to allow a user to delete documents.


HowTo: Preview a document of a case
------------------------------------

#. Open the task details of a task

#. Click on the |preview-icon| button next to the
   attachment you want to preview

#. A preview dialog is opened

#. Click the button :guilabel:`Close` to close the dialog

|how-to-preview-document|

.. note::

   Portal can preview image, plain text(txt and log) and pdf document.

   Change :ref:`Portal settings <update-portal-settings>` :bdg-ref-warning:`Portal.Document.EnablePreview` to true to enable this feature.


HowTo: Add a note to a task
---------------------------

#. Open the task details of a task

#. Click on the link |add-icon| ``Add note`` below the notes section

#. The :guilabel:`Add note` dialog is opened

#. Enter your note

#. Confirm your note by clicking the button :guilabel:`Save`

|how-to-add-note|

.. note::
   Notes of a task are displayed in the ``Notes`` table of the task details, and in the ``History`` table of its business case.

   If the task belongs to a technical case, its notes are also displayed in the ``History`` table of this technical case.
   
   Grant permission :bdg-ref-warning:`🔑TaskCaseAddNote <TaskCaseAddNote>` to allow a user to add task and case notes.

HowTo: Export a task history
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

.. note:: 
   Grant permission :bdg-warning:`🔑WorkflowEventReadAll` to allow a user to see all workflow events of a task.

#. Open the task details of a task

#. Click on the link ``Actions`` on the right top corner of the **Data and Description** section

#. Click the link ``Workflow Events`` on the overlay panel to see Workflow Events dialog

**Workflow Events dialog**

|workflow-events-table|

HowTo: Share Task Details
-------------------------

#. Open the task details

#. Click on the share button |share-icon|

|how-to-share-task-details|

.. note::

   Grant permission :bdg-ref-warning:`🔑ShareTaskDetailsLink <ShareTaskDetailsLink>` to allow a user to share task details.

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