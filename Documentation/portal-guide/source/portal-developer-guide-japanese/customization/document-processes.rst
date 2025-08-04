.. _customization-document-processes-ja:

ドキュメントのプロセス
==============================================

.. _customization-document-processes-introduction-ja:

概要
-----------------

ドキュメントをアップロードし、ドキュメント管理システム（DMS）など、Ivy の外部で管理したい場合は、このセクションに従って、ポータルのドキュメント機能をカスタマイズしてください。



.. _customization-document-processes-customization-ja:

カスタマイズ
--------------------------------
以下の情報を使用して、プロジェクトで 4 つの呼び出し可能サブプロセスを作成し、ドキュメントの機能をカスタマイズします。


ドキュメントリストの取得
+++++++++++++++++++++++++++++++++++++++++++++++++

ポータルのドキュメントの取得方法をカスタマイズします。以下を使用して呼び出し可能サブプロセスを作成します。

**シグネチャ**：portalGetDocumentList

.. csv-table::
   :file: tables/portal-get-document-list.csv
   :widths: 30, 70
   :header-rows: 1

.. note::

   DMS からドキュメントリストを取得したら、 ``List<ch.ivy.addon.portal.component.ivydata.bo.IvyDocument>`` に変換してください。
   ``id``、 ``name``、 ``contentType`` は、マッピング時の必須フィールドです。

ドキュメントのアップロード
++++++++++++++++++++++++++++++++++++++++++++

ユーザーがドキュメントをアップロードする際のポータルの動作をカスタマイズします。以下を使用して呼び出し可能サブプロセスを作成します。


**シグネチャ**：portalUploadDocument

.. csv-table::
   :file: tables/portal-upload-document.csv
   :widths: 30, 50, 20
   :header-rows: 1

ドキュメントのダウンロード
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

ユーザーがポータルからドキュメントをダウンロードする際の動作をカスタマイズします。以下を使用して呼び出し可能サブプロセスを作成します。


**シグネチャ**：portalDownloadDocument

.. csv-table::
   :file: tables/portal-download-document.csv
   :widths: 30, 70
   :header-rows: 1


ドキュメントの削除
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

ユーザーがポータルからドキュメントを削除する際の動作をカスタマイズします。以下を使用して呼び出し可能サブプロセスを作成します。


**シグネチャ**：portalDeleteDocument

.. csv-table::
   :file: tables/portal-delete-document.csv
   :widths: 30, 70
   :header-rows: 1

