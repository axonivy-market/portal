.. _full-task-list:

Full Task List
**************

The **Full Task List** page shows you the tasks, as well as, advanced search and filter
capabilities. You can reach the page by using the |task-list-icon| :guilabel:`Tasks` 
link in the |ivy| Portal menu.

.. note::

   Grant permission :bdg-ref-warning:`🔑AccessFullTaskList <AccessFullTaskList>` to allow a user to access the full task list ( |task-list-icon| :guilabel:`Tasks`) in the |ivy| Portal menu.


.. hint:: 
   Depending on the roles you hold
   in the application and the     
   configuration of the |ivy|  
   Portal, you might see not only 
   the tasks belonging to you but 
   all open tasks.     

   Visibility tasks assigned to roles depending on state:

   - Open: Visible for all members

   - In Progress: Visible for all members, but "parked" for working user (no actions possible for other role members)

   - Done: Visible for all members

|navigate-to-full-task-list-page|

On the top of the **Full Task List** page you see next to the heading
:guilabel:`Tasks` in brackets the overall number of tasks shown to you. Below you find the filter feature.
The usage will be explained in the HowTo’s further down this chapter.
Finally, you see the list of tasks.

For each task the following key information is shown in the list:

#. Priority

#. Name and Description

#. Responsible user or role

#. Task ID

#. Creation Date

#. Expiry Date

#. Status

#. Category

#. Application (This column is not displayed by default)

|task-key-information|

Furthermore, at the end of each row you find a side step menu of key features in
handling tasks:

#. Reset Task

#. Delegate Task

#. Reserve

#. Destroy Task

#. Trigger Escalation Task

#. Add Ad-hoc Task

.. note::

   Grant permissions :bdg-ref-warning:`🔑TaskDisplayResetAction <TaskDisplayResetAction>`, 
   :bdg-ref-warning:`🔑TaskDisplayReserveAction <TaskDisplayReserveAction>`, :bdg-ref-warning:`🔑TaskDisplayDelegateAction <TaskDisplayDelegateAction>`, 
   :bdg-ref-warning:`🔑TaskDisplayDestroyAction <TaskDisplayDestroyAction>`, :bdg-ref-warning:`🔑TaskDisplayAdditionalOptions <TaskDisplayAdditionalOptions>`,
   :bdg-ref-warning:`🔑TaskDisplayWorkflowEventAction <TaskDisplayWorkflowEventAction>` to allow a user to see the corresponding buttons and menu items.

Finally, you have the possibility to access the full set of the task
data by clicking on the row containing the task Name and Description.

The task details are separated into 3 different sections:

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


HowTo: Use existing filter
--------------------------

.. hint:: 
   If there are no public filters and you didn’t create any filters so far,
   there might be no filters available. In this case, you must create a filter
   first. This is described in detail in HowTo: Create new filter.   

#. Click on the :guilabel:`Save Filter` dropdown menu. 

#. Select the filter you want to use.

HowTo: Create new filter
------------------------

#. Click on the button :guilabel:`More` to select one of the available filters.
   For a list of available filters, see  `Task filter`_.

#. Click on the newly added filter and configure it.

#. Add more filters by clicking on the button :guilabel:`Filter` again.

#. Drop filters by clicking on the |delete-circle-icon| icon next to
   the filter.

#. When finished defining your filter, click on the button **Save
   filter**.

#. The :guilabel:`Save filter` dialog is opened.

#. Under :guilabel:`Filter name`, provide a recognizable name for the filter.

#. Under :guilabel:`Filter visibility`, you can select if the filter is only
   visible for you or shall be available to all users.

#. Save the filter by clicking the button :guilabel:`Ok`.

|how-to-create-task-filter|


.. centered:: _`Task filter`

+-----------------------------------+-----------------------------------+
| Criteria                          | Description                       |
+===================================+===================================+
| ID                                | The filter lets you specify the   |
|                                   | task identify number              |
+-----------------------------------+-----------------------------------+
| Name                              | The filter lets you specify the   |
|                                   | task name                         |
+-----------------------------------+-----------------------------------+
| Completed on (from / to)          | This filter lets you specify task |
|                                   | complete period.                  |
+-----------------------------------+-----------------------------------+
| Created (from / to)               | This filter lets you specify in   |
|                                   | which time period the task was    |
|                                   | created.                          |
+-----------------------------------+-----------------------------------+
| Description                       | This filter lets you search for   |
|                                   | keywords within the task          |
|                                   | description.                      |
+-----------------------------------+-----------------------------------+
| Expiry (from / to)                | This filter lets you specify in   |
|                                   | which time period the task will   |
|                                   | expire.                           |
+-----------------------------------+-----------------------------------+
| Priority                          | This filter lets you specify the  |
|                                   | priority of the tasks to be       |
|                                   | shown.                            |
+-----------------------------------+-----------------------------------+
| Responsible                       | This filter lets you specify a    |
|                                   | user or role which is responsible |
|                                   | for the task.                     |
+-----------------------------------+-----------------------------------+
| State                             | This filter lets you specify the  |
|                                   | task state.                       |
+-----------------------------------+-----------------------------------+
| Category                          | This filter lets you specify      |
|                                   | the task category.                |
+-----------------------------------+-----------------------------------+
| Missing Activator                 | This filter is only for Admin and |
|                                   | lets you find tasks which have no |
|                                   | activator, activator is not found |
|                                   | or is disabled.                   |
+-----------------------------------+-----------------------------------+
| Application                       | This filter lets you select the   |
|                                   | application                       |
+-----------------------------------+-----------------------------------+



.. hint::
   #. You can create a new filter by selecting an existing filter, reconfiguring it, and saving it.

   #. Only users with the admin role ``AXONIVY_PORTAL_ADMIN`` can save the filter for all users.

HowTo: Configure displayed tasks in the Full Task List
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. To choose columns to display, click on :guilabel:`Manage columns`. Then you could choose displayed columns by checking the related checkboxes.

#. To sort data, click on the column header. To change sort direction, click on that column header one more time.
   Note that some columns do not support sorting.

HowTo: Move and Resize Widgets in Task Details
----------------------------------------------
#. Open the task details of a task

#. Click on the :guilabel:`Edit` button to drag, drop and resize widgets.

   |how-to-switch-to-edit-mode|


#. Click on the :guilabel:`Save` button to save current state of page and switch to readonly mode.

   |how-to-switch-to-view-mode|


#. Click :guilabel:`Reset` button to reset to default configuration.

   |how-to-reset-to-default|



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

HowTo: Attach a document to the case
------------------------------------

#. Open the task details of a task
#. Click the link |add-icon| ``Add document`` 

#. The :guilabel:`Add document` dialog is opened

#. Upload a file by using the :guilabel:`Select` button or by simply dragging
   the file into the dialog

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

HowTo: Export Task List
-----------------------

You may export the Task List by clicking on the button **Export to Excel**.

|export-to-excel-button|


HowTo: Share Task Details
-------------------------

#. Open the task details

#. Click on the share button |share-icon|

|how-to-share-task-details|


.. note::

   Grant permission :bdg-ref-warning:`🔑ShareTaskDetailsLink <ShareTaskDetailsLink>` to allow a user to share task details.

.. include:: ../includes/_common-icon.rst


.. |navigate-to-full-task-list-page| image:: ../../screenshots/dashboard/expanded-left-menu.png
.. |task-key-information| image:: ../../screenshots/task/task-key-information.png
.. |detailed-task-information-data-description| image:: ../../screenshots/task-detail/detailed-task-information-data-description.png
.. |detailed-task-information-note| image:: ../../screenshots/task-detail/detailed-task-information-note.png
.. |detailed-task-information-documents| image:: ../../screenshots/task-detail/detailed-task-information-documents.png
.. |detailed-task-information| image:: ../../screenshots/task-detail/detailed-task-information.png
.. |how-to-create-task-filter| image:: ../../screenshots/task/how-to-create-task-filter.png
.. |how-to-upload-document| image:: ../../screenshots/task-detail/how-to-upload-document.png
.. |how-to-delete-document| image:: ../../screenshots/task-detail/how-to-delete-document.png
.. |how-to-add-note| image:: ../../screenshots/task-detail/how-to-add-note.png
.. |how-to-show-note-details| image:: ../../screenshots/task-detail/how-to-show-note-details.png
.. |how-to-export-note-details| image:: ../../screenshots/task-detail/how-to-export-note-details.png
.. |how-to-show-workflow-event| image:: ../../screenshots/task-detail/how-to-show-workflow-event.png
.. |workflow-events-table| image:: ../../screenshots/task-detail/workflow-events-table.png
.. |export-to-excel-button| image:: ../../screenshots/task/export-to-excel-button.png
.. |how-to-switch-to-view-mode| image:: ../../screenshots/task-detail/how-to-switch-to-view-mode.png
.. |how-to-switch-to-edit-mode| image:: ../../screenshots/task-detail/how-to-switch-to-edit-mode.png
.. |how-to-reset-to-default| image:: ../../screenshots/task-detail/how-to-reset-to-default.png
.. |how-to-share-task-details| image:: ../../screenshots/task-detail/share-page-button.png
.. |how-to-preview-document| image:: ../../screenshots/case-detail/how-to-preview-document.png
