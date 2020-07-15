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
deployment <http://developer.axonivy.com/doc/latest/EngineGuideHtml/administration.html#administration-deployment>`__
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
   notes <https://developer.axonivy.com/doc/latest/MigrationNotes.html>`__.
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

2. Redeploy Portal projects (exclude PortalConnector) and customer
   project.
3. Follow detailed migration notes for each version below.

Migrate 8.x to 9.1
----------------------

1. Remove the ``views`` field in SubMenuItem.java. Adapt it if you overrode the ``LoadSubMenuItems`` callable process

2. Add parameter ``<ui:param name="viewName" value="TASK" />`` to your customized ``PortalTasksTemplate`` to displayed breadcrumb of Task list.

3. Add parameter ``<ui:param name="viewName" value="CASE" />`` to your customized ``PortalCasesTemplate`` to displayed breadcrumb of Case list.

4. Ivy core enhanced the Ivy URI, so Portal needs to make a migration. Deploy :download:`PortalUrlMigration.iar <documents/PortalUrlMigration.iar>` project to any Ivy Application then run ``migratePortalUrl.ivp`` once and wait until it is redirected to another page without error (E.g: Homepage). It is recommended to remove it after the migration.

5. HOMEPAGE_URL (single Portal app mode) and registered application link (multi Portal app mode) are not available anymore. To let portal know about your new portal home, you have to set default pages to your project, follow this chapter to customize standard processes: `Standard Processes <https://developer.axonivy.com/doc/latest/engine-guide/administration/standard-processes.html>`_

6. Portal now uses |css_variable| instead of SASS. Therefore you must convert SASS syntax to new CSS variable or use online tool such as |css_variable_convert| to convert it.

7. If administrator activate the ``ENABLE_CASE_OWNER`` Portal settings and there is a customized case list, customize this field to this case list, e.g. add filter, column configuration, header


.. _installation-release-notes:

Release notes
=============

This part lists all relevant changes since the last official product
releases of Axon.ivy.

Changes in 9.1
----------------

- Refactor style customization approach. From now on, Portal use CSS Variable as technology to customize CSS.

- Introduce the User Guide feature, using the ``SHOW_USER_GUIDE`` Portal Setting to activate/deactivate it, 
  and follow :ref:`Customize user guide <customization-portal-home-user-guide>` for your customization.

.. |css_variable| raw:: html

   <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties" target="_blank">CSS Variable</a>
.. |css_variable_convert| raw:: html

   <a href="https://www.npmjs.com/package/sass-to-css-variables" target="_blank">SASS to CSS Variables</a>