.. _configure-new-dashboard-welcome-widget-ja:

ウェルカムウィジェットの設定
==========================================

ウェルカムウィジェットの定義
----------------------------------------

ポータルダッシュボードのウェルカムウィジェットには、ウェルカムテキストと、その背景の画像が表示されます。

以下はポータルダッシュボードのウェルカムウィジェットの JSON 定義の例です。

.. code-block:: javascript

   {
      "type":"welcome",
      "id":"welcome_1",
      "layout":{
         "x":0,
         "y":0,
         "w":12,
         "h":3
      },
      "welcomeTexts":[
         {
            "locale":"en",
            "value":"Welcome"
         },
         {
            "locale":"fr",
            "value":"Bienvenue"
         },
         {
            "locale":"de",
            "value":"Willkommen"
         },
         {
            "locale":"es",
            "value":"Bienvenido"
         }
      ],
      "imageLocation": "welcome_1_en.jpg",
      "imageType": "jpg",
      "imageLocationDarkMode": "welcome_1_en_darkmode.png",
      "imageTypeDarkMode": "png",
      "welcomeTextPosition":"BOTTOM_LEFT",
      "welcomeTextSize":"NORMAL_TEXT",
      "welcomeTextColor":"#000000",
      "welcomeTextColorDarkMode": "#fc0e0e",
      "greeting":false,
      "welcomeTextStyleClass":"custom-text",
      "imageStyleClass":"custom-image",
      "welcomeImageFit":"COVER",
      "imageInlineStyle":"background-color:red; border:solid;"
   }

ウェルカムウィジェットの JSON の基本的な構造

   ``type``：ウィジェットのタイプ。ウェルカムウィジェットには ``welcome`` を使用します。

   ``id``：ウィジェットの ID。

   ``layout``：ウェルカムウィジェットのレイアウトの定義。

      ``x``：HTML DOMスタイル ``left`` は ``x / 12 * 100%`` として計算されます。

      ``y``：HTML DOMスタイル ``top`` は ``y / 12 * 100%`` として計算されます。

      ``w``：HTML DOMスタイル ``width`` は ``60 * w + 20 * (w - 1)`` として計算されます。

      ``h``：HTML DOMスタイル ``height`` は ``60 * h + 20 * (h - 1)`` として計算されます。

      ``styleClass`` （オプション）：CSS クラスをウィジェットの HTML DOM に追加します。

      ``style`` （オプション）：インラインスタイルをウィジェットの HTML DOM に追加します。

   ``welcomeTexts``：ウィジェットのウェルカムテキスト。ユーザーのロケールによる複数言語が可能です。

   ``welcomeTextPosition``：ウィジェットに関係するウェルカムテキストの位置。以下の 5 つの位置があります。

         ``BOTTOM_LEFT``：ウィジェットの左下隅。

         ``BOTTOM_RIGHT``：ウィジェットの右下隅。

         ``TOP_LEFT``：ウィジェットの左上隅。

         ``TOP_RIGHT``：ウィジェットの右上隅。

         ``CENTER``：ウィジェットの中央。

   ``welcomeTextSize``：ウェルカムテキストのサイズ。以下の 4 つのサイズがあります。

      ``NORMAL_TEXT``：通常のテキストのフォントサイズ（1.5rem）

      ``HEADING_3``：通常のヘッダーのフォントサイズ（2.5rem）

      ``HEADING_2``：通常より大きなヘッダーのフォントサイズ（3.5rem）

      ``HEADING_1``：大きなヘッダーのフォントサイズ（5rem）

   ``welcomeTextColor``：ウェルカムテキストの色。 ``font-color`` CSS 属性が受け入れる任意の値を入力できます。
   
   ``welcomeTextColorDarkMode``：ダークモードでのウェルカムテキストの色。 ``font-color`` CSS 属性が受け入れる任意の値を入力できます。

   ``greeting``：挨拶文をウェルカムテキストに追加する場合に定義します。

      ``false``：ウィジェットは、ユーザーの言語の ``welcomeTexts`` に含まれるテキストのみ表示します。

      ``true``：ウィジェットは、ユーザーの言語の標準的なポータルの挨拶文と ``welcomeTexts`` に含まれるテキストを表示します。2 つのテキストはカンマで区切られます。

   ``welcomeTextStyleClass``：ウェルカムテキストのスタイルクラス。

   ``imageStyleClass``：画像のスタイルクラス。

   ``imageInlineStyle``：画像のインラインスタイル。

   ``welcomeImageFit``：プロパティを使用して、コンテナに合わせて画像のサイズを変更する方法を指定します。以下の 4 つのオプションがあります。

        ``NONE``：画像のサイズは変更されません。画像がコンテナよりも大きい場合は、画像の中央が部分的に表示され、コンテナよりも小さい場合は、コンテナの中央に配置されます。

        ``FILL``：指定された寸法に合わせて画像のサイズが変更されます。画像とコンテナのアスペクト比が異なる場合、画像が変形します。

        ``COVER``：画像のアスペクト比が維持され、コンテナに合わせてサイズ変更とトリミングが行われます。

        ``CONTAIN``：画像のアスペクト比が維持され、全体がコンテナに表示されるようにサイズが変更され、左右または上下に余白が表示されます。

ポータルで直接、ウィジェットを設定して、背景画像をアップロードできます。

.. warning::

   ポータルは内部で ``imageLocation``、 ``imageType``、 ``imageContent``、 ``imageLocationDarkMode``、 ``imageTypeDarkMode``、 ``imageContentDarkMode`` 属性を使用しています。

   ウィジェットのエラーの原因となるため、手動で変更しないでください。
