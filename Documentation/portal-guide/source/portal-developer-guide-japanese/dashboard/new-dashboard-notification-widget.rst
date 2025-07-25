.. _configure-new-dashboard-notification-widget-ja:

通知ウィジェットの設定
==============================================

通知ウィジェットの定義
------------------------------------------------

ポータルダッシュボードの通知ウィジェットには、通知設定に基づくすべての通知が表示されます。詳細については、:ref:`通知ウィジェット <add-new-notification-widget-ja>` を参照してください。


以下はポータルダッシュボードの通知ウィジェットの JSON 定義の例です。

.. code-block:: javascript

   {
      "type": "notification",
      "id": "notification_848ba48391014fcb801a14fb841a879a",
      "names": [
         {
            "locale": "en",
            "value": "My Notifications"
         },
         {
            "locale": "fr",
            "value": "Mes notifications"
         },
         {
            "locale": "de",
            "value": "Meine Benachrichtigungen"
         },
         {
            "locale": "es",
            "value": "Mis notificaciones"
         }
      ],
      "layout": {
         "w": 4,
         "h": 6,
         "x": 0,
         "y": 12,
         "style": "text-color: blue;",
         "styleClass": "your-widget-class"
      },
      "onlyUnread": true,
      "showFullscreenMode": true
   }
..

通知ウィジェットの JSON の基本的な構造

   ``type``：ウィジェットのタイプ。通知ウィジェットには ``notification`` を使用します。

   ``id``：ウィジェットの ID。

   ``names``：UI に表示されるウィジェットの複数言語の名前。

   ``layout``：ウィジェットのレイアウトの定義。

      ``x``：HTML DOMスタイル ``left`` は ``x / 12 * 100%`` として計算されます。

      ``y``：HTML DOMスタイル ``top`` は ``y / 12 * 100%`` として計算されます。

      ``w``：HTML DOMスタイル ``width`` は ``60 * w + 20 * (w - 1)`` として計算されます。

      ``h``：HTML DOMスタイル ``height`` は ``60 * h + 20 * (h - 1)`` として計算されます。

      ``styleClass`` （オプション）：CSS クラスをウィジェットの HTML DOM に追加します。

      ``style`` （オプション）：インラインスタイルをウィジェットの HTML DOM に追加します。

   ``onlyUnread`` ：未読の通知のみフィルターします。デフォルト値は「false」です。

   ``showFullscreenMode`` ：全画面モードのアイコンの表示設定。デフォルト値は ``true`` です。アイコンを非表示にするには ``false`` に設定してください。

