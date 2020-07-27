package ch.ivy.addon.portalkit.datamodel;

import static ch.ivy.addon.portalkit.comparator.RemoteCaseComparator.comparatorString;
import static ch.ivy.addon.portalkit.comparator.RemoteCaseComparator.naturalOrderNullsFirst;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.casefilter.DefaultCaseFilterContainer;
import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import ch.ivy.addon.portalkit.enums.CaseAssigneeType;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.CaseFilterService;
import ch.ivy.addon.portalkit.service.CaseQueryService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.support.CaseQueryCriteria;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivy.ws.addon.CaseSearchCriteria;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseLazyDataModel extends LazyDataModel<RemoteCase> {
  private static final int BUFFER_LOAD = 10;
  private static final long serialVersionUID = 1L;
  private final List<RemoteCase> data;
  private Map<GlobalCaseId, RemoteCase> displayedCaseMap;
  private Map<GlobalCaseId, RemoteCase> notDisplayedCaseMap;

  private String caseWidgetComponentId;
  private int rowIndex;
  private CaseSearchCriteria searchCriteria;
  private CaseQueryCriteria queryCriteria;
  private Long serverId;

  protected List<CaseFilter> filters;
  protected List<CaseFilter> selectedFilters;
  protected CaseFilterContainer filterContainer;
  private CaseFilterData selectedFilterData;
  private boolean isNotKeepFilter = false;
  protected boolean disableCaseCount;

  public CaseLazyDataModel() {
    this("case-widget");
  }

  public CaseLazyDataModel(String caseWidgetComponentId) {
    super();
    data = new ArrayList<>();
    displayedCaseMap = new HashMap<>();
    notDisplayedCaseMap = new HashMap<>();
    selectedFilters = new ArrayList<>();
    this.caseWidgetComponentId = caseWidgetComponentId;
    searchCriteria = buildInitSearchCriteria();
    queryCriteria = buildInitQueryCriteria();
    setIgnoreInvolvedUser(PermissionUtils.checkReadAllCasesPermission());
    selectedFilterData = UserUtils.getSessionSelectedCaseFilterSetAttribute();
    disableCaseCount = new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.DISABLE_CASE_COUNT);
  }

  @Override
  public List<RemoteCase> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    if (first == 0) {
      initializedDataModel(searchCriteria);
      if (!disableCaseCount) {
        RequestContext.getCurrentInstance().execute("updateCaseCount()");
      }
    }

    List<RemoteCase> foundCases = findCases(first, pageSize, searchCriteria);
    if (disableCaseCount) {
      int rowCount = 0;
      if (foundCases.size() >= pageSize) {
        rowCount = first + pageSize + 1;
      } else {
        rowCount = first + foundCases.size();
      }
      setRowCount(rowCount);
      RequestContext.getCurrentInstance().execute("PF('case-list-scroller').updateTotalSize(" + rowCount + ")");
    }
    putCasesToNotDisplayedTaskMap(foundCases);
    List<RemoteCase> notDisplayedCases = new ArrayList<>();
    notDisplayedCases.addAll(notDisplayedCaseMap.values());
    Optional<Comparator<? super RemoteCase>> comparator = getComparatorForSorting();
    comparator.ifPresent(c -> notDisplayedCases.sort(c));
    List<RemoteCase> displayedCases = getDisplayedCases(notDisplayedCases, pageSize);

    storeDisplayedCases(notDisplayedCases);

    return displayedCases;
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

  public void initFilters() throws IllegalAccessException, InvocationTargetException {
    if (filterContainer == null) {
      initFilterContainer();
      filters = filterContainer.getFilters();
      setValuesForCaseStateFilter(queryCriteria);
      restoreSessionAdvancedFilters();
    }
  }

  private void setValuesForCaseStateFilter(CaseQueryCriteria criteria) {
    if (filterContainer != null) {
      filterContainer.getStateFilter().setFilteredStates(new ArrayList<>(criteria.getIncludedStates()));
      filterContainer.getStateFilter().setSelectedFilteredStates(criteria.getIncludedStates());
    }
  }

  private Optional<Comparator<? super RemoteCase>> getComparatorForSorting() {
    Comparator<? super RemoteCase> comparator = null;
    if (CaseSortField.NAME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorString(RemoteCase::getName);
    } else if (CaseSortField.ID.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getId);
    } else if (CaseSortField.START_TIME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getStartTimestamp);
    } else if (CaseSortField.END_TIME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getEndTimestamp);
    } else if (CaseSortField.CREATOR.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorString(caseCreator());
    } else if (CaseSortField.STATE.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getState);
    }
    if (comparator != null && queryCriteria.isSortDescending()) {
      comparator = comparator.reversed();
    }
    return Optional.ofNullable(comparator);
  }

  private Function<RemoteCase, String> caseCreator() {
    return remoteCase -> {
      if (StringUtils.isNotEmpty(remoteCase.getCreatorFullName())) {
        return remoteCase.getCreatorFullName();
      }
      return remoteCase.getCreatorUserName();
    };
  }

  private void storeDisplayedCases(List<RemoteCase> displayedCases) {
    data.addAll(displayedCases);
    for (RemoteCase oneCase : displayedCases) {
      displayedCaseMap.put(globalCaseId(oneCase), oneCase);
    }
  }

  private List<RemoteCase> findCases(int first, int pageSize, CaseSearchCriteria criteria) {
    IvyComponentLogicCaller<List<RemoteCase>> findCaseCaller = new IvyComponentLogicCaller<>();
    int startIndex = first - BUFFER_LOAD;
    int count = pageSize + BUFFER_LOAD;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    List<RemoteCase> cases =
        findCaseCaller.invokeComponentLogic(caseWidgetComponentId, "#{logic.findCases}", new Object[] {startIndex,
            count, criteria, serverId});
    return cases;
  }

  private void initializedDataModel(CaseSearchCriteria criteria) {
    searchCriteria.setInvolvedUsername(Ivy.session().getSessionUserName());
    data.clear();
    displayedCaseMap.clear();
    notDisplayedCaseMap.clear();
    buildQueryToSearchCriteria();
    if (disableCaseCount) {
      setRowCount(0);
    } else {
      setRowCount(getCaseCount(criteria));
    }
  }

  private List<RemoteCase> getDisplayedCases(List<RemoteCase> notDisplayedCases, int pageSize) {
    int displayedCaseCount = notDisplayedCases.size() > pageSize ? pageSize : notDisplayedCases.size();
    List<RemoteCase> displayedCases = notDisplayedCases.subList(0, displayedCaseCount);
    for (RemoteCase oneCase : displayedCases) {
      notDisplayedCaseMap.remove(globalCaseId(oneCase));
    }
    return displayedCases;
  }

  private void putCasesToNotDisplayedTaskMap(List<RemoteCase> cases) {
    for (RemoteCase oneCase : cases) {
      GlobalCaseId keyOfCase = globalCaseId(oneCase);
      if (!displayedCaseMap.containsKey(keyOfCase) && !notDisplayedCaseMap.containsKey(keyOfCase)) {
        notDisplayedCaseMap.put(keyOfCase, oneCase);
      }
    }
  }

  @SuppressWarnings("unchecked")
  public void onFilterChange(ValueChangeEvent event) {
    List<CaseFilter> oldSelectedFilters = (List<CaseFilter>) event.getOldValue();
    List<CaseFilter> newSelectedFilters = (List<CaseFilter>) event.getNewValue();
    List<CaseFilter> toggleFilters =
        (List<CaseFilter>) CollectionUtils.subtract(newSelectedFilters, oldSelectedFilters);
    if (CollectionUtils.isNotEmpty(toggleFilters)) {
      toggleFilters.get(0).resetValues();
    }
  }

  public void removeFilter(CaseFilter filter) {
    filter.resetValues();
    selectedFilters.remove(filter);
  }

  public void resetFilters() {
    for (CaseFilter selectedFilter : selectedFilters) {
      selectedFilter.resetValues();
    }
    selectedFilters = new ArrayList<>();
    selectedFilterData = null;
  }

  private GlobalCaseId globalCaseId(RemoteCase oneCase) {
    return new GlobalCaseId(oneCase.getServer().getId(), oneCase.getId(), oneCase.isBusinessCase());
  }

  private int getCaseCount(CaseSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countCaseCaller = new IvyComponentLogicCaller<>();
    Long caseCount =
        countCaseCaller.invokeComponentLogic(caseWidgetComponentId, "#{logic.countCases}", new Object[] {criteria,
            serverId});
    return caseCount.intValue();
  }

  private CaseSearchCriteria buildInitSearchCriteria() {
    CaseSearchCriteria crit = new CaseSearchCriteria();
    crit.setInvolvedUsername(Ivy.session().getSessionUserName());
    crit.setBusinessCase(true);
    return crit;
  }

  public void setSorting(String sortedField, boolean descending) {
    queryCriteria.setSortField(sortedField);
    queryCriteria.setSortDescending(descending);
  }

  @Override
  public void setRowIndex(int index) {
    if (index >= data.size()) {
      index = -1;
    }
    this.rowIndex = index;
  }

  @Override
  public RemoteCase getRowData() {
    return data.get(rowIndex);
  }

  @Override
  public boolean isRowAvailable() {
    if (data == null) {
      return false;
    }
    return rowIndex >= 0 && rowIndex < data.size();
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    searchCriteria.setIgnoreInvolvedUser(ignoreInvolvedUser);
    if (ignoreInvolvedUser && !queryCriteria.getIncludedStates().contains(CaseState.DONE)) {
      queryCriteria.addIncludedStates(Arrays.asList(CaseState.DONE));
      setValuesForCaseStateFilter(queryCriteria);
    }
  }

  public void setTaskId(Long taskId) {
    queryCriteria.setTaskId(taskId);
    queryCriteria.addIncludedStates(Arrays.asList(CaseState.DONE));
    setValuesForCaseStateFilter(queryCriteria);
  }

  public void setCaseId(Long caseId) {
    queryCriteria.setCaseId(caseId);
    queryCriteria.setIncludedStates(new ArrayList<>());
    setValuesForCaseStateFilter(queryCriteria);
  }

  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }

  public void setInvolvedApplications(String... involvedApplications) {
    searchCriteria.setInvolvedApplications(involvedApplications);
  }
  
  public void setCaseAssigneeType(CaseAssigneeType assigneeType) {
    queryCriteria.setCaseAssigneeType(assigneeType);
  }

  public void setCategory(String category) {
    queryCriteria.setCategory(category);
  }

  public String getSortField() {
    return queryCriteria.getSortField();
  }

  public boolean isSortDescending() {
    return queryCriteria.isSortDescending();
  }

  public CaseSearchCriteria getSearchCriteria() {
    return searchCriteria;
  }

  public void setQueryCriteria(CaseQueryCriteria queryCriteria) {
    this.queryCriteria = queryCriteria;
  }

  public CaseQueryCriteria getQueryCriteria() {
    return queryCriteria;
  }

  protected CaseQueryCriteria buildInitQueryCriteria() {
    CaseQueryCriteria jsonQueryCriteria = new CaseQueryCriteria();
    jsonQueryCriteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
    jsonQueryCriteria.setSortField(CaseSortField.ID.toString());
    jsonQueryCriteria.setSortDescending(true);
    if (!isNotKeepFilter) {
      jsonQueryCriteria.setKeyword(UserUtils.getSessionCaseKeywordFilterAttribute());
    }
    return jsonQueryCriteria;
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

  /**
   * Builds and converts CaseQuery to JsonQuery and put it into CaseSearchCriteria.
   */
  protected void buildQueryToSearchCriteria() {
    if (queryCriteria.getCaseQuery() == null) {
      String jsonQuery =
          SubProcessCall.withPath("Functional Processes/BuildCaseJsonQuery").withStartSignature("buildCaseJsonQuery()")
              .call().get("jsonQuery", String.class);
      CaseQuery customizedCaseQuery =
          StringUtils.isNotBlank(jsonQuery) ? CaseQuery.fromJson(jsonQuery) : CaseQuery.create();
      queryCriteria.setCaseQuery(customizedCaseQuery);
    }
    if (Objects.isNull(filterContainer) || selectedFilters.contains(filterContainer.getStateFilter())) {
      queryCriteria.setIncludedStates(new ArrayList<>());
    } else {
      queryCriteria.setIncludedStates(filterContainer.getStateFilter().getSelectedFilteredStates());
    }
    CaseQuery caseQuery = buildCaseQuery();
    searchCriteria.setJsonQuery(caseQuery.asJson());
  }

  private CaseQuery buildCaseQuery() {
    CaseQuery caseQuery = CaseQueryService.service().createQuery(queryCriteria);
    IFilterQuery filterQuery = caseQuery.where();
    selectedFilters.forEach(selectedFilter -> {
      CaseQuery subQuery = selectedFilter.buildQuery();
      if (subQuery != null) {
        filterQuery.and(subQuery);
      }
    });
    if (!isNotKeepFilter) {
      UserUtils.setSessionSelectedCaseFilterSetAttribute(selectedFilterData);
      UserUtils.setSessionCaseKeywordFilterAttribute(queryCriteria.getKeyword());
      UserUtils.setSessionCaseAdvancedFilterAttribute(selectedFilters);
    }
    return caseQuery;
  }

  /**
   * Save all filter settings to business data
   * @param filterName 
   * @param filterType 
   * @param filterGroupId 
   * @return saved CaseFilterData
   */
  public CaseFilterData saveFilter(String filterName, FilterType filterType, Long filterGroupId) {
    CaseFilterData filterData = new CaseFilterData();
    List<CaseFilter> filters = new ArrayList<>(selectedFilters);
    filterData.setFilters(filters);
    filterData.setKeyword(queryCriteria.getKeyword());
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

  public CaseFilterData getSelectedFilterData() {
    return selectedFilterData;
  }

  public void setSelectedFilterData(CaseFilterData selectedFilterData) {
    this.selectedFilterData = selectedFilterData;
  }

  /**
   * Apply filter settings loaded from business data to this {@link #CaseLazyDataModel}
   * @param caseFilterData 
   * @throws IllegalAccessException 
   * @throws InvocationTargetException 
   * @throws NoSuchMethodException 
   */
  public void applyFilter(CaseFilterData caseFilterData) throws IllegalAccessException, InvocationTargetException,
      NoSuchMethodException {
    selectedFilterData = caseFilterData;
    new CaseFilterService().applyFilter(this, caseFilterData);
    applyCustomSettings(caseFilterData);
  }

  private void applyCustomSettings(CaseFilterData caseFilterData) {
    queryCriteria.setKeyword(caseFilterData.getKeyword());
  }

  public boolean isNotKeepFilter() {
    return isNotKeepFilter;
  }

  public void setNotKeepFilter(boolean isNotKeepFilter) {
    this.isNotKeepFilter = isNotKeepFilter;
  }

  private void restoreSessionAdvancedFilters() throws IllegalAccessException, InvocationTargetException {
    if (!isNotKeepFilter) {
      List<CaseFilter> sessionCaseFilters = UserUtils.getSessionCaseAdvancedFilterAttribute();
      for (CaseFilter filter : filters) {
        for (CaseFilter sessionCaseFilter : sessionCaseFilters) {
          if (sessionCaseFilter.getClass() == filter.getClass()) {
            BeanUtils.copyProperties(filter, sessionCaseFilter);
            selectedFilters.add(filter);
          }
        }
      }
    }
  }

  public boolean getDisableCaseCount() {
    return disableCaseCount;
  }

  public void setDisableCaseCount(boolean disableCaseCount) {
    this.disableCaseCount = disableCaseCount;
  }
  
}
