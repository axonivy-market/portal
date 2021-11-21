.. _customization-change-group-id:

Change groupId
==============

.. _customization-change-group-id-introduction:

Portal groupId is ``ch.ivyteam.ivy.project.portal``.

When and why you need to change it
----------------------------------
Most customers want to change (at least) colors and logos in their version of the portal, so they need to update the project PortalStyle.
Changing anything in the Portal **requires** a change of the Maven identifier, which consists of artifactId, groupId and version. If the
Portal is changed without adapting this identifier, then the changed version will be written to your local Maven repository and therefore
be used in *any Maven build of any project that uses the same Portal version*.

The preferred and supported way to create a new Portal Maven identifier is to change the groupId only (best practice is to change it to
the groupId, that you use for your other projects). Because of dependencies between the Portal projects, you must propagate this change to
all Portal projects (and their dependencies).

.. important:: Do not change ``artifactId`` such as PortalStyle, PortalKit...

.. note::
	Do not forget to change default pages to your project, follow this chapter to customize standard processes:
	`Standard Processes <https://developer.axonivy.com/doc/|version|/designer-guide/user-interface/standard-processes/index.html>`_