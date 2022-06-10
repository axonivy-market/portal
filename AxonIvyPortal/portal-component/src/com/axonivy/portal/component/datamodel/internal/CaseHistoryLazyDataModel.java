package com.axonivy.portal.component.datamodel.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.axonivy.portal.component.enums.AdditionalProperty;
import com.axonivy.portal.component.enums.CaseSortField;
import com.axonivy.portal.component.ivydata.searchcriteria.CaseSearchCriteria;
import com.axonivy.portal.component.service.IvyAdapterService;
import com.axonivy.portal.component.service.RegisteredApplicationService;
import com.axonivy.portal.component.util.PermissionUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.jsf.primefaces.legazy.LazyDataModel7;

public class CaseHistoryLazyDataModel extends LazyDataModel7<ICase> {

  private static final long serialVersionUID = 3023805225538732101L;

  private String businessEntityId;
  private final List<ICase> data;
  private CaseSearchCriteria criteria;
  protected int rowIndex;

  public CaseHistoryLazyDataModel(String businessEntityId, boolean isCaseOwnerEnabled) {
    super();
    this.businessEntityId = businessEntityId;
    data = new ArrayList<>();
    criteria = buildInitSearchCriteria(isCaseOwnerEnabled);
    setAdminQuery(PermissionUtils.checkReadAllCasesPermission());
    setInvolvedApplications();
  }

  protected void setInvolvedApplications() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    criteria.setApps(service.findActiveIvyAppsBasedOnConfiguration(Ivy.session().getSessionUserName()));
  }

  public void setAdminQuery(boolean isAdminQuery) {
    criteria.extendStatesQueryByPermission(isAdminQuery);
  }

  private CaseSearchCriteria buildInitSearchCriteria(boolean isCaseOwnerEnabled) {
    CaseSearchCriteria crit = new CaseSearchCriteria();
    crit.setInvolvedUsername(Ivy.session().getSessionUserName());
    crit.setBusinessCase(true);
    crit.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
    crit.setSortField(CaseSortField.ID.toString());
    crit.setSortDescending(true);
    crit.setCaseOwnerEnabled(isCaseOwnerEnabled);
    return crit;
  }

  @Override
  public List<ICase> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    if (first == 0) {
      initializedDataModel(criteria);
    }

    List<ICase> foundCases = findCases(criteria, first, pageSize);
    data.addAll(foundCases);
    return foundCases;
  }

  @SuppressWarnings("unchecked")
  private List<ICase> findCases(CaseSearchCriteria criteria, int first, int pageSize) {
    int startIndex = first;
    int count = pageSize;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    Map<String, Object> params = new HashMap<>();
    params.put("startIndex", startIndex);
    params.put("count", count);
    params.put("caseSearchCriteria", criteria);
    Map<String, Object> response =
        IvyAdapterService.startSubProcess(
            "findCasesByCriteria(com.axonivy.portal.component.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer)", params, new ArrayList<>());
    return (List<ICase>) response.get("cases");
  }

  public void setSorting(String sortedField, boolean descending) {
    criteria.setSortField(sortedField);
    criteria.setSortDescending(descending);
  }

  public String getSortField() {
    return criteria.getSortField();
  }

  public boolean isSortDescending() {
    return criteria.isSortDescending();
  }

  private int getCaseCount(CaseSearchCriteria criteria) {
    Map<String, Object> params = new HashMap<>();
    params.put("caseSearchCriteria", criteria);
    Map<String, Object> response =
        IvyAdapterService.startSubProcess("countCasesByCriteria(com.axonivy.portal.component.ivydata.searchcriteria.CaseSearchCriteria)", params,
            new ArrayList<>());
    return ((Long) response.get("totalCases")).intValue();
  }

  private void initializedDataModel(CaseSearchCriteria criteria) {
    data.clear();
    buildQueryToSearchCriteria();
    setRowCount(getCaseCount(criteria));
  }

  private void buildQueryToSearchCriteria() {
    if (criteria.getCustomCaseQuery() == null) {
      CaseQuery caseQuery = CaseQuery.businessCases();
      caseQuery.where().customField().textField(AdditionalProperty.CASE_BUSINESS_ENTITY_PROPERTY.toString())
          .isEqual(businessEntityId);
      criteria.setCustomCaseQuery(caseQuery);
    }
    
    criteria.setFinalCaseQuery(criteria.createQuery());
  }

  public String getBusinessEntityId() {
    return businessEntityId;
  }

  public void setBusinessEntityId(String businessEntityId) {
    this.businessEntityId = businessEntityId;
  }
  
  public boolean isSelectedColumn(String column) {
    return StringUtils.isNotEmpty(column);
  }
  
  public boolean isAutoHideColumns() {
    return false;
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
