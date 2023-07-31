.. _customization-change-password-process:

Change Password Process
=======================

.. _customization-change-password-process-introduction:

Introduction
------------

In Portal, the ``Change password process`` allows users to change their current
password. You can customize this process, e.g. to check new passwords against
leaked passwords databases when the user changes his password.

.. _customization-change-password-process-customization:

Customization Change Password Process
-------------------------------------

Create a callable subprocess in your project with 

**Signature**: portalChangePassword

+-----------------------+-----------------------------------------------------------+
| Name                  | Type                                                      |
+=======================+===========================================================+
| **Parameter**                                                                     |
+-----------------------+-----------------------------------------------------------+
| currentPassword       | java.lang.String                                          |
+-----------------------+-----------------------------------------------------------+
| newPassword           | java.lang.String                                          |
+-----------------------+-----------------------------------------------------------+
|**Result**                                                                         |
+-----------------------+-----------------------------------------------------------+
| message               | java.lang.String                                          |
+-----------------------+-----------------------------------------------------------+
| status                | com.axonivy.portal.components.enums.ChangePasswordStatus  |
+-----------------------+-----------------------------------------------------------+

.. tip::

   Refer to process ``CustomChangePassword`` in project ``portal-developer-examples``
   for an example of how to customize change password process.


