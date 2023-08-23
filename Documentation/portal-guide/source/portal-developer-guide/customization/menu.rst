.. _customization-menu:

Menu And Default Homepage In User Profile
=========================================

.. _customization-menu-introduction:

Introduction
------------

By default, the Portal main menu has five items: Dashboard, Processes, Tasks, Cases and Statistics.

|default-menu-items|

Portal allows you to add custom menu items or hide some default items by using Portal permissions.

Please be informed that all menu items beside external links can be select as default homepage in the user profile.

.. _customization-menu-customization:

Custom menu items
-----------------

Menu type
+++++++++

There are two type of custom menu items:

    - Ivy process: This kind of menu will call an Ivy process when be clicked and can set as the default homepage in user profile.

    - External link: This kind of menu will open its link in a new tab and cannot set as the default homepage in user profile.

By default, the type of custom menu item is Ivy process. To change its type to external link, set the ``isExternalLink`` attribute to ``true``.

Menu index
++++++++++

The custom menu items are under the default menu items.

To define the order of custom menu items, set a number to the ``index`` attribute, it will displayed by the given index.
If you don't set index for custom menu items, they will be sort by alphabetical order.

Add custom menu items
---------------------

Portal provides two approaches to add custom menu items. Please read below sections for more details.

Callable subprocess
+++++++++++++++++++

To add custom menu items, create a callable subprocess with:

**Signature**: portalLoadSubMenuItems

+-----------------------+-------------------------------------------------------------------------------+
| Name                  | Type                                                                          |
+=======================+===============================================================================+
|**Result**                                                                                             |
+-----------------------+-------------------------------------------------------------------------------+
| subMenuItems          | java.util.List<com.axonivy.portal.components.configuration.CustomSubMenuItem> |
+-----------------------+-------------------------------------------------------------------------------+

Example of a menu that redirect to an Ivy process.

   .. code-block:: javascript

        import com.axonivy.portal.components.configuration.CustomSubMenuItem;
        import com.axonivy.portal.components.publicapi.ProcessStartAPI;
        import org.apache.commons.lang3.StringUtils;

        String userExampleGuideLink =
           ProcessStartAPI.
           findStartableLinkByUserFriendlyRequestPath("Start Processes/UserExampleGuide/userExampleGuide.ivp");
        
        if (!StringUtils.isEmpty(userExampleGuideLink)) {  
           CustomSubMenuItem userExampleGuide = new CustomSubMenuItem();

           userExampleGuide.setIcon("si si-bulb");
           userExampleGuide.setLabel("User example guide");
           userExampleGuide.setLink(userExampleGuideLink + "?embedInFrame");
           userExampleGuide.setIndex(0);

           in.subMenuItems.add(userExampleGuide);
        }

Example of an external link:

   .. code-block:: javascript

        import com.axonivy.portal.components.configuration.CustomSubMenuItem;

        CustomSubMenuItem external = new CustomSubMenuItem();
        external.setIcon("si si-information-circle");
        external.setLabel("External page");
        external.setLink("https://your_external_page.com");
        external.setIsExternalLink(true);
        external.setIndex(1);

        in.subMenuItems.add(external);

.. tip::
   Refer to process ``CustomLoadSubMenuItems`` in project ``portal-developer-examples``
   for an example of how to create custom menu items.

.. tip::
    | If you want it to show label of the custom menu item in different 
    | languages, create a CMS entry and use the ``ApplicationMultiLanguageAPI.getCmsValueByUserLocale`` method.
    | E.g. ``subMenuItem.setLabel(ApplicationMultiLanguageAPI.getCmsValueByUserLocale<CMS_URI>));``

Portal variable
+++++++++++++++

Beside the callable subprocess approach, you can define custom menu items in the Portal variable
``Portal.Menu.CustomMenuItems``.

Example of custom menu items in the ``Portal.Menu.CustomMenuItems`` variable.

   .. code-block:: json

        [
            {
                "index": 0,
                "link": "https://your_external_page.com",
                "label": "External link",
                "isExternal": "true",
                "icon": "si si-bulb",
                "version": "10.0.0"
            }, {
                "index": 1,
                "link": "/designer/pro/portal-user-examples/17236DB1D3DA14C0/userExampleGuide.ivp",
                "label": "Guideline Ivy process",
                "isExternal": "false",
                "version": "10.0.0"
            }
        ]

The basic JSON structure of a custom menu items

    ``index``: Index of the menu item on the main menu.

    ``link``: Link of the menu item. In case of Ivy process, You can set a friendly user request path or a process ID.

    ``label``: Label of the menu item.

    ``isExternal``: set to ``true`` to mark this custom menu item as an external link.

    ``version``: version of the menu.

.. _customization-menu-hide-default-menu-item:

Hide default menu items
-----------------------

You can hide four default menu items: Processes, Tasks, Cases and Statistics from the main menu
by using :ref:`Portal permissions <settings-permission-settings-others>`.

.. |default-menu-items| image:: ../../screenshots/dashboard/expanded-left-menu.png