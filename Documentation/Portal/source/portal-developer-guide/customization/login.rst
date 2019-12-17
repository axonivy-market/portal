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

Additional
----------
To change the default login background image, simply replace the default image in ``/PortalStyle/webContent/resources/serenity-layout/images/login/login-bg.jpg`` by your image

.. |login-default| image:: images/login/login-default.png

