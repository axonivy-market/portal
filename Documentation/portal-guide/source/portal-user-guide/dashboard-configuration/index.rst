.. _dashboard-configuration:

Portal Configuration
********************

.. important::
   **Access Requirements**: Only users with the :ref:`🔑DashboardWriteOwn <DashboardWriteOwn>` or :ref:`🔑DashboardWritePublic <DashboardWritePublic>` permission, or the ``AXONIVY_PORTAL_ADMIN`` role, can see and access the :guilabel:`Portal Configuration` menu item. These permissions allow you to add, edit, reorder, show, and hide private or public dashboards, and to configure sidebar navigation.

Portal Configuration provides centralized management of your personal and organization-wide dashboards, as well as the sidebar navigation menu.
Access it through the :guilabel:`Portal Configuration` user menu item.

|portal-configuration|

Overview
========

The Portal Configuration page has three tabs:

.. table::
   :widths: 20 40 40

   +-------------------------+---------------------------------------------------+--------------------------------------------+
   | Tab                     | Required Permission                               | Purpose                                    |
   +=========================+===================================================+============================================+
   | **Private Dashboards**  | :ref:`🔑DashboardWriteOwn                         | Manage your personal dashboards            |
   |                         | <DashboardWriteOwn>`                              | visible only to you                        |
   +-------------------------+---------------------------------------------------+--------------------------------------------+
   | **Public Dashboards**   | :ref:`🔑DashboardWritePublic                      | Manage shared dashboards visible           |
   |                         | <DashboardWritePublic>`                           | to users with specific permissions         |
   +-------------------------+---------------------------------------------------+--------------------------------------------+
   | **Sidebar Navigation**  | ``AXONIVY_PORTAL_ADMIN`` role                     | Configure sidebar menu items and           |
   |                         |                                                   | sidebar behaviour (admin only)             |
   +-------------------------+---------------------------------------------------+--------------------------------------------+

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

:guilabel:`Default Template`, :guilabel:`Two Task list dashboard` template, :guilabel:`Accessibility dashboard` template, :guilabel:`Full Task list dashboard` template and :guilabel:`Full Case list dashboard` template. Please see the image below for more details.

|dashboard-templates|

.. hint::
   The list of private dashboards shows only the private dashboards that have been created by you.

HowTo: import private dashboards
--------------------------------

.. note::
   Requires :ref:`🔑DashboardImportOwn <DashboardImportOwn>` permission.

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
- |download-icon| **Export**: Download dashboard as JSON file (requires :ref:`🔑DashboardExportOwn <DashboardExportOwn>` permission)
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
Public dashboards are organized into three sections based on their **Display Type** setting:

- **Top Menu** — dashboards appear as top-level items in the sidebar navigation
- **Submenu** — dashboards are grouped under the Dashboard icon in the sidebar
- **Hidden** — dashboards are not shown in the sidebar

|public-dashboard-configuration|

.. _howto-add-public-dashboard:

HowTo: add public dashboard
---------------------------

#. Select :guilabel:`Add New Dashboard` button.

#. Select one of the available templates to add  (see :ref:`Available dashboard templates <public-available-dashboard-template>`).

#. The :guilabel:`Create new public dashboard` dialog is opened.

#. Enter the mandatory title, permissions to see the public dashboard and the optional description for the public dashboard.
   Select a **Display Type** to control where the dashboard appears in the sidebar:

   - **Submenu** — the dashboard is grouped under the Dashboard icon in the sidebar
   - **Top Menu** — the dashboard appears as a top-level item in the sidebar navigation
   - **Hidden** — the dashboard is not shown in the sidebar

#. Create the public dashboard by selecting |add-icon| Create dashboard.

#. Configuration public dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Go back to the dashboards list to see that a new public dashboard has been added.

|create-public-dashboard-dialog|

To set multi languages for the dashboard title, see at :ref:`portal-multi-language`:

.. _public-available-dashboard-template:

Available dashboard templates 
-----------------------------

:guilabel:`Default Template`, :guilabel:`Two Task list dashboard` template, :guilabel:`Accessibility dashboard` template, :guilabel:`Full Task list dashboard` template and :guilabel:`Full Case list dashboard` template. Please see the image below for more details.

|dashboard-templates|

.. hint::
   Depending on dashboard roles, you might see other public dashboards in the dashboards list.
    
.. _howto-import-your-public-dashboards:

HowTo: import public dashboard
------------------------------

.. note::
   Requires :ref:`🔑DashboardImportPublic <DashboardImportPublic>` permission.

#. Select :guilabel:`Add New Dashboard` button.

#. Select |import-icon| Import.

#. The :guilabel:`Import Public Dashboard` dialog is opened.

#. Drag and drop, or click on the :guilabel:`Upload one here` link to select the JSON file containing the dashboard you want to import.

#. Edit the mandatory title, permissions to see the public dashboard and the optional description for the public dashboard.
   Select a **Display Type** to control where the dashboard appears in the sidebar:

   - **Submenu** — the dashboard is grouped under the Dashboard icon in the sidebar
   - **Top Menu** — the dashboard appears as a top-level item in the sidebar navigation
   - **Hidden** — the dashboard is not shown in the sidebar

#. Import the dashboard by selecting :guilabel:`Create Dashboard` button.

#. Configuration public dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Go back to the dashboard list to see that a new public dashboard has been added.

|import-public-dashboard-dialog|

HowTo: edit public dashboards
-----------------------------

The :guilabel:`Edit public dashboards` section shows you the public dashboards organized into three sections: **Top Menu**, **Submenu**, and **Hidden**. Each section displays a table with the following columns:

- **Dashboard title**: The name of the dashboard
- **Dashboard permissions**: Roles/permissions that can access the dashboard
- **Dashboard description**: Optional description text
- **Display Type**: Where the dashboard appears in the sidebar (Top Menu, Submenu, or Hidden)

**Available Actions:**

Click on |actions-menu-icon| to open the :guilabel:`Actions` menu with these options:

- |edit-icon| **Edit**: Modify dashboard name, permissions, description, and Display Type
- |settings-icon| **Configuration**: Configure dashboard widgets (see: :ref:`Dashboards <new-dashboard>`)
- |download-icon| **Export**: Download dashboard as JSON file (requires :ref:`🔑DashboardExportPublic <DashboardExportPublic>` permission)
- |share-icon| **Share**: Generate a shareable link to the dashboard (requires :ref:`🔑ShareDashboardLink <ShareDashboardLink>` permission)
- |trash-icon| **Delete**: Remove the public dashboard

|edit-public-dashboards|

.. note::
   To move a dashboard between sections, open the Edit dialog and change the **Display Type** field.

.. hint::
   When exporting a dashboard that references other dashboards, you will be prompted to include the referenced dashboards in the export.


HowTo: share public dashboards
--------------------------------

.. note::
   Requires :ref:`🔑ShareDashboardLink <ShareDashboardLink>` permission.

To generate a public dashboard link, simply click on the |share-icon| Share in the :guilabel:`Actions` menu of the dashboard.

|share-public-dashboards|

.. tip::
   To share the dashboard without the menu frame, add the parameter ``openWithoutMenu=true`` to the URL. However, please be aware that this option is only for embedding purposes.

HowTo: reorder public dashboards
--------------------------------

You can rearrange dashboards within the **Submenu** section by dragging and dropping |reorder-dashboard-icon| icon.

|reorder-public-dashboards|

.. note::
   Drag-and-drop reordering is only available within the **Submenu** section. Dashboards in the **Top Menu** and **Hidden** sections cannot be reordered by drag and drop.

Sidebar Navigation configuration
==================================

.. important::
   **Admin Access Required**: The :guilabel:`Sidebar Navigation` tab is only accessible to users with the ``AXONIVY_PORTAL_ADMIN`` role.

The tab :guilabel:`Sidebar Navigation` allows portal administrators to manage the sidebar menu items and configure sidebar behaviour for all users.

|sidebar-navigation-configuration|

Menu Items
----------

The menu items table displays all sidebar navigation entries with the following columns:

- **Label**: The display title of the menu item
- **Links To**: The URL or dashboard the item links to
- **Type**: The kind of item (Standard, External Link, Main Dashboard, Static Page, etc.)
- **Actions**: Edit and Delete operations accessible via the overflow menu
- **Reorder**: Drag handle (|reorder-dashboard-icon|) to reorder items

.. note::
   Built-in items such as **Dashboard** and **Processes** are Standard items and cannot be edited or deleted. The Actions menu is not available for these items.

HowTo: add a sidebar menu item
-------------------------------

#. Click :guilabel:`Add Item` to open the :guilabel:`Add Menu Item` dialog.

   |add-menu-item-dialog|

#. Select the item type:

   - **Main Dashboard** — links to a portal dashboard
   - **External Link** — links to any URL; can be configured to open in a new tab
   - **Static Page** — links to a static portal page

   .. note::
      The item type cannot be changed after the menu item has been created.

#. Enter a title for the item. To provide translations, use the multi-language input (see :ref:`portal-multi-language`).

#. Select an icon to represent the item in the sidebar.

#. Set permissions to control which users can see this menu item.

#. Click :guilabel:`Save` to add the item to the sidebar.

HowTo: edit a sidebar menu item
--------------------------------

#. In the menu items table, click |actions-menu-icon| next to the item you want to edit.

#. Select |edit-icon| **Edit**.

#. Update the title, icon, link target, or permissions as needed.

#. Click :guilabel:`Save` to apply the changes.

HowTo: delete a sidebar menu item
----------------------------------

#. In the menu items table, click |actions-menu-icon| next to the item you want to remove.

#. Select |trash-icon| **Delete**.

#. Confirm the deletion in the confirmation dialog.

.. note::
   Deletion is permanent. Standard (built-in) items cannot be deleted.

HowTo: reorder sidebar menu items
-----------------------------------

You can rearrange sidebar menu items by dragging and dropping |reorder-dashboard-icon| icon to the desired position.

Sidebar Settings
----------------

Below the menu items table, the **Sidebar Settings** card provides controls that affect the sidebar behaviour for all users.

|sidebar-settings-panel|

.. table::
   :widths: 25 75

   +---------------------------+--------------------------------------------------------------+
   | Setting                   | Description                                                  |
   +===========================+==============================================================+
   | **Disable Sidebar**       | When enabled, hides the entire sidebar for all users.        |
   +---------------------------+--------------------------------------------------------------+
   | **Sidebar Behaviour**     | Controls how the sidebar opens. Only visible when the        |
   |                           | sidebar is not disabled. Options:                            |
   |                           |                                                              |
   |                           | - **Hover** — sidebar opens on mouse-over                    |
   |                           | - **Click** — sidebar requires a click to open               |
   |                           | - **Stick** — sidebar is always visible (pinned)             |
   +---------------------------+--------------------------------------------------------------+

.. note::
   When either Sidebar Setting is changed, the page automatically reloads to apply the change.

Permission Reference
====================

Different Portal Configuration actions require specific permissions:

.. table::
   :widths: 35 65

   +---------------------------------------+------------------------------------------------------------------------------+
   | Action                                | Required Permission                                                          |
   +=======================================+==============================================================================+
   | **Import private dashboard**          | :ref:`🔑DashboardImportOwn <DashboardImportOwn>`                             |
   +---------------------------------------+------------------------------------------------------------------------------+
   | **Export private dashboard**          | :ref:`🔑DashboardExportOwn <DashboardExportOwn>`                             |
   +---------------------------------------+------------------------------------------------------------------------------+
   | **Import public dashboard**           | :ref:`🔑DashboardImportPublic <DashboardImportPublic>`                       |
   +---------------------------------------+------------------------------------------------------------------------------+
   | **Export public dashboard**           | :ref:`🔑DashboardExportPublic <DashboardExportPublic>`                       |
   +---------------------------------------+------------------------------------------------------------------------------+
   | **Share public dashboard link**       | :ref:`🔑ShareDashboardLink <ShareDashboardLink>`                             |
   +---------------------------------------+------------------------------------------------------------------------------+
   | **Manage sidebar navigation**         | ``AXONIVY_PORTAL_ADMIN`` role                                                |
   +---------------------------------------+------------------------------------------------------------------------------+

.. include:: ../includes/_common-icon.rst

.. |portal-configuration| image:: ../../screenshots/settings/dashboard-configuration.png
   :alt: Portal configuration menu
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
.. |sidebar-navigation-configuration| image:: ../../screenshots/dashboard-configuration/sidebar-navigation-configuration.png
   :alt: Sidebar navigation configuration tab
.. |add-menu-item-dialog| image:: ../../screenshots/dashboard-configuration/add-menu-item-dialog.png
   :alt: Add menu item dialog
.. |sidebar-settings-panel| image:: ../../screenshots/dashboard-configuration/sidebar-settings-panel.png
   :alt: Sidebar settings panel
