.. _installation-ja:

インストール
**************************

インストールのセクションでは、ポータルをインストール、設定するのに必要なすべての手順を説明します。
アプリケーションを初めてインストールする場合は、基本インストールから始めることが重要です。
このセクションでは、初回インストールで行う必要があるすべての初期手順を説明します。
アプリケーションがすでにインストールされ、設定されている場合は、 `移行に関する注記`_ を参照して移行してください。


基本インストール
==========================================

プロジェクトモジュール
---------------------------------------

アプリケーションは 3 つのプロセスモジュールで構成されます。各モジュールの詳細については、:ref:`architecture-ja` を参照してください。


-  portal-components
-  portal

Ivy プロジェクトの展開については、:dev-url:`project deployment </doc/|version|/engine-guide/deployment/index.html>` で説明します。



インストール
------------------------

デザイナー
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

ポータルのモジュールをデザイナーにインポートします。

ライセンスのないエンジン（デモモード）
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

エンジンは自動的に、以下のデフォルトユーザーでポータルアプリケーションを展開します。

.. csv-table:: 
   :file: tables/portal-default-users.csv
   :widths: 20, 30, 50
   :header-rows: 1


ライセンス付きエンジン（プロダクションモード）
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

エンジンは何も展開しません。ポータルアプリケーションを手動で展開、設定する必要があります。


ダッシュボードの設定ファイルのエンジンへのインストール（プロダクションモード）
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
ダッシュボードの JSON 設定ファイルを Axon Ivy エンジンにインストールする方法として、以下のいずれかを選択できます。

- 以下の構造に示すように、 ``<app.zip>/config/variables/Portal.Dashboard.json`` の app.zip の下に Dashboard.json ファイルを配置します。

   .. code-block:: 

      app.zip
      ├── config
      │   └── app.yaml
      |   └── variables
      |       └── Portal.Dashboard.json
      ├── portal.iar
      ├── portal-components.iar    
   ..

   JSON ファイルの名前は Portal.Dashboard.json とする必要があります。:dev-url:`エンジンの展開 </doc/|version|/engine-guide/deployment/index.html>` を参照してください。 
- Dashboard.json ファイルを ``<engine>/configuration/applications/<application>`` パスの ``<application>`` フォルダーに直接コピーします。JSON ファイルの名前は ``variables.Portal.Dashboard.json`` とする必要があります。

- ポータルのダッシュボードのインポート機能を使用します。:ref:`公開用ダッシュボードのインポート方法 <howto-import-your-public-dashboards-ja>` を参照してください。

ポータルのマルチアプリケーションのセットアップ
"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
.. important::

   これは、各ユーザーの共通タスクリストを複数のアプリケーションで表示するのに使用します。セキュリティコンテキストにインストールされたすべてのアプリケーションのタスク／ケースリストが、このセキュリティコンテキストで実行中のポータルによって表示されます。

すべてのアプリケーションは同じセキュリティコンテキストにあり、他のアプリケーションに展開されるのは **portal-components** だけです。:ref:`ポータルのマルチアプリケーション <multi-app-structure-ja>` の概要を参照してください。

-  新しいアプリケーションを作成します。このアプリケーションにポータル（portal、portal-components）を展開します。

-  新しいアプリケーション（アプリ 1、アプリ 2...）を作成します。新しいアプリケーションにプロジェクトを展開します。


.. _installation-migration-notes-ja:

移行に関する注記
===================================

このドキュメントでは、ポータルのバージョン間の不整合について詳しく説明し、既存のポータルを現在の |ivy-engine| で動作させるために必要な措置について述べます。



移行方法
--------------

.. important::
   API ドキュメントに記載されていないポータル API を呼び出す場合、変更または削除されていることがあります。
   忘れずに該当する API をプロジェクトに再実装してください。
   

   ポータルを移行するには、最初に |ivy| を移行する必要があります。:dev-url:`|ivy| の移行に関する注記 </doc/|version|/axonivy/migration/index.html>` を参照してください。
   
   顧客プロジェクトが適切に移行されていない場合、|ivy| の変更によって問題が生じることがあります。
   

デザイナー
-----------

#. ポータルのすべてのプロジェクトを新しいリリースのバージョンに置き換えます。
#. 最新のポータルバージョンを使用するため、 **プロジェクトの変換** 機能を使用して、プロジェクトをアップグレードします。:dev-url:`プロジェクトの変換 </doc/|version|/designer-guide/process-modeling/projects/converting.html#converting-projects>` で詳細を確認してください。
#. 以下の各バージョンの移行に関する注記に従います。
#. カスタマイズ中、ポータルからコードをコピーする必要がある場合は、コピーしたコードについて、ポータルの 2 つのバージョン間で変更をマージします。
   

..

エンジン
--------------------

#. 必要に応じてデータベーススキーマを変換します。

#. ポータルプロジェクトと顧客プロジェクトを再展開します。

#. 以下の各バージョンの移行に関する注記に従います。

11.3.2 から 12.0.0 への移行
---------------------------------------------

#. ビジネスの詳細ページのカスタムフィールドの値は、プロセスリンクの相対パスから :dev-url:`IWebStartable ID </doc/|version|/public-api/ch/ivyteam/ivy/workflow/start/IWebStartable.html#getId()>` にサイレントに移行されます。
   管理者が何かを行う必要はありません。これは単なる参考情報です。

#. タスクウィジェットとケースウィジェットの列の幅を調整する新しい機能を実装しました。
   この変更により、以前のバージョンからのタスクウィジェットとケースウィジェットの列の幅に若干の影響が生じることがあります。列の幅を手動で調整する方法については、:ref:`タスクウィジェットの設定 <new-dashboard-task-list-widget-ja>` と :ref:`ケースウィジェットの設定 <new-dashboard-case-list-widget-ja>` を参照してください。


11.1.0 から 11.2.0 への移行
-----------------------------------------------

``AxonIvyExpress`` モジュールは ``axonivy-express`` という名前に変更され、Axon Ivy Market で入手できるようになりました。移行するには、以下の手順を実行する必要があります。

#. **ポータル** を開き、［ **設定** ］、［ **Express の管理** ］の順に移動します。Express のすべての設定をエクスポートします。
#. **コックピット** を開き、 **ポータル** アプリケーションで PM **AxonIvyExpress** を停止します。
#. Axon Ivy Market から取得した **axonivy-express** モジュールを **ポータル** アプリケーションに展開します。
#. エンジンを再起動します。
#. **ポータル** を開き、［ **設定** ］、［ **Expressの管理** ］の順に移動します。最初にエクスポートした設定をインポートします。
#. ``PortalStartTimeCleanObsoletedDataExpression`` 変数をオーバーライドする場合は、新しい Ivy CRON のジョブパターンに更新します。
#. Axon Ivy CRON のジョブパターンを参照してください（ `CRON Expression <https://developer.axonivy.com/doc/|version|/engine-guide/configuration/advanced-configuration.html#cron-expression>`_ ）。
#. 例：毎日 01:00 AM にジョブをトリガーする場合は、 ``0 0 1 * * ?`` を ``0 1 * * *`` に変更します。

10.0.19 から 10.0.20 への移行
---------------------------------------------------

ポータルはこれまで、文字列または配列形式による :ref:`プロセスチェーン <components-portal-components-process-chain-ja>` のプロセスステップの設定をサポートしてきました。 
文字列形式は非推奨ですが、後方互換性のため、引き続きサポートします。配列形式を使用することを推奨します。以下のように変更できます。

#. ``window.processSteps = "Create Investment Request,Approve Investment Request";`` を ``window.processSteps = ["Create Investment Request", "Approve Investment Request"];`` に変更します。

#. ``window.processSteps = "#{fn:join(data.steps.toArray(), ',')}";`` を ``window.processSteps = #{portalComponentUtilsBean.convertToJSON(data.steps)};`` に変更します。

10.0.12 から 10.0.13 への移行
---------------------------------------------------------------

#. HTML のオーバーライドダイアログである ``UserWithEnvironmentInfo`` が削除され、サポート対象外になりました。代わりに ``GlobalFooterInfo`` ダイアログと GlobalVariable を使用してください。

#. HTML のオーバーライドダイアログである ``PageHeader`` と ``PageFooter`` がサポート対象外になりました。代わりに callable を使用してください。

10.0.11 から 10.0.12 への移行
----------------------------------------------

#. いくつかのサブプロセスについて、プロセスのオーバーライドがサポート対象外になりました。以下のガイドラインに従って、サブプロセスのオーバーライドを移行してください。

   - :ref:`パスワードを忘れた場合のカスタマイズ <customization-forgot-password-ja>`
      - プロジェクトから ``ResetPassword``、 ``SendPasswordResetEmail`` のサブプロセスのオーバーライドを削除します。
      - 以下のとおり、呼び出し可能な開始サブプロセスのシグネチャを変更します。

         +-------------------------+-------------------------------+
         | サブプロセス            | 新しいシグネチャ              |
         +=========================+===============================+
         | ResetPassword           | portalResetPassword           |
         +-------------------------+-------------------------------+
         | SendPasswordResetEmail  | portalSendPasswordResetEmail  |
         +-------------------------+-------------------------------+

   - :ref:`ドキュメントのプロセス <customization-document-processes-ja>`
      - プロジェクトから ``GetDocumentList``、 ``UploadDocument``、 ``DeleteDocument``、 ``DownloadDocument`` のサブプロセスのオーバーライドを削除します。
      - 以下のとおり、呼び出し可能な開始サブプロセスのシグネチャを変更します。

         +----------------------+----------------------------+
         | サブプロセス         | 新しいシグネチャ           |
         +======================+============================+
         | GetDocumentList      | portalGetDocumentList      |
         +----------------------+----------------------------+
         | UploadDocument       | portalUploadDocument       |
         +----------------------+----------------------------+
         | DeleteDocument       | portalDeleteDocument       |
         +----------------------+----------------------------+
         | DownloadDocument     | portalDownloadDocument     |
         +----------------------+----------------------------+

   - :ref:`ログアウトプロセスのカスタマイズ <customization-logout-ja>`
      - プロジェクトから ``LogoutPage``、 ``Logout`` のサブプロセスのオーバーライドを削除します。
      - 以下のとおり、呼び出し可能な開始サブプロセスのシグネチャを変更します。

         +----------------------+----------------------------+
         | サブプロセス         | 新しいシグネチャ           |
         +======================+============================+
         | LogoutPage           | portalGetLogoutPage        |
         +----------------------+----------------------------+
         | Logout               | portalLogout               |
         +----------------------+----------------------------+

   - :ref:`パスワードの変更プロセスのカスタマイズ <customization-change-password-process-ja>`
      - プロジェクトから ``ChangePassword`` のサブプロセスのオーバーライドを削除します。
      - 以下のとおり、呼び出し可能な開始サブプロセスのシグネチャを変更します。

         +----------------------+----------------------------+
         | サブプロセス         | 新しいシグネチャ           |
         +======================+============================+
         | ChangePassword       | portalChangePassword       |
         +----------------------+----------------------------+

   - :ref:`タスクの委任 <customization-task-delegation-ja>`
      - プロジェクトから ``CalculateTaskDelegate`` のサブプロセスのオーバーライドを削除します。
      - 以下のとおり、呼び出し可能な開始サブプロセスのシグネチャを変更します。

            +-------------------------+-------------------------------+
            | サブプロセス            | 新しいシグネチャ              |
            +=========================+===============================+
            | CalculateTaskDelegate   | portalCalculateTaskDelegate   |
            +-------------------------+-------------------------------+


   - :ref:`メニュー項目のカスタマイズ <customization-menu-customization-ja>`
      - プロジェクトから ``LoadSubMenuItems`` のサブプロセスのオーバーライドを削除します。
      - 以下のとおり、呼び出し可能な開始サブプロセスのシグネチャを変更します。

         +-------------------------+-------------------------------+
         | サブプロセス            | 新しいシグネチャ              |
         +=========================+===============================+
         | LoadSubMenuItems        | portalLoadSubMenuItems        |
         +-------------------------+-------------------------------+
      - デフォルトのメニュー項目を非表示にするには、変数を使用します。方法については、:ref:`デフォルトのメニュー項目の非表示 <customization-menu-hide-default-menu-item-ja>` を参照してください。
      - 各カスタムメニュー項目の ``index`` を更新します。
      - カスタムメニュー項目の作成方法の例については、 ``portal-developer-examples`` プロジェクトの ``CustomLoadSubMenuItems`` プロセスを参照してください。

#. ``imageContent`` フィールドの **外部リンク** の設定を変更しました。詳細については、:ref:`ポータルプロセスの外部リンク <portal-process-external-link-ja>` を参照してください。基本的に、エンジンでの移行は必要ありません。展開によって `Portal.Processes.ExternalLinks` 変数をオーバーライドした場合は、JSON 変数の `Portal.Processes.ExternalLinks` ファイルで `data:image/jpeg;base64` のようなプレフィックスを削除して、 ``imageContent`` フィールドを更新してください。

10.0 から 10.0.7 への移行
---------------------------------------------------------

``ch.ivy.addon.portalkit.publicapi.PortalNavigatorInFrameAPI`` クラスが削除され、サポート対象外になりました。代わりに ``com.axonivy.portal.components.util.PortalNavigatorInFrameAPI`` を使用してください。 


8.x から 10.0 への移行
-----------------------------------------------------

``8.x から ... への移行`` から ``... から9.x への移行`` までのすべての手順を実行する必要があります。


9.3 から 9.4 への移行
------------------------------------------------

9.4 から ``PortalStyle``、 ``PortalKit``、 ``PortalTemplate`` が ``portal-components`` と ``portal`` に置き換えられました。:ref:`architecture-ja` を参照してください。

#. PortalStyle をカスタマイズしている場合は、:ref:`ポータルのロゴと色のカスタマイズ <customization-portal-logos-and-colors-ja>` を参照して、ログインの背景、ファビコン、ロゴの画像をオーバーライドします。
   
   ``PortalStyle`` で CMS を変更している場合は、 ``portal`` の CMS をそれに合わせます。

#. ``customization.css`` ファイルが削除されました。このファイルをプロジェクトで使用している場合は、:dev-url:`エンジンのブランディング </doc/|version|/designer-guide/user-interface/branding/branding-engine.html>` の使用に切り替えて、スタイルをカスタマイズしてください。
   

#. ドキュメントに関連するサブプロセスが独立した ``portal-components`` プロジェクトに移動されました。
   これらのプロセスをカスタマイズした場合は、対応するサブプロセスをもう一度オーバーライドしてからカスタマイズしてください。

   以下は ``portal`` プロジェクトの非推奨プロセスと ``portal-components`` プロジェクトの新しいプロセスのリストです。

    .. csv-table::
      :file: tables/list-of-document-processes.csv
      :header-rows: 1
      :widths: 50, 50


#. ポータルのいくつかのクラスが独立した ``portal-components`` プロジェクトに移動されました。以下の表を参照して、正しく移行してください。

   .. csv-table::
      :file: tables/class_replacement_9.4.csv
      :header-rows: 1
      :class: longtable
      :widths: 1 1

#. ポータルのいくつかのコンポーネントが独立した ``portal-components`` プロジェクトに移動されました。以下の手順に従って移行してください。

   - 新しい :ref:`ユーザーの選択 <components-portal-components-user-selection-ja>` コンポーネントの :ref:`移行手順 <components-portal-components-migrate-from-old-user-selection-ja>`

   - 新しい :ref:`ロールの選択 <components-portal-components-role-selection-ja>` コンポーネントの :ref:`移行手順 <components-portal-components-migrate-from-old-role-selection-ja>`

   - 新しい :ref:`ドキュメントテーブル <components-portal-components-document-table-ja>` コンポーネントの :ref:`移行手順 <components-portal-components-migrate-from-old-document-table-ja>`

   - 新しい :ref:`プロセスチェーン <components-portal-components-process-chain-ja>` コンポーネントの :ref:`移行手順 <components-portal-components-migrate-from-old-process-chain-ja>`

#. ポータルダッシュボードのウィジェットは、 ``custom-fields.yaml`` ファイルで宣言された ``CustomFields`` のみサポートします。
   ``CustomFields`` がダッシュボードのウィジェットで使用されている場合は、:dev-url:`カスタムフィールドのメタ情報 </doc/|version|/designer-guide/how-to/workflow/custom-fields.html#meta-information>` に従って、データを適合させてください。

#. ``DefaultChartColor.p.json`` サブプロセスが削除されました。プロジェクトで使用している場合は、このサブプロセスのオーバーライドを削除して、:dev-url:`エンジンのブランディング </doc/|version|/designer-guide/user-interface/branding/branding-engine.html>` の使用に切り替えて、チャート、データラベル、凡例の色をカスタマイズしてください。
   

#. :download:`portal-migration-9.4.0.iar <documents/portal-migration-9.4-9.4.0.iar>` プロジェクトを Ivy アプリケーションに展開し、 ``your_host/your_application/pro/portal-migration/175F92F71BC45295/startMigrateConfiguration.ivp`` リンクにアクセスして実行します。
   

   .. important::
      * アプリケーションが複数ある場合は、1 つのアプリケーションにのみ展開し、 ``https://portal.io/Portal/pro/portal-migration/175F92F71BC45295/startMigrateConfiguration.ivp`` などの移行リンクにアクセスして実行してください。
        

      * 管理者アカウントを使用してサインインしてください。
      * 移行プロセスは一度だけ実行してください。
      * 移行に成功した後、 ``portal-migration``、 ``PortalStyle``、 ``PortalKit``、 ``PortalTemplate`` プロセスモデルを削除する必要があります。

9.2 から 9.3 への移行
--------------------------------------

#. :download:`portal-migration.iar <documents/portal-migration-9.3.0.iar>` プロジェクトを Ivy アプリケーションに展開し、 ``your_host/your_application/pro/portal-migration/175F92F71BC45295/startMigrateConfiguration.ivp`` リンクにアクセスして実行します。
   

   .. important::
      * アプリケーションが複数ある場合は、1 つのアプリケーションにのみ展開し、 ``https://portal.io/Portal/pro/portal-migration/175F92F71BC45295/startMigrateConfiguration.ivp`` などの移行リンクにアクセスして実行してください。
        

      * 管理者アカウントを使用してサインインしてください。
      * 移行プロセスは一度だけ実行してください。

#. タスクの分析コンポーネントに移動する方法を変更しました。 ``Start Processes/TaskAnalysis/start.ivp`` プロセスが新しい場所である ``Start Processes/PortalStart/showTaskAnalysis.ivp`` に移動されました。

#. 通知、サードパーティーのアプリケーション、デフォルトの統計チャート、アプリケーションのお気に入りのプロセス、公開用外部リンク、Express のプロセスの設定を BusinessData から変数に移動しました。

#. DefaultApplicationHomePage.ivp と PortalDashboardConfiguration.ivp に関連する変更を行ったため、PortalStart プロセスを PortalTemplate からプロジェクトにコピーしてください。
   それから、プロジェクトの PortalStart をカスタマイズしてください。

#. 日付の形式を確認するため、TaskCreationDateFilter、CaseCreationDateFilter などのポータルの日付フィルターの ``<p:messages for="..." />`` メッセージが各カレンダーコンポーネントに追加されました。
   プロジェクトでカスタマイズされた日付フィルターを使用している場合は、それに合わせてテンプレートを交信してください。

#. ``DefaultChart.p.json`` 、 ``DefaultUserProcess.p.json`` 呼び出し可能プロセスが削除されました。これらは、:dev-url:`変数 </doc/|version|/designer-guide/configuration/variables.html>` の設定に置き換えられます。
   
   

9.1 から 9.2 への移行
----------------------------------------------------------------------

#. :download:`MigrateData.iar <documents/MigrateData.iar>` プロジェクトを Ivy アプリケーションに展開し、 ``your_host/your_application/pro/MigrateData/175F92F71BC45295/startMigrateConfiguration.ivp`` リンクにアクセスして実行します。
   

   アプリケーションが複数ある場合は、1 つのアプリケーションにのみ展開し、 ``your_host/your_application/pro/MigrateData/175F92F71BC45295/startMigrateConfiguration.ivp`` リンクにアクセスして実行してください。
   

   例： ``https://portal.io/Portal/pro/MigrateData/175F92F71BC45295/startMigrateConfiguration.ivp``

    移行プロセスは一度だけ実行してください。

#. ポータルのマルチアプリケーションの実装を削除しました。そのため、以下を適合させる必要があります。

   - この呼び出し可能プロセスをオーバーライドしている場合は、 ``ChangePassword.mod`` の ``PasswordService`` 開始プロセスのシグネチャを適合させます。
   - ``ProcessStartCollector`` を使用している場合は、 ``ProcessStartCollector(application)`` コンストラクターを ``ProcessStartCollector()`` に置き換えます。
   - TaskLazyDataModel、CaseLazyDataModel をカスタマイズしている場合は、検索基準から ``setInvolvedApplications()`` メソッド、``setInvolvedUsername`` を削除します。

#. PortalNavigatorInFrame.java のメソッドを非 static から static に変更します。

#. PortalTemplate の CaseDetails コンポーネントが削除されました。

#. ``OpenPortalSearch.mod``、 ``OpenPortalTasks.mod`` 、 ``OpenPortalTaskDetails.mod`` 、 ``OpenPortalCases.mod`` 、 ``OpenPortalCaseDetails.mod`` プロセスが非推奨の呼び出し可能プロセスになりました。

   ポータルでは、:dev-url:`|ivy| HtmlOverride ウィザード </doc/|version|/designer-guide/how-to/overrides.html?#override-new-wizard>` を使用して、 ``ポータルの HTML ダイアログ`` をカスタマイズすることを推奨します。

   .. important:: カスタマイズダイアログを開く呼び出し可能プロセスは将来削除されます。新しいプロジェクトでは使用しないでください。

#. ivy-icon.css を削除し、現在のクラスを `HTML Dialog Demo <https://market.axonivy.com/html-dialog-demo>`_ にある Streamline アイコンからの新しいクラスに置き換えました。そのため、ivy-icon.css のクラスを使用しているファイルを更新する必要があります。

#. taskItemDetailCustomPanelTop、taskItemDetailCustomPanelBottom をカスタマイズしている場合は、:ref:`TaskItemDetail のオーバーライド方法 <customization-task-item-details-ja>` に従って、カスタムウィジェットを追加してください。

#. ``caseItemDetailCustomTop``、 ``caseItemDetailCustomMiddle``、 ``caseItemDetailCustomBottom`` をカスタマイズしている場合は、:ref:`CaseItemDetail のオーバーライド方法 <customization-case-item-details-ja>` に従って、これらのカスタムウィジェットを追加してください。

8.x から 9.1 への移行
----------------------

#. SubMenuItem.java の ``views`` を削除します。 ``LoadSubMenuItems`` 呼び出し可能プロセスをオーバーライドしている場合は、適合させます。

#. ``<ui:param name="viewName" value="TASK" />`` パラメーターをカスタマイズした ``PortalTasksTemplate`` に追加して、タスクリストのパンくずリストを表示します。

#. ``<ui:param name="viewName" value="CASE" />`` パラメーターをカスタマイズした ``PortalCasesTemplate`` に追加して、ケースリストのパンくずリストを表示します。

#. Ivy のコアにより、Ivy の URI が拡張されました。ポータルで移行が必要です。アプリケーションごとに、以下の手順を実行してください。
   

   #. :download:`PortalUrlMigration.iar <documents/PortalUrlMigration.iar>` プロセスモデルを Ivy アプリケーションに展開します。
      

   #. ``migratePortalUrl.ivp`` を 1 回実行し、エラーなしで他のページ（ホームページ）にリダイレクトされるまで待ちます。
      

   #. 移行に成功したら、 ``migratePortalUrl.ivp`` プロセスモデルを削除します。

#. HOMEPAGE_URL（シングルポータルアプリモード）と登録済みアプリケーションのリンク（マルチポータルアプリモード）が利用できなくなりました。
   ポータルに新しいポータルホームページの場所を認識させるため、プロジェクトでデフォルトのページを設定する必要があります。
   
   :dev-url:`デフォルトのページ </doc/|version|/designer-guide/user-interface/default-pages/index.html>` に従って、デフォルトのページをカスタマイズしてください。
   

#. ポータルは、 SASS の代わりに |css_variable| を使用します。そのため、SASS 構文を新しい CSS 変数に変換するか、|css_variable_convert| などのオンラインツールを使用して変換する必要があります。
   
   

#. エンジンの管理者が ``Portal.Cases.EnableOwner`` 設定をアクティブ化しており、カスタマイズされたケースリストがある場合は、このフィールドをそのケースリストにカスタマイズします（フィルターの追加、列の設定、ヘッダーなど）。
   
   

#. 9.1 から、Ivy エンジンは新しい方法を使用して、ユーザーのデータを同期します。
   そのため、ポータルでユーザーに関連するデータを適合させる必要があります。正しく動作するように、いくつかのデータを移行する必要があります。
   以下の手順に従って、アプリケーションの既存のデータを移行してください。
   

   :download:`MigrateRelatedDataOfUserTo9.iar <documents/MigrateRelatedDataOfUserTo9.iar>` プロセスモデルをアプリケーションに展開します。
     

   - ``migratePrivateChat.ivp`` を実行して、プライベートチャットメッセージを移行します。

   - ``migrateGroupChat.ivp`` を実行して、グループチャットを移行します。

   - ``migrateUserProcessesAndExternalLinks.ivp`` を実行して、ユーザープロセスと外部リンクを移行します。

   - ``migrateExpressProcesses.ivp`` を実行して、Express のプロセスを移行します。アプリケーションに Express が含まれない場合は、この手順をスキップしてください。
     
   - Ivy エンジンを再起動します。

#. ``simplePageContent`` の代わりに、 ``pageContent`` を使用して ``BasicTemplate.xhtml`` のセクションを定義します。

#. ``TaskTemplate-7`` が削除されました。 ``TaskTemplate-8`` に変更してください。 ``TaskTemplate`` も削除されました。 ``frame-8`` （Ivy により提供）に変更してください。

#. ``MenuKind`` enum に複数のエントリ（EXTERNAL_LINK）があります。これは、項目が外部リンクの場合に使用します。
   内部リンクの場合は CUSTOM を使用してください。

#. ``PortalNavigatorInFrameAPI#navigateToPortalHome`` メソッドは非推奨です。代わりにページの ivy.html.applicationHomeRef() にリダイレクトしてください。
   

8.x から 9.x への移行
--------------------------------------------------

``8.x から ... への移行`` から ``... から9.x への移行`` までのすべての手順を実行する必要があります。


.. _installation-release-notes-ja:

リリースノート
==========================

ここには、|ivy| の前回の正式な製品リリース以降のすべての関連する変更が記載されています。


12.0.4 での変更内容
---------------------------------------------------
ダッシュボード、グローバル検索、関連ケース、複合モードのプロセスウィジェットのケースウィジェットでケースをクリックしたときに、ケースの詳細またはビジネスの詳細へのアクセスを切り替える ``Portal.Cases.BehaviourWhenClickingOnLineInCaseList`` ポータル設定を導入しました。

12.0.1 での変更内容
------------------------------------------------
- タスクの詳細とケースの詳細のドキュメントプレビュー機能を導入しました。画像（png または jpeg）、プレーンテキスト（txt または log）、pdf ドキュメントをプレビューできます。
- ポータルのドキュメントのプレビューアイコンの表示設定を制御する ``Portal.Document.EnablePreview`` ポータル設定を導入しました。

12.0.0 での変更内容
-------------------------------------------------

- 古い統計チャートが削除されました。代わりに新しい :ref:`statistic-chart-ja` を使用してください。
- IFrame のタスクを設定する IFrameTaskConfig コンポーネントを導入しました。詳細については、:ref:`iframe-configure-template-ja` を参照してください。
- ダッシュボードウィジェットのコンセプトに合わせて、:ref:`全タスクリストページ <full-task-list-ja>` と :ref:`全ケースリスト <full-case-list-ja>` のデザインが一新されました。ページは 1 つの全幅ウィジェットを含むダッシュボードとして機能します。他のウィジェットと同じように調整して設定できます。
- 複数ケースのオーナーをサポートします。1 つのケースのオーナーの並べ替え機能は削除されます。

11.2.0 での変更内容
--------------------------------------------

- ``ch.ivy.addon.portalkit.publicapi.ApplicationMultiLanguageAPI`` クラスが削除され、サポート対象外になりました。代わりに ``com.axonivy.portal.components.publicapi.ApplicationMultiLanguageAPI`` を使用してください。
- ``ch.ivy.addon.portalkit.publicapi.CaseAPI`` クラスが削除され、サポート対象外になりました。代わりに ``com.axonivy.portal.components.publicapi.CaseAPI`` を使用してください。
- ``ch.ivy.addon.portalkit.publicapi.PortalGlobalGrowInIFrameAPI`` クラスが削除され、サポート対象外になりました。代わりに ``com.axonivy.portal.components.publicapi.PortalGlobalGrowInIFrameAPI`` を使用してください。
- ``ch.ivy.addon.portalkit.publicapi.PortalNavigatorAPI`` クラスが削除され、サポート対象外になりました。代わりに ``com.axonivy.portal.components.publicapi.PortalNavigatorAPI`` を使用してください。
- ``ch.ivy.addon.portalkit.publicapi.ProcessStartAPI`` クラスが削除され、サポート対象外になりました。代わりに ``com.axonivy.portal.components.publicapi.ProcessStartAPI`` を使用してください。
- ``ch.ivy.addon.portalkit.publicapi.RoleAPI`` クラスが削除され、サポート対象外になりました。代わりに ``com.axonivy.portal.components.publicapi.RoleAPI`` を使用してください。
- ``ch.ivy.addon.portalkit.publicapi.TaskAPI`` クラスが削除され、サポート対象外になりました。代わりに ``com.axonivy.portal.components.publicapi.TaskAPI`` を使用してください。
- ``com.axonivy.portal.components.util.PortalNavigatorInFrameAPI`` クラスが削除され、サポート対象外になりました。代わりに ``com.axonivy.portal.components.publicapi.PortalNavigatorInFrameAPI`` を使用してください。
- プロセスダッシュボードウィジェットの並べ替え機能を導入しました。ユーザーは、インデックス、アルファベット順、ユーザー設定順序でプロセスを並べ替えることができます。
- プロセスビューアーの現在のステップを強調表示する ``ic:com.axonivy.portal.components.ProcessViewer`` コンポーネントの ``taskId`` パラメーターを導入しました。
- ポータルのレガシーダッシュボードが削除されました。 ``AxonIvyExpress`` モジュールは ``axonivy-express`` という名前に変更され、Axon Ivy Market で入手できるようになりました。

10 での変更内容
--------------------------------------------

- 全タスクリスト、全ケースリスト、ダッシュボードのタスクリスト、ダッシュボードのケースリスト、タスクの分析に ``アプリケーション`` フィルターと ``アプリケーション`` 列を導入しました。

9.4 での変更内容
---------------------------------------------

- ``PortalStyle``、 ``PortalKit``、 ``PortalTemplate`` プロジェクトを ``portal`` という名前の 1 つのプロジェクトにまとめました。タスクリストの行、新しいダッシュボードのタスクウィジェット、ケースの詳細の関連するタスクをクリックしたときの動作を設定する ``Portal.Tasks.BehaviourWhenClickingOnLineInTaskList`` ポータル設定を導入しました。各ユーザーはユーザープロファイルから変更を行うことができます。  

- 統計チャートのスケーリングの定期的なリクエストを行う際の間隔を秒単位で設定する ``Portal.StatisticChartScalingInterval`` ポータル設定を導入しました。

- ログインページのフッターの表示設定を制御する ``Portal.LoginPage.ShowFooter`` ポータル設定を導入しました。

- デフォルトのテーマモード（ライトまたはダーク）を設定する ``Portal.Theme.Mode`` ポータル設定を導入しました。

- トップバーのテーマの切り替えボタンの状態を制御する ``Portal.Theme.EnableSwitchThemeModeButton`` ポータル設定を導入しました。

- ポータルの全タスクリストと全ケースリストに新しい ``タスク ID``、 ``タスク名``、 ``ケース ID``、 ``ケース名`` フィルターを導入しました。

- ``プロセスビューアー`` ページを導入しました。ユーザーは、プロセスの開始を視覚的に表示できます。 :ref:`プロセスビューアーの表示 <how-to-show-process-viewer>` で詳細をご覧ください。

- 値の形式を設定する ``書式設定言語の設定`` を導入しました。例えば、小数点は世界中の異なる地域で異なる形式で表示されます。

- ``DefaultChartColor.p.json`` サブプロセスを削除し、デフォルトのチャートの色をカスタマイズするためのポータルの変数を導入しました。

- 新しい ``portal-components`` プロジェクトに以下のコンポーネントを導入しました。

   - :ref:`ユーザーの選択コンポーネント <components-portal-components-user-selection-ja>`

   - :ref:`ロールの選択コンポーネント <components-portal-components-role-selection-ja>`

   - :ref:`ドキュメントテーブルコンポーネント <components-portal-components-document-table-ja>`

   - :ref:`プロセスチェーンコンポーネント <components-portal-components-process-chain-ja>`

   - :ref:`プロセスビューアーコンポーネント <components-portal-components-process-viewer-ja>`

9.3 での変更内容
---------------------------------------

 ポータルのグループ ID を変更するときに PortalGroupId 変数を更新する必要はなくなりました。


9.2 での変更内容
----------------------------------------

- ポータルのタスクリストとタスクの状態フィルターに ``破棄済``、 ``失敗``、 ``参加失敗``、 ``イベント待機中`` などの新しいタスクの状態を含めました。

- ポータルのケースリストとケースの状態フィルターに新しいケースの状態 ``破棄済`` を含めました。

- :ref:`ワークフローイベントテーブル <how-to-show-workflow-events-ja>` を導入しました。:bdg-warning:`🔑WorkflowEventReadAll` 権限を持つユーザーは、すべての ``WORKFLOW_EVENTS`` を表示できます。

- デフォルトのホームページを設定する ``Portal.Homepage`` ポータル設定を導入しました。各ユーザーはユーザープロファイルから変更を行うことができます。

- :ref:`ポータルのケース項目の詳細 <customization-case-item-details-ja>` をカスタマイズする新しい方法を導入しました。ケースの詳細ページとケース情報ダイアログには、同じケース情報が表示されます。

- :ref:`ポータルのタスク項目の詳細 <customization-task-item-details-ja>` をカスタマイズする新しい方法を導入しました。

- ポータルのボタンアイコンの表示設定を制御する ``Portal.ShowButtonIcon`` ポータル設定を導入しました。

- ログインページを表示するか、非表示にして代わりにエラーページを表示する ``PortalLoginPageDisplay`` という名前の新しい変数を導入しました。

- マルチアプリケーションがサポート対象外になりました。ポータルは現在のアプリケーションでのみ動作します。つまり、管理者は新しい Ivy アプリケーションを追加できません。

- 統計チャートは、サポートされる言語ごとに複数の名前をサポートします。

- ポータルは、複数言語のユーザーのお気に入りをサポートします。

- ポータルは SVG 形式のロゴをサポートします。

9.1 での変更内容
------------------------------------------

- スタイルのカスタマイズ方法をリファクタリングしました。今後は、ポータルは CSS をカスタマイズするテクノロジーとして CSS 変数を使用します。

- ポータルのボタンアイコンの表示設定を制御する ``Portal.ShowButtonIcon`` ポータル設定を導入しました。

- アイコンデコレーターを含む新しいポータルダイアログを導入しました。

- TaskTemplate-7、TaskTemplate、TwoColumnTemplate が削除されました。


.. |css_variable| raw:: html

   <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties" target="_blank">CSS Variable</a>

.. |css_variable_convert| raw:: html

   <a href="https://www.npmjs.com/package/sass-to-css-variables" target="_blank">SASS to CSS Variables</a>
