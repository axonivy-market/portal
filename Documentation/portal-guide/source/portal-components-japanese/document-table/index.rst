.. _components-portal-components-document-table-ja:

ドキュメントテーブル
**************************************

このコンポーネントは、ドキュメントのエントリの表示、アップロード、プレビュー、ダウンロード、削除機能を持つケースドキュメントテーブルです。


|document-table|

属性
^^^^
.. csv-table::
  :file: ../documents/document_table_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

ウイルススキャン
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

**ドキュメントテーブル** には、ファイルをシステムにアップロードする前に、ファイル内の有害なスクリプトやウイルスをチェックするオプションが用意されています。

   - 有害なスクリプトをチェックするには、 ``enableScriptCheckingForUploadedDocument`` を ``true`` に設定します。
   - ウイルススキャンを有効にするには、 ``enableVirusScannerForUploadedDocument`` を ``true`` に設定します。

コードの例：

.. code-block:: html

   <ic:com.axonivy.portal.components.DocumentTable id="document-table-component"
      enableScriptCheckingForUploadedDocument="true"
      enableVirusScannerForUploadedDocument="true" />

ウイルススキャンの詳細については、:ref:`settings-virus-scanning-setting` を参照してください。

.. _components-portal-components-migrate-from-old-document-table-ja:

カスタマイズ
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

以下の情報を使用して、プロジェクトで 4 つの呼び出し可能サブプロセスを作成し、 **ドキュメントテーブル** の機能をカスタマイズします。


.. tip::

   ``portal-component-examples`` プロジェクトの ``CustomDocumentFeatures`` プロセスを参照して、カスタマイズ方法を確認できます。
   

ドキュメントリストの取得
----------------------------------------------

**ドキュメントテーブル** のドキュメントの取得方法をカスタマイズするには、以下を使用して呼び出し可能サブプロセスを作成します。

**シグネチャ** ：portalGetDocumentItems


+------------------------+----------------------------------------------------------------------+
| 名前                   | タイプ                                                               |
+========================+======================================================================+
| **パラメーター**                                                                              |
+------------------------+----------------------------------------------------------------------+
| businessCase           | ch.ivyteam.ivy.workflow.ICase                                        |
+------------------------+----------------------------------------------------------------------+
|**結果**                                                                                       |
+------------------------+----------------------------------------------------------------------+
| documents              | java.util.List<com.axonivy.portal.components.ivydata.bo.IvyDocument> |
+------------------------+----------------------------------------------------------------------+
| message                | java.lang.String                                                     |
+------------------------+----------------------------------------------------------------------+

.. note::

   DMS からドキュメントリストを取得したら、 ``List<ch.ivy.addon.portal.component.ivydata.bo.IvyDocument>`` に変換してください。
   ``id``、 ``name``、 ``contentType`` をマッピングする場合、これらのフィールドは必須です。

ドキュメントのアップロード
--------------------------------------------------

ユーザーがドキュメントをアップロードする際の **ドキュメントテーブル** の動作をカスタマイズするには、以下を使用して呼び出し可能サブプロセスを作成します。


**Signature**: portalUploadDocumentItem

+-----------------------------------------+-----------------------------------------------+---------------+
| 名前                                    | タイプ                                        | 注記          |
+=========================================+===============================================+===============+
| **パラメーター**                                                                                        |
+-----------------------------------------+-----------------------------------------------+---------------+
| businessCase                            | ch.ivyteam.ivy.workflow.ICase                 |               |
+-----------------------------------------+-----------------------------------------------+---------------+
| uploadedFile                            | org.primefaces.model.file.UploadedFile        |               |
+-----------------------------------------+-----------------------------------------------+---------------+
| enableScriptCheckingForUploadedDocument | java.lang.Boolean                             |               |
+-----------------------------------------+-----------------------------------------------+---------------+
| enableVirusScannerForUploadedDocument   | java.lang.Boolean                             |               |
+-----------------------------------------+-----------------------------------------------+---------------+
| allowedUploadFileTypes                  | java.lang.String                              |               |
+-----------------------------------------+-----------------------------------------------+---------------+
| **結果**                                                                                                |
+-----------------------------------------+-----------------------------------------------+---------------+
| uploadedDocument                        | ch.ivyteam.ivy.workflow.document.IDocument    |               |
+-----------------------------------------+-----------------------------------------------+---------------+
| message                                 | java.lang.String                              |               |
+-----------------------------------------+-----------------------------------------------+---------------+
| status                                  | java.lang.String                              | OK または FAIL|
+-----------------------------------------+-----------------------------------------------+---------------+

ドキュメントのダウンロード
------------------------------------------------------------

ユーザーが **ドキュメントテーブル** からドキュメントをダウンロードする際の動作をカスタマイズするには、以下を使用して呼び出し可能サブプロセスを作成します。


**シグネチャ**：portalDownloadDocumentItem

+------------------------+------------------------------------------------------+
| 名前                   | タイプ                                               |
+========================+======================================================+
|**パラメーター**                                                               |
+------------------------+------------------------------------------------------+
| businessCase           | ch.ivyteam.ivy.workflow.ICase                        |
+------------------------+------------------------------------------------------+
| document               | com.axonivy.portal.components.ivydata.bo.IvyDocument |
+------------------------+------------------------------------------------------+
|**結果**                                                                       |
+------------------------+------------------------------------------------------+
| streamedContent        | org.primefaces.model.StreamedContent                 |
+------------------------+------------------------------------------------------+

ドキュメントの削除
------------------------------------------------------

ユーザーが **ドキュメントテーブル** からドキュメントを削除する際の動作をカスタマイズするには、以下を使用して呼び出し可能サブプロセスを作成します。


**シグネチャ**：portalDeleteDocumentItem

+------------------------+------------------------------------------------------+
| 名前                   | タイプ                                               |
+========================+======================================================+
|**パラメーター**                                                               |
+------------------------+------------------------------------------------------+
| businessCase           | ch.ivyteam.ivy.workflow.ICase                        |
+------------------------+------------------------------------------------------+
| document               | com.axonivy.portal.components.ivydata.bo.IvyDocument |
+------------------------+------------------------------------------------------+
|**結果**                                                                       |
+------------------------+------------------------------------------------------+
| message                | java.lang.String                                     |
+------------------------+------------------------------------------------------+

ユーザーインターフェース
------------------------------------------------

機能だけでなく、新しい列の追加やデフォルトの列の削除など、 **ドキュメントテーブル** の UI もカスタマイズできます。


コードの例：

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

   詳細については、 ``portal-components-examples`` プロジェクトの ``DocumentTableExample`` プロセスを参照してください。

移行に関する注記
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

10.0.x から 10.0.12 への移行
---------------------------------------------------------------
このバージョンから、 **ドキュメントテーブル** の機能のプロセスのオーバーライドがサポート対象外になりました。
以下のガイドラインに従って、サブプロセスのオーバーライドを移行してください。

   - プロジェクトから ``GetDocumentItems``、 ``UploadDocumentItem``、 ``DeleteDocumentItem``、 ``DownloadDocumentItem`` のサブプロセスのオーバーライドを削除します。
     

   - 以下のとおり、呼び出し可能な開始サブプロセスのシグネチャを変更します。

      +----------------------+----------------------------+
      | サブプロセス         | 新しいシグネチャ           |
      +======================+============================+
      | GetDocumentItems     | portalGetDocumentItems     |
      +----------------------+----------------------------+
      | UploadDocumentItem   | portalUploadDocumentItem   |
      +----------------------+----------------------------+
      | DeleteDocumentItem   | portalDownloadDocumentItem |
      +----------------------+----------------------------+
      | DownloadDocumentItem | portalDeleteDocumentItem   |
      +----------------------+----------------------------+

**ドキュメントテーブル** がこれまでと同じように動作するはずです。

10.0.0 への移行
------------------------------

#. HTML ファイルのコードを置き換えます。 ``ic:ch.ivy.addon.portalkit.component.document.DocumentTable`` を ``ic:com.axonivy.portal.components.DocumentTable`` に置き換えてください。

#. 必要に応じて、新しい ``enableScriptCheckingForUploadedDocument``、 ``enableVirusScannerForUploadedDocument``、 ``allowedUploadFileTypes`` 属性の値の更新を検討します。

#. 必要な場合はサブプロセスをオーバーライドし、ビジネスをそれに合わせます。

   +--------------------------+--------------------------+
   | 新しいサブプロセス       | 非推奨のサブプロセス     |
   +==========================+==========================+
   | GetDocumentItems         | GetDocumentList          |
   +--------------------------+--------------------------+
   | UploadDocumentItem       | UploadDocument           |
   +--------------------------+--------------------------+
   | DeleteDocumentItem       | DeleteDocument           |
   +--------------------------+--------------------------+
   | DownloadDocumentItem     | DownloadDocument         |
   +--------------------------+--------------------------+

#. IvyDocument をカスタマイズした場合は、必ず ``com.axonivy.portal.components.ivydata.bo.IvyDocument`` クラスから拡張してください。

#. ``typeSelectionItems`` と ``selectedType`` 属性が ``com.axonivy.portal.components.enums.DocumentType`` を使用するようになりました。
   ``ch.ivy.addon.portalkit.enums.DocumentType`` を ``com.axonivy.portal.components.enums.DocumentType`` に置き換えてください。

   .. note::
      GetDocumentListOverride、UploadDocumentOverride など、オーバーライドされた重複する設定、サブプロセス、データクラスを削除してください。
      

.. |document-table| image:: ../../screenshots/components/document-table.png
