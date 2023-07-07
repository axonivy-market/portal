.. _portal-multi-language:

Portal Multi Language
*********************

The document translation shows how easy it has become, to translate from the pure text, into the language of your end user.
Say goodbye to language barriers in process automation with Axon Ivy's translation service powered by DeepL.

HowTo: Enable the translation
-----------------------------

To use DeepL powered translations:

- You need to redirect to :ref:`admin-settings`, set this Portal setting to true: :guilabel:`Portal.DeepL.Enable`.

- Get a `free developer account <https://www.deepl.com/pro#developer/>`__ from DeepL.com, then copy the API-Key of your account into this Portal setting: :guilabel:`Portal.DeepL.AuthKey`.

HowTo: Translate a pure text
----------------------------

.. note::

   The Portal translation only works if both Portal.DeepL.Enable is set to true and Portal.DeepL.AuthKey has a valid key.

   Otherwise, you can translate the inputText manually.

#. If any inputText is supported the translation, a :guilabel:`translator icon` will be shown.
   For example, let's create a private dashboard. Then, clicking on the :guilabel:`translator icon`.

   |create-private-dashboard-dialog|

#. A Setting multiple languages dialog will be shown.

   |dashboard-multi-language-dialog|

#. You can click on the inputText of a foreign language to translate it. To apply the translated text, clicking on :guilabel:`Auto. translation`.

   |overlay-panel-translation|

.. include:: ../includes/_common-icon.rst

.. |create-private-dashboard-dialog| image:: ../../screenshots/multiple-language-configuration/create-private-dashboard-dialog.png
.. |overlay-panel-translation| image:: ../../screenshots/multiple-language-configuration/overlay-panel-translation.png
.. |dashboard-multi-language-dialog| image:: ../../screenshots/multiple-language-configuration/dashboard-multi-language-dialog.png
