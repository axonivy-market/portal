.. _customization-logout:

Logout process
==============

#. Introduce an Axon Ivy project which has ``PortalTemplate`` as a
   required library.

#. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. This process is new home page and administrator should
   register this link by global

#. Refer to :ref:`Customize Portal
   home <customization-portal-home>` to set new home
   page.

#. Override the ``Logout`` process to customize the logout behavior.

#. Create a callable sub process in your project with the
   ``getLogoutPage()`` signature to customize the page which will be
   redirect to after logout, default is Portal home page. Make sure this
   signature is unique in your application.
