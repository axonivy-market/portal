.. _components-portal-chat:

Portal chat
===========

.. _components-portal-chat-chat-feature:

Chat feature
------------

|chat|

.. _components-portal-chat-information:

Information
-----------

Chat feature is reimplemented from Ivy 7.4.

There are two modes of chat: Group chat and Private chat. Both features
are disabled by default.

1. You can turn on private chat with this Global setting:
   **ENABLE_PRIVATE_CHAT**.

2. You can turn on group chat with this Global setting:
   **ENABLE_GROUP_CHAT**.

3. If browsers access Portal through a reverse proxy Nginx, set Global
   setting **CHAT_RESPONSE_TIMEOUT** a number less than Nginx timeout to
   make chat work properly.

4. Each tab uses one long-polling request for chat. Browsers limit the number of long-polling request for one domain, max number could be six or more.
   To handle this limitation, Portal introduces Global setting **CHAT_MAX_CONNECTION**, default value is 3. 
   If fourth tab is open, chat in one inactive tab will be deactivated. If you select deactivated chat tab again, all chat information will be refreshed and chat is activated again, 
   then chat in another tab will be deactivated.


How to create a group chat
--------------------------

After turn on group chat feature, go to any task using Task Template, you will see the chat group icon there.

|create-new-group-chat|

Click on group chat icon, the dialog will appear to choose members of group chat. Members could be users or roles.

|chat-group-assignee|

.. tip:: Group chat supports some customizations, refer to
        	   :ref:`Group chat customization <customization-group-chat>` for
        	   more details.


.. _components-portal-chat-limitation:

Limitation of current Portal chat
---------------------------------

Portal Chat does not support cross application chat. That means user is
able to chat with other users in the current application, but he cannot chat
with users in other application.

.. |chat| image:: ../../screenshots/chat/chat.png
.. |chat-group-assignee| image:: ../../screenshots/chat/chat-group-assignee.png
.. |create-new-group-chat| image:: ../../screenshots/chat/create-new-group-chat.png
