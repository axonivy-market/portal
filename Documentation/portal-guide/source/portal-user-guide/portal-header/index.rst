.. _portal-header:

Portal Header
*************

The |ivy| Portal header is always visible. On the top right of the
header you find two very useful features:

#. The name of the user you’re logged in with. Additionally, if you
   click on your username you get a menu with options that allow you to
   configure the |ivy| Portal to your personal needs. See `User Settings in
   the |ivy| Portal`_ for a detailed overview of the configuration options.

#. Next to your username is a |search-icon| **Search Bar** providing you
   with a portal-wide search. This feature is described in detail in 
   `Global Search`_.

.. figure:: images/portal-header.png

User Settings in the |ivy| Portal
------------------------------------

When you click on your username in the top right of the |ivy| Portal,
a menu is shown allowing you to configure the portal to your personal
needs. These features are described in the following sections below.

Admin Settings
--------------

Menu entry :ref:`admin-settings` is only available for users have role AXONIVY_PORTAL_ADMIN.

Absences
--------

The menu entry :guilabel:`Absences` provides you with an absence management
component. Here you can enter your absences and define delegates for
tasks assigned to you or one of the roles you hold.

.. hint::
   Although you can specify         
   delegates for a role you hold, we recommend specifying only delegates for tasks
   assigned personally to you. Roles are normally held by a group of users, so if
   you’re absent one of your role members is still able to pick the task.

.. figure:: images/portal-absences.png

The dialog shows you all current and upcoming absences. You may show also past
absences by activating the option :guilabel:`Show absences in the past`. Furthermore,
you can edit or delete absences by using the |edit-icon| **Edit** or |delete-icon|
**Delete** action in the row of the respective absence.

HowTo: Add absence
^^^^^^^^^^^^^^^^^^

#. Click on the button :guilabel:`Add absence`

#. The :guilabel:`Add absence` dialog is opened

#. Enter a start date

#. Enter an end date

#. You may provide an explanatory comment

#. Confirm your absence with the button :guilabel:`Next`

#. You are forwarded to the :guilabel:`Set deputies` dialog (see `HowTo:
   Set deputies`_).

.. figure:: images/how-to-add-an-absence.png

HowTo: Set deputies
^^^^^^^^^^^^^^^^^^^

#. Click on the button :guilabel:`Set deputies`. Alternatively, you’re forwarded
   to this dialog after you added an absence.

#. The :guilabel:`Set deputies` dialog is opened.

#. You see a list with an entry for your personal tasks and for each
   role you hold.

#. Specify a deputy by selecting him from the list.

#. You may add a comment

#. Confirm your deputies with the button :guilabel:`OK`.

.. figure:: images/how-to-set-absence-deputies.png

Email settings
--------------

The menu entry :guilabel:`Email settings` allows you to configure your
preferences related to standard email notifications send by the |ivy|
Engine.

.. hint:: 
   If configured by your company’s application, you might also be able to configure application specific email notifications.   
..

The :guilabel:`Email settings` dialog provides you with the following configuration options:

#. The option :guilabel:`For all applications` allows you to use one
   configuration for all applications. It is important to note, that in the
   background these settings will be applied to all available applications.

#. The option :guilabel:`For each application separate` allows you to provide
   individual settings for each available application.

#. The option :guilabel:`Receive email notification on task assignment` allows you to
   specify if you want to receive an instant notification when a task is
   assigned to you or one of the roles you hold.

#. The setting :guilabel:`Receive daily summary` allows you to specify the weekdays
   when you receive a summary of all tasks assigned to you or one of
   your roles.

#. The option :guilabel:`Receive further emails from application` allows you to activate
   / deactivate further email notifications.


.. figure:: images/axon-ivy-portal-email-notifications-settings.png

Language Settings
-----------------

The menu entry :guilabel:`Language settings` allows you to specify your
preferred application language. The setting will affect all |ivy|
Portal pages and – if supported – your companies applications pages as
well. The following languages are currently supported in the |ivy|
Portal:

-  English

-  German

-  French

-  Spanish

.. hint:: 
   Your application needs to support
   the same language as the one     
   selected in this menu. Otherwise 
   the application specific content 
   will be shown in the standard    
   language of the application. E.g.
   if your application only supports
   English, but you select Spanish  
   as the portal language, your     
   application specific dialogs will
   still be shown in English.       

.. figure:: images/portal-language-settings.png

Change Password
---------------

The menu entry :guilabel:`Change password` allows you to set a new password.

.. figure:: images/portal-password-change-dialog.png

Info
----

The menu entry :guilabel:`Info` provides you with detailed information about the
|ivy-engine|, the |ivy| Portal and your companies application. You
might be asked for this information in case you issue a support request.

.. figure:: images/portal-version-information.png

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

#. Processes

#. Cases

#. Tasks

When you confirm the keyword you’re looking for, the |ivy| Portal
will route you to the **Global Search Results** page. For each category
a tab is provided, giving you the results of your search.

.. figure:: images/portal-global-search-result-page.png

.. hint:: 
   Your company’s application may   
   provide further search           
   capabilities which are integrated
   in the |ivy| Portal Global    
   Search. You’d find the results on
   the result page accordingly.     
   Contact your administrator for   
   more information.                

.. include:: ../includes/_common-icon.rst  