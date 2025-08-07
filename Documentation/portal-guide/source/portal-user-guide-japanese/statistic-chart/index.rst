.. _statistic-chart-ja:

統計チャート
**************************

概要
---------------

ポータルは、多くのカスタマイズ機能を備え、ユーザーのタスク、ケース、全体的な生産性の理解を深める新しい統計チャートウィジェットを提供します。

使用方法
------------------------------

ダッシュボード設定で統計機能を使用するには、以下の手順に従ってください。

- ウィジェットを追加したいダッシュボードのダッシュボード設定に移動し、:guilabel:`ウィジェットの追加` を選択します。

- **統計ウィジェット** ドロップダウンメニューからチャートを選択します。

|statistic-widget-list-page|

標準チャート
----------------------

- 優先度別タスク

    |tasks-by-prior-pie-chart|

    - この円グラフは、すべてのタスクの数を優先度別に表示します。
    - チャートのタイプ：:bdg-info:`円グラフ`

- 3 日以内に期限切れ

    |top-prior-chart|

    - この棒グラフは、ユーザーが作業できるすべてのタスクの内、今後 3 日以内に期限が切れるものの数を、優先度別にグループ化して表示します。
    - チャートのタイプ：:bdg-info:`棒グラフ`

- 優先度別タスク

    |tasks-by-prior-bar-chart|

    - この棒グラフは、ユーザーが作業できるすべてのタスクの数を、優先度別にグループ化して表示します。
    - チャートのタイプ：:bdg-info:`棒グラフ`

- ケースカテゴリー平均実行時間

    |avg-runtime-chart|

    - このグラフは、カテゴリ別のケース平均処理時間を表示します。 
    - チャートのタイプ：:bdg-info:`棒グラフ`

- 1 日あたりの新規ケース数

    |new-cases-chart|

    - このグラフは、1 日あたりの開始ケース数（過去 5 日間）を表示します。
    - チャートのタイプ：:bdg-info:`折れ線グラフ`

- 1 日あたりの完了ケース数

    |completed-cases-chart|

    - このグラフは、1 日あたりの完了ケース数（過去 5 日間）を表示します。
    - チャートのタイプ：:bdg-info:`折れ線グラフ`

- 実行中のケース

    |running-cases-chart|

    - これは、ユーザーが関与しているすべての実行中のケースの数を表示します。
    - チャートのタイプ：:bdg-info:`数値表`

- 今週末までに期限が切れるタスク

    |tasks-expire-end-week-chart|

    - これは、今週末までに期限が切れるすべてのタスクの数を表示します。
    - チャートのタイプ：:bdg-info:`数値表`

- 優先度別タスク

    |tasks-by-prior-number-chart|

    - これは、ユーザーが作業できるすべてのタスクの数を優先度別に表示します。
    - チャートのタイプ：:bdg-info:`数値表`

- オープンなタスク

    |open-tasks-chart|

    - これは、ユーザーが作業できるすべてのオープンタスクの数を表示します。
    - チャートのタイプ：:bdg-info:`数値表`

- 本日納期のタスク

    |due-today-chart|

    - これは、今日納期のタスクの数を表示します。
    - チャートのタイプ：:bdg-info:`数値表`

How to configure new statistic chart
------------------------------------

- Navigate to Dashboard Configuration of the dashboard and select :guilabel:`Create custom statistic widget`.

    |create-new-custom-statistic-widget|

- From the configuration for statistic chart, fill in the form to create new statistic chart.

    |custom-statistic-widget-configuration-page|

Available values for configuring statistic chart 
------------------------------------------------

.. list-table::

 * - **Value**
   - **Usage Guideline**
 * - Name
   - | Name of the statistic chart.
 * - Description
   - | Description for the statistic chart.
 * - Icon
   - | Icon for custom statistic chart.
     | Click on the plus icon at the end of the line to see full list of icons.
 * - Chart type
   - | Type of the chart (Bar, Line, Pie, Number).
 * - For
   - | Chart target (Task, Case).
 * - KPI
   - | List of numeric custom fields is shown alongside with `Counting`.
     | The calculation using below aggregation method will be performed on selected KPI. 
     | If `Counting` is selected to count numbers of tasks or cases, the aggregation method 
     | selection will be disabled.
 * - Aggregation method
   - | List of 4 aggregation methods: `Sum`, `Average`, `Max`, `Min`.
 * - Group by
   - | Aggregation to group all the results, the drop down values depend on the chart target.
     | Currently we don't support aggregations have type number.
 * - Filters
   - | Works like complex filter of Portal.
     | Please refer to :ref:`complex-filter` for more details.
 * - Permissions
   - | Permission for the current statistic chart.
 * - Auto-refresh enabled
   - | The statistic can be auto-refreshed after a certain prediod.
     | Click on this button to turn on/off the feature.
 * - Auto-refresh interval (seconds)
   - | The time for statistic chart to be auto-refreshed in second.
     | Minimum number is 60.
 * - X axis title
   - | X title for the statistic chart.
 * - Y axis title
   - | Y title for the statistic chart.
 * - Color 1-8
   - | Colors for data in the chart.
 * - Hide label
   - | Toggle to show label of number chart.

.. |statistic-widget-list-page| image:: ../../screenshots/statistic/statistic-widget-list.png
   :alt: 統計ウィジェットのリスト
.. |dashboard-statistic-widget-demo| image:: ../../screenshots/statistic/dashboard-statistic-widget-demo.png
   :alt: 統計ウィジェットのデモ
.. |tasks-by-prior-pie-chart| image:: ../../screenshots/statistic/tasks-by-prior-pie-chart.png
   :alt: 優先度別タスクの円グラフ
.. |tasks-by-prior-bar-chart| image:: ../../screenshots/statistic/tasks-by-prior-bar-chart.png
   :alt: 優先度別タスクの棒グラフ
.. |tasks-by-prior-number-chart| image:: ../../screenshots/statistic/tasks-by-prior-number-chart.png
   :alt: 優先度別タスクの数値表
.. |avg-runtime-chart| image:: ../../screenshots/statistic/avg-runtime-chart.png
   :alt: 平均実行時間のグラフ
.. |completed-cases-chart| image:: ../../screenshots/statistic/completed-cases-chart.png
   :alt: 完了ケースのグラフ
.. |due-today-chart| image:: ../../screenshots/statistic/due-today-chart.png
   :alt: 今日期限切れのタスクの数 
.. |new-cases-chart| image:: ../../screenshots/statistic/new-cases-chart.png
   :alt: 新規ケース数のグラフ
.. |open-tasks-chart| image:: ../../screenshots/statistic/open-tasks-chart.png
   :alt: オープンなタスク数の表
.. |tasks-expire-end-week-chart| image:: ../../screenshots/statistic/tasks-expire-end-week-chart.png
   :alt: 今週期限切れのタスク数の表
.. |top-prior-chart| image:: ../../screenshots/statistic/top-prior-chart.png
   :alt: 期限切れが近いタスクのグラフ
.. |running-cases-chart| image:: ../../screenshots/statistic/running-cases-chart.png
   :alt: 実行中のケースの表
.. |create-new-custom-statistic-widget| image:: ../../screenshots/statistic/create-new-custom-statistic-widget.png
   :alt: Create new custom statistic widget
.. |custom-statistic-widget-configuration-page| image:: ../../screenshots/statistic/custom-statistic-widget-configuration-page.png
   :alt: Custom statistic widet configuration page
