.. _customization-portal-logos-and-colors:

Portal Styles Customization (Logos, Colors, Date Patterns)
==========================================================

.. _customization-portal-logos-and-colors-change-portal-logos:

Change Logos in Portal
----------------------

You can change both the top-left corner logo in the menu and the login logo by
using :dev-url:`Engine Branding </doc/|version|/designer-guide/user-interface/branding/branding-engine.html>`.

We have two different logo graphics for the expanded and the collapsed
menu. You can use one "pure" logo for the small, quadratic format (default: 42x42 pixels), and a logo
with a slogan for the bigger one. The larger logo is intended to have a 4:1
width:height ratio (default: 168x42 pixels).

The best graphics format for logos is SVG (Scalable Vector Graphics) as it
allows to scale while retaining proportions - logos are always rendered as good
as possible in the current resolution. The resolution is defined by the browser
window size and zoom as well as the screen resolution. In addition, SVG files
are usually pretty small because they are text files. All modern browsers can
render SVG graphics.

On the other hand, PNG logos may blur when expanded, and they result in large
file sizes if supplied in high resolution to prevent the blurring.

We recommend using images with a transparent background, if your Corporate
Identity does not define a mandatory background color. 

To change the logos used in the Portal, please use resources that are described at 
:dev-url:`User Interface Branding </doc/|version|/designer-guide/user-interface/branding/branding-user-interface.html#resources>` and follow the guidelines below.

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
which you plan to override from the files ``portal-root-variables.css``, ``portal-variables-light.css``, and ``portal-variables-dark.css`` to file ``custom.css``.

Refer to :dev-url:`Engine Branding </doc/|version|/designer-guide/user-interface/branding/branding-engine.html>` for the
location of :dev-url:`custom.css </doc/|version|/engine-guide/configuration/files/custom-css.html>`.

.. caution:: Please do not modify ``portal-root-variables.css``, ``portal-variables-light.css``, ``portal-variables-dark.css`` and ``portal.css`` directly because they may change in a future Portal version.

..

Below is the list of some Portal elements which are using customizable colors:

.. csv-table::
  :file: documents/available_css_variables.csv
  :widths: 20 10 40 
  :header-rows: 1
  :class: longtable

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
   :dev-url:`/doc/|version|/designer-guide/how-to/overrides.html?#override-new-wizard`

.. warning::
   - Do not change ``font-family`` property values.

.. |css_variable| raw:: html

   <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties" target="_blank">CSS Variable</a>