.. _admin-settings-ja:

.. raw:: html

    <style>
      .wy-nav-content {
         max-width: 1350px;
      }
    </style>

管理者設定
**********************

.. hint::
   ``AXONIVY_PORTAL_ADMIN`` ロールが付与されたユーザーのみ、ユーザーメニューの :guilabel:`管理者設定` を表示してアクセスできます。 
   これらのユーザーは、ポータルの設定の更新、通知の定義、表示、非表示を行うことができます。 
   

:guilabel:`管理者設定` ユーザーメニュー項目を選択します。

   |select-admin-settings|

.. _add-third-party-application-ja:

ハウツー：サードパーティーのアプリケーションを追加する
--------------------------------------------------------------------------------

#. :guilabel:`アプリケーション` タブを選択します。

   |applications|

#. :guilabel:`新規` をクリックして、新しいサードパーティーのアプリケーションを追加します。:guilabel:`新しいサードパーティーのアプリケーションを追加` ダイアログが表示されます。

   |add-application|

#. メニューアイコンを変更するには、:guilabel:`変更` をクリックします。

#. :guilabel:`表示名` を入力し、追加言語での表示名を指定します。:guilabel:`言語の追加` をクリックします。

#. :guilabel:`リンク` に URL を入力します（http://www.google.com など）。

#. :guilabel:`保存` をクリックします。

.. _update-portal-settings-ja:

ハウツー：ポータル設定を更新する
------------------------------------------------

#. :guilabel:`設定` タブを選択します。利用可能なすべての設定とデフォルト値、説明が表に表示されます。

   |global-settings|

#. ポータル設定の変数の値を更新するには、|edit-icon| アイコンをクリックします。

   |edit-global-settings|

#. 設定をデフォルト値にリセットするには、|undo-icon| アイコンをクリックします。

#. すべての設定をデフォルト値にリセットするには、:guilabel:`すべて初期値に戻す` をクリックします。

.. hint:: 
   ポータル設定は |ivy| の変数として格納され、:dev-url:`|ivy| コックピット </doc/|version|/engine-guide/reference/engine-cockpit/configuration.html#engine-cockpit-variables>` で設定できます。 
   

.. _portal-available-settings-ja:

.. centered:: 利用可能な設定

.. csv-table::
  :file: documents/available_settings.csv
  :header-rows: 1
  :class: longtable
  :widths: 20 20 60

ハウツー：通知の表示／非表示を切り替える
---------------------------------------------------------------------

#. :guilabel:`通知` タブを選択します。

   |announcement|

#. 通知テキストを入力し、:guilabel:`保存` ボタンをクリックします

#. :guilabel:`通知の有効化` オプションをオンにして、通知を表示します。

#. :guilabel:`通知の有効化` オプションをオフにして、通知を非表示にします。

ハウツー：ロールを管理する
------------------------------
ポータルの :guilabel:`管理者設定` 領域にある :guilabel:`ロールの管理` セクションで、ロールを管理できます。

| :guilabel:`ロールの管理` タブを表示するには、:bdg-ref-warning:`🔑RoleManagement <RoleManagement>` 権限が必要です。
| ロールを管理するには、以下の権限も必要になります。

  - :bdg-ref-warning:`🔑RoleCreate <RoleCreate>`：新しい動的ロールを作成
  - :bdg-ref-warning:`🔑RoleDelete <RoleDelete>`：動的ロールを削除
  - :bdg-ref-warning:`🔑RoleMove <RoleMove>`： :guilabel:`新しいロールを作成する` ステップで親ロールを選択できます。デフォルトでは、システムは ``Everybody`` を使用します。

#. :guilabel:`ロールの管理` タブを選択します。

   |role-assignment-tab|

#. :guilabel:`新しいロールを作成する` ボタンをクリックし、データを入力します。

   |role-assignment-creation-dialog|

#. すべての必須フィールドにデータを入力した後、:guilabel:`保存` ボタンをクリックして、ロールを作成します。

#. ロールツリーテーブルに新しいロールが表示されます。

#. ユーザーは、ロールのプロパティを編集し、ユーザーをロールに割り当て、:guilabel:`アクション` 列にあるアクションを使用してロールを削除し、新しいロールを管理できます。

.. note::
   ポータルには、:guilabel:`ロールの管理` タブに表示されるロールの数を制御する 2 つの詳細設定も用意されています。
   詳細については、:ref:`設定の変数 <portal-available-settings-ja>` を参照してください。

ハウツー：パスワード検証を有効／無効にする
----------------------------------------------------
ポータルの  :guilabel:`管理者設定`  領域にある  :guilabel:`パスワード検証`  セクションで、パスワード検証を有効／無効にし、パスワードポリシーを変更できます。 :guilabel:`パスワード検証` タブを表示するには、:bdg-ref-warning:`🔑PasswordValidation <PasswordValidation>` 権限が必要です。

#. :guilabel:`パスワード検証` タブを選択します。

   |password-validation-tab|

#. :guilabel:`パスワード検証の有効化` トグルスイッチをクリックして、機能を有効／無効にします。

#. :guilabel:`パスワード検証の有効化` トグルスイッチが :guilabel:`有効` の場合、パスワードポリシーをアクティブ化／非アクティブ化し、パスワードポリシーの設定を変更し、:guilabel:`保存` ボタンをクリックして設定を保存できます。

#. 設定を保存した後、パスワードを変更するか、パスワードをリセットして検証できます。

.. include:: ../includes/_common-icon.rst

.. |applications| image:: ../../screenshots/settings/applications.png
   :alt: 管理者設定：アプリケーション
.. |add-application| image:: ../../screenshots/settings/add-application.png
   :alt: 管理者設定：アプリケーションの追加ダイアログ
.. |announcement| image:: ../../screenshots/settings/announcement.png
   :alt: 管理者設定：通知
.. |select-admin-settings| image:: ../../screenshots/settings/select-admin-settings.png
   :alt: 管理者設定メニュー
.. |global-settings| image:: ../../screenshots/settings/global-settings.png
   :alt: 管理者設定：グローバル設定
.. |edit-global-settings| image:: ../../screenshots/settings/edit-global-settings.png
   :alt: 管理者設定：グローバル設定の編集ダイアログ
.. |role-assignment-tab| image:: ../../screenshots/settings/role-assignment-tab.png
   :alt: 管理者設定：ロールの管理
.. |role-assignment-creation-dialog| image:: ../../screenshots/settings/role-assignment-creation-dialog.png
   :alt: 管理者設定：ロールの作成ダイアログ
.. |password-validation-tab| image:: ../../screenshots/settings/password-validation-tab.png
   :alt: 管理者設定：パスワード検証
