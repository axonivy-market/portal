.. _full-task-list-ja:

全タスクリスト
*********************************

**全タスクリスト** ページは、1 つの全幅タスクウィジェットを含むダッシュボードです。 
|ivy| Portal のメニューにある |task-list-icon| :guilabel:`タスク` リンクを使用して、ページにアクセスできます。

.. note::

   ユーザーが |ivy| Portal メニューで全タスクリスト（|task-list-icon| :guilabel:`タスク` ）にアクセスできるようにするには、:bdg-ref-warning:`🔑AccessFullTaskList <AccessFullTaskList>` 権限を付与してください。


.. hint:: 
   アプリケーションで保有しているロールと |ivy| Portal の設定によって、自分のタスクだけでなく、すべてのオープンなタスクも表示されます。
        

|navigate-to-full-task-list-page|

**全タスクリスト** ページの一番上に表示される見出しの :guilabel:`タスク` の隣に、表示されるタスクの総数がかっこ書きで表示されます。
その下にはフィルター機能が表示されます。
使用方法については、この章の「ハウツー」で説明します。
最後に、タスクのリストが表示されます。

タスクごとに以下の情報がリストに表示されます。

#. 優先度

#. タスク ID

#. 名前

#. 説明

#. 責任ユーザーまたはロール

#. 状態

#. 作成日

#. 有効期限

#. カテゴリー

#. アプリケーション（この列は自動的に表示されません）

|task-key-information|

さらに、各行の終わりにある`アクション`メニューには、タスクを処理する機能が表示されます。


#. 詳細

#. タスクをリセット

#. タスクを委任する

#. 予約

#. 破棄

#. エスカレーションのトリガー

#. ワークフローイベント

#. プロセスビューアー

.. note::

   ユーザーが対応するボタンとメニュー項目を表示できるようにするには、:bdg-ref-warning:`🔑TaskDisplayResetAction <TaskDisplayResetAction>`、:bdg-ref-warning:`🔑TaskDisplayWorkflowEventAction <TaskDisplayWorkflowEventAction>` 権限を付与してください。 
   :bdg-ref-warning:`🔑TaskDisplayReserveAction <TaskDisplayReserveAction>`, :bdg-ref-warning:`🔑TaskDisplayDelegateAction <TaskDisplayDelegateAction>`, 
   :bdg-ref-warning:`🔑TaskDisplayDestroyAction <TaskDisplayDestroyAction>`, :bdg-ref-warning:`🔑TaskDisplayAdditionalOptions <TaskDisplayAdditionalOptions>`,
   
最後に、 ``アクション`` をクリックし、 ``詳細`` メニュー項目をクリックして、すべてのタスクデータにアクセスできます。


タスクの詳細は、以下の 4 つのセクションに別れています。

#. 注意な必要な情報や対処すべき情報を表示するタスクステータスバナー。

   |task-status-banner|

#. データと説明：タスクとタスクに関連するケースのメタデータが表示されます。
   
   |detailed-task-information-data-description|

#. 履歴：以下に説明するノートを追加できます。

   |detailed-task-information-note|

#. ドキュメント：ケースに添付されたドキュメントはすべてのタスクで利用できます。
   以下の「ハウツー」のとおり、ドキュメントを追加、削除、プレビューできます。 
   

   |detailed-task-information-documents|

|detailed-task-information|

ハウツー：全タスクリストを設定する
-------------------------------------------------------------

全タスクリストページは、トップレベルメニューからアクセス可能なダッシュボードとして機能します。 
タスクリストをカスタマイズするには、:ref:`ダッシュボードの設定 <dashboard-configuration-ja>` に移動します。

メニューアイコン、タスクリストのレイアウトを含め、ダッシュボードを初期設定に戻すには、ダッシュボードを削除します。ポータルは自動的に初期設定でダッシュボードを作成し直します。 


ハウツー：タスクの詳細のウィジェットを移動し、サイズを変更する
--------------------------------------------------------------------------------------------------
#. タスクのタスクの詳細を開きます。

#. :guilabel:`編集` ボタンをクリックして、ウィジェットをドラッグ＆ドロップし、サイズを変更します。

   |how-to-switch-to-edit-mode|


#. :guilabel:`保存` ボタンをクリックして、ページの現在の状態を保存し、読み取り専用モードに切り替えます。

   |how-to-switch-to-view-mode|


#. :guilabel:`リセット` ボタンをクリックして初期設定にリセットします。

   |how-to-reset-to-default|


ハウツー：ケースにドキュメントを添付する
------------------------------------------------------------

#. タスクのタスクの詳細を開きます。
#. |add-icon| ``ドキュメントを追加`` リンクをクリックします。 

#. :guilabel:`ドキュメントを追加` ダイアログが開きます。

#. :guilabel:`ここにアップロード` をクリックするか、ファイルをダイアログにドラッグして、ファイルをアップロードします。   

#. :guilabel:`閉じる` ボタンをクリックしてダイアログを閉じます。

|how-to-upload-document|

.. note::

   ユーザーがドキュメントをアップロードできるようにするには、:bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>` または :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>` のいずれかの権限を付与してください。

ハウツー：ケースから添付ファイルを削除する
-------------------------------------------------------------

#. タスクのタスクの詳細を開きます。

#. 削除したい添付ファイルの隣にある |delete-icon| ボタンをクリックします。   

#. 確認ダイアログが開きます。

#. :guilabel:`削除` ボタンをクリックして、削除を確定します。 

|how-to-delete-document|

.. note::

   ユーザーがドキュメントを削除できるようにするには、:bdg-ref-warning:`🔑DocumentOfInvolvedCaseWrite <DocumentOfInvolvedCaseWrite>` または :bdg-ref-warning:`🔑DocumentWrite <DocumentWrite>` のいずれかの権限を付与してください。


ハウツー：ケースのドキュメントをプレビューする
--------------------------------------------------------------------------------

#. タスクのタスクの詳細を開きます。

#. プレビューしたい添付ファイルの隣にある |preview-icon| ボタンをクリックします。   

#. プレビューダイアログが開きます。

#. :guilabel:`閉じる` ボタンをクリックしてダイアログを閉じます。

|how-to-preview-document|

.. note::

   ポータルでは、画像、プレーンテキスト（txt と log）、pdf ドキュメントをプレビューできます。

   この機能を有効にするには、:ref:`ポータル設定 <update-portal-settings-ja>` の :guilabel:`Portal.Document.EnablePreview` を true に変更します。


ハウツー：タスクにノートを追加する
------------------------------------------------------

#. タスクのタスクの詳細を開きます。

#. ノートセクションの下にある |add-icon| ``ノートを追加`` リンクをクリックします。

#. :guilabel:`ノートを追加` ダイアログが開きます。

#. ノートを入力します。

#. :guilabel:`保存` ボタンをクリックして、ノートを確定します。

|how-to-add-note|

.. note::
   タスクのノートは、タスクの詳細の ``ノート`` 表と、タスクのビジネスケースの ``履歴`` 表に表示されます。

   タスクがテクニカルケースに属する場合、そのテクニカルケースの ``履歴`` 表にもノートが表示されます。
   
   ユーザーがタスクとケースのノートを追加できるようにするには、:bdg-ref-warning:`🔑TaskCaseAddNote <TaskCaseAddNote>` 権限を付与してください。

ハウツー：タスク履歴をエクスポートする
------------------------------------------------------

#. タスクのタスクの詳細を開きます。

#. ノートセクションの下にある |show-more-icon| ``さらに表示`` リンクをクリックします。

   |how-to-show-note-details|

#. タスク履歴を含む新しいページが開きます。

#. :guilabel:`Excel にエクスポート` ボタンをクリックして、履歴をエクスポートできます。 

   |how-to-export-note-details|


.. _how-to-show-workflow-events-ja:

ハウツー：ワークフローイベントを表示する
----------------------------------------------------------------------

.. note:: 
   ユーザーがタスクのすべてのワークフローイベントを表示できるようにするには、:bdg-warning:`🔑WorkflowEventReadAll` 権限を付与してください。

#. タスクのタスクの詳細を開きます。

#. **データと説明** セクションの右上隅にある ``アクション`` リンクをクリックします。

#. オーバーレイパネルの ``ワークフローイベント`` リンクをクリックして、ワークフローイベントダイアログを表示します。

**ワークフローイベントダイアログ**

|workflow-events-table|

ハウツー：タスクの詳細を共有する
----------------------------------------------------

#. タスクの詳細を開きます。

#. 共有ボタン |share-icon| をクリックします。

|how-to-share-task-details|

.. note::

   ユーザーがタスクの詳細を共有できるようにするには、:bdg-ref-warning:`🔑ShareTaskDetailsLink <ShareTaskDetailsLink>` 権限を付与してください。

.. include:: ../includes/_common-icon.rst


.. |navigate-to-full-task-list-page| image:: ../../screenshots/dashboard/expanded-left-menu.png
   :alt: 全タスクリストページに移動するメニュー
.. |task-key-information| image:: ../../screenshots/task/task-key-information.png
   :alt: 全タスクリスト：主な情報
.. |detailed-task-information-data-description| image:: ../../screenshots/task-detail/detailed-task-information-data-description.png
   :alt: タスクの詳細：一般セクション
.. |detailed-task-information-note| image:: ../../screenshots/task-detail/detailed-task-information-note.png
   :alt: タスクの詳細：ノートセクション
.. |detailed-task-information-documents| image:: ../../screenshots/task-detail/detailed-task-information-documents.png
   :alt: タスクの詳細：ドキュメントセクション
.. |detailed-task-information| image:: ../../screenshots/task-detail/detailed-task-information.png
   :alt: タスクの詳細ページ
.. |how-to-upload-document| image:: ../../screenshots/task-detail/how-to-upload-document.png
   :alt: タスクの詳細：ドキュメントの追加ダイアログ
.. |how-to-delete-document| image:: ../../screenshots/task-detail/how-to-delete-document.png
   :alt: タスクの詳細：ドキュメントの削除の確認ダイアログ
.. |how-to-add-note| image:: ../../screenshots/task-detail/how-to-add-note.png
   :alt: タスクの詳細：ノートの追加ダイアログ
.. |how-to-show-note-details| image:: ../../screenshots/task-detail/how-to-show-note-details.png
   :alt: タスクの詳細：ノートの詳細を表示するリンク
.. |how-to-export-note-details| image:: ../../screenshots/task-detail/how-to-export-note-details.png
   :alt: タスクのノートをエクスポートするリンク
.. |workflow-events-table| image:: ../../screenshots/task-detail/workflow-events-table.png
   :alt: タスクのワークフローイベントダイアログ
.. |how-to-switch-to-view-mode| image:: ../../screenshots/task-detail/how-to-switch-to-view-mode.png
   :alt: タスクの詳細：保存ボタン
.. |how-to-switch-to-edit-mode| image:: ../../screenshots/task-detail/how-to-switch-to-edit-mode.png
   :alt: タスクの詳細：編集モードに切り替えるボタン
.. |how-to-reset-to-default| image:: ../../screenshots/task-detail/how-to-reset-to-default.png
   :alt: タスクの詳細：デフォルト設定にリセットするボタン
.. |how-to-share-task-details| image:: ../../screenshots/task-detail/share-page-button.png
   :alt: タスクの詳細：タスクの詳細の共有可能なリンクを取得するボタン
.. |task-status-banner| image:: ../../screenshots/task-detail/task-status-banner.png
   :alt: タスクの詳細：ステータスバナー
.. |how-to-preview-document| image:: ../../screenshots/case-detail/how-to-preview-document.png
