.. _portal-header-ja:

ポータルのヘッダー
*************************************

|ivy| Portal のヘッダーは常時表示されます。ヘッダーの右上には、2 つの便利な機能があります。


#. ログインしたときに使用したユーザーの名前。自分のユーザー名をクリックすると、自分のニーズに合わせて |ivy| Portal を設定できるオプションを含むメニューが表示されます。
   
   設定オプションの詳細については、 `|ivy| Portal のユーザー設定`_ を参照してください。
   

#. ユーザー名の隣には、ポータル全体を検索できる |search-icon| **検索バー** があります。
   この機能については、 `グローバル検索`_ で詳しく説明します。
   

|portal-header|

|ivy| Portal のユーザー設定
-----------------------------------------------------

|ivy| Portal の右上にあるユーザー名をクリックすると、自分のニーズに合わせてポータルを設定できるメニューが表示されます。

これらの機能について、以下のセクションで説明します。

管理者設定
--------------

:ref:`管理者設定 <admin-settings-ja>` メニューエントリは、 ``AXONIVY_PORTAL_ADMIN`` ロールを持つユーザーのみ利用可能です。

マイプロフィール
-----------------------------------

:ref:`my-profile-ja` メニューエントリには、一般設定、ユーザーの言語、通知チャネルのサブスクリプションの設定が含まれます。

ダッシュボード設定
--------------------------

:ref:`dashboard-configuration-ja` メニューエントリには、ダッシュボードの設定が含まれます。
ユーザーが個人用ダッシュボードまたは公開用ダッシュボードのダッシュボードの設定を定義できるようにするには、:bdg-ref-warning:`🔑DashboardWriteOwn <DashboardWriteOwn>` または :bdg-ref-warning:`🔑DashboardWritePublic <DashboardWritePublic>` のいずれかの権限をそれぞれ付与してください。 


不在
---------

:guilabel:`不在` メニューエントリには、不在管理コンポーネントがあります。
自分の不在を入力し、自分に割り当てられたタスクまたは保有するロールの 1 つに割り当てられたタスクの委任を定義できます。


.. hint::
   保有するロールに対する委任を指定できますが、自分に直接割り当てられたタスクのみ委任を指定することを推奨します。通常、ロールは複数のユーザーが保有するため、自分が不在の場合、自分と同じロールの別のメンバーがそのタスクを引き受けることができます。

|portal-absences|

ダイアログには、現在の不在日程と次の不在日程がすべて表示されます。また、:guilabel:`過去の不在を表示` オプションをアクティブにして、過去の不在を表示できます。
さらに、それぞれの不在の行にある |edit-icon| **編集** または |trash-icon| **削除** アクションを使用して、不在を編集、削除できます。



ハウツー：不在の追加
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. :guilabel:`不在の追加` ボタンをクリックします。

#. :guilabel:`不在の追加` ダイアログが開きます。

#. :guilabel:`開始日` と :guilabel:`終了日` を入力します。

#. 説明のコメントを入力できます。

#. :guilabel:`保存` ボタンをクリックして、不在を確定します。

|how-to-add-an-absence|

.. note::

   ユーザーが自分の個人用の不在または公開用の不在を作成できるようにするには、:bdg-ref-warning:`🔑UserCreateOwnAbsence <UserCreateOwnAbsence>` または :bdg-ref-warning:`🔑UserCreateAbsence <UserCreateAbsence>` のいずれかの権限をそれぞれ付与してください。 
   

ハウツー：代行者を設定する
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. 自分のタスクと自分が保有する各ロールの 2 つの項目のリストが表示されます。
   

   ロールごとに、複数の代行者を選択できます。

   自分のタスクには複数の代行者を選択できます。以下の 2 つのタイプの代行者があります。

   - 個人的に割り当てられたタスクを恒久的に代行する人。
   - この不在期間中、個人的に割り当てられたタスクを代行する人。

   代行者が属することができるタイプは 1 つだけです。

#. |si-notes-quill| で終わるリンクをクリックして代行者を指定し、代行者を選択し、:guilabel:`追加` ボタンをクリックして追加します。

#. :guilabel:`保存` ボタンをクリックして、代行者を確定します。

|how-to-set-absence-substitutes|

.. note::

   ユーザーが自分の代行者の定義を作成したり、すべてのユーザーの代行者の定義を作成できるようにするには、:bdg-ref-warning:`🔑UserCreateOwnSubstitute <UserCreateOwnSubstitute>` または :bdg-ref-warning:`🔑UserCreateSubstitute <UserCreateSubstitute>` のいずれかの権限をそれぞれ付与してください。
   

パスワードの変更
-------------------------------------


:guilabel:`パスワードを変更` メニューエントリでは、新しいパスワードを設定できます。

|portal-password-change-dialog|

.. note::
   ユーザーがパスワードを変更できるようにするには、 :bdg-ref-warning:`🔑UserSetOwnPassword <UserSetOwnPassword>` 権限を付与してください。次に権限を設定します :dev-url:`エンジンコックピット </doc/|version|/engine-guide/reference/engine-cockpit/security.html>` . セキュリティ領域で［PersonalPermissions］、［PersonalSecurityPermissions］、［UserSetOwnPassword］を順に開いてください。

情報
-------------

:guilabel:`情報` メニューエントリには、|ivy-engine| 、|ivy| Portal、会社のアプリケーションに関する詳細情報が表示されます。
サポートリクエストを発行する場合、この情報が求められることがあります。


|portal-version-information|

ログアウト
-------------------------

:guilabel:`ログアウト` メニューエントリは、|ivy| Portal でのセッションを終了します。
ユーザーは |ivy| Portal のログインページにリダイレクトされます。


.. hint::
   また、ユーザーが一定時間何もしない場合、|ivy| Portal は自動ログアウトを行います。
   
   
   

グローバル検索
------------------------------------

グローバル検索は、|ivy| Portal 全体の情報を検索できる便利なツールです。
任意のキーワードを検索できます。
|ivy| Portal は自動的に以下を検索します。

#. プロセス：名前、説明

#. ケース：ID、名前、説明

#. タスク：ID、名前、説明、すべてのカスタム文字列フィールド

検索するキーワードを確定すると、 **グローバル検索結果** ページに転送されます。
カテゴリーごとにタブが用意され、検索結果が表示されます。


|portal-global-search-result-page|

ハウツー：グローバル検索の検索範囲を限定する
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

管理者は、グローバル検索で検索するフィールドを制限できます。
ポータルには以下の 3 つの変数があります。

   - ``Portal.SearchScope.ByCaseFields``：グローバル検索で一致するケースを見つけるために、ケース ID のほかに使用するフィールドを定義します（検索のパフォーマンスに影響する可能性があります）。
     
   - ``Portal.SearchScope.ByTaskFields``：グローバル検索で一致するタスクを見つけるために、タスク ID のほかに使用するフィールドを定義します（検索のパフォーマンスに影響する可能性があります）。
     
   - ``Portal.GlobalSearchScopeCategories``：グローバル検索で一致するタイプを定義します（検索のパフォーマンスに影響する可能性があります）。
     

:ref:`settings-admin-settings-ja` を参照して、3 つの変数を設定できます。

.. include:: ../includes/_common-icon.rst


.. |portal-header| image:: ../../screenshots/dashboard/portal-header-with-numbering-annotation.png
   :alt: ポータルのヘッダーの項目
.. |portal-absences| image:: ../../screenshots/settings/absence.png
   :alt: 不在ページ
.. |how-to-add-an-absence| image:: ../../screenshots/settings/new-absence.png
   :alt: 不在ページ：新しい不在の追加ダイアログ
.. |how-to-set-absence-substitutes| image:: ../../screenshots/settings/set-deputy.png
   :alt: 不在ページ：代行者の設定セクション
.. |portal-password-change-dialog| image:: ../../screenshots/settings/change-password.png
   :alt: パスワードの変更ダイアログ
.. |portal-version-information| image:: ../../screenshots/settings/portal-version-information.png
   :alt: バージョン情報ダイアログ
.. |portal-global-search-result-page| image:: ../../screenshots/search/global-search-result.png
   :alt: グローバル検索結果ページ

