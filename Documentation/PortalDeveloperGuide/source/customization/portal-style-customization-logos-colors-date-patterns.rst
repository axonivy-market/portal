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

-  Override the variables: ``$loginLogoHeight``, ``$homeLogoHeight`` in
   ``variables.scss`` to scale your logos.

.. _customization-portal-logos-and-colors-change-portal-background:

Change Portal styles
--------------------

Portal applies |sass_framework| framework to support
you in customizing styles of Portal. They are ``theme.scss`` and ``variables.scss``.

- ``theme.scss`` in ``PortalStyle/webContent/resources/serenity-portal``

- ``variables.scss`` in ``PortalStyle/webContent/resources/sass/ivy``

.. caution:: Please do not modify directly on these files because they can be changed by new Portal version for upgrade.

You can customize in:

- ``font-faces.scss``: to replace default font url-s by your font url-s and add/change other font styles to customize the Portal's font styles.

- ``customization.scss``: to change styles of Portal. E.g. Portal's component styles.

.. tip::
   For ``font-size``, Portal uses ``rem``. 
   Font size of all elements are calculated based on font-size of ``html`` element. 

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