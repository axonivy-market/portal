.. _customization-forgot-password:

Forgot Password
===============

.. _customization-case-widget-how-to-override-token-generation-and-email-sending:

How to override token generation and email sending
--------------------------------------------------

Create a new callable process to override the SendPasswordResetEmail callable process.

|token-generation-and-email-sending|

.. _customization-case-widget-how-to-override-password-resetting:

How to override password resetting
----------------------------------

Create a new callable process to override the ResetPassword callable process.

|password-resetting|

.. |token-generation-and-email-sending| image:: images/forgot-password/token-generation-and-email-sending.png
.. |password-resetting| image:: images/forgot-password/password-resetting.png
