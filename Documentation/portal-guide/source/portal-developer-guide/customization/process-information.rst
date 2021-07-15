.. _customization-process-information:

Process information
===================

.. _customization-process-information-introduction:

Introduction
------------

When you want to customize your own Process information page such as add
a case list related to the selected process, you should follow this section
to override built-in Process information page of Portal.

.. _customization-process-information-customization:

Define your own process steps
-----------------------------

You can describe details and steps of each process easily by define
process steps for it.

#. Create a CMS object with type ``source`` in your project with this path:
   ``Processes\Example\processStep``

   ``Example`` is the process start signature name (Example.ivp)

#. Format of process step:

   -  Header lines for process step are start with ``#`` (hashtag) symbol

   -  Other lines are descriptions of each steps

   |process-step|


   .. tip::
      You can define as much steps as you want.
      But we recommend you to define maximum 3 steps for better look and feel.

Customization
-------------

#. Override HTML dialog ``ch.ivy.addon.portal.component.ProcessInformation``
   of project ``PortalTemplate``.

#. Copy content and logic of HTML dialog ``ch.ivy.addon.portal.component.ProcessInformation``
   to your overrided HTML dialog.

#. Define your customization in ``processInfo`` section. This section will be displayed
   below process steps section.

   .. code-block:: html

      <ui:define name="processInfo">
        <!-- Put your customization here -->
      </ui:define>

   .. tip::
      Please refer to HTML dialog ``CustomizedProcessInformation``
      of project ``portal-developer-examples`` for more details about customization.

.. |process-step| image:: images/process-information/process-step.png