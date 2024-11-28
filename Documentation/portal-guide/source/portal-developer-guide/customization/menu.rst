.. _customization-menu:

Menu And Default Homepage In User Profile
=========================================

.. _customization-menu-introduction:

Introduction
------------

The Portal main menu has four default items: Dashboard, Processes, Tasks and Cases.

|default-menu-items|

Portal allows you to add custom menu items or hide some default items by using the Portal permissions.

All menu items except external links can be set as the user profile's default homepage.

.. _customization-menu-customization:

Custom menu items
-----------------

Menu type
+++++++++

There are two types of custom menu items:

    - Ivy process: triggers an Ivy process when clicked. Can be set as the default homepage in a user's profile.

    - External link: opens an external link in a new tab. Cannot be set as the default homepage in a user's profile.

The Ivy process is the default custom menu item type. To change custom menu item type to external link, set the ``isExternalLink`` attribute to ``true``.

Menu index
++++++++++

You can find the custom menu items below the default menu items.

To set the order of custom menu items, assign a number to the ``index`` attribute. The item will be displayed based on the given index.
If custom menu items are not indexed, they will be sorted alphabetically.

Add custom menu items
---------------------

There are two ways to add custom menu items in Portal. Please refer to the following sections for more information.

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

Example of a menu that redirects to an Ivy process.

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
    | If you want the label of a custom menu item to display in multiple 
    | languages, create a CMS entry and use the ``ApplicationMultiLanguageAPI.getCmsValueByUserLocale`` method.
    | E.g. ``subMenuItem.setLabel(ApplicationMultiLanguageAPI.getCmsValueByUserLocale<CMS_URI>));``

Portal variable
+++++++++++++++

Besides the callable subprocess approach, you can also define custom menu items in the Portal variable
``Portal.CustomMenuItems``.

Here's an example of custom menu items in the Portal.CustomMenuItems variable.

   .. code-block:: javascript

        [
            {
                "index": 0,
                "link": "https://your_external_page.com",
                "label": "External link",
                "isExternal": "true",
                "icon": "si si-bulb",
                "version": "11.2.0"
            }, {
                "index": 1,
                "link": "/designer/pro/portal-user-examples/17236DB1D3DA14C0/userExampleGuide.ivp",
                "label": "Guideline Ivy process",
                "isExternal": "false",
                "version": "11.2.0"
            }
        ]

The basic JSON structure of a custom menu item

    ``index``: index of the menu item on the main menu.

    ``link``: link of the menu item. You can set a user-friendly request path or a process ID for an Ivy process.

    ``label``: label of the menu item.

    ``isExternal``: set to ``true`` to mark this custom menu item as an external link.

    ``version``: version of the menu.

.. _customization-menu-hide-default-menu-item:

Hide default menu items
-----------------------

You can hide three default menu items: Processes, Tasks and Cases from the main menu
by using :ref:`Portal permissions <settings-permission-settings-others>`.

.. |default-menu-items| image:: ../../screenshots/dashboard/expanded-left-menu.png