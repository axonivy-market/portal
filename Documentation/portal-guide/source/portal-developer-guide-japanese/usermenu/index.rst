.. _customization-user-menu-ja:

ユーザーメニュー
===============================

.. _customization-user-menu-introduction-ja:

概要
------------

ユーザーメニューは設定可能です。ユーザーは、ユーザー項目を追加、削除、編集できます。

以下のタイプのユーザー項目が利用可能です。

- 外部リンクを開く

- Ivy プロセスの開始

**Portal.UserMenu** 変数を設定することにより、ユーザーメニュー項目と各項目の詳細を事前に定義できます。


.. _customization-user-menu-definition-ja:

独自のユーザーメニューの定義
-----------------------------------------------

ポータルでは、複数のユーザー項目を設定できます。これらはデフォルトのユーザー項目の下に表示されます。


ユーザー項目ごとにユーザー項目の ID、タイトル、権限を事前に定義できます。


|user-menu-configuration|

以下はユーザー項目を設定する JSON の例です。

.. code-block:: javascript

  [
      {
          "id" : "axon-ivy",
          "title" : "|ivy|",
          "permissions": ["#demo"],
          "url" : "https://www.axonivy.com/"
      },
      {
          "id" : "re-order-dashboard",
          "titles": [
              {
              "locale": "en",
              "value": "Reorder your dashboards"
              },
              {
              "locale": "de",
              "value": "Dashboards neu anordnen"
              }
          ],
          "permissions": ["Employee", "AXONIVY_PORTAL_ADMIN", "#daniel"],
          "url": "Start Processes/ExamplePortalStart/DashboardReorder.ivp",
          "params": {
              "isPublicDashboard":"false"
          }
      }
  ]

..

各ユーザー項目の JSON の構造：

    ``id``：ユーザー項目の ID。

    ``titles``：ユーザー項目の複数言語のタイトル。

    ``permissions``：ユーザー項目を表示できるユーザーとロール。

    .. tip::
       ユーザー項目の ``permissions`` を定義しない場合、すべてのユーザーがそのユーザー項目を表示できます。

    ``url``：外部ウェブページの URL またはリダイレクトしたい Ivy プロセスの開始。

    .. tip::
        Ivy プロセスの開始 URL は、プロセスへの **相対リンク** です。

          - 例： ``Start Processes/CreateTestData/CreateTestDataForCustomizedDashboard.ivp``

    ``params``：上記で定義された Ivy プロセスのパラメーター。以下のようにキーと値の組み合わせで各パラメーターを定義できます。
    

      - key：Ivy プロセスのパラメーターの名前。
      - value：パラメーターの事前定義済みの値

    .. tip::
       URL に外部ウェブページが示されている場合、パラメーターは必要ありません。

.. |user-menu-configuration| image:: ../../screenshots/settings/user-menu-configuration.png
