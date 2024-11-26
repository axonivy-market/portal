.. _portal-header:

Portal Header
*************

|ivy| Portal header is always visible. On the top right of the
header you find two useful features:

#. Name of the user you’re logged in with. Additionally, if you
   click on your username you get a menu with options that allow you to
   configure the |ivy| Portal to your personal needs. See `User Settings in
   the |ivy| Portal`_ for a detailed overview of the configuration options.

#. Next to your username is a |search-icon| **Search Bar** providing you
   with a portal-wide search. This feature is described in detail in
   `Global Search`_.

|portal-header|

User Settings in the |ivy| Portal
------------------------------------

When you click on your username in the top right of the |ivy| Portal,
a menu is shown allowing you to configure the Portal to your personal
needs. These features are described in the following sections below.

Admin Settings
--------------

Menu entry :ref:`Admin Settings <admin-settings>` is only available for users who have role ``AXONIVY_PORTAL_ADMIN``.

My profile
----------

Menu entry :ref:`my-profile` contains general setting, user language and notification channels subscription settings.

Dashboard Configuration
-----------------------

Menu entry :ref:`dashboard-configuration` contains dashboards settings,
grant either :bdg-ref-warning:`🔑DashboardWriteOwn <DashboardWriteOwn>` or :bdg-ref-warning:`🔑DashboardWritePublic <DashboardWritePublic>` 
to allow a user to define dashboard settings either for their own private dashboards or public dashboards, respectively.

Absences
--------

Menu entry :guilabel:`Absences` provides you with an absence management
component. Here you can enter your absence and define delegate for
tasks assigned to you or one of the roles you hold.

.. hint::
   Although you can specify delegates for a role you hold, we recommend specifying delegates only for tasks assigned directly to you. Roles are typically held by a group of users, so if you're absent, another member of your role can still take on the task.

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

.. note::

   Grant either permission :bdg-ref-warning:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>` or :bdg-ref-warning:`🔑UserCreateAbsence <UserCreateAbsence>` 
   to allow a user to create own, personal absences or public absences, respectively.

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

.. note::

   Grant either permission :bdg-ref-warning:`🔑UserCreateOwnSubstitute <UserCreateOwnSubstitute>` or :bdg-ref-warning:`🔑UserCreateSubstitute <UserCreateSubstitute>` 
   to allow a user to create their own substitute definitions or create substitute definitions for all users, respectively.

Change Password
---------------

The menu entry :guilabel:`Change password` allows you to set a new password.

|portal-password-change-dialog|

Info
----

The menu entry :guilabel:`Info` provides you with detailed information about the
|ivy-engine|, the |ivy| Portal and your company's application. You
might be asked for this information in case you issue a support request.

|portal-version-information|

Logout
------

The menu entry :guilabel:`Logout` will terminate your session in the |ivy|
Portal. You’ll be routed back to the login page of the |ivy|
Portal.

.. hint::
   The |ivy| Portal will also do
   an automatic logout if you’re
   inactive for a certain amount of
   time.

Global Search
-------------

The Global Search is a convenient tool for you to look up
information in the whole |ivy| Portal. You may search for any
keyword. By default, the |ivy| Portal will search for:

#. Processes: name, description

#. Cases: id, name, description

#. Tasks: id, name, description and all custom string fields

When you confirm the keyword you’re looking for, the |ivy| Portal
will route you to the **Global Search Results** page. For each category
a tab is provided, giving you the results of your search.

|portal-global-search-result-page|

HowTo: Limit search scope of Global Search
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

As an administrator, you may want to limit fields which Global Search should be looking for.
Portal provides three variables to help you:

   - ``Portal.SearchScope.ByCaseFields``: Defining the fields that the global search
     will use to find matching cases besides case Id (this can influence the performance of the search).
   - ``Portal.SearchScope.ByTaskFields``: Defining the fields that the global search
     will use to find matching tasks besides task Id (this can influence the performance of the search).
   - ``Portal.GlobalSearchScopeCategories``: Defining the types that the global search will search for.
     (this can influence the performance of the search).

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