.. _customization-menu:

Menu and default homepage in user profile
=========================================

.. _customization-menu-introduction:

Introduction
------------

By default Portal main menu has 4 items: Processes, Tasks, Cases and
Statistics. You can remove these items or add your own items, it will effect to the homepage selection in user profile,
the menu items, which are not external link, are shown in the homepage selection.

|default-menu-items|

.. _customization-menu-customization:

Customization
-------------

Create a callable sub process in your project with the
``loadSubMenuItems()`` signature, make sure this signature is unique in
your application and follow the hints.

|load-sub-menu-items-process|

User can hide Statistic widget in :ref:`Admin settings <settings-admin-settings>`.
Therefore, if you want to hide Statistic widget in your overrided
process, please take a look on NOTE section of ``LoadSubMenuItems`` in
PortalTemplate.

.. tip::
    | For the Label of SubMenuItem, if you want to show it in multilingual correctly, you should create a cms and use ``ApplicationMultiLanguageAPI.getCmsValueByUserLocale`` method.
    | e.g: ``subMenuItem.setLabel(ApplicationMultiLanguageAPI.getCmsValueByUserLocale<CMS_URI>));``

.. |default-menu-items| image:: ../../screenshots/dashboard/expanded-left-menu.png
.. |load-sub-menu-items-process| image:: images/menu/load-sub-menu-items-process.png