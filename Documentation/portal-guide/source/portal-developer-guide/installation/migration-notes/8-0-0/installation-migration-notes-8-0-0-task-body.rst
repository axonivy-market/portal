.. _installation-migration-notes-8-0-0-task-body:

Migrate TaskBody to  :ref:`customization-task-item-details` component
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