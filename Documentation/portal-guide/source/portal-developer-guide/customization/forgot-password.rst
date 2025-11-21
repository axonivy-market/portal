.. _customization-forgot-password:

Forgot Password
===============

Introduction
------------

The Forgot Password customization allows you to override the default password reset mechanism, enabling you to implement custom token generation algorithms,
integrate with external authentication systems, or customize the email sending process to match your organization's security policies and branding requirements.

.. _customization-token-generation-email:

Customize Token Generation And Email Sending
--------------------------------------------

Token Generation And Email Sending is the process that will send an email to reset the password.

To customize this process, create a callable subprocess with:

**Signature**: portalSendPasswordResetEmail

**Parameters:**

``email`` (java.lang.String)
   The email address to send the password reset token to.

.. tip::

   Please refer to process ``CustomSendPasswordResetEmail`` in project ``portal-developer-examples``
   for an example of how to customize the token generation and email sending process.

.. _customization-password-reset:

Customize Password Resetting
----------------------------

Password resetting is the process that validates and updates the password for the user.

You can customize how to validate new passwords or messages you want to show to
the user when he creates the new password successfully.

To customize this process, create a callable subprocess with:

**Signature**: portalResetPassword

**Parameters:**

``newPassword`` (java.lang.String)
   The new password to be set.

``passwordConfirmation`` (java.lang.String)
   Confirmation of the new password for validation.

``token`` (java.lang.String)
   The password reset token previously sent to the user.

``username`` (java.lang.String)
   The username for the account being reset.

**Result:**

``message`` (java.lang.String)
   Status or error message to display to the user.

``resetSuccess`` (java.lang.Boolean)
   True if the password was reset successfully, false otherwise.

.. tip::

   Please refer to process ``CustomResetPassword`` in project ``portal-developer-examples``
   for an example of how to customize Password Resetting.
