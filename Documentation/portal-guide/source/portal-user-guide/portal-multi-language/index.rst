.. _portal-multi-language:

Portal Multi Language
*********************

The document translation shows how easy it has become, to translate from the pure text, into the language of your end user.
Say goodbye to language barriers in process automation with Axon Ivy's translation service.

.. _enable-translation:

HowTo: Enable the translation
-----------------------------

To use Axon Ivy's translation service:

- Open :ref:`Admin Settings <admin-settings>`, set :guilabel:`Portal.TranslationService.Enable` to true.

- Configure translation service variable, refer to :doc-url:`Axon Ivy translation service </engine-guide/configuration/translation-service/index.html>`

HowTo: Translate a pure text
----------------------------

.. note::

   The Portal translation only works if both Portal.TranslationService.Enable is set to true and the translation service is configured correctly.

   Otherwise, you can translate the input text field manually.


#. If any input text field is supported the translation, a :guilabel:`translator icon` will be shown.
   For example, let's create a private dashboard. Then, click on the :guilabel:`translator icon`.

   |create-private-dashboard-dialog|

#. A Setting multiple languages dialog is shown.

   |dashboard-multi-language-dialog|

#. Click on the input text field of a foreign language to have it translated by Axon Ivy's translation service. To accept the translated text, clicking on :guilabel:`Auto. translation`.

.. include:: ../includes/_common-icon.rst

.. |create-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-private-dashboard-dialog.png
   :alt: Create private dashboard dialog
.. |dashboard-multi-language-dialog| image:: ../../screenshots/dashboard-configuration/dashboard-multi-language-dialog-ml.png
   :alt: Multi language dialog