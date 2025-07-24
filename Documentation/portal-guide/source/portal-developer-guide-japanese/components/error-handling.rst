.. _components-error-handling-ja:

エラー処理
======================================

このセクションでは、ポータルの使用中に発生する可能性があるエラーのタイプと、ポータル内でのエラー処理の方法について説明します。


-  Ajax のエラー：タスクウィジェットをフルモードに切り替えるために「フルモードで表示」ボタンをクリックするなど、JSF Ajax リクエスト中に発生するエラーです。
   
   アクションが正常に完了しなかった場合、適切なエラー処理を行わないと、フィードバックを得られません。

-  Ajax 以外のエラー：ユーザーが、サーバーが正常に処理できない URL からポータルにアクセスした場合や、破損した URL に移動した場合に発生するエラーです。
   
   例えば、ユーザーが存在しないタスクを開始するリンクをクリックしたときに発生します。

-  期限切れエラーの表示：ユーザーがセッションのアクティブ表示ではなくなったページのリクエストを送信した場合や、ユーザーのセッションの期限が切れた場合に発生するエラーです。
   

.. _components-error-handling-ajax-error-handling-ja:

Ajax エラーの処理
--------------------------------------------

.. _components-error-handling-ajax-error-handling-introduction-ja:

概要
^^^^^^^^^^^^^^^^^^^^^^^^^^^

ポータルは自動的に Ajax リクエストによって生成されるすべての例外を処理します。

例外が発生すると、例外タイプとメッセージを含むエラー通知がエンドユーザーに表示されます。
エラーメッセージの ``詳細`` リンクをクリックすると、例外の詳細が表示されます。


スタックトレースの表示は、Ivy システムの ``Errors.ShowDetailsToEndUser`` プロパティで制御されます。


.. note:: 

      この機能は、ポータルのデフォルトのテンプレート（ ``BasicTemplate`` ）またはその拡張バージョンを使用している場合のみ、利用可能です。
      

.. _components-error-handling-ajax-error-handling-result-ja:

結果
^^^^^^^^^^^^^^^^^^^^^^^

|portal-ajax-error-handler|

.. _components-error-handling-nonajax-error-handling-ja:

Ajax 以外のエラーの処理
------------------------------------------------------------

.. _components-error-handling-nonajax-error-handling-introduction-ja:

概要
^^^^^^^^^^^^^^^^^^^^^^^^^

ページの読み込み中にサーバーで ``HTTP 404``、 ``HTTP 500`` などのエラーや例外が発生した場合、|ivy-engine| は自動的にデフォルトのエラーページを表示します。

例：

|default-ivy-error|

このページのコンテンツは ``${AxonIvyEngineFolder}/webapp/ivy/ivy-error-page.xhtml`` にあるファイルにありますが、エラーページはユーザーフレンドリーではありません。

そのため、|ivy| Portal では、エンドユーザーがこのページを理解しやすくする別の方法が用意されています。


.. _components-error-handling-nonajax-error-handling-howtoconfigure-ja:

設定方法
^^^^^^^^^^^^^^^^^^^^^^^^^^^

以下の ZIP ファイルをダウンロードし、エンジン（またはデザイナー）で設定します。

.. important:: ZIP ファイルを展開し、README.txt ファイルの指示に従ってください。

:download:`PortalErrorPageConfiguration.zip <documents/error-handling/portal-error-page-configuration.zip>` 

.. _components-error-handling-nonajax-error-handling-result-ja:

結果
^^^^^^^^^^^^^^^^^^^^^^^^
指示に従って設定した後は、ユーザーフレンドリーなデザインのカスタムエラーページが表示されるようになります。

HTTP 404 Page Not Found

テスト URL の例：404

|404|

HTTP 500エラー

テスト URL の例：500

|500|

期限切れエラーの表示
----------------------------------------------------

概要
^^^^^^^^^^^^^^^^^

.. _components-error-handling-customize-view-expired-dialog-ja:

Ivy には ``ViewExpiredException`` の警告ダイアログが用意されています。このダイアログは、プロジェクトの ``webContent/layouts/includes/exception.xhtml`` ファイルにあります。

プロジェクトのダイアログで :ref:`iframe-in-portal-ja` が使用されている場合、 ``ViewExpiredException`` が発生すると、デフォルトの警告ダイアログが表示されます。


設定方法
^^^^^^^^^^^^^^^^^^^^^^^^^^^

ポータルには、この例外のカスタマイズされたダイアログも用意されています。
上記のデフォルトのダイアログの代わりにポータルのカスタマイズされたダイアログを使用したい場合は、以下のコードスニペットを使用して、プロジェクトの ``exception.xhtml`` にある ``onexception`` コールバックを更新してください。


.. code-block:: javascript

    <p:ajaxExceptionHandler
        type="javax.faces.application.ViewExpiredException"
        update="viewExpiredExceptionDialog"
        onexception="parent.PF &amp;&amp; parent.PF('portal-view-expired-exception-dialog') ? 
            parent.PF('portal-view-expired-exception-dialog').show() :
            PF('viewExpiredExceptionDialog').show()" />

.. |portal-ajax-error-handler| image:: ../../screenshots/error-handling/portal-ajax-error-handler.png
.. |default-ivy-error| image:: ../../screenshots/error-handling/default-ivy-error.png
.. |404| image:: ../../screenshots/error-handling/404.png
.. |500| image:: ../../screenshots/error-handling/500.png


