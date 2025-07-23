.. _customization-change-password-process-ja:

パスワードの変更プロセス
===============================================

.. _customization-change-password-process-introduction-ja:

概要
--------------

ポータルでは、パスワードの変更プロセスにより、例えばユーザーがパスワードを変更したときに新しいパスワードを漏洩パスワードデータベースで確認できます。


.. _customization-change-password-process-customization-ja:

カスタマイズ
-------------------------

以下を使用して、プロジェクトで呼び出し可能サブプロセスを作成します。 

**シグネチャ**：portalChangePassword

.. csv-table::
  :file: tables/portal-change-password.csv
  :header-rows: 1
  :widths: 30, 50, 20


.. tip::

   パスワードの変更プロセスのカスタマイズの例については、 ``portal-developer-examples`` プロジェクトの ``CustomChangePassword`` プロセスを参照してください。
   



