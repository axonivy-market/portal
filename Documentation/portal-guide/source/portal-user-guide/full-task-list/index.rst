.. _full-task-list:

Full Task List
**************

The :guilabel:`Full Task List` page shows you the same tasks you see on your
personal dashboard. However, this page provides you with extended
information on the tasks, as well as, advanced search and filter
capabilities. You can reach the page by using the |task-list-icon| **Tasks**
link in the Axon.ivy Portal menu.

.. hint:: 
   Depending on the roles you hold
   in the application and the     
   configuration of the Axon.ivy  
   Portal, you might see not only 
   the tasks belonging to you but 
   all open tasks.                


|navigate-to-full-task-list-page|

On the top of the **Full Task List** page you see next to the heading
**Tasks** in brackets the overall number of tasks shown to you. Below you find the filter feature.
The usage will be explained in the HowTo’s further down this chapter.
Finally, you see the list of tasks.

For each task the following key information is shown in the list:

1. Priority

2. Name and Description

3. Responsible user or role

4. Task ID

5. Creation Date

6. Expiry Date

7. Status

|task-key-information|

Furthermore, at the end of each row you find a side step menu of key features in
handling tasks:

1. Reset Task

2. Delegate Task

3. Reverse

4. Destroy Task

5. Add Ad-hoc Task

Finally, you have the possibility to access the full set of the task
data by clicking on the row containing the task Name and Description.

The task details are separated in 3 different sections:

1. Data and description, you find various metadata concerning the task and
   the respective case it is related to.

|detailed-task-information-data-description|

2. Note, you may add additional notes which is described below

|detailed-task-information-note|

3. Document, these documents once attached to the case are
   available on all tasks. You may add or delete documents which his
   described in the HowTo’s below.

|detailed-task-information-documents|

|detailed-task-information|

HowTo: Use existing filter
--------------------------

.. hint:: 
   If there are no public filters and you didn’t create any filters so far,
   there might be no filters available. In this case, you must create a filter
   first. This is described in detail in HowTo: Create new filter.   

1. Click on the **Save Filter** dropdown menu. 

2. Select the filter you want to use.

HowTo: Create new filter
------------------------

1. Click on the button **More** to select one of the available filters.
   For a list of available filters, see  `Task filter criterias`_.

2. Click on the newly added filter and configure it.

3. Add more filters by clicking on the button **Filter** again.

4. Drop filters by clicking on the |delete-circle-icon| **Delete** button next to
   the filter.

5. When finished defining your filter, click on the button **Save
   filter**.

6. The :guilabel:`Save filter` dialog is opened.

7. Under **Filter name**, provide a recognizable name for the filter.

8. Under **Filter visibility**, you can select if the filter is only
   visible for you or shall be available to all users.

9. Save the filter by clicking the button **Ok**.

|how-to-create-task-filter|


.. centered:: _`Task filter criterias`

+-----------------------------------+-----------------------------------+
| Criteria                          | Description                       |
+===================================+===================================+
| Created (from / to)               | The filter lets you specify in    |
|                                   | which time period the task was    |
|                                   | created.                          |
+-----------------------------------+-----------------------------------+
| Description                       | The filter lets you search for    |
|                                   | keywords within the task          |
|                                   | description.                      |
+-----------------------------------+-----------------------------------+
| Expiry (from / to)                | The filter lets you specify in    |
|                                   | which time period the task will   |
|                                   | expire.                           |
+-----------------------------------+-----------------------------------+
| Priority                          | The filter lets you specify the   |
|                                   | priority of the tasks to be       |
|                                   | shown.                            |
+-----------------------------------+-----------------------------------+
| Responsible                       | The filter lets you specify a     |
|                                   | user or role which is responsible |
|                                   | for the task.                     |
+-----------------------------------+-----------------------------------+
| State                             | The filter lets you specify the   |
|                                   | task state.                       |
+-----------------------------------+-----------------------------------+


.. hint:: 
   You may also start the creation
   of a new filter by selecting an
   existing filter first,         
   reconfiguring it and saving it.

.. hint:: 
   Depending on your permissions you
   might only be allowed to save    
   filters for yourself.            

HowTo: Attach a document to the case
------------------------------------

1. Open the task details of a task

2. Click the link |add-icon| **Add document**

3. The :guilabel:`Add document` dialog is opened

4. Upload a file by using the **Select** button or by simply dragging
   the file into the dialog

5. Click the button **Close** to add the attachment

|how-to-upload-document|

HowTo: Remove an attachment from the case
-----------------------------------------

1. Open the task details of a task

2. Click on the |delete-icon| **Delete attachment** link next to the
   attachment you want to remove

3. A :guilabel:`Confirmation` dialog is opened

4. Confirm the deletion with the button **Yes**

|how-to-delete-document|

HowTo: Add a note to a task
---------------------------

1. Open the task details of a task

2. Click on the |add-icon| **Add note** link below the notes section

3. The :guilabel:`Add note` dialog is opened

4. Enter your note

5. Confirm your note by clicking the button :guilabel:`Save`

|how-to-add-note|

HowTo: Export a task history
----------------------------

1. Open the task details of a task

2. Click on the |show-more-icon| **Show more** link below the notes section

|how-to-show-note-details|

3. A new page with the task history is opened

4. You may export the history by clicking on the button **Export to
   Excel**

|how-to-export-note-details|


.. _how-to-show-workflow-events:

HowTo: Show Workflow Events
---------------------------

.. note:: 
   User must to has permission ``WORKFLOW_EVENT_READ_ALL`` to see all workflow events of a task

1. Open the task details of a task

2. Click on the |show-more-icon| **Show more** link below the notes section

|how-to-show-note-details|

3. A new page with the task history is opened

4. Click **Workflow Events** link on the right side of page to see Workflow Events dialog

|how-to-show-workflow-event|

**Workflow Events dialog**

|workflow-events-table|

HowTo: Export Task List
-----------------------

You may export the Task List by clicking on the button **Export to Excel**.

|export-to-excel-button|

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
