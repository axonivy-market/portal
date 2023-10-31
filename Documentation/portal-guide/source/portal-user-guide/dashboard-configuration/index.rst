.. _dashboard-configuration:

Dashboard Configuration
***********************

.. hint::
   Only users who have the permission ``DASHBOARD_WRITE_OWN`` or ``DASHBOARD_WRITE_PUBLIC`` can see and access
   the :guilabel:`Dashboard Configuration` user menu item. They can add, edit, reorder, show and hide the private or public dashboards.

Select the :guilabel:`Dashboard Configuration` user menu item.

|dashboard-configuration|

The :guilabel:`Dashboard Configuration` page provides you with private and public dashboard configuration tabs depending on the permissions
you hold in the application. The usage will be explained in the HowTo’s further down in this chapter.

|dashboard-configuration-page|

Private dashboards configuration
--------------------------------

The tab :guilabel:`Private dashboards` is only available for users who have the permission ``DASHBOARD_WRITE_OWN``. Here you can
add or edit your private dashboards, and reorder all dashboards visible to you.

|private-dashboard-configuration|

.. _howto-add-private-dashboard:

How to: add private dashboard
=============================

#. Select |add-icon| Add.

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

#. Select |add-icon| Add.

#. Select |import-icon| Import.

#. The :guilabel:`Import Private Dashboard` dialog is opened.

#. Press the Select button and choose the JSON file that contains the dashboard you want to import.

#. Edit the mandatory title and the optional description for the private dashboard.

#. Import the dashboard by selecting the Create Dashboard button.

#. Configuration your private dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Back on the dashboard list, you see that a new private dashboard has been added.

|import-private-dashboard-dialog|

How to: edit private dashboards
===============================

You can edit your private dashboards by selecting |edit-icon| Edit. The :guilabel:`Edit private dashboard` section shows
your private dashboards.

A table of private dashboards is shown with the following information:

#. Dashboard title

#. Description

#. :guilabel:`Actions` for further actions: |edit-icon| edit (name, description), |settings-icon| configure
   (see: :ref:`Dashboards <new-dashboard>` ), |download-icon| export dashboard,
   and |trash-icon| delete a private dashboard.

   .. note::

      the Export function only available for users who have the permission ``DASHBOARD_EXPORT_OWN``.

|edit-private-dashboards|

.. _howto-reorder-your-dashboards:

How to: reorder your dashboards
===============================

To access the reorder dashboards section, click on |move-expand-vertical| Reorder your dashboards. All dashboards that you can access will be listed,
you can hide or show a dashboard by selecting the checkbox `Visible`, re-arrange dashboards by drag and drop using |move-expand-vertical| Reorder.

A table of private dashboards is shown with the following information:

#. Dashboard title

#. Dashboard type: type of dashboard (public or private).

#. :guilabel:`Visible`: toggle visibility of dashboards.

#. :guilabel:`Reorder`: re-arrange dashboards.

#. Apply your changes by selecting Save.

|reorder-your-dashboards|

.. hint::
   - This feature reorders both your private and all public dashboards visible to you.
   - The resulting re-arrangement of dashboards

         - is only visible to yourself.
         - overrides all re-orderings of public dashboards.

.. note::
   - Once created, your personal sort order is retained.
   - Once you have created a personal sort order, you can rearrange the order, but you cannot delete your personal sort order.

.. hint::
   If you create new private dashboards, or if new public dashboards are available to you, they are
   automatically added to the end of your personal sort order. New public dashboards are added
   in front of new private dashboards.


Public dashboards configuration
-------------------------------

The tab :guilabel:`Public dashboards` is only available for users who have the permission ``DASHBOARD_WRITE_PUBLIC``. Here you can
add, edit, and reorder public dashboards.

|public-dashboard-configuration|

.. _howto-add-public-dashboard:

How to: add public dashboard
============================

#. Select |add-icon| Add.

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

#. Select |add-icon| Add.

#. Select |import-icon| Import.

#. The :guilabel:`Import Public Dashboard` dialog is opened.

#. Press the Select button and choose the JSON file that contains the dashboard you want to import.

#. Edit the mandatory title, permissions to see the public dashboard and the optional description for the public dashboard.

#. Import the dashboard by selecting the Create Dashboard button.

#. Configuration public dashboard (see: :ref:`Dashboard <new-dashboard>`).

#. Go back to the dashboard list to see that a new public dashboard has been added.

|import-public-dashboard-dialog|

How to: edit public dashboards
==============================

You can edit public dashboards by selecting |edit-icon| Edit. The :guilabel:`Edit public dashboards` section shows
you the public dashboards.

A table of public dashboards is shown with the following information:

#. Dashboard title

#. Dashboard roles

#. Description

#. :guilabel:`Actions` for further actions: |edit-icon| edit (name, description), |settings-icon| configure
   (see: :ref:`Dashboards <new-dashboard>` ), |download-icon| export dashboard, |share-icon| share
   and |trash-icon| delete a public dashboard.

   .. note::

      the Export function only available for users who have the permission ``DASHBOARD_EXPORT_PUBLIC``.

|edit-public-dashboards|

How to: share public dashboards
===============================

To generate a public dashboard link, simply click on the |share-icon| Share in the :guilabel:`Edit public dashboards` section.

|share-public-dashboards|

.. note::
   To share the dashboard without the menu frame, add the parameter ``openWithoutMenu=true`` to the URL. However, please be aware that this option is only for embedding purposes.


How to: reorder public dashboards
=================================

To access the reorder public dashboards section, click on |move-expand-vertical| Reorder public dashboards. Here, all public dashboards are listed.
You may re-arrange them by drag and drop using |move-expand-vertical| Reorder.

A table of public dashboards is shown with the following information:

#. Dashboard title

#. Dashboard type: type of dashboard (public).

#. :guilabel:`Reorder`: re-arrange public dashboards.

#. Apply your changes by selecting Save.

|reorder-public-dashboards|

.. hint::
   - This feature reorders public dashboards only.
   - Note that this ordering does not apply to all users who ordered their dashboards (public and private) as detailed in the section :ref:`howto-reorder-your-dashboards` above. It is overridden by the personal ordering.

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
