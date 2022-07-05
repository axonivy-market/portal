.. _customization-logout:

Logout Process
==============

#. Create an Axon Ivy project which has ``PortalTemplate`` as a
   required library.

#. Copy the ``PortalStart`` process from ``PortalTemplate`` to your project.
   This process is the new home page. The administrator has to register this
   link.

#. Refer to :ref:`Customize Portal home <customization-portal-home>` to set new
   home page.

#. Override the ``Logout`` process to customize the logout behavior.

#. Create a callable subprocess in your project with the ``getLogoutPage()``
   signature to customize the page which will be redirected to after logout,
   default is the Portal home page. Make sure this signature is unique in your
   application.
