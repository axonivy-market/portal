.. _axonivyportal.customization.changepasswordprocess:

Change password process
=======================

.. _axonivyportal.customization.changepasswordprocess.introduction:

Introduction
------------

In Portal, the ``Change password process`` helps users to change their
current password, you can customize this process to add more handling
when user change password.

.. _axonivyportal.customization.changepasswordprocess.customization:

Customization
-------------

Create a callable sub process in your project with the
``changePassword(String,String)`` signature, make sure this signature is
unique in your application. It must return an enums
``ChangePasswordStatus`` and the ``message`` showing to user (you can
override this process in PortalKit):
