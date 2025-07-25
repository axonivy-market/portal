.. _customization-process-information-ja:

プロセス情報
===================================

.. _customization-process-information-page-introduction-ja:

概要
------------

プロセス情報ページを表示するには、このセクションに従って、ポータルに組み込まれているプロセス情報ページを定義します。


.. _customization-process-information-page-customization-ja:

独自のプロセス情報ページの定義
-------------------------------------------------------------

#. プロセスの開始の :guilabel:`カスタムフィールド` で ``portalProcessInfo`` カスタムフィールドを定義します。

#. ``portalProcessInfo`` の値の定義

   |define-portal-process-info|

   .. tip::
      ``portalProcessInfo`` の値をプロセスで直接定義することは可能です。しかし、（HTML でサポートされている）CMS オブジェクトで定義することを推奨します。
      

.. |define-portal-process-info| image:: images/process-information/define-portal-process-info-image.png
