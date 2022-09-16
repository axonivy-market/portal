.. _full-case-list:

Full Case List
**************

The **Full Case List** page shows you all cases you are involved in.
This page provides you with extended information on the cases as well
as advanced search and filter capabilities. You can reach the page by
selecting |case-icon| :guilabel:`Cases` in the Axon Ivy Portal menu.

.. hint:: 
   The set of cases you see in the Portal depends on the roles you hold 
   in both the application and the Axon Ivy Portal. Administrators 
   (having role AXONIVY_PORTAL_ADMIN) see all cases.                      

|navigate-to-full-cases-list-page|

On the top of the **Full Case List** page, next to the heading
:guilabel:`Cases`, you see the overall number of cases shown to you, in
brackets. Below, you find the filter feature. Their usage will be explained below in
the HowTo’s section. Finally, you see the list of cases.

For each case, the list shows the following key information:

#. Name and Description

#. Case ID

#. Creator

#. Creation Date

#. Finished Date

#. Status

#. Category

#. Application (This column is not displayed by default)

|case-key-information|

At the end of each row you find a set of key features to handle cases:

#. Detail

#. :guilabel:`Actions` for further actions

To access the full set of case data, click on the case Name/Description.

|case-details|

The case details are separated into four different sections:

#. Data and Description: Contain various metadata of the case and its description.

   |case-details-data-description|

#. Related running tasks and cases: Contains a list of all related running cases
   and tasks. Moving the mouse over a task shows you both state and responsible
   user. To show all related tasks see the HowTo below.

   |case-details-related-tasks-cases|

#. Histories: The Axon Ivy Portal adds notes here for state changes of the
   case. You may add your own notes as described below.

   |case-details-histories|

#. Documents: Here, documents attached to the case are listed. You
   may add or delete documents as described in the HowTo’s below.

   |case-details-documents|

.. _howto-use-existing-filter-1:

HowTo: Use Existing Filter
--------------------------

.. hint:: 
   If there are no public filters and you didn’t create any filters so far,
   there might be no filters available. In this case, you have to create a filter
   first. This is described in detail in :ref:`howto-create-new-filter-1`.
   

#. Click on the :guilabel:`Save Filter` dropdown menu.

#. Select the filter you want to use.

.. _howto-create-new-filter-1:

HowTo: Create New Filter
------------------------

#. Click on :guilabel:`More` to select one of the available filters.
   For a list of available filters, see Table 3: Case filter criteria.

#. Click on the newly added filter and configure it.

#. Add more filters by clicking on :guilabel:`More` again.

#. Drop filters by clicking on |delete-circle-icon| next to the filter.

#. When finished, click on :guilabel:`Save filter`.

#. The dialog :guilabel:`Save filter` is opened.

#. In :guilabel:`Filter name`, provide a descriptive name for the filter.

#. In :guilabel:`Filter visibility`, define if the filter is only
   visible to you or to all users.

#. Save the filter by clicking :guilabel:`Ok`.

|how-to-create-case-filter|


.. centered:: Case filter criteria

+-----------------------+-----------------------------------+
| Criteria              | Description                       |
+=======================+===================================+
|  ID                   |  Specify the ID of the case       |
|                       |                                   |
+-----------------------+-----------------------------------+
|  Name                 |  Specify the case name            |
|                       |                                   |
+-----------------------+-----------------------------------+
|  Created (from / to)  |  Specify the period in which the  |
|                       |  case has been created.           |
|                       |                                   |
+-----------------------+-----------------------------------+
|  Creator              |  Specify the user who has created |
|                       |  the case.                        |
+-----------------------+-----------------------------------+
|  Description          |  Search for keywords within the   |
|                       |  case description.                |
|                       |                                   |
+-----------------------+-----------------------------------+
|  Finished (from / to) |  Specify the period in which the  |
|                       |  case has been finished.          |
+-----------------------+-----------------------------------+
|  State                |  Specify the case state           |
|                       |                                   |
+-----------------------+-----------------------------------+

.. hint::
   #. You can create a new filter by selecting an existing filter, reconfiguring it, and saving it.

   #. Only users with the admin role ``AXONIVY_PORTAL_ADMIN`` can save the filter for all users.

HowTo: Configure Displayed Cases in the Full Case List
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. To choose columns to display, click on :guilabel:`Manage columns`. Select
   columns to display by checking the related checkboxes.

#. To sort data, click on the column header. To change sort direction, click on
   that column header one more time. Note that some columns do not support
   sorting.

HowTo: Move and Resize Widgets in Case Details
----------------------------------------------
1. Open the case details

2. Click on the **Switch to Edit Mode** button to drag, drop and resize widgets.

|how-to-switch-to-edit-mode|


3. Click on the **Save** button to save the current state of the page and switch
   to read-only mode.

|how-to-switch-to-view-mode|


4. Click the **Reset** button to reset the widget to its default configuration.

|how-to-reset-to-default|

.. _howto-attach-a-document-to-the-case-1:

HowTo: Attach a Document to the Case
------------------------------------

#. Open the case details

#. Click |add-icon| :guilabel:`Add document` 

#. The :guilabel:`Add document` dialog is opened

#. To upload a file, click :guilabel:`Select` or simply drag the file into the
   dialog.

#. Click :guilabel:`Close` close the dialog

|how-to-attach-document-to-case|

.. _howto-remove-an-attachment-from-the-case-1:

HowTo: Remove an Attachment from the Case
-----------------------------------------

#. Open the case details

#. Click on the |delete-icon| button next to the
   attachment you want to remove.

#. A confirmation dialog is opened

#. Click :guilabel:`Delete` to remove the attachment

|how-to-delete-an-attachment-from-case|

HowTo: Add a Note to a Case
---------------------------

#. Open the case details

#. Click on the |add-icon| ``Add note`` link below the notes section.

#. The :guilabel:`Add note` dialog is opened.

#. Enter your note.

#. To save your note, click :guilabel:`Save`.

|how-to-add-task-note|

HowTo: Export a Case History
----------------------------

#. Open the case details

#. Click on |show-more-icon| :guilabel:`Show more` below the notes section

   |how-to-show-note-details|

#. A page containing the case history is opened

#. To export the case history, click on :guilabel:`Export to Excel` 

   |export-case-history|

HowTo: Export Case List
-----------------------

To export the Case List, click on **Export to Excel**.

|export-to-excel-button|

HowTo: Manipulate Related Tasks Inside Case Details
---------------------------------------------------

#. Open the case details

#. Scroll to **Related Tasks of Case** 

|case-details-related-tasks-cases|

This section shows you the related tasks of this case.

.. hint:: 
   What you see here depends on the roles granted to you in both the application 
   and the Axon Ivy Portal. Administrators can see all open tasks.

The list shows the following key information for each task. To change the
columns displayed, click on :guilabel:`Manage columns`. Select from the
following columns:

- Priority

- Name

- Description

- Responsible user or role

- Task ID

- Creation Date

- Expiry Date

- State

At the end of each row, a side step menu is available. It offers the following
actions:

- Reset Task

- Delegate Task

- Reserve Task

- Destroy Task

- Trigger Escalation Task

- Add Ad-hoc Task

To access the full set of the task data, click on the row containing the
task.

HowTo: Manipulate Related Cases Inside Case Details
---------------------------------------------------

#. Open the case details

#. Scroll to **Related Cases** section. This section is shown only if the case
   has related cases.

|case-details|

By default, the following information is shown. To change it, click on :guilabel:`Manage columns` to select from:

- Name

- Description

- Case ID

- Creator

- Creation date

- Finished date

- State

- Category

The Action column provides these actions to handle the related case:

- Case details

- Business details

- Side step

To see the details of the related case, click on its row. To export the related
case list, click on :guilabel:`Export to Excel`.

.. _how-to-show-process-viewer:

HowTo: Show Process Viewer
--------------------------

| Portal provides a visual representation of the process flow of the current case or task. To open the viewer, select :guilabel:`Process Viewer` in the :guilabel:`Action` menu.
| You find this option on the following pages:

   - Case details
   - Task details
   - Full case list
   - Full task list

#. Click on :guilabel:`Action`

#. Click on :guilabel:`Process Viewer`

#. Portal opens a new browser tab. You see the process viewer.

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
