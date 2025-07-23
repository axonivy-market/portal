.. _components-additional-component-ja:

追加コンポーネント
====================================

.. _components-additional-component-global-growl-ja:

グローバル通知
--------------------------------------

.. _components-additional-component-global-growl-introduction-ja:

概要
^^^^^^^^^^^^^^^^^^^^^^^^^

このコンポーネントは、基本テンプレートに導入されたグローバル通知です。以下のコードを使用して、ポータルでメッセージを表示できます。


.. code-block:: html

    <p:growl id="portal-global-growl" widgetVar="portal-global-growl" for="portal-global-growl-message" escape="false" showDetail="true" />


タスクを終了した後の通知の表示
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

``Portal.DisplayMessageAfterFinishTask`` が true の場合、タスクの完了後に通知メッセージが表示されます。

|example-global-growl-finished-task|

.. _global-growl-display-growl-after-finish-task-ja:

タスクを離れた後の通知の表示
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

``Portal.DisplayMessageAfterFinishTask`` が true の場合、ユーザーがタスクをキャンセルすると通知メッセージが表示されます。

|example-global-growl-cancelled-task|

.. |example-global-growl-finished-task| image:: ../../screenshots/components/example-global-growl-finished-task.png
.. |example-global-growl-cancelled-task| image:: ../../screenshots/components/example-global-growl-cancelled-task.png

