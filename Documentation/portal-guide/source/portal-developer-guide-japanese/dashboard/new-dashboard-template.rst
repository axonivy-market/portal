.. _configure-new-dashboard-template-ja:

ダッシュボードのテンプレートの設定
====================================================

JSON 定義
----------------------

以下はポータルダッシュボードのテンプレートの JSON 定義の例です。

.. code-block:: javascript

   [
      {
         "id": "template-id"
         "titles": [
            {
            "locale": "en",
            "value": "Default template"
            },
            {
            "locale": "de",
            "value": "Standard Vorlage"
            }
         ],
         "descriptions": [
            {
            "locale": "en",
            "value": "Create dashboard with 1 Task list"
            },
            {
            "locale": "de",
            "value": "Dashboard mit 1 Aufgabenliste"
            }
         ],
         "icon": "si-cog-double-2",
         "dashboard": {
            "id": "dashboard_id",
            "templateId": "template-id",
            "title": "Default dashboard",
            "permissions": ["Everybody"]
            ...
         }
      }
   ]

..

ダッシュボードのテンプレートの JSON の構造

   ``id`` ：テンプレートを識別する ID。 **必須** フィールドであり、 **一意** である必要があります。
   

   ``titles`` ：:guilabel:`あなたのテンプレートを選択` ダイアログのテンプレートの複数言語のタイトル。

   ``descriptions`` ：:guilabel:`あなたのテンプレートを選択` ダイアログのテンプレートの複数言語の説明。
   

   ``icon`` ：:guilabel:`あなたのテンプレートを選択` ダイアログのテンプレートの項目のアイコン。
   ポータルは、 `HTML Dialog Demo <https://market.axonivy.com/html-dialog-demo>`_ にある Streamline アイコンのみサポートします。

   ``dashboard`` ：ダッシュボードの詳細をテンプレートとして使用します。ダッシュボードの設定の詳細については、:ref:`ダッシュボードの設定 <customization-new-dashboard-ja>` を参照してください。
   
   
