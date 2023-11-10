.. _customization-change-password-process:

Change Password Process
=======================

.. _customization-change-password-process-introduction:

Introduction
------------

The Portal allows the change password process, e.g. to check new passwords against
leaked passwords databases when the user changes his password.

.. _customization-change-password-process-customization:

Customize
---------

Create a callable subprocess in your project with 

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
   for an example of change password process customization.


