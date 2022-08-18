.. _customization-change-group-id:

Change GroupId
==============

.. _customization-change-group-id-introduction:

Portal groupId is ``ch.ivyteam.ivy.project.portal``.

When And Why You Need To Change the GroupId
-------------------------------------------

Most customers want to change (at least) colors and logos in their version of
the portal, so they need to update ``portal-component``. Changing anything in
the Portal **requires** a change of the Maven identifier, which consists of
artifactId, groupId, and version. If the Portal is changed without adapting this
identifier, then the changed version will be written to your local Maven
repository and therefore be used in **any Maven build of any projects that uses
the same Portal version**.

Let's explain this: If you have two customer projects that use different logos
or colors using the **same Portal version**, and you build it using maven, e.g.
you execute ``mvn clean install`` on  portal-component of Project 1, then the local
maven repository stores  portal-component of Project 1. Later you build the deployment
package of Project 2, and it will collect all dependencies of Project 2 to build
a zip file. But now  portal-component from the local maven repository will be found
and used. This means that the deployment package of Project 2 contains
portal-component of Project 1. This is a problem because the deployment package of
Project 2 should contain  portal-component of Project 2.

The preferred and supported way to create a new Portal maven identifier is to
change the groupId only. Best practice is to change it to the groupId that you
use for your other projects in the same deployment package. Because of
dependencies between the Portal projects, you **have to propagate** this change
to all Portal projects (and their dependencies).

.. important:: Do not change ``artifactId`` such as portal-component, portal...

.. note::
	Do not forget to change default pages of your project. Follow this chapter to customize default pages:
	:dev-url:`Default Pages </doc/9.4.0-m229/designer-guide/user-interface/default-pages/index.html>`