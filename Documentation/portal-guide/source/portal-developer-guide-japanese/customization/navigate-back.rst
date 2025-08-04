.. _customization-navigateback-ja:

戻る
=============

概要
------------

タスクが完了すると、ポータルは前のページに戻ります。例えば、タスクがホームページから開始された場合、ホームページにリダイレクトします。

タスクがタスクリストから開始された場合は、タスクの完了後、そのタスクリストにリダイレクトします。


キャンセルなど、タスクを終了しないナビゲーションボタンがプロジェクトで表示される場合は、以下を実装する必要があります。


-  前のページ： ``PortalNavigatorInFrameAPI`` クラスから ``navigateToPortalEndPage()`` を呼び出します。
-  特定の URL： ``PortalNavigatorInFrameAPI`` クラスから ``navigateToUrl(String url)`` を呼び出します。


