.. _customization-hideTechnicalStuffs:

Hide technical stuffs
=====================

Hide technical roles
--------------------

A technical role is the role which is not displayed anywhere (e.g.
delegate, absence management). AXONIVY_PORTAL_ADMIN is a technical role
by default.

To mark a role as a technical role, set the **HIDE** property with any
value to the role.

.. tip:: Use the utility method of Portal:
      
      - Set property:
         ch.ivy.addon.portalkit.publicapi.RoleAPI.setProperty([YOUR_ROLE], ch.ivy.addon.portalkit.enums.AdditionalProperty.HIDE.toString(), "HIDE")
      - Reverse it:
         ch.ivy.addon.portalkit.publicapi.RoleAPI.removeProperty([YOUR_ROLE], ch.ivy.addon.portalkit.enums.AdditionalProperty.HIDE.toString())


Hide technical tasks
--------------------

A technical task is the task which is not displayed in any task lists of
Portal.

.. tip:: Use the utility methods of Portal:
      
      - Set task as technical:
         ch.ivy.addon.portalkit.publicapi.TaskAPI.setHidePropertyToHideInPortal(ITask)
      - Reverse it:
         ch.ivy.addon.portalkit.publicapi.TaskAPI.removeHidePropertyToDisplayInPortal(ITask)

Hide technical cases
--------------------

A technical case is the case which is not displayed in any case lists of
Portal.

Tasks belong to the technical case is considered as technical tasks and
should be hide as well.

To mark a case as a technical case, make sure Ivy global variable
``PortalHiddenTaskCaseExcluded`` is set to true. Follow tip

.. tip:: Use the utility methods of Portal:

      - Set case as technical:
         ch.ivy.addon.portalkit.publicapi.CaseAPI.setHidePropertyToHideInPortal(ICase)
      - Reverse it:
         ch.ivy.addon.portalkit.publicapi.CaseAPI.removeHidePropertyToDisplayInPortal(ICase)
