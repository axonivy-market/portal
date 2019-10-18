.. _axonivyportal.customization.groupchat:

Group chat customization
========================

.. _axonivyportal.customization.groupchat.introduction:

Introduction
------------

Group chat feature supports us to customize Group chat header, Group
chat name, Confirgured role list for each process.

.. _axonivyportal.customization.groupchat.customize.groupchatheader:

Customize group chat header
---------------------------

Override group chat title/header via "groupChatHeader" CMS entry.

.. _axonivyportal.customization.groupchat.customize.groupchatname:

Customize group chat name
-------------------------

1. Introduce an Axon.ivy project which has ``PortalTemplate`` as a
   required library.

2. Override ``SetGroupChatName`` process to customize group chat name,
   follow its note to change group chat name.

3. If you have parameters which are not available in GroupChat.java,
   override ``GetGroupChatParams`` callable process and follow its note.

.. _axonivyportal.customization.groupchat.customize.predefinedroles:

Customize predefined responsible roles
--------------------------------------

Override ``AssignRolesGroupChat`` process to customize predefined
responsible roles, follow its note to configure.
