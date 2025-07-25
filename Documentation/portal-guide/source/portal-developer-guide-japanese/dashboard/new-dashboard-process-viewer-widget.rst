.. _configure-new-dashboard-process-viewer-widget-ja:

プロセスビューアーウィジェットの設定
=========================================================================

プロセスビューアーウィジェットの定義
-------------------------------------------------------------------------

ポータルダッシュボードのプロセスビューアーウィジェットは、プロセスフローを視覚的に表現します。

以下はポータルダッシュボードのプロセスビューアーウィジェットの JSON 定義の例です。

.. code-block:: javascript

   {
      "type": "process-viewer",
      "id": "process_viewer_1",
      "names": [
         {
            "locale": "en",
            "value": "Your Process Viewer"
         }
      ],
      "layout": {
         "x": 0,
         "y": 0,
         "w": 12,
         "h": 6
      },
      "processPath": "designer/portal-developer-examples/Start Processes/CreateTestData/createNewPayment.ivp",
      "showFullscreenMode": true
   }

プロセスビューアーウィジェットの JSON の基本的な構造

   ``type``：ウィジェットのタイプ。プロセスビューアーウィジェットには ``process-viewer`` を使用します。

   ``id``：ウィジェットの ID。

   ``names``：UI に表示されるウィジェットの複数言語の名前。

   ``layout``：プロセスビューアーウィジェットのレイアウトの定義。

      ``x``：HTML DOMスタイル ``left`` は ``x / 12 * 100%`` として計算されます。

      ``y``：HTML DOMスタイル ``top`` は ``y / 12 * 100%`` として計算されます。

      ``w``：HTML DOMスタイル ``width`` は ``60 * w + 20 * (w - 1)`` として計算されます。

      ``h``：HTML DOMスタイル ``height`` は ``60 * h + 20 * (h - 1)`` として計算されます。

      ``styleClass`` （オプション）：CSS クラスをウィジェットの HTML DOM に追加します。

      ``style`` （オプション）：インラインスタイルをウィジェットの HTML DOM に追加します。

   ``processPath``：表示したいプロセスの :dev-url:`|ivy| IWebStartable </doc/|version|/public-api/ch/ivyteam/ivy/workflow/start/IWebStartable.html>` ID。

   ``showFullscreenMode``：全画面モードのアイコンの表示設定。デフォルト値は ``true`` です。アイコンを非表示にするには ``false`` に設定してください。

