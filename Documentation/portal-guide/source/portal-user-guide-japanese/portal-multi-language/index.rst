.. _portal-multi-language-ja:

ポータルの複数言語
*************************************

ドキュメントの翻訳サービスを利用すると、簡単にピュアテキストをエンドユーザーの言語に翻訳できます。
Axon Ivy の DeepL を利用した翻訳サービスでプロセスを自動化し、言語の障壁を取り払いましょう。

.. _enable-translation-ja:

ハウツー：翻訳を有効にする
-----------------------------------------------------

DeepL 翻訳を使用するには、

- :ref:`管理者設定 <admin-settings-ja>` を開き、:guilabel:`Portal.DeepL.Enable` を true に設定します。

- DeepL.com で `無料の開発者アカウント <https://www.deepl.com/pro#developer/>`__ を取得し、アカウントの API キーを :guilabel:`Portal.DeepL.AuthKey` ポータル設定にコピーします。

ハウツー：ピュアテキストを翻訳する
-----------------------------------------------------------

.. note::

   ポータルの翻訳機能は、Portal.DeepL.Enable が true に設定され、Portal.DeepL.AuthKey に有効なキーが含まれる場合のみ機能します。

   そうでない場合は、入力テキストフィールドを手動で翻訳できます。

#. 入力テキストフィールドが翻訳に対応している場合、:guilabel:`翻訳者のアイコン` が表示されます。
   例として、個人用ダッシュボードを作成します。:guilabel:`翻訳者のアイコン` をクリックします。

   |create-private-dashboard-dialog|

#. 複数言語の設定ダイアログが表示されます。

   |dashboard-multi-language-dialog|

#. 外国語の入力テキストフィールドをクリックし、DeepL に翻訳させます。翻訳されたテキストを受け入れるには、:guilabel:`自動翻訳` をクリックします。

.. include:: ../includes/_common-icon.rst

.. |create-private-dashboard-dialog| image:: ../../screenshots/dashboard-configuration/create-private-dashboard-dialog-ml.png
   :alt: 個人用ダッシュボードを作成するときの複数言語の使用
.. |dashboard-multi-language-dialog| image:: ../../screenshots/dashboard-configuration/dashboard-multi-language-dialog-ml.png
   :alt: 複数言語ダイアログ
