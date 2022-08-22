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

.. _portal-available-settings:

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

HowTo: Manage Roles
------------------------------
Portal provides the :guilabel:`Role Management` section in the :guilabel:`Admin Settings` area, where the user can manage roles.

| To see the :guilabel:`Role Management` tab, the user has to have the ``RoleManagement`` permission.
| To manage roles, the user also has to own these permissions:

  - ``RoleCreate``: create a new dynamic role
  - ``RoleDelete``: delete a dynamic role
  - ``RoleMove``: can select the parent role at the :guilabel:`Create new role` step, by default the system will use ``Everybody``

#. Select the :guilabel:`Role Management` tab.

   |role-assignment-tab|

#. Click on the :guilabel:`Create new role` button and fill in the data.

   |role-assignment-creation-dialog|

#. After filling all mandatory fields, click on the :guilabel:`Save` button to create the role.

#. On the role tree table, you will see the new role.

#. The user can manage this new role by editing role properties, assigning users to the role, and deleting the role using the actions available on the :guilabel:`Actions` column.

.. note::
   Portal also provides two advanced configurations to control the number of roles that are shown in the :guilabel:`Role Management` tab.
   Refer to :ref:`Settings Variables <portal-available-settings>` for more information.

HowTo: Enable/Disable Password Validation
-----------------------------------------
Portal provides the :guilabel:`Password Validation` section in the :guilabel:`Admin Settings` area, where you can enable/disable password validation and change password policies as well. 

| To see the :guilabel:`Password Validation` tab, you have to have the ``PasswordValidation`` permission.

#. Select the :guilabel:`Password Validation` tab.

   |password-validation-tab|

#. Click :guilabel:`Enable Password Validation` toggle switch to enable/disable the feature.

#. If :guilabel:`Enable Password Validation` toggle switch is :guilabel:`Enabled`, you can activate/deactivate password policies and change password policy settings, click on the :guilabel:`Save` button to save the configuration.

#. After saving the configuration, you can verify it by either changing the password or reset password.

.. include:: ../includes/_common-icon.rst

.. |applications| image:: ../../screenshots/settings/applications.png
.. |add-application| image:: ../../screenshots/settings/add-application.png
.. |announcement| image:: ../../screenshots/settings/announcement.png
.. |select-admin-settings| image:: ../../screenshots/settings/select-admin-settings.png
.. |global-settings| image:: ../../screenshots/settings/global-settings.png
.. |edit-global-settings| image:: ../../screenshots/settings/edit-global-settings.png
.. |role-assignment-tab| image:: ../../screenshots/settings/role-assignment-tab.png
.. |role-assignment-creation-dialog| image:: ../../screenshots/settings/role-assignment-creation-dialog.png
.. |password-validation-tab| image:: ../../screenshots/settings/password-validation-tab.png