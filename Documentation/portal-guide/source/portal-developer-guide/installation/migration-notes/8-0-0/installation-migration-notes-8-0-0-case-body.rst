.. _installation-migration-notes-8-0-0-case-body:

Migrate CaseBody to :ref:`customization-case-item-details`  component
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