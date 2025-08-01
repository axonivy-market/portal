.. _customization-logout-ja:

ログアウトプロセス
===========================================

概要
------------

ポータルでは、以下をカスタマイズできます。

   - ユーザーがポータルからログアウトする際のログアウト機能。
   - デフォルトのログアウトプロセスを使用してログアウトした後、ユーザーに表示されるページ。

.. tip::

   ログアウトプロセスのカスタマイズ方法の例については、 ``portal-developer-examples`` プロジェクトの ``CustomLogoutPage`` プロセスを参照してください。
   

ログアウト機能のカスタマイズ
-------------------------------------------------------------

以下を使用して呼び出し可能サブプロセスを作成します。

**シグネチャ**：portalLogout


.. csv-table::
  :file: tables/portal-logout.csv
  :header-rows: 1
  :widths: 50, 50

ログアウトページのカスタマイズ
-----------------------------------------------

以下を使用して呼び出し可能サブプロセスを作成します。 

**シグネチャ**：portalGetLogoutPage

.. csv-table::
  :file: tables/portal-get-logout-page.csv
  :header-rows: 1
  :widths: 50, 50
