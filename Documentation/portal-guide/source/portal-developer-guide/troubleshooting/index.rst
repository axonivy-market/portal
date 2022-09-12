.. _troubleshooting:

===============
Troubleshooting
===============

Here, we present solutions to some of the most common problems related
to the Axon Ivy Portal.

If you can't find your solution here there are some other sources which
could help:

-  Community

   The `Community <http://community.axonivy.com/>`__ contains a
   considerable amount of questions and answers related to Axon Ivy
   Designer and Engine.

-  Stack Overflow

   Problems related to common technologies like Java, JSF, Primefaces
   are answered on the web, e.g. on `Stack
   Overflow <http://www.stackoverflow.com/>`__.

-  Support

   You can get support via support@axonivy.com (support may be subject
   to charging, depending on your licence agreement).

.. _troubleshooting-ie-security-problem:

Portal Installation with Reverse Proxy
======================================

If you install the Portal in an environment that uses a reverse proxy (Nginx,
IIS or Apache httpd), you have to define the proxy in the Axon Ivy Engine
configuration.

Update Default Pages
====================
If you copy ``PortalStart.p.json`` to your project for customization, you need
to follow this chapter to customize the default pages in your designer and
engine: :dev-url:`Default Pages </doc/9.4.0/designer-guide/user-interface/default-pages/index.html>`

For example, if you start with the default settings:

.. literalinclude:: files/app-designer.yaml
  :language: yaml

and your Portal project has a different package and name, such as:
``com.example:customizedPortal``, then you have to change the configuration as
shown below:

.. literalinclude:: files/app-designer-customized.yaml
  :language: yaml

