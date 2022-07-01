.. _customization-hideTechnicalStuffs:

Hide Technical Stuff
====================

Depending on the value of Ivy variable ``PortalHiddenTaskCaseExcluded``, hidden
tasks and cases are shown (FALSE) or hidden (TRUE). Default value is TRUE.

Hide Technical Roles
--------------------

A technical role is not displayed anywhere (e.g. in delegation or absence
management). AXONIVY_PORTAL_ADMIN is a technical role by default.

To mark a role as a technical role, set the **HIDE** property with any
value on the role.

.. tip:: Use the utility method of Portal:
      
      - Set property:
         ch.ivy.addon.portalkit.publicapi.RoleAPI.setProperty([YOUR_ROLE], ch.ivy.addon.portalkit.enums.AdditionalProperty.HIDE.toString(), "HIDE")
      - Reverse it:
         ch.ivy.addon.portalkit.publicapi.RoleAPI.removeProperty([YOUR_ROLE], ch.ivy.addon.portalkit.enums.AdditionalProperty.HIDE.toString())


Hide Technical Tasks
--------------------

A technical task is not displayed in any task lists of Portal.

To mark a task as a technical task, use our utility methods in TaskAPI.

.. tip:: Use the utility methods of Portal:
      
      - Set task as technical:
         ch.ivy.addon.portalkit.publicapi.TaskAPI.setHidePropertyToHideInPortal(ITask)
      - Reverse it:
         ch.ivy.addon.portalkit.publicapi.TaskAPI.removeHidePropertyToDisplayInPortal(ITask)

Hide Technical Cases
--------------------

A technical case is not displayed in any case lists of Portal.

Tasks belonging to a technical case are considered technical tasks and should be
hidden as well.

To mark a case as a technical case, use our utility methods in CaseAPI.

.. tip:: Use the utility methods of Portal:

      - Set case as technical:
         ch.ivy.addon.portalkit.publicapi.CaseAPI.setHidePropertyToHideInPortal(ICase)
      - Reverse it:
         ch.ivy.addon.portalkit.publicapi.CaseAPI.removeHidePropertyToDisplayInPortal(ICase)
