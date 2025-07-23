.. _full-case-list-ja:

全ケースリスト
*********************************

**全ケースリスト** ページは、1 つの全幅タスクウィジェットを含むダッシュボードです。 
|ivy| Portal のメニューにある |case-icon| :guilabel:`ケース` リンクを使用して、ページにアクセスできます。

.. note::
   |case-icon| :guilabel:`ケース` が表示されない場合は、:bdg-ref-warning:`🔑AccessFullCaseList <AccessFullCaseList>` 権限を付与してください。

                     
.. hint::

   ポータルに表示されるケースは、アプリケーションで保有しているロールによって異なります。 

|navigate-to-full-cases-list-page|

リストには、ケースごとに以下の情報が表示されます。

#. ケース ID

#. 名前

#. 説明

#. 作成者

#. 作成日

#. 終了日

#. 状態

#. カテゴリー

|case-key-information|

各行の終わりにある :guilabel:`アクション` をクリックすると、ケースを処理する機能が表示されます。

ケースデータ全体にアクセスするには、ケースの名前／説明をクリックします。

|case-details|

ケースの詳細は、以下の 4 つのセクションに別れています。

#. データと説明：ケースとその説明のメタデータが表示されます。
   |case-details-data-description|

#. 関連する実行中のタスクとケース：関連するすべての実行中のケースとタスクのリストが表示されます。
   マウスをタスクの上に移動すると、状態と責任ユーザーの両方が表示されます。
   関連するタスクをすべて表示するには、以下の「ハウツー」を参照してください。
   |case-details-related-tasks-cases|

#. 履歴：|ivy| Portal は、ケースの状態の変更に関するノートをここに追加します。
   以下のとおり、独自のノートを追加できます。
   |case-details-histories|

#. ドキュメント：ここには、ケースに添付されたドキュメントが表示されます。以下の「ハウツー」のとおり、ドキュメントを追加、削除、プレビューできます。
   |case-details-documents|



ハウツー：ケースの詳細のウィジェットを移動し、サイズを変更する
-----------------------------------------------------------------------------------------------

#. ケースの詳細を開きます。

#. :guilabel:`編集` ボタンをクリックして、ウィジェットをドラッグ＆ドロップし、サイズを変更します。
   |how-to-switch-to-edit-mode|

#. **保存** ボタンをクリックして、ページの現在の状態を保存し、読み取り専用モードに切り替えます。
   |how-to-switch-to-view-mode|
   
#. **リセット** ボタンをクリックして、ウィジェットをデフォルト設定にリセットします。


ハウツー：ケースにドキュメントを添付する
---------------------------------------------------------------

#. ケースの詳細を開きます。

#. |add-icon| :guilabel:`ドキュメントを追加` をクリックします。 

#. :guilabel:`ドキュメントを追加` ダイアログが開きます。

#. :guilabel:`ここにアップロード` をクリックするか、ファイルをダイアログにドラッグして、ファイルをアップロードします。
   
#. :guilabel:`閉じる` をクリックしてダイアログを閉じます。
   |how-to-attach-document-to-case|

.. note::

   ユーザーがケースドキュメントを作成できるようにするには、:bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>` または :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>` のいずれかの権限を付与してください。



ハウツー：ケースから添付ファイルを削除する
----------------------------------------------------------------------------------

#. ケースの詳細を開きます。

#. 削除したい添付ファイルの隣にある |delete-icon| ボタンをクリックします。

#. 確認ダイアログが開きます。

#. :guilabel:`削除` をクリックして添付ファイルを削除します。
   |how-to-delete-an-attachment-from-case|

.. note::
   ユーザーがケースから添付ファイルを削除できるようにするには、:bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>` または :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>` のいずれかの権限を付与してください。


ハウツー：ケースのドキュメントをプレビューする
----------------------------------------------------------------------------------------

#. ケースの詳細を開きます。

#. プレビューしたい添付ファイルの隣にある |preview-icon| ボタンをクリックします。

#. プレビューダイアログが開きます。

#. :guilabel:`閉じる` ボタンをクリックしてダイアログを閉じます。
   |how-to-preview-document|

.. note::
   ポータルでは、画像、プレーンテキスト（txt と log）、pdf ドキュメントをプレビューできます。

   この機能を有効にするには、:ref:`ポータル設定 <update-portal-settings-ja>` の :guilabel:`Portal.Document.EnablePreview` を true に変更します。



ハウツー：ケースにノートを追加する
---------------------------------------------------------------------------------

#. ケースの詳細を開きます。

#. ノートセクションの下にある |add-icon| ``ノートを追加`` リンクをクリックします。

#. :guilabel:`ノートを追加` ダイアログが開きます。

#. ノートを入力します。

#. ノートを保存するには、:guilabel:`保存` をクリックします。

   |how-to-add-task-note|

.. note::
   テクニカルケースのノートは、ケースの ``ノート`` 表とそのタスクに表示されます。

   ビジネスケースのノートの表示に関する規則：
   
      - ケースの詳細とその直接タスクの ``履歴`` 表に表示されます。
      - そのテクニカルケースの詳細の ``履歴`` 表に表示されます。
      - テクニカルケースに属するタスクの ``ノート`` 表には表示されません。
   
   ユーザーがケースにノートを追加できるようにするには、:bdg-ref-warning:`🔑TaskCaseAddNote <TaskCaseAddNote>` 権限を付与してください。

ハウツー：ケース履歴をエクスポートする

#. ケースの詳細を開きます。

#. ノートセクションの下にある |show-more-icon| :guilabel:`さらに表示` をクリックします。
   |how-to-show-note-details|

#. ケース履歴を含むページが開きます。

#. ケース履歴をエクスポートするには、:guilabel:`Excel にエクスポート` をクリックします。 
   |export-case-history|



ハウツー：ケースの詳細の関連するタスクを処理する
------------------------------------------------------------------------------------------------

#. ケースの詳細を開きます。

#. **ケースの関連タスク** までスクロールします。 


このセクションには、このケースの関連タスクが表示されます。

.. hint::
   ここに表示されるタスクは、アプリケーションと |ivy| Portal の両方で付与されたロールによって異なります。 
   管理者はすべてのオープンなタスクを表示できます。

.. note::   
   ユーザーがケースのすべての関連タスクを表示できるようにするには、:bdg-ref-warning:`🔑TaskReadOwnCaseTasks <TaskReadOwnCaseTasks>` または :bdg-ref-warning:`🔑TaskReadAll <TaskReadAll>` のいずれかの権限を付与してください。

リストには、タスクごとに以下の情報が表示されます。表示される列を変更するには、:guilabel:`列の管理` をクリックします。
以下の列から選択します。


- 優先度

- 名前

- 説明

- 責任ユーザーまたはロール

- タスク ID

- 作成日

- 有効期限

- 終了日

- 状態

各行の終わりにサイドステップメニューが表示されます。メニューには以下のアクションが表示されます。


- タスクをリセット

- タスクを委任する

- タスクを予約

- タスクを破棄

- エスカレーションタスクのトリガー

- ワークフローイベント

タスクデータ全体にアクセスするには、タスクを含む行をクリックします。


ハウツー：ケースの詳細の関連するケースを処理する
---------------------------------------------------------------------------------------

#. ケースの詳細を開きます。

#. **関連するケース** セクションまでスクロールします。このセクションは、ケースに関連するケースがある場合のみ表示されます。
   
|case-details|

以下の情報が自動的に表示されます。変更するには、:guilabel:`列の管理` をクリックして選択します。

- 名前

- 説明

- ケース ID

- 作成者

- 作成日

- 終了日

- 状態

- カテゴリー

アクション列には、関連するケースを処理する以下のアクションが表示されます。

- ケースの詳細

- ビジネスの詳細

- サイドステップ

関連するケースの詳細を表示するには、その行をクリックします。関連するケースのリストをエクスポートするには、:guilabel:`Excel にエクスポート` をクリックします。

.. _how-to-show-process-viewer-ja:

ハウツー：プロセスビューアーを表示する
-----------------------------------------------------------------

| ポータルは、現在のケースまたはタスクのプロセスフローを視覚的に表現します。ビューアーを開くには、:guilabel:`アクション` メニューで :guilabel:`プロセスビューアー` を選択します。
| このオプションは以下のページに表示されます。

   - ケースの詳細
   - タスクの詳細
   - 全ケースリスト
   - 全タスクリスト

#. :guilabel:`アクション` をクリックします。

#. :guilabel:`プロセスビューアー` をクリックします。

#. ブラウザの新しいタブが開きます。プロセスビューアーが表示されます。
   |portal-process-viewer|


ハウツー：ケースの詳細を共有する
------------------------------------------------------------------------

#. ケースの詳細を開きます。

#. 共有ボタン |share-icon| をクリックします。

   |how-to-share-case-details|

.. note::
   
   ユーザーがケースの詳細を共有できるようにするには、:bdg-ref-warning:`🔑ShareCaseDetailsLink <ShareCaseDetailsLink>` 権限を付与してください。


.. include:: ../includes/_common-icon.rst

.. |navigate-to-full-cases-list-page| image:: ../../screenshots/case/navigate-to-full-cases-list-page.png
   :alt: Menu to navigate to the Full case list page
.. |case-key-information| image:: ../../screenshots/case/case-key-information.png
   :alt: Full case list: key informations
.. |case-details| image:: ../../screenshots/case-detail/case-details.png
   :alt: The Case Details page
.. |case-details-data-description| image:: ../../screenshots/case-detail/case-details-data-description.png
   :alt: Case Details: General section
.. |case-details-related-tasks-cases| image:: ../../screenshots/case-detail/case-details-related-tasks-cases.png
   :alt: Case Details: Related Tasks of Case section
.. |case-details-histories| image:: ../../screenshots/case-detail/case-details-histories.png
   :alt: Case Details: Histories section
.. |case-details-documents| image:: ../../screenshots/case-detail/case-details-documents.png
   :alt: Case Details: Documents section
.. |how-to-attach-document-to-case| image:: ../../screenshots/case-detail/how-to-attach-document-to-case.png
   :alt: Case Details: Add document dialog
.. |how-to-delete-an-attachment-from-case| image:: ../../screenshots/case-detail/how-to-delete-an-attachment-from-case.png
   :alt: Case Details: Delete document confirm dialog
.. |how-to-add-task-note| image:: ../../screenshots/case-detail/how-to-add-task-note.png
   :alt: Case Details: Add note dialog
.. |how-to-show-note-details| image:: ../../screenshots/case-detail/how-to-show-note-details.png
   :alt: Case Details: Link to show more notes details
.. |export-case-history| image:: ../../screenshots/case/export-case-history.png
   :alt: Case Business Information page: Link to export case history
.. |how-to-switch-to-view-mode| image:: ../../screenshots/case-detail/how-to-switch-to-view-mode.png
   :alt: Case Details: save button
.. |how-to-switch-to-edit-mode| image:: ../../screenshots/case-detail/how-to-switch-to-edit-mode.png
   :alt: Case Details: button to switch to the edit mode
.. |how-to-reset-to-default| image:: ../../screenshots/case-detail/how-to-reset-to-default.png
   :alt: Case Details: button to reset to the default configurations
.. |portal-process-viewer| image:: ../../screenshots/case/portal-process-viewer.png
   :alt: Process Viewer page
.. |how-to-share-case-details| image:: ../../screenshots/case-detail/share-page-button.png
   :alt: Case Details: button to get the shareable link of the case details
.. |how-to-preview-document| image:: ../../screenshots/case-detail/how-to-preview-document.png
