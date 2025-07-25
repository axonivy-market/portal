.. _forgot-password-ja:

パスワードを忘れた場合
********************************************

.. hint:: 
  ユーザーに「パスワードを忘れました」リンクが表示されるには、管理者がパスワードの変更機能を有効にする必要があります。
  
  Active Directory、Entra ID などの外部セキュリティシステムを使用している場合は、その外部システムでパスワード管理を処理する必要があります。 
  
  
  リンクはログインページの右下に表示されます。

|login-screen|

パスワードをリセットするには、 ``パスワードを忘れました`` をクリックします。以下のページが表示されます。

|send-email-screen|

登録したメールアドレスを入力し、:guilabel:`送信` をクリックします。成功すると、リンクを含むメールがそのメールアドレスに送信されます。
リンクをクリックすると、以下のページが表示されます。

|reset-password-screen|

新しいパスワードを入力し、パスワードを再入力し、:guilabel:`続ける` をクリックします。成功すると、以下のページが表示されます。


|reset-password-success-screen|

:guilabel:`ログインページに戻る` をクリックし、自分のアカウントと新しいパスワードでもう一度ログインします。

.. include:: ../includes/_common-icon.rst

.. |login-screen| image:: ../../screenshots/login/login-form.png
   :alt: Portal login page
.. |send-email-screen| image:: ../../screenshots/forgot-password/send-email-screen.png
   :alt: 「パスワードをリセットするためのメールの送信」ページ
.. |reset-password-screen| image:: ../../screenshots/forgot-password/reset-password-screen.png
   :alt: 新しいパスワードページ
.. |reset-password-success-screen| image:: ../../screenshots/forgot-password/reset-password-success-screen.png
   :alt: パスワードのリセットに成功ページ
