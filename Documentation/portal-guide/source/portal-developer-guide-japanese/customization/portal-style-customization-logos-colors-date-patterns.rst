.. _customization-portal-logos-and-colors-ja:

ポータルのスタイルのカスタマイズ（ロゴ、色、日付パターン）
=====================================================================

.. _customization-portal-logos-and-colors-change-portal-logos-ja:

ポータルで表示されるロゴの変更
------------------------------------------------------

:dev-url:`エンジンのブランディング </doc/|version|/designer-guide/user-interface/branding/branding-engine.html>` により、メニューの左上隅のロゴとログインロゴの両方を変更できます。


展開されたメニューと折りたたまれたメニュー用に 2 つの異なるロゴのグラフィックスがあります。
小さい二次形式の「純粋な」ロゴ（初期値：42x42 ピクセル）と、それよりも大きいスローガン付きのロゴを使用できます。
大きい方のロゴは、4:1 の幅と高さの比率を想定しています（初期値：168x42 ピクセル）。


比率を維持しながら拡大縮小できるため、ロゴのグラフィックス形式は SVG（Scalable Vector Graphics）が最善です。ロゴは常に現在の解像度で可能な限りきれいにレンダリングされます。

解像度は、ブラウザのウィンドウのサイズ、倍率、画面解像度によって決まります。
また、SVG ファイルはテキストファイルであるため、通常はかなり小さいです。
すべての最新のブラウザは、SVG 画像をレンダリングできます。


一方、PNG のロゴは拡大時にぼやけることがあるため、ぼやけないように高解像度で提供された場合、ファイルサイズが大きくなります。


コーポレートアイデンティティで必須の背景色が定められていない場合は、背景が透明な画像を使用することを推奨します。
 

ポータルで使用するロゴを変更する場合は、:dev-url:`ユーザーインターフェースのブランディング </doc/|version|/designer-guide/user-interface/branding/branding-user-interface.html#resources>` に記載されているリソースを使用し、以下のガイドラインに従ってください。 


展開されたメニューのロゴ
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

-  左側のメニューが展開されたときは、 ``logo`` リソースを使用して、ホームページのロゴを更新します。

   .. tip::
      - PNG では、展開時の左側のメニューサイズに合わせて、サイズが 168x42 ピクセル（幅x高さ）の画像を使用することを推奨します。 
        
        SVG では、比率が 4:1 のロゴが最善です。
        
      - ロゴを拡大縮小するには、 ``--home-logo-width``、 ``--home-logo-height`` 変数をオーバーライドします。

ログインロゴと折りたたまれたメニューのロゴ
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

-  左側のメニューが折りたたまれたときは、 ``logo_small`` リソースを使用して、ログインロゴとホームページのロゴを更新します。

   .. tip::
      - サイズが 50x50 ピクセルの画像を使用することを推奨します。

      - ログインロゴを拡大縮小するには、 ``--login-logo-height`` 変数をオーバーライドします。幅が自動的に計算されます。

      - ホームページのロゴを拡大縮小するには、 ``--small-home-logo-width``、 ``--home-logo-height`` 変数をオーバーライドします。

ファビコン
^^^^^^^^^^^^^^^^^^^^^^^^^^

-  ``favicon`` リソースを使用して、ファビコンを更新します。

   .. tip::
      - サイズが 16x16 または 32x32 ピクセルの画像を使用することを推奨します。

ポータルのスタイルの変更
-------------------------------------------

ポータルは、最新の |css_variable| を使用して、スタイルのカスタマイズをサポートします。 

安全にアップデートできる方法でポータルのスタイルをカスタマイズするため、オーバーライドするすべての CSS 変数とスタイルを ``portal-root-variables.css``、 ``portal-variables-light.css``、 ``portal-variables-dark.css`` ファイルから ``custom.css`` ファイルにコピーすることを推奨します。


:dev-url:`custom.css </doc/|version|/engine-guide/configuration/files/custom-css.html>` の場所については、:dev-url:`エンジンのブランディング </doc/|version|/designer-guide/user-interface/branding/branding-engine.html>` を参照してください。


.. caution:: 将来のポータルバージョンで変更される可能性があるため、``portal-root-variables.css``、``portal-variables-light.css``、``portal-variables-dark.css``、``portal.css`` 変数を直接変更しないでください。

..

以下はカスタマイズ可能な色を使用しているポータルの要素の一例です。

.. csv-table::
  :file: documents/available_css_variables.csv
  :widths: 20 10 40 
  :header-rows: 1
  :class: longtable

``custom.css`` でポータルのスタイルをカスタマイズできます。

  例えば、デフォルトの ``font-family`` をカスタマイズするには、

  #. ``START OVERRIDING PORTAL STYLES`` セクションの後に、 ``font-family`` の名前とファミリーメンバーのフォントの URL を含む新しい ``@font-face`` ブロックを追加します。
     
     

  #. ``:root`` タグ内の ``--portal-font-family`` の値を ``font-family`` の名前に変更します。

.. tip::
   - ポータルは、 ``font-size`` のサイズ測定単位として ``rem`` を使用します。 
     すべての要素のフォントサイズは、 ``html`` 要素のフォントサイズをベースに計算されます。

   - ``--primary-color`` が明るい色（黄色、水色など）の場合は、 ``--primary-text-color`` を ``black`` に変更できます。 
     高コントラストにより、ボタンなどのコンポーネントのテキストが読みやすくなります。

.. tip::
   IFrame を使用しており、プロジェクト固有の UI の色をカスタマイズしたい場合は、このドキュメントを参照してください。 
   :dev-url:`/doc/|version|/designer-guide/how-to/overrides.html?#override-new-wizard`

.. warning::
   - ``font-family`` プロパティの値を変更しないでください。

.. |css_variable| raw:: html

   <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties" target="_blank">CSS Variable</a>
