.. _components-portal-components-user-selection:

User Selection
**************

Introduction
^^^^^^^^^^^^

This component is used for selecting a single user from a list of users defined by a role name list.
If you don't define the role name list, all users will be loaded.
It includes a label, an autocomplete and one message element to display a message related to that autocomplete element.

How to Use
^^^^^^^^^^

You can insert this component into any page. This component supports two styles to display a label.

#. Default Style

   |user-selection|

   Code example:

   .. code-block:: html

      <ic:com.axonivy.portal.components.UserSelection componentId="default-user-autocomplete"
         selectedUser="#{data.selectedUser}" label="Default user selection"
         isRequired="true" labelPanelStyleClass="ui-g-6 ui-md-6 ui-sm-12"
         autoCompleteStyleClass="width-100" autoCompletePanelStyleClass="ui-g-6 ui-sm-12" />

#. Floating Label

   |user-selection-floating-label|

   Code example:

   .. code-block:: html

      <ic:com.axonivy.portal.components.UserSelection componentId="all-user-autocomplete"
         hightlight="false" selectedUser="#{data.selectedUserForExcludingUsers}"
         label="Loading users (exclude gm1, gm2, admin)" autoCompleteStyleClass="width-100"
         autoCompletePanelStyleClass="ui-g-12 floating-label-margin-top"
         excludedUsernames="#{data.excludedUsernames}" floatingLabel="true" />

.. tip::
   The autocomplete element of the user selection component allows to
   insert children and ajax events (Refer to ``UserSelection.xhtml``).
   Any child in the UserSelection component will be re-parented into
   this autocomplete at the location of the ``insertChildren`` tag.
   We introduce a facet named ``event`` for autocomplete so that the
   ajax event can be nested, as well.

An example:

I want to display users in a dropdown list formatted as "<Full name>
(<username>)". When I select a user, a message shall be displayed.

|user-selection-with-children-and-ajax-event|

|user-selection-component-ajax-expand|

.. code-block:: html

   <ic:com.axonivy.portal.components.UserSelection id="item-select-event-component"
      componentId="item-select-event-for-user-selection" floatingLabel="true"
      fromRoleNames="#{data.definedRoleNames}" label="Demonstrate facet and children"
         selectedUser="#{data.selectedUserForInsertChildren}"
      autoCompleteStyleClass="width-100"
      autoCompletePanelStyleClass="ui-g-12 floating-label-margin-top" hasCustomizedSelectionList="true">
         <p:column>
            <pc:securityMemberNameAndAvatar displayName="#{user.getDisplayName()} (#{user.getMemberName()})" securityMember="#{user}" isStandAlone="false" />
         </p:column>
         <f:facet name="event">
            <p:ajax event="itemSelect" listener="#{logic.showSelectedUser}"
               update="#{p:component('item-select-event-for-user-selection-message')}"/>
         </f:facet>
   </ic:com.axonivy.portal.components.UserSelection>

Please refer to ``UserSelectionExample.xhtml`` in project ``portal-components-examples`` for more details.

This component offers the following attributes:

.. csv-table::
  :file: ../documents/user_selection_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

.. _components-portal-components-migrate-from-old-user-selection:

Migrate from Deprecated User Selection
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. Replace code in HTML files: replace ``ic:ch.ivy.addon.portalkit.component.UserSelection`` with ``ic:com.axonivy.portal.components.UserSelection``.

#. Replace ``ch.ivy.addon.portalkit.dto.UserDTO`` with ``com.axonivy.portal.components.dto.UserDTO``.

   .. note:: If you stored class ``ch.ivy.addon.portalkit.dto.UserDTO`` in your database, you have to update the database manually.

.. |user-selection| image:: ../../screenshots/components/user-selection-component.png
.. |user-selection-floating-label| image:: ../../screenshots/components/user-selection-component-floating-label.png
.. |user-selection-with-children-and-ajax-event| image:: ../../screenshots/components/user-selection-component-ajax-event-selected-message.png
.. |user-selection-component-ajax-expand| image:: ../../screenshots/components/user-selection-component-ajax-expand.png