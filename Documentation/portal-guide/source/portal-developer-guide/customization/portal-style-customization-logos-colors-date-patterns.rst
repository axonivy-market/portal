.. _customization-portal-logos-and-colors:

PortalStyle customization (logos, colors, date patterns)
========================================================

.. _customization-portal-logos-and-colors-change-portal-logos:

Change Portal's logos
---------------------

You can change logo and login logo by modifying default logo in
PortalStyle project. And we recommend using an image with transparent background.

-  Modify cms entry ``PortalStyle/images/logo/CorporateLogoSvg`` to
   update homepage logo when menu is expanded. File type is SVG. This approach is highly recommended.

-  Modify cms entry ``PortalStyle/images/logo/CorporateLogo`` to
   update homepage logo when menu is expanded. Only use this approach if you don't have SVG file. File type is PNG.

   .. tip::
      - We recommend using an image with width size is 168 pixels to fit the left menu size when it's expanded.
        And height size is 42 pixels.

      - To scale your logo, override these variables: ``--home-logo-width``, ``--home-logo-height``.

-  Modify cms entry ``PortalStyle/images/logo/SmallCorporateLogoSvg`` to
   update homepage logo when menu is collapsed. File type is SVG. This approach is highly recommended.

-  Modify cms entry ``PortalStyle/images/logo/SmallCorporateLogo`` to
   update homepage logo when menu is collapsed. Only use this approach if you don't have SVG file. File type is PNG.

   .. tip::
      - We recommend using an image with width size is 42 pixels to fit the left menu size when it's collapsed.
        And height size is 42 pixels.

      - To scale your logo, override these variables: ``--small-home-logo-width``, ``--home-logo-height`` (the same variable for ``PortalStyle/images/logo/CorporateLogo`` or ``PortalStyle/images/logo/CorporateLogoSvg``).

-  Modify cms entry ``PortalStyle/images/logo/loginLogoSvg`` to update
   login logo. File type is SVG. This approach is highly recommended.

-  Modify cms entry ``PortalStyle/images/logo/loginLogo`` to update
   login logo. Only use this approach if you don't have SVG file. File type is PNG.

   .. tip::
      - We recommend using an image with size is 50x50 pixels.

      - To scale your logo, override variable: ``--login-logo-height``, the width will be calculated automatically.

-  Modify cms entry ``PortalStyle/images/logo/faviconLogoSvg`` to update
   favicon logo. File type is SVG. This approach is highly recommended.

-  Modify cms entry ``PortalStyle/images/logo/faviconLogo`` to update
   favicon logo. Recommended size for favicon: 16x16 or 32x32 pixels. Only use this approach if you don't have SVG file. File type is PNG.

-  Please refer to default value of these variables: ``--home-logo-width``, ``--home-logo-height``, ``--small-home-logo-width``, ``--login-logo-height`` in
   ``portal-variables.css`` to scale your logos.

   .. _customization-portal-logos-and-colors-change-portal-background:

Change Portal styles
--------------------

Portal uses modern |css_variable|  to support styles customization.
Portal variables are stored in ``portal-variables.css`` and Portal default styles are stored in ``portal.css``.

Both of them are placed in folder ``PortalStyle/webContent/resources/css``

.. caution:: Please do not modify directly on these files because they can be changed by new Portal version for upgrade. You can use ``customization.css`` to override them easily without migration problem.

..

Below is the list of some Portal elements which are using customizable colors:

.. table::

   +------------------------------+-----------------------------+----------------------------------------------------------------+
   | Variable                     | Default value               | Description                                                    |
   +==============================+=============================+================================================================+
   | ``--primary-color``          | hsl(195,100%,29%)           | - Background color of button (except Cancel button)            |
   |                              |                             | - Background color of selected checkbox/radio button           |
   |                              |                             | - Background color of process chain (except waiting state)     |
   |                              |                             | - Portal header bar                                            |
   |                              |                             | - Label and bottom border of active input field                |
   |                              |                             | - Sidebar anchor                                               |
   |                              |                             | - Text color of active tab                                     |
   +------------------------------+-----------------------------+----------------------------------------------------------------+
   | ``--primary-dark-color``     | hsl(195, 100%, 20%)         | - Background color of hovered button                           |
   +------------------------------+-----------------------------+----------------------------------------------------------------+
   | ``--primary-text-color``     | white                       | - Text, icon color of button, checkbox, and other elements     |
   +------------------------------+-----------------------------+----------------------------------------------------------------+
   | ``--accent-color``           | hsl(0, 1%, 34%)             | - Background color of active items (form elements)             |
   |                              |                             | - Background color of active page number in datatable          |
   |                              |                             |   (except table in Case/Task detail)                           |
   |                              |                             | - Bottom border of active tab                                  |
   |                              |                             | - Border color of datepicker-today                             |
   +------------------------------+-----------------------------+----------------------------------------------------------------+
   | ``--accent-text-color``      | white                       | - Text of active items                                         |
   |                              |                             | - Text of active page number in datatable                      |
   +------------------------------+-----------------------------+----------------------------------------------------------------+

You can customize in:

- ``customization.css``: to change styles of Portal. E.g. Portal's component styles.

  For example, to customize default ``font-family``:

  #. After ``START OVERRIDING PORTAL STYLES`` section, add new ``@font-face`` block with your ``font-family`` name, url-s.

  #. Inside ``:root`` tag, override ``--portal-font-family`` variable by using your ``font-family`` name.

.. tip::
   - For ``font-size``, Portal uses ``rem``. 
     Font size of all elements are calculated based on font-size of ``html`` element.

   - If ``--primary-color`` is a bright tone color (such as yellow or light blue), you may want to change ``--primary-text-color`` to ``black``. 
     It helps text of buttons and other components easier to recognize.

.. tip::
   If you are using Iframes and you want to customize the colors of the project specific UIs, then refer to this documentation 
   :dev-url:`/doc/9.3.5/designer-guide/how-to/overrides.html?#override-new-wizard`

.. warning::
   - Do not change ``font-family`` property values.

   - Limitation: the task priority color customization hasn't changed the task priority colors in statistic.

   If you want to custom color of statistic, please refer to :ref:`Override Statistic color <override-Statistic-colors>`

.. _customization-portal-logos-and-colors-changedatepatterns:

Change date time pattern
------------------------

You can change date pattern by modifying CMS in PortalStyle project:
``PortalStyle/patterns/datePattern`` and
``PortalStyle/patterns/dateTimePattern``.

.. |css_variable| raw:: html

   <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties" target="_blank">CSS Variable</a>