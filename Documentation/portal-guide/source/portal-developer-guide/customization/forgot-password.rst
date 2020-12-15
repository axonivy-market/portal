.. _customization-forgot-password:

Forgot Password
===============

.. _customization-case-widget-how-to-override-token-generation-and-email-sending:

How to override token generation and email sending
--------------------------------------------------

Create a new callable process to override the SendPasswordResetEmail callable process.
Please examine below example in portal-developer-examples project:

|token-generation-and-email-sending|

.. _customization-case-widget-how-to-override-password-resetting:

How to override password resetting
----------------------------------

Create a new callable process to override the ResetPassword callable process.
Please examine below example in portal-developer-examples project:

|password-resetting|

.. |token-generation-and-email-sending| image:: images/forgot-password/generate-token-and-send-email.png
.. |password-resetting| image:: images/forgot-password/reset-password.png
