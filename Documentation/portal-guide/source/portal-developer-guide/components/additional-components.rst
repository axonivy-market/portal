.. _components-additional-component:

Additional Components
=====================

.. _components-additional-component-process-history:

Process History
---------------

.. _components-additional-component-process-history-introduction:

Introduction
^^^^^^^^^^^^

This component is a lazy loading list which displays all business cases
of a business entity in your application. You can include this component
everywhere:

In a page

|process-history-example|

In a dialog

|process-history-dialog-example|

This component is a part of project ``portal-component``, which is independent of the Portal. You don't need to import the Portal projects to use it.

.. note::

      If you use this component in Portal, redirect to case details page when clicking on a row.

.. _components-additional-component-process-history-how-to-use:

How to Use
^^^^^^^^^^

First, you have to link the cases to the business entity. Call the subprocess
``SetBusinessEntityId`` in each process which needs to be linked and input an
identifier unique to your business entity. The subprocess will set the id to the
additional property "CASE_BUSINESS_ENTITY_PROPERTY" of the business case.

#. Link the cases to the business entity.

   Call the subprocess ``SetCaseBusinessEntity`` in the process which needs to be
   linked and input an identifier unique to your business entity. The
   subprocess will set the id to the additional property
   "CASE_BUSINESS_ENTITY_PROPERTY" of the business case.

   |set-business-entity-id-sub-process|

   Include the process history component into your page:

#. Include the process history component into your page:

   .. code-block:: html

         <ic:com.axonivy.portal.component.ProcessHistory businessEntityId="alpha" />

   The value of the attribute ``businessEntityId`` must match the id input
   into the subprocess in the first step.

   By default, the component will load 20 cases at a time. You can change this by
   setting the attribute ``chunkSize`` to the number you want. You should use this
   attribute alongside the attribute ``scrollHeight`` to configure the scroll bar
   of the list.

   Attributes of this component:

   .. csv-table::
      :file: documents/additional-components/process_history_component_attributes.csv
      :header-rows: 1
      :class: longtable
      :widths: 1 1 1 3

   .. note::

         If you use this component in a dialog, you have to run script
         ``processHistory.setup();`` when the dialog is shown, e.g.:

   .. code-block:: html

      <p:dialog widgetVar="process-history-dialog" id="process-history-dialog" width="800" height="500" resizable="false"
         header="Process history of Beta Company" modal="true" onShow="processHistory.setup();">
         <ic:com.axonivy.portal.component.ProcessHistory businessEntityId="beta" chunkSize="10" scrollHeight="400" />
            </p:dialog>

3. Override the callable subprocess ``OpenCaseDetailsHook`` to handle navigation when clicking on a case in the Process History component.

   Please refer to the process ``ProcessHistoryExample`` in the project ``portal-component-example`` for more details on how to use the Process History without the Portal.

   Please refer to the process ``ProcessHistoryComponent`` in the project ``portal-developer-examples`` for more details on how to customize the Process History with the Portal.

   If you want to customize its style,
   please refer to :ref:`components-additional-components-style-customization`.

   .. important::

         If your process has a Trigger component or sends a signal to start
         another process with the option "Attach to Business Case that
         triggered this process" selected, the current case of the process
         will become a technical case and will not be loaded into the process
         history list. In this case, you need to call the ``SetCaseBusinessEntity``
         subprocess after the first Trigger or signal sending step.

.. _components-additional-components-migrate-from-old-process-history:

Migrate from Deprecated Process History
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. Replace code in HTML files: replace ``ic:ch.ivy.addon.portal.component.ProcessHistory`` with ``ic:com.axonivy.portal.component.ProcessHistory``.

#. Optional: set ``showCaseOfOwner`` to ``true`` if you want to show the cases owned by the login user.

#. Optional: set ``dateTimePattern`` to a specific date/time pattern if you want to show the date/time in a unique format different from the default format (dd:MM.yyyy HH:mm).

#. Optional: Override the subprocess ``OpenCaseDetailsHook`` to handle navigation when clicking on a case in the Process History component.

   For applications using the Portal without IFrame:

      + Copy the contents of the subprocess ``OpenPortalCaseDetails`` in the project ``PortalTemplate`` to the overridden subprocess ``OpenCaseDetailsHook``.

   For applications using the Portal with an IFrame:

      + Set attribute ``isOpenInFrame`` to ``true``.

      + Modify the subprocess ``OpenCaseDetailsHook`` to generate ``caseDetailsUrl``. Process History component will navigate to this URL when the user clicks on a case.

      + Please refer to the subprocess ``OpenCaseDetailsHookOverride`` in project ``portal-developer-examples`` to see how to generate this link in the Portal.

.. _components-additional-component-task-analysis:

Task Analysis
-------------

.. _components-additional-component-task-analysis-introduction:

Introduction
^^^^^^^^^^^^

The Portal's Task Analysis component provides features in accordance with the
user's permission StatisticAnalyzeTask in PortalTaskPermissions. Refer to
:dev-url:`Security section in Cockpit
</doc/nightly/engine-guide/tool-reference/engine-cockpit/security.html>` to
analyze not only tasks but also cases. These features are:

#. Sets of filters for both tasks and cases which allow to filter
   and find tasks and cases easier. Additionally, user can create and
   manage their own filter sets.

#. Dynamic result table with lots of information for both task and case.

#. Export results into an Excel files (currently we only support .xlsx
   extension).

|task-analysis|

.. _components-additional-component-task-analysis-how-to-use:

How to Use
^^^^^^^^^^

The Task Analysis component is integrated into the Statistics widget. You can
use this component directly when opening the Statistics widget. If you want to
use this component, you only have to redirect to the Task Analysis component
with the following code:

.. code-block:: java

      import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
      import java.util.HashMap;
      import javax.faces.context.FacesContext;

      String taskAnalysisUrl = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath("Start Processes/PortalStart/showTaskAnalysis.ivp");
      FacesContext.getCurrentInstance().getExternalContext().redirect(taskAnalysisUrl);


.. _components-additional-component-process-chain:

Process Chain
-------------

.. _components-additional-components-process-chain-introduction:

Introduction
^^^^^^^^^^^^

The Process Chain component provides status information for all steps in a
process: the currently executable steps, done steps, and open steps. Its features are:

#. Display all currently executable steps, or display only helpful steps like
   begin, last, current, previous, and next steps.

#. Change the shape of the process chain: circle or line.

#. Change the orientation of the process chain: horizontal or vertical.

This component is a part of project ``portal-component``, which is independent of the Portal. You don't need to import the Portal projects to use it.

|process-chain|

.. _components-additional-component-process-chain-how-to-use:

How to Use
^^^^^^^^^^

You can integrate the Process Chain component in any widget by including
the component on a page with following code:

.. code-block:: html

		<ic:com.axonivy.portal.component.ProcessChain id="process-chain-circle-horizontal" componentId="component-circle-horizontal" shape="CIRCLE" direction="HORIZONTAL"
         isShowAllSteps="false" actualStepIndex="0" steps="#{['Step 1','Step 2','Step 3','Step 4','Step 5','Step 6','Step 7','Step 8','Step 9']}" />

#. You have to set the parameters

   -  ``actualStepIndex``. This is the index of the current step.
   -  ``steps``. This is list of working steps.

#. You may change the parameters

   -  ``shape`` to ``CIRCLE`` or ``LINE`` according to your requirements. Default is ``CIRCLE``.
   -  ``direction`` to ``HORIZONTAL`` or ``VERTICAL`` . Default is ``HORIZONTAL``.
   -  ``isShowAllSteps`` to ``TRUE`` or ``FALSE`` . Default is ``FALSE``.

.. csv-table::
  :file: documents/additional-components/process_chain_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3


If you want to customize its style,
please refer to :ref:`components-additional-components-style-customization`.

.. _components-additional-components-migrate-from-old-process-chain:

Migrate from Deprecated Process Chain
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- Replace code in HTML files: replace ``ch.ivy.addon.portalkit.singleapp.process.ProcessChain`` with ``com.axonivy.portal.component.ProcessChain``.

.. _components-additional-component-global-growl:

Global Growl
------------

.. _components-additional-component-global-growl-introduction:

Introduction
^^^^^^^^^^^^

This component is a global growl introduced in BasicTemplate. You can use it to
display your messages in Portal using this code:

.. code-block:: html

    <p:growl id="portal-global-growl" widgetVar="portal-global-growl" for="portal-global-growl-message" escape="false" showDetail="true" />


Display Growl After Finishing a Task
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

After a task is finished, a growl message appears if ``Portal.DisplayMessageAfterFinishTask`` is true.

|example-global-growl-finished-task|

.. _components-additional-component-global-growl-display-growl-after-finish-task:

Display Growl After Leaving a Task
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

After the user cancels a task, a growl message is displayed if ``Portal.DisplayMessageAfterFinishTask`` is true.

|example-global-growl-cancelled-task|

.. _components-additional-component-global-growl-display-growl-after-cancel-task:

Customize the Global Growl Message for a Task without using IFrames
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

For each task, you can turn the growl message display off or override it.
Initially, when you submit the form to the interacting task, you need to set the
``overridePortalGrowl`` key in the associated flash object:

::

   Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
   flash.put("overridePortalGrowl", true);
   flash.setRedirect(true);

If you want to turn the global growl message off, that is all that is required.
To override the message with your own, add ``facesMessage`` to this component.
You can customize the message for finished or cancelled tasks separately.

::

   import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
   import javax.faces.context.Flash;
   import javax.faces.context.FacesContext;
   import javax.faces.application.FacesMessage;

   FacesMessage message = new FacesMessage("Task is done successfully", ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/linkToCaseDetails",
   	[PortalNavigator.buildPortalCaseDetailsUrl(ivy.case.getBusinessCase().getId())]));
   FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);

   Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
   flash.put("overridePortalGrowl", true);
   flash.setRedirect(true);
   flash.setKeepMessages(true);

Customize the Global Growl Message for a Task using IFrames
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

If ``Portal.DisplayMessageAfterFinishTask`` is true, before a task is finished
or cancelled, you can trigger the display of a customized message by calling the
API below:

::

   import ch.ivy.addon.portalkit.publicapi.PortalGlobalGrowInIFrameAPI;

   PortalGlobalGrowInIFrameAPI api = new PortalGlobalGrowInIFrameAPI();
   api.displayCustomizedMessage("Your customized message");

Please refer to GlobalGrowl Start Process in the portal-developer-examples project for details.

.. _components-additional-components-document-table:

Document Table
--------------

This component is a case document table with the features display, upload,
download and delete document entries.

This component is a part of project ``portal-component``, which is independent of the Portal. You don't need to import the Portal projects to use it.

|document-table|

To extend features of this component, please override these subprocesses: ``GetDocumentItems``,
``UploadDocumentItem``, ``DeleteDocumentItem``, and ``DownloadDocumentItem``.
You can also add a new column or remove default columns of the document table.
Find examples in process ``DocumentTableComponent`` in project portal-developer-examples.

Code Example:

.. code-block:: html

   <h:form id="form">
      <ic:com.axonivy.portal.component.DocumentTable id="document-table-component"
         allowedUploadFileTypes="doc,docx,xls,xlsx,xlsm,csv,pdf,ppt,pptx,txt"
         typeSelectionItems="#{documentTableExampleBean.documentTypes}">
         <f:facet name="componentHeader">
            <h2>This is the customized document table component header</h2>
         </f:facet>
         <p:column headerText="Creator" styleClass="document-creator-column">
            <h:outputText id="creator" value="#{document.creation.userName}" title="#{document.creation.userName}" />
         </p:column>
         <p:column headerText="Created time" styleClass="document-created-column">
            <h:outputText id="created-time" value="#{document.creation.timestamp}" title="#{document.creation.timestamp}" />
         </p:column>
         <p:column headerText="Customer" styleClass="document-customer-column">
            <h:outputText id="customer" value="#{document.customer}" title="#{document.customer}" />
         </p:column>
         <f:facet name="componentFooter">
            <h2>This is the customized document table component footer</h2>
         </f:facet>
      </ic:com.axonivy.portal.component.DocumentTable>
   </h:form>


Refer to process ``DocumentTableExample`` in project ``portal-component-example`` for more details.

Attributes of this component:


.. csv-table::
  :file: documents/additional-components/document_table_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

Script checking and virus scanning
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The Document Table has options to check for harmful scripts and viruses inside the file before uploading it to the system.

   - Set ``enableScriptCheckingForUploadedDocument`` to ``true`` to check for harmful scripts.
   - Set ``enableVirusScannerForUploadedDocument`` to ``true`` to enable virus scanning.

Code example:

.. code-block:: html

   <ic:com.axonivy.portal.component.DocumentTable id="document-table-component"
      enableScriptCheckingForUploadedDocument="true"
      enableVirusScannerForUploadedDocument="true" />

Please refer to :ref:`settings-virus-scanning-setting` for more details about virus scanning.

.. _components-additional-components-migrate-from-old-document-table:

Migrate from Deprecated Document Table
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. Replace code in HTML files: replace ``ic:ch.ivy.addon.portalkit.component.document.DocumentTable`` with ``ic:com.axonivy.portal.component.DocumentTable``.

#. Consider updating value of new attributes ``enableScriptCheckingForUploadedDocument``, ``enableVirusScannerForUploadedDocument`` and ``allowedUploadFileTypes`` as required.

#. Override subprocesses if you want and adapt your business accordingly.

   +-----------------------------------+--------------------------+
   | New sub process                   | Deprecated sub process   |
   +===================================+==========================+
   | GetDocumentItems                  | GetDocumentList          |
   +-----------------------------------+--------------------------+
   | UploadDocumentItem                | UploadDocument           |
   +-----------------------------------+--------------------------+
   | DeleteDocumentItem                | DeleteDocument           |
   +-----------------------------------+--------------------------+
   | DownloadDocumentItem              | DownloadDocument         |
   +-----------------------------------+--------------------------+

#. If you have customized IvyDocument make sure to extend it from class ``com.axonivy.portal.component.ivydata.bo.IvyDocument``.

#. Attributes ``typeSelectionItems`` and ``selectedType`` now use ``com.axonivy.portal.component.enums.DocumentType``.
   Please replace ``ch.ivy.addon.portalkit.enums.DocumentType`` with ``com.axonivy.portal.component.enums.DocumentType``.

.. note::
   Please remove redundant overridden configurations, subprocesses, and data classes such as GetDocumentListOverride,
   UploadDocumentOverride, etc.

.. _components-additional-components-user-selection:

User Selection
--------------

Introduction
^^^^^^^^^^^^

This component is used for selecting a single user from a list of users defined by a role name list.
If you don't define the role name list, all users will be loaded.
It includes a label, an autocomplete and one message element to display a message related to that autocomplete element.

This component is a part of project ``portal-component``, which is independent of the Portal. You don't need to import the Portal projects to use it.

How to Use
^^^^^^^^^^

You can insert this component into any page. This component supports two styles to display a label.

#. Default Style

   |user-selection|

   Code example:

   .. code-block:: html

      <ic:com.axonivy.portal.component.UserSelection componentId="default-user-autocomplete"
         selectedUser="#{data.selectedUser}" label="Default user selection"
         isRequired="true" labelPanelStyleClass="ui-g-6 ui-md-6 ui-sm-12"
         autoCompleteStyleClass="width-100" autoCompletePanelStyleClass="ui-g-6 ui-sm-12" />

#. Floating Label

   |user-selection-floating-label|

   Code example:

   .. code-block:: html

      <ic:com.axonivy.portal.component.UserSelection componentId="all-user-autocomplete"
         hightlight="false" selectedUser="#{data.selectedUserForExcludingUsers}"
         label="Loading users (exclude gm1, gm2, admin)" autoCompleteStyleClass="width-100"
         autoCompletePanelStyleClass="ui-g-12 floating-label-margin-top"
         excludedUsernames="#{data.excludedUsernames}" floatingLabel="true" />

.. tip::
   The autocomplete element of the user selection component allows to
   insert children and ajax events (Refer to ``UserSelection.xhtml``).
   Any child in the UserSelection component will be re-parented into
   this autocomplete at the location of the ``insertChildren`` tag.
   We introduce a facet named ``event`` for autocomplete so that the
   ajax event can be nested, as well.

An example:

I want to display users in a dropdown list formatted as "<Full name>
(<username>)". When I select a user, a message shall be displayed.

|user-selection-with-children-and-ajax-event|

|user-selection-component-ajax-expand|

.. code-block:: html

   <ic:com.axonivy.portal.component.UserSelection id="item-select-event-component"
      componentId="item-select-event-for-user-selection" floatingLabel="true"
      fromRoleNames="#{data.definedRoleNames}" label="Demonstrate facet and children"
         selectedUser="#{data.selectedUserForInsertChildren}"
      autoCompleteStyleClass="width-100"
      autoCompletePanelStyleClass="ui-g-12 floating-label-margin-top">
         <p:column>
         <h:outputText value="#{user.displayName} (#{user.name})" />
         </p:column>
         <f:facet name="event">
            <p:ajax event="itemSelect" listener="#{logic.showSelectedUser}"
               update="#{p:component('item-select-event-for-user-selection-message')}"/>
         </f:facet>
   </ic:com.axonivy.portal.component.UserSelection>

Please refer to ``UserSelectionExample.xhtml`` in project ``portal-component-example`` for more details.

This component offers the following attributes:

.. csv-table::
  :file: documents/additional-components/user_selection_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

.. _components-additional-components-migrate-from-old-user-selection:

Migrate from Deprecated User Selection
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

#. Replace code in HTML files: replace ``ic:ch.ivy.addon.portalkit.component.UserSelection`` with ``ic:com.axonivy.portal.component.UserSelection``.

#. Replace ``ch.ivy.addon.portalkit.dto.UserDTO`` with ``com.axonivy.portal.component.dto.UserDTO``.

   .. note:: If you stored class ``ch.ivy.addon.portalkit.dto.UserDTO`` in your database, you have to update the database manually.

.. _components-additional-components-role-selection:

Role Selection
--------------

Introduction
^^^^^^^^^^^^

This component is used to select a role from a given list of roles. If you don't
define the role list, all roles will be loaded. It includes one label, one
autocomplete and one message element to display messages related to this
autocomplete element.

This component is a part of project ``portal-component``, which is independent of the Portal. You don't need to import the Portal projects to use it.

How to Use
^^^^^^^^^^

You can insert this component into any page. This component offers the following
two styles to display its label.

#. Default Style

   |role-selection|

   Code example:

   .. code-block:: html

      <ic:com.axonivy.portal.component.RoleSelection
         componentId="role-from-defined-role-autocomplete"
         fromRoleNames="#{data.definedRoleNames}"
         selectedRole="#{data.selectedRoleForDefinedRoles}"
         isRequired="true"
         label="Roles from defined role names"/>

#. Floating Label

   |role-selection-floating-label|

   Code example:

   .. code-block:: html

      <ic:com.axonivy.portal.component.RoleSelection
         componentId="floating-label-and-exclude-role-autocomplete" hightlight="false"
         selectedRole="#{data.selectedRole}"
         label="Loading with all roles (exclude CaseOwner, GeneralManager)"
         excludedRolenames="#{data.excludedRoleNames}"
         isRequired="true" floatingLabel="true" />

.. tip::
   The autocomplete element of the role selection component allows you to
   insert children and ajax events (Refer to ``RoleSelection.xhtml``).
   Any child in the RoleSelection component will be re-parented into this
   autocomplete at the location of the ``insertChildren`` tag. We
   introduced a facet named ``event`` for autocomplete so that the ajax
   event can be nested, as well.

For example:

I want to display roles in dropdown list formatted as "<Display Name> (<Member
Name>)". When I select a role, a message shall be displayed.

|role-selection-with-children-and-ajax-event|

|role-selection-component-ajax-expand|

.. code-block:: html

      <ic:com.axonivy.portal.component.RoleSelection
         id="item-select-event-component"
         componentId="item-select-event-for-role-selection"
         fromRoleNames="#{data.definedRoleNames}"
         selectedRole="#{data.selectedRoleForInsertChildren}"
         label="Demonstrate facet and children"
         autoCompleteStyleClass="width-100"
         autoCompletePanelStyleClass="ui-g-12 floating-label-margin-top"
         isRequired="true" floatingLabel="true">
         <p:column>
            <h:outputText value="#{role.getDisplayName()} (#{role.getMemberName()})" />
         </p:column>
         <f:facet name="event">
            <p:ajax event="itemSelect" listener="#{logic.showSelectedRole}"
               update="#{p:component('item-select-event-for-role-selection-message')}" />
         </f:facet>
      </ic:com.axonivy.portal.component.RoleSelection>

Please refer to ``RoleSelectionExample.xhtml`` in ``portal-component-example`` project for more details.

This component offers the following attributes:

.. csv-table::
  :file: documents/additional-components/role_selection_component_attributes.csv
  :header-rows: 1
  :class: longtable
  :widths: 1 1 1 3

.. _components-additional-components-migrate-from-old-role-selection:

Migrate from Deprecated Role Selection
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
#. Replace code in HTML files: replace ``ic:ch.ivy.addon.portalkit.component.RoleSelection`` with ``ic:com.axonivy.portal.component.RoleSelection``.

#. Replace ``ch.ivy.addon.portalkit.dto.RoleDTO`` with ``com.axonivy.portal.component.dto.RoleDTO``.

   .. note:: If you stored class ``ch.ivy.addon.portalkit.dto.RoleDTO`` in your database, you have to update the database manually.

.. _components-additional-portal-dialog-with-icon:

Portal Dialog with Icon
-----------------------

Introduction
^^^^^^^^^^^^

This decorator is used to display a dialog with a big icon and a header in the middle; the content is shown below.

How to Use
^^^^^^^^^^

.. code-block:: html

      <ui:decorate template="/layouts/decorator/portal-dialog-with-icon.xhtml">
         <ui:param name="id" value="destroy-task-confirmation-dialog" />
         <ui:param name="widgetVar" value="destroy-task-dialog" />
         <ui:param name="appendTo" value="@(body)" />
         <ui:param name="iconClass" value="icon ivyicon-delete-1" />
         <ui:param name="iconStyleClass" value="portal-dialog-error-icon" />
         <ui:param name="dialogContent" value="#{ivy.cms.co('/ch.ivy.addon.portalkit.ui.jsf/taskList/destroyTaskMessage')}" />

         <ui:define name="dialogFooter">
            <p:commandLink value="#{ivy.cms.co('/ch.ivy.addon.portalkit.ui.jsf/common/cancel')}"
               onclick="PF('destroy-task-dialog').hide();" styleClass="u-mar-right-15"/>
            <p:commandButton id="confirm-destruction" value="#{ivy.cms.co('/ch.ivy.addon.portalkit.ui.jsf/common/destroy')}"
               icon="#{visibilityBean.generateButtonIcon('icon ivyicon-remove')}"
               actionListener="#{logic.destroyTask(task)}"
               oncomplete="PF('destroy-task-dialog').hide()"
               update="#{cc.clientId}:task-detail-general-container"
               process="@this"/>
         </ui:define>
      </ui:decorate>

Please refer to ``PortalDialogExample.xhtml`` in ``portal-developer-examples``
for examples.

This decorator provides two custom sections:

-  ``dialogFooter``: The footer of the dialog. Required.
-  ``dialogContentSection``: If you want to use your custom style for
   the dialog content, define this section. Optional.

This decorator offers the following parameters:

.. csv-table::
  :file: documents/additional-components/portal_dialog_decorator.csv
  :header-rows: 1
  :class: longtable
  :widths: 20 10 25 45

Portal Cronjob Trigger
----------------------

Portal provides the helper bean
``ch.ivy.addon.portalkit.util.CronByGlobalVariableTriggerStartEventBean``. It
uses the Quartz framework to trigger cron jobs using a variable to define
the trigger schedule. The variable has to contain a cron job pattern to
trigger the process as required.

To generate cron patterns, use the online site `Cron Maker
<http://www.cronmaker.com>`_. It makes creating your own cron job patterns a
breeze.

.. _components-additional-components-style-customization:

Style Customization
--------------------

This customization is only available for the components in the new project ``portal-component``.

How to customize
^^^^^^^^^^^^^^^^

1. You have to add a new css file to your resources and import it into your template.

   Code Example:

   .. code-block:: html

      <ui:composition template="/layouts/basic-10.xhtml">
         <ui:define name="title">test</ui:define>
         <ui:define name="content">
            <ic:com.axonivy.portal.component.ProcessHistory businessEntityId="alpha" chunkSize="12" scrollHeight="600" />
            <h:outputStylesheet name="layouts/styles/process-history-customize.css" />
         </ui:define>
      </ui:composition>

   .. note::
      You have to place your css file in a ``<h:outputStylesheet />`` below the component to override defined styles.

2. Within this file you can override default css variables of components. For example, the \--process-history-description-text-color:

   .. code-block:: css

      :root {
         --process-history-description-text-color: red;
      }

List of css variables that you can override
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Process Chain
^^^^^^^^^^^^^

.. csv-table::
  :file: documents/additional-components/css_variables/process_chain.csv
  :header-rows: 1
  :class: longtable
  :widths: 2 1 2


Process History
^^^^^^^^^^^^^^^

.. csv-table::
  :file: documents/additional-components/css_variables/process_history.csv
  :header-rows: 1
  :class: longtable
  :widths: 2 1 2

.. |process-history-example| image:: ../../screenshots/components/process-history-example.png
.. |process-history-dialog-example| image:: ../../screenshots/components/process-history-dialog-example.png
.. |set-business-entity-id-sub-process| image:: images/additional-component/set-business-entity-id-sub-process.png
.. |task-analysis| image:: ../../screenshots/components/task-analysis.png
.. |process-chain| image:: ../../screenshots/components/process-chain.png
.. |example-global-growl-finished-task| image:: ../../screenshots/components/example-global-growl-finished-task.png
.. |example-global-growl-cancelled-task| image:: ../../screenshots/components/example-global-growl-cancelled-task.png
.. |document-table| image:: ../../screenshots/components/document-table.png
.. |user-selection| image:: ../../screenshots/components/user-selection-component.png
.. |user-selection-floating-label| image:: ../../screenshots/components/user-selection-component-floating-label.png
.. |user-selection-with-children-and-ajax-event| image:: ../../screenshots/components/user-selection-component-ajax-event-selected-message.png
.. |user-selection-component-ajax-expand| image:: ../../screenshots/components/user-selection-component-ajax-expand.png
.. |role-selection| image:: ../../screenshots/components/role-selection-component-from-defined-role.png
.. |role-selection-floating-label| image:: ../../screenshots/components/role-selection-component-floating-label-and-exclude-role.png
.. |role-selection-with-children-and-ajax-event| image:: ../../screenshots/components/role-selection-component-ajax-event-selected-message.png
.. |role-selection-component-ajax-expand| image:: ../../screenshots/components/role-selection-component-ajax-expand.png