.. _components-portal-components-document-table:

Document Table
**************

This component is a case document table with functions display, upload,
preview, download and delete document entries.

|document-table|

Attributes
^^^^^^^^^^
.. csv-table::
  :file: ../documents/document_table_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

Virus scanning
^^^^^^^^^^^^^^

The **Document Table** has options to check for harmful scripts and viruses inside the file before uploading it to the system.

   - Set ``enableScriptCheckingForUploadedDocument`` to ``true`` to check for harmful scripts.
   - Set ``enableVirusScannerForUploadedDocument`` to ``true`` to enable virus scanning.

Code example:

.. code-block:: html

   <ic:com.axonivy.portal.components.DocumentTable id="document-table-component"
      enableScriptCheckingForUploadedDocument="true"
      enableVirusScannerForUploadedDocument="true" />

Please refer to :ref:`settings-virus-scanning-setting` for more details about virus scanning.

.. _components-portal-components-migrate-from-old-document-table:

Customize
^^^^^^^^^

In your project, create four callable subprocesses with the information below to customize
the functions of the **Document Table**.

.. tip::

   You can refer to process ``CustomDocumentFeatures`` in project ``portal-components-examples``
   to examine how to customize.

Get document list
-----------------

To customize how **Document Table** gets documents, create a callable subprocess with:

**Signature**: portalGetDocumentItems

**Parameters:**

``businessCase`` (ch.ivyteam.ivy.workflow.ICase)
   The case for which to retrieve documents.

**Result:**

``documents`` (java.util.List<com.axonivy.portal.components.ivydata.bo.IvyDocument>)
   List of documents associated with the case. After retrieving documents from your DMS, convert them into this type with mandatory fields: ``id``, ``uuid``, ``name``, ``contentType``.

``message`` (java.lang.String)
   Status or error message from the document retrieval operation.

Upload document
---------------

To customize what **Document Table** should do when a user uploads a document,
create a callable subprocess with:

**Signature**: portalUploadDocumentItem

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
-----------------

To customize behavior when a user downloads a document from **Document Table**,
create a callable subprocess with:

**Signature**: portalDownloadDocumentItem

**Parameters:**

``businessCase`` (ch.ivyteam.ivy.workflow.ICase)
   The case containing the document.

``document`` (com.axonivy.portal.components.ivydata.bo.IvyDocument)
   The document to be downloaded.

**Result:**

``streamedContent`` (org.primefaces.model.StreamedContent)
   The document content stream for download.

Delete document
---------------

To customize behavior when a user deletes a document from **Document Table**,
create a callable subprocess with:

**Signature**: portalDeleteDocumentItem

**Parameters:**

``businessCase`` (ch.ivyteam.ivy.workflow.ICase)
   The case containing the document.

``document`` (com.axonivy.portal.components.ivydata.bo.IvyDocument)
   The document to be deleted.

**Result:**

``message`` (java.lang.String)
   Status or error message from the delete operation.

Rename document
---------------

To customize behavior when a user rename a document from **Document Table**,
create a callable subprocess with:

**Signature**: portalRenameDocumentItem

**Parameters:**

``businessCase`` (ch.ivyteam.ivy.workflow.ICase)
   The case containing the document.

``document`` (com.axonivy.portal.components.ivydata.bo.IvyDocument)
   The document to be renamed. The new filename should already be modified in the document's name attribute.

**Result:**

``message`` (java.lang.String)
   Message to inform the user that their new filename is successfully updated or invalid to use.

``isSuccess`` (java.lang.Boolean)
   ``true`` if the document's name is successfully updated, otherwise ``false``.

``status`` (java.lang.String)
   Operation status: OK or SKIP (if customized process skipped updating the filename).

User interface
--------------

Not only the functions, but you can also customize the UI of the **Document Table**
such as adding a new column or removing default columns.

Code Example:

.. code-block:: html

   <h:form id="form">
      <ic:com.axonivy.portal.components.DocumentTable id="document-table-component"
         allowedUploadFileTypes="doc,docx,xls,xlsx,xlsm,csv,pdf,ppt,pptx,txt,png"
         typeSelectionItems="#{documentTableExampleBean.documentTypes}">
         <f:facet name="componentHeader">
            <h2>This is the customized document table component header</h2>
         </f:facet>
         <p:column headerText="Creator" styleClass="document-creator-column">
            <h:outputText id="creator" value="#{document.creation.userName}" title="#{document.creation.userName}" />
         </p:column>
         <p:column headerText="Created time" styleClass="document-created-column">
            <h:outputText id="created-time" value="#{document.creation.timestamp}" title="#{document.creation.timestamp}" />
         </p:column>
         <p:column headerText="Customer" styleClass="document-customer-column">
            <h:outputText id="customer" value="#{document.customer}" title="#{document.customer}" />
         </p:column>
         <f:facet name="componentFooter">
            <h2>This is the customized document table component footer</h2>
         </f:facet>
      </ic:com.axonivy.portal.components.DocumentTable>
   </h:form>

.. tip::

   Refer to process ``DocumentTableExample`` in project ``portal-components-examples`` for more details.

Migration Notes
^^^^^^^^^^^^^^^

Migrate 10.0.x to 10.0.12
-------------------------

Since this version, we no longer support the override process approach for functions of **Document Table**.
Please follow our guidelines below to migrate your override subprocesses.

   - Remove Subprocess Override of ``GetDocumentItems``, ``UploadDocumentItem``,
     ``DeleteDocumentItem``, and ``DownloadDocumentItem`` from your project.

   - Change the signature of your callable starts as described below.

      +----------------------+----------------------------+
      | Subprocess           | New signature              |
      +======================+============================+
      | GetDocumentItems     | portalGetDocumentItems     |
      +----------------------+----------------------------+
      | UploadDocumentItem   | portalUploadDocumentItem   |
      +----------------------+----------------------------+
      | DeleteDocumentItem   | portalDownloadDocumentItem |
      +----------------------+----------------------------+
      | DownloadDocumentItem | portalDeleteDocumentItem   |
      +----------------------+----------------------------+

Now your **Document Table** should work as before.

Migrate to 10.0.0
-----------------

#. Replace code in HTML files: replace ``ic:ch.ivy.addon.portalkit.component.document.DocumentTable`` with ``ic:com.axonivy.portal.components.DocumentTable``.

#. Consider updating value of the new attributes ``enableScriptCheckingForUploadedDocument``, ``enableVirusScannerForUploadedDocument`` and ``allowedUploadFileTypes`` as required.

#. Override subprocesses if you want and adapt your business accordingly.

   +-----------------------------------+--------------------------+
   | New subprocess                    | Deprecated subprocess    |
   +===================================+==========================+
   | GetDocumentItems                  | GetDocumentList          |
   +-----------------------------------+--------------------------+
   | UploadDocumentItem                | UploadDocument           |
   +-----------------------------------+--------------------------+
   | DeleteDocumentItem                | DeleteDocument           |
   +-----------------------------------+--------------------------+
   | DownloadDocumentItem              | DownloadDocument         |
   +-----------------------------------+--------------------------+

#. If you have customized IvyDocument make sure to extend it from class ``com.axonivy.portal.components.ivydata.bo.IvyDocument``.

#. Attributes ``typeSelectionItems`` and ``selectedType`` now use ``com.axonivy.portal.components.enums.DocumentType``.
   Please replace ``ch.ivy.addon.portalkit.enums.DocumentType`` with ``com.axonivy.portal.components.enums.DocumentType``.

   .. note::
      Please remove redundant overridden configurations, subprocesses, and data classes such as GetDocumentListOverride,
      UploadDocumentOverride, etc.

.. |document-table| image:: ../../screenshots/components/document-table.png
