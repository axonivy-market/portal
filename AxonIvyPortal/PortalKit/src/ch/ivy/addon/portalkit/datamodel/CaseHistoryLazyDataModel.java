package ch.ivy.addon.portalkit.datamodel;

import static ch.ivy.addon.portalkit.comparator.RemoteCaseComparator.comparatorString;
import static ch.ivy.addon.portalkit.comparator.RemoteCaseComparator.naturalOrderNullsFirst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.service.CaseQueryService;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.support.CaseQueryCriteria;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.ws.addon.CaseSearchCriteria;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseHistoryLazyDataModel extends LazyDataModel<RemoteCase> {

  private static final long serialVersionUID = 3023805225538732101L;
  private static final int BUFFER_LOAD = 10;

  private String businessEntityId;
  private final List<RemoteCase> data;
  private Map<GlobalCaseId, RemoteCase> displayedCaseMap;
  private Map<GlobalCaseId, RemoteCase> notDisplayedCaseMap;

  private int rowIndex;
  private CaseSearchCriteria searchCriteria;
  private CaseQueryCriteria queryCriteria;
  private Long serverId;

  public CaseHistoryLazyDataModel(String businessEntityId) {
    super();
    serverId = SecurityServiceUtils.getServerIdFromSession();
    this.businessEntityId = businessEntityId;
    data = new ArrayList<>();
    displayedCaseMap = new HashMap<>();
    notDisplayedCaseMap = new HashMap<>();
    searchCriteria = buildInitSearchCriteria();
    queryCriteria = buildInitQueryCriteria();
    setIgnoreInvolvedUser(PermissionUtils.checkReadAllCasesPermission());
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    searchCriteria.setIgnoreInvolvedUser(ignoreInvolvedUser);
    if (ignoreInvolvedUser && !queryCriteria.getIncludedStates().contains(CaseState.DONE)) {
      queryCriteria.addIncludedStates(Arrays.asList(CaseState.DONE));
    }
  }

  private CaseSearchCriteria buildInitSearchCriteria() {
    CaseSearchCriteria crit = new CaseSearchCriteria();
    crit.setInvolvedUsername(Ivy.session().getSessionUserName());
    crit.setBusinessCase(true);
    String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
    if (applicationName != null) {
      crit.setInvolvedApplications(new String[] {applicationName});
    }
    return crit;
  }

  @Override
  public List<RemoteCase> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    if (first == 0) {
      initializedDataModel(searchCriteria);
    }

    List<RemoteCase> foundCases = findCases(first, pageSize, searchCriteria);
    putCasesToNotDisplayedTaskMap(foundCases);
    List<RemoteCase> notDisplayedCases = new ArrayList<>();
    notDisplayedCases.addAll(notDisplayedCaseMap.values());
    Optional<Comparator<? super RemoteCase>> comparator = getComparatorForSorting();
    comparator.ifPresent(c -> notDisplayedCases.sort(c));
    List<RemoteCase> displayedCases = getDisplayedCases(notDisplayedCases, pageSize);

    storeDisplayedCases(notDisplayedCases);
    return displayedCases;
  }

  private void initializedDataModel(CaseSearchCriteria criteria) {
    data.clear();
    displayedCaseMap.clear();
    notDisplayedCaseMap.clear();
    buildQueryToSearchCriteria();
    setRowCount(getCaseCount(criteria));
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

  @Override
  public void setRowIndex(int index) {
    int rowIndex = index;
    if (index >= data.size()) {
      rowIndex = -1;
    }
    this.rowIndex = rowIndex;
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

  @SuppressWarnings("unchecked")
  private List<RemoteCase> findCases(int first, int pageSize, CaseSearchCriteria criteria) {
    int startIndex = first - BUFFER_LOAD;
    int count = pageSize + BUFFER_LOAD;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    Map<String, Object> params = new HashMap<>();
    params.put("serverId", serverId);
    params.put("startIndex", startIndex);
    params.put("count", count);
    params.put("caseSearchCriteria", criteria);
    Map<String, Object> response =
        IvyAdapterService.startSubProcess(
            "findCasesByCriteria(Long,Integer,Integer,ch.ivy.ws.addon.CaseSearchCriteria)", params, new ArrayList<>());
    return (List<RemoteCase>) response.get("cases");
  }

  public void setSorting(String sortedField, boolean descending) {
    queryCriteria.setSortField(sortedField);
    queryCriteria.setSortDescending(descending);
  }

  public String getSortField() {
    return queryCriteria.getSortField();
  }

  public boolean isSortDescending() {
    return queryCriteria.isSortDescending();
  }

  private int getCaseCount(CaseSearchCriteria criteria) {
    Map<String, Object> params = new HashMap<>();
    params.put("serverId", serverId);
    params.put("caseSearchCriteria", criteria);
    Map<String, Object> response =
        IvyAdapterService.startSubProcess("countCasesByCriteria(Long,ch.ivy.ws.addon.CaseSearchCriteria)", params,
            new ArrayList<>());
    return ((Long) response.get("caseCount")).intValue();
  }

  private void storeDisplayedCases(List<RemoteCase> displayedCases) {
    data.addAll(displayedCases);
    for (RemoteCase oneCase : displayedCases) {
      displayedCaseMap.put(globalCaseId(oneCase), oneCase);
    }
  }

  private GlobalCaseId globalCaseId(RemoteCase oneCase) {
    return new GlobalCaseId(oneCase.getServer().getId(), oneCase.getId(), oneCase.isBusinessCase());
  }

  private Optional<Comparator<? super RemoteCase>> getComparatorForSorting() {
    Comparator<? super RemoteCase> comparator = null;
    if (CaseSortField.NAME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorString(RemoteCase::getName);
    } else if (CaseSortField.ID.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getId);
    } else if (CaseSortField.START_TIME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getStartTimestamp);
    } else if (CaseSortField.CREATOR.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorString(caseCreator());
    } else if (CaseSortField.STATE.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getState);
    } else if (CaseSortField.PROCESS_NAME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getProcessName);
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

  private CaseQueryCriteria buildInitQueryCriteria() {
    CaseQueryCriteria jsonQueryCriteria = new CaseQueryCriteria();
    jsonQueryCriteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING,
        CaseState.DONE)));
    jsonQueryCriteria.setSortField(CaseSortField.ID.toString());
    jsonQueryCriteria.setSortDescending(true);
    return jsonQueryCriteria;
  }

  private void buildQueryToSearchCriteria() {
    if (queryCriteria.getCaseQuery() == null) {
      CaseQuery caseQuery = CaseQuery.create();
      caseQuery.where().additionalProperty(AdditionalProperty.CASE_BUSINESS_ENTITY_PROPERTY.toString())
          .isEqual(businessEntityId);
      queryCriteria.setCaseQuery(caseQuery);
    }
    queryCriteria.setIncludedStates(new ArrayList<>());
    CaseQuery caseQuery = CaseQueryService.service().createQuery(queryCriteria);
    searchCriteria.setJsonQuery(caseQuery.asJson());
  }

  public String getBusinessEntityId() {
    return businessEntityId;
  }

  public void setBusinessEntityId(String businessEntityId) {
    this.businessEntityId = businessEntityId;
  }
}
