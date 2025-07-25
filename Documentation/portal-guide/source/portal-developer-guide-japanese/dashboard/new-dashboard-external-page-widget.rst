.. _configure-new-dashboard-external-page-widget-ja:

外部ページウィジェットの設定
==============================================================

.. _define-an-external-page-widget-ja:

外部ページウィジェットの定義
------------------------------------------------------------

このウィジェットを使用して、外部ウェブページをポータルダッシュボードに埋め込むことができます。
この機能は、|ivy| の他にアプリケーションを使用している企業にとって特に便利です。
ユーザーは、複数のシステムをポータルダッシュボードから直接操作できます。

   |external-page-widget-configuration|

   ``Widget title``：ウィジェットのタイトル。ウィジェットのヘッダーを非表示にするには、空白のままにしてください。

   ``External URL``：外部ページのリンク。

   ``showFullscreenMode``：全画面モードのアイコンの表示設定。デフォルト値は ``true`` です。アイコンを非表示にするには ``false`` に設定してください。
   

JSON を使用した外部ページウィジェットの定義
---------------------------------------------------------------------------------------

JSON 構造は以下のとおりです。

   .. code-block:: javascript

      {
         "type": "custom",
         "id": "custom-widget",
         "showFullscreenMode": true,
         "names": [
            {
               "locale": "en",
               "value": "Custom Widget"
            }
         ],
         "layout": {
            "x": 10, "y": 0, "w": 2, "h": 4
         },
         "data": {
            "url" : "https://www.axonivy.com/"
         }
      }
   ..

属性の説明：

   ``type``：ウィジェットのタイプ。外部ページウィジェットには ``custom`` を使用します。

   ``id``：ウィジェットの ID。

   ``names``：UI に表示されるウィジェットの複数言語の名前。

   ``layout``：クライアントの統計ウィジェットのレイアウトの定義。

      - ``x``：HTML DOMスタイル ``left`` は ``x / 12 * 100%`` として計算されます。

      - ``y``：HTML DOMスタイル ``top`` は ``y / 12 * 100%`` として計算されます。

      - ``w``：HTML DOMスタイル ``width`` は ``60 * w + 20 * (w - 1)`` として計算されます。

      - ``h``：HTML DOMスタイル ``height`` は ``60 * h + 20 * (h - 1)`` として計算されます。

   ``url``：表示したい外部ウェブページの URL。

   ``showFullscreenMode``：全画面モードのアイコンの表示設定。デフォルト値は ``true`` です。アイコンを非表示にするには ``false`` に設定してください。

.. warning::
   外部ウェブサイトによっては、IFrame にロードできません。そのようなページをカスタムウィジェットで使用したい場合は、ウェブサイトのセキュリティポリシーで埋め込みが許可されていることを確認する必要があります。 
   

.. |external-page-widget-configuration| image:: ../../screenshots/dashboard/external-page-widget-configuration.png
