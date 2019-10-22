.. _axonivyportal.customization.defaultuserprocess:

Default user process
====================

.. _axonivyportal.customization.defaultuserprocess.introduction:

Introduction
------------

In Portal homepage, the ``Process widget`` displays default processes,
you can customize them so that project important starts are displayed.

.. _axonivyportal.customization.defaultuserprocess.customization:

Customization
-------------

Create an override which overrides sub process
``createDefaultUserProcess()`` in Portal Kit. This sub process return a
list of user processes. Follow instruction to create default processes.

   **Tip**

   We provide the method to generate link from UserFriendlyRequestPath
   in
   ProcessStartCollector
   class:
   findStartableLinkByUserFriendlyRequestPath(String requestPath)
   . This method will return startable link if user has permission to
   start the process, otherwise return emtpy string.
