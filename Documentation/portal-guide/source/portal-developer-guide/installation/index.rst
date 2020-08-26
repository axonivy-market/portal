.. _installation:

Installation
************

Installation section describes all steps, that are necessary for install and setup Portal.
If you install your application first time then it’s important to start with Basic installation. It describes all initial steps that must be done for first installation.
If application is already installed and initial prepared, refer
to `Migration notes`_ to migrate it.

Basic installation
==================

Project modules
---------------

The application consists of 4 process modules. For detailed information
of each module, refer to :ref:`architecture` .

-  PortalStyle

-  PortalKit

-  PortalTemplate

-  AxonIvyExpress

The project deployment of Ivy project are described in `project
deployment <https://developer.axonivy.com/doc/9.1/engine-guide/administration/deployment.html>`__
.

Installation
------------

Designer
^^^^^^^^

Import Portal modules to Designer.


Engine without license (demo mode)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The engine automatically deploys the Portal application with default users.

.. table:: 

   +-----------------------+-----------------------+-----------------------+
   | Username              | Password              | Description           |
   +=======================+=======================+=======================+
   | admin                 | admin                 | This user has all     |
   |                       |                       | Portal permissions,   |
   |                       |                       | can access to Portal  |
   |                       |                       | Admin Settings.       |
   +-----------------------+-----------------------+-----------------------+
   | demo                  | demo                  | This user has         |
   |                       |                       | permission to manage  |
   |                       |                       | user absences.        |
   +-----------------------+-----------------------+-----------------------+
   | guest                 | guest                 | Default normal user   |
   |                       |                       | of portal.            |
   +-----------------------+-----------------------+-----------------------+


Engine with license (production mode)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The engine does not deploy anything, you need to deploy and configure Portal application 
manually.


.. _installation-migration-notes:

Migration notes
===============

This document informs you in detail about incompatibilities that were
introduced between Portal versions and tells you what needs to be done
to make your existing Portal working with current Axon.ivy engine.

How to migrate
--------------
   
.. important:: 
   If you call any Portal API which is not mentioned in the document. It could be changed or removed without inform. Don't forget
   to re-implement the concerning API in your own project.

   In order to migrate Portal, you need to migrate Axon.ivy, refer
   `Axon.ivy migration
   notes <https://developer.axonivy.com/doc/9.1/axonivy/migration/index.html>`__.
   Changes in Axon.ivy could lead to problems if customer project is not
   migrated properly.

In designer
-----------

1. Replace all Portal projects
2. Update PortalTemplate dependency of customer project in pom.xml.
3. If PortalStyle is customized, copy logo, customization.scss,
   font-faces.scss, customized stuff from old to new PortalStyle, run
   maven to compile CSS.
4. Follow detailed migration notes for each version below.
5. If customization needs copying code from Portal, merge changes
   between two versions of Portal for copied code.

..

In engine
---------

1. Convert database schema if needed.

2. Redeploy Portal projects and customer
   project.
3. Follow detailed migration notes for each version below.

Migrate 9.1 to 9.2
------------------

1. In PortalNavigatorInFrame.java, change the methods from non-static to static.

Migrate 8.x to 9.1
------------------

1. Remove the ``views`` field in SubMenuItem.java. Adapt it if you overrode the ``LoadSubMenuItems`` callable process

2. Add parameter ``<ui:param name="viewName" value="TASK" />`` to your customized ``PortalTasksTemplate`` to displayed breadcrumb of Task list.

3. Add parameter ``<ui:param name="viewName" value="CASE" />`` to your customized ``PortalCasesTemplate`` to displayed breadcrumb of Case list.

4. Ivy core enhanced the Ivy URI, so Portal needs to make a migration. Deploy :download:`PortalUrlMigration.iar <documents/PortalUrlMigration.iar>` project to any Ivy Application then run ``migratePortalUrl.ivp`` once and wait until it is redirected to another page without error (E.g: Homepage). It is recommended to remove it after the migration.

5. HOMEPAGE_URL (single Portal app mode) and registered application link (multi Portal app mode) are not available anymore. To let portal know about your new portal home, you have to set default pages to your project, follow this chapter to customize standard processes: `Standard Processes <https://developer.axonivy.com/doc/9.1/engine-guide/administration/standard-processes.html>`_

6. Portal now uses |css_variable| instead of SASS. Therefore you must convert SASS syntax to new CSS variable or use online tool such as |css_variable_convert| to convert it.

7. If administrator activate the ``ENABLE_CASE_OWNER`` Portal settings and there is a customized case list, customize this field to this case list, e.g. add filter, column configuration, header.

8. From 9.1, Ivy engine use new mechanism to synchronize user data, therefore Portal must adapt some data related to users. Some data must be migrated to work properly. Please follow these steps to migrate data of your application:

   - Deploy :download:`MigrateRelatedDataOfUserTo9.iar <documents/MigrateRelatedDataOfUserTo9.iar>` project to your Ivy application.

   - Run ``migratePrivateChat.ivp`` to migrate private chat messages.

   - Run ``migrateGroupChat.ivp`` to migrate group chat.

   - Run ``migrateUserProcessesAndExternalLinks.ivp`` to migrate user processes and external links.

   - Run ``migrateExpressProcesses.ivp`` to migrate Express processes, please skip this step if your application does not have Express.

   - Restart Ivy engine.

9. Use ``pageContent`` to define your section in ``BasicTemplate.xhtml`` instead of ``simplePageContent``.

10. ``TaskTemplate-7`` is removed, change it to ``TaskTemplate-8``. ``TaskTemplate`` is also removed, change it to ``frame-8`` (provided by Ivy).

.. _installation-release-notes:

Release notes
=============

This part lists all relevant changes since the last official product
releases of Axon.ivy.


Changes in 9.2
--------------

- Include new TaskState such as ``Destroyed``, ``Failed``, ``Join failed`` and ``Waiting for event`` in Portal Task list, also in Task State filter.

- Include new CaseState ``Destroyed`` in Portal Case list, also in Case State filter.

- Introduce :ref:`Workflow Events table <how-to-show-workflow-events>`, user who has permission ``WORKFLOW_EVENT_READ_ALL`` can see all ``WORKFLOW_EVENTS``.



Changes in 9.1
--------------

- Refactor style customization approach. From now on, Portal use CSS Variable as technology to customize CSS.

- Introduce the User Guide feature, using the ``SHOW_USER_GUIDE`` Portal Setting to activate/deactivate it, 
  and follow :ref:`Customize user guide <customization-portal-home-user-guide>` for your customization.

- Introduce new Portal Setting ``SHOW_BUTTON_ICON`` to control visibility of icon of button in Portal.

- Introduce new Portal dialog with icon decorator. Refer to :ref:`this section <components-additional-portal-dialog-with-icon>` for more detail.

- TaskTemplate-7, TaskTemplate and TwoColumnTemplate are removed.


.. |css_variable| raw:: html

   <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties" target="_blank">CSS Variable</a>
.. |css_variable_convert| raw:: html

   <a href="https://www.npmjs.com/package/sass-to-css-variables" target="_blank">SASS to CSS Variables</a>