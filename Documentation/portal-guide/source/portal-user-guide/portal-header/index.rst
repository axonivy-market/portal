.. _portal-header:

Portal Header
*************

Axon.ivy Portal header is always visible. On the top right of the
header you find two useful features:

1. Name of the user you’re logged in with. Additionally, if you
   click on your username you get a menu with options that allow you to
   configure the Axon.ivy Portal to your personal needs. See `User Settings in
   the Axon.ivy Portal`_ for a detailed overview of the configuration options.

2. Next to your username is a |search-icon| **Search Bar** providing you
   with a portal-wide search. This feature is described in detail in 
   `Global Search`_.

|portal-header|

User Settings in the Axon.ivy Portal
------------------------------------

When you click on your username in the top right of the Axon.ivy Portal,
a menu is shown allowing you to configure the portal to your personal
needs. These features are described in the following sections below.

My profile
----------

Menu entry **My profile** contains general setting, user language and email settings.

|my-profile-save|

General settings
^^^^^^^^^^^^^^^^

**General settings** allows you config the portal according your requirements. This area
contains :guilabel:`Homepage selection`, :guilabel:`Process list configuration`,
:guilabel:`Task list configuration`, :guilabel:`Case list configuration` and :guilabel:`Show tutorial`.


Language settings
^^^^^^^^^^^^^^^^^

**Language settings** allows you to specify your
preferred application language. This setting will affect all Axon.ivy
Portal pages and – if supported – your companies applications pages as
well. The following languages are currently supported in the Axon.ivy
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

Email settings
^^^^^^^^^^^^^^

**Email settings** allows you to configure your
preferences related to standard email notifications send by the Axon.ivy
Engine.

.. hint:: 
   If configured by your company’s application, you might also be able to configure application specific email notifications.   
..

Toggle button **Receive email notification on task assignment** allows you to 
specify if you want to receive an instant notification when a task is assigned to you or one of the roles you hold.

Toggle button **Receive further emails from application** allows you to activate / deactivate further email notifications.

Toggle button **Receive daily summary at** allows you to specify the weekdays 
when you receive a summary of all tasks assigned to you or one of your roles.

Click **Save** to save all your changes.

Absences
--------

Menu entry **Absences** provides you with an absence management
component. Here you can enter your absence and define delegate for
tasks assigned to you or one of the roles you hold.

.. hint::
   Although you can specify         
   delegates for a role you hold, we recommend specifying only delegates for tasks
   assigned personally to you. Roles are normally held by a group of users, so if
   you’re absent one of your role members is still able to pick up the task.

|portal-absences|

The dialog shows you all current and upcoming absences. You may show also past
absences by activating the option **Show absences in the past**. Furthermore,
you can edit or delete absences by using the |edit-icon| **Edit** or |trash-icon|
**Delete** action in the row of the respective absence.

How to: Add absence
^^^^^^^^^^^^^^^^^^^

1. Click on the button **Add absence**

2. The :guilabel:`Add absence` dialog is opened

3. Enter start date and end date

4. You may provide an explanatory comment

5. Confirm your absence by clicking button **OK**

|how-to-add-an-absence|

How to: Set deputy
^^^^^^^^^^^^^^^^^^

1. You see a list with an entry for your personal tasks and for each
   role you hold.

2. Specify a deputy by selecting user from the list.

3. With personal task, you can choose **Always** or **During my absence**

4. Confirm your deputies with the button **Save**.

|how-to-set-absence-deputies|

Change Password
---------------

The menu entry **Change password** allows you to set a new password.

|portal-password-change-dialog|

Info
----

The menu entry **Info** provides you with detailed information about the
Axon.ivy Engine, the Axon.ivy Portal and your companies application. You
might be asked for this information in case you issue a support request.

|portal-version-information|

Logout
------

The menu entry **Logout** will terminate your session in the Axon.ivy
Portal. You’ll be routed back to the login page of the Axon.ivy
Portal.

.. hint:: 
   The Axon.ivy Portal will also do
   an automatic logout if you’re   
   inactive for a certain amount of
   time.                           
   
Global Search
-------------

The Global Search is a convenient tool for you to look up
information in the whole Axon.ivy Portal. You may search for any
keyword. By default, the Axon.ivy Portal will search for:

1. Processes

2. Cases

3. Tasks

When you confirm the keyword you’re looking for, the Axon.ivy Portal
will route you to the :guilabel:`Global Search Results` page. For each category
a tab is provided, giving you the results of your search.

|portal-global-search-result-page|

.. hint:: 
   Your company’s application may   
   provide further search           
   capabilities which are integrated
   in the Axon.ivy Portal Global    
   Search. You’d find the results on
   the result page accordingly.     
   Contact your administrator for   
   more information.                

.. include:: ../includes/_common-icon.rst  


.. |portal-header| image:: ../../screenshots/dashboard/portal-header-with-numbering-annotation.png
.. |my-profile-save| image:: ../../screenshots/my-profile/email-settings.png
.. |portal-absences| image:: ../../screenshots/settings/absence.png
.. |how-to-add-an-absence| image:: ../../screenshots/settings/new-absence.png
.. |how-to-set-absence-deputies| image:: ../../screenshots/settings/set-deputy.png
.. |portal-password-change-dialog| image:: ../../screenshots/settings/change-password.png
.. |portal-version-information| image:: ../../screenshots/settings/portal-version-information.png
.. |portal-global-search-result-page| image:: ../../screenshots/search/global-search-result.png