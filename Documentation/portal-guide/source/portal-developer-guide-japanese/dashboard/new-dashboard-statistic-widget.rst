.. _configure-new-dashboard-statistic-widget-ja:

統計ウィジェットの設定
=================================================

.. _portal-statistic-widget-ja:

統計ウィジェットの定義
-------------------------------------------------------------

ポータルダッシュボードの統計ウィジェットには、統計とチャートが表示されます。

以下はポータルダッシュボードの統計ウィジェットの JSON 定義の例です。

.. code-block:: javascript

   {
      "type": "client-statistic",
      "id": "client_statistic_1",
      "layout": {
         "w" : 2,
         "h" : 2,
         "x" : 8,
         "y" : 2
      },
      "chartId": "10",
      "showFullscreenMode": true
   }
..

統計ウィジェットの JSON の基本的な構造

   ``id``：ウィジェットの ID。

   ``type``：ウィジェットのタイプ。クライアントの統計ウィジェットには ``client-statistic`` を使用します。

   ``layout``：クライアントの統計ウィジェットのレイアウトの定義。

      - ``x``：HTML DOMスタイル ``left`` は ``x / 12 * 100%`` として計算されます。

      - ``y``：HTML DOMスタイル ``top`` は ``y / 12 * 100%`` として計算されます。

      - ``w``：HTML DOMスタイル ``width`` は ``60 * w + 20 * (w - 1)`` として計算されます。

      - ``h``：HTML DOMスタイル ``height`` は ``60 * h + 20 * (h - 1)`` として計算されます。
   
   ``chartId``：標準的なクライアントの統計チャートの ID。詳細については、:ref:`Portal.StatisticCharts <portal-statistic-charts>` を参照してください。

   ``showFullscreenMode``：全画面モードのアイコンの表示設定。デフォルト値は ``true`` です。アイコンを非表示にするには ``false`` に設定してください。
