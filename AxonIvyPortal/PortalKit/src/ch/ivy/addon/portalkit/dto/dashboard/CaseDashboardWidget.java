package ch.ivy.addon.portalkit.dto.dashboard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.bo.CaseCategoryStatistic;
import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.datamodel.DashboardCaseLazyDataModel;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CreatedDateColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CreatorColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.DescriptionColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.FinishedDateColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.IdColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.NameColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.OwnerColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.StateColumnModel;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardCaseSearchCriteria;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivyteam.ivy.workflow.CaseState;

public class CaseDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;
  private static final String CRITERIA_PARAM = "caseSearchCriteria";

  private int rowsPerPage = 10;
  @JsonIgnore
  private DashboardCaseLazyDataModel dataModel;
  @JsonIgnore
  private CheckboxTreeNode categoryTree;
  @JsonIgnore
  private CheckboxTreeNode[] categoryNodes;
  @JsonIgnore
  private Map<CaseState, Long> caseByStateStatistic;
  @JsonIgnore
  private Map<String, Long> caseByCategoryStatistic;

  public CaseDashboardWidget() {
    dataModel = new DashboardCaseLazyDataModel();
    setColumns(new ArrayList<>());
    caseByCategoryStatistic = new HashMap<>();
    caseByStateStatistic = new HashMap<>();
  }

  public CheckboxTreeNode[] getCategoryNodes() {
    return categoryNodes;
  }

  public void setCategoryNodes(CheckboxTreeNode[] categoryNodes) {
    this.categoryNodes = categoryNodes;
    setUserFilterCategories(CategoryUtils.getCategoryPaths(categoryNodes));
  }

  public CheckboxTreeNode getCategoryTree() {
    return categoryTree;
  }

  public void setCategoryTree(CheckboxTreeNode categoryTree) {
    this.categoryTree = categoryTree;
  }

  public void buildCategoryTree() {
    this.categoryTree = CaseTreeUtils.buildCaseCategoryCheckboxTreeRoot();
    CategoryUtils.disableSelectionExcept(this.categoryTree, getCategories());
    if (CollectionUtils.isNotEmpty(getUserFilterCategories())) {
      CategoryUtils.recoverSelectedCategories(this.categoryTree, getUserFilterCategories());
    }
  }

  @Override
  public void buildStatisticInfos() throws ParseException {
    buildCaseByStateStatistic();
    buildCaseByCategoryStatistic();
  }

  private void buildCaseByStateStatistic() throws ParseException {
    Map<String, Object> params = new HashMap<>();
    params.put(CRITERIA_PARAM, generateCaseSearchCriteriaWithoutOrderByClause());

    Map<String, Object> response = IvyAdapterService.startSubProcess("analyzeCaseStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)", params,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));

    CaseStateStatistic caseStateStatistic = (CaseStateStatistic) response.get("caseStateStatistic");
    Map<CaseState, Long> result = new HashMap<>();
    Optional<CaseColumnModel> caseStateColumn = getColumns().stream()
        .filter(column -> DashboardStandardCaseColumn.STATE.getField().equalsIgnoreCase(column.getField())).findFirst();
    List<CaseState> selectedStates = null;
    if (caseStateColumn.isPresent()) {
      selectedStates = ((StateColumnModel) caseStateColumn.get()).getStates();
      for (CaseState state : selectedStates) {
        switch (state) {
          case CREATED:
            result.put(CaseState.CREATED, caseStateStatistic.getCreated());
            break;
          case DONE:
            result.put(CaseState.DONE, caseStateStatistic.getDone());
            break;
          case RUNNING:
            result.put(CaseState.RUNNING, caseStateStatistic.getRunning());
            break;
          case DESTROYED:
            result.put(CaseState.DESTROYED, caseStateStatistic.getFailed());
            break;
          default:
            break;
        }
      }
    }

    if (!caseStateColumn.isPresent() || CollectionUtils.isEmpty(selectedStates)) {
      result.put(CaseState.CREATED, caseStateStatistic.getCreated());
      result.put(CaseState.DONE, caseStateStatistic.getDone());
      result.put(CaseState.DESTROYED, caseStateStatistic.getFailed());
      result.put(CaseState.RUNNING, caseStateStatistic.getRunning());
    }

    caseByStateStatistic = result.entrySet()
            .stream().sorted(Comparator.comparingInt(s -> s.getKey().ordinal()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

  }

  private void buildCaseByCategoryStatistic() throws ParseException {
    Map<String, Object> params = new HashMap<>();
    params.put(CRITERIA_PARAM, generateCaseSearchCriteriaWithoutOrderByClause());

    Map<String, Object> response = IvyAdapterService.startSubProcess("analyzeCaseCategoryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)",
        params, Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));

    CaseCategoryStatistic caseCategoryStatistic = (CaseCategoryStatistic) response.get("caseCategoryStatistic");
    caseByCategoryStatistic.putAll(caseCategoryStatistic.getNumberOfCasesByCategory());
  }

  private CaseSearchCriteria generateCaseSearchCriteriaWithoutOrderByClause() throws ParseException {
    CaseSearchCriteria caseSearchCriteria = new CaseSearchCriteria();
    caseSearchCriteria.setFinalCaseQuery(dataModel.getCriteria().buildQueryWithoutOrderByClause());
    return caseSearchCriteria;
  }

  @JsonIgnore
  public List<ColumnModel> getFilterableColumns() {
    return getColumns().stream()
        .filter(col -> !StringUtils.equalsIgnoreCase(col.getField(), DashboardStandardCaseColumn.ID.toString()))
        .collect(Collectors.toList());
  }

  public List<String> getCategories() {
    return this.dataModel.getCategories();
  }

  public void setCategories(List<String> categories) {
    this.dataModel.setCategories(categories);
  }

  @JsonIgnore
  public String getDisplayCategories() {
    return Optional.ofNullable(getCategories()).orElse(new ArrayList<>()).stream().collect(Collectors.joining(", "));
  }

  @JsonIgnore
  public List<String> getUserFilterCategories() {
    return this.dataModel.getUserFilterCategories();
  }

  public void setUserFilterCategories(List<String> categories) {
    this.dataModel.setUserFilterCategories(categories);
  }

  @JsonIgnore
  public String getUserFilterDisplayCategories() {
    return Optional.ofNullable(getUserFilterCategories()).orElse(new ArrayList<>()).stream()
        .collect(Collectors.joining(", "));
  }

  public String getSortField() {
    return this.dataModel.getCriteria().getSortField();
  }

  public void setSortField(String sortField) {
    this.dataModel.getCriteria().setSortField(sortField);
  }

  public boolean isSortDescending() {
    return this.dataModel.getCriteria().isSortDescending();
  }

  public void setSortDescending(boolean sortDescending) {
    this.dataModel.getCriteria().setSortDescending(sortDescending);
  }

  public DashboardCaseLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(DashboardCaseLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public List<CaseColumnModel> getColumns() {
    return this.dataModel.getCriteria().getColumns();
  }

  public void setColumns(List<CaseColumnModel> columns) {
    this.dataModel.getCriteria().setColumns(columns);
  }

  @JsonIgnore
  public int getCaseCount() {
    return getDataModel().getRowCount();
  }

  @JsonIgnore
  public boolean isInConfiguration() {
    return this.dataModel.getCriteria().isInConfiguration();
  }

  @JsonIgnore
  public void setInConfiguration(boolean isInConfiguration) {
    this.dataModel.getCriteria().setInConfiguration(isInConfiguration);
  }

  @JsonIgnore
  public Map<CaseState, Long> getCaseByStateStatistic() {
    return caseByStateStatistic;
  }

  @JsonIgnore
  public Map<String, Long> getCaseByCategoryStatistic() {
    return caseByCategoryStatistic;
  }

  @Override
  @JsonIgnore
  public void onApplyUserFilters() throws ParseException {
    super.onApplyUserFilters();
    this.userDefinedFiltersCount = countDefinedUserFilter(this);
  }

  @JsonIgnore
  public static CaseDashboardWidget buildDefaultWidget(String id, String name) {
    CaseDashboardWidget widget = new CaseDashboardWidget();
    widget.setId(id);
    widget.setName(name);

    WidgetLayout layout = new WidgetLayout();
    layout.setWidth(10);
    layout.setHeight(9);
    widget.setLayout(layout);

    widget.setAutoPosition(true);
    widget.setSortField(CaseSortField.ID.toString());
    widget.setSortDescending(true);
    widget.setColumns(initStandardColumns());
    return buildColumns(widget);
  }
  
  @JsonIgnore
  public static CaseDashboardWidget buildColumns(CaseDashboardWidget caseWidget) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    List<CaseColumnModel> columns = caseWidget.getColumns();

    for (int i = 0; i < columns.size(); i++) {
      CaseColumnModel column = columns.get(i);
      String field = column.getField();
      if (DashboardStandardCaseColumn.ID.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, IdColumnModel.class);
      } else if (DashboardStandardCaseColumn.NAME.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, NameColumnModel.class);
      } else if (DashboardStandardCaseColumn.DESCRIPTION.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, DescriptionColumnModel.class);
      } else if (DashboardStandardCaseColumn.CREATOR.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, CreatorColumnModel.class);
      } else if (DashboardStandardCaseColumn.STATE.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, StateColumnModel.class);
      } else if (DashboardStandardCaseColumn.CREATED.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, CreatedDateColumnModel.class);
      } else if (DashboardStandardCaseColumn.FINISHED.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, FinishedDateColumnModel.class);
      } else if (DashboardStandardCaseColumn.OWNER.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, OwnerColumnModel.class);
      }
      column.initDefaultValue();
      columns.set(i, column);
    }
    return caseWidget;
  }

  @JsonIgnore
  public static List<CaseColumnModel> initStandardColumns() {
    List<CaseColumnModel> columnModels = new ArrayList<>();
    for (DashboardStandardCaseColumn col : DashboardStandardCaseColumn.values()) {
      CaseColumnModel columnModel = new CaseColumnModel();
      columnModel.setField(col.getField());
      columnModels.add(columnModel);
    }
    return columnModels;
  }

  @JsonIgnore
  public static boolean hasPredefinedFilter(CaseDashboardWidget widget) throws ParseException {
    List<ColumnModel> filterableColumns = widget.getFilterableColumns();

    if (CollectionUtils.isEmpty(filterableColumns)) {
      return false;
    }
    for (ColumnModel col : filterableColumns) {
      if (col instanceof StateColumnModel && !CollectionUtils.isEmpty(((StateColumnModel) col).getStates())) {
        return true;
      }
      else if (col instanceof CreatorColumnModel && !CollectionUtils.isEmpty(((CreatorColumnModel) col).getCreators())) {
        return true;
      }
      else if (col instanceof OwnerColumnModel && !CollectionUtils.isEmpty(((OwnerColumnModel) col).getOwners())) {
        return true;
      }
      else if ((col.getFormat() == DashboardColumnFormat.TEXT || col.getFormat() == DashboardColumnFormat.STRING)
          && !(CollectionUtils.isEmpty(col.getFilterList()) && StringUtils.isBlank(col.getFilter()))) {
        return true;
      }
      else if (col.getFormat() == DashboardColumnFormat.NUMBER
          && !(StringUtils.isBlank(col.getFilterFrom()) && StringUtils.isBlank(col.getFilterTo()))) {
        return true;
      }
      else if (col.getFormat() == DashboardColumnFormat.TIMESTAMP
          && !(col.getDateFilterFrom() == null && col.getDateFilterTo() == null)) {
        return true;
      }
    }
    if (CollectionUtils.isNotEmpty(widget.getCategories())) {
      return true;
    }
    return false;
  }

  @Override
  public void buildPredefinedFilterData() throws ParseException {
    setHasPredefinedFilter(hasPredefinedFilter(this));
  }

  @JsonIgnore
  public static Optional<String> countDefinedUserFilter(CaseDashboardWidget widget) throws ParseException {
    int numberOfFilters = 0;
    List<ColumnModel> filterableColumns = widget.getFilterableColumns();
    if (CollectionUtils.isEmpty(filterableColumns)) {
      return Optional.empty();
    }
    for (ColumnModel col : filterableColumns) {
      if (StringUtils.isNotEmpty(col.getUserFilter()) || StringUtils.isNotEmpty(col.getUserFilterFrom())
          || col.getUserDateFilterFrom() != null || CollectionUtils.isNotEmpty(col.getUserFilterList())) {
        numberOfFilters++;
      }
      if (StringUtils.isNotEmpty(col.getUserFilterTo()) || col.getUserDateFilterTo() != null) {
        numberOfFilters++;
      }
      if (numberOfFilters > MAX_NOTI_FILTERS) {
        break;
      }
    }
    if (CollectionUtils.isNotEmpty(widget.getDataModel().getCriteria().getUserFilterCategories())
        && numberOfFilters < MAX_NOTI_FILTERS) {
      numberOfFilters++;
    }

    if (numberOfFilters > MAX_NOTI_FILTERS) {
      return Optional.of(String.format(MAX_NOTI_PATTERN, MAX_NOTI_FILTERS));
    }

    if (numberOfFilters == 0) {
      return Optional.empty();
    }

    return Optional.of(String.valueOf(numberOfFilters));
  }

  @Override
  @JsonIgnore
  public void resetWidgetFilters() {
    for (ColumnModel column : this.getColumns()) {
      column.setUserFilter(StringUtils.EMPTY);
      column.setUserFilterList(new ArrayList<>());
      column.setUserFilterFrom(StringUtils.EMPTY);
      column.setUserFilterTo(StringUtils.EMPTY);
      column.setUserDateFilterFrom(null);
      column.setUserDateFilterTo(null);
    }
    if (Optional.ofNullable(dataModel)
        .map(DashboardCaseLazyDataModel::getCriteria)
        .map(DashboardCaseSearchCriteria::getUserFilterCategories).isPresent()) {
      dataModel.getCriteria().getUserFilterCategories().clear();
    }
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.CASE;
  }

  public int getRowsPerPage() {
    return rowsPerPage;
  }

  public void setRowsPerPage(int rowsPerPage) {
    this.rowsPerPage = rowsPerPage;
  }
}
