.. _customization-new-dashboard-ja:

ポータルダッシュボード
=============================================

.. _customization-portal-dashboard-introduction-ja:

概要
------------

ポータルダッシュボードは、ウィジェットやフィルターを追加、削除、編集できる柔軟性の高いページです。
レイアウトをダッシュボード間でドラッグ＆ドロップするだけで、カスタマイズできます。

ユーザーは簡単な方法で、ダッシュボードやウィジェットの定義済みフィルター、列、UI 要素、スタイルなどを事前に定義できます。

ポータルダッシュボードで利用可能なウィジェット：

- タスクウィジェット
- ケースウィジェット
- プロセスウィジェット
- 統計ウィジェット
- カスタムウィジェット
- プロセスビューアーウィジェット
- ウェルカムウィジェット
- ニュースフィードウィジェット
- 外部ページウィジェット
- 通知ウィジェット

**Portal.Dashboard** 変数を設定することにより、ポータルダッシュボードと各ウィジェットの詳細を事前に定義できます。


.. _customization-dashboards-customization-ja:

ダッシュボードの定義
---------------------------------

ポータルでは、複数のダッシュボードを表示できます。これらはタブとして表示されます。

ダッシュボードごとにダッシュボードの ID、タイトル、アクセス権限、ウィジェットを事前に定義できます。


以下は 1 つのタスクウィジェットを含むダッシュボードを設定する JSON の例です。

.. code-block:: javascript

   [
      {
         "id": "1",
         "version": "12.0.0",
         "templateId": "default-portal-dashboard-template",
         "titles": [
            {
            "locale": "en",
            "value": "Dashboard"
            },
            {
            "locale": "fr",
            "value": "Tableau de bord"
            },
            {
            "locale": "de",
            "value": "Dashboard"
            },
            {
            "locale": "es",
            "value": "Tablero de mandos"
            }
         ],
         "icon": "fa-play",
         "widgets": [
            {
            "type": "task",
            "id": "task_18459642ba614d79bddea3f57e800bcf",
            "names": [
               {
                  "locale": "en",
                  "value": "Your Tasks"
               },
               {
                  "locale": "fr",
                  "value": "Your Tasks"
               },
               {
                  "locale": "de",
                  "value": "Your Tasks"
               },
               {
                  "locale": "es",
                  "value": "Your Tasks"
               }
            ],
            "layout": {
               "w": 12,
               "h": 5,
               "x": 0,
               "y": 5
            },
            "columns": [
               {
                  "field": "start"
               },
               {
                  "field": "priority"
               },
               {
                  "field": "id",
                  "quickSearch": false
               },
               {
                  "field": "name",
                  "quickSearch": true
               },
               {
                  "field": "description",
                  "quickSearch": true
               },
               {
                  "field": "activator",
                  "quickSearch": false
               },
               {
                  "field": "state"
               },
               {
                  "field": "startTimestamp"
               },
               {
                  "field": "expiryTimestamp"
               },
               {
                  "field": "category",
                  "quickSearch": false
               },
               {
                  "field": "application",
                  "styleClass": "dashboard-tasks__priority u-text-align-center widget-column",
                  "quickSearch": false
               },
               {
                  "field": "actions"
               }
            ]
            }
         ],
         "permissions": [
            "Everybody"
         ],
         "accessibility": false
      }
   ]

..

各ダッシュボードの JSON の構造：

   ``id``：ダッシュボードを識別する ID。

   ``version``：ダッシュボードのバージョン。

   ``templateId``：このダッシュボードが参照する事前定義されたテンプレートを識別するキー。

   ``titles``：ダッシュボードの複数言語のタイトル。

   ``icon``：ダッシュボードアイコン。

   ``permissions``：ダッシュボードにアクセスできるロール。

   .. tip:: 
      ダッシュボードの ``permissions`` を定義しない場合、すべてのユーザーがそのダッシュボードを表示できます。

   ``widgets``：ダッシュボードのウィジェットの定義。 ``task``、 ``case``、 ``process``、 ``custom`` の 4 つのタイプのウィジェットがあります。
   各ウィジェットの設定の詳細については、以降のセクションを参照してください。
   

   .. warning::
      ウィジェットの ``id`` は、ダッシュボード間で一意である必要があります。

ダッシュボードのウィジェットの設定
------------------------------------------------------------

以下のセクションで、ポータルダッシュボードの各ウィジェットの JSON 設定について詳しく説明します。
ウィジェットの設定方法を効率的に理解するのに役立ちます。


.. toctree::
   :maxdepth: 1

   new-dashboard-task-widget
   new-dashboard-case-widget
   new-dashboard-process-widget
   new-dashboard-statistic-widget
   new-dashboard-custom-widget
   new-dashboard-process-viewer-widget
   new-dashboard-welcome-widget
   dashboard-newsfeed-widget
   new-dashboard-external-page-widget
   new-dashboard-notification-widget

.. tip:: 
   カスタムダッシュボードの JSON 構造を理解するには、

   - ``portal-developer-examples/resources/files`` プロジェクトの ``variables.Portal.Dashboard.json`` ファイルを参照します。
   - Designer の対応するアプリケーションフォルダーにコピーします。

      - e.g: AxonIvyDesigner12.0.0/configuration/applications/designer

   ``portal-developer-examples`` プロジェクトの ``Start Processes/CreateTestData/CreateTestDataForCustomizedDashboard.ivp`` プロセスを開始します。
   - 新しいポータルダッシュボードを開いて、新しいカスタムレイアウトを確認します。

   変数の設定については、:dev-url:`|ivy| の変数 </doc/|version|/designer-guide/configuration/variables.html>` を参照してください。

ダッシュボードのテンプレートの設定
----------------------------------------------------

ポータルには、ダッシュボードを作成する場合の時間と手間を低減するため、事前定義されたダッシュボードテンプレートが用意されています。
ダッシュボードの名前などの項目を入力し、事前定義されたテンプレートから 1 つ選び、ニーズに合わせて詳細を変更するだけで、素早くダッシュボードを作成できます。


これらのテンプレートは、 ``DashboardTemplates.json`` 変数を使用して設定可能です。

.. toctree::
   :maxdepth: 1

   new-dashboard-template

ダッシュボードのメインメニューエントリの設定
--------------------------------------------------------------------
ポータルでは、ダッシュボードのメインメニューエントリをカスタマイズできます。

メニューをカスタマイズするには、ポータルの ``Portal.Dashboard.MainMenuEntry.json`` 変数を編集します。
以下はカスタムメニューの JSON の例です。

.. code-block:: javascript

      {
         "names": [
               {
                  "locale": "en",
                  "value": "Dashboard EN"
               },
               {
                  "locale": "fr",
                  "value": "Dashboard FR"
               },
               {
                  "locale": "de",
                  "value": "Dashboard DE"
               },
               {
                  "locale": "es",
                  "value": "Dashboard ES"
               }
            ],
        "icon": "si si-layout-bullets"
      }

..
   
JSON の構造：

   ``names``：メニューエントリの複数言語の名前。

   .. important:: 
         表示される名前は、アカウントの言語設定に従います。

   ``icon``：メニューエントリのアイコン。ポータルは、Streamline アイコンと FontAwesome アイコンをサポートします。
