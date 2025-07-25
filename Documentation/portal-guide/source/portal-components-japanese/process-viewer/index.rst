.. _components-portal-components-process-viewer-ja:

プロセスビューアー
*************************************

概要
^^^^^^^^^^
プロセスビューアーコンポーネントは、指定されたタスク ID、ケース ID、プロセスの相対リンクからのプロセスフローを視覚的に表現します。

|portal-process-viewer-component|

.. warning::
   プロセスのユーザーへの表示が許可されていない場合は、プロセスの代わりにエラーメッセージが表示されます。

このコンポーネントの属性：

.. csv-table::
  :file: ../documents/process_viewer_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

使用方法
^^^^^^^^^^^^^^^^^^^^^^^^^

以下のコードを含めることにより、任意のページにプロセスビューアーコンポーネントを統合できます。

#. 以下のいずれかのパラメーターを設定する必要があります。

   - ``taskId``： ``taskId`` に等しい ID を持つタスクのプロセスまたはケースマップを表示します。

   .. code-block:: html

      <ic:com.axonivy.portal.components.ProcessViewer taskId="000001" containerStyleClass="process-viewer-container" />

   - ``caseId``： ``caseId`` に等しい ID を持つケースのプロセスまたはケースマップを表示します。

   .. code-block:: html

      <ic:com.axonivy.portal.components.ProcessViewer caseId="000001" containerStyleClass="process-viewer-container" />

   - ``processLink``: show the process or Case Map which by its defined relative link.

   .. code-block:: html

      <ic:com.axonivy.portal.components.ProcessViewer processLink="/designer/pro/portal-components-examples/182E92730FF57035/start.ivp" containerStyleClass="process-viewer-container" />

   .. warning::
      サブケース（テクニカルケース）はサポートされていません。

#. ``containerStyleClass`` パラメーターを使用して、コンテナのスタイルをカスタマイズできます。

詳細については、 ``portal-components-examples`` プロジェクトの ``ProcessViewerExample`` プロセスを参照してください。


スタイルをカスタマイズしたい場合は、:ref:`components-portal-components-style-customization-ja` を参照してください。


.. |portal-process-viewer-component| image:: ../../screenshots/components/portal-process-viewer-component.png
