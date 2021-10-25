.. _customization-default-user-process:

Default user process - Deprecated
=================================

.. _customization-default-user-process-introduction:

Introduction
------------

In legacy Portal homepage, the ``Process widget`` displays default processes,
you can customize them so that project important starts are displayed.

.. _customization-default-user-process-customization:

Customization
-------------

Create an override which overrides sub process
``createDefaultUserProcess()`` in PortalKit. This sub process return a
list of user processes. Follow instruction to create default processes.

|default-user-processes|


.. tip::
    - We provide 3 public APIs in ``ProcessStartAPI`` class (Refer to :ref:`Axon Ivy Public API document <public-api>`) to initiate a default user process.
      We recommend you to use them when you want to create user favorite process.

        + For Ivy Process: ``initUserProcessByUserFriendlyRequestPath(String friendlyRequestPath, String displayName)``
        + For Express Process: ``initUserProcessByExpressProcessName(String expressProcessName, String displayName)``
        + For External Link: ``initUserProcessByExternalLinkName(String externalLinkName, String displayName)``

    - The default processes are sorted by their index attribute. If this attribute is not set, the process will be put at the bottom of the list.

.. |default-user-processes| image:: images/default-user-process/default-user-processes.png