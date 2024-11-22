.. _customization-logout:

Logout Process
==============

Introduction
------------

The Portal allows you to customize:

   - Logout function when the user logs out from Portal.
   - The page that is shown to the user after he has logged out using the default logout process.

.. tip::

   Refer to process ``CustomLogoutPage`` in project ``portal-developer-examples``
   for an example of how to customize the logout process.

Customize the logout function
-----------------------------

Create a callable subprocess with

**Signature**: portalLogout

+-----------------------+-------------------+
| Name                  | Type              |
+=======================+===================+
|**Parameter**                              |
+-----------------------+-------------------+
| isTaskReserve         | java.lang.String  |
+-----------------------+-------------------+
| task                  | java.lang.String  |
+-----------------------+-------------------+

Customize the logout page
-------------------------

Create a callable subprocess with 

**Signature**: portalGetLogoutPage

+-----------------------+-------------------+
| Name                  | Type              |
+=======================+===================+
|**Result**                                 |
+-----------------------+-------------------+
| logoutPage            | java.lang.String  |
+-----------------------+-------------------+