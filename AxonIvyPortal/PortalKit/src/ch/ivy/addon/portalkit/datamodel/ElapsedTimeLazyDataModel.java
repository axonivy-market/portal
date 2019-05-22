package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.casefilter.DefaultCaseFilterContainer;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class ElapsedTimeLazyDataModel extends LazyDataModel<ICase> {
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

  public ElapsedTimeLazyDataModel() {
    this("statistics-widget:statistic-dashboard-widget:elapsed-time-chart-details");
  }

  public ElapsedTimeLazyDataModel(String caseWidgetComponentId) {
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
    if (first == 0) {
      initializedDataModel();
    }
    List<ICase> foundCases =  findCases(criteria, first, pageSize);
    data.addAll(foundCases);
    return foundCases;
  }

  public void initFilters() throws ReflectiveOperationException {
    if (filterContainer == null) {
      initFilterContainer();
      filters = filterContainer.getFilters();
      setValuesForCaseStateFilter(criteria);
    }
  }

  public void setAdminQuery(boolean isAdminQuery) {
    criteria.setAdminQuery(isAdminQuery);
    if (isAdminQuery && !criteria.getIncludedStates().contains(CaseState.DONE)) {
      criteria.addIncludedStates(Arrays.asList(CaseState.DONE));
      setValuesForCaseStateFilter(criteria);
    }
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
   * if ("CustomVarcharField5".equalsIgnoreCase(criteria.getSortField())) {
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
    criteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.DONE)));
    criteria.setSortField(CaseSortField.ID.toString());
    criteria.setSortDescending(true);
    if (!isNotKeepFilter) {
      criteria.setKeyword(UserUtils.getSessionCaseKeywordFilterAttribute());
    }
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
