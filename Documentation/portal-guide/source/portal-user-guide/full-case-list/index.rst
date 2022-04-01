.. _full-case-list:

Full Case List
**************

The **Full Case List** page shows you all cases you’re involved in.
This page provides you with extended information on the cases, as well
as advanced search and filter capabilities. You can reach the page by
using the |case-icon| :guilabel:`Cases` link in the Axon Ivy Portal menu.

.. hint:: 
   Depending on the roles you hold in the application and the configuration of
   the Axon Ivy Portal, you might see not only the cases you’re involved in but
   all cases.                      

|navigate-to-full-cases-list-page|

On the top of the **Full Case List** page you see next to the heading :guilabel:`Cases`
in brackets the overall number of cases shown to you. Below you find the filter feature. The usage will be explained in
the HowTo’s further down this chapter. Finally, you see the list of cases.

For each case the following key information is shown in the list:

#. Name and Description

#. Case ID

#. Creator

#. Creation Date

#. Finished Date

#. Status

|case-key-information|

Furthermore, at the end of each row you find a set of key features in
handling cases:

#. Detail

#. :guilabel:`Actions` for further actions

Finally, you have the possibility to access the full set of the case
data by clinking the case Name/Description.

|case-details|

The case details are separated into 4 different sections:

#. Data and Description, you find various metadata concerning the case and description.

   |case-details-data-description|

#. Related running tasks and cases, you find a list of all related running cases and
   tasks. Moving the mouse over the tasks shows you the state and the
   responsible user of the task. To show all related tasks see the HowTo
   below.

   |case-details-related-tasks-cases|


#. Histories, the Axon Ivy Portal adds notes
   here for state changes of the case. You may add additional notes
   which is described below.

   |case-details-histories|

#. Documents, you find an area for documents that were
   attached to the case. You may add or delete documents as
   described in the HowTo’s below.

   |case-details-documents|

.. _howto-use-existing-filter-1:

HowTo: Use existing filter
--------------------------

.. hint:: 
   If there are no public filters and you didn’t create any filters so far,
   there might be no filters available. In this case, you must create a filter
   first. This is described in detail in :ref:`howto-create-new-filter-1`.
   

#. Click on the :guilabel:`Save Filter` dropdown menu.

#. Select the filter you want to use.

.. _howto-create-new-filter-1:

HowTo: Create new filter
------------------------

#. Click on the button :guilabel:`More` to select one of the available filters.
   For a list of available filters, see Table 3: Case filter criteria.

#. Click on the newly added filter and configure it.

#. Add more filters by clicking on the button :guilabel:`More` again.

#. Drop filters by clicking on the |delete-circle-icon| icon next to
   the filter.

#. When finished, click on the button :guilabel:`Save filter`.

#. The dialog :guilabel:`Save filter` is opened.

#. Under :guilabel:`Filter name`, provide a recognizable name for the filter.

#. Under :guilabel:`Filter visibility`, you can select if the filter is only
   visible for you or shall be available to all users.

#. Save the filter by clicking the button :guilabel:`Ok`.

|how-to-create-case-filter|


.. centered:: Case filter criteria

+-----------------------------------+-----------------------------------+
| Criteria                          | Description                       |
+===================================+===================================+
| ID                                | The filter lets you specify the   |
|                                   | case identify number              |
+-----------------------------------+-----------------------------------+
| Name                              | The filter lets you specify the   |
|                                   | case name                         |
+-----------------------------------+-----------------------------------+
| Created (from / to)               | The filter lets you specify in    |
|                                   | which time period the case was    |
|                                   | created.                          |
+-----------------------------------+-----------------------------------+
| Creator                           | The filter lets you specify the   |
|                                   | user who created the case.        |
+-----------------------------------+-----------------------------------+
| Description                       | The filter lets you search for    |
|                                   | keywords within the case          |
|                                   | description.                      |
+-----------------------------------+-----------------------------------+
| Finished (from / to)              | The filter lets you specify in    |
|                                   | which time period the case was    |
|                                   | finished                          |
+-----------------------------------+-----------------------------------+
| State                             | The filter lets you specify the   |
|                                   | case state                        |
+-----------------------------------+-----------------------------------+

.. hint:: 
   #. You may also start the creation of a new filter by selecting an existing
      filter first, reconfiguring it and saving it. 

   #. Depending on your permissions you might only be allowed to save filters for
      yourself.

HowTo: Configure displayed cases in the Full Case List
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. To choose columns to display, click on :guilabel:`Manage columns`. Then you could choose displayed columns by checking the related checkboxes.

#. To sort data, click on the column header. To change sort direction, click on that column header one more time.
   Note that some columns do not support sorting.

HowTo: Move and Resize Widgets in Case Details
----------------------------------------------
1. Open the case details of a case

2. Click on the **Switch to Edit Mode** button to drag, drop and resize widgets.

|how-to-switch-to-edit-mode|


3. Click on the **Save** button to save current state of page and switch to readonly mode.

|how-to-switch-to-view-mode|


4. Click **Reset** button to reset to default configuration.

|how-to-reset-to-default|

.. _howto-attach-a-document-to-the-case-1:

HowTo: Attach a document to the case
------------------------------------

#. Open the case details of a case

#. Click the link |add-icon| :guilabel:`Add document` 

#. The :guilabel:`Add document` dialog is opened

#. Upload a file by using the :guilabel:`Select` button or by simply dragging
   the file into the dialog.

#. Click the button :guilabel:`Close` to close the dialog

|how-to-attach-document-to-case|

.. _howto-remove-an-attachment-from-the-case-1:

HowTo: Remove an attachment from the case
-----------------------------------------

#. Open the case details of a task

#. Click on the |delete-icon| button next to the
   attachment you want to remove.

#. A confirmation dialog is opened

#. Confirm the deletion with the button :guilabel:`Delete` 

|how-to-delete-an-attachment-from-case|

HowTo: Add a note to a case
---------------------------

#. Open the case details of a case

#. Click on the |add-icon| :guilabel:`Add note` link below the notes section.

#. The :guilabel:`Add note` dialog is opened.

#. Enter your note.

#. Confirm your note by clicking the button :guilabel:`Save` .

|how-to-add-task-note|

HowTo: Export a case history
----------------------------

#. Open the case details of a case

#. Click on the |show-more-icon| :guilabel:`Show more` link below the notes section

   |how-to-show-note-details|

#. A new page with the case history is opened

#. You may export the history by clicking on the button :guilabel:`Export to Excel` 

   |export-case-history|

HowTo: Export Case List
-----------------------

You may export the Case List by clicking on the button **Export to Excel**.

|export-to-excel-button|

HowTo: Manipulate related tasks inside case details
---------------------------------------------------

#. Open the case details of a case

#. Scroll to related tasks section

|case-details-related-tasks-cases|

This section shows you the related tasks of this case.

.. hint:: 
   Depending on the roles you hold
   in the application and the     
   configuration of the Axon Ivy  
   Portal, you might see not only 
   the tasks belonging to you but 
   all open tasks.                

For each task the following key information is shown in the list, you can change it by click on :guilabel:`Manage columns`:

- Priority

- Name

- Description

- Responsible user or role

- Task ID

- Creation Date

- Expiry Date

- State

Furthermore, at the end of each row you find a side step menu of key features in
handling tasks:

- Reset Task

- Delegate Task

- Reserve

- Destroy Task

- Add Ad-hoc Task

Finally, you have the possibility to access the full set of the task
data by clicking on the row containing the task.

HowTo: Manipulate related cases inside case details
---------------------------------------------------

#. Open the case details

#. Scroll to related cases section (this section is shown if case has related case).

|case-details|

The following information is shown by default, you can change it by click on :guilabel:`Manage columns`:

- Name

- Description

- Case ID

- Creator

- Creation date

- Finished date

- State

- Category

Action column provides actions in handling case:

- Case details

- Business details

- Side step

Click on row will navigate to detail of related case. 
You can export the related case list by clicking on the button :guilabel:`Export to Excel`.

.. _how-to-show-process-viewer:

HowTo: Show Process Viewer
--------------------------

| Portal provides a page to let users can see the visual viewer of the process start.
  Users can access this page by clicking on the :guilabel:`Process Viewer` option in the :guilabel:`Action` menu.
| This option is available on the case and task details page, also the full case/task list page.
|

#. Click on the :guilabel:`Action` menu

#. Click on the :guilabel:`Process Viewer` option

#. A new tab of the browser is opened and users can see the process viewer with details information

|portal-process-viewer|

.. include:: ../includes/_common-icon.rst

.. |navigate-to-full-cases-list-page| image:: ../../screenshots/case/navigate-to-full-cases-list-page.png
.. |case-key-information| image:: ../../screenshots/case/case-key-information.png
.. |case-details| image:: ../../screenshots/case-detail/case-details.png
.. |case-details-data-description| image:: ../../screenshots/case-detail/case-details-data-description.png
.. |case-details-related-tasks-cases| image:: ../../screenshots/case-detail/case-details-related-tasks-cases.png
.. |case-details-histories| image:: ../../screenshots/case-detail/case-details-histories.png
.. |case-details-documents| image:: ../../screenshots/case-detail/case-details-documents.png
.. |how-to-create-case-filter| image:: ../../screenshots/case/how-to-create-case-filter.png
.. |how-to-attach-document-to-case| image:: ../../screenshots/case-detail/how-to-attach-document-to-case.png
.. |how-to-delete-an-attachment-from-case| image:: ../../screenshots/case-detail/how-to-delete-an-attachment-from-case.png
.. |how-to-add-task-note| image:: ../../screenshots/case-detail/how-to-add-task-note.png
.. |how-to-show-note-details| image:: ../../screenshots/case-detail/how-to-show-note-details.png
.. |export-case-history| image:: ../../screenshots/case/export-case-history.png
.. |export-to-excel-button| image:: ../../screenshots/case/export-to-excel-button.png
.. |how-to-switch-to-view-mode| image:: ../../screenshots/case-detail/how-to-switch-to-view-mode.png
.. |how-to-switch-to-edit-mode| image:: ../../screenshots/case-detail/how-to-switch-to-edit-mode.png
.. |how-to-reset-to-default| image:: ../../screenshots/case-detail/how-to-reset-to-default.png
.. |portal-process-viewer| image:: ../../screenshots/case/portal-process-viewer.png
