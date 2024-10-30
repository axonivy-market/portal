.. _portal-chat:

Portal Chat
***********

Portal supports process chat related to a case or private chat between two users. By default, chat is deactivated.


HowTo: Activate chat
--------------------

You can turn on private chat with this Portal setting: :guilabel:`Portal.Chat.EnablePrivate`.

You can turn on process chat with this Portal setting: :guilabel:`Portal.Chat.EnableGroup`.

See :ref:`Update Portal settings <update-portal-settings>`.


HowTo: Create a process chat related to a case
----------------------------------------------

.. note::

   Process chat of a case could have pre-defined roles that all users of those roles will join the process chat automatically. 

#. Go to any tasks, you will see the process chat icon there.

   |create-new-process-chat|

#. If the login user belongs to pre-defined roles, you would see :guilabel:`Add users to chat` dialog. You could choose members of process chat.
   Members could be users or roles. With each selection, click :guilabel:`Add`. After adding members, click :guilabel:`Create process chat`.

   |chat-process-assignee|

   If the login user does not belong to pre-defined roles, you would be informed that the process chat is created.

HowTo: Read/Send chat message
-----------------------------

#. Click on |bubble-icon| icon on the top right corner.

   |access-chat|

#. Click on a process chat or a user to read messages

   |chat|

#. You could type, then press :kbd:`Enter` or click |send-email-icon| to send messages.

.. include:: ../includes/_common-icon.rst

.. |chat| image:: ../../screenshots/chat/chat.png
.. |chat-process-assignee| image:: ../../screenshots/chat/chat-group-assignee.png
.. |create-new-process-chat| image:: ../../screenshots/chat/create-new-group-chat.png
.. |access-chat| image:: ../../screenshots/chat/access-chat.png

