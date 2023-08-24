.. _customization-case-item-details:

Case Item Details
=================

CaseItemDetails is a built-in component of the Portal that contains the case
information which users can interact with. To show additional required case
information, the Portal supports overriding the CaseItemDetails.

Each CaseItemDetails contains

- **CaseItemGeneralInformation** ``1``
- **CaseItemDetailsDocuments** ``2``
- **CaseItemDetailsRelatedCases** ``3``
- **CaseItemDetailsRelatedTasks** ``4``
- **CaseItemDetailsHistories** ``5``
-  Case Details custom panel: ``caseItemDetailCustomTop``,
   ``caseItemDetailCustomMiddle``, ``caseItemDetailCustomBottom``

|case-standard-1|

|case-standard-2|

.. important:: All visible widgets are configured in :ref:`variable Portal.CaseDetails<case-details-configuration-variable>`

.. _case-details-configuration-variable:

How to Configure Widgets in Case Details
----------------------------------------

-  Settings of all visible widgets on the Case Details page are saved in the **variable Portal.CaseDetails**.
-  In the Engine Cockpit, an administrator can configure widgets in the variable **Portal.CaseDetails** on the Settings page.
   |edit-variable-portal-task-case-details|

-  The default configuration includes five widgets:

   .. code-block:: html

    [{
       "id": "default-case-detail",
       "widgets": [{
             "type": "information",
             "id": "information",
             "layout": {
                "w": 6, "h": 8, "x": 0, "y": 0
               }
          }, {
             "type": "document",
             "id": "document",
             "layout": {
                "w": 6, "h": 8, "x": 6, "y": 0
               }
          }, {
             "type": "technicalCase",
             "id": "technicalCase",
             "layout": {
                "w": 12, "h": 6, "x": 0, "y": 8
               }
          }, {
             "type": "relatedTask",
             "id": "relatedTask",
             "layout": {
                "w": 12, "h": 6, "x": 0, "y": 14
               }
          }, {
             "type": "history",
             "id": "history",
             "layout": {
                "w": 12, "h": 6, "x": 0, "y": 20
               }
            }
          ]
       }
    ]


   -  The structure of each Case Details layout is saved in variable **Portal.CaseDetails**:

      ``id``: ID of layout.

      ``widgets``: definition of widgets in layout.

      ``filters``: conditions to determine which cases are eligible to use the layout. There are two types of case filters:
      
      -  ``categories`` (case categories)
      -  ``states`` (case business states).

         Refer to :dev-url:`Case business states </doc/|version|/public-api/ch/ivyteam/ivy/workflow/caze/CaseBusinessState.html>` for
         available case business states.

   -  The structure of each widget inside the case details layout is stored in variable **Portal.CaseDetails**:

      ``id``: ID of widget.

      ``type``: type of widget, there are 6 types: 
      -  ``information``
      -  ``document``
      -  ``technicalCase``
      -  ``relatedTask``
      -  ``history``
      -  ``custom``.

      ``layout``: defines the UI styling of the widget.

         ``x``: HTML DOM Style ``left`` is computed as ``x / 12 * 100%``.

         ``y``: HTML DOM Style ``top`` is computed as ``y / 12 * 100%``.

         ``w``: HTML DOM Style ``width`` is computed as ``60 * w + 20 * (width - 1)``.

         ``h``: HTML DOM Style ``height`` is computed as ``60 * h + 20 * (height - 1)``.

         ``styleClass`` (optional): add CSS Classes to HTML DOM.

         ``style`` (optional): add inline style to HTML DOM.

      ``data`` (for custom widget): data for custom widget, refer to :ref:`Show custom widgets <customization-case-item-details-how-to-override-ui-custom-body>`

   .. important::

      - **Do not change** ``type`` and ``id`` of widgets.
      - You can change ``x``, ``y``, ``w`` and ``h`` to update size and position of widgets.
      - ``x``, ``y``, ``w`` and ``h`` have to be **integers**.
      - ``x + w`` must **not be larger** than **12**.
      - We support all case business states in filter type ``states``.


.. _customization-case-details-how-to-override-ui-show-hidden-ui:

Show/Hide Components by Keywords
--------------------------------

Refer to the ``ui:param`` tag list in ``CaseInformation.xhtml`` of
``portal``. In case, we want to show/hide any elements on
CaseItemDetails, we should override value of ``ui:param``

List valid parameters:

-  ``ui:param name="showItemDetailsHeader" value="true"`` to show/hide case header, by default it's true, you should set as
   ``false`` if you set **alwaysShowDetails** for **CaseItem**.

-  ``ui:param name="showItemBackButton" value="true"`` to show/hide back button, by default it's true.

.. _customization-case-item-details-how-to-override-ui-custom-body:

Show custom widgets
-------------------

For the custom widget, we have a new node is ``data`` inside of case details widget. The structure of ``data`` on each custom widget will be:

   - ``type``: type of custom widget panel, there are 3 types: ``caseItemDetailCustomTop``, ``caseItemDetailCustomMiddle``, ``caseItemDetailCustomBottom``.

   - ``url``: URL for external website

   - ``processPath``: the user-friendly request path of the Ivy process which will be displayed in custom widget

   - ``params``: parameters for the Ivy process above, each parameter can be defined as follows:

      - Key name that will be the parameter name for the Ivy process above. Note: don't use ``caseId``.

      - The key value for the case has to start with ``case.``. Supported are two values: ``case.id``, ``case.category``.

      - Key value for case custom fields: must start with ``case.customFields.``, followed by custom field name.

      - Other key values will be treated as a hard coded value.

.. tip:: 
      To quickly understand how the JSON of custom case details looks like.
   
      - Refer to ``variables.Portal.CaseDetails.json`` file in ``portal-developer-examples/resources/files`` project.
      - Copy to the corresponding application folder located in the designer.

          - e.g: AxonIvyDesigner/configuration/applications/designer.

      - Create some destroyed case or start the process ``Start Processes/CaseDetailsCustomWidgetExample/CreateEventTest.ivp`` in ``portal-developer-examples`` project.
      - Go to the example homepage by the process ``Start Processes/ExamplePortalStart/DefaultApplicationHomePage.ivp``.
      - And then go to case details to check the new custom layout.
   
      About how to configure Variables, refer to :dev-url:`|ivy| Variables </doc/|version|/designer-guide/configuration/variables.html>`
   

.. |case-standard-1| image:: ../../screenshots/case-detail/customization/case-standard-1.png
.. |case-standard-2| image:: ../../screenshots/case-detail/customization/case-standard-2.png
.. |edit-variable-portal-task-case-details| image:: images/customization/edit-variable-portal-task-case-details.png