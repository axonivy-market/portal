.. _new-dashboard-ja:

ダッシュボード
***********************************

概要
------------

|ivy| Portal ダッシュボードは、ログイン成功後に最初に表示されるページです。
|ivy| Portal のナビゲーションメニューにある **ダッシュボード** リンクを使用して、いつでもダッシュボードに戻ることができます。 

または、ヘッダーの左上にあるロゴ画像をクリックします。

ダッシュボード自体には、ユーザーの会社で定められた基準に従ってページに配置されたウィジェットが表示されます。
 

|dash-board|

以下のデフォルトのウィジェットが利用可能です。

#. **タスクリストウィジェット**：このウィジェットには、定義された設定に従って関連するタスク情報が表示されます。

#. **ケースリストウィジェット**：このウィジェットは、定義された設定に従って関連するケース情報を表示します。

#. **プロセスリストウィジェット**：このウィジェットには、利用可能なプロセス開始が表示されます。さまざまな形式を選択できます。

#. **プロセスビューアーウィジェット**：このウィジェットは、プロセスフローを視覚的に表現します。

#. **クライアントの統計チャートウィジェット**：このウィジェットは、クライアントの統計データをグラフで表示します。

#. **ウェルカムウィジェット**：このウィジェットは、より親しみやすいように、現地時間に合わせた挨拶文をユーザーに表示します。

#. **ニュースフィードウィジェット**：このウィジェットは、関連情報をニュースフィードとして |ivy| に共有します。

#. **外部ページウィジェット**：このウィジェットは、外部ウェブページをダッシュボードに表示します。

#. **通知ウィジェット**：このウィジェットには、通知設定に基づいてすべての通知が表示されます。

さらに、開発者は、カスタムウィジェットを作成して、関連するプロジェクトの情報をユーザーのダッシュボードに追加できます。

**タスクリストウィジェット、ケースリストウィジェット、プロセスリストウィジェット（コンパクトモード）** には、以下が標準で表示されます。

   - 保存されたフィルターとフィルターオプション

   |widget-filter|

   - ウィジェット情報

   |widget-info|

権限がある場合は、ダッシュボードの右上隅にある :guilabel:`編集` ボタンを使用して、ウィジェットのサイズを変更し、並べ替え、作成、削除できます。


|edit-widget|

編集モードでは、以下を行うことができます。

   - **ドラッグ＆ドロップによるウィジェットの移動**：移動したいウィジェットをクリックし、再びドロップします。補助線を目安にしてください。

   - **既存のウィジェットの編集**：:guilabel:`編集` ボタンをクリックして設定パネルを開き、ウィジェットを編集します。

   - **既存のウィジェットの削除**：ウィジェットが削除されます。

   - **新しいウィジェットの追加**：ウィザードに従って、新しいウィジェットを作成し、ダッシュボードに追加します。

   - **標準ダッシュボードへのリセット**：変更を取り消したい場合は、デフォルトのダッシュボードにリセットします。

   - **列の幅の変更（タスクリスト／ケースリストウィジェット）**：サイズを変更したいヘッダーのグリッド線をクリックし、再びドロップします。補助線を目安にしてください。

新しいウィジェットの追加
--------------------------------------------------------

編集モードで新しいウィジェットを追加するには、:guilabel:`+ ウィジェットの追加` ボタンを押し、追加するウィジェットを 1 つ選択します。


|add-widget|

.. _new-dashboard-task-list-widget-ja:

タスクリストウィジェット
===================================================

タスクリストウィジェットを追加すると、知りたい情報を好きな方法で確認できます。


|task-list-widget|

ウィジェット設定パネルでウィジェット名を定義し、クイック検索機能を有効にし、ウィジェット情報と全画面モードのアイコンの表示／非表示を切り替え、列とフィルターを管理し、列のヘッダーをクリックして表の列の並べ替え順を編集できます。



**フィルターパネル**

:guilabel:`フィルター` ボタンをクリックして、ウィジェットの複合フィルターを設定し、:guilabel:`適用` ボタンをクリックしてプレビューできます。
詳細については、:ref:`複合フィルター <complex-filter-ja>` を参照してください。


|task-list-widget-configuration|

**列の管理**

設定パネルの右上にある :guilabel:`列の管理` ボタンをクリックします。以下のダイアログが表示されます。

|task-list-widget-table-configuration|

列の管理ダイアログにある 2 つのセクションで表の列を設定します。

   #. フィールドの追加セクション：利用可能なフィールドをウィジェットの表に追加します。
   #. 列セクション：以下のとおり、ウィジェットの表を設定します。
 
      - 表示を有効にします。
      - クイック検索チェックボックスを選択して、クイック検索を有効にします。
      - |move-expand-vertical| 矢印を使用したドラッグ＆ドロップにより、列を並べ替えます。
      - 列を削除します。

  .. important::

    クイック検索は以下のフィールドで使用できます。

      #. 標準的なフィールド：ID、名前、説明、カテゴリー、責任者（表示名）、アプリケーション
      #. カスタムタスクフィールド： ``STRING`` または ``TEXT`` タイプのフィールド
      #. カスタムケースフィールド： ``STRING`` または ``TEXT`` タイプのフィールド
      #. ビジネスケースのカスタムフィールド： ``STRING`` または ``TEXT`` タイプのフィールド

    クイック検索は、標準的なフィールドの名前と説明に対して自動的に有効になります。

ポータルは、タスクリストでの **カスタムケースフィールド** と **カスタムビジネスケース** の表示をサポートします。

|task-column-field-type-configuration|

**Excel へのエクスポート**

表示モードでは、ウィジェット情報パネルの左下隅にある ``Excel にエクスポート`` リンクをクリックして、タスクウィジェットのすべてのデータをエクスポートできます。

|task-export-excel|

エクスポートするタスクの数が Excel ファイルの最大行数を超える場合、ポータルはデータを複数の Excel ファイルに分け、1 つの zip ファイルに格納します。


**クイック検索**

表示モードでは、クイック検索が有効な場合、検索が可能なテキストボックスが表示されます。

|task-quick-search-textbox|

  .. important::

    #. 設定パネルでクイック検索機能の範囲を定義できます。
    #. ウィジェットに複合フィルターが適用されている場合、クイック検索機能の結果に影響が及ぼすことがあります。

**列の幅の調整**

編集モードでは、ウィジェットの表で直接、各列の幅を調整できます。

|task-list-widget-edit-mode|

また、表には、より効率的に列の幅を調整できるグリッド線がポータルにより表示されます。

  .. important::

    列の全幅がウィジェットの幅よりも狭い場合、ポータルは自動的に列の幅をウィジェットの幅に合わせて調整します。

.. _new-dashboard-case-list-widget-ja:

ケースリストウィジェット
==================================================

ケースリストウィジェットを追加すると、知りたい情報を好きな方法で確認できます。


|case-list-widget|

ウィジェット設定パネルでウィジェット名を定義し、クイック検索機能を有効にし、ウィジェット情報と全画面モードのアイコンの表示／非表示を切り替え、列とフィルターを管理し、列のヘッダーをクリックして表の列の並べ替え順を編集できます。



**フィルターパネル**

:guilabel:`フィルター` ボタンをクリックして、ウィジェットの複合フィルターを設定し、:guilabel:`適用` ボタンをクリックしてプレビューできます。
詳細については、:ref:`複合フィルター <complex-filter-ja>` を参照してください。


|case-list-widget-configuration|

**列の管理**

設定パネルの右上にある :guilabel:`列の管理` ボタンをクリックします。以下のダイアログが表示されます。

|case-list-widget-table-configuration|

列の管理ダイアログにある 2 つのセクションで表の列を設定します。

   #. フィールドの追加セクション：利用可能なフィールドをウィジェットの表に追加します。
   #. 列セクション：以下のとおり、ウィジェットの表を設定します。

      - 表示を有効にします。
      - クイック検索チェックボックスを選択して、クイック検索を有効にします。
      - |move-expand-vertical| 矢印を使用したドラッグ＆ドロップにより、列を並べ替えます。
      - 列を削除します。

  .. important::

    クイック検索は以下のフィールドで使用できます。

      #. 標準的なフィールド：ID、名前、説明、カテゴリー、作成者（表示名）、アプリケーション
      #. カスタムフィールド： ``STRING`` または ``TEXT`` タイプのフィールド

    クイック検索は、標準的なフィールドの名前と説明に対して自動的に有効になります。

**Excel へのエクスポート**

表示モードでは、ウィジェット情報パネルの端にある ``Excel にエクスポート`` リンクをクリックして、ケースウィジェットのすべてのデータをエクスポートできます。

|case-export-excel|

エクスポートするケースの数が Excel ファイルの最大行数を超える場合、ポータルはデータを複数の Excel ファイルに分け、1 つの zip ファイルに格納します。


**クイック検索**

表示モードでは、クイック検索が有効な場合、検索が可能なテキストボックスが表示されます。

|case-quick-search-textbox|

  .. important::

    #. 設定パネルでクイック検索機能の範囲を定義できます。
    #. ウィジェットに複合フィルターが適用されている場合、クイック検索機能の結果に影響が及ぼすことがあります。

**列の幅の調整**

編集モードでは、ウィジェットの表で直接、各列の幅を調整できます。

|case-list-widget-edit-mode|

また、表には、より効率的に列の幅を調整できるグリッド線がポータルにより表示されます。

  .. important::

    列の全幅がウィジェットの幅よりも狭い場合、ポータルは自動的に列の幅をウィジェットの幅に合わせて調整します。

.. _new-dashboard-process-list-widget-ja:

プロセスリストウィジェット
======================================================

以下の 4 つのプロセスウィジェットモードを使用できます。

   - 複合モード
   - コンパクトモード
   - フルモード
   - イメージモード

ウィジェット設定で表示モードを選択し、ウィジェット情報と全画面モードのアイコンの表示／非表示を切り替えることができます。

|process-widget-modes|

**複合モード**

このウィジェットは、選択したプロセスの開始と関連するすべてのケース、タスクを 1 つのウィジェットに表示します。
このウィジェット設定では、特定のプロセスのタスクが特に見つけやすくなります。


|process-widget-combined-mode|

**コンパクトモード**

このウィジェットには、選択したすべてのプロセスの開始のリストが表示されます。インデックス、アルファベット順、使用頻度順、ユーザー設定順序でプロセスを並べ替えることができます。 
プロセスをドラッグ＆ドロップしてユーザー設定順序を変更し、クイック検索チェックボックスを選択して、クイック検索を有効にすることができます。

|process-widget-compact-mode|

表示モードでは、クイック検索機能が有効な場合、検索が可能なテキストボックスが表示されます。

  .. important::
   - クイック検索機能は、プロセスウィジェットが ``コンパクトモード`` の場合のみ使用できます。
   - クイック検索機能は、 ``プロセス名`` による検索をサポートします。

|process-quick-search-textbox|

**フルモード**

1 つのプロセスのアイコンを含むカードレイアウトでウィジェットを作成できます。

|process-widget-full-mode|

**イメージモード**

1 つのプロセスの写真を含むカードレイアウトでウィジェットを作成できます。

|process-widget-image-mode|

プロセスビューアーウィジェット
===================================================================

表示するウィジェット名とプロセスを定義できます。

- プロセスビューアーウィジェットの設定：

  |process-viewer-widget-configuration|

- 以下は、上記で設定したウィジェットの出力例です。

  |process-viewer-widget|

統計チャートウィジェット
===================================================

ポータルでは、レガシー統計チャートウィジェットが非推奨になりました。代わりに新しい標準的な統計チャートが多数用意されています。詳細については、:ref:`statistic-chart-ja` のセクションを参照してください。


ウェルカムウィジェット
===================================================

ライトモードとダークモードの背景画像とウェルカムテキストを使用してウェルカムウィジェットを定義できます。

|welcome-widget-configuration|


ニュースフィードウィジェット
======================================================

ユーザーにすべてのニュースエントリを表示するニュースフィードウィジェットを定義できます。

- ニュースフィードウィジェットの設定：

  |news-feed-widget-configuration|

- 以下は、上記で設定したウィジェットの出力例です。

  |news-feed-widget|

**ニュースフィードのコンテンツの管理**

.. note:: 
   ユーザーがニュースフィードのコンテンツを管理できるようにするには、 :bdg-ref-warning:`🔑NewsManagement <NewsManagement>` 権限を付与してください。

ユーザーに権限がある場合は、以下を行うことができます。

- **ニュースメッセージの追加**：  :guilabel:`ニュースを追加` :ボタンをクリックして、ニュースフィードの新しいエントリを追加します。

- **ニュースメッセージの編集**：各エントリの右下隅にある |edit-icon| アイコンをクリックして、リストの既存のエントリを編集します。

- **ニュースメッセージの削除**：各エントリの右下隅にある |trash-icon| アイコンをクリックして、エントリをリストから直接削除します。

:guilabel:`ニュースの管理` ダイアログ

|news-feed-widget-manage-content|

ニュースの管理では、以下を行うことができます。

#. 1 つのエントリを複数の言語で作成する。エントリを作成するときに、タブビューのヘッダーをクリックして、言語を切り替えることができます。

#. 入力ラベルをクリックして、タイトルを翻訳する。|translate-icon| アイコンをクリックして、コンテンツを翻訳します。翻訳を有効にするには、:ref:`enable-translation-ja` を参照してください。

#. アイコンを直接クリックして、ニュースのアイコンを選択する。:guilabel:`Icon browser` ブラウザが表示されます。

#. ニュースのタイトルを定義する。長さは 200 文字に制限されています。

#. ニュースコンテンツ、すなわちニュースフィードの主な情報を提供する。コンテンツは最大 1,000 文字に制限されています。

すべて設定した後、ダッシュボードの下部にある :guilabel:`このダッシュボードを共有` リンクをクリックして、同僚と共有します。

外部ページウィジェット
============================================

URL を入力して、外部ウェブページをポータルダッシュボードに埋め込みます。

|external-page-widget-configuration|

.. _add-new-notification-widget-ja:

通知ウィジェット
==============================

通知ウィジェットを定義して、通知設定に基づいてすべての通知を表示できます。

|notification-widget-configuration|

- 以下は、上記で設定したウィジェットの出力例です。

|notification-widget|

ウィジェット複製
-------------------------------------------

既存のダッシュボードからのウィジェット複製
================================================================================

ダッシュボードを変更するときに、 :guilabel:`+ ウィジェットの追加` ボタンを押して :guilabel:`ウィジェットを追加` ダイアログを開き、別のダッシュボードからウィジェットを複製できます。

ダイアログが開いたら、一番上にある :guilabel:`ウィジェット複製` ボタンをクリックします。

|clone-widget-from-button|

:guilabel:`ウィジェット複製` ダイアログが開きます。複製したいウィジェットを含むダッシュボードを選択し、ウィジェットを選択します。

|clone-widget-from-dialog|

ウィジェットを選択した後、 :guilabel:`複製` ボタンをクリックして現在のダッシュボードに追加します。

別のダッシュボードへのウィジェットの複製
============================================================================

ポータルには、ダッシュボード間でウィジェットを複製するもう 1 つの便利な方法が用意されています。ウィジェットを複製するには、ウィジェットのヘッダーにある :guilabel:`コピー` アイコンをクリックします。

|clone-widget-button|

ウェルカムウィジェットなど、ウィジェットタイプによっては、コンテキストメニューに :guilabel:`複製` オプションが表示されます。

|clone-widget-menu-option|

:guilabel:`複製` オプションを選択すると、:guilabel:`ウィジェット複製` ダイアログが表示されます。ウィジェットをコピーしたいダッシュボードを選択し、 :guilabel:`複製` ボタンをクリックしてプロセスを完了します。

|clone-widget-dialog|

.. include:: ../includes/_common-icon.rst

.. |dash-board| image:: ../../screenshots/new-dashboard/dashboard.png
   :alt: ダッシュボード画面
.. |widget-filter| image:: ../../screenshots/new-dashboard/widget-filter.png
   :alt: ダッシュボードウィジェットのフィルターパネル
.. |widget-info| image:: ../../screenshots/new-dashboard/widget-info.png
   :alt: ダッシュボードウィジェットの情報パネル
.. |case-export-excel| image:: ../../screenshots/new-dashboard/case-export-excel.png
   :alt: ダッシュボードのケースウィジェット：Excel へのエクスポート機能
.. |task-export-excel| image:: ../../screenshots/new-dashboard/task-export-excel.png
   :alt: ダッシュボードのタスクウィジェット：Excel へのエクスポート機能
.. |edit-widget| image:: ../../screenshots/new-dashboard/edit-widget.png
   :alt: ダッシュボード設定画面
.. |add-widget| image:: ../../screenshots/new-dashboard/add-widget.png
   :alt: 新しいウィジェットの追加ダイアログ
.. |task-list-widget| image:: ../../screenshots/new-dashboard/task-list-widget.png
   :alt: ダッシュボードのタスクウィジェットの設定ダイアログ 
.. |task-list-widget-configuration| image:: ../../screenshots/new-dashboard/task-list-widget-configuration.png
   :alt: ダッシュボードのタスクウィジェットの設定パネル
.. |task-list-widget-table-configuration| image:: ../../screenshots/new-dashboard/task-list-widget-table-configuration.png
   :alt: ダッシュボードのタスクウィジェットの表の設定
.. |task-column-field-type-configuration| image:: ../../screenshots/new-dashboard/task-column-field-type-configuration.png
   :alt: ダッシュボードのタスクウィジェットの列の管理
.. |task-widget-complex-filter-configuration| image:: ../../screenshots/new-dashboard/task-widget-complex-filter-configuration.png
   :alt: ダッシュボードのタスクウィジェットの複合フィルターの設定
.. |case-list-widget| image:: ../../screenshots/new-dashboard/case-list-widget.png
   :alt: ダッシュボードのケースウィジェットの設定ダイアログ
.. |case-list-widget-configuration| image:: ../../screenshots/new-dashboard/case-list-widget-configuration.png
   :alt: ダッシュボードのケースウィジェットの設定パネル
.. |case-list-widget-table-configuration| image:: ../../screenshots/new-dashboard/case-list-widget-table-configuration.png
   :alt: ダッシュボードのケースウィジェットの表の設定
.. |process-widget-modes| image:: ../../screenshots/new-dashboard/process-widget-modes.png
   :alt: ダッシュボードのプロセスウィジェットのモードの選択
.. |process-widget-combined-mode| image:: ../../screenshots/new-dashboard/process-widget-combined-mode.png
   :alt: ダッシュボードのプロセスウィジェットの複合モード
.. |process-widget-compact-mode| image:: ../../screenshots/new-dashboard/process-widget-compact-mode.png
   :alt: ダッシュボードのプロセスウィジェットのコンパクトモード
.. |process-widget-full-mode| image:: ../../screenshots/new-dashboard/process-widget-full-mode.png
   :alt: ダッシュボードのプロセスウィジェットのフルモード
.. |process-widget-image-mode| image:: ../../screenshots/new-dashboard/process-widget-image-mode.png
   :alt: ダッシュボードのプロセスウィジェットのイメージモード
.. |process-viewer-widget| image:: ../../screenshots/new-dashboard/process-viewer-widget.png
   :alt: ダッシュボードのプロセスビューアーウィジェット
.. |process-viewer-widget-configuration| image:: ../../screenshots/new-dashboard/process-viewer-widget-configuration.png
   :alt: ダッシュボードのプロセスビューアーウィジェットの設定ダイアログ
.. |process-quick-search-textbox| image:: ../../screenshots/new-dashboard/process-quick-search-textbox.png
   :alt: ダッシュボードのプロセスウィジェットのクイック検索機能
.. |welcome-widget-configuration| image:: ../../screenshots/new-dashboard/welcome-widget-configuration.png
   :alt: ダッシュボードのウェルカムウィジェットの設定ダイアログ
.. |news-feed-widget-configuration| image:: ../../screenshots/new-dashboard/news-feed-widget-configuration.png
   :alt: ダッシュボードのニュースフィードウィジェットの設定ダイアログ
.. |news-feed-widget| image:: ../../screenshots/new-dashboard/news-feed-widget.png
   :alt: ダッシュボードのニュースフィードウィジェット
.. |news-feed-widget-manage-content| image:: ../../screenshots/new-dashboard/news-feed-widget-manage-content.png
   :alt: ダッシュボードのニュースフィードウィジェットのニュースの管理ダイアログ
.. |external-page-widget-configuration| image:: ../../screenshots/dashboard/external-page-widget-configuration.png
   :alt: ダッシュボードの外部ページウィジェットの設定ダイアログ
.. |notification-widget-configuration| image:: ../../screenshots/new-dashboard/notification-widget-configuration.png
   :alt: ダッシュボードの通知ウィジェットの設定ダイアログ
.. |notification-widget| image:: ../../screenshots/new-dashboard/notification-widget.png
   :alt: ダッシュボードの通知ウィジェット
.. |task-quick-search-textbox| image:: ../../screenshots/new-dashboard/task-quick-search-textbox.png
   :alt: ダッシュボードのタスクウィジェットのクイック検索
.. |case-quick-search-textbox| image:: ../../screenshots/new-dashboard/case-quick-search-textbox.png
   :alt: ダッシュボードのケースウィジェットのクイック検索
.. |task-list-widget-edit-mode| image:: ../../screenshots/new-dashboard/task-list-widget-edit-mode.png
   :alt: 編集モードのダッシュボードのタスクウィジェット
.. |case-list-widget-edit-mode| image:: ../../screenshots/new-dashboard/case-list-widget-edit-mode.png
   :alt: 編集モードのダッシュボードのケースウィジェット
.. |clone-widget-button| image:: ../../screenshots/dashboard-configuration/clone-widget-button.png
   :alt: 編集モードのウィジェットのヘッダーに配置されたウィジェット複製ボタン。このボタンをクリックすると、ウィジェット複製ダイアログが表示されます。
.. |clone-widget-menu-option| image:: ../../screenshots/dashboard-configuration/clone-widget-menu-option.png
   :alt: 編集モードのウィジェットのウィジェット複製メニュー。このメニューをクリックすると、ウィジェット複製ダイアログが表示されます。
.. |clone-widget-dialog| image:: ../../screenshots/dashboard-configuration/clone-widget-dialog.png
   :alt: 編集モードのウィジェットのウィジェット複製ダイアログ
.. |clone-widget-from-button| image:: ../../screenshots/dashboard-configuration/clone-widget-from-button.png
   :alt: 「新しいウィジェットの追加」ダイアログに配置された「ウィジェット複製」ボタン。このボタンをクリックすると、「ウィジェット複製」ダイアログが表示されます。
.. |clone-widget-from-dialog| image:: ../../screenshots/dashboard-configuration/clone-widget-from-dialog.png
   :alt: 「ウィジェット複製」ダイアログ
