.. _forgot-password:

Forgot Password
***************

.. hint:: 
  The admin will need to enable the change password feature for you to see the option
  to display the "Forgot My Password" link.
  If you use an external security system, like Active Directory or Entra ID, 
  password management will need to be handled in that external system.
  
  You see the link on the bottom right of the login page.

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

.. include:: ../includes/_common-icon.rst

.. |login-screen| image:: ../../screenshots/login/login-form.png
   :alt: Portal login page
.. |send-email-screen| image:: ../../screenshots/forgot-password/send-email-screen.png
   :alt: "Send reset password email" page
.. |reset-password-screen| image:: ../../screenshots/forgot-password/reset-password-screen.png
   :alt: New password page
.. |reset-password-success-screen| image:: ../../screenshots/forgot-password/reset-password-success-screen.png
   :alt: Reset password successful page