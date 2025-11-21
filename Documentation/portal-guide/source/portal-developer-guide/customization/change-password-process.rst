.. _customization-change-password-process:

Change Password Process
=======================

.. _customization-change-password-process-introduction:

Introduction
------------

The Change Password Process customization enables you to implement additional security validations when users change their passwords,
such as checking against leaked password databases, enforcing custom complexity rules,
or integrating with external password management systems to ensure compliance with your organization's security policies.

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


