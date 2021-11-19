.. _customization-change-group-id:

Change groupId
==============

.. _customization-change-group-id-introduction:

Portal groupId is ``ch.ivyteam.ivy.project.portal``.

When and why you need to change it
----------------------------------
In general, customers want to change Portal colors, logos so developers need to update project PortalStyle. If you have two customer projects that 
use the **same Portal version**, then you build maven, e.g. `mvn clean install` PortalStyle of Project 1, then the local maven repository 
stores PortalStyle of Project 1.

Now you want to build the deployment package of Project 2, it will collect all dependencies of Project 2 to build a zip file, 
then PortalStyle from the local maven repository is collected. It means the deployment package of Project 2 has PortalStyle of Project 1.

So what we want to achieve is to differentiate artifacts of different projects. We could change groupId, artifactId or version for that purpose
and changing groupId is the simple way with the smallest impact.

As a good practice, you should change groupId of PortalStyle so that it does not overwrite PortalStyle of other projects. Changing groupId 
of PortalStyle leads to changing PortalKit, PortalTemplate. Therefore, you should change groupId of PortalStyle, PortalKit, PortalTemplate.

.. important:: Do not change ``artifactId`` such as PortalStyle, PortalKit...

.. note::
	Do not forget to change default pages to your project, follow this chapter to customize standard processes:
	`Standard Processes <https://developer.axonivy.com/doc/|version|/engine-guide/administration/standard-processes.html>`_