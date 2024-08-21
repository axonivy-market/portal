.. _components-additional-component:

Additional Components
=====================

.. _components-additional-component-task-analysis:

Task Analysis
-------------

.. _components-additional-component-task-analysis-introduction:

Introduction
^^^^^^^^^^^^

The Portal's Task Analysis component provides features in accordance with the
user's permission :bdg-ref-warning:`🔑StatisticAnalyzeTask <StatisticAnalyzeTask>`. Refer to
:dev-url:`Security section in Cockpit
</doc/|version|/engine-guide/reference/engine-cockpit/security.html>` to
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

      import com.axonivy.portal.components.publicapi.ProcessStartAPI;
      import java.util.HashMap;
      import javax.faces.context.FacesContext;

      String taskAnalysisUrl = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath("Start Processes/PortalStart/showTaskAnalysis.ivp");
      FacesContext.getCurrentInstance().getExternalContext().redirect(taskAnalysisUrl);

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


.. |task-analysis| image:: ../../screenshots/components/task-analysis.png
.. |example-global-growl-finished-task| image:: ../../screenshots/components/example-global-growl-finished-task.png
.. |example-global-growl-cancelled-task| image:: ../../screenshots/components/example-global-growl-cancelled-task.png
