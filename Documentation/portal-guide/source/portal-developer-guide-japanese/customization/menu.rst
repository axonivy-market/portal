.. _customization-menu-ja:

ユーザープロファイルのメニューとデフォルトのホームページ
============================================================================================

.. _customization-menu-introduction-ja:

概要
------------

ポータルのメインメニューには、ダッシュボード、プロセス、タスク、ケースの 4 つのデフォルトの項目が表示されます。

|default-menu-items|

ポータルでは、ポータルの権限を使用して、カスタムメニュー項目を追加したり、デフォルトの項目を非表示にしたりすることができます。

外部リンク以外のすべてのメニュー項目をユーザープロファイルのデフォルトのホームページとして設定できます。

.. _customization-menu-customization-ja:

カスタムメニュー項目
---------------------------------------------

メニューのタイプ
++++++++++++++++++++++++++++++++++++

以下の 2 つのタイプのカスタムメニュー項目があります。

    - Ivy プロセス：クリックすると、Ivy プロセスがトリガーされます。ユーザーのプロファイルのデフォルトのホームページとして設定できます。

    - 外部リンク：新しいタブで外部リンクを開きます。ユーザーのプロファイルのデフォルトのホームページとして設定することはできません。

カスタムメニュー項目のデフォルトのタイプは、Ivy プロセスです。カスタムメニュー項目のタイプを外部リンクに変更するには、 ``isExternalLink`` 属性を ``true`` に設定します。

メニューのインデックス
+++++++++++++++++++++++++++++++++++++++++++++

カスタムメニュー項目は、デフォルトのメニュー項目の下に表示されます。

カスタムメニュー項目の順序を設定するには、 ``index`` 属性に番号を割り当てます。指定されたインデックスに基づいて、項目が表示されます。
カスタムメニュー項目のインデックスが作成されていない場合は、アルファベット順に表示されます。

カスタムメニュー項目の追加
-------------------------------------------------------------

ポータルでカスタムメニュー項目を追加する方法は 2 つあります。詳細については、以下のセクションを参照してください。

呼び出し可能サブプロセス
++++++++++++++++++++++++++++++++++++++++++++++++++

カスタムメニュー項目を追加するには、以下を使用して呼び出し可能サブプロセスを作成します。

**シグネチャ**：portalLoadSubMenuItems

.. csv-table::
  :file: tables/portal-load-sub-menu.csv
  :header-rows: 1
  :widths: 50, 50

Ivy プロセスにリダイレクトするメニューの例

   .. code-block:: javascript

        import com.axonivy.portal.components.configuration.CustomSubMenuItem;
        import com.axonivy.portal.components.publicapi.ProcessStartAPI;
        import org.apache.commons.lang3.StringUtils;

        String userExampleGuideLink =
           ProcessStartAPI.
           findStartableLinkByUserFriendlyRequestPath("Start Processes/UserExampleGuide/userExampleGuide.ivp");
        
        if (!StringUtils.isEmpty(userExampleGuideLink)) {  
           CustomSubMenuItem userExampleGuide = new CustomSubMenuItem();

           userExampleGuide.setIcon("si si-bulb");
           userExampleGuide.setLabel("User example guide");
           userExampleGuide.setLink(userExampleGuideLink + "?embedInFrame");
           userExampleGuide.setIndex(0);

           in.subMenuItems.add(userExampleGuide);
        }

外部リンクの例：

   .. code-block:: javascript

        import com.axonivy.portal.components.configuration.CustomSubMenuItem;

        CustomSubMenuItem external = new CustomSubMenuItem();
        external.setIcon("si si-information-circle");
        external.setLabel("External page");
        external.setLink("https://your_external_page.com");
        external.setIsExternalLink(true);
        external.setIndex(1);

        in.subMenuItems.add(external);

.. tip::
   カスタムメニュー項目の作成方法の例については、 ``portal-developer-examples`` プロジェクトの ``CustomLoadSubMenuItems`` プロセスを参照してください。
   

.. tip::
    | カスタムメニュー項目のラベルを複数の言語で表示したい場合は、CMS エントリを作成し、 ``ApplicationMultiLanguageAPI.getCmsValueByUserLocale`` メソッドを使用します。 
    
    | 例： ``subMenuItem.setLabel(ApplicationMultiLanguageAPI.getCmsValueByUserLocale<CMS_URI>));``

ポータルの変数
++++++++++++++++++++++++

呼び出し可能サブプロセスを作成する方法のほかにも、ポータルの ``Portal.CustomMenuItems`` 変数でカスタムメニュー項目を定義できます。


以下は、Portal.CustomMenuItems 変数のカスタムメニュー項目の例です。

   .. code-block:: javascript

        [
            {
                "index": 0,
                "link": "https://your_external_page.com",
                "label": "External link",
                "isExternal": "true",
                "icon": "si si-bulb",
                "version": "11.2.0"
            }, {
                "index": 1,
                "link": "/designer/pro/portal-user-examples/17236DB1D3DA14C0/userExampleGuide.ivp",
                "label": "Guideline Ivy process",
                "isExternal": "false",
                "version": "11.2.0"
            }
        ]

カスタムメニュー項目の JSON の基本的な構造

    ``index``：メインメニューのメニュー項目のインデックス。

    ``link``：メニュー項目のリンク。Ivy プロセスのユーザーフレンドリーなリクエストパスまたはプロセス ID を設定できます。

    ``label``：メニュー項目のラベル。

    ``isExternal``：カスタムメニュー項目を外部リンクとしてマークするには、 ``true`` に設定します。

    ``version``：メニューのバージョン。

.. _customization-menu-hide-default-menu-item-ja:

デフォルトのメニュー項目の非表示
----------------------------------------------------

:ref:`ポータルの権限 <settings-permission-settings-others-ja>` を使用して、プロセス、タスク、ケースの 3 つのメインメニューのデフォルトのメニュー項目を非表示にすることができます。


.. |default-menu-items| image:: ../../screenshots/dashboard/expanded-left-menu.png
