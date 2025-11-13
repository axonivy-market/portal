.. _portal-header:

Portal Header
*************

Axon Ivy Portal header is always visible. On the top right of the
header you find two useful features:

#. Name of the user you're logged in with. Additionally, if you
   click on your username you get a menu with options that allow you to
   configure the Axon Ivy Portal to your personal needs. See `User Settings in
   the Axon Ivy Portal`_ for a detailed overview of the configuration options.

#. Next to your username is a |search-icon| **Search Bar** providing you
   with a portal-wide search. This feature is described in detail in
   `Global Search`_.

|portal-header|

Permission Reference
====================

Different header features require specific permissions:

.. table::
   :widths: 40 60

   +--------------------------------------+--------------------------------------------------------+
   | Action                               | Required Permission                                    |
   +======================================+========================================================+
   | **Access admin settings**            | Role ``AXONIVY_PORTAL_ADMIN``                          |
   +--------------------------------------+--------------------------------------------------------+
   | **Manage own dashboards**            | :bdg-ref-warning:`🔑DashboardWriteOwn                 |
   |                                      | <DashboardWriteOwn>`                                   |
   +--------------------------------------+--------------------------------------------------------+
   | **Manage public dashboards**         | :bdg-ref-warning:`🔑DashboardWritePublic              |
   |                                      | <DashboardWritePublic>`                                |
   +--------------------------------------+--------------------------------------------------------+
   | **Create own absences**              | :bdg-ref-warning:`🔑UserCreateOwnAbsence              |
   |                                      | <UserCreateOwnAbsence>`                                |
   +--------------------------------------+--------------------------------------------------------+
   | **Create public absences**           | :bdg-ref-warning:`🔑UserCreateAbsence                 |
   |                                      | <UserCreateAbsence>`                                   |
   +--------------------------------------+--------------------------------------------------------+
   | **Create own substitutes**           | :bdg-ref-warning:`🔑UserCreateOwnSubstitute           |
   |                                      | <UserCreateOwnSubstitute>`                             |
   +--------------------------------------+--------------------------------------------------------+
   | **Create all substitutes**           | :bdg-ref-warning:`🔑UserCreateSubstitute              |
   |                                      | <UserCreateSubstitute>`                                |
   +--------------------------------------+--------------------------------------------------------+
   | **Change own password**              | :bdg-warning:`🔑UserSetOwnPassword`                    |
   +--------------------------------------+--------------------------------------------------------+

User Settings in the Axon Ivy Portal
------------------------------------

When you click on your username in the top right of the Axon Ivy Portal,
a menu is shown allowing you to configure the Portal to your personal
needs.

.. table::
   :widths: 30 70

   +-------------------------------+---------------------------------------------------------------+
   | Menu Option                   | Description                                                   |
   +===============================+===============================================================+
   | **Admin Settings**            | Portal administration (requires AXONIVY_PORTAL_ADMIN role)    |
   +-------------------------------+---------------------------------------------------------------+
   | **My Profile**                | General settings, language, and notifications                 |
   +-------------------------------+---------------------------------------------------------------+
   | **Dashboard Configuration**   | Manage private and public dashboards                          |
   +-------------------------------+---------------------------------------------------------------+
   | **Absences**                  | Manage absences and task delegates                            |
   +-------------------------------+---------------------------------------------------------------+
   | **Change Password**           | Set a new password                                            |
   +-------------------------------+---------------------------------------------------------------+
   | **Info**                      | Version information about Portal and Engine                   |
   +-------------------------------+---------------------------------------------------------------+
   | **Logout**                    | End your session                                              |
   +-------------------------------+---------------------------------------------------------------+

These features are described in the following sections below.

Admin Settings
--------------

Menu entry :ref:`Admin Settings <admin-settings>` provides portal administration features.

.. important::
   Only users with role ``AXONIVY_PORTAL_ADMIN`` can access Admin Settings.

My Profile
----------

Menu entry :ref:`my-profile` contains general setting, user language and notification channels subscription settings.

Dashboard Configuration
-----------------------

Menu entry :ref:`dashboard-configuration` allows you to manage your private dashboards and public dashboards (if permitted).

Absences
--------

Menu entry :guilabel:`Absences` provides you with an absence management
component. Here you can enter your absence and define delegate for
tasks assigned to you or one of the roles you hold.

.. tip::
   Specify delegates only for tasks assigned directly to you. Roles are typically held by multiple users, so if you're absent, another member can handle the task.

|portal-absences|

The dialog shows you all current and upcoming absences. You may show also past
absences by activating the option :guilabel:`Display absences in the past`. Furthermore,
you can edit or delete absences by using the |edit-icon| **Edit** or |trash-icon|
**Delete** action in the row of the respective absence.

HowTo: Add absence
^^^^^^^^^^^^^^^^^^

#. Click on the button :guilabel:`Add absence`

#. The :guilabel:`Add absence` dialog is opened

#. Enter :guilabel:`From` and :guilabel:`Until`

#. You may provide an explanatory comment

#. Confirm your absence by clicking button :guilabel:`Save`

|how-to-add-an-absence|

HowTo: Set substitute
^^^^^^^^^^^^^^^^^^^^^

#. You see a list with 2 entries for your personal tasks and for each
   role you hold.

   For each role, you can choose multiple substitutes.

   For your personal tasks, you can choose multiple substitutes and there are two types of substitute:

   - Permanent substitutes for personally assigned tasks.
   - Substitutes for personally assigned tasks during the absence.

   A substitute can only belonged to one type.

#. Specify substitutes by clicking on link ends with |si-notes-quill|, select substitutes and click :guilabel:`Add` button to add.

#. Confirm your substitutes with the button :guilabel:`Save`.

|how-to-set-absence-substitutes|

Change Password
---------------

The menu entry :guilabel:`Change password` allows you to set a new password.

|portal-password-change-dialog|

.. note::
   Configure the UserSetOwnPassword permission in the :dev-url:`Engine Cockpit
   </doc/12.0/engine-guide/reference/engine-cockpit/security.html>` under PersonalPermissions → PersonalSecurityPermissions → UserSetOwnPassword.

Info
----

The menu entry :guilabel:`Info` provides you with detailed information about the
Axon Ivy Engine, the Axon Ivy Portal and your company's application. You
might be asked for this information in case you issue a support request.

|portal-version-information|

Logout
------

The menu entry :guilabel:`Logout` will terminate your session in the Axon Ivy
Portal. You'll be routed back to the login page of the Axon Ivy
Portal.

.. tip::
   The Axon Ivy Portal will automatically log you out after a period of inactivity.

Global Search
-------------

The Global Search is a convenient tool for you to look up
information in the whole Axon Ivy Portal. You may search for any
keyword.

By default, the Axon Ivy Portal searches the following:

.. table::
   :widths: 25 75

   +-------------------+---------------------------------------------------------------+
   | Category          | Searchable Fields                                             |
   +===================+===============================================================+
   | **Processes**     | Name, description                                             |
   +-------------------+---------------------------------------------------------------+
   | **Cases**         | ID, name, description                                         |
   +-------------------+---------------------------------------------------------------+
   | **Tasks**         | ID, name, description, and all custom string fields           |
   +-------------------+---------------------------------------------------------------+

When you confirm the keyword you’re looking for, the Axon Ivy Portal
will route you to the **Global Search Results** page. For each category
a tab is provided, giving you the results of your search.

|portal-global-search-result-page|

HowTo: Limit Search Scope of Global Search
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

As an administrator, you may want to limit fields which Global Search should be looking for.

**Available Portal Variables:**

- **Portal.SearchScope.ByCaseFields**: Defining the fields that the global search will use to find matching cases besides case ID (can influence performance)
- **Portal.SearchScope.ByTaskFields**: Defining the fields that the global search will use to find matching tasks besides task ID (can influence performance)
- **Portal.GlobalSearchScopeCategories**: Defining the types that the global search will search for (can influence performance)

You can configure these variables by :ref:`settings-admin-settings`.

.. include:: ../includes/_common-icon.rst


.. |portal-header| image:: ../../screenshots/dashboard/portal-header-with-numbering-annotation.png
   :alt: Portal header items
.. |portal-absences| image:: ../../screenshots/settings/absence.png
   :alt: Absences page
.. |how-to-add-an-absence| image:: ../../screenshots/settings/new-absence.png
   :alt: Absences page: Add new absence dialog
.. |how-to-set-absence-substitutes| image:: ../../screenshots/settings/set-deputy.png
   :alt: Absences page: Set deputy section
.. |portal-password-change-dialog| image:: ../../screenshots/settings/change-password.png
   :alt: Change password dialog
.. |portal-version-information| image:: ../../screenshots/settings/portal-version-information.png
   :alt: Version information dialog
.. |portal-global-search-result-page| image:: ../../screenshots/search/global-search-result.png
   :alt: Global search results page
