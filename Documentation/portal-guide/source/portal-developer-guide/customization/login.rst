.. _customization-login:

Login
=====

Login page
----------

|login-default|

To replace default login page, extend existing templates with
``ui:define name="login"`` to define your new login component like below

.. code-block:: html

    <ui:composition template="/layouts/BasicTemplate.xhtml">
      <ui:define name="login">
        <ic:internaltest.ui.YourOwnLoginComponent />
      </ui:define>
    </ui:composition>
..

Login background
----------
To change the default login background image, simply add or replace the image in ``configuration/applications/<APPNAME>/branding/background.jpg`` by your image.

Refer to `Engine Branding <https://developer.axonivy.com/doc/nightly/designer-guide/user-interface/branding/branding-engine.html#>`__ for more detail.

.. tip::
  - We recommend using an image with size is 1920x1080 pixels.

  - Images are supported with every known extension, e.g. ``.jpg``, ``.png``, ``.svg``. However, if there are multiple files with different extensions (e.g. a ``background.jpg`` and a ``background.png``) in the same directory, the first found will be chosen.

Login error page
----------------

|login-error-page|

This page is to show error message instead of Login page when you are using external authentication
and the user is not created in your application user list.

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