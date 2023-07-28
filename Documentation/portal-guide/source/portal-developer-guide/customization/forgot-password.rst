.. _customization-forgot-password:

Forgot Password
===============

.. _customization-case-widget-how-to-override-token-generation-and-email-sending:

How To Override Token Generation And Email Sending
--------------------------------------------------

Create a new callable process to override ``SendPasswordResetEmail``.
In project ``portal-developer-examples``, you find the following example:

|token-generation-and-email-sending|

.. _customization-case-widget-how-to-override-password-resetting:

.. _customization-password-reset:
Customize Password Resetting
----------------------------

Password resetting is the process that validates and updates the password for the user.

You can customize how to validate new passwords or messages you want to show to
user when he creates the new password successfully.

To do that, you should create a callable subprocess with:

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

.. |token-generation-and-email-sending| image:: images/forgot-password/generate-token-and-send-email.png
