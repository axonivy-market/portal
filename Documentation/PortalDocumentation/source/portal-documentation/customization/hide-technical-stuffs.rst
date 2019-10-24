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

   **Tip**

   Use the utility method of Portal to set property:

   ch.ivy.addon.portalkit.util.RoleUtils.setProperty([YOUR_ROLE],
   ch.ivy.addon.portalkit.util.HIDE, [ANY_VALUE])

Hide technical tasks
--------------------

A technical task is the task which is not displayed in any task lists of
Portal.

   **Tip**

   Use the utility methods of Portal:

   -  Set task as technical:
      ch.ivy.addon.portalkit.util.TaskUtils.setHidePropertyToHideInPortal(ITask)

   -  Reverse it:
      ch.ivy.addon.portalkit.util.TaskUtils.removeHidePropertyToDisplayInPortal(ITask)

Hide technical cases
--------------------

A technical case is the case which is not displayed in any case lists of
Portal.

Tasks belong to the technical case is considered as technical tasks and
should be hide as well.

To mark a case as a technical case, make sure Ivy global variable
``PortalHiddenTaskCaseExcluded`` is set to true. Follow tip

   **Tip**

   Use the utility methods of Portal:

   -  Set case as technical:
      ch.ivy.addon.portalkit.util.CaseUtils.setHidePropertyToHideInPortal(ICase)

   -  Reverse it:
      ch.ivy.addon.portalkit.util.CaseUtils.removeHidePropertyToDisplayInPortal(ICase)
