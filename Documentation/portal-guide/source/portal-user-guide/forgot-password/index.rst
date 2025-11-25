.. _forgot-password:

Forgot Password
***************

.. important::
   The *Forgot Password* feature must be enabled by an administrator in the Portal configuration
   before the "Forgot my password" link is shown on the login screen. If you do not see the link,
   contact your Portal administrator.

.. note::
   External identity providers (e.g. Microsoft Entra ID (Azure AD), Active Directory, LDAP) manage
   user credentials themselves. In those setups Portal does **not** change passwords; the link may be
   hidden automatically or should be disabled. Use the provider's own self-service password reset pages.

You find the "Forgot my password" link at the bottom right of the login page.

|login-screen|

To reset your password, click on ``Forgot my password``. You will see the page below.

|send-email-screen|

Input your registered email address and click on :guilabel:`Send`. If successful, an email
with a link will be sent to this email address. After clicking on this link, you will see this page:

|reset-password-screen|

Input your new password and the password confirmation, then click on :guilabel:`Continue`. If successful,
you will see this page:

|reset-password-success-screen|

Click on :guilabel:`Back To Login Page` and log in again with your account and the new password.

.. tip::
   For developers: This feature can be customized (e.g., custom email templates, validation rules).
   See :ref:`Customize Forgot Password <customization-forgot-password>` in the Developer Guide.

.. include:: ../includes/_common-icon.rst

.. |login-screen| image:: ../../screenshots/login/login-form.png
   :alt: Portal login page
.. |send-email-screen| image:: ../../screenshots/forgot-password/send-email-screen.png
   :alt: "Send reset password email" page
.. |reset-password-screen| image:: ../../screenshots/forgot-password/reset-password-screen.png
   :alt: New password page
.. |reset-password-success-screen| image:: ../../screenshots/forgot-password/reset-password-success-screen.png
   :alt: Reset password successful page