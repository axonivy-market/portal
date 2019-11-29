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

.. _installation-basic-portal-mode:

Portal mode
-----------

-  **Single application mode**  : One Portal application on one engine. It must include PortalKit,
   PortalTemplate and PortalStyle modules.

-  **Multi applications mode** : Multiple Portal applications on one engine.
   Each Portal application must include PortalKit, PortalTemplate and PortalStyle modules.

.. important::
   In multi applications mode, if you need overall dashboard, create
   an application named **Portal** with the Portal modules.

Installation
------------

Designer
^^^^^^^^

Import Portal modules to Designer.

.. important:: Designer only supports single application mode.


Engine without license (demo mode)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The engine automatically deploys the Portal application with default users. You do
not need to deploy anything (Single application mode).

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

Single application mode
"""""""""""""""""""""""

Create an application and deploy Portal modules.

.. important::

   Only one active application contains Portal modules.

Multi applications mode
"""""""""""""""""""""""

Refer :ref:`settings-admin-settings-setup-multi-portals`.

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
2. If your ivy version is before 7.3.0 : deactivate standard Portal
   application if it's not needed.
3. Redeploy Portal projects (exclude PortalConnector) and customer
   project.
4. Follow detailed migration notes for each version below.

.. _installation-migration-notes-8-0-0:

Migrate to 8.0.0
----------------


From Portal version 8.0.0, we use the Serenity theme as the default
style for project. If your project is using ``LESS`` languages for
customizing style, you have to convert them to new format ``SASS``. 

How to convert `LESS <http://lesscss.org>`__ to `SASS <https://sass-lang.com/>`__ languages
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
If ``customization.less`` file is used for customizing Portal colors with variables and not 
using other ``LESS`` syntax, you do not need to convert by tool, just change file type from ``*.less``
to ``*.scss`` manually and jump to :ref:`Put scss files to specific folder <put-scss-files-to-specific-folder>` step.

Please follow below step to do automation step convert your less file by
plugin ``less2sass.``

The **less2sass** converter is pretty good and maintained
https://www.npmjs.com/package/less2sass.

-  Install
   NodeJS on your system (can get by this page  https://nodejs.org/en/download). Once installed, restart your
   system as well.
-  Open Cmd command line and run command:

   .. code-block:: bash

      npm install -g less2sass

-  Once installed you can simply run:

   .. code-block:: bash
   
      less2sass 'path_to_less_file_or_directory'

   E.g for path_to_less_file_or_directory:

   ``C:\Projects\Portal\axonivyportal\AxonIvyPortal\PortalStyle\webContent\resources\less\customization.less``

   |less-2-sass|

   After run above command, you also see a new
   \*.scss file is created into same folder.
   ``C:\Projects\Portal\axonivyportal\AxonIvyPortal\PortalStyle\webContent\resources\less\customization.scss``

.. _put-scss-files-to-specific-folder:

-   Put ``*.scss`` files to new folder as ``..\webContent\resources\sass\ivy``
-  Update customized variables manually:

.. table:: 

   +-----------------------------------------+-----------------------------------------+
   | Old version                             | New version                             |
   +=========================================+=========================================+
   | @body-background-color                  | $bodyBackgroundColor                    |
   +-----------------------------------------+-----------------------------------------+
   | @announcement-background-color          | $announcementBackgroundColor            |
   +-----------------------------------------+-----------------------------------------+
   | @announcement-border-color              | $announcementBorderColor                |
   +-----------------------------------------+-----------------------------------------+
   | @action-button-border-radius            | $actionButtonBorderRadius               |
   +-----------------------------------------+-----------------------------------------+
   | @action-button-color                    | $primaryColor (default from theme)      |
   +-----------------------------------------+-----------------------------------------+
   | @cancel-button-background-color         | $cancelButtonBackgroundColor            |
   +-----------------------------------------+-----------------------------------------+
   | @context-button-color                   | $contextButtonBackgroundColor           |
   +-----------------------------------------+-----------------------------------------+
   | @context-button-border-radius           | $contextButtonBorderRadius              |
   +-----------------------------------------+-----------------------------------------+
   | @first-header-bar-color                 | $firstHeaderBarColor                    |
   +-----------------------------------------+-----------------------------------------+
   | @second-header-bar-color                | $secondHeaderBarColor                   |
   +-----------------------------------------+-----------------------------------------+
   | @third-header-bar-color                 | $thirdHeaderBarColor                    |
   +-----------------------------------------+-----------------------------------------+
   | @login-logo-height                      | $loginLogoHeight                        |
   +-----------------------------------------+-----------------------------------------+
   | @home-logo-height                       | $homeLogoHeight                         |
   +-----------------------------------------+-----------------------------------------+
   | @task-priority-low-color                | $taskPriorityLowColor                   |
   +-----------------------------------------+-----------------------------------------+
   | @task-priority-normal-color             | $taskPriorityNormalColor                |
   +-----------------------------------------+-----------------------------------------+
   | @task-priority-high-color               | $taskPriorityHighColor                  |
   +-----------------------------------------+-----------------------------------------+
   | @task-priority-exception-color          | $taskPriorityExceptionColor             |
   +-----------------------------------------+-----------------------------------------+
   | @task-state-open-color                  | $taskStateOpenColor                     |
   +-----------------------------------------+-----------------------------------------+
   | @task-state-in-progress-color           | $taskStateInProgressColor               |
   +-----------------------------------------+-----------------------------------------+
   | @task-state-done-color                  | $taskStateDoneColor                     |
   +-----------------------------------------+-----------------------------------------+
   | @task-state-zombie-destroyed-color      | $taskStateZombieDestroyedColor          |
   +-----------------------------------------+-----------------------------------------+
   | @task-state-reserved-color              | $taskStateReservedColor                 |
   +-----------------------------------------+-----------------------------------------+
   | @task-state-system-color                | $taskStateSystemColor                   |
   +-----------------------------------------+-----------------------------------------+
   | @process-chain-menu-color               | $processChainMenuColor                  |
   +-----------------------------------------+-----------------------------------------+
   | @menu-color                             | Removed                                 |
   +-----------------------------------------+-----------------------------------------+
   | @sidebar-opacity-transition-duration    | Removed                                 |
   +-----------------------------------------+-----------------------------------------+
   | @sidebar-dimension-transition-duration  | Removed                                 |
   +-----------------------------------------+-----------------------------------------+

..

-  Run ``mvn libsass:compile`` to compile your ``scss`` to ``css`` file.

.. _installation-migration-notes-8-0-0-task-template:



How to migrate TaskTemplate
^^^^^^^^^^^^^^^^^^^^^^^^^^^

Since Portal 8, we introduce iFrame so that customer project can be loaded inside iFrame. It can reduce side affect between customer project and Portal as well as migrate effort in future.

Portal introduced 4 scenarios for migration, choose suitable one:

IFrame mode: 

+----------------------------------------------+------------------------------------------------------+
| TaskTemplate.xhtml - Modena ivy theme        | Your own template                                    |
+==============================================+======================================================+
| Pros:                                        | Pros:                                                |
|                                              |                                                      |
|  - Keep the old look&feel since Portal 7     |  - Portal and project styles are independent         |
|  - Less migration effort                     |  - Less migration effort in future                   |
|                                              |                                                      |
| Cons:                                        | Cons:                                                |
|                                              |                                                      |
|  - Modena is out of support by Primefaces    |  - Highest migration effort expected for old projects|
|  - Modena was highly customized by Portal    |                                                      |
|  - Different look&feel between Portal and UIs|                                                      |
+----------------------------------------------+------------------------------------------------------+

No-IFrame mode:

+------------------------------------------------+-------------------------------------------------------+
| DeprecatedTaskTemplate-7, Serenity theme       | TaskTemplate-8, Serenity theme                        |
+================================================+=======================================================+
| Pros:                                          | Pros:                                                 |
|                                                |                                                       |
|  - Keep the old structure for UIs (TabView)    |  - Portal and project styles are independent          |
|  - Use Serenity theme from Portal              |  - Less migration effort in future                    |
|  - Consistent look&feel with Portal            |  - Less migration effort in future                    |
|                                                |                                                       |
| Cons:                                          | Cons:                                                 |
|                                                |                                                       |
|  - Template is deprecated (will remove in v9.0 |  - Some migration effort                              |
|  - Some migration effort                       |                                                       |
+------------------------------------------------+-------------------------------------------------------+

- ``TaskTemplate.xhtml``, if you keep using TaskTemplate, your task must be loaded inside iFrame and Modena theme is included. For more details, refer to :ref:`Task template <components-layout-templates-task-template>`.

- ``DeprecatedTaskTemplate-7.xhtml``, change your template to DeprecatedTaskTemplate-7, it will keep your old template UI (including tab view) with Serenity theme. For more details, refer to :ref:`Deprecated task template 7 <components-layout-templates-deprecated-task-template-7>`.

- ``TaskTemplate-8.xhtml``, change your template to TaskTemplate-8, it will include Serenity theme and no tab view predefined. For more details, refer to :ref:`Task template 8 <components-layout-templates-task-template-8>`.

If your project has tag ``<ui:insert name="content">``, rename ``content`` to something else. This specific name may cause error since text ``content`` is already used in Portal template.

.. _installation-migration-notes-8-0-0-basic-template:

How to migrate BasicTemplate
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

There are some changes in BasicTemplate. In the past, if you overwritten BasicTemplate ``footer`` section and copied responsive js in old BasicTemplate,
please copy the latest js in ``footer`` section of BasicTemplate.

.. _installation-migration-notes-8-0-0-task-body:

How to migrate TaskBody to  :ref:`customization-task-item-details` component
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

On Portal version 8.0.0, we removed ``taskBody`` in TaskWidget. Instead
of that, we will use TaskItemDetails component to show task information
with more details and responsiveness.

If you have customized ``taskBody`` of TaskWidget, we need to migrate
the code of ``taskBody`` to new component as ``TaskItemDetails``

Please follow the steps to migrate

-  You can take a look at ``PortalTaskDetails.xhtml`` to see how to use
   and customize ``TaskItemDetails``.

   There are 2 sections we need to take a look:

   -  On the ``taskItemDetailCustomPanelTop`` section.

      This section will be shown on the top ``TaskItemDetails``
      component. You can change the width of this panel as your
      requirement, we recommend to use ``ui-g-*`` class of ``Primeface``
      to define the width for the box and make it flexible.

   -  On the ``taskItemDetailCustomPanelBottom`` section.

      This section will be shown on the bottom of the ``TaskItemDetails``
      component. You can change the width of this panel as your requirement,
      we recommend to use ``ui-g-*`` class of ``Primeface`` to define  
      the width for the box and make it flexible.

   -  After deciding where we will push the custom code to ``TaskItemDetails``.

      Move your customized code for Custom box section from old
      ``taskBody`` under these sections.

      Finally, your customization will be shown in the ``TaskItemDetails``.

   -  For example:

      Old taskBody

      .. code-block:: html

         <ui:define name="taskBody">
         <!-- Reuse some components -->
            <ic:ch.ivy.addon.portalkit.component.TaskItemDescription id="task-description" task="#{task}"
               descriptionComponentToUpdate="#{p:component('task-description')}" />
            <ic:ch.ivy.addon.portalkit.component.TaskItemGeneralInfo id="task-general-info" task="#{task}"
               priorityComponentToUpdate="#{p:component('task-priority')}"
               componentToUpdate="#{p:component('task-details-notes')}" />
            <ic:ch.ivy.addon.portalkit.component.TaskItemNotes id="task-notes" task="#{task}" />
         
         <!-- Add new panel -->
            <div class="task-details-item custom-task-details-panel">
               <p>Custom panel</p>
            </div>
         </ui:define>
      ..

      TaskItemDetail content

      .. code-block:: html

         <!-- In this HTML dialog, we override task list header, task header, task filter, and task body -->
            <!-- To show/hidden any sections of Task detail, you can turn true/false for below parameters -->
            <!-- To show the Header component inside Task details body. By default it's true -->
            <ui:param name="showItemDetailsHeader" value="true" />
            <!-- To show the Notes component inside Task details body. By default it's true -->
            <ui:param name="showItemDetailsNotes" value="true" />
            <!-- To show the Documents component inside Task details body. By default, it's true -->
            <ui:param name="showItemDetailDocuments" value="true" />
            
            <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
            !!!!!!!!!!! TO ADD YOUR CUSTOMIZATION CODE ON THE TASK DETAILS PAGE, WE PROVIDE 2 SECTIONS AS BELOW HELP YOU CAN DO IT !!!!!!!!!!!!
            !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
            <!-- Add a content as a Custom panel for Task Detail on top section -->

               <!-- Add a content as Custom panel for Task Detail on top -->
            <ui:define name="taskItemDetailCustomPanelTop">
               <h:panelGroup styleClass="ui-g-12 ui-sm-12 custom-task-panel">
               <div class="card card-w-title ">
                  <div class="task-detail-section-title u-truncate-text">
                     <h:outputText value="This is custom panel on top section" />
                  </div>
                  <div class="Separator" />

                  <div class="custom-task-details-panel-top">
                     <h1>This is custom content on top</h1>
                     <p>Custom height to auto</p>
                     <p>Custom font size to 1.6rem</p>
                  </div>
               </div>
               </h:panelGroup>
            </ui:define>

               <!-- Add content as Custom panel for Task Detail on bottom-->
            <ui:define name="taskItemDetailCustomPanelBottom">
               <h:panelGroup styleClass="ui-g-12 ui-sm-12 custom-task-panel">
               <div class="card card-w-title #{cc.attrs.customPanelStyleClass}">
                  <div class="task-detail-section-title u-truncate-text">
                     <h:outputText value="This is custom panel bottom section" />
                  </div>
                  <div class="Separator" />

                  <div class="custom-task-details-panel">
                     <h1>This is custom content bottom</h1>
                     <p>Custom height to auto</p>
                     <p>Custom font size to 1.6rem</p>
                  </div>
               </div>
               </h:panelGroup>
            </ui:define>
      ..

-  In case we need to hide Notes, Documents, we can refer to
   :ref:`Show/hide component on Task Item Details
   <customization-task-item-details-how-to-overide-ui-show-hidden-ui>` 

-  Additionally, if we want to customize more ``TaskItemDetails``
   components, please refer to :ref:`TaskItemDetails component <customization-task-item-details-how-to-overide-ui>` 

.. _installation-migration-notes-8-0-0-case-body:

How to migrate CaseBody to :ref:`customization-case-item-details`  component
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

On Portal version 8.0.0, we removed ``caseBody`` in CaseWidget. Instead
of that, we are using CaseItemDetails component for showing case
information with more details and responsiveness.

If you have customized ``caseBody`` of CaseWidget, we need to migrate
the code of ``caseBody`` to new component as ``CaseItemDetails``

Please follow below check list to migrate

-  You can take a look at ``PortalCaseDetails.xhtml`` to see how to use
   and customize ``CaseItemDetails``.

   There are 3 sections we need to take a look:

   -  On the ``caseItemDetailCustomTop`` section.

      This section will be shown on the top of the ``CaseItemDetails``
      component. You can define the width of this panel as you like, we recommend to use ``ui-g-*`` class of ``Primeface``
      to define size the width of the box and make it flexible.

   -  On the ``caseItemDetailCustomMiddle`` section.

      This section will be shown on the middle of the
      ``CaseItemDetails`` component. You can define the width of this
      panel as you like, we recommend to use ``ui-g-*`` class of
      ``Primeface`` to define the width of the box and make it flexible.

   -  On the ``caseItemDetailCustomBottom`` section.

      This section will be shown on the bottom of the
      ``CaseItemDetails`` component. You can define the width of this
      panel as you like, we recommend to use ``ui-g-*`` class of
      ``Primeface`` to define the width of the box and make it flexible.

   -  After deciding where we will push the custom code to
      ``CaseItemDetails``.

      Move your customized code for Custom box section from old
      ``caseBody`` to below that sections.

      Finally, your customization will be shown in ``CaseItemDetails``.

   -  For example:

      Old caseBody

      .. code-block:: html

         <ui:define name="caseBody">
            <ic:ch.ivy.addon.portalkit.component.CaseItemGeneralInformation id="general-information" case="#{case}" />
            <ic:ch.ivy.addon.portalkit.component.CaseItemRelatedTask id="related-tasks" case="#{case}" />
            <ic:ch.ivy.addon.portalkit.component.CaseItemHistory id="history" case="#{case}" />
            <ic:ch.ivy.addon.portalkit.component.CaseItemDocument id="document" case="#{case}" componentToUpdate="#{p:component('history')}" />
            <ic:ch.ivy.addon.portalkit.component.CaseItemDescription id="description" case="#{case}" descriptionComponentToUpdate="#{p:component('description-cell')}" />
            <ic:ch.ivy.addon.portalkit.component.ResponsivenessHandleContainer styleClass="hidden-lg">
               <ic:ch.ivy.addon.portalkit.component.ResponsivenessHandleButton icon="fa fa-share-alt js-related-task-column-responsive-button" displayedSelectors="['.js-related-task-column']"
                  hiddenSelectorsInSmallScreen="['.case-details .replaced']" />
               <ic:ch.ivy.addon.portalkit.component.ResponsivenessHandleButton icon="fa fa-align-left js-history-column-responsive-button" displayedSelectors="['.js-history-column']"
                  hiddenSelectorsInMediumScreen="['.case-details .replaced']" hiddenSelectorsInSmallScreen="['.case-details .replaced']" />
               <ic:ch.ivy.addon.portalkit.component.ResponsivenessHandleButton icon="fa fa-file js-document-column-responsive-button" displayedSelectors="['.js-document-column']"
                  hiddenSelectorsInLargeScreen="['.case-details .replaced']" hiddenSelectorsInMediumScreen="['.case-details .replaced']" hiddenSelectorsInSmallScreen="['.case-details .replaced']" />
               <ic:ch.ivy.addon.portalkit.component.ResponsivenessHandleButton icon="fa fa-clipboard js-description-column-responsive-button" displayedSelectors="['.js-description-column']"
                  hiddenSelectorsInMediumScreen="['.case-details .replaced']" hiddenSelectorsInSmallScreen="['.case-details .replaced']" />
               <h:outputScript library="js" name="case-detail-default-responsiveness.js" />
            </ic:ch.ivy.addon.portalkit.component.ResponsivenessHandleContainer>

            <!-- Add new panel -->
            <div class="task-details-item custom-task-details-panel">
               <p>Custom panel</p>
            </div>
         </ui:define>
         
      ..

      CaseItemDetail content

      .. code-block:: html
            
         <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
         !!!!!!!!!!!!!!!!!!!!!! TO SHOW /HIDDEN ANY SECTIONS OF CASE DETAILS, YOU CAN TURN TRUE/FALSE FOR BELOW PARAMETERS !!!!!!!!!!!!!!!!!
         !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
         <!-- To show the header of case details. By default it's true -->
         <ui:param name="showItemDetailsHeader" value="true" />
         <!-- To show the Histories component inside Case details body. By default it's true -->
         <ui:param name="showItemDetailsHistories" value="true" />
         <!-- To show the Documents component inside Case details body. By default, it's true -->
         <ui:param name="showItemDetailDocuments" value="true" />
         <!-- To show the RelatedTask component inside Case details. By default, it's true -->
         <ui:param name="showItemDetailRelated" value="true" />

         <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
         !!!!!!!!!!! TO ADD YOUR CUSTOMIZATION CODE ON THE CASE DETAILS PAGE, WE PROVIDE 3 SECTIONS AS BELOW HELP YOU CAN DO IT !!!!!!!!!!!!
         !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
         <!-- Add a content as a Custom panel for Case Detail on top section -->
         <ui:define name="caseItemDetailCustomTop">
            <h:panelGroup styleClass="ui-g-12" layout="block">
            <div class="card card-w-title case-detail-card">
               <div class="case-detail-section-title u-truncate-text">
                  <h:outputText value="This is custom panel on top section" />
               </div>
               <div class="Separator" />

               <div class="custom-task-details-panel-top">
                  <h1>This is custom content on top</h1>
                  <p>Custom height to auto</p>
                  <p>Custom font size to 1.6rem</p>
               </div>
            </div>
            </h:panelGroup>
         </ui:define>

         <!-- Add a content as a Custom panel for Case Detail on middle section, below the General & description box -->
         <ui:define name="caseItemDetailCustomMiddle">
            <h:panelGroup styleClass="ui-g-12" layout="block">
            <div class="card card-w-title case-detail-card">
               <div class="case-detail-section-title u-truncate-text">
                  <h:outputText value="This is custom panel on middle section" />
               </div>
               <div class="Separator" />

               <div class="custom-task-details-panel-middle">
                  <h1>This is custom content on middle</h1>
                  <p>Custom height to auto</p>
                  <p>Custom font size to 1.6rem</p>
               </div>
            </div>
            </h:panelGroup>
         </ui:define>

         <!-- Add a content as a Custom panel for Case Detail on bottom section -->
         <ui:define name="caseItemDetailCustomBottom">
            <h:panelGroup styleClass="ui-g-12" layout="block">
            <div class="card card-w-title case-detail-card">
               <div class="case-detail-section-title u-truncate-text">
                  <h:outputText value="This is custom panel on bottom section" />
               </div>
               <div class="Separator" />

               <div class="custom-task-details-panel">
                  <h1>This is custom content on bottom</h1>
                  <p>Custom height to auto</p>
                  <p>Custom font size to 1.6rem</p>
               </div>
            </div>
            </h:panelGroup>
         </ui:define>
      ..

-  In case we need to hide Notes, Documents, Related running component,
   we can refer to :ref:`Show/hide component on Case Item Details
   <customization-case-item-details-how-to-override-ui-show-hidden-ui>` 

-  Additional, if we want to customize more ``CaseItemDetails``
   component, please help refer to :ref:`CaseItemDetails
   component <customization-case-item-details-how-to-override-ui>`

Migrate to 7.4.0
----------------

From 7.4.0, CaseTemplate is deprecated and we don't support it anymore.
If you are using CaseTemplate, please do consider to migrate to
TaskTemplate manually.

Migrate to 7.3.0
----------------

From 7.3.0, Portal supports some permissions to show/hide left menu
item, if you override ``LoadSubMenuItems`` process and want to use these
permissions, refer to
:ref:`Customization Menu <customization-menu-customization>`
for more detail.

There is a small change when initializing statistic chart, so if you
override ``DefaultChart.mod``, take a look at its note to see what is
changed.

Portal connector is removed, so there are many things related to it must
be adjusted. Check list below

-  All ``Remote*`` classes are removed, replaced by the Ivy classes: ICase, ITask,
   IUser, IApplication, etc..
-  Use ``BuildTaskQuery`` and ``BuildCaseQuery`` callable process instead of
   ``BuildTaskJsonQuery``  and  ``BuildCaseJsonQuery``  .
-  If you override TaskLazyDataModel, remove
   ``extendSortTasksInNotDisplayedTaskMap`` method. Use ``criteria`` field instead of
   ``queryCriteria`` or ``searchCriteria``, use ``adminQuery`` field instead of
   ``ignoreInvolvedUser`` .
-  If you override CaseLazyDataModel: remove
   ``extendSortCasesInNotDisplayedTaskMap`` method. Use ``criteria`` field instead of
   ``queryCriteria`` or ``searchCriteria``, use ``adminQuery`` field instead of
   ``ignoreInvolvedUser`` .
-  If you override ChangePassword.mod: change process call from
   ``MultiPortal/PasswordService:changePasswordService(String,String)``  to  ``Ivy Data
   Processes/PasswordService:changePassword(String,String)``  .

Migrate hidden task and case to 7.3.0
-------------------------------------

Portal 7.0.10 has the option to store hidden information in custom field of
task and case instead of additional property to improve performance.
Other versions of Portal store these info in additional property.

If you use hide task/case feature, you need to follow these steps:

1. Deploy this project :download:`MigrateHiddenTaskCase.iar <documents/MigrateHiddenTaskCase.iar>` 
   to all your portal applications.
2. In each application, run start process
   ``MigrateHiddenTaskCase``  to migrate.
3. It's optional to clean up redundant data. After migration finishes
   successfully, run start process  ``RemoveHideAdditionalProperty``  in each
   application to clean up HIDE additional property. It will delete HIDE
   additional property of all tasks and cases in current application, so be
   careful with it.

Migrate 7.1.0 to 7.2.0
----------------------

Portal needs `Apache POI <https://poi.apache.org>`__ for exporting to
Excel features.

If you override task widget's data query described at
:ref:`How to override task widget's data query <customization-task-widget-how-to-override-data-query>`,
follow these steps to migrate

-  Add new start method with signature
   ``buildTaskJsonQuery(Boolean)``  in your overridden file of BuildTaskJsonQuery.mod
   (refer to original file BuildTaskJsonQuery.mod).
-  If you customized
   ``TaskLazyDataModel,``  change  ``withStartSignature("buildTaskJsonQuery()``  to
   ``withStartSignature("buildTaskJsonQuery(Boolean)").withParam("isQueryForHomePage",
   compactMode)``  in your customized  ``TaskLazyDataModel``  class.

There are some changes (DefaultApplicationHomePage, DefaultLoginPage,
GlobalSearch) in PortalStart process of Portal Template. If you have
customized this process in your project, copy the new PortalStart from
Portal Template to your project and re-apply your customization.

   .. important::
      In case you already have PortalStart process in your project, delete
      all elements in that process and copy everything from PortalStart
      process of Portal Template (to prevent start link id change).
      
      Check map param result of callable process after copy to make sure
      it's the same as original process.
      |copy-start-process|

EXPIRY_CHART_LAST_DRILLDOWN_LEVEL global variable is removed. User now
can use a context menu to drilldown Task By Expiry chart.

Migrate 7.0.3 to 7.0.5 (or 7.1.0)
---------------------------------

There are some changes in PortalStart process of Portal Template. If you
have customized this process in your project, copy the new PortalStart
from Portal Template to your project and re-apply your customization.

We introduce new method
``findStartableLinkByUserFriendlyRequestPath(String requestPath)`` in
``ProcessStartCollector`` class. If your project has customized
:ref:`Default user process <customization-default-user-process>`,
use this method to generate link to your process. If user doesn't have
permission to start the process, this method will return empty string.

   .. important::
      In case you already have PortalStart process in your project, delete
      all elements in that process and copy everything from PortalStart
      process of Portal Template (to prevent start link id change).

      Check map param result of callable process after copy to make sure
      it's the same as original process.

      |copy-start-process|

Migrate 7.0.2 to 7.0.3
----------------------

If you have additional columns in your customized task widget, refer 
:ref:`Task widget  <customization-task-widget-how-to-overide-ui-task-header>`  to adapt your
customization in ``taskHeader`` section.

Migrate 7.0.1 to 7.0.2
----------------------

In PortalStyle\pom.xml, update project-build-plugin version to
``7.1.0``\ and run maven to compile CSS.

If changing password is customized, change method call
``PasswordService.mod#changePassword(String,String)`` to
``PasswordService.mod#changePasswordService(String, String)`` in this
customization.

Custom fields in Portal task list can now be sorted properly. The method
``extendSort()`` of ``TaskLazyDataModel`` is changed to have a
``taskQuery`` parameter. If you override this method, change your code
to use the new parameter instead of using the ``criteria`` taskQuery.

Portal does not have separate full task list in the homepage anymore.
It's mean that you don't have to customize the task list in
``/layouts/DefaultHomePageTemplate.xhtml``. You can remove your task
list customization code in ``PortalHome.xhtml``.

If you have added new language to Portal by adding cms entry
``/AppInfo/SupportedLanguages`` in your project. Move this entry to
Portal Style.

Migrate 7.0.0 to 7.0.1
----------------------

**Ajax error handling**: By default, Portal handles all exceptions from
ajax requests. Old configuration, customization of ajax error handling
should be removed.

Migrate 6.x to 7.0.0
--------------------

If you copy the ``PortalStart`` process or the ``PortalHome`` HTMLDialog
for customizations, adapt the changes:

-  The whole process is refactored to be clearer. So it is recommended
   that you copy it again.

-  New process is introduced: restorePortalTaskList.ivp

-  PortalStart: some new ivy scripts are added to handle the navigation
   back to the same page before starting a task.

-  PortalHome: The ``taskView`` parameter is added to the start method.

SQL conversion
^^^^^^^^^^^^^^

From Portal ``7.0`` , we use standard axon.ivy Task Category field to
store task category.

To migrate task categories, deploy
:download:`MigrateTaskCategorySample.iar <documents/MigrateTaskCategorySample.iar>`
to your application and run ``Migrate Task Category`` process to:

1. Migrate data from column ``customVarCharField5`` to ``category`` for
   all tasks in the application.

2. Delete leftover data in ``customVarCharField5`` of all tasks in the
   application.

3. Create CMS entries for task categories in the application.

If you have queries which referring to task category, plese replace
``customVarCharField5()`` part with ``category()`` part.

Migrate 6.4 or 6.5 to 6.6
-------------------------

-  Task header is supported to be customized. The
   ``useOverride``  param, which is used to override the task item's body, is changed
   to  ``useOverrideBody`` 
-  If you customize  ``TaskLazyDataModel`` , remove this customized class and customize as
   :ref:`How to override task widget's data query. <customization-task-widget-how-to-override-data-query>` . 

Migrate 6.4 to 6.5
------------------

-  If compilation error "The type org.apache.axis2.databinding.ADBBean
   cannot be resolved" occurs, use  `Project compilation classpath <https://developer.axonivy.com/doc/latest/doc/migrationNotes/MigrationNotesDesigner7.0.html#projectClasspath>`_  to fix.
-  The relative link in default user processes starts with ivy context
   path instead of "pro". If there are customized default user proceses, append
   context path at the beginning. E.g. in Portal  ``6.4`` , it is
   /pro/.../PortalStart.ivp. In Portal  ``6.5`` , change it to
   /ivy/pro/.../PortalStart.ivp. You may use :  ``ivy.html.startref(...)``  or
   ``RequestUriFactory.createProcessStartUri(...)``  to generate links.

Migrate 6.x (x < 4) to 6.4 (Jakobshorn)
---------------------------------------

Portal appearance
^^^^^^^^^^^^^^^^^

Portal ``6.4`` are redesigned. Therefore many components look different
from the previous version like menu, task list, case list ... . Portal
``BasicTemplate`` does not use ``p:layout`` and ``p:layoutUnit``
anymore. You may need to adapt your pages to this change.

For now the menu customization is not supported.

From ``6.4`` , Portal applies `LESS <http://lesscss.org/>`__ to support
customizing Portal styles. You can customize colors, fonts and Portal's
component styles. For more information about customizing Portal's style
with LESS, refer to
:ref:`PortalStyle customization (logos, colors, date patterns) <customization-portal-logos-and-colors>`
.

Steps to migrate

1. Copy PortalStyle/webContent/resources of Portal ``6.4`` to
   PortalStyle/webContent/resources of the current Portal.

2. Modify PortalStyle/webContent/resources/less/theme.less, update value
   of @body-background-color for the background color and @menu-color
   for the menu and primary button color.

3. Put custom styles to
   PortalStyle/webContent/resources/less/customization.less.

4. Add properties and plugins which are defined in PortalStyle/pom.xml
   of Portal ``6.4`` to PortalStyle/pom.xml of the current Portal.

5. Run the maven command ``mvn lesscss:compile`` in PortalStyle to build
   CSS file.

6. PortalStyle/webContent/resources/css/theme.css is obsolete, remove
   it.

Migrate 5.0 (Rothorn) to 6.0 (Säntis)
-------------------------------------

Database conversion
^^^^^^^^^^^^^^^^^^^

If you are using Portal ``5.0`` , you have to manual configure all
settings (create servers, applications, variables) again since Portal
now doesn't use external database. All settings on from Portal ``6.0``
are stored in Ivy system database. If you are using Portal ``6.0`` , you
don't need to convert database.

Portal appearance
^^^^^^^^^^^^^^^^^

Portal now doesn't use `Modena <http://primefaces.org/eos/modena>`__
theme, it's a big difference to previous ``6.0`` . Therefore many things
in Portal ``5.0`` and ``6.0`` will not look the same in new Portal. Many
things have been redesigned like menu, task list, case list ...

.. _installation-release-notes:

Release notes
=============

This part lists all relevant changes since the last official product
releases of Axon.ivy.

Changes in 8.0
--------------

-  Upgraded to Serenity's theme, refer to :ref:`Migration
   Notes <installation-migration-notes-8-0-0>` for more details.
   
-  Support to see case if user is an owner. Administrator can activate/deactivate it via the ``ENABLE_CASE_OWNER`` Portal settings, default is false.
   
-  Support to start a task in IFrame, refer to :ref:`IFrame in Portal <iframe>`.

-  Refactor ``TaskTemplate.xhtml``, refer to :ref:`Migration
   Notes <installation-migration-notes-8-0-0-task-template>` for more details.
   
-  Remove ``caseBody`` inside CaseWidget, refer to :ref:`Migration
   Notes <installation-migration-notes-8-0-0-case-body>` for more details.

-  Introduce new actions button on :ref:`CaseWidget's
   header <customization-case-widget-how-to-override-ui-case-header>`.

-  Introduce new page as the Case item details. The default page is
   portal case details, refer to
   :ref:`Case item details <customization-case-item-details>`
   for the customization.

-  Remove ``taskBody`` inside TaskWidget, refer to :ref:`Migration
   Notes <installation-migration-notes-8-0-0-task-body>` for more details.

-  Introduce two new actions button on :ref:`TaskWidget's
   header <customization-task-widget-how-to-overide-ui-task-header>`.

-  Introduce new page as Task item details. The default page is portal
   task details, refer to
   :ref:`Task item details <customization-task-item-details>`
   for the customization.

-  Task list customization now support responsiveness. refer to :ref:`this
   part <customization-task-widget-responsive-layout>` for more details.

-  Case list customization now support responsiveness. refer to  :ref:`this
   part <customization-case-widget-responsive-layout>` for more details.

-  Start link ivp can not start outside Portal.

Changes in 7.4
--------------

-  New Portal Chat is introduced, now Portal supports Group chat and
   Private chat, refer to
   :ref:`Portal chat<components-portal-chat>`
   for more detail

-  Portal group id is officially configurable, refer to
   :ref:`Change group id <customization-change-group-id>`
   for more detail

-  CaseTemplate is removed, from now on we only use TaskTemplate. Please
   refer to
   :ref:`Migration notes <installation-migration-notes>`
   to see how to migrate CaseTemplate to TaskTemplate

Changes in 7.3
--------------

-  Remove PortalConnector, query data via Ivy API directly to increase
   performance, refer to Migration Notes.

-  Provide the mobile pages. The default page is task list.

-  Provide more permissions to show/hide menu, button and link in
   Portal, refer to :ref:`settings-permission-settings`
   for more detail.

-  Hide Statistic widget can be configured in Admin setting.

-  Hide technical task / case can be configured using additional
   property or custom field (more performance).

Changes in 7.2
--------------

-  Introduce variables to customize task priority and state colors and
   header bar colors

-  Introduce new page: Global search result, and supports the
   customization

-  Override DefaultApplicationHomePage.ivp, DefaultLoginPage.ivp,
   DefaultEndPage.ivp processes, refer to `Replacement
   Project <https://developer.axonivy.com/doc/latest/EngineGuideHtml/administration.html#ServerAdministration-htmlworkflowui>`__,
   check migration notes if you have the customized PortalStart.ivp
   process.

-  Check permission when upload/delete document. User needs permission
   ``IPermission.DOCUMENT_WRITE`` or ``IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE``
   to upload/delete document.

-  Support disable upload/delete document when a case is done. This
   function can be configured by ``HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE``
   setting.

-  Support configure upload file extension whitelist. Only file extensions
   appear in this list are allowed to upload to Portal. This function
   can be configured by ``UPLOAD_DOCUMENT_WHITELIST_EXTENSION`` setting.

-  Support script checking function for upload file. You can
   enable/disable this function by configuring
   ``ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT`` setting.

Changes in 7.1
--------------

-  Support client side timeout: informs user when session is about to
   expire and auto logout when expired.

-  Hide technical cases (the HIDE additional property is set), so that
   they and their related task are not displayed in any Portal case
   lists.

-  More search criteria for user in Case list are added and allowed to
   customize.

-  User can add new language. Refer to
   :ref:`Language settings <settings-language>`
   for detail.

-  Axon ivy express has custom end page. It can be turned off or
   customized.

-  User can create default start process with permission check. If the
   user doesn't have permission to start the process, it won't appear in
   favorite processes. Refer to
   :ref:`Default user process  <customization-default-user-process>`
   for detail.

Changes in 7.0 (Jakobshorn)
---------------------------

-  More search criteria for user in Task list are added and allowed to
   customize.

-  Task delegate customization is supported.

-  The same task list is displayed before and after a task. Set default
   end page to another project to remove this feature.

-  Task category of Portal is now stored in new Task category field of
   ivy.

   Refer to
   :ref:`Migration notes  <installation-migration-notes>`
   to learn how to migrate data from ``customVarCharField5`` to new
   ``category`` field.

-  Hide technical tasks (the HIDE additional property is set), so that
   they are not displayed in any Portal task lists.

-  Change password is supported to be customized. Refer to
   :ref:`Change password process <customization-change-password-process>`
   to know how to customize this feature.

Changes in 6.6 (Jakobshorn)
---------------------------

-  Task widget's customization is extended with task header and task
   data query.

-  Hide technical roles (the HIDE property is set), so that they are not
   displayed anywhere (e.g. delegate, absence mgmt). The default hidden
   role is AXONIVY_PORTAL_ADMIN.

Changes in 6.0 (Säntis)
-----------------------

-  Portal has 2 level menu with animation.

-  All components such as button, text field ...have been re-styled, not
   applied Modena's styles.

-  Support responsiveness with 3 screen widths: 1920, 1366 and 1024.
   Refer to
   :ref:`Responsiveness <components-layout-templates-reponsiveness>`
   for more details.

-  Some customizations are not supported in this release: main menu,
   case header.

.. |less-2-sass| image:: images/installation/less-2-sass.png
.. |copy-start-process| image:: images/installation/copy-start-process.png
.. |server-address-settings| image:: images/installation/server-address-settings.png
