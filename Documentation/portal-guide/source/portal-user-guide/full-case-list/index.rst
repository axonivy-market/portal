.. _full-case-list:

Full Case List
**************

The **Full Case List** page is a dashboard featuring a single, full-width task widget. 
You can reach the page by using the |case-icon| :guilabel:`Cases` link in the |ivy| Portal menu.

.. note::

   Grant permission :bdg-ref-warning:`🔑AccessFullCaseList <AccessFullCaseList>` if |case-icon| :guilabel:`Cases` is not visible.

.. hint:: 
   The set of cases you see in the Portal depends on the roles you hold 
   in in your applications.                      

|navigate-to-full-cases-list-page|

For each case, the list shows the following key information:

#. Case ID

#. Name

#. Description

#. Creator

#. Creation Date

#. Finished Date

#. State

#. Category

|case-key-information|

At the end of each row you find a set of key features to handle cases by clicking on :guilabel:`Actions`.

To access the full set of case data, click on the case Name/Description.

|case-details|

The case details are separated into four different sections:

#. Data and Description: Contain various metadata of the case and its description.

   |case-details-data-description|

#. Related running tasks and cases: Contains a list of all related running cases
   and tasks. Moving the mouse over a task shows you both state and responsible
   user. To show all related tasks see the HowTo below.

   |case-details-related-tasks-cases|

#. Histories: The |ivy| Portal adds notes here for state changes of the
   case. You may add your own notes as described below.

   |case-details-histories|

#. Documents: Here, documents attached to the case are listed. You
   may add or delete documents as described in the HowTo’s below.

   |case-details-documents|

.. _howto-use-existing-filter-1:

HowTo: Move and Resize Widgets in Case Details
----------------------------------------------
1. Open the case details

2. Click on the :guilabel:`Edit` button to drag, drop and resize widgets.

|how-to-switch-to-edit-mode|


3. Click on the **Save** button to save the current state of the page and switch
   to read-only mode.

|how-to-switch-to-view-mode|


4. Click the **Reset** button to reset the widget to its default configuration.

|how-to-reset-to-default|

.. _howto-attach-a-document-to-the-case-1:

HowTo: Attach a document to a case
------------------------------------

#. Open the case details

#. Click |add-icon| :guilabel:`Add document` 

#. The :guilabel:`Add document` dialog is opened

#. Upload a file by clicking :guilabel:`Upload here` or by dragging the
   file into the dialog

#. Click :guilabel:`Close` close the dialog

|how-to-attach-document-to-case|

.. note::

   Grant either permission :bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>` or :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>` to allow a user to write case documents.

.. _howto-remove-an-attachment-from-the-case-1:

HowTo: Remove an Attachment from the Case
-----------------------------------------

#. Open the case details

#. Click on the |delete-icon| button next to the
   attachment you want to remove.

#. A confirmation dialog is opened

#. Click :guilabel:`Delete` to remove the attachment

|how-to-delete-an-attachment-from-case|

.. note::
   
   Grant either permission :bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>` or :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>` to allow a user to delete attachments from a case.

HowTo: Add a Note to a Case
---------------------------

#. Open the case details

#. Click on the |add-icon| ``Add note`` link below the notes section.

#. The :guilabel:`Add note` dialog is opened.

#. Enter your note.

#. To save your note, click :guilabel:`Save`.

|how-to-add-task-note|

.. note::
   Notes of a technical case are displayed on the ``Notes`` table of the case, and its tasks.

   Visibility rule for notes of business case:
   
      - Displayed in the ``History`` table of the case details and its direct tasks
      - Displayed in the ``History`` table of its technical case details.
      - Not displayed in the ``Notes`` table of tasks belong to technical case.
   
   Grant permission :bdg-ref-warning:`🔑TaskCaseAddNote <TaskCaseAddNote>` to allow a user to add a note to a case.

HowTo: Export a Case History
----------------------------

#. Open the case details

#. Click on |show-more-icon| :guilabel:`Show more` below the notes section

   |how-to-show-note-details|

#. A page containing the case history is opened

#. To export the case history, click on :guilabel:`Export to Excel` 

   |export-case-history|

HowTo: Manipulate Related Tasks Inside Case Details
---------------------------------------------------

#. Open the case details

#. Scroll to **Related Tasks of Case** 

|case-details-related-tasks-cases|

This section shows you the related tasks of this case.

.. hint:: 
   What you see here depends on the roles granted to you in both the application 
   and the |ivy| Portal. Administrators can see all open tasks.

.. note::
   
   Grant either permission :bdg-ref-warning:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>` or :bdg-ref-warning:`🔑TaskReadAll <TaskReadAll>` to allow a user to see all related tasks of a case.

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

- Finished date

- State

At the end of each row, a side step menu is available. It offers the following
actions:

- Reset Task

- Delegate Task

- Reserve Task

- Destroy Task

- Trigger Escalation Task

- Workflow Events

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

#. Click on :guilabel:`Actions`

#. Click on :guilabel:`Process Viewer`

#. Portal opens a new browser tab. You see the process viewer.

|portal-process-viewer|


HowTo: Share Case Details
-------------------------

#. Open the case details

#. Click on the share button |share-icon|

|how-to-share-case-details|

.. note::

   Grant permission :bdg-ref-warning:`🔑ShareCaseDetailsLink <ShareCaseDetailsLink>` to allow a user to share case details.

.. include:: ../includes/_common-icon.rst

.. |navigate-to-full-cases-list-page| image:: ../../screenshots/case/navigate-to-full-cases-list-page.png
   :alt: Menu to navigate to the Full case list page
.. |case-key-information| image:: ../../screenshots/case/case-key-information.png
   :alt: Full case list: key informations
.. |case-details| image:: ../../screenshots/case-detail/case-details.png
   :alt: The Case Details page
.. |case-details-data-description| image:: ../../screenshots/case-detail/case-details-data-description.png
   :alt: Case Details: General section
.. |case-details-related-tasks-cases| image:: ../../screenshots/case-detail/case-details-related-tasks-cases.png
   :alt: Case Details: Related Tasks of Case section
.. |case-details-histories| image:: ../../screenshots/case-detail/case-details-histories.png
   :alt: Case Details: Histories section
.. |case-details-documents| image:: ../../screenshots/case-detail/case-details-documents.png
   :alt: Case Details: Documents section
.. |how-to-attach-document-to-case| image:: ../../screenshots/case-detail/how-to-attach-document-to-case.png
   :alt: Case Details: Add document dialog
.. |how-to-delete-an-attachment-from-case| image:: ../../screenshots/case-detail/how-to-delete-an-attachment-from-case.png
   :alt: Case Details: Delete document confirm dialog
.. |how-to-add-task-note| image:: ../../screenshots/case-detail/how-to-add-task-note.png
   :alt: Case Details: Add note dialog
.. |how-to-show-note-details| image:: ../../screenshots/case-detail/how-to-show-note-details.png
   :alt: Case Details: Link to show more notes details
.. |export-case-history| image:: ../../screenshots/case/export-case-history.png
   :alt: Case Business Information page: Link to export case history
.. |how-to-switch-to-view-mode| image:: ../../screenshots/case-detail/how-to-switch-to-view-mode.png
   :alt: Case Details: save button
.. |how-to-switch-to-edit-mode| image:: ../../screenshots/case-detail/how-to-switch-to-edit-mode.png
   :alt: Case Details: button to switch to the edit mode
.. |how-to-reset-to-default| image:: ../../screenshots/case-detail/how-to-reset-to-default.png
   :alt: Case Details: button to reset to the default configurations
.. |portal-process-viewer| image:: ../../screenshots/case/portal-process-viewer.png
   :alt: Process Viewer page
.. |how-to-share-case-details| image:: ../../screenshots/case-detail/share-page-button.png
   :alt: Case Details: button to get the shareable link of the case details
