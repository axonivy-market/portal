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
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
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

  protected List<CaseFilter> selectedFilters;
  protected CaseFilterContainer filterContainer;

  public ElapsedTimeLazyDataModel() {
    this("statistics-widget:statistic-dashboard-widget:elapsed-time-chart-details");
  }

  public ElapsedTimeLazyDataModel(String caseWidgetComponentId) {
    super();
    data = new ArrayList<>();
    selectedFilters = new ArrayList<>();
    this.caseWidgetComponentId = caseWidgetComponentId;
    buildCriteria();
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
    this.criteria.setFinalCaseQuery(caseQuery);
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
    return caseQuery;
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
  }

  public CaseFilterContainer getFilterContainer() {
    return filterContainer;
  }

  public void setFilterContainer(CaseFilterContainer filterContainer) {
    this.filterContainer = filterContainer;
  }

  public void setCategory(String category) {
    criteria.setCategory(category);
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
