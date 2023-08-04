.. _customization-logout:

Logout Process
==============

Introduction
------------

Portal supports you to customize:

   - Logout function when user logout from Portal.
   - The page which will be show to the user after he logged out.

.. tip::

   Refer to process ``CustomLogoutFeatures`` in project ``portal-developer-examples``
   for an example of how to customize logout process.

Customize logout function
-------------------------

Create callable sub process with

**Signature**: potalLogout

+-----------------------+-------------------+
| Name                  | Type              |
+=======================+===================+
|**Parameter**                              |
+-----------------------+-------------------+
| isTaskReserve         | java.lang.String  |
+-----------------------+-------------------+
| task                  | java.lang.String  |
+-----------------------+-------------------+

Customize logout page
---------------------

Create callable sub process with 

**Signature**: potalGetLogoutPage

+-----------------------+-------------------+
| Name                  | Type              |
+=======================+===================+
|**Result**                                 |
+-----------------------+-------------------+
| logoutPage            | java.lang.String  |
+-----------------------+-------------------+