.. _customization-login:

Login
=====

Login Page
----------

|login-default|

To replace the default login page, add ``ui:define name="login"`` to the
existing template to define your new login component as shown below

.. code-block:: html

    <ui:composition template="/layouts/BasicTemplate.xhtml">
      <ui:define name="login">
        <ic:internaltest.ui.YourOwnLoginComponent />
      </ui:define>
    </ui:composition>
..

Login Background
----------------

To change the default login background image, simply add or replace the image in
``configuration/applications/<APPNAME>/branding/background.jpg`` with your image.

Refer to :dev-url:`Engine Branding </doc/nightly/designer-guide/user-interface/branding/branding-engine.html#>` for details.

.. tip::
  - We recommend using an image with 1920x1080 pixels.

  - Portal supports ``.jpg``, ``.png``, ``.svg`` extensions for background
    images. If there are multiple files with different extensions (e.g.
    a ``background.jpg`` and a ``background.png``) in the same directory, the
    first found will be chosen.

Login Error Page
----------------

|login-error-page|

This page is to show an error message instead of the Login page when you are using external authentication
and the user is not available in your application user list.

To replace default login error page, extend existing templates with
``ui:define name="loginErrorPage"`` to define your new login error component like below

.. code-block:: html

    <ui:composition template="/layouts/BasicTemplate.xhtml">
      <ui:define name="loginErrorPage">
        <ic:internaltest.ui.YourOwnLoginErrorComponent />
      </ui:define>
    </ui:composition>
..

.. |login-default| image:: ../../screenshots/login/login-form.png
.. |login-error-page| image:: ../../screenshots/login/login-error-page.png