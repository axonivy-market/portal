.. _case-widget:

Case Widget
***********

The **Case Widget** shows a list of cases. The cases you see depend on the roles you hold in your
applications. This chapter describes how to work with the widget and with the case details behind it.

To add a Case Widget to a dashboard and configure it, follow :ref:`Case list widget
<new-dashboard-case-list-widget>` in the Dashboard chapter. To configure it in the dashboard JSON
instead, see :ref:`Configure Case Widget <configure-new-dashboard-case-widget>`.

.. hint::
   Portal ships a default dashboard named :guilabel:`Cases` that contains a single case widget.
   You can reach it with the |case-icon| :guilabel:`Cases` link in the Axon Ivy Portal menu.

   |navigate-to-case-widget-page|

   - The template Full Case List dashboard will support you in creating a new dashboard with a similar layout, see :ref:`public-available-dashboard-template`.

   - The dashboard is visible to all users by default. To restrict access to specific user roles, configure the dashboard permissions in the :ref:`Dashboard Configuration <dashboard-configuration>` page.

Overview
========

Below are the standard fields of a case. You can configure them in the column management dialog,
see :ref:`Column Management <case-widget-column-management>`.

#. Pin

#. Case Id

#. Case name

#. Description

#. State

#. Creator

#. Created

#. Finished

#. Case Owners

#. Category

#. Application (This column is not added by default)

|case-key-information|

Widget Actions
--------------

On the right side of the widget header you find the operations that apply to the whole widget.
Click the actions menu button to open them.

|case-widget-actions-menu|

- **Quick search**: Search the cases of the widget by keyword

- **Show pinned cases**: Show your pinned cases only, :guilabel:`Show all cases` switches back

- **Filters**: Open the complex filter panel of the widget, see :ref:`Complex Filter <complex-filter>`

- **Expand**: Show the widget in fullscreen, :guilabel:`Collapse` returns it to its normal size

- **Widget information**: Show additional widget details and statistics, and export the list to Excel

.. note::
   Which of these operations are available depends on the widget configuration, see
   :ref:`Configuration panel <case-list-widget-configuration-panel>` of the Case list widget.

**Quick search**

When quick search is enabled, click the :guilabel:`Quick search` button to reveal the search input
and start searching.

|case-quick-search-textbox|

  .. important::

    #. The scope of the quick search feature is defined in the configuration panel of the widget.
    #. The result of the quick search function may be affected if you apply a complex filter on the widget.

**Show pinned cases**

:guilabel:`Show pinned cases` filters the widget so it only displays cases you have pinned; the
item then switches to :guilabel:`Show all cases`, which returns you to the full case list.

**Filters**

:guilabel:`Filters` opens the widget's complex filter panel, where you can narrow down the
displayed cases by criteria such as status, category, or date. See :ref:`complex-filter` for more
details.

**Expand**

:guilabel:`Expand` enlarges the widget to fill the entire screen for a better view of your data;
:guilabel:`Collapse` returns it to its normal size.

**Widget information**

:guilabel:`Widget information` opens the information panel, showing additional widget details and
statistics. From there, you can also export all data of the case widget using the
``Export to Excel`` link at the end of the panel.

|case-export-excel|

If the number of exported cases exceeds the maximum row number of the Excel file, Portal will
separate data into multiple Excel files and put them into a single zip file.

Case Actions
------------

At the end of each row you find the |actions-menu-icon| :guilabel:`Actions` menu with the
following options:

.. table::
   :widths: 30 70

   +---------------------------+---------------------------------------------------------------+
   | Action                    | Description                                                   |
   +===========================+===============================================================+
   | **Details**               | View full case information                                    |
   +---------------------------+---------------------------------------------------------------+
   | **Pin**                   | Pin the case to find it quickly later, :guilabel:`Unpin`      |
   |                           | removes it again                                              |
   +---------------------------+---------------------------------------------------------------+
   | **Process overview**      | Show how far the case has progressed in its process           |
   +---------------------------+---------------------------------------------------------------+
   | **Business details**      | View business-specific case data                              |
   +---------------------------+---------------------------------------------------------------+
   | **Custom Fields**         | View case custom fields                                       |
   +---------------------------+---------------------------------------------------------------+
   | **Process Viewer**        | Visual representation of the process flow                     |
   +---------------------------+---------------------------------------------------------------+
   | **Destroy**               | Destroy the case                                              |
   +---------------------------+---------------------------------------------------------------+

At the end of the menu you find the side steps of the process.

Case Details
============

Clicking on a row opens the case details by default. If the :ref:`Portal setting
<update-portal-settings>` ``Portal.Cases.BehaviourWhenClickingOnLineInCaseList`` is set to
another behaviour, use :guilabel:`Actions` then :guilabel:`Details`.

|case-details|

The case details page consists of the following widgets:

#. **Summary**: The name of the case, its ID and creation date and the |actions-menu-icon|
   :guilabel:`Actions` menu of the case. Below you find Case Id, State
   and Case Category.

#. **Case Details**: The description of the case, followed by
   Role and User Information and Time Information.

   |case-details-data-description|

#. **Related Tasks of Case**: The tasks of the case. Activate :guilabel:`Show only open tasks` to
   hide the tasks which are already done, |download-icon| exports the list to Excel and
   :guilabel:`Manage columns` defines which columns are displayed.

   |case-details-related-tasks-cases|

#. **Documents**: The documents attached to the case. You may add, delete, rename or preview
   documents as described in the HowTo's below.

   |case-details-documents|

#. **History**: The notes and the state changes of the case. Activate :guilabel:`Related case`,
   :guilabel:`System tasks` and :guilabel:`System notes` to show these entries as well, and click
   :guilabel:`Show more` to open the full history.

   |case-details-histories|

#. **Related Cases**: The technical cases of the case. This widget is only displayed if the case
   has related cases.

.. _howto-move-resize-widgets-case-details:

How-to
======

HowTo: Move and Resize Widgets in Case Details
------------------------------------------------

#. Open the case details

#. Click on the :guilabel:`Edit layout` button to drag, drop and resize widgets.

   |how-to-switch-to-edit-mode|

#. Click on the :guilabel:`Save` button to save the current state of the page and switch to read-only mode.

   |how-to-switch-to-view-mode|

#. Click the :guilabel:`Reset` button to reset the widget to its default configuration.

   |how-to-reset-to-default|

.. _howto-attach-a-document-to-the-case-1:

HowTo: Attach a Document to a Case
-----------------------------------

#. Open the case details

#. Click the |add-icon| button in the header of the **Documents** widget

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
   
   Set :ref:`Portal settings <update-portal-settings>` ``Portal.Document.EnablePreview`` to true to enable this feature. 
   For Office documents, deploy `DocFactory <https://market.axonivy.com/doc-factory#tab-description>`_ in the same security context.

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

#. Click the |add-icon| button in the header of the **History** widget

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

#. Click on |show-more-icon| :guilabel:`Show more` in the header of the **History** widget

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

The list shows the following key information for each task. To change the columns displayed, click
on :guilabel:`Manage columns` and select from the following columns:

- Priority

- Name

- Description

- Responsible

- Task Id

- Created

- Expiry

- Completed on

- State

- Application (This column is not selected by default)

- Working user (This column is not selected by default)

- Business Case ID (This column is not selected by default)

- Technical Case ID (This column is not selected by default)

:guilabel:`Default` selects the columns which are displayed by default.

**Available Actions:**

At the end of each row you find the |actions-menu-icon| :guilabel:`Actions` menu of the task, see
:ref:`Task Actions <task-row-actions>` of the Task Widget.

To access the full set of the task data, click on the row containing the task.

HowTo: Manipulate Related Cases Inside Case Details
---------------------------------------------------

#. Open the case details

#. Scroll to **Related Cases** section. This section is shown only if the case
   has related cases.

|case-details|

The list shows the following key information for each case. To change the columns displayed, click
on :guilabel:`Manage columns` and select from the following columns:

- Name

- Description

- Case Id

- Creator

- Case Owners

- Created

- Finished

- State

- Category

All these columns are displayed by default.

**Available Actions:**

At the end of each row you find the |actions-menu-icon| :guilabel:`Actions` menu of the case with
the same options as in the widget, see `Case Actions`_.

To see the details of the related case, click on its row. To export the related case list, click on
|download-icon| :guilabel:`Export to Excel`.

.. _how-to-show-process-viewer:

HowTo: Show Process Viewer
--------------------------

Portal provides a visual representation of the process flow of the current case or task. To open the viewer, select :guilabel:`Process Viewer` in the :guilabel:`Actions` menu.

You find this option on the following pages:

- Case details
- Task details
- Case Widget
- Task Widget

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
   | **View related tasks of case**       | :ref:`🔑TaskReadOwnCaseTasks                           |
   |                                      | <TaskReadOwnCaseTasks>` or                             |
   |                                      | :ref:`🔑TaskReadAll <TaskReadAll>`                     |
   +--------------------------------------+--------------------------------------------------------+
   | **Add/delete case documents**        | :ref:`🔑DocumentOfInvolvedCaseWrite                    |
   |                                      | <DocumentOfInvolvedCaseWrite>` or                      |
   |                                      | :ref:`🔑DocumentWrite <DocumentWrite>`                 |
   +--------------------------------------+--------------------------------------------------------+
   | **Add note to case**                 | :ref:`🔑TaskCaseAddNote                                |
   |                                      | <TaskCaseAddNote>`                                     |
   +--------------------------------------+--------------------------------------------------------+
   | **Share case details link**          | :ref:`🔑ShareCaseDetailsLink                           |
   |                                      | <ShareCaseDetailsLink>`                                |
   +--------------------------------------+--------------------------------------------------------+
   | **View custom fields**               | :ref:`🔑CaseDisplayCustomFieldsAction                  |
   |                                      | <CaseDisplayCustomFieldsAction>`                       |
   +--------------------------------------+--------------------------------------------------------+

.. include:: ../includes/_common-icon.rst

.. |navigate-to-case-widget-page| image:: ../../screenshots/case/navigate-to-full-cases-list-page.png
   :alt: Menu to navigate to the Case Widget page
.. |case-key-information| image:: ../../screenshots/case/case-key-information.png
   :alt: Case Widget: key information
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
.. |case-quick-search-textbox| image:: ../../screenshots/new-dashboard/case-quick-search-textbox.png
   :alt: Dashboard case widget's quick search
.. |case-export-excel| image:: ../../screenshots/new-dashboard/case-export-excel.png
   :alt: Dashboard case widget: export to Excel feature
.. |case-widget-actions-menu| image:: ../../screenshots/new-dashboard/case-widget-actions-menu-panel.png
   :alt: Dashboard case widget's actions menu panel
