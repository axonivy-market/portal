.. _customization-case-widget:

Case Widget
===========

CaseWidget is a built-in component of the Portal that contains the cases which
users can interact with. To show needed case information, the Portal allows to
override the CaseWidget. Each CaseWidget consists of two parts:

#. :ref:`UI <customization-case-widget-how-to-override-ui>` :
   CaseListHeader, CaseHeader and CaseFilter

#. :ref:`Data
   query <customization-case-widget-how-to-override-data-query>`
   : select and display the cases you need by modifying the data query

..

.. important::
      - The case header customization currently supports responsive design. Refer to :ref:`this part <customization-case-widget-responsive-layout>` for details.

      - The case header buttons cannot be modified (they stay where they are).

.. _customization-case-widget-how-to-override-ui:

How to Override the Case Widget UI
----------------------------------

We provide a code sample in ``portal-developer-examples``.

Follow these steps to override the case Widget UI:

#. Create an Axon Ivy project and add ``portal`` as a
   required library.

#. To customize the case widget, you have to customize Portal Home first. Refer to
   :ref:`Customize Portal home <customization-portal-home>` to create and set a new home
   page.

#. Copy the ``PortalStart`` process from ``portal`` to your project.
   Point the PortalHome element to your custom home page created in the previous
   step. This process is the new home page. The Portal administrator has to
   register this link in the Portal Admin Settings.

#. Use :dev-url:`Axon Ivy HtmlOverride wizard
   </doc/9.4.0-m229/designer-guide/how-to/overrides.html?#override-new-wizard>` to
   override the ``PortalCases`` HTML dialog.

   .. tip:: This action overrides ``Case widget`` in the CaseList page and the Case Search result page.

#. After you completed these steps, you can override :ref:`CaseHeader and
   CaseListHeader <customization-case-widget-how-to-override-ui-case-header>`
   and
   :ref:`CaseFilter <customization-case-widget-how-to-override-case-filter>`

.. _customization-case-widget-how-to-override-ui-case-header:

Case List Header and Case Header
--------------------------------

Refer to the ``caseListHeader (1)`` and ``caseHeader (2)`` sections in
``PortalCases.xhtml`` in ``portal``. If your case widget has
new columns, you have to override the CaseLazyDataModel to make the sort
function of these columns work:

|case-list|

-  Create a Java class that which extends CaseLazyDataModel

-  Override the ``extendSort`` method and extend the sort function for
   the added columns (see the method's Javadoc comments).

-  The default case list allows the user to configure display or hide the following columns:
   -  ``Custom SortFields (1)``
   -  ``Custom Checkboxes (2)``
   -  ``Custom header column (3)``.

   |case-columns-configuration|

   -  If you added new columns, override method ``getDefaultColumns`` of the
      extended class from CaseLazyDataModel to display checkboxes in the
      configure columns panel and hide or display sortFields (see the methods'
      Javadoc comments).

   -  To add a CMS for checkbox label, add new entries to folder
      ``/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/`` in
      ``portal`` or override method ``getColumnLabel``\ (see the methods'
      Javadoc comments)

   -  In the ``caseListHeader`` section, use the ``CaseColumnHeader`` component

   -  In the ``caseHeader`` section, use the ``CaseCustomField`` component for
      each additional column. This component will handle display/hide new
      columns on case list.

      Currently, CaseCustomField only supports text fields. If you want to create
      your own component, remember to add
      ``rendered="#{caseView.dataModel.isSelectedColumn('YOUR_CUSTOM_COLUMN')}"``

      To show the custom field ``customer name`` stored in
      ``case.customFields().stringField('CustomVarCharField1')``

    .. code-block:: html

       <ic:ch.ivy.addon.portalkit.component.cases.column.CaseCustomField id="case-customer-name-component" panelGroupId="customVarCharField1-column-case-header-panel" componentId="customVarCharField1-column-case-header-text" column="customVarCharField1" dataModel="#{caseView.dataModel}" labelValue="#{case.customFields().stringField('CustomVarCharField1')}" />

-  Use Axon Ivy Override to override the ``InitializeCaseDataModel`` callable
   and initialize the data model with your customized one.

-  In your customized Cases HTMLDialog, the customized data model
   has to be passed as a parameter to components (refer to
   ``PortalCases.xhtml``).

.. _customization-case-widget-how-to-override-case-filter:

Case Filter
-----------

-  Refer to the ``caseFilter`` section in ``PortalCases.xhtml`` of ``portal``.

-  To add a new filter, create a new Java class which extends
   CaseFilter and overrides its methods (see javadoc comments)

   #. Filter ``label()`` and ``value()`` method.
   #. Filter ``resetValue()`` is called when click on ``X`` icon.
   #. Filter ``validate()`` is called when click on ``Apply`` button.

   |case-filter|

-  Introduce a Java class which extends CaseFilterContainer. This filter
   container contains your filters, you can reuse default filters, refer
   to ``DefaultCaseFilterContainer.java``

   .. tip:: StateFilter is added as a default to the case filter container. If you don't need
      it, use this code in constructor: ``filters.remove(stateFilter);``

-  Create a Java class which extends CaseLazyDataModel. Override the
   ``initFilterContainer`` method and initialize filter container (see
   javadoc comments)

-  Use Axon Ivy Override to override the ``InitializeCaseDataModel``
   callable and initialize data model by your customized one.

-  In your customized Cases HTMLDialog, the customized data model
   and filter container have to be passed as parameters to components
   (refer to ``PortalCases.xhtml``).

-  Portal supports storing and loading filters. Your filter class (that extends
   ``CaseFilter``) is stored in business data. Persist properties that store
   user input values. Do not persist properties controlled by logic, as this
   would unnecessarily increase the amount of data stored in business data. Use
   annotation ``@JsonIgnore`` to exclude properties. By default, Portal takes
   care of storing and loading filters. If you want to customize the persisted
   filter data, do it in your data model class (which extends
   ``CaseLazyDataModel``).

-  By default, filters are stored and loaded at process model level. You
   can change this by setting the ui:param ``filterGroupId`` in
   ``PortalCases.xhtml`` to a new Long value.

   .. tip:: If you have multiple case lists in your project, you may want to
      set ``filterGroupId`` to a unique identifier for each of your
      ``PortalCases.xhtml`` across your projects

.. _customization-case-widget-how-to-override-data-query:

How to Override Case Widget's Data Query
----------------------------------------

Override the ``BuildCaseQuery`` callable process of ``portal`` to build
a query that changes the data passed to the case widget.

To provide data to the case list after navigating to case list from your page,
apply the following steps:

-  Use the ``OpenPortalCases`` callable process with the ``CaseView``
   parameter. It is used to define which information is displayed in
   CaseWidget.

-  Refer to CaseView, CaseSearchCriteria to build your CaseView

   .. code-block:: java

      CaseLazyDataModel dataModel = new CaseLazyDataModel();
      dataModel.getCriteria().setCustomCaseQuery(YOUR_CASE_QUERY); // Set your CaseQuery
      dataModel.getCriteria().setAdminQuery(true); // Display the cases of all users
      out.caseView = CaseView.create().dataModel(dataModel)
      .withTitle("My Cases").buildNewView();

.. _customization-case-widget-how-to-override-export-feature:

How to Override the Export Feature
----------------------------------

#. Extend the CaseExporter Java class of ``portal``.

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

#. Override the ExportCaseToExcel callable process and apply your extended CaseExporter Java class.

   .. code-block:: java

      CaseExporter exporter = new CustomizedCaseExporter(in.columnsVisibility);
      in.exportedFile = exporter.getStreamedContent(in.collectedCasesForExporting);

.. _customization-case-widget-responsive-layout:

How to Make a Responsive Case List
----------------------------------

If you have customized the case list and want it to be responsive, implement the following steps.
You can refer to ``portal-developer-examples`` project for examples.

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

   .. tip:: The ``CaseCustomField`` component has a default
      responsiveStyleClass ``u-hidden-sm-down``

2. Anchoring the left menu breaks responsiveness. To maintain the
   responsiveness, you could hide some columns by adding
   ``js-hidden-when-expand-menu`` to the responsiveStyleClass or styleClass parameter of
   caseListHeader and caseHeader.

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

   .. tip:: The smallest browser width you can anchor the left menu is 1025.
      To test which columns need to be hidden, reduce your window's width to 1025.

.. |case-filter| image:: ../../screenshots/case/customization/case-filter.png
.. |case-columns-configuration| image:: ../../screenshots/case/customization/case-columns-configuration.png
.. |case-list| image:: ../../screenshots/case/customization/case-list.png
