.. _customization-change-password-process:

Change Password Process
=======================

.. _customization-change-password-process-introduction:

Introduction
------------

The Change Password Process customization enables you to replace Portal's default password change
logic with your own — for example checking against leaked password databases, enforcing custom
complexity rules, or integrating with external password management systems to ensure compliance
with your organization's security policies.

.. important::

   Once you register a custom subprocess for this signature, Portal skips its own default
   password-persistence step entirely — it does not run in addition to your subprocess. Your
   subprocess is fully responsible for persisting the new password itself (for example via
   ``ivy.session.getSessionUser().setPassword(newPassword)``) as well as validating it. Returning
   ``status = "OK"`` only controls what message is shown to the user; it does not, by itself,
   change the user's password.

.. _customization-change-password-process-customization:

Customize
---------

Create a callable subprocess in your project with 

**Signature**: portalChangePassword

**Parameters:**

``currentPassword`` (java.lang.String)
   The user's current password for validation.

``newPassword`` (java.lang.String)
   The new password to be set.

**Result:**

``message`` (java.lang.String)
   Status or error message returned to the user.

``status`` (java.lang.String)
   Operation status: OK or FAIL.

.. tip::

   Refer to process ``CustomChangePassword`` in project ``portal-developer-examples``
   for an example of change password process customization.


