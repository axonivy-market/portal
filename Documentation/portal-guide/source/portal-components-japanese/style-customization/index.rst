.. _components-portal-components-style-customization-ja:

スタイルのカスタマイズ
*******************************************

カスタマイズ方法
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. 新しい css ファイルをリソースに追加し、テンプレートにインポートする必要があります。

   Code Example:

   .. code-block:: html

      <ui:composition template="/layouts/basic-10.xhtml">
         <ui:define name="title">test</ui:define>
         <ui:define name="content">
            <ic:com.axonivy.portal.components.ProcessHistory businessEntityId="alpha" chunkSize="12" scrollHeight="600" />
            <h:outputStylesheet name="layouts/styles/process-history-customize.css" />
         </ui:define>
      </ui:composition>

   .. note::
      コンポーネントの下にある ``<h:outputStylesheet />`` に css ファイルを配置して、規定のスタイルをオーバーライドする必要があります。

#. このファイル内で、コンポーネントのデフォルトの css 変数をオーバーライドできます。例えば、--process-history-description-text-color の場合は以下のようになります。

   .. code-block:: css

      :root {
         --process-history-description-text-color: red;
      }

オーバーライドできる css 変数のリスト
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

プロセスチェーン
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. csv-table::
  :file: ../documents/css_variables/process_chain.csv
  :header-rows: 1
  :class: longtable
  :widths: 2 1 2


プロセス履歴
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. csv-table::
  :file: ../documents/css_variables/process_history.csv
  :header-rows: 1
  :class: longtable
  :widths: 2 1 2


プロセスビューアー
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. csv-table::
  :file: ../documents/css_variables/process_viewer.csv
  :header-rows: 1
  :class: longtable
  :widths: 2 1 2

