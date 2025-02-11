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

#. **Client Statistic chart widget**: This widget provides graphical display of client statistical data.

#. **Welcome widget**: This widget greets the user based on the local time and enables a friendlier approach.

#. **News feed widget**: This widget shares relevant information as a News Feed into |ivy|.

#. **External page widget**: This widget displays an external webpage on your dashboard.

#. **Notifications widget**: This widget displays all Notifications based on your notification settings.

In addition, a developer can provide custom widgets to add relevant project information to your dashboard.

**Task list widget, Case list widget, Process list widget (compact mode)** have the following standard features:

   - Saved filters and Filter options

   |widget-filter|

   - Widget information

   |widget-info|

If you have the permission, you can re-size, re-arrange, create, or delete widgets
using :guilabel:`Edit` button in the upper right corner of your dashboard:

|edit-widget|

In edit mode, you can:

   - **Move widgets using drag-and-drop**: click on the widget you want to move and drop again. Helper lines will support you.

   - **Edit existing widgets**: open the configuration panel to edit widget by clicking on :guilabel:`Edit` button.

   - **Delete existing widgets**: the widget will be deleted.

   - **Add new widgets**: a wizard will guide you through creating and adding new widgets to your dashboard.

   - **Reset to the standard dashboard**: Do you want to undo your changes? Just reset to the default dashboard.

   - **Resize column width (Task list/Case list widget)**: click on the gridlines of header which you want to resize, then drop again. Helper lines will support you.

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
mode icons, manage columns and filters, edit the default sort order of the table columns by clicking on the column headers.

**Filters panel**

By clicking on the :guilabel:`Filter` button, you can configure the complex filter for
your widget and get a preview of it by clicking on the :guilabel:`Apply` button. Please
refer to :ref:`Complex Filter <complex-filter>` for more details.

|task-list-widget-configuration|

**Column Management**

By clicking on the :guilabel:`Manage Columns` button at the top right of the configuration panel. You get the following dialog:

|task-list-widget-table-configuration|

In the column management dialog, you find two sections to configure the table columns:

   #. Add field section: add any available fields to your widget table
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

In view mode, you can export all data of the task widget by clicking on the link ``Export to Excel`` at the bottom left corner of the widget information panel.

|task-export-excel|

If the number of exported tasks exceeds the maximum row number of the Excel file, Portal will separate data into multiple Excel
files and put them into a single zip file.

**Quick search**

In view mode, when quick search is enabled, a text box will appear to allow you to search.

|task-quick-search-textbox|

  .. important::

    #. You can define the scope of the quick search feature in the configuration panel.
    #. The result of the quick search function may be affected if you apply a complex filter on the widget.

**Adjust column width**

In edit mode, you can adjust the width of each column directly within the widget table.

|task-list-widget-edit-mode|

You may also notice gridlines on the table which implemented by Portal to help you adjust the column widths more efficiently.

  .. important::

    If the total width of the columns is less than the widget’s width, the Portal will automatically adjust the column widths to match the widget’s width.

.. _new-dashboard-case-list-widget:

Case list widget
================

Adding a case list widget will give you total flexibility about what you want to
see and how.

|case-list-widget|

On the widget configuration panel, you can define the widget name, enable the
quick search function, show or hide the widget information and fullscreen
mode icons, manage columns and filters, edit the default sort order of the table columns by clicking on the column headers.

**Filters panel**

By clicking on the :guilabel:`Filter` button, you can configure the complex filter for
your widget and get a preview of it by clicking on the :guilabel:`Apply` button. Please
refer to :ref:`Complex Filter <complex-filter>` for more details.

|case-list-widget-configuration|

**Column Management**

By clicking on the :guilabel:`Manage Columns` button at the top right of the configuration panel. You get the following dialog:

|case-list-widget-table-configuration|

In the column management dialog, you find two sections to configure the table columns:

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

In view mode, you can export all data of the case widget by clicking on the link ``Export to Excel`` at the end of the widget information panel.

|case-export-excel|

If the number of exported cases exceeds the maximum row number of the Excel file, Portal will separate data into multiple Excel
files and put them into a single zip file.

**Quick search**

In view mode, when quick search is enabled, a text box will appear to allow you to search.

|case-quick-search-textbox|

  .. important::

    #. You can define the scope of the quick search feature in the configuration panel.
    #. The result of the quick search function may be affected if you apply a complex filter on the widget.

**Adjust column width**

In edit mode, you can adjust the width of each column directly within the widget table.

|case-list-widget-edit-mode|

You may also notice gridlines on the table which implemented by Portal to help you adjust the column widths more efficiently.

  .. important::

    If the total width of the columns is less than the widget’s width, the Portal will automatically adjust the column widths to match the widget’s width.

.. _new-dashboard-process-list-widget:

Process list widget
===================

There are four process widget modes available:

   - Combined mode
   - Compact mode
   - Full mode
   - Image mode

In the widget configuration, you can choose the display mode, show or hide widget information and fullscreen mode icons.

|process-widget-modes|

**Combined mode**

This widget displays the selected process start and all related cases and tasks combined
in one single widget. This widget configuration will help you find tasks specifically to
a particular process:

|process-widget-combined-mode|

**Compact mode**

This widget displays a list of all selected process starts. You can sort your processes by index, alphabetical order or custom order.
You can change your custom order by drag and drop the processes and enable quick search by selecting the quick search checkbox.

|process-widget-compact-mode|

In view mode, when quick search function is enabled, a text box will appear to allow you to search.

  .. important::
   - Quick search function can only be used when process widget mode is ``Compact Mode``.
   - Quick search function supports searching by ``process name``.

|process-quick-search-textbox|

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

You can define a welcome widget with background images for both light and dark mode and a welcome text.

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

#. Translate the title by clicking on the input label. Translate the content by clicking on the |translate-icon| icon. If you see something wrong with the translation, please prefer to :ref:`enable-translation`

#. Choose an icon for the news by clicking directly on the icon, the :guilabel:`Icon browser` browser will be shown.

#. Define the news title. Its length is limited to 200 characters.

#. Provide news content, i.e., the main information for the news feed. The content is limited to a maximum of 1,000 characters.

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

Clone a widget
--------------

Clone a widget from existing dashboard
======================================

When editing a dashboard, you can clone a widget from another dashboard by opening the :guilabel:`Add a widget` dialog by press on the :guilabel:`+ Add widget` button.

Once the dialog opens, click the :guilabel:`Clone widget` button at the top.

|clone-widget-from-button|

This will open the :guilabel:`Clone widget from` dialog. Select the dashboard that contains the widget you want to clone, then choose the specific widget.

|clone-widget-from-dialog|

After selecting the widget, click the :guilabel:`Clone` button to add it to the current dashboard.

Clone a widget to another dashboard
===================================

The Portal provides another convenient way to clone widgets between dashboards. To clone a specific widget, click the :guilabel:`Copy` icon on the widget header.

|clone-widget-button|

For certain widget types, such as the Welcome widget, you can find the :guilabel:`Clone` option in the context menu.

|clone-widget-menu-option|

After selecting the :guilabel:`Clone` option, the :guilabel:`Clone widget` dialog will appear. Choose the target dashboard where you want to copy the widget, then click the :guilabel:`Clone` button to complete the process.

|clone-widget-dialog|

.. include:: ../includes/_common-icon.rst

.. |dash-board| image:: ../../screenshots/new-dashboard/dashboard.png
   :alt: Dashboard screen
.. |widget-filter| image:: ../../screenshots/new-dashboard/widget-filter.png
   :alt: Dashboard widget's filters panel
.. |widget-info| image:: ../../screenshots/new-dashboard/widget-info.png
   :alt: Dashboard widget's info panel
.. |case-export-excel| image:: ../../screenshots/new-dashboard/case-export-excel.png
   :alt: Dashboard case widget: export to Excel feature
.. |task-export-excel| image:: ../../screenshots/new-dashboard/task-export-excel.png
   :alt: Dashboard task widget: export to Excel feature
.. |edit-widget| image:: ../../screenshots/new-dashboard/edit-widget.png
   :alt: Dashboard configuration screen
.. |add-widget| image:: ../../screenshots/new-dashboard/add-widget.png
   :alt: Add new widget dialog
.. |task-list-widget| image:: ../../screenshots/new-dashboard/task-list-widget.png
   :alt: Dashboard task widget's configuration dialog 
.. |task-list-widget-configuration| image:: ../../screenshots/new-dashboard/task-list-widget-configuration.png
   :alt: Dashboard task widget's configuration panel
.. |task-list-widget-table-configuration| image:: ../../screenshots/new-dashboard/task-list-widget-table-configuration.png
   :alt: Dashboard task widget's table configuration
.. |task-column-field-type-configuration| image:: ../../screenshots/new-dashboard/task-column-field-type-configuration.png
   :alt: Dashboard task widget's column management
.. |task-widget-complex-filter-configuration| image:: ../../screenshots/new-dashboard/task-widget-complex-filter-configuration.png
   :alt: Dashboard task widget's complex filter configuration
.. |case-list-widget| image:: ../../screenshots/new-dashboard/case-list-widget.png
   :alt: Dashboard case widget's configuration dialog
.. |case-list-widget-configuration| image:: ../../screenshots/new-dashboard/case-list-widget-configuration.png
   :alt: Dashboard case widget's configuration panel
.. |case-list-widget-table-configuration| image:: ../../screenshots/new-dashboard/case-list-widget-table-configuration.png
   :alt: Dashboard case widget's table configuration
.. |process-widget-modes| image:: ../../screenshots/new-dashboard/process-widget-modes.png
   :alt: Dashboard process widget mode selection
.. |process-widget-combined-mode| image:: ../../screenshots/new-dashboard/process-widget-combined-mode.png
   :alt: Dashboard process widget's combined mode
.. |process-widget-compact-mode| image:: ../../screenshots/new-dashboard/process-widget-compact-mode.png
   :alt: Dashboard process widget's compact mode
.. |process-widget-full-mode| image:: ../../screenshots/new-dashboard/process-widget-full-mode.png
   :alt: Dashboard process widget's full mode
.. |process-widget-image-mode| image:: ../../screenshots/new-dashboard/process-widget-image-mode.png
   :alt: Dashboard process widget's image mode
.. |process-viewer-widget| image:: ../../screenshots/new-dashboard/process-viewer-widget.png
   :alt: Dashboard process viewer widget
.. |process-viewer-widget-configuration| image:: ../../screenshots/new-dashboard/process-viewer-widget-configuration.png
   :alt: Dashboard process viewer widget's configuration dialog
.. |process-quick-search-textbox| image:: ../../screenshots/new-dashboard/process-quick-search-textbox.png
   :alt: Quick search feature of dashboard process widget
.. |welcome-widget-configuration| image:: ../../screenshots/new-dashboard/welcome-widget-configuration.png
   :alt: Dashboard welcome widget's configuration dialog
.. |news-feed-widget-configuration| image:: ../../screenshots/new-dashboard/news-feed-widget-configuration.png
   :alt: Dashboard news feed widget's configuration dialog
.. |news-feed-widget| image:: ../../screenshots/new-dashboard/news-feed-widget.png
   :alt: Dashboard news feed widget
.. |news-feed-widget-manage-content| image:: ../../screenshots/new-dashboard/news-feed-widget-manage-content.png
   :alt: Dashboard news feed widget's Manage news dialog
.. |news-feed-widget-overlay-panel| image:: ../../screenshots/new-dashboard/news-feed-widget-overlay-panel.png
   :alt: Dashboard external page widget's overlay dialog
.. |external-page-widget-configuration| image:: ../../screenshots/dashboard/external-page-widget-configuration.png
   :alt: Dashboard external page widget's configuration dialog
.. |add-statistic-widget| image:: ../../screenshots/new-dashboard/add-client-statistic-widget.png
   :alt: Add new dashboard client statistic widget
.. |notification-widget-configuration| image:: ../../screenshots/new-dashboard/notification-widget-configuration.png
   :alt: Dashboard notification widget's configuration dialog
.. |notification-widget| image:: ../../screenshots/new-dashboard/notification-widget.png
   :alt: Dashboard notification widget
.. |task-quick-search-textbox| image:: ../../screenshots/new-dashboard/task-quick-search-textbox.png
   :alt: Dashboard task widget's quick search
.. |case-quick-search-textbox| image:: ../../screenshots/new-dashboard/case-quick-search-textbox.png
   :alt: Dashboard case widget's quick search
.. |task-list-widget-edit-mode| image:: ../../screenshots/new-dashboard/task-list-widget-edit-mode.png
   :alt: Dashboard task widget in the Edit mode
.. |case-list-widget-edit-mode| image:: ../../screenshots/new-dashboard/case-list-widget-edit-mode.png
   :alt: Dashboard case widget in the Edit mode
.. |clone-widget-button| image:: ../../screenshots/dashboard-configuration/clone-widget-button.png
   :alt: Clone widget button placed on the header of a widget in Edit mode. After clicking this button, the Clone widget dialog will appear.
.. |clone-widget-menu-option| image:: ../../screenshots/dashboard-configuration/clone-widget-menu-option.png
   :alt: Clone widget menu a widget in Edit mode. After clicking this menu, the Clone widget dialog will appear.
.. |clone-widget-dialog| image:: ../../screenshots/dashboard-configuration/clone-widget-dialog.png
   :alt: Clone widget dialog in Edit mode.
.. |clone-widget-from-button| image:: ../../screenshots/dashboard-configuration/clone-widget-from-button.png
   :alt: "Clone widget from" button placed inside the "Add new widget" dialog. The "Clone widget from" dialog will appear after clicking this button.
.. |clone-widget-from-dialog| image:: ../../screenshots/dashboard-configuration/clone-widget-from-dialog.png
   :alt: "Clone widget from" dialog.