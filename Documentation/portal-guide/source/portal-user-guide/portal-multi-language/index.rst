.. _portal-multi-language:

Portal Multi Language
*********************

Say goodbye to language barriers in process automation with Axon Ivy's translation service powered by DeepL.

HowTo: Activate translation
--------------------

You can turn on the translation with this Portal setting: :guilabel:`Portal.DeepL.Enable`.

Get a `free developer account <https://www.deepl.com/pro#developer/>`__ from DeepL.com, then copy the API-Key of your account into this Portal setting: :guilabel:`Portal.DeepL.AuthKey`.

HowTo: Translate a text
----------------------------------------------

.. note::

   The Portal translation support only works if both Portal.DeepL.Enable is set to true and Portal.DeepL.AuthKey has a valid key.

   Otherwise, you can enter translated text for each language manually.

#. If any inputText is supported to translate, a translator icon will be shown. For example, let's create a private dashboard:

   |create-private-dashboard-dialog|

#. If the login user belongs to pre-defined roles, you would see :guilabel:`Add users to chat` dialog. You could choose members of process chat.
   Members could be users or roles. With each selection, click :guilabel:`Add`. After adding members, click :guilabel:`Create process chat`.

   |dashboard-multi-language-dialog|

   If the login user does not belong to pre-defined roles, you would be informed that the process chat is created.

HowTo: Read/Send chat message
-----------------------------

#. Click on |bubble-icon| icon on the top right corner.

   |access-chat|

#. Click on a process chat or a user to read messages

   |chat|

#. You could type, then press :kbd:`Enter` or click |send-email-icon| to send messages.

.. include:: ../includes/_common-icon.rst

.. |create-private-dashboard-dialog| image:: ../../screenshots/multiple-language-configuration/create-public-dashboard-dialog.png
.. |overlay-panel-translation| image:: ../../screenshots/multiple-language-configuration/overlay-panel-translation.png
.. |dashboard-multi-language-dialog| image:: ../../screenshots/multiple-language-configuration/dashboard-multi-language-dialog.png
