.. _components-portal-chat:

Portal Chat
===========

.. _components-portal-chat-chat-feature:

Chat Feature
------------

|chat|

.. _components-portal-chat-information:

Information
-----------

- To use Portal chat, refer to :ref:`portal-chat`

- The chat feature has been rewritten in Ivy 7.4.

- If browsers access Portal through a reverse proxy like Nginx, set Portal setting **Portal.Chat.ResponseTimeout** to less than the Nginx timeout (usually 60 seconds) to prevent connection abortions.

- Each tab uses one long-polling request for chat. Browsers limit the number of parallel long-polling requests for one domain. The maximumis usually six or more.
  To handle this limitation, Portal introduced the setting **Portal.Chat.MaxConnection**. Default value is 3. If a fourth tab is opened, chat in one inactive tab will be deactivated.
  If you select a tab where chat has been deactivated, all chat information will be refreshed and chat is activated again. If there are still more than MaxConnection chat tabs open, chat in another tab will be deactivated.

- Group chat supports some customizations, refer to :ref:`Group chat customization <customization-group-chat>` for details.

- If your system uses an additional Tomcat **Valve**, then we recommend to add asyncSupported in file ``Context.xml``. An example file is available at :dev-url:`File Reference </doc/nightly/engine-guide/configuration/files/context-xml.html>`.

  .. code-block:: html

    <Context>
      <Valve className="..." asyncSupported="true" />
    </Context>


.. _components-portal-chat-limitation:

Limitations Of Current Portal Chat
----------------------------------

Portal Chat does not support cross-application chat. That means users are
able to chat with other users in the current application, but they cannot chat
with users in other applications.

.. |chat| image:: ../../screenshots/chat/chat.png
