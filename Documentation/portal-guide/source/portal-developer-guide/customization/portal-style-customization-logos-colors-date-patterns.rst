.. _customization-portal-logos-and-colors:

Portal Styles Customization (Logos, Colors, Date Patterns)
==========================================================

.. _customization-portal-logos-and-colors-change-portal-logos:

Change Logos in Portal
----------------------

You can change both the top-left corner logo in the menu and the login logo by
using :dev-url:`Engine Branding </doc/nightly/designer-guide/user-interface/branding/branding-engine.html>`.

We allow for two different logo graphics for the expanded and the collapsed
menu. You can use one "pure" logo for the small, quadratic format (default: 42x42 pixels), and a logo
with a slogan for the bigger one. This one is intended to have a 4:1
width:height ratio (default: 168x42 pixels).

The best graphics format for logos is SVG (Scalable Vector Graphics) as it
allows to scale while retaining proportions - logos are always rendered as good
as possible in the current resolution. The resolution is defined by the browser
window size and zoom as well as the screen resolution. In addition, SVG files
are usually pretty small because they are text files. All modern browsers can
render SVG graphics.

On the other hand, PNG logos blur if expanded, and are big files if supplied in
high resolution to prevent the blurring. 

We recommend using images with a transparent background, if your Corporate
Identity does not define a mandatory background color. 

To change the logos used in the Portal, please use resources that are described at 
:dev-url:`User Interface Branding <doc/nightly/designer-guide/user-interface/branding/branding-user-interface.html#resources>` and follow the guidelines below.

Expanded Menu Logo
^^^^^^^^^^^^^^^^^^

-  Use resource ``logo`` to update the homepage logo when the left-hand menu is expanded.

   .. tip::
      - For PNG, we recommend using an image with a size of 168 pixels by 42 
        pixels (width by height) to fit the left menu size when it is expanded.
        For SVG, it is best if the logo has a 4:1 ratio.
        
      - To scale your logo, override these variables: ``--home-logo-width``, ``--home-logo-height``.

Login & Collapsed Menu Logo
^^^^^^^^^^^^^^^^^^^^^^^^^^^

-  Use resource ``logo_small`` to update the login logo & the homepage logo when the left-hand menu is collapsed.

   .. tip::
      - We recommend using an image with size is 50x50 pixels.

      - To scale the login logo, override variable: ``--login-logo-height``, the width will be calculated automatically.

      - To scale the homepage logo, override these variables: ``--small-home-logo-width``, ``--home-logo-height``.

Favicon
^^^^^^^

-  Use resource ``favicon`` to update the favicon.

   .. tip::
      - We recommend using an image with size 16x16 or 32x32 pixels.

Change Portal Styles
--------------------

The Portal uses modern |css_variable| to support style customization. 

To customize the Portal styles in an upgrade safe way, we suggest that you copy all CSS variables and styles
which you plan to override from the files ``portal-variables.css`` and ``portal.css`` to file ``custom.css``.

Refer to :dev-url:`Engine Branding </doc/nightly/designer-guide/user-interface/branding/branding-engine.html>` for the
location of :dev-url:`custom.css </doc/nightly/engine-guide/configuration/files/custom-css.html>`.

.. caution:: Please do not modify ``portal-variables.css`` and ``portal.css`` directly because they may change in a future Portal version.

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

You can customize the Portal styles in ``custom.css``.

  For example, to customize the default ``font-family``:

  #. After the ``START OVERRIDING PORTAL STYLES`` section, add a new
     ``@font-face`` block with your ``font-family`` name, and the URLs of the
     family member fonts.

  #. Inside the ``:root`` tag, change the value of ``--portal-font-family`` to your ``font-family`` name.

.. tip::
   - As the size measure unit for ``font-size``, the Portal uses ``rem``. 
     Font sizes of all elements are calculated based on the font size of the ``html`` element.

   - If ``--primary-color`` is a bright color (such as yellow or light blue), you may want to change ``--primary-text-color`` to ``black``. 
     It ensures that text on buttons and other components is legible as it has high contrast.

.. tip::
   If you are using IFrames and you want to customize the colors of the project specific UIs, then refer to this documentation 
   :dev-url:`/doc/nightly/designer-guide/how-to/overrides.html?#override-new-wizard`

.. warning::
   - Do not change ``font-family`` property values.

   - Limitation: the task priority color customization hasn't changed the task priority colors in statistic.

   If you want to custom color of statistics please refer to :ref:`Override Statistic color <override-Statistic-colors>`

.. _customization-portal-logos-and-colors-changedatepatterns:

Change date time pattern
------------------------

You can change the date pattern by modifying the CMS in the PortalKit project:
``PortalKit/patterns/datePattern`` and
``PortalKit/patterns/dateTimePattern``.

.. |css_variable| raw:: html

   <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties" target="_blank">CSS Variable</a>