.. _customization-logout:

Logout Process
==============

Introduction
------------

Portal support customize the redirect url after user logout success.

Customize Logout Process
------------------------

Create callable subprocess with 

**Signature**: potalGetLogoutPage

+-----------------------+-------------------+
| Name                  | Type              |
+=======================+===================+
|**Result**                                 |
+-----------------------+-------------------+
| logoutPage            | java.lang.String  |
+-----------------------+-------------------+


.. tip::

   Refer to process ``CustomLogoutPage`` in project ``portal-developer-examples``
   for an example of how to customize logout process.

