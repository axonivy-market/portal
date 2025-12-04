.. _portal-header:

Portal Header
*************

The Portal header is always visible and provides quick access to essential features and settings. The top right section of the header contains two key features:

Key Features
============

**Username Menu**
   Click on your username to access a menu with options for configuring Portal to your personal needs. See `User Settings in the Axon Ivy Portal`_ for detailed configuration options.

**Global Search Bar**
   Next to your username is the |search-icon| search bar, providing portal-wide search functionality. This feature is described in detail in `Global Search`_.

|portal-header|

User Settings in the Axon Ivy Portal
====================================

Click on your username in the top right of the Portal to access a menu with options for configuring Portal to your personal needs.

Available Menu Options
----------------------

.. table::
   :widths: 30 70

   +-------------------------------+---------------------------------------------------------------+
   | Menu Option                   | Description                                                   |
   +===============================+===============================================================+
   | **Admin Settings**            | Portal administration (``AXONIVY_PORTAL_ADMIN`` role)         |
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

Menu Option Details
-------------------

Admin Settings
^^^^^^^^^^^^^^

The :ref:`Admin Settings <admin-settings>` menu entry provides portal administration features.

.. important::
   Only users with role ``AXONIVY_PORTAL_ADMIN`` can access Admin Settings.

My Profile
^^^^^^^^^^

The :ref:`my-profile` menu entry contains general settings, user language, and notification channels subscription settings.

Dashboard Configuration
^^^^^^^^^^^^^^^^^^^^^^^

The :ref:`dashboard-configuration` menu entry allows you to manage your private dashboards and public dashboards (if permitted).

Absences
^^^^^^^^

The :guilabel:`Absences` menu entry provides an absence management component where you can enter your absences and define delegates for tasks assigned to you or the roles you hold.

|portal-absences|

The absence dialog displays all current and upcoming absences. You can also view past absences by activating the :guilabel:`Display absences in the past` option. Edit or delete absences using the |edit-icon| **Edit** or |trash-icon| **Delete** actions in the respective absence row.

.. tip::
   Specify delegates only for tasks assigned directly to you. Roles are typically held by multiple users, so if you're absent, another member can handle the task.

HowTo: Add Absence
""""""""""""""""""

#. Click the :guilabel:`Add absence` button
#. The :guilabel:`Add absence` dialog opens
#. Enter :guilabel:`From` and :guilabel:`Until` dates
#. Optionally provide an explanatory comment
#. Confirm your absence by clicking the :guilabel:`Save` button

|how-to-add-an-absence|

HowTo: Set Substitute
"""""""""""""""""""""

The substitute list contains entries for your personal tasks and each role you hold.

**Substitute Types:**

- **For roles**: You can choose multiple substitutes for each role
- **For personal tasks**: You can choose multiple substitutes in two categories:

  - Permanent substitutes for personally assigned tasks
  - Substitutes for personally assigned tasks during absence

  .. note::
     A substitute can only belong to one type.

**Steps:**

#. Click the link ending with |si-notes-quill| to specify substitutes
#. Select substitutes and click the :guilabel:`Add` button
#. Confirm your substitutes with the :guilabel:`Save` button

|how-to-set-absence-substitutes|

Change Password
^^^^^^^^^^^^^^^

The :guilabel:`Change password` menu entry allows you to set a new password.

|portal-password-change-dialog|

.. note::
   Configure the UserSetOwnPassword permission in the :doc-url:`Engine Cockpit </engine-guide/reference/engine-cockpit/security.html>` under PersonalPermissions → PersonalSecurityPermissions → UserSetOwnPassword.

Info
----

The menu entry :guilabel:`Info` provides you with detailed information about the
Axon Ivy Engine, the Axon Ivy Portal and your company's application. You
might be asked for this information in case you issue a support request.

|portal-version-information|

Logout
------

The menu entry :guilabel:`Logout` will terminate your session in the Axon Ivy
Portal. You’ll be routed back to the login page of the Axon Ivy
Portal.

.. hint::
   The Axon Ivy Portal will also do
   an automatic logout if you’re
   inactive for a certain amount of
   time.

Global Search
=============

Global Search provides a convenient way to look up information across the entire Portal. You can search for any keyword, and Portal will search across multiple categories.

Default Search Scope
--------------------

By default, Portal searches the following categories:

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

Search Results
--------------

When you enter a keyword and confirm your search, Portal routes you to the **Global Search Results** page. Each category has its own tab displaying the matching results.

|portal-global-search-result-page|

HowTo: Configure Search Scope
-----------------------------

As an administrator, you can limit the fields that Global Search uses to improve performance and relevance.

Available Portal Variables
^^^^^^^^^^^^^^^^^^^^^^^^^^

**Portal.SearchScope.ByCaseFields**
   Defines the fields that global search uses to find matching cases besides case ID (can influence performance).

**Portal.SearchScope.ByTaskFields**
   Defines the fields that global search uses to find matching tasks besides task ID (can influence performance).

**Portal.GlobalSearchScopeCategories**
   Defines the categories that global search will search (can influence performance).

.. tip::
   Configure these variables in :ref:`admin-settings` to optimize search performance for your Portal instance.

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
   | **Manage own dashboards**            | :bdg-ref-warning:`🔑DashboardWriteOwn                  |
   |                                      | <DashboardWriteOwn>`                                   |
   +--------------------------------------+--------------------------------------------------------+
   | **Manage public dashboards**         | :bdg-ref-warning:`🔑DashboardWritePublic               |
   |                                      | <DashboardWritePublic>`                                |
   +--------------------------------------+--------------------------------------------------------+
   | **Create own absences**              | :bdg-ref-warning:`🔑UserCreateOwnAbsence               |
   |                                      | <UserCreateOwnAbsence>`                                |
   +--------------------------------------+--------------------------------------------------------+
   | **Create public absences**           | :bdg-ref-warning:`🔑UserCreateAbsence                  |
   |                                      | <UserCreateAbsence>`                                   |
   +--------------------------------------+--------------------------------------------------------+
   | **Create own substitutes**           | :bdg-ref-warning:`🔑UserCreateOwnSubstitute            |
   |                                      | <UserCreateOwnSubstitute>`                             |
   +--------------------------------------+--------------------------------------------------------+
   | **Create all substitutes**           | :bdg-ref-warning:`🔑UserCreateSubstitute               |
   |                                      | <UserCreateSubstitute>`                                |
   +--------------------------------------+--------------------------------------------------------+
   | **Change own password**              | :bdg-warning:`🔑UserSetOwnPassword`                    |
   +--------------------------------------+--------------------------------------------------------+

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
