.. _dashboard-configuration-ja:

ダッシュボード設定
****************************************

.. hint::
   :bdg-ref-warning:`🔑DashboardWriteOwn <DashboardWriteOwn>` または :bdg-ref-warning:`🔑DashboardWritePublic <DashboardWritePublic>` 権限を持つユーザーのみ、:guilabel:`ダッシュボード設定` ユーザーメニュー項目を表示してアクセスできます。
   個人用または公開用ダッシュボードの追加、編集、並べ替え、表示、非表示を行うことができます。

:guilabel:`ダッシュボード設定` ユーザーメニュー項目を選択します。

|dashboard-configuration|

:guilabel:`ダッシュボード設定` ページには、アプリケーションで保有している権限によって、個人用ダッシュボードタブと公開用ダッシュボードタブが表示されます。
使用方法については、この章の「ハウツー」で説明します。

|dashboard-configuration-page|

個人用ダッシュボードの設定
--------------------------------------------------

:guilabel:`個人用ダッシュボード` タブは、:bdg-ref-warning:`🔑DashboardWriteOwn <DashboardWriteOwn>` 権限を持つユーザーにのみ表示されます。タブでは、個人用ダッシュボードを追加または編集し、表示されているすべてのダッシュボードを並べ替えることができます。


|private-dashboard-configuration|

.. _howto-add-private-dashboard-ja:

ハウツー：個人用ダッシュボードを追加する
=======================================================================

#. :guilabel:`新規ダッシュボードを追加` ボタンを選択します。

#. 追加するテンプレートを 1 つ選択します（:ref:`利用可能なダッシュボードのテンプレート <private-available-dashboard-template-ja>` を参照）。

#. :guilabel:`新しい個人用ダッシュボードを作成` ダイアログが開きます。

#. 個人用ダッシュボードのタイトル（必須）と説明（任意）を入力します。

#. |add-icon| （ダッシュボードの作成）を選択して、個人用ダッシュボードを作成します。

#. 個人用ダッシュボードを設定します（:ref:`ダッシュボード <new-dashboard-ja>` を参照）。

#. ダッシュボードリストに戻ると、新しい個人用ダッシュボードが追加されています。

|create-private-dashboard-dialog|

ダッシュボードのタイトルの複数の言語を設定するには、:ref:`portal-multi-language-ja` ：利用可能なダッシュボードのテンプレート、:guilabel:`デフォルトのテンプレート` 、:guilabel:`2 つのタスクリストのダッシュボード` のテンプレート、:guilabel:`アクセシビリティーダッシュボード` のテンプレートを参照してください。

.. _private-available-dashboard-template-ja:


詳細については、以下の画像を参照してください。

|dashboard-templates|

.. hint::
   個人用ダッシュボードのリストには、自分が作成した個人用ダッシュボードのみ表示されます。

ハウツー：個人用ダッシュボードをインポートする
===========================================================================

#. :guilabel:`新規ダッシュボードを追加` ボタンを選択します。

#. |import-icon| （インポート）を選択します。

#. :guilabel:`個人用ダッシュボードをインポート` ダイアログが開きます。

#. ドラッグ＆ドロップするか、:guilabel:`ここにアップロード` リンクをクリックして、インポートしたいダッシュボードを含む JSON ファイルを選択します。

#. 個人用ダッシュボードのタイトル（必須）と説明（任意）を編集します。

#. :guilabel:`ダッシュボードの作成` ボタンを選択して、ダッシュボードをインポートします。

#. 個人用ダッシュボードを設定します（:ref:`ダッシュボード <new-dashboard-ja>` を参照）。

#. ダッシュボードリストに戻ると、新しい個人用ダッシュボードが追加されています。

|import-private-dashboard-dialog|

.. note::

   この機能を使用するには、:bdg-ref-warning:`🔑DashboardImportOwn <DashboardImportOwn>` 権限を付与してください。


ハウツー：個人用ダッシュボードを編集する
=========================================================================
:guilabel:`個人用ダッシュボードの編集` セクションには、個人用ダッシュボードが表示されます。
個人用ダッシュボードの表には、以下の情報が表示されます。

#. ダッシュボードのタイトル

#. ダッシュボードの説明

#. アクション： |actions-menu-icon| をクリックして、:guilabel:`アクション` メニューを表示します。メニューには、|edit-icon| 編集（名前、説明）、|settings-icon| 設定（:ref:`ダッシュボード <new-dashboard-ja>` を参照）、|download-icon| ダッシュボードをエクスポート、|trash-icon| 個人用ダッシュボードを削除アクションが表示されます。
   

|edit-icon| （編集）を選択して、個人用ダッシュボードを編集できます。

.. note::

   ユーザーの個人用ダッシュボードのエクスポート機能を有効にするには、:bdg-ref-warning:`🔑DashboardExportOwn <DashboardExportOwn>` 権限を付与してください。

|edit-private-dashboards|

.. _howto-reorder-your-dashboards-ja:

ハウツー：個人用ダッシュボードを並べ替える
==============================================================================

|reorder-dashboard-icon| アイコンをドラッグ＆ドロップして、ダッシュボードを並べ替えることができます。

|reorder-your-dashboards|

.. note::
   - 作成後は、個人の並べ替え順が維持されます。
   - 個人の並べ替え順を作成した後、順序を並べ替えることはできますが、個人の並べ替え順を削除することはできません。

.. hint::
   新しいダッシュボードを作成した場合、自動的に個人の並べ替え順の最後に追加されます。

公開用ダッシュボードの設定
------------------------------------------------

:guilabel:`公開用ダッシュボード` タブがユーザーに表示されるようにするには、:bdg-ref-warning:`🔑DashboardWritePublic <DashboardWritePublic>` 権限を付与します。タブでは、公開用ダッシュボードを追加、編集し、並べ替えることができます。


|public-dashboard-configuration|

.. _howto-add-public-dashboard-ja:

ハウツー：公開用ダッシュボードを追加する
=================================================================================

#. :guilabel:`新規ダッシュボードを追加` ボタンを選択します。

#. 追加するテンプレートを 1 つ選択します（ :ref:`利用可能なダッシュボードのテンプレート <public-available-dashboard-template-ja>` を参照）。

#. :guilabel:`新しい公開用ダッシュボードを作成` ダイアログが開きます。

#. タイトルと公開用ダッシュボードを表示する権限（必須）、公開用ダッシュボードの説明（任意）を入力します。
      `トップメニュー項目` チェックボックスがオンの場合、ダッシュボードはナビゲーションバーの最上位の項目として表示されます。 
      オフの場合は、 `ダッシュボード` メニュー項目の下にサブ項目として表示されます。

#. |add-icon| （ダッシュボードの作成）を選択して、公開用ダッシュボードを作成します。

#. 公開用ダッシュボードを設定します（:ref:`ダッシュボード <new-dashboard-ja>` を参照）。

#. ダッシュボードリストに戻り、新しい公開用ダッシュボードが追加されていることを確認します。

|create-public-dashboard-dialog|

ダッシュボードのタイトルの複数の言語を設定するには、:ref:`portal-multi-language-ja` ：利用可能なダッシュボードのテンプレート、:guilabel:`デフォルトのテンプレート` 、:guilabel:`2 つのタスクリストのダッシュボード` のテンプレート、:guilabel:`アクセシビリティーダッシュボード` のテンプレートを参照してください。

.. _public-available-dashboard-template-ja:


|dashboard-templates|

.. hint::
   ダッシュボードのロールによって、他の公開用ダッシュボードがダッシュボードリストに表示されることがあります。
    
.. _howto-import-your-public-dashboards-ja:

ハウツー：公開用ダッシュボードをインポートする
=========================================================================

#. :guilabel:`新規ダッシュボードを追加` ボタンを選択します。

#. |import-icon| （インポート）を選択します。

#. :guilabel:`公開用ダッシュボードをインポート` ダイアログが開きます。

#. ドラッグ＆ドロップするか、:guilabel:`ここにアップロード` リンクをクリックして、インポートしたいダッシュボードを含む JSON ファイルを選択します。

#. タイトルと公開用ダッシュボードを表示する権限（必須）、公開用ダッシュボードの説明（任意）を編集します。
   -  `トップメニュー項目` チェックボックスがオンの場合、ダッシュボードはナビゲーションバーの最上位の項目として表示されます。 
   オフの場合は、 `ダッシュボード` メニュー項目の下にサブ項目として表示されます。

#. :guilabel:`ダッシュボードの作成` ボタンを選択して、ダッシュボードをインポートします。

#. 公開用ダッシュボードを設定します（:ref:`ダッシュボード <new-dashboard-ja>` を参照）。

#. ダッシュボードリストに戻り、新しい公開用ダッシュボードが追加されていることを確認します。

|import-public-dashboard-dialog|

.. note::

   ユーザーが公開用ダッシュボードをインポートできるようにするには、:bdg-ref-warning:`🔑DashboardImportPublic <DashboardImportPublic>` 権限を付与してください。

ハウツー：公開用ダッシュボードを編集する
======================================================
:guilabel:`公開用ダッシュボードの編集` セクションには、公開用ダッシュボードが表示されます。
公開用ダッシュボードの表には、以下の情報が表示されます。

#. ダッシュボードのタイトル

#. ダッシュボードの権限

#. ダッシュボードの説明

#. トップメニューとして表示するかどうか

#. アクション：|actions-menu-icon| をクリックして、:guilabel:`アクション` メニューを表示します。メニューには、|edit-icon| 編集（名前、説明）、|settings-icon| 設定（:ref:`ダッシュボード <new-dashboard-ja>` を参照）、|download-icon| ダッシュボードをエクスポート、|share-icon| 共有、|trash-icon| 公開用ダッシュボードを削除アクションが表示されます。
   
   

|edit-icon| （編集）を選択して、公開用ダッシュボードを編集できます。 

.. note::

   ユーザーが公開用ダッシュボードをエクスポートできるようにするには、:bdg-ref-warning:`🔑DashboardExportPublic <DashboardExportPublic>` 権限を付与してください。

|edit-public-dashboards|


ハウツー：公開用ダッシュボードを共有する
==============================================================================

公開用ダッシュボードのリンクを生成するには、ダッシュボードの :guilabel:`アクション` メニューの |share-icon| （共有）アイコンをクリックします。

|share-public-dashboards|

.. note::
   メニューフレームなしでダッシュボードを共有するには、 ``openWithoutMenu=true`` パラメーターを URL に追加します。ただし、このオプションは埋め込み専用です。
   ユーザーがダッシュボードのリンクを共有できるようにするには、:bdg-ref-warning:`🔑ShareDashboardLink <ShareDashboardLink>` 権限を付与してください。

ハウツー：公開用ダッシュボードを並べ替える
===================================================================================

|reorder-dashboard-icon| アイコンをドラッグ＆ドロップして、ダッシュボードを並べ替えることができます。

|reorder-public-dashboards|

.. include:: ../includes/_common-icon.rst

.. |dashboard-configuration| image:: ../../screenshots/settings/dashboard-configuration.png
   :alt: ダッシュボード設定メニュー
.. |dashboard-configuration-page| image:: ../../screenshots/dashboard-configuration/dashboard-configuration-page.png
   :alt: ダッシュボード設定画面
.. |private-dashboard-configuration| image:: ../../screenshots/dashboard-configuration/private-dashboard-configuration.png
   :alt: 個人用ダッシュボードの設定
.. |create-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-private-dashboard-dialog.png
   :alt: 個人用ダッシュボードの作成ダイアログ
.. |edit-private-dashboards| image:: ../../screenshots/dashboard-configuration/edit-private-dashboards.png
   :alt: 個人用ダッシュボードの編集ダイアログ
.. |reorder-your-dashboards| image:: ../../screenshots/dashboard-configuration/reorder-your-dashboards.png
   :alt: 個人用ダッシュボードを並べ替える
.. |public-dashboard-configuration| image:: ../../screenshots/dashboard-configuration/public-dashboard-configuration.png
   :alt: 公開用ダッシュボードの設定
.. |create-public-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-public-dashboard-dialog.png
   :alt: 公開用ダッシュボードの作成ダイアログ
.. |edit-public-dashboards| image:: ../../screenshots/dashboard-configuration/edit-public-dashboards.png
   :alt: 公開用ダッシュボードの編集ダイアログ
.. |reorder-public-dashboards| image:: ../../screenshots/dashboard-configuration/reorder-public-dashboards.png
   :alt: 公開用ダッシュボードを並べ替える
.. |dashboard-templates| image:: ../../screenshots/dashboard-configuration/dashboard-templates.png
   :alt: ダッシュボードのテンプレートの選択ダイアログ
.. |import-public-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/import-public-dashboard-dialog.png
   :alt: 公開用ダッシュボードのインポートダイアログ
.. |import-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/import-private-dashboard-dialog.png
   :alt: 個人用ダッシュボードのインポートダイアログ
.. |share-public-dashboards| image:: ../../screenshots/dashboard-configuration/share-dashboard-dialog.png
   :alt: ダッシュボードの共有ダイアログ

