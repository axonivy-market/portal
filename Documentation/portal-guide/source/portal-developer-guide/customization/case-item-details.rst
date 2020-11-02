.. _customization-case-item-details:

Case item details
=================

CaseItemDetails is a built-in component of Portal which contains the
case info which users can interact with. In order to show needed case's
information, Portal supports overriding concept for CaseItemDetails.

Each CaseItemDetails contains

- ``CaseItemDetailsDataAndDescription (1)`` box.

-  ``CaseItemDetailsHistories (2)`` box.

-  ``CaseItemDetailsRelated (3)`` box.

-  ``CaseItemDetailsDocuments (4)`` box.

-  CaseItemDetail custom panel: caseItemDetailCustomTop,
   caseItemDetailCustomMiddle, caseItemDetailCustomBottom

|case-standard|

.. important:: "Data and Description" panel always displays, we cannot override its
                 content or hide/show this panel.
   
.. _customization-case-item-details-how-to-override-ui:

How to custom Case item details UI
----------------------------------

Refer to ``portal-developer-examples`` project for examples.

1. Introduce an Axon.ivy project which has ``PortalTemplate`` as a
   required library.

2. To customize case item detail, you must customize Portal Home first.
   Refer to :ref:`Customize Portal
   home <customization-portal-home>` to set new home
   page.

3. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. Point PortalHome element to your custom home page in
   previous step. This process is new home page and administrator should
   register this link by Portal's Admin Settings.

4. Custom the Case item details:

   -  Use Axon.ivy ``Html Dialog Overrides`` to override the
      ``ic:ch.ivy.addon.portal.component.CaseInformation`` HTML dialog.
      The original implementation of this html dialog is showing data of case with default styling,
      change the parameters and content inside ui:define tag to show your customization.

5. After previous steps, you can override Case item details UI elements
   as shown/hidden element by keywords:

   To show/hide, please using ``showItemDetailsHeader``,
   ``showItemDetailRelated``, ``showItemDetailsHistories``,
   ``showItemDetailDocuments`` and ``showItemBackButton`` code. For more details, please refer to
   :ref:`Show/Hide
   components <customization-case-item-details-how-to-override-ui-show-hidden-ui>`.

   And to add a new elements, please refer to :ref:`Add new Custom
   panel <customization-case-item-details-how-to-overide-ui-custom-body>`
   code

.. _customization-case-item-details-how-to-override-ui-show-hidden-ui:

Show/Hide components by keywords
--------------------------------

Refer to the ``ui:param`` tag list in ``CaseInformation.xhtml`` of
PortalTemplate. In case, we want to show/hide any elements on
CaseItemDetails, we should override value of ``ui:param``

List valid parameters:

-  ``ui:param name="showItemDetailsHeader" value="true"``

   To show/hide Case header, by default it's true. You should set as
   false in case we set alwaysShowDetails for CaseItem.

-  ``ui:param name="showItemDetailsHistories" value="true"``

   To show/hide Case Histories component, by default it's true.

-  ``ui:param name="showItemDetailDocuments" value="true"``

   To show/hide Case Documents component, by default it's true.

-  ``ui:param name="showItemDetailRelated" value="true"``

   To show/hide Case Related Tasks/Cases component, by default it's
   true.

-  ``ui:param name="showItemBackButton" value="true"``

   To show/hide Back button, by default it's true.

.. _customization-case-item-details-how-to-overide-ui-custom-body:

Add new Custom panel
--------------------

Refer to the ``caseItemDetailCustom*`` section in
``CaseInformation.xhtml`` of PortalTemplate.

-  We need to define ``ui:define`` with a valid name such as
   ``caseItemDetailCustomTop``, ``caseItemDetailCustomMiddle`` and
   ``caseItemDetailCustomBottom``.

   The ``caseItemDetailCustomTop``: will be shown on the top of the
   component

   The ``caseItemDetailCustomMiddle``: will be shown on the middle of
   the component

   The ``caseItemDetailCustomBottom``: will be shown on the bottom of
   the component

-  Add your custom code into tags above.

-  Finally, your custom panel will be displayed inside of
   :ref:`CaseItemDetails <customization-case-item-details>`
   page.

-  Below is example code for override custom panel box of case details

.. code-block:: html

    <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      !!!! START: AREA SHOULD BE CUSTOMIZED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      !!!!!!!! START: TO SHOW /HIDDEN ANY SECTIONS OF CASE DETAILS, YOU CAN TURN TRUE/FALSE FOR BELOW PARAMETERS !!!!!!!!!!!!!!!!!!!!!!!!
      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Attribute showItemDetailsHeader: To show the header of case details. By default it's true
        Attribute showItemDetailsHistories: To show the Histories component inside Case details body. By default it's true
        Attribute showItemDetailDocuments: To show the Documents component inside Case details body. By default, it's true
        Attribute showItemDetailRelated: To show the RelatedTask component inside Case details. By default, it's true
      !!!!!!!! END SHOW /HIDDEN SECTIONS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->

    <ui:param name="id" value="#{cc.clientId}" />
    <ui:param name="showItemDetailsHeader" value="#{cc.attrs.showItemDetailsHeader}" />
    <ui:param name="showItemDetailsHistories" value="#{cc.attrs.showItemDetailsHistories}" />
    <ui:param name="showItemDetailDocuments" value="#{cc.attrs.showItemDetailDocuments}" />
    <ui:param name="showItemDetailRelated" value="#{cc.attrs.showItemDetailRelated}" />
    <ui:param name="descriptionComponentToUpdate" value="#{cc.attrs.descriptionComponentToUpdate}" />
    <ui:param name="isWorkingOnTask" value="#{cc.attrs.isWorkingOnTask}" />

    <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      !!!!!!!! START: TO ADD YOUR CUSTOMIZATION CODE ON THE CASE DETAILS PAGE, WE PROVIDE 3 SECTIONS AS BELOW HELP YOU CAN DO IT !!!!!!!!
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

    <!-- !!!!!!!! END ADD YOUR CUSTOMIZATION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
    <!-- !!!! END: AREA SHOULD BE CUSTOMIZED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->

..

-  After applied above code to your custom page, custom panels will be
   displayed as below

   1. ``caseItemDetailCustomTop`` panel box.
   2. ``caseItemDetailCustomMiddle`` panel box.
   3. ``caseItemDetailCustomBottom`` panel box.

   |case-customized-top|

   |case-customized-bottom|

-  Other example, we have a full flexibility page if we use ``ui-g-*``
   class to define the width of panel


.. |case-standard| image:: ../../screenshots/case-detail/customization/case-standard.png
.. |case-customized-top| image:: ../../screenshots/case-detail/customization/case-customized-top.png
.. |case-customized-bottom| image:: ../../screenshots/case-detail/customization/case-customized-bottom.png


