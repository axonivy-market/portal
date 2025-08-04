.. _components-portal-components-process-chain-ja:

プロセスチェーン
*****************************

.. _components-portal-components-process-chain-introduction-ja:

概要
^^^^^^^^^^^

プロセスチェーンコンポーネントは、現在実行可能なステップ、完了したステップ、未完了のステップを含むすべてのステップのステータス情報を 1 つのプロセスで提供します。
特徴は以下のとおりです。

#. 現在実行可能なすべてのステップを表示します。または、最初のステップ、最後のステップ、現在のステップ、前のステップ、次のステップなど、有用なステップのみ表示します。   

#. プロセスチェーンの形状（円または線）を変更します。

#. プロセスチェーンの向き（横または縦）を変更します。

|process-chain|

.. _components-portal-components-process-chain-how-to-use-ja:

使用方法
^^^^^^^^^^^^^^^^

以下のコードを使用して、プロセスチェーンコンポーネントをページに含めることにより、任意のウィジェットにコンポーネントを統合できます。


.. code-block:: html

		<ic:com.axonivy.portal.components.ProcessChain id="process-chain-circle-horizontal" componentId="component-circle-horizontal" shape="CIRCLE" direction="HORIZONTAL"
         isShowAllSteps="false" actualStepIndex="0" steps="#{['Step 1','Step 2','Step 3','Step 4','Step 5','Step 6','Step 7','Step 8','Step 9']}" />

#. 以下のパラメーターを設定する必要があります。

   -  ``actualStepIndex``：現在のステップのインデックスです。
   -  ``steps``：作業中のステップのリストです。

#. 以下のパラメーターを変更できます。

   -  要件に`応じて ``shape`` を ``CIRCLE`` または ``LINE`` に変更します。初期値は ``CIRCLE`` です。
   -  ``direction`` を ``HORIZONTAL`` または ``VERTICAL`` に変更します。初期値は ``HORIZONTAL`` です。
   -  ``isShowAllSteps`` を ``TRUE`` または ``FALSE`` に変更します初期値は ``FALSE`` です。

.. csv-table::
  :file: ../documents/process_chain_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3


スタイルをカスタマイズしたい場合は、:ref:`components-portal-components-style-customization-ja` を参照してください。


.. _components-portal-components-migrate-from-old-process-chain-ja:

非推奨のプロセスチェーンからの移行
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- HTML ファイルのコードを置き換えます。 ``ch.ivy.addon.portalkit.singleapp.process.ProcessChain`` を ``com.axonivy.portal.components.ProcessChain`` に置き換えてください。

.. |process-chain| image:: ../../screenshots/components/process-chain.png
