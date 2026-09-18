.. _portal-multi-language:

Portal Multi Language
*********************

The translation service integrated from Axon Ivy makes it easy to translate text content into your end user's language, eliminating language barriers in process automation.

The Portal multi-language feature provides:

- **Automated Translation**: Service-powered translations for supported languages
- **Manual Translation**: Manual input when translation service is not configured
- **Flexible Application**: Available for dashboards, processes, and other customizable text fields

How-To
======

.. _enable-translation:

HowTo: Enable the Translation Service
-------------------------------------

To use Axon Ivy's translation service:

#. Open :ref:`Admin Settings <admin-settings>`

#. Set :guilabel:`Portal.TranslationService.Enable` to true

#. Configure the translation service variable, refer to :doc-url:`Axon Ivy translation service </engine-guide/configuration/translation-service/index.html>`

.. note::
   If the translation service is not configured, you can still translate text fields manually.

HowTo: Translate Text
---------------------

Translation is available on many text fields across Portal. The steps below use the creation of a
private dashboard as an example, they work the same way wherever the :guilabel:`translator icon` is shown.

#. Look for the :guilabel:`translator icon` on supported input text fields:

   |create-private-dashboard-dialog|

#. Click on the :guilabel:`translator icon` to open the Setting Multiple Languages dialog.

   |dashboard-multi-language-dialog|

#. Click on the input text field of your target language.

#. If the translation service is enabled, the text will be automatically translated. Click :guilabel:`Auto translation` to accept the translated text.

#. If the translation service is not configured, enter translations manually for each language.

.. include:: ../includes/_common-icon.rst

.. |create-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-private-dashboard-dialog.png
   :alt: Create private dashboard dialog
.. |dashboard-multi-language-dialog| image:: ../../screenshots/dashboard-configuration/dashboard-multi-language-dialog.png
   :alt: Multi language dialog