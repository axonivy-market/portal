.. _components-portal-components-role-selection-ja:

ロールの選択
****************************

概要
^^^^^^^^^^^^

このコンポーネントは、ロールのリストからロールを選択するのに使用します。ロールのリストを定義していない場合は、すべてのロールがロードされます。
コンポーネントには、1 つのラベル、1 つのオートコンプリート、このオートコンプリート要素に関連するメッセージを表示する 1 つのメッセージ要素が含まれます。



使用方法
^^^^^^^^^^^^^^^

このコンポーネントは任意のページに挿入できます。以下の 2 つのスタイルでラベルを表示します。


#. デフォルトのスタイル

   |role-selection|

   コードの例：

   .. code-block:: html

      <ic:com.axonivy.portal.components.RoleSelection
         componentId="role-from-defined-role-autocomplete"
         fromRoleNames="#{data.definedRoleNames}"
         selectedRole="#{data.selectedRoleForDefinedRoles}"
         isRequired="true"
         label="Roles from defined role names"/>

#. フローティングラベル

   |role-selection-floating-label|

   コードの例：

   .. code-block:: html

      <ic:com.axonivy.portal.components.RoleSelection
         componentId="floating-label-and-exclude-role-autocomplete" hightlight="false"
         selectedRole="#{data.selectedRole}"
         label="Loading with all roles (exclude CaseOwner, GeneralManager)"
         excludedRolenames="#{data.excludedRoleNames}"
         isRequired="true" floatingLabel="true" />

.. tip::
   ロールの選択コンポーネントのオートコンプリート要素を使用して、子イベントや Ajax イベントを挿入できます（ ``RoleSelection.xhtml`` を参照）。
   
   RoleSelection コンポーネントの任意の子は、 ``insertChildren`` タグの位置にあるこのオートコンプリートにリペアレントされます。
   また、Ajax イベントを入れ子にできるように、 ``event`` という名前のオートコンプリートのファセットを導入しました。
   
   

例：

「<表示名> (<メンバー名>)」という形式のドロップダウンリストでロールを表示します。
ロールを選択すると、メッセージが表示されます。

|role-selection-with-children-and-ajax-event|

|role-selection-component-ajax-expand|

.. code-block:: html

      <ic:com.axonivy.portal.components.RoleSelection
         id="item-select-event-component"
         componentId="item-select-event-for-role-selection"
         fromRoleNames="#{data.definedRoleNames}"
         selectedRole="#{data.selectedRoleForInsertChildren}"
         label="Demonstrate facet and children"
         autoCompleteStyleClass="width-100"
         autoCompletePanelStyleClass="ui-g-12 floating-label-margin-top"
         isRequired="true" floatingLabel="true" hasCustomizedSelectionList="true">
         <p:column>
            <pc:securityMemberNameAndAvatar displayName="#{role.getDisplayName()} (#{role.getMemberName()})" securityMember="#{role}" isStandAlone="false" />
         </p:column>
         <f:facet name="event">
            <p:ajax event="itemSelect" listener="#{logic.showSelectedRole}"
               update="#{p:component('item-select-event-for-role-selection-message')}" />
         </f:facet>
      </ic:com.axonivy.portal.components.RoleSelection>

詳細については、 ``portal-components-examples`` プロジェクトの ``RoleSelectionExample.xhtml`` を参照してください。

このコンポーネントには以下の属性が用意されています。

.. csv-table::
  :file: ../documents/role_selection_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

.. _components-portal-components-migrate-from-old-role-selection-ja:

非推奨のロールの選択からの移行
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
#. HTML ファイルのコードを置き換えます。 ``ic:ch.ivy.addon.portalkit.component.RoleSelection`` を ``ic:com.axonivy.portal.components.RoleSelection`` に置き換えてください。

#. ``ch.ivy.addon.portalkit.dto.RoleDTO`` を ``com.axonivy.portal.components.dto.RoleDTO`` に置き換えます。

   .. note::
       データベースに ``ch.ivy.addon.portalkit.dto.RoleDTO`` クラスを格納した場合は、データベースを手動で更新する必要があります。

.. |role-selection| image:: ../../screenshots/components/role-selection-component-from-defined-role.png
.. |role-selection-floating-label| image:: ../../screenshots/components/role-selection-component-floating-label-and-exclude-role.png
.. |role-selection-with-children-and-ajax-event| image:: ../../screenshots/components/role-selection-component-ajax-event-selected-message.png
.. |role-selection-component-ajax-expand| image:: ../../screenshots/components/role-selection-component-ajax-expand.png
