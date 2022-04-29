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
of each module, refer to :ref:`architecture`.

-  PortalStyle

-  PortalKit

-  PortalTemplate

-  AxonIvyExpress

The project deployment of Ivy project are described in :dev-url:`project
deployment </doc/nightly/engine-guide/deployment/index.html>`
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
to make your existing Portal working with current Axon Ivy engine.

How to migrate
--------------
   
.. important:: 
   If you call any Portal API which is not mentioned in the document. It could be changed or removed without inform. Don't forget
   to re-implement the concerning API in your own project.

   In order to migrate Portal, you need to migrate Axon Ivy, refer
   :dev-url:`Axon Ivy migration
   notes </doc/nightly/axonivy/migration/index.html>`.
   Changes in Axon Ivy could lead to problems if customer project is not
   migrated properly.

In designer
-----------

#. Replace all Portal projects
#. Update PortalTemplate dependency of customer project in pom.xml.
#. If PortalStyle is customized, copy logo, customization.scss,
   font-faces.scss, customized stuff from old to new PortalStyle, run
   maven to compile CSS.
#. Follow detailed migration notes for each version below.
#. If customization needs copying code from Portal, merge changes
   between two versions of Portal for copied code.

..

In engine
---------

#. Convert database schema if needed.

#. Redeploy Portal projects and customer
   project.
#. Follow detailed migration notes for each version below.

Migrate 9.3 to 9.4
------------------

If you configured Process widgets in your own dashboards as described in :ref:`configure-new-dashboard-proces-widget`, 
you need to adapt JSON as follows:

* Search text ``"type":"process"``, then find related ``displayMode`` of that Process widget.
* If ``displayMode`` is ``COMPACT_MODE``, change ``type`` to ``compact-process``.
* If ``displayMode`` is ``COMBINED_MODE``, change ``type`` to ``combined-process``.
* If ``displayMode`` is ``FULL_MODE``, change ``type`` to ``full-process``.
* If ``displayMode`` is ``IMAGE_MODE``, change ``type`` to ``image-process``.

For example:

   In 9.3, JSON is

   .. code-block:: json

      {"type":"process","displayMode":"COMPACT_MODE","id":"process_1","name":"Your Processes1",
      "layout":{"id":null,"styleClass":null,"style":null,"w":3,"h":8,"x":5,"y":0}}

   ..
   In 9.4, update JSON to

   .. code-block:: json

      {"type":"compact-process","displayMode":"COMPACT_MODE","id":"process_1","name":"Your Processes1",
      "layout":{"id":null,"styleClass":null,"style":null,"w":3,"h":8,"x":5,"y":0}}

   ..

Migrate to 9.3
--------------

#. Deploy :download:`portal-migration.iar <documents/portal-migration-9.3.0.iar>` project to your Ivy application and run it by access link
   ``your_host/your_application/pro/portal-migration/175F92F71BC45295/startMigrateConfiguration.ivp``

   .. important::
      * If you have many applications, deploy to only one application and run it by access the migration link,
        for example: ``https://portal.io/Portal/pro/portal-migration/175F92F71BC45295/startMigrateConfiguration.ivp``

      * Use an administrator account to sign in
      * Run migration process only once

#. We changed the way to navigate to Task Analysis component. Process ``Start Processes/TaskAnalysis/start.ivp`` is moved to new place ``Start Processes/PortalStart/showTaskAnalysis.ivp``. 
   Refer to :ref:`Task Analysis call<components-additional-component-task-analysis-how-to-use>` for more details.

#. We moved the configuration of announcement, thirdparty applications, default statistic charts, application favorite processes, public external links and express processes from the BusinessData to ivy variables.

#. Copy the PortalStart process from PortalTemplate to your project because we changed something relate to DefaultApplicationHomePage.ivp and PortalDashboardConfiguration.ivp.
   Then apply your customization to the PortalStart in your project.

#. Portal date filter such as TaskCreationDateFilter, CaseCreationDateFilter... messages ``<p:messages for="..." />`` have been added for each calendar component to validate date format.
   If you use have any customized date filters in your project, update template accordingly.

Migrate 9.1 to 9.2
------------------

#. Deploy :download:`MigrateData.iar <documents/MigrateData.iar>` project to your Ivy application and run it by access link
   ``your_host/your_application/pro/MigrateData/175F92F71BC45295/startMigrateConfiguration.ivp``
   
   If you have many applications, deploy to only one application and run it by access link
   ``your_host/your_application/pro/MigrateData/175F92F71BC45295/startMigrateConfiguration.ivp``
   
   Example: ``https://portal.io/Portal/pro/MigrateData/175F92F71BC45295/startMigrateConfiguration.ivp``

   .. important:: Run migration process only once

#. We remove implementation of Portal multiple applications. So that you need to adapt some points below:

   - Adapt start process signature of ``PasswordService`` in ``ChangePassword.mod`` if you overrode this callable.
   - If you are using ``ProcessStartCollector``, replace constructor ``ProcessStartCollector(application)`` with ``ProcessStartCollector()``.
   - If you have TaskLazyDataModel, CaseLazyDataModel customization, remove ``setInvolvedApplications()`` method, ``setInvolvedUsername`` in search criteria.   

#. In PortalNavigatorInFrame.java, change the methods from non-static to static.

#. CaseDetails component in PortalTemplate is removed.

#. If you have TaskLazyDataModel, CaseLazyDataModel customization, follow :ref:`How to override export feature of Task list <customization-task-widget-how-to-override-export-feature>` and :ref:`How to override export feature of Case list <customization-case-widget-how-to-override-export-feature>` to customize label and value of custom columns that will be exported.

#. Deprecated callable processes: ``OpenPortalSearch.mod``, ``OpenPortalTasks.mod``, ``OpenPortalTaskDetails.mod``, ``OpenPortalCases.mod``, ``OpenPortalCaseDetails.mod`` process.

   Portal recommends using :dev-url:`Axon Ivy HtmlOverride wizard </doc/nightly/designer-guide/how-to/overrides.html?#override-new-wizard>` to customize ``Portal HTML Dialog``

   .. important:: The callable process which is supporting to open customization dialog will be removed in the future, do not use it in the new project

#. We remove ivy-icon.css and replace current classes with new classes from `Streamline icons <https://dev.demo.ivyteam.io/demo-app/faces/view/html-dialog-demos$1/icons.xhtml>`_. So that you need to update your files that are using classes in ivy-icon.css.

#. If you have taskItemDetailCustomPanelTop, taskItemDetailCustomPanelBottom customization, follow :ref:`How to override TaskItemDetail <customization-task-item-details>` to add custom widgets.

9. If you have ``caseItemDetailCustomTop`` or ``caseItemDetailCustomMiddle`` or ``caseItemDetailCustomBottom`` customization, follow :ref:`How to override CaseItemDetail <customization-case-item-details>` to add these custom widgets.

Migrate 8.x to 9.1
------------------

#. Remove the ``views`` field in SubMenuItem.java. Adapt it if you overrode the ``LoadSubMenuItems`` callable process

#. Add parameter ``<ui:param name="viewName" value="TASK" />`` to your customized ``PortalTasksTemplate`` to displayed breadcrumb of Task list.

#. Add parameter ``<ui:param name="viewName" value="CASE" />`` to your customized ``PortalCasesTemplate`` to displayed breadcrumb of Case list.

#. Ivy core enhanced the Ivy URI, so Portal needs to make a migration. Deploy :download:`PortalUrlMigration.iar <documents/PortalUrlMigration.iar>` project to any Ivy Application then run ``migratePortalUrl.ivp`` once and wait until it is redirected to another page without error (E.g: Homepage). It is recommended to remove it after the migration.

#. HOMEPAGE_URL (single Portal app mode) and registered application link (multi Portal app mode) are not available anymore. To let Portal know about your new Portal home, you have to set default pages to your project, follow this chapter to customize standard processes: :dev-url:`Standard Processes </doc/nightly/designer-guide/user-interface/standard-processes/index.html>`

#. Portal now uses |css_variable| instead of SASS. Therefore you must convert SASS syntax to new CSS variable or use online tool such as |css_variable_convert| to convert it.

#. If administrator activate the ``Portal.Cases.EnableOwner`` Portal settings and there is a customized case list, customize this field to this case list, e.g. add filter, column configuration, header.

#. From 9.1, Ivy engine use new mechanism to synchronize user data, therefore Portal must adapt some data related to users. Some data must be migrated to work properly. Please follow these steps to migrate data of your application:

   - Deploy :download:`MigrateRelatedDataOfUserTo9.iar <documents/MigrateRelatedDataOfUserTo9.iar>` project to your Ivy application.

   - Run ``migratePrivateChat.ivp`` to migrate private chat messages.

   - Run ``migrateGroupChat.ivp`` to migrate group chat.

   - Run ``migrateUserProcessesAndExternalLinks.ivp`` to migrate user processes and external links.

   - Run ``migrateExpressProcesses.ivp`` to migrate Express processes, please skip this step if your application does not have Express.

   - Restart Ivy engine.

#. Use ``pageContent`` to define your section in ``BasicTemplate.xhtml`` instead of ``simplePageContent``.

#. ``TaskTemplate-7`` is removed, change it to ``TaskTemplate-8``. ``TaskTemplate`` is also removed, change it to ``frame-8`` (provided by Ivy).

#. The ``MenuKind`` enum has one more entry: EXTERNAL_LINK, use it if your item is an external link, and use CUSTOM if yours is an internal link.

#. The ``PortalNavigatorInFrameAPI#navigateToPortalHome`` method is deprecated, redirect to ivy.html.applicationHomeRef() in your page instead.

Migrate 8.x to 9.2
------------------

You need to do all steps in ``Migrate 8.x to 9.1`` and ``Migrate 9.1 to 9.2``

.. _installation-release-notes:

Release notes
=============

This part lists all relevant changes since the last official product
releases of Axon Ivy.

Changes in 9.4
--------------

- Introduce the ``Portal.Tasks.BehaviourWhenClickingOnLineInTaskList`` Portal setting to set behaviour when
  clicking on a line in task list, task widget in new dashboard and related tasks in case details, each user can change it via user profile. 

- Introduce new ``Task ID``, ``Task Name``, ``Case ID`` and ``Case Name`` filter in the Portal full task list and case list.

- Introduce the ``Process Viewer`` page, user can get the visual viewer of the process start. See more details :ref:`Show Process Viewer <how-to-show-process-viewer>`

Changes in 9.3
--------------

- No need to update PortalGroupId variable when you change group id of Portal.


Changes in 9.2
--------------

- Include new TaskState such as ``Destroyed``, ``Failed``, ``Join failed`` and ``Waiting for event`` in Portal Task list, also in Task State filter.

- Include new CaseState ``Destroyed`` in Portal Case list, also in Case State filter.

- Introduce :ref:`Workflow Events table <how-to-show-workflow-events>`, user who has permission ``WORKFLOW_EVENT_READ_ALL`` can see all ``WORKFLOW_EVENTS``.

- Introduce the ``Portal.Homepage`` Portal setting to set the default homepage, each user can change it via user profile. 

- Introduce new approach to customize :ref:`Portal Case Item details <customization-case-item-details>`. Now, your case information in Case details page and Case Info dialog is the same

- Introduce new approach to customize :ref:`Portal Task item details <customization-task-item-details>`.

- Introduce new Portal Setting ``Portal.ShowButtonIcon`` to control visibility of icon of button in Portal.

- Introduce new global variable named ``PortalLoginPageDisplay`` to show Login page or hide it then show error page instead.

- No multiple applications anymore, Portal now only works in current application. It means administrator can not add new Ivy application.

- Statistic charts support multiple names for each supported languages.

- Portal supports multilingual user favorites

- Portal supports logos in SVG format.

Changes in 9.1
--------------

- Refactor style customization approach. From now on, Portal use CSS Variable as technology to customize CSS.

- Introduce the User Guide feature, using the ``Portal.Dashboard.ShowUserGuide`` Portal Setting to activate/deactivate it, 
  and follow :ref:`Customize user guide <customization-portal-home-user-guide>` for your customization.

- Introduce new Portal Setting ``Portal.ShowButtonIcon`` to control visibility of icon of button in Portal.

- Introduce new Portal dialog with icon decorator. Refer to :ref:`this section <components-additional-portal-dialog-with-icon>` for more detail.

- TaskTemplate-7, TaskTemplate and TwoColumnTemplate are removed.


.. |css_variable| raw:: html

   <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties" target="_blank">CSS Variable</a>
.. |css_variable_convert| raw:: html

   <a href="https://www.npmjs.com/package/sass-to-css-variables" target="_blank">SASS to CSS Variables</a>