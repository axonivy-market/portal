.. _demo-processes-ja:

デモプロセス
*********************************

|ivy-engine| をデモモードで起動した場合、 **portal-user-examples** が表示されます。
このプロジェクトでは、簡単な例をいくつか紹介します。
これらを試して、|ivy| とポータルの感触をつかむことができます。

.. hint:: 
   以下のアカウントを使用して、デモモードのポータルにログインできます。

  +---------------------+---------------------+-------------------------+
  | ユーザー名          | パスワード          | ロール                  |
  +=====================+=====================+=========================+
  | admin               | admin               | ポータルの管理者        |
  +---------------------+---------------------+-------------------------+
  | demo                | demo                | 通常ユーザー            |
  +---------------------+---------------------+-------------------------+
  | guest               | guest               | 通常ユーザー            |
  +---------------------+---------------------+-------------------------+

休暇申請
--------------------------

#. ポータルにログインします。

#. :guilabel:`ユーザー例のガイド` を選択して、概要ページを開きます。

   |user-example-guide-link|

#. 概要ページで休暇申請を開始します。

   |example-overview-leave-request|

#. 直接、最初のタスク「 **休暇申請の作成: <user>** 」に進みます。

   |leave-request-creation|

#. データを入力し、休暇申請を送信します。選択した承認者によって、次のタスクがそのユーザーに割り当てられます。
   

#. 2 番目のタスクは、 **休暇申請の承認** です。承認者がタスクを完了した後、概要タスクが申請者に割り当て直されます。
   

#. 申請者が **概要タスク** を完了すると、ケースが終了します。


貸出（ケースマップ）
-----------------------------------------

#. ポータルにログインします。

#. :guilabel:`ユーザー例のガイド` を選択して、概要ページを開きます。

   |user-example-guide-link|

#. 概要ページで貸出ケースを開始します。

   |example-overview-lending-case|

#. 直接、最初のタスク「 **個人データの収集** 」に進みます。

   |lending-casemap-collect-personal-data|

   自動的にすべてのフィールドにダミーデータが入力されます。データを入力し、リクエストを送信できます。
   

#. **個人データの確認** タスクに進み、情報を確認します。

#. **内部支払い能力チェック** タスクでは、次のタスクに進むか、 **外部支払い能力サービス** をクリックして **外部支払い能力サービス** を開始できます。
   
   

   |lending-casemap-external-solvency-service|

#. **内部支払い能力チェック** の完了後、金額フィールドと給与フィールドによって、1 段階または 2 段階の承認が必要です。
   金額が給与の 20% 以上の場合、2 段階の承認が必要になります。
   

   |lending-casemap-approval-task|

#. 承認タスクで承認してタスクを完了し、最後の手順である契約作成に進みます。
   **契約作成** タスクが完了すると、ケースが完了になります。
   タスクを却下した場合は、ケースが却下ステータスで終了します。


.. |example-overview-leave-request| image:: ../../screenshots/demo-processes/example-overview-leave-request.png
   :alt: ポータルの例：休暇申請の例
.. |leave-request-creation| image:: ../../screenshots/demo-processes/leave-request-creation.png
   :alt: ポータルの例：休暇申請の作成の例
.. |user-example-guide-link| image:: ../../screenshots/demo-processes/user-example-guide-link.png
   :alt: ユーザー例のガイドへのリンク
.. |example-overview-lending-case| image:: ../../screenshots/demo-processes/example-overview-lending-case.png
   :alt: ポータルの例：貸出
.. |lending-casemap-collect-personal-data| image:: ../../screenshots/demo-processes/lending-casemap-collect-personal-data.png
   :alt: ポータルの例：貸出ケースマップ - 個人データの収集
.. |lending-casemap-external-solvency-service| image:: ../../screenshots/demo-processes/lending-casemap-external-solvency-service.png
   :alt: ポータルの例：貸出ケースマップ - 外部支払い能力サービス
.. |lending-casemap-approval-task| image:: ../../screenshots/demo-processes/lending-casemap-approval-task.png
   :alt: ポータルの例：貸出ケースマップ - 承認タスク
