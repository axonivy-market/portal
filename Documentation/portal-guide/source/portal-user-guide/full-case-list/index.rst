.. _full-case-list:

Full Case List
**************

The **Full Case List** page is a dashboard featuring a single, full-width case widget. 
You can reach the page by using the |case-icon| :guilabel:`Cases` link in the Axon Ivy Portal menu.

.. important::
   **Access Requirement**: Only users with the :bdg-ref-warning:`🔑AccessFullCaseList <AccessFullCaseList>` 
   permission can see the |case-icon| :guilabel:`Cases` menu item.

.. tip:: 
   The cases you see depend on the roles you hold in your applications.

|navigate-to-full-cases-list-page|

Case List Information
=====================

For each case, the list shows the following key information:

#. Case ID

#. Pin

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

Case Details
============

The case details page is organized into four main sections:

.. table::
   :widths: 25 75

   +----------------------------------+---------------------------------------------------------------+
   | Section                          | Content                                                       |
   +==================================+===============================================================+
   | **Data and Description**         | Case metadata and description                                 |
   +----------------------------------+---------------------------------------------------------------+
   | **Related Tasks and Cases**      | Lists related running cases and tasks                         |
   +----------------------------------+---------------------------------------------------------------+
   | **Histories**                    | State changes, system notes, and user-added notes             |
   +----------------------------------+---------------------------------------------------------------+
   | **Documents**                    | Attached files with add, delete, rename, preview options      |
   +----------------------------------+---------------------------------------------------------------+

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
   may add, delete, rename or preview documents as described in the HowTo’s below.

   |case-details-documents|

.. _howto-move-resize-widgets-case-details:

HowTo: Move and Resize Widgets in Case Details
------------------------------------------------

#. Open the case details

#. Click on the :guilabel:`Edit` button to drag, drop and resize widgets.

   |how-to-switch-to-edit-mode|

#. Click on the :guilabel:`Save` button to save the current state of the page and switch to read-only mode.

   |how-to-switch-to-view-mode|

#. Click the :guilabel:`Reset` button to reset the widget to its default configuration.

   |how-to-reset-to-default|

.. _howto-attach-a-document-to-the-case-1:

HowTo: Attach a Document to a Case
-----------------------------------

#. Open the case details

#. Click |add-icon| :guilabel:`Add document` 

#. The :guilabel:`Add document` dialog is opened

#. Upload a file by clicking :guilabel:`Upload here` or by dragging the
   file into the dialog

#. Click :guilabel:`Close` to close the dialog

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

HowTo: Preview a Document of a Case
------------------------------------

#. Open the case details

#. Click on the |preview-icon| button next to the attachment you want to preview

#. A preview dialog is opened

#. Click the button :guilabel:`Close` to close the dialog

|how-to-preview-document|

.. note::
   **Supported Document Types:**
   
   - **Default**: Images (jpg, jpeg, bmp, png), plain text (txt, log), and PDF documents
   - **With DocFactory**: Word (doc, docx), Excel (xls, xlsx), and email (eml) files
   
   Set :ref:`Portal settings <update-portal-settings>` :guilabel:`Portal.Document.EnablePreview` to true to enable this feature. 
   For Office documents, deploy :dev-url:`DocFactory <https://market.axonivy.com/doc-factory#tab-description>` in the same security context.

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

HowTo: Add a Note to a Case
---------------------------

#. Open the case details

#. Click on the |add-icon| ``Add note`` link below the notes section.

#. The :guilabel:`Add note` dialog is opened.

#. Enter your note.

#. To save your note, click :guilabel:`Save`.

|how-to-add-task-note|

.. note::
   **Note Visibility:**
   
   - **Technical case notes**: Displayed in the ``Notes`` table of the case and its tasks
   - **Business case notes**: Displayed in the ``History`` table of the case details, its direct tasks, and technical case details (not shown in task Notes tables)

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

.. note:: 
   The tasks you see depend on your application roles and Portal permissions. Administrators can see all open tasks.

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

**Available Actions:**

At the end of each row, click the side step menu to access:

- **Reset Task**: Revert the task to its initial state
- **Delegate Task**: Assign the task to another user or role
- **Reserve Task**: Reserve the task for yourself
- **Destroy Task**: Permanently remove the task
- **Trigger Escalation Task**: Manually trigger task escalation
- **Workflow Events**: View and manage workflow events

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

**Available Actions:**

- **Case details**: View full case information
- **Business details**: View business-specific case data
- **Side step**: Access additional case operations

To see the details of the related case, click on its row. To export the related
case list, click on :guilabel:`Export to Excel`.

.. _how-to-show-process-viewer:

HowTo: Show Process Viewer
--------------------------

Portal provides a visual representation of the process flow of the current case or task. To open the viewer, select :guilabel:`Process Viewer` in the :guilabel:`Action` menu.

You find this option on the following pages:

- Case details
- Task details
- Full case list
- Full task list

**Steps:**

#. Click on :guilabel:`Actions`

#. Click on :guilabel:`Process Viewer`

#. Portal opens a new browser tab. You see the process viewer.

|portal-process-viewer|


HowTo: Share Case Details
-------------------------

#. Open the case details

#. Click on the share button |share-icon|

|how-to-share-case-details|

HowTo: Show Case Custom Fields Dialog
-------------------------------------

#. Open the case details

#. Click on :guilabel:`Actions`

#. Click on :guilabel:`Custom Fields`

#. Portal opens the custom fields dialog of the case

|case-custom-fields-dialog|

Permission Reference
====================

Different case operations require specific permissions:

.. table::
   :widths: 40 60

   +--------------------------------------+--------------------------------------------------------+
   | Action                               | Required Permission                                    |
   +======================================+========================================================+
   | **View related tasks of case**       | :bdg-ref-warning:`🔑TaskReadOwnCaseTasks               |
   |                                      | <TaskReadOwnCaseTasks>` or                             |
   |                                      | :bdg-ref-warning:`🔑TaskReadAll <TaskReadAll>`         |
   +--------------------------------------+--------------------------------------------------------+
   | **Add/delete case documents**        | :bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite        |
   |                                      | <DocumentOfInvolvedCaseWrite>` or                      |
   |                                      | :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>`     |
   +--------------------------------------+--------------------------------------------------------+
   | **Add note to case**                 | :bdg-ref-warning:`🔑TaskCaseAddNote                    |
   |                                      | <TaskCaseAddNote>`                                     |
   +--------------------------------------+--------------------------------------------------------+
   | **Share case details link**          | :bdg-ref-warning:`🔑ShareCaseDetailsLink               |
   |                                      | <ShareCaseDetailsLink>`                                |
   +--------------------------------------+--------------------------------------------------------+
   | **View custom fields**               | :bdg-ref-warning:`🔑CaseDisplayCustomFieldsAction      |
   |                                      | <CaseDisplayCustomFieldsAction>`                       |
   +--------------------------------------+--------------------------------------------------------+

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
.. |how-to-preview-document| image:: ../../screenshots/case-detail/how-to-preview-document.png
   :alt: Case Details: preview document dialog
.. |how-to-rename-document| image:: ../../screenshots/case-detail/how-to-rename-document.png
   :alt: Case Details: rename document dialog
.. |case-custom-fields-dialog| image:: ../../screenshots/case-detail/case-custom-fields-dialog.png
   :alt: Case Details: custom fields dialog
