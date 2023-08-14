.. _customization-logout:

Logout Process
==============

Introduction
------------

Portal supports you to customize:

   - Logout function when the user logs out from Portal.
   - The page will be shown to the user after he logs out.

.. tip::

   Refer to process ``CustomLogoutFeatures`` in project ``portal-developer-examples``
   for an example of how to customize logout process.

Customize logout function
-------------------------

Create callable sub process with

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

Customize logout page
---------------------

Create callable sub process with 

**Signature**: portalGetLogoutPage

+-----------------------+-------------------+
| Name                  | Type              |
+=======================+===================+
|**Result**                                 |
+-----------------------+-------------------+
| logoutPage            | java.lang.String  |
+-----------------------+-------------------+