.. _list-permissions-ja:

権限リスト
========================

ポータルの柔軟なセキュリティシステムを利用して、アプリケーションにアクセスできるユーザーと、それらのユーザーがポータルで実行できる動作／表示できる内容を設定できます。


ポータルのタスクの権限
--------------------------------

- _`TaskReadAll`
    - すべてのタスクを表示する権限

- _`SystemTaskReadAll`
    - システムタスクを表示する権限

- _`TaskReadOwnCaseTasks`
    - 関連するタスクを表示する権限

    - デフォルトでは Everybody ロールに付与

- _`TaskParkOwnWorkingTask`
    - 自分のタスクを予約する権限

    - デフォルトでは Everybody ロールに付与

- _`TaskResetOwnWorkingTask`
    - 自分のタスクをリセットする権限 

    - デフォルトでは Everybody ロールに付与

- _`TaskWriteName`
    - タスク名を変更する権限

- _`TaskWriteDescription`
    - タスクの説明を変更する権限

- _`TaskWriteOriginalPriority`
    - タスクの優先度を変更する権限

- _`TaskWriteActivator`
    - タスクを委任する権限

    - デフォルトでは Everybody ロールに付与

- _`TaskWriteExpiryActivator`
    - 有効期限の責任者を変更する権限

- _`TaskWriteDelayTimestamp`
    - 遅延時間を変更する権限

- _`TaskReset`
    - すべてのタスクをリセットする権限

- _`TaskDestroy`
    - タスクの破棄アクションを表示する権限

- _`TaskWriteExpiryTimestamp`   
    - 期限を変更するかどうかを制御

- _`TaskWriteActivatorOwnTasks`
    - 自分のタスクを委任する権限

- _`TaskDisplayAdditionalOptions`
    - 追加のアクションを表示する権限

    - デフォルトでは Everybody ロールに付与

- _`TaskDisplayResetAction`
    - リセットアクションを表示する権限

    - デフォルトでは Everybody ロールに付与

- _`TaskDisplayReserveAction`
    - 予約アクションを表示する権限

    - デフォルトでは Everybody ロールに付与

- _`TaskDisplayDelegateAction`
    - 委任アクションを表示する権限

    - デフォルトでは Everybody ロールに付与

- _`TaskDisplayWorkflowEventAction`
    - ワークフローイベントアクションを表示する権限

- _`TaskDisplayDestroyAction`
    - 破棄アクションを表示する権限

- _`TaskResetReadyForJoin`
    - 参加準備完了状態にあるタスクをリセットする権限

- _`ShareTaskDetailsLink`
    - タスクの詳細に共有ボタンを表示する権限

    - デフォルトでは Everybody ロールに付与

ポータルのケースの権限
------------------------------------------------

- _`CaseReadAll`
    - すべてのケースを表示する権限

- _`CaseDestroy`
    ケースを削除する権限

- _`CaseWriteName`
    - ケース名を変更する権限

- _`CaseWriteDescription`
    - ケースの説明を変更する権限

- _`ShowAllTasksOfCase`
    - 関連するタスクのアクションを表示する権限 

    - デフォルトでは Everybody ロールに付与

- _`ShowCaseDetails`
    - ビジネスの詳細アクションを表示する権限

    - デフォルトでは Everybody ロールに付与

- _`ShareCaseDetailsLink`
    - ケースの詳細に共有ボタンを表示する権限

    - デフォルトでは Everybody ロールに付与

ポータルの一般的な権限
---------------------------------------------

- _`RoleCreate`
    - 新しい動的ロールを作成する権限 

- _`RoleDelete`
    - 動的ロールを削除する権限

- _`RoleMove`
    - ロールを移動する権限（親ロールを選択）

- _`RoleReadAll`
    - すべてのロールを表示する権限

    - デフォルトでは Everybody ロールに付与

- _`DocumentRead`
    - すべてのドキュメントを表示する権限

- _`DocumentWrite`
    - ドキュメントを更新、削除する権限

- _`DocumentOfInvolvedCaseWrite`
    - ドキュメントを更新、削除する権限

    - デフォルトでは Everybody ロールに付与

- _`DashboardWriteOwn`
    - 個人用ダッシュボードを更新する権限

    - デフォルトでは Everybody ロールに付与

- _`DashboardWritePublic`
    - 公開用ダッシュボードを更新する権限

- _`AccessFullProcessList`
    - 全プロセスリストにアクセスする権限。 :ref:`full-process-list-ja` を参照してください。

    - デフォルトでは Everybody ロールに付与

- _`AccessFullTaskList`
    - 全タスクリストにアクセスする権限。 :ref:`full-task-list-ja` を参照してください。

    - デフォルトでは Everybody ロールに付与

- _`AccessFullCaseList`
    - 全ケースリストにアクセスする権限。 :ref:`full-case-list-ja` を参照してください。

    - デフォルトでは Everybody ロールに付与

- _`TaskCaseAddNote`
    - タスク／ケースにノートを追加する権限

    - デフォルトでは Everybody ロールに付与

- _`TaskCaseShowMoreNote`
    - ［さらにノートを表示］を表示する権限

    - デフォルトでは Everybody ロールに付与

- _`CreatePublicExternalLink`
    - 公開用外部リンクを作成する権限。他のすべてのユーザーは全プロセスリストのリンクを表示できます。

- _`RoleManagement`
    - ロールの管理タブにアクセスする権限

- _`NewsManagement`
    - ニュースフィードのコンテンツを管理する権限

- _`PasswordValidation`
    - 管理者設定ページのパスワード検証セクションにアクセスする権限

- _`DashboardExportOwn`
    - 個人用ダッシュボードをエクスポートする権限

- _`DashboardExportPublic`
    - 公開用ダッシュボードをエクスポートする権限

- _`DashboardImportOwn`
    - 個人用ダッシュボードをインポートする権限

- _`DashboardImportPublic`
    - 公開用ダッシュボードをインポートする権限

- _`ShareDashboardLink`
    - ダッシュボードのリンクを共有する権限

    - デフォルトでは Everybody ロールに付与

.. _portal-absence-and-sub-permission-ja:

ポータルの不在者と代行者の権限
-------------------------------------------------------

- _`UserCreateAbsence`
    - すべてのユーザーの不在を作成、編集する権限

- _`UserCreateOwnAbsence`
    - 自分の不在を作成、編集する権限

    - デフォルトでは Everybody ロールに付与

- _`UserDeleteAbsence`
    - すべてのユーザーの不在エントリを削除する権限

- _`UserDeleteOwnAbsence`
    - 自分の不在エントリを削除する権限

    - デフォルトでは Everybody ロールに付与

- _`UserReadAbsences`
    - すべてのユーザーの不在を閲覧する権限

- _`UserReadOwnAbsences`
    - 自分の不在を閲覧する権限

    - デフォルトでは Everybody ロールに付与

- _`UserCreateSubstitute`
    - すべてのユーザーの代行者を作成する権限

- _`UserCreateOwnSubstitute`
    - 自分の代行者を作成する権限

    - デフォルトでは Everybody ロールに付与

- _`UserReadSubstitutes`
    - すべてのユーザーの代行者を閲覧する権限

- _`UserSetOwnPassword`




