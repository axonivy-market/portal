.. _configure-new-dashboard-task-widget-ja:

タスクウィジェットの設定
==========================================

タスクウィジェットの定義
--------------------------------------------

ポータルダッシュボードのタスクウィジェットは、インタラクティブなタスクリストです。詳細については、:ref:`タスクリストウィジェット <new-dashboard-task-list-widget-ja>` を参照してください。


以下はポータルダッシュボードのタスクウィジェットの JSON 定義の例です。

.. code-block:: javascript

   {
      "type": "task",
      "id": "task-widget",
      "names": [
         {
            "locale": "en",
            "value": "Task Widget"
         }
      ],
      "layout": {
         "x": 0,
         "y": 0,
         "w": 10,
         "h": 9,
         "style": "color: red;",
         "styleClass": "your-widget-class"
      },
      "sortField": "name",
      "rowsPerPage": 20,
      "showWidgetInfo": true,
      "showFullscreenMode": true,
      "isTopMenu": false,
      "columns": [
         {
            "field": "start"
         },
         {
            "field": "priority",
            "visible": "false"
         },
         {
            "field": "id"
         },
         {
            "field": "name"
         },
         {
            "field": "state",
            "headers": [
            {
               "locale": "en",
               "value": "State"
            },
            {
               "locale": "de",
               "value": "Status"
            }
            ]
         },
         {
            "field": "startTimestamp"
         },
         {
            "field": "actions"
         }
      ]
   }
..

タスクウィジェットの JSON の基本的な構造

   ``type``：ウィジェットのタイプ。タスクウィジェットには ``task`` を使用します。

   ``id``：ウィジェットの ID。

   ``names``：UI に表示されるウィジェットの複数言語の名前。

   ``layout``：ウィジェットのレイアウトの定義。

      ``x``：HTML DOMスタイル ``left`` は ``x / 12 * 100%`` として計算されます。

      ``y``：HTML DOMスタイル ``top`` は ``y / 12 * 100%`` として計算されます。

      ``w``：HTML DOMスタイル ``width`` は ``60 * w + 20 * (w - 1)`` として計算されます。

      ``h``：HTML DOMスタイル ``height`` は ``60 * h + 20 * (h - 1)`` として計算されます。

      ``styleClass`` （オプション）：CSS クラスをウィジェットの HTML DOM に追加します。

      ``style`` （オプション）：インラインスタイルをウィジェットの HTML DOM に追加します。

   ``sortField``：ウィジェットのソートフィールドの初期値。

   ``sortDescending`` ：ソートフィールドの初期値の並べ替え方向。デフォルト値は ``false`` （昇順で並べ替え）です。

   ``rowsPerPage`` ：タスク ウィジェットの 1 ページに表示可能なタスクの最大数。 
   デフォルト値は 1 ページあたり 10 行です。

   ``showWidgetInfo``：ウィジェット情報アイコンの表示設定。デフォルト値は ``true`` です。アイコンを非表示にするには ``false`` に設定してください。

   ``showFullscreenMode``：全画面モードのアイコンの表示設定。デフォルト値は ``true`` です。アイコンを非表示にするには ``false`` に設定してください。

   ``isTopMenu``：値が ``true`` の場合、ダッシュボードはナビゲーションバーの最上位の項目として表示されます。 
   値が ``false`` の場合は、 `ダッシュボード` メニューの下にサブ項目として表示されます。 
   デフォルト値は ``false`` です。

   ``columns``：ウィジェットの各列の列設定。列のフィルター、スタイル、表示設定などを事前に定義し、カスタム列も定義できます。
   
   

      ``field``：列のフィールド名。
         
         標準の列の ``field`` は以下のいずれかになります。
         
            - ``start``：タスクを直接開始する開始ボタンを含む列
  
            - ``priority``：タスクの優先度

            - ``id``：タスク ID

            - ``name``：タスク名

            - ``description``：タスクの説明

            - ``activator``：タスクアクティベーター

            - ``state``：タスクのビジネスの状態

            - ``startTimestamp``：タスクの作成日時

            - ``expiryTimestamp``：タスクの有効期限
            
            - ``actions``：タスクの詳細にアクセス、タスクをリセット、タスクを委任する、予約、タスクを破棄、エスカレーションタスクのトリガー、アドホックタスクの追加などのアクション

         カスタム列の ``field`` は、タスクのカスタムフィールドの名前になります。
         ポータルは、 ``field`` の値を使用して、列の値を取得します。

      ``canWorkOn``：現在のユーザーが作業できるタスクのみフィルターします。デフォルト値は「false」です。

      ``visible``：列の表示設定。デフォルト値は「true」です。
      列を非表示にするには、「false」に設定してください。

      ``quickSearch``：クイック検索の検索範囲にこのフィールドを追加します。デフォルト値は ``false`` です。
      列の検索条件を適用するには、 ``true`` に設定してください。

   -  ``headers``：列の複数言語のヘッダー。

.. _configure-new-dashboard-task-widget-custom-columns-ja:

カスタム列
-------------------------------

|ivy| は、タスクのカスタムフィールドをサポートします。タスクウィジェットでは列として表示できます。


表示する列と、フィルター、形式、スタイルなどの他の属性を事前に定義できます。以下はカスタム列の標準的な JSON です。

.. code-block:: javascript
   
   {
      ...

      "columns": [
            {
               "type": "CUSTOM",
               "field": "HIDE",
               "style": "width: 110px"
            }
      ]
   }

..

カスタム列には、前のセクションで説明した属性のほかに、以下の 2 つの違いがあります。


   - ``type``：ウィジェットの列のタイプ。 ``STANDARD`` と ``CUSTOM`` の 2 つのオプションがあります。

   - ``field``：この属性は、列のデータを取得するのに使用されるタスクのカスタムフィールドの名前です。
     

.. important::
   ポータルでは、 ``custom-fields.yaml`` ファイルで宣言されたカスタムフィールドのみ表示されます。
   詳細については、:dev-url:`カスタムフィールドのメタ情報 </doc/|version|/designer-guide/how-to/workflow/custom-fields.html#meta-information>` を参照してください。

フィルター条件
-----------------------------------------

タスクウィジェットのほとんどの列でフィルター条件を事前に定義できます。要件は列ごとに異なり、リストのみ、文字列のみを受け入れる列もあれば、日時など、特定の形式の文字列を必要とする列もあります。


詳細については、:ref:`複合フィルター <complex-filter-ja>` を参照してください。

Json フィルターの基本構造：

   .. code-block:: javascript

         {
            ...

            "columns" : [
               {
                  "field" : "description"
               }
            ],
            "filters" : [
                  {
                     "field" : "description",
                     "values" : [ "Leave Request" ],
                     "operator" : "contains",
                     "type" : "standard"
                  }
            ]
         }

   ..

..  _configure-new-dashboard-task-widget-filter-structure-ja:

   - ``field``：列名に対応するフィルターのフィールド名
   - ``values``：フィルターの値（リスト、文字列または数値）
   - ``operator``：フィルター演算子。演算子はフィールドのタイプによって異なります。

      - **文字列列**：is、is_not、empty、not_empty、contains、not_contains、start_with、not_start_with、end_with、not_end_with

      - **数値列**：between、not_between、empty、not_empty、equal、not_equal、less、less_or_equal、greater、greater_or_equal

      - **日付列**：today、yesterday、is、is_not、before、after、between、not_between、current、last、next、empty、not_empty

   - ``type``： ``standard`` （標準の列）または ``custom`` （カスタム列）

   - 日付タイプの追加フィールド：

      - ``periodType``：文字列値。例： ``YEAR``、 ``MONTH``、 ``WEEK``、 ``DAY``

      - ``from``：文字列値。例："2024/04/04"

      - ``to``：文字列値。例："2024/05/05"

追加フィールドは、各フィールドタイプの演算子やフィルターに依存します。
以下はフィルター設定可能な列とそれに対応するフィルター条件のリストです。


.. tip::
   これらのウィジェットをカスタマイズする際は、期待どおりに動作するように、ダッシュボード設定を使用してウィジェットを編集してから、ダッシュボードのエクスポート機能を利用することを推奨します。

標準の列：

   - ``activator``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "activator"
               }
            ],
            "filters": [
               {
                  "field": "activator",
                  "values": [ "backendDev2" ],
                  "operator": "not_in",
                  "type": "standard"
               }
            ]
         }

      ..

      この列は、タスク責任者のユーザー名のフィルター条件としてロール名またはユーザー名のリストのみ受け入れます。
      利用可能なフィルターの演算子は ``in``、 ``not_in``、 ``current_user`` です。
      ``current_user`` 演算子には値フィールドは必要ありません。

   - ``name``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "name"
               }
            ],
            "filters" : [
               {
                  "field": "name",
                  "values": [ "Task", "Leave Request" ],
                  "operator": "contains",
                  "type": "standard"
               }
            ]
         }
      ..

      この列は、文字列列で利用可能なすべての演算子を受け入れます。
      さらに、文字列のリストとして``値``も受け入れます。

   - ``description``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "description"
               }
            ],
            "filters": [
               {
                  "field": "description",
                  "values": [
                  "leave request"
                  ],
                  "operator": "contains",
                  "type": "standard"
               }
            ]
         }

      ..

      この列は、文字列列で利用可能なすべての演算子を受け入れます。さらに、文字列のリストとして ``値`` も受け入れます。

   - ``priority``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "priority"
               }
            ],
            "filters": [
               {
                  "field": "priority",
                  "values": [ "HIGH", "NORMAL", "LOW" ],
                  "operator": "in",
                  "type": "standard"
               }
            ]
         }

      ..

      この列は、フィルター条件として優先度名のリストのみ受け入れます。
      利用可能なフィルターの演算子は ``in`` です。

      利用可能なタスクの優先度については、:dev-url:`タスクの優先度 </doc/|version|/public-api/ch/ivyteam/ivy/workflow/WorkflowPriority.html>` を参照してください。
      

   - ``state``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "state"
               },
            ],
            "filters": [
               {
                  "field": "state",
                  "value": [ "DELAYED", "DESTROYED" ],
                  "operator" : "in",
                  "type" : "standard"
               }
            ]
         }
      ..

      この列は、フィルター条件としてタスクのビジネスの状態名のリストのみ受け入れます。
      利用可能なフィルターの演算子は ``in`` です。 

      利用可能なタスクのビジネスの状態については、:dev-url:`タスクのビジネスの状態 </doc/|version|/public-api/ch/ivyteam/ivy/workflow/TaskBusinessState.html>` を参照してください。
      


   - ``startTimestamp``、 ``expiryTimestamp``：タスクの作成日と終了日

      .. code-block:: javascript

         {
            ...
      
            "columns": [
               {
                  "field": "startTimestamp"
               }
            ],
            "filters" : [
               {
                  "field": "startTimestamp",
                  "operator": "today",
                  "type" : "standard"
               },
               {
                  "field" : "startTimestamp",
                  "from" : "04/04/2024",
                  "operator" : "before",
                  "type" : "standard"
               },
               {
                  "field" : "expiryTimestamp",
                  "from" : "04/04/2024",
                  "to" : "04/06/2024",
                  "operator" : "between",
                  "type" : "standard"
               },
               {
                  "field" : "expiryTimestamp",
                  "operator" : "last",
                  "periods" : 1,
                  "periodType" : "YEAR",
                  "type" : "standard"
               }       
            ]
         }

      ..

      これらの列は、日付列で利用可能なすべての演算子を受け入れます。フィールドは演算子によって異なります。
      上記のJSONの例は、日付フィールドのほとんどのユースケースをカバーします。
      受け入れ可能な日付の形式は、 ``dd.MM.yyyy``、 ``dd.MM.yyyy HH:mm``、 ``MM/dd/yyyy``、 ``MM/dd/yyyy HH:mm`` です。
      

   - ``application``

      .. code-block:: javascript

         {
            ...

            "columns": [
               {
                  "field": "application"
               }
            ],
            "filters": [
               {
               "field" : "application",
               "values" : [ "designer" ],
               "operator" : "in",
               "type" : "standard"
               }
            ]
         }

カスタムフィールド列：

   - :ref:`configure-new-dashboard-task-widget-custom-columns-ja` は、:ref:`標準の列 <configure-new-dashboard-task-widget-filter-structure-ja>` と同じ演算子を使用しています。
      
      

   - ``type`` フィールドは ``custom`` （カスタムフィールド）、 ``custom_case`` （カスタムケースフィールド）になります。

   .. code-block:: javascript
      
         {
            ...

            "columns": [
               {
                  "field" : "CustomerName"
               }
            ],
            "filters": [
               {
                  "field" : "CustomerName",
                  "operator" : "not_empty",
                  "type" : "custom"
               }
            ]
         }
   
   ..

クイック検索
-------------------------------------------------

クイック検索は、ユーザーがタスクウィジェット内のタスクを素早く検索できる便利な機能です。
クイック検索の設定には以下の 2 つの属性があります。
   
   * ``enableQuickSearch``：ウィジェットのクイック検索機能を有効にします。

   * ``quickSearch``：クイック検索機能を使用した列の検索が可能なことを示します。

``enableQuickSearch`` 属性を ``false`` に設定すると、 ``quickSearch`` 属性の値にかかわらず、クイック検索機能が無効になります。


逆に、 ``enableQuickSearch`` 属性を ``true`` に設定した場合は、 ``quickSearch`` 属性が ``true`` に設定されているすべての列の値でクイック検索機能による検索が可能になります。

``quickSearch`` 属性をタスクウィジェットのいずれの列にも割り当てていない場合、クイック検索機能はデフォルトで名前フィールドと説明フィールドを検索します。


これらの属性の定義は以下のとおりです。

   * ``enableQuickSearch``：クイック検索機能を有効／無効にします。以下のようにタスクウィジェットの ``enableQuickSearch`` フィールドを設定してください。
     

      .. code-block:: javascript

         {
            ...
            "type": "task",
            "id": "task_98ae4fc1c83f4f22be5244c8027ecf40"
            ...
            "enableQuickSearch": "true",
            ...
         }

      ..

      有効な値：

      * ``true``：クイック検索テキストボックスを表示します。
      * ``false``：クイック検索テキストボックスを非表示にします。
      * ``not defined``：クイック検索テキストボックスを非表示にします。

   * ``quickSearch``：クイック検索機能で検索できる列を選択します。以下のように各列の ``quickSearch`` フィールドを設定してください。
     

      .. code-block:: javascript

         {
            ...
            "type": "task",
            "id": "task_98ae4fc1c83f4f22be5244c8027ecf40"
            ...
            "columns": [
               {
                  "field": "id",
                  "quickSearch": "false"
               },
               ...
            ]
            ...
         }

      ..

      有効な値：

      * ``true``：この列にクイック検索を適用します。
      * ``false``：この列にクイック検索を適用しません。
      * ``not defined``：``name`` 列と ``description`` 列は ``true``、その他の列は自動的に ``false`` になります。

