.. _customization-portal-logos-and-colors:

PortalStyle customization (logos, colors, date patterns)
========================================================

.. _customization-portal-logos-and-colors-change-portal-logos:

Change Portal's logos
---------------------

You can change logo and login logo by modifying default logo in
PortalStyle project.

-  Modify cms entry ``PortalStyle/images/logo/CorporateLogo.png`` to
   update homepage logo when menu is expanded.

   .. tip:: We recommend you to use an image with width size is 168 pixel to fit the left menu size when it's expanded
   

-  Modify cms entry ``PortalStyle/images/logo/SmallCorporateLogo.png`` to
   update homepage logo when menu is collapsed.

   .. tip:: We recommend you to use an image with width size is 42 pixel to fit the left menu size when it's collapsed

-  Modify cms entry ``PortalStyle/images/logo/loginLogo.png`` to update
   login logo.

-  Modify cms entry ``PortalStyle/images/logo/faviconLogo.png`` to update
   favicon logo. Recommended size for favicon: 16x16 or 32x32 pixels.

-  Override the variables: ``$loginLogoHeight``, ``$homeLogoHeight`` in
   ``variables.scss`` to scale your logos.

   .. _customization-portal-logos-and-colors-change-portal-background:

Change Portal styles
--------------------

Portal applies |sass_framework| framework to support
you in customizing styles of Portal. They are ``variables-customization.scss`` and ``customization.scss``.

   - ``variables-customization.scss`` in ``PortalStyle/webContent/resources/sass/ivy``. Please put customization for Portal style variables to this file.
   - ``customization.scss`` in ``PortalStyle/webContent/resources/sass/ivy``. Please put your specific style customization to this file.

.. caution:: Please do not modify ``theme.scss`` and ``variables.scss`` because they can be changed by new Portal version for upgrade.

..

Below is the list of some Portal elements which are using primary colors of Serenity theme:

.. table::

   +-----------------------+-----------------------------+----------------------------------------------------------------+
   | Variable              | Default value               | Description                                                    |
   +=======================+=============================+================================================================+
   | $primaryColor         | $darkBlue: #007095          | - Background color of button (except Cancel button)            |
   |                       |                             | - Background color of Titlebar panel/selectcheckboxmenu header |
   |                       |                             | - Background color of selected checkbox/radio button           |
   |                       |                             | - Background color of process chain (except waiting state)     |
   |                       |                             | - Background color of hovered row in datatable                 |
   |                       |                             | - Portal header bar                                            |
   |                       |                             | - Label and bottom border of active input field                |
   |                       |                             | - Sidebar anchor                                               |
   |                       |                             | - Text color of active tab                                     |
   +-----------------------+-----------------------------+----------------------------------------------------------------+
   | $primaryDarkColor     | $darkBlue: #007095          | - Background color of toolbar                                  |
   |                       |                             | - Background color of datatable pagination                     |
   |                       |                             |   (except table in Case/Task detail)                           |
   +-----------------------+-----------------------------+----------------------------------------------------------------+
   | $primaryLightColor    | $mediumBlue: #49bad6        | - Background color of hovered button                           |
   +-----------------------+-----------------------------+----------------------------------------------------------------+
   | $primaryLightestColor | white                       | - Active checkbox icon                                         |
   |                       |                             | - Background color of active sidebar menu item                 |
   +-----------------------+-----------------------------+----------------------------------------------------------------+
   | $primaryTextColor     | black                       | - For almost texts in Portal (except some specific styles      |
   |                       |                             |   like Case/Task Description in Case/Task list)                |
   +-----------------------+-----------------------------+----------------------------------------------------------------+
   | $accentColor          | $primaryColor               | - Active submenuitem of sidebar                                |
   |                       |                             | - Background color of active items (form elements)             |
   |                       |                             | - Background color of active page number in datatable          |
   |                       |                             |   (except table in Case/Task detail)                           |
   |                       |                             | - Bottom border of active tab                                  |
   |                       |                             | - Border color of datepicker-today                             |
   +-----------------------+-----------------------------+----------------------------------------------------------------+
   | $accentTextColor      | white                       | - Text of active items                                         |
   |                       |                             | - Text of active page number in datatable                      |
   |                       |                             | - Text of panel header and calenda header                      |
   +-----------------------+-----------------------------+----------------------------------------------------------------+

You can customize in:

- ``font-faces.scss``: to replace default font url-s by your font url-s and add/change other font styles to customize the Portal's font styles.

- ``customization.scss``: to change styles of Portal. E.g. Portal's component styles.

.. tip::
   - For ``font-size``, Portal uses ``rem``. 
     Font size of all elements are calculated based on font-size of ``html`` element.

   - If ``$primaryColor`` is a bright tone color (such as yellow or light blue), you may want to change ``$accentTextColor`` to ``black``. 
     It helps text of buttons and other components easier to recognize.

.. tip::
   If you are using Iframes and you want to customize the colors of the project specific UIs, then refer to this documentation 
   `<https://developer.axonivy.com/doc/8.0/designer-guide/user-interface/user-dialogs.html?highlight=frame%208#color-customizing>`_

.. warning::
   - Do not change ``font-family`` property values.

   - Limitation: the task priority color customization hasn't changed the task priority colors in statistic.

   If you want to custom color of statistic, please refer to :ref:`Override Statistic color <override-Statistic-colors>`

After you finish your customization, compile these above scss files to
build the css file named ``theme.css`` and put it at
``PortalStyle/webContent/resources/serenity-portal``.
You are highly recommended to run the ``mvn libsass:compile`` maven
command in PortalStyle to do it quickly.

.. _customization-portal-logos-and-colors-changedatepatterns:

Change date time pattern
------------------------

You can change date pattern by modifying CMS in PortalStyle project:
``PortalStyle/patterns/datePattern`` and
``PortalStyle/patterns/dateTimePattern`` .

.. |sass_framework| raw:: html

   <a href="https://sass-lang.com/" target="_blank">SASS</a>