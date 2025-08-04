.. _components-portal-chat-ja:

ポータルチャット
===============================

.. _components-portal-chat-chat-feature-ja:

チャット機能
---------------------------

|chat|

.. _components-portal-chat-information-ja:

情報
-----------------

- ポータルチャットを使用するには、:ref:`portal-chat-ja` を参照してください。

- チャット機能は Ivy 7.4 で書き直されました。

- ブラウザが Nginx のようなリバースプロキシからポータルにアクセスする場合、接続の中断を防ぐため、 **Portal.Chat.ResponseTimeout** ポータル設定を Nginx タイムアウト（通常は 60 秒）よりも短く設定してください。

- 各タブは、1 回のロングポーリングでチャットのリクエストを行います。ブラウザでは、1 つのドメインのロングポーリングの並行リクエストの数が制限されます。通常、最大数は 6 以上です。
  この制限を処理するため、**Portal.Chat.MaxConnection** 設定がポータルに導入されました。デフォルト値は 3 です。4 番目のタブが開かれた場合、1 つのアクティブでないタブのチャットが非アクティブ化されます。
  チャットが非アクティブ化されたタブを選択した場合、すべてのチャット情報が更新され、チャットが再びアクティブ化されます。引き続き最大数を超えるチャットタブが開かれている場合は、別のタブのチャットが非アクティブ化されます。

- システムが追加の Tomcat の **値** を使用している場合は、 ``Context.xml`` ファイルに asyncSupported を追加することを推奨します。:dev-url:`参照ファイル </doc/|version|/engine-guide/configuration/files/context-xml.html>` にサンプルファイルがあります。

  .. code-block:: html

    <Context>
      <Valve className="..." asyncSupported="true" />
    </Context>


.. _components-portal-chat-limitation-ja:

現在のポータルチャットの制限
------------------------------------------

ポータルチャットは、クロスアプリケーションのチャットをサポートしていません。つまり、ユーザーは現在のアプリケーションにいる他のユーザーとチャットできますが、他のアプリケーションにいるユーザーとはチャットできません。



.. |chat| image:: ../../screenshots/chat/chat.png

