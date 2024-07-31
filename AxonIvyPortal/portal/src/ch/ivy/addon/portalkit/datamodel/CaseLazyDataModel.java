package ch.ivy.addon.portalkit.datamodel;

import static ch.ivy.addon.portalkit.enums.FilterType.ALL_ADMINS;
import static ch.ivy.addon.portalkit.enums.FilterType.ALL_USERS;

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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.CaseColumnsConfiguration;
import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData;
import ch.ivy.addon.portalkit.casefilter.impl.DefaultCaseFilterContainer;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.UserSettingService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.CaseColumnsConfigurationService;
import ch.ivy.addon.portalkit.service.CaseFilterService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
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
  public static final String DESCRIPTION = "DESCRIPTION";

  private static final long serialVersionUID = 1L;

  protected final List<ICase> data;

  protected String caseWidgetComponentId;
  protected int rowIndex;
  protected CaseSearchCriteria criteria;

  protected List<CaseFilter> filters;
  protected List<CaseFilter> selectedFilters;
  protected List<CaseFilter> submittedFilterSelection = new ArrayList<>();
  protected List<CaseFilter> oldSelectedFilters = new ArrayList<>();
  protected CaseFilterContainer filterContainer;
  protected CaseFilterData selectedFilterData;
  protected CaseFilterData defaultCaseFilterData;
  protected boolean isNotKeepFilter = false;
  protected Long filterGroupId;

  protected List<String> allColumns = new ArrayList<>();
  protected List<String> selectedColumns = new ArrayList<>();
  private List<String> portalDefaultColumns;
  private List<String> portalRequiredColumns = Arrays.asList(CaseSortField.NAME.name());

  private boolean isAutoHideColumns;
  private boolean isDisableSelectionCheckboxes;

  protected boolean disableCaseCount;
  protected Boolean isSelectedDefaultFilter;
  protected boolean isSelectedAllFilters;

  public CaseLazyDataModel() {
    this("case-widget");
  }

  public CaseLazyDataModel(String caseWidgetComponentId) {
    super();
    data = new ArrayList<>();
    selectedFilters = new ArrayList<>();
    this.caseWidgetComponentId = caseWidgetComponentId;
    initColumnsConfiguration();
    buildCriteria();
    setAdminQuery(PermissionUtils.checkReadAllCasesPermission());
    loadSessionCaseFiltersAttribute();
  }

  private void loadSessionCaseFiltersAttribute() {
    if (isSameFilterGroupId()) {
      selectedFilterData = UserUtils.getSessionSelectedCaseFilterSetAttribute();
      isSelectedDefaultFilter = UserUtils.getSessionSelectedDefaultCaseFilterSetAttribute();
      if (isSelectedDefaultFilter == null) {
        buildDefaultCaseFilterData();
      }
    } else {
      selectedFilterData = null;
      isSelectedDefaultFilter = true;
    }
  }

  private boolean isSameFilterGroupId() {
    filterGroupId = UserUtils.getSessionFilterGroupIdAttribute();
    return filterGroupId == null || filterGroupId.equals(Ivy.request().getProcessModel().getId());
  }

  public CaseFilterData buildDefaultCaseFilterData() {
    if (defaultCaseFilterData == null) {
      defaultCaseFilterData = new CaseFilterData();
      defaultCaseFilterData.setFilterName(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultFilter"));
      defaultCaseFilterData.setType(FilterType.DEFAULT);
      collectFiltersForDefaultFilterSet();
    }
    isSelectedDefaultFilter = isSelectedDefaultFilter == null ? true : isSelectedDefaultFilter;
    return defaultCaseFilterData;
  }

  public void updateDisableCaseCount() {
    disableCaseCount = GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.DISABLE_CASE_COUNT);
  }

  @Override
  public List<ICase> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    if (first == 0) {
      initializedDataModel();
      if (!disableCaseCount) {
        PrimeFaces.current().executeScript("updateCaseCount()");
      }
    }

    List<ICase> foundCases = findCases(criteria, first, pageSize);
    if (disableCaseCount) {
      int rowCount = 0;
      if (foundCases.size() >= pageSize) {
        rowCount = first + pageSize + 1;
      } else {
        rowCount = first + foundCases.size();
      }
      setRowCount(rowCount);
      PrimeFaces.current().executeScript("PF('case-list-scroller').cfg.totalSize = " + rowCount);
    }
    data.addAll(foundCases);
    return foundCases;
  }

  public void initFilters() throws ReflectiveOperationException {
    if (filterContainer == null) {
      initColumnsConfiguration();
      initFilterContainer();
      filters = filterContainer.getFilters();
      setValuesForCaseStateFilter(criteria, filterContainer);
      restoreSessionAdvancedFilters();
    }
    submittedFilterSelection = new ArrayList<>(selectedFilters);
  }

  private void collectFiltersForDefaultFilterSet() {
    if (defaultCaseFilterData != null && CollectionUtils.isEmpty(defaultCaseFilterData.getFilters())) {
      CaseFilterContainer tempFilterContainer = ObjectUtils.defaultIfNull(this.filterContainer, new DefaultCaseFilterContainer());
      setValuesForCaseStateFilter(criteria, tempFilterContainer);
      defaultCaseFilterData.setFilters(tempFilterContainer.getFilters().stream().filter(CaseFilter::defaultFilter).collect(Collectors.toList()));
    }
  }

  @SuppressWarnings("unchecked")
  public void onFilterChange(ValueChangeEvent event) {
    oldSelectedFilters = (List<CaseFilter>) event.getOldValue();
  }

  @SuppressWarnings("unchecked")
  public void updateSelectedFilter() {
    List<CaseFilter> toggleFilters;
    if (selectedFilters.size() > oldSelectedFilters.size()) {
      toggleFilters = (List<CaseFilter>) CollectionUtils.subtract(selectedFilters, oldSelectedFilters);
    } else {
      toggleFilters = (List<CaseFilter>) CollectionUtils.subtract(oldSelectedFilters, selectedFilters);
    }

    if (CollectionUtils.isNotEmpty(toggleFilters)) {
      toggleFilters.forEach(filter -> filter.resetValues());
    }
    resetFilterData();
    storeCaseFiltersIntoSession();
    submittedFilterSelection = new ArrayList<>(selectedFilters);
  }

  public void restoreFiltersSelection() {
    if (CollectionUtils.isNotEmpty(submittedFilterSelection)) {
      selectedFilters = new ArrayList<>(submittedFilterSelection);
    }
    isSelectedAllFilters = selectedFilters.size() == filters.size();
  }

  public void onFilterApply() {
    resetFilterData();
  }

  public void onKeywordChange() {
    resetFilterData();
  }

  private void resetFilterData() {
    if (this.selectedFilterData != null) {
      this.selectedFilterData = null;
    }
    this.isSelectedDefaultFilter = false;
  }

  public void removeFilter(CaseFilter filter) {
    filter.resetValues();
    selectedFilters.remove(filter);
    resetFilterData();
  }

  public void resetFilters() throws ReflectiveOperationException {
    for (CaseFilter selectedFilter : selectedFilters) {
      selectedFilter.resetValues();
    }
    applyFilter(buildDefaultCaseFilterData());
  }

  public boolean isSameCaseFilterData(CaseFilterData filterToBeRemoved) {
    if (filterToBeRemoved == null || selectedFilterData == null) {
      return false;
    }
    return filterToBeRemoved.getFilterGroupId().equals(selectedFilterData.getFilterGroupId())
        && filterToBeRemoved.getType() == selectedFilterData.getType()
        && filterToBeRemoved.getFilterName().equals(selectedFilterData.getFilterName());
  }

  public void setSorting(String sortedField, boolean descending) {
    updateSortCriteria(sortedField, descending, true);
  }

  private void updateSortCriteria(String sortedField, boolean descending, boolean updateCache) {
    criteria.setSortField(sortedField);
    criteria.setSortDescending(descending);
    if (updateCache) {
      UserUtils.setSessionCaseSortAttribute(SortFieldUtil.buildSortField(sortedField, descending));
    }
  }

  public void setAdminQuery(boolean isAdminQuery) {
    criteria.extendStatesQueryByPermission(isAdminQuery);
    if (isAdminQuery) {
      setValuesForCaseStateFilter(criteria, filterContainer);
    }
  }

  public void setCaseId(Long caseId) {
    criteria.setCaseId(caseId);
    criteria.setIncludedStates(new ArrayList<>());
    setValuesForCaseStateFilter(criteria, filterContainer);
  }

  /**
   * Save all filter settings to business data
   * @param filterName
   * @param filterType
   * @param filterGroupId
   * @return saved CaseFilterData
   */
  public CaseFilterData saveFilter(String filterName, FilterType filterType, Long filterGroupId) {
    isSelectedDefaultFilter = false;
    CaseFilterData filterData = new CaseFilterData();
    List<CaseFilter> filtersToSave = new ArrayList<>(selectedFilters);
    filterData.setFilters(filtersToSave);
    filterData.setKeyword(criteria.getKeyword());
    filterData.setUserId(Ivy.session().getSessionUser().getId());
    filterData.setSecurityMemberId(Ivy.session().getSessionUser().getSecurityMemberId());
    filterData.setFilterGroupId(filterGroupId);
    filterData.setFilterName(filterName);
    filterData.setType(filterType);
    boolean isPublic = ALL_USERS == filterData.getType() || ALL_ADMINS == filterData.getType();
    filterData.setIsPublic(isPublic);
    CaseFilterService filterService = new CaseFilterService();
    filterService.save(filterData);
    BusinessDataInfo<CaseFilterData> info = filterService.save(filterData);
    filterData = filterService.findById(info.getId());
    UserUtils.setSessionSelectedCaseFilterSetAttribute(filterData);
    UserUtils.setSessionSelectedDefaultCaseFilterSetAttribute(isSelectedDefaultFilter);
    return filterData;
  }

  /**
   * Apply filter settings loaded from business data to this {@link #CaseLazyDataModel}
   * @param caseFilterData
   * @throws ReflectiveOperationException
   */
  public void applyFilter(CaseFilterData caseFilterData) throws ReflectiveOperationException {
    isSelectedDefaultFilter = FilterType.DEFAULT.equals(caseFilterData.getType());
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
  public void initFilterContainer() {
    filterContainer = new DefaultCaseFilterContainer();
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
    buildSort();
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
   * if ("CustomerName".equalsIgnoreCase(criteria.getSortField())) {
   *   if (criteria.isSortDescending()) {
   *     caseQuery.orderBy().customField().stringField("CustomerName").descending();
   *   } else {
   *     caseQuery.orderBy().customField().stringField("CustomerName");
   *   }
   * }
   * </pre></code>
   * </p>
   *
   * @param caseQuery case query {@link CaseQuery}
   */
  public void extendSort(@SuppressWarnings("unused") CaseQuery caseQuery) {
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
    storeCaseFiltersIntoSession();
    return caseQuery;
  }

  private void storeCaseFiltersIntoSession() {
    if (!isNotKeepFilter) {
      UserUtils.setSessionFilterGroupIdAttribute(Ivy.request().getProcessModel().getId());
      UserUtils.setSessionSelectedDefaultCaseFilterSetAttribute(isSelectedDefaultFilter);
      UserUtils.setSessionSelectedCaseFilterSetAttribute(selectedFilterData);
      UserUtils.setSessionCaseKeywordFilterAttribute(criteria.getKeyword());
      UserUtils.setSessionCaseAdvancedFilterAttribute(selectedFilters);
    }
  }

  private void setValuesForCaseStateFilter(CaseSearchCriteria criteria, CaseFilterContainer filterContainer) {
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
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    return findCaseCaller.invokeComponentLogic(componentId, "#{logic.findCases}",
        new Object[] {criteria, startIndex, count});
  }

  private void initializedDataModel() {
    data.clear();
    buildQueryToSearchCriteria();
    if (disableCaseCount) {
      setRowCount(0);
    } else {
      setRowCount(getCaseCount(criteria));
    }
  }

  private int getCaseCount(CaseSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countCaseCaller = new IvyComponentLogicCaller<>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    Long caseCount = countCaseCaller.invokeComponentLogic(componentId, "#{logic.countCases}", new Object[] { criteria });
    return caseCount.intValue();
  }

  private void buildCriteria() {
    criteria = new CaseSearchCriteria();
    criteria.setBusinessCase(true);
    criteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
    buildSort();
    if (!isNotKeepFilter) {
      criteria.setKeyword(UserUtils.getSessionCaseKeywordFilterAttribute());
    }
  }

  private void buildSort() {
    String sortField = UserUtils.getSessionCaseSortAttribute();
    String sortColumn = SortFieldUtil.extractSortColumn(sortField);
    if (StringUtils.isBlank(sortColumn) || !getAllColumns().contains(sortColumn)) {
      updateSortCriteria(getDefaultSortField(), isSortedDescendingByDefault(), false);
      return;
    }
    setSorting(sortColumn, !SortFieldUtil.isAscendingSort(sortField));
  }

  private String getDefaultSortField() {
    String defaultSortField = UserSettingService.newInstance().getDefaultSortFieldOfCaseList();
    if (StringUtils.isBlank(defaultSortField) || UserSettingService.DEFAULT.equals(defaultSortField)) {
      GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
      defaultSortField = globalSettingService.findGlobalSettingValue(GlobalVariable.DEFAULT_SORT_FIELD_OF_CASE_LIST);
    }
    return defaultSortField;
   }

  private boolean isSortedDescendingByDefault() {
    String defaultSortDirection = UserSettingService.newInstance().getDefaultSortDirectionOfCaseList();
    if (StringUtils.isBlank(defaultSortDirection) || UserSettingService.DEFAULT.equals(defaultSortDirection)) {
      GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
      defaultSortDirection =
          globalSettingService.findGlobalSettingValue(GlobalVariable.DEFAULT_SORT_DIRECTION_OF_CASE_LIST);
    }

    return !SortFieldUtil.isAscendingSort(defaultSortDirection);
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
    } else if (defaultCaseFilterData != null) {
      selectedFilters.addAll(defaultCaseFilterData.getFilters());
    }
  }

  private void copyProperties(CaseFilter sessionCaseFilter, CaseFilter filter)
      throws IllegalAccessException, InvocationTargetException {
    if (sessionCaseFilter.getClass() == filter.getClass()) {
      BeanUtils.copyProperties(filter, sessionCaseFilter);
      selectedFilters.add(filter);
    }
  }

  public void initColumnsConfiguration() {
    if (GlobalSettingService.getInstance().isCaseOwnerEnabled()) {
      portalDefaultColumns = List.of(CaseSortField.NAME.name(), 
                                      CaseSortField.ID.name(), 
                                      CaseSortField.CREATOR.name(), 
                                      CaseSortField.OWNER.name(), 
                                      CaseSortField.CREATION_TIME.name(),
                                      CaseSortField.FINISHED_TIME.name(), 
                                      CaseSortField.STATE.name(), 
                                      CaseSortField.CATEGORY.name());
    } else {
      portalDefaultColumns = List.of(CaseSortField.NAME.name(), 
                                      CaseSortField.ID.name(), 
                                      CaseSortField.CREATOR.name(), 
                                      CaseSortField.CREATION_TIME.name(), 
                                      CaseSortField.FINISHED_TIME.name(),
                                      CaseSortField.STATE.name(), 
                                      CaseSortField.CATEGORY.name());
    }
    if (GlobalSettingService.getInstance().isHideCaseCreator()) {
      portalDefaultColumns = portalDefaultColumns.stream()
          .filter(column -> !column.contains(CaseSortField.CREATOR.name())).collect(Collectors.toList());
    }
    if (CollectionUtils.isEmpty(allColumns)) {
      allColumns.addAll(getDefaultColumns());
      allColumns.add(CaseSortField.APPLICATION.name());
      initSelectedColumns();
    }
  }

  protected void initSelectedColumns() {
    CaseColumnsConfigurationService service = CaseColumnsConfigurationService.getInstance();
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
    // avoid duplicating
    for (String requiredColumn : portalRequiredColumns) {
      if (!selectedColumns.contains(requiredColumn)) {
        selectedColumns.add(requiredColumn);
      }
    }
    setAutoHideColumns(isDisableSelectionCheckboxes);
    CaseColumnsConfigurationService service = CaseColumnsConfigurationService.getInstance();
    Long processModelId = Ivy.request().getProcessModel().getId();
    Long applicationId = Ivy.request().getApplication().getId();
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
    caseColumnsConfiguration.setSecurityMemberId(Ivy.session().getSessionUser().getSecurityMemberId());
    caseColumnsConfiguration.setApplicationId(Ivy.request().getApplication().getId());
    caseColumnsConfiguration.setSelectedColumns(new ArrayList<>());
    updateCaseColumnsConfiguration(caseColumnsConfiguration);
    return caseColumnsConfiguration;
  }

  private void updateCaseColumnsConfiguration(CaseColumnsConfiguration caseColumnsConfigurationData) {
    caseColumnsConfigurationData.setAutoHideColumns(isAutoHideColumns);
    caseColumnsConfigurationData.getSelectedColumns().clear();
    if (isAutoHideColumns) {
      caseColumnsConfigurationData.getSelectedColumns().addAll(getDefaultColumns());
    } else {
      caseColumnsConfigurationData.getSelectedColumns().addAll(selectedColumns);
    }
  }


  /**
   * <p>
   * Your customized data model needs to override this method if your customized case list has new columns/fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   *
   * return Arrays.asList("NAME", "ID" , "CREATOR", "CREATION_TIME", "FINISHED_TIME", "CustomerName", "CustomerType");
   *
   * </pre></code> This list is the list of sortFields in CaseColumnHeader Portal component when you use it to add new
   * column headers also the list of checkboxes in config columns panel
   * </p>
   *
   * @return default columns
   */
  public List<String> getDefaultColumns() {
    return portalDefaultColumns;
  }

  /**
   * <p>
   * In case you adds new columns, these columns need cms to show in config columns panel
   * </p>
   * <p>
   * You can either add new entry to default folder below in PortalKit or override this method to create your own
   * folder column must be the same with sortField
   * </p>
   *
   * @param column column name
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
    this.selectedFilterData = null;
    this.isSelectedDefaultFilter = false;
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

  /**
   * Check if your column is selected
   * @param column column name
   * @return is column selected
   */
  public boolean isSelectedColumn(String column) {
    return selectedColumns.stream().anyMatch(selectedcolumn -> selectedcolumn.equalsIgnoreCase(column));
  }

  public void setIsAdminQuery(boolean isAdminQuery) {
    criteria.setAdminQuery(isAdminQuery);
  }

  /**
   * Getter for case search criteria
   * @return criteria search criteria type {@link CaseSearchCriteria}
   */
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

  public boolean getDisableCaseCount() {
    return disableCaseCount;
  }

  public void setDisableCaseCount(boolean disableCaseCount) {
    this.disableCaseCount = disableCaseCount;
  }

  public CaseFilterData getDefaultCaseFilterData() {
    return defaultCaseFilterData;
  }

  public void setDefaultCaseFilterData(CaseFilterData defaultCaseFilterData) {
    this.defaultCaseFilterData = defaultCaseFilterData;
  }

  public boolean isSelectedDefaultFilter() {
    return isSelectedDefaultFilter;
  }

  public void setSelectedDefaultFilter(boolean isSelectedDefaultFilter) {
    this.isSelectedDefaultFilter = isSelectedDefaultFilter;
  }

  public boolean isSelectedAllFilters() {
    return isSelectedAllFilters;
  }

  public void setSelectedAllFilters(boolean isSelectedAllFilters) {
    this.isSelectedAllFilters = isSelectedAllFilters;
  }

  public void onSelectedAllFilters() {
    if (isSelectedAllFilters) {
      selectedFilters = new ArrayList<>(filters);
    } else {
      selectedFilters = new ArrayList<>();
    }
  }

  public void onSelectedFilter() {
    isSelectedAllFilters = selectedFilters.size() == filters.size();
  }
  
  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return 0;
  }
}
