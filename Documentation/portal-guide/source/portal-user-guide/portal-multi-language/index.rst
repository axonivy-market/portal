.. _portal-multi-language:

Portal Multi Language
*********************

The Portal translation service powered by DeepL makes it easy to translate text content into your end user's language, eliminating language barriers in process automation.

Overview
========

The Portal multi-language feature provides:

- **Automated Translation**: DeepL-powered translations for supported languages
- **Manual Translation**: Manual input when DeepL is not configured
- **Flexible Application**: Available for dashboards, processes, and other customizable text fields

.. note::
   Portal translation requires both :guilabel:`Portal.DeepL.Enable` set to true and a valid :guilabel:`Portal.DeepL.AuthKey`. Otherwise, you can translate text fields manually.

.. _enable-translation:

HowTo: Enable the Translation Service
=====================================

To use DeepL-powered translations:

#. Open :ref:`Admin Settings <admin-settings>`

#. Set :guilabel:`Portal.DeepL.Enable` to true

#. Get a free developer account from `DeepL.com <https://www.deepl.com/pro#developer/>`__

#. Copy your DeepL API key into the Portal setting :guilabel:`Portal.DeepL.AuthKey`

.. tip::
   The DeepL free tier supports up to 500,000 characters per month, suitable for most Portal translation needs.

HowTo: Translate Text
=====================

#. Look for the :guilabel:`translator icon` on supported input text fields. For example, when creating a private dashboard:

   |create-private-dashboard-dialog|

#. Click on the :guilabel:`translator icon` to open the Setting Multiple Languages dialog.

   |dashboard-multi-language-dialog|

#. Click on the input text field of your target language.

.. include:: ../includes/_common-icon.rst

.. |create-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-private-dashboard-dialog-ml.png
   :alt: Multi language usage when create private dashboard
.. |dashboard-multi-language-dialog| image:: ../../screenshots/dashboard-configuration/dashboard-multi-language-dialog-ml.png
   :alt: Multi language dialog