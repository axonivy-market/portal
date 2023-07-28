.. _components-portal-components-document-table:

Document Table
**************

This component is a case document table with functions: display, upload,
download and delete document entries.

|document-table|

Attributes
^^^^^^^^^^
.. csv-table::
  :file: ../documents/document_table_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

Script checking and virus scanning
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

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

Customization
^^^^^^^^^^^^^

From your project, create four callable sub processes with the below information to customize
the functions of the **Document Table**.

.. tip::

   You can refer to process ``CustomDocumentFeatures`` in project ``portal-component-examples``
   to examnine how to customize.

Get document list
-----------------

Customize how **Document Table** gets documents, create a callable subprocess with:

**Signature**: customGetDocumentItems

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
---------------

Customize what **Document Table** should do when a user uploads a document,
create a callable subprocess with:

**Signature**: customUploadDocumentItem

+-----------------------------------------+---------------------------------------------------------------+
| Name                                    | Type                                                          |
+=========================================+===============================================================+
| **Parameter**                                                                                           |
+-----------------------------------------+---------------------------------------------------------------+
| businessCase                            | ch.ivyteam.ivy.workflow.ICase                                 |
+-----------------------------------------+---------------------------------------------------------------+
| uploadedFile                            | org.primefaces.model.file.UploadedFile                        |
+-----------------------------------------+---------------------------------------------------------------+
| enableScriptCheckingForUploadedDocument | java.lang.Boolean                                             |
+-----------------------------------------+---------------------------------------------------------------+
| enableVirusScannerForUploadedDocument   | java.lang.Boolean                                             |
+-----------------------------------------+---------------------------------------------------------------+
| allowedUploadFileTypes                  | java.lang.String                                              |
+-----------------------------------------+---------------------------------------------------------------+
| **Result**                                                                                              |
+-----------------------------------------+---------------------------------------------------------------+
| uploadedDocument                        | ch.ivyteam.ivy.workflow.document.IDocument                    |
+-----------------------------------------+---------------------------------------------------------------+
| message                                 | java.lang.String                                              |
+-----------------------------------------+---------------------------------------------------------------+
| status                                  | com.axonivy.portal.components.enums.UploadDocumentCheckStatus |
+-----------------------------------------+---------------------------------------------------------------+

Download document
-----------------

Customize behavior when a user downloads a document from **Document Table**,
create a callable subprocess with:

**Signature**: customDownloadDocumentItem

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
---------------

Customize behavior when an user deletes a document from **Document Table**,
create a callable subprocess with:

**Signature**: customDeleteDocumentItem

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

User interface
--------------

Not only the functions, but you can also customize the UI of the **Document Table**
such as adding a new column or removing default columns.

Code Example:

.. code-block:: html

   <h:form id="form">
      <ic:com.axonivy.portal.components.DocumentTable id="document-table-component"
         allowedUploadFileTypes="doc,docx,xls,xlsx,xlsm,csv,pdf,ppt,pptx,txt"
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
Please do as below to migrate your override sub processes

   - Remove Sub Process Override of ``GetDocumentItems``, ``UploadDocumentItem``,
     ``DeleteDocumentItem``, and ``DownloadDocumentItem`` from your project.

   - Change the signature of your callable starts as below.

      +----------------------+----------------------------+
      | Sub process          | New signature              |
      +======================+============================+
      | GetDocumentItems     | customGetDocumentItems     |
      +----------------------+----------------------------+
      | UploadDocumentItem   | customUploadDocumentItem   |
      +----------------------+----------------------------+
      | DeleteDocumentItem   | customDownloadDocumentItem |
      +----------------------+----------------------------+
      | DownloadDocumentItem | customDeleteDocumentItem   |
      +----------------------+----------------------------+

Now your **Document Table** should work as before.

Migrate to 10.0.0
-----------------

#. Replace code in HTML files: replace ``ic:ch.ivy.addon.portalkit.component.document.DocumentTable`` with ``ic:com.axonivy.portal.components.DocumentTable``.

#. Consider updating value of the new attributes ``enableScriptCheckingForUploadedDocument``, ``enableVirusScannerForUploadedDocument`` and ``allowedUploadFileTypes`` as required.

#. Override subprocesses if you want and adapt your business accordingly.

   +-----------------------------------+--------------------------+
   | New sub process                   | Deprecated sub process   |
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