.. _customization-portal-logos-and-colors:

PortalStyle customization (logos, colors, date patterns)
========================================================

.. _customization-portal-logos-and-colors-change-portal-logos:

Change Portal's logos
---------------------

You can change logo and login logo by modifying default logo in
PortalStyle project.

-  Modify cms entry ``PortalStyle/images/logo/CorporateLogo.png`` to
   update homepage logo.

-  Modify cms entry ``PortalStyle/images/logo/loginLogo.png`` to update
   login logo.

-  Override the variables: $loginLogoHeight, $homeLogoHeight in
   ``variables.scss`` into of ``ivy`` folder to scale your logos.

.. _customization-portal-logos-and-colors-change-portal-background:

Change Portal styles
--------------------

Portal applies `SASS <https://sass-lang.com/>`__ framework to support
you in customizing styles of Portal. There are 4 files: ``theme.scss``
(shouldn't be modified), ``variables.scss``, ``font-faces.less``,
``customization.less``, they are placed at
``PortalStyle/webContent/resources/sass/ivy`` and
``PortalStyle/webContent/resources/primefaces-serenity-ivy`` .

-  ``font-faces.scss`` : replace default font url-s by your font url-s
   and add/change other font styles to customize the Portal's font
   styles.

-  ``customization.scss`` : to change styles of Portal. E.g. Portal's
   component styles.

      **Note**

      Portal provides several variables (
      $bodyBackgroundColor
      ,
      $menuColor
      ,
      $processChainMenuColor
      , etc.) to change Portal's style. To override variables, you
      should put overriding code (e.g.
      $bodyBackgroundColor: red
      ) at
      customization.scss
      file.

      -  ``@body-background-color``: Portal background color.

      -  ``@menu-color``: application menu color, color of texts and
         icons on the menu will be calculated based on brightness of
         menu color.

      -  ``$announcementBorderColor`` : it is the border color of the
         announcement panel. ``@sidebar-opacity-transition-duration``:
         application menu text opacity transition duration.

      -  ``@announcement-background-color``: announcement panel
         background color.

      -  ``@announcement-border-color``: announcement panel border
         color.

      -  ``@process-chain-menu-color``: process chain component color.

      -  ``@action-button-color``: Portal action button color e.g:
         close, add, next ...

      -  ``@action-button-border-radius``: action button shape, it's
         rectangle when set @action-button-border-radius:0px;

      -  ``@sidebar-dimension-transition-duration``:
         expanding/collapsing transition duration of application menu.

      -  ``@cancel-button-background-color``: cancel button background
         color.

      -  ``@first-header-bar-color``, ``@second-header-bar-color``,
         ``@third-header-bar-color``: 3 colors of header bar .

      -  ``@task-priority-low-color``, ``@task-priority-normal-color``,
         ``@task-priority-high-color``,
         ``@task-priority-exception-color``: task priority color.

      -  ``@task-state-open-color``, ``@task-state-in-progress-color``,
         ``@task-state-done-color``,
         ``@task-state-zombie-destroyed-color``,
         ``@task-state-reserved-color``, ``@task-state-system-color``:
         task state color.

..

   **Important**

   -  Do not change ``font-family`` property values.

   -  Limitation: the task priority color customization hasn't changed
      the task priority colors in statistic.

There is additional button type allows developer to configure its color
and shape. Use it when you need different button types in your own
project. How to: use style class ``context-button`` for it. For example
: ``<p:commandButton value="My button" styleClass="context-button" >``
There are 2 variables allow developers to change color and shape

-  ``$contextButtonColor`` : it is the color of context button

-  ``$contextButtonBorderRadius`` : it sets shape of action button, it's
   rectangle when set $contextButtonBorderRadius:0px;

After you finish your customization, compile these above scss files to
build the css file named ``theme.css``. By default, this css file will
be created into this folder. If this file didn't put into this folder.
Please help put it at
``PortalStyle/webContent/resources/primefaces-serenity-ivy`` manually. .
You are highly recommended to run the ``mvn libsass:compile`` maven
command in PortalStyle to do it quickly.

.. _customization-portal-logos-and-colors-changedatepatterns:

Change date time pattern
------------------------

You can change date pattern by modifying CMS in PortalStyle project:
``PortalStyle/patterns/datePattern`` and
``PortalStyle/patterns/dateTimePattern`` .
