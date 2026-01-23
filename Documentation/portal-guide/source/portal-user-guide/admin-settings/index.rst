.. _admin-settings:

Admin Settings
**************

.. important::
   **Admin Access Required**: Only users with the role ``AXONIVY_PORTAL_ADMIN`` can access Admin Settings 
   from the user menu. Without this role, the menu item will not be visible.

Admin Settings provide centralized control over Portal configuration, third-party integrations, 
announcements, roles, and security policies. The interface is organized into tabs for different 
administrative functions.

Available Tabs
==============

.. table::
   :widths: 20 80

   +------------------------+------------------------------------------------------------------+
   | Tab                    | Purpose                                                          |
   +========================+==================================================================+
   | **Applications**       | Add and manage third-party applications in the Portal menu       |
   +------------------------+------------------------------------------------------------------+
   | **Settings**           | Configure Portal behavior and feature toggles                    |
   +------------------------+------------------------------------------------------------------+
   | **Announcements**      | Create and display system-wide announcements to users            |
   +------------------------+------------------------------------------------------------------+
   | **Role Management**    | Create, edit, and delete dynamic roles (requires permissions)    |
   +------------------------+------------------------------------------------------------------+
   | **Password Validation**| Configure password policies and validation rules                 |
   +------------------------+------------------------------------------------------------------+

Accessing Admin Settings
=========================

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

#. Select :guilabel:`Permissions` to specify the permissions required for the application to be visible.

#. Click on :guilabel:`Save`.

.. _update-portal-settings:

HowTo: Update Portal Settings
-----------------------------

#. Select the :guilabel:`Settings` tab. All available settings with their default values and descriptions are listed in the table.

   |global-settings|

#. To update value of Portal settings variable, click on the |edit-icon| icon.

   |edit-global-settings|

#. To reset a setting to its default value, click on the |undo-icon| icon.

#. To reset all settings to their default values, click on :guilabel:`Restore all to defaults`.

.. note:: 
   Portal settings are stored as Axon Ivy variables and can be configured in the
   :dev-url:`Axon Ivy Cockpit </doc/12.0/engine-guide/reference/engine-cockpit/configuration.html#engine-cockpit-variables>`.
   
.. deprecated:: 12.0
   **Scheduled for removal in LTS 14**:
   
   - :guilabel:`Portal.Histories.HideSystemNotes`
   - :guilabel:`Portal.Histories.HideSystemNotesForAdministrator`
   
   **Migration**: Use the permission :bdg-ref-warning:`🔑NoteReadAllCaseTaskDetails <NoteReadAllCaseTaskDetails>` 
   to control visibility of system notes in task and case details instead.

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

#. Input your announcement text and click on :guilabel:`Save` button.

#. Turn on the :guilabel:`Enable announcement` option to display the announcement.

#. Turn off the :guilabel:`Enable announcement` option to hide the announcement.

.. _manage-roles:

HowTo: Manage Roles
-------------------

Portal provides the :guilabel:`Role Management` tab in the :guilabel:`Admin Settings` area for creating 
and managing dynamic roles.

Prerequisites
^^^^^^^^^^^^^

To access and use Role Management, you need specific permissions:

.. table::
   :widths: 30 70

   +--------------------------------------------------+------------------------------------------------+
   | Permission                                       | Purpose                                        |
   +==================================================+================================================+
   | :bdg-ref-warning:`🔑RoleManagement               | View the Role Management tab                   |
   | <RoleManagement>`                                |                                                |
   +--------------------------------------------------+------------------------------------------------+
   | :bdg-ref-warning:`🔑RoleCreate <RoleCreate>`     | Create new dynamic roles                       |
   +--------------------------------------------------+------------------------------------------------+
   | :bdg-ref-warning:`🔑RoleDelete <RoleDelete>`     | Delete existing dynamic roles                  |
   +--------------------------------------------------+------------------------------------------------+
   | :bdg-ref-warning:`🔑RoleMove <RoleMove>`         | Select parent role during creation             |
   |                                                  | (default parent: ``Everybody``)                |
   +--------------------------------------------------+------------------------------------------------+

Steps to Manage Roles
^^^^^^^^^^^^^^^^^^^^^^

#. Select the :guilabel:`Role Management` tab.

   |role-assignment-tab|

#. Click on the :guilabel:`Create new role` button and fill in the data.

   |role-assignment-creation-dialog|

#. After filling all mandatory fields, click on the :guilabel:`Save` button to create the role.

#. On the role tree table, you will see the new role.

#. The user can manage this new role by editing role properties, assigning users to the role, and deleting the role using the actions available on the :guilabel:`Actions` column.

.. tip::
   Portal provides two advanced configuration variables to control the number of roles displayed 
   in the Role Management tab. See :ref:`Settings Variables <portal-available-settings>` for details.

HowTo: Enable/Disable Password Validation
------------------------------------------

Portal provides the :guilabel:`Password Validation` tab in the :guilabel:`Admin Settings` area to configure 
password strength requirements and validation rules. This feature works with the Portal's built-in 
password management (e.g., :ref:`Forgot Password <forgot-password>` and password change functionality).

.. note::
   **External Authentication**: Password validation does **not** apply when using external identity 
   providers like Active Directory, Entra ID, or LDAP, as those systems manage their own password policies.

Prerequisites
^^^^^^^^^^^^^

To access Password Validation settings, you need the :bdg-ref-warning:`🔑PasswordValidation <PasswordValidation>` permission.

Steps to Configure Password Validation
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. Select the :guilabel:`Password Validation` tab.

   |password-validation-tab|

#. Toggle the :guilabel:`Enable Password Validation` switch to enable or disable the feature.

#. When enabled, you can:
   
   - Activate or deactivate individual password policies
   - Adjust policy settings (minimum length, required character types, etc.)

#. Click :guilabel:`Save` to apply the configuration.

#. Verify the configuration by testing password change or reset password functionality.

.. tip::
   Define password policies that balance security requirements with user experience. 
   Overly complex requirements may frustrate users and lead to insecure password management practices.

.. include:: ../includes/_common-icon.rst

.. |applications| image:: ../../screenshots/settings/applications.png
   :alt: Admin setting: applications
.. |add-application| image:: ../../screenshots/settings/add-application.png
   :alt: Admin setting: Add application dialog
.. |announcement| image:: ../../screenshots/settings/announcement.png
   :alt: Admin setting: Announcement
.. |select-admin-settings| image:: ../../screenshots/settings/select-admin-settings.png
   :alt: Admin setting menu
.. |global-settings| image:: ../../screenshots/settings/global-settings.png
   :alt: Admin setting: Global settings
.. |edit-global-settings| image:: ../../screenshots/settings/edit-global-settings.png
   :alt: Admin setting: Edit global setting dialog
.. |role-assignment-tab| image:: ../../screenshots/settings/role-assignment-tab.png
   :alt: Admin setting: Role management
.. |role-assignment-creation-dialog| image:: ../../screenshots/settings/role-assignment-creation-dialog.png
   :alt: Admin setting: role creation dialog
.. |password-validation-tab| image:: ../../screenshots/settings/password-validation-tab.png
   :alt: Admin setting: Password Validation