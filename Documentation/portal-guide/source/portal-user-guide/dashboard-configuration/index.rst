.. _dashboard-configuration:

Dashboard Configuration
***********************

.. important::
   **Access Requirements**: Only users with the :bdg-ref-warning:`🔑DashboardWriteOwn <DashboardWriteOwn>` 
   or :bdg-ref-warning:`🔑DashboardWritePublic <DashboardWritePublic>` permission can see and access 
   the :guilabel:`Dashboard Configuration` menu item. These permissions allow you to add, edit, reorder, 
   show, and hide private or public dashboards.

Dashboard Configuration provides centralized management of your personal and organization-wide dashboards. 
Access it through the :guilabel:`Dashboard Configuration` user menu item.

|dashboard-configuration|

Overview
========

The Dashboard Configuration page has two tabs:

.. table::
   :widths: 25 35 40

   +-------------------------+--------------------------------+--------------------------------------------+
   | Tab                     | Required Permission            | Purpose                                    |
   +=========================+================================+============================================+
   | **Private Dashboards**  | :bdg-ref-warning:`🔑           | Manage your personal dashboards            |
   |                         | DashboardWriteOwn              | visible only to you                        |
   |                         | <DashboardWriteOwn>`           |                                            |
   +-------------------------+--------------------------------+--------------------------------------------+
   | **Public Dashboards**   | :bdg-ref-warning:`🔑           | Manage shared dashboards visible           |
   |                         | DashboardWritePublic           | to users with specific permissions         |
   |                         | <DashboardWritePublic>`        |                                            |
   +-------------------------+--------------------------------+--------------------------------------------+

|dashboard-configuration-page|

Permission Reference
====================

Different actions require specific permissions:

.. table::
   :widths: 40 60

   +---------------------------------------+--------------------------------------------------------+
   | Action                                | Required Permission                                    |
   +=======================================+========================================================+
   | **Import private dashboard**          | :bdg-ref-warning:`🔑DashboardImportOwn                 |
   |                                       | <DashboardImportOwn>`                                  |
   +---------------------------------------+--------------------------------------------------------+
   | **Export private dashboard**          | :bdg-ref-warning:`🔑DashboardExportOwn                 |
   |                                       | <DashboardExportOwn>`                                  |
   +---------------------------------------+--------------------------------------------------------+
   | **Import public dashboard**           | :bdg-ref-warning:`🔑DashboardImportPublic              |
   |                                       | <DashboardImportPublic>`                               |
   +---------------------------------------+--------------------------------------------------------+
   | **Export public dashboard**           | :bdg-ref-warning:`🔑DashboardExportPublic              |
   |                                       | <DashboardExportPublic>`                               |
   +---------------------------------------+--------------------------------------------------------+
   | **Share public dashboard link**       | :bdg-ref-warning:`🔑ShareDashboardLink                 |
   |                                       | <ShareDashboardLink>`                                  |
   +---------------------------------------+--------------------------------------------------------+

Private dashboards configuration
================================

The tab :guilabel:`Private dashboards` allows you to add or edit your private dashboards, and reorder all dashboards visible to you.

|private-dashboard-configuration|

.. _howto-add-private-dashboard:

HowTo: add private dashboard
----------------------------

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
-----------------------------

:guilabel:`Default Template`, :guilabel:`Two Task list dashboard` template and :guilabel:`Accessiblity dashboard` template. Please see the image below for more details.

|dashboard-templates|

.. hint::
   The list of private dashboards shows only the private dashboards that have been created by you.

HowTo: import private dashboards
--------------------------------

#. Select :guilabel:`Add new Dashboard` button.

#. Select |import-icon| Import.

#. The :guilabel:`Import Private Dashboard` dialog is opened.

#. Drag and drop, or click on the :guilabel:`Upload one here` link to select the JSON file containing the dashboard you want to import.

#. Edit the mandatory title and the optional description for the private dashboard.

#. Import the dashboard by selecting :guilabel:`Create Dashboard` button.

#. Configuration your private dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Back on the dashboard list, you see that a new private dashboard has been added.

|import-private-dashboard-dialog|


HowTo: edit private dashboards
------------------------------

The :guilabel:`Edit private dashboard` section shows your private dashboards in a table with the following columns:

- **Dashboard title**: The name of the dashboard
- **Dashboard description**: Optional description text
- **Actions**: Available operations for each dashboard

**Available Actions:**

Click on |actions-menu-icon| to open the :guilabel:`Actions` menu with these options:

- |edit-icon| **Edit**: Modify dashboard name and description
- |settings-icon| **Configuration**: Configure dashboard widgets (see: :ref:`Dashboards <new-dashboard>`)
- |download-icon| **Export**: Download dashboard as JSON file
- |trash-icon| **Delete**: Remove the private dashboard

|edit-private-dashboards|

.. _howto-reorder-your-dashboards:

HowTo: reorder private dashboards
---------------------------------

You can rearrange dashboards by dragging and dropping |reorder-dashboard-icon| icon.

|reorder-your-dashboards|

.. note::
   - Once created, your personal sort order is retained.
   - Once you have created a personal sort order, you can rearrange the order, but you cannot delete your personal sort order.

.. hint::
   If you create new dashboards, they are automatically added to the end of your personal sort order.

Public dashboards configuration
================================

The tab :guilabel:`Public dashboards` allows you to add, edit, and reorder public dashboards.

|public-dashboard-configuration|

.. _howto-add-public-dashboard:

HowTo: add public dashboard
---------------------------

#. Select :guilabel:`Add New Dashboard` button.

#. Select one of the available templates to add  (see :ref:`Available dashboard templates <public-available-dashboard-template>`).

#. The :guilabel:`Create new public dashboard` dialog is opened.

#. Enter the mandatory title, permissions to see the public dashboard and the optional description for the public dashboard.
   If the `Top Menu Item` checkbox is checked, the dashboard appears as a top-level item in the navigation bar. 
   If unchecked, it appears as a sub-item under `Dashboard` menu item.

#. Create the public dashboard by selecting |add-icon| Create dashboard.

#. Configuration public dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Go back to the dashboards list to see that a new public dashboard has been added.

|create-public-dashboard-dialog|

To set multi languages for the dashboard title, see at :ref:`portal-multi-language`:

.. _public-available-dashboard-template:

Available dashboard templates 
-----------------------------

:guilabel:`Default Template`, :guilabel:`Two Task list dashboard` template and :guilabel:`Accessiblity dashboard` template.

|dashboard-templates|

.. hint::
   Depending on dashboard roles, you might see other public dashboards in the dashboards list.
    
.. _howto-import-your-public-dashboards:

HowTo: import public dashboard
------------------------------

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

HowTo: edit public dashboards
-----------------------------

The :guilabel:`Edit public dashboards` section shows you the public dashboards in a table with the following columns:

- **Dashboard title**: The name of the dashboard
- **Dashboard permissions**: Roles/permissions that can access the dashboard
- **Dashboard description**: Optional description text
- **Display as top menu or not**: Whether it appears in the top navigation bar

**Available Actions:**

Click on |actions-menu-icon| to open the :guilabel:`Actions` menu with these options:

- |edit-icon| **Edit**: Modify dashboard name, permissions, and description
- |settings-icon| **Configuration**: Configure dashboard widgets (see: :ref:`Dashboards <new-dashboard>`)
- |download-icon| **Export**: Download dashboard as JSON file
- |share-icon| **Share**: Generate a shareable link to the dashboard
- |trash-icon| **Delete**: Remove the public dashboard

|edit-public-dashboards|


HowTo: share public dashboards
--------------------------------

To generate a public dashboard link, simply click on the |share-icon| Share in the :guilabel:`Actions` menu of the dashboard.

|share-public-dashboards|

.. tip::
   To share the dashboard without the menu frame, add the parameter ``openWithoutMenu=true`` to the URL. However, please be aware that this option is only for embedding purposes.

HowTo: reorder public dashboards
--------------------------------

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
