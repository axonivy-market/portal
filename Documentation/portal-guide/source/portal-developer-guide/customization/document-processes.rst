.. _customization-document-processes:

Document Processes
==================

.. _customization-document-processes-introduction:

Introduction
------------

The Document Processes customization allows you to integrate Portal with external Document Management Systems (DMS)
by overriding the default document handling functions. You can implement custom logic for uploading, downloading, retrieving, and deleting documents,
enabling seamless integration with enterprise content management solutions while maintaining Portal's document UI.

.. _customization-document-processes-customization:

Customize
---------
In your project, create four callable subprocesses with the below information
to customize document functions.

Get document list
+++++++++++++++++

Customize how Portal gets documents, create a callable subprocess with:

**Signature**: portalGetDocumentList

**Parameters:**

``businessCase`` (ch.ivyteam.ivy.workflow.ICase)
   The case for which to retrieve documents.

**Result:**

``documents`` (java.util.List<com.axonivy.portal.components.ivydata.bo.IvyDocument>)
   List of documents associated with the case. After retrieving documents from your DMS, convert them into this type with mandatory fields: ``id``, ``name``, ``contentType``.

``message`` (java.lang.String)
   Status or error message from the document retrieval operation.

Upload document
+++++++++++++++

Customize what Portal should do when a user uploads a document,
create a callable subprocess with:

**Signature**: portalUploadDocument

**Parameters:**

``businessCase`` (ch.ivyteam.ivy.workflow.ICase)
   The case to upload the document to.

``uploadedFile`` (org.primefaces.model.file.UploadedFile)
   The file being uploaded by the user.

``enableScriptCheckingForUploadedDocument`` (java.lang.Boolean)
   Whether to check uploaded files for embedded scripts.

``enableVirusScannerForUploadedDocument`` (java.lang.Boolean)
   Whether to scan uploaded files for viruses.

``allowedUploadFileTypes`` (java.lang.String)
   Comma-separated list of allowed file extensions.

**Result:**

``uploadedDocument`` (ch.ivyteam.ivy.workflow.document.IDocument)
   The uploaded document object.

``message`` (java.lang.String)
   Status or error message from the upload operation.

``status`` (java.lang.String)
   Operation status: OK or FAIL.

Download document
+++++++++++++++++

Customize behavior when a user downloads a document from Portal,
create a callable subprocess with:

**Signature**: portalDownloadDocument

**Parameters:**

``businessCase`` (ch.ivyteam.ivy.workflow.ICase)
   The case containing the document.

``document`` (com.axonivy.portal.components.ivydata.bo.IvyDocument)
   The document to be downloaded.

**Result:**

``streamedContent`` (org.primefaces.model.StreamedContent)
   The document content stream for download.

Delete document
+++++++++++++++

Customize behavior when an user deletes a document from Portal,
create a callable subprocess with:

**Signature**: portalDeleteDocument

**Parameters:**

``businessCase`` (ch.ivyteam.ivy.workflow.ICase)
   The case containing the document.

``document`` (com.axonivy.portal.components.ivydata.bo.IvyDocument)
   The document to be deleted.

**Result:**

``message`` (java.lang.String)
   Status or error message from the delete operation.