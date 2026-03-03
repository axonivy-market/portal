.. _components-portal-components-role-selection:

Role Selection
**************

Introduction
^^^^^^^^^^^^

This component is used to select a role from a given list of roles. If you don't
define the role list, all roles will be loaded. It includes one label, one
autocomplete and one message element to display messages related to this
autocomplete element.

How to Use
^^^^^^^^^^

You can insert this component into any page. This component offers the following
two styles to display its label.

#. Default Style

   |role-selection|

   Code example:

   .. code-block:: html

      <ic:com.axonivy.portal.components.RoleSelection
         componentId="role-from-defined-role-autocomplete"
         fromRoleNames="#{data.definedRoleNames}"
         selectedRole="#{data.selectedRoleForDefinedRoles}"
         isRequired="true"
         label="Roles from defined role names"/>

#. Floating Label

   |role-selection-floating-label|

   Code example:

   .. code-block:: html

      <ic:com.axonivy.portal.components.RoleSelection
         componentId="floating-label-and-exclude-role-autocomplete" hightlight="false"
         selectedRole="#{data.selectedRole}"
         label="Loading with all roles (exclude CaseOwner, GeneralManager)"
         excludedRolenames="#{data.excludedRoleNames}"
         isRequired="true" floatingLabel="true" />

.. tip::
   The autocomplete element of the role selection component allows you to
   insert children and ajax events (Refer to ``RoleSelection.xhtml``).
   Any child in the RoleSelection component will be re-parented into this
   autocomplete at the location of the ``insertChildren`` tag. We
   introduced a facet named ``event`` for autocomplete so that the ajax
   event can be nested, as well.

For example:

I want to display roles in dropdown list formatted as "<Display Name> (<Member
Name>)". When I select a role, a message shall be displayed.

|role-selection-with-children-and-ajax-event|

|role-selection-component-ajax-expand|

.. code-block:: html

      <ic:com.axonivy.portal.components.RoleSelection
         id="item-select-event-component"
         componentId="item-select-event-for-role-selection"
         fromRoleNames="#{data.definedRoleNames}"
         selectedRole="#{data.selectedRoleForInsertChildren}"
         label="Demonstrate facet and children"
         autoCompleteStyleClass="width-100"
         autoCompletePanelStyleClass="ui-g-12 floating-label-margin-top"
         isRequired="true" floatingLabel="true" hasCustomizedSelectionList="true">
         <p:column>
            <pc:securityMemberNameAndAvatar displayName="#{role.getDisplayName()} (#{role.getMemberName()})" securityMember="#{role}" isStandAlone="false" />
         </p:column>
         <f:facet name="event">
            <p:ajax event="itemSelect" listener="#{logic.showSelectedRole}"
               update="#{p:component('item-select-event-for-role-selection-message')}" />
         </f:facet>
      </ic:com.axonivy.portal.components.RoleSelection>

Please refer to ``RoleSelectionExample.xhtml`` in ``portal-components-examples`` project for more details.

This component offers the following attributes:

.. csv-table::
  :file: ../documents/role_selection_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

.. _components-portal-components-migrate-from-old-role-selection:

Migrate from Deprecated Role Selection
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
#. Replace code in HTML files: replace ``ic:ch.ivy.addon.portalkit.component.RoleSelection`` with ``ic:com.axonivy.portal.components.RoleSelection``.

#. Replace ``ch.ivy.addon.portalkit.dto.RoleDTO`` with ``com.axonivy.portal.components.dto.RoleDTO``.

   .. note:: If you stored class ``ch.ivy.addon.portalkit.dto.RoleDTO`` in your database, you have to update the database manually.

.. |role-selection| image:: ../../screenshots/components/role-selection-component-from-defined-role.png
.. |role-selection-floating-label| image:: ../../screenshots/components/role-selection-component-floating-label-and-exclude-role.png
.. |role-selection-with-children-and-ajax-event| image:: ../../screenshots/components/role-selection-component-ajax-event-selected-message.png
.. |role-selection-component-ajax-expand| image:: ../../screenshots/components/role-selection-component-ajax-expand.png