.. _customization-case-widget:

Case widget
===========

CaseWidget is a built-in component of Portal which contains the cases
which users can interact with. In order to show needed case's
information, Portal supports overriding concept for CaseWidget. Each
CaseWidget contains 2 parts:

#. :ref:`UI <customization-case-widget-how-to-override-ui>` :
   CaseListHeader, CaseHeader and CaseFilter

#. :ref:`Data
   query <customization-case-widget-how-to-override-data-query>`
   : display the cases as you want by modifying data query

..

.. important:: 
      - Case header customization currently support responsive design. Refer to :ref:`this part <customization-case-widget-responsive-layout>` for more detail.
   
      - Case header's buttons cannot be modified (they stay where they are)

.. _customization-case-widget-how-to-override-ui:

How to override case widget's UI
--------------------------------

Refer to ``portal-developer-examples`` project for examples

#. Introduce an |axon-ivy| project which has ``PortalTemplate`` as a
   required library.

#. To customize case widget, you must customize Portal Home first. Refer
   to :ref:`Customize Portal
   home <customization-portal-home>` to set new home
   page.

#. Copy the ``PortalStart`` process from ``PortalTemplate`` to your
   project. Point PortalHome element to your custom home page in
   previous step. This process is new home page and administrator should
   register this link by Portal's Admin Settings.

#. Use :dev-url:`Axon Ivy HtmlOverride wizard </doc/nightly/designer-guide/how-to/overrides.html?#override-new-wizard>` to override ``PortalCases`` HTML dialog.

   .. tip:: This action overrides ``Case widget`` in: CaseList page, Case Search result.

#. After previous steps, you can override :ref:`CaseHeader and
   CaseListHeader <customization-case-widget-how-to-override-ui-case-header>`
   and
   :ref:`CaseFilter <customization-case-widget-how-to-override-case-filter>`

.. _customization-case-widget-how-to-override-ui-case-header:

Case List Header and Case Header
--------------------------------

Refer to the ``caseListHeader (1)`` and ``caseHeader (2)`` sections in
``PortalCases.xhtml`` of PortalTemplate. In case your case widget has
new columns, you should override CaseLazyDataModel to make the sort
function of these columns work:

|case-list|

-  Introduce a java class extends CaseLazyDataModel

-  Override the ``extendSort`` method and extend the sort function for
   the added columns (see the method's Javadoc comments)

-  Default caseList supports user to config display/hide column: ``Custom SortFields (1)``, ``Custom Checkboxes (2)`` and ``Custom header column (3)``.

   |case-columns-configuration|

   -  In case you have new columns, override method
      ``getDefaultColumns`` of the extended class from CaseLazyDataModel
      to display checkboxes in Config columns panel and display/hide
      sortFields (see the methods' Javadoc comments)

   -  To add cms for checkboxes's label, add new entries to folder
      ``/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/`` in
      ``PortalStyle`` or override method ``getColumnLabel``\ (see the
      methods' Javadoc comments)

   -  In ``caseListHeader`` section, use ``CaseColumnHeader`` component

   -  In ``caseHeader`` section, use ``CaseCustomField`` component for
      each additional column. This component will handle display/hide
      new columns on case list.

      Currently, CaseCustomField only supports text field. If you want
      to create your own component, remember to add
      ``rendered="#{caseView.dataModel.isSelectedColumn('YOUR_CUSTOM_COLUMN')}"``

      For example: Show custom field ``customer name`` which stored in
      ``case.customVarCharField1``
    
    .. code-block:: html
    
       <ic:ch.ivy.addon.portalkit.component.cases.column.CaseCustomField id="case-customer-name-component" panelGroupId="customVarCharField1-column-case-header-panel" componentId="customVarCharField1-column-case-header-text" column="customVarCharField1" dataModel="#{caseView.dataModel}" labelValue="#{case.customVarCharField1}" />

-  Use |axon-ivy| Override to override the ``InitializeCaseDataModel``
   callable and initialize data model by your customized one.

-  In your customized Portal cases HTMLDialog, the customized data model
   should be passed as a parameter to components (refer to
   ``PortalCases.xhtml``).

.. _customization-case-widget-how-to-override-case-filter:

Case filter
-----------

-  Refer to the ``caseFilter`` section in ``PortalCases.xhtml`` of
   PortalTemplate.

-  In order to introduce new filter, create a new java class extends
   CaseFilter and override its methods (see javadoc comments)

   #. Filter ``label()`` and ``value()`` method.
   #. Filter ``resetValue()`` is called when click on ``X`` icon.
   #. Filter ``validate()`` is called when click on ``Apply`` button.

   |case-filter|

-  Introduce a java class extends CaseFilterContainer. This filter
   container contains your filters, you can reuse default filters, refer
   to ``DefaultCaseFilterContainer.java``

   .. tip:: StateFilter is added as default to container. If you don't need
      it, use this code in constructor: ``filters.remove(stateFilter);``

-  Introduce a java class extends CaseLazyDataModel. Override the
   ``initFilterContainer`` method and initialize filter container (see
   javadoc comments)

-  Use |axon-ivy| Override to override the ``InitializeCaseDataModel``
   callable and initialize data model by your customized one.

-  In your customized Portal cases HTMLDialog, the customized data model
   and filter container should be passed as parameters to components
   (refer to ``PortalCases.xhtml``).

-  Portal supports storing/restoring filters. Your filter class (extends
   ``CaseFilter``) is stored in business data. Properties stored user
   input values should be persisted, properties controlled logic should
   not be persisted to reduce persisted data size in business data. Use
   annotation ``@JsonIgnore`` to exclude properties. By default, Portal
   takes care storing/restoring filters. If you want to customize
   storing/restoring filter data, do it in your data model class
   (extends ``CaseLazyDataModel`` class).

-  By default, filters are stored/restored in process model level. You
   can change this by setting the ui:param ``filterGroupId`` in
   ``PortalCases.xhtml`` to a new Long value.

   .. tip:: If you have multiple case lists in your project, you may want to
      set ``filterGroupId`` to an unique identifier for each of your
      ``PortalCases.xhtml`` across your projects

.. _customization-case-widget-how-to-override-data-query:

How to override case widget's data query
----------------------------------------

Override the ``BuildCaseQuery`` callable process of PortalKit and build
your own query to effect the data of case widget.

Apply the following steps in case you would like to provide data for
case list after navigating to case list from your page:

-  Use the ``OpenPortalCases`` callable process with the ``CaseView``
   parameter. It is used to define which information are displayed in
   CaseWidget.

-  Refer to CaseView, CaseSearchCriteria to build your CaseView

   .. code-block:: java

      CaseLazyDataModel dataModel = new CaseLazyDataModel();
      dataModel.getCriteria().setCustomCaseQuery(YOUR_CASE_QUERY); // Set your CaseQuery
      dataModel.getCriteria().setAdminQuery(true); // Display the cases of all users
      out.caseView = CaseView.create().dataModel(dataModel)
      .withTitle("My Cases").buildNewView();
               
.. _customization-case-widget-how-to-override-export-feature:

How to override export feature
------------------------------

#. Extend the CaseExporter java class of PortalKit.

   -  Override the ``getColumnName`` method.

   .. code-block:: java

      @Override
      protected String getColumnName(String column) {
         String columnName = getSpecialColumnName(column);
         return columnName != null ? columnName : Ivy.cms().co("/DefaultColumns/caseList/" + column);
      }

   -  Override the ``getColumnValue`` method.

   .. code-block:: java

      @Override
      protected Object getColumnValue(String column, ICase caseItem) {
         switch (column) {
            case CustomizedCaseLazyDataModel.CUSTOM_VARCHAR_FIELD1:
               return caseItem.customFields().stringField(CustomFields.CUSTOM_VARCHAR_FIELD1).getOrNull();
            case CustomizedCaseLazyDataModel.CUSTOM_TIMESTAMP_FIELD1:
               return caseItem.customFields().timestampField(CustomFields.CUSTOM_TIMESTAMP_FIELD1).getOrNull();
            default:
               return getCommonColumnValue(column, caseItem);
         }
      }

#. Override the ExportCaseToExcel callable process and apply your extended CaseExporter java class.

   .. code-block:: java

      CaseExporter exporter = new CustomizedCaseExporter(in.columnsVisibility);
      in.exportedFile = exporter.getStreamedContent(in.collectedCasesForExporting);

.. _customization-case-widget-responsive-layout:

How to make responsive case list
--------------------------------

If you have customized case list and want it responsive on different
screen sizes, please follow below steps.

You can refer to ``portal-developer-examples`` project for examples

#. Add responsiveStyleClass param (in case you're using Portal
   component), or styleClass (in case you're using Primefaces or JSF
   component) with the same responsive css class for both caseListHeader
   and caseHeader. You can find responsive class in :ref:`this
   part. <customization-responsive-css>`

   .. code-block:: html
      :emphasize-lines: 4,10,35,41

            <!-- New field -->
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseColumnHeader id="customVarCharField1-column-header"
            styleClass="TexAlCenter customized-case-header-column"
            responsiveStyleClass="u-hidden-lg-down
            js-hidden-when-expand-menu"
            value="#{ivy.cms.co('/DefaultColumns/caseList/customVarCharField1')}" sortedField="customVarCharField1"
            sortable="true" dataModel="#{caseView.dataModel}" />
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseColumnHeader id="customTimestampField1-column-header"
            styleClass="TexAlCenter customized-case-header-column"
            responsiveStyleClass="u-hidden-lg-down 
            js-hidden-when-expand-menu "
            value="#{ivy.cms.co('/DefaultColumns/caseList/customTimestampField1')}" sortedField="customTimestampField1"
            sortable="true" dataModel="#{caseView.dataModel}" />
      </ui:define>

      <ui:define name="caseHeader">
            <div class="case-header-name-desc-cell u-truncate-text">
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseName caseNameId="case-header-name-cell"
            caseDescriptionId="description-cell" case="#{case}" dataModel="#{caseView.getDataModel()}" />
            </div>
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseId componentId="case-id-cell" case="#{case}"
            dataModel="#{caseView.getDataModel()}" />
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseCreator componentId="case-creator-cell" case="#{case}"
            dataModel="#{caseView.getDataModel()}" />
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseDate componentId="case-creation-date-cell"
            rendered="#{caseView.dataModel.isSelectedColumn('CREATION_TIME')}" value="#{case.startTimestamp}" />
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseDate componentId="case-expiry-date-cell"
            rendered="#{caseView.dataModel.isSelectedColumn('FINISHED_TIME')}" value="#{case.endTimestamp}"
            responsiveStyleClass="js-hidden-when-expand-menu u-hidden-md-down" />
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseState componentId="case-state-cell" case="#{case}"
            dataModel="#{caseView.getDataModel()}" />

            <!-- New field -->
            <h:panelGroup styleClass="customized-case-header-column js-hidden-when-expand-menu 
            u-hidden-lg-down"
            rendered="#{caseView.dataModel.isSelectedColumn('customVarCharField1')}">
            <h:outputText value="#{case.customFields().stringField('CustomVarCharField1').getOrNull()}"
            styleClass="case-header-default-cell customized-case-header-column" />
            </h:panelGroup>
            <h:panelGroup styleClass="customized-case-header-column js-hidden-when-expand-menu 
            u-hidden-lg-down"
            rendered="#{caseView.dataModel.isSelectedColumn('customTimestampField1')}">
            <h:outputText value="#{case.customFields().timestampField('CustomTimestampField1').getOrNull()}"
            styleClass="case-header-default-cell">
            <f:convertDateTime pattern="#{dateTimePatternBean.configuredPattern}" />
            </h:outputText>
            </h:panelGroup>
      </ui:define>

   .. tip:: ``CaseCustomField`` component has default
      responsiveStyleClass is ``u-hidden-sm-down``

2. Responsiveness could be broken when you anchor left menu. In this
   case, to maintain the responsiveness, you could hide some columns by
   add ``js-hidden-when-expand-menu`` to responsiveStyleClass or
   styleClass param of caseListHeader and caseHeader.

   .. code-block:: html
      :emphasize-lines: 5,11,35,41

            <!-- New field -->
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseColumnHeader id="customVarCharField1-column-header"
            styleClass="TexAlCenter customized-case-header-column"
            responsiveStyleClass="u-hidden-lg-down
            js-hidden-when-expand-menu"
            value="#{ivy.cms.co('/DefaultColumns/caseList/customVarCharField1')}" sortedField="customVarCharField1"
            sortable="true" dataModel="#{caseView.dataModel}" />
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseColumnHeader id="customTimestampField1-column-header"
            styleClass="TexAlCenter customized-case-header-column"
            responsiveStyleClass="u-hidden-lg-down 
            js-hidden-when-expand-menu "
            value="#{ivy.cms.co('/DefaultColumns/caseList/customTimestampField1')}" sortedField="customTimestampField1"
            sortable="true" dataModel="#{caseView.dataModel}" />
      </ui:define>

      <ui:define name="caseHeader">
            <div class="case-header-name-desc-cell u-truncate-text">
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseName caseNameId="case-header-name-cell"
            caseDescriptionId="description-cell" case="#{case}" dataModel="#{caseView.getDataModel()}" />
            </div>
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseId componentId="case-id-cell" case="#{case}"
            dataModel="#{caseView.getDataModel()}" />
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseCreator componentId="case-creator-cell" case="#{case}"
            dataModel="#{caseView.getDataModel()}" />
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseDate componentId="case-creation-date-cell"
            rendered="#{caseView.dataModel.isSelectedColumn('CREATION_TIME')}" value="#{case.startTimestamp}" />
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseDate componentId="case-expiry-date-cell"
            rendered="#{caseView.dataModel.isSelectedColumn('FINISHED_TIME')}" value="#{case.endTimestamp}"
            responsiveStyleClass="js-hidden-when-expand-menu u-hidden-md-down" />
            <ic:ch.ivy.addon.portalkit.component.cases.column.CaseState componentId="case-state-cell" case="#{case}"
            dataModel="#{caseView.getDataModel()}" />

            <!-- New field -->
            <h:panelGroup styleClass="customized-case-header-column u-hidden-lg-down
            js-hidden-when-expand-menu "
            rendered="#{caseView.dataModel.isSelectedColumn('customVarCharField1')}">
            <h:outputText value="#{case.customFields().stringField('CustomVarCharField1').getOrNull()}"
            styleClass="case-header-default-cell customized-case-header-column" />
            </h:panelGroup>
            <h:panelGroup styleClass="customized-case-header-column u-hidden-lg-down 
            js-hidden-when-expand-menu "
            rendered="#{caseView.dataModel.isSelectedColumn('customTimestampField1')}">
            <h:outputText value="#{case.customFields().timestampField('CustomTimestampField1').getOrNull()}"
            styleClass="case-header-default-cell">
            <f:convertDateTime pattern="#{dateTimePatternBean.configuredPattern}" />
            </h:outputText>
            </h:panelGroup>
      </ui:define>

   .. tip:: The smallest browser width you can anchor the left menu is
          1025. So you could reduce width of browser to 1025 to test and decide which columns need to be hidden.

.. |case-filter| image:: ../../screenshots/case/customization/case-filter.png
.. |case-columns-configuration| image:: ../../screenshots/case/customization/case-columns-configuration.png
.. |case-list| image:: ../../screenshots/case/customization/case-list.png
