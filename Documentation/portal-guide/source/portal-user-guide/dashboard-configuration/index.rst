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

#. Select |add-icon| New Dashboard.

#. Select one of the available templates to add  (see Available dashboard templates).

#. The :guilabel:`Create new private dashboard` dialog is opened.

#. Enter the mandatory title and the optional description for the private dashboard.

#. Create your private dashboard by selecting |add-icon| Create dashboard.

#. Configure your private dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Back on the dashboards list, you see that a new private dashboard has been added.

|create-private-dashboard-dialog|

Setting multiple languages for the dashboard title:

|dashboard-multi-language-dialog|

Available dashboard templates:

|dashboard-templates|

.. hint::
   The list of private dashboards shows only the private dashboards that have been created by you.

How to: import private dashboards
=================================

#. Select |add-icon| New Dashboard.

#. Select |import-icon| Import.

#. The :guilabel:`Import Private Dashboard` dialog is opened.

#. Press the Select button and choose the JSON file that contains the dashboard you want to import.

#. Edit the mandatory title and the optional description for the private dashboard.

#. Import the dashboard by selecting the Create Dashboard button.

#. Configuration your private dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Back on the dashboard list, you see that a new private dashboard has been added.

|import-private-dashboard-dialog|

.. note::

   Grant permission :bdg-ref-warning:`🔑DashboardImportOwn <DashboardImportOwn>` to use this feature.


How to: edit private dashboards
===============================

You can edit your private dashboards by selecting |edit-icon| Edit. The :guilabel:`Edit private dashboard` section shows
your private dashboards.

A table of private dashboards is shown with the following information:

#. Dashboard title

#. Dashboard description

#. :guilabel:`Actions` for further actions: |move-expand-vertical| reorder, |edit-icon| edit (name, description), |settings-icon| configure
   (see: :ref:`Dashboards <new-dashboard>` ), |download-icon| export dashboard,
   and |trash-icon| delete a private dashboard.

.. note::

   The Export function only available for users who have the permission :bdg-ref-warning:`🔑DashboardExportOwn <DashboardExportOwn>`.

|edit-private-dashboards|

.. _howto-reorder-your-dashboards:

How to: reorder private dashboards
==================================

You can re-arrange dashboards by drag and drop using |move-expand-vertical| Reorder.

A table of private dashboards is shown with the following information:

#. Dashboard title

#. Dashboard description

#. :guilabel:`Actions` for further actions: |move-expand-vertical| reorder, |edit-icon| edit (name, description), |settings-icon| configure
   (see: :ref:`Dashboards <new-dashboard>` ), |download-icon| export dashboard,
   and |trash-icon| delete a private dashboard.

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

#. Select |add-icon| New Dashboard.

#. Select one of the available templates to add  (see Available dashboard templates).

#. The :guilabel:`Create new public dashboard` dialog is opened.

#. Enter the mandatory title, permissions to see the public dashboard and the optional description for the public dashboard.

#. Create the public dashboard by selecting |add-icon| Create dashboard.

#. Configuration public dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Go back to the dashboards list to see that a new public dashboard has been added.

|create-public-dashboard-dialog|

Setting multiple languages for the dashboard title:

|dashboard-multi-language-dialog|

Available dashboard templates:

|dashboard-templates|

.. hint::
   Depending on dashboard roles, you might see other public dashboards in the dashboards list.

.. _howto-import-your-public-dashboards:

How to: import public dashboard
===============================

#. Select |add-icon| New Dashboard.

#. Select |import-icon| Import.

#. The :guilabel:`Import Public Dashboard` dialog is opened.

#. Press the Select button and choose the JSON file that contains the dashboard you want to import.

#. Edit the mandatory title, permissions to see the public dashboard and the optional description for the public dashboard.

#. Import the dashboard by selecting the Create Dashboard button.

#. Configuration public dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Go back to the dashboard list to see that a new public dashboard has been added.

|import-public-dashboard-dialog|

.. note::

   Grant permission :bdg-ref-warning:`🔑DashboardImportPublic <DashboardImportPublic>` to enable public dashboard import for a user.

How to: edit public dashboards
==============================

You can edit public dashboards by selecting |edit-icon| Edit. The :guilabel:`Edit public dashboards` section shows
you the public dashboards.

A table of public dashboards is shown with the following information:

#. Dashboard title

#. Dashboard permissions

#. Dashboard description

#. :guilabel:`Actions` for further actions: |move-expand-vertical| reorder, |edit-icon| edit (name, description), |settings-icon| configure
   (see: :ref:`Dashboards <new-dashboard>` ), |download-icon| export dashboard, |share-icon| share
   and |trash-icon| delete a public dashboard.

.. note::

   The Export function only available for users who have the permission :bdg-ref-warning:`🔑DashboardExportPublic <DashboardExportPublic>`.

|edit-public-dashboards|

How to: share public dashboards
===============================

To generate a public dashboard link, simply click on the |share-icon| Share in the :guilabel:`Edit public dashboards` section.

|share-public-dashboards|

.. note::
   To share the dashboard without the menu frame, add the parameter ``openWithoutMenu=true`` to the URL. However, please be aware that this option is only for embedding purposes.
   Grant permission :bdg-ref-warning:`🔑ShareDashboardLink <ShareDashboardLink>` to allow a user to share dashboard links.

How to: reorder public dashboards
=================================

You can re-arrange them by drag and drop using |move-expand-vertical| Reorder.

A table of public dashboards is shown with the following information:

#. Dashboard title

#. Dashboard permissions

#. Dashboard description

#. :guilabel:`Actions` for further actions: |move-expand-vertical| reorder, |edit-icon| edit (name, description), |settings-icon| configure
   (see: :ref:`Dashboards <new-dashboard>` ), |download-icon| export dashboard, |share-icon| share
   and |trash-icon| delete a public dashboard.

|reorder-public-dashboards|

.. hint::
   - Note that this ordering does not apply to all users who ordered their dashboards. It is overridden by the personal ordering.

.. include:: ../includes/_common-icon.rst

.. _howto-import-public-dashboard:

.. |dashboard-configuration| image:: ../../screenshots/settings/dashboard-configuration.png
.. |dashboard-configuration-page| image:: ../../screenshots/dashboard-configuration/dashboard-configuration-page.png
.. |private-dashboard-configuration| image:: ../../screenshots/dashboard-configuration/private-dashboard-configuration.png
.. |create-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-private-dashboard-dialog.png
.. |dashboard-multi-language-dialog| image:: ../../screenshots/dashboard-configuration/dashboard-multi-language-dialog.png
.. |edit-private-dashboards| image:: ../../screenshots/dashboard-configuration/edit-private-dashboards.png
.. |reorder-your-dashboards| image:: ../../screenshots/dashboard-configuration/reorder-your-dashboards.png
.. |public-dashboard-configuration| image:: ../../screenshots/dashboard-configuration/public-dashboard-configuration.png
.. |create-public-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-public-dashboard-dialog.png
.. |edit-public-dashboards| image:: ../../screenshots/dashboard-configuration/edit-public-dashboards.png
.. |reorder-public-dashboards| image:: ../../screenshots/dashboard-configuration/reorder-public-dashboards.png
.. |dashboard-templates| image:: ../../screenshots/dashboard-configuration/dashboard-templates.png
.. |import-public-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/import-public-dashboard-dialog.png
.. |import-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/import-private-dashboard-dialog.png
.. |share-public-dashboards| image:: ../../screenshots/dashboard-configuration/share-dashboard-dialog.png
