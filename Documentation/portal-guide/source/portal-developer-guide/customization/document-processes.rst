.. _customization-document-processes:

Document processes
==================

.. _customization-document-processes-introduction:

Introduction
------------

When you upload documents but want to manage them outside ivy, for
example: in Document Management System (DMS), you should follow this
section to customize the document functions of Portal.

.. _customization-document-processes-customization:

Customization
-------------
From your project, create four callable sub processes with the below information
to customize document functions.

Get document list
+++++++++++++++++

Customize how Portal gets documents, create a callable sub process with:

**Signature**: portalGetDocumentList

+------------------------+----------------------------------------------------------------------+
| Name                   | Type                                                                 |
+========================+======================================================================+
| **Parameter**                                                                                 |
+------------------------+----------------------------------------------------------------------+
| businessCase           | ch.ivyteam.ivy.workflow.ICase                                        |
+------------------------+----------------------------------------------------------------------+
|**Result**                                                                                     |
+------------------------+----------------------------------------------------------------------+
| documents              | java.util.List<com.axonivy.portal.components.ivydata.bo.IvyDocument> |
+------------------------+----------------------------------------------------------------------+
| message                | java.lang.String                                                     |
+------------------------+----------------------------------------------------------------------+

.. note::

   After get document list from DMS, convert them into ``List<ch.ivy.addon.portal.component.ivydata.bo.IvyDocument>``
   Some mandatory fields when mapping: ``id``, ``name``, ``contentType``

Upload document
+++++++++++++++

Customize what Portal should do when a user uploads a document,
create a callable sub process with:

**Signature**: portalUploadDocument

+-----------------------------------------+------------------------------------------------+---------------+
| Name                                    | Type                                           |     Note      |
+=========================================+================================================+===============+
| **Parameter**                                                                            |               |
+-----------------------------------------+------------------------------------------------+---------------+
| businessCase                            | ch.ivyteam.ivy.workflow.ICase                  |               |
+-----------------------------------------+------------------------------------------------+---------------+
| uploadedFile                            | org.primefaces.model.file.UploadedFile         |               |
+-----------------------------------------+------------------------------------------------+---------------+
| enableScriptCheckingForUploadedDocument | java.lang.Boolean                              |               |
+-----------------------------------------+------------------------------------------------+---------------+
| enableVirusScannerForUploadedDocument   | java.lang.Boolean                              |               |
+-----------------------------------------+------------------------------------------------+---------------+
| allowedUploadFileTypes                  | java.lang.String                               |               |
+-----------------------------------------+------------------------------------------------+---------------+
| **Result**                                                                               |               |
+-----------------------------------------+------------------------------------------------+---------------+
| uploadedDocument                        | ch.ivyteam.ivy.workflow.document.IDocument     |               |
+-----------------------------------------+------------------------------------------------+---------------+
| message                                 | java.lang.String                               |               |
+-----------------------------------------+------------------------------------------------+---------------+
| status                                  | java.lang.String                               | OK or FAIL    |
+-----------------------------------------+------------------------------------------------+---------------+

Download document
+++++++++++++++++

Customize behavior when a user downloads a document from Portal,
create a callable sub process with:

**Signature**: portalDownloadDocument

+------------------------+------------------------------------------------------+
| Name                   | Type                                                 |
+========================+======================================================+
| **Parameter**                                                                 |
+------------------------+------------------------------------------------------+
| businessCase           | ch.ivyteam.ivy.workflow.ICase                        |
+------------------------+------------------------------------------------------+
| document               | com.axonivy.portal.components.ivydata.bo.IvyDocument |
+------------------------+------------------------------------------------------+
|**Result**                                                                     |
+------------------------+------------------------------------------------------+
| streamedContent        | org.primefaces.model.StreamedContent                 |
+------------------------+------------------------------------------------------+

Delete document
+++++++++++++++

Customize behavior when an user deletes a document from Portal,
create a callable sub process with:

**Signature**: portalDeleteDocument

+------------------------+------------------------------------------------------+
| Name                   | Type                                                 |
+========================+======================================================+
| **Parameter**                                                                 |
+------------------------+------------------------------------------------------+
| businessCase           | ch.ivyteam.ivy.workflow.ICase                        |
+------------------------+------------------------------------------------------+
| document               | com.axonivy.portal.components.ivydata.bo.IvyDocument |
+------------------------+------------------------------------------------------+
|**Result**                                                                     |
+------------------------+------------------------------------------------------+
| message                | java.lang.String                                     |
+------------------------+------------------------------------------------------+