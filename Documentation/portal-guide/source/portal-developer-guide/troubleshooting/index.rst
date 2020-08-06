.. _troubleshooting:

===============
Troubleshooting
===============

Here you will find solutions to some of the most common problems related
to Axon.ivy Portal.

If you can't find your solution here there are some other sources which
could help:

-  Axon.ivy Q&A

   The `Axon.ivy Q&A <http://answers.axonivy.com/>`__ contains a
   considerable amount of questions and answers related to Axon.ivy
   Designer and Engine.

-  Stack Overflow

   Problems related to common technologies like Java, JSF, Primefaces
   are answered on the web, e.g. on `Stack
   Overflow <http://www.stackoverflow.com/>`__.

-  Support

   You can get support via support@axonivy.com (support may be subject
   to charging, depending on your licence agreement).

.. _troubleshooting-ie-security-problem:

IE Security Problem
===================

If you start Portal in Internet Explorer installed on Windows Server
then Portal application may not work correctly. The reason could be
**Internet Explorer Enhanced Security Configuration** is enabled by
default which means ActiveX Controls and scripting are disabled, so
Internet sites may not display in Internet Explorer as you expect.

To fix this, you may turn off **Internet Explorer Enhanced Security
Configuration** if you are running in Windows Server. Another way is
adding that site to the Trusted sites zone in Internet Explorer.

.. _troubleshooting-portal-install-with-iis:

Portal install with IIS
=======================

It could be a problem when install portal with IIS with proxy, depends
on your environment. Consider to configure if your IIS is called via
proxy. Add ``-Dhttp.proxyHost`` to ``VM argument`` could help.

Update default pages
====================
If you copy ``PortalStart.mod`` to your project for customization, or :ref:`change the group id of Portal artifact <customization-change-group-id>`,
you need to follow this chapter to customize standard processes in your designer and engine:
`Standard Processes <https://developer.axonivy.com/doc/latest/engine-guide/administration/standard-processes.html>`_

For example, below is the default setting:

.. literalinclude:: files\app-designer.yaml
  :language: yaml

And your portal project has different package and name, such as: ``com.example:customizedPortal``. Then you can change the configuration as below:

.. literalinclude:: files\app-designer-customized.yaml
  :language: yaml

