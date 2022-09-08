.. _new-dashboard:

Dashboard
*********

Introduction
------------

The Axon Ivy Portal Dashboard is the first page you encounter after
successful login. You can always get back to the dashboard using the 
**Dashboard** link in the Axon Ivy Portal navigation menu.
Alternatively, you can click on the logo image at the top left of the header.

The dashboard itself contains a set of widgets arranged on the page,
according to the defined standards in your company. 

|dash-board|

The following default widgets are available:

#. **Task list widget**: This widget displays relevant task information according to defined settings.

#. **Case list widget**: This widget displays relevant case information according to defined settings.

#. **Process list widget**: This widget displays available process starts. You can choose between different formats.

#. **Process viewer widget**: This widget provides a visual representation of the process flow.

   |process-viewer-widget|

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

Task list widget
================

Adding a task list widget will give you total flexibility about what you want to
see and how.

|task-list-widget|

**Configuration panel**

On the left side, define the widget name, establish the main configuration for
your widget, and get a preview of it by clicking on the preview button.

|task-list-widget-configuration|

**Table configuration panel**

Edit the default sort order of the table rows by clicking on the column headers
and define the order of the columns as well as the displayed set of columns by
clicking on the link ``Manage Columns`` at the top right. You get the following
dialog:

|task-list-widget-table-configuration|

In the column management panel, you will find two sections:

   #. Add field section: add any available field to your widget table
   #. Columns section: configure the widget table itself as follows:
 
      - Enable visibility
      - Reorder the columns by drag and drop using the arrows |move-expand-vertical|
      - Remove columns

Case list widget
================

Adding a case list widget will give you total flexibility about what you want to
see and how.

|case-list-widget|

**Configuration panel**

On the left side, you can define the widget name, establish the main
configuration for your widget, and get a preview of it by clicking on the
preview button.

|case-list-widget-configuration|

**Table configuration panel**

This panel allows you to:

-  Edit the default sort order of the table rows by clicking on the column headers
-  define the order of the columns 
-  define the displayed set of columns by clicking on the link ``Manage Columns`` at the top right. You get the following dialog:

|case-list-widget-table-configuration|

In the table configuration panel, you find two sections:

   #. Add field section: add any available field to your widget table
   #. Columns section: configure the widget table itself as follows:

      - Enable visibility
      - Reorder the columns by drag and drop using the arrows |move-expand-vertical|
      - Remove columns

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

This widget displays a list of all selected process starts.

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

|process-viewer-widget-configuration|

Welcome widget
==============

You can define a welcome widget with a background image and a welcome text.

|welcome-widget-configuration|

.. include:: ../includes/_common-icon.rst  

.. |dash-board| image:: ../../screenshots/new-dashboard/dashboard.png
.. |widget-filter| image:: ../../screenshots/new-dashboard/widget-filter.png
.. |widget-info| image:: ../../screenshots/new-dashboard/widget-info.png
.. |edit-widget| image:: ../../screenshots/new-dashboard/edit-widget.png
.. |add-widget| image:: ../../screenshots/new-dashboard/add-widget.png

.. |task-list-widget| image:: ../../screenshots/new-dashboard/task-list-widget.png
.. |task-list-widget-configuration| image:: ../../screenshots/new-dashboard/task-list-widget-configuration.png
.. |task-list-widget-table-configuration| image:: ../../screenshots/new-dashboard/task-list-widget-table-configuration.png

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