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
be used in **any Maven build of any projects that uses the same Portal version**.

For example: 
If you have two customer projects that use different logos or colors using the **same Portal version**,
and you build maven, e.g. ``mvn clean install`` PortalStyle of Project 1, then the local maven repository stores
PortalStyle of Project 1. Later you build the deployment package of Project 2, and it will collect all
dependencies of Project 2 to build a zip file. But now PortalStyle from the local maven repository will be found
and used. Now the deployment package of Project 2 contains PortalStyle of Project 1.
This is a problem because the deployment package of Project 2 should contain PortalSyle of Project 2.

The preferred and supported way to create a new Portal Maven identifier is to change the groupId only (best practice is to change it to
the groupId, that you use for your other projects). Because of dependencies between the Portal projects, you **must propagate** this change to
all Portal projects (and their dependencies).

.. important:: Do not change ``artifactId`` such as PortalStyle, PortalKit...

.. note::
	Do not forget to change default pages to your project, follow this chapter to customize standard processes:
	:dev-url:`Standard Processes </doc/8.0/engine-guide/administration/standard-processes.html>`