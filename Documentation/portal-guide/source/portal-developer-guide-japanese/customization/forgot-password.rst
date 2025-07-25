.. _customization-forgot-password-ja:

パスワードを忘れた場合
=================================================

.. _customization-case-widget-how-to-override-token-generation-and-email-sending-ja:

トークンの生成とメールの送信のカスタマイズ
-----------------------------------------------------------------------------

トークンの生成とメールの送信は、パスワードをリセットするためのメールを送信するプロセスです。

このプロセスをカスタマイズするには、以下を使用して呼び出し可能サブプロセスを作成します。

**シグネチャ**：portalSendPasswordResetEmail

.. csv-table::
  :file: tables/send-password-reset-email.csv
  :header-rows: 1
  :widths: 50, 50

.. tip::

   トークンの生成とメールの送信プロセスのカスタマイズ方法の例については、 ``portal-developer-examples`` プロジェクトの ``CustomSendPasswordResetEmail`` プロセスを参照してください。
   


.. _customization-password-reset-ja:

パスワードのリセットのカスタマイズ
----------------------------------------------------------------------

パスワードのリセットは、ユーザーのパスワードを検証、更新するプロセスです。

新しいパスワードの検証方法や、ユーザーが新しいパスワードを作成したときに表示するメッセージをカスタマイズできます。


このプロセスをカスタマイズするには、以下を使用して呼び出し可能サブプロセスを作成します。

**シグネチャ**：portalResetPassword

.. csv-table::
  :file: tables/reset-password.csv
  :header-rows: 1
  :widths: 50, 50

.. tip::

   パスワードのリセットのカスタマイズ方法の例については、 ``portal-developer-examples`` プロジェクトの ``CustomResetPassword`` プロセスを参照してください。
   

