.. _customization-login:

Login (Deprecated)
==================
.. warning:: Deprecated: This feature is marked for removal in version LTS 12.

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