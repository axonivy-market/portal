.. _portal-header:

Portal Header
*************

|axon-ivy| Portal header is always visible. On the top right of the
header you find two useful features:

#. Name of the user you’re logged in with. Additionally, if you
   click on your username you get a menu with options that allow you to
   configure the |axon-ivy| Portal to your personal needs. See `User Settings in
   the Axon Ivy Portal`_ for a detailed overview of the configuration options.

#. Next to your username is a |search-icon| **Search Bar** providing you
   with a portal-wide search. This feature is described in detail in 
   `Global Search`_.

|portal-header|

User Settings in the Axon Ivy Portal
------------------------------------

When you click on your username in the top right of the |axon-ivy| Portal,
a menu is shown allowing you to configure the Portal to your personal
needs. These features are described in the following sections below.

Admin Settings
--------------

Menu entry :ref:`admin-settings` is only available for users have role AXONIVY_PORTAL_ADMIN.

My profile
----------

Menu entry :ref:`my-profile` contains general setting, user language and email settings.

Absences
--------

Menu entry :guilabel:`Absences` provides you with an absence management
component. Here you can enter your absence and define delegate for
tasks assigned to you or one of the roles you hold.

.. hint::
   Although you can specify         
   delegates for a role you hold, we recommend specifying only delegates for tasks
   assigned personally to you. Roles are normally held by a group of users, so if
   you’re absent one of your role members is still able to pick the task.

|portal-absences|

The dialog shows you all current and upcoming absences. You may show also past
absences by activating the option :guilabel:`Show absences in the past`. Furthermore,
you can edit or delete absences by using the |edit-icon| **Edit** or |trash-icon|
**Delete** action in the row of the respective absence.

HowTo: Add absence
^^^^^^^^^^^^^^^^^^

#. Click on the button :guilabel:`Add absence` 

#. The :guilabel:`Add absence` dialog is opened

#. Enter :guilabel:`From` and :guilabel:`To` 

#. You may provide an explanatory comment

#. Confirm your absence by clicking button :guilabel:`Save` 

|how-to-add-an-absence|

HowTo: Set deputy
^^^^^^^^^^^^^^^^^

#. You see a list with 2 entries for your personal tasks and for each
   role you hold.

   For each role, you can choose multiple deputies.

   For your personal tasks, you can choose multiple deputies and there are two types of deputy:

   - Permanent deputies for personally assigned tasks.
   - Deputies for personally assigned tasks during the absence.

   A deputy can only belonged to one type.

#. Specify deputies by clicking on link ends with |si-notes-quill|.

#. Confirm your deputies with the button :guilabel:`Save`.

|how-to-set-absence-deputies|

Change Password
---------------

The menu entry :guilabel:`Change password` allows you to set a new password.

|portal-password-change-dialog|

Info
----

The menu entry :guilabel:`Info` provides you with detailed information about the
|axon-ivy| Engine, the |axon-ivy| Portal and your company's application. You
might be asked for this information in case you issue a support request.

|portal-version-information|

Logout
------

The menu entry :guilabel:`Logout` will terminate your session in the Axon Ivy
Portal. You’ll be routed back to the login page of the Axon Ivy
Portal.

.. hint:: 
   The |axon-ivy| Portal will also do
   an automatic logout if you’re   
   inactive for a certain amount of
   time.                           
   
Global Search
-------------

The Global Search is a convenient tool for you to look up
information in the whole |axon-ivy| Portal. You may search for any
keyword. By default, the |axon-ivy| Portal will search for:

#. Processes

#. Cases

#. Tasks

When you confirm the keyword you’re looking for, the |axon-ivy| Portal
will route you to the **Global Search Results** page. For each category
a tab is provided, giving you the results of your search.

|portal-global-search-result-page|

.. hint:: 
   Your company’s application may   
   provide further search           
   capabilities which are integrated
   in the |axon-ivy| Portal Global    
   Search. You’d find the results on
   the result page accordingly.     
   Contact your administrator for   
   more information.                

.. include:: ../includes/_common-icon.rst  


.. |portal-header| image:: ../../screenshots/dashboard/portal-header-with-numbering-annotation.png
.. |portal-absences| image:: ../../screenshots/settings/absence.png
.. |how-to-add-an-absence| image:: ../../screenshots/settings/new-absence.png
.. |how-to-set-absence-deputies| image:: ../../screenshots/settings/set-deputy.png
.. |portal-password-change-dialog| image:: ../../screenshots/settings/change-password.png
.. |portal-version-information| image:: ../../screenshots/settings/portal-version-information.png
.. |portal-global-search-result-page| image:: ../../screenshots/search/global-search-result.png