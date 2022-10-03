.. _components-portal-components-security-member-name-and-avatar:

Security Member Name And Avatar
*******************************

Introduction
^^^^^^^^^^^^

This component is used to display the name and avatar of a security member in the ivy security system.
It is also integrated in :ref:`components-portal-components-user-selection` and :ref:`components-portal-components-role-selection`.

How to Use
^^^^^^^^^^

You can insert this component into any page.

|session-role-security-member-name-and-avatar|

Code example using the top-level role (Everybody):

.. code-block:: html

      <pc:securityMemberNameAndAvatar displayName="#{ivy.security.roles().topLevel().displayName}"
         securityMember="#{ivy.security.roles().topLevel()}" />

|session-user-security-member-name-and-avatar|

Code example using the current session user:

.. code-block:: html

      <pc:securityMemberNameAndAvatar displayName="#{ivy.session.sessionUser.displayName}"
         securityMember="#{ivy.session.sessionUser}" />

This component offers the following attributes:

.. csv-table::
  :file: ../documents/security_member_name_and_avatar_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 1 3

Refer to process ``SecurityMemberNameAndAvatarExample`` in project ``portal-components-examples`` for more details.

.. |session-user-security-member-name-and-avatar| image:: ../../screenshots/components/session-user-security-member-name-and-avatar.png
.. |session-role-security-member-name-and-avatar| image:: ../../screenshots/components/session-role-security-member-name-and-avatar.png