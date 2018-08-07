package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import ch.ivy.ws.addon.CaseSearchCriteria;
import ch.ivy.ws.addon.CaseState;
import ch.ivyteam.ivy.environment.Ivy;

public class CaseLazyDataModel extends LazyDataModel<RemoteCase> {
  private static final int BUFFER_LOAD = 10;
  private static final long serialVersionUID = 1L;
  private final List<RemoteCase> data;
  private Map<GlobalCaseId, RemoteCase> displayedCaseMap;
  private Map<GlobalCaseId, RemoteCase> notDisplayedCaseMap;

  private String caseWidgetComponentId;
  private int rowIndex;
  private CaseSearchCriteria criteria;

  public CaseLazyDataModel() {
    this("case-widget");
  }

  public CaseLazyDataModel(String caseWidgetComponentId) {
    super();
    data = new ArrayList<>();
    displayedCaseMap = new HashMap<>();
    notDisplayedCaseMap = new HashMap<>();
    this.caseWidgetComponentId = caseWidgetComponentId;
    criteria = buildCriteria();
  }

  @Override
  public List<RemoteCase> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    if (first == 0) {
      initializedDataModel(criteria);
    }

    List<RemoteCase> foundCases = findCases(first, pageSize, criteria);
    putCasesToNotDisplayedTaskMap(foundCases);
    List<RemoteCase> notDisplayedCases = new ArrayList<>();
    notDisplayedCases.addAll(notDisplayedCaseMap.values());
    List<RemoteCase> displayedCases = getDisplayedCases(notDisplayedCases, pageSize);
    storeDisplayedCases(displayedCases);

    return displayedCases;
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
            count, criteria});
    return cases;
  }

  private void initializedDataModel(CaseSearchCriteria criteria) {
    data.clear();
    displayedCaseMap.clear();
    notDisplayedCaseMap.clear();
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
    return new GlobalCaseId(oneCase.getServer().getId(), oneCase.getId());
  }

  private int getCaseCount(CaseSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countCaseCaller = new IvyComponentLogicCaller<>();
    Long caseCount =
        countCaseCaller.invokeComponentLogic(caseWidgetComponentId, "#{logic.countCases}", new Object[] {criteria});
    return caseCount.intValue();
  }

  private CaseSearchCriteria buildCriteria() {
    CaseSearchCriteria crit = new CaseSearchCriteria();
    crit.setInvolvedUsername(Ivy.session().getSessionUserName());
    crit.setIncludedStates(new CaseState[] {CaseState.CREATED, CaseState.RUNNING});
    return crit;
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
    criteria.setKeyword(keyWord);
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    criteria.setIgnoreInvolvedUser(ignoreInvolvedUser);
    if (ignoreInvolvedUser) {
      criteria.addIncludedStates(CaseState.DONE);
    }
  }

  public void setTaskId(Long taskId) {
    criteria.setTaskId(taskId);
    criteria.addIncludedStates(CaseState.DONE);
  }

  public void setCaseId(Long caseId) {
    criteria.setCaseId(caseId);
  }
}
