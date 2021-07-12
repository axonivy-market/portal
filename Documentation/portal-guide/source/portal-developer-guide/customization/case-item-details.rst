.. _customization-case-item-details:

Case item details
=================

CaseItemDetails is a built-in component of Portal which contains the
case info which users can interact with. In order to show needed case's
information, Portal supports overriding concept for CaseItemDetails.

Each CaseItemDetails contains

- ``CaseItemDetailsDataAndDescription (1)`` box.

-  ``CaseItemDetailsDocuments (2)`` box.

-  ``CaseItemDetailsRelatedTasks (3)`` box.

-  ``CaseItemDetailsRelatedCases (4)`` box.

-  ``CaseItemDetailsHistories (5)`` box.

-  CaseItemDetail custom panel: caseItemDetailCustomTop,
   caseItemDetailCustomMiddle, caseItemDetailCustomBottom

|case-standard-1|

|case-standard-2|

.. important:: All visible widgets will be configured in :ref:`Global Variable Portal.CaseDetails<case-details-configuration-variable>`.

.. _case-details-configuration-variable:

How to configure widgets in case details
----------------------------------------

-  Settings of all visible widgets on case details page are saved in **Global Variable Portal.CaseDetails**.
-  Cockpit Administrator can configure widgets via global variable **Portal.CaseDetails** on Cockpit settings page.
   |edit-variable-portal-task-case-details|

-  Default configuration includes 5 widgets.

   .. code-block:: html

	{
	  "widgets": 
	  [
	    {
	      "type": "information",
	      "id": "information",
	      "axisX": 0,
	      "axisY": 0,
	      "width": 6,
	      "height": 8
	    },
	    {
	      "type": "document",
	      "id": "document",
	      "axisX": 6,
	      "axisY": 0,
	      "width": 6,
	      "height": 8
	    },
	    {
	      "type": "technicalCase",
	      "id": "technicalCase",
	      "axisX": 0,
	      "axisY": 8,
	      "width": 12,
	      "height": 6
	    },
	    {
	      "type": "relatedTask",
	      "id": "relatedTask",
	      "axisX": 0,
	      "axisY": 14,
	      "width": 12,
	      "height": 6
	    },
	    {
	      "type": "history",
	      "id": "history",
	      "axisX": 0,
	      "axisY": 20,
	      "width": 12,
	      "height": 6
	    }
	  ]
	}

   ..

-  Structure of each widget in variable **Portal.CaseDetails**:

   ``type``: There are 6 types: ``information``, ``document``, ``technicalCase``, ``relatedTask``, ``history``, ``custom``

   ``id``: It's used to detect custom widgets.

   ``axisX``: HTML DOM Style ``left`` will be calculated by formula ``axisX / 12 * 100%``

   ``axisY``: HTML DOM Style ``top`` will be calculated by formula ``axisY / 12 * 100%``

   ``width``: HTML DOM Style ``width`` will be calculated by formula ``60 * width + 20 * (width - 1)``

   ``height``: HTML DOM Style ``height`` will be calculated by formula ``60 * height + 20 * (height - 1)``

   ``styleClass`` (optional): add CSS Classes to HTML DOM

   ``style`` (optional): add inline style to HTML DOM

.. important::
   -  **Do not change** ``type`` and ``id`` of widgets.
      You can change ``axisX``, ``axisY``, ``width`` and ``height`` to update size and position of widgets.
   -  ``axisX``, ``axisY``, ``width`` and ``height`` must be **integers**.
   -  ``axisX + width`` must **not be larger** than **12**.
   
.. _customization-case-item-details-how-to-override-ui:

How to custom Case details UI
-----------------------------

Refer to ``portal-developer-examples`` project for examples.

#. Introduce an Axon Ivy project which has ``PortalTemplate`` as a
   required library.

#. To customize case detail, you must customize Portal Home first.
   Refer to :ref:`Customize Portal
   home <customization-portal-home>` to set new home
   page.

#. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. Point PortalHome element to your custom home page in
   previous step. This process is new home page and administrator should
   register this link by Portal's Admin Settings.

#. Use `Axon Ivy HtmlOverride wizard <https://developer.axonivy.com/doc/9.1/designer-guide/how-to/overrides.html?#override-new-wizard>`_ to override ``CaseInformation`` HTML dialog.

#. After previous steps, you can override Case details UI elements
   as shown/hidden element by keywords:

   To show/hide, please using ``showItemDetailsHeader`` and ``showItemBackButton`` code. For more details, please refer to
   :ref:`Show/Hide
   components <customization-case-details-how-to-override-ui-show-hidden-ui>`.

   And to add a new elements, please refer to :ref:`Add new Custom
   panel <customization-case-item-details-how-to-override-ui-custom-body>`
   code

.. _customization-case-details-how-to-override-ui-show-hidden-ui:

Show/Hide components by keywords
--------------------------------

Refer to the ``ui:param`` tag list in ``CaseInformation.xhtml`` of
PortalTemplate. In case, we want to show/hide any elements on
CaseItemDetails, we should override value of ``ui:param``

List valid parameters:

-  ``ui:param name="showItemDetailsHeader" value="true"``

   To show/hide Case header, by default it's true. You should set as
   false in case we set alwaysShowDetails for CaseItem.

-  ``ui:param name="showItemBackButton" value="true"``

   To show/hide Back button, by default it's true.

.. _customization-case-item-details-how-to-override-ui-custom-body:

Show custom widgets
-------------------


.. tip:: 
      To quickly understand how the JSON of custom case details looks like.
   
      - Refer to ``variables.Portal.CaseDetails.json`` file in ``portal-developer-examples/resources/files`` project.
      - Copy to the corresponding application folder located in the designer.

          - e.g: AxonIvyDesigner/configuration/applications/designer.

      - Create some cases and go to the example homepage by the process ``Start Processes/ExamplePortalStart/DefaultApplicationHomePage.ivp``.
      - And then go to case details to check the new custom layout.
   
      About how to configure Global Var, refer to `Axon Ivy Global Variables <https://developer.axonivy.com/releases/ivy/9.1/documents/designer-guide/configuration/global-variables.html>`_
   


There are **two steps** for adding new custom panels.

1. **Cockpit admin** must configure global variable :ref:`Portal.CaseDetails<case-details-configuration-variable>`
   on Cockpit Page to add custom widgets.

.. _case-details-custom-configuration-variable-example:

-  Example Portal.CaseDetails with including 1 custom widget configuration:

   .. code-block:: html

	{
	  "widgets": 
	  [
	    {
	      "type": "information",
	      "id": "information",
	      "axisX": 0,
	      "axisY": 6,
	      "width": 6,
	      "height": 8
	    },
	    {
	      "type": "document",
	      "id": "document",
	      "axisX": 6,
	      "axisY": 6,
	      "width": 6,
	      "height": 8
	    },
	    {
	      "type": "technicalCase",
	      "id": "technicalCase",
	      "axisX": 0,
	      "axisY": 20,
	      "width": 12,
	      "height": 6
	    },
	    {
	      "type": "relatedTask",
	      "id": "relatedTask",
	      "axisX": 0,
	      "axisY": 26,
	      "width": 12,
	      "height": 6
	    },
	    {
	      "type": "history",
	      "id": "history",
	      "axisX": 0,
	      "axisY": 32,
	      "width": 12,
	      "height": 6
	    },
	    {
	      "type": "custom",
	      "id": "caseItemDetailCustomTop",
	      "axisX": 0,
	      "axisY": 0,
	      "width": 12,
	      "height": 6
	    },
	    {
	      "type": "custom",
	      "id": "caseItemDetailCustomMiddle",
	      "axisX": 0,
	      "axisY": 14,
	      "width": 12,
	      "height": 6
	    },
	    {
	      "type": "custom",
	      "id": "caseItemDetailCustomBottom",
	      "axisX": 0,
	      "axisY": 38,
	      "width": 12,
	      "height": 6
	    }
	  ]
	}

   ..
   
2. Refer to the ``caseItemDetailCustom*`` section in
``CaseInformation.xhtml`` of PortalTemplate.

-  We need to define ``ui:define`` with a valid name such as
   ``caseItemDetailCustomTop``, ``caseItemDetailCustomMiddle`` and
   ``caseItemDetailCustomBottom``.

-  Add your custom code into tags above.

-  Finally, your custom widget will be displayed in :ref:`CaseItemDetails <customization-case-item-details>` page.

-  Below is example code for adding custom widgets to case details

.. code-block:: html

    <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      !!!! START: AREA SHOULD BE CUSTOMIZED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      !!!!!!!! START: TO SHOW /HIDDEN ANY SECTIONS OF CASE DETAILS, YOU CAN TURN TRUE/FALSE FOR BELOW PARAMETERS !!!!!!!!!!!!!!!!!!!!!!!!
      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Attribute showItemDetailsHeader: To show the header of case details. By default it's true
      !!!!!!!! END SHOW /HIDDEN SECTIONS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->

    <ui:param name="id" value="#{cc.clientId}" />
    <ui:param name="showItemDetailsHeader" value="#{cc.attrs.showItemDetailsHeader}" />
    <ui:param name="descriptionComponentToUpdate" value="#{cc.attrs.descriptionComponentToUpdate}" />
    <ui:param name="isWorkingOnTask" value="#{cc.attrs.isWorkingOnTask}" />

    <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      !!!!!!!! START: TO ADD YOUR CUSTOMIZATION CODE ON THE CASE DETAILS PAGE, WE PROVIDE 3 SECTIONS AS BELOW HELP YOU CAN DO IT !!!!!!!!
      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->

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

-  In additional, we have a full flexibility page if we use ``ui-g-*``
   class to define the width of panel


.. |case-standard-1| image:: ../../screenshots/case-detail/customization/case-standard-1.png
.. |case-standard-2| image:: ../../screenshots/case-detail/customization/case-standard-2.png
.. |edit-variable-portal-task-case-details| image:: images/customization/edit-variable-portal-task-case-details.png

