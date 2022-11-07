.. _installation:

Installation
************

The Installation section describes all steps necessary to install and configure
the Portal. If you install your application the first time, then it is important
to start with the Basic installation. This section describes all initial steps
that must be done for the first installation. If the application is already
installed and configured, refer to `Migration notes`_ to migrate it.

Basic Installation
==================

Project Modules
---------------

The application consists of 3 process modules. For detailed information
on each module, refer to :ref:`architecture`.

-  portal-components
-  portal
-  AxonIvyExpress

The deployment of Ivy projects is described in :dev-url:`project
deployment </doc/10.0.1/engine-guide/deployment/index.html>`
.

Installation
------------

Designer
^^^^^^^^

Import Portal modules to Designer.


Engine Without License (Demo Mode)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The engine automatically deploys the Portal application with the following set
of default users:

.. table::

   +-----------------------+-----------------------+-----------------------+
   | Username              | Password              | Description           |
   +=======================+=======================+=======================+
   | admin                 | admin                 | This user has all     |
   |                       |                       | Portal permissions,   |
   |                       |                       | can access Portal     |
   |                       |                       | Admin Settings.       |
   +-----------------------+-----------------------+-----------------------+
   | demo                  | demo                  | This user has         |
   |                       |                       | permission to manage  |
   |                       |                       | user absences.        |
   +-----------------------+-----------------------+-----------------------+
   | guest                 | guest                 | Default standard user |
   |                       |                       | of the portal.        |
   +-----------------------+-----------------------+-----------------------+


Engine With License (Production Mode)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The engine does not deploy anything, you need to deploy and configure the Portal
application manually.


.. _installation-migration-notes:

Migration Notes
===============

This document informs you in detail about incompatibilities that were
introduced between Portal versions and tells you what needs to be done
to make your existing Portal working with current Axon Ivy engine.

How To Migrate
--------------

.. important::
   If you call any Portal API which is not mentioned in the API documentation,
   it may have changed or have been removed. Don't forget to re-implement the
   APIs concerned in your own project.

   To migrate the Portal, you need to migrate Axon Ivy first. Refer to the
   :dev-url:`Axon Ivy Migration Notes
   </doc/10.0.1/axonivy/migration/index.html>`. Changes in Axon Ivy could lead
   to problems if a customer project is not migrated properly.

In Designer
-----------

#. Replace all Portal projects with the versions of the new release.
#. Update portal dependency of the customer project in pom.xml.
#. Follow detailed migration notes for each version below.
#. If customization needs copying code from Portal, merge changes between the
   two versions of the Portal for copied code.

..

In Engine
---------

#. Convert database schema if needed.

#. Redeploy Portal projects and customer project.

#. Follow detailed migration notes for each version below.

Migrate 9.3 To 9.4
------------------

``PortalStyle``, ``PortalKit`` and ``PortalTemplate`` have been replaced by ``portal-components`` and ``portal`` from 9.4, refer to :ref:`architecture`.

#. If you have customized PortalStyle, please refer to
   :ref:`Customization Portal Logos And Colors <customization-portal-logos-and-colors>` to override login background, favicon & logo images.
   If you have changed the CMS in ``PortalStyle``, please adapt the ``portal`` CMS accordingly.

#. If you configured Process widgets in your own dashboards as described in :ref:`configure-new-dashboard-proces-widget`,
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

#. The ``customization.css`` file has been removed, in case you use it in your project, please switch to using
   :dev-url:`Engine Branding </doc/10.0.1/designer-guide/user-interface/branding/branding-engine.html>` to customize styling

#. Sub processes related to documents are moved to the independent project ``portal-components``.
   If you customized these processes, please override the correspond sub process again and added your customization to it.

   Below is a list of deprecated processes in project ``portal`` and new processes in project ``portal-components``.

   +-----------------------------------+--------------------------+
   | New subprocess                    | Deprecated subprocess    |
   +===================================+==========================+
   | GetDocumentItems                  | GetDocumentList          |
   +-----------------------------------+--------------------------+
   | UploadDocumentItem                | UploadDocument           |
   +-----------------------------------+--------------------------+
   | DeleteDocumentItem                | DeleteDocument           |
   +-----------------------------------+--------------------------+
   | DownloadDocumentItem              | DownloadDocument         |
   +-----------------------------------+--------------------------+

#. Some classes of the Portal have been moved to the independent project ``portal-components``. Please refer to below table to migrate them correctly

   .. csv-table::
      :file: documents/class_replacement_9.4.csv
      :header-rows: 1
      :class: longtable
      :widths: 1 1

#. Some components of the Portal have been moved to the independent project ``portal-components``. Please follow these steps to migrate them:

   - :ref:`Migration steps <components-portal-components-migrate-from-old-user-selection>` for the new :ref:`User Selection <components-portal-components-user-selection>` component.

   - :ref:`Migration steps <components-portal-components-migrate-from-old-role-selection>` for the new :ref:`Role Selection <components-portal-components-role-selection>` component.

   - :ref:`Migration steps <components-portal-components-migrate-from-old-document-table>` for the new :ref:`Document Table <components-portal-components-document-table>` component.

   - :ref:`Migration steps <components-portal-components-migrate-from-old-process-history>` for the new :ref:`Process History <components-portal-components-process-history>` component.

   - :ref:`Migration steps <components-portal-components-migrate-from-old-process-chain>` for the new :ref:`Process Chain <components-portal-components-process-chain>` component.

#. Portal dashboard widgets only support the ``CustomFields`` declared in the ``custom-fields.yaml`` file.
   If your ``CustomFields`` are used in the dashboard widget, please follow the :dev-url:`Custom Fields Meta Information </doc/10.0.1/designer-guide/how-to/workflow/custom-fields.html#meta-information>` to adapt the data.

#. The ``DefaultChartColor.p.json`` sub process has been removed, in case you use it in your project, please remove override this sub process and switch to using
   :dev-url:`Engine Branding </doc/10.0.1/designer-guide/user-interface/branding/branding-engine.html>` to customize chart, data labels, legend color.
   Refer to :ref:`Default chart colors <customization-default-chart-colors>`.

#. Deploy :download:`portal-migration-9.4.0.iar <documents/portal-migration-9.4-9.4.0.iar>` project to your Ivy application and run it by access link
   ``your_host/your_application/pro/portal-migration/175F92F71BC45295/startMigrateConfiguration.ivp``

   .. important::
      * If you have many applications, deploy to only one application and run it by access the migration link,
        for example: ``https://portal.io/Portal/pro/portal-migration/175F92F71BC45295/startMigrateConfiguration.ivp``

      * Use an administrator account to sign in
      * Run migration process only once
      * You must remove some process models: ``portal-migration``, ``PortalStyle``, ``PortalKit`` and ``PortalTemplate`` after successfully migrating.

Migrate 9.2 To 9.3
------------------

#. Deploy :download:`portal-migration.iar <documents/portal-migration-9.3.0.iar>` project to your Ivy application and run it by access link
   ``your_host/your_application/pro/portal-migration/175F92F71BC45295/startMigrateConfiguration.ivp``

   .. important::
      * If you have many applications, deploy to only one application and run it by access the migration link,
        for example: ``https://portal.io/Portal/pro/portal-migration/175F92F71BC45295/startMigrateConfiguration.ivp``

      * Use an administrator account to sign in
      * Run migration process only once

#. We changed the way to navigate to Task Analysis component. Process ``Start Processes/TaskAnalysis/start.ivp`` is moved to new place ``Start Processes/PortalStart/showTaskAnalysis.ivp``.
   Refer to :ref:`Task Analysis call<components-additional-component-task-analysis-how-to-use>` for details.

#. We moved the configuration of announcement, thirdparty applications, default statistic charts, application favorite processes, public external links and express processes from the BusinessData tovariables.

#. Copy the PortalStart process from PortalTemplate to your project because we changed something relate to DefaultApplicationHomePage.ivp and PortalDashboardConfiguration.ivp.
   Then apply your customization to the PortalStart in your project.

#. Portal date filter such as TaskCreationDateFilter, CaseCreationDateFilter... messages ``<p:messages for="..." />`` have been added for each calendar component to validate date format.
   If you use have any customized date filters in your project, update template accordingly.

#. The callable process ``DefaultChart.p.json``, ``DefaultUserProcess.p.json`` has been removed. They are replaced by
   the :dev-url:`Variables </doc/10.0.1/designer-guide/configuration/variables.html>` configuration approach,
   refer to :ref:`Default Chart <customization-default-chart>` and :ref:`Default User Process <customization-default-user-process>` for more information

Migrate 9.1 To 9.2
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

   Portal recommends using :dev-url:`Axon Ivy HtmlOverride wizard </doc/10.0.1/designer-guide/how-to/overrides.html?#override-new-wizard>` to customize ``Portal HTML Dialog``

   .. important:: The callable process which is supporting to open customization dialog will be removed in the future, do not use it in the new project

#. We remove ivy-icon.css and replace current classes with new classes from `Streamline icons <https://dev.demo.ivyteam.io/demo-app/faces/view/html-dialog-demos$1/icons.xhtml>`_. So that you need to update your files that are using classes in ivy-icon.css.

#. If you have taskItemDetailCustomPanelTop, taskItemDetailCustomPanelBottom customization, follow :ref:`How to override TaskItemDetail <customization-task-item-details>` to add custom widgets.

9. If you have ``caseItemDetailCustomTop`` or ``caseItemDetailCustomMiddle`` or ``caseItemDetailCustomBottom`` customization, follow :ref:`How to override CaseItemDetail <customization-case-item-details>` to add these custom widgets.

Migrate 8.x To 9.1
------------------

#. Remove the ``views`` field in SubMenuItem.java. Adapt it if you overrode the ``LoadSubMenuItems`` callable process

#. Add parameter ``<ui:param name="viewName" value="TASK" />`` to your customized ``PortalTasksTemplate`` to displayed breadcrumb of Task list.

#. Add parameter ``<ui:param name="viewName" value="CASE" />`` to your customized ``PortalCasesTemplate`` to displayed breadcrumb of Case list.

#. Ivy core has enhanced the Ivy URI, so Portal needs to make a migration. For
   each of your applications, execute the following steps:

   #. Deploy process model :download:`PortalUrlMigration.iar <documents/PortalUrlMigration.iar>`
      to your Ivy Application.

   #. run ``migratePortalUrl.ivp`` once and wait until it is redirected to
      another page (i.e. the Homepage) without error.

   #. Remove the process model ``migratePortalUrl.ivp`` after successfully migrating.

#. HOMEPAGE_URL (single Portal app mode) and registered application link (multi
   Portal app mode) are not available anymore. To let Portal know where your new
   Portal home page is, you have to set default pages in your project.
   Follow this chapter to customize default-pages:
   :dev-url:`Default Pages </doc/10.0.1/designer-guide/user-interface/default-pages/index.html>`

#. Portal now uses |css_variable| instead of SASS. Therefore, you have to convert
   the SASS syntax to the new CSS variables or use online tools such as
   |css_variable_convert| to convert it.

#. If the Engine Administrator activates the ``Portal.Cases.EnableOwner``
   setting and you have a customized case list, customize this field to this
   case list, e.g. add filter, column configuration, header.

#. Starting in 9.1, the Ivy engine uses a new mechanism to synchronize user
   data. Therefore, the Portal has to adapt some data related to users. Some
   data has to be migrated to work properly. Please follow these steps to
   migrate the existing data of your application:

   - Deploy process model :download:`MigrateRelatedDataOfUserTo9.iar
     <documents/MigrateRelatedDataOfUserTo9.iar>` to your application.

   - Run ``migratePrivateChat.ivp`` to migrate private chat messages.

   - Run ``migrateGroupChat.ivp`` to migrate group chat.

   - Run ``migrateUserProcessesAndExternalLinks.ivp`` to migrate user processes
     and external links.

   - Run ``migrateExpressProcesses.ivp`` to migrate Express processes. Please
     skip this step if your application does not include Express.

   - Restart Ivy engine.

#. Use ``pageContent`` to define your section in ``BasicTemplate.xhtml`` instead of ``simplePageContent``.

#. ``TaskTemplate-7`` has been removed, change it to ``TaskTemplate-8``. ``TaskTemplate`` has been removed, too, change it to ``frame-8`` (provided by Ivy).

#. The ``MenuKind`` enum has one more entry: EXTERNAL_LINK. Use it if your item
   is an external link. Use CUSTOM if it is an internal link.

#. The ``PortalNavigatorInFrameAPI#navigateToPortalHome`` method is deprecated,
   redirect to ivy.html.applicationHomeRef() in your page instead.

Migrate 8.x To 9.x
------------------

You need to do all steps starting at ``Migrate 8.x To ...`` up to and including
``Migrate ... To 9.x``

.. _installation-release-notes:

Release notes
=============

This part lists all relevant changes since the last official product
releases of Axon Ivy.

Changes in 10
-------------

- Introduced the ``Application`` filter and the ``Application`` column at the following places: full task list, full case list, dashboard task list, dashboard case list, and task analysis.


Changes in 9.4
--------------

- Combined projects ``PortalStyle``, ``PortalKit``, and ``PortalTemplate`` to one project named ``portal``.

- Introduced the ``Portal.Tasks.BehaviourWhenClickingOnLineInTaskList`` Portal setting to set behaviour when
  clicking on a line in task list, task widget in new dashboard and related tasks in case details, each user can change it via user profile.

- Introduced the ``Portal.StatisticChartScalingInterval`` Portal setting to set the interval in seconds to do periodic statistic chart scaling requests.

- Introduced the ``Portal.LoginPage.ShowFooter`` Portal setting to control visibility of the footer on the login page.

- Introduced the ``Portal.Theme.Mode`` Portal setting to set the default theme mode: Light or Dark.

- Introduced the ``Portal.Theme.EnableSwitchThemeModeButton`` Portal setting to control state of the switch theme button on the top-bar.

- Introduced new ``Task ID``, ``Task Name``, ``Case ID`` and ``Case Name`` filter in the Portal full task list and case list.

- Introduced the ``Process Viewer`` page, user can get the visual viewer of the process start. See details :ref:`Show Process Viewer <how-to-show-process-viewer>`

- Introduced the ``Formatting language setting`` to format values, for example the decimal separator is displayed differently in different regions of the world.

- Removed sub process ``DefaultChartColor.p.json``, introduced some Portal variables for customizing the default chart color. See details: :ref:`Default chart colors <customization-default-chart-colors>`.

- Introduce some components in new ``portal-components`` project.

   - :ref:`User Selection Component <components-portal-components-user-selection>`

   - :ref:`Role Selection Component <components-portal-components-role-selection>`

   - :ref:`Document Table Component <components-portal-components-document-table>`

   - :ref:`Process History Component <components-portal-components-process-history>`

   - :ref:`Process Chain Component <components-portal-components-process-chain>`

   - :ref:`Process Viewer Component <components-portal-components-process-viewer>`

Changes in 9.3
--------------

- No need to update PortalGroupId variable when you change group id of Portal.


Changes in 9.2
--------------

- Included new TaskState such as ``Destroyed``, ``Failed``, ``Join failed`` and ``Waiting for event`` in Portal Task list, also in Task State filter.

- Included new CaseState ``Destroyed`` in Portal Case list, also in Case State filter.

- Introduced :ref:`Workflow Events table <how-to-show-workflow-events>`, user who has permission ``WORKFLOW_EVENT_READ_ALL`` can see all ``WORKFLOW_EVENTS``.

- Introduced the ``Portal.Homepage`` Portal setting to set the default homepage, each user can change it via user profile.

- Introduced new approach to customize :ref:`Portal Case Item details <customization-case-item-details>`. Now, your case information in Case details page and Case Info dialog is the same

- Introduced new approach to customize :ref:`Portal Task item details <customization-task-item-details>`.

- Introduced new Portal Setting ``Portal.ShowButtonIcon`` to control visibility of icon of button in Portal.

- Introduced new variable named ``PortalLoginPageDisplay`` to show Login page or hide it then show error page instead.

- No multiple applications anymore, Portal now only works in current application. It means administrator can not add new Ivy application.

- Statistic charts support multiple names for each supported languages.

- Portal supports multilingual user favorites

- Portal supports logos in SVG format.

Changes in 9.1
--------------

- Refactored style customization approach. From now on, Portal use CSS Variable as technology to customize CSS.

- Introduced the User Guide feature, using the ``Portal.Dashboard.ShowUserGuide`` Portal Setting to activate/deactivate it,
  and follow :ref:`Customize user guide <customization-portal-home-user-guide>` for your customization.

- Introduced new Portal Setting ``Portal.ShowButtonIcon`` to control visibility of icon of button in Portal.

- Introduced new Portal dialog with icon decorator. Refer to :ref:`this section <components-additional-portal-dialog-with-icon>` for detail.

- TaskTemplate-7, TaskTemplate and TwoColumnTemplate have been removed.


.. |css_variable| raw:: html

   <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties" target="_blank">CSS Variable</a>

.. |css_variable_convert| raw:: html

   <a href="https://www.npmjs.com/package/sass-to-css-variables" target="_blank">SASS to CSS Variables</a>