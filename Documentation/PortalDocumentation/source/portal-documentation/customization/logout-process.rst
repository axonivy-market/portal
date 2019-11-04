.. _customization-logout:

Logout process
==============

1. Introduce an Axon.ivy project which has ``PortalTemplate`` as a
   required library.

2. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. This process is new home page and administrator should
   register this link by global

3. Refer to :ref:`Customize Portal
   home <customization-portal-home>` to set new home
   page.

4. Override the ``Logout`` process to customize the logout behavior.

5. Create a callable sub process in your project with the
   ``getLogoutPage()`` signature to customize the page which will be
   redirect to after logout, default is Portal home page. Make sure this
   signature is unique in your application.
