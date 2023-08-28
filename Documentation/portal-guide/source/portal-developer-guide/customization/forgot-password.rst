.. _customization-forgot-password:

Forgot Password
===============

.. _customization-case-widget-how-to-override-token-generation-and-email-sending:

Customize Token Generation And Email Sending
--------------------------------------------

Token Generation And Email Sending is the process that will send an email to reset the password.

To customize this process, create a callable subprocess with:

**Signature**: portalSendPasswordResetEmail

+-----------------------+-------------------+
| Name                  | Type              |
+=======================+===================+
| **Parameter**                             |
+-----------------------+-------------------+
| email                 | java.lang.String  |
+-----------------------+-------------------+

.. tip::

   Please refer to process ``CustomSendPasswordResetEmail`` in project ``portal-developer-examples``
   for an example of how to customize the token generation and email sending process.

.. _customization-case-widget-how-to-override-password-resetting:

.. _customization-password-reset:

Customize Password Resetting
----------------------------

Password resetting is the process that validates and updates the password for the user.

You can customize how to validate new passwords or messages you want to show to
the user when he creates the new password successfully.

To customize this process, create a callable subprocess with:

**Signature**: portalResetPassword

+-----------------------+-------------------+
| Name                  | Type              |
+=======================+===================+
| **Parameter**                             |
+-----------------------+-------------------+
| newPassword           | java.lang.String  |
+-----------------------+-------------------+
| passwordConfirmation  | java.lang.String  |
+-----------------------+-------------------+
| token                 | java.lang.String  |
+-----------------------+-------------------+
| username              | java.lang.String  |
+-----------------------+-------------------+
|**Result**                                 |
+-----------------------+-------------------+
| message               | java.lang.String  |
+-----------------------+-------------------+
| resetSuccess          | java.lang.Boolean |
+-----------------------+-------------------+

.. tip::

   Please refer to process ``CustomResetPassword`` in project ``portal-developer-examples``
   for an example of how to customize Password Resetting.
