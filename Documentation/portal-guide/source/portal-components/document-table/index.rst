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

   You can refer to process ``CustomDocumentFeatures`` in project ``portal-component-examples``
   to examnine how to customize.

Get document list
-----------------

To customize how **Document Table** gets documents, create a callable subprocess with:

**Signature**: portalGetDocumentItems

+------------------------+----------------------------------------------------------------------+----------------+
| Name                   | Type                                                                 | Note           |
+========================+======================================================================+================+
| **Parameter**                                                                                                  |        
+------------------------+----------------------------------------------------------------------+----------------+
| businessCase           | ch.ivyteam.ivy.workflow.ICase                                        |                |
+------------------------+----------------------------------------------------------------------+----------------+
|**Result**                                                                                                      |
+------------------------+----------------------------------------------------------------------+----------------+
| documents              | java.util.List<com.axonivy.portal.components.ivydata.bo.IvyDocument> |                |
+------------------------+----------------------------------------------------------------------+----------------+
| message                | java.lang.String                                                     |                |
+------------------------+----------------------------------------------------------------------+----------------+
| status                 | java.lang.String                                                     | OK or SKIP     |
+------------------------+----------------------------------------------------------------------+----------------+

Portal will call subprocesses with the above details in the same security context and then
get the first result where ``status`` is ``"OK"``.

.. note::

   After you get the document list from DMS, convert it into ``List<ch.ivy.addon.portal.component.ivydata.bo.IvyDocument>``
   These fields are mandatory when mapping: ``id``, ``name``, ``contentType``

Upload document
---------------

To customize what **Document Table** should do when a user uploads a document,
create a callable subprocess with:

**Signature**: portalUploadDocumentItem

+-----------------------------------------+----------------------------------------------------------+----------------+
| Name                                    | Type                                                     | Note           |
+=========================================+==========================================================+================+
| **Parameter**                                                                                                       |
+-----------------------------------------+----------------------------------------------------------+----------------+
| businessCase                            | ch.ivyteam.ivy.workflow.ICase                            |                |
+-----------------------------------------+----------------------------------------------------------+----------------+
| uploadedFile                            | org.primefaces.model.file.UploadedFile                   |                |
+-----------------------------------------+----------------------------------------------------------+----------------+
| enableScriptCheckingForUploadedDocument | java.lang.Boolean                                        |                |
+-----------------------------------------+----------------------------------------------------------+----------------+
| enableVirusScannerForUploadedDocument   | java.lang.Boolean                                        |                |
+-----------------------------------------+----------------------------------------------------------+----------------+
| allowedUploadFileTypes                  | java.lang.String                                         |                |
+-----------------------------------------+----------------------------------------------------------+----------------+
| **Result**                                                                                                          |
+-----------------------------------------+----------------------------------------------------------+----------------+
| uploadedDocument                        | ch.ivyteam.ivy.workflow.document.IDocument               |                |
+-----------------------------------------+----------------------------------------------------------+----------------+
| message                                 | java.lang.String                                         |                |
+-----------------------------------------+----------------------------------------------------------+----------------+
| status                                  | java.lang.String                                         | OK or SKIP     |
+-----------------------------------------+----------------------------------------------------------+----------------+

The parameters of the callable subprocess data contain the file you want to upload ``uploadedFile``
and other variables to validate the file before uploading it.

Portal will call subprocesses with the above details in the same security context and then
get the first result where ``status`` is ``"OK"``.

Download document
-----------------

To customize behavior when a user downloads a document from **Document Table**,
create a callable subprocess with:

**Signature**: portalDownloadDocumentItem

+------------------------+------------------------------------------------------+----------------+
| Name                   | Type                                                 | Note           |
+========================+======================================================+================+
| **Parameter**                                                                                  |
+------------------------+------------------------------------------------------+----------------+
| businessCase           | ch.ivyteam.ivy.workflow.ICase                        |                |
+------------------------+------------------------------------------------------+----------------+
| document               | com.axonivy.portal.components.ivydata.bo.IvyDocument |                |
+------------------------+------------------------------------------------------+----------------+
|**Result**                                                                                      |
+------------------------+------------------------------------------------------+----------------+
| streamedContent        | org.primefaces.model.StreamedContent                 |                |
+------------------------+------------------------------------------------------+----------------+
| status                 | java.lang.String                                     | OK or SKIP     |
+------------------------+------------------------------------------------------+----------------+

The parameters of the callable subprocess data contain the Ivy ``document`` you want to download.

Portal will call subprocesses with the above details in the same security context and then
get the first result where ``status`` is ``"OK"``.

Delete document
---------------

To customize behavior when a user deletes a document from **Document Table**,
create a callable subprocess with:

**Signature**: portalDeleteDocumentItem

+------------------------+------------------------------------------------------+----------------+
| Name                   | Type                                                 | Note           |
+========================+======================================================+================+
| **Parameter**                                                                                  |
+------------------------+------------------------------------------------------+----------------+
| businessCase           | ch.ivyteam.ivy.workflow.ICase                        |                |
+------------------------+------------------------------------------------------+----------------+
| document               | com.axonivy.portal.components.ivydata.bo.IvyDocument |                |
+------------------------+------------------------------------------------------+----------------+
|**Result**                                                                                      |
+------------------------+------------------------------------------------------+----------------+
| message                | java.lang.String                                     |                |
+------------------------+------------------------------------------------------+----------------+
| status                 | java.lang.String                                     | OK or SKIP     |
+------------------------+------------------------------------------------------+----------------+

The parameters of the callable subprocess data contain the Ivy ``document`` to delete.

Portal will call subprocesses with the above details in the same security context and then
get the first result where ``status`` is ``"OK"``.

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

Please read the :ref:`Customize <components-portal-components-migrate-from-old-document-table>` section for more details.

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