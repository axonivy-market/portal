package ch.ivy.addon.portalkit.datamodel;

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

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.service.CaseQueryService;
import ch.ivy.addon.portalkit.support.CaseQueryCriteria;
import ch.ivy.ws.addon.CaseSearchCriteria;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

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

  public CaseLazyDataModel() {
    this("case-widget");
  }

  public CaseLazyDataModel(String caseWidgetComponentId) {
    super();
    data = new ArrayList<>();
    displayedCaseMap = new HashMap<>();
    notDisplayedCaseMap = new HashMap<>();
    this.caseWidgetComponentId = caseWidgetComponentId;
    searchCriteria = buildSearchCriteria();
    queryCriteria = buildQueryCriteria();
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

  private Optional<Comparator<? super RemoteCase>> getComparatorForSorting() {
    Comparator<? super RemoteCase> comparator = null;
    if (CaseSortField.NAME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getName);
    } else if (CaseSortField.ID.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getId);
    } else if (CaseSortField.START_TIME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getStartTimestamp);
    } else if (CaseSortField.END_TIME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(RemoteCase::getEndTimestamp);
    } else if (CaseSortField.CREATOR.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = naturalOrderNullsFirst(caseCreator());
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
      if(StringUtils.isNotEmpty(remoteCase.getCreatorFullName())) {
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
    List<RemoteCase> cases = findCaseCaller.invokeComponentLogic(caseWidgetComponentId, "#{logic.findCases}",
        new Object[] { startIndex, count, criteria, serverId });
    return cases;
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

  private GlobalCaseId globalCaseId(RemoteCase oneCase) {
    return new GlobalCaseId(oneCase.getServer().getId(), oneCase.getId(), oneCase.isBusinessCase());
  }

  private int getCaseCount(CaseSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countCaseCaller = new IvyComponentLogicCaller<>();
    Long caseCount = countCaseCaller.invokeComponentLogic(caseWidgetComponentId, "#{logic.countCases}",
        new Object[] { criteria, serverId });
    return caseCount.intValue();
  }

  private CaseSearchCriteria buildSearchCriteria() {
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

  public void setKeyword(String keyWord) {
	  queryCriteria.setKeyword(keyWord.trim());
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
	searchCriteria.setIgnoreInvolvedUser(ignoreInvolvedUser);
    if (ignoreInvolvedUser) {
    queryCriteria.addIncludedStates(Arrays.asList(CaseState.DONE));
    }
  }

  public void setTaskId(Long taskId) {
	queryCriteria.setTaskId(taskId);
	queryCriteria.addIncludedStates(Arrays.asList(CaseState.DONE));
  }

  public void setCaseId(Long caseId) {
	queryCriteria.setCaseId(caseId);
	queryCriteria.setIncludedStates(new ArrayList<>());
  }

  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }

  public void setInvolvedApplications(String... involvedApplications) {
	  searchCriteria.setInvolvedApplications(involvedApplications);
  }

  public String getSortField() {
    return queryCriteria.getSortField();
  }

  public boolean isSortDescending() {
    return queryCriteria.isSortDescending();
  }

  public void getQueryCriteria(CaseQueryCriteria queryCriteria) {
	  this.queryCriteria = queryCriteria;
  }
  
  public CaseQueryCriteria setQueryCriteria() {
	  return queryCriteria;
  }
  
  protected CaseQueryCriteria buildQueryCriteria() {
    CaseQueryCriteria jsonQueryCriteria = new CaseQueryCriteria();
    jsonQueryCriteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING)));
    jsonQueryCriteria.setSortField(CaseSortField.ID.toString());
    jsonQueryCriteria.setSortDescending(true);
    return jsonQueryCriteria;
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

    CaseQuery caseQuery = buildCaseQuery();
    searchCriteria.setJsonQuery(caseQuery.asJson());
  }
  
  private CaseQuery buildCaseQuery() {
    CaseQuery caseQuery = CaseQueryService.service().createQuery(queryCriteria);
    return caseQuery;
  }
  
}
