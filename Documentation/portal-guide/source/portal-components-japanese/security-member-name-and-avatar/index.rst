.. _components-portal-components-security-member-name-and-avatar-ja:

セキュリティメンバーの名前とアバター
************************************************************

概要
^^^^^^^^^^^^^^^^^^^

このコンポーネントは、ivy のセキュリティシステムでセキュリティメンバーの名前とアバターを表示するのに使用します。
コンポーネントは、:ref:`components-portal-components-user-selection-ja`、:ref:`components-portal-components-role-selection-ja` にも統合されています。

使用方法
^^^^^^^^^^^^^^^^^^^^

このコンポーネントは任意のページに挿入できます。

|session-role-security-member-name-and-avatar|

最上位のロール（Everybody）を使用したコードの例：

.. code-block:: html

      <pc:securityMemberNameAndAvatar displayName="#{ivy.security.roles().topLevel().displayName}"
         securityMember="#{ivy.security.roles().topLevel()}" />

|session-user-security-member-name-and-avatar|

現在のセッションのユーザーを使用したコードの例：

.. code-block:: html

      <pc:securityMemberNameAndAvatar displayName="#{ivy.session.sessionUser.displayName}"
         securityMember="#{ivy.session.sessionUser}" />

このコンポーネントには以下の属性が用意されています。

.. csv-table::
  :file: ../documents/security_member_name_and_avatar_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 1 3

詳細については、 ``portal-components-examples`` プロジェクトの ``SecurityMemberNameAndAvatarExample`` プロセスを参照してください。

.. |session-user-security-member-name-and-avatar| image:: ../../screenshots/components/session-user-security-member-name-and-avatar.png
.. |session-role-security-member-name-and-avatar| image:: ../../screenshots/components/session-role-security-member-name-and-avatar.png
