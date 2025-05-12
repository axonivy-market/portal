.. _dashboard-configuration:

Dashboard Configuration
***********************

.. hint::
   Only users who have the permission :bdg-ref-warning:`🔑DashboardWriteOwn <DashboardWriteOwn>` or :bdg-ref-warning:`🔑DashboardWritePublic <DashboardWritePublic>` can see and access
   the :guilabel:`Dashboard Configuration` user menu item. They can add, edit, reorder, show and hide the private or public dashboards.

Select the :guilabel:`Dashboard Configuration` user menu item.

|dashboard-configuration|

The :guilabel:`Dashboard Configuration` page provides you with private and public dashboard configuration tabs depending on the permissions
you hold in the application. The usage will be explained in the HowTo’s further down in this chapter.

|dashboard-configuration-page|

Private dashboards configuration
--------------------------------

The tab :guilabel:`Private dashboards` is only available for users who have the permission :bdg-ref-warning:`🔑DashboardWriteOwn <DashboardWriteOwn>`. Here you can
add or edit your private dashboards, and reorder all dashboards visible to you.

|private-dashboard-configuration|

.. _howto-add-private-dashboard:

How to: add private dashboard
=============================

#. Select :guilabel:`Add New Dashboard` button

#. Select one of the available templates to add  (see :ref:`Available dashboard templates <private-available-dashboard-template>`).

#. The :guilabel:`Create new private dashboard` dialog is opened.

#. Enter the mandatory title and the optional description for the private dashboard.

#. Create your private dashboard by selecting |add-icon| Create dashboard.

#. Configure your private dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Back on the dashboards list, you see that a new private dashboard has been added.

|create-private-dashboard-dialog|

To set multi languages for the dashboard title, see at :ref:`portal-multi-language`:

.. _private-available-dashboard-template:

Available dashboard templates 
=============================

:guilabel:`Default Template`, :guilabel:`Two Task list dashboard` template and :guilabel:`Accessiblity dashboard` template. Please see the image below for more details.

|dashboard-templates|

.. hint::
   The list of private dashboards shows only the private dashboards that have been created by you.

How to: import private dashboards
=================================

#. Select :guilabel:`Add new Dashboard` button.

#. Select |import-icon| Import.

#. The :guilabel:`Import Private Dashboard` dialog is opened.

#. Drag and drop, or click on the :guilabel:`Upload one here` link to select the JSON file containing the dashboard you want to import.

#. Edit the mandatory title and the optional description for the private dashboard.

#. Import the dashboard by selecting :guilabel:`Create Dashboard` button.

#. Configuration your private dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Back on the dashboard list, you see that a new private dashboard has been added.

|import-private-dashboard-dialog|

.. note::

   Grant permission :bdg-ref-warning:`🔑DashboardImportOwn <DashboardImportOwn>` to use this feature.


How to: edit private dashboards
===============================
The :guilabel:`Edit private dashboard` section shows your private dashboards.
A table of private dashboards is shown with the following information:

#. Dashboard title

#. Dashboard description

#. Actions: click on |actions-menu-icon| to get :guilabel:`Actions` menu for further actions: |edit-icon| Edit (name, description), |settings-icon| Configuration
   (see: :ref:`Dashboards <new-dashboard>` ), |download-icon| Export dashboard, |trash-icon| Delete a private dashboard.

You can edit your private dashboards by selecting |edit-icon| Edit.

.. note::

   Grant permission :bdg-ref-warning:`🔑DashboardExportOwn <DashboardExportOwn>` to enable the export function for personal dashboards for a user.

|edit-private-dashboards|

.. _howto-reorder-your-dashboards:

How to: reorder private dashboards
==================================

You can rearrange dashboards by dragging and dropping |reorder-dashboard-icon| icon.

|reorder-your-dashboards|

.. note::
   - Once created, your personal sort order is retained.
   - Once you have created a personal sort order, you can rearrange the order, but you cannot delete your personal sort order.

.. hint::
   If you create new dashboards, they are automatically added to the end of your personal sort order.

Public dashboards configuration
-------------------------------

Grant permission :bdg-ref-warning:`🔑DashboardWritePublic <DashboardWritePublic>` to make tab :guilabel:`Public dashboards` visible for a user. Here you can
add, edit, and reorder public dashboards.

|public-dashboard-configuration|

.. _howto-add-public-dashboard:

How to: add public dashboard
============================

#. Select :guilabel:`Add New Dashboard` button.

#. Select one of the available templates to add  (see :ref:`Available dashboard templates <public-available-dashboard-template>`).

#. The :guilabel:`Create new public dashboard` dialog is opened.

#. Enter the mandatory title, permissions to see the public dashboard and the optional description for the public dashboard.

#. Select one of the display types for your dashboard. There are three types: ``TOP MENU``, ``SUB MENU``, and ``HIDDEN``. The default value is ``SUB MENU``, which places your dashboard inside the `Dashboard` menu item. 
   If the ``Top Menu`` is selected, the dashboard appears as a top-level item in the navigation bar. The option ``HIDDEN`` will hide the dashboard from the menu.

#. Create the public dashboard by selecting |add-icon| Create dashboard.

#. Configuration public dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Go back to the dashboards list to see that a new public dashboard has been added.

|create-public-dashboard-dialog|

To set multi languages for the dashboard title, see at :ref:`portal-multi-language`:

.. _public-available-dashboard-template:

Available dashboard templates 
=============================

:guilabel:`Default Template`, :guilabel:`Two Task list dashboard` template and :guilabel:`Accessiblity dashboard` template.

|dashboard-templates|

.. hint::
   Depending on dashboard roles, you might see other public dashboards in the dashboards list.
    
.. _howto-import-your-public-dashboards:

How to: import public dashboard
===============================

#. Select :guilabel:`Add New Dashboard` button.

#. Select |import-icon| Import.

#. The :guilabel:`Import Public Dashboard` dialog is opened.

#. Drag and drop, or click on the :guilabel:`Upload one here` link to select the JSON file containing the dashboard you want to import.

#. Edit the mandatory title, permissions to see the public dashboard and the optional description for the public dashboard.
   If the `Top Menu Item` checkbox is checked, the dashboard appears as a top-level item in the navigation bar. 
   If unchecked, it appears as a sub-item under `Dashboard` menu item.

#. Import the dashboard by selecting :guilabel:`Create Dashboard` button.

#. Configuration public dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Go back to the dashboard list to see that a new public dashboard has been added.

|import-public-dashboard-dialog|

.. note::

   Grant permission :bdg-ref-warning:`🔑DashboardImportPublic <DashboardImportPublic>` to enable public dashboard import for a user.

How to: edit public dashboards
==============================
The :guilabel:`Edit public dashboards` section shows you the public dashboards.
A table of public dashboards is shown with the following information:

#. Dashboard title

#. Dashboard permissions

#. Dashboard description

#. Display as top menu or not

#. Actions: click on |actions-menu-icon| to get :guilabel:`Actions` menu for further actions: |edit-icon| Edit (name, description), |settings-icon| Configuration
   (see: :ref:`Dashboards <new-dashboard>` ), |download-icon| Export dashboard, |share-icon| Share,
   and |trash-icon| Delete a public dashboard.

You can edit public dashboards by selecting |edit-icon| Edit. 

.. note::

   Grant permission :bdg-ref-warning:`🔑DashboardExportPublic <DashboardExportPublic>` to enable export of public dashboards for users.

|edit-public-dashboards|


How to: share public dashboards
===============================

To generate a public dashboard link, simply click on the |share-icon| Share in the :guilabel:`Actions` menu of the dashboard.

|share-public-dashboards|

.. note::
   To share the dashboard without the menu frame, add the parameter ``openWithoutMenu=true`` to the URL. However, please be aware that this option is only for embedding purposes.
   Grant permission :bdg-ref-warning:`🔑ShareDashboardLink <ShareDashboardLink>` to allow a user to share dashboard links.

How to: reorder public dashboards
=================================

You can rearrange dashboards by dragging and dropping |reorder-dashboard-icon| icon.

|reorder-public-dashboards|

.. include:: ../includes/_common-icon.rst

.. |dashboard-configuration| image:: ../../screenshots/settings/dashboard-configuration.png
   :alt: Dashboard configuration menu
.. |dashboard-configuration-page| image:: ../../screenshots/dashboard-configuration/dashboard-configuration-page.png
   :alt: Dashboard configuration screen
.. |private-dashboard-configuration| image:: ../../screenshots/dashboard-configuration/private-dashboard-configuration.png
   :alt: Private dashboard configuration
.. |create-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-private-dashboard-dialog.png
   :alt: Create private dashboard dialog
.. |edit-private-dashboards| image:: ../../screenshots/dashboard-configuration/edit-private-dashboards.png
   :alt: Edit private dashboard dialog
.. |reorder-your-dashboards| image:: ../../screenshots/dashboard-configuration/reorder-your-dashboards.png
   :alt: Reorder private dashboards
.. |public-dashboard-configuration| image:: ../../screenshots/dashboard-configuration/public-dashboard-configuration.png
   :alt: Public dashboard configuration
.. |create-public-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-public-dashboard-dialog.png
   :alt: Create public dashboard dialog
.. |edit-public-dashboards| image:: ../../screenshots/dashboard-configuration/edit-public-dashboards.png
   :alt: Edit public dashboard dialog
.. |reorder-public-dashboards| image:: ../../screenshots/dashboard-configuration/reorder-public-dashboards.png
   :alt: Reorder public dashboards
.. |dashboard-templates| image:: ../../screenshots/dashboard-configuration/dashboard-templates.png
   :alt: Dashboard template selection dialog
.. |import-public-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/import-public-dashboard-dialog.png
   :alt: Import public dashboard dialog
.. |import-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/import-private-dashboard-dialog.png
   :alt: Import private dashboard dialog
.. |share-public-dashboards| image:: ../../screenshots/dashboard-configuration/share-dashboard-dialog.png
   :alt: Share dashboard dialog
