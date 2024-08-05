.. _new-dashboard:

Dashboard
*********

Introduction
------------

The |ivy| Portal Dashboard is the first page you encounter after
successful login. You can always get back to the dashboard using the 
**Dashboard** link in the |ivy| Portal navigation menu.
Alternatively, you can click on the logo image at the top left of the header.

The dashboard itself contains a set of widgets arranged on the page,
according to the defined standards in your company. 

|dash-board|

The following default widgets are available:

#. **Task list widget**: This widget displays relevant task information according to defined settings.

#. **Case list widget**: This widget displays relevant case information according to defined settings.

#. **Process list widget**: This widget displays available process starts. You can choose between different formats.

#. **Process viewer widget**: This widget provides a visual representation of the process flow.

#. **Legacy statistic chart widget**: This widget provides graphical display of legacy statistical data.

#. **Statistic chart widget**: This widget provides graphical display of statistical data.

#. **Client Statistic chart widget**: This widget provides graphical display of client statistical data.

#. **Welcome widget**: This widget greets the user based on the local time and enables a friendlier approach.

#. **News feed widget**: This widget shares relevant information as a News Feed into |ivy|.

#. **External page widget**: This widget displays an external webpage on your dashboard.

#. **Notifications widget**: This widget displays all Notifications based on your notification settings.

In addition, a developer can provide custom widgets to add relevant project information to your dashboard.

**Task list widget, Case list widget, Process list widget (compact mode)** has the following standard features:

   - Saved filters and Filter options

   |widget-filter|

   - Widget information

   |widget-info|

If you have the permission, you can re-size, re-arrange, create, or delete widgets
using the **Edit** button in the upper right corner of your dashboard:

|edit-widget|

In edit mode, you can:

   - **Move widgets using drag-and-drop**: click on the widget you want to move and drop again. Helper lines will support you.

   - **Edit existing widgets**: the configuration panel for a specific widget will open to edit it.

   - **Delete existing widgets**: the widget will be deleted.

   - **Add new widgets**: a wizard will guide you through creating and adding new widgets to your dashboard.

   - **Reset to the standard dashboard**: Do you want to undo your changes? Just reset to the default dashboard.

Add a new widget
----------------

To add a new widget in the edit mode, press on the :guilabel:`+ Add widget`
button and select one of the available widgets to add:

|add-widget|

.. _new-dashboard-task-list-widget:

Task list widget
================

Adding a task list widget will give you total flexibility about what you want to
see and how.

|task-list-widget|

On the widget configuration panel, you can define the widget name, enable the
quick search function, show or hide the widget information and fullscreen
mode icons, and manage columns and filters.

**Filters panel**

By clicking on the ``Filter`` button, you can configure the complex filter for
your widget and get a preview of it by clicking on the ``apply`` button. Please
refer to :ref:`Complex Filter <complex-filter>` for more details.

|task-widget-complex-filter-configuration|

**Column Management**

This panel allows you to:

-  Edit the default sort order of the table rows by clicking on the column headers
-  Define the order of the columns 
-  Define the displayed set of columns by clicking on the link ``Manage Columns`` at the top right. You get the following dialog:

|task-list-widget-table-configuration|

In the table configuration panel, you find two sections:

   #. Add field section: add any available field to your widget table
   #. Columns section: configure the widget table itself as follows:
 
      - Enable visibility
      - Enable the quick search by selecting the quick search checkbox
      - Reorder the columns by drag and drop using the arrows |move-expand-vertical|
      - Remove columns

  .. important::

    The Quick search feature is supporting these fields:

      #. Standard fields: Id, name, description, category, responsible (display name), and application.
      #. Task custom fields: fields that have type ``STRING`` or ``TEXT``.
      #. Case custom fields: fields that have type ``STRING`` or ``TEXT``.

    Quick search is enabled for the standard field name and description by default.

Portal supports to display **custom case fields** within the task list.

|task-column-field-type-configuration|

**Excel export**

You can export all data of the task widget by clicking on the link ``Export to Excel`` at the bottom left corner of the widget information panel.

|task-export-excel|

If the number of exported tasks exceeds the maximum row number of the Excel file, Portal will separate data into multiple Excel
files and put them into a single zip file.

**Quick search**

In view mode, when quick search is enabled, a text box will appear to allow searching.

|task-quick-search-textbox|

  .. important::

    #. The quick search function is unaffected by widget filters.
    #. You can define the scope of the quick search feature in the configuration panel.

.. _new-dashboard-case-list-widget:

Case list widget
================

Adding a case list widget will give you total flexibility about what you want to
see and how.

|case-list-widget|

On the widget configuration panel, you can define the widget name, enable the
quick search function, show or hide the widget information and fullscreen
mode icons, and manage columns and filters.

**Filters panel**

By clicking on the ``Filter`` button, you can configure the complex filter for
your widget and get a preview of it by clicking on the ``apply`` button. Please
refer to :ref:`Complex Filter <complex-filter>` for more details.

|case-list-widget-configuration|

**Column Management**

This panel allows you to:

-  Edit the default sort order of the table rows by clicking on the column headers
-  define the order of the columns 
-  define the displayed set of columns by clicking on the link ``Manage Columns`` at the top right. You get the following dialog:

|case-list-widget-table-configuration|

In the table configuration panel, you find two sections:

   #. Add field section: add any available field to your widget table
   #. Columns section: configure the widget table itself as follows:

      - Enable visibility
      - Enable quick search by selecting the quick search checkbox
      - Reorder the columns by drag and drop using the arrows |move-expand-vertical|
      - Remove columns

  .. important::

    Quick search feature is supporting these fields:

      #. Standard fields: Id, name, description, category, creator (display name), and application.
      #. Custom fields: fields that have type ``STRING`` or ``TEXT``.

    Quick search is enabled for the standard field name and description by default.

**Excel export**

You can export all data of the case widget by clicking on the link ``Export to Excel`` at the end of the widget information panel.

|case-export-excel|

If the number of exported cases exceeds the maximum row number of the Excel file, Portal will separate data into multiple Excel
files and put them into a single zip file.

**Quick search**

In the view mode, when quick search is enabled, a text box will appear to allow you to searching.

|case-quick-search-textbox|

  .. important::

    #. The quick search function is unaffected by widget filters.
    #. You can define the scope of the quick search feature in the configuration panel.

.. _new-dashboard-process-list-widget:

Process list widget
===================

There are four process widget modes available:

   - Combined mode
   - Compact mode
   - Full mode
   - Image mode

|process-widget-modes|

**Combined mode**

This widget displays the selected process start and all related cases and tasks combined
in one single widget. This widget configuration will help you find tasks specifically to
a particular process:

|process-widget-combined-mode|

**Compact mode**

This widget displays a list of all selected process starts. You can sort your processes by index, alphabetical order or custom order. You can change your custom order by drag and drop the processes.

|process-widget-compact-mode|

**Full mode**

You can create a widget with a card layout including an icon for a single process.

|process-widget-full-mode|

**Image mode**

You can create a widget with a card layout, including a nice picture for a single process.

|process-widget-image-mode|

Process viewer widget
=====================

You can define the widget name and process to be displayed.

- Configuration of a process viewer widget:

  |process-viewer-widget-configuration|

- This is sample output of the widget configured above:

  |process-viewer-widget|

Statistic chart widget
======================

Portal has deprecated the legacy statistic chart widget and now offers a wide range of new standard statistical charts. For more information, please refer to the :ref:`statistic-chart` section.


Welcome widget
==============

You can define a welcome widget with a background image and a welcome text.

|welcome-widget-configuration|


News feed widget
================

You can define a news feed widget to display all news entries to the user

- Configuration of the news feed widget:

  |news-feed-widget-configuration|

- This is sample output of the widget configured above:

  |news-feed-widget|

**Manage news feed content**

.. note:: Grant permission :bdg-ref-warning:`🔑NewsManagement <NewsManagement>` to allow a user to manage the content of the news feed.

If the user has permission, then the user can:

- **Add news message**:  add a new entry for the news feed by clicking :guilabel:`Add News`: button.

- **Edit news message**: edit an existing entry for the list by clicking on the |edit-icon| icon in the lower right corner of each entry.

- **Delete news message**: delete entry directly from the list, by clicking on the |trash-icon| icon in the lower right corner of each entry.

The :guilabel:`Manage News` dialog.

|news-feed-widget-manage-content|

|news-feed-widget-overlay-panel|

In manage news, the user can:

#. Create one entry in multiple languages. When creating the entry, the user can switch between the available languages with a click on the tab view header.

#. Translate the title by clicking on the input label. Translate the content by clicking on the |translate-icon| icon.

#. Choose an icon for the news by clicking directly on the icon, the :guilabel:`Icon browser` browser will be shown.

#. Define the news title. Its length is limited to 200 characters.

#. Provide news content, i.e., the main information for the newsfeed. The content is limited to 1000 characters, max.

After setting up everything, simply click on the :guilabel:`Share this dashboard` link at the bottom of your dashboard to share it with colleagues.

External page widget
====================

You enter an URL to embed an external webpage into the Portal dashboard.

|external-page-widget-configuration|

.. _add-new-notification-widget:

Notifications widget
====================

You can define a Notifications widget to display all Notifications based on your notification settings.

|notification-widget-configuration|

- This is the sample output of the widget configured above:

|notification-widget|

.. include:: ../includes/_common-icon.rst

.. |dash-board| image:: ../../screenshots/new-dashboard/dashboard.png
.. |widget-filter| image:: ../../screenshots/new-dashboard/widget-filter.png
.. |widget-info| image:: ../../screenshots/new-dashboard/widget-info.png
.. |case-export-excel| image:: ../../screenshots/new-dashboard/case-export-excel.png
.. |task-export-excel| image:: ../../screenshots/new-dashboard/task-export-excel.png
.. |edit-widget| image:: ../../screenshots/new-dashboard/edit-widget.png
.. |add-widget| image:: ../../screenshots/new-dashboard/add-widget.png

.. |task-list-widget| image:: ../../screenshots/new-dashboard/task-list-widget.png
.. |task-list-widget-configuration| image:: ../../screenshots/new-dashboard/task-list-widget-configuration.png
.. |task-list-widget-table-configuration| image:: ../../screenshots/new-dashboard/task-list-widget-table-configuration.png
.. |task-column-field-type-configuration| image:: ../../screenshots/new-dashboard/task-column-field-type-configuration.png
.. |task-widget-complex-filter-configuration| image:: ../../screenshots/new-dashboard/task-widget-complex-filter-configuration.png

.. |case-list-widget| image:: ../../screenshots/new-dashboard/case-list-widget.png
.. |case-list-widget-configuration| image:: ../../screenshots/new-dashboard/case-list-widget-configuration.png
.. |case-list-widget-table-configuration| image:: ../../screenshots/new-dashboard/case-list-widget-table-configuration.png

.. |process-widget-modes| image:: ../../screenshots/new-dashboard/process-widget-modes.png
.. |process-widget-combined-mode| image:: ../../screenshots/new-dashboard/process-widget-combined-mode.png
.. |process-widget-compact-mode| image:: ../../screenshots/new-dashboard/process-widget-compact-mode.png
.. |process-widget-full-mode| image:: ../../screenshots/new-dashboard/process-widget-full-mode.png
.. |process-widget-image-mode| image:: ../../screenshots/new-dashboard/process-widget-image-mode.png
.. |process-viewer-widget| image:: ../../screenshots/new-dashboard/process-viewer-widget.png
.. |process-viewer-widget-configuration| image:: ../../screenshots/new-dashboard/process-viewer-widget-configuration.png

.. |welcome-widget-configuration| image:: ../../screenshots/new-dashboard/welcome-widget-configuration.png
.. |news-feed-widget-configuration| image:: ../../screenshots/new-dashboard/news-feed-widget-configuration.png
.. |news-feed-widget| image:: ../../screenshots/new-dashboard/news-feed-widget.png
.. |news-feed-widget-manage-content| image:: ../../screenshots/new-dashboard/news-feed-widget-manage-content.png
.. |news-feed-widget-overlay-panel| image:: ../../screenshots/new-dashboard/news-feed-widget-overlay-panel.png

.. |external-page-widget-configuration| image:: ../../screenshots/dashboard/external-page-widget-configuration.png

.. |add-statistic-widget| image:: ../../screenshots/new-dashboard/add-client-statistic-widget.png

.. |notification-widget-configuration| image:: ../../screenshots/new-dashboard/notification-widget-configuration.png
.. |notification-widget| image:: ../../screenshots/new-dashboard/notification-widget.png
.. |task-quick-search-textbox| image:: ../../screenshots/new-dashboard/task-quick-search-textbox.png
.. |case-quick-search-textbox| image:: ../../screenshots/new-dashboard/case-quick-search-textbox.png
