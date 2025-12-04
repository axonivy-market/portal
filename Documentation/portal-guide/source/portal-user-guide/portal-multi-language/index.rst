.. _portal-multi-language:

Portal Multi Language
*********************

The translation service integrated from Axon Ivy makes it easy to translate text content into your end user's language, eliminating language barriers in process automation.

Translation Overview
====================

The Portal multi-language feature provides:

- **Automated Translation**: Service-powered translations for supported languages
- **Manual Translation**: Manual input when translation service is not configured
- **Flexible Application**: Available for dashboards, processes, and other customizable text fields

.. note::
   Portal translation requires both :guilabel:`Portal.TranslationService.Enable` set to true and proper translation service configuration. Otherwise, you can translate text fields manually.

.. _enable-translation:

HowTo: Enable the Translation Service
-------------------------------------

To use Axon Ivy's translation service:

#. Open :ref:`Admin Settings <admin-settings>`

#. Set :guilabel:`Portal.TranslationService.Enable` to true

#. Configure the translation service variable, refer to :doc-url:`Axon Ivy translation service </engine-guide/configuration/translation-service/index.html>`

HowTo: Translate Text
---------------------

#. Look for the :guilabel:`translator icon` on supported input text fields. For example, when creating a private dashboard:

   |create-private-dashboard-dialog|

#. Click on the :guilabel:`translator icon` to open the Setting Multiple Languages dialog.

   |dashboard-multi-language-dialog|

#. Click on the input text field of your target language.

#. If the translation service is enabled, the text will be automatically translated. 
Click :guilabel:`Auto. translation` to accept the translated text.

#. If the translation service is not configured, enter translations manually for each language.

.. include:: ../includes/_common-icon.rst

.. |create-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-private-dashboard-dialog-ml.png
   :alt: Multi language usage when create private dashboard
.. |dashboard-multi-language-dialog| image:: ../../screenshots/dashboard-configuration/dashboard-multi-language-dialog-ml.png
   :alt: Multi language dialog