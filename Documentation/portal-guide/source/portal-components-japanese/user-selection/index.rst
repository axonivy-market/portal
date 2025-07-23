.. _components-portal-components-user-selection-ja:

ユーザーの選択
*******************************

概要
^^^^^^^^^^^^^^^^

このコンポーネントは、ロール名のリストで定義されたユーザーのリストから 1 人のユーザーを選択するのに使用します。
ロール名のリストを定義していない場合は、すべてのユーザーがロードされます。
コンポーネントには、ラベル、オートコンプリート、そのオートコンプリート要素に関連するメッセージを表示する 1 つのメッセージ要素が含まれます。

使用方法
^^^^^^^^^^^^^^^^^^^^^^^

このコンポーネントは任意のページに挿入できます。以下の 2 つのスタイルでラベルを表示します。

#. デフォルトのスタイル

   |user-selection|

   Code example:

   .. code-block:: html

      <ic:com.axonivy.portal.components.UserSelection componentId="default-user-autocomplete"
         selectedUser="#{data.selectedUser}" label="Default user selection"
         isRequired="true" labelPanelStyleClass="ui-g-6 ui-md-6 ui-sm-12"
         autoCompleteStyleClass="width-100" autoCompletePanelStyleClass="ui-g-6 ui-sm-12" />

#. フローティングラベル

   |user-selection-floating-label|

   Code example:

   .. code-block:: html

      <ic:com.axonivy.portal.components.UserSelection componentId="all-user-autocomplete"
         hightlight="false" selectedUser="#{data.selectedUserForExcludingUsers}"
         label="Loading users (exclude gm1, gm2, admin)" autoCompleteStyleClass="width-100"
         autoCompletePanelStyleClass="ui-g-12 floating-label-margin-top"
         excludedUsernames="#{data.excludedUsernames}" floatingLabel="true" />

.. tip::
   ユーザーの選択コンポーネントのオートコンプリート要素を使用して、子イベントや Ajax イベントを挿入できます（ ``UserSelection.xhtml`` を参照）。
   
   UserSelection コンポーネントの任意の子は、 ``insertChildren`` タグの位置にあるこのオートコンプリートにリペアレントされます。
   
   また、Ajax イベントを入れ子にできるように、 ``event`` という名前のオートコンプリートのファセットを導入します。
   

例：

「<氏名> (<ユーザー名>)」という形式のドロップダウンリストでユーザーを表示します。
ユーザーを選択すると、メッセージが表示されます。

|user-selection-with-children-and-ajax-event|

|user-selection-component-ajax-expand|

.. code-block:: html

   <ic:com.axonivy.portal.components.UserSelection id="item-select-event-component"
      componentId="item-select-event-for-user-selection" floatingLabel="true"
      fromRoleNames="#{data.definedRoleNames}" label="Demonstrate facet and children"
         selectedUser="#{data.selectedUserForInsertChildren}"
      autoCompleteStyleClass="width-100"
      autoCompletePanelStyleClass="ui-g-12 floating-label-margin-top" hasCustomizedSelectionList="true">
         <p:column>
            <pc:securityMemberNameAndAvatar displayName="#{user.getDisplayName()} (#{user.getMemberName()})" securityMember="#{user}" isStandAlone="false" />
         </p:column>
         <f:facet name="event">
            <p:ajax event="itemSelect" listener="#{logic.showSelectedUser}"
               update="#{p:component('item-select-event-for-user-selection-message')}"/>
         </f:facet>
   </ic:com.axonivy.portal.components.UserSelection>

詳細については、 ``portal-components-examples`` プロジェクトの ``UserSelectionExample.xhtml`` を参照してください。

このコンポーネントには以下の属性が用意されています。

.. csv-table::
  :file: ../documents/user_selection_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

.. _components-portal-components-migrate-from-old-user-selection-ja:

非推奨のユーザーの選択からの移行
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. HTML ファイルのコードを置き換えます。 ``ic:ch.ivy.addon.portalkit.component.UserSelection`` を ``ic:com.axonivy.portal.components.UserSelection`` に置き換えてください。

#. ``ch.ivy.addon.portalkit.dto.UserDTO`` を ``com.axonivy.portal.components.dto.UserDTO`` に置き換えます。

   .. note::
      データベースに ``ch.ivy.addon.portalkit.dto.UserDTO`` クラスを格納した場合は、データベースを手動で更新する必要があります。

.. |user-selection| image:: ../../screenshots/components/user-selection-component.png
.. |user-selection-floating-label| image:: ../../screenshots/components/user-selection-component-floating-label.png
.. |user-selection-with-children-and-ajax-event| image:: ../../screenshots/components/user-selection-component-ajax-event-selected-message.png
.. |user-selection-component-ajax-expand| image:: ../../screenshots/components/user-selection-component-ajax-expand.png
