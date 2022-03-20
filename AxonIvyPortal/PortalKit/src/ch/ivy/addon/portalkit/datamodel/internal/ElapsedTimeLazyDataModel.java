package ch.ivy.addon.portalkit.datamodel.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.jsf.primefaces.legazy.LazyDataModel7;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.OrderByColumnQuery;

public class ElapsedTimeLazyDataModel extends LazyDataModel7<ICase> {
  private static final long serialVersionUID = 1L;
  protected final List<ICase> data;

  protected String caseWidgetComponentId;
  protected int rowIndex;
  protected CaseSearchCriteria criteria;
  public ElapsedTimeLazyDataModel() {
    this("statistics-widget:statistic-dashboard-widget:elapsed-time-chart-details");
  }

  public ElapsedTimeLazyDataModel(String caseWidgetComponentId) {
    super();
    data = new ArrayList<>();
    this.caseWidgetComponentId = caseWidgetComponentId;
    buildCriteria();
  }

  @Override
  public List<ICase> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    criteria.setSortField(sortField);
    criteria.setSortDescending(sortOrder == SortOrder.DESCENDING);
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
    CaseQuery caseQuery = buildCaseQuery();

    /**
     * Filter CaseSearchCriteria with noCategory
     */
    if (StringUtils.isEmpty(criteria.getCategory())) {
      caseQuery.where().and().category().isEqual("");
    }
    buildSortCaseQuery(caseQuery);
    this.criteria.setFinalCaseQuery(caseQuery);
  }


  private CaseQuery buildCaseQuery() {
    return criteria.createQuery();
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
    setRowCount(getCaseCount(criteria));
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
    criteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.DONE)));
    criteria.setSortField(CaseSortField.ID.toString());
    criteria.setSortDescending(true);
    criteria.setAdminQuery(PermissionUtils.checkReadAllCasesPermission());
  }

  private void buildSortCaseQuery(CaseQuery caseQuery) {
    CaseSortField sortColumn = CaseSortField.valueOf(criteria.getSortField());
    OrderByColumnQuery orderQuery = null;
    if (sortColumn.equals(CaseSortField.ELAPSED_TIME)) {
      orderQuery = caseQuery.orderBy().businessRuntime();
    } else {
      orderQuery = caseQuery.orderBy().caseId();
    }

    if (criteria.isSortDescending()) {
      orderQuery.descending();
    } else {
      orderQuery.ascending();
    }
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
