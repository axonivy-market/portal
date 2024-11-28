.. _portal-multi-language:

Portal Multi Language
*********************

The document translation shows how easy it has become, to translate from the pure text, into the language of your end user.
Say goodbye to language barriers in process automation with Axon Ivy's translation service powered by DeepL.

.. _enable-translation:

HowTo: Enable the translation
-----------------------------

To use DeepL powered translations:

- Open :ref:`Admin Settings <admin-settings>`, set :guilabel:`Portal.DeepL.Enable` to true.

- Get a `free developer account <https://www.deepl.com/pro#developer/>`__ from DeepL.com, then copy the API-Key of your account into this Portal setting: :guilabel:`Portal.DeepL.AuthKey`.

HowTo: Translate a pure text
----------------------------

.. note::

   The Portal translation only works if both Portal.DeepL.Enable is set to true and Portal.DeepL.AuthKey contains a valid key.

   Otherwise, you can translate the input text field manually.

#. If any input text field is supported the translation, a :guilabel:`translator icon` will be shown.
   For example, let's create a private dashboard. Then, click on the :guilabel:`translator icon`.

   |create-private-dashboard-dialog|

#. A Setting multiple languages dialog is shown.

   |dashboard-multi-language-dialog|

#. Click on the input text field of a foreign language to have it translated by DeepL. To accept the translated text, clicking on :guilabel:`Auto. translation`.

   |overlay-panel-translation|

.. include:: ../includes/_common-icon.rst

.. |create-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-private-dashboard-dialog-ml.png
   :alt: Multi language usage when create private dashboard
.. |overlay-panel-translation| image:: ../../screenshots/dashboard-configuration/overlay-panel-translation-ml.png
   :alt: Multi language: Translation panel 
.. |dashboard-multi-language-dialog| image:: ../../screenshots/dashboard-configuration/dashboard-multi-language-dialog-ml.png
   :alt: Multi language dialog