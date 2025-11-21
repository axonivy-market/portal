.. _customization-process-information:

Process Information
===================

.. _customization-process-information-page-introduction:

Introduction
------------

The Process Information customization enables you to create dedicated information pages for your processes that appear in Portal's process list. 
By defining the portalProcessInfo custom field with HTML-supported CMS content, you can provide users with detailed process descriptions, prerequisites, and guidance before they start a workflow.

.. _customization-process-information-page-customization:

Define Your Own Process Information Page
-----------------------------------------

#. Define a custom field ``portalProcessInfo`` in :guilabel:`Custom Fields` of the process start.

#. Define value for ``portalProcessInfo``

   |define-portal-process-info|

   .. tip::
      You can directly define value for ``portalProcessInfo`` in your process. However, we recommend
      define it in a CMS object (HTML supported).

.. |define-portal-process-info| image:: images/process-information/define-portal-process-info-image.png