.. _admin-settings:

Admin Settings
**************

Only users have role ``AXONIVY_PORTAL_ADMIN`` could access :guilabel:`Admin settings` user menu item. They could update Portal settings, show/hide announcement.

Select :guilabel:`Admin settings` user menu item.

   |select-admin-settings|

.. _add-third-party-application:

HowTo: Add third party application
----------------------------------

#. Select :guilabel:`Applications` tab.

   |applications|

#. Click on :guilabel:`New` to add new third party applications, then dialog :guilabel:`Add new third party application` is displayed.

   |add-application|

#. To change menu icon, click on :guilabel:`Change`.

#. Enter :guilabel:`Display name`, to specify different display name for different language, click on :guilabel:`Add languages`.

#. Enter :guilabel:`Link`, e.g. http://www.google.com.

#. Click on :guilabel:`Ok`.

.. _update-portal-settings:

HowTo: Update Portal settings
-----------------------------

#. Select :guilabel:`Settings` tab. All available settings with their default value and description are listed in this place.

   |global-settings|

#. To update Portal settings, click on |edit-icon| button.

   |edit-global-settings|

#. To reset to default value of each setting, click on |undo-icon| button.

#. To reset to default value of all settings, click on :guilabel:`Restore all to defaults`.

.. note:: Portal settings could be configured in :dev-url:`Axon Ivy Cockpit </doc/9.3.5/engine-guide/tool-reference/engine-cockpit/configuration.html#engine-cockpit-variables>`.

HowTo: Show/Hide announcement
-----------------------------

#. Select :guilabel:`Announcements` tab.

   |announcement|

#. Input announcement.

#. To show announcement, click on :guilabel:`Enable`.

#. To hide announcement when announcement is showing, click on :guilabel:`Disable`.

.. include:: ../includes/_common-icon.rst

.. |applications| image:: ../../screenshots/settings/applications.png
.. |add-application| image:: ../../screenshots/settings/add-application.png
.. |announcement| image:: ../../screenshots/settings/announcement.png
.. |select-admin-settings| image:: ../../screenshots/settings/select-admin-settings.png
.. |global-settings| image:: ../../screenshots/settings/global-settings.png
.. |edit-global-settings| image:: ../../screenshots/settings/edit-global-settings.png
