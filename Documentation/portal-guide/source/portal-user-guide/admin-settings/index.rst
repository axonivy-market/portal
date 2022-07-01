.. _admin-settings:

Admin Settings
**************

.. hint::
   Only users who have granted the role ``AXONIVY_PORTAL_ADMIN`` can see and access 
   the :guilabel:`Admin settings` user menu item. They can update Portal settings, 
   as well as define, show and hide the announcement.

Select the :guilabel:`Admin settings` user menu item.

   |select-admin-settings|

.. _add-third-party-application:

HowTo: Add a Third Party Application
------------------------------------

#. Select the :guilabel:`Applications` tab.

   |applications|

#. Click on :guilabel:`New` to add a new third party application. Dialog :guilabel:`Add new third party application` is displayed.

   |add-application|

#. To change its menu icon, click on :guilabel:`Change`.

#. Enter :guilabel:`Display name` to specify its display name in an additional language, click on :guilabel:`Add languages`.

#. Enter its URL in :guilabel:`Link`, e.g. http://www.google.com.

#. Click on :guilabel:`Ok`.

.. _update-portal-settings:

HowTo: Update Portal Settings
-----------------------------

#. Select the :guilabel:`Settings` tab. All available settings with their default values and descriptions are listed in the table.

   |global-settings|

#. To update Portal settings, click on the |edit-icon| icon.

   |edit-global-settings|

#. To reset a setting to its default value, click on the |undo-icon| icon.

#. To reset all settings to their default values, click on :guilabel:`Restore all to defaults`.

.. hint:: 
   Portal settings are stored as Axon Ivy variables and can be configured in the 
   :dev-url:`Axon Ivy Cockpit </doc/nightly/engine-guide/tool-reference/engine-cockpit/configuration.html#engine-cockpit-variables>`.

.. centered:: Available settings

.. csv-table::
  :file: documents/available_settings.csv
  :header-rows: 1
  :class: longtable
  :widths: 20 20 60

HowTo: Show/Hide the Announcement
---------------------------------

#. Select the :guilabel:`Announcements` tab.

   |announcement|

#. Input your announcement text.

#. To show the announcement, click on :guilabel:`Enable`.

#. If the announcement is enabled, hide it again by clicking on :guilabel:`Disable`.

.. include:: ../includes/_common-icon.rst

.. |applications| image:: ../../screenshots/settings/applications.png
.. |add-application| image:: ../../screenshots/settings/add-application.png
.. |announcement| image:: ../../screenshots/settings/announcement.png
.. |select-admin-settings| image:: ../../screenshots/settings/select-admin-settings.png
.. |global-settings| image:: ../../screenshots/settings/global-settings.png
.. |edit-global-settings| image:: ../../screenshots/settings/edit-global-settings.png
