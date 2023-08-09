.. _customization-change-password-process:

Change Password Process
=======================

.. _customization-change-password-process-introduction:

Introduction
------------

Portal supports you to customize the change password process, e.g. to check new passwords against
leaked passwords databases when the user changes his password.

.. _customization-change-password-process-customization:

Customization Change Password Process
-------------------------------------

Create a callable sub process in your project with 

**Signature**: portalChangePassword

+-----------------------+-----------------------+------------------+
| Name                  | Type                  | Note             |
+=======================+=======================+==================+
| **Parameter**                                 |                  |
+-----------------------+-----------------------+------------------+
| currentPassword       | java.lang.String      |                  |
+-----------------------+-----------------------+------------------+
| newPassword           | java.lang.String      |                  |
+-----------------------+-----------------------+------------------+
|**Result**                                     |                  |
+-----------------------+-----------------------+------------------+
| message               | java.lang.String      |                  |
+-----------------------+-----------------------+------------------+
| status                | java.lang.String      | OK or FAIL       |
+-----------------------+-----------------------+------------------+

.. tip::

   Refer to process ``CustomChangePassword`` in project ``portal-developer-examples``
   for an example of how to customize change password process.


