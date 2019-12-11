package ch.ivy.addon.portalkit.datamodel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.CaseColumnsConfiguration;
import ch.ivy.addon.portalkit.casefilter.CaseCategoryFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.casefilter.DefaultCaseFilterContainer;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.service.CaseColumnsConfigurationService;
import ch.ivy.addon.portalkit.service.CaseFilterService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseLazyDataModel extends LazyDataModel<ICase> {
  private static final long serialVersionUID = 1L;

  protected final List<ICase> data;

  protected String caseWidgetComponentId;
  protected int rowIndex;
  protected CaseSearchCriteria criteria;

  protected List<CaseFilter> filters;
  protected List<CaseFilter> selectedFilters;
  protected CaseFilterContainer filterContainer;
  protected CaseFilterData selectedFilterData;
  protected boolean isNotKeepFilter = false;

  protected List<String> allColumns = new ArrayList<>();
  protected List<String> selectedColumns = new ArrayList<>();
  private List<String> portalDefaultColumns = Arrays.asList("NAME", "ID", "CREATOR", "CREATION_TIME", "FINISHED_TIME", "STATE");
  private List<String> portalRequiredColumns = Arrays.asList("NAME");

  private boolean isAutoHideColumns;
  private boolean isDisableSelectionCheckboxes;
  private CaseFilter selectedCaseFilter;

  public CaseLazyDataModel() {
    this("case-widget");
  }

  public CaseLazyDataModel(String caseWidgetComponentId) {
    super();
    data = new ArrayList<>();
    selectedFilters = new ArrayList<>();
    this.caseWidgetComponentId = caseWidgetComponentId;
    buildCriteria();
    setAdminQuery(PermissionUtils.checkReadAllCasesPermission());
    selectedFilterData = UserUtils.getSessionSelectedCaseFilterSetAttribute();
  }

  @Override
  public List<ICase> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    if (selectedCaseFilter != null && !selectedCaseFilter.reloadViewContainer()) {
      selectedCaseFilter = null;
      return data;
    }

    if (first == 0) {
      initializedDataModel();
      PrimeFaces.current().executeScript("updateCaseCount()");
    }
    
    List<ICase> foundCases = findCases(criteria, first, pageSize);
    data.addAll(foundCases);
    return foundCases;
  }

  public void initFilters() throws ReflectiveOperationException {
    if (filterContainer == null) {
      initColumnsConfiguration();
      initFilterContainer();
      filters = filterContainer.getFilters();
      setValuesForCaseStateFilter(criteria);
      restoreSessionAdvancedFilters();
    }
  }

  @SuppressWarnings("unchecked")
  public void onFilterChange(ValueChangeEvent event) {
    List<CaseFilter> oldSelectedFilters = (List<CaseFilter>) event.getOldValue();
    List<CaseFilter> newSelectedFilters = (List<CaseFilter>) event.getNewValue();
    List<CaseFilter> toggleFilters = null;
    if (newSelectedFilters.size() > oldSelectedFilters.size()) {
      toggleFilters = (List<CaseFilter>) CollectionUtils.subtract(newSelectedFilters, oldSelectedFilters);
    } else {
      toggleFilters = (List<CaseFilter>) CollectionUtils.subtract(oldSelectedFilters, newSelectedFilters);
    }

    if (CollectionUtils.isNotEmpty(toggleFilters)) {
      selectedCaseFilter = toggleFilters.get(0);
      toggleFilters.get(0).resetValues();
    }
    resetFilterData();
  }

  public void onKeywordChange() {
    resetFilterData();
  }

  private void resetFilterData() {
    if (this.selectedFilterData != null) {
      this.selectedFilterData = null;
    }
  }

  public void removeFilter(CaseFilter filter) {
    selectedCaseFilter = filter;
    filter.resetValues();
    selectedFilters.remove(filter);
    resetFilterData();
  }

  public void resetFilters() {
    for (CaseFilter selectedFilter : selectedFilters) {
      selectedFilter.resetValues();
    }
    selectedFilters = new ArrayList<>();
    selectedFilterData = null;
  }

  public void setSorting(String sortedField, boolean descending) {
    criteria.setSortField(sortedField);
    criteria.setSortDescending(descending);
  }

  public void setAdminQuery(boolean isAdminQuery) {
    criteria.setAdminQuery(isAdminQuery);
    if (isAdminQuery && !criteria.getIncludedStates().contains(CaseState.DONE)) {
      criteria.addIncludedStates(Arrays.asList(CaseState.DONE));
      setValuesForCaseStateFilter(criteria);
    }
  }

  public void setCaseId(Long caseId) {
    criteria.setCaseId(caseId);
    criteria.setIncludedStates(new ArrayList<>());
    setValuesForCaseStateFilter(criteria);
  }

  /**
   * Save all filter settings to business data
   * 
   * @param filterName
   * @param filterType
   * @param filterGroupId
   * @return saved CaseFilterData
   */
  public CaseFilterData saveFilter(String filterName, FilterType filterType, Long filterGroupId) {
    CaseFilterData filterData = new CaseFilterData();
    List<CaseFilter> filtersToSave = new ArrayList<>(selectedFilters);
    filterData.setFilters(filtersToSave);
    filterData.setKeyword(criteria.getKeyword());
    filterData.setUserId(Ivy.session().getSessionUser().getId());
    filterData.setFilterGroupId(filterGroupId);
    filterData.setFilterName(filterName);
    filterData.setType(filterType);
    CaseFilterService filterService = new CaseFilterService();
    BusinessDataInfo<CaseFilterData> info = filterService.save(filterData);
    filterData = filterService.findById(info.getId());
    UserUtils.setSessionSelectedCaseFilterSetAttribute(filterData);
    return filterData;
  }

  /**
   * Apply filter settings loaded from business data to this {@link #CaseLazyDataModel}
   * 
   * @param caseFilterData
   * @throws ReflectiveOperationException
   */
  public void applyFilter(CaseFilterData caseFilterData) throws ReflectiveOperationException {
    selectedFilterData = caseFilterData;
    new CaseFilterService().applyFilter(this, caseFilterData);
    applyCustomSettings(caseFilterData);
  }

  /**
   * <p>
   * Initialize CaseFilterContainer with your customized CaseFilterContainer class.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * filterContainer = new CustomizedCaseFilterContainer();
   * </pre></code>
   * </p>
   */
  protected void initFilterContainer() {
    filterContainer = new DefaultCaseFilterContainer();
  }

  protected void setInvolvedApplications() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    criteria.setApps(service.findActiveIvyAppsBasedOnConfiguration(Ivy.session().getSessionUserName()));
  }

  /**
   * Builds and converts CaseQuery to JsonQuery and put it into CaseSearchCriteria.
   */
  protected void buildQueryToSearchCriteria() {
    if (criteria.getCustomCaseQuery() == null) {
      CaseQuery customCaseQuery = SubProcessCall.withPath(PortalConstants.BUILD_CASE_QUERY_CALLABLE)
          .withStartSignature("buildCaseQuery()")
          .call()
          .get("caseQuery", CaseQuery.class);
      criteria.setCustomCaseQuery(customCaseQuery);
    }
    
    if (filterContainer != null) {
      if (selectedFilters.contains(filterContainer.getStateFilter())) {
        criteria.setIncludedStates(new ArrayList<>());
      } else {
        criteria.setIncludedStates(filterContainer.getStateFilter().getSelectedFilteredStates());
      }
    }
    CaseQuery caseQuery = buildCaseQuery();
    extendSort(caseQuery);
    this.criteria.setFinalCaseQuery(caseQuery);
  }

  /**
   * <p>
   * If your customized case list has new columns/fields, please extend the {@code caseQuery} parameter with the sort
   * query for these fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * if ("CustomVarCharField5".equalsIgnoreCase(criteria.getSortField())) {
   *   if (criteria.isSortDescending()) {
   *     caseQuery.orderBy().customField().stringField("CustomVarCharField5").descending();
   *   } else {
   *     caseQuery.orderBy().customField().stringField("CustomVarCharField5");
   *   }
   * }
   * </pre></code>
   * </p>
   * 
   * @param caseQuery
   */
  protected void extendSort(@SuppressWarnings("unused") CaseQuery caseQuery) {
    // Placeholder for customization
  }

  private CaseQuery buildCaseQuery() {
    CaseQuery caseQuery = criteria.createQuery();
    IFilterQuery filterQuery = caseQuery.where();
    selectedFilters.forEach(selectedFilter -> {
      CaseQuery subQuery = selectedFilter.buildQuery();
      if (subQuery != null) {
        filterQuery.and(subQuery);
      }
    });
    if (!isNotKeepFilter) {
      UserUtils.setSessionSelectedCaseFilterSetAttribute(selectedFilterData);
      UserUtils.setSessionCaseKeywordFilterAttribute(criteria.getKeyword());
      UserUtils.setSessionCaseAdvancedFilterAttribute(selectedFilters);
    }
    return caseQuery;
  }

  private void setValuesForCaseStateFilter(CaseSearchCriteria criteria) {
    if (filterContainer != null) {
      filterContainer.getStateFilter().setFilteredStates(new ArrayList<>(criteria.getIncludedStates()));
      filterContainer.getStateFilter().setSelectedFilteredStates(criteria.getIncludedStates());
    }
  }

  private List<ICase> findCases(CaseSearchCriteria criteria, int first, int pageSize) {
    IvyComponentLogicCaller<List<ICase>> findCaseCaller = new IvyComponentLogicCaller<>();
    int startIndex = first;
    int count = pageSize;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    return findCaseCaller.invokeComponentLogic(caseWidgetComponentId, "#{logic.findCases}",
        new Object[] {criteria, startIndex, count});
  }

  private void initializedDataModel() {
    criteria.setInvolvedUsername(Ivy.session().getSessionUserName());
    setInvolvedApplications();
    data.clear();
    buildQueryToSearchCriteria();
    setRowCount(getCaseCount(criteria));
  }

  private int getCaseCount(CaseSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countCaseCaller = new IvyComponentLogicCaller<>();
    Long caseCount = countCaseCaller.invokeComponentLogic(caseWidgetComponentId, "#{logic.countCases}", new Object[] { criteria });
    return caseCount.intValue();
  }

  private void buildCriteria() {
    criteria = new CaseSearchCriteria();
    criteria.setBusinessCase(true);
    criteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
    criteria.setSortField(CaseSortField.ID.toString());
    criteria.setSortDescending(true);
    if (!isNotKeepFilter) {
      criteria.setKeyword(UserUtils.getSessionCaseKeywordFilterAttribute());
    }
  }

  private void applyCustomSettings(CaseFilterData caseFilterData) {
    criteria.setKeyword(caseFilterData.getKeyword());
  }

  private void restoreSessionAdvancedFilters() throws IllegalAccessException, InvocationTargetException {
    if (!isNotKeepFilter) {
      List<CaseFilter> sessionCaseFilters = UserUtils.getSessionCaseAdvancedFilterAttribute();
      if(sessionCaseFilters.isEmpty()) {
        selectedFilters.addAll(filters.stream().filter(CaseFilter::defaultFilter).collect(Collectors.toList()));
      } else {
        for (CaseFilter filter : filters) {
          for (CaseFilter sessionCaseFilter : sessionCaseFilters) {
            copyProperties(sessionCaseFilter, filter);
          }
        }
      }
    }
  }

  private void copyProperties(CaseFilter sessionCaseFilter, CaseFilter filter)
      throws IllegalAccessException, InvocationTargetException {
    if (sessionCaseFilter.getClass() == filter.getClass()) {
      BeanUtils.copyProperties(filter, sessionCaseFilter);
      if (filter instanceof CaseCategoryFilter) {
        ((CaseCategoryFilter) filter).updateRootAndCategoryPaths();
      }
      selectedFilters.add(filter);
    }
  }

  public void initColumnsConfiguration() {
    if (CollectionUtils.isEmpty(allColumns)) {
      allColumns.addAll(getDefaultColumns());
      initSelectedColumns();
    }
  }

  protected void initSelectedColumns() {
    CaseColumnsConfigurationService service = new CaseColumnsConfigurationService();
    Long userId = Optional.ofNullable(Ivy.session().getSessionUser()).map(IUser::getId).orElse(null);
    Long applicationId = Ivy.request().getApplication().getId();
    Long processModelId = Ivy.request().getProcessModel().getId();
    if (userId != null) {
      CaseColumnsConfiguration configData = service.getConfiguration(applicationId, userId, processModelId);
      if (configData != null) {
        selectedColumns = configData.getSelectedColumns();
      }
    }
    if (selectedColumns.isEmpty()) {
      selectedColumns.addAll(getDefaultColumns());
      isAutoHideColumns = true;
    }
    setDisableSelectionCheckboxes(isAutoHideColumns);
  }

  public void saveColumnsConfiguration() {
    selectedColumns.addAll(portalRequiredColumns);
    setAutoHideColumns(isDisableSelectionCheckboxes);
    CaseColumnsConfigurationService service = new CaseColumnsConfigurationService();
    Long applicationId = Ivy.request().getApplication().getId();
    Long processModelId = Ivy.request().getProcessModel().getId();
    CaseColumnsConfiguration caseColumnsConfiguration = service.getConfiguration(applicationId,
        Ivy.session().getSessionUser().getId(), processModelId);
    if (caseColumnsConfiguration != null) {
      updateCaseColumnsConfiguration(caseColumnsConfiguration);
    } else {
      caseColumnsConfiguration = createNewCaseColumnsConfigurationData();
    }
    service.save(caseColumnsConfiguration);
    initSelectedColumns();
  }

  private CaseColumnsConfiguration createNewCaseColumnsConfigurationData() {
    CaseColumnsConfiguration caseColumnsConfiguration = new CaseColumnsConfiguration();
    caseColumnsConfiguration.setProcessModelId(Ivy.request().getProcessModel().getId());
    caseColumnsConfiguration.setUserId(Ivy.session().getSessionUser().getId());
    caseColumnsConfiguration.setApplicationId(Ivy.request().getApplication().getId());
    updateCaseColumnsConfiguration(caseColumnsConfiguration);
    return caseColumnsConfiguration;
  }

  private void updateCaseColumnsConfiguration(CaseColumnsConfiguration caseColumnsConfigurationData) {
    caseColumnsConfigurationData.setAutoHideColumns(isAutoHideColumns);
    if (isAutoHideColumns) {
      caseColumnsConfigurationData.setSelectedColumns(getDefaultColumns());
    } else {
      caseColumnsConfigurationData.setSelectedColumns(selectedColumns);
    }
  }

  
  /**
   * <p>
   * Your customized data model needs to override this method if your customized case list has new columns/fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * 
   * return Arrays.asList("NAME", "ID" , "CREATOR", "CREATION_TIME", "FINISHED_TIME", "customVarCharField5", "customVarCharField1");
   * 
   * </pre></code> This list is the list of sortFields in CaseColumnHeader Portal component when you use it to add new
   * column headers also the list of checkboxes in config columns panel
   * </p>
   * 
   * @return default columns
   */
  protected List<String> getDefaultColumns() {
    return portalDefaultColumns;
  }

  /**
   * <p>
   * In case you adds new columns, these columns need cms to show in config columns panel
   * </p>
   * <p>
   * You can either add new entry to default folder below in PortalStyle or override this method to create your own
   * folder column must be the same with sortField
   * </p>
   * 
   * @param column
   * 
   * @return column label
   */
  public String getColumnLabel(String column) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/" + column);
  }

  public boolean isDisableSelectionCheckboxes() {
    return isDisableSelectionCheckboxes;
  }

  public void setDisableSelectionCheckboxes(boolean isDisableSelectionCheckboxes) {
    this.isDisableSelectionCheckboxes = isDisableSelectionCheckboxes;
  }

  public CaseFilterData getSelectedFilterData() {
    return selectedFilterData;
  }

  public void setSelectedFilterData(CaseFilterData selectedFilterData) {
    this.selectedFilterData = selectedFilterData;
  }

  public boolean isNotKeepFilter() {
    return isNotKeepFilter;
  }

  public void setNotKeepFilter(boolean isNotKeepFilter) {
    this.isNotKeepFilter = isNotKeepFilter;
  }

  public List<CaseFilter> getFilters() {
    return filters;
  }

  public void setFilters(List<CaseFilter> filters) {
    this.filters = filters;
  }

  public CaseFilterContainer getFilterContainer() {
    return filterContainer;
  }

  public void setFilterContainer(CaseFilterContainer filterContainer) {
    this.filterContainer = filterContainer;
  }

  public List<CaseFilter> getSelectedFilters() {
    return selectedFilters;
  }

  public void setSelectedFilters(List<CaseFilter> selectedFilters) {
    this.selectedFilters = selectedFilters;
  }

  public void setCategory(String category) {
    criteria.setCategory(category);
  }

  public String getSortField() {
    return criteria.getSortField();
  }

  public boolean isSortDescending() {
    return criteria.isSortDescending();
  }

  public List<String> getSelectedColumns() {
    return selectedColumns;
  }

  public void setSelectedColumns(List<String> selectedColumns) {
    this.selectedColumns = selectedColumns;
  }

  public List<String> getAllColumns() {
    return allColumns;
  }

  public List<String> getPortalRequiredColumns() {
    return portalRequiredColumns;
  }

  public boolean isAutoHideColumns() {
    return isAutoHideColumns;
  }

  public void setAutoHideColumns(boolean isAutoHideColumns) {
    this.isAutoHideColumns = isAutoHideColumns;
  }
  
  public boolean isSelectedColumn(String column) {
    return selectedColumns.stream().anyMatch(selectedcolumn -> selectedcolumn.equalsIgnoreCase(column));
  }
  
  public void setIsAdminQuery(boolean isAdminQuery) {
    criteria.setAdminQuery(isAdminQuery);
  }

  public CaseSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(CaseSearchCriteria criteria) {
    this.criteria = criteria;
  }
  
  @Override
  public void setRowIndex(int index) {
    int idx = index;
    if (idx >= data.size()) {
      idx = -1;
    }
    this.rowIndex = idx;
  }

  @Override
  public ICase getRowData() {
    return data.get(rowIndex);
  }

  @Override
  public boolean isRowAvailable() {
    if (data == null) {
      return false;
    }
    return rowIndex >= 0 && rowIndex < data.size();
  }
}
